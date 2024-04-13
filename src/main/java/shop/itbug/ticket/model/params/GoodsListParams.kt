package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "产品列表参数")
open class GoodsListParams {
    /**
     * 默认为1，支持传统的页码分页方式和scroll_id分页方式，根据用户自身需求传入值。
     * 示例1：商品入库，则首次传入1，后续传入接口返回的pageid，接口将持续返回符合条件的完整商品列表，该方式可以避免入口商品重复；
     * 示例2：根据pagesize和totalNum计算出总页数，按照需求返回指定页的商品（该方式可能在临近页取到重复商品
     */
    @Schema(description = "默认为1，支持传统的页码分页方式和scroll_id分页方式，根据用户自身需求传入值。示例1：商品入库，则首次传入1，后续传入接口返回的pageid，接口将持续返回符合条件的完整商品列表，该方式可以避免入口商品重复；示例2：根据pagesize和totalNum计算出总页数，按照需求返回指定页的商品（该方式可能在临近页取到重复商品", defaultValue = "1")
    open var pageId: String? = null

    /**
     * 每页条数，默认为100，最大值200，若小于10，则按10条处理，每页条数仅支持输入10,50,100,200
     */
    @Schema(description = "每页条数，默认为100，最大值200，若小于10，则按10条处理，每页条数仅支持输入10,50,100,200", defaultValue = "10")
    open var pageSize: String? = null

    /**
     * 排序方式，默认为0，0-综合排序，1-商品上架时间从高到低，2-销量从高到低，3-领券量从高到低，4-佣金比例从高到低，5-价格（券后价）从高到低，6-价格（券后价）从低到高
     */
    @Schema(description = "排序方式，默认为0，0-综合排序，1-商品上架时间从高到低，2-销量从高到低，3-领券量从高到低，4-佣金比例从高到低，5-价格（券后价）从高到低，6-价格（券后价）从低到高", defaultValue = "0")
    open var sort: String? = null

    /**
     * 大淘客的一级分类id，如果需要传多个，以英文逗号相隔，如：”1,2,3”。当一级类目id和二级类目id同时传入时，会自动忽略二级类目id
     */
    @Schema(description = "大淘客的一级分类id，如果需要传多个，以英文逗号相隔，如：”1,2,3”。当一级类目id和二级类目id同时传入时，会自动忽略二级类目id")
    open var cids: String? = null

    /**
     * 大淘客的二级类目id，通过超级分类API获取。仅允许传一个二级id，当一级类目id和二级类目id同时传入时，会自动忽略二级类目id
     */
    @Schema(description = "大淘客的二级类目id，通过超级分类API获取。仅允许传一个二级id，当一级类目id和二级类目id同时传入时，会自动忽略二级类目id")
    open var subcid: String? = null

    /**
     * 商品卖点，1.拍多件活动；2.多买多送；3.限量抢购；4.额外满减；6.买商品礼赠
     */
    @Schema(description = " 商品卖点，1.拍多件活动；2.多买多送；3.限量抢购；4.额外满减；6.买商品礼赠")
    open var specialId: String? = null

    /**
     * 1-聚划算商品，0-所有商品，不填默认为0
     */
    @Schema(description = "1-聚划算商品，0-所有商品，不填默认为0")
    open var juHuaSuan: String? = null

    /**
     * 1-淘抢购商品，0-所有商品，不填默认为0
     */
    @Schema(description = "1-淘抢购商品，0-所有商品，不填默认为0", defaultValue = "0")
    open var taoQiangGou: String? = null

    /**
     * 1-天猫商品， 0-非天猫商品，不填默认所有商品
     */
    @Schema(description = "1-天猫商品， 0-非天猫商品，不填默认所有商品")
    open var tmall: String? = null

    /**
     * 1-天猫超市商品， 0-所有商品，不填默认为0
     */
    @Schema(description = "1-天猫超市商品， 0-所有商品，不填默认为0")
    open var tchaoshi: String? = null

    /**
     * 1-金牌卖家商品，0-所有商品，不填默认为0
     */
    @Schema(description = "1-金牌卖家商品，0-所有商品，不填默认为0")
    open var goldSeller: String? = null

    /**
     * 1-海淘商品， 0-所有商品，不填默认为0
     */
    @Schema(description = " 1-海淘商品， 0-所有商品，不填默认为0")
    open var haitao: String? = null

    /**
     * 1-预告商品，0-所有商品，不填默认为0
     */
    @Schema(description = "1-预告商品，0-所有商品，不填默认为0")
    open var pre: String? = null

    /**
     * 1-活动预售商品，0-所有商品，不填默认为0。（10.30新增字段）
     */
    @Schema(description = "1-活动预售商品，0-所有商品，不填默认为0。（10.30新增字段）")
    open var preSale: String? = null

    /**
     * 1-品牌商品，0-所有商品，不填默认为0
     */
    @Schema(description = " 1-品牌商品，0-所有商品，不填默认为0")
    open var brand: String? = null

    /**
     * 当brand传入0时，再传入brandIds可能无法获取结果。品牌id可以传多个，以英文逗号隔开，如：”345,321,323”
     */
    @Schema(description = "当brand传入0时，再传入brandIds可能无法获取结果。品牌id可以传多个，以英文逗号隔开，如：”345,321,323”")
    open var brandIds: String? = null

    /**
     * 价格（券后价）下限
     */
    @Schema(description = "价格（券后价）下限")
    open var priceLowerLimit: String? = null

    /**
     * 价格（券后价）上限
     */
    @Schema(description = "价格（券后价）上限")
    open var priceUpperLimit: String? = null

    /**
     * 最低优惠券面额
     */
    @Schema(description = "最低优惠券面额")
    open var couponPriceLowerLimit: String? = null

    /**
     * 最低佣金比率
     */
    @Schema(description = "最低佣金比率")
    open var commissionRateLowerLimit: String? = null

    /**
     * 最低月销量
     */
    @Schema(description = "最低月销量")
    open var monthSalesLowerLimit: String? = null

    /**
     * 偏远地区包邮，1-是，0-非偏远地区，不填默认所有商品
     */
    @Schema(description = "偏远地区包邮，1-是，0-非偏远地区，不填默认所有商品")
    open var freeshipRemoteDistrict: String? = null
}