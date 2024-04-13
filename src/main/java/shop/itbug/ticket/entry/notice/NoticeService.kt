package shop.itbug.ticket.entry.notice

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface NoticeService {
    fun findAll(): List<Notice>
    fun save(entity: Notice): Notice
    fun deleteById(id: Long)
    fun findById(id: Long): Notice?
    fun findAllByPage(pageable: Pageable): Page<Notice>
}