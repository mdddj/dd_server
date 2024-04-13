package shop.itbug.ticket.dao

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import shop.itbug.ticket.entry.Mianji
import shop.itbug.ticket.entry.User

@Repository
interface MianjiRepository : JpaRepository<Mianji, Long> {
    fun findAllByUser(user: User, pageable: Pageable) : Page<Mianji>
    fun findByIdAndUser(id: Long,user: User) : Mianji?
}