package com.urun.messageserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.urun.messageserver.domain.MessageRecord;
import com.urun.messageserver.interfaces.IMessageDao;
import com.urun.messageserver.tools.JdbcTool;

public class MessageDao implements IMessageDao<MessageRecord>{
    private JdbcTool tool=new JdbcTool();
    private Connection connection=null;
    public MessageDao() 
    {
    	try {
			connection=tool.getConnection();
		} catch (SQLException e) {
		throw new RuntimeException("MySql数据库连接失败……");
		}
    }
    public void saveMessageRecord(MessageRecord record) throws SQLException {
    	String sql="insert into messagerecord(sender,Recipient,ip,content,timestamp) values(?,?,?,?,?)";
       PreparedStatement statement=connection.prepareStatement(sql);
       statement.setString(1, record.getSender());
       statement.setString(2, record.getReceiver());
       statement.setString(3, record.getSenderIP());
       statement.setString(4, record.getContent());
       statement.setTimestamp(5, record.getTimestamp());
       statement.executeUpdate();
        
    }

    public String checkUser(String checkInfo) throws SQLException {
        String sql="select username from userinfo where tel=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1, checkInfo);
        ResultSet resultSet= statement.executeQuery();
        while (resultSet.next()) {
          return resultSet.getString(1);
        }
         return null;
        
    }

    public String VerifyUser(String ip) throws SQLException {
       String sql="select username from userinfo where ip=? and enablesendmsg=1";
       PreparedStatement statement=connection.prepareStatement(sql);
       statement.setString(1, ip);
       ResultSet resultSet= statement.executeQuery();
       while (resultSet.next()) {
          return resultSet.getString(1);
      }
       return null;
       
    }
    public String getReceiverByGroup(String groupid) throws SQLException
    {
        String userid="";
        StringBuffer strbuf=new StringBuffer();
        String sql="select member from tgroup where groupid=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1, groupid);
        ResultSet resultSet=statement.executeQuery();
        if(resultSet.next())
          userid=resultSet.getString(1);
        else {
          return null;
        }
        String tel[]=userid.split(",");
        for (int i = 0; i < tel.length; i++) {
            sql="select tel from userinfo where uid=?";
            PreparedStatement telstatement=connection.prepareStatement(sql);
            telstatement.setString(1, tel[i]);
            ResultSet telResultSet=telstatement.executeQuery();
            if(telResultSet.next())
            {
                if(telResultSet.getString(1)!=null)
                strbuf.append(telResultSet.getString(1)).append(",");
            }
          }
        String tellist=strbuf.toString();
        if(tellist.endsWith(","))
            tellist=tellist.substring(0,tellist.length()-1);
        return tellist;
        
    }
}
