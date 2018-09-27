package com.customize.cq.selfsvc.charge.dao;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

public class FeeChargeDaoImpl extends BaseDaoImpl
{
    /**
     * 银联卡缴费记录日志
     * 
     * @param cardChargeLogVO 银行卡信息
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.insertChargeLog", cardChargeLogVO);
    }
    
    /**
     * 银联扣款成功之后，更新日志
     * 
     * @param cardChargeLogVO
     * @see 
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.updateCardChargeLog", cardChargeLogVO);
    }
    
    /**
     * 更新充值缴费记录
     *
     * @param cardChargeLogVO
     * @see 
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.updateChargeLog", cardChargeLogVO);
    }
    
    /**
     * 记录打印发票日志
     * 
     * @param record
     * @see 
     */
    public void insertInvoiceLog(InvoicePrintRecord record)
    {
        sqlMapClientTemplate.insert("charge.insertInvoiceLog", record);
    }
    
    /**
     * 获取缴费日志OID
     * 
     * @return
     * @see 
     */
    public String getChargeLogOID()
    {
        return (String) sqlMapClientTemplate.queryForObject("charge.getChargeLogOID");
    }
}
