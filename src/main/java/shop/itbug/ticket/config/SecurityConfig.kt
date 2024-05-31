package shop.itbug.ticket.config

import jakarta.annotation.Resource
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.*
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import shop.itbug.ticket.auth.account.AccountLoginAuthenticationFilter
import shop.itbug.ticket.auth.account.AccountLoginAuthenticationProvider
import shop.itbug.ticket.auth.email.EmailLoginAuthenticationFailure
import shop.itbug.ticket.auth.email.EmailLoginAuthenticationFilter
import shop.itbug.ticket.auth.email.EmailLoginAuthenticationProvider
import shop.itbug.ticket.auth.email.EmailLoginAuthenticationSuccess
import shop.itbug.ticket.filter.JwtTokenBeforeFilter
import shop.itbug.ticket.filter.RestAuthenticationEntryPoint
import shop.itbug.ticket.filter.SecurityNoAccessResultFilter
import shop.itbug.ticket.service.UserService


@Configuration
open class SecurityConfig {


    val log: Logger = LoggerFactory.getLogger(SecurityConfig::class.java)

    @Resource
    private lateinit var securityNoAccessResultFilter: SecurityNoAccessResultFilter

    @Resource
    private lateinit var restAuthenticationEntryPoint: RestAuthenticationEntryPoint

    @Resource
    private lateinit var userService: UserService

    fun getPermitUrls() = listOf(
        "/static/**",
        "/resources/**",
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/api/public/**",
        "/api/friend/**",
        "/api-doc",
        "/index",
        "/js/**",
        "/css/**",
        "/wm",
        "/api/app/**",
        "/actuator/**",
        "/instances/**",
        "/api/version/**",//版本信息接口所有放行
        "/",
        "/assets/**",
        "/api/blog/**",
        "/tkapi/**", //大淘客所有接口放行
        "/api/zhe/**",//折淘客所有接口放行
        "/api/user-public/**",//
        "/api/rc/**"
    )

    @Bean
    @Order(1)
    @Throws(Exception::class)
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .httpBasic {
            }
            .cors {
                it.configurationSource {
                    CorsConfiguration().apply {
                        allowedOrigins = arrayListOf("*")
                        allowedMethods = arrayListOf("*")
                        allowedHeaders = arrayListOf("*")
                    }
                }
            }.csrf {
                it.disable()
            }.sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }.addFilterBefore(
                JwtTokenBeforeFilter(userService),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .addFilterBefore(emailLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(
                accountLoginAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .authorizeHttpRequests {
                it.requestMatchers("/api/v1/dtk/detail/**").hasAuthority("admin")
                it.requestMatchers("/api/role/**").hasAuthority("admin")
                it.requestMatchers(*getPermitUrls().toTypedArray()).permitAll().anyRequest().authenticated()
            }
            .exceptionHandling {
                it.accessDeniedHandler(securityNoAccessResultFilter)
                it.authenticationEntryPoint(restAuthenticationEntryPoint)
            }
        return http.build()

    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }


    /**
     * 邮箱认证
     */
    open fun emailLoginAuthenticationFilter(): EmailLoginAuthenticationFilter {
        val filter = EmailLoginAuthenticationFilter()
        filter.setAuthenticationManager(ProviderManager(EmailLoginAuthenticationProvider(userService)))
        filter.setAuthenticationFailureHandler(EmailLoginAuthenticationFailure())
        filter.setAuthenticationSuccessHandler(EmailLoginAuthenticationSuccess())
        return filter
    }

    /**
     * 账号认证
     */
    open fun accountLoginAuthenticationFilter(): AccountLoginAuthenticationFilter {
        val filter = AccountLoginAuthenticationFilter()
        filter.setAuthenticationManager(ProviderManager(AccountLoginAuthenticationProvider(userService)))
        filter.setAuthenticationFailureHandler(EmailLoginAuthenticationFailure())
        filter.setAuthenticationSuccessHandler(EmailLoginAuthenticationSuccess())
        return filter
    }


}