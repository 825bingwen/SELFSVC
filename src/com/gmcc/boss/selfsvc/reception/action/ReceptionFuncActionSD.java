/*
 * �� �� ��:  AcrTheYearActFuncAction.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  ����Ӫ�����ȡ�Ӳ˵���
 * �� �� ��:  jWX216858
 * �޸�ʱ��:  2014-11-27
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.gmcc.boss.selfsvc.reception.action;

import java.util.ArrayList;
import java.util.List;

import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;

/**
 * ����Ӫ�����ȡ�Ӳ˵���
 * 
 * @author  jWX216858
 * @version  [�汾��, 2014-11-27]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ReceptionFuncActionSD extends BaseAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ��ǰ�˵�
	 */
	private String curMenuId;
	
	/**
     * �Ӳ˵��б�
     */
    private List<MenuInfoPO> menus = null;
    
    /**
     * �Ӳ˵���ʽ
     */
    private String pageStyle;
    
    /** 
     * ��ʾ�Ӳ˵���Ϣ
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     */
    public String nextLevelFuncList()
    {
    	// ��ȡ��ǰ�ն˻����µĲ˵�
    	List<MenuInfoPO> titleTotalMenus = (List<MenuInfoPO>) PublicCache.getInstance().getCachedData(getTerminalInfoPO().getTermgrpid());
    	
    	// ����titleTotalMenus����ȡ�����ն�Ӫ����Ӳ˵�
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
