package com.ZTest01.Codes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chat_messages")
public class ChatMessage {

	@Id
	private String id;
	private String sender;
	private String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ChatMessage(String id, String sender, String message) {
		super();
		this.id = id;
		this.sender = sender;
		this.message = message;
	}

	public ChatMessage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ChatMessage [id=" + id + ", sender=" + sender + ", message=" + message + "]";
	}

}
