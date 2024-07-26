package shop.itbug.ticket.model.params

import jakarta.validation.constraints.NotBlank

/**
 * 京东商品批量转链 吐槽建议
 * 应用场景：
 * 在其他渠道如果获取到包含多链接+多文本的情况下，可以直接将整段文件传入进行批量转链。输出转链后的原始文本（链接顺序不变）
 * 接口说明：
 * 该接口针对京东商品进行批量转链，转链后即可进行推广并赚取佣金(优惠券无法进行转链，无法转链的地址会按照原数据返回)
 *
 * 官方文档地址[https://www.dataoke.com/kfpt/api-d.html?id=78]
 */
open class JDPromotionUnionConvert {
    /**
     * 目标推客的联盟ID
     */
    open var unionId: @NotBlank(message = "请输入目标推客的联盟ID") String? = null

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
     * 待转链京东商品物料地址(需要urlencode，优惠券无法进行转链，无法转链的地址会按照原数据返回)
     */
    open var content: @NotBlank(message = "请输入待转链京东商品物料地址") String? = null
}