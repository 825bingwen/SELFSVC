package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.gmcc.boss.common.base.CEntityString;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ������ʷ��ѯ
 * @author xkf29026
 *
 */
public class ServiceHistoryBean extends BaseBeanImpl
{
    /**
     * ������ʷ��ѯ
     * @param startDate ��ʼ����
     * @param endDate ��������
     * @param terminalInfoPO �ն˻���Ϣ
     * @param customer �ͻ���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map qryAllServiceHistory(String startDate,String endDate,TerminalInfoPO terminalInfoPO,NserCustomerSimp customer, String curMenuId)
    {
        Map paramMap = new HashMap();
        
        //���ÿ�ʼ����
        paramMap.put("startDate", startDate);
        
        //���ý�������
        paramMap.put("endDate", endDate);
        
        //���ò���Աid
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //�����ն˻�id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //���ÿͻ��ֻ���
        paramMap.put("servnumber", customer.getServNumber());
        
        //���ÿͻ��Ӵ�id
        paramMap.put("touchoid", customer.getContactId());
        
        //���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = selfSvcCall.qryAllServiceHistory(paramMap);

        // begin zKF66389 2015-09-15 9�·�findbugs�޸�
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String title = "";
            
            String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
            {
                title = "����ʱ��=��������=ҵ������";
            }
            else
            {
                title = "����ʱ��=����ص�=ҵ������";
            }
            
            Vector v = new Vector();            
            v.add(title);
            v.add(rw.getReturnObject());
            
            Map map = new HashMap();
            
            //���÷��ؽ��
            map.put("returnObj", v);
            
            //���÷�����Ϣ
            String returnMsg = rw.getReturnMsg();            
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else
        {
            String returnMsg = rw.getReturnMsg();
            
            Map map = new HashMap();
            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            // add begin g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
            map.put("errcode", rw.getErrcode());
            // add end g00140516 2012/08/07 R003C12L08n01 ������������������ʾ��Ϣ����
            
            return map;
        }
       
    }
}
