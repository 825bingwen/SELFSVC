/*
 * 文 件 名:  ReissueCardAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  山东空白卡补卡
 * 修 改 人: zKF69263
 * 修改时间:  2014-12-30
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.cardbusi.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.ReissueCardBean;
import com.customize.sd.selfsvc.cardbusi.model.CardBusiLogPO;
import com.customize.sd.selfsvc.cardbusi.model.FeeConfirmPO;
import com.customize.sd.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.sd.selfsvc.charge.service.FeeChargeService;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DateUtil;

/**
 * 补卡
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-12-27]
 * @see  [相关类/方法]
 * @since  [产品/OR_SD_201407_1069_山东_关于在自助终端增加开户、补卡功能的需求]
 */
public class ReissueCardAction extends CardBusiBaseAction
{
	/**
     * 注释内容
     */
    private static final long serialVersionUID = 538199090873947646L;

    /**
     * 序列化
     */
	
	private static Log logger = LogFactory.getLog(ReissueCardAction.class);
	
	/**
	 * 补卡接口调用
	 */
	private transient ReissueCardBean reissueCardBean;
	
    /**---------日志处理--------------
     * 交费日志
     */
    private transient FeeChargeService feeChargeService;
	
	/**
	 * 银联卡交费vo
	 */
	private transient CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
	
	/**
	 * 用户手机号码
	 */
	private String servnumber;
	
	/**
	 * 密码
	 */
	private String password;
	
    /**-----------------------交费相关-----------------------------
     * 可用交费方式
     */
    private List usableTypes;
    
    /**
     * 用户投币金额/银联扣款金额
     */
    private String tMoney = "0";
    
    /**
     * 支付方式 1：银联卡，4：现金
     */
    private String payType;
    
    /**
     * 是否需要吐出银联卡 1：需要
     */
    private String needReturnCard;
    
    /**
     * 银联打印信息
     */
    private String printcontext = "";
    
	/**
	 * <补卡登录页面>
	 * <跳转补卡登录页面，要求输入两次手机号码和服务密码>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String inputTelnum()
	{
		//首先清除session
		removeUserSession();
		
		return "inputTelnum";
	}
	
	/** 
     * 取得终端机补卡提示信息
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String getReissueCardInfo()
    {
        // 终端机补卡提示信息
        String reissueCardInfo = (String)PublicCache.getInstance().getCachedData("SH_REISSUECARD_INFO");
        
        return reissueCardInfo + "<br/>" + getBlankCardTypeAlertMsg();
    }
	
	/**
	 * <校验用户身份并跳转至读取身份证页面>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String readCert()
	{
		String forward = "readCert";
        
        try 
        {
            // 校验用户密码
            NserCustomerSimp customer = reissueCardBean.checkSubTelPwd(servnumber,
                password, getCurMenuId(), getTerminalInfoPO());
            
            // 补卡规则校验
            reissueCardBean.checkReissueNum(servnumber, curMenuId, getTerminalInfoPO());
            
            // 校验补卡次数
            getCardBusiService().checkReissueNum(customer);
            
            // 首先清除session
            removeUserSession();
            
            // 将新的用户信息存放在session中
            this.getRequest().getSession().setAttribute(Constants.USER_INFO, customer);
        } 
        catch (ReceptionException e) 
        {
            // 错误信息
            setErrormessage(e.getMessage());
            
            // 记录日志:手机号码、业务类型、业务流水号、业务费用、状态（0：成功，1:）、描述
            this.createRecLog(servnumber, "reissueCard", "0", "0", "1", e.getMessage());
            
            forward = ERROR;
        }
		
		return forward;
	}
	
	/**
	 * <错误时记录sh_rec_log日志>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String addRecLog()
	{
		this.createRecLog((null != getCustomerSimp()) ? getCustomerSimp().getServNumber() : "", 
		    "reissueCard", "0", "0", "1", errormessage);
		
		return ERROR;
	}
	
	/**
	 * <显示读取的身份证信息>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String certShow()
	{
		return "certShow";
	}
	
	/**
	 * <校验用户的手机号码和身份证信息、校验用户的补卡次数>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	public void checkIdCardAndReissueNum()
	{
        try
        {
            // 校验用户的身份证信息和手机号码是否相符
            reissueCardBean.checkReissueIdCard(getIdCardPO().getIdCardNo(), getCustomerSimp().getServNumber(), 
            		curMenuId, getTerminalInfoPO());
            
            // 补卡规则校验
        	reissueCardBean.checkReissueNum(getCustomerSimp().getServNumber(), curMenuId, getTerminalInfoPO());
        	
            // 校验补卡次数
        	getCardBusiService().checkReissueNum(getCustomerSimp());
        	
            // 校验成功
        	writeXmlMsg("0");
        }
        catch (ReceptionException e)
        {
           logger.error("校验用户的手机号码和身份证信息、补卡次数失败", e);
           writeXmlMsg("1~~"+e.getMessage());
        }
	}
	
	/**
	 * <空白卡资源相关校验>
	 * <检验空白卡是否可用，如可用预占该空白卡资源>
	 * @see [类、类#方法、类#成员]
	 */
	public void authBlankCard()
	{
		try 
		{
            // 终端机信息
            TerminalInfoPO termInfo = getTerminalInfoPO();
            
            // 取得用户信息
            NserCustomerSimp customerSimp = getCustomerSimp();
            
            // 空白卡资源校验
            cardInstallBean.chkBlankNo(termInfo, getCurMenuId(), getCardBusiLogPO().getBlankCard(),
                customerSimp.getProductID(), "ChangeEnum");

            // 校验是否为预制空卡
            cardInstallBean.chkPreSetBlankCard(termInfo, getCurMenuId(), 
                cardBusiLogPO.getBlankCard(), getCustomerSimp().getServNumber(), "ChangeEnum");
            
            // 空白卡资源预占
            SimInfoPO simInfo = cardInstallBean.getEncryptedData(termInfo, getCurMenuId(),
                cardBusiLogPO.getBlankCard(), getCustomerSimp().getServNumber(), "ChangeEnum");

            // 空白卡资源校验成功
			writeXmlMsg("0~~" + simInfo.getWriteCardData());
		} 
        catch (ReceptionException e)
        {
           logger.error("空白卡资源校验失败：", e);
           writeXmlMsg("1~~"+e.getMessage());
        }
	}
	
	/**
	 * <补卡算费>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String feeConfirm()
	{
		String forward = "feeConfirm";
		
		try 
		{
			//补卡算费
			Map<String,Object>  map = reissueCardBean.countReissueFee(getCustomerSimp().getServNumber(), 
				simInfoPO.getOldiccid(), cardBusiLogPO.getBlankCard(), curMenuId, getTerminalInfoPO());
			
			//费用明细
			feeList = (List<FeeConfirmPO>)map.get("feeList");
			
			//总费用 元
			recFee = (String)map.get("recFee");
			
			//添加补卡业务日志
            addCardBusiLog();
			
			//补卡费用为0，不需要交费，直接写卡
            if (isZero(recFee))
			{
				//补卡费用
				recFee = "0";
				
				//免交费时默认支付类型为4：现金支付
				payType = Constants.PAYBYMONEY;
				
				//直接记录交费日志
				addCardChargeLog(Constants.PAYBYMONEY, "10", "补卡费用为0，无需交费");
				
				//需要写卡，需屏蔽超时返回主页功能
				this.getRequest().setAttribute("sdCashFlag", "1");
			}
		} 
		catch (ReceptionException e) 
		{
            //错误信息
            setErrormessage(e.getMessage());
            
            // 记录日志:手机号码、业务类型、业务流水号、业务费用、状态（0：成功，1:）、描述
            this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", "0", "0", "1", e.getMessage());
            
			forward = ERROR;
		}
		
		return forward;
	}
	
	/**
	 * <获取sim卡加密数据并展示付款方式>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String selectPayType()
	{
		String forward = "selectType";
		
	    // 获取可用的充值方式
	    usableTypes = getPayType(getTerminalInfoPO().getTermgrpid());
	    
	    // findbugs修改 2015-09-02 zKF66389
	    //logger.info("当前，话费充值的可选方式有" + (usableTypes == null ? 0 : usableTypes.size()) + "种");
	    // findbugs修改 2015-09-02 zKF66389
	    
	    //若查不到可用的充值方式，则报错
	    // findbugs修改 2015-09-02 zKF66389
	    //if (usableTypes == null || usableTypes.size() == 0)
	    if (usableTypes.size() == 0)
	    // findbugs修改 2015-09-02 zKF66389
	    {
	        // 没有可用的充值方式
	        setErrormessage("对不起，暂时没有可用的充值方式，请返回执行其他操作。");
	        
	        // 记录日志
	        this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", "0", "0", "1", "暂时没有可用的充值方式");
	        
	        forward = ERROR;
	    }
		
		return forward;
	}
	
	/**
	 * <现金交费>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String cashPay()
	{
		//屏蔽超时返回首页功能
		this.getRequest().setAttribute("sdCashFlag", "1");
		
		// 记录交费日志
		addCardChargeLog(Constants.PAYBYMONEY, "00", "现金交费之前增加交费日志");
		
		return "cashPay";
	}
	
	/**
	 * <银联卡交费读卡页面>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String unionCardPay()
	{
	    // 屏蔽超时返回首页的功能
	    this.getRequest().setAttribute("sdCashFlag", "1");
	    
	    // 记录交费日志
	    addCardChargeLog(Constants.PAYBYUNIONCARD, "00", "银联扣款之前增加交费日志");
	    
		return "readCard";
	}
	
	/** 
	 * <处理现金/银联卡交费异常>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String goChargeError()
	{
        try
        {
            // 记录sh_rec_log日志
            this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", "0", "0", "1", errormessage);
            
            // 银联卡返回的金额单位是分，转换为元
            if (Constants.PAYBYUNIONCARD.equals(payType))
            {
                tMoney = CommonUtil.fenToYuan(tMoney);
            }
            
            // 更新交费日志
            updateCardChargeLog();
        }
        
        // 捕获异常，避免吞卡等情况
        catch (Exception e)
        {
            logger.error("记录扣款错误日志异常：", e);
            errormessage = errormessage + e.getMessage();
        }
        
        return ERROR;
	}
	
	/**
	 * <补卡提交>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String reissueCommit()
	{
	    logger.warn("提交交费请求，号码：" + getCustomerSimp().getServNumber() + "；金额：" + tMoney + "；流水：" + cardChargeLogVO.getTerminalSeq());
	    
		String forward = SUCCESS;
		
		// 银联卡返回的金额单位是分，转换为元
        if (Constants.PAYBYUNIONCARD.equals(payType))
        {
            tMoney = CommonUtil.fenToYuan(tMoney);
        }
        
        // 将交费金额单位转换为分
        String commitMoney = CommonUtil.yuanToFen(tMoney);
        
        // 更新交费日志
        this.updateCardChargeLog("01", "交费成功，未办理补卡业务");
		
		try
		{
		    // add begin zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
		    // 业务办理前记录业务费用信息
            cardInstallBean.writeNetFeeInfo(getTerminalInfoPO(), getCustomerSimp(), getCurMenuId(), getCustomerSimp().getServNumber(), 
                this.getChargeType(Constants.PAYBYMONEY.equals(payType) ? "Cash" : "Card"), cardChargeLogVO.getTerminalSeq(), 
                commitMoney, Constants.ACCEPTTYPE_REISSUECARD);
		    // add end zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
		    
		    // 写卡成功接口
            simInfoPO.setWriteResult("1");
            simInfoPO.setErrCode("0");
            simInfoPO.setErrMsg("写卡成功");
            
            // 写卡成功
            cardInstallBean.updateWriteCardResult(getTerminalInfoPO(), curMenuId, cardBusiLogPO.getBlankCard(), simInfoPO);
		    
			// modify begin zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送
			//补卡提交
            String recOid = reissueCardBean.reissueCommit(getCustomerSimp().getServNumber(),
            		curMenuId, commitMoney, payType, cardBusiLogPO.getBlankCard(), simInfoPO, getTerminalInfoPO(),
                this.getChargeType(Constants.PAYBYMONEY.equals(payType) ? "Cash" : "Card"), cardChargeLogVO.getTerminalSeq());
			// modify end zKF69263 2015-5-8 OR_SD_201503_333_SD_自助终端活动受理预存赠送

			//受理流水号，打印凭条时用到
            cardBusiLogPO.setFormnum(recOid);
            
            //开户状态 默认2：初始状态 0：开户成功 1：开户失败
            cardBusiLogPO.setInstallStatus("0");
			
			// 更新交费日志
			this.updateCardChargeLog("11", "交费成功，补卡业务办理成功");
			
            // 记录日志
            this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", recOid, commitMoney, "0", "补卡成功");	
    	
		}
		catch(ReceptionException e)
		{
            logger.error("补卡提交失败：", e);
            
            // 开户状态 默认2：初始状态 0：开户成功 1：开户失败
            cardBusiLogPO.setInstallStatus("1");
            
            // 更新交费日志
            this.updateCardChargeLog("10", "交费成功，补卡业务办理失败：" + e.getMessage());
			
			//错误信息
			setErrormessage("交费成功，补卡业务办理失败：" + e.getMessage());
			
            // 记录日志
            this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", "0", commitMoney, "1", e.getMessage());
            
            try
            {
                // 写卡失败接口
                simInfoPO.setWriteResult("-1");
                simInfoPO.setErrCode("-1");
                simInfoPO.setErrMsg(e.getMessage());
                
                // 写卡失败
                getCardInstallBean().updateWriteCardResult(getTerminalInfoPO(), curMenuId, cardBusiLogPO.getBlankCard(), simInfoPO);
            }
            catch(ReceptionException ex)
            {
                logger.error("写卡失败接口失败：", ex);
            }
            
            forward = ERROR;
		}
		
		return forward;
	}
	
	/**
     * <增加补卡受理日志>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    private void addCardBusiLog()
    {
        // 日志id
        cardBusiLogPO.setOid(this.getCardBusiService().getInstallId());
        
        // 终端号
        cardBusiLogPO.setTermId(getTerminalInfoPO().getTermid());
        
        // 办理类型reissueCard 补卡
        cardBusiLogPO.setRectype("reissueCard");
        
        // 证件号码
        cardBusiLogPO.setCertId(idCardPO.getIdCardNo());
        
        // 用户名称
        cardBusiLogPO.setCustName(idCardPO.getIdCardName());
        
        // iccid
        cardBusiLogPO.setIccid(simInfoPO.getIccid());
        
        // imsi
        cardBusiLogPO.setImsi(simInfoPO.getImsi());
        
        // 性别
        cardBusiLogPO.setSex(idCardPO.getIdCardSex());
        
        // 住址
        cardBusiLogPO.setLinkAddr(idCardPO.getIdCardAddr());
        
        // 手机号码
        cardBusiLogPO.setServnumber(getCustomerSimp().getServNumber());
        
        // 手机号码subsid
        cardBusiLogPO.setSubsId(getCustomerSimp().getSubsID());
        
        // 地区
        cardBusiLogPO.setRegion(getTerminalInfoPO().getRegion());
        
        // 补卡费用
        cardBusiLogPO.setRecFee(CommonUtil.yuanToFen(recFee));
        
        // 默认2：初始状态 0：写卡成功 1：写卡失败
        cardBusiLogPO.setWriteCardStatus("2");
        
        // 默认2：初始状态 0：交费成功 1：交费失败
        cardBusiLogPO.setPayStatus("2");
        
        // 开户状态 默认2：初始状态 0：开户成功 1：开户失败
        cardBusiLogPO.setInstallStatus("2");
        
        // 默认2：初始状态 0：退款成功 1：退款失败
        cardBusiLogPO.setRefundment("2");
        
        // 备注
        cardBusiLogPO.setNotes("补卡费用已确认，开始交费");
        
        // 新增开户日志
        this.getCardBusiService().addLogInstall(cardBusiLogPO);
    }
    
    /** 
     * <新增交费日志>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    private void addCardChargeLog(String payType, String status, String desc)
    {
        // 取得终端机信息
        TerminalInfoPO termInfo = getTerminalInfoPO();
        
        // 取得用户信息
        NserCustomerSimp customerSimp = getCustomerSimp();
        
        String logOID = feeChargeService.getChargeLogOID();
        
        //交费日志id，与补卡日志chargeid关联
        cardChargeLogVO.setChargeLogOID(logOID);
        
        //终端地区
        cardChargeLogVO.setRegion(termInfo.getRegion());
        
        //终端id
        cardChargeLogVO.setTermID(termInfo.getTermid());
        
        //终端机操作员id
        cardChargeLogVO.setOperID(termInfo.getOperid());
        
        //手机号码
        cardChargeLogVO.setServnumber(customerSimp.getServNumber());
        
        //交费方式，费用为0时，设为现金交费4，方便对账
        //1:银联卡；4:现金
        cardChargeLogVO.setPayType(payType);
        
        //交费金额
        cardChargeLogVO.setFee(CommonUtil.yuanToFen(recFee));
        
        //交易时间
        cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
        
        //交费状态 11：交费成功；10：投币成功或者银联扣款成功，但是交费失败；00：除10外其它交费失败的情况
        cardChargeLogVO.setStatus(status);
        
        //描述
        cardChargeLogVO.setDescription(desc);
        
        //号码归属地
        cardChargeLogVO.setServRegion(customerSimp.getRegionID());
        
        //组织结构id
        cardChargeLogVO.setOrgID(termInfo.getOrgid());
        
        //业务类型
        cardChargeLogVO.setRecType("reissueCard");
        
        //银行号
        cardChargeLogVO.setBankno(getChargeType(Constants.PAYBYMONEY.equals(payType) ? "Cash" : "Card")
            + termInfo.getBankno());
        
        // modify begin zKF69263 2015-5-11 OR_SD_201503_333_SD_自助终端活动受理预存赠送
        //业务类型：补卡：ZZBK
        cardChargeLogVO.setAcceptType(Constants.ACCEPTTYPE_REISSUECARD);
		// modify end zKF69263 2015-5-11 OR_SD_201503_333_SD_自助终端活动受理预存赠送
        
        // add begin hWX5316476 2015-6-19 补卡算费为0时缴费前记录接口报错
        // 补卡费用为0，没有硬件终端流水，设置生成  终端ID+时间戳
        if (isZero(recFee) && Constants.PAYBYMONEY.equals(payType))
        {
            String terminalSeq = termInfo.getTermid() + DateUtil._getCurrentTime();
            
            // 设置终端流水
            cardChargeLogVO.setTerminalSeq(terminalSeq);
        }
        // add end hWX5316476 2015-6-19 补卡算费为0时缴费前记录接口报错

        // 插入交费日志
        feeChargeService.addChargeLog(cardChargeLogVO);
        
        // 更新补卡受理日志
        CardBusiLogPO cardBusiLog = new CardBusiLogPO();
        
        //日志id
        cardBusiLog.setOid(cardBusiLogPO.getOid());
        
        //关联交费日志
        cardBusiLog.setChargeId(logOID);
        
        //交费类型
        cardBusiLog.setChargeType(payType);
        
        //交费成功，更新开户交费状态
        if ("10".equals(status))
        {
            // 实收费用
            cardBusiLog.setToFee("0");
            
            // 默认2：初始状态 0：交费成功 1：交费失败 
            cardBusiLog.setPayStatus("0");
        }
        
        // 更新开户日志
        this.getCardBusiService().updateInstallLog(cardBusiLog);
    }
    
    /** 
     * <更新交费日志>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    private void updateCardChargeLog()
    {
        updateCardChargeLog(null, null);
    }
    
    /** 
     * <更新交费日志>
     * <功能详细描述>
     * @param status 交费状态
     * @param desc 交费描述
     * @see [类、类#方法、类#成员]
     */
    private void updateCardChargeLog(String status, String desc)
    {
        //----------------更新交费日志-----------------
        // "补卡不免费"或"交费状态不为空"时更新交费日志
        if (!isZero(recFee) || (null != status))
        {
            // 取得终端机信息
            TerminalInfoPO termInfo = getTerminalInfoPO();
            
            // 终端地区
            cardChargeLogVO.setRegion(termInfo.getRegion());
            
            // 组织结构id
            cardChargeLogVO.setOrgID(termInfo.getOrgid());
            
            //更新为用户投币金额/银联扣款金额
            cardChargeLogVO.setFee(CommonUtil.yuanToFen(tMoney));
            
            //现金交费流水号
            if (Constants.PAYBYMONEY.equals(payType) 
                && StringUtils.isNotBlank(cardChargeLogVO.getTerminalSeq()))
            {
                String terminalSeq = getTerminalInfoPO().getTermid() + cardChargeLogVO.getTerminalSeq();
                if (terminalSeq.length() > 20)
                {
                    terminalSeq = terminalSeq.substring(terminalSeq.length() - 20);
                }
                
                cardChargeLogVO.setTerminalSeq(terminalSeq);
            }
            
            // 受理类型
            // cardChargeLogVO.setAcceptType(acceptType);
            
            // 投币及银联卡交费状态
            cardChargeLogVO.setStatus((null != status) ? status : (isZero(tMoney) ? "00" : "10"));
            
            // 描述信息
            cardChargeLogVO.setDescription((null != desc) ? desc : errormessage);
            
            // 交易时间
            cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
            
            // 更新交费记录
            this.getCardBusiService().updateCardChargeLog(cardChargeLogVO);
        }
        
        //--------更新补卡受理日志--------
        // 实收费用
        cardBusiLogPO.setToFee(CommonUtil.yuanToFen(tMoney));
        
        // 状态为10：投币成功或者银联扣款成功，但补卡失败，更新开户日志PayStatus=0付款成功
        if ("10".equals(cardChargeLogVO.getStatus()))
        {
            cardBusiLogPO.setPayStatus("0");
        }
        
        // 状态为11：交费补卡成功，更新开户日志InstallStatus=0业务办理成功
        if ("11".equals(cardChargeLogVO.getStatus()))
        {
            cardBusiLogPO.setInstallStatus("0");
        }
        
        // 错误信息
        cardBusiLogPO.setNotes((null != desc) ? desc : errormessage);
        
        // iccid
        cardBusiLogPO.setIccid(simInfoPO.getIccid());
        
        // imsi
        cardBusiLogPO.setImsi(simInfoPO.getImsi());
        
        // 更新开户日志
        this.getCardBusiService().updateInstallLog(cardBusiLogPO);
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

    /**
     * @return 返回 reissueCardBean
     */
    public ReissueCardBean getReissueCardBean()
    {
        return reissueCardBean;
    }

    /**
     * @param 对reissueCardBean进行赋值
     */
    public void setReissueCardBean(ReissueCardBean reissueCardBean)
    {
        this.reissueCardBean = reissueCardBean;
    }

    /**
     * @return 返回 feeChargeService
     */
    public FeeChargeService getFeeChargeService()
    {
        return feeChargeService;
    }

    /**
     * @param 对feeChargeService进行赋值
     */
    public void setFeeChargeService(FeeChargeService feeChargeService)
    {
        this.feeChargeService = feeChargeService;
    }

    /**
     * @return 返回 cardChargeLogVO
     */
    public CardChargeLogVO getCardChargeLogVO()
    {
        return cardChargeLogVO;
    }

    /**
     * @param 对cardChargeLogVO进行赋值
     */
    public void setCardChargeLogVO(CardChargeLogVO cardChargeLogVO)
    {
        this.cardChargeLogVO = cardChargeLogVO;
    }

    /**
     * @return 返回 servnumber
     */
    public String getServnumber()
    {
        return servnumber;
    }

    /**
     * @param 对servnumber进行赋值
     */
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }

    /**
     * @return 返回 password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param 对password进行赋值
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @return 返回 usableTypes
     */
    public List getUsableTypes()
    {
        return usableTypes;
    }

    /**
     * @param 对usableTypes进行赋值
     */
    public void setUsableTypes(List usableTypes)
    {
        this.usableTypes = usableTypes;
    }

    /**
     * @return 返回 tMoney
     */
    public String getTMoney() {
        
        return tMoney;
    }

    /**
     * @param 对tMoney进行赋值
     */
    public void setTMoney(String money) {
        
        this.tMoney = money;
    }

    /**
     * @return 返回 payType
     */
    public String getPayType()
    {
        return payType;
    }

    /**
     * @param 对payType进行赋值
     */
    public void setPayType(String payType)
    {
        this.payType = payType;
    }

    /**
     * @return 返回 needReturnCard
     */
    public String getNeedReturnCard()
    {
        return needReturnCard;
    }

    /**
     * @param 对needReturnCard进行赋值
     */
    public void setNeedReturnCard(String needReturnCard)
    {
        this.needReturnCard = needReturnCard;
    }

    /**
     * @return 返回 printcontext
     */
    public String getPrintcontext()
    {
        return printcontext;
    }

    /**
     * @param 对printcontext进行赋值
     */
    public void setPrintcontext(String printcontext)
    {
        this.printcontext = printcontext;
    }
}
