package shop.itbug.ticket.model.params


/**
 * 短视频商品列表参数
 * 文档地址:[https://www.dataoke.com/kfpt/api-d.html?id=46]
 * 应用场景：
 * 可用于搭建短视频购物栏目，让用户边刷视频边购物~
 * 接口说明：
 * 该接口返回大淘客抖货商品合集
 */
open class VideoGoodsParamsModel {
    /**
     * 短视频分类id
     */
    open var cid: String? = null

    /**
     * 分页id：常规分页方式，请直接传入对应页码（比如：1,2,3……）
     */
    open var pageId: String? = null

    /**
     * 每页记录条数：10，20，50，100
     */
    open var pageSize: String? = null
}