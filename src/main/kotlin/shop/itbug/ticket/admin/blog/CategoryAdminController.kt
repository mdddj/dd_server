package shop.itbug.ticket.admin.blog

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.entry.blog.Category
import shop.itbug.ticket.service.blog.CategoryService
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.successResult

/**
 * @Author: 梁典典
 * @Date: 2024/09/30/下午2:19
 * @Description:
 */
@RestController
@RequestMapping("/api/auth/category")
@Tag(name = "博客分类管理")
class CategoryAdminController {
    @Resource
    private lateinit var categoryService: CategoryService

    @PostMapping("/update")
    @Operation(summary = "添加或者修改博客分类")
    fun updateBlogCategory(@RequestBody category: Category) : R<Category> {
        return categoryService.save(category).successResult("操作成功")
    }

}