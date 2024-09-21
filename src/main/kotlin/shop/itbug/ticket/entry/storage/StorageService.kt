package shop.itbug.ticket.entry.storage

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface StorageService {
    fun findAll(): List<Storage>
    fun save(entity: Storage): Storage
    fun deleteById(id: Long)
    fun findById(id: Long): Storage?
    fun findAllByPage(pageable: Pageable): Page<Storage>
}