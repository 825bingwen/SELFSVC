package com.customize.nx.selfsvc.charge.service;

import java.util.Map;

import com.customize.nx.selfsvc.charge.dao.FeeChargeDaoNXImpl;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.CashDetailLogPO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;

public class FeeChargeServiceNXImpl implements FeeChargeNXService
{
    
    private FeeChargeDaoNXImpl feeChargeDaoImpl;
    
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
    
    /**
     * ��¼�û�������־֮ǰ�ȼ�¼�û���Ͷ�����
     * 
     * @param params
     * @see 
     * @remark create g00140516 2012/03/09 R003C12L03n01 OR_NX_201201_312
     */
    public void insertCashDetailInfo(Map<String, String> params)
    {
        if (null == params)
        {
            return;
        }
        
        // �û�Ͷ�ҵ������Ϣ����50;100����Ͷ�����Σ���һ��50���ڶ���100
        String cashDetail = params.get("cashDetail");
        if (null == cashDetail || "".equals(cashDetail.trim()))
        {
            return;
        }
        
        String termID = params.get("termID");
        String servNumber = params.get("servNumber");
        String terminalSeq = params.get("terminalSeq");
        
        String[] cashes = cashDetail.split(";");
        
        CashDetailLogPO log = null;
        for (int i = 0; i < cashes.length; i++)
        {
            log = new CashDetailLogPO();
            log.setTermID(termID);
            log.setServNum(servNumber);
            log.setFormNum(terminalSeq);
            log.setCashFee(cashes[i]);
            
            feeChargeDaoImpl.insertCashDetailInfo(log);
        }       
    }
    
    public FeeChargeDaoNXImpl getFeeChargeDaoImpl()
    {
        return feeChargeDaoImpl;
    }
    
    public void setFeeChargeDaoImpl(FeeChargeDaoNXImpl feeChargeDaoImpl)
    {
        this.feeChargeDaoImpl = feeChargeDaoImpl;
    }
}
