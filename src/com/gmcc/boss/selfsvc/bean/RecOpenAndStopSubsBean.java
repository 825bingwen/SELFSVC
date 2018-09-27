package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ͣ����ҵ����
 * @author xkf29026
 *
 */
public class RecOpenAndStopSubsBean extends BaseBeanImpl
{
    @SuppressWarnings("unchecked")
    public Map stopOpenSubs(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId,String stoptype,String reason)
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
        
        //ͣ��������
        paramMap.put("stoptype", stoptype);
        
        //ͣ����˵��
        paramMap.put("reason", reason);
        
        ReturnWrap rw = selfSvcCall.stopOpenSubs(paramMap);
        Map map = new HashMap();
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // ���÷��ؽ��
            map.put("returnObj", v);
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
        }
        else
        {
        	String returnMsg = rw.getReturnMsg();
        	map.put("returnMsg", returnMsg);
        }
        return map;
    }
}
