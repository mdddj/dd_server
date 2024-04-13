package shop.itbug.ticket.entry

import com.alibaba.fastjson2.annotation.JSONField
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.SchemaProperty
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL
import shop.itbug.ticket.entry.blog.Blog
import shop.itbug.ticket.utils.inter.JpaCommentInter
import java.util.*
import java.util.function.Consumer


/**
 * @author ldd
 */
@Entity
@Schema(name = "评论模型")
open class Comment {
    @Id
    @GeneratedValue
    @SchemaProperty(name = "主键ID")
    open var id: Long? = null

    /**
     * 用户邮箱
     */
    @JpaCommentInter(value = "用户评论邮箱")
    @SchemaProperty(name = "用户邮箱")
    open var email: @NotBlank(message = "请输入用户邮箱") @Email(message = "请输入有效的邮箱格式") String? = null

    /**
     * 用户的网站
     */
    @JpaCommentInter(value = "用户的网站")
    @SchemaProperty(name = "用户网站")
    open var website: @URL(message = "请输入有效的网址") String? = null

    /**
     * 留言内容
     */
    @Lob
    @JpaCommentInter(value = "评论的内容，支持markdown格式")
    @SchemaProperty(name = "评论内容")
    open var content: @NotBlank(message = "请输入评论内容") String? = null

    /**
     * 一些特殊页面的留言。
     * 根据这个key查找留言列表。
     */
    @JpaCommentInter(value = "特殊页面评论的查找key")
    @SchemaProperty(name = "标识评论查询的主键ID key")
    open var findKey: String? = null

    /**
     * 用户昵称
     */
    @JpaCommentInter(value = "评论者的名字")
    @SchemaProperty(name = "用户昵称")
    @NotBlank(message = "请输入用户名字")
    @Length(max = 20, min = 1, message = "昵称字数在1-20字之间~")
    open var name: String? = null

    /**
     * 留言可以携带一些代码。
     */
    @Lob
    @JpaCommentInter(value = "评论携带的一些代码")
    @SchemaProperty(name = "评论的一些代码")
    open var code: String? = null

    /**
     * 头像
     */
    @SchemaProperty(name = "头像")
    open var avatarUrl: String? = null

    /**
     * 主留言。
     */
    @ManyToOne
    @JpaCommentInter(value = "如果是一个子留言，这个是父亲留言的ID")
    @SchemaProperty(name = "主留言对象")
    open var parentComment: Comment? = null

    /**
     * 评论类型
     */
    @JpaCommentInter(value = "评论类型，分博客评论，特殊页面的评论")
    @SchemaProperty(name = "评论类型,特殊页面的评论")
    open var type: String? = null

    /**
     *
     * 如果是博客评论，需要关联博客
     */
    @ManyToOne
    @JSONField(serialize = false)
    @SchemaProperty(name = "关联的博客")
    open var blog: Blog? = null

    /**
     * 子留言。
     */
    @get:JSONField(serialize = false)
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @SchemaProperty(name = "子留言列表")
    open var childCommentWithData: MutableSet<Comment>? = null

    /**
     * 评论时间。
     */
    @SchemaProperty(name = "评论时间")
    open var createDate: Date? = null


    @ManyToOne()
    @SchemaProperty(name = "作者")
    open var user: User? = null

    /**
     * 点赞的人。
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JSONField(serialize = false)
    @SchemaProperty(name = "点赞的人")
    open var likeUsers: MutableSet<User>? = null


    /**
     * 解决序列化死循环的问题
     * @return 处理结果
     */
    fun getChildComment(): Set<Comment> {
        childCommentWithData?.let {
            val comments: HashSet<Comment> = LinkedHashSet()
            it.forEach(Consumer { comment: Comment ->
                val parentComment: Comment? = comment.parentComment
                parentComment?.childCommentWithData = mutableSetOf()
                comment.parentComment = parentComment
                comments.add(comment)
            })
            return comments
        }
        return emptySet()
    }

    companion object {
        const val resourceTypeKey = "resource"
    }
}