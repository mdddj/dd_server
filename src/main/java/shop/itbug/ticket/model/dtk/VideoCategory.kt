package shop.itbug.ticket.model.dtk

// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json          = Json { allowStructuredMapKeys = true }
// val videoCategory = json.parse(VideoCategory.serializer(), jsonString)


import kotlinx.serialization.Serializable


@Serializable
data class VideoCategory (
    val id: Long? = null,
    val name: String? = null
)