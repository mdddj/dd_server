package shop.itbug.ticket.service

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.PublicApiController
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.Comment
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.model.params.FindCommentParams

/**
 * @author ldd
 */
@Service
interface CommentService {
    /**
     * 添加一个留言
     * @param comment 留言信息
     * @return 留言结果
     */
    fun save(comment: Comment): Comment

    /**
     * 查询评论列表
     * @param params 查询参数
     * @return 查询结果
     */
    fun selectList(params: FindCommentParams): Page<Comment>

    /**
     * 根据ID查询博客对象
     * @param id 主键
     * @return 查询结果,可能为null
     */
    fun findById(id: Long): Comment?

    /**
     * 删除某条评论
     * @param id 主键ID
     */
    fun deleteById(id: Long)

    /**
     * 查询最近十条博客的评论
     */
    fun findRecentlyComments(): List<Comment>

    fun findByUser(pageModel: PageModel,user: User): Page<Comment>


    fun findAllByType(findType: String,findKey: String, pageModel: PageModel) : Page<Comment>

    /**
     * 回复评论
     * @param param 传参
     */
    fun replyComment(param: PublicApiController.AddCommentWithVisitorsParam,user: User?): Comment
}