package shop.itbug.ticket.model.params

import jakarta.validation.constraints.NotBlank

/**
 * 京东商品转链 吐槽建议
 * 应用场景：
 * 可用于导购平台的高佣转链工具
 * 接口说明：
 * 该接口针对京东商品进行转链，转链后即可进行推广并赚取佣金
 * 文档地址:{https://www.dataoke.com/kfpt/api-d.html?id=52}
 */
open class JDPromotionUnionConvertWithOneParams {
    /**
     * 推客的联盟ID
     */
    open var unionId: @NotBlank(message = "请输入推客的联盟ID") String? = null

    /**
     * 推广物料url，例如活动链接、商品链接等；不支持仅传入skuid
     */
    open var materialId: @NotBlank(message = "推广物料url") String? = null

    /**
     * 新增推广位id （若无subUnionId权限，可入参该参数用来确定不同用户下单情况）
     */
    open var positionId: String? = null

    /**
     * 联盟子推客身份标识（不能传入接口调用者自己的pid）
     */
    open var childPid: String? = null

    /**
     * 子渠道标识，您可自定义传入字母、数字或下划线，最多支持80个字符，该参数会在订单行查询接口中展示，需要有权限才可使用
     */
    open var subUnionId: String? = null

    /**
     * 优惠券领取链接，在使用优惠券、商品二合一功能时入参，且materialId须为商品详情页链接（5.27更新：若不填则会自动匹配上全额最大的优惠券进行转链）
     */
    open var couponUrl: String? = null

    /**
     * 转链类型，默认短链，短链有效期60天1：长链2：短链 3：长链+短链，
     */
    open var chainType: String? = null

    /**
     * 礼金批次号
     */
    open var giftCouponKey: String? = null
}