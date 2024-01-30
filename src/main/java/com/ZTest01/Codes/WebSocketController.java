package com.ZTest01.Codes;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

	@MessageMapping("/chat/{username}")
	@SendTo("/topic/messages")
	public ChatMessage sendChatMessage(ChatMessage message) {
		return message;
	}
}
