/*
 * 文 件 名:  BusiAcceptTimeServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <受理时间表的操作>
 * 修 改 人:  zWX176560
 * 修改时间:  Sep 6, 2013
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <新增>
 */
package com.gmcc.boss.selfsvc.busiAcceptTime.service;

import java.sql.SQLException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.gmcc.boss.selfsvc.busiAcceptTime.dao.BusiAcceptTimeDaoImpl;
import com.gmcc.boss.selfsvc.busiAcceptTime.model.BusiAcceptTimePO;

/**
 * <受理时间表的操作>
 * <功能详细描述>
 * 
 * @author  zWX176560
 * @version  2013/09/05 OR_SD_201308_934 R003C13L09n01
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BusiAcceptTimeServiceImpl implements BusiAcceptTimeService
{
    private Log logger = LogFactory.getLog(BusiAcceptTimeServiceImpl.class);
    
    /**
     * 对受理时间表的操作Dao
     */
    private BusiAcceptTimeDaoImpl busiAcceptTimeDaoImpl;
    
    /**
     * 将业务受理信息插入表中
     * <功能详细描述>
     * @param busiAcceptTimePO
     * @throws SQLException 
     * @see [类、类#方法、类#成员]
     */
    public void insertBusiAcceptTime(BusiAcceptTimePO busiAcceptTimePO) throws SQLException
    {
       logger.debug("insertBusiAcceptTime starting...");
       
       this.busiAcceptTimeDaoImpl.insertBusiAcceptTime(busiAcceptTimePO);
       
       logger.debug("insertBusiAcceptTime ending...");
    }

    public BusiAcceptTimeDaoImpl getBusiAcceptTimeDaoImpl()
    {
        return busiAcceptTimeDaoImpl;
    }

    public void setBusiAcceptTimeDaoImpl(BusiAcceptTimeDaoImpl busiAcceptTimeDaoImpl)
    {
        this.busiAcceptTimeDaoImpl = busiAcceptTimeDaoImpl;
    }

    

}
