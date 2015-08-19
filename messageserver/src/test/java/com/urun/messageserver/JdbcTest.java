package com.urun.messageserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.urun.messageserver.dao.MessageDao;
import com.urun.messageserver.domain.MessageRecord;
import com.urun.messageserver.tools.JdbcTool;

public class JdbcTest {

    /***
     * 测试数据库连接
     * @throws SQLException
     */
	@Test
	public void testGetConnection() throws SQLException
	{
		JdbcTool jdbc=new JdbcTool();
		Connection connection=jdbc.getConnection();
		Assert.assertNotNull(connection);
	}
	/***
	 * 测试存储短信发送流水
	 * @throws SQLException
	 */
	@Test
	public void testInsertRecord() throws SQLException
	{
		MessageDao dao =new MessageDao();
		MessageRecord record=new MessageRecord();
		record.setContent("这是一条测试短信");
		record.setReceiver("张三");
		record.setSender("王五");
		record.setSenderIP("127.0.0.1");
		record.setTimestamp(new Timestamp(System.currentTimeMillis()));
		dao.saveMessageRecord(record);
	}
	/***
	 * 根据ip验证用户权限
	 * @throws SQLException
	 */
	@Test
	public void testVerifyUser() throws SQLException
	{
	    MessageDao dao =new MessageDao();
        System.out.println(dao.VerifyUser("192.168.1.137"));
	}
	/***
	 * 根据短信号码
	 * @throws SQLException
	 */
	@Test
	public void testcheckUser() throws SQLException
	{
	    MessageDao dao =new MessageDao();
        System.out.println(dao.checkUser("18818907546"));
	}
	/***
	 * 根据组名得到接收列表
	 * @throws SQLException
	 */
	@Test
	public void testgetReceiverByGroup() throws SQLException
	{
	    MessageDao dao =new MessageDao();
        System.out.println(dao.getReceiverByGroup("0001"));
	}
	
}
