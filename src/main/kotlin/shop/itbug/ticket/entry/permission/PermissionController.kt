package shop.itbug.ticket.entry.permission

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/permission")
class PermissionController {
    @Resource
    private lateinit var permissionService: PermissionService
}