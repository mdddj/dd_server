package shop.itbug.ticket.entry.share

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/share")
class ShareController {
    @Resource
    private lateinit var shareService: ShareService
}