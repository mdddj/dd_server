package shop.itbug.ticket.entry.useagent

import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MyUseAgentServiceImpl : MyUseAgentService {
    @Resource
    lateinit var myuseagentRepository: MyUseAgentRepository
    override fun findAll(): List<MyUseAgent> {
        return myuseagentRepository.findAll()
    }

    override fun save(entity: MyUseAgent): MyUseAgent {
        return myuseagentRepository.save(entity)
    }

    override fun deleteById(id: Long) {
        myuseagentRepository.deleteById(id)
    }

    override fun findById(id: Long): MyUseAgent? {
        return myuseagentRepository.findByIdOrNull(id)
    }

    override fun findAllByPage(pageable: Pageable): Page<MyUseAgent> {
        return myuseagentRepository.findAll(pageable)
    }
}