package com.ZTest01.Codes;

public class ChatMessage {

	private String sender;
	private String message;

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

	public ChatMessage(String sender, String message) {
		super();
		this.sender = sender;
		this.message = message;
	}

	public ChatMessage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ChatMessage [sender=" + sender + ", message=" + message + "]";
	}

}
