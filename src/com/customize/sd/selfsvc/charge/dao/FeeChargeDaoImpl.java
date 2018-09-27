package com.customize.sd.selfsvc.charge.dao;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

public class FeeChargeDaoImpl extends BaseDaoImpl
{
    /**
     * �������ɷѼ�¼��־
     * 
     * @param cardChargeLogVO ���п���Ϣ
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.insertChargeLog", cardChargeLogVO);
    }
    
    /**
     * �����ۿ�ɹ�֮�󣬸�����־
     * 
     * @param cardChargeLogVO
     * @see 
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.updateCardChargeLogSD", cardChargeLogVO);
    }
    
    /**
     * ���³�ֵ�ɷѼ�¼
     *
     * @param cardChargeLogVO
     * @see 
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.updateChargeLogSD", cardChargeLogVO);
    }
    
    /**
     * ��¼��ӡ��Ʊ��־
     * 
     * @param record
     * @see 
     */
    public void insertInvoiceLog(InvoicePrintRecord record)
    {
        sqlMapClientTemplate.insert("charge.insertInvoiceLog", record);
    }
    
    /**
     * ��ȡ�ɷ���־OID
     * 
     * @return
     * @see 
     */
    public String getChargeLogOID()
    {
        return (String) sqlMapClientTemplate.queryForObject("charge.getChargeLogOID");
    }
    
    /**
     * <��֤�ýɷѺ����Ƿ�ʡ���û�>
     * <������ϸ����>
     * @param telnum
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-7-19 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�
     */
    public int anthSdTelnum(String region)
    {
    	return (Integer)sqlMapClientTemplate.queryForObject("charge.isSdTelnum",region);
    }
    
    /**
     * <�����ֽ�ɷ�Ͷ����ϸ>
     * <������ϸ����>
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-7-1 �޸�bug93196 ����Ͷ����ϸʱ��ֻ����CASHDETAIL
     */
    public void updateCashDetail(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.updateCashDetailSD", cardChargeLogVO);
    }
}
