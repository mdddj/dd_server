package shop.itbug.ticket.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.ResourcesCategoryService
import shop.itbug.ticket.utils.BlogUtil
import shop.itbug.ticket.utils.Result

/**
 * 资源分类管理
 *
 * @author eee
 */
@RestController
@RequestMapping("/api/res")
@Tag(name = "群组接口(管理员)")
class ResourceController {
    @Autowired
     lateinit var resourcesCategoryService: ResourcesCategoryService

    /**
     * 分页查询群组
     *
     * @param pageModel 分页数据
     * @param category  条件查询条件
     * @return 查询结果
     */
    @GetMapping("/list")
    @Operation(summary = "分类列表(分页)")
    fun list(pageModel: PageModel?, category: ResourcesCategory?): Result<BlogUtil.CoverToPage<ResourcesCategory>> {
        val page = pageModel?.let { resourcesCategoryService.findList(it, category) } ?: throw BizException("获取数据失败")
        val map = BlogUtil.coverToPage(page)
        return Result.ok(map)
    }

    /**
     * 模糊查询群组
     *
     * @param name 群组名
     * @return 查询结果
     */
    @GetMapping("/like-list")
    @Operation( summary = "模糊查找分类")
    fun findListByName(@RequestParam(name = "name", defaultValue = "") name: String): Result<*> {
        val listByLikeName = resourcesCategoryService.findListByLikeName("%$name%")
        return Result.ok(listByLikeName)
    }

    /**
     * 条件查询一个资源分类
     * @param resourcesCategory 条件
     * @return  查询结果
     */
    @GetMapping("/find")
    @Operation(summary = "条件查询分类")
    fun findOne(resourcesCategory: ResourcesCategory?): Result<ResourcesCategory> {
        val byObj = resourcesCategoryService.findByObj(resourcesCategory)
            ?: throw BizException("资源丢失,或者不存在此分类")
        return Result.ok(byObj)
    }

    /**
     * 查询分类下面的子文件夹和全部子资源列表
     * @return 查询结果
     */
    @GetMapping("/sub")
    @Operation(summary = "查询子分类")
    fun findSubObject(resourcesCategory: ResourcesCategory): Result<Map<String,Any>> {
        return Result.ok(resourcesCategory.id?.let { resourcesCategoryService.findResourcesAndChilders(it) })
    }

    /**
     * 获取全部的分类列表
     * @return 列表
     */
    @GetMapping("/all")
    @Operation(summary = "获取所有分类")
    fun findAll(): Result<List<ResourcesCategory>> {
        return Result.ok(resourcesCategoryService.findAll())
    }
}