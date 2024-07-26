package shop.itbug.ticket.entry.permission

import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PermissionServiceImpl : PermissionService {
    @Resource
    lateinit var permissionRepository: PermissionRepository
    override fun findAll(): List<Permission> {
        return permissionRepository.findAll()
    }

    override fun save(entity: Permission): Permission {
        return permissionRepository.save(entity)
    }

    override fun deleteById(id: Long) {
        permissionRepository.deleteById(id)
    }

    override fun findById(id: Long): Permission? {
        return permissionRepository.findByIdOrNull(id)
    }

    override fun findAllByPage(pageable: Pageable): Page<Permission> {
        return permissionRepository.findAll(pageable)
    }
}