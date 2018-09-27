package com.customize.hub.selfsvc.scorexecoup.service;

import java.util.List;

import com.customize.hub.selfsvc.scorexecoup.model.RewardAction;
import com.customize.hub.selfsvc.scorexecoup.model.ScorePojo;

/**
 * 
 * 查询积分配置信息数据
 * 
 * @author user
 * @version [版本号, Mar 29, 2013]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ScoreExECoupService
{
    /**
     * 查询指定类型的积分信息数据
     * 
     * @param type
     * @return 返回单个对象
     * @see [类、类#方法、类#成员]
     */
    public ScorePojo getScorePojoType(String type);
    
    /**
     * 查询指定类型的积分信息数据
     * 
     * @param type
     * @return 返回查询集合
     * @see [类、类#方法、类#成员]
     */
    public List<ScorePojo> getScorePojoListType(String type);
    /**
     * 根据用户的积分查询用户的可以抽奖区域
     * 
     * @param Score
     * @return
     * @see [类、类#方法、类#成员]
     * remark create yKF73963  2013-09-09 OR_HUB_201304_824  自助终端-积分专区 
     */
    public List<RewardAction> qryAllRewardLevel(RewardAction rewardAction);
    /**
     * 根据用户的积分和抽奖区域查询用户的可以抽奖的活动
     * 
     * @param RewardAction
     * @return
     * @see [类、类#方法、类#成员]
     * remark create yKF73963  2013-09-09 OR_HUB_201304_824  自助终端-积分专区 
     */
    public List<RewardAction> qryActiveListByLevel(RewardAction rewardAction);
    
}
