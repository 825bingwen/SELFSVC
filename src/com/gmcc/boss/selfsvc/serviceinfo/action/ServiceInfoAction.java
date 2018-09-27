package com.gmcc.boss.selfsvc.serviceinfo.action;

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
 * ������Ϣ��ѯ
 * @author xkf29026
 *
 */
public class ServiceInfoAction extends PagedAction
{

    /**
     * ���к�
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * ��ǰ�˵�����
     */   
    private String curMenuId = "";
    
    /**
    * �Ӳ˵��б�
    */
    private List<MenuInfoPO> menus = null;
    
    /**
     * ��ʾ�����б�
     * @return
     */
    public String initFunctionList()
    {
        
        // add begin cKF76106 2012/11/02 V200R003C12L11 OR_NX_201210_1335
        HttpServletRequest request = this.getRequest();
        
        HttpSession session = request.getSession();
        
        // �ն˻���Ϣ
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
        // add begin cKF76106 2012/11/02 V200R003C12L11 OR_NX_201210_1335
        
        //modify begin sWX219697 2015-1-14 15:43:12 OR_HUB_201408_620 ��ӷ�ҳ
        if(Constants.PROOPERORGID_HUB.equals(CommonUtil.getParamValue("ProvinceID")))
        {
        	menus = this.getPageList(menus, 12);
        }
        
        return "funclist";
    }

    public List<MenuInfoPO> getMenus()
    {
        return menus;
    }

    public void setMenus(List<MenuInfoPO> menus)
    {
        this.menus = menus;
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}
}
