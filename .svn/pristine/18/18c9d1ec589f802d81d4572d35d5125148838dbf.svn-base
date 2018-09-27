/*
 * 文 件 名:  RefreshCacheHub.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HW
 * 修改时间:  2011-11-23
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.common.cache;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.common.DateUtilHub;
import com.gmcc.boss.selfsvc.cache.PublicCache;

/**
 * 清理缓存中的数据 <[XQ[2011]_11_062]自助终端-重复缴费控制>
 * 
 * @author LiFeng
 * @version [版本号, 2011-11-23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RefreshCacheHub extends TimerTask
{
    public static final Map<String, String> cashFeeCacher = new Hashtable<String, String>();
    
    // 定时时间，默认5分钟
    private static final int clearTime = 10;
    
    public static final String SH_CASH_SEQ_CLEAR_TIME = "SH_CASH_SEQ_CLEAR_TIME";
    
    private static Log logger = LogFactory.getLog(RefreshCacheHub.class);
    
    private static RefreshCacheHub instance = null;
    
    private RefreshCacheHub()
    {
        
    }
    
    /**
     * 返回类的实例
     * 
     * @return
     */
    public static synchronized RefreshCacheHub getInstance()
    {
        if (instance == null)
        {
            instance = new RefreshCacheHub();
        }
        
        return instance;
    }
    
    public void run()
    {
        logger.info("开始清理内存中的流水号...");
        clearData();
        logger.info("清理完成...");
    }
    
    private void clearData()
    {
        int time = clearTime;
        try
        {
            time = Integer.parseInt((String)PublicCache.getInstance().getCachedData(SH_CASH_SEQ_CLEAR_TIME));
        }
        catch (Exception e)
        {
            time = clearTime;
        }
        String currentDate = DateUtilHub.curOnlyTime();
        
        Vector<String> tmpKey = new Vector<String>();
        
        for (String tmp : cashFeeCacher.keySet())
        {
            if (DateUtilHub.compareTime("yyyyMMddHHmmssSSS", cashFeeCacher.get(tmp), 
                    currentDate, time))
            {
                tmpKey.add(tmp);
            }
        }
        
        for (String key : tmpKey)
        {
            cashFeeCacher.remove(key);
        }
        
    }
    
}
