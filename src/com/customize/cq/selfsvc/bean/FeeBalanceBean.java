package com.customize.cq.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class FeeBalanceBean extends BaseBeanCQImpl
{
    
    /**
     * �˻�����ѯ
     * @param terminalInfoPO  �ն˻���Ϣ
     * @param customer �ͻ���Ϣ
     * @param menuid ��ǰ�˵�id
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map queryBalance(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String menuid)
    {
        Map<String,String> paramMap = new HashMap<String,String>();
        
        //���ò���Աid
        paramMap.put("operid", terminalInfoPO.getOperid());
        
        //�����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //���ÿͻ��ֻ���
        paramMap.put("telnumber", customer.getServNumber());
        
        //���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        //���õ�ǰ�˵�id
        paramMap.put("menuid", menuid);        

        // ��ȡ���
        ReturnWrap rw = this.getSelfSvcCallCQ().queryBalance(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet) rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // ���÷��ؽ��
            map.put("returnObj", v);
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        return null;
    }
}
