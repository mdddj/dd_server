package shop.itbug.ticket.admin.doc

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.entry.directory.DirectoryDto
import shop.itbug.ticket.entry.directory.DirectoryService
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/api/admin/doc")
@Tag(name = "idea文档")
class DocAdminController {

    @Resource
    private lateinit var directoryService: DirectoryService

    @DeleteMapping("/{name}")
    @Operation(summary = "删除某个文档")
    fun deleteById(@PathVariable("name") name: String): R<Boolean> {
        directoryService.deleteByName(name)
        return R.ok(true, "删除成功")
    }


    @GetMapping("/all")
    @Operation(summary = "全部文档")
    fun findAll(): R<List<DirectoryDto>> {
        return directoryService.findAllRoot().successResult("获取成功")
    }

}