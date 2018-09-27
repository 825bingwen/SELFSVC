/*
 * 文 件 名:  Recommend4GPO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  4G终端推荐PO类
 * 修 改 人:  jWX216858
 * 修改时间:  2014-12-19
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.recommend4G.model;

/**
 * 4G终端推荐PO类
 * 
 * @author  jWX216858
 * @version  [版本号, 2014-12-19]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求 
 */
public class Recommend4GPO 
{
	/**
	 * 地区
	 */
	private String region;
	
	/**
	 * 手机编码
	 */
	private String phoneId;
	
	/**
	 * 手机名称
	 */
	private String phoneName;
	
	/**
	 * 广告语
	 */
	private String message;
	
	/**
	 * 手机价格
	 */
	private String phonePrice;
	
	/**
	 * 生效日期
	 */
	private String vaildDate;
	
	/**
	 * 失效日期
	 */
	private String expireDate;
	
	/**
	 * 是否推荐 1：推荐 0：不推荐
	 */
	private String recommend;
	
	/**
	 * 排序
	 */
	private String sortOrder;
	
	/**
	 * 手机图片地址
	 */
	private String phoneAdobe;
	
	/**
	 * 描述图片地址 多个图片以逗号","分割
	 */
	private String descAdobe;

	/**
	 * 组织机构id
	 */
	private String orgid;
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	public String getPhoneName() {
		return phoneName;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPhonePrice() {
		return phonePrice;
	}

	public void setPhonePrice(String phonePrice) {
		this.phonePrice = phonePrice;
	}

	public String getVaildDate() {
		return vaildDate;
	}

	public void setVaildDate(String vaildDate) {
		this.vaildDate = vaildDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getPhoneAdobe() {
		return phoneAdobe;
	}

	public void setPhoneAdobe(String phoneAdobe) {
		this.phoneAdobe = phoneAdobe;
	}

	public String getDescAdobe() {
		return descAdobe;
	}

	public void setDescAdobe(String descAdobe) {
		this.descAdobe = descAdobe;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

}
