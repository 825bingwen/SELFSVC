/*
 * 文 件 名:  Axis2Client.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  axis调 webservice客户端
 * 修 改 人:  wWX217192
 * 修改时间:  June 18, 2014
 * @remark create wWX217192 2014/06/18 OR_SD_201403_1491 
 * */
package com.gmcc.boss.selfsvc.common;


import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.cache.PublicCache;

/**
 * 
 * axis调用webservice客户端
 * 
 * @author wWX217192
 * @version [版本号，2014-4-8]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 * 
 */
public class Axis2Client 
{
	
	/**
	 * 日志
	 */
	private static Log logger = LogFactory.getLog(Axis2Client.class);
	
	/**
	 * 客户端调用WebService
	 */
	private ServiceClient serviceClient;
	
	/**
	 * 设置调用WebService的URL
	 */
	private EndpointReference targetEPR;
	
	/**
	 * 构造函数 传入wsURL地址和调用方法
	 * @param endPointReference 提交请求url
     * @param operation 调用方法
     * @remark create wWX217192 20140619 OR_SD_201403_1491 
	 */
	public Axis2Client(String epf, String operation) throws AxisFault 
	{
		
		logger.debug("WebService访问地址：" + epf);
		
		// 调用webservice
		serviceClient = new ServiceClient();
		
		// 指定调用WebServiceURL
		targetEPR = new EndpointReference(epf);
		
		// 设定参数
		Options options = serviceClient.getOptions();
		options.setAction(operation);
		options.setTo(targetEPR);
		
		// 客户端调用webService超时时间(秒)
		String axis2ClientOutTime = (String) PublicCache.getInstance().getCachedData("SH_AXISTOCLIENT_OUTTIME");
		
		int outTime = 4000;
		
		// 当数据库未配置，或配置错误时，默认 4000 秒
		if(StringUtils.isNotEmpty(axis2ClientOutTime))
		{
		    try
            {
		        outTime = Integer.parseInt(axis2ClientOutTime);
            }
            catch (Exception e)
            {
                outTime = 4000;
            }
		}
		
		options.setTimeOutInMilliSeconds(outTime*1000L);
        options.setManageSession(true);
        options.setProperty(org.apache.axis2.transport.http.HTTPConstants.SO_TIMEOUT, Integer.valueOf(outTime*1000));
        
        logger.debug("指定调用WebServiceURL：" + targetEPR);
		
	}
	
	/**
	 * webService服务调用
	 * 
	 * @param requestXML 入参
	 * @param operation 要调用的方法
	 * @return String
	 * @throws AxisFault
	 * @remark create wWX217192 20140619 OR_SD_201403_1491 
	 * @see [类、类#方法、类#成员]
	 */
	public String invokeWebService(OMElement requestMsg) throws AxisFault 
	{
        logger.debug("WebService发送组装的报文信息：" + requestMsg.toString());
        
        // 调用方法并输出该方法返回值
        OMElement responseMsg = serviceClient.sendReceive(requestMsg);
        
        logger.debug("WebService返回报文信息：" + responseMsg.toString());
        
        return responseMsg.toString();
	}
	
}
