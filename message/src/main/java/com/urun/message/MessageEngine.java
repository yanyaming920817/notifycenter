package com.urun.message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.urun.message.domain.Message;
import com.urun.message.domain.MessageBody;
import com.urun.message.domain.MessageStatus;
import com.urun.message.util.PropertiesTool;
/***
 * 短信发送API
 * @author yanyamin
 *
 */
public class MessageEngine {

	private PropertiesTool config=new PropertiesTool("message.conf");
	private HttpClient client=new DefaultHttpClient();
	private MessageConfig messageConfig=MessageConfig.getMessageConfig();
	/***
	 * 发送短信
	 * @param message 为用户要发送的消息体
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public MessageStatus sendMessage(Message message) throws ClientProtocolException, IOException
	{
		MessageBody messageBody=new MessageBody();
		messageBody.setConfig(messageConfig);
		messageBody.setMessage(message);
		HttpPost post=getMessagePost(messageBody);
		MessageStatus status=null;
		try {
			post.setURI(new URI(messageConfig.getUrl()));
		} catch (URISyntaxException e) {
		}
		HttpResponse response=client.execute(post);
		if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
		{
			HttpEntity entity=response.getEntity();
	    	String reslut= EntityUtils.toString(entity);
	    	if(reslut.contains("成功")) status=new MessageStatus(SendStatus.OK, "短信发送成功！短信回执为："+reslut);
	    	else {
				status=new MessageStatus(SendStatus.Failure, "短信发送失败！短信回执为："+reslut);
			}
	    
		}
		else status=new MessageStatus(SendStatus.Failure,"短信发送请求出了一个错误，请联系服务提供商！"+messageConfig.getUrl());
		return status;
		
		
	}
	
    /***
     * 根据短信用户的信息和验证消息组成一次发送短信的请求
     * @param messageBody
     * @return
     * @throws UnsupportedEncodingException
     */
	public HttpPost getMessagePost(MessageBody messageBody) throws UnsupportedEncodingException
	{
		HttpPost post=new HttpPost();
		List<NameValuePair> paras=new ArrayList<NameValuePair>();
		paras.add(new BasicNameValuePair("name", messageBody.getConfig().getUsername()));
	    paras.add(new BasicNameValuePair("pwd", messageBody.getConfig().getPassword()));
	    paras.add(new  BasicNameValuePair("sign",messageBody.getConfig().getSign()));
	    paras.add(new  BasicNameValuePair("mobile",messageBody.getMessage().getMobile()));
	    paras.add(new  BasicNameValuePair("content",messageBody.getMessage().getContent()));
	    paras.add(new  BasicNameValuePair("type","pt"));
		UrlEncodedFormEntity entity=new UrlEncodedFormEntity(paras,"UTF-8");
		post.setEntity(entity);
		return post;
		
	}
	/***
	 * 查询电信剩余的额度
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String getMessageBalance() throws ClientProtocolException, IOException
	{
		PropertiesTool config=new PropertiesTool("message.conf");
		HttpPost post=new HttpPost(config.getValue("message.url"));
		List<NameValuePair> paras=new ArrayList<NameValuePair>();
		paras.add(new BasicNameValuePair("name", messageConfig.getUsername()));
	    paras.add(new BasicNameValuePair("pwd", messageConfig.getPassword()));
	    paras.add(new  BasicNameValuePair("type","balance"));
		UrlEncodedFormEntity entity=new UrlEncodedFormEntity(paras,"UTF-8");
		post.setEntity(entity);
	    HttpResponse response=client.execute(post);
	    if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
		{
	    	HttpEntity reentity=response.getEntity();
	    	String reslut= EntityUtils.toString(reentity);
	    	String res[]=reslut.split(",");
	    	if(res[0].equals("0"))
	    	{
	    		return "查询成功，账号还剩余："+res[1]+"条短信";
	    	}
	    	return "查询失败，返回码为："+res[0]+",错误原因为："+res[1];
		}
	    else
	    {
	    	return "查询失败，访问运营商服务器失败！返回码为："+response.getStatusLine().getStatusCode();
	    }
		
	}
}
