package shop.itbug.ticket.entry

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank

/**
 * 轮播图组件
 */
@Entity
@Schema(name = "轮播图(实体)")
open class Carousel {

    @Id
    @GeneratedValue
    @Schema(description = "ID")
    open var id: Long? = null

    @NotBlank(message = "请输入轮播图图片地址")
    @Schema(description = "图片URL")
    open var imgUrl: String? = null

    @Schema(description = "标题")
    open var title: String? = null

    @Schema(description = "跳转url")
    open var location: String? = null

    @Schema(description = "跳转类型")
    open var clickType: String? = null

    @Schema(description = "跳转携带参数")
    open var params: String? = null
}