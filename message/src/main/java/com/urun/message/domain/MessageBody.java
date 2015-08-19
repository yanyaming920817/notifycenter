package com.urun.message.domain;

import com.urun.message.MessageConfig;

public class MessageBody {

	private Message message;
	private MessageConfig config;
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public MessageConfig getConfig() {
		return config;
	}
	public void setConfig(MessageConfig config) {
		this.config = config;
	}
	
}
