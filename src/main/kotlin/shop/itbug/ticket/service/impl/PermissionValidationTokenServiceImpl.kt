package shop.itbug.ticket.service.impl

import jakarta.annotation.Resource
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import shop.itbug.ticket.dao.PermissionValidationTokenDao
import shop.itbug.ticket.entry.PermissionValidationToken
import shop.itbug.ticket.service.PermissionValidationTokenService

@Service
class PermissionValidationTokenServiceImpl : PermissionValidationTokenService {
    @Resource
    private val permissionValidationTokenDao: PermissionValidationTokenDao? = null
    override fun createAndUpdate(`object`: PermissionValidationToken): PermissionValidationToken {
        val select = select(`object`)
        return if (select.size == 0) {
            permissionValidationTokenDao!!.save(`object`)
        } else select[0]
    }

    override fun selectByToken(token: String?): PermissionValidationToken? {
        return permissionValidationTokenDao!!.findPermissionValidationTokenByToken(token)
    }

    override fun select(`object`: PermissionValidationToken): List<PermissionValidationToken> {
        return permissionValidationTokenDao!!.findAll(Example.of(`object`))
    }
}