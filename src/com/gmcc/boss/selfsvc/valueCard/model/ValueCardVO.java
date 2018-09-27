package com.gmcc.boss.selfsvc.valueCard.model;

public class ValueCardVO 
{
	// 手机号码
	private String telnum;
	
	// 卡面值
	private String cntTotal;
	
	// 卡数量
	private String cardNum;
	
	// 卡业务类型
	private String cardType;
	
	// 应缴金额
	private String minMoney;
	
	// 实际支付金额
	private String payMoney;
	
	// 发票付款人姓名
	private String custName;
	
	// 卡号
	private String cardNo;
	
	// 卡有效期
	private String expDate;
	
	// 卡业务属性值
	private String cardBusiPro;
	
	// 平台交易流水号
	private String transActionId;
	
	// 卡密码
	private String cardPwd;
	
	public String getTelnum() {
		return telnum;
	}

	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}

	public String getCntTotal() {
		return cntTotal;
	}

	public void setCntTotal(String cntTotal) {
		this.cntTotal = cntTotal;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getCardBusiPro() {
		return cardBusiPro;
	}

	public void setCardBusiPro(String cardBusiPro) {
		this.cardBusiPro = cardBusiPro;
	}

	public String getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(String minMoney) {
		this.minMoney = minMoney;
	}

	public String getTransActionId() {
		return transActionId;
	}

	public void setTransActionId(String transActionId) {
		this.transActionId = transActionId;
	}

	public String getCardPwd() {
		return cardPwd;
	}

	public void setCardPwd(String cardPwd) {
		this.cardPwd = cardPwd;
	}
}
