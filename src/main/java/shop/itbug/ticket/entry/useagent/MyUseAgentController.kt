package shop.itbug.ticket.entry.useagent

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/myuseagent")
class MyUseAgentController {
    @Resource
    private lateinit var myuseagentService: MyUseAgentService
}