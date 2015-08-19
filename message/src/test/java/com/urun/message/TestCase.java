package com.urun.message;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.urun.message.domain.Message;
import com.urun.message.domain.MessageStatus;
import com.urun.message.util.PropertiesTool;

public class TestCase {

	/**
	 * 测试配置文件工具
	 * @throws IOException
	 */
	@Test
	public void testPropertiesTool() throws IOException
	{
		PropertiesTool properties=new PropertiesTool("message.conf");
		System.out.printf(properties.getValue("message.sign"));
	}
	/***
	 * 测试读取配置文件
	 */
	@Test
	public void testGetMessageConfig()
	{
		MessageConfig config=MessageConfig.getMessageConfig();
	    System.out.println(config.getUsername());
	    System.out.println(config.getPassword());
	}
	/***
	 * 测试短信发送
	 * @throws Exception
	 */
	@Test
	public void testSendMessage() throws Exception
	{
		MessageEngine engine=new MessageEngine();
		Message message=new Message();
		message.setContent("这是但愿测试1");
		message.setMobile("18818907546");
		MessageStatus status=engine.sendMessage(message);
	    System.out.println(status.getSendStatus()+"  "+status.getSendinfo());
	}
	/***
	 * 测试余额查询
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void testgetMessageBalance() throws ClientProtocolException, IOException
	{
		MessageEngine engine=new MessageEngine();
		System.out.println(engine.getMessageBalance());
	}
}
