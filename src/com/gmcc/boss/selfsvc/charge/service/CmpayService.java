/*
 * �ļ�����CmpayService.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * �������ֻ�֧�����˻���ֵ��¼��־
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-12-24
 * �޸����ݣ�����
 */
package com.gmcc.boss.selfsvc.charge.service;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;

/**
 * 
 * �ֻ�֧�����˻���ֵ��¼��־
 * 
 * 
 * @author  g00140516
 * @version  1.0��2010-12-24
 * @see  
 * @since  
 */
public interface CmpayService
{
    /**
     * ��־��¼
     * 
     * @param cmpayLogPO
     * @see 
     */
    public void addCmpayLog(CardChargeLogVO cmpayLogPO);
    
    /**
     * ������־��¼
     * 
     * @param cmpayLogPO
     * @see 
     */
    public void updateCmpayLog(CardChargeLogVO cmpayLogPO);
    
    /**
     * ��ȡ�ɷ���־OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID();
}
