package shop.itbug.ticket.entry.report

import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.admin.model.getPageable
import shop.itbug.ticket.entry.User

@Service
class ReportServiceImpl : ReportService {
    @Resource
    lateinit var reportRepository: ReportRepository
    override fun findAll(): List<Report> {
        return reportRepository.findAll()
    }

    override fun save(entity: Report): Report {
        return reportRepository.save(entity)
    }

    override fun deleteById(id: Long) {
        reportRepository.deleteById(id)
    }

    override fun findById(id: Long): Report? {
        return reportRepository.findByIdOrNull(id)
    }

    override fun findAllByPage(pageModel: PageModel): Page<Report> {
        return reportRepository.findAll(pageModel.getPageable())
    }

    override fun findAllByUser(pageModel: PageModel, user: User): Page<Report> {
        return reportRepository.findAllByUser(user,pageModel.getPageable("createTime"))
    }
}