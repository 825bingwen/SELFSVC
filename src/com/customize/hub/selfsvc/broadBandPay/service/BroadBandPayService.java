package com.customize.hub.selfsvc.broadBandPay.service;

import java.util.List;

import com.customize.hub.selfsvc.broadBandPay.model.WBandVO;
import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;

/**
 * 
 * �������
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Sep 13, 2012]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface BroadBandPayService
{
    /**
     * ��¼�ɷ���־
     * <������ϸ����>
     * @param cardChargeLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * �����ۿ�ɹ�֮�󣬸�����־
     * <������ϸ����>
     * @param cardChargeLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * ��ȡ�ɷ���־OID
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String getChargeLogOID();
    
    /**
     * ���³�ֵ�ɷѼ�¼
     * <������ϸ����>
     * @param cardChargeLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * �����ظ��ɷѴ�����־
     * 
     * @param cashFeeErrorInfo
     * @see [�ࡢ��#��������#��Ա]
     */
    void insertFeeErrorLog(CashFeeErrorInfoVO cashFeeErrorInfo);
    
    /**
     * ��ѯ�����Ʒ�б�
     * <������ϸ����>
     * @param mainProdId
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<WBandVO> qryWBankList(String mainProdId);
}
