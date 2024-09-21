package shop.itbug.ticket.model.blog

/**
 * @author eee
 */

data class PushNewBlogParams(
    val title: String,
    val content: String,
    val tags: Set<String> = setOf(),
    val categoryId: Long,
    val alias: String = "",
    val thumbnail: String = "",
    val id: Long? = null,
    val html: String = ""
)
