package com.urun.messageserver;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.urun.messageserver.environment.SystemConfig;

public class MessageServer {

	static  Logger logger=Logger.getLogger(MessageServer.class);
	public static void main(String[] args) {
	    
		int port=SystemConfig.getSystemConfig().getPort();//配置端口
		//设置服务接口，默认为9431
		Server server=new Server(port);
		//初始化Servlet容器
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(com.urun.messageserver.servlet.SingleMessageServlet.class,"/message/send");
        context.addServlet(com.urun.messageserver.servlet.GroupMessageServlet.class,"/message/group");
        context.addServlet(com.urun.messageserver.servlet.BalanceServlet.class,"/message/balance");
        server.setHandler(context);
         
	    //启动服务器
	    try {
	        logger.debug("准备启动服务器中……");
			server.start();
		    
		} catch (Exception e) {
	    	logger.error("启动服务器失败："+e.getMessage());
		}
	    while (server.isStarted()) {
	    	logger.debug("服务器启动中……");
	      break;
	    }
	    logger.debug("服务器启动成功……");
	    System.out.println("系统启动成功,服务器侦听端口为："+port+",listening……");
	    logger.debug("服务器侦听端口为：……"+port);
	    try {
			server.join();
		} catch (InterruptedException e) {
		logger.error("服务器运行出错，"+e.getMessage());
		}
	}
	
}
