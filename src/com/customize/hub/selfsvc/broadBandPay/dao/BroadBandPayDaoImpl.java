package com.customize.hub.selfsvc.broadBandPay.dao;

import java.util.List;

import com.customize.hub.selfsvc.broadBandPay.model.WBandVO;
import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

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
public class BroadBandPayDaoImpl extends BaseDaoImpl
{
    /**
     * �ɷѼ�¼��־
     * <������ϸ����>
     * @param cardChargeLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("broadBandPay.insertChargeLog", cardChargeLogVO);
    }
    
    /**
     * �����ۿ�ɹ�֮�󣬸�����־
     * <������ϸ����>
     * @param cardChargeLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
    	sqlMapClientTemplate.update("broadBandPay.updateCardChargeLog", cardChargeLogVO);
    }
    
    /**
     * ���³�ֵ�ɷѼ�¼
     * <������ϸ����>
     * @param cardChargeLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
    	sqlMapClientTemplate.update("broadBandPay.updateChargeLog", cardChargeLogVO);
    }
    
    /**
     * ��ȡ�ɷ���־OID
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String getChargeLogOID()
    {
        return (String)sqlMapClientTemplate.queryForObject("charge.getChargeLogOID");
    }
    
    /**
     * �����ظ��ɷѴ�����־
     * <������ϸ����>
     * @param cashFeeErrorInfo
     * @see [�ࡢ��#��������#��Ա]
     */
    public void insertFeeErrorLog(CashFeeErrorInfoVO cashFeeErrorInfo)
    {
        sqlMapClientTemplate.insert("broadBandPay.insertFeeErrorLog", cashFeeErrorInfo);
    }
    
    /**
     * ���������Ʒ��ѯ�����Ʒ�б�
     * <������ϸ����>
     * @param mainProdId
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<WBandVO> qryWBankList(String mainProdId)
    {
        return sqlMapClientTemplate.queryForList("broadBandPay.qryWBankList",mainProdId);
    }
}
