package shop.itbug.ticket.service.impl

import jakarta.annotation.Resource
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getCurrentPage
import shop.itbug.ticket.admin.model.getPageable
import shop.itbug.ticket.controller.resource.MyResourceControllerWithApp
import shop.itbug.ticket.dao.blog.MyResourcesRepository
import shop.itbug.ticket.entry.MyResources
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.entry.User
import shop.itbug.ticket.exception.BizException
import shop.itbug.ticket.service.FileInfoService
import shop.itbug.ticket.service.MyResourceService
import shop.itbug.ticket.service.ResourcesCategoryService

/**
 * @author eee
 */
@Service
class MyResourcesServiceImpl : MyResourceService {
    @Resource
    private lateinit var myResourcesRepository: MyResourcesRepository

    @Resource
    private lateinit var fileInfoService: FileInfoService

    @Resource
    private lateinit var resourcesCategoryService: ResourcesCategoryService

    /**
     * 保存或者修改一个资源数据
     *
     * @param myResources 对象
     * @return 数据库对象
     */
    override fun saveOrUpdate(myResources: MyResources): MyResources {
        return myResourcesRepository.save(myResources)
    }


    override fun findListByCategoryId(
        param: MyResourceControllerWithApp.FindMyResourceListParam,
        pageModel: PageModel
    ): Page<MyResources> {
        val cate = resourcesCategoryService.findById(param.id)
        return myResourcesRepository.findAllByCategory(cate,pageModel.getPageable("createDate"))
    }

    /**
     * 列表查询
     *
     * @param pageModel   页码查询
     * @param myResources 资源
     * @return 查询结果
     */
    override fun findAll(pageModel: PageModel, myResources: MyResources?): Page<MyResources> {
        if(myResources==null){
            return myResourcesRepository.findAll(pageModel.getPageable())
        }
        val pageRequest = PageRequest.of(pageModel.getCurrentPage(), pageModel.pageSize, Sort.Direction.DESC, "id")
        return myResourcesRepository.findAll(Example.of(myResources), pageRequest)
    }

    /**
     * 根据群组查询资源列表
     *
     * @param category  分类
     * @param pageModel 分页
     * @return 查询结果
     */
    override fun findListByResourceCategory(category: ResourcesCategory, pageModel: PageModel): Page<MyResources> {
        // 按照创建时间倒叙查询
        val pageRequest =
            PageRequest.of(pageModel.getCurrentPage(), pageModel.pageSize, Sort.Direction.DESC, "createDate")
        return myResourcesRepository.findAllByCategory(category, pageRequest)
    }

    /**
     * 删除一个资源。
     *
     * @param id 资源ID
     */
    @Transactional()
    override fun deleteMyResource(id: Long) {
        val byId = myResourcesRepository.findById(id)
        if (byId.isPresent) {
            val myResources = byId.get()
            // 判断是否有图片资源
            val images = myResources.images
            if (!images.isNullOrEmpty()) {
                for (info in images) {
                    fileInfoService.deleteImageFromAliyunOss(info)
                }
            }
           try {
               myResources.id?.let { myResourcesRepository.deleteById(it) }
           }catch (e:IllegalArgumentException ){
               throw BizException("删除失败:${e.localizedMessage}")
           }
        }else{
            throw BizException("资源不存在")
        }

    }

    /**
     * 根据ID查询一个资源详情
     *
     * @param id 主键ID
     * @return 查询结果
     */
    override fun findById(id: Long): MyResources? {
        val o = myResourcesRepository.findById(id)
        if(o.isPresent) return o.get()
        return null
    }

    /**
     * 根据一个分类ID查询全部子资源。
     *
     * @param id 资源分类ID。
     * @return 查询结果列表
     */
    override fun findByCategoryId(id: Long): List<MyResources>? {
        val byId = resourcesCategoryService.findById(id)
        return myResourcesRepository.findAllByCategory(byId)
    }

    /**
     * 查询全部label不为空的资源
     */
    override fun finAllWithLabelNotEmpty(): List<MyResources> {
        return myResourcesRepository.findAllByLabelNotNull()
    }

    override fun findListByUser(user: User, pageModel: PageModel): Page<MyResources> {
        val pageRequest = PageRequest.of(pageModel.getCurrentPage(),pageModel.pageSize,Sort.Direction.DESC,"createDate")
        return myResourcesRepository.findAllByUser(user,pageRequest)
    }

    override fun deleteByUserAndId(user: User, id: Long) {
        myResourcesRepository.deleteByUserAndId(user, id)
    }


    override fun findListAllByCategory(param: MyResourceControllerWithApp.FindMyResourceListParam): List<MyResources> {
        return myResourcesRepository.findAllByCategory(resourcesCategoryService.findById(param.id))
    }
}