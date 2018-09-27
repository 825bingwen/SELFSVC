/*
 * @filename: CycleInfoPO.java
 * @  All Right Reserved (C), 2012-2106, HUAWEI TECO CO.
 * @brif:  NG3.5ɽ�����굥���죬���ڶ���
 * @author: ��Ⱥ/g00140516
 * @de:  2012/02/09 
 * @description: 
 * @remark: create g00140516 2012/02/09 R003C12L02n01 OR_huawei_201112_953
 */
package com.gmcc.boss.selfsvc.feeservice.model;

import com.gmcc.boss.selfsvc.common.BasePO;

/**
 * 
 * ���ڶ���
 * 
 * @author  g00140516
 * @version  1.0, 2012/02/26
 * @see  
 * @since  
 */
public class CycleInfoPO extends BasePO
{
    private static final long serialVersionUID = 1L;
    
    /**
     * ����
     */
    private String cycle = "";
    
    /**
     * ���ڿ�ʼ����
     */
    private String startDate = "";
    
    /**
     * ���ڽ�������
     */
    private String endDate = "";
    
    /**
     * ���˺�
     */
    private String acctID = "";   
    
    /**
     * �Ƿ�ϻ��û�
     */
    private String unionAcct = "";
    
    /**
     * ���ڿ�ʼ����
     */
    private String chStartDate = "";
    
    /**
     * ���ڽ�������
     */
    private String chEndDate = "";

    public String getCycle()
    {
        return cycle;
    }

    public void setCycle(String cycle)
    {
        this.cycle = cycle;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getAcctID()
    {
        return acctID;
    }

    public void setAcctID(String acctID)
    {
        this.acctID = acctID;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getUnionAcct()
    {
        return unionAcct;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setUnionAcct(String unionAcct)
    {
        this.unionAcct = unionAcct;
    }

    public String getChStartDate()
    {
        return chStartDate;
    }

    public void setChStartDate(String chStartDate)
    {
        this.chStartDate = chStartDate;
    }

    public String getChEndDate()
    {
        return chEndDate;
    }

    public void setChEndDate(String chEndDate)
    {
        this.chEndDate = chEndDate;
    }
}
