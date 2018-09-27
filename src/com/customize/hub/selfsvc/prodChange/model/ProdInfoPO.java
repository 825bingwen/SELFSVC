/*
 * 文 件 名:  ProdInfoPO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人: yKF70747
 * 修改时间:  Apr 17, 2012
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.prodChange.model;

/**
 * 需确认的套餐信息PO
 * 
 * @author  yKF70747
 * @version  [版本号, Apr 17, 2012]
 * @see 
 * @since 
 */
public class ProdInfoPO
{
    

    /**
     * 类型
     */
    private String type;
    
    /**
     * 产品编码
     */
    private String prodid;
    
    /**
     * 产品名称
     */
    private String prodname;
    
    /**
     * 产品开始时间
     */
    private String begindate;
    
    /**
     * 产品结束时间
     */
    private String enddate;
    
    /**
     * 产品包名称
     */
    private String pkgname;
    
    /**
     * 优惠编码
     */
    private String privid;
    
    /**
     * 优惠名称
     */
    private String privname;
    
    /**
     * 优惠开始时间
     */
    private String privbegindate;
    
    /**
     * 优惠结束时间
     */
    private String privenddate;
    
    /**
     * 取消原因
     */
    private String reason;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getProdid()
    {
        return prodid;
    }

    public void setProdid(String prodid)
    {
        this.prodid = prodid;
    }

    public String getProdname()
    {
        return prodname;
    }

    public void setProdname(String prodname)
    {
        this.prodname = prodname;
    }

    public String getBegindate()
    {
        return begindate;
    }

    public void setBegindate(String begindate)
    {
        this.begindate = begindate;
    }

    public String getEnddate()
    {
        return enddate;
    }

    public void setEnddate(String enddate)
    {
        this.enddate = enddate;
    }

    public String getPkgname()
    {
        return pkgname;
    }

    public void setPkgname(String pkgname)
    {
        this.pkgname = pkgname;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPrivid()
    {
        return privid;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPrivid(String privid)
    {
        this.privid = privid;
    }

    public String getPrivname()
    {
        return privname;
    }

    public void setPrivname(String privname)
    {
        this.privname = privname;
    }

    public String getPrivbegindate()
    {
        return privbegindate;
    }

    public void setPrivbegindate(String privbegindate)
    {
        this.privbegindate = privbegindate;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPrivenddate()
    {
        return privenddate;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPrivenddate(String privenddate)
    {
        this.privenddate = privenddate;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }
    
   
}
