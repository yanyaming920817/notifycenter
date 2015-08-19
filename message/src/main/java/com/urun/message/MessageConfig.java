package com.urun.message;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.urun.message.util.PropertiesTool;

public class MessageConfig {   //单例模式 ：获得MessageConfig的实例
	private static MessageConfig messageConfig=null;
	private String username;
	private String password;
	private String sign;
	private String url;
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	private MessageConfig(){};

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getSign() {
		return sign;
	}
	
	public static MessageConfig getMessageConfig()
	{   
		if(messageConfig==null)
		{
		PropertiesTool config=new PropertiesTool("message.conf");
		try {
			messageConfig=new MessageConfig();
			messageConfig.username=config.getValue("message.name");
			messageConfig.password=config.getValue("message.password");
			messageConfig.sign=config.getValue("message.sign");
			messageConfig.url=config.getValue("message.url");
		} catch (IOException e) {
			throw new RuntimeException("找不到配置文件");
		}
		}
		return messageConfig;

	}
}
