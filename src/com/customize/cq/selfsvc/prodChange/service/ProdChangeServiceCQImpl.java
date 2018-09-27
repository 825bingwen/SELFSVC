package com.customize.cq.selfsvc.prodChange.service;

import java.util.List;

import com.customize.cq.selfsvc.prodChange.dao.ProdChangeDaoCQImpl;
import com.customize.cq.selfsvc.prodChange.model.ProdLogVO;
import com.customize.cq.selfsvc.prodChange.model.TemplateFilterInfoVO;

public class ProdChangeServiceCQImpl implements ProdChangeCQService
{
    
    private ProdChangeDaoCQImpl prodChangeDaoCQImpl;
    
    public void createProdLog(ProdLogVO prodLogVO)
    {
        prodChangeDaoCQImpl.createProdLog(prodLogVO);
        
    }
    
    public void updateProdLog(ProdLogVO prodLogVO)
    {
        prodChangeDaoCQImpl.updateProdLog(prodLogVO);
    }
    
    public ProdChangeDaoCQImpl getProdChangeDaoCQImpl()
    {
        return prodChangeDaoCQImpl;
    }
    
    public void setProdChangeDaoCQImpl(ProdChangeDaoCQImpl prodChangeDaoCQImpl)
    {
        this.prodChangeDaoCQImpl = prodChangeDaoCQImpl;
    }
    
    /**
     * 获取变更产品的过滤模板
     * 
     * @param templateFilterInfo
     * @return
     */
    public List<TemplateFilterInfoVO> qryTempFilterInfo(TemplateFilterInfoVO templateFilterInfo)
    {
        return this.prodChangeDaoCQImpl.qryTempFilterInfo(templateFilterInfo);
    }
    
}
