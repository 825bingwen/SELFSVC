/*
 * 文 件 名:  AcrTheYearActFuncAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  跨年营销活动获取子菜单类
 * 修 改 人:  jWX216858
 * 修改时间:  2014-11-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.gmcc.boss.selfsvc.reception.action;

import java.util.ArrayList;
import java.util.List;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;

/**
 * 跨年营销活动获取子菜单类
 * 
 * @author  jWX216858
 * @version  [版本号, 2014-11-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ReceptionFuncActionSD extends BaseAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 当前菜单
	 */
	private String curMenuId;
	
	/**
     * 子菜单列表
     */
    private List<MenuInfoPO> menus = null;
    
    /**
     * 子菜单样式
     */
    private String pageStyle;
    
    /** 
     * 显示子菜单信息
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String nextLevelFuncList()
    {
    	// 获取当前终端机组下的菜单
    	List<MenuInfoPO> titleTotalMenus = (List<MenuInfoPO>) PublicCache.getInstance().getCachedData(getTerminalInfoPO().getTermgrpid());
    	
    	// 遍历titleTotalMenus，获取跨年终端营销活动子菜单
    	if (null != titleTotalMenus && titleTotalMenus.size() > 0)
    	{
    		menus = new ArrayList<MenuInfoPO>();
    		
    		MenuInfoPO menu = null;
    		for (int i = 0; i < titleTotalMenus.size(); i++)
    		{
    			menu = titleTotalMenus.get(i);
    			
    			if (curMenuId.equalsIgnoreCase(menu.getParentid()) 
    					&&(Constants.PROVINCE_REGION_999.equalsIgnoreCase(menu.getRegion())
    							|| getTerminalInfoPO().getRegion().equalsIgnoreCase(menu.getRegion())))
    			{
    				menus.add(menu);
    			}
    		}
    	}
    	return "funcList";
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public List<MenuInfoPO> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuInfoPO> menus) {
		this.menus = menus;
	}

	public String getPageStyle() {
		return pageStyle;
	}

	public void setPageStyle(String pageStyle) {
		this.pageStyle = pageStyle;
	}
    
}
