package shop.itbug.ticket.model.params


/**
 * 淘宝官方活动
 * 接口文档: [http://www.dataoke.com/kfpt/api-d.html?id=24]
 * 应用场景：
 * 可用于导购平台的活动推广专区
 * 接口说明：
 * 接口返回联盟最新官方活动推广精品内容，成品页面适配，快速接入，不错过每一次精彩活动
 */
class TbTopicParamsModel {
    private val pageId: String? = null
    private val pageSize: String? = null

    /**
     * 输出的端口类型：0.全部（默认），1.PC，2.无线
     */
    private val type: String? = null

    /**
     * 阿里妈妈上申请的渠道id
     */
    private val channelID: String? = null
}