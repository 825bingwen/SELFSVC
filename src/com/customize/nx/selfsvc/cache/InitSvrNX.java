/*
 * @filename: InitSvrNX.java
 * @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
 * @brif:  缓存缴费信息，并定时清除
 * @author: g00140516
 * @de:  2012/10/08 
 * @description: 
 * @remark: create g00140516 2012/10/08 R003C12L09n01 规避缴费重复问题
 */
package com.customize.nx.selfsvc.cache;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.cache.PublicCache;

/**
 * 缓存缴费信息，并定时清除
 * 
 * @author g00140516
 * @version 1.0 2012.10.08
 */
public class InitSvrNX extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(InitSvrNX.class);
    
    // 定时器
    private static Timer timer = new Timer();
    
    public InitSvrNX()
    {
        super();
    }    
    
    public void destroy()
    {
        try
        {
            if (timer != null)
            {
                timer.cancel();
            }
        }
        catch (Exception e)
        {
            logger.error("清除定时任务异常：", e);
        }
        finally
        {
            super.destroy();
        }
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        RefreshCacheNX.getInstance().run();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(this.getClass());
        out.println(", using the GET method");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        RefreshCacheNX.getInstance().run();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(this.getClass());
        out.println(", using the POST method");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

    public void init() throws ServletException
    {
        try
        {
            super.init();
            
            RefreshCacheNX refreshCacheNX = RefreshCacheNX.getInstance();
            
            String strCashChargeTime = (String) PublicCache.getInstance().getCachedData("SH_CASHCHARGE_SEQLOG_TIME");
            if (strCashChargeTime == null)
            {
                Thread.sleep(15 * 1000);
            }
            
            strCashChargeTime = (String) PublicCache.getInstance().getCachedData("SH_CASHCHARGE_SEQLOG_TIME");
            if (strCashChargeTime == null || "".equals(strCashChargeTime.trim()))
            {
                strCashChargeTime = "30";
            }
            
            // 默认：30分钟
            int cashChargeTime = 30;
            
            try
            {
                cashChargeTime = Integer.parseInt(strCashChargeTime);
            }
            catch (Exception e)
            {
                logger.error("格式化失败：", e);
            }
            
            timer.schedule(refreshCacheNX, 0, cashChargeTime * 60L * 1000);
        }
        catch (Exception e)
        {
            logger.error("缴费记录缓存操作异常：", e);
            
            throw new ServletException(e);
        }
    }    
}
