package com.gmcc.boss.selfsvc.serviceinfo.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.CurrentStatusBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class CurrentStatusAction extends BaseAction
{
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 当前菜单id
     */
    private String curMenuId = "";
    
    private static Log logger = LogFactory.getLog(CurrentStatusAction.class);
    
    // 调用接口bean
    private CurrentStatusBean currentStatusBean;
    
    // 当前状态
    private String currentStatus;
    
    // 错误消息
    private String errormessage;
    
    //客户信息
    private NserCustomerSimp customer;
    
    /**
     * 本机状态查询
     * 
     * @return
     */
    public String qryCurrentStatus()
    {
        // 记录日志
        logger.debug("queryCurrentStatus starting");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        setCustomer((NserCustomerSimp)session.getAttribute(Constants.USER_INFO));
        
        // 查询客户状态信息
        Map result = currentStatusBean.queryCurrentStatus(terminalInfoPO, customer, curMenuId);
        
        // 返回页面
        String forward = "";
        
        if (result != null && result.size() > 1)
        {
            CTagSet cset = (CTagSet)result.get("returnObj");
            
            // 设置当前状态
            setCurrentStatus(cset.GetValue("state"));
            
            // 记录成功日志
            this.createRecLog(Constants.BUSITYPE_CURRENTSTATUS, "0", "0", "0", "手机当前状态:当前状态查询成功!");
        
            forward = "qryCurrentStatus";
        }
        else if (result != null)
        {
        	setErrormessage("查询手机当前服务状态失败：" + result.get("returnMsg"));
            
            // 记录错误日志
            this.createRecLog(Constants.BUSITYPE_CURRENTSTATUS, "0", "0", "1", "手机当前状态:当前状态查询失败!");
            forward = "error";
        }
        else
        {
        	setErrormessage("查询手机当前服务状态异常！" );
        	forward = "error";
        }
        
        logger.debug("queryCurrentStatus end!");
        
        return forward;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public CurrentStatusBean getCurrentStatusBean()
    {
        return currentStatusBean;
    }
    
    public void setCurrentStatusBean(CurrentStatusBean currentStatusBean)
    {
        this.currentStatusBean = currentStatusBean;
    }
    
    public String getCurrentStatus()
    {
        return currentStatus;
    }
    
    public void setCurrentStatus(String currentStatus)
    {
        this.currentStatus = currentStatus;
    }
    
    public NserCustomerSimp getCustomer()
    {
        return customer;
    }

    public void setCustomer(NserCustomerSimp customer)
    {
        this.customer = customer;
    }

	public String getErrormessage() 
	{
		return errormessage;
	}

	public void setErrormessage(String errormessage) 
	{
		this.errormessage = errormessage;
	}
}
