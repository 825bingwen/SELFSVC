
package com.customize.hub.selfsvc.cardbusi.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.ReissueCardBean;
import com.customize.hub.selfsvc.cardbusi.model.CardBusiLogPO;
import com.customize.hub.selfsvc.cardbusi.model.FeeConfirmPO;
import com.customize.hub.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.customize.hub.selfsvc.charge.service.FeeChargeHubService;
import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.common.cache.RefreshCacheHub;
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
 * <补卡>
 * <功能详细描述>
 * 
 * @author  sWX219697
 * @version  [版本号, Oct 23, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡补卡)
 */
public class ReissueCardAction extends CardBusiBaseAction
{
	/**
     * 序列化
     */
    private static final long serialVersionUID = 3129620728444184038L;
	
	private static Log logger = LogFactory.getLog(ReissueCardAction.class);
	
	/**
	 * 补卡接口调用
	 */
	private transient ReissueCardBean reissueCardBean;
	
	/**
	 * 用户登录
	 */
	private transient UserLoginBean userLoginBean;
	
    /**---------日志处理--------------
     * 缴费日志
     */
    private transient FeeChargeHubService feeChargeService;

	/**
	 * 用户信息类
	 */
	private transient NserCustomerSimp customer;
	
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
	
    /**-----------------------缴费相关-----------------------------
     * 可用交费方式
     */
    private List usableTypes;
    
    /**
     * 用户投币金额/银联扣款金额
     */
    private String tMoney;
    
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
    private String printcontext;
    
    /**
     * 更新日志方式，add/update
     */
    private String errorType;
	
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
	 * <校验用户身份并跳转至读取身份证页面>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String readCert()
	{
		String forward = "readCert";
		
		//校验用户密码
		Map authResult = userLoginBean.getUserInfoWithPwd(servnumber, password, getTerminalInfoPO());
		customer = (NserCustomerSimp)authResult.get("customerSimp");
		
		//校验失败
		if(null == authResult.get("customerSimp"))
		{
			//错误信息转换
            String resultMsg = getConvertMsg((String) authResult.get("returnMsp"), 
                    "zz_04_01_000001", String.valueOf(authResult.get("errcode")), 
                    null);
            
            if (StringUtils.isBlank(resultMsg))
            {
            	resultMsg = "登录失败";
            }
            
            // 校验失败
            setErrormessage(resultMsg);
            
            // 记录日志:手机号码、业务类型、业务流水号、业务费用、状态（0：成功，1:）、描述
            this.createRecLog(servnumber, "reissueCard", "0", "0", "1", (String) authResult.get("returnMsp"));
			forward = ERROR;
		}
		else
		{
			//不允许跨地区办理业务
			if (!getTerminalInfoPO().getRegion().equals(customer.getRegionID()))
			{
	            setErrormessage("暂不允许跨地区办理补卡业务");
	            
	            // 记录日志:手机号码、业务类型、业务流水号、业务费用、状态（0：成功，1:）、描述
	            this.createRecLog(servnumber, "reissueCard", "0", "0", "1", "暂不允许跨地区办理补卡业务");
				forward = ERROR;
			}
			
			//将用户信息保存在session
			else
			{
				//首先清除session
				removeUserSession();
				
                //将新的用户信息存放在session中
                this.getRequest().getSession().setAttribute(Constants.USER_INFO, customer);
			}
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
		this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", "0", "0", "1", errormessage);
		
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
            //校验用户的身份证信息和手机号码是否相符
            reissueCardBean.checkReissueIdCard(getIdCardPO().getIdCardNo(), getCustomerSimp().getServNumber(), 
            		curMenuId, getTerminalInfoPO());
            
            //查询用户信息
            String subscriber = reissueCardBean.getSubscriberByTel(getCustomerSimp().getServNumber(), 
            		curMenuId, getTerminalInfoPO());
            
            //补卡规则校验
        	reissueCardBean.checkReissueNum(getCustomerSimp().getServNumber(), 
        			curMenuId, getTerminalInfoPO(), subscriber);
        	
        	//校验补卡次数
        	checkReissueNum();
        	
        	writeXmlMsg("0");
        }
        catch (ReceptionException e)
        {
           logger.error("校验用户的手机号码和身份证信息、补卡次数失败", e);
           writeXmlMsg("1~~"+e.getMessage());
        }
	}
	
	/**
	 * <校验用户补卡次数>
	 * <若数据库中没有配置补卡次数，或配置的补卡次数非数字，则不进行次数校验>
	 * @see [类、类#方法、类#成员]
	 */
	private void checkReissueNum()
	{
		//查询配置的每月允许最多补卡次数
		if (StringUtils.isBlank(CommonUtil.getParamValue("sh_reissueCard_num")))
		{
			return;
		}
		
		int limitNum;
		
		try 
		{
			limitNum = Integer.parseInt(CommonUtil.getParamValue("sh_reissueCard_num"));
		} 
		catch (NumberFormatException e) 
		{
			logger.error("用户补卡次数查询错误：", e);
			return;
		}
		
		CardBusiLogPO cardBusiLog = new CardBusiLogPO();
		
		//当前月的第一天
		cardBusiLog.setCreateDate(getFirstDay("yyyyMMdd"));
		
		//当前月的最后一天
		cardBusiLog.setStatusDate(getLastDay("yyyyMMdd"));
		
		//用户地区
		cardBusiLog.setRegion(getCustomerSimp().getRegionID());
		
		//手机号码
		cardBusiLog.setServnumber(getCustomerSimp().getServNumber());
		
		//本月已经补卡的次数
		int usedNum = getCardBusiService().getReissueCardNum(cardBusiLog);
		
		//用户本月补卡次数用完
		if (usedNum >= limitNum)
		{
			throw new ReceptionException("用户已超出本月补卡次数限制，暂时无法补卡");
		}
		
	}
	
	/**
	 * <获取当前月的第一天>
	 * <功能详细描述>
	 * @param dateFormat
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String getFirstDay(String dateFormat)
	{
		Calendar cale = null;
	    SimpleDateFormat format = new SimpleDateFormat(dateFormat); 
	    // 获取前月的第一天 
	    cale = Calendar.getInstance(); 
	    cale.add(Calendar.MONTH, 0); 
	    cale.set(Calendar.DAY_OF_MONTH, 1); 
	    
	    return format.format(cale.getTime()); 
	}
	
	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dateFormat
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String getLastDay(String dateFormat)
	{
		Calendar cale = null;
		SimpleDateFormat format = new SimpleDateFormat(dateFormat); 
		
		// 获取前月的最后一天 
	    cale = Calendar.getInstance(); 
	    cale.add(Calendar.MONTH, 1); 
	    cale.set(Calendar.DAY_OF_MONTH, 0); 
	    return format.format(cale.getTime()); 
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
			//终端机信息
			TerminalInfoPO termInfo = getTerminalInfoPO();
			
			//空白卡资源校验
			cardInstallBean.chkBlankNo(termInfo, getCurMenuId(), 
					getCardBusiLogPO().getBlankCard());
			
			//校验是否为预制空卡
			cardInstallBean.chkPreSetBlankCard(termInfo, curMenuId, cardBusiLogPO.getBlankCard(), 
					getCustomerSimp().getServNumber());
			
			//空白卡资源预占
			SimInfoPO simInfo = cardInstallBean.blankCardTmpPick(termInfo, getCurMenuId(), 
					cardBusiLogPO.getBlankCard(), getCustomerSimp().getServNumber());
			
			writeXmlMsg("0~~"+simInfo);
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
					simInfoPO.getIccid(), cardBusiLogPO.getBlankCard(), curMenuId, getTerminalInfoPO());
			
			//费用明细
			feeList = (List<FeeConfirmPO>)map.get("feeList");
			
			//总费用 元
			recFee = (String)map.get("recFee");
			
			//补卡费用为0，不需要交费，直接写卡
			if(isZero(recFee))
			{
				//补卡费用
				recFee = "0";
				
				//添加补卡业务日志
				addCardBusiLog();
				
				//直接记录交费日志
				addFreeLog();
				
				//需要写卡，需屏蔽超时返回主页功能
				this.getRequest().setAttribute("telProdFlag", "1");
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
	 * <增加补卡受理日志>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	private void addCardBusiLog()
	{
		//日志id
		cardBusiLogPO.setOid(this.getCardBusiService().getInstallId());
		
        // 终端号
        cardBusiLogPO.setTermId(getTerminalInfoPO().getTermid());
        
        // 办理类型reissueCard 补卡
        cardBusiLogPO.setRectype("reissueCard");
		
		//证件号码
		cardBusiLogPO.setCertId(idCardPO.getIdCardNo());
		
		//用户名称
		cardBusiLogPO.setCustName(idCardPO.getIdCardName());
		
		//iccid
		cardBusiLogPO.setIccid(simInfoPO.getIccid());
		
		//imsi
		cardBusiLogPO.setImsi(simInfoPO.getImsi());
		
		//smsp
		cardBusiLogPO.setSmsp(simInfoPO.getSmsp());
		
        //性别
        cardBusiLogPO.setSex(idCardPO.getIdCardSex());
		
		//住址
		cardBusiLogPO.setLinkAddr(idCardPO.getIdCardAddr());
		
		//手机号码
		cardBusiLogPO.setServnumber(getCustomerSimp().getServNumber());
		
		//手机号码subsid
		cardBusiLogPO.setSubsId(getCustomerSimp().getSubsID());
		
		//地区
		cardBusiLogPO.setRegion(getTerminalInfoPO().getRegion());
		
		//补卡费用
		cardBusiLogPO.setRecFee(CommonUtil.yuanToFen(recFee));
		
		// 默认2：初始状态 0：写卡成功 1：写卡失败 
		cardBusiLogPO.setWriteCardStatus("2");
      
		// 默认2：初始状态 0：缴费成功 1：缴费失败 
		cardBusiLogPO.setPayStatus("2");
		
		// 开户状态 默认2：初始状态 0：开户成功 1：开户失败
		cardBusiLogPO.setInstallStatus("2");
		
		// 默认2：初始状态 0：退款成功 1：退款失败
		cardBusiLogPO.setRefundment("2");
		
		// 备注
		cardBusiLogPO.setNotes("补卡费用已确认，开始缴费");
		this.getCardBusiService().addLogInstall(cardBusiLogPO);
	}
	
	/**
	 * <补卡费用为0时记录日志>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private void addFreeLog()
	{
		String logOID = feeChargeService.getChargeLogOID();
		
		//交费日志id，与补卡日志chargeid关联
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
        cardChargeLogVO.setFee(CommonUtil.yuanToFen(recFee));
        
        //交费状态 初始状态:11
        cardChargeLogVO.setStatus("10");
        
        //交费方式，费用为0时，设为现金缴费4，方便对账
        cardChargeLogVO.setPayType(Constants.PAYBYMONEY);
        
        //交易时间
        cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
        
        //业务类型
        cardChargeLogVO.setRecType("reissueCard");

        //描述
        cardChargeLogVO.setDescription("补卡费用为0，无需交费");
		
		// 插入缴费日志
		feeChargeService.addChargeLog(cardChargeLogVO);
		
		// 更新补卡受理日志
		CardBusiLogPO cardBusiLog = new CardBusiLogPO();
		
		//日志id
		cardBusiLog.setOid(cardBusiLogPO.getOid());
		
		//关联交费日志
		cardBusiLog.setChargeId(logOID);
		
		//交费类型
		cardBusiLog.setChargeType(Constants.PAYBYMONEY);
		
		// 默认2：初始状态 0：缴费成功 1：缴费失败 
		cardBusiLog.setPayStatus("0");
		
		//实际缴费金额
		cardBusiLog.setToFee(CommonUtil.yuanToFen(recFee));
		
		this.getCardBusiService().updateInstallLog(cardBusiLog);
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
		
		//增加受理日志
		addCardBusiLog();
		
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
	 * <现金缴费>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String cashPay()
	{
		//屏蔽超时返回首页功能
		this.getRequest().setAttribute("telProdFlag", "1");
		
		return "cashPay";
	}
	
	/**
	 * <银联卡缴费读卡页面>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String unionCardPay()
	{
		return "readCard";
	}
	
	/**
	 * <输入银联卡密码>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String inputCardPwd()
	{
		return "inputCardPwd";
	}
	
	/**
	 * <银联交费确认>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String makeSure()
	{
		//屏蔽超时返回首页功能
		this.getRequest().setAttribute("telProdFlag", "1");
		
		return "makeSure";
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
			
			//交费日志id，与补卡日志chargeid关联
			cardChargeLogVO.setChargeLogOID(logOID);
			
			//终端地区
            cardChargeLogVO.setRegion(getTerminalInfoPO().getRegion());
            
            //终端id
            cardChargeLogVO.setTermID(getTerminalInfoPO().getTermid());
            
            //终端机操作员id
            cardChargeLogVO.setOperID(getTerminalInfoPO().getOperid());
            
            //组织机构编码
            cardChargeLogVO.setOrgID(getTerminalInfoPO().getOrgid());
            
            //手机号码
            cardChargeLogVO.setServnumber(getCustomerSimp().getServNumber());
            
            //地区
            cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());
            
            //支付方式
            cardChargeLogVO.setPayType(Constants.PAYBYUNIONCARD);
            
            //交费金额
            cardChargeLogVO.setFee(CommonUtil.yuanToFen(recFee));
            
            //加密的银联卡卡号
            cardChargeLogVO.setCardnumber(EncryptDecryptUtil.encryptAesPwd(cardChargeLogVO.getCardnumber()));
            
            //交费状态 初始状态:00
            cardChargeLogVO.setStatus("00");
            
            //交易时间
            cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
            
            //业务类型
            cardChargeLogVO.setRecType("reissueCard");

            //描述
            cardChargeLogVO.setDescription("银联扣款之前增加交费日志");
			
			// 插入缴费日志
			feeChargeService.addChargeLog(cardChargeLogVO);
			
			// 更新补卡受理日志
			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
			
			//日志id
			cardBusiLog.setOid(cardBusiLogPO.getOid());
			
			//关联交费日志
			cardBusiLog.setChargeId(logOID);
			
			//交费类型
			cardBusiLog.setChargeType(Constants.PAYBYUNIONCARD);
			
			this.getCardBusiService().updateInstallLog(cardBusiLog);
			
			writeXmlMsg("0~~" + logOID);
		} 
		catch (Exception e) 
		{
			logger.error("扣款钱添加交费日志和更新补卡日志异常：", e);
			writeXmlMsg("1");
		}
		
	}
	
	/**
	 * <银联扣款成功之后更新交费日志>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	public void updateUnionCardLog()
	{
        try 
        {
        	//银联返回的交易类型
			String busitype = cardChargeLogVO.getBusiType();
			
			cardChargeLogVO.setBusiType(java.net.URLDecoder.decode(busitype, "UTF-8"));
			
			//银联扣款费用
			String unionpayfee = cardChargeLogVO.getUnionpayfee();
			
			// modify begin wWX217192 2015-5-25 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
            unionpayfee = CommonUtil.formatNumberStr(unionpayfee);
            // modify end wWX217192 2015-5-25 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
			
			//银联扣款费用 单位 分
			cardChargeLogVO.setUnionpayfee(unionpayfee);
			
			//01 付款成功
			cardChargeLogVO.setStatus("01");
			
			//描述信息
			cardChargeLogVO.setDescription("银联扣款成功");
			
			//日期
			cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
			
			//银联返回码
			String initPosResCode = cardChargeLogVO.getPosResCode();
			cardChargeLogVO.setPosResCode(null == initPosResCode ? "" : initPosResCode);

			//更新银联交费日志
			feeChargeService.updateCardChargeLog(cardChargeLogVO);
			
			// ---------------更新补卡受理日志----------------
			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
			
			//日志id
			cardBusiLog.setOid(cardBusiLogPO.getOid());
			
	        //实际缴费金额
	        cardBusiLog.setToFee(unionpayfee);
			
	        // 默认2：初始状态 0：缴费成功 1：缴费失败 
			cardBusiLog.setPayStatus("0");
			
			//描述信息
			cardBusiLog.setNotes("缴费成功");
	        
			//更新补卡受理日志
			this.getCardBusiService().updateInstallLog(cardBusiLog);
			
			writeXmlMsg("0");
		} 
        catch (Exception e) 
        {
			logger.error("扣款之后更新日志失败", e);
			writeXmlMsg("1");
		}
		
	}
	
	/**
	 * <处理补卡费用为0时写卡失败的情况>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String goFreeWriteError()
	{
		// ---------------更新补卡受理日志----------------
		CardBusiLogPO cardBusiLog = new CardBusiLogPO();
		
		//日志id
		cardBusiLog.setOid(cardBusiLogPO.getOid());
		
        // 默认2：初始状态 0：缴费成功 1：缴费失败 
		cardBusiLog.setPayStatus("0");
		
		//写卡状态
		cardBusiLog.setWriteCardStatus(cardBusiLogPO.getWriteCardStatus());
		
		//描述信息
		cardBusiLog.setNotes("补卡费用为0，写卡失败");
        
		//更新补卡受理日志
		this.getCardBusiService().updateInstallLog(cardBusiLog);
		
        // 记录日志
        this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", "0", "0", "1", errormessage);
		
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
            this.createRecLog(getCustomerSimp().getServNumber(), payType, "0", "0", "1", errormessage);
            
            //终端机
            TerminalInfoPO termInfoPO = getTerminalInfoPO();
            
            //----------------更新交费日志-----------------
            //支付类型
            cardChargeLogVO.setPayType(payType);
            
            //现金缴费流水号
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

            //判断用户已投币金额
            if (isZero(tMoney))
            {
            	//未投币
            	cardChargeLogVO.setStatus("00");
            }
            else
            {
            	//已投币
            	cardChargeLogVO.setStatus("10");
            }
            
            //缴费日志id
            String chargeLogId = feeChargeService.getChargeLogOID();
            
            //缴费日志id
        	cardChargeLogVO.setChargeLogOID(chargeLogId);
        	
        	//终端地区
        	cardChargeLogVO.setRegion(termInfoPO.getRegion());
        	
        	//终端编号
        	cardChargeLogVO.setTermID(termInfoPO.getTermid());
        	
        	//终端操作员
        	cardChargeLogVO.setOperID(termInfoPO.getOperid());
        	
        	//组织机构编码
        	cardChargeLogVO.setOrgID(termInfoPO.getOrgid());
        	
        	//手机号码
        	cardChargeLogVO.setServnumber(getCustomerSimp().getServNumber());
        	
        	//手机号码归属地
        	cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());
            
        	//缴费金额 分
        	cardChargeLogVO.setFee(CommonUtil.yuanToFen(tMoney));
            
        	//交易时间
        	cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
        	
        	//描述信息
        	cardChargeLogVO.setDescription(errormessage);
        	
        	//业务类型
        	cardChargeLogVO.setRecType("reissueCard");
            
        	//增加交费日志
            feeChargeService.addChargeLog(cardChargeLogVO);
            
			//--------更新补卡受理日志--------
			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
			
			//日志id
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
        	//记录错误日志
            this.createRecLog(getCustomerSimp().getServNumber(), payType, "0", "0", "1", errormessage);
            
            //银联交费后写卡异常处理
            if("1".equals(cardBusiLogPO.getWriteCardStatus()))
            {
    			//--------更新补卡受理日志--------
    			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
    			
    			//日志id
    			cardBusiLog.setOid(cardBusiLogPO.getOid());
    			
    			//写卡状态
    			cardBusiLog.setWriteCardStatus(cardBusiLogPO.getWriteCardStatus());
    			
    			//错误信息
    			cardBusiLog.setNotes(errormessage);
    			
    			//更新日志
    			this.getCardBusiService().updateInstallLog(cardBusiLog);
    			
    			return ERROR;
    			
            }
            
            //新增银联交费和现金缴费日志
            if (StringUtils.isEmpty(errorType) || "add".equalsIgnoreCase(errorType))
            {
            	//交费日志id
            	String chargeLogId = feeChargeService.getChargeLogOID();
            	cardChargeLogVO.setChargeLogOID(chargeLogId);
            	
            	//交费类型
            	cardChargeLogVO.setPayType(payType);
            	
            	//交费流水
                cardChargeLogVO.setTerminalSeq("");

                //归属地
                cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());
                
                //判断扣款金额
                if (isZero(tMoney))
                {
                	//未交费
                	cardChargeLogVO.setStatus("00");
                }
                else
                {
                	//已交费
                	cardChargeLogVO.setStatus("10");
                }
                
            	//交费金额
            	cardChargeLogVO.setFee(tMoney);
            	
            	//终端地区
            	cardChargeLogVO.setRegion(getTerminalInfoPO().getRegion());
            	
            	//终端编号
            	cardChargeLogVO.setTermID(getTerminalInfoPO().getTermid());
            	
            	//终端操作员
            	cardChargeLogVO.setOperID(getTerminalInfoPO().getOperid());
            	
            	//组织机构编码
            	cardChargeLogVO.setOrgID(getTerminalInfoPO().getOrgid());
            	
            	//手机号码
            	cardChargeLogVO.setServnumber(getCustomerSimp().getServNumber());
            	
            	//用户归属地
            	cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());

            	//交易时间
            	cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
            	
            	//描述信息
            	cardChargeLogVO.setDescription(errormessage);
            	
            	//业务类型
            	cardChargeLogVO.setRecType("reissueCard");
                
            	//添加日志
                feeChargeService.addChargeLog(cardChargeLogVO);
                
    			//--------关联更新补卡受理日志--------
    			CardBusiLogPO cardBusiLog = new CardBusiLogPO();
    			
    			//日志id
    			cardBusiLog.setOid(cardBusiLogPO.getOid());
    			
    			//实际缴费金额
    			cardBusiLog.setToFee(tMoney);
    			
    			//交费状态
    			cardBusiLog.setPayStatus(cardBusiLogPO.getPayStatus());
    			
    			//关联交费日志
    			cardBusiLog.setChargeId(chargeLogId);
    			
    			//交费类型
    			cardBusiLog.setChargeType(payType);
    			
    			this.getCardBusiService().updateInstallLog(cardBusiLog);
            }
            
            // 更新银联扣款日志日志
            else
            {
            	//交费金额为0
                if (isZero(tMoney))
                {
                	cardChargeLogVO.setStatus("00");
                }
                else
                {
                	cardChargeLogVO.setStatus("10");
                }
                
                //错误信息
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
     * 防止用户重复缴费
     * 
     * @param termInfo
     * @return
     * @see [类、类#方法、类#成员]
     */
    private boolean checkRepeatCash()
    {
    	TerminalInfoPO termInfo = getTerminalInfoPO();
    	
        String seq = termInfo.getTermid().concat(cardChargeLogVO.getTerminalSeq());
        
        String tmpSeq = seq.concat(getCustomerSimp().getServNumber());
        
        // 如果有相同的串，则是重复缴费
        if (RefreshCacheHub.getInstance().cashFeeCacher.containsKey(tmpSeq))
        {
            String tmpErrorMsg = "重复缴费:手机号[".concat(getCustomerSimp().getServNumber())
                    .concat("],投币金额[")
                    .concat(tMoney)
                    .concat("]元,归属营业厅[")
                    .concat(termInfo.getOrgname())
                    .concat("],流水号[")
                    .concat(seq)
                    .concat("]");
            
            CashFeeErrorInfoVO cashFeeErrorInfo = new CashFeeErrorInfoVO(termInfo.getTermid(), termInfo.getRegion(),
                    termInfo.getOperid(), termInfo.getOrgid());
            
            cashFeeErrorInfo.setServnumber(getCustomerSimp().getServNumber());
            
            // 现金投币
            cashFeeErrorInfo.setPayType(Constants.PAYBYMONEY);
            
            cashFeeErrorInfo.setFee(CommonUtil.yuanToFen(tMoney));
            
            // 现金缴费流水,终端id+厂商生成流水
            cashFeeErrorInfo.setTerminalSeq(seq);
            
            cashFeeErrorInfo.setStatus("1");
            
            cashFeeErrorInfo.setRecDate(DateUtilHub.getCurrentDateTime());
            
            cashFeeErrorInfo.setDescription(tmpErrorMsg);
            
            // 记录重复缴费日志
            feeChargeService.insertFeeErrorLog(cashFeeErrorInfo);
            
            // 记录到SH_REC_LOG,交易流水号这里记录现金缴费流水号
            this.createRecLog(getCustomerSimp().getServNumber(), Constants.PAY_BYCASH, seq, CommonUtil.yuanToFen(tMoney), "1", tmpErrorMsg);
            
            return false;
        }
        else
        {
            RefreshCacheHub.getInstance().cashFeeCacher.put(tmpSeq, DateUtilHub.curOnlyTime());
            return true;
        }
        
    }
	
	/**
	 * <补卡提交>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String reissueCommit()
	{
		String forward = SUCCESS;
		
		//交费金额转换：若现金缴费，tMoney单位为元，需转换成分，银联交费，返回的tMoney单位为分，无需转换
		String commitMoney = Constants.PAYBYMONEY.equals(payType) ? CommonUtil.yuanToFen(tMoney) : tMoney;
		
		//缴费日志id，用于更新交费状态
		String chargeLogOID = Constants.PAYBYMONEY.equals(payType) ? 
				feeChargeService.getChargeLogOID() : cardChargeLogVO.getChargeLogOID();
		
		//现金缴费提交时，增加现金交费日志
		if (Constants.PAYBYMONEY.equals(payType))
		{
	        //防止现金重复缴费
	        if(!checkRepeatCash())
	        {
	            setErrormessage("交易流水号重复，重复缴费！");
	            
	            return ERROR;
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
	        
	        //现金缴费流水
	        cardChargeLogVO.setTerminalSeq(terminalSeq);
	        
	        // 组装数据
	        cardChargeLogVO.setChargeLogOID(chargeLogOID);
	        
	        //地区
	        cardChargeLogVO.setRegion(getTerminalInfoPO().getRegion());
	        
	        //终端编号
	        cardChargeLogVO.setTermID(getTerminalInfoPO().getTermid());
	        
	        //操作员
	        cardChargeLogVO.setOperID(getTerminalInfoPO().getOperid());
	        
	        //手机号码
	        cardChargeLogVO.setServnumber(getCustomerSimp().getServNumber());
	        
	        //缴费方式
	        cardChargeLogVO.setPayType(payType);
	        
	        //缴费金额
	        cardChargeLogVO.setFee(commitMoney);
	        
	        //交易时间
	        cardChargeLogVO.setRecdate(CommonUtil.getCurrentTime());
	        
	        //归属地
	        cardChargeLogVO.setServRegion(getCustomerSimp().getRegionID());
	        
	        //组织机构编码
	        cardChargeLogVO.setOrgID(getTerminalInfoPO().getOrgid());
	        
	        //缴费状态 成功缴费
	        cardChargeLogVO.setStatus("10");
	        
	        //描述信息
	        cardChargeLogVO.setDescription("补卡缴费成功");
	        
	        //业务类型
	        cardChargeLogVO.setRecType("reissueCard");
	        
	        // 添加缴费日志
	        feeChargeService.addChargeLog(cardChargeLogVO);
	        
	        //-------------------更新业务受理日志-----------------------
	        cardBusiLogPO.setChargeId(chargeLogOID);
	        
	        //缴费类型
	        cardBusiLogPO.setChargeType(payType);
	        
	        //实际缴费金额
	        cardBusiLogPO.setToFee(commitMoney);
			
	        // 默认2：初始状态 0：缴费成功 1：缴费失败 
			cardBusiLogPO.setPayStatus("0");
			
	    	// 默认2：初始状态 0：写卡成功 1：写卡失败 
			cardBusiLogPO.setWriteCardStatus("0");

			//描述信息
			cardBusiLogPO.setNotes("缴费成功");
	        
	    	this.getCardBusiService().updateInstallLog(cardBusiLogPO);
		}
		
		// 提交后更新业务受理日志
		CardBusiLogPO cardBusiLog = new CardBusiLogPO();
		
		//日志id
		cardBusiLog.setOid(cardBusiLogPO.getOid());
		
		//实际缴费金额 单位：分
		cardBusiLog.setToFee(commitMoney);
		
		// 默认2：初始状态 0：写卡成功 1：写卡失败 
		cardBusiLog.setWriteCardStatus("0");
		
		try
		{
			//写卡成功接口
			simInfoPO.setWriteResult("1");
			simInfoPO.setErrCode("0");
			simInfoPO.setErrMsg("写卡成功");
			
			cardInstallBean.updateWriteCardResult(getTerminalInfoPO(), curMenuId, cardBusiLogPO.getBlankCard(), simInfoPO);

			//补卡费用为0时，交费方式传现金缴费 4，方便对账
			if(isZero(commitMoney))
			{
				payType = Constants.PAYBYMONEY;
			}
			
			//补卡提交
			String recOid = reissueCardBean.reissueCommit(getCustomerSimp().getServNumber(), curMenuId, commitMoney, 
					payType, cardBusiLogPO.getBlankCard(), simInfoPO, getTerminalInfoPO());

			//用于页面显示，单位 元
			tMoney = CommonUtil.fenToYuan(commitMoney);
			
			//----------更新交费日志状态----------
			CardChargeLogVO cardChargeLog = new CardChargeLogVO();
			
			cardChargeLog.setChargeLogOID(chargeLogOID);
			
			cardChargeLog.setStatus("11");
			
			cardChargeLog.setDescription("交费成功，业务办理成功");
			
			this.getCardBusiService().updateCardChargeLog(cardChargeLog);
			//----------更新交费日志结束----------
			
			//受理流水号，打印凭条时用到
			cardBusiLogPO.setFormnum(recOid);

			// 开户状态 默认2：初始状态 0：开户成功 1：开户失败
			cardBusiLog.setInstallStatus("0");
			
			//业务流水号
			cardBusiLog.setFormnum(recOid);
			
			//描述信息
			cardBusiLog.setNotes("补卡成功");
			
            // 记录日志
            this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", recOid, commitMoney, "0", "补卡成功");	
    	
		}
		catch(ReceptionException e)
		{
			logger.error("补卡提交失败：", e);
			
			// 开户状态 默认2：初始状态 0：开户成功 1：开户失败
			cardBusiLog.setInstallStatus("1");
			
			//描述信息
			cardBusiLog.setNotes(e.getMessage());
			
			//错误信息
			setErrormessage(e.getMessage());
			
            // 记录日志
            this.createRecLog(getCustomerSimp().getServNumber(), "reissueCard", "0", commitMoney, "1", e.getMessage());
			
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
            
            forward = ERROR;
		}
		
		//补卡提交后更新日志
		this.getCardBusiService().updateInstallLog(cardBusiLog);
		
		return forward;
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
	
	public String getServnumber() 
	{
		return servnumber;
	}

	public void setServnumber(String servnumber) 
	{
		this.servnumber = servnumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}

	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}

	public NserCustomerSimp getCustomer() {
		return customer;
	}

	public void setCustomer(NserCustomerSimp customer) {
		this.customer = customer;
	}

	public ReissueCardBean getReissueCardBean() {
		return reissueCardBean;
	}

	public void setReissueCardBean(ReissueCardBean reissueCardBean) {
		this.reissueCardBean = reissueCardBean;
	}

	public List<FeeConfirmPO> getFeeList() {
		return feeList;
	}

	public void setFeeList(List<FeeConfirmPO> feeList) {
		this.feeList = feeList;
	}

	/**
	 * @return 返回 usableTypes
	 */
	public List getUsableTypes() {
		return usableTypes;
	}

	/**
	 * @param 对usableTypes进行赋值
	 */
	public void setUsableTypes(List usableTypes) {
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
		tMoney = money;
	}

	/**
	 * @return 返回 payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param 对payType进行赋值
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * @return 返回 feeChargeService
	 */
	public FeeChargeHubService getFeeChargeService() {
		return feeChargeService;
	}

	/**
	 * @param 对feeChargeService进行赋值
	 */
	public void setFeeChargeService(FeeChargeHubService feeChargeService) {
		this.feeChargeService = feeChargeService;
	}

	/**
	 * @return 返回 cardChargeLogVO
	 */
	public CardChargeLogVO getCardChargeLogVO() {
		return cardChargeLogVO;
	}

	/**
	 * @param 对cardChargeLogVO进行赋值
	 */
	public void setCardChargeLogVO(CardChargeLogVO cardChargeLogVO) {
		this.cardChargeLogVO = cardChargeLogVO;
	}

	/**
	 * @return 返回 errorType
	 */
	public String getErrorType() {
		return errorType;
	}

	/**
	 * @param 对errorType进行赋值
	 */
	public void setErrorType(String errorType) {
		this.errorType = errorType;
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

	/**
	 * @return 返回 printcontext
	 */
	public String getPrintcontext() {
		return printcontext;
	}

	/**
	 * @param 对printcontext进行赋值
	 */
	public void setPrintcontext(String printcontext) {
		this.printcontext = printcontext;
	}
	
	
}
