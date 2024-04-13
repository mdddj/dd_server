package shop.itbug.ticket.shiro

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import java.util.*

/**
 * @author eee
 */
object JwtUtil {

    /**
     * 校验token是否正确
     *
     * @param token    密钥
     * @param id 用户ID
     * @param password 密码
     * @return true 表示成功
     */
    fun verify(token: String, id: Long, password: String): Boolean {
        return try {
            val algorithm = Algorithm.HMAC256(password)
            val verifier: JWTVerifier = JWT.require(algorithm).withClaim("id", id.toString()).build()
            verifier.verify(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * 获取登录名
     *
     * @param token 前端传过来的token
     * @return 用户ID
     */
    fun getUsername(token: String): Long? {
        return try {
            val jwt = JWT.decode(token)
            jwt.getClaim("id").asString().toLong()
        } catch (e: Exception) {
            null
        }
    }

    /**
     * 生成签名
     *
     * @param id 用户ID
     * @param password 密码
     * @return  jwt token
     */
    fun sign(id: Long, password: String): String {
        val instance = Calendar.getInstance()
        instance.add(Calendar.DATE,30)//30天过期时间
        val time = instance.time
        val algorithm = Algorithm.HMAC256(password)
        return JWT.create()
            .withClaim("id", id.toString())
            .withExpiresAt(time)
            .sign(algorithm)
    }
}