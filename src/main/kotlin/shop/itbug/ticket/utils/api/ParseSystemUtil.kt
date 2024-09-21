package shop.itbug.ticket.utils.api

import java.util.*

object ParseSystemUtil {
    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return String
     */
    fun parseByte2HexStr(buf: ByteArray): String {
        val sb = StringBuffer()
        for (i in buf.indices) {
            var hex = Integer.toHexString(buf[i].toInt() and 0xFF)
            if (hex.length == 1) {
                hex = "0$hex"
            }
            sb.append(hex.uppercase(Locale.getDefault()))
        }
        return sb.toString()
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return byte[]
     */
    fun parseHexStr2Byte(hexStr: String): ByteArray? {
        if (hexStr.length < 1) return null
        val result = ByteArray(hexStr.length / 2)
        for (i in 0 until hexStr.length / 2) {
            val high = hexStr.substring(i * 2, i * 2 + 1).toInt(16)
            val low = hexStr.substring(i * 2 + 1, i * 2 + 2).toInt(16)
            result[i] = (high * 16 + low).toByte()
        }
        return result
    }
}