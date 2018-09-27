/*
 * �� �� ��:  InvoicePrintPO.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��: zKF69263
 * �޸�ʱ��:  2014-5-9
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.invoice.model;

/**
 * ��Ʊ��ӡ��־PO
 * 
 * @author  zKF69263
 * @version  [�汾��, 2014-5-9]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class InvoicePrintPO
{
    /**
     * �������
     */
    private String servNumber = "";
    
    /**
     * ����
     */
    private String billCycle = "";
    
    /**
     * �ն˻�ID
     */
    private String termID = "";
    
    /**
     * ��ӡ����
     */
    private String recDate = "";
    
    /**
     * ҵ�������
     */
    private String recoid = "";
    
    /**
     * �˺�
     */
    private String acctId = "";
    
    /**
     * ��ӡ����  0 �վ�  1 ��Ʊ
     */
    private String invType = "";
    
    /**
     * ��ʼʱ�䣬��ʽ��YYYYMMDD
     */
   private String startdate;
    
    /**
     * ����ʱ�䣬��ʽ��YYYYMMDD
     */
    private String enddate;

    /**
     * @return ���� servNumber
     */
    public String getServNumber()
    {
        return servNumber;
    }

    /**
     * @param ��servNumber���и�ֵ
     */
    public void setServNumber(String servNumber)
    {
        this.servNumber = servNumber;
    }

    /**
     * @return ���� billCycle
     */
    public String getBillCycle()
    {
        return billCycle;
    }

    /**
     * @param ��billCycle���и�ֵ
     */
    public void setBillCycle(String billCycle)
    {
        this.billCycle = billCycle;
    }

    /**
     * @return ���� termID
     */
    public String getTermID()
    {
        return termID;
    }

    /**
     * @param ��termID���и�ֵ
     */
    public void setTermID(String termID)
    {
        this.termID = termID;
    }

    /**
     * @return ���� recDate
     */
    public String getRecDate()
    {
        return recDate;
    }

    /**
     * @param ��recDate���и�ֵ
     */
    public void setRecDate(String recDate)
    {
        this.recDate = recDate;
    }

    /**
     * @return ���� recoid
     */
    public String getRecoid()
    {
        return recoid;
    }

    /**
     * @param ��recoid���и�ֵ
     */
    public void setRecoid(String recoid)
    {
        this.recoid = recoid;
    }

    /**
     * @return ���� acctId
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getAcctId()
    {
        return acctId;
    }

    /**
     * @param ��acctId���и�ֵ
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setAcctId(String acctId)
    {
        this.acctId = acctId;
    }

    /**
     * @return ���� invType
     */
    public String getInvType()
    {
        return invType;
    }

    /**
     * @param ��invType���и�ֵ
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
