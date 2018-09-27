package com.gmcc.boss.selfsvc.baseService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.smsCode.model.RecordSmsCodePO;
import com.gmcc.boss.selfsvc.bean.PasswordResetBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ConstantsBase;
import com.gmcc.boss.selfsvc.login.model.LoginErrorPO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.login.service.LoginService;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DESedeEncrypt;
import com.gmcc.boss.selfsvc.util.DESedeEncryptNX;
import com.gmcc.boss.selfsvc.util.DateUtil;

/**
 * 密码重置
 * 
 * @author xkf29026
 * 
 */
public class PasswordResetAction extends BaseAction
{
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单id
    private String curMenuId = "";
    
    // 日志记录
    private static Log logger = LogFactory.getLog(PasswordResetAction.class);
    
    // 手机号码
    private String servnumber;
    
    // 随机密码
    private String randomPwd;
    
    // 新密码
    private String newPasswd;
    
    // 调用接口bean
    private PasswordResetBean passwordResetBean;
    
    private transient LoginService loginService = null;
    
    // 成功信息
    private String successMessage;
    
    // add begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
    // 初始密码登录标志
    private String initPwdLoginFlag = "";
    // add end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
    
    /**
     * 重置密码电子协议书（宁夏）
     * 
     * @return
     */
    public String resetPwdProtocol()
    {
        return "resetPwdProtocol";
    }
    
    /**
     * 验证身份证号并发送随机短信
     * 
     * @return
     */
    public String sendRandomPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("sendRandomPwd Starting ...");
        }
        
        HttpSession session = this.getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        setServnumber(customer.getServNumber());
        
        // 当前菜单不存在则设为空
        if (curMenuId == null)
        {
        	curMenuId = "";
        }
        
        // 根据手机号码、认证方式查询错误登录记录
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        
        // 被锁定
        if (loginErrorPO != null && isLocked(loginErrorPO))
        {
            String lockedTime = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGIN_LOCKEDTIME);
            
            // modify begin g00140516 2012/09/18 R003C12L09n01 湖北电子渠道二期提示信息改造
            logger.error("由于随机密码认证失败次数达到了系统限制，号码" + servnumber + "暂时不能使用密码重置功能");
                        
            String errorMsg = "由于随机密码认证失败次数达到了系统限制，您暂时不能使用密码重置功能，请" + lockedTime + "分钟后再试";
            
            errorMsg = getConvertMsg(errorMsg, "zz_02_01_000004", null, new String[]{"密码重置功能", lockedTime});
            // modify end g00140516 2012/09/18 R003C12L09n01 湖北电子渠道二期提示信息改造
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            this.createRecLog(servnumber,
                    Constants.BUSITYPE_RESETPWD,
                    "0",
                    "0",
                    "1",
                    errorMsg);
            
            return "error";
        }
        
        // 随机密码
        String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
        
        // 获取省份
        String provinceID = (String)PublicCache.getInstance().getCachedData("ProvinceID");
        
        // add begin cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
        if(Constants.PROOPERORGID_HUB.equalsIgnoreCase(provinceID))
        {
         // add begin lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
            if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_ANTISMSBOMB)))
            {
                // 判断是否符合新增的下发短信的条件
                String errorMsg = canSmsCode(servnumber);
                
                // 如果errorMsg不为空则不符合条件
                if (StringUtils.isNotBlank(errorMsg))
                {
                    logger.error(errorMsg);
                    
                    this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", errorMsg);
                    
                    this.getRequest().setAttribute("errormessage", errorMsg);
                    
                    return "error";
                }
            }
            // add end lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
            // 参数列表:12013-07-03 12:01:01#2123456
            String smsparam = "1" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "#2" + randomPwd;
            if (!passwordResetBean.sendRandomPwdHub(terminalInfoPO,
                    smsparam,
                    servnumber,
                    "Atsv_recPwdReset",
                    curMenuId))
            {
                logger.error("密码重置功能，向用户" + servnumber + "发送随机密码短信失败");
                
                this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", "密码重置功能，随机密码短信发送失败。");
                
                this.getRequest().setAttribute("errormessage", "随机密码短信发送失败，请稍后再试。");
                
                return "error";
            }
            else
            {
                if (logger.isInfoEnabled())
                {
                    logger.info("密码重置功能，向用户" + servnumber + "发送随机密码短信成功，随机码" + randomPwd);
                    // add begin lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
                    if ("true".equals(CommonUtil.getParamValue(ConstantsBase.SH_ANTISMSBOMB)))
                    {
                        // 将发送时间记录到session中
                        session.setAttribute(servnumber, new Date().getTime());
                        // 执行插入语句将信息记录到SH_SMS_HISTORY表中
                        insertSmsCode(servnumber, terminalInfoPO, randomPwd);
                    }
                    // add end lWX431760 2018-03-12 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
                }
            }
        }
        // add end cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20

        else
        {
            // 发送随机密码短信
            String shortMessage = (String)PublicCache.getInstance().getCachedData(Constants.RANDOM_PWD_CONTENT);
            shortMessage = shortMessage.replace("[%1]", randomPwd);
            
            if (!passwordResetBean.sendRandomPwd(terminalInfoPO, shortMessage, servnumber, curMenuId))
            {
                logger.error("密码重置功能，向用户" + servnumber + "发送随机密码短信失败");
                
                this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", "密码重置功能，随机密码短信发送失败。");
                
                this.getRequest().setAttribute("errormessage", "随机密码短信发送失败，请稍后再试。");
                
                return "error";
            }
            else
            {
                if (logger.isInfoEnabled())
                {
                    logger.info("密码重置功能，向用户" + servnumber + "发送随机密码短信成功，随机码" + randomPwd);
                }
            }
            
        }
        
        String forward = "randomPwdPage";
        
        // 判断省份转向不同的随机密码输入页面（湖北不需要输入新密码）
        if (Constants.PROOPERORGID_HUB.equalsIgnoreCase(provinceID))
        {
            forward = "randomPwdPageHub";
        }
        
        return forward;
    }
    
    /**
     * 使用随机密码进行密码重置
     * 
     * @return
     */
    public String resetPassword()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("resetPassword Starting...");
        }
        
        // modify begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
        HttpSession session = this.getRequest().getSession();
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        setServnumber(customer.getServNumber());
        
        // 根据手机号码、认证方式查询错误登录记录
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        // modify end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
        
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        if ("1".equals(result))
        {
            if (logger.isInfoEnabled())
            {
                logger.info("用户" + servnumber + "使用随机密码进行身份认证成功");
            }
            
            // 认证成功，删除记录
            loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
                        
            TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            if (newPasswd == null)
            {
                newPasswd = "";
            }
            
            //modify begin g00140516 2012/03/24 R003C12L02n01 山东需对密码进行3DES加密
            // 调用接口进行密码重置
            boolean resetStatus = false;
            
            // 山东，需要对密码进行3DES加密
            String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            
            // 服务密码加密标志（宁夏），1:加密
            String encryptFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PASSWORD_ENCRYPT_FLAG);
            
            if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
            {
                String newpwd = newPasswd;
                
                try
                {
                    DESedeEncrypt encrypt = new DESedeEncrypt();
                    // findbugs修改 2015-09-02 zKF66389
                    //newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
                    newpwd = encrypt.encrypt(newpwd);
                    // findbugs修改 2015-09-02 zKF66389
                }
                catch (Exception e)
                {
                    logger.error("服务密码重置时，加密密码错误：", e);
                }
                
                resetStatus = passwordResetBean.resetPassword(terminalInfoPO, servnumber, curMenuId, newpwd);
            }
            // modify begin cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
            else if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province) && "1".equals(encryptFlag))
            {
                String newpwd = newPasswd;
                
                try
                {
                    DESedeEncryptNX encrypt = DESedeEncryptNX.getInstance();
                    // findbugs修改 2015-09-02 zKF66389
                    //newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
                    newpwd = encrypt.encrypt(newpwd);
                    // findbugs修改 2015-09-02 zKF66389
                }
                catch (Exception e)
                {
                    logger.error("服务密码重置时，加密密码错误：", e);
                }
                
                resetStatus = passwordResetBean.resetPassword(terminalInfoPO, servnumber, curMenuId, newpwd);
            }
            // modify end cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
            else
            {
                resetStatus = passwordResetBean.resetPassword(terminalInfoPO, servnumber, curMenuId, newPasswd);
            }
			//modify begin g00140516 2012/03/24 R003C12L02n01 山东需对密码进行3DES加密
            
            if (resetStatus)
            {
                this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "0", "密码重置成功。");
                successMessage = "密码重置成功";
                
                // add begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
                if ("1".equals(initPwdLoginFlag))
                {
                    successMessage = " 尊敬的客户，您的服务密码已重置成功，请重新登录!";
                }
                // add end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
                
                // add begin cKF76106 2013/02/17 NG3.3&3.5 规范符合度测试项目软件测试问题报告-支持将办理结果以短信方式通知客户
                if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
                {
                    // 发送随机密码短信
                    String shortMessage = (String)PublicCache.getInstance().getCachedData("SH_PWDRESET_SUCCESS");
                    shortMessage = shortMessage.replace("[%1]", newPasswd);
                    
                    if (!passwordResetBean.sendRandomPwd(terminalInfoPO, shortMessage, servnumber, curMenuId))
                    {
                        logger.error("密码重置成功，向用户" + servnumber + "发送密码重置成功短信失败");
                        
                        this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", "密码重置成功，发送密码重置成功短信失败。");
                        
                        this.getRequest().setAttribute("errormessage", "密码重置成功，发送密码重置成功短信失败。");
                        
                        return "error";
                    }
                    else
                    {
                        if (logger.isInfoEnabled())
                        {
                            logger.info("密码重置成功，向用户" + servnumber + "发送密码重置成功短信成功");
                        }
                    }
                }
                // add end cKF76106 2013/02/17 NG3.3&3.5 规范符合度测试项目软件测试问题报告-支持将办理结果以短信方式通知客户
                
                return "success";
            }
            else
            {
                logger.error("用户" + servnumber + "进行密码重置失败");
                
                this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", "密码重置失败。");
                
                this.getRequest().setAttribute("errormessage", "密码重置失败");
                
                return "error";
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
                // modify begin g00140516 2012/09/18 R003C12L09n01 湖北电子渠道二期提示信息改造
                errorMsg = getConvertMsg("随机密码输入错误，请重新输入。", "zz_02_01_000003", null, null);
                // modify end g00140516 2012/09/18 R003C12L09n01 湖北电子渠道二期提示信息改造
            }
            
            // 认证失败
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            logger.error("用户" + servnumber + "输入的随机密码错误或超时，密码重置失败");
            
            this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            // 获取省份
            String provinceID = (String)PublicCache.getInstance().getCachedData("ProvinceID");

            if (Constants.PROOPERORGID_HUB.equalsIgnoreCase(provinceID))
            {
               return "randomPwdErrorHub";
            }
            
            return "randomPwdError";
        }        
    }
    
    /**
     * 使用随机密码进行密码重置(宁夏调用的新接口)
     * 
     * @return String
     * @remark create by hWX5316476 2014-2-20 OR_NX_201402_306 宁夏自助终端优化需求_弱密码改造需求
     */
    public String resetPasswordNewNX()
    {
        logger.debug("resetPasswordNewNX Starting...");

        HttpSession session = this.getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 设置手机号码
        setServnumber(customer.getServNumber());
        
        // 根据手机号码、认证方式查询错误登录记录
        LoginErrorPO loginErrorPO = loginService.qryErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
        
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        if ("1".equals(result))
        {
            logger.info("用户" + servnumber + "使用随机密码进行身份认证成功");
            
            // 认证成功，删除记录
            loginService.deleteErrorRecords(servnumber, Constants.AUTHTYPE_RANDOMPWD);
                        
            TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            if (newPasswd == null)
            {
                newPasswd = "";
            }
            
            // 服务密码加密标志（宁夏），1:加密
            String encryptFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PASSWORD_ENCRYPT_FLAG);

            String newpwd = newPasswd;
                
            // 如果宁夏密码加密
            if("1".equals(encryptFlag))
            {
                try
                {
                    DESedeEncryptNX encrypt = DESedeEncryptNX.getInstance();
                    // findbugs修改 2015-09-02 zKF66389
                    //newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
                    newpwd = encrypt.encrypt(newpwd);
                    // findbugs修改 2015-09-02 zKF66389
                }
                catch (Exception e)
                {
                    logger.error("服务密码重置时，加密密码错误：", e);
                }
            }
            Map returnMap = this.passwordResetBean.resetPasswordNew(terminalInfoPO, servnumber, curMenuId, newpwd);    
            
            if (null != returnMap && returnMap.size() > 2)
            {
                this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "0", "密码重置成功!");
                successMessage = "密码重置成功!";
                
                if ("1".equals(initPwdLoginFlag))
                {
                    successMessage = " 尊敬的客户，您的服务密码已重置成功，请重新登录!";
                }
               
                // 发送随机密码短信
                String shortMessage = (String)PublicCache.getInstance().getCachedData("SH_PWDRESET_SUCCESS");
                shortMessage = shortMessage.replace("[%1]", newPasswd);
                
                if (!passwordResetBean.sendRandomPwd(terminalInfoPO, shortMessage, servnumber, curMenuId))
                {
                    logger.error("密码重置成功，向用户" + servnumber + "发送密码重置成功短信失败");
                    
                    this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", "密码重置成功，发送密码重置成功短信失败。");
                    
                    this.getRequest().setAttribute("errormessage", "密码重置成功，发送密码重置成功短信失败。");
                    
                    return "error";
                }
                else
                {
                    logger.info("密码重置成功，向用户" + servnumber + "发送密码重置成功短信成功");
                }
                
                return "success";
            }
            else if (null != returnMap && returnMap.size() == 2)
            {
                logger.error("用户" + servnumber +""+(String)returnMap.get("returnMsg"));
                
                this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", "密码重置失败原因："+(String)returnMap.get("returnMsg"));
                
                this.getRequest().setAttribute("errormessage", (String)returnMap.get("returnMsg"));
                
                return "error";
            }
            else
            {
            	logger.error("用户" + servnumber +"调用密码重置接口失败");
                
                this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", "密码重置接口调用失败");
                
                this.getRequest().setAttribute("errormessage", "密码重置接口调用失败，请稍后再试!");
                
            	return "error";
            }
        }
        else 
        {
            String errorMsg = "";
            
         // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
//            if ("-1".equals(result))
//            {
//                errorMsg = "您输入的随机密码已经超时，请返回重试或者进行其它操作。";
//            }
//            else
//            {
//                errorMsg = getConvertMsg("随机密码输入错误，请重新输入。", "zz_02_01_000003", null, null);
//            }
            errorMsg = "-1".equals(result) ? "您输入的随机密码已经超时，请返回重试或者进行其它操作。" : getConvertMsg("随机密码输入错误，请重新输入。", "zz_02_01_000003", null, null);
            // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
            
            // 认证失败
            loginService.updateErrorRecords(loginErrorPO, servnumber, Constants.AUTHTYPE_RANDOMPWD);
            
            logger.error("用户" + servnumber + "输入的随机密码错误或超时，密码重置失败");
            
            this.createRecLog(servnumber, Constants.BUSITYPE_RESETPWD, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
            
            return "randomPwdError";
        }        
    }
    
    /**
     * 判断用户号码是否被锁定
     * 
     * @param loginErrorPO
     * @return
     */
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
    
    public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
    
    public PasswordResetBean getPasswordResetBean()
    {
        return passwordResetBean;
    }
    
    public void setPasswordResetBean(PasswordResetBean passwordResetBean)
    {
        this.passwordResetBean = passwordResetBean;
    }
    
    public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public LoginService getLoginService()
    {
        return loginService;
    }
    
    public void setLoginService(LoginService loginService)
    {
        this.loginService = loginService;
    }
    
    public String getRandomPwd()
    {
        return randomPwd;
    }
    
    public void setRandomPwd(String randomPwd)
    {
        this.randomPwd = randomPwd;
    }
    
    public String getNewPasswd()
    {
        return newPasswd;
    }
    
    public void setNewPasswd(String newPasswd)
    {
        this.newPasswd = newPasswd;
    }
    
    public String getInitPwdLoginFlag()
    {
        return initPwdLoginFlag;
    }
    
    public void setInitPwdLoginFlag(String initPwdLoginFlag)
    {
        this.initPwdLoginFlag = initPwdLoginFlag;
    }
}
