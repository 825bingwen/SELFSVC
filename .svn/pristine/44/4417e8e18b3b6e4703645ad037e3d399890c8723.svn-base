/*
 * @filename: RefreshCacheNX.java
 * @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
 * @brif:  缓存缴费信息，并定时清除
 * @author: g00140516
 * @de:  2012/10/08 
 * @description: 
 * @remark: create g00140516 2012/10/08 R003C12L09n01 规避缴费重复问题
 */
package com.customize.nx.selfsvc.cache;

import java.util.Hashtable;
import java.util.Map;
import java.util.TimerTask;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.common.DateUtilHub;
import com.gmcc.boss.selfsvc.cache.PublicCache;

/**
 * 缓存缴费信息，并定时清除
 * 
 * @author g00140516
 * @version 1.0 2012.10.08
 */
public class RefreshCacheNX extends TimerTask
{
    private static Log logger = LogFactory.getLog(RefreshCacheNX.class);
    
    public static final Map<String, String> cashChargeRecords = new Hashtable<String, String>();
    
    private static RefreshCacheNX instance = null;
    
    /**
     * 返回类的实例
     * 
     * @return
     */
    public static synchronized RefreshCacheNX getInstance()
    {
        if (instance == null)
        {
            instance = new RefreshCacheNX();
        }
        
        return instance;
    }  
    
    public void run()
    {
        if (logger.isInfoEnabled())
        {
            logger.info("开始清理缓存中保存的缴费信息");
        }
        
        refreshData();
        
        if (logger.isInfoEnabled())
        {
            logger.info("清理结束");
        }
    }
    
    /**
     * 清除缓存中过期数据
     */
    private void refreshData()
    {
    	String cashChargeFlag = (String) PublicCache.getInstance().getCachedData("SH_CASHCHARGE_SEQLOG_FLAG");
        if ("1".equalsIgnoreCase(cashChargeFlag))
        {            
            synchronized(cashChargeRecords)
            {
                String currentDate = DateUtilHub.curOnlyTime();
                
                String strCashChargeTime = (String) PublicCache.getInstance().getCachedData("SH_CASHCHARGE_SEQLOG_TIME");
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
                
                if (logger.isInfoEnabled())
                {
                    logger.info("清理" + cashChargeTime +"分钟前的数据，剩余信息有：");
                }
                
                Vector<String> tmpKey = new Vector<String>();
                
                for (String tmp : cashChargeRecords.keySet())
                {
                    if (DateUtilHub.compareTime("yyyyMMddHHmmssSSS", cashChargeRecords.get(tmp), 
                            currentDate, cashChargeTime))
                    {
                        tmpKey.add(tmp);
                    }
                    else if (logger.isInfoEnabled())
                    {
                        logger.info(tmp);
                    }
                }
                
                for (String key : tmpKey)
                {
                    cashChargeRecords.remove(key);
                }   
            }           
        }
    }
}
