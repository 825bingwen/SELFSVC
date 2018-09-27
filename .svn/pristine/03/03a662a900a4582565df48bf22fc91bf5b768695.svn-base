/*
 * 文件名：Cache.java
 * 版权：CopyRight 2000-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：H60010815
 * 修改时间：2006-4-27
 * 修改内容：新增
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：修改，以满足自助终端的需要，保存配置信息、菜单数据、region等常用的信息
 * 
 */
package com.gmcc.boss.selfsvc.cache;

/**
 * @author H60010815, g00140516
 * @version 2.0 2010.11.30
 */
public interface Cache
{
    /**
     * 向缓存中存放数据
     * 
     * @param cacheKey
     * @param obj
     */
    public void cache(Object cacheKey, Object obj);
    
    /**
     * 根据key值自缓存中获取对应的value
     * 
     * @param cacheKey
     * @return
     */
    public Object getCachedData(Object cacheKey);
    
    /**
     * 返回cache的大小
     * 
     * @return
     */
    public long getCachedSize();
    
    /**
     * 查询cacheKey是否已经存在于缓存中
     * 
     * @param cacheKey
     * @return
     */
    public boolean contains(Object cacheKey);
    
    /**
     * 自缓存中删除指定的key值
     * 
     * @param cacheKey
     * @return
     */
    public Object remove(Object cacheKey);
    
    /**
     * 清空缓存
     * 
     * @return
     */
    public void clear();
    
}
