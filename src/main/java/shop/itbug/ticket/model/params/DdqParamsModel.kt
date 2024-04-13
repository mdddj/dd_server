package shop.itbug.ticket.model.params


/**
 * 咚咚抢商品
 * 接口文档:[https://www.dataoke.com/kfpt/api-d.html?id=23]
 *
 * 应用场景：
 * 该接口可用于搭建营销活动的板块内容
 * 接口说明：
 * 接口返回每天参与整点抢购活动的高性价比商品列表。精品中的极品，只为性价比而生
 */
open class DdqParamsModel {
    /**
     * 默认为当前场次，场次时间输入方式：yyyy-mm-dd hh:mm:ss
     */
    open var roundTime: String? = null
}