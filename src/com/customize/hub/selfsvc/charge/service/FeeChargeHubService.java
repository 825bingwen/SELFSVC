package com.customize.hub.selfsvc.charge.service;

import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public interface FeeChargeHubService
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
     * 插入重复缴费错误日志
     * 
     * @param cashFeeErrorInfo
     * @see [类、类#方法、类#成员]
     */
    void insertFeeErrorLog(CashFeeErrorInfoVO cashFeeErrorInfo);
    /**
     * 根据用户的交费类型，及交费金额，查询最适合用户的抽奖活动
     * 〈功能详细描述〉
     * @param [type] [交费类型]
     * @param [交费金额] [mcount]
     * @return 抽奖活动Id
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark create yKF73963 （2012-11-09） OR_HUB_201209_706  电子渠道-抽奖平台-抽奖接口改造 
     */
     public String getActId(String type,String mcount);
     
     /**
      * <校验重复缴费>
      * <功能详细描述>
      * @param termInfo
      * @param servnumber
      * @param tMoney 交费金额，分
      * @param terminalSeq 硬件生成的流水号
      * @return true 正常，false，有问题，重复缴费
      * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-4-16 17:29:35 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
      */
     public boolean checkCashFee(TerminalInfoPO termInfo, String servnumber, String tMoney, String terminalSeq);
}
