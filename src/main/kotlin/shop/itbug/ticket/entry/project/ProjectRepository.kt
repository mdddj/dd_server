package shop.itbug.ticket.entry.project

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : JpaRepository<Project, Long> {
    fun findByName(name: String): Project?
}