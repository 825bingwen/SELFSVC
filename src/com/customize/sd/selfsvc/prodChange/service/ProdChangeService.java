package com.customize.sd.selfsvc.prodChange.service;

import java.util.List;

import com.customize.sd.selfsvc.prodChange.model.ProdTemplatePO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
/**
 * �ײ��ʷѱ��
 * @author  hWX5316476
 * @version  [�汾��, Jan 5, 2015]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface ProdChangeService
{
    /**
     * ��ѯ��Ʒ��ģ����Ϣ
     * @param prodid �����Ʒid
     * @param region ����
     * @param brand Ʒ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<ProdTemplatePO> qryLevelByProdId(NserCustomerSimp customer, String newProdName);
}
