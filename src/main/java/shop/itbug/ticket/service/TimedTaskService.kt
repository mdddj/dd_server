package shop.itbug.ticket.service

import org.springframework.stereotype.Service

/**
 * 定时任务
 */
@Service
interface TimedTaskService {
    /**
     * 实时查找订单
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    fun realTimeFindOrder(startTime: String?, endTime: String?)
}