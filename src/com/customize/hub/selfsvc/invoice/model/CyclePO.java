package com.customize.hub.selfsvc.invoice.model;

/**
 * ����PO
 * 
 * @author  jWX216858
 * @version  [�汾��, 2014-6-19]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class CyclePO 
{
	// ���ڣ���ɢ���ڶ����ڸ�ʽYYYYMMDD����Ȼ�����ڸ�ʽYYYYMM
    private String cycle;
    
    // ��ʼʱ�䣬��ʽ:YYYYMMDD
    private String startdate;
    
    // ����ʱ�䣬��ʽ:YYYYMMDD
    private String enddate;
    
    // ���˺�
    private String acctid;
    
    // �Ƿ�ϻ��û���1���ǣ�0������
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
