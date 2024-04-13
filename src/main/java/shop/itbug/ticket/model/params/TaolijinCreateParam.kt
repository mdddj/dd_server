package shop.itbug.ticket.model.params

import jakarta.validation.constraints.NotBlank

/**
 * 淘礼金创建入参
 */

class TaolijinCreateParam {
     val alimamaAppKey: @NotBlank(message = "请填写您申请的具有淘礼金应用权限的Appkey 申请地址https://survey.taobao.com/apps/zhiliao/BQ2RPRlpU") String? =
        null
     val alimamaAppSecret: @NotBlank(message = "请填写您申请的具有淘礼金应用权限的Appsecret") String? = null
     val name: @NotBlank(message = "淘礼金名称，最大10个字符") String? = null
     val itemId: @NotBlank(message = "宝贝商品id") String? = null
     val pid: String? = null
     val campaignType: String? = null
     val perFace: @NotBlank(message = "单个淘礼金面额，支持两位小数，单位元") String? = null
     val totalNum: @NotBlank(message = "淘礼金总个数") String? = null
     val winNumLimit: @NotBlank(message = "单用户累计中奖次数上限，最小值为1") String? = null
     val sendStartTime: @NotBlank(message = "发放开始时间，格式为yyyy-MM-dd HH:mm:ss示例：发放开始时间 2018-09-01 00:00:00") String? =
        null
     val sendEndTime: @NotBlank(message = "发放截止时间，格式为yyyy-MM-dd HH:mm:ss发放截止时间，示例： 2018-09-01 00:00:00") String? =
        null
     val useEndTimeMode: String? = null
     val useStartTime: String? = null
     val userEndTime: String? = null
}