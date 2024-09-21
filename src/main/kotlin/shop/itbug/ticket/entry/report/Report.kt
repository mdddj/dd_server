package shop.itbug.ticket.entry.report

import cn.hutool.core.date.DateUtil
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.persistence.*
import shop.itbug.ticket.entry.FileInfo
import shop.itbug.ticket.entry.User
import java.util.*

@Entity
@Schema(name = "举报")
class Report {

    @Id
    @GeneratedValue
    val id: Long? = null

    @SchemaProperty(name = "举报详情")
    var content: String? = null

    @SchemaProperty(name = "举报类型")
    var type: String? = null

    @SchemaProperty(name = "举报的图片")
    @OneToMany
    var images: MutableSet<FileInfo>? = null

    @SchemaProperty(name = "举报时间")
    var createTime : Date = DateUtil.date()

    @ManyToOne
    @SchemaProperty(name = "举报人")
    var user: User? = null

    @SchemaProperty(name = "举报状态,0 : 处理中, 1: 举报已处理, 2: 举报失效")
    var status: Int? = null

    @SchemaProperty(name = "举报客户评价")
    var comment: String? = null

    @SchemaProperty(name = "资源ID,如果举报的是资源,可以传递这个参数")
    var resourceId:Long? = null
}