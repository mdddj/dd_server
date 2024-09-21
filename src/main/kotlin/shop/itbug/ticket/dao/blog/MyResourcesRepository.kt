package shop.itbug.ticket.dao.blog

import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import shop.itbug.ticket.entry.MyResources
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.entry.User

/**
 * @author eee
 */
@Transactional
interface MyResourcesRepository : JpaRepository<MyResources, Long> {
    /**
     * 根据群组查询资源列表
     * @param category  群组
     * @param pageable  分页数据
     * @return  查询结果
     */
    fun findAllByCategory(category: ResourcesCategory, pageable: Pageable): Page<MyResources>

    /**
     * 使用分类ID获取全部的资源
     * @param category  分类
     * @return 查询结果
     */
    fun findAllByCategory(category: ResourcesCategory?): List<MyResources>

    fun findAllByLabelNotNull(): List<MyResources>


    fun findAllByUser(user: User,pageable: Pageable): Page<MyResources>


    fun deleteByUserAndId(user: User,id:Long)
    fun findAllByCategory(category: ResourcesCategory,sort: Sort = Sort.by(Sort.Direction.DESC,"createDate")) : List<MyResources>

}