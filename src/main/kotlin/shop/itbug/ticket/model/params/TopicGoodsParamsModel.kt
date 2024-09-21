package shop.itbug.ticket.model.params


/**
 * 精选专题商品列表参数
 */
open class TopicGoodsParamsModel {
    open var pageId: String? = null
    open var pageSize: String? = null

    /**
     * 专辑id，通过精选专辑API获取的活动id
     */
    open var topicId: String? = null
}