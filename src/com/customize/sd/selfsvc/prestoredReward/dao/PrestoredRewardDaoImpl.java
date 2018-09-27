/*
 * 文 件 名:  PrestoredRewardDaoImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  预存有礼Dao类
 * 修 改 人:  jWX216858
 * 修改时间:  2014-11-28
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.prestoredReward.dao;

import java.util.List;

import com.customize.sd.selfsvc.prestoredReward.model.ActivityLogPO;
import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 预存有礼Dao类
 * 
 * @author  jWX216858
 * @version  [版本号, 2014-11-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PrestoredRewardDaoImpl extends BaseDaoImpl
{
	/** 
     * 获取促销活动组
     * 
     * @return List<PrestoredRewardPO>
     * @see [类、类#方法、类#成员]
     */
	public List<PrestoredRewardPO> getActivitiesGroups(PrestoredRewardPO prestoredRewardPO)
	{
		return sqlMapClientTemplate.queryForList("prestoredReward.getActivitiesGroups",prestoredRewardPO);
	}
	
	/** 
     * 根据促销活动组查询档次
     * 
     * @return List<PrestoredRewardPO>
     * @see [类、类#方法、类#成员]
     */
	public List<PrestoredRewardPO> getAllActLevels()
	{
		return sqlMapClientTemplate.queryForList("prestoredReward.getAllActLevels");
	}
	
	/**
     * 获取缴费日志OID
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getChargeLogOID()
    {
        return (String)sqlMapClientTemplate.queryForObject("charge.getChargeLogOID");
    }
    
    /**
     * 增加缴费日志
     * 
     * @param cardChargeLogVO 缴费信息
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.insertChargeLog", cardChargeLogVO);
    }
    
    /** 
     * 更新缴费日志
     * 
     * @return List<PrestoredRewardPO>
     * @see [类、类#方法、类#成员]
     */
	public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
	{
		sqlMapClientTemplate.update("prestoredReward.updateCardChargeLog", cardChargeLogVO);
	}
	
	/**
     * 增加受理记录
     * <功能详细描述>
     * @param activityLogPO
     * @see [类、类#方法、类#成员]
     */
    public void createActivityLog(ActivityLogPO activityLogPO)
    {
    	sqlMapClientTemplate.insert("prestoredReward.createActivityLog", activityLogPO);
    }
}
