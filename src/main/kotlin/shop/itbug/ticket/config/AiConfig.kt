package shop.itbug.ticket.config

import ai.koog.agents.core.agent.AIAgent
import ai.koog.prompt.executor.clients.LLMClient
import ai.koog.prompt.executor.clients.openai.OpenAIClientSettings
import ai.koog.prompt.executor.clients.openai.OpenAILLMClient
import ai.koog.prompt.executor.llms.SingleLLMPromptExecutor
import ai.koog.prompt.llm.LLMCapability
import ai.koog.prompt.llm.LLMProvider
import ai.koog.prompt.llm.LLModel
import cn.hutool.core.lang.UUID
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import shop.itbug.ticket.utils.logger

@ConfigurationProperties(prefix = "ai")
@Configuration
class AiConfig {
    lateinit var baseUrl: String
    lateinit var model: String
    lateinit var apiKey: String
    var blogDescriptionLength: Int? = 100

    fun aiModel(): LLModel {
        val model = LLModel(
            LLMProvider.OpenAI,
            model,
            listOf(LLMCapability.Completion, LLMCapability.Tools),
        )
        return model
    }

    @Bean
    fun aiAgent(): AIAgent<String, String> {
        logger.info("AiAgent is enabled, using model $model base url $baseUrl")
        return AIAgent(
            llmExecutor(),
            aiModel(),
            temperature = 1.0,
            systemPrompt = """
                你是一个博客总结大师,需要把传入的博客文本,使用精简的语法描述出来,我需要把你生成的总结内容设置进 Blog的description字段,然后在博客的前端显示出来.所以不要携带无关的文本,
                总结长度大约在${blogDescriptionLength}左右
            """.trimIndent(),
            id = UUID.fastUUID().toString(),
        )
    }

    fun aiClient(): LLMClient {
        return OpenAILLMClient(apiKey, OpenAIClientSettings(baseUrl = baseUrl))
    }

    fun llmExecutor(): SingleLLMPromptExecutor {
        return SingleLLMPromptExecutor(aiClient())
    }

}