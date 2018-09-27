/*
 * 文件名：ResDataDaoImpl.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.resdata.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.resdata.model.TermInfosPO;

/**
 * 资源类数据操作
 * 
 * @author g00140516
 * 
 */
public class ResDataDaoImpl extends BaseDaoImpl
{
    public ResDataDaoImpl()
    {
        super();
    }
    
    /**
     * 自SH_PARAM表读取所有的配置信息
     * 
     */
    public List getAllResDataList()
    {
        return sqlMapClientTemplate.queryForList("ResData.getAllResDataList", null);
    }
    
    /**
     * 读取菜单表信息
     * 
     * @return
     */
    public List getAllMenuInfoList()
    {
        return sqlMapClientTemplate.queryForList("ResData.getAllMenuInfo", null);
    }
    
    /**
     * 读取region信息
     * 
     * @return
     */
    public List getAllRegionInfoList()
    {
        return sqlMapClientTemplate.queryForList("ResData.getAllRegionInfo", null);
    }
    
    /**
     * 获取字典数据
     * 
     * @return
     * @see
     */
    public List<DictItemPO> getDictItems()
    {
        return sqlMapClientTemplate.queryForList("ResData.getDictItems");
    }
    
    /**
     * 去sh_info_rectel表的数据
     * 
     * @return Map，其中key：终端机ID；value：业务推荐营业员工号
     * @see 
     * @remark create g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
     */
    public Map<String, String> getRectelInfo()
    {
        Map<String, String> resultMap = new HashMap<String, String>();
                
        List list = sqlMapClientTemplate.queryForList("ResData.getRectelInfo");
        if (list != null && list.size() > 0)
        {
            Map recordMap = null;
            
            for (int i = 0; i < list.size(); i++)
            {
                recordMap = (Map) list.get(i);
                
                // key：终端机ID；value：业务推荐营业员工号
                resultMap.put((String) recordMap.get("TERMID"), (String) recordMap.get("TELNUMBER"));
            }
        }

        return resultMap;
    }
    
    /**
     * 获取终端设备属性数据 add lwx439898 2017-10-12 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
     */
    public List<TermInfosPO> getAllTermInfos()
    {
        return sqlMapClientTemplate.queryForList("ResData.getAllTermInfos");
    }
}
