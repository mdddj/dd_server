package shop.itbug.ticket.dao

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import shop.itbug.ticket.entry.FileInfo
import shop.itbug.ticket.entry.User

/**
 * @author eee
 */
interface FileInfoRepository : JpaRepository<FileInfo, Long>{
    fun findAllByUser(user: User,pageable: Pageable): Page<FileInfo>
}