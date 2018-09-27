package com.customize.cq.selfsvc.prodChange.service;

import java.util.List;

import com.customize.cq.selfsvc.prodChange.model.ProdLogVO;
import com.customize.cq.selfsvc.prodChange.model.TemplateFilterInfoVO;

public interface ProdChangeCQService
{
    /**
     * �����־��¼
     * 
     * @param prodLogVO
     */
    public void createProdLog(ProdLogVO prodLogVO);
    
    /**
     * ������־��¼
     */
    public void updateProdLog(ProdLogVO prodLogVO);
    
    /**
     * ��ȡ�����Ʒ�Ĺ���ģ��
     * 
     * @param templateFilterInfo
     * @return
     */
    List<TemplateFilterInfoVO> qryTempFilterInfo(TemplateFilterInfoVO templateFilterInfo);
}
