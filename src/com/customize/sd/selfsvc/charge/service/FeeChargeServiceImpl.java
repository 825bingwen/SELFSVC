package com.customize.sd.selfsvc.charge.service;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.customize.sd.selfsvc.charge.dao.FeeChargeDaoImpl;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

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
    
    /**
     * 验证交费号码是否为省内用户
     * @param telnum
     * @return
     * @remark create by sWX219697 2014-7-19 11:14:24 OR_huawei_201406_1125_山东_支撑跨区缴费
     */
    public int anthSdTelnum(String region)
    {
    	return feeChargeDaoImpl.anthSdTelnum(region);
    }
    
    /**
     * 话费连缴增加缴费日志
     * 
     * @param chargeLogOID 缴费流水
     * @param morePhone 话费连缴
     * @param termInfo 终端机
     * @param chargeType 缴费类型
     * @remark create by jWX216858 2015-4-16 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
    @Override
	public void addCardLog(String chargeLogOID, List<CardChargeLogVO> morePhones,
			TerminalInfoPO termInfo, String chargeType)
    {
    	for (CardChargeLogVO morePhone : morePhones)
    	{
    		morePhone.setMoreChargeOID(chargeLogOID); // 话费连缴唯一流水
    		morePhone.setRegion(termInfo.getRegion()); // 归属地市
    		morePhone.setTermID(termInfo.getTermid()); // 终端机id
    		morePhone.setOperID(termInfo.getOperid()); // 操作员id
    		morePhone.setPayType(Constants.PAYBYUNIONCARD); // 支付方式
    		morePhone.setFee(CommonUtil.yuanToFen(morePhone.gettMoney())); // 用户实缴费用
    		
    		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
    		String payDate = sdf1.format(new Date());
    		morePhone.setRecdate(payDate); // 受理时间
    		
    		morePhone.setStatus(Constants.CHARGE_ERROR); // 
    		morePhone.setDescription("扣款之前记录日志");
    		morePhone.setOrgID(termInfo.getOrgid());
    		morePhone.setRecType("morePhone");// 业务类型（morePhone：话费连缴）
    		morePhone.setBankno(chargeType + termInfo.getBankno());
    		
    		feeChargeDaoImpl.addChargeLog(morePhone);
    	}
	} 
    
    /**
     * <验话费连缴更新日志>
     * <功能详细描述>
     * @param cardChargeVO
     * @throws UnsupportedEncodingException 
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2015-3-31 OR_SD_201501_1063 自助终端支撑连缴功能优化
     */
	@Override
	public void updateMorePhoneLog(CardChargeLogVO cardChargeVO,
			CardChargeLogVO morePhone, TerminalInfoPO termInfo) throws UnsupportedEncodingException
	{

		// 终端流水号，话费连缴区分单笔业务
		cardChargeVO.setTerminalSeq(morePhone.getChargeLogOID());
		
		// 缴费日志流水
		cardChargeVO.setChargeLogOID(morePhone.getChargeLogOID());
		
		// 缴费金额
		cardChargeVO.setUnionpayfee(CommonUtil.yuanToFen(morePhone.gettMoney()));
		
		// 设置交易类型
		cardChargeVO.setBusiType(java.net.URLDecoder.decode(cardChargeVO.getBusiType(), "UTF-8"));
		
		// 描述
		cardChargeVO.setDescription(java.net.URLDecoder.decode(cardChargeVO.getDescription(), "UTF-8"));
		
		// 组织机构
		cardChargeVO.setOrgID(termInfo.getOrgid());
		
		// 归属地市
		cardChargeVO.setRegion(termInfo.getRegion());
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String payDate = sdf1.format(new Date());
        
        // 受理时间
        cardChargeVO.setRecdate(payDate);
		
        feeChargeDaoImpl.updateCardChargeLog(cardChargeVO);
	}
	
    /**
     * 更新充值缴费记录
     * 
     * @param cardChargeLogVO
     * @see 
     * @remark create by sWX219697 2015-7-1 修改bug93196 更新投币明细时，只更新CASHDETAIL
     */
    public void updateCashDetail(CardChargeLogVO cardChargeLogVO)
    {
        feeChargeDaoImpl.updateCashDetail(cardChargeLogVO);
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
