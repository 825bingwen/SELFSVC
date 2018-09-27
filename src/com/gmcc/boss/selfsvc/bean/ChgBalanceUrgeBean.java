package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class ChgBalanceUrgeBean extends BaseBeanImpl
{
    /**
     * ������Ѳ�ѯ
     * 
     * @param terminalInfoPO �ն���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map qryBalanceNotice(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId)
    {
        Map paramMap = new HashMap();
        
        // ���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // �����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // ���ÿͻ��ֻ���
        paramMap.put("telnumber", customer.getServNumber());
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = selfSvcCall.qryBalanceNotice(paramMap);
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
     * ��ѯ��������ֵ������
     * 
     * @param terminalInfoPO �ն���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @param groupid ��id
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map getDictItem_chgBalanceUrge(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId,
            String groupid)
    {
        Map paramMap = new HashMap();
        
        // ���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // �����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // ���ÿͻ��ֻ���
        paramMap.put("telnumber", customer.getServNumber());
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        // ���õ�ǰ�˵�
        paramMap.put("groupid", groupid);
        
        ReturnWrap rw = selfSvcCall.getDictItem(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet v = (CRSet)rw.getReturnObject();
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
     * �����������ֵ
     * 
     * @param terminalInfoPO �ն���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @param balanceAwake �������ֵ
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map setBalanceNotice(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId,
            String balanceAwake, String operType)
    {
        Map paramMap = new HashMap();
        
        // ���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // �����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // ���ÿͻ��ֻ���
        paramMap.put("telnumber", customer.getServNumber());
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        // ���õ�ǰ�˵�
        paramMap.put("balanceAwake", balanceAwake);
        
        // ���õ�ǰ��������
        paramMap.put("operType", operType);
        
        ReturnWrap rw = selfSvcCall.setBalanceNotice(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet v = (CRSet)rw.getReturnObject();
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
