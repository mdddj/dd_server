package shop.itbug.ticket.config

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONWriter
import com.alibaba.fastjson2.filter.PropertyPreFilter
import com.alibaba.fastjson2.support.spring6.data.redis.GenericFastJsonRedisSerializer
import com.alibaba.fastjson2.toJSONString
import jakarta.annotation.Resource
import org.apache.commons.lang3.StringUtils
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory
import org.springframework.data.redis.cache.RedisCache
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.cache.RedisCacheWriter
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import org.springframework.data.redis.serializer.StringRedisSerializer
import shop.itbug.ticket.ex.log
import java.time.Duration
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


/**
 * https://www.cnblogs.com/ludangxin/p/15139522.html
 * @author ldd
 */
@Configuration
class JedisRedisConfig {

    @Resource
    lateinit var redisProperties: RedisProperties

    @Bean
    fun connectionFactory(): RedisConnectionFactory {
        val redisConf = RedisStandaloneConfiguration()
        redisConf.hostName = redisProperties.host
        redisConf.port = redisProperties.port
        redisConf.setPassword(redisProperties.password)
        redisConf.database = 0
        return LettuceConnectionFactory(redisConf)
    }

    @Bean
    fun cacheManager(connectionFactory: RedisConnectionFactory): RedisCacheManager {
        var redisCacheConfiguration =
            RedisCacheConfiguration.defaultCacheConfig()
        redisCacheConfiguration = redisCacheConfiguration.serializeKeysWith(
            SerializationPair
                .fromSerializer(MyStringKeySerializer())
        ) // key的序列
        redisCacheConfiguration =
            redisCacheConfiguration.serializeValuesWith(SerializationPair.fromSerializer(GenericFastJsonRedisSerializer()))//value的序列化
         redisCacheConfiguration.configureKeyConverters {
            it.addConverterFactory(MyCovertFactory())
        }
        redisCacheConfiguration.addCacheKeyConverter(object : Converter<Any,String> {
            override fun convert(source: Any): String? {
                return source.toJSONString(IgnoreEmptyStringPropertyFilter())
            }

        })
        redisCacheConfiguration = redisCacheConfiguration.prefixCacheNameWith("__")
//        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofMinutes(60))
        return CustomRedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory),redisCacheConfiguration)
    }

    @Bean
    fun redisTemplate(factory: RedisConnectionFactory): RedisTemplate<Any, Any> {
        val template: RedisTemplate<Any, Any> = RedisTemplate()
        template.connectionFactory = factory
        val myFastJsonRedisSerializer = GenericFastJsonRedisSerializer()
        val stringRedisSerializer = MyStringKeySerializer()
        template.keySerializer = stringRedisSerializer
        template.hashKeySerializer = stringRedisSerializer
        template.valueSerializer = myFastJsonRedisSerializer
        template.hashValueSerializer = myFastJsonRedisSerializer
        template.setEnableTransactionSupport(true)
        template.afterPropertiesSet()
        return template
    }


}


class CustomRedisCacheManager(writer: RedisCacheWriter, private val redisConfig: RedisCacheConfiguration): RedisCacheManager(writer,redisConfig) {
    override fun createRedisCache(name: String, cacheConfiguration: RedisCacheConfiguration?): RedisCache {
        if (StringUtils.isNotEmpty(name) && name.contains("#")) {
            val split: List<String> = name.split("#")
            // 缓存键名
            val cacheName = split[0]
            // "#"后第一位是时间
            val expire = split[1].toInt()
            // 过期时间，默认为h(小时)
            var duration = Duration.ofHours(expire.toLong())
            // 根据"#"后第二位字符判断过期时间的单位，设置相应的过期时间，默认时间单位是h(小时)
            if (split.size == 3) {
                duration = when (split[2]) {
                    "d" -> Duration.ofDays(expire.toLong())
                    "m" -> Duration.ofMinutes(expire.toLong())
                    "s" -> Duration.ofSeconds(expire.toLong())
                    else -> Duration.ofHours(expire.toLong())
                }
            }
            return super.createRedisCache(cacheName, redisConfig.entryTtl(duration))
        }
        return super.createRedisCache(name, cacheConfiguration)
    }

    override fun isTransactionAware(): Boolean {
        return true
    }
}


class MyStringKeySerializer : StringRedisSerializer(){
    override fun serialize(value: String?): ByteArray {
        return super.serialize(value)
    }

    override fun deserialize(bytes: ByteArray?): String {
        return super.deserialize(bytes)
    }
}

class MyCovertFactory : ConverterFactory<Any, Any> {
    override fun <T : Any?> getConverter(targetType: Class<T>): Converter<Any, T> {
        return MyConverter(targetType)
    }

    private inner class MyConverter<T>(val targetType: Class<T>) : Converter<Any, T> {
        override fun convert(source: Any): T? {
            val r = JSON.to(targetType, source)
            return r
        }
    }

}

class IgnoreEmptyStringPropertyFilter : PropertyPreFilter {
    override fun process(writer: JSONWriter?, source: Any?, name: String?): Boolean {
        if(source != null) {
            val v = name?.let { source::class.java.getDeclaredField(it) }
            v?.isAccessible = true
            val value = v?.get(source)
            if(value is String && value.isBlank()) {
                return false
            }
        }

        return true
    }
}