package com.customize.nx.selfsvc.common.service;

import com.customize.nx.selfsvc.call.SelfSvcCallNX;
import com.gmcc.boss.selfsvc.common.service.BaseServiceImpl;

/**
 * 
 * <宁夏基础的Service调用类>
 * <功能详细描述>
 * 
 * @author  wWX217192
 * @version  [版本号, Apr 22, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark create by wWX219697 OR_SD_201502_373_山东_关于自助终端承载和娱乐包新业务的支撑需求
 */
public class BaseServiceNXImpl extends BaseServiceImpl
{
	private SelfSvcCallNX selfSvcCallNX;

    public SelfSvcCallNX getSelfSvcCallNX()
    {
        return selfSvcCallNX;
    }

    public void setSelfSvcCallNX(SelfSvcCallNX selfSvcCallNX)
    {
        this.selfSvcCallNX = selfSvcCallNX;
    }
}
