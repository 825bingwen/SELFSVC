/*
* @filename: RectelInfoService.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  业务推荐营业员的手机号码维护类
* @author: g00140516
* @de:  2012/07/04 
* @description: 
* @remark: create g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
*/
package com.customize.nx.selfsvc.rectelinfo.service;

/**
 * 
 * 业务推荐营业员的手机号码维护类
 * 
 * @author  g00140516
 * @version  1.0, 2012/07/04
 * @see  
 * @since  
 */
public interface RectelInfoService
{
    /**
     * 根据终端机ID查询对应的业务推荐营业员的手机号码
     * 
     * @param termid
     * @return
     * @see 
     */
    public String getRectelInfoWithTermid(String termid);
    
    /**
     * 注销终端机对应的业务推荐营业员的手机号码
     * 
     * @param termid
     * @see 
     */
    public void deleteRectelWithTermid(String termid);
    
    /**
     * 维护终端机对应的业务推荐营业员的手机号码
     * 
     * @param termid
     * @see 
     */
    public void insertRectelWithTermid(String termid, String telnumber);
}
