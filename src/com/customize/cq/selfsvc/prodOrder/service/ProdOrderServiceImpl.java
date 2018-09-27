package com.customize.cq.selfsvc.prodOrder.service;

import java.util.List;

import com.customize.cq.selfsvc.prodOrder.dao.ProdOrderDaoImpl;
import com.customize.cq.selfsvc.prodOrder.model.ProdOrderPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;

/**
 * ��Ʒ��ѯ����
 * @author z90080209
 * @date   Oct 28, 2011
 */
public class ProdOrderServiceImpl implements ProdOrderService 
{
	private ProdOrderDaoImpl prodOrderDaoImpl;
	
	/**
	 * ��ѯ�û�������Ĳ�Ʒ����
	 */
	public List<ProdOrderPO> qryProdOrderList(NserCustomerSimp customer)
	{
		String brandID = customer.getBrandID();
		String brands = "111";
		//ȫ��ͨ
		if("BrandGotone".equals(brandID))brands = "1__";
		//���еش�
		if("BrandMzone".equals(brandID))brands = "_1_";
		//������
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
