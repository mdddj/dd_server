package shop.itbug.ticket.dao.blog

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import shop.itbug.ticket.entry.blog.Category

@Repository
interface CategoryDao : JpaRepository<Category, Long> {
    /**
     * 根据名字查找obj
     * @param name 名字
     * @return  obj
     */
    fun findByName(name: String?): Category?
}