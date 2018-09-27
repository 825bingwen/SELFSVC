/*
 * 文 件 名:  PrestoredRewardServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  预存有礼Service接口
 * 修 改 人:  jWX216858
 * 修改时间:  2014-11-28
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.prestoredReward.service;

import java.util.List;

import com.customize.sd.selfsvc.prestoredReward.model.ActivityLogPO;
import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;

/**
 * 预存有礼Service接口
 * 
 * @author  jWX216858
 * @version  [版本号, 2014-11-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IPrestoredRewardService
{
	/** 
     * 获取促销活动组
     * 
     * @return List<PrestoredRewardPO>
     * @see [类、类#方法、类#成员]
     */
	public List<PrestoredRewardPO> getActivitiesGroups(PrestoredRewardPO prestoredRewardPO);
	
	/** 
     * 获取所有活动列表
     * 
     * @return List<PrestoredRewardPO>
     * @see [类、类#方法、类#成员]
     */
	public List<PrestoredRewardPO> getActivitiesList(PrestoredRewardPO prestoredRewardPO);
	
	/**
     * 获取缴费日志OID
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getChargeLogOID();
    
    /**
     * 增加缴费日志
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * 更新缴费日志状态
     * @param CardChargeLogVO
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * 增加受理记录
     * <功能详细描述>
     * @param activityLogPO
     * @see [类、类#方法、类#成员]
     */
    public void createActivityLog(ActivityLogPO activityLogPO);

}
