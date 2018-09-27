/*
 * 文件名：BlackListPO.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：错误的登录记录
 * 修改人：zKF77391
 * 修改时间：2013-03-27
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.login.model;
/**
 * 
 * 黑名单PO
 * 
 * @author zKF77391
 * @see
 * @since 2013-03-27
 */
public class BlackListPO
{
    
    // 手机号
    private String servNumber;
    
    // 操作员工号
    private String operid;
    
    // 状态 1 黑名单 0 不是黑名单
    private String status;
    
    // 加入黑名单日期
    private String statusDate;
    
    // 加入黑名单原因
    private String reason;

    public String getServNumber()
    {
        return servNumber;
    }

    public void setServNumber(String servNumber)
    {
        this.servNumber = servNumber;
    }

    public String getOperid()
    {
        return operid;
    }

    public void setOperid(String operid)
    {
        this.operid = operid;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatusDate()
    {
        return statusDate;
    }

    public void setStatusDate(String statusDate)
    {
        this.statusDate = statusDate;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }
    
}
