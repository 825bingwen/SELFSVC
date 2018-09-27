package com.customize.cq.selfsvc.charge.service;


import com.customize.cq.selfsvc.charge.dao.FeeChargeDaoImpl;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;

public class FeeChargeServiceImpl implements FeeChargeService
{
    
    private FeeChargeDaoImpl feeChargeDaoImpl;
    
    /**
     * �������ɷ���־
     * 
     * @param cardChargeLogVO �������ɷ���Ϣ
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        feeChargeDaoImpl.addChargeLog(cardChargeLogVO);
    }
    
    /**
     * �����ۿ�ɹ�֮�󣬸�����־
     * 
     * @param cardChargeLogVO
     * @see 
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        feeChargeDaoImpl.updateCardChargeLog(cardChargeLogVO);
    }
    
    /**
     * ��¼��ӡ��Ʊ��־
     * 
     * @param record
     * @see 
     */
    public void insertInvoiceLog(InvoicePrintRecord record)
    {
        feeChargeDaoImpl.insertInvoiceLog(record);
    }
    
    /**
     * ��ȡ�ɷ���־OID
     * 
     * @return
     * @see 
     */
    public String getChargeLogOID()
    {
        return feeChargeDaoImpl.getChargeLogOID();
    }
    
    /**
     * ���³�ֵ�ɷѼ�¼
     * 
     * @param cardChargeLogVO
     * @see 
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        feeChargeDaoImpl.updateChargeLog(cardChargeLogVO);
    }
    
    public FeeChargeDaoImpl getFeeChargeDaoImpl()
    {
        return feeChargeDaoImpl;
    }
    
    public void setFeeChargeDaoImpl(FeeChargeDaoImpl feeChargeDaoImpl)
    {
        this.feeChargeDaoImpl = feeChargeDaoImpl;
    }    
}
