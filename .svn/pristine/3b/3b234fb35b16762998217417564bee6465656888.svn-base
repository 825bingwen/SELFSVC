package com.customize.sd.selfsvc.scoreExchange.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 
 * <自助终端上增加积分兑换电子券>
 * <功能详细描述>
 * 
 * @author  sWX219697
 * @version  [版本号, May 29, 2015]
 * @see  [相关类/方法]
 * @since  [OR_SD_201505_61自助终端上增加积分兑换电子券]
 */
@Repository
public class ScoreExchangeDaoImpl extends BaseDaoImpl
{
    @Autowired
    public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate)
    {
        super.setSqlMapClientTemplate(sqlMapClientTemplate);
    }
    
    /**
     * <查询用户可以兑换的电子券>
     * <功能详细描述>
     * @param score 用户可用积分
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<PrestoredRewardPO> qryScoreExECash(String score)
    {
        return sqlMapClientTemplate.queryForList("prestoredReward.qryScoreExECash", Integer.parseInt(score));
    }
    
    /** <查询全部兑换电子券信息>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     * remark add begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化
     */
    public PrestoredRewardPO qryScoreExEQuantity()
    {
        return (PrestoredRewardPO)sqlMapClientTemplate.queryForObject("prestoredReward.qryScoreQuantity");
    }
}
