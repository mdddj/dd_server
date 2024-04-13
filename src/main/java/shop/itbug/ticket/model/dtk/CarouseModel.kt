package shop.itbug.ticket.model.dtk


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TkCarousel (
    @SerialName("activityId")
    val activityID: String,

    val link: String,
    val sourceType: Long,

    @SerialName("topicId")
    val topicID: Long,

    val topicImage: String,
    val topicName: String
)
