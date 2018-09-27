/*
 * �ļ�����PublicCache.java
 * ��Ȩ��CopyRight 2000-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�H60010815
 * �޸�ʱ�䣺2006-4-27
 * �޸����ݣ�����
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ��޸ģ������������ն˵���Ҫ
 */
package com.gmcc.boss.selfsvc.cache;

/**
 * ϵͳ��������� ���õ���ģʽ
 * 
 * @author H60010815, g00140516
 * @version 2.0 2010.11.30
 */
public class PublicCache implements Cache
{
    
    private static int Max_Size = 200; // cache�����ֵ
    
    private static PublicCache instance = null; // PublicCache��ʵ����������ʽ
    
    private Cache _cache; // cache��
    
    /**
     * ��ʼ��cache��
     */
    private PublicCache()
    {
        _cache = new SimpCache(Max_Size);
    }
    
    /**
     * �������ʵ��
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
