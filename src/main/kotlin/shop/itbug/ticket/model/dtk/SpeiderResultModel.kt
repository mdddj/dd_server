package shop.itbug.ticket.model.dtk

// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json               = Json { allowStructuredMapKeys = true }
// val speiderResultModel = json.parse(SpeiderResultModel.serializer(), jsonString)


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeiderResultModel (
    val list: List<SpeiderListElement>? = null,
    val pageId: Long? = null,
    val totalNum: Long? = null
)

@Serializable
data class SpeiderListElement (
    val content: String? = null,
    val contentApp: String? = null,
    val couponUrl: String? = null,
    val createTime: String? = null,
    val img: String? = null,
    val itemId: String? = null,
    val itemUrl: String? = null,
    val msgId: String? = null,
    val remark: String? = null,
    val source: Source? = null,
    val sourceType: SourceType? = null,
    val tag: String? = null,
    val title: String? = null,
    val updateTime: String? = null
)

@Serializable
enum class Source(val value: String) {
    @SerialName("京东") Jd("京东"),
    @SerialName("天猫") Tm("天猫");
}

@Serializable
enum class SourceType(val value: String) {
    @SerialName("jd") Jd("jd"),
    @SerialName("tmall") Tmall("tmall");
}
