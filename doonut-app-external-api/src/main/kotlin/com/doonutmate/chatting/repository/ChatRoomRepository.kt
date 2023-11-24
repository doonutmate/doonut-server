package com.doonutmate.chatting.repository

import com.doonutmate.chatting.dto.ChatRoomDTO
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class ChatRoomRepository {
    private lateinit var chatRoomDTOMap: MutableMap<String, ChatRoomDTO?>

    @PostConstruct
    private fun init() {
        chatRoomDTOMap = LinkedHashMap()
    }

    fun findAllRooms(): List<ChatRoomDTO?> {
        return ArrayList(chatRoomDTOMap.values)
    }

    fun findRoomById(id: String): ChatRoomDTO? {
        return chatRoomDTOMap[id]
    }

    fun createChatRoomDTO(name: String): ChatRoomDTO {
        val room = ChatRoomDTO.create(name)
        chatRoomDTOMap[room.roomId] = room
        return room
    }
}
