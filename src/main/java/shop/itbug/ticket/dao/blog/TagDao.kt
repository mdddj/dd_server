package shop.itbug.ticket.dao.blog

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import shop.itbug.ticket.entry.blog.Tag

@Repository
interface TagDao : JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {
    fun findByName(name: String?): Tag?
}