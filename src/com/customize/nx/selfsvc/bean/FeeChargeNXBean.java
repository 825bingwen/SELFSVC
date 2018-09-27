package com.customize.nx.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.customize.nx.selfsvc.bean.impl.BaseBeanNXImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;

import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ���ѳ�ֵ�ɷ�
 * 
 * @author xkf29026
 * 
 */
public class FeeChargeNXBean extends BaseBeanNXImpl
{
    
    /**
     * ���ѳ�ֵ�˻���Ϣ��ѯ
     * 
     * @param termInfo �ն˻���Ϣ
     * @param servnumber �ֻ�����
     * @param curMenuId ��ǰ�˵�
     * @return
     */
    public Map qryfeeChargeAccount(TerminalInfoPO termInfo, String servnumber, String curMenuId)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // ���ò���Աid
        paramMap.put("operid", termInfo.getOperid());
        
        // �����ն˻�id
        paramMap.put("atsvNum", termInfo.getTermid());
        
        // ���ÿͻ��ֻ���
        paramMap.put("servnumber", servnumber);
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchoid", "");
        
        // ���õ�ǰ�˵�id
        paramMap.put("menuid", curMenuId);
        
        // ��ȡ���
        ReturnWrap rw = this.getSelfSvcCallNX().qryfeeChargeAccount(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // ���÷��ؽ��
            map.put("returnObj", v);
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null && rw.getStatus() != SSReturnCode.SUCCESS)
        {
           int errcode = rw.getErrcode();
           
           Map map = new HashMap();
           map.put("errcode", errcode);
           return map;
           
        }
        return null;
    }
    
    /**
     * �˻�����ѯ
     * @param terminalInfoPO  �ն˻���Ϣ
     * @param customer �ͻ���Ϣ
     * @param menuid ��ǰ�˵�id
     * @return
     */    
    public Map queryBalance(TerminalInfoPO terminalInfoPO, String servNumber,String menuid)
    {
        Map<String,String> paramMap = new HashMap<String,String>();
        
        // ���ò���Աid
        paramMap.put("operid", terminalInfoPO.getOperid());
        
        // �����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // ���ÿͻ��ֻ���
        paramMap.put("telnumber", servNumber);
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchoid", "");
        
        // ���õ�ǰ�˵�id
        paramMap.put("menuid", menuid);
        
        // ��ȡ���
        ReturnWrap rw = getSelfSvcCallNX().queryBalance(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // ���÷��ؽ��
            map.put("returnObj", v);
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
    }
    
    /**
     * ���ѳ�ֵ�ɷ�
     * 
     * @param termInfo �ն˻���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @param servnumber �ֻ�����
     * @param money �ɷѽ��
     * @param payType �ɷѷ�ʽ
     * @return
     */
    public Map chargeCommit(TerminalInfoPO termInfo, String curMenuId, String servnumber, String money, String terminalSeq)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("operid", termInfo.getOperid());
        paramMap.put("termid", termInfo.getTermid());
        paramMap.put("menuid", curMenuId);
        paramMap.put("servnumber", servnumber);
        paramMap.put("money", money);
        paramMap.put("terminalSeq", terminalSeq);
        paramMap.put("touchoid", "");
        
        ReturnWrap rw = this.getSelfSvcCallNX().chargeCommit(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // ���÷��ؽ��
            map.put("returnObj", v);
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
    }
    
    /**
     * ���û���������������
     * 
     * @param servNumber���ֻ�����
     * @param termInfo���ն˻���Ϣ
     * @param shortMessage����������
     * @param curMenuId����ǰ�˵�
     * @return
     * @see
     */
    public boolean sendRandomPwd(String servNumber, TerminalInfoPO termInfo, String shortMessage, String curMenuId)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servNumber);
        paramMap.put("smsContent", shortMessage);
        paramMap.put("priority", "5");
        paramMap.put("menuID", curMenuId);
        paramMap.put("touchOID", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallNX().sendSMS(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * ��ѯ�������ɷѷ�Ʊ��Ϣ
     * 
     * @param termInfo �ն˻���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @param servnumber �ֻ�����
     * @param money �ɷѽ��֣�
     * @param channel ����
     * @remark create cKF76106 2013/02/04 R003C13L01n01 OR_huawei_201302_122 
     * @return
     */
    public Map queryInvoice(TerminalInfoPO termInfo, String curMenuId, String servnumber, String money, String channel)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("operid", termInfo.getOperid());
        paramMap.put("termid", termInfo.getTermid());
        paramMap.put("menuid", curMenuId);
        paramMap.put("servnumber", servnumber);
        paramMap.put("money", money);
        paramMap.put("touchoid", "");
        paramMap.put("channel", channel);
        
        ReturnWrap rw = this.getSelfSvcCallNX().queryInvoice(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // ���÷��ؽ��
            map.put("returnObj", v);
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
    }
}
