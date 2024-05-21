package shop.itbug.ticket.admin.controller

import cn.hutool.http.HttpUtil
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import shop.itbug.ticket.admin.model.PageModel
import shop.itbug.ticket.entry.blog.Text
import shop.itbug.ticket.service.blog.TextService
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.Result
import shop.itbug.ticket.utils.successResult

/**
 * @author eee
 */
@RestController
@RequestMapping("/api/text")
@Tag(name = "字典接口(管理员)")
class TextController {


    @Resource
    private lateinit var textService: TextService

    @GetMapping("/list")
    @Operation(summary = "获取字典列表")
    fun getList(pageModel: PageModel, text: Text?): Result<Page<Text>> {
        return textService.selectAll(pageModel, text).successResult("查询成功")
    }

    /**
     * 获取flutter的插件
     * @param name 插件名字
     * @return  json
     */
    @GetMapping("/flutter-flugin")
    @Operation(summary = "获取flutter插件信息")
    fun getFlutterPlugin(@Parameter(name = "插件名字") name: String): Result<String> {
        val s = HttpUtil.get("https://pub.dartlang.org/api/packages/$name")
        return s.successResult()
    }


    @PostMapping("/save")
    @Operation(summary = "添加一个字典,或者修改一个字典")
    fun save(@RequestBody text: Text): Result<Text> {
        return textService.save(text).successResult(if (text.id != null) "修改成功" else "添加成功")
    }


    @DeleteMapping("/delete")
    @Operation(summary = "删除某个字典")
    fun delete(id: Long): R<Boolean> {
        textService.deleteById(id)
        return true.successResult("删除成功")
    }
}