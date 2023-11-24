package com.doonutmate.chatting.dto

import org.springframework.web.socket.WebSocketSession
import java.util.UUID

data class ChatRoomDTO(
    val roomId: String,
    val name: String,
    val sessions: Set<WebSocketSession> = setOf(),
) {
    companion object {

        fun create(name: String): ChatRoomDTO {
            return ChatRoomDTO(UUID.randomUUID().toString(), name)
        }
    }
}
