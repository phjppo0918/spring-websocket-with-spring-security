package com.example.back.chat

import com.fasterxml.jackson.annotation.JsonProperty

data class MessageRequest(
    @JsonProperty("value") val value : String
)
