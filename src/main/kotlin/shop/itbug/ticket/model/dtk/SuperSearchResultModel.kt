package shop.itbug.ticket.model.dtk

// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json                   = Json { allowStructuredMapKeys = true }
// val superSearchResultModel = json.parse(SuperSearchResultModel.serializer(), jsonString)


import kotlinx.serialization.Serializable

@Serializable
data class SuperSearchResultModel (
    val bizSceneId: Long? = null,
    val brandList: List<SuperSearchBrandList>? = null,
    val list: List<DtaokeBaseProduct>? = null,
    val pageId: Long? = null,
    val totalNum: Long? = null,
    var len: Long? = null
)

@Serializable
data class SuperSearchBrandList (
    val brandId: String? = null,
    val brandLogo: String? = null,
    val brandName: String? = null
)

