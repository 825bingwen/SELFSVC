package com.gmcc.boss.selfsvc.quickpublish.dao;

import java.util.List;

import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdConfigPO;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdTypePO;

/**
 * 
 * 产品查询
 * <功能详细描述>
 * 
 * @author  cKF76106
 * @version  [版本号, Jul 23, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class QuickPubDaoImpl extends BaseDaoImpl
{
    /**
     * 
     * 产品查询
     * @param prodConfigPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ProdConfigPO> getProdList(ProdConfigPO prodConfigPO)
    {
        List<ProdConfigPO> prodList = super.sqlMapClientTemplate.queryForList("quickpublish.getProdList",prodConfigPO);

        return prodList;
    }
    
    
    public List<ProdTypePO> getProdTypeList()
    {
        List<ProdTypePO> prodTypeList = super.sqlMapClientTemplate.queryForList("quickpublish.getProdTypeList");

        return prodTypeList;
    }

}
