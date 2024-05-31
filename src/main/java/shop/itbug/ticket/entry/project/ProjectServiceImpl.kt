package shop.itbug.ticket.entry.project

import jakarta.annotation.Resource
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getPageable
import shop.itbug.ticket.service.redis.RedisKeys


///
@Service
class ProjectServiceImpl : ProjectService {
    @Resource
    lateinit var projectRepository: ProjectRepository

    @Cacheable(RedisKeys.PROJECT+"all")
    override fun findAll(): List<Project> {
        return projectRepository.findAll()
    }


    @CacheEvict(RedisKeys.PROJECT+"all", allEntries = true)
    override fun save(entity: Project): Project {
        return projectRepository.save(entity)
    }


    @Caching(evict = [
        CacheEvict(RedisKeys.PROJECT+"all", allEntries = true),
        CacheEvict(RedisKeys.PROJECT+"findById", key = "#id"),
        CacheEvict(RedisKeys.PROJECT+"findByName", allEntries = true),
    ])
    override fun deleteById(id: Long) {
        projectRepository.deleteById(id)
    }

    @Cacheable(RedisKeys.PROJECT+"findById",key = "#id")
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

    @Cacheable(RedisKeys.PROJECT+"findByName",key = "#name")
    override fun findByName(name: String): Project? {
        return projectRepository.findByName(name)
    }
}