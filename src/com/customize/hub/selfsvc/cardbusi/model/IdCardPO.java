/*
 * 文 件 名:  IdCardPO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  sWX219697
 * 修改时间:  Oct 25, 2014
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.cardbusi.model;

/**
 * <身份证信息>
 * <功能详细描述>
 * 
 * @author  sWX219697
 * @version  [版本号, Oct 25, 2014]
 * @see  [相关类/方法]
 * @since  OR_HUB_201405_478_湖北_关于自助终端支持现场写_卡的需求(空白卡开户、补卡)
 */
public class IdCardPO 
{
	/**
	 * 身份证内容
	 */
    private String idCardContent;
    
    /**
     * 照片
     */
    private String idCardPhoto;
    
    /**
     * 姓名
     */
    private String idCardName;
    
    /**
     * 开始时间
     */
    private String idCardStartTime;
    
    /**
     * 开始时间
     */
    private String idCardEndTime;
    
    /**
     * 性别
     */
    private String idCardSex;

    /**
     * 身份证号码
     */
    private String idCardNo;
    
    /**
     * 地址
     */
    private String idCardAddr;

	public void setIdCardContent(String idCardContent) {
		this.idCardContent = idCardContent;
	}

	public void setIdCardPhoto(String idCardPhoto) {
		this.idCardPhoto = idCardPhoto;
	}

	public void setIdCardName(String idCardName) {
		this.idCardName = idCardName;
	}

	public void setIdCardStartTime(String idCardStartTime) {
		this.idCardStartTime = idCardStartTime;
	}
	
	public String getIdCardContent() {
		return idCardContent;
	}
	
	public String getIdCardPhoto() {
		return idCardPhoto;
	}
	
	public String getIdCardName() {
		return idCardName;
	}
	
	public String getIdCardStartTime() {
		return idCardStartTime;
	}

	public String getIdCardEndTime() {
		return idCardEndTime;
	}
	
	public String getIdCardSex() {
		return idCardSex;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String getIdCardAddr() {
		return idCardAddr;
	}
	
	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardEndTime(String idCardEndTime) {
		this.idCardEndTime = idCardEndTime;
	}

	public void setIdCardSex(String idCardSex) {
		this.idCardSex = idCardSex;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public void setIdCardAddr(String idCardAddr) {
		this.idCardAddr = idCardAddr;
	}

}
