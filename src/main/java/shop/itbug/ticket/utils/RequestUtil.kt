package shop.itbug.ticket.utils

import jakarta.servlet.http.HttpServletRequest
import org.apache.commons.lang3.StringUtils
import shop.itbug.ticket.constant.Constants

object RequestUtil {
    fun formatGetUrl(request: HttpServletRequest): String {
        val path = request.requestURI
        val parameterMap = request.parameterMap
        val keySet: Set<String> = parameterMap.keys
        val paramsStr = StringBuilder()
        var i = 0
        for (key in keySet) {
            i = i + 1
            if (StringUtils.equals(key, "data") || StringUtils.equals(key, Constants.DECRYPT_FLAG)) continue
            val values = parameterMap[key]!![0]
            val hz = if (i == parameterMap.size) "" else "&"
            paramsStr.append(key).append("=").append(values).append(hz)
        }
        return "$path?$paramsStr"
    }
}