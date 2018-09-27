/*
 * �ļ�����CmpayAction.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * �������ֻ�֧�����˻���ֵ��¼��־
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-12-24
 * �޸����ݣ�����
 */
package com.gmcc.boss.selfsvc.charge.dao;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 
 * �ֻ�֧�����˻���ֵ��¼��־
 * 
 * 
 * @author g00140516
 * @version 1.0��2010-12-24
 * @see
 * @since
 */
public class CmpayDaoImpl extends BaseDaoImpl
{
    /**
     * ��־��¼
     * 
     * 
     * @param cmpayLogPO
     * @see
     */
    public void addCmpayLog(CardChargeLogVO cmpayLogPO)
    {
        this.sqlMapClientTemplate.insert("charge.insertCmpayLog", cmpayLogPO);
    }
    
    /**
     * ������־��¼
     * 
     * 
     * @param cmpayLogPO
     * @see
     */
    public void updateCmpayLog(CardChargeLogVO cmpayLogPO)
    {
        this.sqlMapClientTemplate.insert("charge.updateCmpayLog", cmpayLogPO);
    }
    
    /**
     * ��ȡ�ɷ���־OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID()
    {
        return (String) this.sqlMapClientTemplate.queryForObject("charge.getChargeLogOID");
    }
}
