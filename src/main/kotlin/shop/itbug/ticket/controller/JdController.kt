package shop.itbug.ticket.controller

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.service.jd.JdService

/**
 * 京东官方api接口
 * @author eee
 */
@RestController
@RequestMapping("/api/jd")
@Tag(name = "京东接口")
class JdController {
    @Resource
    private lateinit var jdService: JdService
}