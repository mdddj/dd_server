package shop.itbug.ticket.entry.blog

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import java.io.Serializable
import java.util.*

/**
 * 博客
 *
 * @author 梁典典
 */
@Entity
@Schema(name = "博客")
open class Blog : Serializable {
    @Id
    @GeneratedValue
    @Schema(description = "主键ID")
    open var id: Long? = null

    @Schema(description = "标题")
    open var title: String? = null


    @Lob
    @Column(columnDefinition = "LONGTEXT")
    @Schema(description = "markdown内容")
    open var content: String? = null

    @Schema(description = "文章创建时间")
    open var createTime: Date? = null


    @ManyToOne
    @Schema(description = "文章分类")
    open var category: Category? = null

    @Schema(description = "文章作者")
    open var author: String? = null

    @Schema(description = "文章缩略图")
    @Column(columnDefinition = "LONGTEXT")
    open var thumbnail: String? = null

    @Schema(description = "发布时间")
    open var dateString: String? = null

    @Schema(description = "文章标签")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "blog_tags",
        joinColumns = [JoinColumn(referencedColumnName = "id", name = "blog_id")],
        inverseJoinColumns = [JoinColumn(referencedColumnName = "id", name = "tag_id")]
    )
    open var tags: MutableSet<Tag> = mutableSetOf()

    @Schema(description = "博客别名")
    open var aliasString: String? = null


    @Schema(description = "html正文内容")
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    open var html: String? = null
}