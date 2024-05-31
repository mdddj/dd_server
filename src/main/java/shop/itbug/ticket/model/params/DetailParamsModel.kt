package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema

data class DetailParamsModel(
    /**
     * 大淘客商品id，请求时id或goodsId必填其中一个，若均填写，将优先查找当前单品id
     */

    val productsId: String?,
    @Schema(description = "大淘客商品id，请求时id或goodsId必填其中一个，若均填写，将优先查找当前单品id")
    var id: String? = productsId,
    /**
     * 淘宝商品id，id或goodsId必填其中一个，若均填写，将优先查找当前单品id
     */
    @Schema(description = "淘宝商品id，id或goodsId必填其中一个，若均填写，将优先查找当前单品id")
    open var goodsId: String? = null
)