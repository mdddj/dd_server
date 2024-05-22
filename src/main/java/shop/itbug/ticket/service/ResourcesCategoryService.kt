package shop.itbug.ticket.service

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.dao.model.ResourceCategoryTypes
import shop.itbug.ticket.entry.ResourceType
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.entry.ResourcesCategoryCounter
import shop.itbug.ticket.model.ResourceCategoryTreeModel

/**
 * @author eee
 */
@Service
interface ResourcesCategoryService {
    /**
     * 新增或者修改
     * @param category  obj
     * @return  保存后的
     */
    fun save(category: ResourcesCategory): ResourcesCategory

    /**
     * 获取群组总数
     * @return 数量
     */
    fun count(): Long

    /**
     * 获取全部群组
     * @return  列表
     */
    fun findAll(): List<ResourcesCategory>

    /**
     * 根据相似对象来查询某个对象
     * @param category  相似对象
     * @return  查询结果
     */
    fun findByObj(category: ResourcesCategory?): ResourcesCategory

    /**
     * 根据筛选条件查询全部群组
     * @param category  查询条件
     * @return  全部结果
     */
    fun findAllByObj(category: ResourcesCategory): List<ResourcesCategory>

    /**
     * 分页查询群组列表
     * @param pageModel 分页数据
     * @param category  查询条件
     * @return  结果
     */
    fun findList(pageModel: PageModel, category: ResourcesCategory?): Page<ResourcesCategory>

    /**
     * 根据id删除一个群组
     * @param id    id
     */
    fun deleteById(id: Long)

    /**
     * 根据名字模糊查询列表
     * @param name 模糊查询文本
     * @return  查询结果列表
     */
    fun findListByLikeName(name: String?): List<ResourcesCategory>

    /**
     * 查找对象
     * @param id 主键查找
     * @return  查找结果
     */
    fun findById(id: Long): ResourcesCategory

    /**
     * 查找全部顶部分类菜单
     * @return 数组
     */
    fun findAllLevelTop(): List<ResourcesCategory>


    /**
     * 根据父亲ID查询所有子分类的ID列表
     * 可以根据返回的这个ID列表去查询全部资源列表
     * @param pid 父亲ID
     * @return  和父亲ID关联的子ID列表
     */
    fun selectAllChildrenIds(pid: Long): List<Long>

    /**
     * 根据Ids 查询列表
     * @param ids ID列表
     * @return 全部子节点集合列表
     */
    fun selectAllByIds(ids: List<Long>): List<ResourcesCategory>

    /**
     * 根据分类ID查询全部的子节点和全部文章资源
     * @param categoryId    分类ID
     * @return 查询结果
     */
    fun findResourcesAndChilders(categoryId: Long): Map<String, Any>

    /**
     * 转换为tree的对象
     * @param id ID
     * @return 查询结果，可能为null
     */
    fun findTreeModelById(id: Long): ResourceCategoryTreeModel

    /**
     * 查询全部类型
     * @return 类型列表
     */
    fun findAllTypes(): List<ResourceCategoryTypes>

    /**
     * 创建一个顶级频道
     */
    fun createCategoryWithSimple(name: String,type: ResourceType,logo: String?,desc: String?)

    /**
     * 根据type查询频道列表
     */
    fun selectCategoryResourceWithType(type: String) : List<ResourcesCategoryCounter>

    /**
     * 根据名字来获取分组对象
     * 注意: 如果没找到会立即新建一个分组
     */
    fun findByName(name: String, type: String = ""): ResourcesCategory
}