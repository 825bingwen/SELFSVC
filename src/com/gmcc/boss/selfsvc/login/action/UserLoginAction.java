/*
 * 文件名：UserLoginAction.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.login.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.smsCode.model.RecordSmsCodePO;
import com.gmcc.boss.selfsvc.bean.UserLoginBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ConstantsBase;
import com.gmcc.boss.selfsvc.login.model.LoginErrorPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.login.service.LoginService;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DESedeEncrypt;
import com.gmcc.boss.selfsvc.util.DESedeEncryptNX;
import com.gmcc.boss.selfsvc.util.DateUtil;
import com.huawei.boss.common.security.RSAUtil;

/**
 * 用户登录
 * 
 * @author g00140516
 * 
 */
public class UserLoginAction extends BaseAction
{
    private static Log logger = LogFactory.getLog(UserLoginAction.class);
    
    private static final long serialVersionUID = -3950351941316700610L;
    
    private transient UserLoginBean userLoginBean = null;
    
    private transient LoginService loginService = null;
    
    private String servnumber = "";
    
    private String password = "";
    
    private String curMenuId = "";
    
    private String randomPwd = "";
    
    private String IDCard = "";
    
    private String oldPasswd = "";
    
    private String newPasswd = "";
    
    //add begin CKF76106 2012/09/27 R003C12L07n01 OR_HUB_201206_597    
    // 产品大类
    private String typeID= "";
    
    // 热门产品受理标志
    private String hotRecFlag = "";
    
    // 快速发布标识
    private String quickPubFlag = "";
    
    //add end CKF76106 2012/09/27 R003C12L07n01 OR_HUB_201206_597 
    
    //add begin CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
    // 标志：缴费页面登录后办理营销活动
    private String commitProductFlag = "";
    
    private String userSeq = "";
    
    // add begin create zWX176560 OR_HUB_201303_200_湖北_自助终端一体化营销功能优化
    // 业务推荐唯一流水号
    private String commendOID = "";
    // add end create zWX176560 OR_HUB_201303_200_湖北_自助终端一体化营销功能优化
    
    private String feeChargeFlag = "";
    //add end CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
    
    // add begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
    // 初始密码登录标志
    private String initPwdLoginFlag = "";
    // add end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
    
    // 停开机原因
    private String selectReason = "";
    
    /**
     * 菜单的搜索类型。2，品牌；3，首字母
     */
    private String searchType = "";
    
    // add begin m00227318 2013/02/16 R003C13L01n01 宁夏，修改，增加可选认证方式
    // 认证方式类型：optional，可选;其他，必选
    private String authCodeType = "";
    
    // 可选认证方式
    private String resultAvaiCode = "";
    // add end m00227318 2013/02/16 R003C13L01n01 宁夏，修改，增加可选认证方式
    
    // add begin lWX431760 2017/01/03 OR_HUB_201609_640_自助终端用户登录验证方式修改，增加图片验证码
    private String randomCodeImage = "";
    // add end lWX431760 2017/01/03 OR_HUB_201609_640_自助终端用户登录验证方式修改，增加图片验证码
    
    public String getRandomPwd()
    {
        return randomPwd;
    }
    
    public void setRandomPwd(String randomPwd)
    {
        this.randomPwd = randomPwd;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String card) {
        IDCard = card;
    }
        
    // add begin lWX431760 2017/01/03 OR_HUB_201609_640_自助终端用户登录验证方式修改，增加图片验证码
    public String getRandomCodeImage() {
		return randomCodeImage;
	}

	public void setRandomCodeImage(String randomCodeImage) {
		this.randomCodeImage = randomCodeImage;
	}
	 // add end lWX431760 2017/01/03 OR_HUB_201609_640_自助终端用户登录验证方式修改，增加图片验证码

	/**
     * 根据终端ID删除登录信息
     * 
     * @author lWX5316086
     * @see [类、类#方法、类#成员]
     */
    public void deleteLoginCheckByTermId()
    {
        // 获得终端机信息
        HttpSession session = this.getRequest().getSession();
        TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 删除终端ID对应的登录信息
        loginService.deleteLoginCheckByTermId(terminalInfo.getTermid());
    }
    
    /**
     * 通过手机号码、服务密码进行身份认证
     * 
     * @return
     */
    public String loginWithPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithPwd Starting ...");
        }
        
        String forward = "failed";
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        
        // 服务密码加密标志(宁夏)，1:加密
        String encryptFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PASSWORD_ENCRYPT_FLAG);

        //add begin CKF76106 2012/09/29 R003C12L08n01 OR_HUB_201207_875
        // 湖北服务密码认证
        String loginFlag = getRequest().getParameter("loginflag");
        if(Constants.PROOPERORGID_HUB.equals(province))
        {
            if("hub".equals(loginFlag))
            {
                forward = "failed_hub";
            } 
            password=RSAUtil.decrypt(this.getRequest().getSession(), password);
        }
        //add end CKF76106 2012/09/29 R003C12L08n01 OR_HUB_201207_875
        
        //add begin CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
        // 缴费支持营销推荐活动，服务密码验证页面
        if("1".equals(commitProductFlag))
        {
            forward = "goCheckPassword";
        }
        //add end CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
                
        LoginErrorPO loginErrorPO = null;
        if (!Constants.PROOPERORGID_SD.equals(province))
        {
	        loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
	        if (loginErrorPO != null && isLocked(loginErrorPO))
	        {
	            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
	            
	            // 被锁定
	            logger.error("由于服务密码连续输入错误次数达到了系统限制，号码" + servnumber + "暂时被锁定");
	            
	            if (Constants.PROOPERORGID_NX.equals(province))
	            {
	                this.getRequest().setAttribute("errormessage", "由于服务密码连续输入错误次数达到了系统限制，您的号码暂时被锁定，请" + Integer.parseInt(lockedTime)/60 + "小时后再试");
	            }
	            else
	            {
	                this.getRequest().setAttribute("errormessage", "由于服务密码连续输入错误次数达到了系统限制，您的号码暂时被锁定，请" + lockedTime + "分钟后再试");
	            }
	            
	            return forward;
	        }
        }

        if (Constants.PROOPERORGID_SD.equals(province))
        {
            //modify begin g00140516 2012/03/24 R003C12L02n01 山东需对密码进行3DES加密
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
            
            // 调用CRM统一认证接口进行认证
            Map returnMap = userLoginBean.checkPassword(termInfo, servnumber, curMenuId, newpwd);
            //modify end g00140516 2012/03/24 R003C12L02n01 山东需对密码进行3DES加密

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
		                //老手机号码及其验证方式
		                String oldServNumber = oldCustomerSimp.getServNumber();
		                String oldLoginMark = oldCustomerSimp.getLoginMark();
		
		                //清除详单数据
		                session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
		                
		                // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
		                //清除账单数据
		                getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
		                getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
		                // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中

		                //清除用户信息
		                session.removeAttribute(Constants.USER_INFO);
		                
		                //将新的用户信息存放在session中
		                session.setAttribute(Constants.USER_INFO, customerSimp);
		                
		                //设置用户的loginMark
		                if (servnumber.equals(oldServNumber))
		                {
		                    customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "1000"));
		                }
		            }
		            
		            this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "使用服务密码进行身份认证成功");
		            
		            forward = "success";
	        	}
        	}
        	// 初始密码或缺省密码-->随机密码验证-->密码修改页面
        	else if (returnMap != null && ("102".equals(String.valueOf(returnMap.get("errcode"))) || "119".equals(String.valueOf(returnMap.get("errcode")))))
        	{
                // 生成随机密码
                String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
                
                // 发送随机密码短信
                String shortMessage = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
                shortMessage = shortMessage.replace("[%1]", randomPwd);
                
                NserCustomerSimp customerSimp = new NserCustomerSimp();
                customerSimp.setServNumber(servnumber);
                
                if (!userLoginBean.sendRandomPwd(customerSimp, termInfo, shortMessage, ""))
                {
                    logger.error("向用户" + customerSimp.getServNumber() + "发送随机密码短信失败");
                    
                    this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "随机密码短信发送失败。");
                    
                    this.getRequest().setAttribute("errormessage", "随机密码短信发送失败，请稍后再试。");
                    
                    forward = "failed";
                }
                else
                {
                    if (logger.isInfoEnabled())
                    {
                        logger.info("向用户" + servnumber + "发送随机密码短信成功，随机码" + randomPwd);
                    }
                    
                    forward = "randomcode_editPwd";
                }
        	}
        	// 登录失败
        	else
        	{
	            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_SERVICEPWD);
	            
	            logger.error("使用服务密码进行身份认证失败，手机号码：" + servnumber);
	            
	            if (returnMap != null)
	            {
	            	this.getRequest().setAttribute("errormessage", StringUtils.isBlank((String)returnMap.get("returnMsg")) ? "使用服务密码进行认证失败。" : returnMap.get("returnMsg"));
	            }
	            else
	            {
	            	this.getRequest().setAttribute("errormessage", "使用服务密码进行认证失败。");
	            }
	            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "使用服务密码进行认证失败。"); 
	            
	            forward = "failed";
        	}
        }
        else
        {
            // modify begin cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
            Map customerSimpMap = null;
            if (Constants.PROOPERORGID_NX.equals(province) && "1".equals(encryptFlag))
            {
                String newpwd = password;
                try
                {
                    DESedeEncryptNX encrypt = DESedeEncryptNX.getInstance();
                    newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
                }
                catch (Exception e)
                {
                    logger.error("服务密码认证时，加密密码错误：", e);
                }
                
                customerSimpMap = userLoginBean.getUserInfoWithPwd(servnumber, newpwd, termInfo);

            }
            else
            {   
                customerSimpMap = userLoginBean.getUserInfoWithPwd(servnumber, password, termInfo);
            }
            // modify end cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
	        if (customerSimpMap.get("customerSimp") == null)
	        {
	            // 登录失败
	            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_SERVICEPWD);
	            
	            logger.error("使用服务密码进行身份认证失败，手机号码：" + servnumber);
	            
	            // modify begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
	            String resultMsg = getConvertMsg((String) customerSimpMap.get("returnMsp"), 
	                    "zz_04_01_000001", String.valueOf(customerSimpMap.get("errcode")), 
	                    null);
                
                this.getRequest().setAttribute("errormessage", resultMsg);
                
                // add begin cKF76106 2013/02/05  宁夏用户登录失败根据错误转换提示信息
                if (Constants.PROOPERORGID_NX.equals(province) && "713".equals(String.valueOf(customerSimpMap.get("errcode"))))
                {
                	// [CDATA[BUSINESSID:BegChargeFee_ZZZD,号码[15138053999]查询归属地失败]]
                	this.getRequest().setAttribute("errormessage", "此用户非宁夏移动用户，不允许进行操作");
                }
                // add end cKF76106 2013/02/05  宁夏用户登录失败根据错误转换提示信息
                else if (Constants.PROOPERORGID_NX.equals(province))
                {
                	int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));                	
                	String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
	            	
                	if ((loginErrorPO == null && 1 < maxTimes) 
                			|| (loginErrorPO != null && (loginErrorPO.getErrorTimes() + 1) < maxTimes))
                	{
                		this.getRequest().setAttribute("errormessage", "服务密码认证失败，请退出或者重新认证");
                	}
                	else
                	{
                		//this.getRequest().setAttribute("errormessage", "服务密码认证失败，由于连续输入错误次数达到了系统限制，您的号码暂时被锁定，请" + lockedTime + "分钟后再试");
                	    this.getRequest().setAttribute("errormessage", "服务密码认证失败，由于连续输入错误次数达到了系统限制，您的号码暂时被锁定，请" + Integer.parseInt(lockedTime)/60 + "小时后再试");
                	}
                }
                
	            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", resultMsg);
	            // modify end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
	        }
	        else
	        {   
	            //add begin ykf38827 2012/02/24 R003C12L02n01 OR_NX_201112_87
                if (Constants.PROOPERORGID_NX.equals(province)
                        && userLoginBean.valiIsfirstpwd(termInfo, curMenuId, servnumber))
                {
                    // modify begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
                    forward = "initPwdLogin";
                    // modify end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
                    
                }
                //add end ykf38827 2012/02/24 R003C12L02n01 OR_NX_201112_87
	         
	            else
	            { 
	                if (logger.isInfoEnabled())
	                {
	                    logger.info("用户" + servnumber + "使用服务密码进行身份认证成功");
	                }
	                
	                
	                // add begin lWX5316086 

	                // 如果同一号码同一渠道多次登录
	                if(Constants.PROOPERORGID_NX.equals(province) && 
	                        "1".equals((String)PublicCache.getInstance().getCachedData("SH_CHECKLOGIN_SWITCH")) && 
	                          !loginService.checkLoginByServNumber(servnumber,termInfo.getTermid()))
	                {
	                    // 返回错误提示
	                    this.getRequest().setAttribute("errormessage", (String)PublicCache.getInstance().getCachedData("SH_CHECKLOGIN_TIPMESSAGE"));
	                    
	                    return "failed";
	                }
               
                    NserCustomerSimp customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
                    
                    // 登录成功，删除记录
                    loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
                    
                    //modify begin g00140516 2011/10/20 R003C11L10n01 重构            
                    NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
                    if (oldCustomerSimp == null)
                    {
                        session.setAttribute(Constants.USER_INFO, customerSimp);
                    }
                    else
                    {
                        //老手机号码及其验证方式
                        String oldServNumber = oldCustomerSimp.getServNumber();
                        String oldLoginMark = oldCustomerSimp.getLoginMark();
        
                        //清除详单数据
                        session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                        
                        // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                        //清除账单数据
                        getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
                        getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
                        // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                        
                        //清除用户信息
                        session.removeAttribute(Constants.USER_INFO);
                        
                        //将新的用户信息存放在session中
                        session.setAttribute(Constants.USER_INFO, customerSimp);
                        
                        //设置用户的loginMark
                        if (servnumber.equals(oldServNumber))
                        {
                            customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "1000"));
                        }
                    }
                    //modify end g00140516 2011/10/20 R003C11L10n01 重构 
                    
                    // add begin cKF76106 2012/08/24 R003C12L08n01 OR_HUB_201206_96
                    // 清除已向用户推荐活动标志
                    session.removeAttribute(Constants.ALREADY_REC_FLAG);
                    // add end cKF76106 2012/08/24 R003C12L08n01 OR_HUB_201206_96
                    
                    this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "使用服务密码进行身份认证成功");
                    
                    //add begin CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
                    if("1".equals(commitProductFlag))
                    {
                        session.setAttribute(Constants.ALREADY_REC_FLAG, "1");
                        return "continue";
                    }
                    //add end CKF76106 2012/09/19 R003C12L08n01 OR_HUB_201206_96
                    forward = "success";  
	                
	                // add end lWX5316086 
	                
	            }
	       }
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithPwd End");
        }
        return forward;
    }
    
    /**
     * 生成并向用户发送随机密码短信
     * 
     * @return
     * @see
     */
    public String sendRandomPwd()
    {
        logger.debug("sendRandomPwd Starting ...");
        
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(customerSimp.getServNumber(),
                Constants.AUTHTYPE_RANDOMPWD);
        if (loginErrorPO != null && isLocked(loginErrorPO))
        {
            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
            
            // modify begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造           
            // 被锁定
            logger.error("由于随机密码认证失败次数达到了系统限制，号码" + customerSimp.getServNumber() + "暂时不能使用随机密码认证");
            
            String errorMsg = "";
            if (Constants.PROOPERORGID_NX.equals(province))
            {
                errorMsg = "由于随机密码认证失败次数达到了系统限制，您暂时不能使用随机密码认证，请" + lockedTime + "分钟后再试";
            }
            
            // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
            else if (Constants.PROOPERORGID_SD.equals(province))
            {
                errorMsg = "由于随机密码认证失败次数达到了系统限制，您暂时不能使用随机密码认证，请" + Integer.parseInt(lockedTime) + "分钟后再试";
            }
            else
            {
                errorMsg = "由于随机密码认证失败次数达到了系统限制，您暂时不能使用随机密码认证，请" + Integer.parseInt(lockedTime) + "小时后再试";
            }
            // modify end by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
            
            errorMsg = getConvertMsg(errorMsg, "zz_02_01_000004", null, new String[]{"随机密码认证", lockedTime});
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            // modify end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            
            return forward;
        }
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 生成随机密码
        String randomPwd = createRandomPassword(customerSimp.getServNumber(), CommonUtil.getCurrentTime());
        
        // add begin cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
        if(Constants.PROOPERORGID_HUB.equals(province) && ("qryMuster".equals(curMenuId)||"recStopOpen".equals(curMenuId)))
        {
            // add begin lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
            if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_ANTISMSBOMB)))
            {
                // 判断是否符合新增的下发短信的条件
                String errorMsg = canSmsCode(customerSimp.getServNumber());
                
                // 如果errorMsg不为空则不符合条件
                if (StringUtils.isNotBlank(errorMsg))
                {
                    logger.error(errorMsg);
                    this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", errorMsg);
                    
                    this.getRequest().setAttribute("errormessage", errorMsg);
                    return forward;
                }
            }
            // add end lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
            
            // 参数列表:12013-07-03 12:01:01#2123456
            // modify begin lwx439898 2017-03-05 R005C20LG2701 OR_HUB_201802_33 业务办理类短信优化需求
            String smsparam = "";
            if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_SMSINFO_NEWMOD, "false")))
            {
                smsparam = "1" + new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss").format(new Date()) + "#2" + randomPwd;
            }
            else
            {
                smsparam = "1" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "#2" + randomPwd;
            }
            // modify end lwx439898 2017-03-05 R005C20LG2701 OR_HUB_201802_33 业务办理类短信优化需求
            
            // 短信模板
            String templateno = "";
            
            if ("qryMuster".equals(curMenuId))
            {
                templateno = "Atsv_qryMuster";
            }
            if ("recStopOpen".equals(curMenuId))
            {
                templateno = "Atsv_recStopOpen";
            }
            
            if (!userLoginBean.sendRandomPwdHub(termInfo, smsparam, customerSimp.getServNumber(), templateno, curMenuId))
            {
                logger.error("向用户" + customerSimp.getServNumber() + "发送随机密码短信失败");
                
                this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "随机密码短信发送失败。");
                
                this.getRequest().setAttribute("errormessage", "随机密码短信发送失败，请稍后再试。");
            }
            else
            {
                logger.info("向用户" + customerSimp.getServNumber() + "发送随机密码短信成功，随机码" + randomPwd);
                
                // add begin lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
                if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_ANTISMSBOMB)))
                {
                    // 将发送时间记录到session中
                    session.setAttribute(customerSimp.getServNumber(), new Date().getTime());
                    
                    // 执行插入语句将信息记录到SH_SMS_HISTORY表中
                    insertSmsCode(customerSimp.getServNumber(), termInfo, randomPwd);
                }
                // add end lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
                
                forward = "success";
                
                return forward;
            }
        }
        // add end cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
        else
        {
			//add begin ykf38827 2012/02/28 R003C12L03n01   OR_HUB_201201_63
			//获取随机短信密码内容
			String shortMessage = getSMTemplate(randomPwd); 
			//add end ykf38827 2012/02/28 R003C12L03n01   OR_HUB_201201_63
	        
	        if (!userLoginBean.sendRandomPwd(customerSimp, termInfo, shortMessage, curMenuId))
	        {
	            logger.error("向用户" + customerSimp.getServNumber() + "发送随机密码短信失败");
	            
	            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "随机密码短信发送失败。");
	            
	            this.getRequest().setAttribute("errormessage", "随机密码短信发送失败，请稍后再试。");
	        }
	        else
	        {
	            logger.info("向用户" + customerSimp.getServNumber() + "发送随机密码短信成功，随机码" + randomPwd);
	            logger.info("短信内容：" + shortMessage);
	            
	            forward = "success";
	        }
        }
        
        logger.debug("sendRandomPwd End");
        
        return forward;
    }
    
    /**
     * 根据菜单ID查询配置的短信模板
     * <功能详细描述>
     * @param randomPwd 随机密码
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create YKF38827 2012/02/28 R003C12L03n01   OR_HUB_201201_63
     */
    public String getSMTemplate(String randomPwd)
    {
        String  shortMsg = "";
        
        List<DictItemPO> itemList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.CDR_SMTEMPLATE);
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        
        //湖北详单查询另取模板
        if (null != itemList && Constants.PROOPERORGID_HUB.equals(province) && itemList.size() > 0)
        {                     
            for (int i=0; i<itemList.size(); i++)
            {
                DictItemPO cip = (DictItemPO)itemList.get(i);
                //如果存在配置的短信模板，获取该短信模板
                if (cip.getDictid().equals(curMenuId))
                {
                    shortMsg = (String)PublicCache.getInstance().getCachedData(cip.getDictname());
                    shortMsg = shortMsg.replace("%TIME", DateUtilHub.getStringDate());  
                    shortMsg = shortMsg.replace("%RANDOMPWD", randomPwd);
                    break;
                }
            }
            if ("".equals(shortMsg) || 0 == shortMsg.length())
            {
            	shortMsg = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
                shortMsg = shortMsg.replace("[%1]", randomPwd); 
            }
        }
        else //获取默认的短信模板s
        {
            shortMsg = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
            shortMsg = shortMsg.replace("[%1]", randomPwd); 
        }
        
        return shortMsg;
    }
    /**
     * 使用随机密码进行认证
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String loginWithRandomPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithRandomPwd Starting ...");
        }
        
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(customerSimp.getServNumber(),
                Constants.AUTHTYPE_RANDOMPWD);
        
        String result = this.loginWithRandomPwd(customerSimp.getServNumber(), randomPwd);
        if ("1".equals(result))
        {
            if (logger.isInfoEnabled())
            {
                logger.info("用户" + customerSimp.getServNumber() + "使用随机密码进行身份认证成功");
            }
            
            // 认证成功，删除记录
            loginService.deleteErrorRecords(customerSimp.getServNumber(), Constants.AUTHTYPE_RANDOMPWD);
            
            customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(customerSimp.getLoginMark(), "0100"));
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "0", "随机密码验证成功。");
            
            forward = "success";
            
            //判断是否为停开机业务，如果是停开机业务则办理成功后应转到业务办理成功页面
            if (getRequest().getAttribute(Constants.CUR_MENUID).equals("recStopOpen"))
            {
                  try
                {
                    this.getResponse().sendRedirect(this.getRequest().getContextPath()+"/baseService/recOpenAndStopSubs.action?actionCase=SHstopOpen&curMenuId=recStopOpen" + "&selectReason=" + selectReason);
                }
                catch (IOException e)
                {
                    return "error";
                }
            }           
        }
        else 
        {
            String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "您输入的随机密码已经超时，请返回重试或者进行其它操作。";
            }
            else
            {
                errorMsg = getConvertMsg("随机密码输入错误，请重新输入。", "zz_02_01_000003", null, null);
            }
            
            // 认证失败
            loginService.updateErrorRecords(loginErrorPO,customerSimp.getServNumber(),Constants.AUTHTYPE_RANDOMPWD);
            
            logger.error("用户" + customerSimp.getServNumber() + "输入的随机密码错误或超时，身份认证失败");
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithRandomPwd End");
        }
        
        return forward;
    }
    
    /**
     * 通过手机号码、身份证号进行认证
     * 
     * @return
     */
    public String loginWithID()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithID Starting ...");
        }
        
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        //modify begin g00140516 2011/10/20 R003C11L10n01 重构 
        Map customerSimpMap = userLoginBean.checkIDCard(termInfo, IDCard, servnumber, curMenuId);
        
        NserCustomerSimp newCustomerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
        if (newCustomerSimp != null)
        {
            if (logger.isInfoEnabled())
            {
                logger.info("用户" + servnumber + "使用身份证号进行认证成功");
            }            
            
            NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);            
            if (customerSimp == null)
            {
                session.setAttribute(Constants.USER_INFO, newCustomerSimp);
            }
            else
            {
                //老手机号码及其验证方式
                String oldServNumber = customerSimp.getServNumber();
                String oldLoginMark = customerSimp.getLoginMark();
                
                //清除详单数据
                session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                
                // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                //清除账单数据
                getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
                getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
                // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                
                //清除用户信息
                session.removeAttribute(Constants.USER_INFO);
                
                session.setAttribute(Constants.USER_INFO, newCustomerSimp);
                
                //同一个号码
                if (servnumber.equals(oldServNumber))
                {
                    newCustomerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "0010"));
                }
            }            
            
            this.createRecLog(Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "0", "使用身份证号进行认证成功");
            
            forward = "success";          
        }
        else
        {
            logger.error("使用身份证号进行认证失败，手机号码：" + servnumber + ";身份证号：" + IDCard);
            
            // modify begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
            String resultMsg = getConvertMsg((String) customerSimpMap.get("returnMsp"), 
                    "zz_04_01_000002", String.valueOf(customerSimpMap.get("errcode")), null);
            
            String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_NX.equals(province))
            {
            	resultMsg = "身份证号认证失败，请退出或重新认证";
            }
            
            this.getRequest().setAttribute("errormessage", resultMsg);
            
            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", resultMsg);
            // modify end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
        }
        //modify end g00140516 2011/10/20 R003C11L10n01 重构 
        
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithID End");
        }
        
        return forward;
    }
    
    /**
     * 生成并向用户发送随机密码短信_重庆
     * 
     * @return
     * @see
     */
    public String randomcode_cq()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("randomcode_cq Starting ...");
        }
        
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 生成随机密码
        String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
        
        // 发送随机密码短信
        String shortMessage = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
        shortMessage = shortMessage.replace("[%1]", randomPwd);
        
        // 未登录情况下发送短信随即密码_必须存在手机号码
        NserCustomerSimp customerSimp = new NserCustomerSimp();
        customerSimp.setServNumber(servnumber);
        
        // 发送随机密码短信失败
        Map resultMap = userLoginBean.sendRandomPwdByMap(customerSimp, termInfo, shortMessage, curMenuId);
        if (!"".equals((String)resultMap.get("returnMsp")))
        {
            logger.error("向用户" + customerSimp.getServNumber() + "发送随机密码短信失败,原因："+(String)resultMap.get("returnMsp"));
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "随机密码短信发送失败。");
            
            this.getRequest().setAttribute("errormessage", (String)resultMap.get("returnMsp"));
            
        }
        // 发送随机密码短信成功
        else
        {
            if (logger.isInfoEnabled())
            {
                logger.info("向用户" + customerSimp.getServNumber() + "发送随机密码短信成功，随机码" + randomPwd);
            }
            forward = "success";
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("randomcode_cq End");
        }
        
        return forward;
    }
    
    /**
     * 通过随机密码进行认证_重庆（无密码登录）
     * 
     * @return
     */
    public String loginWithRandom_cq()
    {
        logger.debug("loginWithPwd Starting ...");
        
        String forward = "failed";
        
        // 查询号码是否锁定
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        if (loginErrorPO != null && isLocked(loginErrorPO))
        {
            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
            
            // 被锁定
            logger.error("由于随机密码连续输入错误次数达到了系统限制，号码" + servnumber + "暂时被锁定");
            
            this.getRequest().setAttribute("errormessage", "由于随机密码连续输入错误次数达到了系统限制，您的号码暂时被锁定，请" + lockedTime + "分钟后再试");
            
            return forward;
        }
        
        // 校验随机密码
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        
        // 随机密码校验失败
        if (!"1".equals(result))
        {
        	String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "您输入的随机密码已经超时，请返回重试或者进行其它操作。";
            }
            else
            {
                errorMsg = "随机密码输入错误，请重新输入。";
            }
            
            // 认证失败
            loginService.updateErrorRecords(loginErrorPO,servnumber,Constants.AUTHTYPE_RANDOMPWD);
            
            logger.error("用户" + servnumber + "输入的随机密码错误或超时，身份认证失败");
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            return forward;
        }
        
        logger.info("用户" + servnumber + "使用随机密码进行身份认证成功");

        // 进行调用接口登录
        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        Map customerSimpMap = userLoginBean.getUserInfo(servnumber, termInfo);
        if (customerSimpMap.get("customerSimp") == null)
        {
            // 登录失败
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_SERVICEPWD);
            
            logger.error("使用使用随机密码进行身份认证失败，手机号码：" + servnumber + ";服务密码：" + password);
            
            this.getRequest().setAttribute("errormessage", customerSimpMap.get("returnMsp"));
            
            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "使用服务密码进行认证失败。");
        }
        else
        {
            logger.info("用户" + servnumber + "使用服务密码进行身份认证成功");
            
            NserCustomerSimp customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
            
            // 登录成功，删除记录
            loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
                       
            NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
            if (oldCustomerSimp == null)
            {
                session.setAttribute(Constants.USER_INFO, customerSimp);
            }
            else
            {
                //老手机号码及其验证方式
                String oldServNumber = oldCustomerSimp.getServNumber();
                String oldLoginMark = oldCustomerSimp.getLoginMark();

                //清除详单数据
                session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                
                // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                //清除账单数据
                getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
                getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
                // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                
                //清除用户信息
                session.removeAttribute(Constants.USER_INFO);
                
                //将新的用户信息存放在session中
                session.setAttribute(Constants.USER_INFO, customerSimp);
                
                //设置用户的loginMark
                if (servnumber.equals(oldServNumber))
                {
                    customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "1000"));
                }
            }
            this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "使用无密码进行身份认证成功");
            
            forward = "success";
        }
        
        logger.debug("loginWithPwd End");
        
        return "success";
    }
    
    private boolean isLocked(LoginErrorPO loginErrorPO)
    {
        int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));
        
        // 错误次数达到了系统限制
        if (loginErrorPO.getErrorTimes() >= maxTimes)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String lastTime = loginErrorPO.getLastTime();
            
            try
            {
                // 锁定时长
                String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
                
                Calendar c = Calendar.getInstance();
                
                // 当前时间
                String currTime = sdf.format(c.getTime());
                
                // 解锁时间
                c.setTime(sdf.parse(lastTime));
                c.add(Calendar.MINUTE, Integer.parseInt(lockedTime));
                
                String unlockedTime = sdf.format(c.getTime());
                
                // 解锁时间要晚于当前时间，即服务号码在锁定期间内
                if (unlockedTime.compareTo(currTime) > 0)
                {
                    return true;
                }
                
                return false;
            }
            catch (ParseException e)
            {
                logger.error("判断号码是否被锁定时发生异常：", e);
            }
        }
        
        // 错误次数尚未达到系统限制
        return false;
    }
    
    /**
     * 
     * 初始密码或服务密码登录之后随机密码认证
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String editPasswordByRandomPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("editPasswordByRandomPwd Starting ...");
        }
        
        String forward = "failed";
        
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        if ("1".equals(result))
        {
            if (logger.isInfoEnabled())
            {
                logger.info("用户" + servnumber + "使用随机密码认证成功");
            }
            forward = "success";
        }
        else 
        {
            String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "您输入的随机密码已经超时，请返回重试或者进行其它操作。";
            }
            else
            {
                errorMsg = getConvertMsg("随机密码输入错误，请重新输入。", "zz_02_01_000003", null, null);
            }
            
            logger.error("用户" + servnumber + "输入的随机密码错误或超时，身份认证失败");
            
            this.getRequest().setAttribute("errormessage", errorMsg);
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("editPasswordByRandomPwd End");
        }
        
        return forward;
    }
    
    /**
     * 
     * 初始密码或服务密码登录之后修改密码
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String editPassword()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("editPassword Starting ...");
        }
        
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);

    	//modify begin g00140516 2012/03/24 R003C12L02n01 山东需对密码进行3DES加密
    	// 修改密码        
    	Map returnMap = null;
    	
    	String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
    	
    	 // 服务密码加密标志(宁夏)，1:加密
        String encryptFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PASSWORD_ENCRYPT_FLAG);
        
        if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
        {
            String oldpwd = oldPasswd;
            String newpwd = newPasswd;
            
            try
            {
                DESedeEncrypt encrypt = new DESedeEncrypt();
                oldpwd = encrypt.encrypt(null == oldpwd ? "" : oldpwd);
                newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
            }
            catch (Exception e)
            {
                logger.error("初始密码登录后修改服务密码时，加密密码错误：", e);
            }
            
            returnMap = userLoginBean.editPassword(termInfo, servnumber, curMenuId, oldpwd, newpwd);
        }
        // modify begin cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
        else if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province) && "1".equals(encryptFlag))
        {
            String oldpwd = oldPasswd;
            String newpwd = newPasswd;
            
            try
            {
                DESedeEncryptNX encrypt = DESedeEncryptNX.getInstance();
                oldpwd = encrypt.encrypt(null == oldpwd ? "" : oldpwd);
                newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
            }
            catch (Exception e)
            {
                logger.error("初始密码登录后修改服务密码时，加密密码错误：", e);
            }
            
            returnMap = userLoginBean.editPassword(termInfo, servnumber, curMenuId, oldpwd, newpwd);
        }
        // modify end cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
        else
        {
            returnMap = userLoginBean.editPassword(termInfo, servnumber, curMenuId, oldPasswd, newPasswd);
        }
        //modify end g00140516 2012/03/24 R003C12L02n01 山东需对密码进行3DES加密

    	// 成功 
        // begin zKF66389 2015-09-15 9月份findbugs修改
    	//if (returnMap != null && "1".equals(String.valueOf(returnMap.get("status"))))
    	if ("1".equals(String.valueOf(returnMap.get("status"))))
    	{
            if (logger.isInfoEnabled())
            {
                logger.info("用户" + servnumber + "修改密码成功!");
            }
            
            this.getRequest().setAttribute("errormessage", "尊敬的客户，您的服务密码已修改成功，请重新登录!");
            
            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "修改密码成功。"); 
            
            forward = "success";
            
    	}
    	// 失败
    	else 
    	{
            logger.error("手机号码：" + servnumber + "修改密码失败，原因：" + returnMap.get("returnMsg"));
            
            this.getRequest().setAttribute("errormessage", returnMap.get("returnMsg"));
            
            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "修改密码失败。"); 
            
            forward = "failed";
    	}
        
        if (logger.isDebugEnabled())
        {
            logger.debug("editPassword End");
        }
        
        return forward;
    }
    
    /**
     * 生成并向用户发送短信验证码(湖北)
     * 
     * @return
     * @see
     */
    public void sendRandomPwd_hub() throws IOException
    {
        // 头信息
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        this.getResponse().setContentType("text/html;charset=GBK");
        PrintWriter out = this.getResponse().getWriter();
        
        HttpSession session = this.getRequest().getSession();
        
        // add begin lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
        if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_ANTISMSBOMB)))
        {
            // 判断是否符合新增的下发短信的条件
            String errorMsg = canSmsCode(servnumber);
            
            // 如果errorMsg不为空则不符合条件
            if (StringUtils.isNotBlank(errorMsg))
            {
                logger.error(errorMsg);
                
                out.write(errorMsg);
                out.flush();
                return;
            }
        }
        // add end lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
        
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        if (loginErrorPO != null && isLocked(loginErrorPO))
        {
            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
            
            // 被锁定
            logger.error("由于短信验证码认证失败次数达到了系统限制，号码" + servnumber + "暂时不能使用短信验证码认证");
            
            String errorMsg = "由于短信验证码认证失败次数达到了系统限制，您暂时不能使用短信验证码认证，请" + lockedTime + "分钟后再试";
            
            // 提示信息转换
            String[] params = new String[] {"短信验证码认证", lockedTime};
            errorMsg = super.getConvertMsg(errorMsg, "zz_02_01_000004", "", params);
            
            out.write(errorMsg);
            out.flush();
            return;
        }
        
        // 终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 生成短信验证码
        String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
        
        // 获取短信验证码短信内容
        String shortMessage = getSMTemplate(randomPwd);
        
        // 未登录情况下发送短信验证码
        NserCustomerSimp customerSimp = new NserCustomerSimp();
        customerSimp.setServNumber(servnumber);
        
        //获取当前省份
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if (Constants.PROOPERORGID_HUB.equals(province))
        {
            // modify begin lwx439898 2017-03-05 R005C20LG2701 OR_HUB_201802_33 业务办理类短信优化需求
            String smsparam = "";
            if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_SMSINFO_NEWMOD, "false")))
            {
                smsparam = "1" + new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss").format(new Date()) + "#2" + randomPwd;
            }
            else
            {
                smsparam = "1" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "#2" + randomPwd;
            }
            // modify end lwx439898 2017-03-05 R005C20LG2701 OR_HUB_201802_33 业务办理类短信优化需求
            
            String templateno = "Atsv_RandomPwd";
            if (!userLoginBean.sendRandomPwdHub(termInfo, smsparam, customerSimp.getServNumber(), templateno, curMenuId))
            {
                logger.error("向用户" + servnumber + "发送短信验证码失败");
                
                this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "短信验证码发送失败。");
                
                out.write("短信验证码发送失败，请稍后再试。");
                out.flush();
	            return;
	        }
	        else
	        {
	            if (logger.isInfoEnabled())
	            {
	                logger.info("向用户" + customerSimp.getServNumber() + "发送短信验证码成功，短信验证码" + randomPwd);
	                logger.info("短信内容：" + shortMessage);
	            }
	            
	            // add begin lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
	            if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_ANTISMSBOMB)))
	            {
	                // 将发送时间记录到session中
	                session.setAttribute(servnumber, new Date().getTime());
	                
	                // 执行插入语句将信息记录到SH_SMS_HISTORY表中
                    insertSmsCode(servnumber, termInfo, randomPwd);
	            }
	            // add end lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
	        }
        }
        else
        {
	        if (!userLoginBean.sendRandomPwd(customerSimp, termInfo, shortMessage, curMenuId))
	        {
	            logger.error("向用户" + servnumber + "发送短信验证码失败");
	            
	            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "短信验证码发送失败。");
	            
	            out.write("短信验证码发送失败，请稍后再试。");
	            out.flush();
	            return;
	        }
	        else
	        {
	            if (logger.isInfoEnabled())
	            {
	                logger.info("向用户" + customerSimp.getServNumber() + "发送短信验证码成功，短信验证码" + randomPwd);
	                logger.info("短信内容：" + shortMessage);
	            }
	        }
        }
        out.write("");
        out.flush();
        
    }
    
    /**
     * 使用短信验证码登录(湖北)
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String loginWithRandomPwd_hub()
    {
        logger.debug("loginWithRandomPwd Starting ...");
        
        String forward = "failed";
        
        // 查询号码是否锁定
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        if (loginErrorPO != null && isLocked(loginErrorPO))
        {
            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
            
            // 被锁定
            logger.error("由于短信验证码认证失败次数达到了系统限制，号码" + servnumber + "暂时不能使用短信验证码认证");
            
            String errorMsg = "由于短信验证码认证失败次数达到了系统限制，您暂时不能使用短信验证码认证，请" + lockedTime + "分钟后再试";
            
            // 提示信息转换
            String[] params = new String[] {"短信验证码认证", lockedTime};
            errorMsg = super.getConvertMsg(errorMsg, "zz_02_01_000004", "", params);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            return forward;
        }
        
        // 校验短信验证码
        String result = this.loginWithRandomPwd(servnumber, password);
        
        // 短信验证码校验失败
        if (!"1".equals(result))
        {
            String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "您输入的短信验证码已经超时，请返回重试或者进行其它操作。";
            }
            else
            {
                errorMsg = "短信验证码输入错误，请重新输入。";
                // 提示信息转换
                errorMsg = super.getConvertMsg(errorMsg, "zz_02_01_000003", "", null);
            }
            
            // 认证失败
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            logger.error("用户" + servnumber + "输入的短信验证码错误或超时，身份认证失败");
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            return forward;
        }
        
        logger.info("用户" + servnumber + "使用短信验证码进行身份认证成功");
        
        // 进行调用接口登录
        HttpSession session = this.getRequest().getSession();
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        Map customerSimpMap = userLoginBean.getUserInfo(servnumber, termInfo);
        if (customerSimpMap.get("customerSimp") == null)
        {
            // 登录失败
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_SERVICEPWD);
            
            logger.error("使用无密码进行身份认证失败，手机号码：" + servnumber);
            
            String errorMsg = (String)customerSimpMap.get("returnMsp");
            
            // 提示信息转换
            String errCode = String.valueOf(customerSimpMap.get("errcode"));
            
            errorMsg = super.getConvertMsg(errorMsg, "zz_04_01_000005", errCode, null);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            this.createRecLog(servnumber, Constants.BUSITYPE_SUBSVERIFYID, "0", "0", "1", "使用无密码进行身份认证失败。");
        }
        else
        {
            logger.info("用户" + servnumber + "使用无密码进行身份认证成功");
            
            NserCustomerSimp customerSimp = (NserCustomerSimp)customerSimpMap.get("customerSimp");
            
            // 登录成功，删除记录
            loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_SERVICEPWD);
            
            NserCustomerSimp oldCustomerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            if (oldCustomerSimp == null)
            {
                session.setAttribute(Constants.USER_INFO, customerSimp);
            }
            else
            {
                // 老手机号码及其验证方式
                String oldServNumber = oldCustomerSimp.getServNumber();
                String oldLoginMark = oldCustomerSimp.getLoginMark();
                
                // 清除详单数据
                session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                
                // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                //清除账单数据
                getRequest().getSession().removeAttribute(oldServNumber + "_billhalfyeartrend");
                getRequest().getSession().removeAttribute(oldServNumber + "_billfixed");
                // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                
                // 清除用户信息
                session.removeAttribute(Constants.USER_INFO);
                
                // 将新的用户信息存放在session中
                session.setAttribute(Constants.USER_INFO, customerSimp);
                
                // 设置用户的loginMark
                if (servnumber.equals(oldServNumber))
                {
                    customerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "1000"));
                }
            }
            this.createRecLog(Constants.BUSITYPE_SUBSVERIFYPWD, "0", "0", "0", "使用无密码进行身份认证成功");
            
            forward = "success";
        }
        
        logger.debug("loginWithRandomPwd End");
        
        return forward;
    }
    
    /**
     * 初始密码登录，选择密码修改，发送短信密码
     * 
     * @return
     * @see 
	 * @remark create cKF76106 2012/12/15  V200R003C12L12 OR_NX_201211_746
     */ 
    public String sendRandomCode()
    {
        String forward = "failed";
        
        // 生成随机密码
        String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
        // 发送随机密码短信
        String shortMessage = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
        shortMessage = shortMessage.replace("[%1]", randomPwd);
        
        NserCustomerSimp customerSimp = new NserCustomerSimp();
        customerSimp.setServNumber(servnumber);
        
        HttpSession session = this.getRequest().getSession();
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        if (!userLoginBean.sendRandomPwd(customerSimp, termInfo, shortMessage, ""))
        {
            logger.error("向用户" + customerSimp.getServNumber() + "发送随机密码短信失败");
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", "随机密码短信发送失败。");
            
            this.getRequest().setAttribute("errormessage", "随机密码短信发送失败，请稍后再试。");
        }
        else
        {
            if (logger.isInfoEnabled())
            {
                logger.info("向用户" + servnumber + "发送随机密码短信成功，随机码" + randomPwd);
            }
            
            forward = "randomcode_editPwd";
        }
        
        return forward;
    }
    
    /**
     * 初始密码登录，选择密码重置，转向身份证认证页面
     * 
     * @return
     * @see
	 * @remark create cKF76106 2012/12/15  V200R003C12L12 OR_NX_201211_746
     */ 
    public String goIdCardPage()
    {
        this.getRequest().setAttribute("initPwdLoginFlag", "1");
        
        return "idCardPage";
    }
    
    public String goRamdomPage_hub()
    {
        return "goRamdomPage";
    }
    
    public String goServicePwdPage_hub()
    {
        return "goServicePwdPage";
    }
    
    /**
     * 可选认证方式，使用随机密码进行认证
     * 
     * @return
     * @see 
     */
    public String loginWithRandomPwdNew()
    {
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        
        String result = this.loginWithRandomPwd(servnumber, password);
        if ("1".equals(result))
        {
            if (logger.isInfoEnabled())
            {
                logger.info("用户" + servnumber + "使用随机密码进行身份认证成功");
            }
            
            // 认证成功，删除记录
            loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // 用户信息
            NserCustomerSimp customerSimp = new NserCustomerSimp();
            
            // 取客户信息
            if (Constants.PROOPERORGID_SD.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
            {
                Map customerSimpMap = userLoginBean.getUserInfo(servnumber, termInfo);
                if (customerSimpMap.get("customerSimp") != null)
                {
                    customerSimp = (NserCustomerSimp) customerSimpMap.get("customerSimp");
                }
            }
            
            customerSimp.setServNumber(servnumber);
            customerSimp.setLoginMark("0100");
            
            // session中存放的用户信息
            NserCustomerSimp oldCustomerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
            if (oldCustomerSimp == null)
            {
                session.setAttribute(Constants.USER_INFO, customerSimp);
            }
            else
            {
            	//老手机号码及其验证方式
                String oldServNumber = oldCustomerSimp.getServNumber();
                String oldLoginMark = oldCustomerSimp.getLoginMark();

                //清除详单数据
                session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + oldServNumber);
                
                // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                //清除账单数据
                session.removeAttribute(oldServNumber + "_billhalfyeartrend");
                session.removeAttribute(oldServNumber + "_billfixed");
                // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中

                if (!servnumber.equals(oldCustomerSimp.getServNumber()))
                {
                	//清除用户信息
                    session.removeAttribute(Constants.USER_INFO);
                    
                    //将新的用户信息存放在session中
                    session.setAttribute(Constants.USER_INFO, customerSimp);
                }
                else
                {
                	oldCustomerSimp.setLoginMark(CommonUtil.getCurrentAuthCode(oldLoginMark, "0100"));   
                }
            }
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "0", "随机密码验证成功。");
            
            forward = "success";
        }
        // 认证失败
        else 
        {
        	// modify begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        	// 获取认证失败后的错误信息
        	String errorMsg = getErrMsg(loginErrorPO, result);
        	// modify end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
            
            // 认证失败
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            logger.error("用户" + servnumber + "输入的随机密码错误或超时，身份认证失败");
            
            this.createRecLog(Constants.BUSITYPE_VALIDATERESULT, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
        }
        
        return forward;
    }
    
    /**
     * 获取认证失败后的错误信息
     * @param loginErrorPO
     * @param result
     * @return 错误信息
     * @remark create by jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
     */
	private String getErrMsg(LoginErrorPO loginErrorPO, String result)
	{
		String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
		String errorMsg = "";
		
		if ("-1".equals(result))
		{
		    errorMsg = "您输入的随机密码已经超时，请返回重试或者进行其它操作。";
		    
		    if (Constants.PROOPERORGID_NX.equals(province))
		    {
		    	int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));                	
		    	String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
		    	
		    	if ((null == loginErrorPO && 1 < maxTimes) 
		    			|| (null != loginErrorPO  && (loginErrorPO.getErrorTimes() + 1) < maxTimes))
		    	{
		    		errorMsg = "您输入的随机密码已经超时，请退出或者重新认证";
		    	}
		    	else
		    	{
		    		errorMsg = "您输入的随机密码已经超时，由于随机密码认证失败次数达到了系统限制，您的号码暂时被锁定，请" + Integer.parseInt(lockedTime)/60 + "小时后再试";
		    	}
		    }
		}
		else
		{
		    errorMsg = getConvertMsg("随机密码输入错误，请重新输入。", "zz_02_01_000003", null, null);
		    
		    if (Constants.PROOPERORGID_NX.equals(province))
		    {
		    	int maxTimes = Integer.parseInt((String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_MAXTIMES));                	
		    	String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
		    	
		    	if ((null == loginErrorPO  && 1 < maxTimes) 
		    			|| (null != loginErrorPO  && (loginErrorPO.getErrorTimes() + 1) < maxTimes))
		    	{
		    		errorMsg = "随机密码输入错误，请退出或者重新认证";
		    	}
		    	else
		    	{
		    		errorMsg = "随机密码输入错误，由于随机密码认证失败次数达到了系统限制，您的号码暂时被锁定，请" + Integer.parseInt(lockedTime)/60 + "小时后再试";
		    	}
		    }
		}
		return errorMsg;
	}
    
    /**
     * 可选认证方式，短信验证码认证页面
     * @return
     * @remark create m00227318 2013/02/07 R003C13L01n01 OR_NX_201302_600
     */
    public String goRandomPwdPage()
    {
    	return "randomPwdPage";
    }
    
    /**
     * 可选认证方式，服务密码认证页面
     * @return
     * @remark create m00227318 2013/02/07 R003C13L01n01 OR_NX_201302_600
     */
    public String goServicePwdPage()
    {
    	return "servicePwdPage";
    }
    
    /**
     * 可选认证方式，身份认证页面
     * @return
     * @remark create m00227318 2013/02/07 R003C13L01n01 OR_NX_201302_600
     */
    public String goIDPage()
    {
    	return "idCardPage";
    }
    
    
    /**
     * 检验用户是否在黑名单中(宁夏)
     * @create zKF77391
     * @throws Exception
     */
    public void checkBlackList() throws Exception
    {
        logger.debug("开始查询用户[" + servnumber + "]是否黑名单中");
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        try
        {
            boolean isBlack = loginService.checkBlackListByServNumber(servnumber);
            if (isBlack)
            {
                logger.debug("用户[" + servnumber + "]在黑名单中");
                out.write("yes");
            }
            else
            {
                logger.debug("用户[" + servnumber + "]不在黑名单中");
                out.write("no");
            }
            
            createRecLog(servnumber, "SHCheckBlackList", "0", "0", "0", "查询用户[" + servnumber + "]是否在黑名单中成功");
        }
        catch (Exception e)
        {
            logger.error("查询黑名单名单出错:" + e);
            createRecLog(servnumber, "SHCheckBlackList", "0", "0", "1", "查询用户[" + servnumber + "]是否在黑名单中失败");
            out.write("yes");
        }
        finally
        {
            out.flush();
            out.close();
        }
        logger.debug("结束查询用户[" + servnumber + "]是否黑名单中");
        
    }
	
	    
    /**
     * 是否实名制信息登记(宁夏)
     * @create zKF77391
     * @throws Exception
     */
    public void realNameCheck() throws Exception
    {
        HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        try
        {
            // 是否实名制信息登记
            
            // 1 ---已实名制登记 0 ---未登记
            String prompt = "";
            String ret = this.userLoginBean.realNameCheck(servnumber, (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO));
            if ("0".equals(ret))
            {
                prompt = (String)PublicCache.getInstance().getCachedData("SH_REALNAME_PROMPT");
                if (prompt == null)
                {
                    prompt = "此号码具有非实名特征，为确保客户权益，中国移动宁夏公司提醒您到专业营业厅进行实名登记。";
                }
            }
            
            out.write(ret + "^" + prompt);
        }
        catch (Exception e)
        {
            logger.error("查询是否实名制信息登记出错:" + e);
            out.write("2");
        }
        finally
        {
            out.flush();
            out.close();
        }
    }
    
    /**
     * (宁夏)是否是弱密码检验
     * 
     * @author hWX5316476
     * @remark create by hWX5316476 2014-2-19  OR_NX_201402_306 宁夏自助终端优化需求_弱密码改造需求
     */
    public void weakPwdCheckLogin()throws IOException
    {
    	HttpServletRequest request = this.getRequest();
        request.setCharacterEncoding("GBK");
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();

        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        try
        {
            String prompt = "";
            
            // 调用接口检验是否是弱密码
            Map retmap = this.userLoginBean.weakPwdCheck(termInfo,servnumber,password,curMenuId);

			if(null != retmap && retmap.size() > 2 )
			{
				if("2".equals((String)retmap.get("remindflag")))
				{
					prompt = "尊敬的客户：您设置的密码比较简单，为了保障您个人信息的安全，建议您重新修改为更加复杂的密码！";
	            	
	            	// 获取弱密码提示信息(宁夏)
	            	String pro = (String)PublicCache.getInstance().getCachedData(Constants.SH_WEAKPWD_PROMPT);
	
	            	// 如果配置了弱密码提示信息，则展示配置的弱密码提示信息
	            	if(null != pro && !"".equals(pro) )
	            	{
	            		prompt = pro;
	            	} 
	            	out.write("2^" + prompt);
				}
				else
				{
					out.write("1^" + prompt);
				}
			}
            else if (null != retmap && retmap.size() == 2)
            {
            	// 将调用接口的错误返回信息返回页面
            	prompt = (String)retmap.get("returnMsg");
            	
            	out.write("0^" + prompt);
            }
            else
            {
            	out.write("0^查询是否是弱密码出错!请稍后重试!");
            }
        }
        catch (Exception e)
        {
            logger.error("查询是否弱密码出错:" + e);
            out.write("1");
        }
        finally
        {
            out.flush();
            out.close();
        }
    }

    /**
     * 查询升级页面
     * @remark create by hWX5316476 2014-12-5 OR_HUB_201408_628_湖北_新增自助终端上线提醒页面
     */
    public void qrySysUpdateTipPage()
    {
        // 是否跳转系统升级提示页 0：不跳转 1：跳转
        String sysUpdatePage = "0";
        
        // 获取系统升级开关 （0：关闭  1：开启）
        String sysUpdateSwitch = loginService.qryParamValueById("SH_SYS_UPDATE_SWITCH");
        
        // 没有设置，默认关闭
        if(StringUtils.isEmpty(sysUpdateSwitch))
        {
            sysUpdateSwitch = "0";
        }

        NserCustomerSimp customerSimp = (NserCustomerSimp)this.getRequest().getSession().getAttribute(Constants.USER_INFO);
        
        // 当系统升级开关开启，并且没有用户登录的时候，可跳转系统升级提示页
        if("1".equals(sysUpdateSwitch) && null == customerSimp)
        {
            sysUpdatePage = "1";
        }
        
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        
        try
        {
            response.getWriter().write(sysUpdatePage);
        }
        catch (Exception e)
        {
            logger.error("查询系统升级开关失败",e);
        }
    }
    
    /**
     * 查询系统升级开关
     * @remark create by hWX5316476 2014-12-5 OR_HUB_201408_628_湖北_新增自助终端上线提醒页面
     */
    public void qrySysUpdateSwitch()
    {
        // 获取系统升级开关 （0：关闭  1：开启）
        String sysUpdateSwitch = loginService.qryParamValueById("SH_SYS_UPDATE_SWITCH");
        
        // 没有设置，默认关闭
        if(StringUtils.isEmpty(sysUpdateSwitch))
        {
            sysUpdateSwitch = "0";
        }
        
        HttpServletResponse response = this.getResponse();
        response.setContentType("text/html;charset=GBK");
        
        try
        {
            response.getWriter().write(sysUpdateSwitch);
        }
        catch (Exception e)
        {
            logger.error("查询系统升级开关失败",e);
        }
    }
    

	/**
	 * 校检图片验证码(湖北)
	 * @return 
	 * @see [类、类#方法、类#成员]
	 * @remark create by lWX431760 2017-1-5 OR_HUB_201609_640 自助终端用户登录验证方式
	 */
	public void randomCodeSecurity()
	{
		try
		{
			String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
			if (Constants.PROOPERORGID_HUB.equals(province)) 
			{
				HttpSession session = this.getRequest().getSession();
								
				String validateResult = this.userLoginBean.validteRandomCode(randomCodeImage,
						(String)session.getAttribute("randomCode"));
				
				session.removeAttribute("randomCode");
				
				writeXmlMsg(validateResult);	
			}
		}
		catch(Exception e)
		{
			writeXmlMsg("");
		}
	}
	
    /**
     * 跳转提示页面
     * @return
     * @remark create by hWX5316476 2014-12-10 OR_HUB_201408_628_湖北_新增自助终端上线提醒页面
     */
    public String goSysUpdateTipPage()
    {
        return "sysUpdateTipPage";
    }
    
    public UserLoginBean getUserLoginBean()
    {
        return userLoginBean;
    }
    
    public void setUserLoginBean(UserLoginBean userLoginBean)
    {
        this.userLoginBean = userLoginBean;
    }
    
    public LoginService getLoginService()
    {
        return loginService;
    }
    
    public void setLoginService(LoginService loginService)
    {
        this.loginService = loginService;
    }

	public String getOldPasswd() {
		return oldPasswd;
	}

	public void setOldPasswd(String oldPasswd) {
		this.oldPasswd = oldPasswd;
	}

	public String getNewPasswd() {
		return newPasswd;
	}

	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

    public String getCommitProductFlag()
    {
        return commitProductFlag;
    }

    public String getUserSeq()
    {
        return userSeq;
    }

    public void setCommitProductFlag(String commitProductFlag)
    {
        this.commitProductFlag = commitProductFlag;
    }

    public void setUserSeq(String userSeq)
    {
        this.userSeq = userSeq;
    }

    public String getFeeChargeFlag()
    {
        return feeChargeFlag;
    }

    public void setFeeChargeFlag(String feeChargeFlag)
    {
        this.feeChargeFlag = feeChargeFlag;
    }

    public String getTypeID()
    {
        return typeID;
    }

    public void setTypeID(String typeID)
    {
        this.typeID = typeID;
    }

    public String getHotRecFlag()
    {
        return hotRecFlag;
    }

    public void setHotRecFlag(String hotRecFlag)
    {
        this.hotRecFlag = hotRecFlag;
    }

    public String getQuickPubFlag()
    {
        return quickPubFlag;
    }

    public void setQuickPubFlag(String quickPubFlag)
    {
        this.quickPubFlag = quickPubFlag;
    }
    
    public String getSearchType()
    {
        return searchType;
    }

    public void setSearchType(String searchType)
    {
        this.searchType = searchType;
    }

    public String getInitPwdLoginFlag()
    {
        return initPwdLoginFlag;
    }

    public void setInitPwdLoginFlag(String initPwdLoginFlag)
    {
        this.initPwdLoginFlag = initPwdLoginFlag;
    }

    public String getSelectReason()
    {
        return selectReason;
    }

    public void setSelectReason(String selectReason)
    {
        this.selectReason = selectReason;
    }

	public String getAuthCodeType()
	{
		return authCodeType;
	}

	public void setAuthCodeType(String authCodeType)
	{
		this.authCodeType = authCodeType;
	}

	public String getResultAvaiCode()
	{
		return resultAvaiCode;
	}

	public void setResultAvaiCode(String resultAvaiCode)
	{
		this.resultAvaiCode = resultAvaiCode;
	}

    public String getCommendOID()
    {
        return commendOID;
    }

    public void setCommendOID(String commendOID)
    {
        this.commendOID = commendOID;
    }
}
