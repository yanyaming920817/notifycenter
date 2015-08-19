package com.urun.messageserver.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.urun.message.MessageEngine;
import com.urun.message.util.PropertiesTool;
import com.urun.messageserver.dao.MessageDao;
import com.urun.messageserver.domain.MessageRecord;
import com.urun.messageserver.interfaces.IMessageDao;


public abstract class ServletBase extends HttpServlet {
 
	abstract protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException;
	abstract protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException;
	public IMessageDao<MessageRecord> messageDao=new MessageDao();
	public MessageEngine messageEngine=new MessageEngine();
    private PropertiesTool config=new PropertiesTool("server.conf");
    
    public String checkAuthorization(String ip) throws SQLException
    {
       return  messageDao.VerifyUser(ip);
    }
    public MessageRecord getMessageRecord(String sender,String senderIp,String receiver,String content,Timestamp timestamp)
    {
        MessageRecord record=new MessageRecord();
        record.setContent(content);
        record.setReceiver(receiver);
        record.setSenderIP(senderIp);
        record.setSender(sender);
        record.setTimestamp(timestamp);
        return record;
    }
	
}
