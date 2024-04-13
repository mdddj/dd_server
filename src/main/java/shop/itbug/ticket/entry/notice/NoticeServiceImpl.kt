package shop.itbug.ticket.entry.notice

import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NoticeServiceImpl : NoticeService {
    @Resource
    lateinit var noticeRepository: NoticeRepository
    override fun findAll(): List<Notice> {
        return noticeRepository.findAll()
    }

    override fun save(entity: Notice): Notice {
        return noticeRepository.save(entity)
    }

    override fun deleteById(id: Long) {
        noticeRepository.deleteById(id)
    }

    override fun findById(id: Long): Notice? {
        return noticeRepository.findByIdOrNull(id)
    }

    override fun findAllByPage(pageable: Pageable): Page<Notice> {
        return noticeRepository.findAll(pageable)
    }
}