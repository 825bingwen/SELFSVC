/*
 * 文 件 名:  MainProdChangeServiceHubImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人: yKF70747
 * 修改时间:  Apr 12, 2012
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
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
