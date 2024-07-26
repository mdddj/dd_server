package shop.itbug.ticket.model.params

import jakarta.validation.constraints.NotBlank

/**
 * 唯品会详情v2版本
 */
class WeipinhuiDetailParam {
    private val id: @NotBlank(message = "输入产品id或者url") String? = null
    private val queryDetail = false
    private val queryStock: String? = null
    private val queryReputation: String? = null
    private val queryStoreServiceCapability: String? = null
    private val queryPMSAct: String? = null
    private val extendBySpu: String? = null
    private val queryExclusiveCoupon: String? = null
    private val extendSku: String? = null
}