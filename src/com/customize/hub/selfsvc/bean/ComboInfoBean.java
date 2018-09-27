package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * �����ײ���Ϣ��ѯ
 * 
 * @author xkf29026
 * 
 */
public class ComboInfoBean extends BaseBeanHubImpl
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
        paramMap.put("billcycle", month);
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = getSelfSvcCallHub().qryComboInfo(paramMap);
        if (rw != null)
        {
//            Vector v = (Vector)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // ���÷��ؽ��
            map.put("returnObj", rw.getReturnObject());
            
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            // add begin g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
            map.put("errcode", rw.getErrcode());
            // add end g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
            
            return map;
        }

        return null;
    }
}