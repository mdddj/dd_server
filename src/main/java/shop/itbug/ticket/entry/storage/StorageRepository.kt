package shop.itbug.ticket.entry.storage

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StorageRepository : JpaRepository<Storage, Long> {
}