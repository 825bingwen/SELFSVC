package com.customize.hub.selfsvc.scorexecoup.dao;

import java.util.List;

import com.customize.hub.selfsvc.scorexecoup.model.RewardAction;
import com.customize.hub.selfsvc.scorexecoup.model.ScorePojo;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 
 * 查询积分配置信息数据
 * 
 * @author user
 * @version [版本号, Mar 29, 2013]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ScoreExECoupDaoImpl extends BaseDaoImpl
{
    /**
     * 查询指定类型的积分信息数据
     * 
     * @param type
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ScorePojo> getScorePojoListType(String type)
    {
        return this.sqlMapClientTemplate.queryForList("SCORESEL.qryScoreType", type);
    }
    
    /**
     * 查询指定类型的积分信息数据
     * 
     * @param type
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ScorePojo getScorePojoType(String type)
    {
        return (ScorePojo)this.sqlMapClientTemplate.queryForObject("SCORESEL.qryScoreType", type);
    }
    
    /**
     * 根据用户的积分查询用户的可以抽奖区域
     * 
     * @param Score
     * @return
     * @see [类、类#方法、类#成员]
     * remark create yKF73963  2013-09-09 OR_HUB_201304_824  自助终端-积分专区 
     */
    public List<RewardAction> qryAllRewardLevel(RewardAction rewardAction)
    {
        return this.sqlMapClientTemplate.queryForList("SCORESEL.qryAllRewardLevel", rewardAction);
    }
    /**
     * 根据用户的积分和抽奖区域查询用户的可以抽奖的活动
     * 
     * @param RewardAction
     * @return
     * @see [类、类#方法、类#成员]
     * remark create yKF73963  2013-09-09 OR_HUB_201304_824  自助终端-积分专区 
     */
    public List<RewardAction> qryActiveListByLevel(RewardAction rewardAction)
    {
        return this.sqlMapClientTemplate.queryForList("SCORESEL.qryActiveListByLevel", rewardAction);
    }
}
