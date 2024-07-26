package shop.itbug.ticket.model.params


open class TaobaoOnePriceProductsParam {
    /**
     * 必须!
     * 分页id，支持传统的页码分页方式
     */
    open var pageId: String? = null

    /**
     * 每页条数，默认为20
     */
    open var pageSize: String? = null

    /**
     * 输出的端口类型：0.全部（默认），1.PC，2.无线
     */
    open var type: String? = null

    /**
     * 阿里妈妈上申请的渠道id
     */
    open var channelID: String? = null
}