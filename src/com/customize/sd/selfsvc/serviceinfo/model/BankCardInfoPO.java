package com.customize.sd.selfsvc.serviceinfo.model;

import java.io.Serializable;

public class BankCardInfoPO implements Serializable
{
    /**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 银行展示名称
     */
    private String bankName;
    
    /**
     * 银行展示图片
     */
    private String imgPath;
    
    /**
     * 银行展示备注信息
     */
    private String note;
    
    /**
     * 银行卡号
     */
    private String bankCardNum;
    
    /**
     * 银行对应代码
     */
    private String bankId;
    
    /**
     * 用户付费类型 0：后付费，1：预付费
     */
    private String payType;
    
    /**
     * 用户自动交费状态，0：已开通，1：未开通
     */
    private String autoStatus;
    
    /**
     * 预付费用户的自动交费触发余额
     */
    private String trigamt;
    
    /**
     * 预付费用户的自动交费设置划扣金额
     */
    private String drawamt;
    
    /**
     * 用户设置的副号码
     */
    private String viceNumber;
    
    /**
     * 副号码管理接口操作类型，1：新增，2：删除
     */
    private String operType;

	/**
	 * @return 返回 bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param 对bankName进行赋值
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return 返回 imgPath
	 */
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getImgPath() {
		return imgPath;
	}
	/**
	 * @param 对imgPath进行赋值
	 */
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * @return 返回 note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param 对note进行赋值
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return 返回 payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param 对payType进行赋值
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * @return 返回 autoStatus
	 */
	public String getAutoStatus() {
		return autoStatus;
	}

	/**
	 * @param 对autoStatus进行赋值
	 */
	public void setAutoStatus(String autoStatus) {
		this.autoStatus = autoStatus;
	}

	/**
	 * @return 返回 trigamt
	 */
	public String getTrigamt() {
		return trigamt;
	}

	/**
	 * @param 对trigamt进行赋值
	 */
	public void setTrigamt(String trigamt) {
		this.trigamt = trigamt;
	}

	/**
	 * @return 返回 drawamt
	 */
	public String getDrawamt() {
		return drawamt;
	}

	/**
	 * @param 对drawamt进行赋值
	 */
	public void setDrawamt(String drawamt) {
		this.drawamt = drawamt;
	}

	/**
	 * @return 返回 bankId
	 */
	public String getBankId() {
		return bankId;
	}

	/**
	 * @param 对bankId进行赋值
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getViceNumber() {
		return viceNumber;
	}

	public void setViceNumber(String viceNumber) {
		this.viceNumber = viceNumber;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getBankCardNum() {
		return bankCardNum;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getOperType() {
		return operType;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setOperType(String operType) {
		this.operType = operType;
	}
	
    
}
