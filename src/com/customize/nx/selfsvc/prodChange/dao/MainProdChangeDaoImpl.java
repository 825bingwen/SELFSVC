package com.customize.nx.selfsvc.prodChange.dao;

import java.util.List;

import com.customize.nx.selfsvc.prodChange.model.MainProdTempletInfoPO;
import com.customize.nx.selfsvc.prodChange.model.MainProdTempletPO;
import com.customize.nx.selfsvc.prodChange.model.ProdLogVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 
 * �����Ʒ���DAO
 * 
 * @author  cKF76106
 * @version  [�汾��, Jun 20, 2013]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MainProdChangeDaoImpl extends BaseDaoImpl
{
    /**
     * �����û��ĵ�ǰ�����ƷID������������Ʒ�Ʋ�ѯ��ת���������Ʒ��Ϣ�б�
     * @return
     * @see 
     */
    public List<MainProdTempletInfoPO> qryMainProdTempChangeList(MainProdTempletPO mainProdTempletPO){
        return sqlMapClientTemplate.queryForList("prodChange.qryMainProdTempChangeList", mainProdTempletPO);
    }
    
    /**
     *��¼�����Ʒת����ϸ��־��Ϣ
     * @param prodLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void createProdChangeLog(ProdLogVO prodLogVO)
    {        
        sqlMapClientTemplate.insert("prodChange.insertProdChangeLog", prodLogVO);
        
    }
}
