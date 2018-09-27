package com.customize.cq.selfsvc.charge.service;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;

public interface FeeChargeService
{
    // �������ɷ���־
    public void addChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * �����ۿ�ɹ�֮�󣬸�����־
     * 
     * @param cardChargeLogVO
     * @see 
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * ��¼��ӡ��Ʊ��־
     * 
     * @param record
     * @see 
     */
    public void insertInvoiceLog(InvoicePrintRecord record);
    
    /**
     * ��ȡ�ɷ���־OID
     * 
     * @return
     * @see 
     */
    public String getChargeLogOID();
    
    /**
     * ���³�ֵ�ɷѼ�¼
     * 
     * @param cardChargeLogVO
     * @see 
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO);
}
