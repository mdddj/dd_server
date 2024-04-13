package shop.itbug.ticket.entry.project

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Lob

@Entity
@Schema(name = "项目")
class Project {

    @Id
    @GeneratedValue
    @Schema(description = "项目主键")
    var id: Long? = null

    @Schema(description = "项目名称")
    var name: String? = null

    @Schema(description = "项目介绍")
    @Lob
    var description: String? = null

    @Schema(description = "项目logo")
    var logo: String? = null

    @Schema(description = "源码仓库")
    var github: String? = null

    @Schema(description="下载地址")
    var downloadUrl: String? = null

    @Schema(description="预览地址")
    var previewUrl: String? = null

}