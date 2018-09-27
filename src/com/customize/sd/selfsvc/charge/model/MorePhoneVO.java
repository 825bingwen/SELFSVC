/*
 * 文 件 名:  MorePhoneVO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  话费连缴VO
 * 修 改 人:  jWX216858
 * 修改时间:  2015-3-30
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */

package com.customize.sd.selfsvc.charge.model;

import com.gmcc.boss.selfsvc.common.BasePO;

/**
 * 话费连缴VO
 * 
 * @author jWX216858
 * @version [版本号, 2015-3-30]
 * @see [相关类/方法]
 * @since [产品/模块版本]OR_SD_201501_1063 自助终端支撑连缴功能优化 
 */
public class MorePhoneVO extends BasePO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5446530081292383470L;

	/**
	 * 手机号
	 */
	private String servnumber;
	
	/**
	 * 话费余额
	 */
	private String balance;
	
	/**
	 * 应缴费用
	 */
    private String yingjiaoFee;
    
    /**
     * region
     */
    private String region;
    
    /**
     * 客户名称
     */
    private String custName;
    
    /**
     * 受理类型
     */
    private String accepttype;
    
    /**
     * 受理流水 打印发票用
     */
    private String recoid;
    
    /**
     * 受理编号 
     */
    private String dealNum;
    
    /**
     * 缴费流水
     */
    private String chargeId;
    
    /**
     * 终端交易流水
     */
    private String terminalSeq;
    
    /**
     * 缴费状态，1：成功，0：失败
     */
    private String status;
    
    /**
     * 地市名称
     */
    private String regionName;
    
    /**
     * 跨区缴费标志，0：跨区
     */
    private String regionFlag;
    
    /**
     * 实缴费用
     */
    private String tMoney;
    
	public String getServnumber() {
		return servnumber;
	}

	public void setServnumber(String servnumber) {
		this.servnumber = servnumber;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getYingjiaoFee() {
		return yingjiaoFee;
	}

	public void setYingjiaoFee(String yingjiaoFee) {
		this.yingjiaoFee = yingjiaoFee;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAccepttype() {
		return accepttype;
	}

	public void setAccepttype(String accepttype) {
		this.accepttype = accepttype;
	}

	public String getRecoid() {
		return recoid;
	}

	public void setRecoid(String recoid) {
		this.recoid = recoid;
	}

	public String getDealNum() {
		return dealNum;
	}

	public void setDealNum(String dealNum) {
		this.dealNum = dealNum;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public String getTerminalSeq() {
		return terminalSeq;
	}

	public void setTerminalSeq(String terminalSeq) {
		this.terminalSeq = terminalSeq;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionFlag() {
		return regionFlag;
	}

	public void setRegionFlag(String regionFlag) {
		this.regionFlag = regionFlag;
	}

	public String getTMoney() {
		return tMoney;
	}

	public void setTMoney(String money) {
		tMoney = money;
	}
    
}
