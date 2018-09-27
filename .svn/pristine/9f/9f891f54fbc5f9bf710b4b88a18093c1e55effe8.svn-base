/*
 * 文件名：TerminalForwardAction.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.login.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * @author g00140516
 * 
 */
public class TerminalForwardAction extends BaseAction
{
    private String curMenuId = "";
    
    private String pagecount = "";
    
    public String getPagecount()
    {
        return pagecount;
    }
    
    public void setPagecount(String pagecount)
    {
        this.pagecount = pagecount;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	/**
     * 后退
     * 
     * @return
     */
    public String backForward()
    {
    	HttpSession session = this.getRequest().getSession();    	

    	String returnJsp = "back";
    	    
    	// 终端信息
    	TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    	
        List titleTotalMenus = (List) PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
        
        // 根据当前菜单获取父菜单ID
        String parentMenuID = CommonUtil.getParentMenuInfo(titleTotalMenus, curMenuId);
        if ("root".equals(parentMenuID))
        {
            NserCustomerSimp customerSimp = (NserCustomerSimp) getRequest().getSession().getAttribute(Constants.USER_INFO);
            
            // modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
            // 通过"上一页"回到首页面时，根据SH_CLEARFLAG的值判断是否需清除session中的用户信息          
            String clearFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_CLEARFLAG);
            if (null != customerSimp && !Constants.SH_CLEARFLAG_0.equalsIgnoreCase(clearFlag))
            {
                // add begin cKF48754 2011/10/27 R003C11L11n01 用户登出日志
                this.createRecLog(customerSimp.getServNumber(), Constants.BUSITYPE_LOGOUT, "0", "0", "0", "用户退出登录成功!");
                // add end cKF48754 2011/10/27 R003C11L11n01 用户登出日志
                
                //清除详单数据
                getRequest().getSession().removeAttribute(Constants.LIST_DATA_SESSION_NAME + customerSimp.getServNumber());
                
                // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                //清除账单数据
                getRequest().getSession().removeAttribute(customerSimp.getServNumber() + "_billhalfyeartrend");
                getRequest().getSession().removeAttribute(customerSimp.getServNumber() + "_billfixed");
                // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
                
                //清除用户信息
                getRequest().getSession().removeAttribute(Constants.USER_INFO);
                
            }
            // modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
        }
        //如果是话费充值缴纳功能，则再往上一层
//        else if ("feeCharge".equals(parentMenuID))
//        {
//            parentMenuID = CommonUtil.getParentMenuInfo(titleTotalMenus, parentMenuID);
//        }
        
        String url = CommonUtil.getMenuLink(parentMenuID);
        
        // 湖北热门业务推荐特殊处理
        String province = (String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if (Constants.PROOPERORGID_HUB.equals(province))
        {
            String hotspotBz = (String)this.getRequest().getSession().getAttribute(termInfo.getTermid()+"_recHotspot");
            
            if (hotspotBz != null && "1".equals(hotspotBz) )
            {
                // 父菜单
                parentMenuID = "rec";
                
                // jsp页面
                returnJsp = "funclist01";
                
                // 返回到业务办理首页
                url = "reception/receptionFunc_hub.action";
            }
        }
        
        this.getRequest().setAttribute("redirectURL", url);
        
        // 将父菜单ID置为当前菜单
        this.getRequest().setAttribute(Constants.CUR_MENUID, parentMenuID);
        
        // 返回
        return returnJsp;
    }
    
    /**
     * 换页
     * 
     * @return
     * @see
     */
    public String turnPage()
    {
        return "turnPage";
    }
}
