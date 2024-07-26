package shop.itbug.ticket.service.config

import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.CharsetUtil
import cn.hutool.setting.Setting
import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import com.alibaba.fastjson2.toJSONString
import jakarta.annotation.Resource
import org.springframework.beans.factory.InitializingBean
import shop.itbug.ticket.config.DdServerConfig
import shop.itbug.ticket.ex.log
import shop.itbug.ticket.service.redis.RedisUtil
import java.io.File
import java.io.Serializable
import kotlin.reflect.KClass

private fun <T : Any> Setting.setByAny(model: T) {
    val json = JSON.parseObject(model.toJSONString())
    json.forEach { t, u ->
        run {
            set(t, "$u")
        }
    }
}

abstract class ConfigBase<T : Serializable>(val type: KClass<T>) : InitializingBean {



    @Resource
    private lateinit var ddServerConfig: DdServerConfig

    @Resource
    private lateinit var redisUtil: RedisUtil<T>

    ///文件名字
    abstract  val configName: String

    ///默认值
    abstract  val defaultModel: T

    lateinit var setting: T

    override fun afterPropertiesSet() {
        setting = getModel()
    }

    /**
     * 读取配置,生成对应的模型
     */
    private fun getModel(): T = run {
        val j = JSONObject()
        getSetting().toProperties().apply {
            this.forEach { t, u ->
                j["$t"] = u
            }
        }
        val model = JSON.to(type.java, j)
//        log().info("获取配置:" + model.toJSONString())
        redisUtil.setCacheObject(configName,model)
        model
    }

    /**
     * 修改配置
     */
    fun updateSetting(model: T) : T {
        getSetting().apply {
            setByAny(model)
            store()
            setting = model
            redisUtil.setCacheObject(configName,setting)
        }
        return setting
    }


    private fun getSetting(): Setting {
        val setting = Setting(FileUtil.touch(createFile()), CharsetUtil.CHARSET_UTF_8, true)
        return setting
    }

    private fun createFile(): File {
        val file = File(ddServerConfig.getRootDirFileFun().absolutePath + File.separator + configName + ".setting")
        if (file.exists().not()) {
            file.createNewFile()
            getSetting().apply {
                setByAny(defaultModel)
                redisUtil.setCacheObject(configName,defaultModel)
                store()
            }

        }
        return file
    }



}