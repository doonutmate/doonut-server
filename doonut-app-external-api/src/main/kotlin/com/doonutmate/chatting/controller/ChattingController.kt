package com.doonutmate.chatting.controller

import com.doonutmate.chatting.dto.ChatMessageDTO
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller


@Tag(name = "chatting-example", description = "채팅 테스트용 API")
@Controller
class ChattingController(
    val template: SimpMessagingTemplate,
) {

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping("/chat/enter")
    fun enter(message: ChatMessageDTO) {
        println("채팅방에 입장했습니다.")
        message.message = "${message.writer}님이 채팅방에 참여하였습니다."
        template.convertAndSend("/sub/chat/room/${message.roomId}", message)
    }

    @MessageMapping("/chat/message")
    fun message(message: ChatMessageDTO) {
        println("채팅을 보냈습니다. ${message.message}")
        template.convertAndSend("/sub/chat/room/${message.roomId}", message.message)
    }
}
