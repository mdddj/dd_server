package shop.itbug.ticket.model.blog


open class SimplePage {
    open var total: Long? = null
    open var currentPage = 0
    open var pageSize = 0
    open var maxPage = 0
    open var hasPrevious = false
    open var isPaged = false
}