package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ����Ӫ���Ƽ��
 * 
 * @author  cKF76106
 * @version  [�汾��, Aug 21, 2012]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class RecommendProductBean extends BaseBeanHubImpl
{
    /**
     * ��ѯ�û����Ƽ���ҵ���б�_����Ӫ���Ƽ��
     * @param terminalInfoPO
     * @param customerSimp
     * @param curMenuId
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public Map<String, Object> qryRecommendProductList(TerminalInfoPO terminalInfoPO,String touchOID,String telnum,String curMenuId )
    {
        // ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
                "",MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        ReturnWrap rw = super.getSelfSvcCallHub().qryRecommendProductList(msgHeader);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String returnMsg = rw.getReturnMsg();
            Map<String, Object> map = new HashMap<String, Object>();
            
            //���÷��ؽ��
            map.put("returnObj", rw.getReturnObject());
            
            //���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            // ���÷�����
            map.put("errcode", rw.getErrcode());
            
            // ���óɹ���־
            map.put("successFlag", "1");

            return map;
        }
        else if (rw != null)
        {
            String returnMsg = rw.getReturnMsg();
            Map<String, Object> map = new HashMap<String, Object>();
            
            //���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            // ���÷�����
            map.put("errcode", rw.getErrcode());

            return map;
        }
        
        return null;
    }
    
    
    /**
     * ��¼ҵ���Ƽ����_����Ӫ���Ƽ��
     * @param terminalInfoPO
     * @param customerSimp
     * @param curMenuId
     * @param userSeq
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark modify zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 ��׼Ӫ������_�����ն���������
     */
    public ReturnWrap recommendFeedback(TerminalInfoPO terminalInfoPO, String touchOID, String telnum,
        String curMenuId, String userSeqs, String status, String actIds, String eventTypes)
    {
        /*Map paramMap = new HashMap();
        
        //���ò���Աid
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        //�����ն˻�id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchOID", touchOID); 
        
        //���õ�ǰ�˵�
        paramMap.put("menuID", curMenuId);
        
        //�ֻ�����
        paramMap.put("telnum", telnum);
        
        //ҵ���Ƽ�Ψһ��ˮ��,����Զ��ŷָ�
        paramMap.put("userSeq", userSeqs);
        
        //״̬
        paramMap.put("status", status);
        
        //�Ƽ������,����Զ��ŷָ�
        paramMap.put("actid", actIds);
        
        //�¼�����,����Զ��ŷָ�
        paramMap.put("eventtype", eventTypes);*/
    	
    	// ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
                "",MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        ReturnWrap rw = super.getSelfSvcCallHub().recommendFeedback(msgHeader, userSeqs, status, actIds, eventTypes);
                
        return rw;
    }
    
    /**
     * �Ƽ�ҵ������
     * @param terminalInfoPO
     * @param customerSimp
     * @param curMenuId
     * @param commendOID
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark modify zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 ��׼Ӫ������_�����ն���������
     */
    public ReturnWrap recommendProduct(TerminalInfoPO terminalInfoPO, String touchOID, String telnum, String curMenuId,
        String userSeq, String actId, String recmdType)
    {
        // ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
                "",MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        ReturnWrap rw = super.getSelfSvcCallHub().recommendProduct(msgHeader, userSeq, actId, recmdType);
        
        return rw;
    }
    
    /**
     * ����ҵ���Ƽ����Ϊ������ɹ���
     * @param terminalInfoPO
     * @param customerSimp
     * @param curMenuId
     * @param commendOID
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap setRecommendSuccess(TerminalInfoPO terminalInfoPO,String touchOID,String telnum,String curMenuId,String commendOID )
    {
        // ��װ����ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
                "",MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        ReturnWrap rw = super.getSelfSvcCallHub().setRecommendSuccess(msgHeader, commendOID);
                
        return rw;
    }
    
    /**
     * ��ѯ����������Ϣ�б�
     * 
     * @param terminalInfoPO �ն˻���Ϣ
     * @param customerSimp �û���Ϣ
     * @param curMenuId �˵�Id
     * @param actId ҵ���Ƽ������
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 ��׼Ӫ������_�����ն���������
     */
    public ReturnWrap qryFeedBackDefList(TerminalInfoPO terminalInfoPO, NserCustomerSimp customerSimp, 
        String curMenuId, String telNum, String actId)
    {
        String contactID = null;
        
        if (null != customerSimp)
        {
            contactID = customerSimp.getContactId();
        }
        
        // ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
            contactID, MsgHeaderPO.ROUTETYPE_TELNUM, telNum);
        
        return super.getSelfSvcCallHub().qryFeedBackDefList(msgHeader, actId);
    }
    
    /** 
     * ������������
     * 
     * @param terminalInfoPO �ն˻���Ϣ
     * @param customerSimp �û���Ϣ
     * @param curMenuId �˵�Id
     * @param actId ҵ���Ƽ������
     * @param contents �ظ�����
     * @param recmdType �Ƽ�����
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 ��׼Ӫ������_�����ն���������
     */
    public ReturnWrap doFeedBackDef(TerminalInfoPO terminalInfoPO, NserCustomerSimp customerSimp,
        String curMenuId, String actId, String contents, String recmdType)
    {
    	// begin zKF66389 2015-09-15 9�·�findbugs�޸�
//    	String contactID = null;
//        
//        if (null != customerSimp)
//        {
//            contactID = customerSimp.getContactID();
//        }
        
    	// ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
        		customerSimp.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customerSimp.getServNumber());
        // end zKF66389 2015-09-15 9�·�findbugs�޸�
    	
        return super.getSelfSvcCallHub().doFeedBackDef(msgHeader, actId, contents, recmdType);
    }
}
