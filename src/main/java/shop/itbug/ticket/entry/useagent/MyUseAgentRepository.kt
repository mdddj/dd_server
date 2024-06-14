package shop.itbug.ticket.entry.useagent

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MyUseAgentRepository : JpaRepository<MyUseAgent, Long> {
}