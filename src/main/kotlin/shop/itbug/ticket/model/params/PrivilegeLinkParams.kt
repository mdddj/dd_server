package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema

/**
 * 文档地址 : http://www.dataoke.com/kfpt/api-d.html?id=7
 */
@Schema(name = "高效转链参数模型")
data class PrivilegeLinkParams(
    /**
     * 淘宝商品id
     */
    @Schema(description = "淘宝商品id") var goodsId: String,
    /**
     * 商品的优惠券ID，一个商品在联盟可能有多个优惠券，可通过填写该参数的方式选择使用的优惠券，请确认优惠券ID正确，否则无法正常跳转
     */
    @Schema(description = "商品的优惠券ID，一个商品在联盟可能有多个优惠券，可通过填写该参数的方式选择使用的优惠券，请确认优惠券ID正确，否则无法正常跳转")
    var couponId: String? = null,
    /**
     * 推广位ID，用户可自由填写当前大淘客账号下已授权淘宝账号的任一pid，若未填写，则默认使用创建应用时绑定的pid
     */
    @Schema(description = "推广位ID，用户可自由填写当前大淘客账号下已授权淘宝账号的任一pid，若未填写，则默认使用创建应用时绑定的pid")
    var pid: String? = null,
    /**
     * 渠道id将会和传入的pid进行验证，验证通过将正常转链，请确认填入的渠道id是正确的 channelId对应联盟的relationId
     */
    @Schema(description = "渠道id将会和传入的pid进行验证，验证通过将正常转链，请确认填入的渠道id是正确的 channelId对应联盟的relationId")
    var channelId: String? = null,
    /**
     * 付定返红包，0.不使用付定返红包，1.参与付定返红包
     */
    @Schema(description = "付定返红包，0.不使用付定返红包，1.参与付定返红包")
    var rebateType: String? = null,


    /**
     * 会员运营id
     */
    @Schema(description = "会员运营id")
    var specialId: String? = null,

    /**
     * 淘宝客外部用户标记，如自身系统账户ID；微信ID等
     */
    @Schema(description = "淘宝客外部用户标记，如自身系统账户ID；微信ID等")
    var externalId: String? = null,

    /**
     * 团长与下游渠道合作的特殊标识，用于统计渠道推广效果 （新增入参）
     */
    @Schema(description = "团长与下游渠道合作的特殊标识，用于统计渠道推广效果 （新增入参）")
    var xid: String? = null

)