package shop.itbug.ticket.admin

import cn.hutool.core.comparator.VersionComparator
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length
import org.springframework.data.domain.Page
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.controller.UserAdminController
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.*
import shop.itbug.ticket.entry.blog.Text
import shop.itbug.ticket.entry.project.Project
import shop.itbug.ticket.entry.project.ProjectService
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.model.params.FindCommentParams
import shop.itbug.ticket.service.*
import shop.itbug.ticket.service.blog.TextService
import shop.itbug.ticket.utils.*

@RestController
@RequestMapping("/api/public")
@Tag(name = "开放接口")
class PublicApiController {
    @Resource
    private lateinit var userService: UserService

    @Resource
    private lateinit var commentService: CommentService

    @Resource
    private lateinit var textService: TextService

    @Resource
    private lateinit var versionService: VersionService

    @Resource
    private lateinit var projectService: ProjectService

    @Resource
    private lateinit var resourcesCategoryService: ResourcesCategoryService

    @Resource
    private lateinit var myResourceService: MyResourceService

    @Resource
    private lateinit var friendLinkService: FriendLinkService

    @Operation(summary = "创建管理员账号")
    @PostMapping("/create-admin")
    fun createAdminAccount(@RequestBody params: UserAdminController.CreateAdminAccountParam): ResultJSON<User> {
        val user = userService.createAdminAccount(params.username, params.password)
        return ResultJSON.ok(user)
    }


    @GetMapping("/has-admin")
    @Operation(summary = "是否注册过管理员账号,true表示有管理员账号")
    fun isRegisterAdminAccount(): ResultJSON<Boolean> {
        val findUsersByType = userService.findUsersByType(UserAccountType.Admin.type)
        return ResultJSON.ok(findUsersByType.isNotEmpty())
    }


    @GetMapping("/find-resource-comment")
    @Operation(summary = "获取动态的评论")
    fun findAllByResource(pageModel: PageModel, resourceId: Long): ResultJSON<Page<Comment>> {
        return commentService.findAllByType(Comment.resourceTypeKey, "$resourceId", pageModel).successResult()
    }


    @Schema(name = "访客评论参数")
    data class AddCommentWithVisitorsParam(
        @Schema(description = "邮箱")
        @NotBlank(message = "请输入邮箱")
        val email: String,
        @Schema(description = "用户网站,可空")
        val website: String?,
        @Schema(description = "评论内容")
        @NotBlank(message = "请输入评论内容")
        val content: String,
        @Schema(description = "昵称")
        @NotBlank(message = "请输入昵称")
        @Length(min = 1, max = 12, message = "昵称在1-12个字符之间")
        val name: String,
        @Schema(description = "类型,比如友链:friend")
        @NotBlank(message = "请输入评论类型")
        val type: String,
        @Schema(description = "查询评论的主键ID")
        @NotNull(message = "请输入主键")
        val findKey: String?,
        @Schema(description = "头像,可空")
        val avatarURL: String?,
        @Schema(description = "如果存在,则是回复某个评论")
        var parentCommentId: Long?
    )

    @Operation(summary = "访客评论", description = "不需要登录")
    @PostMapping("/add-comment")
    fun addCommentWithVisitors(@RequestBody param: AddCommentWithVisitorsParam): R<Comment> {
        val auth = SecurityContextHolder.getContext().authentication.principal
        val save = commentService.replyComment(param, auth as? User)
        return save.successResult("评论成功")
    }

    /**
     * 查询评论列表
     * @param params 参数
     * @param bindingResult 验证
     * @return 结果
     */
    @GetMapping("/find-comment")
    @Operation(summary = "查询评论列表(公开)")
    fun findComment(
        @Validated params: FindCommentParams,
        bindingResult: BindingResult
    ): Result<BlogUtil.CoverToPage<Comment>> {
        bindingResult.verify()
        val comments = commentService.selectList(params)
        val result = BlogUtil.coverToPage(comments)
        return result.successResult()
    }


    @Schema(name = "全局配置")
    data class AppConfigResult(
        @Schema(description = "公告") val notice: Text? = null,
        @Schema(description = "最新版本,需要传platform请求头才能获取") val version: VersionModel? = null,
        @Schema(description = "项目列表") val projects: List<Project> = emptyList()
    )

    @Schema(name = "版本信息")
    data class VersionModel(
        @Schema(description = "最新的一个版本") val lastVersion: Version? = null,
        @Schema(description = "是否需要弹出更新弹出") val showUpdateModel: Boolean? = false,
        @Schema(description = "其他版本") val otherVersions: List<Version>? = null
    )


    @GetMapping("/config")
    @Operation(summary = "APP全局配置")
    fun getAllConfig(httpRequest: HttpServletRequest): R<AppConfigResult> {
        val wrapper = HttpServletRequestWrapper(httpRequest)
        val platform = wrapper.getHeader("platform")
        val version = wrapper.getHeader("version")
        if(platform == null || version == null){
            throw BizException("缺少请求头")
        }
        val lastNewVersion = versionService.findNewVersion(platform)
        val update = lastNewVersion?.let {
            val v = it.versionNumber ?: "0.0.0"
            VersionComparator.INSTANCE.compare(v, version) == 1
        } ?: false //是否需要更新,true为需要,false不需要
        val otherVersion = versionService.findAllByPlatform(platform)
        val config = AppConfigResult(
            notice = textService.findByName("notice"),
            version = VersionModel(
                lastVersion = lastNewVersion,
                showUpdateModel = update,
                otherVersions = otherVersion
            ),
            projects = projectService.findAll()
        )
        return R.ok(config)
    }


    @GetMapping("/findByType-pub")
    @Operation(summary = "获取群组(公开),需要传入类型")
    fun findListByType(type: String) : ResultJSON<List<ResourcesCategoryCounter>> {
        return resourcesCategoryService.selectCategoryResourceWithType(type).successResult()
    }


    @GetMapping("/find-resource-by-id")
    @Operation(summary = "查看资源对象")
    fun findResourceById(id: Long) : R<MyResources> {
        return myResourceService.findById(id)?.successResult("查找成功") ?: throw BizException("资源已被删除或者不存在")
    }

    /**
     * 获取全部友链
     * @param friendLink 查询条件
     * @return  友链集合列表
     */
    @GetMapping("/friends")
    @Operation(summary = "获取友链")
    fun findAll(friendLink: FriendLink?): Result<List<FriendLink>> {
        return Result.ok(friendLinkService.findAllBy(friendLink))
    }


}