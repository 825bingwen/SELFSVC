package com.customize.hub.selfsvc.broadBandPay.service;

import java.util.List;

import com.customize.hub.selfsvc.broadBandPay.dao.BroadBandPayDaoImpl;
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
public class BroadBandPayServiceImpl implements BroadBandPayService
{
    
    private BroadBandPayDaoImpl broadBandPayDaoImpl;
    
    /**
     * ��¼�ɷ���־
     * <������ϸ����>
     * @param cardChargeLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        broadBandPayDaoImpl.addChargeLog(cardChargeLogVO);
    }

    /**
     * �����ۿ�ɹ�֮�󣬸�����־
     * <������ϸ����>
     * @param cardChargeLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        broadBandPayDaoImpl.updateCardChargeLog(cardChargeLogVO);
    }
    
    /**
     * ��ȡ�ɷ���־OID
     * <������ϸ����>
     * @param cardChargeLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public String getChargeLogOID()
    {
        return broadBandPayDaoImpl.getChargeLogOID();
    }
    
    /**
     * ���³�ֵ�ɷѼ�¼
     * <������ϸ����>
     * @param cardChargeLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        broadBandPayDaoImpl.updateChargeLog(cardChargeLogVO);
    }

    /**
     * �����ظ��ɷѴ�����־
     * <������ϸ����>
     * @param cardChargeLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void insertFeeErrorLog(CashFeeErrorInfoVO cashFeeErrorInfo)
    {
        broadBandPayDaoImpl.insertFeeErrorLog(cashFeeErrorInfo);
        
    }
    
    /**
     * ��ѯ�����Ʒ�б�
     * <������ϸ����>
     * @param mainProdId
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<WBandVO> qryWBankList(String mainProdId)
    {
        return (List<WBandVO>)broadBandPayDaoImpl.qryWBankList(mainProdId);
    }

    public BroadBandPayDaoImpl getBroadBandPayDaoImpl()
    {
        return broadBandPayDaoImpl;
    }

    public void setBroadBandPayDaoImpl(BroadBandPayDaoImpl broadBandPayDaoImpl)
    {
        this.broadBandPayDaoImpl = broadBandPayDaoImpl;
    }
    
    
}
