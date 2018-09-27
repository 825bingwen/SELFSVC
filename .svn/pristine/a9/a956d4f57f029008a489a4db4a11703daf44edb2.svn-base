/*
 * 文 件 名:  PrestoredRewardServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  预存有礼Service接口实现类
 * 修 改 人:  jWX216858
 * 修改时间:  2014-11-28
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.sd.selfsvc.prestoredReward.service;

import java.util.ArrayList;
import java.util.List;

import com.customize.sd.selfsvc.prestoredReward.dao.PrestoredRewardDaoImpl;
import com.customize.sd.selfsvc.prestoredReward.model.ActivityLogPO;
import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 预存有礼Service接口实现类
 * 
 * @author  jWX216858
 * @version  [版本号, 2014-11-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PrestoredRewardServiceImpl implements IPrestoredRewardService
{
	private PrestoredRewardDaoImpl prestoredRewardDao;
	
	/** 
     * 获取所有活动列表
     * 
     * @return List<PrestoredRewardPO>
     * @see [类、类#方法、类#成员]
     */
	public List<PrestoredRewardPO> getActivitiesList(PrestoredRewardPO prestoredRewardPO) 
	{
		// 获取促销活动组
		List<PrestoredRewardPO> activitiesGroups = this.getActivitiesGroups(prestoredRewardPO);
		
		// 获取所有可用档次
		List<PrestoredRewardPO> allActLevels = prestoredRewardDao.getAllActLevels();
		
		// 用户可用活动
        List<PrestoredRewardPO> activityAllList = new ArrayList<PrestoredRewardPO>();
        PrestoredRewardPO preRewardPO = null;
		
		// 遍历集合获取当前用户可用活动
		for (PrestoredRewardPO activity : activitiesGroups)
		{
			for (PrestoredRewardPO actLevel : allActLevels)
			{
				if (activity.getGroupId().equals(actLevel.getGroupId()))
				{
					preRewardPO = new PrestoredRewardPO();
					
					preRewardPO.setGroupId(actLevel.getGroupId());// 促销活动组
					preRewardPO.setGroupName(actLevel.getGroupName()); // 促销活动名称
					preRewardPO.setActivityId(actLevel.getActivityId()); // 活动编码
					preRewardPO.setRegion(actLevel.getRegion());
					preRewardPO.setActLevelId(actLevel.getActLevelId());// 档次编码
					preRewardPO.setActLevelName(actLevel.getActLevelName()); // 档次名称
					preRewardPO.setPrepayFee(CommonUtil.fenToYuan(actLevel.getPrepayFee())); // 受理金额
					preRewardPO.setPresentValue(CommonUtil.fenToYuan(actLevel.getPresentValue())); // 赠品价格
					preRewardPO.setActivityDesc(actLevel.getActivityDesc()); // 活动描述
					preRewardPO.setAwardDesc(actLevel.getAwardDesc()); // 小票奖品说明
					activityAllList.add(preRewardPO);
				}
			}
		}
		if (activityAllList.size() == 0)
		{
			throw new ReceptionException("当前没有可办理的活动");
		}
		return activityAllList;
	}
	
	/** 
     * 获取促销活动组
     * 
     * @return List<PrestoredRewardPO>
     * @see [类、类#方法、类#成员]
     */
	public List<PrestoredRewardPO> getActivitiesGroups(PrestoredRewardPO prestoredRewardPO)
	{
		return prestoredRewardDao.getActivitiesGroups(prestoredRewardPO);
	}
	
	/**
     * 获取缴费日志OID
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
	public String getChargeLogOID() 
	{
		return prestoredRewardDao.getChargeLogOID();
	}

	/**
	 * 增加缴费日志
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void addChargeLog(CardChargeLogVO cardChargeLogVO) 
	{
		prestoredRewardDao.addChargeLog(cardChargeLogVO);
	}

	/**
	 * 更新缴费日志
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
	{
		prestoredRewardDao.updateChargeLog(cardChargeLogVO);
	}
	
	/**
     * 增加受理记录
     * <功能详细描述>
     * @param activityLogPO
     * @see [类、类#方法、类#成员]
     */
    public void createActivityLog(ActivityLogPO activityLogPO)
    {
    	prestoredRewardDao.createActivityLog(activityLogPO);
    }
	
	public PrestoredRewardDaoImpl getPrestoredRewardDao() {
		return prestoredRewardDao;
	}

	public void setPrestoredRewardDao(PrestoredRewardDaoImpl prestoredRewardDao) {
		this.prestoredRewardDao = prestoredRewardDao;
	}

}
