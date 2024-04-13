package shop.itbug.ticket.config

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.support.spring6.data.redis.GenericFastJsonRedisSerializer
import jakarta.annotation.Resource
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration


/**
 * https://www.cnblogs.com/ludangxin/p/15139522.html
 * @author ldd
 */
@Configuration
open class JedisRedisConfig {

    @Resource
    lateinit var redisProperties: RedisProperties

    @Bean
    open fun connectionFactory(): RedisConnectionFactory {
        val redisConf = RedisStandaloneConfiguration()
        redisConf.hostName = redisProperties.host
        redisConf.port = redisProperties.port
        redisConf.setPassword(redisProperties.password)
        redisConf.database = 0
        return LettuceConnectionFactory(redisConf)
    }

    @Bean
    open fun cacheManager(connectionFactory: RedisConnectionFactory): RedisCacheManager {
        var redisCacheConfiguration =
            RedisCacheConfiguration.defaultCacheConfig()
        redisCacheConfiguration = redisCacheConfiguration.serializeKeysWith(
            SerializationPair
                .fromSerializer(StringRedisSerializer())
        ) // key的序列
        redisCacheConfiguration =
            redisCacheConfiguration.serializeValuesWith(SerializationPair.fromSerializer(GenericFastJsonRedisSerializer()))//value的序列化
        redisCacheConfiguration.configureKeyConverters {
            it.addConverterFactory(MyCovertFactory())
        }
        redisCacheConfiguration = redisCacheConfiguration.prefixCacheNameWith("__")
        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofMinutes(60))

        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(redisCacheConfiguration)
            .transactionAware()
            .build()
    }

    @Bean
    open fun redisTemplate(factory: RedisConnectionFactory): RedisTemplate<Any, Any> {
        val template: RedisTemplate<Any, Any> = RedisTemplate()
        template.connectionFactory = factory
        val myFastJsonRedisSerializer = GenericFastJsonRedisSerializer()
        val stringRedisSerializer = StringRedisSerializer()
        template.keySerializer = stringRedisSerializer
        template.hashKeySerializer = stringRedisSerializer
        template.valueSerializer = myFastJsonRedisSerializer
        template.hashValueSerializer = myFastJsonRedisSerializer
        template.setEnableTransactionSupport(true)
        template.afterPropertiesSet()
        return template
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