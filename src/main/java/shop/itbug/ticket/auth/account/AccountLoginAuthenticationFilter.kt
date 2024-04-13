package shop.itbug.ticket.auth.account

import cn.hutool.core.io.IoUtil
import com.alibaba.fastjson2.JSONObject
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import shop.itbug.ticket.model.params.LoginParam

class AccountLoginAuthenticationFilter : AbstractAuthenticationProcessingFilter(
    AntPathRequestMatcher.antMatcher(
        HttpMethod.POST, "/api/user-public/login"
    )
) {

    private val loginType = "account"

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {
        if (request.method != "POST") {
            throw AuthenticationServiceException("不支持的方法")
        }
        try {
            val params = JSONObject.parseObject(IoUtil.read(HttpServletRequestWrapper(request).reader), LoginParam::class.java)
            if (params.loginType != loginType) {
                throw AuthenticationServiceException("不支持的登录方式:${params.loginType}")
            }
            val token = AccountLoginAuthenticationToken(params)
            token.details = this.authenticationDetailsSource.buildDetails(request)
            return this.authenticationManager.authenticate(token)
        } catch (e: BadCredentialsException) {
            throw AuthenticationServiceException("用户名或者密码错误")
        } catch (e: Exception) {
            throw AuthenticationServiceException("请检查传参")
        }
    }


}