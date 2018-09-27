/*
 * �� �� ��:  InvoicePrintPO.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��: YKF38827
 * �޸�ʱ��:  Mar 13, 2012
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.hub.selfsvc.invoice.model;

/**
 * <��Ʊ��ӡ��־PO> <������ϸ����>
 * 
 * @author YKF38827
 * @version [Mar 13, 2012]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
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
    private String cycle = "";
    
    /**
     * �ն˻�ID
     */
    private String termID = "";
    
    /**
     * ��ӡ����
     */
    private String recDate = "";
    
    /**
     * ��Ʊ��ˮ��
     */
    private String formnum = "";
    
    //add begin jWX216858 2014/6/17 OR_HUB_201405_829_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ   
    /**
	 * �ͻ�ȫ��
	 */
	private String customerName;
	
	/**
	 * ��������
	 */
	private String fundType;

	/**
	 * ͨ�ŷ����
	 */
	private String commServFee;
	
	/**
	 * �����ۿ�
	 */
	private String sellDiscount;
	
	/**
	 * ���η�Ʊ���
	 */
	private String invoiceFee;
	
	/**
	 * ���
	 */
	private String fee;
	
	/**
	 * ��ͬ��
	 */
	private String contractNum;
	
	/**
	 * ��ӡ���
	 */
	private String printyear;
	
	/**
	 * ��ӡ�·�
	 */
	private String printmonth;
	
	/**
	 * ��ӡ��
	 */
	private String printday;
	//add end jWX216858 2014/6/17 OR_HUB_201405_829_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ   
	
	// add begin wWX217192 2016-01-27 OR_HUB_201512_256_����_������BOSSϵͳ�б����è���ѳ�ֵ�ѿ�Ʊ��������
	/**
	 * ������֧��
	 */
	private String thirdPay;
	// add end wWX217192 2016-01-27 OR_HUB_201512_256_����_������BOSSϵͳ�б����è���ѳ�ֵ�ѿ�Ʊ��������
	
    public String getServNumber()
    {
        return servNumber;
    }
    
    public void setServNumber(String servNumber)
    {
        this.servNumber = servNumber;
    }
    
    public String getCycle()
    {
        return cycle;
    }
    
    public void setCycle(String cycle)
    {
        this.cycle = cycle;
    }
    
    public String getTermID()
    {
        return termID;
    }
    
    public void setTermID(String termID)
    {
        this.termID = termID;
    }
    
    public String getRecDate()
    {
        return recDate;
    }
    
    public void setRecDate(String recDate)
    {
        this.recDate = recDate;
    }
    
    public String getFormnum()
    {
        return formnum;
    }
    
    public void setFormnum(String formnum)
    {
        this.formnum = formnum;
    }

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getCommServFee() {
		return commServFee;
	}

	public void setCommServFee(String commServFee) {
		this.commServFee = commServFee;
	}

	public String getSellDiscount() {
		return sellDiscount;
	}

	public void setSellDiscount(String sellDiscount) {
		this.sellDiscount = sellDiscount;
	}

	public String getInvoiceFee() {
		return invoiceFee;
	}

	public void setInvoiceFee(String invoiceFee) {
		this.invoiceFee = invoiceFee;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getPrintyear() {
		return printyear;
	}

	public void setPrintyear(String printyear) {
		this.printyear = printyear;
	}

	public String getPrintmonth() {
		return printmonth;
	}

	public void setPrintmonth(String printmonth) {
		this.printmonth = printmonth;
	}

	public String getPrintday() {
		return printday;
	}

	public void setPrintday(String printday) {
		this.printday = printday;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getThirdPay() {
		return thirdPay;
	}

	public void setThirdPay(String thirdPay) {
		this.thirdPay = thirdPay;
	}
    
}
