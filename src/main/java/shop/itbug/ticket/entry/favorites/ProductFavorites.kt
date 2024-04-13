package shop.itbug.ticket.entry.favorites

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.validation.constraints.NotBlank
import shop.itbug.ticket.entry.User
import java.util.*

@Entity
@Schema(name = "用户产品收藏夹")
class ProductFavorites {

    @Id
    @GeneratedValue
    val id: Long? = null

    @ManyToOne
    @JsonIgnore
    var user: User? = null

    @SchemaProperty(name ="产品ID,用来查询产品信息的ID")
    @field: NotBlank(message = "请输入产品ID")
    var productId: String? = null

    @SchemaProperty(name = "产品类型,可选值是淘宝,拼多多,京东等等")
    @field: NotBlank(message = "请输入产品类型,比如淘宝,拼多多,京东等等")
    var type: String? = null

    @SchemaProperty(name = "优惠结束时间")
    @field: NotBlank(message = "请输入产品优惠结束时间")
    var endTime: String? = null

    @SchemaProperty(name = "产品主图")
    @field: NotBlank(message = "请输入产品主图")
    var imageUrl: String? =null

    @SchemaProperty(name = "产品标题")
    @field: NotBlank(message = "请输入产品标题")
    var title: String? = null

    @SchemaProperty(name = "优惠券金额")
    @field: NotBlank(message = "请输入产品优惠券金额")
    var amount: String? = null

    @SchemaProperty(name = "到手价")
    @field: NotBlank(message = "请输入产品最终到手价")
    var arrivalPrice: String? = null

    @SchemaProperty(name = "收藏时间")
    val createTime = Date()

}