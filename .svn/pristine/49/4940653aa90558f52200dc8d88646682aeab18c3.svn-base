/*
 * @filename: ChargeRecordPO.java
 * @  All Right Reserved (C), 2014-2018, HUAWEI TECO CO.
 * @brif:  实名认证办理 缴费记录PO
 * @author: hWX5316476
 * @de:  2014-06-10 
 * @description: 新增缴费记录PO
 * @remark: create hWX5316476 2014-05-30 V200R003C10LG0601 OR_huawei_201405_878
 */
package com.customize.sd.selfsvc.realNameReg.model;

import java.io.Serializable;
import java.util.List;

import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 缴费记录PO
 * 
 * @author hWX5316476
 * @version V200R003C10LG0601
 */
public class ChargeRecordPO implements Serializable
{
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 当前月充值月份
     */
    private String currMonth;
    
    /**
     * 当前月日期数组
     */
    private List<String> currMonDays;
    
    /**
     * 当前月充值日期
     */
    private String currMonChargeDate;
    
    /**
     * 当前月充值金额
     */
    private String currMonChargeAmount;
    
    /**
     * 上个月充值月份
     */
    private String lastMonth;
    
    /**
     * 上个月日期数组
     */
    private List<String> lastMonDays;
    
    /**
     * 上个月充值日期
     */
    private String lastMonChargeDate;
    
    /**
     * 上个月充值金额
     */
    private String lastMonChargeAmount;
    
    /**
     * 上上个月充值月份
     */
    private String preLastMonth;
    
    /**
     * 上上个月日期数组
     */
    private List<String> preLastMonDays;
    
    /**
     * 上上个月充值日期
     */
    private String preLastMonChargeDate;
    
    /**
     * 上上个月充值金额
     */
    private String preLastMonChargeAmount;

    /**
     * 无参构造方法
     */
    public ChargeRecordPO()
    {
    }
    
    /**
     * 有参构造方法
     * @param currMonthYear YYYYMM 当前月年月
     * @param lastMonthYear YYYYMM 上个月年月
     * @param preLastMonthYear YYYYMM 上上个月年月
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
