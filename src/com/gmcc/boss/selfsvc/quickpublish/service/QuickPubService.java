package com.gmcc.boss.selfsvc.quickpublish.service;

import java.util.List;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdConfigPO;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdTypePO;

/**
 * 
 * 产品配置service
 * <功能详细描述>
 * 
 * @author  cKF76106
 * @version  [版本号, Jun 30, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface QuickPubService
{
    /**
     * 查询产品列表
     * <功能详细描述>
     * @param prodConfigPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ProdConfigPO> getProdList(ProdConfigPO prodConfigPO);
    
    public List<ProdTypePO> getProdTypeList();

    
}
