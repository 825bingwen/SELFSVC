/*
 * 文 件 名:  Recommend4GAction.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  4G终端推荐Action类
 * 修 改 人:  jWX216858
 * 修改时间:  2014-12-19
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.recommend4G.action;

import java.util.ArrayList;
import java.util.List;

import com.customize.sd.selfsvc.recommend4G.model.Recommend4GPO;
import com.customize.sd.selfsvc.recommend4G.service.IRecommend4GService;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 4G终端推荐Action类
 * 
 * @author  jWX216858
 * @version  [版本号, 2014-12-19]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求 
 */
public class Recommend4GAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * service
	 */
	private transient IRecommend4GService recommend4GService;

	/**
	 * 菜单 
	 */
	private String curMenuId;
	
	/**
	 * 手机信息集合
	 */
	private List<Recommend4GPO> phoneInfoList = new ArrayList<Recommend4GPO>();
	
	/**
	 * 手机详情图片
	 */
	private String descAdobe = "";
	
	/**
	 * 错误信息
	 */
	private String errormessage;
	
	/**
	 * 手机详情图片数组
	 */
	private List<String> descAdobes = new ArrayList<String>();
	
	/** 
     * 获取手机列表
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
     */
	public String getPhoneList()
	{
		Recommend4GPO recommend4GPO = new Recommend4GPO();
		
		// 地区
		recommend4GPO.setRegion(getTerminalInfoPO().getRegion());
		
		// 组织机构
		String orgid = getTerminalInfoPO().getOrgid();
		recommend4GPO.setOrgid(CommonUtil.splitOrgid(orgid));
		
		phoneInfoList = recommend4GService.getPhoneList(recommend4GPO);
		
		if (phoneInfoList.size() == 0)
		{
			errormessage = "没有查询到相应的手机信息";
			return "error";
		}
		
		for (Recommend4GPO recommend4G : phoneInfoList)
		{
			recommend4G.setPhonePrice(CommonUtil.fenToYuan(recommend4G.getPhonePrice()));
		}
		
		return SUCCESS;
	}
	
	/** 
     * 手机详情展示
     * 
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_关于自助终端增加预存赠送活动办理和终端销售推荐功能的需求
     */
	public String phoneDetail()
	{
		String[] descs = descAdobe.split(",");
		
		for (int i = 0; i < descs.length; i++)
		{
			descAdobes.add(descs[i]);
		}
		
		return SUCCESS;
	}
	
	public IRecommend4GService getRecommend4GService() {
		return recommend4GService;
	}

	public void setRecommend4GService(IRecommend4GService recommend4GService) {
		this.recommend4GService = recommend4GService;
	}

	public List<Recommend4GPO> getPhoneInfoList() {
		return phoneInfoList;
	}

	public void setPhoneInfoList(List<Recommend4GPO> phoneInfoList) {
		this.phoneInfoList = phoneInfoList;
	}

	public String getDescAdobe() {
		return descAdobe;
	}

	public void setDescAdobe(String descAdobe) {
		this.descAdobe = descAdobe;
	}

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public List<String> getDescAdobes() {
		return descAdobes;
	}

	public void setDescAdobes(List<String> descAdobes) {
		this.descAdobes = descAdobes;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	
}
