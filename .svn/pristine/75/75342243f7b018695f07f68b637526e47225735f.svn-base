/*
 * 文 件 名:  ProdChangeDaoImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  hWX5316476
 * 修改时间:  Jan 5, 2015
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.prodChange.dao;

import java.util.List;

import com.customize.sd.selfsvc.prodChange.model.ProdChangePO;
import com.customize.sd.selfsvc.prodChange.model.ProdTemplatePO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 套餐资费变更
 * 
 * @author  hWX5316476
 * @version  [版本号, Jan 5, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProdChangeDaoImpl extends BaseDaoImpl
{
    /**
     * 查询产品下组内可转换的档次列表
     * 
     * @param prodChangePO
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public List<ProdTemplatePO> qryLevelByProdId(ProdChangePO prodChangePO)
    {
        return sqlMapClientTemplate.queryForList("prodChange.qryLevelByProdId", prodChangePO);
    }
}
