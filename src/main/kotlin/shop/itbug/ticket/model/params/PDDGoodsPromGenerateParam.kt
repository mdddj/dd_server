package shop.itbug.ticket.model.params

import jakarta.validation.constraints.NotBlank

/**
 * 拼多多商品转链 吐槽建议
 * 应用场景：
 * 可用于导购平台的高佣转链工具
 * 接口说明：
 * 该接口针对拼多多商品进行转链，转链后即可进行推广并赚取佣金
 */
open class PDDGoodsPromGenerateParam {
    /**
     * 拼多多推广位id(联盟通知，在2021/4/30日之前，pid、custom_parameters必须进行备案)
     */
    /**
     * 商品goodsSign（详细说明可复制链接查看http://www.dataoke.com/kfpt/open-gz.html?id=100）
     */
    open var goodsSign: @NotBlank(message = "请输入商品goodsSign") String? = null

    /**
     * 自定义参数，为链接打上自定义标签； 自定义参数最长限制64个字节； 格式为： {"uid":"11111","sid":"22222"} ， 其中 uid 用户唯一标识，可自行加密后传入， 每个用户仅且对应一个标识，必填； sid 上下文信息标识，例如sessionId等， 非必填。该json字符串中也可以加入其他自定义的key In
     */
    open var customParameters: String? = null

    /**
     * 招商多多客ID
     */
    open var zsDuoId: String? = null
}