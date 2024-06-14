package shop.itbug.ticket.admin.controller

import cn.hutool.core.thread.ThreadUtil
import com.alibaba.fastjson2.toJSONString
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import shop.itbug.ticket.ex.log
import shop.itbug.ticket.model.MemoryInfo
import shop.itbug.ticket.model.SystemInfo
import shop.itbug.ticket.utils.R
import shop.itbug.ticket.utils.successResult

@RestController
@RequestMapping("/system/info")
@Tag(name = "系统信息接口")
class SystemInfoController {

    @GetMapping("/memory")
    @Operation(summary = "获取服务器内存占用", description = "每秒更新")
    @Deprecated("有问题,弃用,请使用websocket连接")
    fun getSystemMemoryInfo(): SseEmitter {
        val sseEmitter = SseEmitter(60000L)
        var isOk = true
        sseEmitter.onTimeout {
            log().warn("on time out...")
            isOk = false
            sseEmitter.complete()
        }
        sseEmitter.onCompletion {
            log().warn("on completion ....")
            isOk = false
        }
        ThreadUtil.execAsync({
            log().info("exec async ...${isOk}")
            while (isOk) {
                try {
                    val info = MemoryInfo.getMemoryInfo()
                    sseEmitter.send(SseEmitter.event().reconnectTime(1000).name("Memory").data(info.toJSONString()))
                    Thread.sleep(1000)
                } catch (e: Exception) {
                    sseEmitter.completeWithError(e)
                }
            }
            log().info("线程结束////")
        },true)
        return sseEmitter
    }

    @GetMapping("/get")
    @Operation(summary = "获取系统信息")
    fun getSystemInfo(): R<SystemInfo> {
        return SystemInfo.getSystemInfo().successResult()
    }

    @GetMapping("/memory-get")
    @Operation(summary = "获取内存占用信息")
    fun getMemoryInfo(): R<MemoryInfo> {
        return MemoryInfo.getMemoryInfo().successResult()
    }
}