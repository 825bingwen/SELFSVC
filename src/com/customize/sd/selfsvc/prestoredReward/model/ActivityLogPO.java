/*
 * �� �� ��:  ActivityLogPO.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  Ԥ������������־PO��
 * �� �� ��:  jWX216858
 * �޸�ʱ��:  2014-12-15
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.prestoredReward.model;

/**
 * Ԥ������������־PO��
 * 
 * @author  jWX216858
 * @version  [�汾��, 2014-12-15]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ActivityLogPO 
{
	/**
	 * Ԥ������Ψһ������ˮ
	 */
	private String activityLogId;
	
	/**
	 * ��SH_CHARGE_lOG����
	 */
	private String chargeId;
	
	/**
	 * region
	 */
	private String region;
	
	/**
	 * �ֻ�����
	 */
	private String telNum;
	
	/**
	 * �����
	 */
	private String activityId;
	
	/**
	 * ���α���
	 */
	private String actLevelId;
	
	/**
	 * ����ʱ��
	 */
	private String recDate;
	
	/**
	 * �������
	 */
	private String recFee;
	
	/**
	 * �û�ʵ�ɽ��
	 */
	private String totalFee;
	
	/**
	 * �û�Ԥ����
	 */
	private String preFee;
	
	/**
	 * ֧����ʽ
	 */
	private String payType;
	
	/**
	 * ����״̬
	 */
	private String recStatus;
	
	/**
	 * ����״̬����
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
