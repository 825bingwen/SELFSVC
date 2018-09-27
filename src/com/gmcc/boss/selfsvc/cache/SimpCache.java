/*
 * 文件名：SimpCache.java
 * 版权：CopyRight 2000-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：H60010815
 * 修改时间：2006-4-27
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.cache;

import java.util.HashMap;
import java.util.Vector;

/**
 * @author H60010815
 * @version 1.0 2006.4.27
 */
class SimpCache implements Cache
{

    // 对缓存的对象数进行限制，只能在构造SimpCache对象时设定，不能改变
//    private int Max_Size = 200;

    // 存在同样的key值,value对象是否替换的标记
    private boolean sameKeyReplaceValue = true;

    private Vector keys;

    private HashMap map;

    public SimpCache() {
//        map = new HashMap(Max_Size);
//        keys = new Vector(Max_Size);
        map = new HashMap();
        keys = new Vector();
    }

    public SimpCache(int maxSize) {
//        Max_Size = maxSize;
//        map = new HashMap(Max_Size);
//        keys = new Vector(Max_Size);
    	map = new HashMap();
        keys = new Vector();
    }

    /*
     * 返回指定key的cache (non-Javadoc)
     * 
     * @see com.huawei.nser.cache.Cache#getCachedData(java.lang.Object)
     */
    public Object getCachedData(Object cacheKey)
    {
        return map.get(cacheKey);
    }

    /*
     * 添加cache 如果添加的内容已经在cache中存在，则更新 (non-Javadoc)
     * 
     * @see com.huawei.nser.cache.Cache#cache(java.lang.Object,
     *      java.lang.Object)
     */
    public synchronized void cache(Object cacheKey, Object obj)
    {
        /**
         * 检查是否已有同样cacheKey的对象存在 如果有存在，而替换的标记被标为假的话，就不替换
         */
        if ((!sameKeyReplaceValue) && (map.containsKey(cacheKey)))
        {
            return;
        }

        // 如果缓存的对象数已达到最大限制，就删除掉一个缓存对象
//        if (keys.size() >= Max_Size)
//        {
//            removeOne();
//        }
        if (!map.containsKey(cacheKey))
        {
            keys.add(cacheKey);
        }
        map.put(cacheKey, obj);
    }

    /**
     * 删除掉缓存的第一个对象x
     */
    //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
    /*
    private void removeOne()
    {
        map.remove(keys.remove(0));
    }
     */
    //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
    
    /*
     * (non-Javadoc)
     * 
     * @see com.huawei.nser.cache.Cache#getCachedSize()
     */
    public long getCachedSize()
    {
        return map.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huawei.nser.cache.Cache#remove(java.lang.Object)
     */
    public Object remove(Object cacheKey)
    {
        keys.removeElement(cacheKey);
        return map.remove(cacheKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huawei.nser.cache.Cache#clear()
     */
    public void clear()
    {
        keys.clear();
        map.clear();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huawei.nser.cache.Cache#contains(java.lang.Object)
     */
    public boolean contains(Object cacheKey)
    {
        return map.containsKey(cacheKey);
    }
}
