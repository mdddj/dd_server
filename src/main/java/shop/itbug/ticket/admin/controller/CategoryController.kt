package shop.itbug.ticket.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.entry.blog.Category
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.ResourcesCategoryService
import shop.itbug.ticket.service.blog.CategoryService
import shop.itbug.ticket.utils.BlogUtil
import shop.itbug.ticket.utils.Result

/**
 * 博客分类开放接口
 * @author eee
 */
@RestController
@RequestMapping("/api/blog/category")
@Tag(name = "博客分类接口(管理员)")
class CategoryController {
    @Resource
    private lateinit var categoryService: CategoryService
    @Resource
    private lateinit var resourcesCategoryService: ResourcesCategoryService

    /**
     * 查询列表
     *
     * @param pageModel     分页数据
     * @param category      条件查询
     * @param bindingResult 验证参数
     * @return 查询结果集
     */
    @GetMapping("/list")
    @Operation(summary = "列表(分页")
    fun list(@Validated pageModel: PageModel, category: Category?, bindingResult: BindingResult): Result<*> {
        if (bindingResult.hasErrors()) {
            return Result.paramValidError(bindingResult)
        }
        val list = category?.let { it1 -> categoryService.findList(pageModel, it1) } ?: throw BizException("查找失败")
        return Result.ok(BlogUtil.coverToPage(list))
    }

    /**
     * 根据一个名字获取一个分类的对象
     * @param name 分类名字
     * @return 查询获取的数据
     */
    @GetMapping("/findByName")
    @Operation(summary = "分类详情")
    fun findByName(@Parameter(name = "分类名字") name: String): Result<Category> {
        val category = categoryService.select(name)
        return Result.ok(category)
    }

    /**
     * 查询父亲分类的全部子节点ID
     * @param id 父亲ID
     * @return 查询结果
     */
    @GetMapping("/resources/children")
    @Operation(summary = "根据ID查询子分类(资源)")
    fun getResourceCategoryChildrenByParentId(id: Long): Result< List<ResourcesCategory>> {
        val ids = resourcesCategoryService.selectAllChildrenIds(id)
        val all = resourcesCategoryService.selectAllByIds(ids)
        return Result.ok(all)
    }
}