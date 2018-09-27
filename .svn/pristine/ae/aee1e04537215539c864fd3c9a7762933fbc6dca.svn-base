package com.customize.nx.selfsvc.charge.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.CashDetailLogPO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

public class FeeChargeDaoNXImpl extends BaseDaoImpl
{
    // 日志
    private static Log logger = LogFactory.getLog(FeeChargeDaoNXImpl.class);
    
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
        sqlMapClientTemplate.insert("charge.updateCardChargeLogNX", cardChargeLogVO);
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
        return (String)sqlMapClientTemplate.queryForObject("charge.getChargeLogOID");
    }
    
    /**
     * 记录用户交费日志之前先记录用户的投币情况
     * 
     * @param log
     * @see 
     * @remark create g00140516 2012/03/09 R003C12L03n01 OR_NX_201201_312
     */
    public void insertCashDetailInfo(CashDetailLogPO log)
    {
        try
        {
            sqlMapClientTemplate.insert("charge.insertCashDetailLog", log);
        }
        catch (Exception e)
        {
            logger.error("记录用户投币情况异常：", e);
        }
    }
}
