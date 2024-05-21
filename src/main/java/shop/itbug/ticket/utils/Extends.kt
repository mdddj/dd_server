package shop.itbug.ticket.utils

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONWriter
import org.slf4j.LoggerFactory
import shop.itbug.ticket.ex.log

fun <T : Any> T.printToConsole() {
    val logger = LoggerFactory.getLogger(this::class.java)
    logger.info(JSON.toJSONString(this, JSONWriter.Feature.PrettyFormat))
}

val <T : Any> T.logger get() = this.log()