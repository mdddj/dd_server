package shop.itbug.ticket.controller

import cn.hutool.core.date.DateUtil
import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.annotation.GetLoginUser
import shop.itbug.ticket.entry.Comment
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.entry.blog.Blog
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.model.params.FindCommentParams
import shop.itbug.ticket.service.CommentService
import shop.itbug.ticket.service.blog.BlogService
import shop.itbug.ticket.utils.*

/**
 * @author ldd
 */
@RestController
@RequestMapping("/api/comment")
@Tag(name = "评论接口")
class CommentController {
    @Resource
    private lateinit var commentService: CommentService

    @Resource
    private lateinit var blogService: BlogService

    /**
     * 添加留言
     *
     * @param jsonObject 参数
     * @return 操作结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加评论")
    fun addComment(@RequestBody jsonObject: JSONObject,@GetLoginUser user: User): Result<Comment> {
        val comment = JSON.parseObject(jsonObject.toJSONString(), Comment::class.java)
        val blog: Blog?

        ///如果是博客类型
        val blogType = "blog"
        if (comment.type == blogType && comment.findKey !=null) {
            blog = blogService.select(comment.findKey!!.toLong())
            comment.blog = blog
        }
        comment.createDate = DateUtil.date()
        comment.user = user
        val saved = commentService.save(comment)


        // 如果是回复类型的评论,则需要设置父亲评论
        val parentCommentId = jsonObject.getLong("parentCommentId")
        if (parentCommentId != null) {
            // 回复类型的评论
            val parentComment =
                commentService.findById(parentCommentId) ?: throw BizException(90001, "回复评论失败,评论数据丢失")
            // 设置父亲评论
            comment.parentComment = parentComment

            //获取父亲评论的所有儿子评论
            val childComment = parentComment.childCommentWithData

            //添加新的儿子评论
            childComment!!.add(saved)
            commentService.save(parentComment)
            commentService.save(saved)
        }
        return Result.ok(saved)
    }

    /**
     * 查询评论列表
     * @param params 参数
     * @param bindingResult 验证
     * @return 结果
     */
    @GetMapping("/find")
    @Operation(summary = "查询评论列表")
    fun findComment(
        @Validated params: FindCommentParams,
        bindingResult: BindingResult
    ): Result<BlogUtil.CoverToPage<Comment>> {
        bindingResult.verify()
        val comments = commentService.selectList(params)
        val result = BlogUtil.coverToPage(comments)
        return result.successResult()
    }


    @GetMapping("/recently")
    @Operation(summary = "弃用", deprecated = true)
    fun findRecentlyComments(): Result<List<Comment>> {
        return Result(commentService.findRecentlyComments())
    }



    @GetMapping("/user-list")
    @Operation(summary = "获取用户发布的所有评论")
    fun findByUser(pageModel: PageModel,@GetLoginUser user: User) : R<Page<Comment>> {
            return commentService.findByUser(pageModel, user).successResult()
    }



}