/*
 * 文件名：InitSvr.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：启动定时刷新任务，刷新配置信息、菜单、region等常用的信息
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增
 * 
 */
package com.gmcc.boss.selfsvc.cache;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Timer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * @author g00140516
 * @version 1.0 2010.11.30
 */
public class InitSvr extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(InitSvr.class); // 日志类
    
    private static Timer timer = new Timer(); // 定时器
    
    public InitSvr()
    {
        super();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Servlet#destroy()
     */
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
    
    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RefreshCache.getInstance().run();
        //add begin lWX431760 2017-09-25 OR_huawei_201708_809_【山东移动接口迁移专题】-能开token获取机制优化 
        //手动刷新缓存获取能开token
        if (StringUtils.isNotBlank(request.getParameter("getToken")))
        {
            CommonUtil.getOpenEbusToken();
        }        
        //add end lWX431760 2017-09-25 OR_huawei_201708_809_【山东移动接口迁移专题】-能开token获取机制优化 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
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
    
    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RefreshCache.getInstance().run();
        //add begin lWX431760 2017-09-25 OR_huawei_201708_809_【山东移动接口迁移专题】-能开token获取机制优化 
        //手动刷新缓存获取能开token
        if (StringUtils.isNotBlank(request.getParameter("getToken")))
        {
            CommonUtil.getOpenEbusToken();
        }        
        //add end lWX431760 2017-09-25 OR_huawei_201708_809_【山东移动接口迁移专题】-能开token获取机制优化 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
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
    
    /**
     * Initialization of the servlet. <br>
     * 
     * @throws ServletException if an error occure
     */
    public void init(ServletConfig config) throws ServletException
    {
        try
        {
            super.init(config);
            
            // 获取代码相对路径 modify begin wWX217192 2014-12-18 for 山东现场遗留问题
            String prefix = getServletContext().getRealPath("/") + System.getProperty("file.separator");
            // 获取代码相对路径 modify begin wWX217192 2014-12-18 for 山东现场遗留问题
            
            int index = prefix.indexOf("WEB-INF");
            if (index != -1)
            {
                prefix = prefix.substring(0, index);
            }
            
            // Cache初始化，默认每30分钟刷新一次
            long refreshTime = 1000 * 60 * 30;
            
            // 自selfsvc.properties中获取刷新时间间隔
            Properties prop = null;
            try
            {
                prop = new Properties();
                prop.load(this.getClass().getClassLoader().getResourceAsStream("selfsvc.properties"));
                String strRefreshTime = prop.getProperty(Constants.REFRESH_TIME);
                if (strRefreshTime != null && strRefreshTime.trim().length() > 0)
                {
                    refreshTime = Long.parseLong(strRefreshTime);
                }
            }
            catch (Exception e)
            {
                logger.error("获取缓存定时刷新时间异常：", e);
            }
            finally
            {
                if (prop != null)
                {
                    prop.clear();
                }
            }
            
            // 启动定时刷新任务
            RefreshCache refresh = RefreshCache.getInstance();
            refresh.setRootPath(prefix);
            timer.schedule(refresh, 0, refreshTime);
        }
        catch (Exception e)
        {
            logger.error("缓存初始化异常：", e);
            
            throw new ServletException(e);
        }
    }
}
