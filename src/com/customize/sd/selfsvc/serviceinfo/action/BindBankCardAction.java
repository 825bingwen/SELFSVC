/*
 * 文 件 名:  BindBankCardAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <无密绑定银行卡>
 * 修 改 人:  zWX176560
 * 修改时间:  Sep 13, 2013
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <新增>
 */
package com.customize.sd.selfsvc.serviceinfo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.BindBankCardBean;
import com.customize.sd.selfsvc.serviceinfo.model.BankCardInfoPO;
import com.customize.sd.selfsvc.serviceinfo.model.BindBankCardPO;
import com.customize.sd.selfsvc.serviceinfo.service.BindBankCardService;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.UserLoginBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DESedeEncrypt;

/**
 * <无密绑定银行卡>
 * <功能详细描述>
 * 
 * @author  zWX176560
 * @version  2013/09/14 R003C13L08n01 OR_SD_201309_66 
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BindBankCardAction extends BaseAction 
{
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 日志
     */
    private static Log logger = LogFactory.getLog(BindBankCardAction.class);
    
    /**
     * 无密绑定银行卡bean
     */
    private BindBankCardBean bindBankCardBean;
    
    /**
     * 用户登录Bean
     */
    private UserLoginBean userLoginBean;
    
    /**
     * 银行卡绑定信息PO
     */
    private BindBankCardPO bindBankCardPO = new BindBankCardPO();
    
    /**
     * 月
     */
    private String expire1 = "";
    
    /**
     * 年
     */
    private String expire2 = "";
    
    /**
     * 当前菜单编码
     */
    private String curMenuId = "";
    
    /**
     * 异常信息
     */
    private String errorMessage = "";
    
    /**
     * 
     */
    private String confirmUnbind = "";
    
    /**
     * 短信随机码
     */
    private String randomPwd;
    
    /**
     * 短信随机码输入的错误次数
     */
    private String randomPwdErrTimes;
    
    // Add begin wWX217192 2014-11-28 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
    /**
     * 0: 后付费 1: 预付费 9: 查询绑定的用户不存在
     */
    private String payTypeFlag = "";
    
    /**
     * 验证码类型
     */
    private String smsType;
    
    /**
     * 短信验证码
     */
    private String smsCode;
    
    /**
     * 和包提交标志 1：签约提交，2：解约提交
     */
    private String heBaoCommitFlag;
    
    /**
     * 业务办理成功后的提示信息
     */
    private String successMessage;
    
    /**
     * 查询银行卡信息Service
     */
    private transient BindBankCardService bindBankCardService;
    
    /**
     * 银行卡信息列表
     */
    private List<BankCardInfoPO> cardInfoList = new ArrayList<BankCardInfoPO>();
    
    /**
     * 用户信息展示页面温馨提示
     */
    private String userInfoTips = "";
    
    /**
     * 自动交费阀值信息
     */
    private List<DictItemPO> balanceList;
    
    /**
     * 自动交费充值金额
     */
    private List<DictItemPO> chargeList;
    
    /**
     * 选定的银行卡信息
     */
    private BankCardInfoPO bankCardInfoPO = new BankCardInfoPO();
    
    /**
     * 设置自动交费的类型
     */
    private String oprType;
    
    /**
     * 最低余额值
     */
    private String trigAmt;
    
    /**
     * 划扣金额
     */
    private String drawAmt;
    
    /**
     * 管理页面温馨提示信息
     */
    private String HBManageTips;
    
    /**
     * 登录页面的银行卡卡号
     */
    private String cardNo;
    
    /**
     * 登录页面的手机号码
     */
    private String servnumber;
    
    /**
     * 登录页面服务密码
     */
    private String password;
    // Add end wWX217192 2014-11-28  R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
    
    //add begin sWX219697 2014-12-5 09:44:10 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
    /**
     * 副号码列表
     */
    private List<BankCardInfoPO> viceNumList;
    
    /**
     * 用户已绑定的副号码串，格式：13911111111~13811111111
     */
    private String oldViceNumber;
    
    /**
     * 易充值管理界面温馨提示字典项
     */
    private List<DictItemPO> easyPayTipList;
    //add end sWX219697 2014-12-5 09:44:10 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
    
    /**
     * <校验用户是否为真实信息登入>
     * <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String checkLoginUserIsFactUser() throws Exception
    {
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            
        // 定向到错误页面
        String forward = "checkError";
        
        userInfoTips = (String)PublicCache.getInstance().getCachedData(Constants.SH_HBUSERINFO_TIPS);
        
        // 设置页面手机号码是客户登录的手机号码
        bindBankCardPO.setTelNum(customer.getServNumber());
        
        // 查询用户绑定银行卡信息
        Map<String, String> bindCardMap = null;
        
        // modify begin wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
        // 查询和包绑定信息
        Map<String, Object> heBaoMap = new HashMap<String, Object>();
        
        // 校验用户是否是真实身份登录
        Map retMap = bindBankCardBean.checkIsFactNameRegist(terminalInfoPO, customer, curMenuId);
        
        try
        {
        	// 易充值签约关系查询
            bindCardMap = bindBankCardBean.queryBindInfo(bindBankCardPO);
        }
        catch(Exception e)
        {
            // 创建错误日志
        	this.createRecLog(Constants.SH_HEBAO_SIGNRELATIONSHIP, "0", "0", "1", "易充值签约关系查询失败，请稍后再试!");
            this.getRequest().setAttribute("errormessage", "易充值签约关系查询失败，请稍后再试!");
            return forward;
        }
        
        // 易充值已经绑定，则跳转到绑定信息提示页面
        // 1:已签约 0:已解约 或未签约
        if ("1".equals(bindCardMap.get("STATUS")))
        {
        	//modify by sWX219697 2014-12-15 11:24:11 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
        	//易充值管理页面开关，1：开启，跳转至易充值管理页面。非1：关闭，跳转至解绑定页面
            if ("1".equals(CommonUtil.getParamValue(Constants.SH_EASYPAYMNG_SWITCH)))
            {
            	String bankCardNum = bindCardMap.get("CARDNO");
            	
            	//展示银行卡号后四位
            	bankCardInfoPO.setBankCardNum(bankCardNum.substring(bankCardNum.length()-4));
            	
                forward = easyPayMng();
            }
            
            //易充值管理页面关闭，跳转至解绑定页面
            else
            {
                //定义错误信息
                errorMessage = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_BINDTIP);
                this.getRequest().setAttribute("errormessage", errorMessage);
                
                // 转到解绑页面
                forward = "unBindCard";
            }
            //modify end sWX219697 2014-12-15 11:29:26 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求

            return forward;
        }
        
    	// 和包易充值签约关系查询
        heBaoMap = bindBankCardBean.checkHeBao(terminalInfoPO, customer, curMenuId, cardNo);
        
        // 和包易充值已绑定
        // 0: 已签约   非0：未签约或已失效 此处处理逻辑在Bean层
        if(heBaoMap.containsKey("returnObj"))
        {
        	// 用户为真实身份登录
            if(null != retMap)
            {
                CTagSet ctagset = (CTagSet)retMap.get("returnObj");
                
                // 身份证号码
                bindBankCardPO.setIdCardNum((String)ctagset.get("certid"));
                
                // 姓名
                bindBankCardPO.setUserFactName((String)ctagset.get("custname"));	
            }
        	return manageHBEasyPay(heBaoMap);
        }
        // 和包易充值查询签约关系接口调用时异常处理
        else if(SSReturnCode.ERROR == (Integer)heBaoMap.get("status"))
        {
        	this.createRecLog(Constants.SH_HEBAO_SIGNRELATIONSHIP, "0", "0", "1", (String)heBaoMap.get("errMsg"));
        	this.getRequest().setAttribute("errormessage", heBaoMap.get("errMsg"));
        	return forward;
        }
        // modify end wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
        
        //用户没有绑定
        // 类型列表
        List<DictItemPO> dictItemList = getDictItemByGrp("easyPayChange");   
        
        // 用户为真实身份登录
        if(null != retMap)
        {
            CTagSet ctagset = (CTagSet)retMap.get("returnObj");
            
            // 证件类型
            bindBankCardPO.setIdCardType((String)ctagset.get("certtypeid"));
            
            // 遍历类型列表
            for(DictItemPO dictItemPO : dictItemList)
            {
                if (dictItemPO.getDictid().equals(bindBankCardPO.getIdCardType()))
                {
                    // 证件类型_浪潮用
                    bindBankCardPO.setId_type(dictItemPO.getDictname().split(":")[0]);
                    
                    // 证件类型_显示用
                    bindBankCardPO.setIdCardTypeText(dictItemPO.getDictname().split(":")[1]);
                    
                    break;
                }
            }
            
            // 证件号码
            bindBankCardPO.setIdCardNum((String)ctagset.get("certid"));
            
            // 姓名
            bindBankCardPO.setUserFactName((String)ctagset.get("custname"));
        }
//            bindBankCardPO.setUserFactName("刘星");
//            bindBankCardPO.setIdCardNum("111111");
//            bindBankCardPO.setIdCardType("身份证");
        forward = "checkSuccess";
        return forward;
    }
    
    /**
     * 和包易充值管理
     * @param heBaoMap
     * @return
     * @remark create by wWX217192 2014-12-12 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
     */
    private String manageHBEasyPay(Map<String, Object> heBaoMap)
    {
    	// 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
    	// 管理页面温馨提示信息
    	HBManageTips = (String)PublicCache.getInstance().getCachedData(Constants.SH_HBUSERINFO_TIPS);
    	
    	CTagSet set = (CTagSet) heBaoMap.get("returnObj");
    	
    	// 银行卡卡号后4位
    	bindBankCardPO.setBankCardNum(set.GetValue("cardNo"));
    	
    	// 快捷支付协议号
    	bindBankCardPO.setAGRNO(set.GetValue("AGRNO"));
    	
    	// 银行卡类型
    	bindBankCardPO.setBankCardType(set.GetValue("cardType"));
    	
    	// 银行代码 为自动交费设置功能准备数据
    	bankCardInfoPO.setBankId(set.GetValue("BANKABBR"));
    	
    	// 银行代码，为发送短信随机码使用
    	bindBankCardPO.setBankAbbr(set.GetValue("BANKABBR"));
    	
		try
		{
			Map<String, String> payTypeMap = new HashMap<String, String>();
			
			// 付费类型查询接口调用
			payTypeMap = bindBankCardBean.qrySubsPrepayType(customer, terminalInfoPO, curMenuId);
			
			// 后付费
			if("0".equals(payTypeMap.get("payType")))
			{
				payTypeFlag = "0";
			}
			// 预付费
			else if("1".equals(payTypeMap.get("payType")))
			{
				payTypeFlag = "1";
				
				// 最低触发余额列表
				balanceList = getDictItemByGrp("EasyPayAutoBalance");
				
				// 最低充值金额列表
				chargeList = getDictItemByGrp("EasyPayAutoChargeValue");
			}
		}
		catch(Exception e)
		{
			// 创建错误日志
			this.createRecLog(Constants.SH_HEBAO_SIGNRELATIONSHIP, "0", "0", "1", e.getMessage());
			this.getRequest().setAttribute("errormessage", e.getMessage());
			return "checkError";
		}
		
    	return "heBaoManage";
    }
    
    /**
     * 转向卡信息登记界面
     * 
     * @see [类、类#方法、类#成员]
     * @remark create xKF69944 Aug 21, 2013[需求名称]
     */
    public String writeBankCardInfo()
    {
        //"0"为银行卡，"1"为信用卡
        String bankCardType = bindBankCardPO.getBankCardType();
        
        // 获取自助终端系统配置的银行卡信息
        cardInfoList = bindBankCardService.getBankInfoList(bankCardType);
        
        if("0".equals(bankCardType))
        {
            return "debitCard";
        }
        else
        {
            return "creditCard";
        }
    }
    
    /**
     *无密绑定模式提交
     * 
     * @return
     * @throws Exception 
     * @see [类、类#方法、类#成员]
     */
    public String noEncryptBindComit() throws Exception
    {
        // modify begin by zKF69263 2014-6-5 OR_SD_201404_563 关于对易充值安全验证及页面进行优化调整的需求
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // 校验输入的短信随机码
        String resultMsg = this.loginWithRandomPwd(customer.getServNumber(), getRandomPwd());
        
        // 返回不等"1"表示发送短信随机码失败
        if (!"1".equals(resultMsg))
        {
            // 校验短信密码输入错误次数
            return checkInputTimes(customer.getServNumber(), resultMsg);
        }
        
        if (logger.isInfoEnabled())
        {
            logger.info("用户" + customer.getServNumber() + "使用短信随机密码进行身份认证成功");
        }
        // modify end by zKF69263 2014-6-5 OR_SD_201404_563 关于对易充值安全验证及页面进行优化调整的需求
        
        // 有效期
        bindBankCardPO.setExpire(expire2 + expire1);
        
        // 移动手机号
        bindBankCardPO.setTelNum(customer.getServNumber());
        
        // modify begin by hWX5316476 2014-2-11  OR_SD_201402_389易充值自助终端绑定时禁止业务判断条件的优化需求
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)this.getRequest().getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 办理易充值签约之前调用接口查看该用户是否可以开通该产品
        ReturnWrap result = bindBankCardBean.checkProCondition(customer, termInfo, "ZLWGQY", "ADD", "", "","0","EXECUTECMD", curMenuId);
       
        // 根据返回报文分析是否可以开通该产品，如果调用成功，则表示可以开通该产品，否则不符合开通该产品的条件
        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if(result != null && result.getStatus() == SSReturnCode.SUCCESS)
        if(result.getStatus() == SSReturnCode.SUCCESS)
        {
            // 无密绑定模式提交
            Map<String, String> retMap = null;
            
            try
            {
                retMap = bindBankCardBean.noEncrptyBindComit(bindBankCardPO);
            }
            catch(Exception e)
            {
                logger.info(e.getMessage());
                // 创建错误日志
                this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", "易充值签约失败，网路错误，请稍后再试!");
                this.getRequest().setAttribute("errormessage", "易充值签约失败，网路错误，请稍后再试!");
                return "bindError";
            }
            
            //add begin sWX219697 2014-12-22 14:44:56 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
            //绑定银联卡后，后付费用户自动开通自动交费功能
            try 
            {
				Map<String, String> returnMap = null;
				
				//查询用户付费类型
				returnMap = bindBankCardBean.qrySubsPrepayType(customer, termInfo, curMenuId);
				
				//后付费用户自动开通自动交费
				if("0".equals(returnMap.get("payType")))
				{
					//开通自动交费功能，操作类型：1
					bindBankCardBean.bindAutoFeeSet(customer, termInfo, curMenuId, "1", "", "");
				}
			} 
            
            //若开通自动交费失败，只需要记录错误信息，不需要跳转错误页面，用户可后续再开通
            catch (ReceptionException e) 
            {
				logger.error("绑定银联卡后自动开通自动交费失败", e);
                this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", e.getMessage());
			}
            //add end sWX219697 2014-12-22 14:55:53 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
            
            //绑定成功
            if("000".equals(retMap.get("rtn_code")))
            {
                bindBankCardPO.setAppFlowCode(retMap.get("pg_order_code"));
                
                // 创建成功日志
                this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "0", "易充值签约成功!");
                this.getRequest().setAttribute("errormessage", "易充值签约成功!");
                return "bindSuccess";
            }
            //绑定失败
            else
            {
                String rtn_msg = "易充值签约失败!";
                
                // 显示签约失败的具体原因
                if (retMap.get("rtn_msg") != null && !"".equals(retMap.get("rtn_msg")))
                {
                    rtn_msg = retMap.get("rtn_msg");
                }
                
                // modify begin by zKF69263 2014-6-6 OR_SD_201404_563 关于对易充值安全验证及页面进行优化调整的需求
                // 信息转换列表
                List<DictItemPO> dictItemList = getDictItemByGrp("easyPayMsgChg");
                
                if (dictItemList != null)
                {
                    // 遍历列表
                    for(DictItemPO dictItemPO : dictItemList)
                    {
                        if (dictItemPO.getDictid().equals(retMap.get("rtn_code")))
                        {
                            rtn_msg = dictItemPO.getDescription();
                            
                            break;
                        }
                    }
                }
                // modify end by zKF69263 2014-6-6 OR_SD_201404_563 关于对易充值安全验证及页面进行优化调整的需求
                
                // 创建错误日志
                this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", rtn_msg);
                this.getRequest().setAttribute("errormessage", rtn_msg);
                return "bindError";
            }
        }
        else
        {
            // 创建错误日志
            this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", result.getReturnMsg());
            
            // 在错误页面显示错误原因
            this.getRequest().setAttribute("errormessage", result.getReturnMsg());
            
            return "bindError";
        }
        // modify end by hWX5316476 2014-2-11 OR_SD_201402_389易充值自助终端绑定时禁止业务判断条件的优化需求
    }

    /**
     * 银行卡解绑定
     * 
     * @return
     * @throws Exception
     * @remark
     * @modify yWX163692 2013年11月19日 OR_SD_201309_940 易充值二阶段，解约新增自动交费判断流程  
     */
    public String unBindCommit()
    {
        // 返回信息
        Map<String, String> retMap = null;
       
        // 获取终端机信息
        TerminalInfoPO termPO = getTerminalInfoPO();
       
        // 获取客户信息
        NserCustomerSimp customer = getCustomerSimp();
       
	    // 未经用户确认，需判断用户的自动缴费设置
        if("0".equals(confirmUnbind))
	    {
	        try
	        {
	        	//modify by sWX219697 2014-12-2 15:25:04 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
	        	//查询用户是否开通自动交费功能 操作类型：0
	        	retMap = bindBankCardBean.bindAutoFeeSet(customer, termPO, curMenuId, "0", "", "");
	        	
	        	//已开通自动交费功能
	        	if("0".equals(retMap.get("autoStatus")))
	        	{
		        	 //定义错误信息
		            errorMessage = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_UNBINDTIP);
		            this.getRequest().setAttribute("errormessage", errorMessage);
		            
		            // 转到解绑页面
		            return "unBindTip";
	        	}
	        }
	        
	        //接口查询失败
	        catch(ReceptionException e)
	        {
	        	// 创建错误日志
	            this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", e.getMessage());
	        	logger.error("查询用户是否开通自动交费功能：", e);
	        	
	            //转向错误页面
	            return "unBindError";
	        }
	    }
        else
        {
        	//modify by sWX219697 2014-12-2 15:25:51 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
		    // 确认关闭自动交费
	        try
	        {
	        	//关闭自动交费功能，操作类型：2
	        	bindBankCardBean.bindAutoFeeSet(customer, termPO, curMenuId, "2", "", "");
	        }
	        // 记录日志
	        catch(ReceptionException ex)
	        {
		       	// 创建错误日志
		        this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", ex.getMessage());
		           
		        //设置错误信息提示
		        this.getRequest().setAttribute("errormessage", ex.getMessage());
		           
		        //转向错误页面
		        return "unBindError";
	        }
        }
        
        try
        {
            // 调用浪潮解绑定接口
            retMap = bindBankCardBean.unBindBankCard(bindBankCardPO);
        }
        catch (Exception e)
        {
            // 错误提示信息
            String rtn_msg = "银行卡解绑定失败，错误原因：调用银联接口失败！";
            
            // 创建错误日志
            this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", rtn_msg);
            
            //设置错误信息提示
            this.getRequest().setAttribute("errormessage", rtn_msg);
            
            //转向错误页面
            return "unBindError";
        }
        
        if ("0".equals(retMap.get("STATUS")))
        {
            String rtn_msg = "对不起，解绑定银行卡失败失败原因：" + retMap.get("RETMSG");
            
            // 创建错误日志
            this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", rtn_msg);
            
            //设置错误信息提示
            this.getRequest().setAttribute("errormessage", rtn_msg);
            
            //转向错误页面
            return "unBindError";
        }
        else
        {
            // 创建成功日志
            this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "0", "尊敬的" + bindBankCardPO.getTelNum() + "用户,您银行卡解绑定成功！");
            
            // 设置页面返回信息
            this.getRequest().setAttribute("errormessage", "尊敬的" + bindBankCardPO.getTelNum() + "用户,您银行卡解绑定成功！");
            
            //返回
            return "unBindSuccess";
        }
    }
    
    /** 
     * 发送短信随机码
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 OR_SD_201404_563 关于对易充值安全验证及页面进行优化调整的需求
     * @remark modify by wWX217192 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
     * @reson 用户进行易充值绑定时，自助终端系统按照比例进行易充值与和包易充值的分配，故将方法从public改为private类型
     */
    private String sendRandomPwd() 
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("sendRandomPwd Starting ...");
        }
        
        String forward = "error";
        
        // 取得当前Session
        HttpSession session = this.getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 办理易充值签约之前调用接口查看该用户是否可以开通该产品
        ReturnWrap result = bindBankCardBean.checkProCondition(customerSimp, termInfo, "ZLWGQY", "ADD", "", "","0","EXECUTECMD", curMenuId);
       
        // 根据返回报文分析是否可以开通该产品，如果调用成功，则表示可以开通该产品，否则不符合开通该产品的条件
        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if (result != null && result.getStatus() == SSReturnCode.SUCCESS)
        if (result.getStatus() == SSReturnCode.SUCCESS)
        {
            // 生成随机密码
            String randomPwd = createRandomPassword(customerSimp.getServNumber(), CommonUtil.getCurrentTime());
            
            // 获取随机短信密码内容
            String shortMessage = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
            
            // 替换短信随机码参数
            if (StringUtils.isNotEmpty(shortMessage))
            {
                shortMessage = shortMessage.replace("[%1]", randomPwd);
            }
            
            // 发送短信随机码
            if (!userLoginBean.sendRandomPwd(customerSimp, termInfo, shortMessage, curMenuId))
            {
                logger.error("向用户" + customerSimp.getServNumber() + "发送随机密码短信失败");
                
                // 创建错误日志
                this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "随机密码短信发送失败。");
                
                this.getRequest().setAttribute("errormessage", "随机密码短信发送失败，请稍后再试。");
            }
            else
            {
                if (logger.isInfoEnabled())
                {
                    logger.info("向用户" + customerSimp.getServNumber() + "发送随机密码短信成功，随机码" + randomPwd);
                    logger.info("短信内容：" + shortMessage);
                }
                
                forward = "success";
            }
        }
        else
        {
            // 创建错误日志
            this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", result.getReturnMsg());
            
            // 在错误页面显示错误原因
            this.getRequest().setAttribute("errormessage", result.getReturnMsg());
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("sendRandomPwd End");
        }
        
        return forward;
    } 
    
    /** 
     * 输入身份证号
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 OR_SD_201404_563 关于对易充值安全验证及页面进行优化调整的需求
     */
    public String inputCertid()
    {
        return "inputCertid";
    }
    
    /** 
     * 显示用户信息
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 OR_SD_201404_563 关于对易充值安全验证及页面进行优化调整的需求
     */
    public String showUserInfo()
    {
        return "userInfo";
    }
    
    /** 
     * 校验短信密码输入错误次数
     * 
     * @param servNumber
     * @param resultMsg
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 OR_SD_201404_563 关于对易充值安全验证及页面进行优化调整的需求
     */
    private String checkInputTimes(String servNumber, String resultMsg)
    {
        // 从参数表中配置输入短信随机码的最大错误次数
        if (StringUtils.isEmpty(randomPwdErrTimes))
        {
            randomPwdErrTimes = (String)PublicCache.getInstance().getCachedData(Constants.SH_SENDRANDOMERR_MAXTIMES);
        }
        
        // 默认可以输入的最大次数
        if (StringUtils.isEmpty(randomPwdErrTimes))
        {
            randomPwdErrTimes = "5";
        }
        
        // 错误次数减1
        randomPwdErrTimes = String.valueOf(Integer.parseInt(randomPwdErrTimes) - 1);
        
        String errorMsg = "";
        String forward = "error";
        
        if ("-1".equals(resultMsg))
        {
            errorMsg = "您输入的短信密码已经超时，请返回重试或者进行其它操作。";
        }
        else if (Integer.parseInt(randomPwdErrTimes) == 0)
        {
            errorMsg = "短信密码错误输入已达到最大输入次数，请稍后再试。";
        }
        else 
        {
            errorMsg = "短信密码输入错误，还剩"+Integer.parseInt(randomPwdErrTimes)+"次机会，请重新输入。";
            forward = "validRandomError";
        }
        
        logger.error("易充值银行卡签约绑定，用户" + servNumber + "输入的随机密码错误或超时，认证失败");
        
        this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", errorMsg);
        
        this.getRequest().setAttribute("errormessage", errorMsg);
        
        return forward;
    }

    
    /**
     * 和包易充值发送短信验证码接口
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
     */
    public String sendHeBaoRandom()
    {
    	// 取得当前Session
        HttpSession session = this.getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        bindBankCardPO.setUserFactName(customer.getCustomerName());
        
        // 调用短信验证码发送接口
        Map<String, String> resultMap = new HashMap<String, String>();
        
        if(null == bindBankCardPO.getBankCardNum() || "".equals(bindBankCardPO.getBankCardNum()) )
        {
        	bindBankCardPO.setBankCardNum(cardNo);
        }
        
        resultMap =	bindBankCardBean.sendHeBaoRandom(termInfo, customer, curMenuId, smsType, bindBankCardPO);
        
        // 若接口调用成功，且返回交易流水号，系统迁移至输入短信验证码页面
        if(resultMap.containsKey("tradeNo"))
        {
        	bindBankCardPO.setAppFlowCode(resultMap.get("tradeNo"));
        	
        	return "randomPwd";
        }
        // 若接口调用失败，记录错误日志，且系统报错
        else
        {
        	// 创建错误日志
        	this.createRecLog(Constants.SH_HEBAO_SENDRANDOMPWD, "0", "0", "1", resultMap.get("retMsg"));
        	this.getRequest().setAttribute("errormessage", resultMap.get("retMsg"));
        	return "error";
        }
    }
    
    /**
     * 和包易充值签约
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
     */
    public String signHeBaoCommit()
    {
    	// 取得当前Session
        HttpSession session = this.getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);

        Map<String, String> resultMap = bindBankCardBean.signHeBao(termInfo, customer, curMenuId, smsCode, bindBankCardPO);
        
        if(resultMap.containsKey("AGRNO"))
        {
        	bindBankCardPO.setAGRNO(resultMap.get("AGRNO"));
        	
        	setSuccessMessage("和包易充值签约成功!");
        	this.createRecLog(Constants.SH_HEBAO_SIGN, "0", "0", "0", "和包易充值签约成功!");
        	
        	return "signSuccess";
        }
        else
        {
        	// 创建错误日志
        	this.createRecLog(Constants.SH_HEBAO_SIGN, "0", "0", "1", resultMap.get("retMsg"));
        	this.getRequest().setAttribute("errormessage", resultMap.get("retMsg"));
        	return "signError";
        }
    }
    
    /**
     * 通过随机数选择当前易充值受理方式为网管直连还是和包易充值
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2014-11-28 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
     */
    public String getBindBankType()
    {
    	int random = 0;
    	Random r = new Random();
    	random = r.nextInt(100) + 1;
    	 
    	// 获取易充值：和包易充值的比例
    	String percentage = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASTPAY_PERCENTAGE);
    	 
    	// 计算易充值所占随机数的最大值
    	int easyPayNum = Integer.valueOf(percentage.split(":")[0]) * 10;
    	
    	// 当随机数小于easyPayNum时，采用易充值受理方式
    	if(random < easyPayNum)
    	{
    		return sendRandomPwd();
    	}
    	// 当随机数大于easyPayNum时，采用和包易充值的方式
    	else
    	{
    		return sendHeBaoRandom();
    	}
    }
    
    /**
     * 和包易充值解约
     * @return
     * @remark create by wWX217192 2014-11-28 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
     */
    public String unsignHeBao()
    {
    	// 取得当前Session
        HttpSession session = this.getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        try
        {
        	bindBankCardBean.unsignHeBao(termInfo, customer, curMenuId, smsCode, bindBankCardPO);
        	
        	setSuccessMessage("和包易充值解约成功!");
        	this.createRecLog(Constants.SH_HEBAO_SIGN, "0", "0", "0", "和包易充值解约成功!");
        	return SUCCESS;
        }
        catch(ReceptionException e)
        {
        	// 创建错误日志
        	this.createRecLog(Constants.SH_HEBAO_SIGN, "0", "0", "1", "和包易充值解约失败!");
        	this.getRequest().setAttribute("errormessage", e.getMessage());
        	
        	return "error";
        }
    }
    
    /**
     * 和包易充值自动交费设置
     * @return
     * @remark create by wWX217192 2014-11-28 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
     */
    public String setHeBaoAutoValue()
    {
    	// 取得当前Session
        HttpSession session = this.getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        try
        {
        	bindBankCardBean.setHeBaoAutoValue(termInfo, customer, curMenuId, oprType, bankCardInfoPO);
        	
        	setSuccessMessage("自动交费设置成功!");
        	this.createRecLog(Constants.SH_HEBAO_SIGN, "0", "0", "0", "自动交费设置成功!");
        	return SUCCESS;
        }
        catch(Exception e)
        {
        	// 创建错误日志
        	this.createRecLog(Constants.SH_HEBAO_SIGN, "0", "0", "1", "自动交费设置成功!");
        	this.getRequest().setAttribute("errormessage", e.getMessage());
        	
        	return "error";
        }
    }
    
    /**
     * 登录页面跳转
     * @return
     * @remark create by wWX217192 2014-11-28 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
     */
    public String inputNumAndCardNo()
    {
    	// 清除当前登录session
		if (getCustomerSimp() != null)
		{
			//清除session
            this.getRequest().getSession().removeAttribute(Constants.USER_INFO);
		}
		
    	return SUCCESS;
    }
    
    /**
     * 易充值签约登录改写
     * @return
     * @remark create by wWX217192 2014-11-28 R003C10LG1101 OR_SD_201409_82_JT_和包易充值支撑需求
     */
    public String loginWithEasyPay()
    {
    	// 取得当前Session
        HttpSession session = this.getRequest().getSession();
        
    	// 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        String newpwd = password;
        
        try
        {
            DESedeEncrypt encrypt = new DESedeEncrypt();
            newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
        }
        catch (Exception e)
        {
            logger.error("山东密码验证时，加密密码错误：", e);
        }
    	
        Map returnMap = userLoginBean.checkPassword(termInfo, servnumber, curMenuId, newpwd);
    	
    	// 100:成功 102:缺省密码 119:初始密码
    	if (returnMap != null && "100".equals(String.valueOf(returnMap.get("errcode"))))// 100:成功 
    	{
            if (logger.isInfoEnabled())
            {
                logger.info("用户" + servnumber + "使用服务密码进行身份认证成功");
            }
            Map customerSimpMap = userLoginBean.getUserInfo(servnumber, termInfo);
            
            if (customerSimpMap.get("customerSimp") != null)
	        {
            	// 取登录用户信息
	            NserCustomerSimp customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
	                  
            	// 处理session信息
	            NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
	            if (oldCustomerSimp == null)
	            {
	                session.setAttribute(Constants.USER_INFO, customerSimp);
	            }
	            else
	            {
	                //清除用户信息
	                session.removeAttribute(Constants.USER_INFO);
	                
	                //将新的用户信息存放在session中
	                session.setAttribute(Constants.USER_INFO, customerSimp);
	            }
	            this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "使用服务密码进行身份认证成功");
        	}
            
            return SUCCESS;
    	}
    	else
    	{
    		this.getRequest().setAttribute("errormessage", "使用服务密码进行认证失败，请重新输入!");
    		this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "1", "使用服务密码进行身份认证失败!");
    		return "failed";
    	}
    }
    
    /**
     * <易充值签约管理>
     * <1、查询用户付费类型。2、查询用户是否开通自动充值功能。3、查询用户的副号码列表>
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-12-5 09:43:20 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求 
     */
    private String easyPayMng()
    {
    	String result = ERROR;
    	
    	Map<String,String> resMap = null;
    	
        // 获取终端机信息
        TerminalInfoPO termPO = getTerminalInfoPO();
       
        // 获取客户信息
        NserCustomerSimp customer = getCustomerSimp();
        
        //触发金额字典项
        balanceList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAY_FZ);
        
        //划扣金额字典项
        chargeList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAY_HK);
        
        //温馨提示
        easyPayTipList = getDictItemByGrp(Constants.SH_EASYPAY_MNG_TIP);
    	
    	try 
    	{
            //查询用户付费类型 0：后付费 1：预付费 9：查询绑定的用户不存在
        	resMap = bindBankCardBean.qrySubsPrepayType(customer, termPO, curMenuId);
        	
        	//用户付费类型
        	bankCardInfoPO.setPayType(resMap.get("payType"));
        	
			//查询用户是否开通自动充值功能，操作类型 0
			resMap = bindBankCardBean.bindAutoFeeSet(customer, termPO, curMenuId, "0", "", "");
			
			//自动充值
			bankCardInfoPO.setAutoStatus(resMap.get("autoStatus"));
			
			//若用户为预付费用户，且已开通自动充值
			if ("1".equals(bankCardInfoPO.getPayType()) && "0".equals(bankCardInfoPO.getAutoStatus()))
			{
				//触发金额
				bankCardInfoPO.setTrigamt(resMap.get("trigamt"));
				
				//充值金额
				bankCardInfoPO.setDrawamt(resMap.get("drawamt"));
			}
			
			//查询用户的副号码列表，流水线
			Map<String, Object> retMap = bindBankCardBean.viceNumberQry(customer, termPO, curMenuId, "ZLWGQY", "QRY");
			
			//用户已绑定的副号码list
			viceNumList = (List<BankCardInfoPO>)retMap.get("viceNumList");
			
			//用户已绑定的副号码串
			oldViceNumber = (String)retMap.get("oldViceNumber");
			
			result = "easyPayMng";
		} 
    	catch (ReceptionException e) 
    	{
            this.getRequest().setAttribute("errormessage", e.getMessage());
            
	       	// 创建错误日志
	        this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", e.getMessage());
    		logger.error(e);
		}
    	
    	return result;
    }
    
    /**
     * <设置用户的自动扣款金额>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-12-5 15:36:42 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求 
     */
    public String setAutoFee()
    {
    	String forward = ERROR;
    	
    	Map<String,String> resMap = null;
    	
        // 获取终端机信息
        TerminalInfoPO termPO = getTerminalInfoPO();
       
        // 获取客户信息
        NserCustomerSimp customer = getCustomerSimp();
        
        //操作类型，若已开通自动交费则为3:更新，否则为1：开通
        String oprtype = "0".equals(bankCardInfoPO.getAutoStatus()) ? "3" : "1";
        
        //预付费用户的触发余额
        String trigamt = "1".equals(bankCardInfoPO.getPayType()) ? bankCardInfoPO.getTrigamt() : "";
    	
        //预付费用户的扣款金额
        String drawamt = "1".equals(bankCardInfoPO.getPayType()) ? bankCardInfoPO.getDrawamt() : "";
        
    	try 
    	{
    		//自动交费设置
			resMap = bindBankCardBean.bindAutoFeeSet(customer, termPO, curMenuId, oprtype, trigamt, drawamt);
			
			//预付费用户
			if("1".equals(bankCardInfoPO.getPayType()))
			{
				//设置后的触发余额
				bankCardInfoPO.setTrigamt(resMap.get("trigamt"));
				
				//设置后的自动交费金额
				bankCardInfoPO.setDrawamt(resMap.get("drawamt"));
			}
			
			//提示信息
			String successMsg = "0".equals(bankCardInfoPO.getAutoStatus()) ? "自动交费功能修改成功" : "自动交费功能开通成功";
            this.getRequest().setAttribute("successMsg", successMsg);
			
			//跳转至管理页面
			forward = easyPayMng();
		} 
    	catch (ReceptionException e) 
    	{
			logger.error("易充值自动交费功能设置失败：", e);
			
	       	// 创建错误日志
	        this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", e.getMessage());
            this.getRequest().setAttribute("errormessage", e.getMessage());
		}
    	
    	return forward;
    }
    
    /**
     * <易充值用户副号码设置>
     * <用于新增(操作类型：1)、删除(操作类型：2)副号码数组>
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-12-12 16:49:39 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求 
     */
    public String viceNumberMng()
    {
    	String forward = ERROR;
    	
        //用户已绑定的副号码列表
        String[] viceNumArray = bankCardInfoPO.getViceNumber().split("~");
    	
        // 获取终端机信息
        TerminalInfoPO termPO = getTerminalInfoPO();
       
        // 获取客户信息
        NserCustomerSimp customer = getCustomerSimp();
        
        try 
        {
			//副号码设置
			bindBankCardBean.viceNumberSet(customer, termPO, curMenuId, viceNumArray, bankCardInfoPO.getOperType());
			
			//提示信息
			String successMsg = "1".equals(bankCardInfoPO.getOperType()) ? "副号码添加成功" : "副号码删除成功";
            this.getRequest().setAttribute("successMsg", successMsg);
            
			//跳转至管理页面
			forward = easyPayMng();
		} 
        catch (ReceptionException e) 
        {
			logger.error("易充值用户副号码设置失败：", e);
			
	       	// 创建错误日志
	        this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", e.getMessage());
            this.getRequest().setAttribute("errormessage", e.getMessage());
		}
    	
    	return forward;
    }
    
    /**
     * <取消银行卡绑定操作>
     * <若用户已开通自动交费，则先关闭自动交费功能，再解除银行卡绑定>
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-12-9 11:15:50 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求 
     */
    public String cancelBind()
    {
    	String forward = ERROR;
    	
    	Map<String,String> retMap = null;
    	
        // 获取终端机信息
        TerminalInfoPO termPO = getTerminalInfoPO();
       
        // 获取客户信息
        NserCustomerSimp customer = getCustomerSimp();
	        	
        try 
        {
			//查询用户是否开通自动交费功能 操作类型：0
			retMap = bindBankCardBean.bindAutoFeeSet(customer, termPO, curMenuId, "0", "", "");
			
			//已开通
			if("0".equals(retMap.get("autoStatus")))
			{
				//关闭自动交费
				bindBankCardBean.bindAutoFeeSet(customer, termPO, curMenuId, "2", "", "");
			}
			
			retMap.clear();
			
        	bindBankCardPO.setTelNum(customer.getServNumber());
        	
            // 调用浪潮解绑定接口
        	retMap = bindBankCardBean.unBindBankCard(bindBankCardPO);
        	
            if ("0".equals(retMap.get("STATUS")))
            {
                String msg = "对不起，解绑定银行卡失败失败原因：" + retMap.get("RETMSG");
                
                // 创建错误日志
                this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", msg);
                
                //设置错误信息提示
                this.getRequest().setAttribute("errormessage", msg);
            }
            else
            {
                // 创建成功日志
                this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "0", "银行卡解绑定成功");
                
                // 设置页面返回信息
                this.getRequest().setAttribute("errormessage", "尊敬的" + customer.getServNumber() + "用户，您银行卡解绑定成功！");
                
                forward = SUCCESS;
            }
		} 
        catch (ReceptionException e) 
        {
	       	// 创建错误日志
	        this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", e.getMessage());
	           
	        //设置错误信息提示
            this.getRequest().setAttribute("errormessage", e.getMessage());
            
            return forward;
		}
        catch (Exception ex)
        {
            // 错误提示信息
            String msg = "银行卡解绑定失败，错误原因：调用银联接口失败！";
            
            // 创建错误日志
            this.createRecLog(Constants.SH_BINDBANKCARD_DETAILFLAG, "0", "0", "1", msg);
            
            logger.error("银行卡解绑定失败:", ex);
            
            //设置错误信息提示
            this.getRequest().setAttribute("errormessage", msg);
        }
        
    	return forward;
    }
    
    /**
     * <跳转至副号码输入页面>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String inputViceNum()
    {
    	return "inputViceNum";
    }

    
    public BindBankCardPO getBindBankCardPO()
    {
        return bindBankCardPO;
    }
    public void setBindBankCardPO(BindBankCardPO bindBankCardPO)
    {
        this.bindBankCardPO = bindBankCardPO;
    }
    public String getErrorMessage()
    {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public BindBankCardBean getBindBankCardBean()
    {
        return bindBankCardBean;
    }

    public void setBindBankCardBean(BindBankCardBean bindBankCardBean)
    {
        this.bindBankCardBean = bindBankCardBean;
    }

    public String getExpire1()
    {
        return expire1;
    }

    public void setExpire1(String expire1)
    {
        this.expire1 = expire1;
    }

    public String getExpire2()
    {
        return expire2;
    }

    public void setExpire2(String expire2)
    {
        this.expire2 = expire2;
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getConfirmUnbind() {
		return confirmUnbind;
	}

	public void setConfirmUnbind(String confirmUnbind) {
		this.confirmUnbind = confirmUnbind;
	}

    /**
     * @return 返回 userLoginBean
     */
    public UserLoginBean getUserLoginBean()
    {
        return userLoginBean;
    }

    /**
     * @param 对userLoginBean进行赋值
     */
    public void setUserLoginBean(UserLoginBean userLoginBean)
    {
        this.userLoginBean = userLoginBean;
    }

    /**
     * @return 返回 randomPwd
     */
    public String getRandomPwd()
    {
        return randomPwd;
    }

    /**
     * @param 对randomPwd进行赋值
     */
    public void setRandomPwd(String randomPwd)
    {
        this.randomPwd = randomPwd;
    }

    /**
     * @return 返回 randomPwdErrTimes
     */
    public String getRandomPwdErrTimes()
    {
        return randomPwdErrTimes;
    }

    /**
     * @param 对randomPwdErrTimes进行赋值
     */
    public void setRandomPwdErrTimes(String randomPwdErrTimes)
    {
        this.randomPwdErrTimes = randomPwdErrTimes;
    }

	public String getPayTypeFlag() {
		return payTypeFlag;
	}

	public void setPayTypeFlag(String payTypeFlag) {
		this.payTypeFlag = payTypeFlag;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getHeBaoCommitFlag() {
		return heBaoCommitFlag;
	}

	public void setHeBaoCommitFlag(String heBaoCommitFlag) {
		this.heBaoCommitFlag = heBaoCommitFlag;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public BindBankCardService getBindBankCardService() {
		return bindBankCardService;
	}

	public void setBindBankCardService(BindBankCardService bindBankCardService) {
		this.bindBankCardService = bindBankCardService;
	}

	public List<BankCardInfoPO> getCardInfoList() {
		return cardInfoList;
	}

	public void setCardInfoList(List<BankCardInfoPO> cardInfoList) {
		this.cardInfoList = cardInfoList;
	}

	public String getUserInfoTips() {
		return userInfoTips;
	}

	public void setUserInfoTips(String userInfoTips) {
		this.userInfoTips = userInfoTips;
	}

	public List<DictItemPO> getBalanceList() {
		return balanceList;
	}

	public void setBalanceList(List<DictItemPO> balanceList) {
		this.balanceList = balanceList;
	}

	public List<DictItemPO> getChargeList() {
		return chargeList;
	}

	public void setChargeList(List<DictItemPO> chargeList) {
		this.chargeList = chargeList;
	}

	public BankCardInfoPO getBankCardInfoPO() {
		return bankCardInfoPO;
	}

	public void setBankCardInfoPO(BankCardInfoPO bankCardInfoPO) {
		this.bankCardInfoPO = bankCardInfoPO;
	}

	public String getOprType() {
		return oprType;
	}

	public void setOprType(String oprType) {
		this.oprType = oprType;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getTrigAmt() {
		return trigAmt;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setTrigAmt(String trigAmt) {
		this.trigAmt = trigAmt;
	}

	public String getDrawAmt() {
		return drawAmt;
	}

	public void setDrawAmt(String drawAmt) {
		this.drawAmt = drawAmt;
	}

	public String getHBManageTips() {
		return HBManageTips;
	}

	public void setHBManageTips(String manageTips) {
		HBManageTips = manageTips;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public List<BankCardInfoPO> getViceNumList() {
		return viceNumList;
	}

	public void setViceNumList(List<BankCardInfoPO> viceNumList) {
		this.viceNumList = viceNumList;
	}

	public List<DictItemPO> getEasyPayTipList() {
		return easyPayTipList;
	}

	public void setEasyPayTipList(List<DictItemPO> easyPayTipList) {
		this.easyPayTipList = easyPayTipList;
	}
	
	public String getServnumber() {
		return servnumber;
	}

	public void setServnumber(String servnumber) {
		this.servnumber = servnumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldViceNumber() {
		return oldViceNumber;
	}

	public void setOldViceNumber(String oldViceNumber) {
		this.oldViceNumber = oldViceNumber;
	}
	
}
