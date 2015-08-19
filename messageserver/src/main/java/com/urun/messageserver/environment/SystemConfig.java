package com.urun.messageserver.environment;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.urun.message.util.PropertiesTool;

public class SystemConfig {
	private static Logger logger=Logger.getLogger(SystemConfig.class);
    private static SystemConfig config=new SystemConfig();
    private int port;
    private boolean ischeck;
    private String mysqlurl;
    private String mysqluser;
    private String mysqlpwd;

	public int getPort() {
		return port;
	}

	public boolean isIscheck() {
		return ischeck;
	}

	public String getMysqlurl() {
		return mysqlurl;
	}

	public String getMysqluser() {
		return mysqluser;
	}

	public String getMysqlpwd() {
		return mysqlpwd;
	}

	private SystemConfig(){};
   
    private static void initConfig() throws NumberFormatException, IOException
    {
        PropertiesTool configtool=new PropertiesTool("server.conf");
        config.port=configtool.getValue("message.server.port")!=null?Integer.parseInt(configtool.getValue("message.server.port")):9431;
        config.mysqlurl=configtool.getValue("mysql.url");
        config.mysqluser=configtool.getValue("mysql.user");
        config.mysqlpwd=configtool.getValue("mysql.password");
    }
    public static SystemConfig getSystemConfig()
    {
    	try {
			initConfig();
		} catch (NumberFormatException e) {
			logger.error("读取Mysql配置文件出现错误，请检查配置文件配置……");
			throw new  RuntimeException("读取Mysql配置文件出现错误，请检查配置文件配置……");
		} catch (IOException e) {
			logger.error("找不到配置文件");
			throw new  RuntimeException("找不到配置文件");
		}
    	return config;
    }
}
