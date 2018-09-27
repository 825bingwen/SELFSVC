package com.customize.nx.selfsvc.prodChange.model;

/**
 * 
 * 主体模板套餐信息PO 
 * 
 * @author  cKF76106
 * @version  [版本号, Jun 20, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 * @remark modify begin qWX279398 2015年7月20日16:42:21 从湖北同名类中copy，findbugs修改
 */
public class MainProdTempletInfoPO
{
    /**
     * 老主体产品ID
     */
    private String oldPordID;
    
    /**
     * 可转换的主体产品ID
     */
    private String newPordID;
    
    /**
     * 模版号
     */
    private String templetNO;
    
    /**
     * 地区
     */
    private String region;
    
    /**
     * 新资费名称
     */
    private String newProdName;
    
    /**
     * 品牌
     */
    private String brand;
    
    /**
     * 优惠名称
     */
    private String privName;
    
    /**
     * 通话时长
     */
    private String callDuring;
    
    /**
     * 月使用费
     */
    private String monthCost;
    
    /**
     * 包流量
     */
    private String GPRSFlux;
    
    /**
     * 被叫免费范围
     */
    private String freeCalled;
    
    /**
     * 包含数据业务
     */
    private String dataBaseService;
    
    /**
     * 超出套餐价格
     */
    private String outPackaGefee;
    
    /**
     * ncode
     */
    private String ncode;
    
    /**
     * 排序
     */
    private String sortOrder;
    
    /**
     * 备注
     */
    private String remark;

    public String getOldPordID()
    {
        return oldPordID;
    }

    public void setOldPordID(String oldPordID)
    {
        this.oldPordID = oldPordID;
    }

    public String getNewPordID()
    {
        return newPordID;
    }

    public void setNewPordID(String newPordID)
    {
        this.newPordID = newPordID;
    }

    public String getTempletNO()
    {
        return templetNO;
    }

    public void setTempletNO(String templetNO)
    {
        this.templetNO = templetNO;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getNewProdName()
    {
        return newProdName;
    }

    public void setNewProdName(String newProdName)
    {
        this.newProdName = newProdName;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getPrivName()
    {
        return privName;
    }

    public void setPrivName(String privName)
    {
        this.privName = privName;
    }

    public String getCallDuring()
    {
        return callDuring;
    }

    public void setCallDuring(String callDuring)
    {
        this.callDuring = callDuring;
    }

    public String getMonthCost()
    {
        return monthCost;
    }

    public void setMonthCost(String monthCost)
    {
        this.monthCost = monthCost;
    }

    public String getGPRSFlux()
    {
        return GPRSFlux;
    }

    public void setGPRSFlux(String gPRSFlux)
    {
        GPRSFlux = gPRSFlux;
    }

    public String getFreeCalled()
    {
        return freeCalled;
    }

    public void setFreeCalled(String freeCalled)
    {
        this.freeCalled = freeCalled;
    }

    public String getDataBaseService()
    {
        return dataBaseService;
    }

    public void setDataBaseService(String dataBaseService)
    {
        this.dataBaseService = dataBaseService;
    }

    public String getOutPackaGefee()
    {
        return outPackaGefee;
    }

    public void setOutPackaGefee(String outPackaGefee)
    {
        this.outPackaGefee = outPackaGefee;
    }

    public String getNcode()
    {
        return ncode;
    }

    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }

    public String getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}
