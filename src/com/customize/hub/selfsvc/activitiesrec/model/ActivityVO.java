package com.customize.hub.selfsvc.activitiesrec.model;

/**
 * 
 * �����
 * 
 * @author  yKF28472
 * @version  [�汾��, Jan 31, 2012]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ActivityVO
{
    private String region;// ����
    
    private String activityId;// �ID
    
    private String dangciId;// ����ID
    
    private String dangciIds;// �û��Ѵ��ڵĵ���
    
    private String dangciName;// ��������
    
    private String prepayFee;// ������
    
    private String presentValue;// ��Ʒ��ֵ
    
    private String groupId;// ���ID
    
    private String groupName;// �������
    
    private String statusDate;// ״̬ʱ��
    
    private String status;// ״̬
    
    private String activityDesc;// ����

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
