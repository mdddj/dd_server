package shop.itbug.ticket.entry.notice

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/notice")
class NoticeController {
    @Resource
    private lateinit var noticeService: NoticeService



}