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
package com.customize.hub.selfsvc.prodChange.service;

import java.util.List;

import com.customize.hub.selfsvc.prodChange.model.MainProdTempletInfoPO;
import com.customize.hub.selfsvc.prodChange.model.MainProdTempletPO;
import com.customize.hub.selfsvc.prodChange.model.ProdLogVO;

/**
 * 主体产品变更
 * @author  yKF70747
 * @version  [版本号, Apr 12, 2012]
 * @see  
 * @since 
 */
public interface MainProdChangeHubService
{
    /**
     * 根据用户的当前主体产品ID、归属地区、品牌查询可转换的主体产品信息列表
     * @return
     * @see
     */
    public List<MainProdTempletInfoPO> qryMainProdTempChangeList(MainProdTempletPO mainProdTempletPO);
    
    /**
     * 添加日志记录
     * 
     * @param prodLogVO
     */
    public void createProdChangeLog(ProdLogVO prodLogVO);
}
