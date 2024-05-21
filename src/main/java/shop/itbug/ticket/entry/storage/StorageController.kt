package shop.itbug.ticket.entry.storage

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.service.FileInfoService
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.ResultJSON

@RestController
@RequestMapping("/api/storage")
@Tag(name = "存储模块")
class StorageController {
    @Resource
    private lateinit var storageServiceImpl: StorageServiceImpl
    @Resource
    private lateinit var fileInfoService: FileInfoService

    @PostMapping("/save")
    @Operation(summary = "新增存储策略")
    fun save(@RequestBody storage: Storage): R<Storage> {
        val save = storageServiceImpl.save(storage)
        return R.ok(save)
    }


    @DeleteMapping("/delete")
    @Operation(summary = "删除一个文件")
    fun remove(id: Long) : ResultJSON<*> {
        fileInfoService.deleteById(id)
        return shop.itbug.ticket.utils.Result.ok("删除成功")
    }

}