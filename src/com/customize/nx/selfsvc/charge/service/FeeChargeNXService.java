package com.customize.nx.selfsvc.charge.service;

import java.util.Map;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;

public interface FeeChargeNXService
{
    // 银联卡缴费日志
    public void addChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * 银联扣款成功之后，更新日志
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * 记录打印发票日志
     * 
     * @param record
     * @see
     */
    public void insertInvoiceLog(InvoicePrintRecord record);
    
    /**
     * 获取缴费日志OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID();
    
    /**
     * 更新充值缴费记录
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * 记录用户交费日志之前先记录用户的投币情况
     * 
     * @param params
     * @see 
     * @remark create g00140516 2012/03/09 R003C12L03n01 OR_NX_201201_312
     */
    public void insertCashDetailInfo(Map<String, String> params);
}
