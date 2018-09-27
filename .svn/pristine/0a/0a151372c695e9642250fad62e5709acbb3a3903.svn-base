/*
* @filename: RectelInfoAction.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  业务推荐营业员的手机号码维护类
* @author: g00140516
* @de:  2012/07/04 
* @description: 
* @remark: create g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
*/
package com.customize.nx.selfsvc.rectelinfo.action;

import javax.servlet.http.HttpServletRequest;

import com.customize.nx.selfsvc.rectelinfo.service.RectelInfoService;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.managerOperation.model.ManagerOperationPO;
import com.gmcc.boss.selfsvc.managerOperation.service.ManagerOperationService;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * 业务推荐营业员的手机号码维护类
 * 
 * @author  g00140516
 * @version  1.0, 2012/07/04
 * @see  
 * @since  
 */
public class RectelInfoAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    /**
     * 业务推荐营业员的手机号码
     */
    private String rectel = ""; 
    
    /**
     * 管理员密码
     */
    private String mgtpwd = "";
    
    /**
     * 进行管理员密码校验
     */
    private ManagerOperationService managerOperationService;
    
    /**
     * 进行业务推荐营业员的手机号码维护
     */
    private RectelInfoService rectelInfoService;
    
    /**
     * 成功提示信息
     */
    private String successMessage = "";

    /**
     * 进入管理员密码输入页面
     * 
     * @return
     * @see 
     */
    public String inputMgtPwd()
    {
        return "input";
    }
    
    /**
     * 校验管理员密码是否正确。如正确，判断终端机是否有维护业务推荐营业员的手机号码
     * 
     * @return
     * @see 
     */
    public String checkMgtpwd()
    {
        String forward = "failed";
        
        HttpServletRequest request = this.getRequest();
        
        //获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        //终端机id
        String termid = termInfo.getTermid();
        
        //封装对象
        ManagerOperationPO managerOperationPO = new ManagerOperationPO();
        managerOperationPO.setTermid(termid);
        managerOperationPO.setAuditPsw(CommonUtil.MD5Encode(mgtpwd));
        
        //校验密码。1，成功；其它，失败
        String checkStatus = managerOperationService.checkAuditPassword(managerOperationPO);
        if ("1".equals(checkStatus))
        {
            rectel = rectelInfoService.getRectelInfoWithTermid(termid);
            
            // 未维护业务推荐营业员的手机号码
            if (null == rectel || "".equals(rectel.trim()))
            {
                forward = "registerPage";
            }
            // 已维护业务推荐营业员的手机号码
            else
            {
                forward = "logoutPage";
            }
        }        
        else
        {
            //设置错误信息
            request.setAttribute("errormessage", "管理员密码验证失败，请重新输入");
            
            request.setAttribute("backStep", "-1");
        }
        
        return forward;
    }

    /**
     * 注销业务推荐营业员的手机号码
     * 
     * @return
     * @see 
     */
    public String logout()
    {
        HttpServletRequest request = this.getRequest();
        
        //获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        //终端机id
        String termid = termInfo.getTermid();
        
        rectelInfoService.deleteRectelWithTermid(termid);
        
        successMessage = "业务推荐营业员的手机号码注销成功";
        
        this.createRecLog("", Constants.SH_RECTEL_MGT, "0", "0", "0", "业务推荐营业员的手机号码注销成功(终端机：" + termid + ")");
        
        return "success";
    }
    
    /**
     * 注册业务推荐营业员的手机号码
     * 
     * @return
     * @see 
     */
    public String registerRectel()
    {
        HttpServletRequest request = this.getRequest();
        
        //获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        //终端机id
        String termid = termInfo.getTermid();
        
        rectelInfoService.insertRectelWithTermid(termid, rectel);
        
        successMessage = "业务推荐营业员的手机号码维护成功";
        
        this.createRecLog("", Constants.SH_RECTEL_MGT, "0", "0", "0", "业务推荐营业员的手机号码维护成功(终端机：" + termid + "; 手机号码：" + rectel + ")");
        
        return "success";
    }

    public String getMgtpwd()
    {
        return mgtpwd;
    }

    public void setMgtpwd(String mgtpwd)
    {
        this.mgtpwd = mgtpwd;
    }

    public ManagerOperationService getManagerOperationService()
    {
        return managerOperationService;
    }

    public void setManagerOperationService(ManagerOperationService managerOperationService)
    {
        this.managerOperationService = managerOperationService;
    }

    public RectelInfoService getRectelInfoService()
    {
        return rectelInfoService;
    }

    public void setRectelInfoService(RectelInfoService rectelInfoService)
    {
        this.rectelInfoService = rectelInfoService;
    }
    
    public String getRectel()
    {
        return rectel;
    }

    public void setRectel(String rectel)
    {
        this.rectel = rectel;
    }
    
    public String getSuccessMessage()
    {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }
}
