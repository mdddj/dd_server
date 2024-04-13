package shop.itbug.ticket.ex

import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun <T : Any> T.log() : Logger {
    return  LoggerFactory.getLogger(this::class.java)
}