package com.gmcc.boss.selfsvc.baseService.spService.service;

import java.util.List;

import com.gmcc.boss.selfsvc.baseService.spService.model.SpAvailPO;

/**
 * 全网梦网业务订购service
 * @author Administrator
 *
 */
public interface SpService 
{
	// 查询用户可订购的全网梦网集合
	public List<SpAvailPO> qryAvailSP();
	
	/**
	 * <查找该业务是否在携入用户可订购业务白名单中>
	 * <功能详细描述>
	 * @param spPO
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @remark create by sWX219697 2014-6-30 15:07:56 OR_HUB_201406_1115_湖北跨运营商携号转网
	 */
	public int authWhiteList(SpAvailPO spPO);
}
