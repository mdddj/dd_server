package shop.itbug.ticket.ex

import org.springframework.security.crypto.factory.PasswordEncoderFactories
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.config.MyMailAccount
import shop.itbug.ticket.service.config.createMail


fun User.sendValidCode(emailConfig: MyMailAccount) {
    try{
        val createMail = emailConfig.createMail()
        createMail.setTos(email).setTitle("典典账号验证码").setContent("梁典典: 你正在注册典典账号,你的验证码是:${activeCode}.").setHtml(true)
    }catch (e: Exception){
        e.printStackTrace()
        throw BizException("邮件验证码发送失败,请重试",e.toString())
    }
}

/**
 * 获取加密后的密码
 */
fun User.passwordEncode(originPass: String) :String {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(originPass)
}