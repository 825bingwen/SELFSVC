/*
 * 文 件 名:  PrestoredRewardPO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  预存有礼PO类
 * 修 改 人:  jWX216858
 * 修改时间:  2014-11-28
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.prestoredReward.model;

/**
 * 预存有礼PO类
 * 
 * @author  jWX216858
 * @version  [版本号, 2014-11-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PrestoredRewardPO 
{
	// 地区
	private String region;
	
	// 活动id
	private String activityId;
	
	// 档次编码
	private String actLevelId;
	
	// 档次名称
	private String actLevelName;
	
	// 受理金额
	//findbugs修改，modify by sWX219697 2015-7-17 11:27:14，prePayFee改为prepayFee
	private String prepayFee;
	
	// 赠品价格
	private String presentValue;
	
	// 营销活动组
	private String groupId;
	
	// 营销活动名称
	private String groupName;
	
	// 活动说明
	private String activityDesc;
	
	// 小票奖品信息
	private String awardDesc;
	
	// 奖品编码串
	private String actreward;
	
	// 是否为货品
	private String isGoods = "0";

	// 是否为预受理 1：预受理，0受理
	private String onlycheck;
	
	// 支付方式
	private String paytype;
	
	// 用户投入费用
	private String totalFee;
	
	// 营销方案费用
	private String recFee;
	
	// 用户预存费用
	private String preFee;
	
	
	//add begin sWX219697 2015-5-28 17:19:00 OR_SD_201505_61自助终端上增加积分兑换电子券
	/**
	 * 营销活动类型，参见groupid为rewardType的字典项
	 */
	private String actType;
	
	/**
	 * 电子券对应的积分数
	 */
	private String actScore;
	
	/**
	 * 奖品名称
	 */
	private String rewardName;
	//add end sWX219697 2015-5-28 17:19:00 OR_SD_201505_61自助终端上增加积分兑换电子券
	
	// add begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化
	// 奖品数量
	private String quantity;
	// add end by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化
	
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

    // add begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化
    public String getQuantity()
    {
        return quantity;
    }

    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }
    // add end by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化
}
