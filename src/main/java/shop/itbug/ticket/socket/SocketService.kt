//package shop.itbug.ticket.socket
//
//import com.alibaba.fastjson2.JSONObject
//import io.netty.handler.timeout.IdleState
//import io.netty.handler.timeout.IdleStateEvent
//import lombok.extern.log4j.Log4j2
//import org.apache.commons.lang3.StringUtils
//import org.springframework.stereotype.Component
//import shop.itbug.ticket.model.api.SocketResultModel
//import java.io.IOException
//import java.util.concurrent.ConcurrentHashMap
//import java.util.concurrent.atomic.AtomicReference
//
///**
// * 创建游戏房间的入口
// *
// * @author eee
// */
//@ServerEndpoint(path = "/game/room", port = "12350")
//@Component
//@Log4j2
//class SocketService {
//    /**
//     * 给所有在线用户发送一条消息
//     *
//     * @param type 消息类型
//     * @param json 主体内容
//     */
//    fun sendToAll(type: String?, json: String?) {
//        USERS.forEach { (k: String, v: Session?) -> sendMessageToUser(k.toLong(), type, json) }
//    }
//
//    /**
//     * 给某个人发送一条socket消息
//     *
//     * @param userId 接收用户id
//     * @param type   消息类型
//     * @param json   主体内容
//     */
//    fun sendMessageToUser(userId: Long, type: String?, json: String?) {
//        val model = SocketResultModel()
//        model.json = json
//        model.type = type
//        if (USERS.containsKey(userId.toString())) {
//            // 如果存在该用户,则发送消息
//            val session = USERS[userId.toString()]
//            if (session!!.isActive && session.isOpen) {
//                session.sendText(JSONObject.toJSONString(model))
//            }
//        }
//    }
//
//    /**
//     * 获取在线总人数
//     *
//     * @return 总在线人数
//     */
//    fun count(): Int {
//        return USERS.size
//    }
//
//    @BeforeHandshake
//    fun handshake(session: Session) {
//        session.setSubprotocols("stomp")
//    }
//
//    @OnOpen
//    fun onOpen(session: Session, headers: HttpHeaders?, @RequestParam(name = "userId") userId: String) {
//        if (StringUtils.isNotBlank(userId)) {
//            session.setAttribute("userIs", userId)
//            USERS[userId] = session
//            sendToAll(SocketUtil.REFRESH_INLINE_COUNT_KEY, SocketUtil.getRefreshMessageJson(USERS.size))
//        }
//    }
//
//    @OnClose
//    @Throws(IOException::class)
//    fun onClose(session: Session) {
//        val sessonId = session.id().asShortText()
//        val userId = AtomicReference("")
//        USERS.forEach { (k: String, v: Session) ->
//            if (v.id().asShortText() == sessonId) {
//                userId.set(k)
//            }
//        }
//        USERS.remove(userId.get())
//        // 有人退出游戏通知所有用户刷新人数
//        sendToAll(SocketUtil.REFRESH_INLINE_COUNT_KEY, SocketUtil.getRefreshMessageJson(USERS.size))
//    }
//
//    @OnError
//    fun onError(session: Session?, throwable: Throwable) {
//        throwable.printStackTrace()
//    }
//
//    @OnMessage
//    fun onMessage(session: Session?, message: String?) {
//        println(message)
//        try {
//            val jsonObject = JSONObject.parseObject(message)
//            val type = jsonObject.getString("type")
//            val userId = jsonObject.getString("user")
//            val data = jsonObject.getString("data")
//            if ("brush" == type) {
//                // 用户在画画了
//                sendMessageToUser(userId.toLong(), "brush", data)
//            }
//        } catch (e: Exception) {
//        }
//    }
//
//    @OnBinary
//    fun onBinary(session: Session, bytes: ByteArray) {
//        for (b in bytes) {
//            println(b)
//        }
//        session.sendBinary(bytes)
//    }
//
//    @OnEvent
//    fun onEvent(session: Session?, evt: Any?) {
//        if (evt is IdleStateEvent) {
//            when (evt.state()) {
//                IdleState.READER_IDLE -> println("read idle")
//                IdleState.WRITER_IDLE -> println("write idle")
//                IdleState.ALL_IDLE -> println("all idle")
//                else -> {}
//            }
//        }
//    }
//
//    companion object {
//        /**
//         * 管理所有已经连接的用户
//         */
//        private val USERS = ConcurrentHashMap<String, Session>()
//    }
//}