package com.gmcc.boss.selfsvc.baseService;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.bean.ChangePasswordBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.DESedeEncrypt;
import com.gmcc.boss.selfsvc.util.DESedeEncryptNX;
import com.huawei.boss.common.security.RSAUtil;

public class ChangePasswordAction extends BaseAction
{
    
    private static Log logger = LogFactory.getLog(ChangePasswordAction.class);
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 旧密码
    private String oldPasswd;
    
    // 新密码
    private String newPasswd;
    
    // 当前菜单
    private String curMenuId;
    
    // 成功信息
    private String successMessage;
    
    // 错误信息
    private String errormessage;
    
    // 调用接口
    private ChangePasswordBean changePasswordBean;
    
    // add begin cKF76106 20130207 NG3.3&3.5 规范符合度测试项目软件测试问题报告-修改成功后，支持提醒客户妥善保管好密码，并说明密码的重要性
    // 温馨提示
    private String additionalInfo;
    // add end cKF76106 20130207 NG3.3&3.5 规范符合度测试项目软件测试问题报告-修改成功后，支持提醒客户妥善保管好密码，并说明密码的重要性
    
    // 转向修改密码页面
    public String changePasswordPage()
    {
        return "changePasswordPage";
    }
    
    public String changePassword()
    {
        logger.debug("recChangePassword Starting...");
        
        String forward = null;
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        //modify begin g00140516 2012/03/24 R003C12L02n01 山东需对密码进行3DES加密
        // 调用接口修改密码
        Map result = null;
        
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        
        // 服务密码加密标志（宁夏），1:加密
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
                logger.error("服务密码修改时，加密密码错误：", e);
            }
            
            result = changePasswordBean.recChangePassword(terminalInfoPO, customer, oldpwd, newpwd, curMenuId);
        }
        
        // modify begin hWX5316476 2014-2-20 OR_NX_201402_306 宁夏自助终端优化需求_弱密码改造需求
        // modify begin cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
        else if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
        {
            String oldpwd = oldPasswd;
            String newpwd = newPasswd;
            
            // 如果宁夏密码加密 
            if("1".equals(encryptFlag))
            {
	            try
	            {
	                DESedeEncryptNX encrypt = DESedeEncryptNX.getInstance();
	                oldpwd = encrypt.encrypt(null == oldpwd ? "" : oldpwd);
	                newpwd = encrypt.encrypt(null == newpwd ? "" : newpwd);
	            }
	            catch (Exception e)
	            {
	                logger.error("服务密码修改时，加密密码错误：", e);
	            }
            }
            result = changePasswordBean.recChangePasswordNew(terminalInfoPO, customer, oldpwd, newpwd, curMenuId);
        }
        // modify end cKF76106 2012/10/28 R003C12L10n01 OR_NX_201209_589
        // modify end hWX5316476 2014-2-20 OR_NX_201402_306 宁夏自助终端优化需求_弱密码改造需求
        
        else
        {
            // add begin lwx439898 2018-03-05 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
            //湖北密码加密标识开启：1
            if ("1".equals(encryptFlag))
            {             
                oldPasswd = RSAUtil.decrypt(this.getRequest().getSession(), oldPasswd);
                newPasswd = RSAUtil.decrypt(this.getRequest().getSession(), newPasswd);
            }
            // add end lwx439898 2018-03-05 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
            result = changePasswordBean.recChangePassword(terminalInfoPO, customer, oldPasswd, newPasswd, curMenuId);
        }
        //modify end g00140516 2012/03/24 R003C12L02n01 山东需对密码进行3DES加密
        
        // findbugs修改 2015-09-02 zKF66389
        //if (result != null && result.size() > 2)
        if (result.size() > 2)
        // findbugs修改 2015-09-02 zKF66389
        {
            // modify begin cKF76106 20130207 NG3.3&3.5 规范符合度测试项目软件测试问题报告-修改成功后，支持提醒客户妥善保管好密码，并说明密码的重要性
            String msg = "服务密码修改成功。" + additionalInfo;
            // modify end cKF76106 20130207 NG3.3&3.5 规范符合度测试项目软件测试问题报告-修改成功后，支持提醒客户妥善保管好密码，并说明密码的重要性

            // 设置成功信息
            setSuccessMessage(msg);
            
            // 定位到成功页面
            forward = "success";
            
            // 记录成功日志
            this.createRecLog(Constants.BUSITYPE_CHGSUBSPWD, "0", "0", "0", msg);
        }
        else
        {
            String resultMsg = getConvertMsg((String) result.get("returnMsg"), "zz_04_19_000001", 
                    String.valueOf(result.get("errcode")), null);
            
            // 设置错误信息
            setErrormessage(resultMsg);
            
            // 定位到错误页面
            forward = "error";
            
            // 记录错误日志
            this.createRecLog(Constants.BUSITYPE_CHGSUBSPWD, "0", "0", "1", resultMsg);
        }
        logger.debug("recChangePassword End!");
        return forward;
        
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getOldPasswd()
    {
        return oldPasswd;
    }
    
    public void setOldPasswd(String oldPasswd)
    {
        this.oldPasswd = oldPasswd;
    }
    
    public String getNewPasswd()
    {
        return newPasswd;
    }
    
    public void setNewPasswd(String newPasswd)
    {
        this.newPasswd = newPasswd;
    }
    
    public String getSuccessMessage()
    {
        return successMessage;
    }
    
    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public ChangePasswordBean getChangePasswordBean()
    {
        return changePasswordBean;
    }
    
    public void setChangePasswordBean(ChangePasswordBean changePasswordBean)
    {
        this.changePasswordBean = changePasswordBean;
    }

    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }
}
