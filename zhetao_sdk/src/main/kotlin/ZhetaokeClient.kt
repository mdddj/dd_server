import io.ktor.client.plugins.defaultRequest
import io.ktor.http.parameters

class JdZheClient(appKey: String = "") : ZheHttpClient({
    defaultRequest {
        parameters {
            append("appkey", appKey)
            url {
                parameters.append("appkey", appKey)
            }
        }
    }
})