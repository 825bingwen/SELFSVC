/*
 * @filename: CycleInfoPO.java
 * @  All Right Reserved (C), 2012-2106, HUAWEI TECO CO.
 * @brif:  NG3.5山东帐详单改造，账期对象
 * @author: 高群/g00140516
 * @de:  2012/02/09 
 * @description: 
 * @remark: create g00140516 2012/02/09 R003C12L02n01 OR_huawei_201112_953
 */
package com.gmcc.boss.selfsvc.feeservice.model;

import com.gmcc.boss.selfsvc.common.BasePO;

/**
 * 
 * 账期对象
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
     * 账期
     */
    private String cycle = "";
    
    /**
     * 账期开始日期
     */
    private String startDate = "";
    
    /**
     * 账期结束日期
     */
    private String endDate = "";
    
    /**
     * 主账号
     */
    private String acctID = "";   
    
    /**
     * 是否合户用户
     */
    private String unionAcct = "";
    
    /**
     * 账期开始日期
     */
    private String chStartDate = "";
    
    /**
     * 账期结束日期
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
