package shop.itbug.ticket.model.api

import shop.itbug.ticket.entry.User
import shop.itbug.ticket.shiro.JwtUtil

data  class LoginSuccessResultModel(val token: String,val user: User)

/**
 * 生成一个token
 */
fun User.generateApiToken(): String {
    return id?.let { JwtUtil.sign(it, password) } ?: ""
}

fun User.generateLoginSuccessResultModel() : LoginSuccessResultModel{
    return LoginSuccessResultModel(generateApiToken(),this)
}