package shop.itbug.ticket.model.dtk
// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json                   = Json { allowStructuredMapKeys = true }
// val productListResultModel = json.parse(ProductListResultModel.serializer(), jsonString)


import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

@Serializable
data class ProductListResultModel (
    val bizSceneId: Long? = null,
    val list: List<DtaokeBaseProduct>? = null,
    val pageId: String? = null,
    val totalNum: Long? = null
)
