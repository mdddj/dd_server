package shop.itbug.ticket.model.zhe

import jakarta.validation.constraints.NotBlank

open class MeitunTuiGuangParam {
    /**
     * 活动ID：2，美团外卖CPS+CPA推广活动，美团外卖重磅推出最高68元券包，佣金比例3%。
     * 活动ID：4，商超生鲜红包天天领，最高34元红包，佣金比例2%。
     * 活动ID：22，美团优选CPS+CPA，美团优选,便宜有好货,佣金比例3%。
     * 活动ID：23，优选1.99促销页，美团优选,便宜有好货,佣金比例3%。
     * 活动ID：24，优选秒杀栏目活动，美团优选,便宜有好货,佣金比例3%。
     */
    /**
     * 链接类型，枚举值：1：h5链接 2：deeplink(唤起)链接 3：中间页唤起链接 4：微信小程序唤起路径。
     * 注意：活动id=22或者23或者24，不支持链接类型1，其它都支持。
     */
    open var actId: @NotBlank(message = "请输入美团活动id") String? = null
    open var linkType: @NotBlank(message = "请输入链接类型") String? = null
    open var miniCode: String? = null
    open var miniCode2: String? = null
    open var miniCode3: String? = null
    open var platform: String? = null
    open var customerId: String? = null
}