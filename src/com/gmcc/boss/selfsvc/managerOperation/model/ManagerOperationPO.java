package com.gmcc.boss.selfsvc.managerOperation.model;

/**
 * 维护人员管理 包括现金稽核等
 * 
 * @author xKF29026
 * 
 */
public class ManagerOperationPO
{
    // 稽核人密码
    private String auditPsw;
    
    // 稽核ID
    private String id;

    // 终端id
    private String termid;
    
    // add begin g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
    // 稽核开始时间，精确到秒
    private String auditStartTime = "";
    // add end g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
    
    // 稽核结束时间，精确到秒
    private String auditEndTime;
    
    // 稽核实际金额
    private String realMoney;
    
    // 系统计算金额
    private String sysMoney;
    
    // 打印状态 0:未打印 1:已打印
    private String printflag;
    
    // add begin zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能
    /**
     * 地区
     */
    private String region;
    // add end zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能
    
    public String getAuditPsw()
    {
        return auditPsw;
    }
    
    public void setAuditPsw(String auditPsw)
    {
        this.auditPsw = auditPsw;
    }

    public String getTermid()
    {
        return termid;
    }
    
    public void setTermid(String termid)
    {
        this.termid = termid;
    }
    
    public String getAuditEndTime()
    {
        return auditEndTime;
    }
    
    public void setAuditEndTime(String auditEndTime)
    {
        this.auditEndTime = auditEndTime;
    }
    
    public String getRealMoney()
    {
        return realMoney;
    }
    
    public void setRealMoney(String realMoney)
    {
        this.realMoney = realMoney;
    }

    public String getSysMoney()
    {
        return sysMoney;
    }

    public void setSysMoney(String sysMoney)
    {
        this.sysMoney = sysMoney;
    }

    public String getAuditStartTime()
    {
        return auditStartTime;
    }

    public void setAuditStartTime(String auditStartTime)
    {
        this.auditStartTime = auditStartTime;
    }

    public String getPrintflag()
    {
        return printflag;
    }

    public void setPrintflag(String printflag)
    {
        this.printflag = printflag;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

	// add begin zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能
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
    // add end zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能
}
