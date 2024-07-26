package shop.itbug.ticket.service.config

import cn.hutool.extra.mail.Mail
import cn.hutool.extra.mail.MailAccount
import com.sun.mail.util.MailSSLSocketFactory
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.stereotype.Service
import java.io.Serializable


fun MyMailAccount.createMail() : Mail{
    val mailAccount = MailAccount()
    mailAccount.from = from
    mailAccount.host = host
    mailAccount.pass = pass
    mailAccount.isAuth = auth
    mailAccount.isSslEnable = sslEnable
    mailAccount.user = user
    mailAccount.isStarttlsEnable = starttlsEnable
    val sf =  MailSSLSocketFactory()
    mailAccount.setCustomProperty(socketFactoryClass, sf)
    return Mail.create(mailAccount)
}
///邮箱配置
@Schema(name = "邮箱通知配置")
data class MyMailAccount(
    @Schema(description = "邮件服务器的SMTP地址")
    var host: String = "",
    @Schema(description = "邮件服务器的SMTP端口")
    var port: String = "",
    @Schema(description = "是否需要验证")
    var auth: Boolean = false,
    @Schema(description = "发件人（必须正确，否则发送失败）")
    var from: String = "",
    @Schema(description = "用户名（注意：如果使用foxmail邮箱，此处user为qq号）")
    var user: String = "",
    @Schema(description = "密码")
    var pass: String = "",
    @Schema(description = "使用SSL安全连接")
    var sslEnable: Boolean = false,
    @Schema(description = "使用 STARTTLS安全连接，STARTTLS是对纯文本通信协议的扩展。")
    var starttlsEnable: Boolean = false,
    @Schema(description = "指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字")
    var socketFactoryClass: String = ""
) : Serializable

@Service
class MailAccountConfig : ConfigBase<MyMailAccount>(MyMailAccount::class) {
    override val configName: String
        get() = "mail_account"
    override val defaultModel: MyMailAccount
        get() = MyMailAccount()
}

