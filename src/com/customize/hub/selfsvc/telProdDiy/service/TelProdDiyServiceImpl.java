/*
 * �� �� ��:  MainProdChangeServiceHubImpl.java
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

import com.customize.hub.selfsvc.telProdDiy.dao.TelProdDiyDaoImpl;
import com.customize.hub.selfsvc.telProdDiy.model.TelProdPO;

public class TelProdDiyServiceImpl implements TelProdDiyService
{
    private TelProdDiyDaoImpl telProdDiyDaoImpl;
    
    public List<TelProdPO> qryTelProdList(TelProdPO telProdPO)
    {
        return telProdDiyDaoImpl.qryTelProdList(telProdPO);
    }
    
    public TelProdDiyDaoImpl getTelProdDiyDaoImpl()
    {
        return telProdDiyDaoImpl;
    }
    
    public void setTelProdDiyDaoImpl(TelProdDiyDaoImpl telProdDiyDaoImpl)
    {
        this.telProdDiyDaoImpl = telProdDiyDaoImpl;
    }
    
}
