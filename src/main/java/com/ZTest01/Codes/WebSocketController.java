package com.ZTest01.Codes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	@MessageMapping("/chat/{username}")
	@SendTo("/topic/messages")
	public ChatMessage sendChatMessage(ChatMessage message) {
		// Save the message to MongoDB
		chatMessageRepository.save(message);

		return message;
	}

	@MessageMapping("/history")
	public List<ChatMessage> getChatHistory() {
	    // Retrieve chat history from MongoDB
	    List<ChatMessage> chatHistory = chatMessageRepository.findAll();
	    System.out.println("Chat History: " + chatHistory);
	    return chatHistory;
	}

}
