/*
 * �� �� ��:  IHotAPPDownloadService.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����APPӦ������service>
 * �� �� ��:  jWX216858
 * �޸�ʱ��:  Jun 26, 2015
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.hotAPPDownload.service;

import java.util.List;

import com.customize.sd.selfsvc.hotAPPDownload.model.APPInfoPO;

/**
 * <����APPӦ������service>
 * <������ϸ����>
 * 
 * @author  jWX216858
 * @version  [�汾��, Jun 26, 2015]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾 OR_SD_201506_152_ɽ��_�������ն����ӡ�����APPӦ�á�����]
 */
public interface IHotAPPDownloadService
{
	/**
     * <��ȡ���е�appӦ��>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	public List<List<APPInfoPO>> getAppInfoList();
	
	/**
     * <����appӦ��id��ȡapp����>
     * <������ϸ����>
     * @param appId appӦ��id��Ψһ��ʶ
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	public APPInfoPO getAppInfoById(String appId);
	
	/**
     * <���ŷ���app���ص�ַ>
     * <������ϸ����>
     * @param menuId ��ǰ�˵�
	 * @param servNumber �ֻ�����
	 * @param appInfo app��Ϣ
     * @see [�ࡢ��#��������#��Ա]
     */
	public void sendAddress(String menuId, String servNumber, APPInfoPO appInfo);
	
	/**
	 * <�û���¼����¼�ɹ�����ŷ���app���ص�ַ>
     * <������ϸ����>
	 * @param menuId ��ǰ�˵�
	 * @param servNumber �ֻ�����
	 * @param appInfo app��Ϣ
	 * @param password ��������
	 */
	public void customerLogin(String menuId, String servNumber, APPInfoPO appInfo, String password);

}
