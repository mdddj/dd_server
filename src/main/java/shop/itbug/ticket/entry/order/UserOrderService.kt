package shop.itbug.ticket.entry.order

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.utils.OrderQueryTime

@Service
interface UserOrderService {
    fun findAll(): List<UserOrder>
    fun save(entity: UserOrder): UserOrder
    fun deleteById(id: Long)
    fun findById(id: Long): UserOrder?
    fun findAllByPage(pageModel: PageModel,order: UserOrder?): Page<UserOrder>
    fun findByExample(order: UserOrder): UserOrder?
    fun findByExampleWithPage(order: UserOrder,pageModel: PageModel): Page<UserOrder>
    fun insertUserOrder(userOrder: UserOrder,userId: Long) : UserOrder
    fun findOrderBy(timer: OrderQueryTime, queryType: String): List<UserOrder>
}