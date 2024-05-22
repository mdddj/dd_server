package shop.itbug.ticket.service

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.controller.resource.MyResourceControllerWithApp.FindMyResourceListParam
import shop.itbug.ticket.entry.MyResources
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.entry.User

/**
 * @author eee
 */
@Service
interface MyResourceService {

    /**
     * 保存或者修改一个资源数据
     * @param myResources 对象
     * @return  数据库对象
     */
    fun saveOrUpdate(myResources: MyResources): MyResources

    /**
     * 列表查询
     * @param pageModel 页码查询
     * @param myResources   资源
     * @return  查询结果
     */
    fun findAll(pageModel: PageModel, myResources: MyResources?): Page<MyResources>

    /**
     * 根据群组查询资源列表
     * @param category  分类
     * @param pageModel 分页
     * @return  查询结果
     */
    fun findListByResourceCategory(category: ResourcesCategory, pageModel: PageModel): Page<MyResources>

    /**
     * 删除一个资源。
     * @param id 资源ID
     */

    fun deleteMyResource(id: Long)

    /**
     * 根据ID查询一个资源详情
     * @param id 主键ID
     * @return 查询结果
     */
    fun findById(id: Long): MyResources?

    /**
     * 根据一个分类ID查询全部子资源。
     * @param id    资源分类ID。
     * @return 查询结果列表
     */
    fun findByCategoryId(id: Long): List<MyResources>?

    /**
     * 查询全部label不为空的资源
     */
    fun finAllWithLabelNotEmpty(): List<MyResources>

    /**
     * 分页查询用户发布的内容
     */
    fun findListByUser(user: User,pageModel: PageModel) : Page<MyResources>

    fun findListByCategoryId(param: FindMyResourceListParam, pageModel: PageModel): Page<MyResources>

    fun findListAllByCategory(param: FindMyResourceListParam): List<MyResources>

    fun deleteByUserAndId(user: User,id: Long)
}