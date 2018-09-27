package com.customize.cq.selfsvc.prodOrder.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.customize.cq.selfsvc.prodOrder.model.ProdOrderPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 产品受理daoImpl
 * @author z90080209
 * @date   Oct 28, 2011
 */
public class ProdOrderDaoImpl extends BaseDaoImpl
{
	/**
	 * 查询用户可受理的产品集合
	 * @return
	 */
	public List<ProdOrderPO> qryProdOrderList(String brands)
	{
		Map<String, String> map=new HashMap<String, String>();
    	map.put("brands", brands);
		List<ProdOrderPO> prodList = super.sqlMapClientTemplate.queryForList("prodOrder.qryProdOrderList",map);
		if(prodList != null)
		{
			return prodList;
		}
		return null;
	}
}
