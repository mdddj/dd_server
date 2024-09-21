package shop.itbug.ticket.entry.share

import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ShareServiceImpl : ShareService {
    @Resource
    lateinit var shareRepository: ShareRepository
    override fun findAll(): List<Share> {
        return shareRepository.findAll()
    }

    override fun save(entity: Share): Share {
        return shareRepository.save(entity)
    }

    override fun deleteById(id: Long) {
        shareRepository.deleteById(id)
    }

    override fun findById(id: Long): Share? {
        return shareRepository.findByIdOrNull(id)
    }

    override fun findAllByPage(pageable: Pageable): Page<Share> {
        return shareRepository.findAll(pageable)
    }
}