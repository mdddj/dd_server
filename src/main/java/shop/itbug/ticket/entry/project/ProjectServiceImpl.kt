package shop.itbug.ticket.entry.project

import jakarta.annotation.Resource
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getPageable


///
@Service
class ProjectServiceImpl : ProjectService {
    @Resource
    lateinit var projectRepository: ProjectRepository
    override fun findAll(): List<Project> {
        return projectRepository.findAll()
    }

    override fun save(entity: Project): Project {
        return projectRepository.save(entity)
    }

    override fun deleteById(id: Long) {
        projectRepository.deleteById(id)
    }

    override fun findById(id: Long): Project? {
        return projectRepository.findByIdOrNull(id)
    }

    override fun findAllByPage(pageable: Pageable): Page<Project> {
        return projectRepository.findAll(pageable)
    }

    override fun findAllBy(pageModel: PageModel, project: Project?): Page<Project> {
        return project?.let {
            projectRepository.findAll(Example.of(it), pageModel.getPageable())
        } ?: projectRepository.findAll(pageModel.getPageable())
    }

    override fun findByName(name: String): Project? {
        return projectRepository.findByName(name)
    }
}