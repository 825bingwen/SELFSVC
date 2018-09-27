/*
 * @filename: ChargeRecordPO.java
 * @  All Right Reserved (C), 2014-2018, HUAWEI TECO CO.
 * @brif:  ʵ����֤���� �ɷѼ�¼PO
 * @author: hWX5316476
 * @de:  2014-06-10 
 * @description: �����ɷѼ�¼PO
 * @remark: create hWX5316476 2014-05-30 V200R003C10LG0601 OR_huawei_201405_878
 */
package com.customize.sd.selfsvc.realNameReg.model;

import java.io.Serializable;
import java.util.List;

import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * �ɷѼ�¼PO
 * 
 * @author hWX5316476
 * @version V200R003C10LG0601
 */
public class ChargeRecordPO implements Serializable
{
    /**
     * ���л�
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * ��ǰ�³�ֵ�·�
     */
    private String currMonth;
    
    /**
     * ��ǰ����������
     */
    private List<String> currMonDays;
    
    /**
     * ��ǰ�³�ֵ����
     */
    private String currMonChargeDate;
    
    /**
     * ��ǰ�³�ֵ���
     */
    private String currMonChargeAmount;
    
    /**
     * �ϸ��³�ֵ�·�
     */
    private String lastMonth;
    
    /**
     * �ϸ�����������
     */
    private List<String> lastMonDays;
    
    /**
     * �ϸ��³�ֵ����
     */
    private String lastMonChargeDate;
    
    /**
     * �ϸ��³�ֵ���
     */
    private String lastMonChargeAmount;
    
    /**
     * ���ϸ��³�ֵ�·�
     */
    private String preLastMonth;
    
    /**
     * ���ϸ�����������
     */
    private List<String> preLastMonDays;
    
    /**
     * ���ϸ��³�ֵ����
     */
    private String preLastMonChargeDate;
    
    /**
     * ���ϸ��³�ֵ���
     */
    private String preLastMonChargeAmount;

    /**
     * �޲ι��췽��
     */
    public ChargeRecordPO()
    {
    }
    
    /**
     * �вι��췽��
     * @param currMonthYear YYYYMM ��ǰ������
     * @param lastMonthYear YYYYMM �ϸ�������
     * @param preLastMonthYear YYYYMM ���ϸ�������
     */
    public ChargeRecordPO(String currMonthYear, String lastMonthYear, String preLastMonthYear)
    {
        this.currMonth = CommonUtil.getMonth(currMonthYear);
        this.lastMonth = CommonUtil.getMonth(lastMonthYear);
        this.preLastMonth = CommonUtil.getMonth(preLastMonthYear);
        this.currMonDays = CommonUtil.getMonDays(currMonthYear);
        this.lastMonDays = CommonUtil.getMonDays(lastMonthYear);
        this.preLastMonDays = CommonUtil.getMonDays(preLastMonthYear);
    }
    
    
    public ChargeRecordPO(String currMonth)
    {
        this.currMonth = currMonth;
        
    }
    
    public String getCurrMonth()
    {
        return currMonth;
    }

    public void setCurrMonth(String currMonth)
    {
        this.currMonth = currMonth;
    }

    public String getCurrMonChargeDate()
    {
        return currMonChargeDate;
    }

    public void setCurrMonChargeDate(String currMonChargeDate)
    {
        this.currMonChargeDate = currMonChargeDate;
    }

    public String getCurrMonChargeAmount()
    {
        return currMonChargeAmount;
    }

    public void setCurrMonChargeAmount(String currMonChargeAmount)
    {
        this.currMonChargeAmount = currMonChargeAmount;
    }

    public String getLastMonth()
    {
        return lastMonth;
    }

    public void setLastMonth(String lastMonth)
    {
        this.lastMonth = lastMonth;
    }

    public String getLastMonChargeDate()
    {
        return lastMonChargeDate;
    }

    public void setLastMonChargeDate(String lastMonChargeDate)
    {
        this.lastMonChargeDate = lastMonChargeDate;
    }

    public String getLastMonChargeAmount()
    {
        return lastMonChargeAmount;
    }

    public void setLastMonChargeAmount(String lastMonChargeAmount)
    {
        this.lastMonChargeAmount = lastMonChargeAmount;
    }

    public String getPreLastMonth()
    {
        return preLastMonth;
    }

    public void setPreLastMonth(String preLastMonth)
    {
        this.preLastMonth = preLastMonth;
    }

    public String getPreLastMonChargeDate()
    {
        return preLastMonChargeDate;
    }

    public void setPreLastMonChargeDate(String preLastMonChargeDate)
    {
        this.preLastMonChargeDate = preLastMonChargeDate;
    }

    public String getPreLastMonChargeAmount()
    {
        return preLastMonChargeAmount;
    }

    public void setPreLastMonChargeAmount(String preLastMonChargeAmount)
    {
        this.preLastMonChargeAmount = preLastMonChargeAmount;
    }

    public List<String> getCurrMonDays()
    {
        return currMonDays;
    }
    public void setCurrMonDays(List<String> currMonDays)
    {
        this.currMonDays = currMonDays;
    }

    public List<String> getLastMonDays()
    {
        return lastMonDays;
    }

    public void setLastMonDays(List<String> lastMonDays)
    {
        this.lastMonDays = lastMonDays;
    }

    public List<String> getPreLastMonDays()
    {
        return preLastMonDays;
    }

    public void setPreLastMonDays(List<String> preLastMonDays)
    {
        this.preLastMonDays = preLastMonDays;
    }
    
    
}
