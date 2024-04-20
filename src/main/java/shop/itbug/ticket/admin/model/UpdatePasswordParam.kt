package shop.itbug.ticket.admin.model

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

/**
 * @author ldd
 */
class UpdatePasswordParam {
    var currentPass: @NotBlank(message = "请输入当前登录密码") String = ""
    var rePassword: @NotBlank(message = "请输入修改后的密码") @Length(
        max = 20,
        min = 6,
        message = "密码长度在6-20个字符之间"
    ) String = ""
}