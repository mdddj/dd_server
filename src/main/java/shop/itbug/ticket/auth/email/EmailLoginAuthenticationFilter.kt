package shop.itbug.ticket.auth.email

import cn.hutool.core.io.IoUtil
import com.alibaba.fastjson2.JSONObject
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import shop.itbug.ticket.model.params.LoginParam

class EmailLoginAuthenticationFilter :
    AbstractAuthenticationProcessingFilter(AntPathRequestMatcher("/api/user-public/login-by-email", "POST")) {

    private val postOnly = true //只处理post请求

    private val loginTypeKey = "email"
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {
        if (postOnly && !request.method.equals("POST")) {
            throw AuthenticationServiceException("暂时不支持此请求:${request.method}")
        }
        try {
            val paramsJsonString = IoUtil.read(HttpServletRequestWrapper(request).reader)
            val loginParams = JSONObject.parseObject(paramsJsonString, LoginParam::class.java)
            if (loginParams.loginType != loginTypeKey) {
                throw AuthenticationServiceException("不支持的登录方式:${loginParams.loginType}")
            }
            val token = EmailLoginAuthenticationToken(loginParams)
            token.details = this.authenticationDetailsSource.buildDetails(request)
            return this.authenticationManager.authenticate(token)
        } catch (e: BadCredentialsException) {
            throw AuthenticationServiceException(e.message)
        } catch (e: Exception) {
            throw e
        }
    }
}