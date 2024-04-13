package shop.itbug.ticket.service.blog

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.blog.Text

/**
 * @author eee
 */
@Service
interface TextService {
    /**
     * 修改或者新增一个对象
     *
     * @param text 对象
     * @return 保存到数据后的对象
     */
    fun save(text: Text): Text

    /**
     * 根据name查询数据
     *
     * @param name key
     * @return 对象
     */
    fun findByName(name: String): Text?

    /**
     * 根据name字段来修改value
     * 如果key的值不存在,则新建,否则修改
     *
     * @param name   name
     * @param value context
     * @return 修改或者新增的对象
     */
    fun saveAndUpdate(name: String, value: String): Text

    /**
     * 判断数据库中是否存在数据
     *
     * @return 是否存在数据
     */
    fun isEmpty(): Boolean


    /**
     * 根据id删除一个对象
     * @param id   主键
     */
    fun deleteById(id: Long)

    /**
     * 查询该字典是否需要密码查看
     * @param name 字典的key
     * @return 返回true表示需要输入密码才能查看,false 则向所有人开放
     */
    fun isPasswordView(name: String): Boolean

    /**
     * 验证字典的key和用户输入的密码是否相匹配
     * @param password 用户输入的密码
     * @param textKey   字典的查询key
     * @return  匹配结果
     */
    fun verificationPassword(password: String, textKey: String): Boolean

    /**
     * 查询某个字典的原始密码.
     * 注意,这个不要暴露给普通用户查看.
     * 需要管理员才能使用这个服务
     * @param textKey 查询的关键字
     * @return 原始密码结果
     */
    fun selectTextModelOriginPassword(textKey: String): String

    /**
     * 查询列表
     */
    fun selectAll(pageModel: PageModel,text: Text?) : Page<Text>

    fun findAll(): List<Text>
}