package shop.itbug.ticket.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getCurrentPage
import shop.itbug.ticket.entry.Role
import shop.itbug.ticket.service.RoleService
import shop.itbug.ticket.utils.BlogUtil
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/api/role")
@Tag(name = "权限接口")
class RoleController {

    @Resource
    private lateinit var roleService: RoleService

    /**
     * 获取列表成功
     */
    @GetMapping("/list")
    @Operation(summary = "获取全部权限")
    fun list(): Result<List<Role>> {
        return Result(roleService.findAll(), "获取成功")
    }

    @GetMapping("/list-page")
    @Operation(summary = "获取全部(分页)", description = "获取全部权限")
    fun listByPage(pageModel: PageModel): Result<*> {
        val all = roleService.findAllByPage(pageModel.getCurrentPage(), pageModel.pageSize)
        return BlogUtil.coverToPage(all).successResult()
    }

    /**
     * 保存或者修改操作
     */
    @PostMapping("/save")
    @Operation(summary = "保存或者修改权限")
    fun save(@RequestBody role: Role): Result<Role> {
        return Result(roleService.save(role), "操作成功")
    }

    /**
     * 删除权限
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除权限")
    fun delete(id: Long): Result<Any> {
        roleService.delete(id)
        return Result.ok("删除成功")
    }
}