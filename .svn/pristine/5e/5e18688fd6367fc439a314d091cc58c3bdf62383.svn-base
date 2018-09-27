/*
 * 文 件 名:  InitSvrHub.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HW
 * 修改时间:  2011-11-24
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.common.cache;

import java.io.IOException;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.common.CommonUtilHub;
import com.gmcc.boss.selfsvc.cache.PublicCache;

/**
 * 定时器控制- [XQ[2011]_11_062]自助终端-重复缴费控制
 * 
 * @author 李锋
 * @version [版本号, 2011-11-24]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class InitSvrHub extends HttpServlet
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(InitSvrHub.class); // 日志类
    
    private static Timer timer = new Timer(); // 定时器
    
    // 默认5分钟
    private static final int REFRESHTIME = 10;
    
    public InitSvrHub()
    {
        super();
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.doGet(req, resp);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.doPost(req, resp);
    }
    
    /**
     * 
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
    
    /**
     * @throws ServletException
     */
    public void init() throws ServletException
    {
        try
        {
            super.init();
            
            RefreshCacheHub refreshCacheHub = RefreshCacheHub.getInstance();
            
            String tmpStr = (String)PublicCache.getInstance().getCachedData(refreshCacheHub.SH_CASH_SEQ_CLEAR_TIME);
            
            int refreshTime = REFRESHTIME;
            
            if (!CommonUtilHub.strIsEmpty(tmpStr))
            {
                try
                {
                    refreshTime = Integer.valueOf(tmpStr);
                }
                catch (NumberFormatException e)
                {
                    logger.error("转换清理时间错误：", e);
                    e.printStackTrace();
                }
            }
            
            timer.schedule(refreshCacheHub, 0, refreshTime * 60L * 1000);
        }
        catch (Exception e)
        {
            logger.error("缓存初始化异常：", e);
            e.printStackTrace();
        }
    }
    
}
