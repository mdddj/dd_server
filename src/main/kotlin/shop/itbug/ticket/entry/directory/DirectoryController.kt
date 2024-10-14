package shop.itbug.ticket.entry.directory

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/api/doc-directory")
@Tag(name = "markdown目录控制台(管理员)")
class DirectoryController {
    @Resource
    private lateinit var directoryService: DirectoryService

    @PostMapping("/save")
    @Operation(summary = "保存")
    fun createDirectory(@RequestBody directoryDto: DirectoryDto): R<DocDirectory> {
        return directoryService.saveDirectory(directoryDto).successResult("保存笔记成功")
    }


    @DeleteMapping("/{name}")
    @Operation(summary = "删除项目")
    fun deleteByName( @PathVariable(name = "name") name: String): R<Boolean> {
        directoryService.deleteByName(name)
        return R.ok(true,"删除成功")
    }




}