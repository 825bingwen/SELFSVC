package com.customize.hub.selfsvc.prodChange.service;

import java.util.List;

import com.customize.hub.selfsvc.prodChange.dao.ProdChangeDaoHubImpl;
import com.customize.hub.selfsvc.prodChange.model.ProdLogVO;
import com.customize.hub.selfsvc.prodChange.model.TemplateFilterInfoVO;

public class ProdChangeServiceHubImpl implements ProdChangeHubService
{
    
    private ProdChangeDaoHubImpl prodChangeDaoHubImpl;
    
    public void createProdLog(ProdLogVO prodLogVO)
    {
        prodChangeDaoHubImpl.createProdLog(prodLogVO);
        
    }
    
    public void updateProdLog(ProdLogVO prodLogVO)
    {
        prodChangeDaoHubImpl.updateProdLog(prodLogVO);
    }
    
    public ProdChangeDaoHubImpl getProdChangeDaoHubImpl()
    {
        return prodChangeDaoHubImpl;
    }
    
    public void setProdChangeDaoHubImpl(ProdChangeDaoHubImpl prodChangeDaoHubImpl)
    {
        this.prodChangeDaoHubImpl = prodChangeDaoHubImpl;
    }
    
    /**
     * 获取变更产品的过滤模板
     * 
     * @param templateFilterInfo
     * @return
     */
    public List<TemplateFilterInfoVO> qryTempFilterInfo(TemplateFilterInfoVO templateFilterInfo)
    {
        return this.prodChangeDaoHubImpl.qryTempFilterInfo(templateFilterInfo);
    }
    
}
