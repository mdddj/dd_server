package shop.itbug.ticket.admin.blog

import io.swagger.v3.oas.annotations.Operation
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.entry.blog.Tag
import shop.itbug.ticket.service.blog.TagService
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/api/auth/tag")
class TagAdminController {

    @Resource private lateinit var tagService: TagService


    @GetMapping("/all")
    @Operation(summary = "获取所有标签")
    fun getAllTags(): R<List<Tag>> {
        return tagService.selectAll().successResult()
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除标签")
    fun deleteTag(id: Long) : R<Boolean>{
        tagService.delete(id)
        return R.ok(true)
    }

}