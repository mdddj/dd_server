package shop.itbug.ticket.entry.share

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShareRepository : JpaRepository<Share, Long> {
}