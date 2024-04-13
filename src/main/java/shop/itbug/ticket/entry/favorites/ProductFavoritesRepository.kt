package shop.itbug.ticket.entry.favorites

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import shop.itbug.ticket.entry.User

@Repository
interface ProductFavoritesRepository: JpaRepository<ProductFavorites,Long> {

    fun findAllByUserAndId(user: User,id: Long): List<ProductFavorites>

    fun findAllByUser(user: User,pageable: Pageable): Page<ProductFavorites>
}