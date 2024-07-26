package shop.itbug.ticket.model.dtk


import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

@Serializable
data class WeChatModel (
    val list: List<WeChatProductModel>? = null,
    val pageId: String? = null,
    val totalNum: Long? = null
)

@Serializable
data class WeChatProductModel (
    val id: Long? = null,
    val goodsId: String? = null,
    val goodsSign: String? = null,
    val itemLink: String? = null,
    val title: String? = null,
    val dtitle: String? = null,
    val desc: String? = null,
    val cid: Long? = null,
    val subcid: JsonArray? = null,
    val tbcid: Long? = null,
    val mainPic: String? = null,
    val marketingMainPic: String? = null,
    val originalPrice: Double? = null,
    val actualPrice: Double? = null,
    val discounts: Double? = null,
    val commissionType: Long? = null,
    val commissionRate: Double? = null,
    val couponLink: String? = null,
    val couponTotalNum: Long? = null,
    val couponReceiveNum: Long? = null,
    val couponEndTime: String? = null,
    val couponStartTime: String? = null,
    val couponPrice: Long? = null,
    val couponConditions: String? = null,
    val monthSales: Long? = null,
    val twoHoursSales: Long? = null,
    val dailySales: Long? = null,
    val brand: Long? = null,
    val brandId: Long? = null,
    val brandName: String? = null,
    val createTime: String? = null,
    val shopType: Long? = null,
    val shopName: String? = null,
    val quanMLink: Long? = null,
    val hzQuanOver: Long? = null,
    val estimateAmount: Long? = null,
    val circleText: String? = null,
    val freeshipRemoteDistrict: Long? = null,
    val shipaiPic: String? = null,
    val shipaiPicCount: Long? = null,
    val shangchaoPic: String? = null,
    val couponId: String? = null
)

