import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.forms.formData
import io.ktor.http.parameters
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

private const val APP_KEY = "0a565628b2fb4712902712734cec2feb"


fun main(): Unit = runBlocking {
    val httpClient = JdZheClient(APP_KEY)


    // POST请求示例
    try {
        val postData = JdUnionRequestParams(
            appkey = APP_KEY,
            materialId = "saSA",
            unionId = "ss",
        )
        val postResponse = httpClient.getForObject<StatusContentResponse<JDProductItem>>(
            "/api/open_jing_union_open_promotion_byunionid_get.ashx",
            parameters = postData.toMap()
        )
        println("POST Response data: $postResponse")
    }
    catch (e: ApiErrorException){
        println("接口错误:${e}")
    }
    catch (e: Exception) {
        println("POST请求失败: ${e.message}")
    }

    httpClient.close()
}