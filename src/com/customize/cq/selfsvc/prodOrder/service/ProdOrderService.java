package com.customize.cq.selfsvc.prodOrder.service;

import java.util.List;

import com.customize.cq.selfsvc.prodOrder.model.ProdOrderPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;

/**
 * ��Ʒ��ѯ����
 * @author z90080209
 * @date   Oct 28, 2011
 */
public interface ProdOrderService 
{
	// ��ѯ�û�������Ĳ�Ʒ����
	public List<ProdOrderPO> qryProdOrderList(NserCustomerSimp customer);
}
