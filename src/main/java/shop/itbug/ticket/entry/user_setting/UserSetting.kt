package shop.itbug.ticket.entry.user_setting

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import shop.itbug.ticket.entry.User

@Entity
@Schema(name = "用户设置")
class UserSetting {

    @Id
    @GeneratedValue
    @Schema(description = "主键")
    val id: Long? = null


    @ManyToOne
    val user: User? = null


}