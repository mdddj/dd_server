package shop.itbug.ticket.entry.share

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface ShareService {
    fun findAll(): List<Share>
    fun save(entity: Share): Share
    fun deleteById(id: Long)
    fun findById(id: Long): Share?
    fun findAllByPage(pageable: Pageable): Page<Share>
}