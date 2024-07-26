package shop.itbug.ticket.entry.report

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.User

@Service
interface ReportService {
    fun findAll(): List<Report>
    fun save(entity: Report): Report
    fun deleteById(id: Long)
    fun findById(id: Long): Report?
    fun findAllByPage(pageModel: PageModel): Page<Report>
    fun findAllByUser(pageModel: PageModel, user: User): Page<Report>
}