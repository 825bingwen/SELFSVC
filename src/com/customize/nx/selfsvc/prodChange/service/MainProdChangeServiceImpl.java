package com.customize.nx.selfsvc.prodChange.service;

import java.util.List;

import com.customize.nx.selfsvc.prodChange.dao.MainProdChangeDaoImpl;
import com.customize.nx.selfsvc.prodChange.model.MainProdTempletInfoPO;
import com.customize.nx.selfsvc.prodChange.model.MainProdTempletPO;
import com.customize.nx.selfsvc.prodChange.model.ProdLogVO;



/**
 * 
 * �����Ʒ���ҵ����
 * 
 * @author  cKF76106
 * @version  [�汾��, Jun 20, 2013]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
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
