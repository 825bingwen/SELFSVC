/*
 * �� �� ��:  CashFeeErrorInfoVO.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  HW
 * �޸�ʱ��:  2011-11-24
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.hub.selfsvc.charge.model;

/**
 * <һ�仰���ܼ���> <������ϸ����>
 * 
 * @author HW
 * @version [�汾��, 2011-11-24]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class CashFeeErrorInfoVO
{
    /** ������ˮ�� */
    private String terminalSeq;
    
    /** �ն˻���� */
    private String termid;
    
    /** �������� */
    private String region;
    
    /** ����Ա��� */
    private String operID;
    
    /** �ɷѵص� */
    private String orgID;
    
    /** �ɷѺ��� */
    private String servnumber;
    
    /** �ɷѷ�ʽ��1����������4���ֽ� */
    private String payType;
    
    /** �ɷѽ���λ���֣� */
    private String fee;
    
    /** 1.�ظ��ɷ� */
    private String status;
    
    /** ����ʱ�� */
    private String recDate;
    
    /** ��¼����ʱ�� */
    private String createDate;
    
    /** ˵�� */
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
     * @return ���� terminalSeq
     */
    public String getTerminalSeq()
    {
        return terminalSeq;
    }
    
    /**
     * @param ��terminalSeq���и�ֵ
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
     * @return ���� region
     */
    public String getRegion()
    {
        return region;
    }
    
    /**
     * @param ��region���и�ֵ
     */
    public void setRegion(String region)
    {
        this.region = region;
    }
    
    /**
     * @return ���� operID
     */
    public String getOperID()
    {
        return operID;
    }
    
    /**
     * @param ��operID���и�ֵ
     */
    public void setOperID(String operID)
    {
        this.operID = operID;
    }
    
    /**
     * @return ���� orgID
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getOrgID()
    {
        return orgID;
    }
    
    /**
     * @param ��orgID���и�ֵ
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setOrgID(String orgID)
    {
        this.orgID = orgID;
    }
    
    /**
     * @return ���� servnumber
     */
    public String getServnumber()
    {
        return servnumber;
    }
    
    /**
     * @param ��servnumber���и�ֵ
     */
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    /**
     * @return ���� payType
     */
    public String getPayType()
    {
        return payType;
    }
    
    /**
     * @param ��payType���и�ֵ
     */
    public void setPayType(String payType)
    {
        this.payType = payType;
    }
    
    /**
     * @return ���� fee
     */
    public String getFee()
    {
        return fee;
    }
    
    /**
     * @param ��fee���и�ֵ
     */
    public void setFee(String fee)
    {
        this.fee = fee;
    }
    
    /**
     * @return ���� status
     */
    public String getStatus()
    {
        return status;
    }
    
    /**
     * @param ��status���и�ֵ
     */
    public void setStatus(String status)
    {
        this.status = status;
    }
    /**
     * @return ���� recDate
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getRecDate()
    {
        return recDate;
    }
    /**
     * @param ��recDate���и�ֵ
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setRecDate(String recDate)
    {
        this.recDate = recDate;
    }
    
    /**
     * @return ���� createDate
     */
    public String getCreateDate()
    {
        return createDate;
    }
    
    /**
     * @param ��createDate���и�ֵ
     */
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }
    
    /**
     * @return ���� description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @param ��description���и�ֵ
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
    
}
