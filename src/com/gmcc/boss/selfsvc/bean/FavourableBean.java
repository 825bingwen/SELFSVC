package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.gmcc.boss.common.base.CEntityString;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ����Ʒ���ʷѼ��ѿ�ͨ����
 * @author xkf29026
 *
 */
public class FavourableBean extends BaseBeanImpl
{
    /**
     * ��ѯ����Ʒ���ʷѼ��ѿ�ͨ����
     * @param terminalInfoPO �ն˻���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @param queryType ��ѯ����
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map qryFavourable(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId,String queryType)
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
        
        //���ò�ѯ����
        paramMap.put("queryType", queryType);
        
        ReturnWrap rw = selfSvcCall.qryFavourable(paramMap);
        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            if ("2".equals(queryType))// 1��Ʒ���ʷ� 2���ѿ�ͨ�����Ż�
            {
                String title = "�ѿ�ͨҵ��=�ʷ�����=��������=��������";
                Vector vector = new Vector();
                vector.add(title);
                vector.add(rw.getReturnObject());
                rw.setReturnObject(vector);
            }
            
            List v = (List)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //���÷��ؽ��
            map.put("returnObj", v);
            
            //���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            // add begin g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
            map.put("errcode", rw.getErrcode());
            // add end g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
            
            return map;
            
        }
    }
}
