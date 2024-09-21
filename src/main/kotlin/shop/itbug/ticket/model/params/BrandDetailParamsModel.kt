package shop.itbug.ticket.model.params


open class BrandDetailParamsModel {
    /**
     * 品牌id
     */
    open var brandId: String = ""

    /**
     * 分页id：常规分页方式，请直接传入对应页码（比如：1,2,3……） 第一页时会携带品牌信息数据
     */
    open var pageId: String = ""

    /**
     * 每页记录条数（每页支持最大记录条数100）
     */
    open var pageSize: String = ""
}