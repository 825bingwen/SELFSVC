/*
 * 文 件 名:  TelProdDiyDaoImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <自选套餐>
 * 创 建 人: jWX216858
 * 创建时间: 2014-10-10
 */
package com.customize.sd.selfsvc.telProdDiy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.customize.sd.selfsvc.telProdDiy.model.TelProdPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 自选套餐dao
 * @author  jWX216858
 * @version  [版本号, 2014-10-10]
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
	 * 查询语音通话套餐信息
	 * @param telProdPO
	 * @return
	 */
	public List<TelProdPO> qryVoiceProdList(TelProdPO telProdPO)
	{
		return sqlMapClientTemplate.queryForList("telProdDiy.qryVoiceProdList", telProdPO);
	}
	
	/**
	 * 查询上网流量套餐信息
	 * @param telProdPO
	 * @return
	 */
	public List<TelProdPO> qryNetProdList(TelProdPO telProdPO)
	{
		return sqlMapClientTemplate.queryForList("telProdDiy.qryNetProdList", telProdPO);
	}
	
	/**
	 * 根据id查询套餐信息
	 * @param telProdPO
	 * @return
	 */
	public String qryProdById(TelProdPO telProdPO)
	{
		return (String) sqlMapClientTemplate.queryForObject("telProdDiy.qryProdById", telProdPO);
	}
	
    /**
     * <查询主体产品套餐>
     * <功能详细描述>
     * @param telProdPO
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-4-29 15:06:06 OR_SD_201503_508_SD_自助终端增加主体产品自选套餐的办理
     */
    public List<TelProdPO> qryProdList(TelProdPO telProdPO)
    {
        return (List<TelProdPO>)sqlMapClientTemplate.queryForList("telProdDiy.qryProdList", telProdPO);
    }
}
