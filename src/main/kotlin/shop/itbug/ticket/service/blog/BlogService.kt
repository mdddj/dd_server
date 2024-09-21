package shop.itbug.ticket.service.blog

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.entry.blog.Blog
import shop.itbug.ticket.entry.blog.Tag
import shop.itbug.ticket.model.api.CachePage
import shop.itbug.ticket.model.blog.BlogMonthParam
import shop.itbug.ticket.model.blog.MonthBlogModel
import shop.itbug.ticket.model.blog.MonthResultModel
import shop.itbug.ticket.model.blog.StatisticsResultModel

@Service

interface BlogService {
    /**
     * 发布一篇博客
     *
     * @param title      标题
     * @param content    正文
     * @param categoryId 分类id
     * @return obj
     */
    fun create(title: String, content: String, categoryId: Long): Blog

    /**
     * 发布一篇博客
     *
     * @param title      标题
     * @param content    正文
     * @param categoryId 分类id
     * @param tags       标签列表
     * @param alias 文章别名
     * @return obj
     */
    fun create(title: String?, content: String?, categoryId: Long, alias: String?, tags: HashSet<Tag>?,html: String?): Blog

    /**
     * 查找一篇博客
     *
     * @param id 博客id
     * @return obj
     */
    fun select(id: Long): Blog

    /**
     * 删除一篇博客
     *
     * @param id 博客id
     */
    fun delete(id: Long)

    /**
     * 分页查找博客
     *
     * @param page 页数
     * @return Page<obj>
    </obj> */
    fun select(page: Int): Page<Blog>

    /**
     * 给文章添加一个缩略图
     *
     * @param imageUrl 图床地址
     * @param blogId   博客id
     */
    fun addThumbnail(blogId: Long, imageUrl: String)

    /**
     * 分页查找博客
     *
     * @param page     页数
     * @param pageSize 每页大小
     * @return 数据列表
     */
    fun select(page: Int, pageSize: Int): CachePage<Blog>

    /**
     * 修改一篇博客
     *
     * @param blog 要修改的内容
     * @return 修改后的博客
     */
    fun update(blog: Blog): Blog


    /**
     * 根据分类id 获取博客列表
     *
     * @param categoryId 分类id
     * @param page       第几页 注意从1开始, 接口中已经减去了1
     * @param pageSize   每页几条数据
     * @return 查询结果
     */
    fun selectBlogsWithCategoryId(categoryId: Long, page: Int, pageSize: Int): Page<Blog>

    /**
     * 获取已发表的博客总数
     *
     * @return 数量
     */
    fun count(): Long

    /**
     * 根据别名查找一片博客
     *
     * @param alias 别名
     * @return 博客对象
     */
    fun findByAlias(alias: String): Blog?

    /**
     * 根据月统计发布的博客数量
     */
    fun getMonthCount(): List<MonthResultModel>

    /**
     * 根据月份查询博客列表,一次查出全部
     * @param param 请求参数
     * @return  博客列表
     */
    fun findByMounth(param: BlogMonthParam): Page<Blog>

    /**
     * 根据月份获取博客列表
     * @param month 月份
     * @return 查询结果
     */
    fun getMonthBlogsWithMonth(month: String): List<MonthBlogModel>
    val isEmpty: Boolean

    fun findAll() : List<Blog>

    fun getDataStatistics(): StatisticsResultModel
}