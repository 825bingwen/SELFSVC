/**
 * 
 */
package com.customize.hub.selfsvc.prodChange.model;

/**
 * @author HW
 * 
 */
public class TemplateFilterInfoVO
{
    
    // 用户当前主体产品ID
    private String oldProdID;
    
    // 新选择的主体产品ID
    private String newProdID;
    
    // 模板号
    private String templetNO;
    
    // 地区编号
    private String region;
    
    // 状态
    private String status;
    
    // 时间
    private String statusDate;
    
    public TemplateFilterInfoVO()
    {
        
    }
    
    public TemplateFilterInfoVO(String oldProdID, String newProdID, String region)
    {
        this.oldProdID = oldProdID;
        this.newProdID = newProdID;
        this.region = region;
    }
    
    /**
     * @return the oldProdID
     */
    public String getOldProdID()
    {
        return oldProdID;
    }
    
    /**
     * @param oldProdID the oldProdID to set
     */
    public void setOldProdID(String oldProdID)
    {
        this.oldProdID = oldProdID;
    }
    
    /**
     * @return the newProdID
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getNewProdID()
    {
        return newProdID;
    }
    
    /**
     * @param newProdID the newProdID to set
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setNewProdID(String newProdID)
    {
        this.newProdID = newProdID;
    }
    
    /**
     * @return the templetNO
     */
    public String getTempletNO()
    {
        return templetNO;
    }
    
    /**
     * @param templetNO the templetNO to set
     */
    public void setTempletNO(String templetNO)
    {
        this.templetNO = templetNO;
    }
    
    /**
     * @return the region
     */
    public String getRegion()
    {
        return region;
    }
    
    /**
     * @param region the region to set
     */
    public void setRegion(String region)
    {
        this.region = region;
    }
    
    /**
     * @return the status
     */
    public String getStatus()
    {
        return status;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    /**
     * @return the statusDate
     */
    public String getStatusDate()
    {
        return statusDate;
    }
    
    /**
     * @param statusDate the statusDate to set
     */
    public void setStatusDate(String statusDate)
    {
        this.statusDate = statusDate;
    }
    
}
