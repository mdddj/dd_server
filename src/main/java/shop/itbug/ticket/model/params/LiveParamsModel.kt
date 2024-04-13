package shop.itbug.ticket.model.params


/**
 * 直播好货
 * 文档地址:[https://www.dataoke.com/kfpt/api-d.html?id=35]
 * 应用场景：
 * 此接口可搭建商品精推素材直播栏目，以更丰富的展现形式推广商品，打造爆款
 * 接口说明：
 * 该接口返回官网新版咚咚抢好货直播商品数据，严格筛选，品质保障
 */
open class LiveParamsModel {
    /**
     * 选择某一天的直播商品数据，默认返回全部参与过直播，且未下架的商品。时间格式：2020-09-16
     */
    open var date: String? = null

    /**
     * 排序方式，默认为0，0-综合排序，1-商品上架时间从高到低，2-销量从高到低，3-领券量从高到低，4-佣金比例从高到低，5-价格（券后价）从高到低，6-价格（券后价）从低到高
     */
    open var sort: String? = null
}