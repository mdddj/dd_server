package shop.itbug.ticket.controller.resource

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.dao.model.ResourceCategoryTypes
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.entry.ResourcesCategoryCounter
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.service.ResourcesCategoryService
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.ResultJSON
import shop.itbug.ticket.utils.SuccessResult

/**
 * ResourceCategory 公共的接口控制器
 * 
 *
 */
@RequestMapping("/api/rc")
@RestController
@Tag(name = "群组接口")
class ResourceCategoryController {
    @Resource
    private lateinit var resourcesCategoryService: ResourcesCategoryService

    /**
     * 获取全部已经存在的类型列表
     * @return 查询结果
     */
    @Operation(summary = "查询群组所有的类型")
    @GetMapping("/types")
    fun getTypes(): Result<List<ResourceCategoryTypes?>> {
        val allTypes = resourcesCategoryService.findAllTypes()
        return Result(allTypes)
    }


    @GetMapping("/findByType")
    fun findListByType(type: String) : ResultJSON<List<ResourcesCategoryCounter>> {
        return resourcesCategoryService.selectCategoryResourceWithType(type).SuccessResult()
    }

    @GetMapping("/findById")
    @Operation(summary = "根据id查找分类")
    fun findById(id: Long) : ResultJSON<ResourcesCategory> {
        val result =  resourcesCategoryService.findById(id) ?: throw BizException(CommonEnum.NOT_FOUND)
        return ResultJSON.ok(result)
    }

    @GetMapping("/cates")
    @Operation(summary = "查找所有分类")
    fun findAll(): Result<*> {
        return Result.ok(resourcesCategoryService.findAll())
    }

    @GetMapping("/find-cate")
    @Operation(summary = "条件查询分类")
    fun find(category: ResourcesCategory?): Result<*> {
        return Result.ok(resourcesCategoryService.findByObj(category))
    }
}