package shop.itbug.ticket.model.dtk


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTransformingSerializer

@Serializable
data class DtkOrderResult (
    val cache: Boolean? = null,
    val code: Long? = null,
    val data: DtkOrderData? = null,
    val msg: String? = null,
    val requestId: String? = null,
    val time: Long? = null
)

@Serializable
data class DtkOrderData (
    @SerialName("has_next")
    val hasNext: Boolean? = null,

    @SerialName("has_pre")
    val hasPre: Boolean? = null,

    @SerialName("page_no")
    val pageNo: Long? = null,

    @SerialName("page_size")
    val pageSize: Long? = null,

    @SerialName("position_index")
    val positionIndex: String? = null,

    val results: DtkOrderDataResults? = null
)

@Serializable
data class DtkOrderDataResults (
    @SerialName("publisher_order_dto")
    val publisherOrderDto: List<DtkOrderDto>? = null
)

@Serializable
data class DtkOrderDto (
    @SerialName("adzone_id")
    val adzoneId: Long? = null,

    @SerialName("adzone_name")
    val adzoneName: String? = null,

    @SerialName("alimama_rate")
    val alimamaRate: String? = null,

    @SerialName("alimama_share_fee")
    val alimamaShareFee: String? = null,

    @SerialName("alipay_total_price")
    val alipayTotalPrice: String? = null,

    @SerialName("click_time")
    val clickTime: String? = null,

    @SerialName("deposit_price")
    val depositPrice: String? = null,

    @SerialName("flow_source")
    val flowSource: String? = null,

    @SerialName("income_rate")
    val incomeRate: String? = null,

    @SerialName("item_category_name")
    val itemCategoryName: String? = null,

    @SerialName("item_id")
    val itemid: String? = null,

    @SerialName("item_img")
    val itemImg: String? = null,

    @SerialName("item_link")
    val itemLink: String? = null,

    @SerialName("item_num")
    val itemNum: Long? = null,

    @SerialName("item_price")
    val itemPrice: String? = null,

    @SerialName("item_title")
    val itemTitle: String? = null,

    @SerialName("marketing_type")
    val marketingType: String? = null,

    @SerialName("modified_time")
    val modifiedTime: String? = null,

    @SerialName("order_type")
    val orderType: String? = null,

    @SerialName("pay_price")
    val payPrice: String? = null,

    @SerialName("pub_id")
    val pubid: Long? = null,

    @SerialName("pub_share_fee")
    val pubShareFee: String? = null,

    @SerialName("pub_share_fee_for_commission")
    val pubShareFeeForCommission: Double? = null,

    @SerialName("pub_share_fee_for_sdy")
    val pubShareFeeForSdy: Long? = null,

    @SerialName("pub_share_pre_fee")
    val pubSharePreFee: String? = null,

    @SerialName("pub_share_pre_fee_for_commission")
    val pubSharePreFeeForCommission: Double? = null,

    @SerialName("pub_share_pre_fee_for_sdy")
    val pubSharePreFeeForSdy: Long? = null,

    @SerialName("pub_share_rate")
    val pubShareRate: String? = null,

    @SerialName("pub_share_rate_for_sdy")
    val pubShareRateForSdy: Long? = null,

    @SerialName("refund_tag")
    val refundTag: Long? = null,

    @SerialName("seller_nick")
    val sellerNick: String? = null,

    @SerialName("seller_shop_title")
    val sellerShopTitle: String? = null,

    @SerialName("site_id")
    val siteid: Long? = null,

    @SerialName("site_name")
    val siteName: String? = null,

    @SerialName("relation_id")
    val relationId: Long? = null,

    @SerialName("special_id")
    val specialId: Long? = null,

    @SerialName("subsidy_fee")
    val subsidyFee: String? = null,

    @SerialName("subsidy_rate")
    val subsidyRate: String? = null,

    @SerialName("subsidy_type")
    val subsidyType: String? = null,

    @SerialName("tb_deposit_time")
    val tbDepositTime: String? = null,

    @SerialName("tb_paid_time")
    val tbPaidTime: String? = null,

    @SerialName("terminal_type")
    val terminalType: String? = null,

    @SerialName("tk_create_time")
    val tkCreateTime: String? = null,

    @SerialName("tk_deposit_time")
    val tkDepositTime: String? = null,

    @SerialName("tk_earning_time")
    val tkEarningTime: String? = null,

    @SerialName("tk_order_role")
    val tkOrderRole: Long? = null,

    @SerialName("tk_paid_time")
    val tkPaidTime: String? = null,

    @SerialName("tk_status")
    val tkStatus: Long? = null,

    @SerialName("tk_total_rate")
    val tkTotalRate: String? = null,

    @SerialName("tk_total_rate_for_sdy")
    val tkTotalRateForSdy: Long? = null,

    @SerialName("total_commission_fee")
    val totalCommissionFee: String? = null,

    @SerialName("total_commission_rate")
    val totalCommissionRate: String? = null,

    @SerialName("trade_id")
    val tradeid: String? = null,

    @SerialName("trade_parent_id")
    val tradeParentid: String? = null
)

object SnakeCaseSerializer : JsonTransformingSerializer<DtkOrderDto>(DtkOrderDto.serializer()) {
    override fun transformSerialize(element: JsonElement): JsonElement {
        if (element !is JsonObject) return element
        val newMap = mutableMapOf<String,JsonElement>()
        for ((key, value) in element) {
            val snakeCaseKey = camelToSnakeCase(key)
            newMap[snakeCaseKey] = value
        }
        return JsonObject(newMap)
    }
    override fun transformDeserialize(element: JsonElement): JsonElement {
        if (element !is JsonObject) return element
        val newMap = mutableMapOf<String,JsonElement>()
        for ((key, value) in element) {
            val camelCaseKey = snakeToCamelCase(key)
            newMap[camelCaseKey] = value
        }
        return JsonObject(newMap)
    }
    private fun camelToSnakeCase(input: String): String {
        return buildString {
            input.forEachIndexed { index, c ->
                if (c.isUpperCase()) {
                    if (index != 0) append('_')
                    append(c.lowercaseChar())
                } else {
                    append(c)
                }
            }
        }
    }
    private fun snakeToCamelCase(input: String): String {
        return buildString {
            var capitalizeNext = false
            input.forEach { c ->
                capitalizeNext = if (c == '_') {
                    true
                } else {
                    append(if (capitalizeNext) c.uppercaseChar() else c)
                    false
                }
            }
        }
    }
}