package shop.itbug.ticket.service.impl

import cn.hutool.http.HttpUtil
import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import jakarta.annotation.Resource
import kotlinx.serialization.json.Json
import org.apache.commons.lang3.StringUtils
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import shop.itbug.ticket.ex.log
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.model.params.OpenScPublisherGetParam
import shop.itbug.ticket.model.params.ScResult
import shop.itbug.ticket.model.params.TbkScPublisherInfoGetResponseMapDatum
import shop.itbug.ticket.model.params.toMutableMap
import shop.itbug.ticket.model.zhe.MeituanResult
import shop.itbug.ticket.model.zhe.MeitunTuiGuangParam
import shop.itbug.ticket.model.zhe.WeipinghuiXianbaoParam
import shop.itbug.ticket.service.ZheTkService
import shop.itbug.ticket.service.config.ZheConfigService
import shop.itbug.ticket.service.redis.RedisKeys
import java.util.*

@Service
class ZheTkServiceImpl : ZheTkService {
    @Resource
    private lateinit var zheConfigService: ZheConfigService

    /**
     * 获取美团推广数据
     *
     * @param param 参数
     * @return json
     */
    @Cacheable(RedisKeys.ZHE_TAO_KE+"getMeiTuan#24",key = "#param")
    override fun getMeiTuan(param: MeitunTuiGuangParam): MeituanResult? {
        val copy = mutableMapOf<String, Any?>()
        copy.putAll(paramsMap)
        copy["actId"] = param.actId
        copy["linkType"] = param.linkType
        copy["miniCode"] = param.miniCode
        copy["miniCode2"] = param.miniCode2
        copy["miniCode3"] = param.miniCode3
        copy["platform"] = param.platform
        copy["customer_id"] = param.customerId

        try {
            val result = HttpUtil.post(
                "https://api.zhetaoke.com:10001/api/open_meituan_generateLink.ashx",
                copy
            )
            log().info(result)
            val jsonObject = JSONObject.parseObject(result)
            if (jsonObject.getIntValue("status") != 0) {
                throw BizException("很抱歉,活动已经结束")
            }
            return JSONObject.parseObject(result, MeituanResult::class.java)
        } catch (e: BizException) {
            throw e
        } catch (e: Exception) {
            throw BizException("请求失败:$e")
        }
    }

    /**
     * 生成一个渠道邀请码
     *
     * @param relationApp 渠道推广的物料类型，示例值：common
     * @param codeType    邀请码类型，1 - 渠道邀请，2 - 渠道裂变，3 -会员邀请
     * @param relationId  渠道关系ID
     */
    @Cacheable(RedisKeys.ZHE_TAO_KE+"channelGeneration#1",key = "#relationApp+'_'+#codeType+'_'+#relationId")
    override fun channelGeneration(relationApp: String, codeType: String, relationId: String): String? {
        val paramsMap = mutableMapOf<String, Any?>()
        val s = zheConfigService.setting
        paramsMap["relation_app"] = relationApp
        paramsMap["code_type"] = codeType
        paramsMap["relation_id"] = relationId
        paramsMap["appKey"] = s.appKey
        paramsMap["sid"] = s.sid
        val result = HttpUtil.post(
            "https://api.zhetaoke.com:10001/api/open_sc_invitecode_get.ashx",
            paramsMap
        )
        log().info(result)
        if (result != null) {
            try {
                val jsonObject = JSONObject.parseObject(result)
                if (jsonObject.getJSONObject("tbk_sc_invitecode_get_response") != null) {
                    val r = jsonObject.getJSONObject("tbk_sc_invitecode_get_response")
                    if (r != null) {
                        val data = r.getJSONObject("data")
                        if (data != null) {
                            return data.getString("inviter_code")
                        }
                    }
                }
            } catch (e: Exception) {
                return null
            }
        }
        return null
    }

    /**
     * 获取渠道方列表
     */
    @Cacheable(RedisKeys.ZHE_TAO_KE+"openScPublisherGet#1",key = "#params")
    override fun openScPublisherGet(params: OpenScPublisherGetParam): ScResult {
        val toMutableMap = params.toMutableMap()
        toMutableMap.putAll(zheConfigService.getRequestParam())
        val url = "https://api.zhetaoke.com:10001/api/open_sc_publisher_get.ashx"
        try {
            val get = HttpUtil.get(url, toMutableMap)
            return Json.decodeFromString(ScResult.serializer(), get)
        } catch (e: Exception) {
            throw BizException("获取渠道会员失败")
        }
    }

    @Cacheable(RedisKeys.ZHE_TAO_KE+"findScItem#1",key = "#userId")
    override fun findScItem(userId: Long): TbkScPublisherInfoGetResponseMapDatum? {
        val openScPublisherGet = openScPublisherGet(OpenScPublisherGetParam(infoType = 1, relationApp = "common"))
        return openScPublisherGet.tbkScPublisherInfoGetResponse.data.inviterList.mapData.find { it.rtag != null && it.rtag.toLong() == userId }
    }

    /**
     * 获取唯品会精编商品文案
     *
     * @param param 参数
     * @return json
     */
    @Cacheable(RedisKeys.ZHE_TAO_KE+"getWPHXianbao#1",key = "#param")
    override fun getWPHXianbao(param: WeipinghuiXianbaoParam): String? {
        val map = mutableMapOf<String, Any?>()
        map["page"] = param.page
        map["page_size"] = param.pageSize
        map["sort"] = param.sort
        if (StringUtils.isNotBlank(param.totalCount)) {
            map["total_count"] = param.totalCount
        }
        map.putAll(paramsMap)
        return HttpUtil.get("https://api.zhetaoke.com:10001/api/open_vip_wenan_list.ashx", map)
    }

    /**
     * 解析唯品会商品
     *
     * @param idOrUrl id或者url
     * @return json
     */
    @Cacheable(RedisKeys.ZHE_TAO_KE+"wepProductJx#1",key = "#idOrUrl", unless = "idOrUrl!=null")
    override fun wepProductJx(idOrUrl: String?): String? {
        val url = "https://api.zhetaoke.com:10001/api/open_vip_getByGoodsIdsWithOauth.ashx"
        val paramsMap = paramsMapWph
        paramsMap["id"] = idOrUrl ?: ""
        return HttpUtil.get(url, paramsMap)
    }


    /**
     * 淘宝授权接口,生成一个登录url
     *
     * @param type 淘客授权页面类型，1：电脑版授权页面，0：手机版授权页面，默认值1
     * @return 登录 url 发给用户进行登录授权的url
     */
    @Cacheable(RedisKeys.ZHE_TAO_KE+"taokeShouquan#1",key = "#type")
    override fun taokeShouquan(type: String?): String? {
        val url = "https://api.zhetaoke.com:10001/api/open_taokeshouquan.ashx"
        val params = TreeMap<String, Any?>()
        val s = zheConfigService.setting
        params["appKey"] = s.appKey
        params["backurl"] = s.tbAuthBackUrl
        params["type"] = type
        return HttpUtil.get(url, params)
    }

    /**
     * 淘宝渠道备案接口
     *
     * @param inviterCode  必填 淘宝客邀请渠道的邀请码
     * @param inviterCodeS 淘宝客邀请会员的邀请码
     * @param backurl      必填 代理授权，并登记备案后，返回渠道信息至回调地址。
     * @param sNote        渠道备注，此内容建议填写用户唯一标识（比如用户编号，账号等唯一字段），跟返回的relation_id字段做关联。
     * @param type         淘客授权页面类型，1：电脑版授权页面，0：手机版授权页面，默认值1
     * @return 折淘客返回的数据
     */
    override fun beian(
        inviterCode: String,
        inviterCodeS: String,
        backurl: String,
        sNote: String,
        type: String
    ): String? {
        val url = "https://api.zhetaoke.com:10001/api/open_sc_publisher_save.ashx"
        val params = TreeMap<String, Any>()
        params["inviter_code"] = inviterCode
        params["inviter_code_s"] = inviterCodeS
        params["backurl"] = backurl
        params["s_note"] = sNote
        params["type"] = type
        return HttpUtil.get(url, params)
    }

    /**
     * 获取畅销榜单商品
     *
     * @return 产品列表
     */
    @get:Cacheable(RedisKeys.ZHE_TAO_KE + "bestSellerProducts#2")
    override val bestSellerProducts: String?
        get() {
            val url = "https://api.zhetaoke.com:10001/api/api_quantian.ashx"
            val params = TreeMap<String, Any>()
            val s = zheConfigService.setting
            params["appKey"] = s.appKey
            params["page"] = "1"
            params["page_size"] = "50"
            params["sort"] = "new"
            return HttpUtil.get(url, params)
        }

    @Cacheable(RedisKeys.ZHE_TAO_KE + "getCarouselList#8")
    override fun getCarouselList(): JSONObject {
        val url = "https://api.zhetaoke.com:10001/api/api_lunbo.ashx"
        val params = TreeMap<String, Any>()
        params["appkey"] = zheConfigService.setting.appKey
        params["page"] = 1
        params["page_size"] = 20
        val json = HttpUtil.get(url, params)
        val parseObj = JSON.parseObject(json)
        return parseObj
    }

    @get:Cacheable(RedisKeys.ZHE_TAO_KE + "getAppKey#8")
    override val getAppKey: String
        get() = zheConfigService.setting.appKey


    /**
     * sid 折淘客
     *
     * @return
     */
    private val paramsMap: MutableMap<String, Any>
        get() {
            val paramMap = mutableMapOf<String, Any>()
            val s = zheConfigService.setting
            paramMap["appkey"] = s.appKey
            paramMap["sid"] = s.sid
            return paramMap
        }

    /**
     * sid 唯品会
     *
     * @return
     */
    val paramsMapWph: HashMap<String, Any>
        get() {
            val paramMap = HashMap<String, Any>()
            val s = zheConfigService.setting
            paramMap["appkey"] = s.appKey
            paramMap["sid"] = s.wphSid
            return paramMap
        }

    /**
     * sid 唯品会
     *
     * @return
     */
    fun getParamsMapWph(map: MutableMap<String, Any?>): Map<String, Any?> {
        val s = zheConfigService.setting
        map["appkey"] = s.appKey
        map["sid"] = s.wphSid
        return map
    } //
}