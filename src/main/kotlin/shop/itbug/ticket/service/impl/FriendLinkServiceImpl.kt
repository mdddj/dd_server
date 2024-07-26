package shop.itbug.ticket.service.impl

import jakarta.annotation.Resource
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getPageable
import shop.itbug.ticket.dao.FriendLinkDao
import shop.itbug.ticket.entry.FriendLink
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.service.FriendLinkService
import shop.itbug.ticket.service.redis.RedisKeys

@Service
class FriendLinkServiceImpl : FriendLinkService {
    @Resource
    private lateinit var friendLinkDao: FriendLinkDao

    /**
     * 根据条件获取全部友链
     *
     * @param friendLink 条件
     * @return 列表
     */
    @Cacheable(RedisKeys.FRIEND+"filter", key = "#friendLink")
    override fun findAllBy(friendLink: FriendLink): List<FriendLink> {
        return friendLinkDao.findAll(Example.of(friendLink))
    }

    @Cacheable(RedisKeys.FRIEND+"list")
    override fun findAll(): List<FriendLink> {
        return friendLinkDao.findAll()
    }

    /**
     * 创建或者修改一个友链
     *
     * @param friendLink 对象
     * @return 对象
     */
    @Caching(
        evict = [
            CacheEvict(RedisKeys.FRIEND + "list", allEntries = true),
            CacheEvict(RedisKeys.FRIEND + "filter", allEntries = true)
        ]
    )
    override fun save(friendLink: FriendLink): FriendLink {
        if (friendLinkDao.exists(Example.of(friendLink))) {
            throw BizException(CommonEnum.RESOURCES_REPETITION)
        }
        return friendLinkDao.save(friendLink)
    }

    /**
     * 删除一个友情链接
     *
     * @param id 主键ID
     */
    @Caching(
        evict = [
            CacheEvict(RedisKeys.FRIEND + "list", allEntries = true),
            CacheEvict(RedisKeys.FRIEND + "filter", allEntries = true)
        ]
    )
    override fun delete(id: Long) {
        friendLinkDao.deleteById(id)
    }

    /**
     * 查询列表
     */
    @Cacheable(RedisKeys.FRIEND + "list", key = "#pageModel+''+#friendLink")
    override fun findAll(pageModel: PageModel, friendLink: FriendLink?): Page<FriendLink> {
        val pageable = pageModel.getPageable()
        return if (friendLink == null) {
            friendLinkDao.findAll(pageable)
        } else {
            friendLinkDao.findAll(Example.of(friendLink), pageable)
        }
    }
}