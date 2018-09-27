package com.gmcc.boss.selfsvc.quickpublish.service;

import java.util.List;

import com.gmcc.boss.selfsvc.quickpublish.dao.QuickPubDaoImpl;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdConfigPO;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdTypePO;

/**
 * 
 * 产品配置serviceImpl
 * <功能详细描述>
 * 
 * @author  cKF76106
 * @version  [版本号, Jul 23, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class QuickPubServiceImpl implements QuickPubService
{
    private QuickPubDaoImpl quickPubDaoImpl;

    /**
     * 查询产品列表
     * @param prodConfigPO
     * @return
     */
    public List<ProdConfigPO> getProdList(ProdConfigPO prodConfigPO)
    {
        return quickPubDaoImpl.getProdList(prodConfigPO);

    }
    
    public List<ProdTypePO> getProdTypeList()
    {
        return quickPubDaoImpl.getProdTypeList();

    }
    
    public QuickPubDaoImpl getQuickPubDaoImpl()
    {
        return quickPubDaoImpl;
    }

    public void setQuickPubDaoImpl(QuickPubDaoImpl quickPubDaoImpl)
    {
        this.quickPubDaoImpl = quickPubDaoImpl;
    }

}
