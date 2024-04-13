package shop.itbug.ticket.dao

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import shop.itbug.ticket.entry.User

@Repository
@Transactional
interface UserDao : JpaRepository<User, Long> {
    fun findByLoginNumber(loginNumber: String?): User?
    fun findByPhone(phone: String): User?
    fun findAllByType(type: Int): List<User>
    fun findByEmailAndStatus(email: String,status: Int): User?
    fun findByEmailAndActiveCode(email:String,code: String): User?
    fun findByEmail(email: String): User?
    fun findByRelationId(relationId: String): User?
}