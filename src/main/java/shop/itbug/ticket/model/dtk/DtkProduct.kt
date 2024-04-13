package shop.itbug.ticket.model.dtk

// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json       = Json { allowStructuredMapKeys = true }
// val dtkProduct = json.parse(DtkProduct.serializer(), jsonString)


import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray


@Serializable
data class DtkProduct (
    val activityType: Long? = null,
    val actualPrice: Double? = null,
    val commissionRate: Double? = null,
    val commissionType: Long? = null,
    val couponConditions: String? = null,
    val couponEndTime: String? = null,
    val couponLink: String? = null,
    val couponPrice: Long? = null,
    val couponReceiveNum: Long? = null,
    val couponStartTime: String? = null,
    val couponTotalNum: Long? = null,
    val createTime: String? = null,
    val dailySales: Long? = null,
    val desc: String? = null,
    val dtitle: String? = null,
    val estimateAmount: Long? = null,
    val freeshipRemoteDistrict: Long? = null,
    val fresh: Long? = null,
    val goodsId: String? = null,
    val guideName: String? = null,
    val haitao: Long? = null,
    val hotPush: Long? = null,
    val hzQuanOver: Long? = null,
    val id: Long? = null,
    val lowest: Long? = null,
    val mainPic: String? = null,
    val marketGroup: List<Long>? = null,
    val monthSales: Long? = null,
    val newRankingGoods: Long? = null,
    val num: Long? = null,
    val originalPrice: Double? = null,
    val quanMLink: Long? = null,
    val ranking: Long? = null,
    val sellerId: String? = null,
    val shopName: String? = null,
    val shopType: Long? = null,
    val specialText: JsonArray? = null,
    val tchaoshi: Long? = null,
    val title: String? = null,
    val twoHoursSales: Long? = null,
    val yunfeixian: Long? = null
)
