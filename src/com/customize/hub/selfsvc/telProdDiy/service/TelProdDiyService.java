/*
 * �� �� ��:  MainProdChangeHubService.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��: yKF70747
 * �޸�ʱ��:  Apr 12, 2012
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.hub.selfsvc.telProdDiy.service;

import java.util.List;

import com.customize.hub.selfsvc.telProdDiy.model.TelProdPO;


public interface TelProdDiyService
{
    /**
     * �����û�����������Ʒ�Ʋ�ѯ����ͨ���ײ�
     * @return
     * @see
     */
    public List<TelProdPO> qryTelProdList(TelProdPO telProdPO);
}
