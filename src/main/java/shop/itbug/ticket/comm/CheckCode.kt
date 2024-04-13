package shop.itbug.ticket.comm

import java.io.Serializable
import java.time.LocalDateTime

/**
 * @author ldd
 */
class CheckCode @JvmOverloads constructor(
    /**
     * 验证码
     */
    val code: String, expireTime: Int = 60
) : Serializable {

    /**
     * 过期时间
     */
    private val expireTime: LocalDateTime

    /**
     * 默认60秒过期
     * @param code 验证码
     */
    init {
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime.toLong())
    }

    /**
     * 是否过期
     * @return true 表示已经过期
     */
    val isExpried: Boolean
        get() = expireTime.isBefore(LocalDateTime.now())
}