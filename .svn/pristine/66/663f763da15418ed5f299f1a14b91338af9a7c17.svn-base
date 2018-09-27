/*
 * 文件名：ResDataServiceImpl.java
 * 版权：CopyRight 2000-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：自助终端资源数据 ServiceImpl
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

import com.gmcc.boss.selfsvc.resdata.dao.ResDataDaoImpl;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.TermInfosPO;

/**
 * 自数据库中获取自助终端所有的配置数据、菜单数据等
 * 
 * @author z00107005
 * @version 1.0 Feb 2, 2008
 */
public class ResDataServiceImpl implements ResDataService
{
    private ResDataDaoImpl resDataDaoImpl;
    
    /**
     * 自SH_PARAM表读取所有的配置信息
     * @return
     */
    public List getAllResDataList()
    {
        return resDataDaoImpl.getAllResDataList();
    }
    
    /**
     * 获取菜单数据
     * 
     * @return
     */
    public List getAllMenuInfoList()
    {
        return resDataDaoImpl.getAllMenuInfoList();
    }
    
    /**
     * 获取region数据
     * @return
     */
    public List getAllRegionInfoList()
    {
        return resDataDaoImpl.getAllRegionInfoList();
    }
    
    /**
     * 获取字典数据
     * 
     * @return
     */
    public List<DictItemPO> getDictItems()
    {
    	return resDataDaoImpl.getDictItems();
    }
    
    /**
     * 获取sh_info_rectel信息
     * 
     * @return
     * @see
     * @remark create g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
     */
    public Map<String, String> getRectelInfo()
    {
        return resDataDaoImpl.getRectelInfo();
    }
    
    public ResDataDaoImpl getResDataDaoImpl()
    {
        return resDataDaoImpl;
    }
    
    public void setResDataDaoImpl(ResDataDaoImpl resDataDaoImpl)
    {
        this.resDataDaoImpl = resDataDaoImpl;
    }
    
    /**
     * 获取终端设备属性数据 add lwx439898 2017-10-12 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    public List<TermInfosPO> getAllTermInfos()
    {
        return resDataDaoImpl.getAllTermInfos();
    }
    
}
