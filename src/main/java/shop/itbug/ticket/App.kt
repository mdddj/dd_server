package shop.itbug.ticket

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.cache.annotation.EnableCaching
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@ServletComponentScan
@EnableCaching
@EnableAsync
@EnableWebMvc
@EnableConfigurationProperties
@ConfigurationPropertiesScan
class App

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}