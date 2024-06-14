package shop.itbug.ticket.model

import cn.hutool.system.*
import cn.hutool.system.oshi.OshiUtil
import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable
import java.text.DecimalFormat


@Schema(name = "内存信息")
class MemoryInfo : Serializable {
    @Schema(description = "总内存")
    var total: Long = 0

    @Schema(description = "可用内存")
    var available: Long = 0

    @Schema(description = "已用内存")
    var use: Long = 0

    @Schema(description = "总内存格式化")
    var totalString: String? = null

    @Schema(description = "可用内存格式化")
    var availableString: String? = null

    @Schema(description = "已用内存格式化")
    var useString: String? = null

    @Schema(description = "使用率")
    var usageRate: Double? = null

    companion object {
        fun getMemoryInfo(): MemoryInfo {
            return MemoryInfo().apply {
                val m = OshiUtil.getMemory()
                total = m.total
                available = m.available
                use = total - available
                totalString = formatByte(total)
                availableString = formatByte(available)
                useString = formatByte(use)
                usageRate = (use * 1.0) / total
            }
        }


        fun formatByte(byteNumber: Long): String {
            val format = 1024.0
            val kbNumber = byteNumber / format
            if (kbNumber < format) {
                return DecimalFormat("#.##KB").format(kbNumber)
            }
            val mbNumber = kbNumber / format
            if (mbNumber < format) {
                return DecimalFormat("#.##MB").format(mbNumber)
            }
            val gbNumber = mbNumber / format
            if (gbNumber < format) {
                return DecimalFormat("#.##GB").format(gbNumber)
            }
            val tbNumber = gbNumber / format
            return DecimalFormat("#.##TB").format(tbNumber)
        }
    }
}

//系统信息
class SystemInfo : Serializable {
    var jvmSpecInfo: JvmSpecInfo? = null
    var jvmInfo: JvmInfo? = null
    var javaSpecInfo: JavaSpecInfo? = null
    var javaInfo: JavaInfo? = null
    var javaRuntimeInfo: JavaRuntimeInfo? = null
    var osInfo: OsInfo? = null
    var userInfo: UserInfo? = null
    var hostInfo: HostInfo? = null
    var runtimeInfo: RuntimeInfo? = null

    companion object {
        fun getSystemInfo(): SystemInfo {
            return SystemInfo().apply {
                jvmSpecInfo = SystemUtil.getJvmSpecInfo()
                jvmInfo = SystemUtil.getJvmInfo()
                javaSpecInfo = SystemUtil.getJavaSpecInfo()
                javaInfo = SystemUtil.getJavaInfo()
                javaRuntimeInfo = SystemUtil.getJavaRuntimeInfo()
                osInfo = SystemUtil.getOsInfo()
                userInfo = SystemUtil.getUserInfo()
                hostInfo = SystemUtil.getHostInfo()
                runtimeInfo = SystemUtil.getRuntimeInfo()
            }
        }

    }

}
