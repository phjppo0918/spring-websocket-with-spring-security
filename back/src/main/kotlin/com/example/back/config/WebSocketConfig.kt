package com.example.back.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer{

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/connect") // 연결 전송을 보낼 endpoint
            .setAllowedOriginPatterns("*") // cors 허용 패턴
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/queue", "/topic")
        // queue는 1대1 통신,
        // topic은 브로드캐스팅 통신
        registry.setApplicationDestinationPrefixes("/pub") //메시지 행들러로 라우팅되는 prifix
    }
}