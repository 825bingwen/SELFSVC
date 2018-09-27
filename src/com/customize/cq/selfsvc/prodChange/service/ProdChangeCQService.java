package com.customize.cq.selfsvc.prodChange.service;

import java.util.List;

import com.customize.cq.selfsvc.prodChange.model.ProdLogVO;
import com.customize.cq.selfsvc.prodChange.model.TemplateFilterInfoVO;

public interface ProdChangeCQService
{
    /**
     * 添加日志记录
     * 
     * @param prodLogVO
     */
    public void createProdLog(ProdLogVO prodLogVO);
    
    /**
     * 更新日志记录
     */
    public void updateProdLog(ProdLogVO prodLogVO);
    
    /**
     * 获取变更产品的过滤模板
     * 
     * @param templateFilterInfo
     * @return
     */
    List<TemplateFilterInfoVO> qryTempFilterInfo(TemplateFilterInfoVO templateFilterInfo);
}
