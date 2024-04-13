package shop.itbug.ticket.model.dtk


import kotlinx.serialization.Serializable

@Serializable
data class PrivilegeLinkModel (
    val couponClickUrl: String? = null,
    val couponEndTime: String? = null,
    val couponInfo: String? = null,
    val couponStartTime: String? = null,
    val itemId: String? = null,
    val couponTotalCount: String? = null,
    val couponRemainCount: String? = null,
    val itemUrl: String? = null,
    val tpwd: String? = null,
    val longTpwd: String? = null,
    val maxCommissionRate: String? = null,
    val shortUrl: String? = null,
    val minCommissionRate: String? = null,
    val kuaiZhanUrl: String? = null,
    val originalPrice: Double? = null,
    val actualPrice: Double? = null,
    val title: String? = null
)
