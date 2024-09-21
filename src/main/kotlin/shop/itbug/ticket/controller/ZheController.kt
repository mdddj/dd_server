package shop.itbug.ticket.controller

import cn.hutool.http.HttpUtil
import com.alibaba.fastjson2.JSONObject
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import kotlinx.serialization.json.Json
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.ex.log
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.model.params.OpenScPublisherGetParam
import shop.itbug.ticket.model.params.ScResult
import shop.itbug.ticket.model.zhe.ElmResultModel
import shop.itbug.ticket.model.zhe.MeituanResult
import shop.itbug.ticket.model.zhe.MeitunTuiGuangParam
import shop.itbug.ticket.model.zhe.WeipinghuiXianbaoParam
import shop.itbug.ticket.service.DTKDetailService
import shop.itbug.ticket.service.ZheTkService
import shop.itbug.ticket.service.config.ZheConfigService
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.successResult


@RestController
@RequestMapping("/api/zhe")
@Tag(name = "折淘客接口")
class ZheController {
    @Resource
    private lateinit var zheTkService: ZheTkService

    @Resource
    private lateinit var dtkDetailService: DTKDetailService

    @Resource
    private lateinit var zheConfigService: ZheConfigService

    @GetMapping("/mt/tg")
    @Operation(summary = "美团领券")
    fun meituan(param: MeitunTuiGuangParam): Result<MeituanResult> {
        val meiTuan = zheTkService.getMeiTuan(param)
        return Result.ok(meiTuan)
    }


    @GetMapping("/sc")
    @Operation(summary = "获取会员关系列表")
    fun openScPublisherGet(params: OpenScPublisherGetParam) : R<ScResult> {
        val openScPublisherGet = zheTkService.openScPublisherGet(params)
        return R.ok(openScPublisherGet)
    }

    data class ChannelGenerationParam(val relationApp: String, val codeType: String)
    /**
     * 渠道生成的接口
     *
     * @return 数据
     */
    @PostMapping("/channel-generation")
    @Operation(summary = "渠道邀请码生成")
    fun channelGeneration(@RequestBody param: ChannelGenerationParam): Result<String> {
        val detail = dtkDetailService.findDefault() ?: throw BizException("请先绑定大淘客账号")
        if(detail.relationId == null || detail.relationId!!.isEmpty()) {
            throw BizException("请绑定渠道ID")
        }
        val s = zheTkService.channelGeneration(param.relationApp, param.codeType, detail.relationId!!) ?: throw BizException("邀请码生成失败")
        return Result.ok(s)
    }

    /**
     * 唯品会精编文案商品
     *
     * @param param 入参
     * @return json
     */
    @GetMapping("/jb")
    fun getWphJbGoods(@Validated param: WeipinghuiXianbaoParam): Result<*> {
        return Result.ok(zheTkService.getWPHXianbao(param))
    }

    /**
     * 唯品会商品详情
     *
     * @param id 商品id
     * @return json
     */
    @GetMapping("/info")
    fun getWphDetailInfo(id: String?): Result<*> {
        return Result.ok(zheTkService.wepProductJx(id))
    }





    /// 获取淘宝授权的url
    @GetMapping("/auth-url")
    fun getAuthUrl(type: String?): Result<*> {
        val s = zheTkService.taokeShouquan(type)
        return Result.ok(s)
    }

    /**
     * 进行备案
     *
     * @param jsonObject 请求参数
     * @return 数据
     */
    @PostMapping("/beian")
    fun beian(@RequestBody jsonObject: JSONObject): Result<*> {
        val inviterCode = jsonObject.getString("inviterCode")
        val inviterCodeS = jsonObject.getString("inviterCodeS")
        val backurl = jsonObject.getString("backurl")
        val sNote = jsonObject.getString("sNote")
        val type = jsonObject.getString("type")
        val beian = zheTkService.beian(inviterCode, inviterCodeS, backurl, sNote, type)
        return Result.ok(beian)
    }

    /**
     * 获取畅销榜单商品
     * @return json 数据
     */
    @GetMapping("/bast_seller_products")
    fun getBestSellerProducts(): Result<*> = Result.ok(zheTkService.bestSellerProducts)


    /**
     * 获取轮播图接口
     */
    @GetMapping("/carousel-list")
    fun getCarousels(): Result<*> {
        return Result.ok(zheTkService.getCarouselList())
    }

    /**
     * 获取折淘客的APP key
     */
    @GetMapping("/app-key")
    fun getAppKey(): Result<*>{
        return  Result.ok(zheTkService.getAppKey)
    }



    data class ElmDataParam( val activityId:String,
                          val customerId: String)

    @GetMapping("/elm")
    @Operation(summary = "饿了么转链")
    fun getElmData(params: ElmDataParam) : R<ElmResultModel> {
        val url = "https://api.zhetaoke.com:10001/api/open_eleme_generateLink.ashx"
        try {
            val s = zheConfigService.setting
            val result = HttpUtil.get(url, mapOf(
                "appkey" to s.appKey,
                "sid" to s.sid,
                "activity_id" to params.activityId,
                "customer_id" to params.customerId
            ))
            log().info(result)
            val decodeFromString = Json.decodeFromString(ElmResultModel.serializer(), result)

            return decodeFromString.successResult("获取成功")
        }catch (e:Exception){
            throw BizException("出现异常:${e.localizedMessage}")
        }
    }

}