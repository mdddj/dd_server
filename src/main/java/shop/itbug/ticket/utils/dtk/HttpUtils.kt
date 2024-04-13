package shop.itbug.ticket.utils.dtk

import cn.hutool.http.HttpUtil
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.*
import java.util.function.Consumer

object HttpUtils {
    @Throws(RuntimeException::class)
    fun sendGet(getUrl: String, paraMap: MutableMap<String, Any>): String {
        val sb = StringBuilder()
        paraMap.entries.forEach(Consumer { (key, value): Map.Entry<String, Any> ->
            sb.append(key)
            sb.append("=")
            try {
                sb.append(URLEncoder.encode(value.toString(), "utf-8"))
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
            sb.append("&")
        })
        val finalUrl: String = if (getUrl.contains("?")) getUrl else "$getUrl?"
        return HttpUtil.get(finalUrl + sb.toString())
    }
}