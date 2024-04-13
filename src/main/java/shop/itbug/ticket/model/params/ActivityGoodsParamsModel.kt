package shop.itbug.ticket.model.params


open class ActivityGoodsParamsModel {
    /**
     * 必须
     * 分页id，默认为1，支持传统的页码分页方式和scroll_id分页方式，根据用户自身需求传入值。示例1：商品入库，则首次传入1，后续传入接口第一次返回的pageid，接口将持续返回符合条件的完整商品列表，该方式可以避免入库商品重复；示例2：根据pagesize和totalNum计算出总页数，按照需求返回指定页的商品（该方式可能在临近页取到重复商品）
     */
    open var pageId: String? = null

    /**
     * 每页条数，默认为100，大于100按100处理
     */
    open var pageSize: String? = null

    /**
     * 必须
     * 通过热门活动API获取的活动id
     */
    open var activityId: String? = null

    /**
     * 大淘客一级分类ID：1 -女装，2 -母婴，3 -美妆，4 -居家日用，5 -鞋品，6 -美食，7 -文娱车品，8 -数码家电，9 -男装，10 -内衣，11 -箱包，12 -配饰，13 -户外运动，14 -家装家纺
     */
    open var cid: String? = null

    /**
     * 大淘客二级分类ID：可通过超级分类接口获取二级分类id，当同时传入一级分类id和二级分类id时，以一级分类id为准
     */
    open var subcid: String? = null

    /**
     * 偏远地区包邮：1.是，0.否
     */
    open var freeshipRemoteDistrict: String? = null
}