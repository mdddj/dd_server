package shop.itbug.ticket.constant

object RedisExpireTime {
    /**
     * 一天
     */
    const val ONE_DAY = 1000L * 60 * 60 * 24

    /**
     * 20分钟有效期
     */
    const val TWENTY_MINUTES = 1000L * 60 * 20
}