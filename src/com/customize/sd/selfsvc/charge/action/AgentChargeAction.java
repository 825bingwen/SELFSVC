package com.customize.sd.selfsvc.charge.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.AgentChargeBean;
import com.customize.sd.selfsvc.charge.service.FeeChargeService;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * <代理商空中充值账户续费>
 * <功能详细描述>
 * 
 * @author  sWX219697
 * @version  [版本号, Jun 5, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark create by sWX219697 OR_huawei_201404_1118 山东_[自助终端]_支撑代理商空中充值续费
 */
public class AgentChargeAction extends BaseAction
{
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 日志
	 */
	private static final Log logger = LogFactory.getLog(AgentChargeAction.class);
	
    /**
     * 业务层
     */
    private FeeChargeService feeChargeService;
    
    /**
     * 接口调用
     */
    private transient AgentChargeBean agentChargeBean;
	
	/**
	 * 菜单
	 */
    private String curMenuId = "";
    
    /**
     * 用户手机号
     */
    private String servnumber;
    
    /**
     * 代理商充值的金额
     */
    private String yingjiaoFee;
    
    /**
     * 银行实际扣款金额
     */
    private String tMoney;
    
    /**
     * 终端机流水号
     */
    private String terminalSeq;
    
    /**
     * 错误信息
     */
    private String errormessage;
    
    /**
     * 代理商手机号码所属地区
     */
    private String servRegion;
    
    /**
     * 银行卡号
     */
    private String cardnumber;
    
    /**
     * 银联为刷卡的终端分配的唯一编号
     */
    private String unionpaycode;
    
    /**
     * 银联商户号（授卡方标识）
     */
    private String unionpayuser;
    
    /**
     * 业务类型
     */
    private String busitype;
    
    /**
     * 终端批次号
     */
    private String batchnum;
    
    /**
     * 银联实际扣款金额，单位（分）
     */
    private String unionpayfee;
    
    /**
     * 银联实际扣款时间
     */
    private String unionpaytime;
    
    /**
     * 授权码（山东用）
     */
    private String authorizationcode;
    
    /**
     * 交易参考号（山东用）
     */
    private String businessreferencenum;
    
    /**
     * 评证号（山东用）
     */
    private String vouchernum;
    
    /**
     * BOSS流水
     */
    private String dealNum;
    
    /**
     * 缴费日志对应的oid
     */
    private String chargeLogOID;
    
    /**
     * 异常类型，add：新增异常日志，其他更新异常日志
     */
    private String errorType;
    
    /**
     * 是否需要退卡
     */
    private String needReturnCard;
    
    /**
     * 凭条交易时间
     */
    private String pDealTime;
    
    /**
     * 代理商名称
     */
    private String agentName;
    
    /**
     * 代理商账户
     */
    private String agentAccount;
    
    /**
     * 账户余额
     */
    private String balance;
    
    /**
     * 代理商组织机构编码
     */
    private String orgId;
    
    /**
     * 代理商缴费前流水号
     */
    private String beforeChargeNo;
    
    /**
     * 代理商科目编码
     */
    private String subjectId;
    /**
     * 银联扣款接口的返回码
     */
    private String unionRetCode;
    
    /**
     * 银联打印信息
     */
    private String printcontext;
    
    /**
     * 代理商充值时可选择的金额
     */
    private List<DictItemPO> selectMoneyList;

    /**
     * 代理商单笔充值的最低金额
     */
    private String minMoney;
    /**
     * <跳转至代理商续费页面>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
	public String agentChargePage()
    {
        logger.debug("agentChargePage start");

        //失败页面
        String forward = "error";
		HttpSession session = this.getRequest().getSession();
		
		//终端机信息
		TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
		
		//代理商手机号信息
		NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);

		//手机号码
		servnumber = customerSimp.getServNumber();
		
		//归属地
		servRegion = customerSimp.getRegionID();
		
		//检测当前时间银联卡充值是否可用
		String time = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_CARD_LIMIT);
		String errorMsg = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_CARD_MSG);
		
		//判断配置的时间是否为空，若为空，则不作时间段限制
		if (null != time && !"".equals(time.trim()))
		{
		    // 当前时间
		    String currTime = CommonUtil.dateToString(new Date(), "HHmm");
		    
		    // 当前时间在0025至2320之间时可用
		    String[] times = time.split("-");
		    
		    //当前时间段可以充值
		    if (times.length == 2 && currTime.compareTo(times[1]) > 0 && currTime.compareTo(times[0]) < 0)
		    {
		    	logger.debug("当前时间:"+time+"代理商可以充值");
		    }
		    
		    //结账期内不能使用银联卡交费
		    else
		    {
		    	setErrormessage(errorMsg);
		    	this.createRecLog(servnumber, Constants.AGENT_CHARGE, "0", "0", "1", "当前时间段银联卡充值不可用");
		    	return forward;
		    }
		}
		
		//查询手机号对应的代理商信息
		Map<String,String> resultMap = agentChargeBean.qryAgentInfo(termInfo, servnumber, curMenuId);
		
		//查询成功
		if (String.valueOf(SSReturnCode.SUCCESS).equals(resultMap.get("retcode")))
		{
		    //代理商组织机构编码
		    orgId = resultMap.get("orgId");
		    
		    //代理商名称
		    agentName = resultMap.get("agentName");
		    
		    //代理商资金账户编码
			agentAccount = resultMap.get("agentAccount");
			
			//代理商科目编码
			subjectId = resultMap.get("subjectId");
			
			//账户余额
			balance = resultMap.get("balance");
			
			//从字典中查询代理商可选择的金额
			selectMoneyList = (List<DictItemPO>)PublicCache.getInstance()
				.getCachedData(Constants.AGENT_SELECT_MONEY);
			
			//查询单笔充值的最低金额限制，若没有配置，则默认为200元
			String lowMoney = (String)PublicCache.getInstance().getCachedData(Constants.AGENT_MIN_MONEY);
			minMoney = ((null == lowMoney || "".equals(lowMoney)) ? "200" : lowMoney);
			
			forward = "otherFee";
		}
		
		//查询代理商信息失败
		else
		{
			logger.error("查询代理商账户信息失败！");
		    String errMsg = CommonUtil.getParamValue(Constants.AGENT_QRY_INFO_ERR_MSG);
		    
		    //若提示语未配置，则使用默认的
		    errMsg = StringUtils.isEmpty(errMsg) ? "查询代理商账户信息失败，无法为资金账户交费！" : errMsg;
		    
		    //封装错误消息
		    String showErrMsg = StringUtils.isEmpty(resultMap.get("returnMsg")) ? errMsg : resultMap.get("returnMsg");
		    setErrormessage(showErrMsg);
		    
		    // 记录异常日志
		    this.createRecLog(servnumber, Constants.AGENT_CHARGE, "0", "0", "1", "查询代理商账户信息失败");
		}
    	
    	logger.debug("agentChargePage end");
    	return forward;
    }
    
    /**
     * <代理商充值金额输入页面>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String inputMoney()
    {
    	return "inputMoney";
    }
    
    /**
     * <跳转免责声明页面>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String dutyInfo()
    {
    	return "dutyInfo";
    }
    
    /**
     * <代理商充值插卡页面>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String readAgentCard()
    {
        logger.debug("readAgentCard begin");
        
    	String forward = "readAgentCard";
    	
    	try 
    	{
			HttpSession session = this.getRequest().getSession();
			TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);   
			
			//获取交费日志id，记录缴费前日志
			chargeLogOID = feeChargeService.getChargeLogOID();
			CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
			
			//交费日志唯一标识
			cardChargeLogVO.setChargeLogOID(chargeLogOID);
			
			//终端机地区
			cardChargeLogVO.setRegion(termInfo.getRegion());
			
			//终端编号
			cardChargeLogVO.setTermID(termInfo.getTermid());
			
			//操作员编号
			cardChargeLogVO.setOperID(termInfo.getOperid());
			
			//手机号码
			cardChargeLogVO.setServnumber(servnumber);
			
			//交费类型，银联卡 1，现金缴费 4
			cardChargeLogVO.setPayType(Constants.PAYBYUNIONCARD);
			
			//交费金额 单位 分
			cardChargeLogVO.setFee(CommonUtil.yuanToFen(yingjiaoFee));
			
			//记录时间
			cardChargeLogVO.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
			
			//扣款状态 11：交费成功，10：银联扣款成功，但交费失败，01：银联扣款成功的临时状态 00：
			cardChargeLogVO.setStatus("00");
			cardChargeLogVO.setDescription("扣款之前记录日志");

			//手机号归属地
			cardChargeLogVO.setServRegion(servRegion);
			
			//终端的组织机构编码
			cardChargeLogVO.setOrgID(termInfo.getOrgid());
			
			//业务类型（phone：话费缴费  favourable：优惠缴费 agentpay：代理商交费）
			cardChargeLogVO.setRecType("agentpay");
			
			//终端的银行号
			cardChargeLogVO.setBankno(getChargeType("Card") + termInfo.getBankno());
      
			// 插入缴费日志
			feeChargeService.addChargeLog(cardChargeLogVO);
			
			// 屏蔽超时返回首页的功能
			this.getRequest().setAttribute("sdCashFlag", "1");
		} 
    	catch (Exception e) 
    	{
            logger.error("插卡之前记录日志失败：", e);
            
            this.createRecLog(servnumber, Constants.AGENT_CHARGE, "0", "0", "1", "插卡之前记录日志失败，无法使用银联卡进行充值");
            setErrormessage("插卡之前记录日志失败，无法使用银联卡进行充值");
            forward = "cardErrorPage";
		}
        
        logger.debug("readAgentCard end");
    	
    	return forward;
    }
    
    /**
     * <校验用户的充值金额>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-8-23 11:09:10 OR_huawei_201408_657_自助终端代理商资金账户充值功能优化
     */
    public void checkBeforeCharge() throws Exception
    {
        logger.debug("checkBeforeCharge Starting ...");
        
    	HttpSession session = this.getRequest().getSession();
		TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);   
		
		//字符格式
		this.getResponse().setContentType("text/xml;charset=GBK");
        
		PrintWriter writer = null;
        try
        {
            writer = this.getResponse().getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("代理商缴费前充值金额校验失败！");
        }
        
        //代理商缴费前充值金额校验
    	String resultMsg = agentChargeBean.checkBeforeCharge(termInfo, servRegion, 
    			curMenuId, agentAccount, CommonUtil.yuanToFen(yingjiaoFee), subjectId, orgId);
        
		writer.write(resultMsg);
		writer.flush();
    }

    /**
     * <代理商交费前接口调用>
     * <代理商确认银联卡信息后先调用此接口记录交费前信息并获得交费前流水号，再进行银联扣款操作>
     * @see [类、类#方法、类#成员]
     */
    public void beforeAgentCharge() throws Exception
    {
        logger.debug("beforeAgentCharge Starting ...");
        
    	HttpSession session = this.getRequest().getSession();
		TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);   
        
        PrintWriter writer = null;
        try
        {
            writer = this.getResponse().getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("记录缴费前信息接口调用失败！");
        }
        
        //代理商缴费前信息记录接口
    	beforeChargeNo = agentChargeBean.beforeAgentCharge(termInfo, servnumber, curMenuId, 
    			orgId, agentAccount, yingjiaoFee, subjectId);
        
    	//若缴费前流水号不为空，则返回1~~beforeChargeNo，否则返回0
    	if (null != beforeChargeNo && !"".equals(beforeChargeNo))
    	{
    		writer.write("1~~"+beforeChargeNo);
    		writer.flush();
    	}
    	else
    	{
    		writer.write("0");
    		writer.flush();
    	}
    	
    }
    
    /**
     * <代理商交费提交>
     * <银联扣款成功后，调用接口对代理商账户充值>
     * @see [类、类#方法、类#成员]
     */
    public String cardChargeCommit()
    {
        logger.debug("cardChargeCommit start");
        
        String forward = "cardErrorPage";
        
        HttpSession session = this.getRequest().getSession();
        
        //防止用户跳过银联扣款流程
        String referer = this.getRequest().getHeader("Referer");
        
        //若上一个链接为空
        if (null == referer)
        {
            setErrormessage("无效请求！");
            
            return forward;
        }
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        pDealTime = CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        
        //将分转换成元，注意：银联返回的结果以分为单位，充值接口则以元为单位
        tMoney = CommonUtil.fenToYuan(tMoney);
        
        // 更新日志
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        selfCardPayLogVO.setChargeLogOID(chargeLogOID);
        selfCardPayLogVO.setRegion(termInfo.getRegion());
        selfCardPayLogVO.setOrgID(termInfo.getOrgid());
        
        // 代理商充值
        Map<String,Object> resultMap = agentChargeBean.agentCharge(termInfo, servnumber, curMenuId, tMoney, beforeChargeNo);
        
        //充值成功
        if (null != resultMap && SSReturnCode.SUCCESS == (Integer)resultMap.get("retcode"))
        {
            selfCardPayLogVO.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
            selfCardPayLogVO.setStatus("11");
            selfCardPayLogVO.setDescription("代理商银联卡交费成功！");
            
            forward = "success";
            
            // 记录缴费成功日志
            this.createRecLog(servnumber, Constants.AGENT_CHARGE, "0", "0", "0", "代理商交费:自助终端交费成功!");
        }
        
        //充值失败，保存错误信息
        else
        {
            String errMsg = "";
            if (null != resultMap)
            {
                errMsg = (String) resultMap.get("returnMsg");
            }
            
            if (null == errMsg || "".equals(errMsg.trim()))
            {
                errMsg = "银联卡扣款成功，但是交费失败！";
            }
            
            selfCardPayLogVO.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
            selfCardPayLogVO.setStatus("10");
            selfCardPayLogVO.setDescription(errMsg);
            
            forward = "cardErrorPage";
            
            String chargeErrMsg = (String) PublicCache.getInstance().getCachedData(Constants.AGENT_CHARGE_ERR_MSG);
            
            //若提示语未配置，则使用默认的
            if (null == chargeErrMsg || "".equals(chargeErrMsg))
            {
            	chargeErrMsg = "银联卡扣款成功，但是交费失败，我们将在稍后为您补交，请联系营业员取走您的银行卡！";
            }
            
            setErrormessage(chargeErrMsg);
            
            // 记录缴费失败日志
            this.createRecLog(servnumber, Constants.AGENT_CHARGE, "0", "0", "1", errMsg);
        }
        
        feeChargeService.updateChargeLog(selfCardPayLogVO);
        
        logger.debug("cardChargeCommit end");
        
        return forward;
    }
    
    /**
     * <银联交费异常处理>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String goCardError()
    {
        logger.debug("goCardError Starting ...");
        
        //记录异常日志
        this.createRecLog(servnumber, Constants.AGENT_CHARGE, "0", "0", "1", errormessage);
        
        // 终端信息
        HttpSession session = getRequest().getSession();
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        CardChargeLogVO selfCardPayLogVO = new CardChargeLogVO();
        
        //错误类型：为空或add，表示新增日志，否则更新原有的日志
        if (null == errorType || "".equals(errorType) || "add".equalsIgnoreCase(errorType))
        {
            selfCardPayLogVO.setChargeLogOID(feeChargeService.getChargeLogOID());
            selfCardPayLogVO.setRegion(termInfo.getRegion());
            selfCardPayLogVO.setTermID(termInfo.getTermid());
            selfCardPayLogVO.setOperID(termInfo.getOperid());
            selfCardPayLogVO.setServnumber(servnumber);
            
            //银联卡支付
            selfCardPayLogVO.setPayType(Constants.PAYBYUNIONCARD);
            
            //tMoney:银联扣款金额，yingjiaoFee：代理商输入的金额
            if (null == tMoney || "".equals(tMoney.trim()))
            {
                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(yingjiaoFee));
            }
            else
            {
                selfCardPayLogVO.setFee(CommonUtil.yuanToFen(tMoney));
            }
            
            selfCardPayLogVO.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
            selfCardPayLogVO.setStatus("00");
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setServRegion(servRegion);
            selfCardPayLogVO.setOrgID(termInfo.getOrgid());
            selfCardPayLogVO.setRecType("agentpay");
            selfCardPayLogVO.setBankno(getChargeType("Card") + termInfo.getBankno());
            feeChargeService.addChargeLog(selfCardPayLogVO);
        }
        // 更新日志
        else
        {
            selfCardPayLogVO.setChargeLogOID(chargeLogOID);
            selfCardPayLogVO.setRegion(termInfo.getRegion());
            selfCardPayLogVO.setOrgID(termInfo.getOrgid());
            
            //若tMoney不为空，说明银联已成功扣款，需要将status改为10:银联扣款成功，但交费失败
            if (null == tMoney || "".equals(tMoney.trim()))
            {
                selfCardPayLogVO.setStatus("00");
            }
            else
            {
                selfCardPayLogVO.setStatus("10");
            }
            selfCardPayLogVO.setDescription(errormessage);
            selfCardPayLogVO.setPosResCode(unionRetCode);
            
            feeChargeService.updateChargeLog(selfCardPayLogVO);
        }
        
        logger.debug("goCardError End");
        
        return "cardErrorPage";
    }
    
    /**
     * <银联扣款成功后更新日志信息>
     * <功能详细描述>
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public void updateCardChargeLog() throws Exception
    {
        logger.debug("updateCardChargeLog Starting...");
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        this.getResponse().setContentType("text/xml;charset=GBK");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try
        {
            writer = this.getResponse().getWriter();
        }
        catch (IOException e)
        {
            throw new IOException("银联扣款之后记录日志失败");
        }
        
        // 组装日志对象
        CardChargeLogVO cardChargeLogVO = new CardChargeLogVO();
        
        //终端所属地区
        cardChargeLogVO.setRegion(termInfo.getRegion());
        
        //终端组织机构编码
        cardChargeLogVO.setOrgID(termInfo.getOrgid());
        
        //日志唯一标示
        cardChargeLogVO.setChargeLogOID(chargeLogOID);
        
        //银联商户号
        cardChargeLogVO.setUnionpayuser(unionpayuser);
        
        //银联终端号
        cardChargeLogVO.setUnionpaycode(unionpaycode);
        
        //消费类型
        busitype = java.net.URLDecoder.decode(busitype, "UTF-8");
        cardChargeLogVO.setBusiType(busitype);
        
        //卡号
        cardChargeLogVO.setCardnumber(cardnumber);
        
        //批次号
        cardChargeLogVO.setBatchnum(batchnum);
        
        //授权码
        cardChargeLogVO.setAuthorizationcode(authorizationcode);
        
        //交易参考号
        cardChargeLogVO.setBusinessreferencenum(businessreferencenum);
        
        //银联实际扣款时间
        cardChargeLogVO.setUnionpaytime(unionpaytime);
        
        //凭证号
        cardChargeLogVO.setVouchernum(vouchernum);
        
        //银联扣款金额
        if (null != unionpayfee)
        {
            while (unionpayfee.startsWith("0"))
            {
                unionpayfee = unionpayfee.substring(1);
            }
        }
        else
        {
            unionpayfee = "";
        }
        
        //实际扣款金额
        cardChargeLogVO.setUnionpayfee(unionpayfee);
        
        //交易流水号
        cardChargeLogVO.setTerminalSeq(terminalSeq);
        
        //当前交费状态
        String status = (String)request.getParameter("status");
        cardChargeLogVO.setStatus(status);
        
        //描述信息
        String description = (String)request.getParameter("description");
        description = java.net.URLDecoder.decode(description, "UTF-8");
        cardChargeLogVO.setDescription(description);
        
        //日志记录时间
        cardChargeLogVO.setRecdate(CommonUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
        
        String xml = "";
        try
        {
            // 插入缴费日志
            feeChargeService.updateCardChargeLog(cardChargeLogVO);
            
            xml = "1";
        }
        catch (Exception e)
        {
            xml = "0";
            
            logger.error("扣款之后更新日志异常：", e);
        }
        finally
        {
            // 输出到client
            if (null != writer)
            {
                writer.println(xml);
                
                try
                {
                    writer.close();
                    writer = null;
                }
                catch (Exception e)
                {
                    logger.error("关闭writer异常：", e);
                    
                    throw new Exception("扣款之后更新日志异常");
                }
            }
        }
        
        logger.debug("updateCardChargeLog end!");
    }

    /**
     * 取缴费类型
     * 
     * @param payType(Card或者Cash) groupid = 'ChargeType'
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getChargeType(String payType)
    {
        String chargeType = "";
        List<DictItemPO> chargeTypeList = (List<DictItemPO>)PublicCache.getInstance()
                .getCachedData(Constants.ChargeType);

        if (null != chargeTypeList)
        {
        	for (int i = 0; i < chargeTypeList.size(); i++)
            {
                DictItemPO dictItemPO = chargeTypeList.get(i);
                if (payType.equals(dictItemPO.getDictid()))
                {
                    chargeType = dictItemPO.getDictname();
                    break;
                }
            }
        }
        
        return chargeType;
    }
    
	public FeeChargeService getFeeChargeService() {
		return feeChargeService;
	}

	public void setFeeChargeService(FeeChargeService feeChargeService) {
		this.feeChargeService = feeChargeService;
	}

	public AgentChargeBean getAgentChargeBean() {
		return agentChargeBean;
	}

	public void setAgentChargeBean(AgentChargeBean agentChargeBean) {
		this.agentChargeBean = agentChargeBean;
	}

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getServnumber() {
		return servnumber;
	}

	public void setServnumber(String servnumber) {
		this.servnumber = servnumber;
	}

	public String getTMoney() {
		return tMoney;
	}

	public void setTMoney(String money) {
		tMoney = money;
	}

	public String getTerminalSeq() {
		return terminalSeq;
	}

	public void setTerminalSeq(String terminalSeq) {
		this.terminalSeq = terminalSeq;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public String getServRegion() {
		return servRegion;
	}

	public void setServRegion(String servRegion) {
		this.servRegion = servRegion;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getUnionpaycode() {
		return unionpaycode;
	}

	public void setUnionpaycode(String unionpaycode) {
		this.unionpaycode = unionpaycode;
	}

	public String getUnionpayuser() {
		return unionpayuser;
	}

	public void setUnionpayuser(String unionpayuser) {
		this.unionpayuser = unionpayuser;
	}

	public String getBusitype() {
		return busitype;
	}

	public void setBusitype(String busitype) {
		this.busitype = busitype;
	}

	public String getBatchnum() {
		return batchnum;
	}

	public void setBatchnum(String batchnum) {
		this.batchnum = batchnum;
	}

	public String getUnionpayfee() {
		return unionpayfee;
	}

	public void setUnionpayfee(String unionpayfee) {
		this.unionpayfee = unionpayfee;
	}

	public String getUnionpaytime() {
		return unionpaytime;
	}

	public void setUnionpaytime(String unionpaytime) {
		this.unionpaytime = unionpaytime;
	}

	public String getAuthorizationcode() {
		return authorizationcode;
	}

	public void setAuthorizationcode(String authorizationcode) {
		this.authorizationcode = authorizationcode;
	}

	public String getBusinessreferencenum() {
		return businessreferencenum;
	}

	public void setBusinessreferencenum(String businessreferencenum) {
		this.businessreferencenum = businessreferencenum;
	}

	public String getVouchernum() {
		return vouchernum;
	}

	public void setVouchernum(String vouchernum) {
		this.vouchernum = vouchernum;
	}

	public String getDealNum() {
		return dealNum;
	}


	public void setDealNum(String dealNum) {
		this.dealNum = dealNum;
	}

	public String getChargeLogOID() {
		return chargeLogOID;
	}


	public void setChargeLogOID(String chargeLogOID) {
		this.chargeLogOID = chargeLogOID;
	}


	public String getErrorType() {
		return errorType;
	}


	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}


	public String getNeedReturnCard() {
		return needReturnCard;
	}


	public void setNeedReturnCard(String needReturnCard) {
		this.needReturnCard = needReturnCard;
	}


	public String getPDealTime() {
		return pDealTime;
	}


	public void setPDealTime(String dealTime) {
		pDealTime = dealTime;
	}


	public String getAgentName() {
		return agentName;
	}


	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}


	public String getAgentAccount() {
		return agentAccount;
	}


	public void setAgentAccount(String agentAccount) {
		this.agentAccount = agentAccount;
	}


	public String getBalance() {
		return balance;
	}


	public void setBalance(String balance) {
		this.balance = balance;
	}


	public String getOrgId() {
		return orgId;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getUnionRetCode() {
		return unionRetCode;
	}


	public void setUnionRetCode(String unionRetCode) {
		this.unionRetCode = unionRetCode;
	}


	public String getPrintcontext() {
		return printcontext;
	}


	public void setPrintcontext(String printcontext) {
		this.printcontext = printcontext;
	}


	public String getBeforeChargeNo() {
		return beforeChargeNo;
	}

	public void setBeforeChargeNo(String beforeChargeNo) {
		this.beforeChargeNo = beforeChargeNo;
	}

	public String getYingjiaoFee() {
		return yingjiaoFee;
	}

	public void setYingjiaoFee(String yingjiaoFee) {
		this.yingjiaoFee = yingjiaoFee;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public List<DictItemPO> getSelectMoneyList() {
		return selectMoneyList;
	}

	public void setSelectMoneyList(List<DictItemPO> selectMoneyList) {
		this.selectMoneyList = selectMoneyList;
	}

	public String getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(String minMoney) {
		this.minMoney = minMoney;
	}
    
}
