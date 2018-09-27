/*
 * 文 件 名:  MainProdChangeServiceHubImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人: yKF70747
 * 修改时间:  Apr 12, 2012
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.prodChange.service;

import java.util.List;

import com.customize.hub.selfsvc.prodChange.dao.MainProdChangeDaoImpl;
import com.customize.hub.selfsvc.prodChange.model.MainProdTempletInfoPO;
import com.customize.hub.selfsvc.prodChange.model.MainProdTempletPO;
import com.customize.hub.selfsvc.prodChange.model.ProdLogVO;

/**
 * 主体产品变更业务类
 * @author  yKF70747
 * @version  [版本号, Apr 12, 2012]
 * @see  
 * @since 
 */
public class MainProdChangeServiceHubImpl implements MainProdChangeHubService
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
