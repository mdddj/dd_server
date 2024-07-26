package shop.itbug.ticket.model.params

import jakarta.validation.constraints.NotNull

open class ActivityParam {
    /**
     * 联盟官方活动ID，从联盟官方活动页获取或从大淘客官方活动推广接口获取（饿了么微信推广活动ID：20150318020002192，饿了么外卖活动ID：20150318019998877，饿了么商超活动ID：1585018034441）
     */
    open var promotionSceneId: @NotNull(message = "请输入活动id") String? = null

    /**
     * 推广pid，默认为在”我的应用“添加的pid
     */
    open var pid: String? = null

    /**
     * 渠道id将会和传入的pid进行验证，验证通过将正常转链，请确认填入的渠道id是正确的
     */
    open var relationId: String? = null

    /**
     * 自定义输入串，英文和数字组成，长度不能大于12个字符，区分不同的推广渠道
     */
    open var unionId: String? = null
}