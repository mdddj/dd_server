package shop.itbug.ticket.service.config

import org.springframework.stereotype.Service
import shop.itbug.ticket.utils.minio.MinioDetailModel

@Service
class MinioConfig: ConfigBase<MinioDetailModel>(MinioDetailModel::class) {

    override val configName: String
        get() = "minio_config"
    override val defaultModel: MinioDetailModel
        get() = MinioDetailModel()
}