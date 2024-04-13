package shop.itbug.ticket.service

import com.alibaba.fastjson2.JSONObject
import org.springframework.stereotype.Service
import shop.itbug.ticket.model.params.OpenScPublisherGetParam
import shop.itbug.ticket.model.params.ScResult
import shop.itbug.ticket.model.params.TbkScPublisherInfoGetResponseMapDatum
import shop.itbug.ticket.model.zhe.MeituanResult
import shop.itbug.ticket.model.zhe.MeitunTuiGuangParam
import shop.itbug.ticket.model.zhe.WeipinghuiXianbaoParam

@Service
interface ZheTkService {
    /**
     * 获取美团推广数据
     *
     * @param param 参数
     * @return json
     */
    fun getMeiTuan(param: MeitunTuiGuangParam): MeituanResult?

    /**
     * 生成一个渠道邀请码
     *
     * @param codeType    邀请码类型，1 - 渠道邀请，2 - 渠道裂变，3 -会员邀请
     * @param relationApp 渠道推广的物料类型，示例值：common
     * @param relationId  渠道关系ID
     */
    fun channelGeneration(relationApp: String, codeType: String, relationId: String): String?


    //https://api.zhetaoke.com:10001/api/open_sc_publisher_get.ashx
    fun openScPublisherGet(params: OpenScPublisherGetParam) : ScResult

    /**
     * 查找渠道ID
     */
    fun findScItem(userId: Long) : TbkScPublisherInfoGetResponseMapDatum?

    /**
     * 获取唯品会精编商品文案
     *
     * @param param 参数
     * @return json
     */
    fun getWPHXianbao(param: WeipinghuiXianbaoParam): String?

    /**
     * 解析唯品会商品
     *
     * @param idOrUrl id或者url
     * @return json
     */
    fun wepProductJx(idOrUrl: String?): String?


    /**
     * 淘宝授权接口,生成一个登录url
     *
     * @param type 淘客授权页面类型，1：电脑版授权页面，0：手机版授权页面，默认值1
     * @return 登录 url 发给用户进行登录授权的url
     */
    fun taokeShouquan(type: String?): String?

    /**
     * 淘宝渠道备案接口
     *
     * @return
     */
    fun beian(inviterCode: String, inviterCodeS: String, backurl: String, sNote: String, type: String): String?

    /**
     * 获取畅销榜单商品: 50条数据,综合排行
     * @return 产品列表
     */
    val bestSellerProducts: String?

    /**
     * 获取轮播图列表
     */
    fun getCarouselList(): JSONObject

    /**
     * 获取折淘客的APP Key
     */
    val getAppKey: String
}