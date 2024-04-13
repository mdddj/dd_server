package shop.itbug.ticket.dao

import org.springframework.data.jpa.repository.JpaRepository
import shop.itbug.ticket.entry.system.SystemAvator

/**
 *
 * 访问仓库
 * @author 梁典典
 */
interface SystemAvatarRepository : JpaRepository<SystemAvator, Long> {
    /**
     * 根据类型统计总数
     * @param type 类型
     * @return  总数
     */
    fun countByType(type: Int): Long

    /**
     * 根据类型或者全部的资源图片
     * @param type  类型
     * @return  列表
     */
    fun findAllByType(type: Int): List<SystemAvator>
}