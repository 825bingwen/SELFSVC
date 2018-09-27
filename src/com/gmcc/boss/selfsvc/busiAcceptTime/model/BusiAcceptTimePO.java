/*
 * �� �� ��:  BusiAcceptTimePO.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <ҵ������ʱ��>
 * �� �� ��:  zWX176560
 * �޸�ʱ��:  Sep 6, 2013
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <����>
 */
package com.gmcc.boss.selfsvc.busiAcceptTime.model;

import java.io.Serializable;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  zWX176560 
 * @version  2013/09/05 OR_SD_201308_934 R003C13L09n01
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class BusiAcceptTimePO implements Serializable
{
    private static final long serialVersionUID = -1616747056817423801L;
    
    /**
     * ����ԱId
     */
    private String operId;
    
    /**
     * �ն�Id
     */
    private String termId;
    /**
     * ҵ��id
     */
    private String businessId = "";
    
    /**
     * �˵�Id
     */
    private String menuId = "";
    
    /**
     * ·������ 0Ϊ������1Ϊ�ֻ�����
     */
    private String routeType = "";
    
    /**
     * ����
     */
    private String region = "";
    
    /**
     * �ֻ�����
     */
    private String servnum = "";
    
    /**
     * ��ʼʱ��
     */
    private String startTime = "";
    
    /**
     * ����ʱ��
     */
    private String endTime = "";
    
    /**
     * ����ʱ��
     */
    private long acceptTime = 0;
    
    /**
     * ״̬ 0 Ϊʧ�ܣ�1 Ϊ�ɹ�
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
