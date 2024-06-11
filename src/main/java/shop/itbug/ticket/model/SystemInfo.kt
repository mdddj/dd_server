package shop.itbug.ticket.model

import cn.hutool.system.HostInfo
import cn.hutool.system.JavaInfo
import cn.hutool.system.JavaRuntimeInfo
import cn.hutool.system.JavaSpecInfo
import cn.hutool.system.JvmInfo
import cn.hutool.system.JvmSpecInfo
import cn.hutool.system.OsInfo
import cn.hutool.system.RuntimeInfo
import cn.hutool.system.SystemUtil
import cn.hutool.system.UserInfo
import cn.hutool.system.oshi.OshiUtil
import java.io.Serializable


//内存信息
class MemoryInfo : Serializable {
    //总内存
    var total: Long = 0
    //可用内存
    var available: Long = 0

    companion object {
        fun getMemoryInfo(): MemoryInfo {
            return MemoryInfo().apply {
               val m = OshiUtil.getMemory()
                total = m.total
                available = m.available
            }
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
         fun getSystemInfo() : SystemInfo {
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
