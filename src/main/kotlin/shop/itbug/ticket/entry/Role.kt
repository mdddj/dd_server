package shop.itbug.ticket.entry

import cn.hutool.core.date.DateUtil
import com.alibaba.fastjson2.annotation.JSONField
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import shop.itbug.ticket.entry.permission.Permission
import java.io.Serializable
import java.util.*

/**
 * @author ldd
 */
@Entity
@Schema(name = "权限设计")
class Role : Serializable, GrantedAuthority {
    @Id
    @GeneratedValue
    @Schema(description = "主键ID")
    var id: Long? = null

    @Schema(description = "名称")
    var name: String = ""

    @Schema(description = "备注")
    var note: String? = null

    @Schema(description = "创建时间")
    var createDate: Date? = null

    @Schema(description = "启用状态")
    var status: Int? = null

    @Schema(description = "排序")
    var sort: Int? = null

    @Schema(description = "图标")
    var icon: String? = null

    @Schema(description = "许可列表")
    @ManyToMany(fetch = FetchType.EAGER)
    var permissions: MutableSet<Permission>? = null

    /**
     * 拥有这个权限的用户
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JSONField(serialize = false)
    var users: MutableSet<User>? = null


    override fun getAuthority(): String {
        return name
    }

    companion object {
        fun createRole(name: String, desc: String?): Role {
            return Role().apply {
                this.name = name
                note = desc
                createDate = DateUtil.date()
                sort = 1
            }
        }
    }
}