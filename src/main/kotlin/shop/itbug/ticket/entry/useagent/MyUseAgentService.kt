package shop.itbug.ticket.entry.useagent

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface MyUseAgentService {
    fun findAll(): List<MyUseAgent>
    fun save(entity: MyUseAgent): MyUseAgent
    fun deleteById(id: Long)
    fun findById(id: Long): MyUseAgent?
    fun findAllByPage(pageable: Pageable): Page<MyUseAgent>
}