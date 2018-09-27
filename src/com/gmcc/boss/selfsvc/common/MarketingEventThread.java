/*
 * �� �� ��:  MarketingEventThread.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  Ӫ���¼������߳�
 * �� �� ��:  wWX217192
 * �޸�ʱ��:  June 19, 2014
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
 * Ӫ���¼�������� ����
 * 
 * @author wWX217192
 * @version [�汾�ţ�2014-4-8]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 * 
 */
public class MarketingEventThread implements Runnable {

	/**
	 * ��־��Ϣ
	 */
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
	//private static Logger logger = CommandContext.getLog();
	private static Log logger = LogFactory.getLog(MarketingEventThread.class);
	// modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
	
	/**
	 * �ύ����URL
	 */
	private String wsURL;
	
	/**
     * ��װ��������Ϣ����
     */
    private OMElement requestMsg;
	
	/**
	 * ���÷���
	 */
	private String operation;
	
	/**
	 * ���췽��
	 * @param wsURL �ύ����URL
	 * @param operation ���÷���
	 * @param requestMsg ��װ��������Ϣ����
	 * @remark create wWX217192 20140619 OR_SD_201403_1491
	 */
	public MarketingEventThread(String wsURL,String operation, OMElement requestMsg) {
		this.wsURL = wsURL;
		this.requestMsg = requestMsg;
		this.operation = operation;
	}
	
	/**
	 * ��������
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
				logger.debug("WebService ����ʧ�ܣ�");
				e.printStackTrace();
			}
		}
		
	}

}
