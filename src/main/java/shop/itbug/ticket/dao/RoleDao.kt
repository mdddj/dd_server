package shop.itbug.ticket.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import shop.itbug.ticket.entry.Role

@Repository
interface RoleDao : JpaRepository<Role, Long> {
    fun findRoleByName(name: String?): Role?
}