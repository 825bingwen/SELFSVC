package com.customize.sd.selfsvc.charge.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

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
    
    /**
     * <验证交费号码是否省内用户>
     * <功能详细描述>
     * @param telnum
     * @return true 省内用户，false 省外用户
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-7-19 11:12:09 OR_huawei_201406_1125_山东_支撑跨区缴费
     */
    public int anthSdTelnum(String region);
    
    /**
     * 话费连缴增加缴费日志
     * 
     * @param chargeLogOID 缴费流水
     * @param morePhones 话费连缴
     * @param termInfo 终端机
     * @param chargeType 缴费类型
     * @remark create by jWX216858 2015-4-16 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
	public void addCardLog(String chargeLogOID, List<CardChargeLogVO> morePhones,
			TerminalInfoPO termInfo, String chargeType);

	/**
     * <话费连缴更新日志>
     * <功能详细描述>
     * @param cardChargeVO
	 * @throws UnsupportedEncodingException 
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-4-16 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
	public void updateMorePhoneLog(CardChargeLogVO cardChargeVO,
			CardChargeLogVO cardChargeLogVO, TerminalInfoPO termInfo) throws UnsupportedEncodingException;
	
    /**
     * 更新充值缴费记录
     * 
     * @param cardChargeLogVO
     * @see 
     * @remark create by sWX219697 2015-7-1 修改bug93196 更新投币明细时，只更新CASHDETAIL
     */
    public void updateCashDetail(CardChargeLogVO cardChargeLogVO);
}
