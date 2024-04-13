package shop.itbug.ticket.entry.order

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.annotation.GetLoginUser
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.utils.ResultJSON
import shop.itbug.ticket.utils.successResult

@Schema(name = "查询订单列表的参数")
data class SelectUserOrderParam(
    @Schema(description = "订单状态,不传则是全部", example = "Success")
    val status: TkStatus? = null
)

@RequestMapping("/api/app/order")
@RestController
@Tag(name = "查询用户订单接口(APP)")
class UserOrderAppController {

    @Resource private lateinit var userOrderService: UserOrderService //服务


    @GetMapping("/list")
    @Operation(summary = "查询列表")
    fun selectUserOrder(@GetLoginUser user: User,params: SelectUserOrderParam,pageModel: PageModel) : ResultJSON<Page<UserOrder>> {
        if(user.relationId.isNullOrEmpty()){
            throw BizException(5001,"请先加入会员")
        }
        val order = UserOrder().apply {
            relationId = user.relationId?.toLong()
            tkStatus = params.status?.getIntValue()?.toLong()
        }
        return userOrderService.findByExampleWithPage(order,pageModel).successResult("查询成功")
    }
}