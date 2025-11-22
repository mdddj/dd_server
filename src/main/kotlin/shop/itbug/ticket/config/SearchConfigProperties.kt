package shop.itbug.ticket.config

import com.alibaba.fastjson2.toJSONString
import com.meilisearch.sdk.Client
import com.meilisearch.sdk.Config
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import shop.itbug.ticket.entry.blog.Blog
import shop.itbug.ticket.ex.log

interface MeiliSearchBase {
    fun getIndexData(): Map<String, String>
}

@ConfigurationProperties(prefix = "meilisearch")
@Configuration
class SearchConfigProperties {
    lateinit var host: String
    lateinit var key: String


    val client get() = Client(Config(host, key))


    /**
     * 索引全部博客
     */
    fun indexBlogs(blogs: List<Blog>) {
        indexData("blogs", blogs)
    }

    fun indexBlog(blog: Blog) {
        val index = client.index("blogs")
        try {
            index.addDocuments(blog.getIndexData().toJSONString())
        } catch (e: Exception) {
            log().warn("尝试索引博客失败:${e}")
        }
    }

    fun indexData(uid: String, dataList: List<MeiliSearchBase>) {
        val index = client.index(uid)
        dataList.forEach { data ->
            try{
                index.addDocuments(data.getIndexData().toJSONString())
            }catch (e: Exception){
                log().warn("索引数据失败:${e.localizedMessage}")
            }
        }
    }

}