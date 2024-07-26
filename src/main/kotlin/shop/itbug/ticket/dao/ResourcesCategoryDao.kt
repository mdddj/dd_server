package shop.itbug.ticket.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import shop.itbug.ticket.dao.model.ResourceCategoryTypes
import shop.itbug.ticket.entry.ResourcesCategory
import shop.itbug.ticket.entry.ResourcesCategoryCounter

/**
 * @author eee
 */
@Repository
interface ResourcesCategoryDao : JpaRepository<ResourcesCategory, Long>,
    JpaSpecificationExecutor<ResourcesCategory> {
    /**
     * 根据群组名模糊查询列表
     * @param name  群组名
     * @return  查询结果
     */
    fun findAllByNameLike(name: String?): List<ResourcesCategory>


    /**
     * 查询某个父亲节点下面的全部子节点ID列表
     * @param pid 父亲ID
     * @return 查询结果，是个long类型的列表
     */
    @Query(
        value = "select id from resources_category s where s.parent_node_id = :pid",
        nativeQuery = true
    )
    fun findChildIds(@Param("pid") pid: Long): List<Long>

    /**
     * 获取都有哪些类型,分类
     */
    @Query(
        value = "select type,count(distinct id) as count from resources_category where type IS NOT NULL group by type",
        nativeQuery = true
    )
    fun findTypes(): List<ResourceCategoryTypes>?

    /**
     * 查询是否存在改[name]名字的频道
     */
    fun findByName(name: String): ResourcesCategory?

    /**
     * 根据type查询列表,这里会把动态数量查询出来
     */
    @Query(
        value = "select r.*, count(rr.id) as resourceCount  from resources_category r  left join my_resources rr on r.id = rr.category_id where r.type = :type  group by r.id",
        nativeQuery = true
    )
    fun findAllByType(@Param("type") type: String): List<ResourcesCategoryCounter>
}