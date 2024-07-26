package shop.itbug.ticket.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.service.ResourcesCategoryService
import shop.itbug.ticket.utils.BlogUtil
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.ResultJSON

/**
 * 资源分类管理
 *
 * @author eee
 */
@RestController
@RequestMapping("/api/res")
@Tag(name = "群组接口(管理员)")
@PreAuthorize("hasRole('admin')")
class ResourceController {
    @Autowired
     lateinit var resourcesCategoryService: ResourcesCategoryService

    @GetMapping("/list")
    @Operation(summary = "分类列表(分页)")
    fun list(pageModel: PageModel, category: ResourcesCategory?): Result<BlogUtil.CoverToPage<ResourcesCategory>> {
        val page = resourcesCategoryService.findList(pageModel, category)
        val map = BlogUtil.coverToPage(page)
        return Result.ok(map)
    }

    @GetMapping("/like-list")
    @Operation( summary = "模糊查找分类")
    fun findListByName(@RequestParam(name = "name", defaultValue = "") name: String): Result<*> {
        val listByLikeName = resourcesCategoryService.findListByLikeName("%$name%")
        return Result.ok(listByLikeName)
    }

    @GetMapping("/find")
    @Operation(summary = "条件查询分类")
    fun findOne(resourcesCategory: ResourcesCategory): Result<ResourcesCategory> {
        val byObj = resourcesCategoryService.findByObj(resourcesCategory)
        return Result.ok(byObj)
    }

    @GetMapping("/sub")
    @Operation(summary = "查询子分类")
    fun findSubObject(resourcesCategory: ResourcesCategory): Result<Map<String,Any>> {
        return Result.ok(resourcesCategory.id?.let { resourcesCategoryService.findResourcesAndChilders(it) })
    }

    @GetMapping("/all")
    @Operation(summary = "获取所有分类")
    fun findAll(): Result<List<ResourcesCategory>> {
        return Result.ok(resourcesCategoryService.findAll())
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除群组")
    fun deleteById(id: Long) : Result<Boolean> {
        resourcesCategoryService.deleteById(id)
        return Result.ok(true)
    }

    @DeleteMapping("/deleteAll")
    @Operation(summary = "删除多个")
    fun deleteAllByIds(ids: List<Long>): Result<Boolean> {
        resourcesCategoryService.selectAllByIds(ids)
        return ResultJSON("删除成功")
    }
}