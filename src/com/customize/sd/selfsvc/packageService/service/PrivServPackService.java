package com.customize.sd.selfsvc.packageService.service;

import java.util.List;

import com.customize.sd.selfsvc.packageService.model.PrivServPackPO;

/**
 * 特惠业务包Service
 * @author  hWX5316476
 * @version  [版本号, Dec 22, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface PrivServPackService
{
    /**
     * 查询特惠业务包列表
     * @param privServPackPO
     * @return
     */
    public List<PrivServPackPO> qryPrivServPackList(PrivServPackPO privServPackPO);
    
    /**
     * 查询特惠业务包详情
     * @param privServPackPO
     * @return
     */
    public PrivServPackPO qryPrivServPackDetail(PrivServPackPO privServPackPO);
}
