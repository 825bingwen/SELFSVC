package com.customize.nx.selfsvc.prodChange.dao;

import java.util.List;

import com.customize.nx.selfsvc.prodChange.model.MainProdTempletInfoPO;
import com.customize.nx.selfsvc.prodChange.model.MainProdTempletPO;
import com.customize.nx.selfsvc.prodChange.model.ProdLogVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 
 * 主体产品变更DAO
 * 
 * @author  cKF76106
 * @version  [版本号, Jun 20, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MainProdChangeDaoImpl extends BaseDaoImpl
{
    /**
     * 根据用户的当前主体产品ID、归属地区、品牌查询可转换的主体产品信息列表
     * @return
     * @see 
     */
    public List<MainProdTempletInfoPO> qryMainProdTempChangeList(MainProdTempletPO mainProdTempletPO){
        return sqlMapClientTemplate.queryForList("prodChange.qryMainProdTempChangeList", mainProdTempletPO);
    }
    
    /**
     *记录主体产品转换详细日志信息
     * @param prodLogVO
     * @see [类、类#方法、类#成员]
     */
    public void createProdChangeLog(ProdLogVO prodLogVO)
    {        
        sqlMapClientTemplate.insert("prodChange.insertProdChangeLog", prodLogVO);
        
    }
}
