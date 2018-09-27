package com.customize.cq.selfsvc.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

//add begin l00190940 2011-11-17 �ҵ���Ϣ��ѯ�����죩
/**
 * ���������Ϣ��ѯ
 * 
 * @author yKF28472
 * 
 */
public class PersionInfoBean extends BaseBeanCQImpl
{
    @SuppressWarnings("unchecked")
    public Map qryComboInfo(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String month)
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
        
        // ��������Ϊ��ǰ�·�
        paramMap.put("cycle", month);
           
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = getSelfSvcCallCQ().qryComboInfo(paramMap);
        if (rw != null)
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
//add end l00190940 2011-11-17 �ҵĸ�����Ϣ��ѯ�����죩
    
    /**
     * ��ѯ����Ʒ���ʷѼ��ѿ�ͨ���ܣ���Ʒչʾ��
     * @param terminalInfoPO �ն˻���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map qryFavourable(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId)
    {
        Map paramMap = new HashMap();
        
        //���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //�����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //���ÿͻ��ֻ���
        paramMap.put("telnumber", customer.getServNumber());
        
        //���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        //���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = getSelfSvcCallCQ().qryFavourable(paramMap);
        if (rw != null)
        {
        	CRSet crset = (CRSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //���÷��ؽ��
            map.put("returnObj", crset);
            
            //���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        return null;
    }
}
