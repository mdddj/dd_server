package shop.itbug.ticket.model.api

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.URL

/**
 * @author 梁典典
 */
class RegistorParamsModel {
    val loginName: @NotBlank(message = "请输入登录账户") @Size(min = 5, max = 11, message = "登录名字符长度5-11之间") String? =
        null
    val password: @NotBlank(message = "请输入密码") @Size(min = 6, max = 20, message = "登录密码长度6-20之间") String? = null
    val pic: @NotBlank(message = "请输入头像") @URL(message = "请输入一个有效url") String? = null
}