package com.gmcc.boss.selfsvc.baseService.spService.dao;

import java.util.List;

import com.gmcc.boss.selfsvc.baseService.spService.model.SpAvailPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 梦网业务订购daoImpl
 * @author xkf29026
 *
 */
public class SpDaoImpl extends BaseDaoImpl
{
	/**
	 * 查询用户可订购的全网梦网集合
	 * @return
	 */
	public List<SpAvailPO> qryAvailSP()
	{
		List<SpAvailPO> AvailSPList = super.sqlMapClientTemplate.queryForList("spService.qryAvailSp");
		if(AvailSPList != null)
		{
			return AvailSPList;
		}
		return null;
	}
	
	/**
	 * <判断携入用户要订购的业务是否在业务白名单>
	 * <功能详细描述>
	 * @param spPO
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @remark create by sWX219697 2014-6-30 OR_HUB_201406_1115_湖北跨运营商携号转网
	 */
	public int authWhiteList(SpAvailPO spPO)
	{
		return (Integer)super.sqlMapClientTemplate.queryForObject("spService.findWhiteList", spPO);
	}
}
