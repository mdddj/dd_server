package shop.itbug.ticket.dao

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import shop.itbug.ticket.entry.Comment
import shop.itbug.ticket.entry.User

/**
 * 留言
 * @author ldd
 */
interface CommentRepository : JpaRepository<Comment, Long> {
    /**
     * 查询评论列表
     * @param findKey 查询主键或者特殊查询key
     * @param type 评论的类型
     * @param pageable 分页参数
     * @param parentComment 父评价
     * @return 查询结果
     */
    fun findAllByFindKeyAndTypeAndParentComment(
        findKey: String?,
        type: String?,
        parentComment: Comment?,
        pageable: Pageable?
    ): Page<Comment>


    fun findAllByUser(user: User,pageable: Pageable) : Page<Comment>

    fun findAllByFindKey(findKey: String,pageable: Pageable): Page<Comment>

    fun findAllByFindKeyAndType(findKey: String,type: String,pageable: Pageable) : Page<Comment>
}