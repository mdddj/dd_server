package shop.itbug.ticket.model.keys

import shop.itbug.ticket.entry.abs.RedisKey

/// 专辑列表
class TopicListKey(key: String) : RedisKey(key) {
    companion object {
        const val KEY = "topic_list"
    }
}