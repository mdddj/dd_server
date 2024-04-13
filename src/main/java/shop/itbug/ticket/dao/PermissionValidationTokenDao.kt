package shop.itbug.ticket.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import shop.itbug.ticket.entry.PermissionValidationToken

@Repository
interface PermissionValidationTokenDao : JpaRepository<PermissionValidationToken, Long>,
    JpaSpecificationExecutor<PermissionValidationToken?> {
    fun findPermissionValidationTokenByToken(token: String?): PermissionValidationToken?
}