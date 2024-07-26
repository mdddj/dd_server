package shop.itbug.ticket.model.params

import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 * 淘口令生成 参数
 * 文档地址: [https://www.dataoke.com/kfpt/api-d.html?id=28]
 */
@Schema(name = "淘客口令生成参数")
open class CreateTklParams {
    @Schema(description = "口令弹框内容，长度大于5个字符")
    open var text: String? = ""

    @Schema(description = "口令跳转目标页，如：https://uland.taobao.com/，必须以https开头，可以是二合一链接、长链接、短链接等各种淘宝高佣链接；支持渠道备案链接。* 该参数需要进行Urlencode编码后传入*")
    open var url: String? = ""

    @Schema(description = "口令弹框logoURL,非必填")
    open var logo: String? = null

    @Schema(description = "生成口令的淘宝用户ID，非必传参数")
    open var userId: String? = null
}