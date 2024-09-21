package shop.itbug.ticket.model.params


/**
 * 大淘客独家券
 * 接口文档地址:[https://www.dataoke.com/kfpt/api-d.html?id=32]
 *
 * 应用场景：
 * 大淘客平台独家优惠券，可用于创建营销栏目。
 * 接口说明：
 * 该接口返回大淘客独家的优惠券商品资源，拥有独家优势。助力淘客推广
 */
open class ExclusiveParamsModel {
    /**
     * 每页条数
     */
    open var pageSize: String? = null

    /**
     * 分页
     */
    open var pageId: String? = null

    /**
     * 大淘客的一级分类id，如果需要传多个，以英文逗号相隔，如：”1,2,3”。
     */
    open var cids: String? = null

    /**
     * 排序方式，默认为0，0-综合排序，1-商品上架时间从高到低，2-销量从高到低，3-领券量从高到低，4-佣金比例从高到低，5-价格（券后价）从高到低，6-价格（券后价）从低到高
     */
    open var sort: String? = null
}