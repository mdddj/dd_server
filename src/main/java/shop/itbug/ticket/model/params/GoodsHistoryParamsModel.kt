package shop.itbug.ticket.model.params


/**
 * 商品历史价格
 * 文档地址:[https://www.dataoke.com/kfpt/api-d.html?id=36]
 * 应用场景：
 * 可用于商品详情展示该商品的价格变化
 * 接口说明：
 * 该接口返回某个商品在大淘客平台的历史券后价，通过价格的变化，帮助用户购买决策
 */
open class GoodsHistoryParamsModel {
    /**
     * 在大淘客的在线商品id（已下架的商品id不支持）
     */
    open var id: String? = null

    /**
     * 淘宝商品id
     */
    open var goodsId: String? = null
}