package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

/**
 * @author ldd
 */
@Schema(name = "登录参数")
open class LoginParam {

    @Schema(name = "用户名")
    open var loginNumber: @NotNull(message = "请输入用户名") @NotEmpty(message = "请输入用户名") String = ""

    @Schema(name = "密码")
    open var password: @NotNull(message = "请输入密码") @NotEmpty(message = "请输入密码") String = ""

    @Schema(
        name = "登录方式,有三种,account,phone,email,分别对应账号,手机号,邮箱,不传默认使用account", description = "登录方式",
        allowableValues = ["account", "phone", "email"]
    )
    open var loginType: String = "account"
    companion object {
        fun new(email: String,password: String):LoginParam {
            return  LoginParam().apply {
                this.loginNumber = email
                this.password = password
            }
        }
    }
}