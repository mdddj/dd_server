package shop.itbug.ticket.model.params


/**
 * 每日半价接口
 * 文档:[https://www.dataoke.com/kfpt/api-d.html?id=38]
 * 应用场景：
 * 可用于特色营销栏目搭建或社群分发推广
 * 接口说明：
 * 该接口返回每日半价活动商品，可用于吸引用户下单，打造半价爆款商品，提高流量变现！
 */
open class HalfDayParamsModel {
    /**
     * 默认为当前场次，场次输入格式，例如02、08、12、13
     */
    open var sessions: String? = null
}