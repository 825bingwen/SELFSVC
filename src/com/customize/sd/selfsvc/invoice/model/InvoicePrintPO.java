/*
 * 文 件 名:  InvoicePrintPO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人: zKF69263
 * 修改时间:  2014-5-9
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.invoice.model;

/**
 * 发票打印日志PO
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-5-9]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class InvoicePrintPO
{
    /**
     * 服务号码
     */
    private String servNumber = "";
    
    /**
     * 账期
     */
    private String billCycle = "";
    
    /**
     * 终端机ID
     */
    private String termID = "";
    
    /**
     * 打印日期
     */
    private String recDate = "";
    
    /**
     * 业务受理号
     */
    private String recoid = "";
    
    /**
     * 账号
     */
    private String acctId = "";
    
    /**
     * 打印类型  0 收据  1 发票
     */
    private String invType = "";
    
    /**
     * 开始时间，格式：YYYYMMDD
     */
   private String startdate;
    
    /**
     * 结束时间，格式：YYYYMMDD
     */
    private String enddate;

    /**
     * @return 返回 servNumber
     */
    public String getServNumber()
    {
        return servNumber;
    }

    /**
     * @param 对servNumber进行赋值
     */
    public void setServNumber(String servNumber)
    {
        this.servNumber = servNumber;
    }

    /**
     * @return 返回 billCycle
     */
    public String getBillCycle()
    {
        return billCycle;
    }

    /**
     * @param 对billCycle进行赋值
     */
    public void setBillCycle(String billCycle)
    {
        this.billCycle = billCycle;
    }

    /**
     * @return 返回 termID
     */
    public String getTermID()
    {
        return termID;
    }

    /**
     * @param 对termID进行赋值
     */
    public void setTermID(String termID)
    {
        this.termID = termID;
    }

    /**
     * @return 返回 recDate
     */
    public String getRecDate()
    {
        return recDate;
    }

    /**
     * @param 对recDate进行赋值
     */
    public void setRecDate(String recDate)
    {
        this.recDate = recDate;
    }

    /**
     * @return 返回 recoid
     */
    public String getRecoid()
    {
        return recoid;
    }

    /**
     * @param 对recoid进行赋值
     */
    public void setRecoid(String recoid)
    {
        this.recoid = recoid;
    }

    /**
     * @return 返回 acctId
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getAcctId()
    {
        return acctId;
    }

    /**
     * @param 对acctId进行赋值
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setAcctId(String acctId)
    {
        this.acctId = acctId;
    }

    /**
     * @return 返回 invType
     */
    public String getInvType()
    {
        return invType;
    }

    /**
     * @param 对invType进行赋值
     */
    public void setInvType(String invType)
    {
        this.invType = invType;
    }

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
}
