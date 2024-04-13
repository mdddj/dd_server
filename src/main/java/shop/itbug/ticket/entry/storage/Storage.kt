package shop.itbug.ticket.entry.storage

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.*

@Entity
@Schema(name = "存储策略")
class Storage {

    @Id
    @GeneratedValue
    @SchemaProperty(name = "主键ID")
    val id: Long? = null

    @SchemaProperty(name = "存储路径")
    val path: String? = null

    @SchemaProperty(name="策略名称")
    val name: String? = null

    @SchemaProperty(name="备注")
    val note: String? = null

    @SchemaProperty(name="创建时间")
    val createDate: Date? = null

    @SchemaProperty(name="存储策略类型")
    val type: String? = null


}