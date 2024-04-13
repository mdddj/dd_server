package shop.itbug.ticket.entry.order

import com.alibaba.fastjson2.annotation.JSONField
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.persistence.*
import shop.itbug.ticket.entry.User

/**
 * 订单的四种状态
 */
@Schema(name = "订单状态")
enum class TkStatus {
    @Schema(description = "已付款,对应的是12") Payment,
    @Schema(description = "已结算,对应的是3") Settlement,
    @Schema(description = "已失效,对应的是13") Expiration,
    @Schema(description = "已成功,对应的是14") Success
}

fun TkStatus.getIntValue() : Int{
    return when(this) {
        TkStatus.Payment -> 12
        TkStatus.Settlement -> 3
        TkStatus.Expiration -> 13
        TkStatus.Success -> 14
    }
}
/**
 * 订单有四种状态
 * export enum TkStatus {
 *   '订单付款' = 12,
 *   '订单结算' = 3,
 *   '订单失效' = 13,
 *   '订单成功' = 14,
 * }
 */
@Entity
@Schema(name = "用户订单")
class UserOrder {

    @Id
    @GeneratedValue
    @Schema(description = "订单ID")
    var id: Long? = null


    @ManyToOne(fetch = FetchType.LAZY)
    @JSONField(serialize = false)
    var user: User? = null

    @Schema(description = "折淘客自动增长列")

    var zheCode: String? = null

    @Schema(description = "折淘客账号")
    var zheAccount: String? = null

    @SchemaProperty(name = "折淘客appkey")
    var zheAppKey: String? = null


    @Schema(description = "原始推广位sid")
    var zheSid: String? = null


    @Schema(description = "折淘客是否结算，0未结算，1已结算")
    var zheIsJieSuan: String? = null


    @Schema(description = "折淘客结算时间")
    var zheJieSuanTime: String? = null


    @Schema(description = "折淘客结算金额")
    var zheJieSuanProfit: String? = null


    @Schema(description = "订单编号")
    var zheOrderId: String? = null


    @Schema(description = "订单支付时间")
    var zhePayTime: String? = null


    @Schema(description = "订单实际支付金额")
    var zhePayPrice: String? = null


    @Schema(description = "预估佣金")
    var zheProfit: String? = null

    @Schema(description = "订单标题")
    var zheSmsTitle: String? = null


    @Schema(description = "退款金额")
    var zheRefundPrice: String? = null


    @Schema(description = "退款时间")
    var zheRefundTime: String? = null


    @Schema(description = "订单状态，1已付款，8已完成，9已退款或风控")
    var zheStatus: String? = null


    @Schema(description = "数据最后更新时间")
    var zheUpdateTime: String? = null


    @Schema(description = "订单类型")
    var zheType: String? = null


    @Schema(description = "折淘客授权sid")
    var zheSidZtk: String? = null


    @Schema(description = "推广者自定义编号")
    var zheCustomerIdZtk: String? = null


    @Schema(description = "推广者平台类型，默认zhetaoke")
    var zhePlatformZtk: String? = null


    @Schema(description = "活动id")
    var zheActId: String? = null

    @Schema(description = "订单所属平台,考拉、美团、苏宁、淘宝、京东、拼多多、唯品会、饿了么等")
    var zheSanPingTai: String? = null

    @Schema(description = "订单所属平台id，1美团、2考拉、3苏宁、4淘宝、5京东、6拼多多、7唯品会、8饿了么")
    var zheSanPingTaiId: String? = null


    /**
     * 下面是大淘客的订单字段
     */

    var terminalType: String? = null

    var itemCategoryName: String? = null

    var tkPaidTime: String? = null

    var adzoneid: Long? = null

    var alipayTotalPrice: String? = null

    var sellerShopTitle: String? = null

    var alimamaShareFee: String? = null

    var tradeid: String? = null

    var clickTime: String? = null

    var subsidyRate: String? = null

    var refundTag: Long? = null

    var itemTitle: String? = null

    var orderType: String? = null

    var tbPaidTime: String? = null

    var tkOrderRole: Long? = null

    var totalCommissionFee: String? = null

    var pubid: Long? = null

    var itemImg: String? = null

    var alimamaRate: String? = null

    var itemid: String? = null

    var itemPrice: String? = null

    var tbDepositTime: String? = null

    var tkStatus: Long? = null

    var totalCommissionRate: String? = null

    var tradeParentid: String? = null

    var subsidyType: String? = null

    var tkCreateTime: String? = null

    var pubShareFee: String? = null

    var itemNum: Long? = null

    var tkCommissionFeeForMediaPlatform: String? = null

    var incomeRate: String? = null

    var siteName: String? = null

    var pubSharePreFee: String? = null

    var tkCommissionRateForMediaPlatform: String? = null

    var tkDepositTime: String? = null

    var tkTotalRate: String? = null

    var pubShareRate: String? = null

    var adzoneName: String? = null

    var siteid: Long? = null

    var itemLink: String? = null

    var depositPrice: String? = null

    var sellerNick: String? = null

    var subsidyFee: String? = null

    var flowSource: String? = null

    var tkCommissionPreFeeForMediaPlatform: String? = null


    var relationId: Long? = null

}

