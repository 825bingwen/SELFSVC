/*
 * 文 件 名:  ActivityLogPO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  预存有礼受理日志PO类
 * 修 改 人:  jWX216858
 * 修改时间:  2014-12-15
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.prestoredReward.model;

/**
 * 预存有礼受理日志PO类
 * 
 * @author  jWX216858
 * @version  [版本号, 2014-12-15]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ActivityLogPO 
{
	/**
	 * 预存有礼唯一受理流水
	 */
	private String activityLogId;
	
	/**
	 * 与SH_CHARGE_lOG关联
	 */
	private String chargeId;
	
	/**
	 * region
	 */
	private String region;
	
	/**
	 * 手机号码
	 */
	private String telNum;
	
	/**
	 * 活动编码
	 */
	private String activityId;
	
	/**
	 * 档次编码
	 */
	private String actLevelId;
	
	/**
	 * 受理时间
	 */
	private String recDate;
	
	/**
	 * 活动受理金额
	 */
	private String recFee;
	
	/**
	 * 用户实缴金额
	 */
	private String totalFee;
	
	/**
	 * 用户预存金额
	 */
	private String preFee;
	
	/**
	 * 支付方式
	 */
	private String payType;
	
	/**
	 * 受理状态
	 */
	private String recStatus;
	
	/**
	 * 受理状态描述
	 */
	private String redStatusDesc;

	public String getActivityLogId() {
		return activityLogId;
	}

	public void setActivityLogId(String activityLogId) {
		this.activityLogId = activityLogId;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActLevelId() {
		return actLevelId;
	}

	public void setActLevelId(String actLevelId) {
		this.actLevelId = actLevelId;
	}

	public String getRecDate() {
		return recDate;
	}

	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}

	public String getRecFee() {
		return recFee;
	}

	public void setRecFee(String recFee) {
		this.recFee = recFee;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getPreFee() {
		return preFee;
	}

	public void setPreFee(String preFee) {
		this.preFee = preFee;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getRecStatus() {
		return recStatus;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}

	public String getRedStatusDesc() {
		return redStatusDesc;
	}

	public void setRedStatusDesc(String redStatusDesc) {
		this.redStatusDesc = redStatusDesc;
	}
	
}
