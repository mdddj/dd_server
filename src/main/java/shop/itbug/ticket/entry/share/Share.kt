package shop.itbug.ticket.entry.share

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Schema(name = "产品类型")
enum class ProductType {
    @Schema(description = "淘宝")
    Tb,

    @Schema(description = "拼多多")
    Pdd,

    @Schema(description = "京东")
    Jd
}

@Entity
@Schema(name = "分享")
open class Share {

    @Id
    @GeneratedValue
    open var id: Long? = null

    @Schema(description = "产品标题")
    open var title: String? = null

    @Schema(description = "产品价格")
    open var price: String? = null

    @Schema(description = "产品主图")
    open var image: String? = null

    @Schema(description = "产品主键")
    open var productId: String? = null

    @Schema(description = "产品平台")
    open var type: ProductType? = null


}