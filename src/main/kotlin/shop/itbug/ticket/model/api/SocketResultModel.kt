package shop.itbug.ticket.model.api


/**
 * socket 消息返回模型
 * @author eee
 */
open class SocketResultModel {
    /**
     * 消息类型
     */
    open var type: String? = null

    /**
     * 要解析的数据格式 (json类型的)
     */
    open var json: String? = null
}