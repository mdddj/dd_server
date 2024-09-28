package shop.itbug.ticket.config

import com.alibaba.fastjson2.JSONReader
import com.alibaba.fastjson2.JSONWriter
import com.alibaba.fastjson2.support.config.FastJsonConfig
import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter
import jakarta.annotation.Resource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.ResourceLoader
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import shop.itbug.ticket.annotation.AutomaticallyImportServerIp
import shop.itbug.ticket.comm.GetUserParameterHandling
import java.nio.charset.StandardCharsets
import java.util.*


/**
 * @author ldd
 */
@Configuration
class WebConfig : WebMvcConfigurationSupport() {

    @Resource
    private lateinit var getUserParameterHandling: GetUserParameterHandling

    @Resource private lateinit var automaticallyImportServerIp: AutomaticallyImportServerIp


    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        super.extendMessageConverters(converters)
        for (i in converters) {
            if (i is StringHttpMessageConverter) {
                i.defaultCharset = StandardCharsets.UTF_8
            }
        }
    }


    override fun configureAsyncSupport(configurer: AsyncSupportConfigurer) {
        super.configureAsyncSupport(configurer)
        configurer.setTaskExecutor(asyncExecutor())
    }

    private fun asyncExecutor(): AsyncTaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        executor.initialize()
        return executor
    }


    @Bean
    fun createResourceLoader(): ResourceLoader {
        return DefaultResourceLoader()
    }

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        super.addArgumentResolvers(argumentResolvers)
        argumentResolvers.add(getUserParameterHandling)
        argumentResolvers.add(automaticallyImportServerIp)
    }


    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        val converter = FastJsonHttpMessageConverter()
        converter.fastJsonConfig = getWriterConfig()
        converter.defaultCharset = StandardCharsets.UTF_8
        converter.supportedMediaTypes = Collections.singletonList(MediaType.APPLICATION_JSON)
        converters.add(0, converter)
        super.addDefaultHttpMessageConverters(converters)
        super.configureMessageConverters(converters)
    }


    companion object {
        fun getWriterConfig() : FastJsonConfig {
            val config = FastJsonConfig()
            config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean)
            config.setWriterFeatures(
                JSONWriter.Feature.WriteMapNullValue,
                JSONWriter.Feature.WriteNullBooleanAsFalse,
                JSONWriter.Feature.WriteNullListAsEmpty,
                JSONWriter.Feature.WriteNullStringAsEmpty,
                JSONWriter.Feature.WriteNulls,
                JSONWriter.Feature.WriteEnumUsingToString,
                JSONWriter.Feature.SortMapEntriesByKeys,
                JSONWriter.Feature.WriteNullStringAsEmpty,
                JSONWriter.Feature.ReferenceDetection
            )
            return config
        }
    }
}


