package shop.itbug.ticket.entry.permission

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import shop.itbug.ticket.entry.Role

@Entity
@Schema(name = "权限许可")
class Permission {

    @Id
    @GeneratedValue
    @SchemaProperty(name = "主键")
    val id: Long? = null


    @SchemaProperty(name="名称")
    var name: String? = null

    @SchemaProperty(name="注释")
    var note: String? = null

    @SchemaProperty(name="权限列表")
    @ManyToMany()
    var roles: Set<Role>? = null

}