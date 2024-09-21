package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema

/**
 * 9.9包邮商品
 * 文档地址:[https://www.dataoke.com/kfpt/api-d.html?id=15]
 */
@Schema(name = "9.9包邮参数")
open class NineNineParamsModel {
    /**
     * 每页条数
     */
    @Schema(description = "每页条数", defaultValue = "10")
    open var pageSize: String? = null

    /**
     * 分页id
     */
    @Schema(description = "分页ID", defaultValue = "1")
    open var pageId: String? = null

    /**
     * 9.9精选的类目id，分类id请求详情：-1-精选，1 -5.9元区，2 -9.9元区，3 -19.9元区（调整字段）
     */
    @Schema(description = "9.9精选的类目id，分类id请求详情：-1-精选，1 -5.9元区，2 -9.9元区，3 -19.9元区（调整字段）", defaultValue = "-1")
    open var nineCid: String? = null
}