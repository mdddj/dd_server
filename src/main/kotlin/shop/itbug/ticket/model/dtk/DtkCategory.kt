package shop.itbug.ticket.model.dtk

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*


@Serializable
data class DtkCategory (
    val cid: Long? = null,
    val cname: String? = null,
    val cpic: String? = null,
    val subcategories: List<Subcategory>? = null
)

@Serializable
data class Subcategory (
    val scpic: String? = null,
    val subcid: Long? = null,
    val subcname: String? = null
)