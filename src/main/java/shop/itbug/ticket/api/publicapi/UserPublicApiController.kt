package shop.itbug.ticket.api.publicapi

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.entry.system.SystemAvator
import shop.itbug.ticket.model.api.RegistorParamsModel
import shop.itbug.ticket.service.SystemAvatarService
import shop.itbug.ticket.service.UserService
import shop.itbug.ticket.utils.Result

/**
 * @author 梁典典
 */
@RestController
@RequestMapping("/api/public/user")
@Tag(name = "用户开放的相关接口")
class UserPublicApiController {
    @Resource
    private lateinit var userService: UserService

    @Resource
    private lateinit var systemAvatorService: SystemAvatarService


    /**
     * 用户注册 公共api
     *
     * @param paramsModel   参数
     * @param bindingResult 参数验证
     * @return 消息提示
     */
    @PostMapping("/registor")
    @Operation(summary = "用户注册")
    fun registory(@RequestBody @Validated paramsModel: RegistorParamsModel, bindingResult: BindingResult): Result<*> {
        if (bindingResult.hasErrors()) {
            return Result.paramValidError(bindingResult)
        }
        paramsModel.loginName?.let { userService.registor(it, paramsModel.password, paramsModel.pic) }
        return Result.ok("注册成功")
    }

    /**
     * 获取系统预设头像
     *
     * @return 头像列表
     */
    @GetMapping("/ava-pics")
    @Operation(summary = "获取系统预设头像列表")
    fun avaPics() : Result< List<SystemAvator>> {
        return  Result.ok(systemAvatorService.findAllByType(1))
    }


    /**
     * 获取已在线人数
     *
     * @return 人数
     */
    @GetMapping("/inline-count")
    fun getInLineCount(): Result<*> {
        return Result.ok(0)
    }



}