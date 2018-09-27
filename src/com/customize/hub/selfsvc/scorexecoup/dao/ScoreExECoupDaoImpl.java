package com.customize.hub.selfsvc.scorexecoup.dao;

import java.util.List;

import com.customize.hub.selfsvc.scorexecoup.model.RewardAction;
import com.customize.hub.selfsvc.scorexecoup.model.ScorePojo;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 
 * ��ѯ����������Ϣ����
 * 
 * @author user
 * @version [�汾��, Mar 29, 2013]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class ScoreExECoupDaoImpl extends BaseDaoImpl
{
    /**
     * ��ѯָ�����͵Ļ�����Ϣ����
     * 
     * @param type
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<ScorePojo> getScorePojoListType(String type)
    {
        return this.sqlMapClientTemplate.queryForList("SCORESEL.qryScoreType", type);
    }
    
    /**
     * ��ѯָ�����͵Ļ�����Ϣ����
     * 
     * @param type
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ScorePojo getScorePojoType(String type)
    {
        return (ScorePojo)this.sqlMapClientTemplate.queryForObject("SCORESEL.qryScoreType", type);
    }
    
    /**
     * �����û��Ļ��ֲ�ѯ�û��Ŀ��Գ齱����
     * 
     * @param Score
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * remark create yKF73963  2013-09-09 OR_HUB_201304_824  �����ն�-����ר�� 
     */
    public List<RewardAction> qryAllRewardLevel(RewardAction rewardAction)
    {
        return this.sqlMapClientTemplate.queryForList("SCORESEL.qryAllRewardLevel", rewardAction);
    }
    /**
     * �����û��Ļ��ֺͳ齱�����ѯ�û��Ŀ��Գ齱�Ļ
     * 
     * @param RewardAction
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * remark create yKF73963  2013-09-09 OR_HUB_201304_824  �����ն�-����ר�� 
     */
    public List<RewardAction> qryActiveListByLevel(RewardAction rewardAction)
    {
        return this.sqlMapClientTemplate.queryForList("SCORESEL.qryActiveListByLevel", rewardAction);
    }
}
