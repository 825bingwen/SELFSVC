/*
 * 文 件 名:  MarketingEventThread.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  营销事件触发线程
 * 修 改 人:  wWX217192
 * 修改时间:  June 19, 2014
 * @remark create wWX217192 2014/06/19 OR_SD_201403_1491 
 * */
package com.gmcc.boss.selfsvc.common;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.gmcc.boss.common.base.CommandContext;

/**
 * 
 * 营销事件触发借口 进程
 * 
 * @author wWX217192
 * @version [版本号，2014-4-8]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 * 
 */
public class MarketingEventThread implements Runnable {

	/**
	 * 日志信息
	 */
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
	//private static Logger logger = CommandContext.getLog();
	private static Log logger = LogFactory.getLog(MarketingEventThread.class);
	// modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
	
	/**
	 * 提交请求URL
	 */
	private String wsURL;
	
	/**
     * 组装请求报文信息对象
     */
    private OMElement requestMsg;
	
	/**
	 * 调用方法
	 */
	private String operation;
	
	/**
	 * 构造方法
	 * @param wsURL 提交请求URL
	 * @param operation 调用方法
	 * @param requestMsg 组装请求报文信息对象
	 * @remark create wWX217192 20140619 OR_SD_201403_1491
	 */
	public MarketingEventThread(String wsURL,String operation, OMElement requestMsg) {
		this.wsURL = wsURL;
		this.requestMsg = requestMsg;
		this.operation = operation;
	}
	
	/**
	 * 启动进程
	 * @remark create wWX217192 20140619 OR_SD_201403_1491
	 */
	@Override
	public void run() {
		
		if(!wsURL.isEmpty()) {
			try
			{
				Axis2Client axis2Client = new Axis2Client(wsURL, operation);
				axis2Client.invokeWebService(requestMsg);
			}
			catch(AxisFault e)
			{
				logger.debug("WebService 调用失败！");
				e.printStackTrace();
			}
		}
		
	}

}
