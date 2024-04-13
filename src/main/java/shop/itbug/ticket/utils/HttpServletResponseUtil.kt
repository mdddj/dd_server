package shop.itbug.ticket.utils

import com.alibaba.fastjson2.JSONObject
import jakarta.servlet.http.HttpServletResponse
import shop.itbug.ticket.config.WebConfig

fun HttpServletResponse.result(result: Result<*>) {
    characterEncoding = "utf-8"
    contentType = "application/json; charset=utf-8"
    writer.write(JSONObject.toJSONString(result, *WebConfig.getWriterConfig().writerFeatures))
    writer.flush()
}