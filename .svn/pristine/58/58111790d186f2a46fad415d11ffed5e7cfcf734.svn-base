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
 * <积分兑换电子券> <功能详细描述>
 * 
 * @author sWX219697
 * @version [版本号, May 28, 2015]
 * @see [相关类/方法]
 * @since [OR_SD_201505_61自助终端上增加积分兑换电子券]
 */
@Controller
@Scope("prototype")
public class ScoreExchangeAction extends BaseAction
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 日志类
     */
    public static final Log logger = LogFactory.getLog(ScoreExchangeAction.class);
    
    /**
     * 营销活动实体类
     */
    private transient PrestoredRewardPO rewardPO = new PrestoredRewardPO();
    
    /**
     * 业务层
     */
    @Autowired
    private transient ScoreExchangeService scoreExchangeService;
    
    /**
     * 用户可用的电子积分券
     */
    private List<PrestoredRewardPO> exECashList;
    
    /**
     * 错误信息
     */
    private String errormessage;
    
    /**
     * 用户积分
     */
    private String subsScore;
    
    /**
     * <展示用户可以兑换的电子券> <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String scoreExchange()
    {
        String forward = ERROR;
        
        try
        {
            // 查询用户积分
            setSubsScore(scoreExchangeService.qryScore(this.getCurMenuId()));
            
            // 用户可用的电子积分券
            exECashList = scoreExchangeService.qryScoreExECash(subsScore);
            
            // 查询全部电子券兑换信息
            // add begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化
            rewardPO = scoreExchangeService.qryScoreExEQuantity(subsScore);
            
            int rewardScore = Integer.parseInt(this.subsScore);
            
            // 奖品数量
            rewardScore = rewardScore/10;
            
            // 奖品数量为0
            if (rewardScore ==0)
            {
                rewardPO = null;
            }
            // add begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化
            
            forward = SUCCESS;
        }
        catch (ReceptionException e)
        {
            logger.error("用户可用积分查询失败：", e);
            setErrormessage(e.getMessage());
        }
        
        return forward;
    }
    
    /**
     * <积分兑换电子券提交> <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String exchangeCommit()
    {
        String forward = ERROR;
        
        try
        {
            // 积分兑换电子券提交
            scoreExchangeService.exchangeCommit(this.getCurMenuId(), rewardPO);
            
            forward = SUCCESS;
        }
        catch (ReceptionException e)
        {
            logger.error("积分兑换电子券提交失败：", e);
            setErrormessage(e.getMessage());
        }
        
        return forward;
    }
    
    /**
     * <积分兑换电子券温馨提示>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<DictItemPO> getExchangeTipList()
    {
        return getDictItemByGrp(Constants.SH_SCOREEXCHANGE_TIP);
    }
    
    /**
     * <查询出的电子券信息为空时的提示>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getExCashTip()
    {
        return CommonUtil.getParamValue(Constants.SH_SCOREEXCHANGE_EMPTY_TIP, "暂时没有可以兑换的电子券");
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
