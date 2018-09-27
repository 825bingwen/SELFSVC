/*
 * 文件名：RefreshCache.java
 * 版权：CopyRight 2000-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：H60010815
 * 修改时间：2006-3-30
 * 修改内容：新增
 * 
 * 修改人2：ZhouJingxin
 * 修改时间2：2010-11-11
 * 修改内容2：当数据从数据库中取出来后，再进行清空cache操作。
 * 
 * 修改人3：g00140516
 * 修改时间3：2010-11-30
 * 修改内容3：修改，以适应自助终端的需要
 */
package com.gmcc.boss.selfsvc.cache;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.Level;

import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.resdata.model.ResDataPO;
import com.gmcc.boss.selfsvc.resdata.model.TermInfosPO;
import com.gmcc.boss.selfsvc.resdata.service.ResDataService;
import com.gmcc.boss.selfsvc.util.ApplicationContextUtil;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DateUtil;

/**
 * 定时任务，从配置文件、数据库中加载配置信息；加载菜单信息；加载region信息；进行文件同步
 * 
 * @author H60010815, g00140516
 * @version 2.0 2010.11.30
 */
public class RefreshCache extends TimerTask
{
    private static Log logger = LogFactory.getLog(RefreshCache.class);
    
    private Cache cacher = null; // cache类
    
    //同步文件时会用到
    private String rootPath = null;
    
    private static RefreshCache instance = null;
    
    /**
     * 返回类的实例
     * 
     * @return
     */
    public static synchronized RefreshCache getInstance()
    {
        if (instance == null)
        {
            instance = new RefreshCache();
        }
        
        return instance;
    }
    
    /**
     * 设置根目录
     * @param rootPath
     */
    public void setRootPath(String rootPath)
    {
    	this.rootPath = rootPath;
    }  
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        refreshData();
    }
    
    /**
     * 更新系统cache
     */
    private void refreshData()
    {
    	cacher = PublicCache.getInstance();
    	
    	//加载selfsvc.properties中的配置数据
    	cacheConfigDataFromFile(cacher);
    	
    	//加载SH_PARAM表中的配置数据
    	cacheConfigDataFromDB(cacher);
    	
    	//把log4j的日志级别在数据库中设置一下，可以定时刷新。修改日志级别后，不需要重启应用    
    	try
        {    		
    		// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
            // 重置日志级别
            String logLevel = (String) cacher.getCachedData(Constants.LOG4J_LEVEL);            
//            Logger.getRootLogger().setLevel(Level.toLevel(logLevel));
    		logger.info("当前日志级别为："+LogManager.getLogger(RefreshCache.class).getLevel());
    	    LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
    	    Configuration conf = ctx.getConfiguration();
    	    conf.getLoggerConfig(LogManager.ROOT_LOGGER_NAME).setLevel(Level.toLevel(logLevel));
    	    ctx.updateLoggers(conf);
    	    logger.info("重置log4j的日志级别结束，当前日志级别为：" + LogManager.getLogger(RefreshCache.class).getLevel());
            
//    		String logFile = (String) cacher.getCachedData(Constants.LOG4J_FILE);
//    		File file = new File(logFile);
//    		if(file.exists())// 如果日志文件存在
//    		{
//    			logger.info("当前日志级别："+LogManager.getLogger(RefreshCache.class).getLevel());
//                LoggerContext context =(LoggerContext)LogManager.getContext(false);
//    			context.setConfigLocation(file.toURI());
//    			context.reconfigure();
//    			logger.info("重置log4j的日志级别结束，当前日志级别为：" + LogManager.getLogger(RefreshCache.class).getLevel());
//    		}

//            if (Constants.PROOPERORGID_SD.equals((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
//            {
//                Logger.getLogger("org.springframework").setLevel(Level.toLevel(logLevel));
//                Logger.getLogger("com.ibatis").setLevel(Level.toLevel(logLevel));
//                Logger.getLogger("com.ibatis.common.jdbc.SimpleDataSource").setLevel(Level.toLevel(logLevel));
//                Logger.getLogger("com.ibatis.common.jdbc.ScriptRunner").setLevel(Level.toLevel(logLevel));
//                Logger.getLogger("com.ibatis.sqlmap.engine.impl.SqlMapClientDelegate").setLevel(Level.toLevel(logLevel));
//                Logger.getLogger("java.sql.Connection").setLevel(Level.toLevel(logLevel));
//                Logger.getLogger("java.sql.Statement").setLevel(Level.toLevel(logLevel));
//                Logger.getLogger("java.sql.PreparedStatement").setLevel(Level.toLevel(logLevel));
//                Logger.getLogger("java.sql.ResultSet").setLevel(Level.toLevel(logLevel));
//            }
    	 // modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本

        }
        catch (Exception ex)
        {            
            logger.error("重置log4j的日志级别异常：", ex);
        }
//        finally
//        {
//        	if (logger.isInfoEnabled())
//            {
//            	logger.info("重置log4j的日志级别结束，当前日志级别为：" + Logger.getRootLogger().getLevel());
//            }
//        }
        
        //加载菜单
        cacheMenuitems(cacher);
        
        //加载region信息
        cacheRegionInfo(cacher);
        
        // 加载字典表
        cacheDictItems(cacher);
        
        //add begin lwx439898 2017-10-16 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
        //湖北专用
        if(Constants.PROOPERORGID_HUB.equals((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
        {           
            //加载终端属性信息
            cacheTreminalInfos(cacher);
        }
        //add end lwx439898 2017-10-16 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
        
        //将屏保、广告、自助售货图片、优惠打折图片由本机目录同步至应用内的目录
        SSMediaFileFresh.synchronizeFiles(rootPath);
        
        /**
         * 将sh_info_rectel表中的数据加载到缓存中
         */
        cacheRectelInfo(cacher);
        
        // add begin jWX216858 2015-5-13
        if (CommonUtil.isEmpty(CommonUtil.getParamValue(Constants.SH_JSVERSION)))
        {
        	// 当前时间，格式：20150513
        	String currTime = DateUtil.getNoFormatToday();
        	
        	// 设置js版本号，例：20150513 解决IE客户端缓存未清理导致JS未更新
        	cacher.cache(Constants.SH_JSVERSION, currTime);
        }
        // add end jWX216858 2015-5-13
    }
   
    /**
     * 从配置文件selfsvc.properties中加载配置信息
     * @param cacher
     */
    private void cacheConfigDataFromFile(Cache cacher)
    {
    	if (logger.isInfoEnabled())
        {
        	logger.info("从文件selfsvc.properties中加载配置信息。。。");
        }
    	
    	Properties prop = null;    	
    	try
    	{
    		prop = new Properties();
    		prop.load(this.getClass().getClassLoader().getResourceAsStream("selfsvc.properties"));
    		Enumeration<Object> keys = prop.keys();
    		if (keys != null)
    		{
    			String key = "";
    			String value = "";
    			while (keys.hasMoreElements())
    			{
    				key = (String) keys.nextElement();
    				if (key == null || key.trim().length() == 0)
    				{
    					continue;
    				}
    				value = (String) prop.getProperty(key);
    				if (value == null)
    				{
    					value = "";
    				}
    				
    				cacher.cache(key.trim(), value.trim());
    			}
    		}
    	}
    	catch (Exception e)
    	{
    		logger.error("从文件selfsvc.properties中加载配置信息异常：", e);
    	}
    	finally
    	{
    		if (prop != null)
    		{
    			prop.clear();
    		}
    	}
    	
    	if (logger.isInfoEnabled())
        {
        	logger.info("从配置文件selfsvc.properties中加载配置信息结束");
        }
    }
    
    /**
     * 从表SH_PARAM中加载配置信息
     * @param cacher
     */
    private void cacheConfigDataFromDB(Cache cacher)
    {
    	if (logger.isInfoEnabled())
        {
        	logger.info("从表SH_PARAM中加载配置信息。。。");
        }
    	
    	ResDataService service = (ResDataService) ApplicationContextUtil.getBean("resDataService");
    	List data = service.getAllResDataList();
    	if (data != null && data.size() > 0)
    	{
    		if (logger.isInfoEnabled())
            {
            	logger.info("表SH_PARAM中配置信息条数：" + data.size());
            }
    		
    		ResDataPO resDataPO = null;
    		String key = "";
    		String value = "";
    		for (int i = 0; i < data.size(); i++)
    		{
    			resDataPO = (ResDataPO) data.get(i);
    			key = resDataPO.getReskey();
    			if (key == null || key.trim().length() == 0)
 				{
    				continue;
 				}
    			value = resDataPO.getResval();
    			if (value == null)
 				{
    				value = "";
 				}
    			
    			cacher.cache(key.trim(), value.trim());
    		}
    	}
    	
    	if (logger.isInfoEnabled())
        {
        	logger.info("从表SH_PARAM中加载配置信息结束");
        }
    }
    
    /**
     * 加载菜单信息
     * @param cacher
     */
    private void cacheMenuitems(Cache cacher)
    {
        logger.info("加载菜单信息。。。");
        	
    	List<MenuInfoPO> data = null;
    	ResDataService service = (ResDataService) ApplicationContextUtil.getBean("resDataService");
    	data = (List<MenuInfoPO>) service.getAllMenuInfoList();
    	if(data != null && data.size() > 0)
    	{
    		
    		String lastGroup = "";
            String nextGroup = "";
            String currGroup = "";
            MenuInfoPO menuitem = null;
            List<MenuInfoPO> subItems = null;
            for (int i = 0; i < data.size(); i++)
            {
            	menuitem = (MenuInfoPO) data.get(i);
            	
                nextGroup = menuitem.getTermgrpid();
                
                if (!currGroup.equalsIgnoreCase(nextGroup))
                {
                    if (subItems != null && subItems.size() > 0)
                    {
                        cacher.cache(currGroup, subItems);
                        
                        logger.info("菜单表中termgrpid=" + currGroup + "的数据共" + subItems.size() + "条。");
                    }
                    
                    currGroup = nextGroup;
                }
                
                if (!lastGroup.equalsIgnoreCase(currGroup))
                {
                    lastGroup = currGroup;
                    subItems = new ArrayList<MenuInfoPO>();
                }
                
                subItems.add(menuitem);
            }
            
            if (subItems != null && subItems.size() > 0)
            {
                cacher.cache(currGroup, subItems);
                
                logger.info("菜单表中termgrpid=" + currGroup + "的数据共" + subItems.size() + "条。");
            }
    		
    	}
    	if (data != null && data.size() > 0)
    	{
            logger.info("菜单个数：" + data.size());
    		
    		cacher.cache(Constants.MENU_INFO, data);
    	}    	
    	
        logger.info("加载菜单信息结束");
    }
    
    /**
     * 加载region信息
     * @param cacher
     */
    private void cacheRegionInfo(Cache cacher)
    {
    	if (logger.isInfoEnabled())
        {
        	logger.info("加载region信息。。。");
        }
    	
    	ResDataService service = (ResDataService) ApplicationContextUtil.getBean("resDataService");
    	List data = service.getAllRegionInfoList();
    	if (data != null && data.size() > 0)
    	{
    		if (logger.isInfoEnabled())
            {
            	logger.info("region条数：" + data.size());
            }
    		
    		cacher.cache(Constants.REGION_INFO, data);
    	}    	
    	
    	if (logger.isInfoEnabled())
        {
        	logger.info("加载region信息结束");
        }
    }
    
    /**
     * 加载字典数据
     * 
     * @param cacher
     * @see
     */
    private void cacheDictItems(Cache cacher)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("加载字典数据。。。");
        }
        
        ResDataService service = (ResDataService)ApplicationContextUtil.getBean("resDataService");
        List<DictItemPO> data = service.getDictItems();
        if (data != null && data.size() > 0)
        {
            String lastGroup = "";
            String nextGroup = "";
            String currGroup = "";
            DictItemPO dictitem = null;
            List<DictItemPO> subItems = null;
            for (int i = 0; i < data.size(); i++)
            {
                dictitem = (DictItemPO)data.get(i);
                nextGroup = dictitem.getGroupid();
                
                if (!currGroup.equalsIgnoreCase(nextGroup))
                {
                    if (subItems != null && subItems.size() > 0)
                    {
                        cacher.cache(currGroup, subItems);
                        
                        if (logger.isInfoEnabled())
                        {
                            logger.info("字典表中groupid=" + currGroup + "的数据共" + subItems.size() + "条。");
                        }
                    }
                    
                    currGroup = nextGroup;
                }
                
                if (!lastGroup.equalsIgnoreCase(currGroup))
                {
                    lastGroup = currGroup;
                    subItems = new ArrayList<DictItemPO>();
                }
                
                subItems.add(dictitem);
            }
            
            if (subItems != null && subItems.size() > 0)
            {
                cacher.cache(currGroup, subItems);
                
                if (logger.isInfoEnabled())
                {
                    logger.info("字典表中groupid=" + currGroup + "的数据共" + subItems.size() + "条。");
                }
            }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("加载字典数据结束");
        }
    }
    
    /**
     * 将sh_info_rectel表中的数据加载到缓存中，记录sh_rec_log时用
     * 
     * @param cacher
     * @see 
     * @remark create g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
     */
    private void cacheRectelInfo(Cache cacher)
    {
        // 先从缓存中去掉
        cacher.remove(Constants.SH_INFO_RECTEL);
        
        ResDataService service = (ResDataService) ApplicationContextUtil.getBean("resDataService");
        Map<String, String> rectelInfo = service.getRectelInfo();
        if (null != rectelInfo && rectelInfo.size() > 0)
        {
            // 存到缓存中
            cacher.cache(Constants.SH_INFO_RECTEL, rectelInfo);
        }
    }
    
    /**
     * 将Sh_Term_externattr表中的数据加载到缓存中
     * 
     * @param cacher
     * @see 
     * @remark create lwx439898 2017-10-12 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    private void cacheTreminalInfos(Cache cacher)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("加载终端设备信息。。。");
        }
        
        ResDataService service = (ResDataService) ApplicationContextUtil.getBean("resDataService");
        List<TermInfosPO> termInfos = service.getAllTermInfos();
        if (termInfos != null && termInfos.size() > 0)
        {
            logger.info("终端个数：" + termInfos.size());
            
            cacher.cache(Constants.TERM_INFO, termInfos);
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("加载终端设备信息结束");
        }
    }
    
}
