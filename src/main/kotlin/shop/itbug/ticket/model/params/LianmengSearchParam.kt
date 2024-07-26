package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema
@Schema(name = "超级搜索参数")
open class LianmengSearchParam {
    @Schema(description = "搜索类型：0-综合结果，1-大淘客商品，2-联盟商品", defaultValue = "0")
    open var type: String = ""

    @Schema(description = "请求的页码", defaultValue = "1")
    open var pageId: String = ""

    @Schema(description = "每页条数，默认为20，最大值100", defaultValue = "20")
    open var pageSize: String? = null

    @Schema(description = "搜索关键字")
    open var keyWords: String = ""

    @Schema(description = "是否天猫商品：1-天猫商品，0-所有商品，不填默认为0", defaultValue = "0")
    open var tmall: String? = null

    @Schema(description = "是否海淘商品：1-海淘商品，0-所有商品，不填默认为0", defaultValue = "0")
    open var haitao: String? = null


    @Schema(description = "排序字段信息 销量（total_sales） 价格（price），排序_des（降序），排序_asc（升序），示例：升序查询销量total_sales_asc 新增排序字段和排序方式，默认为0，0-综合排序，1-销量从高到低，2-销量从低到高，3-佣金比例从低到高，4-佣金比例从高到低，5-价格从高到低，6-价格从低到高(2021/1/15新增字段，之前的排序方式也可以使用)", defaultValue = "0")
    open var sort: String? = null

    @Schema(description = "会员运营id")
    open var specialId: String? = null

    @Schema(description = "渠道id将会和传入的pid进行验证，验证通过将正常转链，请确认填入的渠道id是正确的channelId对应联盟的relationId")
    open var channelId: String? = null

    @Schema(description = "商品券后价下限(2021/1/15新增字段)")
    open var priceLowerLimit: String? = null

    @Schema(description = "商品券后价上限(2021/1/15新增字段)")
    open var priceUpperLimit: String? = null


    @Schema(description = "淘客佣金比率上限(2021/1/15新增字段)")
    open var endTkRate: String? = null

    @Schema(description = "淘客佣金比率下限(2021/1/15新增字段)")
    open var startTkRate: String? = null

    @Schema(description = "是否有券，1为有券，默认为全部(2021/1/15新增字段)")
    open var hasCoupon: String? = null
}