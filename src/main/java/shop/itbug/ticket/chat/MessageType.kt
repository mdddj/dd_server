package shop.itbug.ticket.chat

///处理socket消息
interface IdeaPluginMessageHandle {
    val messageType get() = String
    fun handle(jsonString: String)
}