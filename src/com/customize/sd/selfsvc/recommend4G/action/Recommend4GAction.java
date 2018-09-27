/*
 * �� �� ��:  Recommend4GAction.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  4G�ն��Ƽ�Action��
 * �� �� ��:  jWX216858
 * �޸�ʱ��:  2014-12-19
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.recommend4G.action;

import java.util.ArrayList;
import java.util.List;

import com.customize.sd.selfsvc.recommend4G.model.Recommend4GPO;
import com.customize.sd.selfsvc.recommend4G.service.IRecommend4GService;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 4G�ն��Ƽ�Action��
 * 
 * @author  jWX216858
 * @version  [�汾��, 2014-12-19]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ����� 
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
	 * �˵� 
	 */
	private String curMenuId;
	
	/**
	 * �ֻ���Ϣ����
	 */
	private List<Recommend4GPO> phoneInfoList = new ArrayList<Recommend4GPO>();
	
	/**
	 * �ֻ�����ͼƬ
	 */
	private String descAdobe = "";
	
	/**
	 * ������Ϣ
	 */
	private String errormessage;
	
	/**
	 * �ֻ�����ͼƬ����
	 */
	private List<String> descAdobes = new ArrayList<String>();
	
	/** 
     * ��ȡ�ֻ��б�
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ�����
     */
	public String getPhoneList()
	{
		Recommend4GPO recommend4GPO = new Recommend4GPO();
		
		// ����
		recommend4GPO.setRegion(getTerminalInfoPO().getRegion());
		
		// ��֯����
		String orgid = getTerminalInfoPO().getOrgid();
		recommend4GPO.setOrgid(CommonUtil.splitOrgid(orgid));
		
		phoneInfoList = recommend4GService.getPhoneList(recommend4GPO);
		
		if (phoneInfoList.size() == 0)
		{
			errormessage = "û�в�ѯ����Ӧ���ֻ���Ϣ";
			return "error";
		}
		
		for (Recommend4GPO recommend4G : phoneInfoList)
		{
			recommend4G.setPhonePrice(CommonUtil.fenToYuan(recommend4G.getPhonePrice()));
		}
		
		return SUCCESS;
	}
	
	/** 
     * �ֻ�����չʾ
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ�����
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
