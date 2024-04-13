package shop.itbug.ticket.entry.notice

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
@Schema(name = "站内消息通知对象")
class Notice {

    @Id
    @GeneratedValue
    val id: Long? = null

    @Schema(name = "消息内容")
    val message: String = ""

    @Schema(name = "消息标题")
    val title: String = ""



}