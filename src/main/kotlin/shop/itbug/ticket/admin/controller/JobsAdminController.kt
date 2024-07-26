package shop.itbug.ticket.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.service.ResourcesCategoryService
import shop.itbug.ticket.utils.ResultJSON
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/api/admin/jobs")
@Tag(name = "找工作接口(管理员)")
class JobsAdminController {

    @Resource
    lateinit var resourcesCategoryService: ResourcesCategoryService

    @Schema(name = "找工作添加城市参数")
    data class AddCityGroupParams(@SchemaProperty(name = "城市名") val name: String)

    @PostMapping("/add-city")
    @Operation(summary = "添加城市")
    fun createCityGroup(@RequestBody params: AddCityGroupParams): ResultJSON<*> {
        val resourcesCategory = ResourcesCategory().apply {
            name = params.name
            type = "jobs-city"
        }
        return resourcesCategoryService.save(resourcesCategory).successResult()
    }


}