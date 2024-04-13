package shop.itbug.ticket.auth.email

import org.springframework.security.authentication.AbstractAuthenticationToken
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.model.params.LoginParam

class EmailLoginAuthenticationToken(private val params: LoginParam) : AbstractAuthenticationToken(
    emptyList()
) {
    init {
        isAuthenticated = false
    }

    override fun getCredentials(): Any {
        return params.password
    }

    override fun getPrincipal(): String {
        return params.loginNumber
    }
}