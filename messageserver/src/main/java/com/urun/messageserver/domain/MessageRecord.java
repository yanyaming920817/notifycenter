package com.urun.messageserver.domain;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class MessageRecord {
	private String sender;  //发送人
	private String senderIP;  //发送人ip
	private String receiver; //接收人
	private String content;  //短信内容
	private Timestamp timestamp;
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSenderIP() {
		return senderIP;
	}
	public void setSenderIP(String senderIP) {
		this.senderIP = senderIP;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	

}
