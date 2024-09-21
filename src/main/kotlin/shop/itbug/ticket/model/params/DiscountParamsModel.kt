package shop.itbug.ticket.model.params


/**
 * 折上折
 * 文档地址:[https://www.dataoke.com/kfpt/api-d.html?id=39]
 * 应用场景：
 * 可用于搭建导购网站、APP、小程序等应用的特色栏目或社群分发推广
 * 接口说明：
 * 该接口返回全网最新的折上折商品，以更丰富的形式提高转化
 */
open class DiscountParamsModel {
    /**
     * 每页返回条数，每页条数支持输入10,20，50,100。默认50条
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