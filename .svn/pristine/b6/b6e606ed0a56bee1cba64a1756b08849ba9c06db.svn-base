/*
 * 文 件 名:  StoreCardAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  湖北备卡
 * 修 改 人:  c00233019
 * 修改时间:  2014-10-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.cardbusi.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.PrepareCardBean;
import com.customize.hub.selfsvc.bean.ReissueCardBean;
import com.customize.hub.selfsvc.cardbusi.model.CardBusiLogPO;
import com.customize.hub.selfsvc.charge.service.FeeChargeHubService;
import com.gmcc.boss.selfsvc.bean.UserLoginBean;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.EncryptDecryptUtil;

/**
 * 
 * 湖北备卡
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-10-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]OR_HUB_201405_478 关于自助终端支持现场写卡的需求之在线开户
 */
public class PrepareCardAction  extends CardBusiBaseAction
{
	
	/**
     * 注释内容
     */
    private static final long serialVersionUID = 8336831060603314515L;

    /** 
     * 日志对象
     */
    private static Log logger = LogFactory.getLog(PrepareCardAction.class);
    
    /**
	 * 备卡接口调用
	 */
	private transient PrepareCardBean prepareCardBean;
	
	/**
	 * 备卡接口调用
	 */
	private transient ReissueCardBean reissueCardBean;
    
    /**
	 * 用户登录
	 */
	private transient UserLoginBean userLoginBean;
	
	/**
	 * 用户手机号码
	 */
	private String servnumber;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 随机验证码
	 */
	private String randomPwd;
    
    /**-----------------------缴费相关-----------------------------
     * 可用交费方式
     */
    protected List usableTypes;
    
    /**
     * 备卡总费用
     */
    private String totalFee;
    
    /**
     * 缴费方式
     */
    private String payType;
    
    /**
     * 用户投币金额（银联扣款金额）
     */
    private String tMoney;
    
    /**
     * 流水号
     */
    private String formNum;
    
    /**--------------------写卡处理--------------------------
    /**
     * 写卡所需信息串
     * iccid~~imsi~~eki~~smsp~~this.pin1~~pin2~~puk1~~puk2;
     */
    private String cardInfoStr;
    
    /**--------------------日志处理--------------------------
     * 缴费日志SERVICE
     */
    private transient FeeChargeHubService feeChargeService;
    
    /**
	 * 缴费日志VO
	 */
	private transient CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
    
	/**
     * 更新日志方式，add/update
     */
    private String errorType;
    
    /**
     * 是否吐出银联卡 1 吐出
     */
    private String needReturnCard;
    
    /**
     * 是否支持跳转新营销活动推荐页面 1：支持  其它：不支持
     */
    private String toRecActFlag;
    
	/**
     * 处理异常
     *
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String prepareCardError()
    {
        // 记录错误日志到sh_rec_log
        this.createRecLog("prepareCard", "0", "0", "1", errormessage);
        
        return "prepareCardError";
    }
	
	/**
     * 校验号码和服务密码
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String validTelAndPwd()
    {
    	removeUserSession();
    	
        return "validTelAndPwd";
    }
    
	/**
	 * <清除用户session>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	private void removeUserSession()
	{
		if (getCustomerSimp() != null)
		{
			//清除session
            this.getRequest().getSession().removeAttribute(Constants.USER_INFO);
		}
	}
    
    /**
	 * <校验服务密码>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	public String validPassword()
	{
		String forward = SUCCESS;
		
        try
        {
        	//校验用户密码
    		Map authResult = userLoginBean.getUserInfoWithPwd(servnumber, password, getTerminalInfoPO());
    		NserCustomerSimp customer = (NserCustomerSimp)authResult.get("customerSimp");
    		
    		//校验失败
    		if(null == customer)
    		{
    			//错误信息转换
                String resultMsg = getConvertMsg((String) authResult.get("returnMsp"), 
                        "zz_04_01_000001", String.valueOf(authResult.get("errcode")), 
                        null);
                
                // 校验失败
                setErrormessage(resultMsg);
                
                // 记录日志:手机号码、业务类型、业务流水号、业务费用、状态（0：成功，1:）、描述
                this.createRecLog(servnumber, "prepareCard", "0", "0", "1", (String) authResult.get("returnMsp"));
                
                forward = ERROR;
    		}
    		else
    		{
    			//清除session
    			removeUserSession();
                
                //将新的用户信息存放在session中
                this.getRequest().getSession().setAttribute(Constants.USER_INFO, customer);
                
    			//不允许跨地区办理业务
    			if (!getTerminalInfoPO().getRegion().equals(customer.getRegionID()))
    			{
    	            setErrormessage("暂不允许跨地区办理备卡业务");
    	            
    	            // 记录日志:手机号码、业务类型、业务流水号、业务费用、状态（0：成功，1:）、描述
    	            this.createRecLog(servnumber, "prepareCard", "0", "0", "1", "暂不允许跨地区办理备卡业务");
    	            
    	            forward = ERROR;
    			}
    			else
    			{
    	        	//查询用户有无备卡
    	        	prepareCardBean.qryStoreCard(customer.getSubsID(), curMenuId, getTerminalInfoPO());
    			}
    		}
        }
        catch (ReceptionException e)
        {
            setErrormessage(e.getMessage());
            
            // 记录日志:手机号码、业务类型、业务流水号、业务费用、状态（0：成功，1:）、描述
            this.createRecLog(servnumber, "prepareCard", "0", "0", "1", e.getMessage());
            
            forward = ERROR;
        }
        
        // add begin hWX5316476 2015-2-9 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
        try
        {
            // 需要新营销活动推荐的时候转向推荐页面 
            if("1".equals(toRecActFlag))
            {
                this.getResponse().sendRedirect(this.getRequest().getContextPath()+"/recommendActivity/qryRecommendActList.action?curMenuId=" + curMenuId);
            }
        }
        catch (Exception e)
        {
           // 异常情况不做处理
        }
        // add end hWX5316476 2015-2-9 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
        return forward;
        
	}
	
	/**
	 * <验证随机验证码>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void validRandomPwd()
	{
		try 
		{
			String validResult = this.loginWithRandomPwd(getCustomerSimp().getServNumber(), randomPwd);
			writeXmlMsg(validResult);
		} 
		catch (Exception e) 
		{
			writeXmlMsg("");
		}
	}
	
    /**
	 * <校验用户身份并跳转至读取身份证页面>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String selectCertOrPwd()
	{
		return "selectCertOrPwd";
	}

	/**
	 * <读取身份证页面>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String selectCert()
	{
		return "selectCert";
	}
	
	/**
	 * <身份证信息显示页面>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String certShow()
	{
		return "certShow";
	}
	
	/**
	 * <校验随机验证码>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String selectRandomPwd()
	{
		return "selectRandomPwd";
	}
	
	/**
	 * <备卡算费页面>
	 * <功能详细描述>
	 * @return
	 * @throws Exception 
	 * @see [类、类#方法、类#成员]
	 */
	public String prepareFeeConfirm()
	{
		return "prepareFeeConfirm";
	}
	
	/**
	 * <校验用户的手机号码和身份证信息>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	public void checkIdCard()
	{
        try
        {
            //校验用户的身份证信息和手机号码是否相符
            reissueCardBean.checkReissueIdCard(idCardPO.getIdCardNo(), getCustomerSimp().getServNumber(), 
            		curMenuId, this.getTerminalInfoPO());
            
        	writeXmlMsg("0");
        }
        catch (ReceptionException e)
        {
           logger.error("校验用户的手机号码和身份证信息失败", e);
           writeXmlMsg("1~~"+e);
        }
	}
	
	/**
	 * <调用备卡算费接口>
	 * <功能详细描述>
	 * @return
	 * @throws Exception 
	 * @see [类、类#方法、类#成员]
	 */
	public void prepareRecFee()
	{
		try 
		{
			//备卡算费
			totalFee = prepareCardBean.reckonrecfeeByStore(getCustomerSimp().getServNumber(), simInfoPO.getIccid(), 
					curMenuId, getTerminalInfoPO());
			
			totalFee = CommonUtil.fenToYuan(totalFee);
			
			//备卡费用为0，不需要交费，直接写卡
			if(isZero(totalFee))
			{
				//备卡费用
				totalFee = "0";
				
				//需要写卡，需屏蔽超时返回主页功能
				this.getRequest().setAttribute("telProdFlag", "1");
			}
			
			writeXmlMsg("0~~"+totalFee);
		} 
		catch (ReceptionException e) 
		{
			logger.error("调用备卡算费接口失败：", e);
	        writeXmlMsg("1~~"+e.getMessage());
		}
		
	}
	
	/**
	 * <费用为0时备卡提交>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String freeCommit()
	{
		String forward = "prepareCommit";
		
		//费用为0时 交费方式传现金缴费 4
		String payTypeZero = Constants.PAYBYMONEY;
		
		//添加备卡业务日志
		String oid = addCardBusiLog();
		
		//添加备卡缴费日志
		String chargeLogOid = addFreeChargeLog(oid);
		
		// 更新备卡受理日志
		CardBusiLogPO cardBusi = new CardBusiLogPO();
		cardBusi.setOid(oid);
		
		//关联交费日志
		cardBusi.setChargeId(chargeLogOid);
		
		//写卡成功
		cardBusi.setWriteCardStatus("0");
		
		// 默认2：初始状态 0：缴费成功 1：缴费失败 
		cardBusi.setPayStatus("0");
		
		cardBusi.setNotes("费用为0时备卡写卡成功");
		
		this.getCardBusiService().updateInstallLog(cardBusi);
		
		// 提交后更新业务受理日志
		CardBusiLogPO cardBusiLog = new CardBusiLogPO();
		
		//日志id
		cardBusiLog.setOid(oid);
		
		//实际缴费金额 单位：分
		cardBusiLog.setToFee("0");
		
		try
		{
			//写卡成功接口
			simInfoPO.setWriteResult("1");
			simInfoPO.setErrCode("0");
			simInfoPO.setErrMsg("写卡成功");
			
			getCardInstallBean().updateWriteCardResult(getTerminalInfoPO(), curMenuId, cardBusiLogPO.getBlankCard(), simInfoPO);

			//备卡提交
			formNum = prepareCardBean.prepareCashCommit(getCustomerSimp().getServNumber(), simInfoPO.getIccid(), 
					CommonUtil.yuanToFen(tMoney), payTypeZero, curMenuId, getTerminalInfoPO());
	    	
			//----------更新交费日志状态----------
			CardChargeLogVO cardChargeLog = new CardChargeLogVO();
			
			cardChargeLog.setChargeLogOID(chargeLogOid);
			
			cardChargeLog.setStatus("11");
			
			cardChargeLog.setDescription("交费成功，业务办理成功");
			
			this.getCardBusiService().updateCardChargeLog(cardChargeLog);
			
			//----------更新交费日志结束----------
			//受理流水号，打印凭条时用到
			cardBusiLogPO.setFormnum(formNum);

			// 开户状态 默认2：初始状态 0：开户成功 1：开户失败
			cardBusiLog.setInstallStatus("0");
			
			//业务流水号
			cardBusiLog.setFormnum(formNum);
			
			//描述信息
			cardBusiLog.setNotes("备卡成功");
			
            // 记录日志
            this.createRecLog(getCustomerSimp().getServNumber(), "prepareCard", formNum, "0", "0", "备卡成功");	

		}
		catch(ReceptionException e)
		{	
			logger.error("备卡提交失败", e);
			
			try
			{
				//写卡成功接口
				simInfoPO.setWriteResult("-1");
				simInfoPO.setErrCode("-1");
				simInfoPO.setErrMsg(e.getMessage());
				getCardInstallBean().updateWriteCardResult(getTerminalInfoPO(), curMenuId, cardBusiLogPO.getBlankCard(), simInfoPO);
				
			}
			catch(ReceptionException ex)
			{
				logger.error("写卡失败接口失败：", ex);
			}

			// 开户状态 默认2：初始状态 0：开户成功 1：开户失败
			cardBusiLog.setInstallStatus("1");
			
			//描述信息
			cardBusiLog.setNotes(e.getMessage());
			
			//错误信息
			setErrormessage(e.getMessage());
			
            // 记录日志
            this.createRecLog(getCustomerSimp().getServNumber(), "prepareCard", "0", "0", "1", e.getMessage());
			
            forward = ERROR;
		}
		
		//备卡提交后更新日志
		this.getCardBusiService().updateInstallLog(cardBusiLog);
		
		return forward;
	}
	
	/**
	 * <备卡备卡付费方式选择页面>
	 * <功能详细描述>
	 * @return
	 * @throws Exception 
	 * @see [类、类#方法、类#成员]
	 */
	public String selectPayTypePrepare() throws  Exception
	{
		String forward = "selectPayTypePrepare";

		//增加受理日志
		addCardBusiLog();
		
		logger.info("当前菜单："+curMenuId);
		
		// 获取可用的充值方式
        usableTypes = getPayType(getTerminalInfoPO().getTermgrpid());
        
        // findbugs修改 2015-09-02 zKF66389
        //logger.info("当前，备卡缴费的可选方式有" + (usableTypes == null ? 0 : usableTypes.size()) + "种");
        // findbugs修改 2015-09-02 zKF66389
        
        // findbugs修改 2015-09-02 zKF66389
        //if (usableTypes == null || usableTypes.size() == 0)
        if (usableTypes.size() == 0)
        // findbugs修改 2015-09-02 zKF66389
        {
            // 没有可用的充值方式
            setErrormessage("对不起，暂时没有可用的充值方式，请返回执行其他操作。");
            
            // 记录日志
            this.createRecLog(getCustomerSimp().getServNumber(), "prepareCard", "0", "0", "1", "暂时没有可用的充值方式");
            
            forward = "error";
        }
		
		return forward;
	}
	
	/**
	 * <现金缴费备卡提交 取卡打印发票页面>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String prepareCashCommit()
	{
		String forward = "prepareCashCommit";
		
		// 防止用户不投币，直接从浏览器中模拟交费请求
        if (null == getRequest().getHeader("Referer"))       
        {
            setErrormessage("无效请求");
            
            return "prepareCardError";
        }
        
        // 终端流水(终端id+现金缴费流水 并取后20位)
        String terminalSeq = "";
        
        if(StringUtils.isNotEmpty(cardChargeLogVO.getTerminalSeq()))
        {
	        terminalSeq = getTerminalInfoPO().getTermid() + cardChargeLogVO.getTerminalSeq();
	        
	        if (terminalSeq.length() > 20)
	        {
	            terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
	        }
        }

        // 记录现金缴费日志
        String chargeLogOID = feeChargeService.getChargeLogOID();
        
        // 组装数据
        cardChargeLogVO.setChargeLogOID(chargeLogOID);
        cardChargeLogVO.setRegion(getTerminalInfoPO().getRegion());
        cardChargeLogVO.setTermID(getTerminalInfoPO().getTermid());
        cardChargeLogVO.setOperID(getTerminalInfoPO().getOperid());
        cardChargeLogVO.setServnumber(getCustomerSimp().getServNumber());
        cardChargeLogVO.setPayType(payType);
        cardChargeLogVO.setFee(CommonUtil.yuanToFen(tMoney));
        cardChargeLogVO.setTerminalSeq(terminalSeq);
        cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
        cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());
        cardChargeLogVO.setOrgID(getTerminalInfoPO().getOrgid());
        cardChargeLogVO.setStatus("01");
        cardChargeLogVO.setDescription("备卡现金缴费成功");
        cardChargeLogVO.setRecType("prepareCard");
        
        // 添加缴费日志
        feeChargeService.addChargeLog(cardChargeLogVO);
        
        //更新业务受理日志
        cardBusiLogPO.setChargeId(chargeLogOID);
        cardBusiLogPO.setChargeType(payType);
        
        //实际缴费金额
        cardBusiLogPO.setToFee(CommonUtil.yuanToFen(tMoney));
		
        // 默认2：初始状态 0：缴费成功 1：缴费失败 
		cardBusiLogPO.setPayStatus("0");
		
    	// 默认2：初始状态 0：写卡成功 1：写卡失败 
		cardBusiLogPO.setWriteCardStatus("0");

		cardBusiLogPO.setNotes("备卡现金缴费成功");
		
		logger.info("-----备卡提交前新增缴费日志，更新受理日志成功！-----chargeLogOID:"+chargeLogOID+",Region:"+getTerminalInfoPO().getRegion()+",Termid:"+getTerminalInfoPO().getTermid()+
				",Operid:"+getTerminalInfoPO().getOperid()+",servnumber:"+getCustomerSimp().getServNumber()+",payType:"+payType+",tMoney:"+CommonUtil.yuanToFen(tMoney)+
				",terminalSeq:"+terminalSeq+",Orgid:"+getTerminalInfoPO().getOrgid());
        
    	this.getCardBusiService().updateInstallLog(cardBusiLogPO);
    	
		// 更新业务受理日志
		CardBusiLogPO cardBusiLog = new CardBusiLogPO();
		cardBusiLog.setOid(cardBusiLogPO.getOid());
    	
    	try
    	{
			//写卡成功接口
			simInfoPO.setWriteResult("1");
			simInfoPO.setErrCode("0");
			simInfoPO.setErrMsg("写卡成功");
    		getCardInstallBean().updateWriteCardResult(getTerminalInfoPO(), curMenuId, cardBusiLogPO.getBlankCard(), simInfoPO);
            
    		formNum = prepareCardBean.prepareCashCommit(getCustomerSimp().getServNumber(), simInfoPO.getIccid(), 
    				CommonUtil.yuanToFen(tMoney), payType, curMenuId, getTerminalInfoPO());

			//用于打印小票时取值
			cardBusiLogPO.setFormnum(formNum);
			
			// 开户状态 默认2：初始状态 0：开户成功 1：开户失败
			cardBusiLog.setInstallStatus("0");
			
			//业务流水号
			cardBusiLog.setFormnum(formNum);
			
			cardBusiLog.setNotes("现金缴费成功，备卡成功");
			
			//-----------------备卡成功后更新交费日志-------------
			CardChargeLogVO cardChargeLog = new CardChargeLogVO();
			
			//日志id
			cardChargeLog.setChargeLogOID(cardChargeLogVO.getChargeLogOID());
			
			//扣款成功，业务办理成功
			cardChargeLog.setStatus("11");
			
			//描述信息
			cardChargeLog.setDescription("现金缴费成功，备卡业务办理成功");
			
			//更新交费日志
			this.getCardBusiService().updateCardChargeLog(cardChargeLog);
			
	        // 记录日志
	        this.createRecLog(getCustomerSimp().getServNumber(), "prepareCard", formNum, CommonUtil.yuanToFen(tMoney), "0", "备卡成功");
			logger.info("------备卡提交接口调用成功！现金缴费成功，更新受理日志，，更新操作日志成功！流水号为"+formNum+"----------");

    	}
    	catch(ReceptionException e)
    	{
			logger.error("备卡提交失败", e);
			
			try
			{
				//写卡失败接口
				simInfoPO.setWriteResult("-1");
				simInfoPO.setErrCode("-1");
				simInfoPO.setErrMsg(e.getMessage());
				getCardInstallBean().updateWriteCardResult(getTerminalInfoPO(), curMenuId, cardBusiLogPO.getBlankCard(), simInfoPO);
				
			}
			catch(ReceptionException ex)
			{
				logger.error("写卡失败接口失败：", ex);
			}
			
			// 更新业务受理日志
			// 开户状态 默认2：初始状态 0：开户成功 1：备卡失败
			cardBusiLog.setInstallStatus("1");
			
			cardBusiLog.setNotes(e.getMessage());
			

			setErrormessage(e.getMessage());
			
            // 记录日志
            this.createRecLog(getCustomerSimp().getServNumber(), "prepareCard", "0", CommonUtil.yuanToFen(tMoney), "1", e.getMessage());
			forward = ERROR;
    	}
		
		//备卡提交后更新日志
		this.getCardBusiService().updateInstallLog(cardBusiLog);

		return forward;
	}
	
	/**
	 * <银联卡备卡提交>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String unionCardCommit()
	{
		String forward = "unionCardCommit";
		
		//-----------------备卡成功后更新备卡业务日志-------------
		CardBusiLogPO cardBusiLog = new CardBusiLogPO();
		
		cardBusiLog.setOid(cardBusiLogPO.getOid());
		
		// 默认2：初始状态 0：写卡成功 1：写卡失败 
		cardBusiLog.setWriteCardStatus("0");
		
		try
		{
			//写卡成功接口
			simInfoPO.setWriteResult("1");
			simInfoPO.setErrCode("0");
			simInfoPO.setErrMsg("写卡成功");
			
			getCardInstallBean().updateWriteCardResult(getTerminalInfoPO(), curMenuId, cardBusiLogPO.getBlankCard(), simInfoPO);

			//备卡提交
			formNum = prepareCardBean.prepareCashCommit(getCustomerSimp().getServNumber(), simInfoPO.getIccid(), 
					tMoney, payType, curMenuId, getTerminalInfoPO());

			//用于打印小票时取值
			cardBusiLogPO.setFormnum(formNum);
			
			// 备卡状态 默认2：初始状态 0：备卡成功 1：备卡失败
			cardBusiLog.setInstallStatus("0");
			
			//业务流水号
			cardBusiLog.setFormnum(formNum);
			cardBusiLog.setNotes("备卡业务办理成功");
			
			//-----------------备卡成功后更新交费日志-------------
			CardChargeLogVO cardChargeLog = new CardChargeLogVO();
			
			//日志id
			cardChargeLog.setChargeLogOID(cardChargeLogVO.getChargeLogOID());
			
			//扣款成功，业务办理成功
			cardChargeLog.setStatus("11");
			
			//描述信息
			cardChargeLog.setDescription("扣款成功，备卡业务办理成功");
			
			//更新交费日志
			this.getCardBusiService().updateCardChargeLog(cardChargeLog);
			
			// 记录日志
            this.createRecLog(getCustomerSimp().getServNumber(), "prepareCard", formNum, tMoney, "0", "备卡成功");
			
    		//用于页面显示
    		tMoney = CommonUtil.fenToYuan(tMoney);
    		
			logger.info("-------银联缴费备卡提交后更新缴费日志、业务受理日志和操作日志-------busioid:"+cardBusiLogPO.getOid()+",tMoney:"+tMoney+",formNum:"+formNum+",chargelogoid:"+cardChargeLogVO.getChargeLogOID());
		}
		catch(ReceptionException e)
		{
			logger.error("备卡提交失败", e);
			
			try
			{
				//写卡失败接口
				simInfoPO.setWriteResult("-1");
				simInfoPO.setErrCode("-1");
				simInfoPO.setErrMsg(e.getMessage());
				getCardInstallBean().updateWriteCardResult(getTerminalInfoPO(), curMenuId, cardBusiLogPO.getBlankCard(), simInfoPO);
				
			}
			catch(ReceptionException ex)
			{
				logger.error("写卡失败接口失败：", ex);
			}
			
			//-----------------备卡成功后更新备卡业务日志-------------
			// 开户状态 默认2：初始状态 0：开户成功 1：备卡失败
			cardBusiLog.setInstallStatus("1");
			
			cardBusiLog.setNotes(e.getMessage());
			
			setErrormessage(e.getMessage());
			
			// 记录日志
            this.createRecLog(getCustomerSimp().getServNumber(), "prepareCard", "0", tMoney, "1", e.getMessage());
            
			forward = ERROR;
		}
		
		
		//备卡提交后更新备卡业务日志
		this.getCardBusiService().updateInstallLog(cardBusiLog);
		
		return forward;
	}
	
	/**
	 * <现金缴费>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String cashPayPrepare()
	{
		this.getRequest().setAttribute("telProdFlag", "1");
		return "cashPayPrepare";
	}
	
	/**
	 * <银联卡缴费>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String cardPayPrepare()
	{
		
		return "cardPayPrepare";
	}
	
	/**
	 * <输入银联卡密码页面>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String cardPwdPrepare()
	{
		
		return "cardPwdPrepare";
	}
	
	/**
	 * <银联卡缴费确认页面>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String cardConfirmPrepare()
	{
		this.getRequest().setAttribute("telProdFlag", "1");
		return "cardConfirmPrepare";
	}
	
	/**
	 * <增加备卡受理日志>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	private String addCardBusiLog()
	{
		String oid = this.getCardBusiService().getInstallId();
		//日志id
		cardBusiLogPO.setOid(oid);
		
        // 终端号
        cardBusiLogPO.setTermId(getTerminalInfoPO().getTermid());
        
        // 办理类型prepareCard 备卡
        cardBusiLogPO.setRectype("prepareCard");

        //证件号码
		cardBusiLogPO.setCertId(idCardPO.getIdCardNo());
		
		//用户名称
		cardBusiLogPO.setCustName(idCardPO.getIdCardName());
		
		//iccid
		cardBusiLogPO.setIccid(simInfoPO.getIccid());
		
		//imsi
		cardBusiLogPO.setImsi(simInfoPO.getImsi());
		
        //性别
        cardBusiLogPO.setSex(idCardPO.getIdCardSex());
		
		//住址
		cardBusiLogPO.setLinkAddr(idCardPO.getIdCardAddr());
		
		//手机号码
		cardBusiLogPO.setServnumber(getCustomerSimp().getServNumber());
		
		//subsid
		cardBusiLogPO.setSubsId(getCustomerSimp().getSubsID());
		
		//缴费方式
		cardBusiLogPO.setChargeType(payType);
		
		//地区
		cardBusiLogPO.setRegion(getTerminalInfoPO().getRegion());
		
		//备卡费用
		cardBusiLogPO.setRecFee(CommonUtil.yuanToFen(totalFee));
		
		// 默认2：初始状态 0：写卡成功 1：写卡失败 
		cardBusiLogPO.setWriteCardStatus("2");
      
		// 默认2：初始状态 0：缴费成功 1：缴费失败 
		cardBusiLogPO.setPayStatus("2");
		
		// 开户状态 默认2：初始状态 0：开户成功 1：开户失败
		cardBusiLogPO.setInstallStatus("2");
		
		// 默认2：初始状态 0：退款成功 1：退款失败
		cardBusiLogPO.setRefundment("2");
		
		// 备注
		cardBusiLogPO.setNotes("备卡费用已确认，开始缴费");
		logger.info("-----增加业务受理日志------oid:"+oid+",termid："+getTerminalInfoPO().getTermid()+"," +
				"idCardNo："+idCardPO.getIdCardNo()+",IdCardName："+idCardPO.getIdCardName()+",Iccid："+simInfoPO.getIccid()+",Imsi："
				+simInfoPO.getImsi()+",IdCardSex："+idCardPO.getIdCardSex()+",IdCardAddr："+idCardPO.getIdCardAddr()+",Region："
				+getTerminalInfoPO().getRegion()+",totalFee："+CommonUtil.yuanToFen(totalFee));
		this.getCardBusiService().addLogInstall(cardBusiLogPO);
		
		return oid;
	}
	
	/**
	 * <判断费用是否为0>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private boolean isZero(String fee)
	{
		//若为空，则返回true
		if (StringUtils.isBlank(fee))
		{
			return true;
		}
		
		//判断应收费用是否为0，若为0则跳过交费页面
		BigDecimal zero = new BigDecimal("0");
		
		return (0 == zero.compareTo(new BigDecimal(fee)) ? true : false);
	}
	
	/**
	 * <备卡费用为0时记录日志>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String addFreeChargeLog(String oid)
	{
		String logOID = feeChargeService.getChargeLogOID();
		
		//交费日志id，与备卡日志chargeid关联
		cardChargeLogVO.setChargeLogOID(logOID);
		
		//终端地区
        cardChargeLogVO.setRegion(getTerminalInfoPO().getRegion());
        
        //终端id
        cardChargeLogVO.setTermID(getTerminalInfoPO().getTermid());
        
        //终端机操作员id
        cardChargeLogVO.setOperID(getTerminalInfoPO().getOperid());
        
        //手机号码
        cardChargeLogVO.setServnumber(getCustomerSimp().getServNumber());
        
        //号码归属地
        cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());
        
        //组织结构id
        cardChargeLogVO.setOrgID(getTerminalInfoPO().getOrgid());
        
        //交费金额
        cardChargeLogVO.setFee(CommonUtil.yuanToFen(totalFee));
        
        //交费状态 初始状态:11
        cardChargeLogVO.setStatus("10");
        
        //交费方式，为方便对账，设为现金缴费
        cardChargeLogVO.setPayType(Constants.PAYBYMONEY);
        
        //交易时间
        cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
        
        //业务类型
        cardChargeLogVO.setRecType("prepareCard");

        //描述
        cardChargeLogVO.setDescription("备卡费用为0，无需交费");
		
		// 插入缴费日志
		feeChargeService.addChargeLog(cardChargeLogVO);
		
		// 更新备卡受理日志
		CardBusiLogPO cardBusiLog = new CardBusiLogPO();
		cardBusiLog.setOid(oid);
		
		//关联交费日志
		cardBusiLog.setChargeId(logOID);
		
		//交费类型
		cardBusiLog.setChargeType(Constants.PAYBYMONEY);
		
		// 默认2：初始状态 0：缴费成功 1：缴费失败 
		cardBusiLog.setPayStatus("0");
		
		//实际缴费金额
		cardBusiLog.setToFee(CommonUtil.yuanToFen(totalFee));
		
		cardBusiLog.setNotes(errormessage);
		
		this.getCardBusiService().updateInstallLog(cardBusiLog);
		
		logger.info("备卡费用为0，缴费日志添加成功！");
		
		return logOID;
	}
	
	/**
	 * <银联扣款之前增加交费日志>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	public void addUnionCardLog()
	{
		try 
		{
			String logOID = feeChargeService.getChargeLogOID();
			
			//交费日志id，与备卡日志chargeid关联
			cardChargeLogVO.setChargeLogOID(logOID);
			
			//终端地区
            cardChargeLogVO.setRegion(getTerminalInfoPO().getRegion());
            
            //终端id
            cardChargeLogVO.setTermID(getTerminalInfoPO().getTermid());
            
            //终端机操作员id
            cardChargeLogVO.setOperID(getTerminalInfoPO().getOperid());
            
            //手机号码
            cardChargeLogVO.setServnumber(getCustomerSimp().getServNumber());
            
            //受理地区
            cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());
            
            //支付方式
            cardChargeLogVO.setPayType("1");
            
            //交费金额
            cardChargeLogVO.setFee(CommonUtil.yuanToFen(totalFee));
            
            //加密的银联卡卡号
            cardChargeLogVO.setCardnumber(EncryptDecryptUtil.encryptAesPwd(cardChargeLogVO.getCardnumber()));
            
            //交费状态 初始状态:00
            cardChargeLogVO.setStatus("00");
            
            //交易时间
            cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
            
            //业务类型
            cardChargeLogVO.setRecType("prepareCard");
            
            //组织结构
            cardChargeLogVO.setOrgID(getTerminalInfoPO().getOrgid());

            //描述
            cardChargeLogVO.setDescription("银联扣款之前增加缴费日志");
			
			// 添加缴费日志
			feeChargeService.addChargeLog(cardChargeLogVO);
			
			// 更新备卡受理日志
			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
			cardBusiLog.setOid(cardBusiLogPO.getOid());
			
			//交费类型
			cardBusiLog.setChargeType(Constants.PAYBYUNIONCARD);
			
			//关联交费日志
			cardBusiLog.setChargeId(logOID);
			this.getCardBusiService().updateInstallLog(cardBusiLog);
			
			logger.info("-----银联缴费前增加缴费日志，更新受理日志关联chargeid------ChargeLogOID:"+logOID+",Region："+getTerminalInfoPO().getRegion()+"," +
					"Termid："+getTerminalInfoPO().getTermid()+",Operid："+getTerminalInfoPO().getOperid()+",servnumber："+getCustomerSimp().getServNumber()+",totalFee："
					+CommonUtil.yuanToFen(totalFee)+",Cardnumber："+cardChargeLogVO.getCardnumber()+",tMoney："+tMoney);
			writeXmlMsg("0~~" + logOID);
		} 
		catch (Exception e) 
		{
			logger.error("扣款钱添加交费日志和更新备卡日志异常：", e);
			writeXmlMsg("1");
		}
		
	}
	
	/**
	 * <银联扣款成功之后更新缴费日志>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	public void updateUnionCardLog()
	{
        try 
        {
			String busitype = cardChargeLogVO.getBusiType();
			
			cardChargeLogVO.setBusiType(java.net.URLDecoder.decode(busitype, "UTF-8"));
			
			//银联扣款费用
			String unionpayfee = cardChargeLogVO.getUnionpayfee();
			
			// modify begin wWX217192 2015-5-25 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
            unionpayfee = CommonUtil.formatNumberStr(unionpayfee);
            // modify end wWX217192 2015-5-25 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
			
			cardChargeLogVO.setUnionpayfee(unionpayfee);
			
			cardChargeLogVO.setStatus("01");
			cardChargeLogVO.setDescription("银联扣款成功");
			
			cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
			
			String initPosResCode = cardChargeLogVO.getPosResCode();
			
			cardChargeLogVO.setPosResCode(null == initPosResCode ? "" : initPosResCode);

			// ---------------更新备卡缴费日志----------------
			feeChargeService.updateCardChargeLog(cardChargeLogVO);
			
			// ---------------更新备卡受理日志----------------
			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
			
			//日志id
			cardBusiLog.setOid(cardBusiLogPO.getOid());
			
	        //实际缴费金额
	        cardBusiLog.setToFee(unionpayfee);
			
	        // 默认2：初始状态 0：缴费成功 1：缴费失败 
			cardBusiLog.setPayStatus("0");
			
			cardBusiLog.setNotes("备卡银联缴费成功");
	        
			//更新备卡受理日志
			this.getCardBusiService().updateInstallLog(cardBusiLog);
			
			writeXmlMsg("0");
			
			logger.info("-------银联扣款成功之后更新缴费日志和业务受理日志-----------busitype:"+busitype+",unionpayfee:"+CommonUtil.yuanToFen(unionpayfee)+",initPosResCode:"+initPosResCode+",busioid:"+cardBusiLogPO.getOid());
		} 
        catch (Exception e) 
        {
			logger.error("扣款之后更新日志失败", e);
			writeXmlMsg("1");
		}
		
	}
	
	/**
	 * <错误时记录sh_rec_log日志>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String addRecLog()
	{
		this.createRecLog(getCustomerSimp().getServNumber(), "prepareCard", "0", "0", "1", errormessage);
		return ERROR;
	}
	
	/**
	 * <处理现金缴费异常>
	 * <功能详细描述>
	 * @return	 
	 * @see [类、类#方法、类#成员]
	 */
	public String goCashError()
	{
        try
        {
        	
        	//记录sh_rec_log日志
            this.createRecLog(getCustomerSimp().getServNumber(), "prepareCard", "0", "0", "1", errormessage);
            
            //终端机
            TerminalInfoPO termInfoPO = getTerminalInfoPO();
            
            //----------------更新交费日志-----------------
            //支付类型
            cardChargeLogVO.setPayType(payType);
            	
            if (StringUtils.isNotBlank(cardChargeLogVO.getTerminalSeq()))
            {
                String terminalSeq = getTerminalInfoPO().getTermid() + cardChargeLogVO.getTerminalSeq();
                if (terminalSeq.length() > 20)
                {
                    terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
                }
                
                cardChargeLogVO.setTerminalSeq(terminalSeq);
            }
            else
            {
            	cardChargeLogVO.setTerminalSeq("");
            }

            if (StringUtils.isBlank(tMoney) || "0".equals(tMoney.trim()))
            {
            	cardChargeLogVO.setStatus("00");
            }
            else
            {
            	cardChargeLogVO.setStatus("10");
            }
            
            String chargeLogId = feeChargeService.getChargeLogOID();
            
        	cardChargeLogVO.setChargeLogOID(chargeLogId);
        	cardChargeLogVO.setRegion(termInfoPO.getRegion());
        	cardChargeLogVO.setTermID(termInfoPO.getTermid());
        	cardChargeLogVO.setOperID(termInfoPO.getOperid());
        	cardChargeLogVO.setOrgID(termInfoPO.getOrgid());
        	cardChargeLogVO.setServnumber(getCustomerSimp().getServNumber());
        	cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());
            
        	cardChargeLogVO.setFee(CommonUtil.yuanToFen(tMoney));
            
        	cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
        	cardChargeLogVO.setDescription(errormessage);
        	cardChargeLogVO.setRecType("prepareCard");
            
            feeChargeService.addChargeLog(cardChargeLogVO);
            
			//--------更新备卡受理日志--------
			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
			
			
			cardBusiLog.setOid(cardBusiLogPO.getOid());
			
			//关联交费日志
			cardBusiLog.setChargeId(chargeLogId);
			
			//写卡状态
			cardBusiLog.setWriteCardStatus(cardBusiLogPO.getWriteCardStatus());
			
			//支付类型
			cardBusiLog.setPayStatus(cardBusiLogPO.getPayStatus());
			
			//实收费用
			cardBusiLog.setToFee(CommonUtil.yuanToFen(tMoney));
			
			//交费类型
			cardBusiLog.setChargeType(payType);
			
			//错误信息
			cardBusiLog.setNotes(errormessage);
			
			this.getCardBusiService().updateInstallLog(cardBusiLog);
        }
        
        //捕获异常，避免吞卡等情况
        catch (Exception e)
        {
            logger.error("记录扣款错误日志异常：",e);
            errormessage = errormessage + e.getMessage();
        }
        
		return ERROR;
	}

	/**
	 * <处理交费和写卡异常>
	 * <功能详细描述>
	 * @return	 
	 * @see [类、类#方法、类#成员]
	 */
	public String goCardError()
	{
        try
        {
        	//错误信息
        	String description = errormessage.length()< 256 ? errormessage : errormessage.substring(0,256);
        	
            this.createRecLog(getCustomerSimp().getServNumber(), "prepareCard", "0", "0", "1", description);
            
            //银联交费后写卡异常处理
            if("1".equals(cardBusiLogPO.getWriteCardStatus()))
            {
    			//--------更新备卡受理日志--------
    			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
    			
    			cardBusiLog.setOid(cardBusiLogPO.getOid());
    			
    			//写卡状态
    			cardBusiLog.setWriteCardStatus(cardBusiLogPO.getWriteCardStatus());
    			
    			//错误信息
    			cardBusiLog.setNotes(description);
    			
    			this.getCardBusiService().updateInstallLog(cardBusiLog);
    			
    			return ERROR;
    			
            }
            
            //新增银联交费和现金缴费日志
            if (StringUtils.isEmpty(errorType) || "add".equalsIgnoreCase(errorType))
            {
            	cardChargeLogVO.setPayType(payType);
            	
                cardChargeLogVO.setTerminalSeq("");

                if (StringUtils.isBlank(tMoney) || "0".equals(tMoney.trim()))
                {
                	cardChargeLogVO.setStatus("00");
                }
                else
                {
                	cardChargeLogVO.setStatus("10");
                }
                
                String chargeLogId = feeChargeService.getChargeLogOID();
                
            	cardChargeLogVO.setChargeLogOID(chargeLogId);
            	cardChargeLogVO.setRegion(getTerminalInfoPO().getRegion());
            	cardChargeLogVO.setTermID(getTerminalInfoPO().getTermid());
            	cardChargeLogVO.setOperID(getTerminalInfoPO().getOperid());
            	cardChargeLogVO.setOrgID(getTerminalInfoPO().getOrgid());
            	cardChargeLogVO.setServnumber(getCustomerSimp().getServNumber());
            	cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());
                
            	cardChargeLogVO.setFee(CommonUtil.yuanToFen(tMoney));
                
            	cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
            	cardChargeLogVO.setDescription(description);
            	cardChargeLogVO.setRecType("prepareCard");
                
                feeChargeService.addChargeLog(cardChargeLogVO);
                
    			//--------关联更新备卡受理日志--------
    			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
    			
    			//日志id
    			cardBusiLog.setOid(cardBusiLogPO.getOid());
    			
    			//实际缴费金额
    			cardBusiLog.setToFee(CommonUtil.yuanToFen(tMoney));
    			
    			//交费状态
    			cardBusiLog.setPayStatus(cardBusiLogPO.getPayStatus());
    			
    			//关联交费日志
    			cardBusiLog.setChargeId(chargeLogId);
    			
    			//交费类型
    			cardBusiLog.setChargeType(payType);
    			
    			this.getCardBusiService().updateInstallLog(cardBusiLog);
            }
            
            // 更新银联扣款日志
            else
            {
                if (StringUtils.isBlank(tMoney) || "".equals(tMoney.trim()))
                {
                	cardChargeLogVO.setStatus("00");
                }
                else
                {
                	cardChargeLogVO.setStatus("10");
                }
                cardChargeLogVO.setDescription(errormessage);
                
                //银联终端号
                cardChargeLogVO.setUnionpaycode(getTerminalInfoPO().getUnionpaycode());
                
                //银联商户号
                cardChargeLogVO.setUnionpayuser(getTerminalInfoPO().getUnionuserid());
                
                feeChargeService.updateChargeLog(cardChargeLogVO);
            }
        }
        
        //捕获异常，避免吞卡等情况
        catch (Exception e)
        {
            logger.error("记录扣款错误日志异常：",e);
            errormessage = errormessage + e.getMessage();
        }
        
		return ERROR;
	}
	
	/**
	 * <备卡费用为0时写卡异常新增业务、缴费和操作日志>
	 * <功能详细描述>
	 * @return	 
	 * @see [类、类#方法、类#成员]
	 */
	public String goFreeWriteError()
	{
		//添加备卡业务日志
		String cardBusiLogOid = addCardBusiLog();
		
		//直接记录交费日志
		String chargeLogOid = addFreeChargeLog(cardBusiLogOid);
		
		// 更新备卡受理日志
		CardBusiLogPO cardBusiLog = new CardBusiLogPO();
		cardBusiLog.setOid(cardBusiLogOid);
		
		//关联交费日志
		cardBusiLog.setChargeId(chargeLogOid);
		
		// 默认2：初始状态 0：缴费成功 1：缴费失败 
		cardBusiLog.setPayStatus("0");
		
		//写卡失败
		cardBusiLog.setWriteCardStatus("1");
		
		//写卡失败
		cardBusiLog.setNotes(errormessage);
		
		this.getCardBusiService().updateInstallLog(cardBusiLog);
		
		//失败日志
		this.createRecLog(getCustomerSimp().getServNumber(), "prepareCard", "0", "0", "1", errormessage);
		
		return ERROR;
	}
	
	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}

	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PrepareCardBean getPrepareCardBean() {
		return prepareCardBean;
	}

	public void setPrepareCardBean(PrepareCardBean prepareCardBean) {
		this.prepareCardBean = prepareCardBean;
	}

	public ReissueCardBean getReissueCardBean() {
		return reissueCardBean;
	}

	public void setReissueCardBean(ReissueCardBean reissueCardBean) {
		this.reissueCardBean = reissueCardBean;
	}

	public List getUsableTypes() {
		return usableTypes;
	}

	public void setUsableTypes(List usableTypes) {
		this.usableTypes = usableTypes;
	}
    
	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTMoney() {
		return tMoney;
	}

	public void setTMoney(String money) {
		tMoney = money;
	}

	public String getFormNum() {
		return formNum;
	}

	public void setFormNum(String formNum) {
		this.formNum = formNum;
	}

	public FeeChargeHubService getFeeChargeService() {
		return feeChargeService;
	}

	public void setFeeChargeService(FeeChargeHubService feeChargeService) {
		this.feeChargeService = feeChargeService;
	}

	public CardChargeLogVO getCardChargeLogVO() {
		return cardChargeLogVO;
	}

	public void setCardChargeLogVO(CardChargeLogVO cardChargeLogVO) {
		this.cardChargeLogVO = cardChargeLogVO;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getCardInfoStr() {
		return cardInfoStr;
	}

	public void setCardInfoStr(String cardInfoStr) {
		this.cardInfoStr = cardInfoStr;
	}

	/**
	 * @return 返回 servnumber
	 */
	public String getServnumber() {
		return servnumber;
	}

	/**
	 * @param 对servnumber进行赋值
	 */
	public void setServnumber(String servnumber) {
		this.servnumber = servnumber;
	}

	/**
	 * @return 返回 randomPwd
	 */
	public String getRandomPwd() {
		return randomPwd;
	}

	/**
	 * @param 对randomPwd进行赋值
	 */
	public void setRandomPwd(String randomPwd) {
		this.randomPwd = randomPwd;
	}

	/**
	 * @return 返回 needReturnCard
	 */
	public String getNeedReturnCard() {
		return needReturnCard;
	}

	/**
	 * @param 对needReturnCard进行赋值
	 */
	public void setNeedReturnCard(String needReturnCard) {
		this.needReturnCard = needReturnCard;
	}

    public String getToRecActFlag()
    {
        return toRecActFlag;
    }

    public void setToRecActFlag(String toRecActFlag)
    {
        this.toRecActFlag = toRecActFlag;
    }
	
}
