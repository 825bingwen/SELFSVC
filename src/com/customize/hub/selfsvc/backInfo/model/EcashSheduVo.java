package com.customize.hub.selfsvc.backInfo.model;

public class EcashSheduVo implements Comparable
{

     private String orderid;            
     private String scheduleid;      
     private String region;            
     private String billcylce;         
     private String  subsid;           
     private String servnumber;        
     private String returndate;        
     private String  proctime;           
     private String  activityname;         
     private String  validcycle ;            
     private String expirecycle;          
     private String ecashid ;          
     private String ecashvalue ;        
     private String totalvalue;          
     private String leftvalue;            
     private String retcycle ;      
     private String totalcycles ;       
     private String status ;            
     private String invoker  ;          
     private String smsnotify ;       
     private String smsdate  ;       
     private String filename ;            
     private String linenum   ;          
     private String chkstatus ;         
     private String chkdate ;          
     private String chkfilename  ;     
     private String fsdatetedate  ;       

    
    
    @Override
    public int compareTo(Object o)
    {   
        EcashSheduVo vo=(EcashSheduVo)o;
        //if(this.fsdatetedate.compareTo(vo.fsdatetedate)==0)
        if(this.fsdatetedate.equals(vo.fsdatetedate))
        return this.proctime.compareTo(vo.proctime);
        else return this.fsdatetedate.compareTo(vo.fsdatetedate);
        // TODO Auto-generated method stub
       
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getOrderid()
    {
        return orderid;
    }


    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setOrderid(String orderid)
    {
        this.orderid = orderid;
    }



    public String getScheduleid()
    {
        return scheduleid;
    }



    public void setScheduleid(String scheduleid)
    {
        this.scheduleid = scheduleid;
    }



    public String getRegion()
    {
        return region;
    }



    public void setRegion(String region)
    {
        this.region = region;
    }



    public String getBillcylce()
    {
        return billcylce;
    }



    public void setBillcylce(String billcylce)
    {
        this.billcylce = billcylce;
    }


    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getSubsid()
    {
        return subsid;
    }


    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setSubsid(String subsid)
    {
        this.subsid = subsid;
    }



    public String getServnumber()
    {
        return servnumber;
    }



    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }



    public String getReturndate()
    {
        return returndate;
    }



    public void setReturndate(String returndate)
    {
        this.returndate = returndate;
    }



    public String getProctime()
    {
        return proctime;
    }



    public void setProctime(String proctime)
    {
        this.proctime = proctime;
    }



    public String getActivityname()
    {
        return activityname;
    }



    public void setActivityname(String activityname)
    {
        this.activityname = activityname;
    }



    public String getValidcycle()
    {
        return validcycle;
    }



    public void setValidcycle(String validcycle)
    {
        this.validcycle = validcycle;
    }



    public String getExpirecycle()
    {
        return expirecycle;
    }



    public void setExpirecycle(String expirecycle)
    {
        this.expirecycle = expirecycle;
    }



    public String getEcashid()
    {
        return ecashid;
    }



    public void setEcashid(String ecashid)
    {
        this.ecashid = ecashid;
    }



    public String getEcashvalue()
    {
        return ecashvalue;
    }



    public void setEcashvalue(String ecashvalue)
    {
        this.ecashvalue = ecashvalue;
    }



    public String getTotalvalue()
    {
        return totalvalue;
    }



    public void setTotalvalue(String totalvalue)
    {
        this.totalvalue = totalvalue;
    }



    public String getLeftvalue()
    {
        return leftvalue;
    }



    public void setLeftvalue(String leftvalue)
    {
        this.leftvalue = leftvalue;
    }



    public String getRetcycle()
    {
        return retcycle;
    }



    public void setRetcycle(String retcycle)
    {
        this.retcycle = retcycle;
    }



    public String getTotalcycles()
    {
        return totalcycles;
    }



    public void setTotalcycles(String totalcycles)
    {
        this.totalcycles = totalcycles;
    }



    public String getStatus()
    {
        return status;
    }



    public void setStatus(String status)
    {
        this.status = status;
    }



    public String getInvoker()
    {
        return invoker;
    }



    public void setInvoker(String invoker)
    {
        this.invoker = invoker;
    }



    public String getSmsnotify()
    {
        return smsnotify;
    }



    public void setSmsnotify(String smsnotify)
    {
        this.smsnotify = smsnotify;
    }



    public String getSmsdate()
    {
        return smsdate;
    }



    public void setSmsdate(String smsdate)
    {
        this.smsdate = smsdate;
    }



    public String getFilename()
    {
        return filename;
    }



    public void setFilename(String filename)
    {
        this.filename = filename;
    }



    public String getLinenum()
    {
        return linenum;
    }



    public void setLinenum(String linenum)
    {
        this.linenum = linenum;
    }



    public String getChkstatus()
    {
        return chkstatus;
    }



    public void setChkstatus(String chkstatus)
    {
        this.chkstatus = chkstatus;
    }



    public String getChkdate()
    {
        return chkdate;
    }



    public void setChkdate(String chkdate)
    {
        this.chkdate = chkdate;
    }



    public String getChkfilename()
    {
        return chkfilename;
    }



    public void setChkfilename(String chkfilename)
    {
        this.chkfilename = chkfilename;
    }



    public String getFsdatetedate()
    {
        return fsdatetedate;
    }



    public void setFsdatetedate(String fsdatetedate)
    {
        this.fsdatetedate = fsdatetedate;
    }




    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fsdatetedate == null) ? 0 : fsdatetedate.hashCode());
        result = prime * result + ((proctime == null) ? 0 : proctime.hashCode());
        return result;
    }




    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final EcashSheduVo other = (EcashSheduVo)obj;
        if (fsdatetedate == null)
        {
            if (other.fsdatetedate != null)
                return false;
        }
        else if (!fsdatetedate.equals(other.fsdatetedate))
            return false;
        if (proctime == null)
        {
            if (other.proctime != null)
                return false;
        }
        else if (!proctime.equals(other.proctime))
            return false;
        return true;
    }
    
}
