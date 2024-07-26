package shop.itbug.ticket.entry.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserOrderRepository : JpaRepository<UserOrder, Long> {

    /**
     * 根据订单编号查找
     */
    fun findByTradeParentid(tradeParentid: String) : UserOrder?
}