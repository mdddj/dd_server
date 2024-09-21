package shop.itbug.ticket.admin.controller

import com.alibaba.fastjson2.JSONObject
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.service.CommentService
import shop.itbug.ticket.utils.Result

/**
 *
 * 评论的管理员类
 * 需要管理员权限才能操作
 * @author ldd
 */
@RestController
@RequestMapping("/api/auth/comment")
@Tag(name = "评论接口(管理员)")
class CommentAdminController {
    @Resource
    private val commentService: CommentService? = null

    /**
     * 删除某条评论,如果评论里面有子评论,子评论也会同时被删除
     * @return 操作结果
     */
    @DeleteMapping("/remove")
    @Operation(summary = "删除评论", description = "如果评论里面有子评论,将一并删除")
    fun remove(@RequestBody jsonObject: JSONObject): Result<Any> {
        val id = jsonObject.getLong("id") ?: throw BizException(CommonEnum.PARAMS_ERROR)
        commentService!!.deleteById(id)
        return Result.ok("删除成功")
    }
}