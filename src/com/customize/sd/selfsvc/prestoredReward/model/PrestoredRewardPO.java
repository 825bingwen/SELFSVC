/*
 * �� �� ��:  PrestoredRewardPO.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  Ԥ������PO��
 * �� �� ��:  jWX216858
 * �޸�ʱ��:  2014-11-28
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.prestoredReward.model;

/**
 * Ԥ������PO��
 * 
 * @author  jWX216858
 * @version  [�汾��, 2014-11-28]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class PrestoredRewardPO 
{
	// ����
	private String region;
	
	// �id
	private String activityId;
	
	// ���α���
	private String actLevelId;
	
	// ��������
	private String actLevelName;
	
	// ������
	//findbugs�޸ģ�modify by sWX219697 2015-7-17 11:27:14��prePayFee��ΪprepayFee
	private String prepayFee;
	
	// ��Ʒ�۸�
	private String presentValue;
	
	// Ӫ�����
	private String groupId;
	
	// Ӫ�������
	private String groupName;
	
	// �˵��
	private String activityDesc;
	
	// СƱ��Ʒ��Ϣ
	private String awardDesc;
	
	// ��Ʒ���봮
	private String actreward;
	
	// �Ƿ�Ϊ��Ʒ
	private String isGoods = "0";

	// �Ƿ�ΪԤ���� 1��Ԥ����0����
	private String onlycheck;
	
	// ֧����ʽ
	private String paytype;
	
	// �û�Ͷ�����
	private String totalFee;
	
	// Ӫ����������
	private String recFee;
	
	// �û�Ԥ�����
	private String preFee;
	
	
	//add begin sWX219697 2015-5-28 17:19:00 OR_SD_201505_61�����ն������ӻ��ֶһ�����ȯ
	/**
	 * Ӫ������ͣ��μ�groupidΪrewardType���ֵ���
	 */
	private String actType;
	
	/**
	 * ����ȯ��Ӧ�Ļ�����
	 */
	private String actScore;
	
	/**
	 * ��Ʒ����
	 */
	private String rewardName;
	//add end sWX219697 2015-5-28 17:19:00 OR_SD_201505_61�����ն������ӻ��ֶһ�����ȯ
	
	// add begin by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�
	// ��Ʒ����
	private String quantity;
	// add end by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
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

	public String getActLevelName() {
		return actLevelName;
	}

	public void setActLevelName(String actLevelName) {
		this.actLevelName = actLevelName;
	}


	public String getPrepayFee()
    {
        return prepayFee;
    }

    public void setPrepayFee(String prepayFee)
    {
        this.prepayFee = prepayFee;
    }

    public String getPresentValue() {
		return presentValue;
	}

	public void setPresentValue(String presentValue) {
		this.presentValue = presentValue;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public String getAwardDesc() {
		return awardDesc;
	}

	public void setAwardDesc(String awardDesc) {
		this.awardDesc = awardDesc;
	}

	public String getActreward() {
		return actreward;
	}

	public void setActreward(String actreward) {
		this.actreward = actreward;
	}

	public void setActLevelId(String actLevelId) {
		this.actLevelId = actLevelId;
	}

	public String getIsGoods() {
		return isGoods;
	}

	public void setIsGoods(String isGoods) {
		this.isGoods = isGoods;
	}

	public String getOnlycheck() {
		return onlycheck;
	}

	public void setOnlycheck(String onlycheck) {
		this.onlycheck = onlycheck;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}


	public String getTotalFee()
    {
        return totalFee;
    }

    public void setTotalFee(String totalFee)
    {
        this.totalFee = totalFee;
    }

    public String getRecFee() {
		return recFee;
	}

	public void setRecFee(String recFee) {
		this.recFee = recFee;
	}

	public String getPreFee() {
		return preFee;
	}

	public void setPreFee(String preFee) {
		this.preFee = preFee;
	}

    public String getActType()
    {
        return actType;
    }

    public void setActType(String actType)
    {
        this.actType = actType;
    }

    public String getActScore()
    {
        return actScore;
    }

    public void setActScore(String actScore)
    {
        this.actScore = actScore;
    }

    public String getRewardName()
    {
        return rewardName;
    }

    public void setRewardName(String rewardName)
    {
        this.rewardName = rewardName;
    }

    // add begin by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�
    public String getQuantity()
    {
        return quantity;
    }

    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }
    // add end by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�
}
