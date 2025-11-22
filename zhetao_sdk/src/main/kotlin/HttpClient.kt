import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.Interceptor
import okhttp3.Response


/**
 * 基于Ktor和Kotlin协程的HTTP客户端封装
 */
abstract class ZheHttpClient(
    private val baseUrl: String = "",
    private val config: HttpClientConfig<*>.() -> Unit = {}
) : Interceptor {

    constructor(config: HttpClientConfig<*>.() -> Unit = {}) : this(DEFAULT_BASE_URL, config)

    val client: HttpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        engine {
            addInterceptor(this@ZheHttpClient)
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        config.invoke(this)

    }

    /**
     * 发送GET请求
     *
     * @param url 请求的URL
     * @param headers 请求头
     * @param parameters 查询参数
     * @return 响应体的字符串形式
     */
    suspend fun get(
        url: String,
        headers: Map<String, String> = emptyMap(),
        parameters: Map<String, String> = emptyMap()
    ): String {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = client.get(buildUrl(url)) {
                headers.forEach { (key, value) ->
                    header(key, value)
                }
                parameters.forEach { (key, value) ->
                    parameter(key, value)
                }
            }
            response.bodyAsText()
        }
    }

    /**
     * 发送GET请求并解析为指定类型
     *
     * @param url 请求的URL
     * @param headers 请求头
     * @param parameters 查询参数
     * @return 解析后的对象
     */
    suspend inline fun <reified T> getForObject(
        url: String,
        headers: Map<String, String> = emptyMap(),
        parameters: Map<String, String> = emptyMap()
    ): T {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = client.get(buildUrl(url)) {
                headers.forEach { (key, value) ->
                    header(key, value)
                }
                parameters.forEach { (key, value) ->
                    parameter(key, value)
                }
            }
            response.body()
        }
    }

    /**
     * 发送POST请求
     *
     * @param url 请求的URL
     * @param headers 请求头
     * @param body 请求体
     * @param contentType 内容类型
     * @return 响应体的字符串形式
     */
    suspend fun post(
        url: String,
        headers: Map<String, String> = emptyMap(),
        body: Any? = null,
        contentType: ContentType = ContentType.Application.Json
    ): String {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = client.post(buildUrl(url)) {
                headers.forEach { (key, value) ->
                    header(key, value)
                }
                contentType(contentType)
                if (body != null) {
                    setBody(body)
                }
            }
            response.bodyAsText()
        }
    }

    /**
     * 发送POST请求并解析为指定类型
     *
     * @param url 请求的URL
     * @param headers 请求头
     * @param body 请求参数（将作为查询参数附加到URL上）
     * @param contentType 内容类型
     * @return 解析后的对象
     */
    suspend inline fun <reified T> postForObject(
        url: String,
        body: ZheAttribute? = null,
        headers: Map<String, String> = emptyMap(),
        contentType: ContentType = ContentType.Application.Json
    ): T {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = client.post(buildUrl(url)) {
                headers.forEach { (key, value) ->
                    header(key, value)
                }
                
                // 将body参数作为查询参数附加到URL上
                if (body != null) {
                    // 使用ZheAttribute的toMap方法将对象转换为参数映射
                    val params = body.toMap()
                    params.forEach { (key, value) ->
                        parameter(key, value)
                    }
                }
                
                // 不再设置请求体，只使用查询参数
                contentType(contentType)
            }
            response.body()
        }
    }

    /**
     * 构建完整的URL
     */
    fun buildUrl(url: String): String {
        return if (url.startsWith("http://") || url.startsWith("https://")) {
            url
        } else {
            baseUrl.trimEnd('/') + "/" + url.trimStart('/')
        }
    }

    /**
     * 关闭客户端连接
     */
    fun close() {
        client.close()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.isSuccessful) {
            response.body?.let { body ->
                val json = Json.decodeFromString<JsonObject>(body.string())
                json["status"]?.jsonPrimitive?.let {
                    if (it.int != 200) {
                        throw ApiErrorException.Simple(json["content"].toString())
                    }
                }
                val errorResponse = json["error_response"]?.jsonObject
                if(errorResponse!=null){
                    val error = Json.decodeFromJsonElement<JdErrorResponse>(json)
                    throw ApiErrorException.ErrorResponse(error)
                }
            }
        }
        return response
    }


    companion object {
        const val DEFAULT_BASE_URL = "https://api.zhetaoke.com:10001"
    }
}

/**
 * HttpClient的扩展函数，用于在协程作用域中执行HTTP请求
 */
suspend fun <T> HttpClient.use(block: suspend CoroutineScope.(HttpClient) -> T): T {
    return try {
        block(this)
    } finally {
        this.close()
    }
}


