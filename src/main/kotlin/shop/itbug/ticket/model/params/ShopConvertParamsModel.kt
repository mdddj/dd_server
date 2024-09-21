package shop.itbug.ticket.model.params


/**
 * @author eee
 */
open class ShopConvertParamsModel {
    /**
     * 店铺id
     */
    open var sellerId: String? = null

    /**
     * 推广位id
     */
    open var pid: String? = null

    /**
     * 渠道id
     */
    open var relationId: String? = null

    /**
     * 店铺名称，用于返回淘口令
     */
    open var shopName: String? = null
}