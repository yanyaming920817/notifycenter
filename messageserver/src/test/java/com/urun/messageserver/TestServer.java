package com.urun.messageserver;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestServer {

    private HttpClient client=new DefaultHttpClient();
    private HttpPost post=null;
    /***
     * 测试单独发送短信
     * @throws ClientProtocolException
     * @throws IOException
     */
    @Test
    public void testSendMessage() throws ClientProtocolException, IOException
    {
        post=new HttpPost("http://127.0.0.1:8050/message/send");
        List<NameValuePair> paras=new ArrayList<NameValuePair>();
        paras.add(new BasicNameValuePair("content", "家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴家和万事兴"));
        paras.add(new BasicNameValuePair("receiver","18818907546"));
        UrlEncodedFormEntity entity=new UrlEncodedFormEntity(paras,"UTF-8");
        post.setEntity(entity);
        HttpResponse response=client.execute(post);
        if(response.getStatusLine().getStatusCode()==200)
        {
            HttpEntity reentity=response.getEntity();
            String reslut= EntityUtils.toString(reentity);
            System.out.println(reslut);
        }
    }
    /***
     * 测试短信组发
     * @throws ClientProtocolException
     * @throws IOException
     */
    @Test
    public void testSendGroupMessage() throws ClientProtocolException, IOException
    {
        post=new HttpPost("http://127.0.0.1:8050/message/group");
        List<NameValuePair> paras=new ArrayList<NameValuePair>();
        paras.add(new BasicNameValuePair("content", ""));
        paras.add(new BasicNameValuePair("groupid","0001"));
        UrlEncodedFormEntity entity=new UrlEncodedFormEntity(paras,"UTF-8");
        post.setEntity(entity);
        HttpResponse response=client.execute(post);
        if(response.getStatusLine().getStatusCode()==200)
        {
            HttpEntity reentity=response.getEntity();
            String reslut= EntityUtils.toString(reentity);
            System.out.println(reslut);
        }
    }
    @Test
    public void testGetBalance() throws ClientProtocolException, IOException
    {
        post=new HttpPost("http://127.0.0.1:8050/message/balance");
       
        HttpResponse response=client.execute(post);
        if(response.getStatusLine().getStatusCode()==200)
        {
            HttpEntity reentity=response.getEntity();
            String reslut= EntityUtils.toString(reentity);
            System.out.println(reslut);
        }
    }
}
