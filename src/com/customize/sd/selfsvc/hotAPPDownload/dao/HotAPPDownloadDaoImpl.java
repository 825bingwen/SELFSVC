/*
 * 文 件 名:  HotAPPDownloadServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <热门APP应用下载Dao>
 * 修 改 人:  jWX216858
 * 修改时间:  Jun 26, 2015
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.hotAPPDownload.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.customize.sd.selfsvc.hotAPPDownload.model.APPInfoPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * <热门APP应用下载Dao>
 * <连接、操作数据库>
 * 
 * @author  jWX216858
 * @version  [版本号, Jun 26, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本 OR_SD_201506_152_山东_在自助终端增加“热门APP应用”下载]
 */
@Repository
public class HotAPPDownloadDaoImpl extends BaseDaoImpl
{
	@Autowired
    public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate)
    {
        super.setSqlMapClientTemplate(sqlMapClientTemplate);
    }
	
	/**
     * <获取所有的app应用>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
	public List<APPInfoPO> getAppInfoList()
	{
		return (List<APPInfoPO>)sqlMapClientTemplate.queryForList("hotAppDownload.qryAppInfoList");
	}
	
	/**
     * <根据app应用id获取app描述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
	public APPInfoPO getAppInfoById(String appId)
	{
		return (APPInfoPO)sqlMapClientTemplate.queryForObject("hotAppDownload.getAppInfo", appId);
	}
}
