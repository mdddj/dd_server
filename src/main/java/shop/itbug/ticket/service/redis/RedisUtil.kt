package shop.itbug.ticket.service.redis

import jakarta.annotation.Resource
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.concurrent.TimeUnit

/**
 * spring redis 工具类
 * @author ldd
 */
@Component
class RedisUtil<T : Serializable> {


    @Resource
    lateinit var redisTemplate: RedisTemplate<String,T>

    /**
     * 缓存基本的对象，Integer、String、实体类等
     * @param key 缓存的键值
     * @param value 缓存的值
     * @return 缓存的对象
     */
    fun setCacheObject(key: String, value: T): ValueOperations<String, T> {
        val operation: ValueOperations<String, T> = redisTemplate.opsForValue()
        operation[key] = value
        return operation
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     * @return 缓存的对象
     */
    fun setCacheObject(key: String, value: T, timeout: Long, timeUnit: TimeUnit): ValueOperations<String, T> {
        val operation = redisTemplate.opsForValue()
        operation.set(key, value, timeout, timeUnit)
        return operation
    }


    /**
     * 删除单个对象
     * @param key
     */
    fun deleteObject(key: String) {
        redisTemplate.delete(key)
    }

    /**
     * 删除集合对象
     * @param collection
     */
    fun deleteObject(collection: Collection<String>) {
        redisTemplate.delete(collection)
    }





    /**
     * 缓存Map
     * @param key
     * @param dataMap
     * @return
     */
    fun <T> setCacheMap(key: String, dataMap: Map<String, T & Any>): HashOperations<String, String, T> {
        val hashOperations: HashOperations<String, String, T> = redisTemplate.opsForHash()
            for ((key1, value) in dataMap) {
                hashOperations.put(key, key1, value)
            }
        return hashOperations
    }

    /**
     * 获得缓存的Map
     * @param key
     * @return
     */
    fun <T> getCacheMap(key: String): Map<String, T> {
        val map = redisTemplate.opsForHash<String,T>().entries(key)
        return map
    }

    /**
     * 获得缓存的基本对象列表
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    fun keys(pattern: String): Collection<String> {
        return redisTemplate.keys(pattern)
    }
}
