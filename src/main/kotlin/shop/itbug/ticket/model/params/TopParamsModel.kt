package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(name = "榜单参数")
open class TopParamsModel {
    @NotBlank(message = "请输入榜单类型 (1.实时榜 2.全天榜 3.热推榜 4.复购榜 5.热词飙升榜 6.热词排行榜 7.综合热搜榜)")
    @Schema(description = "榜单类型，1.实时榜 2.全天榜 3.热推榜 4.复购榜 5.热词飙升榜 6.热词排行榜 7.综合热搜榜", defaultValue = "1")
    open var rankType: String = ""

    @Schema(description = "大淘客一级类目id，仅对实时榜单、全天榜单有效")
    open var cid: String? = null

    /**
     *
     */
    @Schema(description = "每页条数返回条数（支持10,20.50，默认返回20条）", defaultValue = "50")
    open var pageSize: String? = null

    /**
     *
     */
    @Schema(description = "分页id：常规分页方式，请直接传入对应页码（比如：1,2,3……）。超过200条，分页返回为空", defaultValue = "1")
    open var pageId: String = ""
}