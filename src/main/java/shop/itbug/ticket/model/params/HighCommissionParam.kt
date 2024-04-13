package shop.itbug.ticket.model.params


open class HighCommissionParam {
    /**
     * 每页返回条数，每页条数支持输入10,20，50,100。
     */
    open var pageSize: String? = null

    /**
     * 分页id：常规分页方式，请直接传入对应页码（比如：1,2,3……）
     */
    open var pageId: String? = null

    /**
     * 大淘客的一级分类id
     */
    open var cid: String? = null

    /**
     * 排序：默认按佣金比例降序1-按销量降序，2-按销量升序，3-按佣金比例降序，4-按佣金比例升序。
     */
    open var sort: String? = null
}