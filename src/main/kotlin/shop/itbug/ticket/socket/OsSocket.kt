package shop.itbug.ticket.socket

import com.alibaba.fastjson2.toJSONString
import jakarta.websocket.OnMessage
import jakarta.websocket.Session
import jakarta.websocket.server.ServerEndpoint
import org.springframework.stereotype.Component
import shop.itbug.ticket.ex.log
import shop.itbug.ticket.model.MemoryInfo
import shop.itbug.ticket.model.SystemInfo

@ServerEndpoint("/os")
@Component
class OsSocket {


    @OnMessage
    fun onMessage(session: Session, message: String) {
        try {
            when (message) {
                "Info" -> session.asyncRemote.sendText(SystemInfo.getSystemInfo().toJSONString())
                "Memory" -> session.asyncRemote.sendText(MemoryInfo.getMemoryInfo().toJSONString())
            }
        } catch (e: Exception) {
            log().info(e.localizedMessage)
        }
    }

}