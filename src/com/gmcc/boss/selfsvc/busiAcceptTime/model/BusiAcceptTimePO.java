/*
 * 文 件 名:  BusiAcceptTimePO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <业务受理时间>
 * 修 改 人:  zWX176560
 * 修改时间:  Sep 6, 2013
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <新增>
 */
package com.gmcc.boss.selfsvc.busiAcceptTime.model;

import java.io.Serializable;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  zWX176560 
 * @version  2013/09/05 OR_SD_201308_934 R003C13L09n01
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BusiAcceptTimePO implements Serializable
{
    private static final long serialVersionUID = -1616747056817423801L;
    
    /**
     * 操作员Id
     */
    private String operId;
    
    /**
     * 终端Id
     */
    private String termId;
    /**
     * 业务id
     */
    private String businessId = "";
    
    /**
     * 菜单Id
     */
    private String menuId = "";
    
    /**
     * 路由类型 0为地区，1为手机号码
     */
    private String routeType = "";
    
    /**
     * 地区
     */
    private String region = "";
    
    /**
     * 手机号码
     */
    private String servnum = "";
    
    /**
     * 开始时间
     */
    private String startTime = "";
    
    /**
     * 结束时间
     */
    private String endTime = "";
    
    /**
     * 受理时长
     */
    private long acceptTime = 0;
    
    /**
     * 状态 0 为失败；1 为成功
     */
    private int status = 0;

    public String getBusinessId()
    {
        return businessId;
    }

    public void setBusinessId(String businessId)
    {
        this.businessId = businessId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getMenuId()
    {
        return menuId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }

    public String getRouteType()
    {
        return routeType;
    }

    public void setRouteType(String routeType)
    {
        this.routeType = routeType;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getServnum()
    {
        return servnum;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setServnum(String servnum)
    {
        this.servnum = servnum;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public long getAcceptTime()
    {
        return acceptTime;
    }

    public void setAcceptTime(long acceptTime)
    {
        this.acceptTime = acceptTime;
    }

    public String getOperId()
    {
        return operId;
    }

    public void setOperId(String operId)
    {
        this.operId = operId;
    }

    public String getTermId()
    {
        return termId;
    }

    public void setTermId(String termId)
    {
        this.termId = termId;
    }
    
    
}
