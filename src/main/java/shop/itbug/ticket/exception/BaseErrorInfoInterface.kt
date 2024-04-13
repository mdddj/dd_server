package shop.itbug.ticket.exception

interface BaseErrorInfoInterface {
    /**
     * 错误码
     *
     * @return code
     */
    fun errCode(): Int

    /**
     * 错误信息
     * @return message
     */
    fun errMessage(): String
}