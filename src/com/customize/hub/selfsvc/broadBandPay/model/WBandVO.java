package com.customize.hub.selfsvc.broadBandPay.model;

/**
 * 
 * 宽带产品对象
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Sep 13, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WBandVO
{
    private String mainProdId;// 主体产品编码
    private String ncode;// 宽带产品NCODE，需与CRM保持一致
    private String prodName;// 宽带产品名称
    private String detailDesc;// 宽带产品描述信息
    private String fee;// 宽带产品所需费用，单位：分
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getMainProdId()
    {
        return mainProdId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setMainProdId(String mainProdId)
    {
        this.mainProdId = mainProdId;
    }
    public String getNcode()
    {
        return ncode;
    }
    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }
    public String getProdName()
    {
        return prodName;
    }
    public void setProdName(String prodName)
    {
        this.prodName = prodName;
    }
    public String getDetailDesc()
    {
        return detailDesc;
    }
    public void setDetailDesc(String detailDesc)
    {
        this.detailDesc = detailDesc;
    }
    public String getFee()
    {
        return fee;
    }
    public void setFee(String fee)
    {
        this.fee = fee;
    }
    
    
}
