package com.customize.hub.selfsvc.familyNumber.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.FamilyNumberBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 实现09版神州行轻松卡亲情号码查询和设置功能 <一句话功能简述> <功能详细描述>
 * 
 * @author l00190940
 * @version [版本号, December 7, 2011]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

public class FamilyNumberAction extends BaseAction
{
    // 记录日志
    private static Log logger = LogFactory.getLog(FamilyNumberAction.class);
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单
    private String curMenuId;
    
    // 接口调用
    private FamilyNumberBean familyNumberBean;
    
    // 亲情号码1
    private String friendnum1;
    
    // 亲情号码2
    private String friendnum2;
    
    // 亲情号码3
    private String friendnum3;
    
    // 号码位置(分为：1,2,3)
    private String sn;
    
    // 要设置的亲情号码
    private String sregion;
    
    // 操作类型(固定传ADD)
    private String stype;
    
    // 成功信息
    private String successMessage;
    
    public String familyNumberPage()
    {
        // 记录日志开始
        logger.debug("query family number starting...");
        
        HttpSession session = this.getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 查询亲情号
        Map<?, ?> resultMap = getFamilyNumberBean().qryFamilyNumber(termInfo, curMenuId, customer);
        
        if (null != resultMap && 0 < resultMap.size())
        {
            CTagSet tagSet = (CTagSet)resultMap.get("returnObj");
            
            friendnum1 = tagSet.GetValue("friendnum1");
            friendnum2 = tagSet.GetValue("friendnum2");
            friendnum3 = tagSet.GetValue("friendnum3");
            
            // 记录日志
            this.createRecLog(curMenuId, "0", "0", "0", "亲情号码查询成功.");
        }
        else
        {
            this.getRequest().setAttribute("errormessage", "亲情号码查询失败！");
            
            // 记录日志
            this.createRecLog(curMenuId, "0", "0", "1", "亲情号码查询失败.");
            
            return "fail";
        }
        
        // 记录日志结束
        if (logger.isDebugEnabled())
        {
            logger.debug("query family number ending...");
        }
        
        return "familyNumberPage";
    }
    
    // 亲情号码设置页面
    public String setFamilyNumberPage()
    {
        return "setFamilyNumberPage";
    }
    
    // 设置、修改或取消亲情号码
    public String setFamilyNumber()
    {
        // 记录日志
        logger.debug("set family number starting");
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        stype = "ADD";
        
        // 调用接口设置、修改或取消亲情号码
        Map<?, ?> result = familyNumberBean.setFamilyNumber(terminalInfoPO, customer, curMenuId, stype, sn, sregion);
        
        if (null == result || 0 == result.size())
        {
            // add begin g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            this.getRequest().setAttribute("backStep", "-1");
            // add end g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面            
            
            this.getRequest().setAttribute("errormessage", "设置亲情号失败！");
            
            // 记录错误日志
            this.createRecLog(curMenuId, "0", "0", "1", "设置亲情号失败！");
            
            // 记录日志
            logger.debug("set family number ending");
            
            return "fail";
        }
        setSuccessMessage("亲情号码设置成功！");
        
        // 记录成功日志
        this.createRecLog(curMenuId, "0", "0", "0", "亲情号码设置成功.");
        
        // 记录日志
        if (logger.isDebugEnabled())
        {
            logger.debug("set family number ending");
        }
        
        return "success";
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public FamilyNumberBean getFamilyNumberBean()
    {
        return familyNumberBean;
    }
    
    public void setFamilyNumberBean(FamilyNumberBean familyNumberBean)
    {
        this.familyNumberBean = familyNumberBean;
    }
    
    public String getFriendnum1()
    {
        return friendnum1;
    }
    
    public void setFriendnum1(String friendnum1)
    {
        this.friendnum1 = friendnum1;
    }
    
    public String getFriendnum2()
    {
        return friendnum2;
    }
    
    public void setFriendnum2(String friendnum2)
    {
        this.friendnum2 = friendnum2;
    }
    
    public String getFriendnum3()
    {
        return friendnum3;
    }
    
    public void setFriendnum3(String friendnum3)
    {
        this.friendnum3 = friendnum3;
    }
    
    public String getSn()
    {
        return sn;
    }
    
    public void setSn(String sn)
    {
        this.sn = sn;
    }
    
    public String getSregion()
    {
        return sregion;
    }
    
    public void setSregion(String sregion)
    {
        this.sregion = sregion;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getStype()
    {
        return stype;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setStype(String stype)
    {
        this.stype = stype;
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
