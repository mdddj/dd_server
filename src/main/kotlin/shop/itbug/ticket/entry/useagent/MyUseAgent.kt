package shop.itbug.ticket.entry.useagent

import cn.hutool.http.useragent.UserAgentUtil
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.servlet.http.HttpServletRequest



@Entity
@Tag(name = "引擎")
@Schema(name = "浏览器引擎")
class MyUseAgent {

    @Id
    @GeneratedValue
    val id: Long? = null

    @Schema(description = "浏览器")
    var browser: String? = null

    @Schema(description = "版本")
    var version: String? = null

    @Schema(description = "引擎名称")
    var engine: String? = null

    @Schema(description = "引擎版本")
    var engineVersion: String? = null

    @Schema(description = "系统名称")
    var osName: String? = null

    @Schema(description = "系统平台")
    var platform: String? = null

    @Schema(description = "是否为移动终端")
    var isMobile: Boolean = false


    companion object {

        fun getFromRequest(request: HttpServletRequest): MyUseAgent? {
            val usa: String = request.getHeader("User-Agent") ?: return null
            val parse = UserAgentUtil.parse(usa)
            return MyUseAgent().apply {
                browser = parse.browser.toString()
                version = parse.version
                engine = parse.engine.toString()
                engineVersion = parse.engineVersion
                osName = parse.os.toString()
                platform = parse.platform.toString()
                isMobile = parse.isMobile
            }
        }
    }
}


fun HttpServletRequest.myUseAgent(): MyUseAgent? {
    return MyUseAgent.getFromRequest(this)
}