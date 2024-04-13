package shop.itbug.ticket.entry.order

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.toJSONString
import jakarta.annotation.Resource
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getPageable
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.model.dtk.DtkOrderResult
import shop.itbug.ticket.model.params.OrderDetailParam
import shop.itbug.ticket.service.UserService
import shop.itbug.ticket.service.daTaoKeStringCovert
import shop.itbug.ticket.service.impl.DTKRequestServiceImpl
import shop.itbug.ticket.utils.OrderQueryTime
import shop.itbug.ticket.utils.successResult

@Service
class UserOrderServiceImpl : UserOrderService {
    @Resource
    lateinit var userOrderRepository: UserOrderRepository

    @Autowired
    private lateinit var dtkRequestService: DTKRequestServiceImpl

    @Resource
    private lateinit var userService: UserService

    override fun findAll(): List<UserOrder> {
        return userOrderRepository.findAll()
    }

    override fun save(entity: UserOrder): UserOrder {
        return userOrderRepository.save(entity)
    }

    override fun deleteById(id: Long) {
        userOrderRepository.deleteById(id)
    }

    override fun findById(id: Long): UserOrder? {
        return userOrderRepository.findByIdOrNull(id)
    }

    override fun findAllByPage(pageModel: PageModel, order: UserOrder?): Page<UserOrder> {
        val req = pageModel.getPageable()
        if (order != null) {
            return userOrderRepository.findAll(Example.of(order), req)
        }
        return userOrderRepository.findAll(req)
    }

    override fun findByExample(order: UserOrder): UserOrder? {
        val findBy = userOrderRepository.findOne(Example.of(order))
        return findBy.orElse(null)
    }

    override fun findByExampleWithPage(order: UserOrder, pageModel: PageModel): Page<UserOrder> {
        return userOrderRepository.findAll(Example.of(order),pageModel.getPageable())
    }

    override fun insertUserOrder(userOrder: UserOrder, userId: Long): UserOrder {
        val user = userService.findByUserId(userId) ?: throw BizException("没有找到用户")
        userOrder.user = user
        if (findByExample(UserOrder().apply { itemid = userOrder.itemid }) != null) {
            throw BizException("订单已归属到该用户,请不要重复操作")
        }
        return userOrderRepository.save(userOrder)
    }

    override fun findOrderBy(timer: OrderQueryTime, queryType: String): List<UserOrder> {
        val params = OrderDetailParam().apply {
            startTime = timer.startTime
            endTime = timer.endTime
            this.queryType = queryType
            this.orderScene = "2"
        }
        val orderDetails = dtkRequestService.getOrderDetails(params).successResult("查询成功").daTaoKeStringCovert {
            Json.decodeFromString(DtkOrderResult.serializer(), it)
        }
        orderDetails.data?.results?.publisherOrderDto?.let {
            if (it.isNotEmpty()) {
                val list = mutableListOf<UserOrder>()
                it.forEach { i ->
                    val userOrderModel = JSON.parseObject(i.toJSONString(), UserOrder::class.java)
                    if (i.relationId != null) {
                        userOrderModel.user = userService.findByRelationId(i.relationId.toString())
                    }
                    list.add(userOrderModel)
                }
                return saveAllOrUpdate(list)
            }
        }
        throw BizException(CommonEnum.ORDER_NOTFOUND_BY_TASK)
    }


    ///插入或者更新
    fun saveAllOrUpdate(list:  MutableList<UserOrder>) : MutableList<UserOrder> {
        val newList = mutableListOf<UserOrder>()
        list.forEach {
            val order = userOrderRepository.findByTradeParentid(it.tradeParentid ?: "")
            if(order!=null){
                it.id = order.id
                newList.add(userOrderRepository.save(it))
            }else{
                newList.add(userOrderRepository.save(it))
            }
        }
        return newList
    }

    ///
}