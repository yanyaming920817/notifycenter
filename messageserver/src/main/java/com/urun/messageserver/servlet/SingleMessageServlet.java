package com.urun.messageserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Receiver;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import com.urun.message.domain.Message;
import com.urun.message.domain.MessageStatus;
import com.urun.messageserver.domain.MessageRecord;

public class SingleMessageServlet extends ServletBase {

    private Logger logger=Logger.getLogger(SingleMessageServlet.class);
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        doPost(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String content=request.getParameter("content");
        String receiver=request.getParameter("receiver");
        PrintWriter pw;
        try {
            pw = response.getWriter();
        } catch (IOException e1) {
            return ;
        }
        Date date =new Date(System.currentTimeMillis());
        String ip=request.getRemoteAddr();
        logger.debug(date.toLocaleString()+" 用户："+ip+",访问了服务器");
        if(content==null||receiver==null||content.length()==0||receiver.length()==0)
        {
            pw.write("对不起，发送失败，消息参数不完整");
            return ;
        }
        if(content.length()>=200)
        {
            pw.write("对不起，短信超长，短信内容不能超过200字");
            logger.debug("对不起，短信超长，短信内容不能超过200字");
            return ;
        }
      String username=null;
      try {
        username=checkAuthorization(ip);
        if(username==null)
        {
         pw.write("对不起，你没有权限发送短信！");
         logger.debug("用户:"+ip+"没有发送权限，请求被阻止");         
         return ;
        }
        Message message=getMessage(receiver,content);
        try {
            MessageStatus status=messageEngine.sendMessage(message);
            pw.print(status.getSendStatus()+" "+status.getSendinfo());
            logger.debug(status.getSendStatus()+" "+status.getSendinfo());
        } catch (ClientProtocolException e) {
           logger.error(e.getMessage());
           pw.print("短信发送失败！");
        } catch (IOException e) {
          logger.error(e.getMessage());
          pw.print("短信发送失败！");
        }
        MessageRecord record=getMessageRecord(username, request.getRemoteAddr(), receiver, content, new Timestamp(System.currentTimeMillis()));
        messageDao.saveMessageRecord(record);
        
    } catch (SQLException e) {
         pw.write("短信服务器数据库发生了一个错误");
         logger.error("短信服务器数据库发生了一个错误");
    
    }
      

    }
    //组成短信主体
    public Message getMessage(String mobile,String content)
    {
        Message message=new Message();
        message.setContent(content);
        message.setMobile(mobile);
        return message;
    }
   
}
