package shop.itbug.ticket.auth.account

import org.springframework.security.authentication.AbstractAuthenticationToken
import shop.itbug.ticket.model.params.LoginParam

class AccountLoginAuthenticationToken(val loginParam: LoginParam): AbstractAuthenticationToken(emptyList()) {

    init {
        isAuthenticated = false
    }
    override fun getCredentials(): Any {
        return loginParam.password
    }

    override fun getPrincipal(): Any {
        return loginParam.loginNumber
    }
}