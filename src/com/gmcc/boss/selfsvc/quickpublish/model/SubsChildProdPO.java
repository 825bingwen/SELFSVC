package com.gmcc.boss.selfsvc.quickpublish.model;

/**
 * 
 * 产品包下子产品PO,字段为接口协议返回参数 <功能详细描述>
 * 
 * @author cKF76106
 * @version [版本号, Jul 9, 2012]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SubsChildProdPO
{
    // 产品包编码
    private String pkgid;
    
    // 增值产品编码
    private String prodid;
    
    // 优惠编码
    private String privid;
    
    // 产品名称
    private String prodname;
    
    // 选择类型 0：可选 1：必选
    private String ismandatory;
    
    // 是否有附加属性 0：无 1：有
    private String hasattr;
    
    // 接口业务类型 PCIntRelaNormal：普通业务 PCIntRelaRadio：业务切换
    private String inftype;

    public String getPkgid()
    {
        return pkgid;
    }

    public void setPkgid(String pkgid)
    {
        this.pkgid = pkgid;
    }

    public String getProdid()
    {
        return prodid;
    }

    public void setProdid(String prodid)
    {
        this.prodid = prodid;
    }

    public String getPrivid()
    {
        return privid;
    }

    public void setPrivid(String privid)
    {
        this.privid = privid;
    }

    public String getProdname()
    {
        return prodname;
    }

    public void setProdname(String prodname)
    {
        this.prodname = prodname;
    }

    public String getIsmandatory()
    {
        return ismandatory;
    }

    public void setIsmandatory(String ismandatory)
    {
        this.ismandatory = ismandatory;
    }

    public String getHasattr()
    {
        return hasattr;
    }

    public void setHasattr(String hasattr)
    {
        this.hasattr = hasattr;
    }

    public String getInftype()
    {
        return inftype;
    }

    public void setInftype(String inftype)
    {
        this.inftype = inftype;
    }

}
