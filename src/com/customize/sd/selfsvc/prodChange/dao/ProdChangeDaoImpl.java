/*
 * �� �� ��:  ProdChangeDaoImpl.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  hWX5316476
 * �޸�ʱ��:  Jan 5, 2015
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.prodChange.dao;

import java.util.List;

import com.customize.sd.selfsvc.prodChange.model.ProdChangePO;
import com.customize.sd.selfsvc.prodChange.model.ProdTemplatePO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * �ײ��ʷѱ��
 * 
 * @author  hWX5316476
 * @version  [�汾��, Jan 5, 2015]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ProdChangeDaoImpl extends BaseDaoImpl
{
    /**
     * ��ѯ��Ʒ�����ڿ�ת���ĵ����б�
     * 
     * @param prodChangePO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    @SuppressWarnings("unchecked")
    public List<ProdTemplatePO> qryLevelByProdId(ProdChangePO prodChangePO)
    {
        return sqlMapClientTemplate.queryForList("prodChange.qryLevelByProdId", prodChangePO);
    }
}
