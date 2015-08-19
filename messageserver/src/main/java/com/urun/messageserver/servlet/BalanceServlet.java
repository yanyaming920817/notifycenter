package com.urun.messageserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.eclipse.jetty.jndi.local.localContextRoot;

import com.urun.message.MessageEngine;

public class BalanceServlet extends ServletBase {

    private Logger logger=Logger.getLogger(BalanceServlet.class);
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String ip = request.getRemoteAddr();
        PrintWriter pw;
        try {
            pw = response.getWriter();
        } catch (IOException e1) {
            return ;
        }
        String username = null;
        try {
            username = checkAuthorization(ip);
        } catch (SQLException e) {
          pw.write("查询失败，请稍后再试……"+e.getMessage());
          return ;
        }
        if (username == null) {
            pw.write("对不起，你没有查询短信余额的权限！");
            logger.debug("用户:"+ip+"没有查询短信余额的权限，请求被阻止");         
            return ;
        } else {
            MessageEngine engine=new MessageEngine();
           try {
            pw.write(engine.getMessageBalance());
            return ;
        } catch (ClientProtocolException e) {
            pw.write("查询失败，请稍后再试……"+e.getMessage());
            return ;
        } catch (IOException e) {
            pw.write("查询失败，请稍后再试……"+e.getMessage());
            return ;
        }
        }

    }

}
