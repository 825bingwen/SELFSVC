/*
 * �� �� ��:  IRecommend4GService.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  4G�ն��Ƽ�service�ӿ�
 * �� �� ��:  jWX216858
 * �޸�ʱ��:  2014-12-19
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.sd.selfsvc.recommend4G.service;

import java.util.List;

import com.customize.sd.selfsvc.recommend4G.model.Recommend4GPO;

/**
 * 4G�ն��Ƽ�service�ӿ�
 * 
 * @author  jWX216858
 * @version  [�汾��, 2014-12-19]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ����� 
 */
public interface IRecommend4GService 
{
	/** 
     * ��ȡ�ֻ��б�
     * 
     * @return String
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2014-12-22 OR_SD_201410_482_���������ն�����Ԥ�����ͻ������ն������Ƽ����ܵ�����
     */
	public List<Recommend4GPO> getPhoneList(Recommend4GPO recommend4GPO);
}
