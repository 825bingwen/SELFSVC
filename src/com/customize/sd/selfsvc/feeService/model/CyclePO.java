package com.customize.sd.selfsvc.feeService.model;

/**
 * 
 * ����PO
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Feb 15, 2012]
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

    public String getStartdate()
    {
        return startdate;
    }

    public void setStartdate(String startdate)
    {
        this.startdate = startdate;
    }

    public String getEnddate()
    {
        return enddate;
    }

    public void setEnddate(String enddate)
    {
        this.enddate = enddate;
    }

    public String getAcctid()
    {
        return acctid;
    }

    public void setAcctid(String acctid)
    {
        this.acctid = acctid;
    }

    public String getUnionacct()
    {
        return unionacct;
    }

    public void setUnionacct(String unionacct)
    {
        this.unionacct = unionacct;
    }
    
    
    
}
