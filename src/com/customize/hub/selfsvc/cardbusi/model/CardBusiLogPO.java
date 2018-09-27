package com.customize.hub.selfsvc.cardbusi.model;

/**
 * 
 * 在线开户
 * <功能详细描述>
 * 
 * @author  user
 * @version  [版本号, Jul 29, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]OR_NX_201303_280  宁夏自助终端优化需求之在线开户
 */
public class CardBusiLogPO
{
    /**
     * 流水号
     */
    private String oid = "";
    
    /**
     * 缴费流水号，与SH_CHARGE_lOG关联 缴费前此列数据为空
     */
    private String chargeId = "";
    
    /**
     * 终端号
     */
    private String termId = "";
    
    /**
     * 地区
     */
    private String region = "";
    
    /**
     * 开户手机号
     */
    private String servnumber = "";
    
    /**
     * 办理类型 presetinstall 预置空白卡开户
     */
    private String rectype = "";
    
    /**
     * 缴费方式，1：银联卡；4：现金
     */
    private String chargeType = "";
    
    /**
     * 开户应收费用
     */
    private String recFee = "";
    
    /**
     * 实收费用
     */
    private String toFee = "";
    
    /**
     * 主体产品编码
     */
    private String mainProdId = "";
    
    /**
     * 产品模板编码
     */
    private String prodTempletId = ""; 
    
    /**
     * 客户名称
     */
    private String custName = "";
    
    /**
     * 证件号
     */
    private String certId = "";
    
    /**
     * 性别
     */
    private String sex = "";
    
    /**
     * 地址
     */
    private String linkAddr = "";

    /**
     * 空白卡号
     */
    private String blankCard = "";
    
    /**
     * ICCID
     */
    private String iccid = "";
    
    /**
     * IMSI
     */
    private String imsi = "";
    
    /**
     * 短消息中心号码
     */
    private String smsp = "";
    
    /**
     * 客户联系电话
     */
    private String linkPhone = "";
    
    /**
     *  选择的产品列表
     */
    private String productList = "";
    
    /**
     * 用户邮件地址
     */
    private String submailAddr = "";
    
    /**
     * 创建时间
     */
    private String createDate = "";
    
    /**
     * 状态时间
     */
    private String statusDate = "";
    
    /**
     * 默认2：初始状态 0：缴费成功 1：缴费失败 
     */
    private String payStatus = "";
    
    /**
     * 默认2：初始状态 0：写卡成功 1：写卡失败 
     */
    private String writeCardStatus = "";
    
    /**
     * 默认2：初始状态 0：开户成功 1：开户失败
     */
    private String installStatus = "";
    
    /**
     * 默认2：初始状态 0：退款成功 1：退款失败
     */
    private String refundment = "";
    
    /**
     * 营业开户受理流水
     */
    private String formnum = "";
    
    /**
     * 用户SUBSID
     */
    private String subsId = "";
    
    /**
     * 备注
     */
    private String notes = ""; 
    
    public String getOid()
    {
        return oid;
    }
    public void setOid(String oid)
    {
        this.oid = oid;
    }
    public String getTermId()
    {
        return termId;
    }
    public void setTermId(String termId)
    {
        this.termId = termId;
    }
    public String getChargeId()
    {
        return chargeId;
    }
    public void setChargeId(String chargeId)
    {
        this.chargeId = chargeId;
    }
    public String getRegion()
    {
        return region;
    }
    public void setRegion(String region)
    {
        this.region = region;
    }
    public String getServnumber()
    {
        return servnumber;
    }
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    public String getMainProdId()
    {
        return mainProdId;
    }
    public void setMainProdId(String mainProdId)
    {
        this.mainProdId = mainProdId;
    }
    public String getProdTempletId()
    {
        return prodTempletId;
    }
    public void setProdTempletId(String prodTempletId)
    {
        this.prodTempletId = prodTempletId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getChargeType()
    {
        return chargeType;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setChargeType(String chargeType)
    {
        this.chargeType = chargeType;
    }
    public String getRecFee()
    {
        return recFee;
    }
    public void setRecFee(String recFee)
    {
        this.recFee = recFee;
    }
    public String getToFee()
    {
        return toFee;
    }
    public void setToFee(String toFee)
    {
        this.toFee = toFee;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getCustName()
    {
        return custName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setCustName(String custName)
    {
        this.custName = custName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getCertId()
    {
        return certId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setCertId(String certId)
    {
        this.certId = certId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getLinkAddr()
    {
        return linkAddr;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setLinkAddr(String linkAddr)
    {
        this.linkAddr = linkAddr;
    }
    public String getSex()
    {
        return sex;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getBlankCard()
    {
        return blankCard;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setBlankCard(String blankCard)
    {
        this.blankCard = blankCard;
    }
    public String getIccid()
    {
        return iccid;
    }
    public void setIccid(String iccid)
    {
        this.iccid = iccid;
    }
    public String getImsi()
    {
        return imsi;
    }
    public void setImsi(String imsi)
    {
        this.imsi = imsi;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getSmsp()
    {
        return smsp;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setSmsp(String smsp)
    {
        this.smsp = smsp;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getLinkPhone()
    {
        return linkPhone;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setLinkPhone(String linkPhone)
    {
        this.linkPhone = linkPhone;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getProductList()
    {
        return productList;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setProductList(String productList)
    {
        this.productList = productList;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getSubmailAddr()
    {
        return submailAddr;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setSubmailAddr(String submailAddr)
    {
        this.submailAddr = submailAddr;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getCreateDate()
    {
        return createDate;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }
    public String getStatusDate()
    {
        return statusDate;
    }
    public void setStatusDate(String statusDate)
    {
        this.statusDate = statusDate;
    }
    public String getWriteCardStatus()
    {
        return writeCardStatus;
    }
    public void setWriteCardStatus(String writeCardStatus)
    {
        this.writeCardStatus = writeCardStatus;
    }
    public String getPayStatus()
    {
        return payStatus;
    }
    public void setPayStatus(String payStatus)
    {
        this.payStatus = payStatus;
    }
    public String getInstallStatus()
    {
        return installStatus;
    }
    public void setInstallStatus(String installStatus)
    {
        this.installStatus = installStatus;
    }
    public String getRefundment()
    {
        return refundment;
    }
    public void setRefundment(String refundment)
    {
        this.refundment = refundment;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getFormnum()
    {
        return formnum;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setFormnum(String formnum)
    {
        this.formnum = formnum;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getSubsId()
    {
        return subsId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setSubsId(String subsId)
    {
        this.subsId = subsId;
    }
    public String getNotes()
    {
        return notes;
    }
    public void setNotes(String notes)
    {
        this.notes = notes;
    }
    public String getRectype()
    {
        return rectype;
    }
    public void setRectype(String rectype)
    {
        this.rectype = rectype;
    }
    
}
