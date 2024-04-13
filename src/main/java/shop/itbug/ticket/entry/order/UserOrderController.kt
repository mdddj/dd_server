package shop.itbug.ticket.entry.order

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.ResultDialogType
import shop.itbug.ticket.model.dtk.DtkOrderResult
import shop.itbug.ticket.model.params.OrderDetailParam
import shop.itbug.ticket.service.daTaoKeStringCovert
import shop.itbug.ticket.service.impl.DTKRequestServiceImpl
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.changeDialogType
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/api/user/order")
@Tag(name = "用户订单接口")
@PreAuthorize("hasRole('admin')")
class UserOrderController {

    @Resource
    private lateinit var userOrderService: UserOrderService


    @Autowired
    private lateinit var dtkRequestService: DTKRequestServiceImpl



    @GetMapping("/list")
    @Operation(summary = "查询订单列表")
    fun findAllUserOrderByList(pageModel: PageModel,userOrder: UserOrder?) : R<Page<UserOrder>> {
        return userOrderService.findAllByPage(pageModel,userOrder).successResult("查询成功")
    }


    @GetMapping("/select-by-dtk")
    @Operation(summary = "大淘客官方订单查询")
    fun selectByDaTaoKe(params: OrderDetailParam) : R<DtkOrderResult> {
        val orderDetails = dtkRequestService.getOrderDetails(params)
        return orderDetails.successResult("查找成功").daTaoKeStringCovert {
            Json.decodeFromString(DtkOrderResult.serializer(),it)
        }.successResult("获取成功")
    }

    @PostMapping("/write-order-by-dtk")
    @Operation(summary = "订单写入(大淘客)", description = "也可以理解为关联订单到用户")
    fun writeOrderToUser(@RequestBody params: JSONObject) : R<UserOrder> {
        if(params.containsKey("userId").not()) {
            throw BizException("需要传入关联用户")
        }
        val userId = params.getLong("userId")
        val userOrder = JSON.parseObject(params.toJSONString(), UserOrder::class.java)
        val insertUserOrder = userOrderService.insertUserOrder(userOrder, userId)
        return R.ok(insertUserOrder,"关联订单成功").changeDialogType(ResultDialogType.FinalToast)
    }



}

