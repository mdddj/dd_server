package shop.itbug.ticket.service

import ai.koog.agents.core.agent.AIAgent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import shop.itbug.ticket.ex.log

@Service
class AiService {
    private var logger = this.log()

    @Autowired
    private lateinit var aiAgent: AIAgent<String, String>


    suspend fun generate(message: String): String {
        val result = aiAgent.run(message)
        return result.trim()
    }



}