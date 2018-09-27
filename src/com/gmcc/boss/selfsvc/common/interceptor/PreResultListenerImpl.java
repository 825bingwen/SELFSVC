package com.gmcc.boss.selfsvc.common.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.common.BaseAction;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * 
 * 记录转向地址
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Dec 13, 2010]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PreResultListenerImpl implements com.opensymphony.xwork2.interceptor.PreResultListener {

    
    /**
     * 在执行Action方法之前执行
     * @param invocation
     * @param resultCode
     */
	public void beforeResult(ActionInvocation invocation, String resultCode) {
		
		BaseAction action = (BaseAction)invocation.getAction();
    	
    	Log logger = LogFactory.getLog(action.getClass());
    	
    	logger.info("Forward:"+resultCode);

	}

}
