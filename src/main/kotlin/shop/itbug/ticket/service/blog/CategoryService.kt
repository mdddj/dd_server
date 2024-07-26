package shop.itbug.ticket.service.blog

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.blog.Category

@Service
interface CategoryService {
    /**
     * 创建一个分类
     * @param name  名称
     * @param intro 介绍
     * @param logo  头像
     * @return  obj
     */
    fun create(name: String?, intro: String?, logo: String?): Category

    /**
     * 修改一个分类
     * @param name 名称
     * @param intro 介绍
     * @param logo  头像
     * @param id 主键
     * @return  obj
     */
    fun update(name: String?, intro: String?, logo: String?, id: Long): Category?

    /**
     * 删除一个分类
     * @param id   主键
     */
    fun delete(id: Long)

    /**
     * 根据id查找一个分类
     * @param id 主键
     * @return obj
     */
    fun select(id: Long): Category

    /**
     * 根据名字查找一个分类
     * @param name 名字
     * @return  obj
     */
    fun select(name: String?): Category

    /**
     * 获取全部分类
     * @return 列表-分类
     */
    fun selectAll(): List<Category>

    /**
     * 获取分类总数
     * @return  总数量
     */
    fun count(): Long

    /**
     * 查找分类列表
     * @param pageModel 分页数据
     * @param category  查询条件
     * @return  查询结果
     */
    fun findList(pageModel: PageModel, category: Category): Page<Category>

    /**
     * 保存或者修改一个分类
     * @param category  分类数据
     * @return  保存到数据库后的结果集
     */
    fun save(category: Category): Category





    /**
     * 查询列表
     */
    fun findAll(pageModel: PageModel,category: Category?) : Page<Category>
}