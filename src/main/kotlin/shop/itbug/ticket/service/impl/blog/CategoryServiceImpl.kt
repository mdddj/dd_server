package shop.itbug.ticket.service.impl.blog

import jakarta.annotation.Resource
import org.apache.commons.lang3.StringUtils
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getCurrentPage
import shop.itbug.ticket.admin.model.getPageable
import shop.itbug.ticket.dao.blog.CategoryDao
import shop.itbug.ticket.entry.blog.Category
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.service.blog.CategoryService
import shop.itbug.ticket.service.redis.RedisKeys
import java.util.*

@Service

class CategoryServiceImpl : CategoryService {

    @Resource
    private lateinit var categoryDao: CategoryDao

    /**
     * 创建一个分类
     *
     * @param name  名称
     * @param intro 介绍
     * @param logo  头像
     * @return obj 对象
     */
    @Caching(
        evict = [
            CacheEvict(
                value = [
                    RedisKeys.CATEGORY + "list",
                    RedisKeys.CATEGORY + "count"
                ], allEntries = true
            )
        ]
    )
    override fun create(
        name: String?,
        intro: String?,
        logo: String?
    ): Category {
        val category = Category()
        category.createTime = Date()
        category.name = name ?: ""
        category.intro = intro ?: ""
        category.logo = logo
        return categoryDao.findByName(name)
            ?: return categoryDao.save(category)
    }

    /**
     * 修改一个分类
     *
     * @param name  名称
     * @param intro 介绍
     * @param logo  头像
     * @param id    主键
     * @return obj
     */
    @Caching(
        evict = [
            CacheEvict(
                value = [
                    RedisKeys.CATEGORY + "list"
                ], allEntries = true
            )
        ]
    )
    override fun update(name: String?, intro: String?, logo: String?, id: Long): Category? {
        val select = this.select(id)
        if (StringUtils.isNoneBlank(intro)) select.intro = intro ?: ""
        if (StringUtils.isNotBlank(name)) select.name = name ?: ""
        if (StringUtils.isNotBlank(logo)) select.logo = logo
        categoryDao.save(select)
        throw BizException(500, "该分类不存在或已删除")
    }

    /**
     * 删除一个分类
     *
     * @param id 主键
     */
    @Caching(
        evict = [
            CacheEvict(
                value = [
                    RedisKeys.CATEGORY + "list",
                    RedisKeys.CATEGORY + "count"
                ], allEntries = true
            )
        ]
    )
    override fun delete(id: Long) {
        categoryDao.deleteById(id)
    }

    /**
     * 根据id查找一个分类
     *
     * @param id 主键
     * @return obj
     */
    override fun select(id: Long): Category {
        val byId = categoryDao.findById(id)
        if (byId.isPresent) return byId.get()
        throw BizException(500, "该分类不存在或已删除")
    }

    /**
     * 根据名字查找一个分类
     *
     * @param name 名字
     * @return obj
     */
    override fun select(name: String?): Category {
        if (StringUtils.isBlank(name)) {
            throw BizException(CommonEnum.PARAMS_ERROR)
        }
        val byName = categoryDao.findByName(name)
        if (byName != null) {
            return byName
        }
        throw BizException(500, "该分类不存在或已删除")
    }

    /**
     * 获取全部分类
     *
     * @return 列表-分类
     */
    @Cacheable(value = [RedisKeys.CATEGORY + "list"])
    override fun selectAll(): List<Category> {
        return categoryDao.findAll() as List<Category>
    }

    /**
     * 获取分类总数
     *
     * @return 总数量
     */
    @Cacheable(value = [RedisKeys.CATEGORY + "count"])
    override fun count(): Long {
        return categoryDao.count()
    }

    /**
     * 查找分类列表
     *
     * @param pageModel 分页数据
     * @param category  查询条件
     * @return 查询结果
     */
    override fun findList(pageModel: PageModel, category: Category): Page<Category> {
        val pageRequest = PageRequest.of(pageModel.getCurrentPage(), pageModel.pageSize, Sort.Direction.DESC, "id")
        return categoryDao.findAll(Example.of(category), pageRequest)
    }

    /**
     * 保存或者修改一个分类
     *
     * @param category 分类数据
     * @return 保存到数据库后的结果集
     */
    @Caching(
        evict = [
            CacheEvict(
                value = [
                    RedisKeys.CATEGORY + "list",
                    RedisKeys.CATEGORY + "count",
                    RedisKeys.BLOG_KEY + "all"
                ], allEntries = true
            )
        ]
    )
    override fun save(category: Category): Category {
        if (category.id == null) {
            //去重
            if (categoryDao.findOne(Example.of(Category().apply { name = category.name })).isPresent) {
                throw BizException("已存在同名称分类")
            }
        }
        return categoryDao.save(category)
    }

    override fun findAll(pageModel: PageModel, category: Category?): Page<Category> {
        if (category != null) {
            return categoryDao.findAll(Example.of(category), pageModel.getPageable())
        }
        return categoryDao.findAll(pageModel.getPageable())
    }
}