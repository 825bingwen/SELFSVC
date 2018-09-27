/*
 * �� �� ��:  TelProdDiyDaoImpl.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <��ѡ�ײ�>
 * �� �� ��: jWX216858
 * ����ʱ��: 2014-10-10
 */
package com.customize.sd.selfsvc.telProdDiy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.customize.sd.selfsvc.telProdDiy.model.TelProdPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * ��ѡ�ײ�dao
 * @author  jWX216858
 * @version  [�汾��, 2014-10-10]
 * @see  
 * @since 
 */
@Repository
public class TelProdDiyDaoImpl extends BaseDaoImpl
{
    @Autowired
    public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate)
    {
        super.setSqlMapClientTemplate(sqlMapClientTemplate);
    }

	/**
	 * ��ѯ����ͨ���ײ���Ϣ
	 * @param telProdPO
	 * @return
	 */
	public List<TelProdPO> qryVoiceProdList(TelProdPO telProdPO)
	{
		return sqlMapClientTemplate.queryForList("telProdDiy.qryVoiceProdList", telProdPO);
	}
	
	/**
	 * ��ѯ���������ײ���Ϣ
	 * @param telProdPO
	 * @return
	 */
	public List<TelProdPO> qryNetProdList(TelProdPO telProdPO)
	{
		return sqlMapClientTemplate.queryForList("telProdDiy.qryNetProdList", telProdPO);
	}
	
	/**
	 * ����id��ѯ�ײ���Ϣ
	 * @param telProdPO
	 * @return
	 */
	public String qryProdById(TelProdPO telProdPO)
	{
		return (String) sqlMapClientTemplate.queryForObject("telProdDiy.qryProdById", telProdPO);
	}
	
    /**
     * <��ѯ�����Ʒ�ײ�>
     * <������ϸ����>
     * @param telProdPO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-4-29 15:06:06 OR_SD_201503_508_SD_�����ն����������Ʒ��ѡ�ײ͵İ���
     */
    public List<TelProdPO> qryProdList(TelProdPO telProdPO)
    {
        return (List<TelProdPO>)sqlMapClientTemplate.queryForList("telProdDiy.qryProdList", telProdPO);
    }
}
