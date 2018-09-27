/*
 * �� �� ��:  MorePhoneVO.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  ��������VO
 * �� �� ��:  jWX216858
 * �޸�ʱ��:  2015-3-30
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */

package com.customize.sd.selfsvc.charge.model;

import com.gmcc.boss.selfsvc.common.BasePO;

/**
 * ��������VO
 * 
 * @author jWX216858
 * @version [�汾��, 2015-3-30]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]OR_SD_201501_1063 �����ն�֧�����ɹ����Ż� 
 */
public class MorePhoneVO extends BasePO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5446530081292383470L;

	/**
	 * �ֻ���
	 */
	private String servnumber;
	
	/**
	 * �������
	 */
	private String balance;
	
	/**
	 * Ӧ�ɷ���
	 */
    private String yingjiaoFee;
    
    /**
     * region
     */
    private String region;
    
    /**
     * �ͻ�����
     */
    private String custName;
    
    /**
     * ��������
     */
    private String accepttype;
    
    /**
     * ������ˮ ��ӡ��Ʊ��
     */
    private String recoid;
    
    /**
     * ������ 
     */
    private String dealNum;
    
    /**
     * �ɷ���ˮ
     */
    private String chargeId;
    
    /**
     * �ն˽�����ˮ
     */
    private String terminalSeq;
    
    /**
     * �ɷ�״̬��1���ɹ���0��ʧ��
     */
    private String status;
    
    /**
     * ��������
     */
    private String regionName;
    
    /**
     * �����ɷѱ�־��0������
     */
    private String regionFlag;
    
    /**
     * ʵ�ɷ���
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
