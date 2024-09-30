package shop.itbug.ticket.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import jakarta.validation.Valid
import org.apache.commons.lang3.StringUtils
import org.springframework.data.domain.Page
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getCurrentPage
import shop.itbug.ticket.entry.blog.Blog
import shop.itbug.ticket.entry.blog.Category
import shop.itbug.ticket.entry.blog.Text
import shop.itbug.ticket.entry.project.Project
import shop.itbug.ticket.entry.project.ProjectService
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.model.blog.*
import shop.itbug.ticket.service.blog.BlogService
import shop.itbug.ticket.service.blog.CategoryService
import shop.itbug.ticket.service.blog.TagService
import shop.itbug.ticket.service.blog.TextService
import shop.itbug.ticket.service.config.MiniAppConfig
import shop.itbug.ticket.service.config.MiniAppConfigModel
import shop.itbug.ticket.utils.*

/**
 * 博客相关请求
 *
 * @author 梁典典
 */
@RestController
@RequestMapping("/api/blog")
@Tag(name = "博客接口")
class BlogController {

    @Resource
    private lateinit var categoryService: CategoryService

    @Resource
    private lateinit var blogService: BlogService

    @Resource
    private lateinit var tagService: TagService

    @Resource
    private lateinit var textService: TextService

    @Resource
    private lateinit var projectService: ProjectService

    @Resource private lateinit var miniAppConfig: MiniAppConfig

    /**
     * 获取分类列表
     *
     * @return 分类数组
     */
    @GetMapping("/category-list")
    @Operation(summary = "获取全部博客分类")
    fun getCategoryList(): Result<List<Category>> {
        val categories = categoryService.selectAll()
        return Result(categories)
    }

    @GetMapping("/categorys")
    @Operation(summary = "分页查询分类列表")
    fun findBlogCategoryList(pageModel: PageModel,category: Category?) : R<Page<Category>> {
        return categoryService.findAll(pageModel, category).successResult("获取成功")
    }




    /**
     * 获取博客列表
     *
     * @param params 分页的相关参数
     * @return 文章列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取博客列表")
    fun getBlogList(params: PageModel, bindingResult: BindingResult): Result<BlogUtil.CoverToPage<Blog>> {
        if (bindingResult.hasErrors()) {
            throw BizException(CommonEnum.PARAMS_VIOLATION_EXCEPTION)
        }
        val select = blogService.select(params.getCurrentPage(), params.pageSize)
        return Result(BlogUtil.coverToPageByCache(select), "获取成功")
    }

    /**
     * 给博客添加一个头图
     *
     * @param params id : 文字id    url : 图片url
     */
    @PostMapping("/update/thumbnail")
    @Operation(summary = "修改博客封面")
    fun setBlogThumbnail(params: @Valid UpdateThumbnailParams?, bindingResult: BindingResult): Result<*> {
        if (bindingResult.hasErrors()) {
            return Result.paramValidError(bindingResult)
        }
        if (params != null) {
            val blogId = params.id
            val url = params.url ?: ""
            if (blogId != null && StringUtils.isNotBlank(url)) {
                blogService.addThumbnail(blogId, url)
                return Result.ok(null)
            }
        }
        return Result.err("修改失败")
    }

    /**
     * 获取博客详情 通过id
     *
     * @param id 博客id
     * @return 博客数据
     */
    @GetMapping("/get/{id}")
    @Operation(summary = "获取博客详情")
    fun getBlogWithId(@PathVariable("id") id: Long): Result<Blog> {
        return Result.ok(blogService.select(id))
    }

    @GetMapping("/get")
    @Operation(summary = "获取博客详情2")
    fun getBlogWithIdByParam(id: Long): Result<Blog> {
        return Result.ok(blogService.select(id))
    }

    /**
     * 根据博客id 获取标签id
     *
     * @param id 博客id
     * @return 标签列表
     */
    @GetMapping("/get-tags/{id}")
    @Operation(summary = "获取博客的标签")
    fun getTagsWithBlogId(@PathVariable("id") id: Long): Result<MutableSet<shop.itbug.ticket.entry.blog.Tag>> {
        val select = blogService.select(id)
        return Result.ok(select.tags)
    }

    /**
     * 根据标签id获取博客列表
     *
     * @param param         参数
     * @param bindingResult 验证
     * @return 信息
     */

    @GetMapping("/tag/blogs")
    @Operation(summary = "获取博客列表 (根据标签)")
    fun getBlogsWithTagId(@Validated param: TagBlogsParam, bindingResult: BindingResult): Result<BlogUtil.CoverToPage<Blog>> {
        bindingResult.verify()
        val blogsByTagId = param.page?.let { param.pageSize?.let { it1 ->
            tagService.findBlogsByTagId(
                param.tagId, it,
                it1
            )
        } } ?: throw BizException("获取数据失败")
        val stringObjectMap = BlogUtil.coverToPage(blogsByTagId)
        return Result(stringObjectMap, "获取成功")
    }

    /**
     * 根据分类id获取博客列表
     *
     * @param param         参数
     * @param bindingResult 验证
     * @return 信息
     */
    @GetMapping("/category/blogs")
    @Operation(summary = "根据分类ID获取博客列表")
    fun getBlogsWithCategory(param: @Valid GetBlogsWithCategoryParam?, bindingResult: BindingResult): Result<*> {
        if (bindingResult.hasErrors()) {
            return Result.paramValidError(bindingResult)
        }
        val blogs = param?.categoryId?.let { param.page?.let { it1 ->
            param.pageSize?.let { it2 ->
                blogService.selectBlogsWithCategoryId(it,
                    it1, it2
                )
            }
        } } ?: throw BizException("获取数据失败")
        return Result(BlogUtil.coverToPage(blogs), "获取成功")
    }

    /**
     * 博客站点数据统计
     * 统计的数据有
     * 1.博客总数
     * 2.分类总数
     * 3.标签总数
     * .... 其他待定
     *
     * @return 将统计结果返回
     */
    @GetMapping("/statistics")
    @Operation(summary = "获取博客统计信息")
    fun getDataStatistics(): Result<StatisticsResultModel> {
        return blogService.getDataStatistics().successResult("获取成功")
    }

    /**
     * 获取全部标签列表
     *
     * @return 返回json
     */
    @GetMapping("/tags")
    @Operation(summary = "获取全部标签列表")
    fun getTags(): Result<List<shop.itbug.ticket.entry.blog.Tag>> {
        val tags: List<shop.itbug.ticket.entry.blog.Tag> = tagService.selectAll()
        return Result(tags, "获取成功")
    }

    /**
     * 根据博客别名获取博客对象
     *
     * @param alias 别名
     * @return 博客对象
     */
    @GetMapping("/alias")
    @Operation(summary = "根据博客别名获取博客对象")
    fun findByAlias(@RequestParam("alias") alias: String): Result<*> {
        val byAlias = blogService.findByAlias(alias)
        if (byAlias != null) {
            return Result(byAlias, "获取成功")
        }
        throw BizException(CommonEnum.NOT_FOUND)
    }

    /**
     * 查询字典对象
     *
     * @param name 查询key的标识
     * @param password 查询密码
     * @return 文本模型对象
     */
    @GetMapping("/text")
    @Operation(summary = "获取字典对象")
    fun getText(
        @RequestParam("name") name: String,
        @RequestParam(name = "password", defaultValue = "") password: String?
    ): Result<*> {

        //先判断一下是否需要密码才能查看
        val passwordView = textService.isPasswordView(name)
        if (passwordView && StringUtils.isBlank(password)) {
            //前端可以根据code==303来展示输入框,来对用户提示输入密码来查看内容
            return Result<Any>(303, "内容已被保护,无法查看")
        }

        //匹配用户用户的密码是否正确
        if (passwordView) {
            password?:throw BizException("请输入密码")
            val verificationPasswordIsSuccess = textService.verificationPassword(password, name)
            if (!verificationPasswordIsSuccess) {
                return Result<Any>(303, "密码错误.")
            }
        }

        //到这里就说明验证密码已经成功了,则直接过去内容返回给前端
        val model = textService.findByName(name)
        return Result.ok(model)
    }

    /**
     * 根据月份统计博客数据
     *
     * @param param 月份
     * @return 博客列表
     */
    @GetMapping("/month/blogs")
    @Operation(summary = "根据月份获取博客列表")
    fun getBlogsByMonths(param: BlogMonthParam): Result<MyPageResult<Blog>> {
        val byMonth = blogService.findByMounth(param)
        return Result(BlogUtil.coverToPage(byMonth), "获取成功")
    }

    @GetMapping("/all")
    @Operation(summary = "获取全部博客")
    fun getAllBlogs() : R<List<Blog>>{
        return blogService.findAll().successResult()
    }

    @GetMapping("/getTextAll")
    @Operation(summary = "获取所有的字典")
    fun getAllText() : R<List<Text>>{
        return textService.findAll().successResult()
    }

    @GetMapping("/projects")
    @Operation(summary = "获取所有项目列表")
    fun findAllProjects() : R<List<Project>> {
        return projectService.findAll().successResult("获取成功")
    }

    @GetMapping("/miniapp-config")
    @Operation(summary = "典典博客小程序的配置")
    fun getMiniAppConfig(): R<MiniAppConfigModel> = miniAppConfig.setting.successResult()


}