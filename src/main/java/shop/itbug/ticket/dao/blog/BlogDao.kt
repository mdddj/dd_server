package shop.itbug.ticket.dao.blog

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import shop.itbug.ticket.entry.blog.Blog
import shop.itbug.ticket.entry.blog.Category
import shop.itbug.ticket.model.blog.MonthBlogModel
import shop.itbug.ticket.model.blog.MonthResultModel
import java.util.*

@Repository
interface BlogDao : JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
    fun findByTitle(title: String?): Blog?
    fun findAllByCategory(category: Category?, pageable: Pageable?): Page<Blog>
    fun findByAliasString(alias: String?): Blog?

    @Query(
        value = "SELECT DATE_FORMAT(create_time,'%Y-%m') months, COUNT(create_time) count FROM blog GROUP BY months ORDER BY months DESC",
        nativeQuery = true
    )
    fun countWithMonths(): List<MonthResultModel>
    fun findByCreateTimeBetween(start: Date?, end: Date?, pageable: Pageable?): Page<Blog>

    @Query(
        value = "select title,create_time as CreateTime,id from blog where date_format(create_time,'%Y-%m') = ?1",
        nativeQuery = true
    )
    fun getBlogsWithMonths(months: String?): List<MonthBlogModel>

}