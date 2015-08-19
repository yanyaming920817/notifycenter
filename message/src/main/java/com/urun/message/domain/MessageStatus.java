package com.urun.message.domain;

import com.urun.message.SendStatus;

public class MessageStatus {

	private SendStatus sendStatus;
	private String sendinfo;
	public SendStatus getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(SendStatus sendStatus) {
		this.sendStatus = sendStatus;
	}
	public String getSendinfo() {
		return sendinfo;
	}
	public void setSendinfo(String sendinfo) {
		this.sendinfo = sendinfo;
	}
	public MessageStatus(SendStatus status,String info) {
	this.sendinfo=info;
	this.sendStatus=status;
	}
	
	
}
