/*
 * �� �� ��:  ServerNumPO.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  ѡ��������
 * �� �� ��:  zKF69263
 * �޸�ʱ��:  2014-10-25
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.hub.selfsvc.cardbusi.model;

/**
 * ѡ��������
 * 
 * @author  zKF69263
 * @version  [�汾��, 2014-10-25]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ServerNumPO
{
    /**
     * ��λ��ԤԼʱʹ�ã�
     */
    private String orgId;
    
    /**
     * ���У�ԤԼʱʹ�ã�
     */
    private String region;
    
    /**
     * �ֻ�����
     */
    private String telnum;
    
    /**
     * Ʒ����Ϣ
     */
    private String brand;
    
    /**
     * ���뼶�𣬼�ѡ�ŷѣ���λ����
     */
    private String fee;
    
    /**
     * �������
     */
    private String lowConsumFee;
    
    /**
     * Ԥ�滰��
     */
    private String lowConsumPre;
    
    /**
     * ǩԼʱ��
     */
    private String lowInserviceTime;

    /**
     * @return ���� orgId
     */
    public String getOrgId()
    {
        return orgId;
    }

    /**
     * @param ��orgId���и�ֵ
     */
    public void setOrgId(String orgId)
    {
        this.orgId = orgId;
    }

    /**
     * @return ���� region
     */
    public String getRegion()
    {
        return region;
    }

    /**
     * @param ��region���и�ֵ
     */
    public void setRegion(String region)
    {
        this.region = region;
    }

    /**
     * @return ���� telnum
     */
    public String getTelnum()
    {
        return telnum;
    }

    /**
     * @param ��telnum���и�ֵ
     */
    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }

    /**
     * @return ���� fee
     */
    public String getFee()
    {
        return fee;
    }

    /**
     * @param ��fee���и�ֵ
     */
    public void setFee(String fee)
    {
        this.fee = fee;
    }
    
    /**
     * @return ���� lowConsumFee
     */
    public String getLowConsumFee()
    {
        return lowConsumFee;
    }

    /**
     * @param ��lowConsumFee���и�ֵ
     */
    public void setLowConsumFee(String lowConsumFee)
    {
        this.lowConsumFee = lowConsumFee;
    }

    /**
     * @return ���� lowConsumPre
     */
    public String getLowConsumPre()
    {
        return lowConsumPre;
    }

    /**
     * @param ��lowConsumPre���и�ֵ
     */
    public void setLowConsumPre(String lowConsumPre)
    {
        this.lowConsumPre = lowConsumPre;
    }

    /**
     * @return ���� lowInserviceTime
     */
    public String getLowInserviceTime()
    {
        return lowInserviceTime;
    }

    /**
     * @param ��lowInserviceTime���и�ֵ
     */
    public void setLowInserviceTime(String lowInserviceTime)
    {
        this.lowInserviceTime = lowInserviceTime;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }
}
