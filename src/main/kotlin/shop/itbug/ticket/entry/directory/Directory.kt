package shop.itbug.ticket.entry.directory

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

import jakarta.persistence.*
import java.io.Serializable
import java.util.*

@Entity
@Table(name = "directory")
@Schema(name = "文档目录")
class DocDirectory : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    var parent: DocDirectory? = null

    @Column(name = "name", nullable = false)
    @Schema(description = "名称")
    var name: String = ""

    @OneToMany(mappedBy = "parent", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @Schema(description = "下面的子目录")
    var children: List<DocDirectory> = emptyList()

    @OneToMany(mappedBy = "directory", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @Schema(description = "下面的md文件")
    var files: List<MarkdownFile> = emptyList()

    @Schema(description = "创建时间")
    var createDate: Date = Date()

    @Schema(description = "介绍")
    var introduce: String? = null
}


@Entity
@Table(name = "markdown_file")
@Schema(name = "md文件实体")
class MarkdownFile : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "directory_id", nullable = true)
    @Schema(description = "所属目录")
    var directory: DocDirectory? = null

    @Column(name = "name", nullable = false)
    @Schema(description = "名称")
    var name: String = ""

    @Schema(description = "文件名")
    var filename: String? = null

    @Lob
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    @Schema(description = "正文内容")
    var content: String = ""

    @Schema(description = "文件创建时间")
    var createDate: Date = Date()

    companion object {
        fun create(name: String, content: String, directory: DocDirectory,filename: String): MarkdownFile {
            return MarkdownFile().apply {
                this.name = name
                this.content = content
                this.directory = directory
                this.filename = filename
            }
        }
    }
}

data class DirectoryDto(
    val name: String,
    val introduce: String? = null,
    val children: List<DirectoryDto> = emptyList(),
    val files: List<MarkdownFileDto> = emptyList()
)

data class MarkdownFileDto(
    val name: String,
    val content: String,
    val filename: String = ""
)