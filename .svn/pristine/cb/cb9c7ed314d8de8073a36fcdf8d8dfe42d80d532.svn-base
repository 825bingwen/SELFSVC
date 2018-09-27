package com.gmcc.boss.selfsvc.baseService;

import com.gmcc.boss.selfsvc.common.BaseAction;

/**
 * 通用受理页面
 * 
 * @author xkf29026
 * 
 */
public class CommonServPageAction extends BaseAction
{
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 受理业务类型
    private String sType;
    
    // 当前菜单
    private String curMenuId;
    
    /**
     * 转向通用受理页面
     * 
     * @return
     */
    public String commonServPage()
    {
        // 判断业务类型是否是停开机
        if ("SHstopOpen".equals(sType))
        {
            // 设置业务类型
            setSType(sType);
            
            // 转向通用受理页面
            return "commonServPage";
        }
        else
        {
            return "commonServPage";
        }
    }
    
    public String getSType()
    {
        return sType;
    }
    
    public void setSType(String type)
    {
        sType = type;
    }

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

}
