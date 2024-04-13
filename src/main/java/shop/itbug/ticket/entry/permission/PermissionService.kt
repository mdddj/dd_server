package shop.itbug.ticket.entry.permission

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface PermissionService {
    fun findAll(): List<Permission>
    fun save(entity: Permission): Permission
    fun deleteById(id: Long)
    fun findById(id: Long): Permission?
    fun findAllByPage(pageable: Pageable): Page<Permission>
}