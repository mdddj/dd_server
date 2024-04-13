package shop.itbug.ticket.entry.blog

import com.alibaba.fastjson2.annotation.JSONField
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import java.io.Serializable
import java.util.*


typealias BlogTag = Tag
/**
 * 博客文章标签
 *
 * @author 梁典典
 */
@Entity
@Schema(name = "博客标签")
open class Tag : Serializable {

    constructor()

    constructor(name: String){
        this.name = name
    }

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    open var id: Long? = null

   open var name: String = ""
    /**
     * 标签下面的博客列表
     */
    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    @JSONField(serialize = false)
    open var blogs: MutableSet<Blog> = mutableSetOf()

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val tag = o as Tag
        return id == tag.id && name == tag.name
    }

    override fun hashCode(): Int {
        return Objects.hash(id, name)
    }
}