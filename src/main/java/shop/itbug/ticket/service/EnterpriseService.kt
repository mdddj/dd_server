package shop.itbug.ticket.service

import jakarta.annotation.Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import shop.itbug.ticket.entry.Enterprise

@Repository
interface EnterpriseDao : JpaRepository<Enterprise, Long>

@Service
class EnterpriseService : BaseService<Enterprise>() {
    @Resource
    lateinit var enterpriseDao: EnterpriseDao
    override fun getDao(): JpaRepository<Enterprise, Long> {
        return enterpriseDao
    }

}