package shop.itbug.ticket.entry.project

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel

@Service
interface ProjectService {
    fun findAll(): List<Project>
    fun save(entity: Project): Project
    fun deleteById(id: Long)
    fun findById(id: Long): Project?
    fun findAllByPage(pageable: Pageable): Page<Project>
    fun findAllBy(pageModel: PageModel,project: Project?): Page<Project>
    fun findByName(name: String): Project?
}