package com.customize.hub.selfsvc.privAccept.model;

public class PrivLogVO {
	//优惠受理的唯一流水
	private String privLogOID;
	
	//与缴费关联的流水
	private String chargeID;
	
	//地区
	private String region;
	
	//服务号码
	private String servnumber;
	
	//优惠ID
	private String privId;
	
	//优惠Ncode
	private String privNcode;
	
	//优惠受理时间
	private String recDate;
	
	//优惠受理费用
	private String privFee;
	
	//用户实际投入费用
	private String toFee;
	
	//用户缴入的费用
	private String chargeFee;
	
	//缴费方式，1：银联卡；4：现金
	private String chargeType; 
	
	//优惠受理的状态,01:优惠受理失败;02:金额不足,缴费失败;03:优惠受理成功,缴费失败;04:优惠受理成功,缴费成功;
	//05:优惠受理成功;06:金额不足,缴费成功;
	private String recStatus;  
  
	//优惠受理状态描述
	private String recStatusDesc;
	
	//favourable -- 优惠办理；activity -- 促销活动。
	private String recType;

	public String getPrivLogOID() {
		return privLogOID;
	}

	public void setPrivLogOID(String privLogOID) {
		this.privLogOID = privLogOID;
	}

	public String getChargeID() {
		return chargeID;
	}

	public void setChargeID(String chargeID) {
		this.chargeID = chargeID;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getServnumber() {
		return servnumber;
	}

	public void setServnumber(String servnumber) {
		this.servnumber = servnumber;
	}

	public String getPrivId() {
		return privId;
	}

	public void setPrivId(String privId) {
		this.privId = privId;
	}

	public String getPrivNcode() {
		return privNcode;
	}

	public void setPrivNcode(String privNcode) {
		this.privNcode = privNcode;
	}

	public String getRecDate() {
		return recDate;
	}

	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}

	public String getPrivFee() {
		return privFee;
	}

	public void setPrivFee(String privFee) {
		this.privFee = privFee;
	}

	public String getToFee() {
		return toFee;
	}

	public void setToFee(String toFee) {
		this.toFee = toFee;
	}

	public String getChargeFee() {
		return chargeFee;
	}

	public void setChargeFee(String chargeFee) {
		this.chargeFee = chargeFee;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getRecStatus() {
		return recStatus;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getRecStatusDesc() {
		return recStatusDesc;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setRecStatusDesc(String recStatusDesc) {
		this.recStatusDesc = recStatusDesc;
	}

    public String getRecType()
    {
        return recType;
    }

    public void setRecType(String recType)
    {
        this.recType = recType;
    } 
	
	
	
}
