package shop.itbug.ticket.service.impl

import cn.hutool.core.date.DateUtil
import jakarta.annotation.Resource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.PublicApiController
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getPageable
import shop.itbug.ticket.dao.CommentRepository
import shop.itbug.ticket.entry.Comment
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.entry.blog.Blog
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.model.params.FindCommentParams
import shop.itbug.ticket.service.CommentService
import shop.itbug.ticket.service.blog.BlogService

/**
 * @author ldd
 */
@Service
class CommentServiceImpl : CommentService {
    @Resource
   lateinit var  commentRepository: CommentRepository


   @Autowired
   private lateinit var blogService: BlogService
    /**
     * 添加一个留言
     * @param comment 留言信息
     * @return 留言结果
     */
    override fun save(comment: Comment): Comment {
        return commentRepository.save(comment)
    }

    /**
     * 查询评论列表
     *
     * @param params 查询参数
     * @return 查询结果
     */
    override fun selectList(params: FindCommentParams): Page<Comment> {
        val pageRequest = params.page?.let { PageRequest.of(it, 10, Sort.Direction.DESC, "createDate") }
        return commentRepository.findAllByFindKeyAndTypeAndParentComment(
            params.findKey,
            params.type,
            null,
            pageRequest
        )
    }

    /**
     * 根据ID查询博客对象
     *
     * @param id 主键
     * @return 查询结果, 可能为null
     */
    override fun findById(id: Long): Comment? {
        val byId = commentRepository.findById(id)
        return byId.orElse(null)
    }

    /**
     * 删除某条评论
     *
     * @param id 主键ID
     */
    override fun deleteById(id: Long) {
        val byId = commentRepository.findById(id)
        if (byId.isPresent) {
            val comment = byId.get()
            val parentComment = comment.parentComment
            if (parentComment != null) {
                comment.parentComment = null
                parentComment.childCommentWithData?.removeIf { comment1: Comment? -> comment1?.id == id }
                save(parentComment)
                commentRepository.deleteById(id)
            }
        } else {
            throw BizException(404, "该评论不存在或者已经被删除")
        }
    }

    /**
    * 查询最近十条博客的评论
    */
    override fun findRecentlyComments(): List<Comment> {
        val pageable  = PageRequest.of(0,10,Sort.Direction.DESC,"createDate")
       return commentRepository.findAll(pageable).content
    }

    override fun findByUser(pageModel: PageModel, user: User): Page<Comment> {
        val req = pageModel.getPageable("createDate")
        return commentRepository.findAllByUser(user,req)
    }


    override fun findAllByType(findType: String, findKey: String, pageModel: PageModel): Page<Comment> {
        val req = pageModel.getPageable("createDate")
        return commentRepository.findAllByFindKeyAndType(findKey,findType,req)
    }

    override fun replyComment(param: PublicApiController.AddCommentWithVisitorsParam,user: User?): Comment {
        val comment = Comment().apply {
            email = param.email
            website = param.website
            content = param.content
            name = param.name
            type = param.type
            findKey = param.findKey
            avatarUrl = param.avatarURL
        }
        val blog: Blog?

        ///如果是博客类型
        val blogType = "blog"
        if (comment.type == blogType && comment.findKey !=null) {
            blog = blogService.select(comment.findKey!!.toLong())
            comment.blog = blog
        }
        comment.createDate = DateUtil.date()
        comment.user = user
        val saved = save(comment)


        // 如果是回复类型的评论,则需要设置父亲评论
        val parentCommentId = param.parentCommentId
        if (parentCommentId != null) {
            // 回复类型的评论
            val parentComment =
                findById(parentCommentId) ?: throw BizException("回复评论失败,评论数据丢失")
            // 设置父亲评论
            comment.parentComment = parentComment

            //获取父亲评论的所有儿子评论
            val childComment = parentComment.childCommentWithData

            //添加新的儿子评论
            childComment?.add(saved)
            save(parentComment)
            save(saved)
        }
        return saved
    }
}