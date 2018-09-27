package com.gmcc.boss.selfsvc.serviceinfo.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.bean.UserRegionBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 号码归属地查询
 * 
 * @author xkf29026
 * 
 */
public class UserRegionAction extends BaseAction
{
    
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    
    private static Log logger = LogFactory.getLog(UserRegionAction.class);
    
    /**
     * 当前菜单id
     */
    private String curMenuId = "";
    
    // 要查询的手机号
    private String qryServnumber;
    
    //地区名称
    private String regionName;
    
    //错误信息
//    private String error;
    
    //调用接口bean
    private UserRegionBean userRegionBean;
    
    /**
     * 号码登录页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String telNumInput()
    {
        return "telNumInput";
    }
    
    /**
     * 号码归属地查询
     * 
     * @param request
     * @return
     */
    public String queryUserRegionReq()
    {
        logger.debug("queryUserRegionReq Starting...");
        
        String qryServnuber = getQryServnumber();
        
        //定义错误指向页面
        String froward = "error";
        
        //获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        //调用接口查询号码归属地
        Map result = userRegionBean.queryUserRegionReq(terminalInfoPO, customer, qryServnuber, curMenuId);
        
        if (result != null && result.size() > 0)
        {
            String regionName = (String)result.get("returnObj");
            setRegionName(regionName);
            setQryServnumber(qryServnuber);
            froward = "userRegionList";
            
            //插入成功日志
            this.createRecLog(Constants.BUSITYPE_USERREGION, "0", "0", "0", "业务信息查询:" + qryServnuber + "号码归属地查询成功!");
        }
        else
        {
            // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            this.getRequest().setAttribute("backStep", "-1");
            // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            
            this.getRequest().setAttribute("errormessage", "号码归属地查询失败！");
            
            //插入错误日志
            this.createRecLog(Constants.BUSITYPE_USERREGION, "0", "0", "1", "业务信息查询:" + qryServnuber + "号码归属地查询失败!");
        }
        logger.debug("queryUserRegionReq End!");
        return froward;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getQryServnumber()
    {
        return qryServnumber;
    }
    
    public void setQryServnumber(String qryServnumber)
    {
        this.qryServnumber = qryServnumber;
    }
    
    public UserRegionBean getUserRegionBean()
    {
        return userRegionBean;
    }

    public void setUserRegionBean(UserRegionBean userRegionBean)
    {
        this.userRegionBean = userRegionBean;
    }

    public String getRegionName()
    {
        return regionName;
    }

    public void setRegionName(String regionName)
    {
        this.regionName = regionName;
    }

//    public String getError()
//    {
//        return error;
//    }
//
//    public void setError(String error)
//    {
//        this.error = error;
//    }
}
