package shop.itbug.ticket.model.keys

import shop.itbug.ticket.entry.abs.RedisKey

/**
 * 设备唯一id
 */
class DeviceKey(key: String) : RedisKey(key) {
    companion object {
        const val KEY = "device_id_"
    }
}