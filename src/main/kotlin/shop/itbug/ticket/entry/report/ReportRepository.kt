package shop.itbug.ticket.entry.report

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import shop.itbug.ticket.entry.User

@Repository
interface ReportRepository : JpaRepository<Report, Long> {
    fun findAllByUser(user: User,pageable: Pageable): Page<Report>
}