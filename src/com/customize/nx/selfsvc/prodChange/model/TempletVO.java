/**
 * 
 */
package com.customize.nx.selfsvc.prodChange.model;

/**
 * @author Administrator
 * 
 */
/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  qWX279398
 * @version  [版本号, 2015-7-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark modify begin qWX279398 2015年7月20日16:42:21 从湖北同名类中copy，findbugs修改
 */
public class TempletVO
{
    // 模板名称
    private String templetName;
    
    // 模板编码
    private String templetID;
    
    public TempletVO(String templetID, String templetName)
    {
        this.templetID = templetID;
        this.templetName = templetName;
    }
    
    public TempletVO()
    {
        
    }

    public String getTempletName()
    {
        return templetName;
    }

    public void setTempletName(String templetName)
    {
        this.templetName = templetName;
    }

    public String getTempletID()
    {
        return templetID;
    }

    public void setTempletID(String templetID)
    {
        this.templetID = templetID;
    }
}
