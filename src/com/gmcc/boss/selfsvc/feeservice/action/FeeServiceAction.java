/*
 * 文件名：FeeServiceAction.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增，移动话费查询对应的action类
 */
package com.gmcc.boss.selfsvc.feeservice.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.PagedAction;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 移动话费查询
 * 
 * @author g00140516
 * 
 */
public class FeeServiceAction extends PagedAction
{
    private static final long serialVersionUID = 1L;

    /**
     * 当前菜单编码
     */   
    private String curMenuId = "";
    
    /**
     * 子菜单列表
     */
    private List<MenuInfoPO> menus = null;
    
    /**
     * 显示功能列表
     * 
     * @return
     * @throws Exception
     */
    public String initFunctionList() throws Exception
    {
        // add begin g00140516 2012/09/19 R003C12L09n01 OR_NX_201207_781
        HttpServletRequest request = this.getRequest();
        
        HttpSession session = request.getSession();
        
        // 终端机信息
        TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        List titleTotalMenus = (List) PublicCache.getInstance().getCachedData(terminalInfo.getTermgrpid());
        
        if (titleTotalMenus != null && titleTotalMenus.size() > 0)
        {
            menus = new ArrayList<MenuInfoPO>();
            
            MenuInfoPO menu = null;
            for (int i = 0; i < titleTotalMenus.size(); i++)
            {
                menu = (MenuInfoPO) titleTotalMenus.get(i);
                
                if (curMenuId.equalsIgnoreCase(menu.getParentid()) 
                        && (Constants.PROVINCE_REGION_999.equalsIgnoreCase(menu.getRegion()) 
                                || terminalInfo.getRegion().equalsIgnoreCase(menu.getRegion()))) 
                {
                    menus.add(menu);
                }
            }
        }
        
        //modify begin sWX219697 2015-1-14 15:43:12 OR_HUB_201408_620 添加分页
        if(Constants.PROOPERORGID_HUB.equals(CommonUtil.getParamValue("ProvinceID")))
        {
        	menus = this.getPageList(menus, 12);
        }
        // add end g00140516 2012/09/19 R003C12L09n01 OR_NX_201207_781
        
        return "funclist";
    }    
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public List<MenuInfoPO> getMenus()
    {
        return menus;
    }

    public void setMenus(List<MenuInfoPO> menus)
    {
        this.menus = menus;
    }
    
}
