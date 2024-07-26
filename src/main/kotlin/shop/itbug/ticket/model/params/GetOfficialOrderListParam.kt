package shop.itbug.ticket.model.params

import jakarta.validation.constraints.NotBlank

/**
 * 京东订单查询
 */
open class GetOfficialOrderListParam {
    open var pageNo: String? = null
    open var pageSize: String? = null
    open var type: @NotBlank(message = "请输入订单时间查询类型") String? = null
    open var childUnionId: String? = null
    open var key: @NotBlank(message = "请输入授权key") String? = null
    open var startTime: @NotBlank(message = "请输入开始查询时间") String? = null
    open var endTime: @NotBlank(message = "请输入结束时间") String? = null
    open var fields: String? = null
}