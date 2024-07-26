package shop.itbug.ticket.model.api

import shop.itbug.ticket.entry.User
import java.io.Serializable

/**
 * @author eee
 */
class RoomModel : Serializable {
    /**
     * 房间名字
     */
    var roomName: String? = null

    /**
     * 谁创建的这个房间
     */
    var roomCreateUser: User? = null

    /**
     * 对战的这个用户是谁
     */
    var pkUser: User? = null
}