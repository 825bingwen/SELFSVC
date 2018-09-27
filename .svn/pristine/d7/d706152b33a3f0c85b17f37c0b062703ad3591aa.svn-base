/*
 * 文件名：PublicCache.java
 * 版权：CopyRight 2000-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：H60010815
 * 修改时间：2006-4-27
 * 修改内容：新增
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：修改，以满足自助终端的需要
 */
package com.gmcc.boss.selfsvc.cache;

/**
 * 系统缓存管理类 采用单例模式
 * 
 * @author H60010815, g00140516
 * @version 2.0 2010.11.30
 */
public class PublicCache implements Cache
{
    
    private static int Max_Size = 200; // cache的最大值
    
    private static PublicCache instance = null; // PublicCache的实例，单例摸式
    
    private Cache _cache; // cache类
    
    /**
     * 初始化cache类
     */
    private PublicCache()
    {
        _cache = new SimpCache(Max_Size);
    }
    
    /**
     * 返回类的实例
     * 
     * @return
     */
    public static synchronized PublicCache getInstance()
    {
    	if (instance == null)
    	{
    		instance = new PublicCache();
    	}
    	
        return instance;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.huawei.nser.cache.Cache#cache(java.lang.Object, java.lang.Object)
     */
    public void cache(Object cacheKey, Object obj)
    {
        _cache.cache(cacheKey, obj);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.huawei.nser.cache.Cache#getCachedData(java.lang.Object)
     */
    public Object getCachedData(Object cacheKey)
    {
        return _cache.getCachedData(cacheKey);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.huawei.nser.cache.Cache#getCachedSize()
     */
    public long getCachedSize()
    {
        return _cache.getCachedSize();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.huawei.nser.cache.Cache#remove(java.lang.Object)
     */
    public Object remove(Object cacheKey)
    {
        return _cache.remove(cacheKey);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.huawei.nser.cache.Cache#clear()
     */
    public void clear()
    {
        _cache.clear();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.huawei.nser.cache.Cache#contains(java.lang.Object)
     */
    public boolean contains(Object cacheKey)
    {
        return _cache.contains(cacheKey);
    }
    
}
