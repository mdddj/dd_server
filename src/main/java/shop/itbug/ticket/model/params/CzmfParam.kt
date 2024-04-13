package shop.itbug.ticket.model.params

import jakarta.validation.constraints.NotBlank

open class CzmfParam {
    open var goodsId: @NotBlank(message = "请输入商品id") String? = null
    open var title: @NotBlank(message = "请输入商品标题") String? = null
    open var pid: String? = null
    open var relationId: String? = null
    open var couponId: String? = null
}