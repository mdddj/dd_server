package shop.itbug.ticket.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.FriendLink
import shop.itbug.ticket.service.FriendLinkService
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.successResult

/**
 * 友链相关接口
 */
@RequestMapping("/api/admin/friend")
@RestController
@Tag(name = "友链接口(管理端)")
class FriendlyLinkController {

    @Resource
    private lateinit var friendLinkService: FriendLinkService



    @GetMapping("/list")
    @Operation(summary = "查询友链列表")
    fun getList(pageModel: PageModel,friendLink: FriendLink?) : R<Page<FriendLink>> {
        return friendLinkService.findAll(pageModel, friendLink).successResult("查询成功")
    }

    @PostMapping("/save")
    @Operation(summary = "保存或者修改友链")
    fun save(@RequestBody friendLink: FriendLink) : R<FriendLink> {
        return friendLinkService.save(friendLink).successResult("保存成功")
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除友链")
    fun delete(id: Long) {
        friendLinkService.delete(id)
    }
}