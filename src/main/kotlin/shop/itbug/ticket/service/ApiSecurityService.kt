package shop.itbug.ticket.service

import org.springframework.stereotype.Service

@Service
interface ApiSecurityService {
    /**
     * 根据设备id生成一个接口请求token
     *
     * @param deviceId 设备唯一id
     * @return 生成的token
     */
    fun generateDeviceToken(deviceId: String): String

    /**
     * 删除token
     *
     * @param deviceId 唯一设备id
     * @return 操作是否成功
     */
    fun removeDeviceToken(deviceId: String): Boolean

    /**
     * 判断token是否有效
     *
     * @param deviceId 设备id
     * @return true表示存在且有效
     */
    fun existsDeviceToken(deviceId: String): Boolean

    /**
     * 根据device获取token
     * 如果缓存服务器不存在
     * 将为它重新生成一个
     * @param deviceId  设备id
     * @return  token
     */
    fun getTokenByDeviceId(deviceId: String): String?
}