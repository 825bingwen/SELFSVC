/*
 * �ļ�����TerminalInfoService.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ���������ն��йص����ݿ����
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ�����
 */
package com.gmcc.boss.selfsvc.terminfo.service;

import java.util.List;

import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ���ն���صĲ���
 * 
 * @author g00140516
 * @version 1.0��2010-11-30
 * @see 
 * @since 
 */
public interface TerminalInfoService
{
    /**
     * ����ip��ȡ�ն���Ϣ
     * 
     * @param IP
     * @return
     * @see 
     */
    public List getTerminalInfoByIP(String IP);
    
    /**
     * ���ݳ��̺���������ͻ�ȡ�����Ϣ 
     * 
     * @param infoPO
     * @return
     * @see 
     */
    public List getPluginInfo(TerminalInfoPO infoPO);
    
    /**
     * ����termid��ȡ���ò˵�ID�б� 
     * 
     * @param termid
     * @return
     * @see 
     */
    public List getMenuIDList(String termid);
    
    // add begin cKF48754 2011/11/01 R003C11L11n01 OR_huawei_201111_149
    /**
     * ����SH_TERM_CONFIG��IP
     * 
     * @param termInfoPO
     * @see
     */
    public void updateTermConfigIp(TerminalInfoPO termInfoPO);
    
    /**
     * �ж��ն˻�IP�Ƿ��Ѿ�����
     * 
     * @param termInfoPO
     * @see
     */
    public boolean isTerminalExisted(TerminalInfoPO termInfoPO);
    // add end cKF48754 2011/11/01 R003C11L11n01 OR_huawei_201111_149
    
    /**
     * ����MAC��ַ��ȡ�ն˻���Ϣ
     * 
     * @param mac �ն˻�mac��ַ
     * @return �ն˻���Ϣ�б�
     * @exception/throws 
     * @see 
     * @depreced
     * @remark create g00140516 2011��11��11 R003C11L11n01 OR_huawei_201111_149
     */
    public List getTerminalInfoByMAC(String mac);
    
    // add begion yKF28472 OR_huawei_201305_474
    /**
     * ȡ�м�orgId ��:����HB.JH.01.03���߼���λΪHB.JH
     * <������ϸ����>
     * @param orgId
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String getCityOrgId(String orgId);
    // add end yKF28472 OR_huawei_201305_474
    
    // add begin hWX5316476 OR_huawei_201305_1248 
    /**
     * �����ն˻���orgid��ȡ��֯������orgtype <������ϸ����>
     * @param termInfoPO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String getOrgtype(TerminalInfoPO termInfoPO);
    // add end hWX5316476 OR_huawei_201305_1248
}
