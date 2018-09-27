package com.customize.sd.selfsvc.scoreExchange.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;
import com.customize.sd.selfsvc.scoreExchange.service.ScoreExchangeService;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.service.SelfSvcService;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 
 * <���ֶһ�����ȯ> <������ϸ����>
 * 
 * @author sWX219697
 * @version [�汾��, May 28, 2015]
 * @see [�����/����]
 * @since [OR_SD_201505_61�����ն������ӻ��ֶһ�����ȯ]
 */
@Controller
@Scope("prototype")
public class ScoreExchangeAction extends BaseAction
{
    
    /**
     * ע������
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * ��־��
     */
    public static final Log logger = LogFactory.getLog(ScoreExchangeAction.class);
    
    /**
     * Ӫ���ʵ����
     */
    private transient PrestoredRewardPO rewardPO = new PrestoredRewardPO();
    
    /**
     * ҵ���
     */
    @Autowired
    private transient ScoreExchangeService scoreExchangeService;
    
    /**
     * �û����õĵ��ӻ���ȯ
     */
    private List<PrestoredRewardPO> exECashList;
    
    /**
     * ������Ϣ
     */
    private String errormessage;
    
    /**
     * �û�����
     */
    private String subsScore;
    
    /**
     * <չʾ�û����Զһ��ĵ���ȯ> <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String scoreExchange()
    {
        String forward = ERROR;
        
        try
        {
            // ��ѯ�û�����
            setSubsScore(scoreExchangeService.qryScore(this.getCurMenuId()));
            
            // �û����õĵ��ӻ���ȯ
            exECashList = scoreExchangeService.qryScoreExECash(subsScore);
            
            // ��ѯȫ������ȯ�һ���Ϣ
            // add begin by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�
            rewardPO = scoreExchangeService.qryScoreExEQuantity(subsScore);
            
            int rewardScore = Integer.parseInt(this.subsScore);
            
            // ��Ʒ����
            rewardScore = rewardScore/10;
            
            // ��Ʒ����Ϊ0
            if (rewardScore ==0)
            {
                rewardPO = null;
            }
            // add begin by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�
            
            forward = SUCCESS;
        }
        catch (ReceptionException e)
        {
            logger.error("�û����û��ֲ�ѯʧ�ܣ�", e);
            setErrormessage(e.getMessage());
        }
        
        return forward;
    }
    
    /**
     * <���ֶһ�����ȯ�ύ> <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String exchangeCommit()
    {
        String forward = ERROR;
        
        try
        {
            // ���ֶһ�����ȯ�ύ
            scoreExchangeService.exchangeCommit(this.getCurMenuId(), rewardPO);
            
            forward = SUCCESS;
        }
        catch (ReceptionException e)
        {
            logger.error("���ֶһ�����ȯ�ύʧ�ܣ�", e);
            setErrormessage(e.getMessage());
        }
        
        return forward;
    }
    
    /**
     * <���ֶһ�����ȯ��ܰ��ʾ>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<DictItemPO> getExchangeTipList()
    {
        return getDictItemByGrp(Constants.SH_SCOREEXCHANGE_TIP);
    }
    
    /**
     * <��ѯ���ĵ���ȯ��ϢΪ��ʱ����ʾ>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String getExCashTip()
    {
        return CommonUtil.getParamValue(Constants.SH_SCOREEXCHANGE_EMPTY_TIP, "��ʱû�п��Զһ��ĵ���ȯ");
    }
    
    
    @Autowired
    public void setSelfSvcService(@Qualifier("selfSvcServiceImpl")SelfSvcService selfSvcService)
    {
        super.setSelfSvcService(selfSvcService);
    }
    
    public PrestoredRewardPO getRewardPO()
    {
        return rewardPO;
    }
    
    public void setRewardPO(PrestoredRewardPO rewardPO)
    {
        this.rewardPO = rewardPO;
    }
    
    public List<PrestoredRewardPO> getExECashList()
    {
        return exECashList;
    }
    
    public void setExECashList(List<PrestoredRewardPO> exECashList)
    {
        this.exECashList = exECashList;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }

    public String getSubsScore()
    {
        return subsScore;
    }

    public void setSubsScore(String subsScore)
    {
        this.subsScore = subsScore;
    }
    
}
