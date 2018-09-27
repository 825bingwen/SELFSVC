package com.customize.hub.selfsvc.prodChange.dao;

import java.util.List;

import com.customize.hub.selfsvc.prodChange.model.ProdLogVO;
import com.customize.hub.selfsvc.prodChange.model.TemplateFilterInfoVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

public class ProdChangeDaoHubImpl extends BaseDaoImpl
{
    
    public void createProdLog(ProdLogVO prodLogVO)
    {
        
        sqlMapClientTemplate.insert("prodChange.insertProdLog", prodLogVO);
        
    }
    
    public void updateProdLog(ProdLogVO prodLogVO)
    {
        sqlMapClientTemplate.update("prodChange.updateProdLog", prodLogVO);
        
    }
    
    public List<TemplateFilterInfoVO> qryTempFilterInfo(TemplateFilterInfoVO templateFilterInfo)
    {
        return sqlMapClientTemplate.queryForList("prodChange.qryTempFilterInfo", templateFilterInfo);
    }
}
