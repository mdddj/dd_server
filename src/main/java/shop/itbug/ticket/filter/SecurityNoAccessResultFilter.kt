package shop.itbug.ticket.filter

import com.alibaba.fastjson2.JSONObject
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import shop.itbug.ticket.utils.noAuthenticationResult

/**
 * 当访问没有权限的接口时,返回了自定义JSON字符串
 */
@Component
class SecurityNoAccessResultFilter: AccessDeniedHandler {
    private val log = LoggerFactory.getLogger(SecurityNoAccessResultFilter::class.java)
    override fun handle(request: HttpServletRequest?, response: HttpServletResponse?, accessDeniedException: AccessDeniedException?) {
        log.error("访问了没有权限的接口: ${accessDeniedException?.localizedMessage}")

        response?.characterEncoding = "UTF-8"
        response?.contentType = "application/json"
        response?.writer?.write(JSONObject.toJSONString(accessDeniedException?.noAuthenticationResult()))
        response?.writer?.flush()
    }
}