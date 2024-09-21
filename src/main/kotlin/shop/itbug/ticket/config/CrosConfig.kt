package shop.itbug.ticket.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


/**
 * @author eee
 */
@Configuration
class CorsConfig : WebMvcConfigurer {


    @Bean
    fun corsConfigurer() : WebMvcConfigurer{
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOriginPatterns("*")
                    .allowCredentials(true)
                    .allowedMethods("*")
                    .maxAge(3600)
                super.addCorsMappings(registry)
            }
        }
    }

    @Bean
    fun corsFilter(): CorsFilter {
        val config = CorsConfiguration()
        config.addAllowedOriginPattern("*")
        config.allowCredentials = true
        config.addAllowedMethod("*")
        config.addAllowedHeader("*")
        config.addExposedHeader("*")
        val configSource = UrlBasedCorsConfigurationSource()
        configSource.registerCorsConfiguration("/**", config)
        return CorsFilter(configSource)
    }



}