package shop.itbug.ticket.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.EnterpriseService
import shop.itbug.ticket.service.UserService
import shop.itbug.ticket.utils.BlogUtil
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.ResultJSON
import java.math.BigInteger

/**
 * @author eee
 */
@RestController
@RequestMapping("/api/user")
@Tag(name = "用户接口(管理员)")
class UserAdminController {
    @Resource
    private lateinit var userService: UserService

    @Resource
    private lateinit var enterpriseService: EnterpriseService

    /**
     * 获取用户列表
     * @return json
     */
    @GetMapping("/list")
    @Operation(summary = "获取用户列表")
    fun list(pageModel: PageModel): Result<BlogUtil.CoverToPage<User>> {
        return Result(BlogUtil.coverToPage(userService.findList(null, pageModel)))
    }

    @GetMapping("/list-q")
    @Operation(summary = "获取用户列表(带条件)")
    fun listByQuery(user: User?, pageModel: PageModel): Result<BlogUtil.CoverToPage<User>> {
        return ResultJSON.ok(BlogUtil.coverToPage(userService.findList(user, pageModel)))
    }

    @PostMapping("/update")
    @Operation(summary = "修改用户信息")
    fun updateUserInfo(@RequestBody model: User.UpdateUserModel): ResultJSON<User> {
        val user = userService.findByUserId(model.id) ?: throw BizException("用户不存在")
        model.nickName?.apply { user.nickName = this }
        model.tokens?.apply { user.openAiTokens = BigInteger(this) }
        model.openAiVip?.apply { user.openAiFlag = this }
        model.status?.apply { user.status = this }
        model.vip?.apply { user.vip = this }
        model.enterpriseId?.apply {
            val enterprise = enterpriseService.findById(this)
            enterprise?.let { user.enterprise = it }
        }
        val save = userService.save(user)
        return Result.ok(save)
    }


    data class CreateAdminAccountParam(val username:String,val password: String,val email: String)



}