package com.customize.sd.selfsvc.scoreExchange.service;

import java.util.List;

import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;

public interface ScoreExchangeService
{
    /**
     * <查询用户可用积分>
     * <功能详细描述>
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String qryScore(String curMenuId);
    
    /**
     * <查找用户可以兑换的电子券>
     * <功能详细描述>
     * @param score
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<PrestoredRewardPO> qryScoreExECash(String score);
    
    /**
     * <积分兑换电子券提交>
     * <功能详细描述>
     * @param curMenuId
     * @param rewardPO
     * @see [类、类#方法、类#成员]
     */
    public void exchangeCommit(String curMenuId, PrestoredRewardPO rewardPO);

    /** <查找积分全部兑换信息>
     * <功能详细描述>
     * @param score
     * @return
     * @see [类、类#方法、类#成员]
     */
    public PrestoredRewardPO qryScoreExEQuantity(String score);
}
