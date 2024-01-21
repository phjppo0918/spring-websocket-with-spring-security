package com.example.back.config.security

import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig {
    /*
    http security chain과 websocket은 완전히 독립적이다.
    즉, spring authenticationProvider은 웹소켓 인증에 전혀 참여하지 않는다.
    다만, stomp 프로토콜은 첫 번째 요청은 HTTP에 의존하기에 connection 요청 엔드포인트는 열어두어야 한다.

     */

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests {
                it
                    //.requestMatchers("/connect").authenticated()
                    .requestMatchers("/signup").permitAll()
                    .anyRequest().permitAll()
            }
            .formLogin {
                it.loginProcessingUrl("/login")
                it.usernameParameter("id")
                it.passwordParameter("password")
                it.successHandler(authenticationSuccessHandler())
                it.failureHandler(authenticationFailureHandler())
            }
            .cors { it.configurationSource(corsConfig()) }
            .csrf { it.disable() }
            .build()
    }

    @Bean
    fun corsConfig(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()

        corsConfiguration.setAllowedOriginPatterns(listOf("http://localhost:5173"))
        corsConfiguration.addAllowedHeader("*")
        corsConfiguration.addAllowedMethod("*")
        corsConfiguration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration)
        return source
    }

    @Bean
    fun authenticationSuccessHandler() =
        AuthenticationSuccessHandler { _, res, _ ->
            res.status = HttpServletResponse.SC_OK
        }

    @Bean
    fun authenticationFailureHandler() =
        AuthenticationFailureHandler { _, res, _ ->
            res.status = HttpServletResponse.SC_UNAUTHORIZED
        }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
