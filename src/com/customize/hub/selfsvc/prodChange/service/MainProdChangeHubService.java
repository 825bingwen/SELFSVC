/*
 * �� �� ��:  MainProdChangeHubService.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��: yKF70747
 * �޸�ʱ��:  Apr 12, 2012
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.hub.selfsvc.prodChange.service;

import java.util.List;

import com.customize.hub.selfsvc.prodChange.model.MainProdTempletInfoPO;
import com.customize.hub.selfsvc.prodChange.model.MainProdTempletPO;
import com.customize.hub.selfsvc.prodChange.model.ProdLogVO;

/**
 * �����Ʒ���
 * @author  yKF70747
 * @version  [�汾��, Apr 12, 2012]
 * @see  
 * @since 
 */
public interface MainProdChangeHubService
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
