/*
 * @filename: CashDetailLogPO.java
 * @  All Right Reserved (C), 2012-2106, HUAWEI TECO CO.
 * @brif:  纸币面额信息
 * @author: 高群/g00140516
 * @de:  2012/03/09 
 * @description: 
 * @remark: create g00140516 2012/03/09 R003C12L03n01 OR_NX_201201_312
 */
package com.gmcc.boss.selfsvc.charge.model;

import com.gmcc.boss.selfsvc.common.BasePO;

/**
 * 
 * 纸币面额信息
 * 
 * @author  g00140516
 * @version  1.0, 2012/03/09
 * @see  
 * @since  
 */
public class CashDetailLogPO extends BasePO
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 终端编号
     */
    private String termID = "";
    
    /**
     * 用户投币流水
     */
    private String formNum = "";
    
    /**
     * 手机号码
     */
    private String servNum = "";
    
    /**
     * 投币金额
     */
    private String cashFee = "";

    public String getTermID()
    {
        return termID;
    }

    public void setTermID(String termID)
    {
        this.termID = termID;
    }

    public String getFormNum()
    {
        return formNum;
    }

    public void setFormNum(String formNum)
    {
        this.formNum = formNum;
    }

    public String getServNum()
    {
        return servNum;
    }

    public void setServNum(String servNum)
    {
        this.servNum = servNum;
    }

    public String getCashFee()
    {
        return cashFee;
    }

    public void setCashFee(String cashFee)
    {
        this.cashFee = cashFee;
    }
    
}
