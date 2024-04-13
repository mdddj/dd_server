package shop.itbug.ticket.service.impl

import jakarta.annotation.Resource
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import shop.itbug.ticket.dao.UserDao

@Component
class SpringSecurityUserDetailsService: UserDetailsService {

    @Resource
    private lateinit var userDao: UserDao

    override fun loadUserByUsername(username: String): UserDetails {
        return userDao.findByLoginNumber(username) ?: throw UsernameNotFoundException("账号错误")
    }
}