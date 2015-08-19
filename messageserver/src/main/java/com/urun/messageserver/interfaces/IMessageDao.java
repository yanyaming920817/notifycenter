package com.urun.messageserver.interfaces;

import java.sql.SQLException;

import com.urun.messageserver.domain.MessageRecord;

public interface IMessageDao <T>{
	
	public void saveMessageRecord(T record) throws SQLException;

	public String checkUser(String checkInfo) throws SQLException;
	
	public String VerifyUser(String token) throws SQLException;
	
	  public String getReceiverByGroup(String groupid) throws SQLException;

}
