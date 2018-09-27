package com.customize.nx.selfsvc.prodChange.model;

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
public class ProdLogVO
{
    /**
     * 产品受理SN
     */
    private String prodSn;
    
    /**
     * SH_PRIV_LOG流水号
     */
    private String prodLogOID;
    
    /**
     *用户服务号码
     */
    private String servnumber;
    
    /**
     * 地区
     */
    private String region;
    
    /**
     * 用户转换产品
     */
    private String toProdId;
    
    /**
     * 当前用户产品
     */
    private String fromProdId;
    
    /**
     * 产品转换类型
     */
    private String prodType;
    
    /**
     * 用户受理类型
     */
    private String opType;
    
    /**
     * 业务受理时间
     */
    private String recDate;
    
    /**
     * 产品转换ID
     */
    private String changeId;
    
    /**
     * 业务受理状态
     */
    private String recStauts;
    
    //add begin yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
    /**
     * 受理状态描述
     */
    private String recstatusdesc;
    
    /**
     * 详细得变更情况
     */ 
    
    private String chgdesp;
    //add begin yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193

    public String getProdSn()
    {
        return prodSn;
    }

    public void setProdSn(String prodSn)
    {
        this.prodSn = prodSn;
    }

    public String getProdLogOID()
    {
        return prodLogOID;
    }

    public void setProdLogOID(String prodLogOID)
    {
        this.prodLogOID = prodLogOID;
    }

    public String getServnumber()
    {
        return servnumber;
    }

    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getToProdId()
    {
        return toProdId;
    }

    public void setToProdId(String toProdId)
    {
        this.toProdId = toProdId;
    }

    public String getFromProdId()
    {
        return fromProdId;
    }

    public void setFromProdId(String fromProdId)
    {
        this.fromProdId = fromProdId;
    }

    public String getProdType()
    {
        return prodType;
    }

    public void setProdType(String prodType)
    {
        this.prodType = prodType;
    }

    public String getOpType()
    {
        return opType;
    }

    public void setOpType(String opType)
    {
        this.opType = opType;
    }

    public String getRecDate()
    {
        return recDate;
    }

    public void setRecDate(String recDate)
    {
        this.recDate = recDate;
    }

    public String getChangeId()
    {
        return changeId;
    }

    public void setChangeId(String changeId)
    {
        this.changeId = changeId;
    }

    public String getRecStauts()
    {
        return recStauts;
    }

    public void setRecStauts(String recStauts)
    {
        this.recStauts = recStauts;
    }

    public String getRecstatusdesc()
    {
        return recstatusdesc;
    }

    public void setRecstatusdesc(String recstatusdesc)
    {
        this.recstatusdesc = recstatusdesc;
    }

    public String getChgdesp()
    {
        return chgdesp;
    }

    public void setChgdesp(String chgdesp)
    {
        this.chgdesp = chgdesp;
    }
    
}
