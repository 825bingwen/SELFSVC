/*
 * 文 件 名:  CashFeeErrorInfoVO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HW
 * 修改时间:  2011-11-24
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.charge.model;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author HW
 * @version [版本号, 2011-11-24]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CashFeeErrorInfoVO
{
    /** 交易流水号 */
    private String terminalSeq;
    
    /** 终端机编号 */
    private String termid;
    
    /** 地市区号 */
    private String region;
    
    /** 操作员编号 */
    private String operID;
    
    /** 缴费地点 */
    private String orgID;
    
    /** 缴费号码 */
    private String servnumber;
    
    /** 缴费方式，1：银联卡；4：现金 */
    private String payType;
    
    /** 缴费金额，单位（分） */
    private String fee;
    
    /** 1.重复缴费 */
    private String status;
    
    /** 交易时间 */
    private String recDate;
    
    /** 记录创建时间 */
    private String createDate;
    
    /** 说明 */
    private String description;
    
    public CashFeeErrorInfoVO()
    {
        
    }
    
    public CashFeeErrorInfoVO(String termID, String region, String operID, String orgID)
    {
        this.termid = termID;
        this.region = region;
        this.operID = operID;
        this.orgID = orgID;
    }
    
    /**
     * @return 返回 terminalSeq
     */
    public String getTerminalSeq()
    {
        return terminalSeq;
    }
    
    /**
     * @param 对terminalSeq进行赋值
     */
    public void setTerminalSeq(String terminalSeq)
    {
        this.terminalSeq = terminalSeq;
    }
    

    public String getTermid()
    {
        return termid;
    }

    public void setTermid(String termid)
    {
        this.termid = termid;
    }

    /**
     * @return 返回 region
     */
    public String getRegion()
    {
        return region;
    }
    
    /**
     * @param 对region进行赋值
     */
    public void setRegion(String region)
    {
        this.region = region;
    }
    
    /**
     * @return 返回 operID
     */
    public String getOperID()
    {
        return operID;
    }
    
    /**
     * @param 对operID进行赋值
     */
    public void setOperID(String operID)
    {
        this.operID = operID;
    }
    
    /**
     * @return 返回 orgID
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getOrgID()
    {
        return orgID;
    }
    
    /**
     * @param 对orgID进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setOrgID(String orgID)
    {
        this.orgID = orgID;
    }
    
    /**
     * @return 返回 servnumber
     */
    public String getServnumber()
    {
        return servnumber;
    }
    
    /**
     * @param 对servnumber进行赋值
     */
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    /**
     * @return 返回 payType
     */
    public String getPayType()
    {
        return payType;
    }
    
    /**
     * @param 对payType进行赋值
     */
    public void setPayType(String payType)
    {
        this.payType = payType;
    }
    
    /**
     * @return 返回 fee
     */
    public String getFee()
    {
        return fee;
    }
    
    /**
     * @param 对fee进行赋值
     */
    public void setFee(String fee)
    {
        this.fee = fee;
    }
    
    /**
     * @return 返回 status
     */
    public String getStatus()
    {
        return status;
    }
    
    /**
     * @param 对status进行赋值
     */
    public void setStatus(String status)
    {
        this.status = status;
    }
    /**
     * @return 返回 recDate
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getRecDate()
    {
        return recDate;
    }
    /**
     * @param 对recDate进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setRecDate(String recDate)
    {
        this.recDate = recDate;
    }
    
    /**
     * @return 返回 createDate
     */
    public String getCreateDate()
    {
        return createDate;
    }
    
    /**
     * @param 对createDate进行赋值
     */
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }
    
    /**
     * @return 返回 description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @param 对description进行赋值
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
    
}
