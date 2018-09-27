package com.customize.sd.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * �ײ���Ϣ��ѯ
 * @author xkf29026
 *
 */
public class ComboInfoBean extends BaseBeanSDImpl
{
    /**
     * �ײ���Ϣ��ѯ 
     * @param terminalInfoPO �ն���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map qryComboInfo(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId)
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
        
        ReturnWrap rw = this.getSelfSvcCallSD().qryComboInfo(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Vector v = (Vector)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //���÷��ؽ��
            map.put("returnObj", v);
            
            //���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
    }
}
