package shop.itbug.ticket.model.params


open class BrandListParamsModel {
    /**
     * 大淘客分类id
     */
    open var cid: String? = null

    /**
     * 分页id：常规分页方式，请直接传入对应页码（比如：1,2,3……）
     */
    open var pageSize: String? = null

    /**
     * 每页记录条数（每页记录最大支持50，如果参数大于50时取50作为每页记录条数）
     */
    open var pageId: String? = null
}