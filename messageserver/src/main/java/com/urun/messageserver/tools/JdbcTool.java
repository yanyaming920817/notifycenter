package com.urun.messageserver.tools;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.urun.message.util.PropertiesTool;
import com.urun.messageserver.environment.SystemConfig;

public class JdbcTool {
	private Connection connection=null;
	private PropertiesTool mysqlconfig=new PropertiesTool("server.conf");
    private static Logger logger=Logger.getLogger(JdbcTool.class);
    static
    {
        try{   
             //加载MySql的驱动类
              Class.forName("com.mysql.jdbc.Driver") ;   
              }catch(ClassNotFoundException e){   
               logger.error("数据库连接失败，请检查后重试！");
               throw new RuntimeException("数据库连接失败，请检查后重试！");
              }   
    }
    public Connection  getConnection() throws SQLException
    {
    	SystemConfig config=SystemConfig.getSystemConfig();
         this.connection=DriverManager.getConnection(config.getMysqlurl(),config.getMysqluser(),config.getMysqlpwd());
        return connection;
    }
    public boolean isalive() throws SQLException
    {
    	if(connection!=null)
    	{
    		return !connection.isClosed();
    	}
    	return false;
    }
   
}
