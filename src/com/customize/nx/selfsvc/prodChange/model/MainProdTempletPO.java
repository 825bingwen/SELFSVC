package com.customize.nx.selfsvc.prodChange.model;

/**
 * 
 * 主体产品转换的主体模板PO
 * 
 * @author  cKF76106
 * @version  [版本号, Jun 20, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark modify begin qWX279398 2015年7月20日16:42:21 从湖北同名类中copy，findbugs修改
 */
public class MainProdTempletPO
{
    /**
     * 可转换的主体产品ID
     */
    private String newPordID;
    
    /**
     * 用户当前主体产品ID
     */
    private String oldProdID;
    
    /**
     * 地区编号
     */
    private String region;
    
    /**
     * 模板号
     */
    private String templetNO;
    
    /**
     * 时间
     */
    private String statusDate;
    
    /**
     * 状态
     */
    private String status;
    
    /**
     * 可变更的主体产品
     */
    private String[] newPodArray;
    
    /**
     * 排序
     */
    private String sortOrder;
    
    /**
     * 品牌
     */
    private String brand;

    public String getNewPordID()
    {
        return newPordID;
    }

    public void setNewPordID(String newPordID)
    {
        this.newPordID = newPordID;
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

    public String[] getNewPodArray()
    {
        return newPodArray;
    }

    public void setNewPodArray(String[] newPodArray)
    {
        this.newPodArray = newPodArray;
    }

    public String getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }
}
