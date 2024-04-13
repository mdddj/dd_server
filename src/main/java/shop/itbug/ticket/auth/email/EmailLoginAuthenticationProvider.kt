package shop.itbug.ticket.auth.email

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.Authentication
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.model.params.LoginParam
import shop.itbug.ticket.service.UserService
import shop.itbug.ticket.utils.printToConsole

//
class EmailLoginAuthenticationProvider(val userService: UserService) : DaoAuthenticationProvider() {

    override fun authenticate(authentication: Authentication): Authentication? {
        if (authentication.isAuthenticated) {
            return authentication
        }
        if (!supports(authentication.javaClass)) {
            return null
        }
        val email = authentication.principal as String
        val password = authentication.credentials as String
        try {
            val user = userService.emailLogin(LoginParam.new(email, password))
            return createSuccessAuthentication(user, authentication, user)
        } catch (e: BizException) {
            throw BadCredentialsException(e.errMessage)
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication?.isAssignableFrom(EmailLoginAuthenticationToken::class.java) ?: false
    }

}