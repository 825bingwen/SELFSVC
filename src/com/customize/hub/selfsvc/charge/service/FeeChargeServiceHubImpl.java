package com.customize.hub.selfsvc.charge.service;

import com.customize.hub.selfsvc.charge.dao.FeeChargeDaoHubImpl;
import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.common.cache.RefreshCacheHub;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class FeeChargeServiceHubImpl implements FeeChargeHubService
{
    
    private FeeChargeDaoHubImpl feeChargeDaoImpl;
    
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
    
    public FeeChargeDaoHubImpl getFeeChargeDaoImpl()
    {
        return feeChargeDaoImpl;
    }
    
    public void setFeeChargeDaoImpl(FeeChargeDaoHubImpl feeChargeDaoImpl)
    {
        this.feeChargeDaoImpl = feeChargeDaoImpl;
    }

    /**
     * 插入重复缴费错误日志
     * @param cashFeeErrorInfo
     */
    public void insertFeeErrorLog(CashFeeErrorInfoVO cashFeeErrorInfo)
    {
        feeChargeDaoImpl.insertFeeErrorLog(cashFeeErrorInfo);
        
    }
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
     public String getActId(String type,String mcount)
     {
         return feeChargeDaoImpl.getActId(type, mcount);
        
     }
     
     /**
      * <校验重复缴费>
      * <功能详细描述>
      * @param termInfo
      * @param servnumber
      * @param tMoney
      * @return
      * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-4-16 17:29:35 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
      */
     public boolean checkCashFee(TerminalInfoPO termInfo, String servnumber, String tMoney, String terminalSeq)
     {
         String seq = termInfo.getTermid().concat(terminalSeq);
         String tmpSeq = seq.concat(servnumber);
         
         // 如果有相同的串，则是重复缴费
         if (RefreshCacheHub.cashFeeCacher.containsKey(tmpSeq))
         {
             String recDate = DateUtilHub.getCurrentDateTime();
             
             String tmpErrorMsg = "重复缴费:手机号[".concat(servnumber)
                     .concat("],投币金额[")
                     .concat(tMoney)
                     .concat("]元,归属营业厅[")
                     .concat(termInfo.getOrgname())
                     .concat("],流水号[")
                     .concat(seq)
                     .concat("]");
             
             CashFeeErrorInfoVO cashFeeErrorInfo = new CashFeeErrorInfoVO(termInfo.getTermid(), termInfo.getRegion(),
                     termInfo.getOperid(), termInfo.getOrgid());
             
             cashFeeErrorInfo.setServnumber(servnumber);
             // 现金投币
             cashFeeErrorInfo.setPayType(Constants.PAYBYMONEY);
             cashFeeErrorInfo.setFee(tMoney);
             
             // 现金缴费流水,终端id+厂商生成流水
             cashFeeErrorInfo.setTerminalSeq(seq);
             
             cashFeeErrorInfo.setStatus("1");
             
             cashFeeErrorInfo.setRecDate(recDate);
             
             cashFeeErrorInfo.setDescription(tmpErrorMsg);
             
             //记录重复缴费日志
             feeChargeDaoImpl.insertFeeErrorLog(cashFeeErrorInfo);
             
             return false;
         }
         else
         {
             RefreshCacheHub.cashFeeCacher.put(tmpSeq, DateUtilHub.curOnlyTime());
             return true;
         }
     }
}
