package com.gmcc.boss.selfsvc.common.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.common.BaseAction;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * 
 * ��¼ת���ַ
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Dec 13, 2010]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class PreResultListenerImpl implements com.opensymphony.xwork2.interceptor.PreResultListener {

    
    /**
     * ��ִ��Action����֮ǰִ��
     * @param invocation
     * @param resultCode
     */
	public void beforeResult(ActionInvocation invocation, String resultCode) {
		
		BaseAction action = (BaseAction)invocation.getAction();
    	
    	Log logger = LogFactory.getLog(action.getClass());
    	
    	logger.info("Forward:"+resultCode);

	}

}
