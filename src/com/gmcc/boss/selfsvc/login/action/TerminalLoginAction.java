/*
 * 文件名：TerminalLoginAction.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.login.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.customize.sd.selfsvc.charge.model.MorePhoneVO;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.login.service.LoginService;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 终端机访问系统平台
 * 
 * @author g00140516
 * 
 */
public class TerminalLoginAction extends BaseAction
{
    //置于主页中间位置的菜单
    private List centerMenus = null;
    
    //置于主页右上位置的菜单
    private List topRightMenus = null;
    
    //置于主页右下位置的菜单
    private List bottomRightMenus = null;
    
    // add begin wWX217192 2015-01-06 OR_HUB_201408_620 自助终端界面改版优化
    // 湖北置于主页右侧的菜单
    private List rightMenus = null;
    // add end wWX217192 2015-01-06 OR_HUB_201408_620 自助终端界面改版优化
    
    // add begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
    /**
     * session超时标志
     */
    private String timeoutFlag = "";
    // add end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920

    /**
     * service
     */
    private transient LoginService loginService;
    
    public List getTopRightMenus()
    {
        return topRightMenus;
    }

    public void setTopRightMenus(List topRightMenus)
    {
        this.topRightMenus = topRightMenus;
    }

    public List getBottomRightMenus()
    {
        return bottomRightMenus;
    }

    public void setBottomRightMenus(List bottomRightMenus)
    {
        this.bottomRightMenus = bottomRightMenus;
    }

    /**
     * 初始化框架页面
     */
    public String login() throws Exception
    {
        return "frame";
    }
    
    /**
     * 索引页
     * 
     * @return
     * @throws Exception
     * @remark modify by hWX5316476 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
     */
    public String index() throws Exception
    {
        if(Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
        {
            String alreadyRecFlag = (String)this.getRequest().getSession().getAttribute(Constants.ALREADY_REC_FLAG);
            
            if(null != alreadyRecFlag)
            {
                // 清除session中的是否已经推荐标识
                this.getRequest().getSession().removeAttribute(Constants.ALREADY_REC_FLAG);
            }
        }
        
        return "index";
    }   
    
    /**
     * 首页
     * 
     * @return
     * @throws Exception
     * @see 
     */
    @SuppressWarnings("unchecked")
	public String goHomePage() throws Exception 
    {
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);

        // modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
        /*
         * 山东：在session超时及用户主动点击退出的情况下，清空session中的数据
         * 其它省份：直接清空session中的数据
         */
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        String clearFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_CLEARFLAG);
        if (customerSimp != null && ("1".equalsIgnoreCase(timeoutFlag) 
                || !Constants.SH_CLEARFLAG_0.equalsIgnoreCase(clearFlag)))
        {
            // add begin cKF48754 2011/11/01 R003C11L11n01 OR_huawei_201111_148
            this.createRecLog(customerSimp.getServNumber(), Constants.BUSITYPE_LOGOUT, "0", "0", "0", "用户退出登录成功!");
            // add end cKF48754 2011/11/01 R003C11L11n01 OR_huawei_201111_148
            
            //清除详单数据
            session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + customerSimp.getServNumber());
            
            // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
            //清除账单数据
            getRequest().getSession().removeAttribute(customerSimp.getServNumber() + "_billhalfyeartrend");
            getRequest().getSession().removeAttribute(customerSimp.getServNumber() + "_billfixed");
            // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
            
            //清除用户信息
            session.removeAttribute(Constants.USER_INFO);
        }
        // modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
        
        //终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        //根据终端组自缓存中获取菜单列表
        String groupID = terminalInfo.getTermgrpid();
        
        List<MenuInfoPO> menus = null;
        
        // add begin jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段） 
        if (StringUtils.isNotEmpty(groupID))
        {                    
            menus = (List<MenuInfoPO>) PublicCache.getInstance().getCachedData(groupID);
        }
        // add end jWX216858 2014/08/06 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        
        centerMenus = new ArrayList();
        topRightMenus = new ArrayList();
        bottomRightMenus = new ArrayList();
        rightMenus = new ArrayList();
        
        //modify begin g00140516 2012/01/07 R003C11L12n01 bug 18636
//        if (menus != null && menus.size() > 0)
//        {
//            MenuInfoPO menu = null;
//            
//            for (int i = 0; i < menus.size(); i++)
//            {
//                menu = (MenuInfoPO) menus.get(i);
//
//                if ("C".equalsIgnoreCase(menu.getPosition()) 
//                        && Constants.MENU_CENTER_MAX > centerMenus.size())
//                {
//                    centerMenus.add(menu);
//                }
//                else if ("TR".equalsIgnoreCase(menu.getPosition())
//                        && Constants.MENU_TOPRIGNT_MAX > topRightMenus.size())
//                {
//                    topRightMenus.add(menu);
//                }
//                else if ("BR".equalsIgnoreCase(menu.getPosition())
//                        && Constants.MENU_BOTTOMRIGHT_MAX > bottomRightMenus.size())
//                {
//                    bottomRightMenus.add(menu);
//                }              
//            }
//        }
        this.addMenus(menus);
        //modify end g00140516 2012/01/07 R003C11L12n01 bug 18636
        
        // add begin YKF70747 2012/04/08 R003C12L04n01 OR_SD_201203_818        
        if (terminalInfo.isSuppKey())
        {            
            return "keyHome";
        }
        // add end YKF70747 2012/04/08 R003C12L04n01 OR_SD_201203_818
        
        // add begin hWX5316476 2014-12-8 OR_HUB_201408_628_湖北_新增自助终端上线提醒页面
        
        // 是否周期检测系统升级(0:否 1：是)
        String isChk = (String) PublicCache.getInstance().getCachedData("SH_ISCHK_SYSUPDATE");
        
        if("1".equals(isChk))
        {
            // 获取系统升级开关 （0：关闭  1：开启）
            String sysUpdateSwitch = loginService.qryParamValueById("SH_SYS_UPDATE_SWITCH");
            
            if ("1".equals(sysUpdateSwitch))
            {            
                return "sysUpdateTipPage";
            }
        }
        // add end hWX5316476 2014-12-8 OR_HUB_201408_628_湖北_新增自助终端上线提醒页面
        
        // add begin 2015-2-10 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
        String alreadyRecFlag = (String)session.getAttribute(Constants.ALREADY_REC_FLAG);
        
        if(null != alreadyRecFlag)
        {
            // 清除session中的是否已经推荐
            session.removeAttribute(Constants.ALREADY_REC_FLAG);
        }
        //  add end 2015-2-10 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
        
        return "home";
    }
    
    /**
     * 添加menu列表
     * @param menus
     */
    private void addMenus(List<MenuInfoPO> menus)
    {
        if (menus != null && menus.size() > 0)
        {
            MenuInfoPO menu = null;
            
            for (int i = 0; i < menus.size(); i++)
            {
                menu = (MenuInfoPO) menus.get(i);
                
                // modify begin wWX217192 2015-01-06 OR_HUB_201408_620 自助终端界面改版优化
                // 由于湖北自助终端页面改版，首页中间部分显示6个菜单，右侧部分显示6个菜单
                if(Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
                {
                	if("C".equalsIgnoreCase(menu.getPosition())
                			&& Constants.CENTER_MENU_MAX_HUB > centerMenus.size())
                	{
                		centerMenus.add(menu);
                	}
                	// 由于湖北原有逻辑中右侧包含顶部及底部两部分，所以判断逻辑修改为包含“R”即可
                	else if("TR".equalsIgnoreCase(menu.getPosition())
                			&& Constants.RIGHT_MENU_MAX_HUB > rightMenus.size())
                	{
                		rightMenus.add(menu);
                	}
                	else if("BR".equalsIgnoreCase(menu.getPosition())
                			&& Constants.RIGHT_MENU_MAX_HUB > rightMenus.size())
                	{
                		rightMenus.add(menu);
                	}
                }
                else
                {
                	if ("C".equalsIgnoreCase(menu.getPosition()) 
                			&& Constants.MENU_CENTER_MAX > centerMenus.size())
                	{
                		centerMenus.add(menu);
                	}
                	else if ("TR".equalsIgnoreCase(menu.getPosition())
                			&& Constants.MENU_TOPRIGNT_MAX > topRightMenus.size())
                	{
                		topRightMenus.add(menu);
                	}
                	else if ("BR".equalsIgnoreCase(menu.getPosition())
                			&& Constants.MENU_BOTTOMRIGHT_MAX > bottomRightMenus.size())
                	{
                		bottomRightMenus.add(menu);
                	}              
                }
                // modify end wWX217192 2015-01-06 OR_HUB_201408_620 自助终端界面改版优化
            }
        }
    }
    
    /**
     * 清除session中的数据
     * 
     * @see 
     */
    public void clearSession()
    {
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        // modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
        /*
         * 在页面超时的情况下，清空session中的数据
         * 即便页面未超时，如果不是山东，也应清空session中的数据
         */
        String clearFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_CLEARFLAG);
        if (null != customerSimp && ("1".equalsIgnoreCase(timeoutFlag) 
                || !Constants.SH_CLEARFLAG_0.equalsIgnoreCase(clearFlag)))
        {
            // add begin cKF48754 2011/11/01 R003C11L11n01 OR_huawei_201111_148
            this.createRecLog(customerSimp.getServNumber(), Constants.BUSITYPE_LOGOUT, "0", "0", "0", "用户退出登录成功!");
            // add end cKF48754 2011/11/01 R003C11L11n01 OR_huawei_201111_148
            
            //清除详单数据
            session.removeAttribute(Constants.LIST_DATA_SESSION_NAME + customerSimp.getServNumber());
            
            // add begin g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
            //清除账单数据
            getRequest().getSession().removeAttribute(customerSimp.getServNumber() + "_billhalfyeartrend");
            getRequest().getSession().removeAttribute(customerSimp.getServNumber() + "_billfixed");
            // add end g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 月账单查询为生成消费趋势图和费用结构图而将部分账单信息存到session中
            
            //清除用户信息
            session.removeAttribute(Constants.USER_INFO);
            
        }
        // modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
        
        // add begin 2015-2-10 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
        String alreadyRecFlag = (String)session.getAttribute(Constants.ALREADY_REC_FLAG);
        
        if(null != alreadyRecFlag)
        {
            // 清除session中的是否已经推荐
            session.removeAttribute(Constants.ALREADY_REC_FLAG);
        }
        //  add end 2015-2-10 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
        
    }
    
    public List getCenterMenus()
    {
        return centerMenus;
    }

    public void setCenterMenus(List centerMenus)
    {
        this.centerMenus = centerMenus;
    }
    
    public String getTimeoutFlag()
    {
        return timeoutFlag;
    }

    public void setTimeoutFlag(String timeoutFlag)
    {
        this.timeoutFlag = timeoutFlag;
    }

    public LoginService getLoginService()
    {
        return loginService;
    }

    public void setLoginService(LoginService loginService)
    {
        this.loginService = loginService;
    }

	public List getRightMenus() {
		return rightMenus;
	}

	public void setRightMenus(List rightMenus) {
		this.rightMenus = rightMenus;
	}
    
}