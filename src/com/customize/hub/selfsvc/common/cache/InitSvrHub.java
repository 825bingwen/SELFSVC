/*
 * �� �� ��:  InitSvrHub.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  HW
 * �޸�ʱ��:  2011-11-24
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
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
 * ��ʱ������- [XQ[2011]_11_062]�����ն�-�ظ��ɷѿ���
 * 
 * @author ���
 * @version [�汾��, 2011-11-24]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class InitSvrHub extends HttpServlet
{
    
    /**
     * ע������
     */
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(InitSvrHub.class); // ��־��
    
    private static Timer timer = new Timer(); // ��ʱ��
    
    // Ĭ��5����
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
            logger.error("�����ʱ�����쳣��", e);
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
                    logger.error("ת������ʱ�����", e);
                    e.printStackTrace();
                }
            }
            
            timer.schedule(refreshCacheHub, 0, refreshTime * 60L * 1000);
        }
        catch (Exception e)
        {
            logger.error("�����ʼ���쳣��", e);
            e.printStackTrace();
        }
    }
    
}
