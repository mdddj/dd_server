package shop.itbug.ticket.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import shop.itbug.ticket.entry.Carousel

@Repository
interface CarouselDao : JpaRepository<Carousel, Long> {
    fun findAllByLocation(location: String?): List<Carousel?>?
}