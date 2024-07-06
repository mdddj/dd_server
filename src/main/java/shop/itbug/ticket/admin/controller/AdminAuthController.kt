package shop.itbug.ticket.admin.controller

import cn.hutool.core.date.DateUtil
import com.alibaba.fastjson2.JSONObject
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import org.apache.commons.lang3.StringUtils
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import shop.itbug.ticket.admin.model.NavLink
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.UpdatePasswordParam
import shop.itbug.ticket.annotation.GetLoginUser
import shop.itbug.ticket.annotation.ServiceHost
import shop.itbug.ticket.controller.getCurrentHost
import shop.itbug.ticket.entry.*
import shop.itbug.ticket.entry.blog.Blog
import shop.itbug.ticket.entry.blog.BlogTag
import shop.itbug.ticket.entry.blog.Category
import shop.itbug.ticket.entry.blog.Text
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.model.blog.PushNewBlogParams
import shop.itbug.ticket.model.params.CreateSubDirectorParam
import shop.itbug.ticket.model.params.IdBody
import shop.itbug.ticket.service.*
import shop.itbug.ticket.service.blog.BlogService
import shop.itbug.ticket.service.blog.CategoryService
import shop.itbug.ticket.service.blog.TagService
import shop.itbug.ticket.service.blog.TextService
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.successResult
import shop.itbug.ticket.utils.verify
import java.io.IOException
import java.util.*
import java.util.function.Consumer

/**
 * 需要管理员的请求控制器
 *
 * @author eee
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "管理员超级接口")
class AdminAuthController {
    @Resource
    lateinit var categoryService: CategoryService


    @Resource
    lateinit var resourcesCategoryService: ResourcesCategoryService

    @Resource
    lateinit var fileInfoService: FileInfoService

    @Resource
    lateinit var myResourceService: MyResourceService

    @Resource
    lateinit var textService: TextService

    @Resource
    lateinit var blogService: BlogService

    @Resource
    lateinit var tagService: TagService

    @Resource
    lateinit var userService: UserService

    @Resource
    lateinit var friendLinkService: FriendLinkService

    /**
     * 添加一个新用户
     *
     * @param user 用户数据
     * @return 保存后的信息
     */
    @PostMapping("/user-addnew")
    @Operation(summary = "添加新用户")
    fun addNewUser(@RequestBody user: User): Result<String> {
        user.loginNumber.let { userService.registor(it, user.password, user.picture) }
        return Result.successMessage("注册成功")
    }

    /**
     * 修改用户的密码
     * @param passwordParam 参数
     * @param bindingResult 验证结果
     * @return 处理结果
     */
    @PostMapping("/user-update-pass")
    @Operation(summary = "修改用户密码")
    fun updatePassword(
        @GetLoginUser @Parameter(hidden = true) user: User,
        @RequestBody @Validated passwordParam: UpdatePasswordParam,
        @Parameter(hidden = true) bindingResult: BindingResult
    ): Result<*> {
        bindingResult.verify()
        userService.updatePassword(user, passwordParam.currentPass, passwordParam.rePassword)
        return R.successResult("修改成功")
    }

    /**
     * 删除一篇博客
     */
    @DeleteMapping("/blog-delete")
    @Operation(summary = "删除博客")
    fun deleteBlog(@RequestBody idBody: IdBody): Result<Boolean> {
        try {
            val blogId = idBody.id
            blogService.delete(blogId)
            return R(true, "删除成功")
        } catch (e: Exception) {
            return R.err("删除博客失败:$e", false)
        }
    }

    /**
     * 发布一篇新博客
     *
     * @param params 文章相关的值
     * @return 返回新的
     */
    @PostMapping("/blog-push-new")
    @Operation(summary = "发布新博客")
    fun pushBlog(@RequestBody @Validated params: PushNewBlogParams, bindingResult: BindingResult): Result<Blog> {
        bindingResult.verify()
        val tagsSet = HashSet<BlogTag>()
        params.tags.forEach(Consumer { s: String -> tagsSet.add(BlogTag(s)) })
        val save = tagService.save(tagsSet)
        try {
            return if (params.id != null) {
                val category = categoryService.select(params.categoryId)
                val updateBlog = blogService.select(params.id)
                updateBlog.tags = save
                updateBlog.title = params.title
                updateBlog.content = params.content
                updateBlog.category = category
                updateBlog.aliasString = params.alias
                updateBlog.html = params.html
                updateBlog.thumbnail = params.thumbnail
                val blog = blogService.update(updateBlog)
                Result.ok(blog)
            } else {
                // 执行新增
                val blog = blogService.create(
                    params.title, params.content,
                    params.categoryId, params.alias, save, params.html
                )
                if (params.thumbnail.isNotEmpty()) {
                    blogService.addThumbnail(blog.id!!, params.thumbnail)
                }
                Result.ok(blog)
            }
        } catch (e: Exception) {
            throw BizException(e)
        }
    }

    /**
     * 保存或者删除一个字典对象
     *
     * @param text text
     * @return json
     */
    @PostMapping("/text-update")
    @Operation(summary = "保存&删除字典")
    fun saveAndUpdate(@RequestBody @Validated text: Text, bindingResult: BindingResult): Result<*> {
        if (bindingResult.hasErrors()) {
            return Result.paramValidError(bindingResult)
        }
        val findedByName = text.name?.let { textService.findByName(it) }
        if (findedByName != null && text.id == null) {
            return Result<Any>(CommonEnum.RESOURCES_REPETITION)
        }

        val isEncryptionText = text.isEncryptionText
        if (isEncryptionText != null && isEncryptionText) {
            val viewPassword = text.viewPassword
            if (StringUtils.isBlank(viewPassword)) {
                return Result.err("请设置查阅密码再重试")
            } else {
                //对密码进行加密
                val encodePassword = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(viewPassword)
                text.viewPassword = encodePassword
                text.originPassword = viewPassword
                text.isEncryptionText = true
            }
        }

        //如果修改对象, 不使用密码, 则将密码等数据清空
        if (text.id != null && isEncryptionText != null && !isEncryptionText) {
            text.originPassword = null
            text.viewPassword = null
            text.isEncryptionText = false
        }
        val saved = textService.save(text)
        return saved.successResult(if (text.id != null) "修改成功" else "新增成功")
    }


    /**
     * 查看某个字典的原始密码,需要管理员权限
     */
    @GetMapping("/text/origin-password-select")
    @Operation(summary = "查看字典原始密码")
    fun selectTextModelOriginPassword(@RequestParam("key") name: String): Result<*> {
        val originPassword = textService.selectTextModelOriginPassword(name)
        return Result.ok(originPassword)
    }

    /**
     * 删除某个对象
     *
     * @param jsonObject 删除的参数
     * @return 操作结果
     */
    @DeleteMapping("/text-delete")
    @Operation(summary = "删除字典")
    fun deleteText(@RequestBody jsonObject: JSONObject): Result<*> {
        val id = jsonObject.getLong("id")
        textService.deleteById(id)
        return Result.ok("删除成功")
    }

    /**
     * 删除某个群组
     *
     * @param jsonObject 请求id
     * @return 操作结果
     */
    @DeleteMapping("/res-cate-delete")
    @Operation(summary = "删除群组分类")
    fun deleteResourceCategory(@RequestBody jsonObject: JSONObject): Result<*> {
        val id = jsonObject.getLong("id")
        if (id != null) {
            // 判断该群组下面是否存在资源文件
            val byId = resourcesCategoryService.findById(id)
            val listByResourceCategory = myResourceService.findListByResourceCategory(byId, PageModel(0, 10))
            if (listByResourceCategory.totalElements != 0L) {
                return Result.err("删除失败,如果需要删除请先删除该群组下面的全部资源")
            }
            resourcesCategoryService.deleteById(id)
            return Result.ok("删除成功")
        }
        return Result<Any>(CommonEnum.NOT_FOUND)
    }

    /**
     * 新增或者修改一个群组
     *
     * @param resourcesCategory 信息
     * @return 处理结果
     */
    @PostMapping("/res-cate-save")
    @Operation(summary = "新增&修改群组分类")
    fun updateAndSave(
        @RequestBody @Validated resourcesCategory: ResourcesCategory,
        bindingResult: BindingResult
    ): Result<*> {
        if (bindingResult.hasErrors()) {
            return Result.paramValidError(bindingResult)
        }

        // 如果存在父文件夹,需要获取父文件夹的信息,并且获取json string 字段
        val parentNode = resourcesCategory.parentNode
        val byId: ResourcesCategory?
        if (parentNode != null) {
            byId = parentNode.id?.let { resourcesCategoryService.findById(it) }
            parentNode.id?.let { gengerNavString(it, TreeSet()) }
            resourcesCategory.parentNode = byId
        }
        resourcesCategory.level =
            if (parentNode == null) 1 else (if (parentNode.level == null) 1 else parentNode.level)?.plus(1)
        var save = resourcesCategoryService.save(resourcesCategory)
        if ("folder" == save.type) {
            save.id?.let { gengerNavString(it, TreeSet()) }
            save = resourcesCategoryService.save(save)
        }
        return Result.ok(save)
    }


    // 生成文件夹导航
    private fun gengerNavString(id: Long, navs: TreeSet<NavLink>): TreeSet<NavLink> {
        val byId = resourcesCategoryService.findById(id)
        if (byId.parentNode != null) {
            val navLink1 = NavLink()
            navLink1.name = byId.name
            navLink1.id = byId.id
            navs.add(navLink1)
            return gengerNavString(byId.parentNode!!.id!!, navs)
        } else {
            val navLink = NavLink()
            navLink.id = byId.id
            navLink.name = byId.name
            navs.add(navLink)
        }
        return navs
    }

    /**
     * 添加或者修改一个资源
     *
     * @param myResources 请求参数
     * @return 处理结果
     */
    @PostMapping("/resource-save")
    @Operation(summary = "修改动态")
    fun save(
        @RequestBody @Validated myResources: MyResources,
        @GetLoginUser user: User
    ): Result<*> {
        val model = myResourceService.findById(myResources.id!!) ?: throw BizException("获取动态数据失败")
        model.updateDate = model.id?.let { DateUtil.date() } //修改更新时间
        myResources.content?.let { model.content = it }//修改正文内容
        myResources.label?.let { model.label = it }  //修改标签
        myResources.title?.let { model.title = it } //修改标题
        myResources.description?.let { model.description = it }
        myResources.links?.let { model.links = it }
        myResources.browserUrl?.let { model.browserUrl = it }
        val myResources1 = myResourceService.saveOrUpdate(model)
        return Result.ok(myResources1)
    }


    @PostMapping("/resource-thumbnail")
    @Operation(summary = "修改缩略图")
    fun updateResourceCategoryThumbnailImage(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("id") id: Long,
        httpServletRequest: HttpServletRequest,
        @GetLoginUser user: User
    ): Result<ResourcesCategory> {
        val config = FileInfoSaveConfig(
            user = user, host = httpServletRequest.getCurrentHost(), folderName = "resourceCategoryThumbnail"
        )
        val linkUrl =
            fileInfoService.getLinkUrl(file, config)
                ?: throw BizException("上传图片失败")
        val resourceCategory = resourcesCategoryService.findById(id)
        resourceCategory.logo = linkUrl.thumbnail
        resourceCategory.fileInfo = linkUrl
        return resourcesCategoryService.save(resourceCategory).successResult()
    }

    /**
     * 上传一个新版本
     *
     * @return 处理结果
     */
    @PostMapping("/file-upload")
    @Operation(summary = "发布新版本")
    fun uploadNewVersion(@RequestParam("file") file: MultipartFile?, @RequestParam("info") json: String?): Result<*> {
        if (file == null) {
            return Result.err("请上传文件")
        }
        val info = JSONObject.parseObject(json, FileInfo::class.java)

        // 如果前端传过来的文件名字是空的,则设置一个默认文件名字
        var filename = file.originalFilename
        if (filename == null) {
            filename = "未知文件.null"
        }

        // 如果前台没有置顶文件名字, 则使用默认的文件名字或者原始文件名
        if (StringUtils.isBlank(info.fileName)) {
            info.fileName = filename
        }


        // 获取文件后缀,也就是文件类, 例子: .apk   .png
        val fileType = filename.substring(filename.lastIndexOf("."))
        info.fileType = fileType


        // 设置文件大小
        val size = file.size
        info.fileSize = size

        // 设置上传时间
        info.createDate = DateUtil.date()

        return try {

            // 保存到数据库
            val fileInfo = fileInfoService.saveOrUpdate(info)

            fileInfo.successResult("上传文件成功")
        } catch (e: IOException) {
            throw BizException(CommonEnum.INTERNAL_SERVER_ERROR)
        }
    }

    /**
     * 保存或者修改一个分类对象
     *
     * @param category 分类数据
     * @return 修改或者添加后的结果
     */
    @PostMapping("/blog-category-save")
    @Operation(summary = "新增&修改博客分类")
    fun saveAndUpdate(@RequestBody @Validated category: Category, bindingResult: BindingResult): Result<*> {
        if (bindingResult.hasErrors()) {
            return Result.paramValidError(bindingResult)
        }
        /// 如果是新增，需要判读是否已经存在
        if (category.id == null || category.id == -1L) {
            val category1 = Category()
            category1.name = category.name
            val list = categoryService.findList(PageModel(0, 10), category1)
            if (!list.isEmpty) {
                return Result.err("该分类已经被创建，不能重复创建")
            }
        }
        val save = categoryService.save(category)
        return Result.ok(save)
    }

    /**
     * 删除一个分类
     *
     * @param jsonObject 需要传 id 一个id参数回来
     * @return 处理结果
     */
    @DeleteMapping("/blog-category-delete")
    @Operation(summary = "删除博客分类")
    fun delete(@RequestBody jsonObject: JSONObject): Result<*> {
        val id = jsonObject.getLong("id") ?: return Result<Any>(CommonEnum.PARAMS_ERROR)
        val blogs = blogService.selectBlogsWithCategoryId(id, 1, 10)
        if (blogs.totalElements != 0L) {
            return Result.err("删除失败,请先删除该分类下的所有博客")
        }
        categoryService.delete(id)
        return Result.ok("删除成功")
    }

    /**
     * 修改一个友链的状态
     *
     * @param friendLink 友链状态
     * @return 修改成功后的数据
     */
    @PostMapping("/update-friends-obj")
    @Operation(summary = "修改友链")
    fun updateFriendLink(@RequestBody @Validated friendLink: FriendLink, bindingResult: BindingResult): Result<*> {
        if (bindingResult.hasErrors()) {
            return Result.paramValidError(bindingResult)
        }
        if (friendLink.id == null) {
            return Result<Any>(CommonEnum.PARAMS_ERROR)
        }
        val id = friendLink.id
        val find = FriendLink()
        find.id = id
        val list = friendLinkService.findAllBy(find)
        if (list.isEmpty()) {
            return Result<Any>(CommonEnum.NOT_FOUND)
        }
        val friendLink1 = friendLinkService.save(friendLink)
        return Result.ok(friendLink1)
    }

    /**
     * 删除一个友情链接
     *
     * @return 删除结果，返回200表示删除成功
     */
    @DeleteMapping("/delete-friends-obj")
    @Operation(summary = "删除友链")
    fun deleteFriendLink(@RequestBody jsonObject: JSONObject): Result<*> {
        val id = jsonObject.getLong("id") ?: return Result<Any>(CommonEnum.PARAMS_ERROR)
        val link = FriendLink()
        link.id = id
        val list = friendLinkService.findAllBy(link)
        if (list.isEmpty()) {
            return Result<Any>(CommonEnum.NOT_FOUND)
        }
        friendLinkService.delete(id)
        return Result.ok("删除成功")
    }

    /**
     * 发送邮件的服务
     *
     * @param jsonObject 请求参数
     * @return 处理结果
     */
    @PostMapping("/send-email")
    @Operation(summary = "发送邮件")
    fun sendEmail(@RequestBody jsonObject: JSONObject): Result<*> {
        val email = jsonObject.getString("email")
        val title = jsonObject.getString("title")
        val content = jsonObject.getString("content")
        val html = jsonObject.getBoolean("html")
        val response = userService.sendSimpleEmail(email, title, content, html)
        return Result.ok(response)
    }

    /**
     * 资源分类列表
     * 查找全部的顶级分类
     */
    @GetMapping("/res-list-level-top")
    @Operation(summary = "群组分类列表")
    fun allResourceCategoryTop(): Result<*> {
        return Result.ok(resourcesCategoryService.findAllLevelTop())
    }

    /**
     * 文件上传
     *
     * @return 结果
     */
    @PostMapping("/simple-upload")
    @Operation(
        summary = "文件上传(返回访问链接)", responses = [
            ApiResponse(description = "返回成功示例", responseCode = "200", useReturnTypeSchema = true)
        ]
    )
    fun simpleUpload(file: MultipartFile, @GetLoginUser user: User, @ServiceHost host: String?): Result<String> {
        return try {
            val config = FileInfoSaveConfig(user = user, folderName = "simple-file", host = host ?: "")
            val linkUrl =
                fileInfoService.getLinkUrl(file, config) ?: throw BizException("上传失败")
            Result.ok(linkUrl.url)
        } catch (e: Exception) {
            throw BizException("上传失败:${e.localizedMessage}")
        }
    }

    /**
     * 新增或者修改一个文档目录
     *
     * @param params        修改参数
     * @param bindingResult 参数验证结果
     * @return 操作结果
     */
    @PostMapping("/cate-director-action")
    @Operation(summary = "新增&修改文档类型群组分类")
    fun saveOrUpdateSubFolder(
        @RequestBody @Validated params: CreateSubDirectorParam,
        @Parameter(hidden = true) bindingResult: BindingResult
    ): Result<ResourcesCategory> {
        try {
            bindingResult.verify()
            val parentCategory = params.parentNodeId?.let { resourcesCategoryService.findById(it) }
            return if (params.updateId == null) {
                val resourcesCategory = ResourcesCategory()
                resourcesCategory.name = params.name
                resourcesCategory.parentNode = parentCategory
                resourcesCategory.type = ResourceType.DocDirectory.type
                resourcesCategory.description = params.description
                val levelId = parentCategory?.level
                if (levelId != null) {
                    resourcesCategory.level = levelId + 1
                }
                val saved = resourcesCategoryService.save(resourcesCategory)

                Result.ok(saved)
            } else {
                val findUpdateObject = resourcesCategoryService.findById(params.updateId!!)
                findUpdateObject.name = params.name
                val updatedObject = resourcesCategoryService.save(findUpdateObject)
                Result.ok(updatedObject)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw BizException(e.localizedMessage)
        }
    }

    data class DeleteUserParam(val id: Long)

    @DeleteMapping("/deleteUser")
    @Operation(summary = "删除用户")
    fun deleteUser(params: DeleteUserParam): Result<*> {
        userService.removeUser(params)
        return Result.ok("删除成功")
    }
}