package com.customize.nx.selfsvc.prodChange.service;

import java.util.List;

import com.customize.nx.selfsvc.prodChange.dao.MainProdChangeDaoImpl;
import com.customize.nx.selfsvc.prodChange.model.MainProdTempletInfoPO;
import com.customize.nx.selfsvc.prodChange.model.MainProdTempletPO;
import com.customize.nx.selfsvc.prodChange.model.ProdLogVO;



/**
 * 
 * 主体产品变更业务类
 * 
 * @author  cKF76106
 * @version  [版本号, Jun 20, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MainProdChangeServiceImpl implements MainProdChangeService
{
    private MainProdChangeDaoImpl mainProdChangeDaoImpl;

    public MainProdChangeDaoImpl getMainProdChangeDaoImpl()
    {
        return mainProdChangeDaoImpl;
    }

    public void setMainProdChangeDaoImpl(MainProdChangeDaoImpl mainProdChangeDaoImpl)
    {
        this.mainProdChangeDaoImpl = mainProdChangeDaoImpl;
    }

    
    public List<MainProdTempletInfoPO> qryMainProdTempChangeList(MainProdTempletPO mainProdTempletPO)
    {        
        return mainProdChangeDaoImpl.qryMainProdTempChangeList(mainProdTempletPO);
    }

   
    public void createProdChangeLog(ProdLogVO prodLogVO)
    {
         mainProdChangeDaoImpl.createProdChangeLog(prodLogVO);
        
    }
}
