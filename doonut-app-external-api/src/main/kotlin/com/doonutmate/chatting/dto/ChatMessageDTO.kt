package com.doonutmate.chatting.dto

/*
{
    "roomId": "1",
    "writer": "yeongun",
    "message": "hi"
}
 */
data class ChatMessageDTO(val roomId: String, val writer: String, var message: String)
