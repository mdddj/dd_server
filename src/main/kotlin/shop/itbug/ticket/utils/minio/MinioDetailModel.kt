package shop.itbug.ticket.utils.minio

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable

@Schema(name = "minio配置模型")
class MinioDetailModel(

    @Schema(description = "api端点", example = "http://127.0.0.1:9001")
    var endpoint: String = "",
    @Schema(description = "accessKey", example = "KqQqZZeNuAvddVyp4U6E")
    var accessKey: String = "",
    @Schema(description = "secretKey", example = "UfAcC6phfBZ7t1o3y1Gb8HHWGINdOa7jPEDo8Ukr")
    var secretKey: String = "",
    @Schema(description = "bucketName")
    var bucketName: String = ""
) : Serializable