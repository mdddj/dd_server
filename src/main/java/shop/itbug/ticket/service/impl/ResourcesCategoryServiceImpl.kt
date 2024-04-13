package shop.itbug.ticket.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.*
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getCurrentPage
import shop.itbug.ticket.dao.ResourcesCategoryDao
import shop.itbug.ticket.dao.blog.MyResourcesRepository
import shop.itbug.ticket.dao.model.ResourceCategoryTypes
import shop.itbug.ticket.entry.ResourceType
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.entry.ResourcesCategoryCounter
import shop.itbug.ticket.model.ResourceCategoryTreeModel
import shop.itbug.ticket.service.ResourcesCategoryService

/**
 * @author eee
 */
@Service
class ResourcesCategoryServiceImpl : ResourcesCategoryService {
    @Autowired
    private lateinit var resourcesCategoryDao: ResourcesCategoryDao

    @Autowired
    private lateinit var myResourcesRepository: MyResourcesRepository

    /**
     * 新增或者修改
     *
     * @param category obj
     * @return 保存后的
     */
    override fun save(category: ResourcesCategory): ResourcesCategory {
        return resourcesCategoryDao.save(category)
    }

    override fun findByName(name: String,type: String): ResourcesCategory {
        return resourcesCategoryDao.findByName(name)
            ?: return resourcesCategoryDao.save(ResourcesCategory().apply {
                this.name = name
                this.type = type })
    }

    override fun count(): Long {
        return resourcesCategoryDao.count()
    }

    override fun findAll(): List<ResourcesCategory> {
        return resourcesCategoryDao.findAll()
    }

    override fun findByObj(category: ResourcesCategory?): ResourcesCategory? {
        val one = category?.let { Example.of(it) }?.let { resourcesCategoryDao.findOne(it) }
        if (one != null) {
            return one.orElse(null)
        }
        return null
    }

    /**
     * 根据筛选条件查询全部群组
     *
     * @param category 查询条件
     * @return 全部结果
     */
    override fun findAllByObj(category: ResourcesCategory): List<ResourcesCategory> {
        return resourcesCategoryDao.findAll(Example.of(category))
    }

    /**
     * 分页查询群组列表
     *
     * @param pageModel 分页数据
     * @param category  查询条件
     * @return 结果
     */
    override fun findList(pageModel: PageModel, category: ResourcesCategory?): Page<ResourcesCategory> {
        val pageable: Pageable = PageRequest.of(pageModel.getCurrentPage(), pageModel.pageSize, Sort.Direction.DESC, "id")
        return if (category == null) {
            resourcesCategoryDao.findAll(pageable)
        } else resourcesCategoryDao.findAll(
            Example.of(category), pageable
        )
    }

    /**
     * 根据id删除一个群组
     *
     * @param id id
     */
    override fun deleteById(id: Long) {
        resourcesCategoryDao.deleteById(id)
    }

    /**
     * 根据名字模糊查询列表
     *
     * @param name 模糊查询文本
     * @return 查询结果列表
     */
    override fun findListByLikeName(name: String?): List<ResourcesCategory> {
        return resourcesCategoryDao.findAllByNameLike(name)
    }

    /**
     * 查找对象
     *
     * @param id 主键查找
     * @return 查找结果
     */
    override fun findById(id: Long): ResourcesCategory? {
        return resourcesCategoryDao.findById(id).orElse(null)
    }

    /**
     * 查找全部顶部分类菜单
     *
     * @return 数组
     */
    override fun findAllLevelTop(): List<ResourcesCategory> {
        val resourcesCategory = ResourcesCategory()
        resourcesCategory.level = 1
        return resourcesCategoryDao.findAll(Example.of(resourcesCategory))
    }


    /**
     * 根据父亲ID查询所有子分类的ID列表
     * 可以根据返回的这个ID列表去查询全部资源列表
     *
     * @param pid 父亲ID
     * @return 和父亲ID关联的子ID列表
     */
    override fun selectAllChildrenIds(pid: Long): List<Long> {
        return resourcesCategoryDao.findChildIds(pid)
    }

    /**
     * 根据Ids 查询列表
     *
     * @param ids ID列表
     * @return 全部子节点的列表
     */
    override fun selectAllByIds(ids: List<Long>): List<ResourcesCategory> {
        return resourcesCategoryDao.findAllById(ids)
    }

    /**
     * 根据分类ID查询全部的子节点和全部文章资源
     *
     * @param categoryId 分类ID
     * @return 查询结果
     */
    override fun findResourcesAndChilders(categoryId: Long): Map<String, Any> {
        val byId = resourcesCategoryDao.findById(categoryId)
        if (byId.isPresent) {
            val resourcesCategory = byId.get()

            // 查询全部子资源
            val myResourcesList = myResourcesRepository.findAllByCategory(resourcesCategory)
            val treeModelById = findTreeModelById(categoryId)
            val stringObjectHashMap = HashMap<String, Any>()
            stringObjectHashMap["folders"] = treeModelById
            stringObjectHashMap["children"] = myResourcesList
            return stringObjectHashMap
        }
        return HashMap()
    }

    /**
     * 转换为tree的对象
     *
     * @param id ID
     * @return 查询结果，可能为null 话说到这个分
     */
    override fun findTreeModelById(id: Long): ResourceCategoryTreeModel {
        val findedCategory = findById(id)
        val ids = selectAllChildrenIds(id)
        val treeModel = ResourceCategoryTreeModel()
        if (findedCategory != null) {
            treeModel.id = findedCategory.id
            treeModel.logo = findedCategory.logo
            treeModel.title = findedCategory.name
            treeModel.childrenIds = ids
        }
        treeModel.hasChildren = ids.isNotEmpty()
        if (ids.isNotEmpty()) {
            val resourceCategoryTreeModels = ArrayList<ResourceCategoryTreeModel>()
            for (cateId in ids) {
                val treeModelById = findTreeModelById(cateId)
                resourceCategoryTreeModels.add(treeModelById)
            }
            treeModel.children = resourceCategoryTreeModels
        }
        val myResourcesList = myResourcesRepository.findAllByCategory(findedCategory)
        treeModel.resources = myResourcesList
        return treeModel
    }

    /**
     * 查询全部类型
     *
     * @return 类型列表
     */
    override fun findAllTypes(): List<ResourceCategoryTypes> {
        return resourcesCategoryDao.findTypes() ?: emptyList()
    }

    override fun createCategoryWithSimple(name: String, type: ResourceType, logo: String?, desc: String?) {
        if (resourcesCategoryDao.findByName(name) != null) return
        val category = ResourcesCategory()
        category.name = name
        category.type = type.type
        category.logo = logo ?: ""
        category.description = desc ?: ""
        category.level = 1
        save(category)
    }

    override fun selectCategoryResourceWithType(type: String): List<ResourcesCategoryCounter> {
        return resourcesCategoryDao.findAllByType(type)
    }
}