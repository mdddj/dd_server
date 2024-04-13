package shop.itbug.ticket.socket

import com.alibaba.fastjson2.JSONObject
import shop.itbug.ticket.model.api.RoomModel

/**
 * socket 帮助工具类
 *
 * @author eee
 */
object SocketUtil {
    /**
     * 人数消息
     */
    const val REFRESH_INLINE_COUNT_KEY = "inline-count-message-notify"

    /**
     * 新房间被创建的消息
     */
    const val NEW_ROOM_BY_CREATE = "new-room-by-created"

    /**
     * 房间被关闭的消息
     */
    const val ROOM_BY_REMOVE = "room-by-remove"

    /**
     * 房间被修改
     */
    const val ROOM_BY_UPDATE = "room-by-update"

    /**
     * 有人进来了事件
     */
    const val ROOM_USER_IN = "room-user-in"

    /**
     * 将一个房间的模型转换为json
     * @param roomModel 房间模型
     * @return  json
     */
    fun getJsonByRoomModel(roomModel: RoomModel?): String {
        return JSONObject.toJSONString(roomModel)
    }

    /**
     * 像所有人推送一条刷新总人数的消息
     *
     * @param size 当前总在线人数
     * @return json 字符串
     */
    fun getRefreshMessageJson(size: Int): String {
        val jsonObject = JSONObject()
        jsonObject["count"] = size
        return JSONObject.toJSONString(jsonObject)
    }
}