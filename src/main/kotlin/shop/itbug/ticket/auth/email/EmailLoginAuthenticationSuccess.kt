package shop.itbug.ticket.auth.email

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.exception.ResultDialogType
import shop.itbug.ticket.model.api.generateLoginSuccessResultModel
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.changeDialogType
import shop.itbug.ticket.utils.result

/**
 * 邮箱登录成功回调
 */
class EmailLoginAuthenticationSuccess: AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        response?.result(Result.ok(data = (authentication?.principal as? User)?.generateLoginSuccessResultModel(), msg = "登录成功").changeDialogType(ResultDialogType.FinalToast))
    }
}