package shop.itbug.ticket.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import shop.itbug.ticket.entry.FriendLink

@Repository
interface FriendLinkDao : JpaRepository<FriendLink, Long>