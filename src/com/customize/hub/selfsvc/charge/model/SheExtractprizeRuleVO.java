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

import java.sql.Date;




public class SheExtractprizeRuleVO
{
    private String rectype; 
    private String step;       
    private String actid;    
    private String activename;
    private int  region;     
    private int costtype;  
    private int  costdata;   
    private int status;
    private Date statusdate; 
    private String  note;
    public String getRectype()
    {
        return rectype;
    }
    public void setRectype(String rectype)
    {
        this.rectype = rectype;
    }
    public String getStep()
    {
        return step;
    }
    public void setStep(String step)
    {
        this.step = step;
    }
    public String getActid()
    {
        return actid;
    }
    public void setActid(String actid)
    {
        this.actid = actid;
    }
    public String getActivename()
    {
        return activename;
    }
    public void setActivename(String activename)
    {
        this.activename = activename;
    }
    public int getRegion()
    {
        return region;
    }
    public void setRegion(int region)
    {
        this.region = region;
    }
    public int getCosttype()
    {
        return costtype;
    }
    public void setCosttype(int costtype)
    {
        this.costtype = costtype;
    }
    public int getCostdata()
    {
        return costdata;
    }
    public void setCostdata(int costdata)
    {
        this.costdata = costdata;
    }
    public int getStatus()
    {
        return status;
    }
    public void setStatus(int status)
    {
        this.status = status;
    }
    public Date getStatusdate()
    {
        return statusdate;
    }
    public void setStatusdate(Date statusdate)
    {
        this.statusdate = statusdate;
    }
    public String getNote()
    {
        return note;
    }
    public void setNote(String note)
    {
        this.note = note;
    }   

}
