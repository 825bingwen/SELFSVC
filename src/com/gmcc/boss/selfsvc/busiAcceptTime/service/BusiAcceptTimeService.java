/*
 * �� �� ��:  BusiAcceptTimeService.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����ʱ���Ĳ���>
 * �� �� ��:  zWX176560
 * �޸�ʱ��:  Sep 6, 2013
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <����>
 */
package com.gmcc.boss.selfsvc.busiAcceptTime.service;

import java.sql.SQLException;

import com.gmcc.boss.selfsvc.busiAcceptTime.model.BusiAcceptTimePO;

/**
 * <����ʱ���Ĳ���>
 * <������ϸ����>
 * 
 * @author  zWX176560
 * @version  2013/09/05 OR_SD_201308_934 R003C13L09n01
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public interface BusiAcceptTimeService
{
    /**
     * ��ҵ��������Ϣ�������
     * <������ϸ����>
     * @param busiAcceptTimePO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void insertBusiAcceptTime(BusiAcceptTimePO busiAcceptTimePO)throws SQLException;
}
