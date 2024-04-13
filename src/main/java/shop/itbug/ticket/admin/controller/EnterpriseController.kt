package shop.itbug.ticket.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.Enterprise
import shop.itbug.ticket.service.EnterpriseService
import shop.itbug.ticket.utils.BlogUtil
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.ResultJSON

@RestController
@RequestMapping("/api/enterprise")
@Tag(name = "企业接口(管理员)")
class EnterpriseController {

    @Autowired
    private lateinit var baseService: EnterpriseService


    @PostMapping("/create")
    @Operation(summary = "创建一个企业")
    fun create(@RequestBody model: Enterprise): ResultJSON<Enterprise> {
        val saveEntity = baseService.saveEntity(model)
        return ResultJSON(saveEntity, "创建公司\"${model.name}\"成功")
    }


    /**
     * 查找列表
     */
    @GetMapping("/list")
    @Operation(summary = "分页获取列表")
    fun findAll(pageModel: PageModel) : ResultJSON<BlogUtil.CoverToPage<Enterprise>> {
        return  ResultJSON(BlogUtil.coverToPage(baseService.findListByPage(pageModel)),"获取成功")
    }

    @GetMapping("/findAll")
    @Operation(summary = "获取全部列表")
    fun findAll() : ResultJSON<List<Enterprise>> {
        return Result(baseService.findAll(),"获取成功")
    }


}