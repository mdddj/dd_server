package shop.itbug.ticket.dao.blog

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import shop.itbug.ticket.entry.blog.Text

/**
 * @author eee
 */
@Repository
interface TextDao : JpaRepository<Text, Long> {
    /**
     * 根据关键字查找一个对象
     * @param name  关键字
     * @return  对象
     */
    fun findByName(name: String): Text?

    /**
     * 根据关键字查询列表
     * @param name  关键字
     * @param pageable  分页数据
     * @return  列表
     */
    fun findAllByName(name: String, pageable: Pageable?): Page<Text>

    /**
     * 根据查询key和密码匹配结果
     * @param name 查询name key
     * @param viewPassword 加密后的密码
     * @return 查询结果
     */
    fun findByNameAndViewPassword(name: String, viewPassword: String): Text?
}