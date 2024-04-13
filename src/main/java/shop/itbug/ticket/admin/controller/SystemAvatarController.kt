package shop.itbug.ticket.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.entry.system.SystemAvator
import shop.itbug.ticket.service.SystemAvatarService
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/api/pic")
@Tag(name = "头像接口(管理员)")
class SystemAvatarController {
    @Resource
    private lateinit var systemAvatarService: SystemAvatarService

    /**
     * 根据类型获取图片列表
     * @param type  类型
     * @return  列表
     */
    @GetMapping("/list")
    @Operation(summary = "根据类型获取图片列表")
    fun getPics(@RequestParam("type") type: Int): Result<List<SystemAvator>> {
        val allByType = systemAvatarService.findAllByType(type)
        return allByType.successResult()
    }
}