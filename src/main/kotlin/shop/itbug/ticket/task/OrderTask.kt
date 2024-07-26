package shop.itbug.ticket.task

import jakarta.annotation.Resource
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import shop.itbug.ticket.entry.order.UserOrderService
import shop.itbug.ticket.service.UserService
import shop.itbug.ticket.utils.MyDateUtil
import shop.itbug.ticket.utils.printToConsole

@Component
@EnableScheduling
class OrderTask {

    @Resource
    private lateinit var userService: UserService

    @Resource
    private lateinit var userOrderService: UserOrderService

    /**
     * 每5秒轮询一下订单
     */
    @Scheduled(cron = "0/5 * * * * ?")
    fun orderTask(){
        getZheTaokeOrder()
    }

    //获取折淘客订单
    fun getZheTaokeOrder() {
        try{
           val orderList =  userOrderService.findOrderBy(MyDateUtil.calcOrderQueryTime(-10),"4")
            orderList.printToConsole()
            userService.sendSimpleEmail("413153189@qq.com","有新的淘客订单","有新的订单! 数量:  ${orderList.size}  ${orderList.map { it.id }}",true)
        }catch (e:Exception){
//            log().info(e.localizedMessage)
        }
    }
}