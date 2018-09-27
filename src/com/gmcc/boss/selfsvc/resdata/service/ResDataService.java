/*
 * 文件名：ResDataService.java
 * 版权：CopyRight 2000-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：自助终端资源数据 Service
 * 修改人：z00107005
 * 修改时间：2008-1-17
 * 修改内容：新增
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：修改，以适应自助终端的需要
 */
package com.gmcc.boss.selfsvc.resdata.service;

import java.util.List;
import java.util.Map;

import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.TermInfosPO;

/**
 * 自数据库中获取自助终端所有的配置数据、菜单数据等
 * 
 */
public interface ResDataService
{
    /**
     * 自SH_PARAM表读取所有的配置信息
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List getAllResDataList();
    
    /**
     * 获取菜单数据
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List getAllMenuInfoList();
    
    /**
     * 获取region数据
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List getAllRegionInfoList();
    
    /**
     * 获取字典数据
     * 
     * @return
     * @see
     */
    public List<DictItemPO> getDictItems();
    
    /**
     * 获取sh_info_rectel信息
     * 
     * @return
     * @see
     * @remark create g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
     */
    public Map<String, String> getRectelInfo();
    
    /**
     * 获取 Sh_Term_externattr信息:终端设备属性
     * 
     * @return
     * @see
     * @remark create lwx439898 2017-10-12 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    public List<TermInfosPO> getAllTermInfos();
    
}
