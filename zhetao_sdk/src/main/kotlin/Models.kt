import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val parseJson = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
    serializersModule = SerializersModule {
        polymorphic(ZheAttribute::class) {
            subclass(JdUnionRequestParams::class)
            subclass(JDProductItem::class)
        }
    }
}

@Serializable
sealed class ZheAttribute {
    fun toMap(): Map<String, String> {
        val jsonString = parseJson.encodeToString(this)
        val jsonObject = parseJson.decodeFromString<JsonObject>(jsonString)
        val map = mutableMapOf<String, String>()
        jsonObject.forEach { (key, value) ->
            if (value is JsonPrimitive) {
                // 处理字符串值，去掉可能存在的引号
                var strValue = value.toString()
                if (strValue.startsWith("\"") && strValue.endsWith("\"")) {
                    strValue = strValue.substring(1, strValue.length - 1)
                }
                map[key] = strValue
            } else {
                map[key] = value.toString()
            }
        }
        return map
    }

    override fun toString(): String {
        return parseJson.encodeToString(this)
    }
}

@Serializable
data class JdUnionRequestParams(
    @SerialName("appkey") val appkey: String, // 折淘客对接秘钥，必填
    @SerialName("materialId") val materialId: String, // 推广物料URL或SKU ID，必填，**需进行URL编码！**
    @SerialName("unionId") val unionId: String, // 京东联盟ID，必填
    @SerialName("positionId") val positionId: String? = null, // 自定义推广位ID，可选，**可自定义数字！**
    @SerialName("pid") val pid: String? = null, // 联盟子推客身份标识，可选
    @SerialName("couponUrl") val couponUrl: String? = null, // 优惠券领取链接，可选，**需进行URL编码！**
    @SerialName("subUnionId") val subUnionId: String? = null, // 子渠道标识，可选
    @SerialName("chainType") val chainType: String? = null, // 转链类型，可选: "1" (长链), "2" (短链), "3" (长链+短链)
    @SerialName("giftCouponKey") val giftCouponKey: String? = null, // 礼金批次号，可选
    @SerialName("signurl") val signurl: String? = null // 是否整合商品详情API，可选: "0" (默认), "5"
) : ZheAttribute()

//京东产品项目
@Serializable
data class JDProductItem(
    @SerialName("code") val code: String, // 折淘客编号
    @SerialName("type_one_id") val typeOneId: String, // 分类ID
    @SerialName("tao_id") val taoId: String, // 商品ID
    @SerialName("title") val title: String, // 商品短标题
    @SerialName("jianjie") val jianjie: String, // 商品简介
    @SerialName("pict_url") val pictUrl: String, // 商品主图
    @SerialName("user_type") val userType: String, // 是否京东自营 (0:非自营, 1:自营)
    @SerialName("seller_id") val sellerId: String, // 卖家ID
    @SerialName("shop_dsr") val shopDsr: String, // 商品描述分
    @SerialName("volume") val volume: String, // 年销量
    @SerialName("size") val size: String, // 折扣价
    @SerialName("quanhou_jiage") val quanhouJiage: String, // 券后价
    @SerialName("date_time_yongjin") val dateTimeYongjin: String, // 数据更新时间
    @SerialName("tkrate3") val tkrate3: String, // 佣金比率
    @SerialName("yongjin_type") val yongjinType: String, // 佣金类型
    @SerialName("coupon_id") val couponId: String, // 优惠券ID
    @SerialName("coupon_start_time") val couponStartTime: String, // 优惠券开始时间
    @SerialName("coupon_end_time") val couponEndTime: String, // 优惠券结束时间
    @SerialName("coupon_info_money") val couponInfoMoney: String, // 优惠券金额
    @SerialName("coupon_total_count") val couponTotalCount: String, // 优惠券总数量
    @SerialName("coupon_remain_count") val couponRemainCount: String, // 优惠券剩余数量
    @SerialName("coupon_info") val couponInfo: String, // 优惠券信息
    @SerialName("juhuasuan") val juhuasuan: String, // 是否京东配送 (1是)
    @SerialName("taoqianggou") val taoqianggou: String, // 是否京东拼购 (1是)
    @SerialName("haitao") val haitao: String, // 是否海淘 (1是)
    @SerialName("jiyoujia") val jiyoujia: String, // 是否京喜商品 (1是)
    @SerialName("jinpaimaijia") val jinpaimaijia: String, // 是否京东好店 (1是)
    @SerialName("pinpai") val pinpai: String, // 是否精选品牌 (1是)
    @SerialName("pinpai_name") val pinpaiName: String, // 品牌名称
    @SerialName("yunfeixian") val yunfeixian: String, // 是否有运费险 (1有)
    @SerialName("nick") val nick: String, // 卖家昵称
    @SerialName("small_images") val smallImages: String, // 商品小图列表 (用 | 分隔)
    @SerialName("white_image") val whiteImage: String, // 商品白底图
    @SerialName("tao_title") val taoTitle: String, // 商品长标题
    @SerialName("provcity") val provcity: String, // 宝贝所在地
    @SerialName("shop_title") val shopTitle: String, // 店铺名称
    @SerialName("zhibo_url") val zhiboUrl: String, // 视频地址
    @SerialName("sellCount") val sellCount: String, // 网页实时总销量(废弃)
    @SerialName("commentCount") val commentCount: String, // 评论数量
    @SerialName("favcount") val favcount: String, // 收藏数量
    @SerialName("score1") val score1: String, // 宝贝描述分
    @SerialName("score2") val score2: String, // 卖家服务分
    @SerialName("score3") val score3: String, // 物流服务分
    @SerialName("creditLevel") val creditLevel: String, // 店铺评分
    @SerialName("shopIcon") val shopIcon: String, // 店铺logo
    @SerialName("pcDescContent") val pcDescContent: String, // 图文详情图片地址 (用 | 分隔)
    @SerialName("item_url") val itemUrl: String, // 商品url
    @SerialName("category_id") val categoryId: String, // 叶子类目id
    @SerialName("category_name") val categoryName: String, // 叶子类目name
    @SerialName("level_one_category_id") val levelOneCategoryId: String, // 一级类目id
    @SerialName("level_one_category_name") val levelOneCategoryName: String, // 一级类目name
    @SerialName("tkfee3") val tkfee3: String, // 返佣金额
    @SerialName("biaoqian") val biaoqian: String, // 店铺活动
    @SerialName("tag") val tag: String, // 朋友圈文案 (需 urldecode)
    @SerialName("date_time") val dateTime: String, // 数据添加时间
    @SerialName("coupon_click_url") val couponClickUrl: String, // 推广长链接
    @SerialName("shorturl") val shorturl: String, // 推广短链接
) : ZheAttribute()


@Serializable
data class StatusContentResponse<T:ZheAttribute>(
    @SerialName("status")
    val status: Int,
    @SerialName("content")
    val content: List<T>

) : ZheAttribute()



@Serializable
data class ErrorResponse(
    val code: String,
    @SerialName("zh_desc") val zhDesc: String,
    @SerialName("en_desc") val enDesc: String,
    @SerialName("request_id") val requestId: String
): ZheAttribute()

@Serializable
data class JdErrorResponse(
    @SerialName("error_response") val errorResponse: ErrorResponse
): ZheAttribute()

sealed class ApiErrorException(message: String) : Exception(message){
    data class ErrorResponse(val errorResponse: JdErrorResponse): ApiErrorException(errorResponse.errorResponse.zhDesc)
    data class Simple(override val message: String): ApiErrorException(message)

    override fun toString(): String {
        return parseJson.encodeToString(this)
    }
}