package com.customize.hub.selfsvc.common.service;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.customize.hub.selfsvc.charge.dao.FeeChargeDaoHubImpl;
import com.customize.hub.selfsvc.charge.service.FeeChargeHubService;
import com.customize.hub.selfsvc.common.dao.FeeDaoImpl;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

public class FeeServiceImpl extends BaseServiceHubImpl implements FeeService
{
	private FeeChargeHubService feeChargeService;
	
	private FeeDaoImpl feeDaoImpl;
	
	/**
	 * 交费前记录日志，现金交费和银联卡交费都可调用此方法
	 * @param selfCardPayLogVO
	 * @param recType
	 * @param posNum
	 * @param batchNumBeforeKoukuan
	 * @see
	 * @return selfCardPayLogVO
	 * @remark servnumber, payType,terminalSeq(银联)数据已存储在selfCardPayLogVO中
	 */ 
	public CardChargeLogVO addChargeLog(CardChargeLogVO selfCardPayLogVO, String recType)
	{
		// 终端机信息
		TerminalInfoPO termInfo = this.getTermInfo();
		
		// 用户信息
		NserCustomerSimp customer = (NserCustomerSimp) this.getSession().getAttribute("valueCardUserInfo");
        
        // 生成投币日志id
        String logOID = feeChargeService.getChargeLogOID();
        
        // 组装数据
        // 缴费日志id
        selfCardPayLogVO.setChargeLogOID(logOID);
        
        // 终端地区
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        
        // 终端id
        selfCardPayLogVO.setTermID(termInfo.getTermid());
        
        // 终端操作员id
        selfCardPayLogVO.setOperID(termInfo.getOperid());
        
        // 用户交费金额(分)
        selfCardPayLogVO.setFee(CommonUtil.yuanToFen(selfCardPayLogVO.getFee()));
        
        // 交易时间
        selfCardPayLogVO.setRecdate(CommonUtil.getCurrentTime());
        
        // 手机号码归属地
        selfCardPayLogVO.setServRegion(customer.getRegionID());
        
        // 组织机构编码
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        
        // 交费状态 00：未交费，01：交费成功临时状态，10：交费成功业务失败，11：交费成功业务成功
        selfCardPayLogVO.setStatus(Constants.CHARGE_ERROR);
        
        // 备注
        selfCardPayLogVO.setDescription("缴费之前记录缴费日志");
        
        // 业务类型，可根据相应的业务自行配置
        selfCardPayLogVO.setRecType(recType);
        
        feeDaoImpl.addChargeLog(selfCardPayLogVO);
        
        return selfCardPayLogVO;
	}
	
	/**
	 * 现金交费投币后，更新交费日志
	 * @param selfCardPayLogVO
	 */
	public void updateCashChargeLog(CardChargeLogVO selfCardPayLogVO)
	{
		// 终端机信息
		TerminalInfoPO termInfo = this.getTermInfo();
		
		// 交费成功的状态
		selfCardPayLogVO.setStatus(Constants.PAYSUCCESS_CHARGEERROR);
		
		// 备注
        selfCardPayLogVO.setDescription("有价卡购买现金交费记录投币明细");
        
        // 交易时间
        selfCardPayLogVO.setRecdate(CommonUtil.getCurrentTime());
        
        // 交费金额
        // selfCardPayLogVO.setFee(CommonUtil.yuanToFen(selfCardPayLogVO.getFee()));
        
        // 地区
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        
        // 组织机构编码
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        
        String terminalSeq = termInfo.getTermid() + selfCardPayLogVO.getTerminalSeq();
    	
        // 终端流水号
        if (terminalSeq.length() > 20)
    	{
    		terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
    	}
    	selfCardPayLogVO.setTerminalSeq(terminalSeq);
        
        // 插入缴费日志
        feeDaoImpl.updateChargeLogForResult(selfCardPayLogVO);
	}
	
	/**
	 * 现金和银联卡交费完成后更新日志
	 * @param selfCardPayLogVO
	 */
	public void updateChargeResult(CardChargeLogVO selfCardPayLogVO)
	{
		// 终端机信息
		TerminalInfoPO termInfo = this.getTermInfo();
		
		// 封装缴费日志对象数据
        String terminalSeq = termInfo.getTermid() + selfCardPayLogVO.getTerminalSeq();
        
        if (terminalSeq.length() > 20)
        {
            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
        }
        selfCardPayLogVO.setTerminalSeq(terminalSeq);
        
        // 地区
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        
        // 组织机构编码
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        
        // 交易时间
        selfCardPayLogVO.setRecdate(CommonUtil.getCurrentTime());
        
        // 插入交费日志
        feeDaoImpl.updateChargeLogForResult(selfCardPayLogVO);
	}
	
	/**
	 * 更新现金交费异常的日志
	 * @param selfCardPayLogVO
	 * @param errormessage
	 * @param recType
	 * @param tMoney
	 */
	public void updateCashChargeError(CardChargeLogVO selfCardPayLogVO, String errormessage, 
			String recType, String tMoney)
	{
		// 交费状态
        if (isZero(tMoney))
        {
            selfCardPayLogVO.setStatus(Constants.CHARGE_ERROR);
        }
        else
        {
            selfCardPayLogVO.setStatus(Constants.PAYSUCCESS_CHARGEERROR);
        }
        
        // 错误信息
        selfCardPayLogVO.setDescription(errormessage);
        
        // 交易时间
        selfCardPayLogVO.setRecdate(CommonUtil.getCurrentTime());
        
        // 更新交费日志
        feeDaoImpl.updateChargeLog(selfCardPayLogVO);
	}
	
	/**
	 * 更新银联卡交费日志
	 * @param selfCardPayLogVO
	 */
	public String updateCardChargeLog(CardChargeLogVO selfCardPayLogVO)
	{
		String str = "";
		try
        {
            // 银联实际扣款金额
            String unionpayfee = selfCardPayLogVO.getUnionpayfee();
            
            selfCardPayLogVO.setUnionpayfee(CommonUtil.formatNumberStr(unionpayfee));
            
            // 交易时间
            selfCardPayLogVO.setRecdate(CommonUtil.getCurrentTime());
            
            // 银联返回码
            String initPosResCode = selfCardPayLogVO.getPosResCode();
            
            selfCardPayLogVO.setPosResCode(null == initPosResCode ? "" : initPosResCode);
            
            selfCardPayLogVO.setBusiType(java.net.URLDecoder.decode(selfCardPayLogVO.getBusiType(), "UTF-8"));
            
            selfCardPayLogVO.setDescription(java.net.URLDecoder.decode(selfCardPayLogVO.getDescription(), "UTF-8"));
            
            // 更新银联卡缴费日志
            feeDaoImpl.updateCardChargeLog(selfCardPayLogVO);
            
            str = "1";
        }   
        catch(Exception e)
        {
        	str = "0";
        }
        
        return str;
	}
	
	/**
	 * 更新银联卡交费异常的日志
	 * @param selfCardPayLogVO
	 * @param errormessage
	 * @param recType
	 * @param tMoney
	 * @param errPosResCode
	 * @see
	 */
	public void updateCardChargeError(CardChargeLogVO selfCardPayLogVO, String errormessage, 
			String recType, String tMoney, String errPosResCode, String errorType)
	{
		TerminalInfoPO termInfo = this.getTermInfo();
		
		NserCustomerSimp customer = (NserCustomerSimp) this.getSession().getAttribute("valueCardUserInfo");
		
		//新增银联交费
        if (StringUtils.isEmpty(errorType) || "add".equalsIgnoreCase(errorType))
        {
        	selfCardPayLogVO = new CardChargeLogVO();
        	//交费日志id
        	String chargeLogId = feeChargeService.getChargeLogOID();
        	selfCardPayLogVO.setChargeLogOID(chargeLogId);
        	
        	// 交费类型, 放置在页面上
        	selfCardPayLogVO.setPayType(Constants.PAYBYUNIONCARD);
        	
        	//交费流水
            selfCardPayLogVO.setTerminalSeq("");

            //归属地
            selfCardPayLogVO.setServRegion(customer.getRegionID());
            
            //判断扣款金额
            if (isZero(tMoney))
            {
            	//未交费
            	selfCardPayLogVO.setStatus(Constants.CHARGE_ERROR);
            }
            else
            {
            	//已交费
            	selfCardPayLogVO.setStatus(Constants.PAYSUCCESS_CHARGEERROR);
            }
            
        	//交费金额
        	selfCardPayLogVO.setFee(tMoney);
        	
        	//终端地区
        	selfCardPayLogVO.setRegion(termInfo.getRegion());
        	
        	//终端编号
        	selfCardPayLogVO.setTermID(termInfo.getTermid());
        	
        	//终端操作员
        	selfCardPayLogVO.setOperID(termInfo.getOperid());
        	
        	//组织机构编码
        	selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        	
        	//手机号码
        	selfCardPayLogVO.setServnumber(customer.getServNumber());
        	
        	//用户归属地
        	selfCardPayLogVO.setServRegion(customer.getRegionID());

        	//交易时间
        	selfCardPayLogVO.setRecdate(CommonUtil.getCurrentTime());
        	
        	//描述信息
        	selfCardPayLogVO.setDescription(errormessage);
        	
        	//业务类型
        	selfCardPayLogVO.setRecType(recType);
            
        	//插入交费异常日志
        	feeDaoImpl.addChargeLog(selfCardPayLogVO);
            
        }
        else
        {
		
			// 交费状态
	        if (isZero(tMoney))
	        {
	            selfCardPayLogVO.setStatus(Constants.CHARGE_ERROR);
	        }
	        else
	        {
	            selfCardPayLogVO.setStatus(Constants.PAYSUCCESS_CHARGEERROR);
	        }
	        
	        // 错误信息
	        selfCardPayLogVO.setDescription(errormessage);
	        
	        // 交易时间
	        selfCardPayLogVO.setRecdate(CommonUtil.getCurrentTime());
	        
        	// 银联终端号
        	selfCardPayLogVO.setUnionpaycode(termInfo.getUnionpaycode());
        	
        	// 银联商户号
        	selfCardPayLogVO.setUnionpayuser(termInfo.getUnionuserid());
        	
        	selfCardPayLogVO.setPosResCode(null == errPosResCode ? "" : errPosResCode);
	        
	        // 更新交费异常日志
        	feeDaoImpl.updateChargeLog(selfCardPayLogVO);
        }
	}
	
	/**
	 * <判断费用是否为0>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private boolean isZero(String fee)
	{
		boolean res = false;
		
		//若为空，则返回true
		if (StringUtils.isBlank(fee))
		{
			res = true;
		}
		else
		{
			//判断应收费用是否为0，若为0则跳过交费页面
			BigDecimal zero = new BigDecimal("0");
			res = (0 == zero.compareTo(new BigDecimal(fee)) ? true : false);
		}
		
		return res;
	}

	public FeeChargeHubService getFeeChargeService() {
		return feeChargeService;
	}

	public void setFeeChargeService(FeeChargeHubService feeChargeService) {
		this.feeChargeService = feeChargeService;
	}

	public FeeDaoImpl getFeeDaoImpl() {
		return feeDaoImpl;
	}

	public void setFeeDaoImpl(FeeDaoImpl feeDaoImpl) {
		this.feeDaoImpl = feeDaoImpl;
	}
}
