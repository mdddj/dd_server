package shop.itbug.ticket.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.lang3.StringUtils
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.service.UserService
import shop.itbug.ticket.shiro.JwtUtil
import shop.itbug.ticket.utils.ResultJSON
import shop.itbug.ticket.utils.result

/**
 * jwt token 拦截器
 */
class JwtTokenBeforeFilter(val userService: UserService) : OncePerRequestFilter() {

    private val jwtTokenHeader = "Authorization"

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var token = request.getHeader(jwtTokenHeader)
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token")
        }
        if (StringUtils.isNotBlank(token)) { //token不为空,则进行验证信息,否则放行
            val userId = JwtUtil.getUsername(token)
            if(userId == null){
                //不是有效的token
                response.result(ResultJSON<String>(CommonEnum.TOKEN_INVALID,token))
            }else{
                try {
                    val user = userService.findByUserId(userId)
                    if (user == null) {
                        response.result(ResultJSON<String>(CommonEnum.NO_AUTHORIZATION_HEADER))
                    } else {
                        val isValid = JwtUtil.verify(token, userId, user.password)
                        if (isValid) {
                            try {
                                val usernamePasswordAuthenticationToken =
                                    UsernamePasswordAuthenticationToken(user, null, user.authorities)
                                usernamePasswordAuthenticationToken.details =
                                    WebAuthenticationDetailsSource().buildDetails(request)
                                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
                                filterChain.doFilter(request, response)
                            } catch (e: Exception) {
                                response.result(ResultJSON<String>(CommonEnum.NO_AUTHORIZATION_INVALID))
                            }
                        } else {
                            response.result(ResultJSON<Any>(CommonEnum.NO_AUTHORIZATION_INVALID))//token过期
                        }
                    }
                } catch (e: UsernameNotFoundException) {
                    response.result(ResultJSON<Any>(CommonEnum.NO_AUTHORIZATION_HEADER))
                }
            }
        } else {
            try {
                filterChain.doFilter(request, response)
            }catch (e:InsufficientAuthenticationException){
              response.result(ResultJSON<String>(CommonEnum.NO_AUTHORIZATION_INVALID,e.localizedMessage))
            } catch (e:Exception){
                response.result(ResultJSON<String>(CommonEnum.EXCEPTION,e.localizedMessage))
            }
        }
    }

}