package shop.itbug.ticket.controller

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import shop.itbug.ticket.model.zhe.MeitunTuiGuangParam
import shop.itbug.ticket.service.ZheTkService

@Controller
@Tag(name = "网页接口")
class HtmlContorller {
    @Resource
    private lateinit var zheTkService: ZheTkService
    @GetMapping("/wm")
    fun waimai(model: Model): String {
        val params = MeitunTuiGuangParam()
        params.linkType = "1"
        params.actId = "33"
        val meiTuan = zheTkService.getMeiTuan(params)
        model.addAttribute("mt", meiTuan?.data) // 美团链接
        return "waimai"
    }

    @GetMapping("/post/{id}")
    fun postView(id: String?): String {
        return "index"
    }
}