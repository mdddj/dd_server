package shop.itbug.ticket.entry

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

/**
 * 接口权限验证
 */
@Entity
open class PermissionValidationToken {
    /**
     * 主键
     */
    @Id
    @GeneratedValue
   open var id: Long? = null

    /**
     * token 类型
     */
   open var type: String? = null

    /**
     * 介绍
     */
   open var description: String? = null

    open var token: String? = null
}