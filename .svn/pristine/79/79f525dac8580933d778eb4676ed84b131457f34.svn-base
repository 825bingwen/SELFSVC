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
     * 银联卡缴费日志
     * 
     * @param cardChargeLogVO 银联卡缴费信息
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        feeChargeDaoImpl.addChargeLog(cardChargeLogVO);
    }
    
    /**
     * 银联扣款成功之后，更新日志
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        feeChargeDaoImpl.updateCardChargeLog(cardChargeLogVO);
    }
    
    /**
     * 记录打印发票日志
     * 
     * @param record
     * @see
     */
    public void insertInvoiceLog(InvoicePrintRecord record)
    {
        feeChargeDaoImpl.insertInvoiceLog(record);
    }
    
    /**
     * 获取缴费日志OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID()
    {
        return feeChargeDaoImpl.getChargeLogOID();
    }
    
    /**
     * 更新充值缴费记录
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        feeChargeDaoImpl.updateChargeLog(cardChargeLogVO);
    }
    
    /**
     * 记录用户交费日志之前先记录用户的投币情况
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
        
        // 用户投币的面额信息，如50;100，既投币两次，第一次50，第二次100
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
