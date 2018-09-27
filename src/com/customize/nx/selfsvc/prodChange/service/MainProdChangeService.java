package com.customize.nx.selfsvc.prodChange.service;

import java.util.List;

import com.customize.nx.selfsvc.prodChange.model.MainProdTempletInfoPO;
import com.customize.nx.selfsvc.prodChange.model.MainProdTempletPO;
import com.customize.nx.selfsvc.prodChange.model.ProdLogVO;



/**
 * 
 * �����Ʒ���
 * 
 * @author  cKF76106
 * @version  [�汾��, Jun 20, 2013]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface MainProdChangeService
{
    /**
     * �����û��ĵ�ǰ�����ƷID������������Ʒ�Ʋ�ѯ��ת���������Ʒ��Ϣ�б�
     * @return
     * @see
     */
    public List<MainProdTempletInfoPO> qryMainProdTempChangeList(MainProdTempletPO mainProdTempletPO);
    
    /**
     * �����־��¼
     * 
     * @param prodLogVO
     */
    public void createProdChangeLog(ProdLogVO prodLogVO);
}
