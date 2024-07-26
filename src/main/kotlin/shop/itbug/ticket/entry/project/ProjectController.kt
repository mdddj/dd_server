package shop.itbug.ticket.entry.project

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.model.params.IdBody
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/api/project")
@Tag(name = "项目接口")
class ProjectController {

    @Resource
    private lateinit var projectService: ProjectService


    @PostMapping("/save")
    @Operation(summary = "保存或者修改项目")
    fun save(@RequestBody project: Project): R<Project> {
        return projectService.save(project).successResult("成功")
    }

    @GetMapping("/all")
    @Operation(summary = "获取全部列表")
    fun findAll(): R<List<Project>> {
        return projectService.findAll().successResult("获取成功")
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除项目")
    fun delete(idBody: IdBody): R<Boolean> {
        try {
            projectService.deleteById(idBody.id)
            return true.successResult("删除成功")
        }catch (e: Exception){
            throw BizException(e)
        }
    }

    @GetMapping("/findBy")
    @Operation(summary = "条件分页查询")
    fun findBy(pageModel: PageModel,project: Project?) : R<Page<Project>> {
        return projectService.findAllBy(pageModel, project).successResult("获取成功")
    }

    @GetMapping("/findById")
    @Operation(summary = "根据ID查询项目")
    fun findById(idBody: IdBody) : R<Project> {
        return projectService.findById(idBody.id)?.successResult("查询成功") ?: throw BizException(CommonEnum.NOT_FOUND)
    }
}