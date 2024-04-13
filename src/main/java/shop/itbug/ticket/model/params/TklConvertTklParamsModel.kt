package shop.itbug.ticket.model.params


/**
 * 淘口令转淘口令
 */
open class TklConvertTklParamsModel {
    /**
     * 支持包含文本的淘口令，但最好是一个单独淘口令
     */
    open var content: String? = null

    /**
     * 推广位ID，用户可自由填写当前大淘客账号下已授权淘宝账号的任一pid，若未填写，则默认使用创建应用时绑定的pid
     */
    open var pid: String? = null

    /**
     * 渠道id将会和传入的pid进行验证，验证通过将正常转链，请确认填入的渠道id是正确的
     */
    open var channelId: String? = null

    /**
     * 会员运营ID
     */
    open var special_id: String? = null

    /**
     * 淘宝客外部用户标记，如自身系统账户ID；微信ID等
     */
    open var external_id: String? = null
}