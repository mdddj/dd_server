package shop.itbug.ticket.filter


import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import shop.itbug.ticket.ex.log
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.result

/**
 * 当没有token或者token失效的时候,返回自定义的json字符串数据
 */
@Component
class RestAuthenticationEntryPoint : AuthenticationEntryPoint {
    val log: Logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint::class.java)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        log().info("rest auth entry point: {}", authException.message)
        if (authException is InsufficientAuthenticationException) {
            response.result(Result.noAuth().apply {
                data = authException.localizedMessage
            })
        }else{
            response.result(Result.err(authException.localizedMessage))
        }

    }

}