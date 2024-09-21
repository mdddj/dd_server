package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "线报参数")
open class SpeiderProductParam {
    @Schema(description = "线报类型：1-超值买返(默认)2-天猫超市3-整点抢购4-最新线报-所有数据5-最新线报-天猫6-最新线报-京东7-最新线报-拼多多", defaultValue = "1")
    open var topic
            : String? = null

    @Schema(description = "整点抢购时的时间戳（秒），示例：1617026400")
    open var selectTime //rush-整点抢购时的时间戳（秒），示例：1617026400
            : String? = null

    @Schema(description = "第几页")
    open var pageId: String? = null
    @Schema(description = "每页多少条")
    open var pageSize: String? = null
}