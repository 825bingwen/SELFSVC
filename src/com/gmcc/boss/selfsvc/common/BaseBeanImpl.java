package com.gmcc.boss.selfsvc.common;

import com.gmcc.boss.selfsvc.call.SelfSvcCall;

/**
 * 
 * 接口调用基类
 * 
 * @author  yKF28472
 * @version  [版本号, Dec 10, 2010]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BaseBeanImpl
{
    protected SelfSvcCall selfSvcCall;

    public void setSelfSvcCall(SelfSvcCall selfSvcCall)
    {
        this.selfSvcCall = selfSvcCall;
    }
  
}
