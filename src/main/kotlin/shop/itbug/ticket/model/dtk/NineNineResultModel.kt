package shop.itbug.ticket.model.dtk



import kotlinx.serialization.Serializable

@Serializable
data class NineNineResultModel (
    val list: List<DtaokeBaseProduct>? = null,
    val pageId: String? = null,
    val totalNum: Long? = null
)
