package com.customize.nx.selfsvc.charge.service;

import java.util.Map;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;

public interface FeeChargeNXService
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
    
    /**
     * ��¼�û�������־֮ǰ�ȼ�¼�û���Ͷ�����
     * 
     * @param params
     * @see 
     * @remark create g00140516 2012/03/09 R003C12L03n01 OR_NX_201201_312
     */
    public void insertCashDetailInfo(Map<String, String> params);
}
