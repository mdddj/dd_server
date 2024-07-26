package shop.itbug.ticket.entry

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.io.Serializable

@Entity
@Schema(name = "企业(实体)")
open class Enterprise : Serializable {

    @Id
    @GeneratedValue
    @SchemaProperty(name = "主键ID")
    open var id: Long? = null

    @SchemaProperty(name = "企业名称")
    open var name: String = ""


    @SchemaProperty(name = "企业介绍")
    open var profile: String = "未填写介绍"

    @SchemaProperty(name = "负责人联系手机号")
    open var phone: String = ""

    @SchemaProperty(name = "负责人联系QQ号")
    open var qq: String = ""

    @SchemaProperty(name = "负责人联系微信")
    open var wechat: String = ""

    @SchemaProperty(name = "是否启用")
    open var isEnable: Boolean = true

    companion object {
        fun new(id: Long) : Enterprise {
            return Enterprise().apply { this.id = id }
        }
    }
}