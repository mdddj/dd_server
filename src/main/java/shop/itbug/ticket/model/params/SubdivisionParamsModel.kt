package shop.itbug.ticket.model.params


/**
 * 细分类目榜
 * 文档地址:[https://www.dataoke.com/kfpt/api-d.html?id=41]
 * 应用场景：
 * 可用于搜索展示某个商品，在细分类目中的销量排行；也可用于单品详情，展示该商品细分类目的排名。帮助用户购买决策，增加购买选择。
 * 接口说明：
 * 该接口返回某个具体细分类目榜单前20名商品数据。
 */
open class SubdivisionParamsModel {
    /**
     * 细分类目榜分类id（从商品详情获取）
     */
    open var subdivisionId: String? = null
}