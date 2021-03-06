package com.customize.sd.selfsvc.scoreExchange.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customize.sd.selfsvc.common.service.BaseServiceSDImpl;
import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;
import com.customize.sd.selfsvc.scoreExchange.dao.ScoreExchangeDaoImpl;
import com.gmcc.boss.common.base.CPEntity;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DocumentUtil;

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
@Service
@Transactional(noRollbackFor=ReceptionException.class)
public class ScoreExchangeServiceImpl extends BaseServiceSDImpl implements ScoreExchangeService
{
    @Autowired
    private ScoreExchangeDaoImpl scoreExchangeDaoImpl;
    
    /**
     * <查询用户可用积分>
     * <该方法不需要事务>
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     */
    //@Transactional(propagation = Propagation.NOT_SUPPORTED)
    @SuppressWarnings("unchecked")
    public String qryScore(String curMenuId)
    {
        //获取客户信息
        NserCustomerSimp customer = getCustomer();
        
        //终端机信息
        TerminalInfoPO termInfo = getTermInfo();
        
        // 组装报文头信息
        MsgHeaderPO header = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
                customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        //查询用户可用积分
        ReturnWrap rw = selfSvcCall.qryScoreValueSD(header);
        
        //add begin lWX431760 2017-11-16 OR_huawei_201703_629_【山东移动接口迁移专题】-自助终端已有接口1
        if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_SCOREVALUESD))
        { 
            CTagSet tagSet = (CTagSet)rw.getReturnObject();
        
            // 将openEBUS  和 UAP 出参统一
            String[] openEbusRtn = {"availscore"};
            String[] destRtn = {"pointBalance"};
            tagSet = CommonUtil.rtnConvert(tagSet, BusinessIdConstants.CLI_QRY_SCOREVALUESD, openEbusRtn, destRtn);
            rw.setReturnObject(tagSet);   
        }
        //add end lWX431760 2017-11-16 OR_huawei_201703_629_【山东移动接口迁移专题】-自助终端已有接口1
        
        //用户可用积分
        String score = "0";
        
        //积分查询成功
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            if(rw.getReturnObject() instanceof Vector)
            {
                Vector<CPEntity> v = (Vector<CPEntity>)rw.getReturnObject();
                CTagSet tagSet = (CTagSet)v.get(0);
                
                //用户可用积分
                score = tagSet.GetValue("pointBalance");
            }
            
            if(rw.getReturnObject() instanceof CTagSet)
            {
                CTagSet tagSet = (CTagSet)rw.getReturnObject();
                
                //用户可用积分
                score = tagSet.GetValue("pointBalance");
            }
            
            score = StringUtils.isEmpty(score) ? "0" : score;

        }
        else
        {
            insertRecLog(Constants.SH_SCOREEXECASH, "", "", Constants.RECSTATUS_FALID, rw.getReturnMsg());
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        return score;

    }
    
    /**
     * <查找用户可以兑换的电子券>
     * <功能详细描述>
     * @param score
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<PrestoredRewardPO> qryScoreExECash(String score)
    {
        List<PrestoredRewardPO> couponList = new ArrayList<PrestoredRewardPO>();
        
        //若用户可用积分不为0
        if(!"0".equals(score))
        {
            couponList = scoreExchangeDaoImpl.qryScoreExECash(score);
        }
        
        return couponList;
    }
    
    
    /** <查询积分全部兑换信息>
     * <功能详细描述>
     * @param score
     * @return
     * @see [类、类#方法、类#成员]
	 * @remark add begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化
     */
    public PrestoredRewardPO qryScoreExEQuantity(String score)
    {
        PrestoredRewardPO prestoredRewardPO = new PrestoredRewardPO();
        //若用户可用积分不为0
        if(!"0".equals(score))
        {
            prestoredRewardPO = scoreExchangeDaoImpl.qryScoreExEQuantity();
        }
        
        return prestoredRewardPO;
    }
    
    /**
     * <积分兑换电子券提交>
     * <功能详细描述>
     * @param curMenuId
     * @see [类、类#方法、类#成员]
     */
    public void exchangeCommit(String curMenuId, PrestoredRewardPO rewardPO)
    {
        //获取客户信息
        NserCustomerSimp customer = getCustomer();
        
        //终端机信息
        TerminalInfoPO termInfo = getTermInfo();
        
        // 组装报文头信息
        MsgHeaderPO header = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
                customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        Document doc = DocumentHelper.createDocument();
        Element ebody = doc.addElement("message_content");

        // 手机号
        DocumentUtil.addSubElementToEle(ebody, "telnum", header.getTelNum());
        
        //活动批次
        DocumentUtil.addSubElementToEle(ebody, "actno", rewardPO.getActivityId());
        
        //活动档次
        DocumentUtil.addSubElementToEle(ebody, "actlevel", rewardPO.getActLevelId());
        
        //奖品编码
        DocumentUtil.addSubElementToEle(ebody, "actreward", rewardPO.getActreward());
        
        // add begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化
        if (rewardPO.getQuantity() != null || !"".equals(rewardPO.getQuantity()))
        {
            // 奖品数量
            DocumentUtil.addSubElementToEle(ebody, "quantity", rewardPO.getQuantity());
        }
        // add end by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化
        
        //查询用户可用积分
        ReturnWrap rw = selfSvcCall.scoreExchange(header, doc);
        
        if(SSReturnCode.ERROR == rw.getStatus())
        {
            insertRecLog(Constants.SH_SCOREEXECASH, "", "", Constants.RECSTATUS_FALID, rw.getReturnMsg());
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet tagSet = (CTagSet)rw.getReturnObject();
        
        //add begin lwx439898 2017-05-19 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_SCOREEXCHANGESD))
        {
            tagSet = CommonUtil.lowerTagKey(tagSet);
        }
        //add end lwx439898  2017-05-19 OR_huawei_201704_412_【山东移动接口迁移专题】-自助终端业务办理3
        
        //办理成功，添加流水号至业务日志
        insertRecLog(Constants.SH_SCOREEXECASH, tagSet.GetValue("recoid"), "", Constants.RECSTATUS_SUCCESS, rw.getReturnMsg());

    }
}
