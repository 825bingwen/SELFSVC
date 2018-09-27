package com.gmcc.boss.selfsvc.media.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.prodInstall.action.ChoSerNOUserAction;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.media.service.MediaService;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 多媒体播放播放Action
 * 终端机生成广告播放配置文件adv.wpl
 * 终端机生成屏保播放配置文件sc.wpl
 * @author  yKF28472
 * @version  [版本号, 2010-12-06]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MediaAction extends BaseAction
{
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
	//private static final Logger log = Logger.getLogger(MediaAction.class);
	private static Log log = LogFactory.getLog(MediaAction.class);
	// modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
	
	// 通过spring注入生成service对象
	private MediaService mediaService;
	
    /** 
     * 取屏保资源配置文件内容
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
	public String getMediaScList() 
	{
		// 终端信息
		TerminalInfoPO terminalInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
		
		// 处理返回信息
		this.getResponse().setContentType("text/xml;charset=UTF-8");

        PrintWriter writer = null;
        try
        {
            writer = this.getResponse().getWriter();
        }
        catch (IOException e)
        {
        	// begin zKF66389 9月份findbugs修改
            //e.printStackTrace();
        	log.error(e.toString());
        	// end zKF66389 9月份findbugs修改
        }
        
        // 参数
        String termid = terminalInfo.getTermid();
        String region = terminalInfo.getRegion();
        
        // 获取代码相对路径 modify begin wWX217192 2014-12-18 for 山东现场遗留问题
        String servletContext = "";
        if(Constants.PROOPERORGID_SD.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
        {
        	servletContext = this.getRequest().getSession().getServletContext().getRealPath("/");
        }
        else
        {
        	servletContext = getClass().getClassLoader().getResource("/").getPath();
        }
        // 获取代码相对路径 modify end wWX217192 2014-12-18 for 山东现场遗留问题
        
        int index = servletContext.indexOf("WEB-INF");
        if (index != -1)
        {
            servletContext = servletContext.substring(0, index);
        }
        
        // 执行
        String xml = "0";
        
        // modify begin wWX217192 2014-09-19 OR_HUB_201403_1773 自助终端LOGO更换及屏保一键更新功能
        if(useProvinceSet())
        {
        	xml = mediaService.getProvinceScList(Constants.SC_TYPE, servletContext);
        }
        else
        {
        	// 结果
        	xml = mediaService.getMediaWpl(termid, region, Constants.SC_TYPE, servletContext);
        }
        // modify end wWX217192 2014-09-19 OR_HUB_201403_1773    
        
        // 返回结果
        // begin zKF66389 9月份findbugs修改
        if (xml != null)
        {
        	writer.println(xml);
        }
        // end zKF66389 9月份findbugs修改
        
		return null;
	}

    /** 
     * 判断当前省份是否使用省统一配置
     * 如果当前省份为湖北或山东并且屏保开关打开那么使用省统一屏保设置
     * @return true：启用全省统一屏保配置；false：未启用全省统一屏保配置
     * @see [类、类#方法、类#成员]
     * @remark create by l00263786 2015-06-02 OR_SD_201504_102 自助终端屏保实现全省统一配置
     */
    private boolean useProvinceSet()
    {
        return (Constants.PROOPERORGID_HUB.equals(CommonUtil.getParamValue(Constants.PROVINCE_ID))
                ||Constants.PROOPERORGID_SD.equals(CommonUtil.getParamValue(Constants.PROVINCE_ID)))
        		&& "1".equals(CommonUtil.getParamValue(Constants.SH_SCREEN_FLAG));
    }
    
    /** 
     * 取广告资源配置文件内容
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
	public String getMediaAdvList() 
	{
		// 终端信息
		TerminalInfoPO terminalInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
		
		// 处理返回信息
		this.getResponse().setContentType("text/xml;charset=UTF-8");

        PrintWriter writer = null;
        try
        {
            writer = this.getResponse().getWriter();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        // 参数
        String termid = terminalInfo.getTermid();
        String region = terminalInfo.getRegion();
        String realPath = this.getRequest().getSession().getServletContext().getRealPath("/");
        
        // 执行
        String xml = "0"; // 存返回结果
        
        xml = mediaService.getMediaWpl(termid, region, Constants.ADV_TYPE, realPath);
        
        // begin zKF66389 9月份findbugs修改
        if (xml != null)
        {
        	writer.println(xml);
        }
        // end zKF66389 9月份findbugs修改
        
		return null;
	}
	
	/**
	 * 广告播放主页面
	 * <功能详细描述>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String advIndex() 
    {
	    return SUCCESS;
    }
  	
	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

}