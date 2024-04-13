package shop.itbug.ticket.auth.email

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.result


/**
 * 邮箱登录失败处理
 */
class EmailLoginAuthenticationFailure : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        response.result(Result.err(exception.message?:"登录失败"))
    }
}