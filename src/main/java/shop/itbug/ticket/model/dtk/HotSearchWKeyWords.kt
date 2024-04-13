package shop.itbug.ticket.model.dtk

import kotlinx.serialization.Serializable

@Serializable
data class HotSearchKeyWords (
    val hotWords: List<String>? = null
)


@Serializable
data class HotSearchKeyWordsItemElement (
    val hotValue: Long? = null,
    val label: String? = null,
    val pic: String? = null,
    val rankNum: Long? = null,
    val theme: String? = null,
    val wordDesc: String? = null,
    val words: String? = null
)
