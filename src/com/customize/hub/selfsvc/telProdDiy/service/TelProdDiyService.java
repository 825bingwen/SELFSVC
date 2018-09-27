/*
 * 文 件 名:  MainProdChangeHubService.java
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

import com.customize.hub.selfsvc.telProdDiy.model.TelProdPO;


public interface TelProdDiyService
{
    /**
     * 根据用户归属地区、品牌查询语音通话套餐
     * @return
     * @see
     */
    public List<TelProdPO> qryTelProdList(TelProdPO telProdPO);
}
