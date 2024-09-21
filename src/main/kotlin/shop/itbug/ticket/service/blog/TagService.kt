package shop.itbug.ticket.service.blog

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.entry.blog.Blog
import shop.itbug.ticket.entry.blog.Tag

@Service
interface TagService {
    /**
     * 添加一个标签
     *
     *
     * 如果该名称已存在,将返回数据库中的实体类
     * 不存在则新建
     *
     * @param name 名称,会自动创建
     * @return obj
     */
    fun createTag(name: String): Tag

    /**
     * 根据名字查找标签
     *
     * @param name 名字
     * @return obj
     */
    fun select(name: String): Tag?

    /**
     * 根据id查找标签
     *
     * @param id 主键
     * @return obj
     */
    fun select(id: Long): Tag?

    /**
     * 删除一个标签
     *
     * @param id 主键
     */
    fun delete(id: Long)


    /**
     * 创建和修改标签
     *
     * @param tag 标签对象
     * @return 修改后的标签
     */
    fun createAndUpdate(tag: Tag): Tag

    /**
     * 将对象存储到数据库并返回保存后的实例
     *
     * @param tags 标签列表
     * @return 数据库里面的实例
     */
    fun save(tags: HashSet<Tag>): HashSet<Tag>

    /**
     * 根据标签id查询关联的博客列表
     *
     * @param id 标签id
     * @return 博客列表
     */
    fun findBlogsByTagId(id: Long?, page: Int, size: Int): Page<Blog>

    /**
     * 标签数量
     * @return 标签总数
     */
    fun count(): Long

    /**
     * 获取全部的标签
     * @return 标签列表
     */
    fun selectAll(): List<Tag>
}