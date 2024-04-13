package shop.itbug.ticket.auth.account

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.Authentication
import shop.itbug.ticket.model.params.LoginParam
import shop.itbug.ticket.service.UserService

class AccountLoginAuthenticationProvider(val userService: UserService): DaoAuthenticationProvider() {

    override fun authenticate(authentication: Authentication): Authentication? {
        if(authentication.isAuthenticated){
            return authentication
        }
        if(!supports(authentication::class.java)){
            return null
        }
        val account = authentication.principal as String
        val password = authentication.credentials as String
        try {
           val loginSuccessModel =  userService.login(LoginParam().apply {
                this.loginNumber = account
                this.password = password
            })
            return createSuccessAuthentication(loginSuccessModel.user,authentication,loginSuccessModel.user)
        }catch (e:Exception){
            throw BadCredentialsException(e.message)
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication.isAssignableFrom(AccountLoginAuthenticationToken::class.java)
    }
}