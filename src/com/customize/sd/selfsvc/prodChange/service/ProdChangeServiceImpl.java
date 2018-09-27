/*
 * �� �� ��:  ProdChangeServiceImpl.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  hWX5316476
 * �޸�ʱ��:  Jan 5, 2015
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.prodChange.service;

import java.util.List;

import com.customize.sd.selfsvc.prodChange.dao.ProdChangeDaoImpl;
import com.customize.sd.selfsvc.prodChange.model.ProdChangePO;
import com.customize.sd.selfsvc.prodChange.model.ProdTemplatePO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  hWX5316476
 * @version  [�汾��, Jan 5, 2015]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ProdChangeServiceImpl implements ProdChangeService
{
    /**
     * �ײ��ʷѱ��daoImpl
     */
    private ProdChangeDaoImpl prodChangeDaoImpl;
    
    /**
     * ��ѯ��Ʒ�ڿ�ת���ĵ����б�
     * @param prodid �����Ʒid
     * @param region ����
     * @param brand Ʒ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<ProdTemplatePO> qryLevelByProdId(NserCustomerSimp customer, String newProdName)
    {
        // ���ò�ѯ������
        ProdChangePO prodChangePO = new ProdChangePO();
        
        // �����Ʒ����
        prodChangePO.setNewProdName(newProdName);
        
        // �����ƷID
        prodChangePO.setOldProdId(customer.getProductID());
        prodChangePO.setNewProdId(customer.getProductID());
        
        // ����
        prodChangePO.setRegion(customer.getRegionID());
        
        // ���ݿ���ѯ��Ʒ�ڿ�ת���ĵ����б�
        return prodChangeDaoImpl.qryLevelByProdId(prodChangePO);
    }
    public ProdChangeDaoImpl getProdChangeDaoImpl()
    {
        return prodChangeDaoImpl;
    }
    public void setProdChangeDaoImpl(ProdChangeDaoImpl prodChangeDaoImpl)
    {
        this.prodChangeDaoImpl = prodChangeDaoImpl;
    }
    
    
}
