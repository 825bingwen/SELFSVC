package com.customize.hub.selfsvc.invoice.model;

/**
 * 账期PO
 * 
 * @author  jWX216858
 * @version  [版本号, 2014-6-19]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CyclePO 
{
	// 帐期，分散帐期多帐期格式YYYYMMDD，自然月帐期格式YYYYMM
    private String cycle;
    
    // 开始时间，格式:YYYYMMDD
    private String startdate;
    
    // 结束时间，格式:YYYYMMDD
    private String enddate;
    
    // 主账号
    private String acctid;
    
    // 是否合户用户。1，是；0，不是
    private String unionacct;

    public String getCycle()
    {
        return cycle;
    }

    public void setCycle(String cycle)
    {
        this.cycle = cycle;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getStartdate()
    {
        return startdate;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setStartdate(String startdate)
    {
        this.startdate = startdate;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getEnddate()
    {
        return enddate;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setEnddate(String enddate)
    {
        this.enddate = enddate;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getAcctid()
    {
        return acctid;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setAcctid(String acctid)
    {
        this.acctid = acctid;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getUnionacct()
    {
        return unionacct;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setUnionacct(String unionacct)
    {
        this.unionacct = unionacct;
    }
}
