package shop.itbug.ticket.admin.model

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort


fun PageModel.getPageable(): PageRequest {
    return PageRequest.of(getCurrentPage(), pageSize)
}
fun PageModel.getPageable(property: String): PageRequest {
    return PageRequest.of(getCurrentPage(), pageSize,Sort.Direction.DESC,property)
}
@Schema(name = "分页入参(参数)")
open class PageModel(
    @Schema(description = "第几页,默认第一页")
    open var page: Int = 1,

    @Schema(description = "每页返回数量")
    open var pageSize: Int = 20,

    @Schema(description = "每页大小,如果不传page,则需要传current,这里是antd表格专用")
    open var current: Int? = null,

    @Schema(description = "页数(page字段)是否需要减1,为了适配antd表格默认为1的问题")
    open var remove: Int? = 0
)


/**
 * 适配多种格式
 */
fun PageModel.getCurrentPage(): Int {
    if (current != null) {
        var p = current!!
        if (remove != null) {
            p -= remove!!
        }
        return p
    }
    var p = page
    if (remove != null) {
        p -= remove!!
    }
    return p
}