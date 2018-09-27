/*
 * 文 件 名:  MainProdChangeDaoImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人: yKF70747
 * 修改时间:  Apr 12, 2012
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.telProdDiy.dao;

import java.util.List;

import com.customize.hub.selfsvc.telProdDiy.model.TelProdPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 主体产品变更DAO
 * @author  yKF70747
 * @version  [版本号, Apr 12, 2012]
 * @see  
 * @since 
 */
public class TelProdDiyDaoImpl extends BaseDaoImpl
{
    /**
     * 根据用户的当前主体产品ID、归属地区、品牌查询可转换的主体产品信息列表
     * @return
     * @see 
     */
    public List<TelProdPO> qryTelProdList(TelProdPO telProdPO)
    {
        return sqlMapClientTemplate.queryForList("telProdDiy.qryTelProdList", telProdPO);
    }
    
}
