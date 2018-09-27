package com.customize.nx.selfsvc.invoice.model;

/**
 * 月结发票
 * 
 * @author  jWX216858
 * @version  [版本号, 2014-6-17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MonInvoicePO
{
	/**
	 * 手机号码
	 */
	private String telnum;
	
	/**
	 * 客户全称
	 */
	private String customerName;
	
	/**
	 * 款项性质
	 */
	private String fundType;

	/**
	 * 通信服务费
	 */
	private String commServFeeName;
	
	/**
	 * 通信服务费金额
	 */
	private String commServFee;
	
	/**
	 * 销售折扣
	 */
	private String sellDiscount;
	
	/**
	 * 本次发票金额
	 */
	private String invoiceFee;
	
	/**
	 * 金额
	 */
	private String fee;
	
	/**
	 * 流水号
	 */
	private String formNum;
	
	/**
	 * 合同号
	 */
	private String contractNum;
	
	/**
	 * 打印时间
	 */
	private String printTime;
	
	public String getTelnum() {
		return telnum;
	}

	public void setTelnum(String telnum) {
		this.telnum = telnum;
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

	public String getInvoiceFee() {
		return invoiceFee;
	}

	public void setInvoiceFee(String invoiceFee) {
		this.invoiceFee = invoiceFee;
	}

	public String getFormNum() {
		return formNum;
	}

	public void setFormNum(String formNum) {
		this.formNum = formNum;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getCommServFee() {
		return commServFee;
	}

	public void setCommServFee(String commServFee) {
		this.commServFee = commServFee;
	}

	public String getCommServFeeName() {
		return commServFeeName;
	}

	public void setCommServFeeName(String commServFeeName) {
		this.commServFeeName = commServFeeName;
	}

	public String getPrintTime() {
		return printTime;
	}

	public void setPrintTime(String printTime) {
		this.printTime = printTime;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	/**
	 * @return the sellDiscount
	 */
	public String getSellDiscount() {
		return sellDiscount;
	}

	/**
	 * @param sellDiscount the sellDiscount to set
	 */
	public void setSellDiscount(String sellDiscount) {
		this.sellDiscount = sellDiscount;
	}
}
