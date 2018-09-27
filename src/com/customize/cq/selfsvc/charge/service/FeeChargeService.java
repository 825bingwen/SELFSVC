package com.customize.cq.selfsvc.charge.service;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;

public interface FeeChargeService
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
}
