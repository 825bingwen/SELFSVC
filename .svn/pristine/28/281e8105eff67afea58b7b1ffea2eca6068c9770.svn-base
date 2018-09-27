package com.customize.cq.selfsvc.charge.service;


import com.customize.cq.selfsvc.charge.dao.FeeChargeDaoImpl;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;

public class FeeChargeServiceImpl implements FeeChargeService
{
    
    private FeeChargeDaoImpl feeChargeDaoImpl;
    
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
    
    public FeeChargeDaoImpl getFeeChargeDaoImpl()
    {
        return feeChargeDaoImpl;
    }
    
    public void setFeeChargeDaoImpl(FeeChargeDaoImpl feeChargeDaoImpl)
    {
        this.feeChargeDaoImpl = feeChargeDaoImpl;
    }    
}
