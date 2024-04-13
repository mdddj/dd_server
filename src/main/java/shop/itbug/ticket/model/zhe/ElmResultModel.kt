package shop.itbug.ticket.model.zhe


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ElmResultModel (
    @SerialName("alibaba_alsc_union_eleme_promotion_officialactivity_get_response")
    val alibabaAlscUnionElemePromotionOfficialactivityGetResponse: AlibabaAlscUnionElemePromotionOfficialactivityGetResponse? = null
)

@Serializable
data class AlibabaAlscUnionElemePromotionOfficialactivityGetResponse (
    val data: ElmData? = null,
    val message: String? = null,

    @SerialName("result_code")
    val resultCode: Long? = null,

    @SerialName("request_id")
    val requestId: String? = null
)

@Serializable
data class ElmData (
    val description: String? = null,

    @SerialName("end_time")
    val endTime: Long? = null,

    val id: String? = null,
    val link: ElmLink? = null,
    val picture: String? = null,

    @SerialName("start_time")
    val startTime: Long? = null,

    val title: String? = null
)

@Serializable
data class ElmLink (
    @SerialName("alipay_mini_url")
    val alipayMiniUrl: String? = null,

    @SerialName("ele_scheme_url")
    val eleSchemeUrl: String? = null,

    @SerialName("h5_short_link")
    val h5ShortLink: String? = null,

    @SerialName("h5_url")
    val h5url: String? = null,

    @SerialName("mini_qrcode")
    val miniQrcode: String? = null,

    val picture: String? = null,

    @SerialName("tb_mini_qrcode")
    val tbMiniQrcode: String? = null,

    @SerialName("tb_qr_code")
    val tbQrCode: String? = null,

    @SerialName("wx_appid")
    val wxAppid: String? = null,

    @SerialName("wx_path")
    val wxPath: String? = null,

    @SerialName("h5_mini_qrcode")
    val h5MiniQrcode: String? = null
)
