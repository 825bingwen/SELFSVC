package com.customize.nx.selfsvc.prodChange.model;

/**
 * 
 * 需确认的套餐信息PO
 * 
 * @author  cKF76106
 * @version  [版本号, Jun 20, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark modify begin qWX279398 2015年7月20日16:42:21 从湖北同名类中copy，findbugs修改
 */
public class TemplateFilterInfoVO
{
    // 新选择的主体产品ID
    private String newProdID;
    
    // 用户当前主体产品ID
    private String oldProdID;
    
    // 地区编号
    private String region;
    
    // 模板号
    private String templetNO;
    
    // 时间
    private String statusDate;
    
    // 状态
    private String status;
    
    public TemplateFilterInfoVO(String oldProdID, String newProdID, String region)
    {
        this.oldProdID = oldProdID;
        this.newProdID = newProdID;
        this.region = region;
    }
    
    public TemplateFilterInfoVO()
    {
        
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getNewProdID()
    {
        return newProdID;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setNewProdID(String newProdID)
    {
        this.newProdID = newProdID;
    }

    public String getOldProdID()
    {
        return oldProdID;
    }

    public void setOldProdID(String oldProdID)
    {
        this.oldProdID = oldProdID;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getTempletNO()
    {
        return templetNO;
    }

    public void setTempletNO(String templetNO)
    {
        this.templetNO = templetNO;
    }

    public String getStatusDate()
    {
        return statusDate;
    }

    public void setStatusDate(String statusDate)
    {
        this.statusDate = statusDate;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
    
}
