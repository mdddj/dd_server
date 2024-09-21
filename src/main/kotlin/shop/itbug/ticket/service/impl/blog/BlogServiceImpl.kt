package shop.itbug.ticket.service.impl.blog

import cn.hutool.core.date.DateUtil
import jakarta.annotation.Resource
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import shop.itbug.ticket.dao.blog.BlogDao
import shop.itbug.ticket.entry.blog.Blog
import shop.itbug.ticket.entry.blog.Tag
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.model.api.CachePage
import shop.itbug.ticket.model.api.cache
import shop.itbug.ticket.model.blog.*
import shop.itbug.ticket.service.blog.BlogService
import shop.itbug.ticket.service.blog.CategoryService
import shop.itbug.ticket.service.blog.TagService
import shop.itbug.ticket.service.redis.RedisKeys
import shop.itbug.ticket.utils.BlogUtil
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
@CacheConfig(cacheNames = ["blog"])
class BlogServiceImpl : BlogService {


    @Resource
    private lateinit var blogDao: BlogDao

    @Resource
    private lateinit var tagService: TagService


    @Resource
    private lateinit var categoryService: CategoryService

    /**
     * 发布一篇博客
     *
     * @param title      标题
     * @param content    正文
     * @param categoryId 分类id
     * @return obj
     */
    @CacheEvict(
        value = [RedisKeys.BLOG_KEY + "all", RedisKeys.BLOG_KEY + "statistics", RedisKeys.BLOG_KEY + "count", RedisKeys.BLOG_KEY + "pageable-select"],
        allEntries = true
    )
    override fun create(title: String, content: String, categoryId: Long): Blog {
        valid(title, content, categoryId)
        val category = categoryService.select(categoryId)
        val blog = Blog()
        blog.content = content
        blog.createTime = Date()
        blog.dateString = DateUtil.now()
        blog.title = title
        blog.author = "梁典典"
        blog.category = category
        return blogDao.save(blog)
    }

    /**
     * 发布一篇博客
     *
     * @param title      标题
     * @param content    正文
     * @param categoryId 分类id
     * @param tags       标签列表
     * @return obj
     */
    @Caching(
        evict = [
            CacheEvict(
                value = [RedisKeys.BLOG_KEY + "monthCount", RedisKeys.BLOG_KEY + "all", RedisKeys.BLOG_KEY + "count", RedisKeys.BLOG_KEY + "pageable-select", RedisKeys.BLOG_KEY + "getMonthBlogsWithMonth", RedisKeys.BLOG_KEY + "statistics"],
                allEntries = true
            ),
        ]
    )
    override fun create(
        title: String?,
        content: String?,
        categoryId: Long,
        alias: String?,
        tags: HashSet<Tag>?,
        html: String?
    ): Blog {
        valid(title, content, categoryId)
        val category = categoryService.select(categoryId)
        val blog = Blog()
        blog.content = content
        blog.createTime = Date()
        blog.aliasString = alias
        blog.dateString = DateUtil.now()
        blog.title = title
        blog.author = "梁典典"
        blog.category = category
        blog.html = html
        if (tags != null) {
            blog.tags = tags
        }
        return blogDao.save(blog)
    }

    @Cacheable(RedisKeys.BLOG_KEY + "statistics")
    override fun getDataStatistics(): StatisticsResultModel {
        val blogCount = blogDao.count()
        val cateCount = categoryService.count()
        val tagCount = tagService.count()
        val categories = categoryService.selectAll()
        val tags = tagService.selectAll()
        val monthCount = blogDao.countWithMonths()
        val archiveModels: MutableList<ArchiveModel> = ArrayList()
        for (model in monthCount) {
            val model1 = ArchiveModel()
            model1.count = model.count
            model1.months = model.months
            model1.blogs = blogDao.getBlogsWithMonths(model.months)
            archiveModels.add(model1)
        }
        val statisticsResultModel = StatisticsResultModel()
        statisticsResultModel.blogCount = blogCount
        statisticsResultModel.cateCount = cateCount
        statisticsResultModel.tagCount = tagCount
        statisticsResultModel.categoryList = categories
        statisticsResultModel.tags = tags
        statisticsResultModel.monthsCounts = monthCount
        statisticsResultModel.archiveModels = archiveModels
        return statisticsResultModel
    }

    /**
     * 查找一篇博客
     *
     * @param id 博客id
     * @return obj
     */
    @Cacheable(RedisKeys.BLOG_KEY + "selectById", key = "#id")
    override fun select(id: Long): Blog {
        val byId = blogDao.findById(id)
        if (byId.isPresent) {
            return byId.get()
        }
        throw BizException(CommonEnum.NOT_FOUND)
    }


    /**
     * 删除一篇博客
     *
     * @param id 博客id
     */
    @Caching(
        evict = [
            CacheEvict(
                value = [RedisKeys.BLOG_KEY + "monthCount", RedisKeys.BLOG_KEY + "all", RedisKeys.BLOG_KEY + "count", RedisKeys.BLOG_KEY + "pageable-select", RedisKeys.BLOG_KEY + "pageable-select"],
                allEntries = true
            ),
            CacheEvict(RedisKeys.BLOG_KEY + "selectById", key = "#id"),
        ]
    )
    override fun delete(id: Long) {
        blogDao.deleteById(id)
    }

    /**
     * 分页查找博客
     *
     * @param page 页数
     * @return Page<obj>
    </obj> */
    @Cacheable(RedisKeys.BLOG_KEY + "pageable-select", key = "#page")
    override fun select(page: Int): Page<Blog> {
        val pageRequest = PageRequest.of(page - 1, 10, Sort.Direction.DESC, "createTime")
        return blogDao.findAll(pageRequest)
    }

    /**
     * 给文章添加一个缩略图
     *
     * @param blogId   博客id
     * @param imageUrl 图床地址
     */
    @Caching(
        evict = [
            CacheEvict(RedisKeys.BLOG_KEY + "selectById", key = "#blogId")
        ]
    )
    override fun addThumbnail(blogId: Long, imageUrl: String) {
        val select = blogDao.findById(blogId).getOrNull() ?: throw BizException("未找到博客")
        select.thumbnail = imageUrl
        blogDao.save(select)
    }

    /**
     * 分页查找博客
     *
     * @param page     页数
     * @param pageSize 每页大小
     * @return 数据列表
     */
    @Cacheable(RedisKeys.BLOG_KEY + "pageable-select", key = "#page+'-'+#pageSize")
    override fun select(page: Int, pageSize: Int): CachePage<Blog> {
        val pageRequest = PageRequest.of(page - 1, pageSize, Sort.Direction.DESC, "createTime")
        val r = blogDao.findAll(pageRequest)
        return r.cache
    }

    /**
     * 修改一篇博客
     *
     * @param blog 要修改的内容
     * @return 修改后的博客
     */
    @Caching(
        evict = [
            CacheEvict(value = [RedisKeys.BLOG_KEY + "selectById"], key = "#blog.id"),
            CacheEvict(
                value = [RedisKeys.BLOG_KEY + "pageable-select", RedisKeys.BLOG_KEY + "all", RedisKeys.BLOG_KEY + "getMonthBlogsWithMonth", RedisKeys.BLOG_KEY + "statistics"],
                allEntries = true
            )
        ]
    )
    override fun update(blog: Blog): Blog {
        return blogDao.save(blog)
    }

    /**
     * 根据分类id 获取博客列表
     *
     * @param categoryId 分类id
     * @param page       第几页
     * @param pageSize   每页几条数据
     */
    override fun selectBlogsWithCategoryId(categoryId: Long, page: Int, pageSize: Int): Page<Blog> {
        val category = categoryService.select(categoryId)
        val pageRequest = PageRequest.of(page - 1, pageSize, Sort.Direction.DESC, "createTime")
        return blogDao.findAllByCategory(category, pageRequest)
    }

    /**
     * 获取已发表的博客总数
     *
     * @return 数量
     */
    @Cacheable(value = [RedisKeys.BLOG_KEY + "count"])
    override fun count(): Long {
        return blogDao.count()
    }

    /**
     * 根据别名查找一片博客
     *
     * @param alias 别名
     * @return 博客对象
     */

    override fun findByAlias(alias: String): Blog? {
        return blogDao.findByAliasString(alias)
    }

    /**
     * 根据月统计发布的博客数量
     */
    @Cacheable(RedisKeys.BLOG_KEY + "monthCount")
    override fun getMonthCount(): List<MonthResultModel> = blogDao.countWithMonths()

    /**
     * 根据月份查询博客列表,一次查出全部
     *
     * @param param 月份
     * @return 博客列表
     */
    override fun findByMounth(param: BlogMonthParam): Page<Blog> {
        val split = param.month.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val start = BlogUtil.getFirstAndLastDay(split[0].toInt(), split[1].toInt(), true)
        val end = BlogUtil.getFirstAndLastDay(split[0].toInt(), split[1].toInt(), false)
        val pageRequest = PageRequest.of(param.page - 1, param.pageSize, Sort.Direction.DESC, "createTime")
        return blogDao.findByCreateTimeBetween(start, end, pageRequest)
    }

    /**
     * 根据月份获取博客列表
     *
     * @param month 月份
     * @return 查询结果
     */
    @Cacheable(RedisKeys.BLOG_KEY + "getMonthBlogsWithMonth", key = "#month")
    override fun getMonthBlogsWithMonth(month: String): List<MonthBlogModel> {
        return blogDao.getBlogsWithMonths(month)
    }

    override val isEmpty: Boolean
        get() = blogDao.count() == 0L

    @Cacheable(value = [RedisKeys.BLOG_KEY + "all"])
    override fun findAll(): List<Blog> {
        return blogDao.findAll(Sort.by(Sort.Direction.DESC, "createTime"))
    }

    /// 验证
    fun valid(title: String?, content: String?, categoryId: Long?) {
        if (!BlogUtil.validBlog(title, content, categoryId)) {
            throw BizException(CommonEnum.PARAMS_ERROR)
        }
    }
}