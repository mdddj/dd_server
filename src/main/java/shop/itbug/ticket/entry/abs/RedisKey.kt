package shop.itbug.ticket.entry.abs

abstract class RedisKey(private val key: String) {
    override fun toString(): String {
        return key
    }
}