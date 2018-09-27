package com.customize.sd.selfsvc.charge.dao;

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
        sqlMapClientTemplate.insert("charge.updateCardChargeLogSD", cardChargeLogVO);
    }
    
    /**
     * 更新充值缴费记录
     *
     * @param cardChargeLogVO
     * @see 
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.updateChargeLogSD", cardChargeLogVO);
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
    
    /**
     * <验证该缴费号码是否省内用户>
     * <功能详细描述>
     * @param telnum
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-7-19 OR_huawei_201406_1125_山东_支撑跨区缴费
     */
    public int anthSdTelnum(String region)
    {
    	return (Integer)sqlMapClientTemplate.queryForObject("charge.isSdTelnum",region);
    }
    
    /**
     * <更新现金缴费投币明细>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-7-1 修改bug93196 更新投币明细时，只更新CASHDETAIL
     */
    public void updateCashDetail(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.updateCashDetailSD", cardChargeLogVO);
    }
}
