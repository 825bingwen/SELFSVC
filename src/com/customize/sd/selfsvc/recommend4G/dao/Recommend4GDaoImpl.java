/*
 * �� �� ��:  Recommend4GDaoImpl.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  4G�ն��Ƽ�Dao��
 * �� �� ��:  jWX216858
 * �޸�ʱ��:  2014-12-19
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.recommend4G.dao;

import java.util.List;

import com.customize.sd.selfsvc.recommend4G.model.Recommend4GPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 4G�ն��Ƽ�Dao��
 * 
 * @author  jWX216858
 * @version  [�汾��, 2014-12-19]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ����� 
 */
public class Recommend4GDaoImpl extends BaseDaoImpl
{
	/** 
     * ��ȡ�ֻ��б�
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2014-12-22 OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ�����
     */
	public List<Recommend4GPO> getPhoneList(Recommend4GPO recommend4GPO)
	{
		return sqlMapClientTemplate.queryForList("recommend4G.qryPhoneList", recommend4GPO);
	}
	
}
