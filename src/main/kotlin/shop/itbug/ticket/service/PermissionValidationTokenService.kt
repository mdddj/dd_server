package shop.itbug.ticket.service

import org.springframework.stereotype.Service
import shop.itbug.ticket.entry.PermissionValidationToken

@Service
interface PermissionValidationTokenService {
    fun createAndUpdate(`object`: PermissionValidationToken): PermissionValidationToken
    fun selectByToken(token: String?): PermissionValidationToken?
    fun select(`object`: PermissionValidationToken): List<PermissionValidationToken>
}