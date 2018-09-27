/*
 * 文 件 名:  APPInfoPO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <可下载的APP信息PO>
 * 修 改 人:  jWX216858
 * 修改时间:  Jun 25, 2015
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.hotAPPDownload.model;

/**
 * <可下载的APP信息PO>
 * <功能详细描述>
 * 
 * @author  jWX216858
 * @version  [版本号, Jun 25, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本 OR_SD_201506_152_山东_在自助终端增加“热门APP应用”下载]
 */
public class APPInfoPO
{
	/**
	 * 应用id，唯一标识
	 */
	private String appId;
	
	/**
	 * 应用名称
	 */
	private String appName;
	
	/**
	 * 应用描述
	 */
	private String appDesc;
	
	/**
	 * 应用类型
	 */
	private String appType;
	
	/**
	 * 短信下载地址
	 */
	private String shortAddress;
	
	/**
	 * 应用图标地址
	 */
	private String appIcon;
	
	/**
	 * 应用二维码地址
	 */
	private String appTWOCode;
	
	/**
	 * 顺序，按应用类型分组后，进行排序
	 */
	private String sortOrder;
	
	/**
	 * 应用名称
	 */
	private String appTypeName;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getShortAddress() {
		return shortAddress;
	}

	public void setShortAddress(String shortAddress) {
		this.shortAddress = shortAddress;
	}

	public String getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}

	public String getAppTWOCode() {
		return appTWOCode;
	}

	public void setAppTWOCode(String appTWOCode) {
		this.appTWOCode = appTWOCode;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppTypeName() {
		return appTypeName;
	}

	public void setAppTypeName(String appTypeName) {
		this.appTypeName = appTypeName;
	}
	
}
