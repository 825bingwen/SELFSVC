package com.customize.sd.selfsvc.scoreExchange.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 
 * <�����ն������ӻ��ֶһ�����ȯ>
 * <������ϸ����>
 * 
 * @author  sWX219697
 * @version  [�汾��, May 29, 2015]
 * @see  [�����/����]
 * @since  [OR_SD_201505_61�����ն������ӻ��ֶһ�����ȯ]
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
     * <��ѯ�û����Զһ��ĵ���ȯ>
     * <������ϸ����>
     * @param score �û����û���
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<PrestoredRewardPO> qryScoreExECash(String score)
    {
        return sqlMapClientTemplate.queryForList("prestoredReward.qryScoreExECash", Integer.parseInt(score));
    }
    
    /** <��ѯȫ���һ�����ȯ��Ϣ>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * remark add begin by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�
     */
    public PrestoredRewardPO qryScoreExEQuantity()
    {
        return (PrestoredRewardPO)sqlMapClientTemplate.queryForObject("prestoredReward.qryScoreQuantity");
    }
}
