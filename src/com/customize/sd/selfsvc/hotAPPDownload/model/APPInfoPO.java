/*
 * �� �� ��:  APPInfoPO.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <�����ص�APP��ϢPO>
 * �� �� ��:  jWX216858
 * �޸�ʱ��:  Jun 25, 2015
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.hotAPPDownload.model;

/**
 * <�����ص�APP��ϢPO>
 * <������ϸ����>
 * 
 * @author  jWX216858
 * @version  [�汾��, Jun 25, 2015]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾 OR_SD_201506_152_ɽ��_�������ն����ӡ�����APPӦ�á�����]
 */
public class APPInfoPO
{
	/**
	 * Ӧ��id��Ψһ��ʶ
	 */
	private String appId;
	
	/**
	 * Ӧ������
	 */
	private String appName;
	
	/**
	 * Ӧ������
	 */
	private String appDesc;
	
	/**
	 * Ӧ������
	 */
	private String appType;
	
	/**
	 * �������ص�ַ
	 */
	private String shortAddress;
	
	/**
	 * Ӧ��ͼ���ַ
	 */
	private String appIcon;
	
	/**
	 * Ӧ�ö�ά���ַ
	 */
	private String appTWOCode;
	
	/**
	 * ˳�򣬰�Ӧ�����ͷ���󣬽�������
	 */
	private String sortOrder;
	
	/**
	 * Ӧ������
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
