package shop.itbug.ticket.controller

import com.alibaba.fastjson2.JSON
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import shop.itbug.ticket.entry.directory.DirectoryDto
import shop.itbug.ticket.entry.directory.DirectoryService
import shop.itbug.ticket.entry.directory.DocDirectory
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.successResult


/**
 * @Author: 梁典典
 * @Date: 2024/09/28/上午9:43
 * @Description:
 */
@RestController
@RequestMapping("/api/public/directory")
@Tag(name = "文档目录开发控制器")
class DirectoryOpenController {

    @Resource
    private lateinit var directoryService: DirectoryService

    @GetMapping("/doc/list")
    @Operation(summary = "获取全部的已保存博客")
    fun findAllRoot(): R<List<DirectoryDto>>{
        val all =  directoryService.findAllRoot()
        return all.successResult()
    }


    @GetMapping("/doc/{name}")
    @Operation(summary = "查找某个root博客")
    fun findByName(@PathVariable(name = "name") name: String): R<DirectoryDto>{
        return directoryService.findRootByName(name).successResult()
    }
}