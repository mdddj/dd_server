package shop.itbug.ticket.model.params

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.annotation.JSONField
import com.alibaba.fastjson2.toJSONString
import io.swagger.v3.oas.annotations.media.Schema
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


fun <T: Any> T.toMutableMap() : MutableMap<String,Any> {
    val jsonString = this.toJSONString()
    val map = JSON.parseObject(jsonString,MutableMap::class.java)
    val result = mutableMapOf<String,Any>()
    map.forEach { (any, any2) ->
        run {
            if (any != null && any2 != null) {
                result[any.toString()] = any2
            }
        }
    }
    return result
}

@Schema(name = "查询渠道会员信息接口")
data class OpenScPublisherGetParam(
    @Schema(description = "渠道推广的物料类型，示例值：common", example = "common")
    @JSONField(name = "relation_app")
    val relationApp : String? = null,

    @Schema(description = "类型，必选 1:渠道信息；2:会员信息", example = "2")
    @JSONField(name = "info_type")
    val infoType : Int,

    @JSONField(name = "relation_id")
    @Schema(description = "渠道备案 - 渠道关系ID", example = "")
    val relationId: String? = null,

    @JSONField(name = "special_id")
    @Schema(description = "会员运营ID", example = "")
    val specialId: String? = null,

    @JSONField(name = "external_id")
    @Schema(description = "淘宝客外部用户标记，如自身系统账户ID；微信ID等", example = "")
    val externalId : String ? = null,

    @JSONField(name = "page_no")
    @Schema(description = "第几页，注意，页数从0开始", example = "0")
    val pageNo : Int = 0,

    @JSONField(name = "page_size")
    @Schema(description = "每页大小", example = "100")
    val pageSize : Int = 1000
)



@Serializable
data class ScResult (
    @SerialName("tbk_sc_publisher_info_get_response")
    val tbkScPublisherInfoGetResponse: TbkScPublisherInfoGetResponse
)

@Serializable
data class TbkScPublisherInfoGetResponse (
    val data: TbkScPublisherInfoGetResponseData,

    @SerialName("request_id")
    val requestID: String
)

@Serializable
data class TbkScPublisherInfoGetResponseData (
    @SerialName("inviter_list")
    val inviterList: TbkScPublisherInfoGetResponseInviterList,

    @SerialName("root_pid_channel_list")
    val rootPIDChannelList: TbkScPublisherInfoGetResponseRootPIDChannelList,

    @SerialName("total_count")
    val totalCount: Long
)

@Serializable
data class TbkScPublisherInfoGetResponseInviterList (
    @SerialName("map_data")
    val mapData: List<TbkScPublisherInfoGetResponseMapDatum>
)

@Serializable
data class TbkScPublisherInfoGetResponseMapDatum (
    @SerialName("account_name")
    val accountName: String,

    @SerialName("create_date")
    val createDate: String,

    val note: String,

    @SerialName("offline_scene")
    val offlineScene: String,

    @SerialName("online_scene")
    val onlineScene: String,

    @SerialName("punish_status")
    val punishStatus: String,

    @SerialName("real_name")
    val realName: String,

    @SerialName("relation_app")
    val relationApp: String,

    @SerialName("relation_id")
    val relationID: Long,

    @SerialName("root_pid")
    val rootPID: String,

    val rtag: String? = null
)

@Serializable
data class TbkScPublisherInfoGetResponseRootPIDChannelList (
    val string: List<String>
)