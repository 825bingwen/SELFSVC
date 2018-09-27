package com.customize.sd.selfsvc.packageService.model;

/**
 * 
 * 特惠业务包PO
 * @author  hWX5316476
 * @version  [版本号, Dec 22, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PrivServPackPO
{
    /**
     * NCODE
     */
    private String ncode;
    
    /**
     * 特惠业务包名称
     */
    private String privName;
    
    /**
     * 地区
     */
    private String region;
    
    /**
     * 优惠价（元）
     */
    private String privFee;
    
    /**
     * 生效类型，（0默认 2立即 3下周期 4指定时间生效）
     */
    private String effType;
    
    /**
     * 生效时间
     */
    private String effDate;
    
    /**
     * 失效时间
     */
    private String endDate;
    
    /**
     * 品牌
     */
    private String brand;
    
    /**
     * 优惠描述
     */
    private String privDesc;
    
    public String getNcode()
    {
        return ncode;
    }
    
    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }
    
    public String getPrivName()
    {
        return privName;
    }
    
    public void setPrivName(String privName)
    {
        this.privName = privName;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public void setRegion(String region)
    {
        this.region = region;
    }
    
    public String getPrivFee()
    {
        return privFee;
    }
    
    public void setPrivFee(String privFee)
    {
        this.privFee = privFee;
    }
    
    public String getEffDate()
    {
        return effDate;
    }
    
    public void setEffDate(String effDate)
    {
        this.effDate = effDate;
    }
    
    public String getEndDate()
    {
        return endDate;
    }
    
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
    public String getBrand()
    {
        return brand;
    }
    
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    
    public String getPrivDesc()
    {
        return privDesc;
    }
    
    public void setPrivDesc(String privDesc)
    {
        this.privDesc = privDesc;
    }
    
    public String getEffType()
    {
        return effType;
    }
    
    public void setEffType(String effType)
    {
        this.effType = effType;
    }
    
}
