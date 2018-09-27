/*
* @filename: RectelInfoDaoImpl.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  业务推荐营业员的手机号码维护类
* @author: g00140516
* @de:  2012/07/04 
* @description: 
* @remark: create g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
*/
package com.customize.nx.selfsvc.rectelinfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 
 * 业务推荐营业员的手机号码维护类
 * 
 * @author  g00140516
 * @version  1.0, 2012/07/04
 * @see  
 * @since  
 */
public class RectelInfoDaoImpl extends BaseDaoImpl
{
    private static Log logger = LogFactory.getLog(RectelInfoDaoImpl.class);
    
    /**
     * 根据终端机ID查询对应的业务推荐营业员的手机号码
     * 
     * @param termid
     * @return
     * @see 
     */
    public String getRectelInfoWithTermid(String termid)
    {
        try
        {
            List list = sqlMapClientTemplate.queryForList("rectel.getRectelInfoWithTermid", termid);
            if (list != null && list.size() > 0)
            {
                Map<String, String> recordMap = (Map<String, String>) list.get(0);
                
                return recordMap.get("TELNUMBER");
            }
        }
        catch (Exception e)
        {
            logger.error("根据终端机ID查询对应的业务推荐营业员的手机号码失败：", e);
        }        

        return "";
    }
    
    /**
     * 注销终端机对应的业务推荐营业员的手机号码
     * 
     * @param termid
     * @see 
     */
    public void deleteRectelWithTermid(String termid)
    {
        try
        {
            sqlMapClientTemplate.delete("rectel.deleteRectelWithTermid", termid);
        }
        catch (Exception e)
        {
            logger.error("注销终端机(" + termid + ")对应的业务推荐营业员的手机号码：", e);
        }
    }
    
    /**
     * 维护终端机对应的业务推荐营业员的手机号码
     * 
     * @param termid
     * @see 
     */
    public void insertRectelWithTermid(Map<String, String> map)
    {
        try
        {
            sqlMapClientTemplate.insert("rectel.insertRectelWithTermid", map);
        }
        catch (Exception e)
        {
            logger.error("维护终端机对应的业务推荐营业员的手机号码：", e);
        }        
    }
}
