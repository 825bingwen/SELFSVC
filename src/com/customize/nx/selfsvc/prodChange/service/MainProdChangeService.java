package com.customize.nx.selfsvc.prodChange.service;

import java.util.List;

import com.customize.nx.selfsvc.prodChange.model.MainProdTempletInfoPO;
import com.customize.nx.selfsvc.prodChange.model.MainProdTempletPO;
import com.customize.nx.selfsvc.prodChange.model.ProdLogVO;



/**
 * 
 * 主体产品变更
 * 
 * @author  cKF76106
 * @version  [版本号, Jun 20, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MainProdChangeService
{
    /**
     * 根据用户的当前主体产品ID、归属地区、品牌查询可转换的主体产品信息列表
     * @return
     * @see
     */
    public List<MainProdTempletInfoPO> qryMainProdTempChangeList(MainProdTempletPO mainProdTempletPO);
    
    /**
     * 添加日志记录
     * 
     * @param prodLogVO
     */
    public void createProdChangeLog(ProdLogVO prodLogVO);
}
