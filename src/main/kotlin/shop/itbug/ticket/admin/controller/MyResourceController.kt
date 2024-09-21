package shop.itbug.ticket.admin.controller

import cn.hutool.core.date.DateUtil
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.apache.commons.lang3.StringUtils
import org.springframework.data.domain.Page
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.annotation.GetLoginUser
import shop.itbug.ticket.controller.getCurrentHost
import shop.itbug.ticket.entry.FileInfo
import shop.itbug.ticket.entry.FileInfoSaveConfig
import shop.itbug.ticket.entry.MyResources
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.entry.useagent.MyUseAgent
import shop.itbug.ticket.entry.useagent.myUseAgent
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.model.params.AddPostParamsModel
import shop.itbug.ticket.model.params.IdBody
import shop.itbug.ticket.service.FileInfoService
import shop.itbug.ticket.service.MianjiService
import shop.itbug.ticket.service.MyResourceService
import shop.itbug.ticket.service.ResourcesCategoryService
import shop.itbug.ticket.utils.*

/**
 * @author eee
 */
@RestController
@RequestMapping("/api/resource")
@Tag(name = "动态接口(管理员)")
class MyResourceController {
    @Resource
    private lateinit var myResourceService: MyResourceService

    @Resource
    private lateinit var resourcesCategoryService: ResourcesCategoryService

    @Resource
    private lateinit var fileInfoService: FileInfoService

    @Resource
    private lateinit var mianjiService: MianjiService

    /**
     * 列表查询
     *
     * @param pageModel 分页数据
     * @param resources 模糊查询对象
     * @return 查询结果
     */
    @GetMapping("/list")
    @Operation(summary = "查询列表")
    fun list(pageModel: PageModel, resources: MyResources?, categoryId: Long?): Result<Page<MyResources>> {
        if (categoryId != null) {
            val byId = resourcesCategoryService.findById(categoryId)
            if (resources != null) {
                resources.category = byId
            }
        }
        val all = myResourceService.findAll(pageModel, resources)
        return Result.ok(all)
    }


    data class AddPostResult(val images: HashSet<FileInfo>?, val post: MyResources)

    /**
     * 发布动态。
     * @param user 已登录用户
     * @param paramsModel 参数
     * @param bindingResult 验证参数
     * @return 添加结果
     */
    @PostMapping("/add-post")
    @Operation(summary = "发布动态")
    fun addPost(
        @GetLoginUser @Parameter(hidden = true) user: User,
        @Valid @ModelAttribute paramsModel: AddPostParamsModel,
        @RequestParam(name = "pictures", required = false) pictures: List<MultipartFile> = emptyList(),
        @Parameter(hidden = true) bindingResult: BindingResult,
        request: HttpServletRequest,
    ): Result<AddPostResult> {
        bindingResult.verify()
        paramsModel.printToConsole()
        val resourcesCategory = paramsModel.categoryId?.let { resourcesCategoryService.findById(it) }
            ?: throw BizException("获取资源失败")
        val config = FileInfoSaveConfig(user,request.getCurrentHost(), resourcesCategory.name ?: "postImage",false)
        val files =
            fileInfoService.saveAllFiles(pictures, config).toHashSet()
        val post = paramsModel.id?.let { myResourceService.findById(it) } ?: MyResources()
        post.content = paramsModel.content
        post.createDate = paramsModel.createDate ?: DateUtil.date()
        post.clickCount = paramsModel.clickCount
        post.type = if (StringUtils.isBlank(paramsModel.type)) "post" else paramsModel.type
        post.images = files
        post.authority = paramsModel.authority
        post.label = paramsModel.label
        post.user = user
        post.category = resourcesCategory
        post.thumbnailImage = paramsModel.thumbnailImage
        post.title = paramsModel.title
        post.description = paramsModel.description
        post.links = paramsModel.links
        post.browserUrl = paramsModel.browserUrl
        post.updateDate = paramsModel.updateDate ?: DateUtil.date()
        post.useAgent = request.myUseAgent()
        paramsModel.mianji?.let {
            post.mianji = mianjiService.save(it)
        }
        val savedPost = myResourceService.saveOrUpdate(post)
        return Result.ok(AddPostResult(images = files, post = savedPost))
    }

    /**
     * 删除一个资源
     *
     * @return 操作结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除资源")
    fun delete(idBody: IdBody): Result<String> {
        try {
            myResourceService.deleteMyResource(idBody.id)
            return Result.ok("删除成功！")
        } catch (e: Exception) {
            throw BizException(e)
        }
    }

    //
    @GetMapping("/labels")
    @Operation(summary = "获取label不为空的全部文章")
    fun findAllLabelPosts(): ResultJSON<*> {
        return myResourceService.finAllWithLabelNotEmpty().successResult()
    }

    @PostMapping("/setUseAgent")
    @Operation(summary = "设置动态ua")
    fun setUseAgent(request: HttpServletRequest, @RequestBody idBody: IdBody): R<MyUseAgent> {
        val findById = myResourceService.findById(idBody.id) ?: throw BizException(CommonEnum.NOT_FOUND)
        val ua = request.myUseAgent() ?: throw BizException("获取ua失败")
        findById.useAgent = ua
        myResourceService.saveOrUpdate(findById)
        return ua.successResult("设置成功")
    }
}