package com.urun.messageserver.service;

import com.urun.messageserver.dao.MessageDao;
import com.urun.messageserver.domain.MessageRecord;
import com.urun.messageserver.interfaces.IMessageDao;
import com.urun.messageserver.interfaces.IMessageService;

public class MessageService implements IMessageService{

	IMessageDao dao=new MessageDao();
	public boolean isLegalUser(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void  recordMsgLog(MessageRecord record)
	{
		
	}

	
}
