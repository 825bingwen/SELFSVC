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
 * <�����ն������ӻ��ֶһ�����ȯ>
 * <������ϸ����>
 * 
 * @author  sWX219697
 * @version  [�汾��, May 29, 2015]
 * @see  [�����/����]
 * @since  [OR_SD_201505_61�����ն������ӻ��ֶһ�����ȯ]
 */
@Service
@Transactional(noRollbackFor=ReceptionException.class)
public class ScoreExchangeServiceImpl extends BaseServiceSDImpl implements ScoreExchangeService
{
    @Autowired
    private ScoreExchangeDaoImpl scoreExchangeDaoImpl;
    
    /**
     * <��ѯ�û����û���>
     * <�÷�������Ҫ����>
     * @param curMenuId
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    //@Transactional(propagation = Propagation.NOT_SUPPORTED)
    @SuppressWarnings("unchecked")
    public String qryScore(String curMenuId)
    {
        //��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = getCustomer();
        
        //�ն˻���Ϣ
        TerminalInfoPO termInfo = getTermInfo();
        
        // ��װ����ͷ��Ϣ
        MsgHeaderPO header = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
                customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        //��ѯ�û����û���
        ReturnWrap rw = selfSvcCall.qryScoreValueSD(header);
        
        //add begin lWX431760 2017-11-16 OR_huawei_201703_629_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1
        if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_SCOREVALUESD))
        { 
            CTagSet tagSet = (CTagSet)rw.getReturnObject();
        
            // ��openEBUS  �� UAP ����ͳһ
            String[] openEbusRtn = {"availscore"};
            String[] destRtn = {"pointBalance"};
            tagSet = CommonUtil.rtnConvert(tagSet, BusinessIdConstants.CLI_QRY_SCOREVALUESD, openEbusRtn, destRtn);
            rw.setReturnObject(tagSet);   
        }
        //add end lWX431760 2017-11-16 OR_huawei_201703_629_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1
        
        //�û����û���
        String score = "0";
        
        //���ֲ�ѯ�ɹ�
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            if(rw.getReturnObject() instanceof Vector)
            {
                Vector<CPEntity> v = (Vector<CPEntity>)rw.getReturnObject();
                CTagSet tagSet = (CTagSet)v.get(0);
                
                //�û����û���
                score = tagSet.GetValue("pointBalance");
            }
            
            if(rw.getReturnObject() instanceof CTagSet)
            {
                CTagSet tagSet = (CTagSet)rw.getReturnObject();
                
                //�û����û���
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
     * <�����û����Զһ��ĵ���ȯ>
     * <������ϸ����>
     * @param score
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<PrestoredRewardPO> qryScoreExECash(String score)
    {
        List<PrestoredRewardPO> couponList = new ArrayList<PrestoredRewardPO>();
        
        //���û����û��ֲ�Ϊ0
        if(!"0".equals(score))
        {
            couponList = scoreExchangeDaoImpl.qryScoreExECash(score);
        }
        
        return couponList;
    }
    
    
    /** <��ѯ����ȫ���һ���Ϣ>
     * <������ϸ����>
     * @param score
     * @return
     * @see [�ࡢ��#��������#��Ա]
	 * @remark add begin by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�
     */
    public PrestoredRewardPO qryScoreExEQuantity(String score)
    {
        PrestoredRewardPO prestoredRewardPO = new PrestoredRewardPO();
        //���û����û��ֲ�Ϊ0
        if(!"0".equals(score))
        {
            prestoredRewardPO = scoreExchangeDaoImpl.qryScoreExEQuantity();
        }
        
        return prestoredRewardPO;
    }
    
    /**
     * <���ֶһ�����ȯ�ύ>
     * <������ϸ����>
     * @param curMenuId
     * @see [�ࡢ��#��������#��Ա]
     */
    public void exchangeCommit(String curMenuId, PrestoredRewardPO rewardPO)
    {
        //��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = getCustomer();
        
        //�ն˻���Ϣ
        TerminalInfoPO termInfo = getTermInfo();
        
        // ��װ����ͷ��Ϣ
        MsgHeaderPO header = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
                customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        Document doc = DocumentHelper.createDocument();
        Element ebody = doc.addElement("message_content");

        // �ֻ���
        DocumentUtil.addSubElementToEle(ebody, "telnum", header.getTelNum());
        
        //�����
        DocumentUtil.addSubElementToEle(ebody, "actno", rewardPO.getActivityId());
        
        //�����
        DocumentUtil.addSubElementToEle(ebody, "actlevel", rewardPO.getActLevelId());
        
        //��Ʒ����
        DocumentUtil.addSubElementToEle(ebody, "actreward", rewardPO.getActreward());
        
        // add begin by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�
        if (rewardPO.getQuantity() != null || !"".equals(rewardPO.getQuantity()))
        {
            // ��Ʒ����
            DocumentUtil.addSubElementToEle(ebody, "quantity", rewardPO.getQuantity());
        }
        // add end by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�
        
        //��ѯ�û����û���
        ReturnWrap rw = selfSvcCall.scoreExchange(header, doc);
        
        if(SSReturnCode.ERROR == rw.getStatus())
        {
            insertRecLog(Constants.SH_SCOREEXECASH, "", "", Constants.RECSTATUS_FALID, rw.getReturnMsg());
            throw new ReceptionException(rw.getReturnMsg());
        }
        
        CTagSet tagSet = (CTagSet)rw.getReturnObject();
        
        //add begin lwx439898 2017-05-19 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3
        if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_SCOREEXCHANGESD))
        {
            tagSet = CommonUtil.lowerTagKey(tagSet);
        }
        //add end lwx439898  2017-05-19 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3
        
        //�����ɹ���������ˮ����ҵ����־
        insertRecLog(Constants.SH_SCOREEXECASH, tagSet.GetValue("recoid"), "", Constants.RECSTATUS_SUCCESS, rw.getReturnMsg());

    }
}