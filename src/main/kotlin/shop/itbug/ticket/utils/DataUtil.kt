package shop.itbug.ticket.utils

import java.text.SimpleDateFormat
import java.util.*

data class OrderQueryTime(val startTime: String,val endTime: String)
object MyDateUtil {


    /**
     * @param interval 几秒之前的时间, 如果是5秒之前的时间,则设置为 -5
     */
    fun calcOrderQueryTime(interval: Int) : OrderQueryTime {
        val beforeTime = Calendar.getInstance()
        beforeTime.add(Calendar.SECOND, interval) // 5秒钟之前的时间
        val beforeD = beforeTime.time
        val before5 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beforeD) // 前五秒钟的时间
        val time = Calendar.getInstance().time
        val now = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time)
        return OrderQueryTime(startTime = before5, endTime = now)
    }

}