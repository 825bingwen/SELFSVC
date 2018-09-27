package com.gmcc.boss.selfsvc.valueCard.dao;

import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

public class ValueCardDaoImpl extends BaseDaoImpl
{

	/**
     * <验证该缴费号码是否省内用户>
     * <功能详细描述>
     * @param region
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2015-05-06 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     */
    public int anthTelnum(String region)
    {
    	return (Integer)sqlMapClientTemplate.queryForObject("charge.isSdTelnum",region);
    }
}
