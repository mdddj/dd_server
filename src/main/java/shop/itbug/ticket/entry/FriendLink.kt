package shop.itbug.ticket.entry

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.URL
import java.util.*


/**
 * @author ldd
 */
@Entity
@Schema(name = "友链对象")
open class FriendLink {
    @Id
    @GeneratedValue
    @SchemaProperty(name = "主键ID")
    open var id: Long? = null

    /**
     * / 网站名字
     */
    @SchemaProperty(name = "网站名字")
    open var name: @NotBlank(message = "请输入网站名称") String? = null

    /**
     * / 网站链接
     */
    @SchemaProperty(name = "网站链接")
    open var url: @NotBlank(message = "请输入网站链接") @URL(message = "请输入一个有效的URL链接") String? = null

    /**
     * / 介绍
     */
    @SchemaProperty(name = "网站介绍")
    open var intro: @NotBlank(message = "请输入网站介绍") String? = null

    /**
     * / logo
     */
    @SchemaProperty(name = "网站logo")
    open var logo: @NotBlank(message = "请输入网站logo") @URL(message = "请输入一个有效的URL链接") String? = null

    /**
     * / 状态 1： 表示正常，0：表示需要审核
     */
    @SchemaProperty(name = "状态, 1： 表示可正常显示，0：表示需要审核")
    open var state : Int? = null

    /**
     * / 提交时间
     */
    @SchemaProperty(name = "提交时间")
    open var createDate: Date? = null

    /**
     * / 接受审核通知的邮箱。
     */
    @SchemaProperty(name = "接收审核通知的邮箱")
    open var email: String? = null
}