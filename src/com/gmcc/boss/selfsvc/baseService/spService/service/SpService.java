package com.gmcc.boss.selfsvc.baseService.spService.service;

import java.util.List;

import com.gmcc.boss.selfsvc.baseService.spService.model.SpAvailPO;

/**
 * ȫ������ҵ�񶩹�service
 * @author Administrator
 *
 */
public interface SpService 
{
	// ��ѯ�û��ɶ�����ȫ����������
	public List<SpAvailPO> qryAvailSP();
	
	/**
	 * <���Ҹ�ҵ���Ƿ���Я���û��ɶ���ҵ���������>
	 * <������ϸ����>
	 * @param spPO
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark create by sWX219697 2014-6-30 15:07:56 OR_HUB_201406_1115_��������Ӫ��Я��ת��
	 */
	public int authWhiteList(SpAvailPO spPO);
}
