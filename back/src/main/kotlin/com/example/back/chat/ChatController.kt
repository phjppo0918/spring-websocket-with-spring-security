package com.example.back.chat

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController {


    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    fun chat(messageRequest: MessageRequest, auth: Authentication) : MessageResponse {
        println(messageRequest.value)
        return MessageResponse(auth.name, messageRequest.value)
    }

}