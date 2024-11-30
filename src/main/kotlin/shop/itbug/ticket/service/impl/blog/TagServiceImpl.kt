package shop.itbug.ticket.service.impl.blog

import jakarta.annotation.Resource
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.JoinType
import jakarta.persistence.criteria.Root
import org.apache.commons.lang3.StringUtils
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import shop.itbug.ticket.dao.blog.BlogDao
import shop.itbug.ticket.dao.blog.TagDao
import shop.itbug.ticket.entry.blog.Blog
import shop.itbug.ticket.entry.blog.Tag
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.service.blog.TagService
import shop.itbug.ticket.service.redis.RedisKeys
import java.util.function.Consumer

/**
 * @author ldd
 */
@Service
class TagServiceImpl : TagService {
    @Resource
    private lateinit var tagDao: TagDao

    @Resource
    private lateinit var blogDao: BlogDao

    /**
     * 添加一个标签
     *
     * @param name 名称
     * @return obj
     */
    @Caching(
        evict = [
            CacheEvict(
                value = [RedisKeys.TAG + "findBlogsByTagId", RedisKeys.TAG + "count", RedisKeys.TAG + "all", RedisKeys.TAG + "selectById", RedisKeys.TAG + "selectByName"],
                allEntries = true
            )
        ]
    )
    override fun createTag(name: String): Tag {
        return this.select(name) ?: tagDao.save(Tag(name))
    }

    /**
     * 根据名字查找标签
     *
     * @param name 名字
     * @return obj
     */
    @Caching(
        cacheable = [
            Cacheable(value = [RedisKeys.TAG + "selectByName"], key = "#name")
        ]
    )
    override fun select(name: String): Tag? {
        if (StringUtils.isBlank(name)) {
            throw BizException(CommonEnum.PARAMS_ERROR)
        }
        return tagDao.findByName(name)
            ?: return tagDao.save(Tag(name))
    }

    /**
     * 根据id查找标签
     *
     * @param id 主键
     * @return obj
     */
    @Caching(
        cacheable = [
            Cacheable(value = [RedisKeys.TAG + "selectById"], key = "#id")
        ]
    )
    override fun select(id: Long): Tag? {
        val byId = tagDao.findById(id)
        return byId.orElse(null)
    }

    /**
     * 删除一个标签
     *
     * @param id 主键
     */
    @Caching(
        evict = [
            CacheEvict(
                value = [RedisKeys.TAG + "findBlogsByTagId", RedisKeys.TAG + "count", RedisKeys.TAG + "all", RedisKeys.TAG + "selectById", RedisKeys.TAG + "selectByName"],
                allEntries = true
            )
        ]
    )
    override fun delete(id: Long) {
        try {
            tagDao.deleteById(id)
        } catch (e: Exception) {
            throw BizException("删除失败:${e.localizedMessage}")
        }
    }


    @Caching(
        evict = [
            CacheEvict(
                value = [RedisKeys.TAG + "findBlogsByTagId", RedisKeys.TAG + "count", RedisKeys.TAG + "all", RedisKeys.TAG + "selectById", RedisKeys.TAG + "selectByName"],
                allEntries = true
            )
        ]
    )
    override fun createAndUpdate(tag: Tag): Tag {
        return tagDao.save(tag)
    }

    /**
     * 将对象存储到数据库并返回保存后的实例
     *
     * @param tags 标签列表
     * @return 数据库里面的实例
     */
    @Caching(
        evict = [
            CacheEvict(
                value = [RedisKeys.TAG + "findBlogsByTagId", RedisKeys.TAG + "count", RedisKeys.TAG + "all", RedisKeys.TAG + "selectById", RedisKeys.TAG + "selectByName"],
                allEntries = true
            )
        ]
    )
    override fun save(tags: HashSet<Tag>): HashSet<Tag> {
        val datas = HashSet<Tag>()
        tags.forEach(Consumer { tag: Tag -> datas.add(this.select(tag.name)!!) })
        return datas
    }

    /**
     * 根据标签id查询关联的博客列表
     *
     * @param id 标签id
     * @return 博客列表
     */
    @Caching(
        cacheable = [
            Cacheable(value = [RedisKeys.TAG + "findBlogsByTagId"], key = "#id+'_'+#page+'_'+#size")
        ]
    )
    override fun findBlogsByTagId(id: Long?, page: Int, size: Int): Page<Blog> {
        val specification =
            Specification { root: Root<Blog?>, _: CriteriaQuery<*>?, criteriaBuilder: CriteriaBuilder ->
                val join = root.join<Any, Any>("tags", JoinType.INNER)
                criteriaBuilder.equal(join.get<Any>("id").`as`(Long::class.java), id)
            }
        val pageRequest = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createTime")
        return blogDao.findAll(specification, pageRequest)
    }

    /**
     * 标签数量
     *
     * @return 标签总数
     */
    @Caching(
        cacheable = [
            Cacheable(value = [RedisKeys.TAG + "count"])
        ]
    )
    override fun count(): Long {
        return tagDao.count()
    }

    /**
     * 获取全部的标签
     *
     * @return 标签列表
     */
    @Caching(
        cacheable = [
            Cacheable(value = [RedisKeys.TAG + "all"])
        ]
    )
    override fun selectAll(): List<Tag> {
        return tagDao.findAll() as List<Tag>
    }
}