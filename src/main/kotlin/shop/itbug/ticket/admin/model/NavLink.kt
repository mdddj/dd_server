package shop.itbug.ticket.admin.model


/**
 * @author eee
 */
class NavLink : Comparable<NavLink> {
    var id: Long? = null
    var name: String? = null

    override fun compareTo(other: NavLink): Int {
        return if(id!! > other.id!!) 0 else 1
    }

}