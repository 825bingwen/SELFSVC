package com.gmcc.boss.selfsvc.baseService.spService.service;

import java.util.List;

import com.gmcc.boss.selfsvc.baseService.spService.dao.SpDaoImpl;
import com.gmcc.boss.selfsvc.baseService.spService.model.SpAvailPO;

/**
 * 全网梦网业务订购
 * @author xkf29026
 *
 */
public class SpServiceImpl implements SpService 
{
	private SpDaoImpl spDaoImpl;
	
	/**
	 * 查询用户可订购的全网梦网集合
	 */
	public List<SpAvailPO> qryAvailSP()
	{
		return spDaoImpl.qryAvailSP();
	}
	
	/**
	 * 查找该业务是否在携入用户可订购业务白名单
	 * @param spPO
	 * @return
	 * @remark create by sWX219697 2014-6-30 OR_HUB_201406_1115_湖北跨运营商携号转网
	 */
	public int authWhiteList(SpAvailPO spPO)
	{
		return spDaoImpl.authWhiteList(spPO);
	}
	
	public SpDaoImpl getSpDaoImpl() 
	{
		return spDaoImpl;
	}
	
	public void setSpDaoImpl(SpDaoImpl spDaoImpl) 
	{
		this.spDaoImpl = spDaoImpl;
	}
}
