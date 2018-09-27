/*
 * �ļ�����InitSvr.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������������ʱˢ������ˢ��������Ϣ���˵���region�ȳ��õ���Ϣ
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ�����
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
    
    private static Log logger = LogFactory.getLog(InitSvr.class); // ��־��
    
    private static Timer timer = new Timer(); // ��ʱ��
    
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
            logger.error("�����ʱ�����쳣��", e);
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
        //add begin lWX431760 2017-09-25 OR_huawei_201708_809_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�ܿ�token��ȡ�����Ż� 
        //�ֶ�ˢ�»����ȡ�ܿ�token
        if (StringUtils.isNotBlank(request.getParameter("getToken")))
        {
            CommonUtil.getOpenEbusToken();
        }        
        //add end lWX431760 2017-09-25 OR_huawei_201708_809_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�ܿ�token��ȡ�����Ż� 
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
        //add begin lWX431760 2017-09-25 OR_huawei_201708_809_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�ܿ�token��ȡ�����Ż� 
        //�ֶ�ˢ�»����ȡ�ܿ�token
        if (StringUtils.isNotBlank(request.getParameter("getToken")))
        {
            CommonUtil.getOpenEbusToken();
        }        
        //add end lWX431760 2017-09-25 OR_huawei_201708_809_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�ܿ�token��ȡ�����Ż� 
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
            
            // ��ȡ�������·�� modify begin wWX217192 2014-12-18 for ɽ���ֳ���������
            String prefix = getServletContext().getRealPath("/") + System.getProperty("file.separator");
            // ��ȡ�������·�� modify begin wWX217192 2014-12-18 for ɽ���ֳ���������
            
            int index = prefix.indexOf("WEB-INF");
            if (index != -1)
            {
                prefix = prefix.substring(0, index);
            }
            
            // Cache��ʼ����Ĭ��ÿ30����ˢ��һ��
            long refreshTime = 1000 * 60 * 30;
            
            // ��selfsvc.properties�л�ȡˢ��ʱ����
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
                logger.error("��ȡ���涨ʱˢ��ʱ���쳣��", e);
            }
            finally
            {
                if (prop != null)
                {
                    prop.clear();
                }
            }
            
            // ������ʱˢ������
            RefreshCache refresh = RefreshCache.getInstance();
            refresh.setRootPath(prefix);
            timer.schedule(refresh, 0, refreshTime);
        }
        catch (Exception e)
        {
            logger.error("�����ʼ���쳣��", e);
            
            throw new ServletException(e);
        }
    }
}
