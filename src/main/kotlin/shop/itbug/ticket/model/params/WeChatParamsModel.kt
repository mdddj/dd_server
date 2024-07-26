package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank


@Schema(
    name = "朋友圈文案参数",
    description = "朋友圈文案参数,接口文档地址: [http://www.dataoke.com/kfpt/api-d.html?id=25],应用场景：可用于自动发单软件，app推广等场景，提供商品转化,接口说明：该接口返回有朋友圈文案素材的商品列表"
)
open class WeChatParamsModel {
    @Schema(description = "每页条数，默认为100，若小于10，则按10条处理，每页条数仅支持输入10,50,100", defaultValue = "100")
    open var pageSize: String? = null

    @NotBlank(message = "请输入分页参数")
    @Schema(description = "分页id，默认为1，支持传统的页码分页方式", defaultValue = "1")
    open var pageId: String = ""

    @Schema(
        description = "排序方式，默认为0，0-综合排序，1-商品上架时间从高到低，2-销量从高到低，3-领券量从高到低，4-佣金比例从高到低，5-价格（券后价）从高到低，6-价格（券后价）从低到高",
        defaultValue = "0"
    )
    open var sort: String? = null

    @Schema(description = "大淘客的一级分类id，如6、4")
    open var cid: String? = null

    @Schema(description = "大淘客的二级类目id，通过超级分类API获取。仅允许传一个二级id，当一级类目id和二级类目id同时传入时，会自动忽略二级类目id")
    open var subcid: String? = null

    @Schema(description = "是否预告商品，1-预告商品，0-所有商品，不填默认为0")
    open var pre: String? = null

    @Schema(description = "偏远地区包邮，1-是，0-非偏远地区，不填默认所有商品")
    open var freeshipRemoteDistrict: String? = null

    @Schema(description = "大淘客id或淘宝id，查询单个商品是否有朋友圈文案，如果有，则返回商品信息及朋友圈文案，如果没有，显示10006错误")
    open var goodsId: String? = null
}