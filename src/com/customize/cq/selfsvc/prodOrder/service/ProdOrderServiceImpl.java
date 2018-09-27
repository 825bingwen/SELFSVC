package com.customize.cq.selfsvc.prodOrder.service;

import java.util.List;

import com.customize.cq.selfsvc.prodOrder.dao.ProdOrderDaoImpl;
import com.customize.cq.selfsvc.prodOrder.model.ProdOrderPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;

/**
 * 产品查询受理
 * @author z90080209
 * @date   Oct 28, 2011
 */
public class ProdOrderServiceImpl implements ProdOrderService 
{
	private ProdOrderDaoImpl prodOrderDaoImpl;
	
	/**
	 * 查询用户可受理的产品集合
	 */
	public List<ProdOrderPO> qryProdOrderList(NserCustomerSimp customer)
	{
		String brandID = customer.getBrandID();
		String brands = "111";
		//全球通
		if("BrandGotone".equals(brandID))brands = "1__";
		//动感地带
		if("BrandMzone".equals(brandID))brands = "_1_";
		//神州行
		if("BrandSzx".equals(brandID))brands = "__1";
		return prodOrderDaoImpl.qryProdOrderList(brands);
	}
	
	public ProdOrderDaoImpl getProdOrderDaoImpl() 
	{
		return prodOrderDaoImpl;
	}
	
	public void setProdOrderDaoImpl(ProdOrderDaoImpl prodOrderDaoImpl) 
	{
		this.prodOrderDaoImpl = prodOrderDaoImpl;
	}
}
