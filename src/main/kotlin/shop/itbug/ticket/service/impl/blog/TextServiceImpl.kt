package shop.itbug.ticket.service.impl.blog

import cn.hutool.core.date.DateUtil
import jakarta.annotation.Resource
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getPageable
import shop.itbug.ticket.dao.blog.TextDao
import shop.itbug.ticket.entry.blog.Text
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.exception.CommonEnum
import shop.itbug.ticket.service.blog.TextService
import shop.itbug.ticket.service.redis.RedisKeys

/**
 * @author eee
 */
@Service
class TextServiceImpl : TextService {

    @Resource
    private lateinit var textDao: TextDao


    @Caching(
        evict = [
            CacheEvict(RedisKeys.TEXT + "model", key = "#text.name"),
            CacheEvict(RedisKeys.TEXT + "list", allEntries = true)
        ]
    )
    override fun save(text: Text): Text {
        if(text.id == null){
            text.createDate = DateUtil.date()
        }else{
            text.updateDate = DateUtil.date()
        }
        return textDao.save(text)
    }


    @Cacheable(RedisKeys.TEXT + "model", key = "#name")
    override fun findByName(name: String): Text? {
        return textDao.findByName(name)
    }


    @Caching(
        evict = [
            CacheEvict(RedisKeys.TEXT + "model", key = "#name"),
            CacheEvict(RedisKeys.TEXT + "list", allEntries = true)
        ]
    )
    override fun saveAndUpdate(name: String, value: String): Text {
        val lastNewVersionNumber = textDao.findByName(name)
        return if (lastNewVersionNumber != null) {
            lastNewVersionNumber.context = value
            lastNewVersionNumber.updateDate = DateUtil.date()
            save(lastNewVersionNumber)
        } else {
            val text = Text()
            text.name = name
            text.context = value
            text.createDate = DateUtil.date()
            save(text)
        }
    }


    @Cacheable(RedisKeys.TEXT + "isEmpty")
    override fun isEmpty(): Boolean = textDao.findAll().isEmpty()


    /**
     * 根据id删除一个对象
     *
     * @param id 主键
     */
    @Caching(
        evict = [
            CacheEvict(RedisKeys.TEXT + "model", allEntries = true),
            CacheEvict(RedisKeys.TEXT + "isEmpty", allEntries = true),
            CacheEvict(RedisKeys.TEXT + "list", allEntries = true)
        ]
    )
    override fun deleteById(id: Long) {
        textDao.deleteById(id)
    }

    /**
     * 查询该字典是否需要密码查看
     *
     * @param name 字典的key
     * @return 返回true表示需要输入密码才能查看, false 则向所有人开放
     */
    override fun isPasswordView(name: String): Boolean {
        val model = textDao.findByName(name) ?: throw BizException(CommonEnum.NOT_FOUND)
        return model.isEncryptionText ?: return false
    }

    /**
     * 验证字典的key和用户输入的密码是否相匹配
     *
     * @param password 用户输入的密码
     * @param textKey  字典的查询key
     * @return 匹配结果
     */
    override fun verificationPassword(password: String, textKey: String): Boolean {
        val encodePassword = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password)
        val find = textDao.findByNameAndViewPassword(textKey, encodePassword)
        return find != null
    }

    /**
     * 查询某个字典的原始密码.
     * 注意,这个不要暴露给普通用户查看.
     * 需要管理员才能使用这个服务
     *
     * @param textKey 查询的关键字
     * @return 原始密码结果
     */
    override fun selectTextModelOriginPassword(textKey: String): String {
        val byName = textDao.findByName(textKey)
        val originPassword = byName?.originPassword
        return originPassword ?: "没有查询到结果"
    }

    @Cacheable(RedisKeys.TEXT + "list")
    override fun findAll(): List<Text> {
        return textDao.findAll()
    }

    @Cacheable(RedisKeys.TEXT + "listSelect", key = "#pageModel+'-'+#text")
    override fun selectAll(pageModel: PageModel, text: Text?): Page<Text> {
        val pageRequest = pageModel.getPageable()
        return if (text == null) textDao.findAll(pageRequest) else textDao.findAll(Example.of(text), pageRequest)
    }
}