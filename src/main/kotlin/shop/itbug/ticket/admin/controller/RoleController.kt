package shop.itbug.ticket.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.entry.Role
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.RoleService
import shop.itbug.ticket.service.UserService
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/api/role")
@Tag(name = "权限接口")
@PreAuthorize("hasRole('admin')")
class RoleControllerWithAdmin {


    @Resource private lateinit var roleService: RoleService

    @Resource private lateinit var userService: UserService

    data class AddUserRoleParam(val userId: Long, val roleId: Long)

    @PostMapping("/action/user-add")
    @Operation(summary = "用户添加权限")
    fun addUserRole(@RequestBody params: AddUserRoleParam) : R<User> {
        val findBy = roleService.findBy(Role().apply { id = params.roleId }) ?: throw BizException("未找到权限数据")
        userService.findByUserId(params.userId)?: throw BizException("未找到用户")
        roleService.userAddRole(params.userId,findBy.name)
        return userService.findByUserId(params.userId)?.successResult() ?: throw BizException("修改失败")
    }


}