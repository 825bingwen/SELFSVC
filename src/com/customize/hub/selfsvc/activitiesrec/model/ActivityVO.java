package com.customize.hub.selfsvc.activitiesrec.model;

/**
 * 
 * 促销活动
 * 
 * @author  yKF28472
 * @version  [版本号, Jan 31, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ActivityVO
{
    private String region;// 区域
    
    private String activityId;// 活动ID
    
    private String dangciId;// 档次ID
    
    private String dangciIds;// 用户已存在的档次
    
    private String dangciName;// 档次名称
    
    private String prepayFee;// 受理金额
    
    private String presentValue;// 赠品价值
    
    private String groupId;// 活动组ID
    
    private String groupName;// 活动组名称
    
    private String statusDate;// 状态时间
    
    private String status;// 状态
    
    private String activityDesc;// 描述

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getActivityId()
    {
        return activityId;
    }

    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }

    public String getDangciId()
    {
        return dangciId;
    }

    public void setDangciId(String dangciId)
    {
        this.dangciId = dangciId;
    }

    public String getDangciName()
    {
        return dangciName;
    }

    public void setDangciName(String dangciName)
    {
        this.dangciName = dangciName;
    }

    public String getPrepayFee()
    {
        return prepayFee;
    }

    public void setPrepayFee(String prepayFee)
    {
        this.prepayFee = prepayFee;
    }

    public String getPresentValue()
    {
        return presentValue;
    }

    public void setPresentValue(String presentValue)
    {
        this.presentValue = presentValue;
    }

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getStatusDate()
    {
        return statusDate;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setStatusDate(String statusDate)
    {
        this.statusDate = statusDate;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDangciIds()
    {
        return dangciIds;
    }

    public void setDangciIds(String dangciIds)
    {
        this.dangciIds = dangciIds;
    }

    public String getActivityDesc()
    {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc)
    {
        this.activityDesc = activityDesc;
    }
    
    
    
    
}
