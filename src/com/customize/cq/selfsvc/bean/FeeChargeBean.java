package com.customize.cq.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * ���ѳ�ֵ�ɷ�
 * @author xkf29026
 *
 */
public class FeeChargeBean  extends BaseBeanCQImpl
{
    
    /**
     * ���ѳ�ֵ�˻���Ϣ��ѯ
     * @param termInfo �ն˻���Ϣ
     * @param servnumber �ֻ�����
     * @param curMenuId ��ǰ�˵�
     * @param bankNo �ɷѷ�ʽ
     * @param payDate �ɷ�ʱ��
     * @param acceptType ��������
     * @return
     */
    public Map qryfeeChargeAccount(TerminalInfoPO termInfo, String servnumber, String curMenuId, String bankNo,
            String payDate, String acceptType)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        //���ò���Աid
        paramMap.put("operid", termInfo.getOperid());
        
        //�����ն˻�id
        paramMap.put("atsvNum", termInfo.getTermid());
        
        //���ÿͻ��ֻ���
        paramMap.put("servnumber", servnumber);
        
        //���ÿͻ��Ӵ�id
        paramMap.put("touchoid", "");
        
        //���õ�ǰ�˵�id
        paramMap.put("menuid", curMenuId);
        
        //�ɷѷ�ʽ
        paramMap.put("bankNo", bankNo);
        
        //�ɷ�����
        paramMap.put("payDate", payDate);
        
        //��������
        paramMap.put("acceptType", acceptType);
        
        //��ȡ���
        ReturnWrap rw = this.getSelfSvcCallCQ().qryfeeChargeAccount(paramMap);
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
     * @param termInfo �ն˻���Ϣ
     * @param curMenuId ��ǰ�˵�
     * @param servnumber �ֻ�����
     * @param bankNo �ɷѷ�ʽ
     * @param payDate �ɷ�ʱ��
     * @param terminalSeq �ն����к�
     * @param money �ɷѽ��
     * @param acceptType ��������
     * @param invoiceType ��Ʊ����
     * @param bankSite
     * @param bankOper
     * @return
     */
    public Map chargeCommit(TerminalInfoPO termInfo, String curMenuId, String servnumber, String bankNo,
            String payDate, String terminalSeq, String money, String acceptType, String invoiceType, String bankSite,
            String bankOper)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("operid", termInfo.getOperid());
        paramMap.put("termid", termInfo.getTermid());
        paramMap.put("menuid", curMenuId);
        paramMap.put("servnumber", servnumber);
        paramMap.put("bankNo", bankNo);
        paramMap.put("payDate", payDate);
        paramMap.put("terminalSeq", terminalSeq);
        paramMap.put("money", money);
        paramMap.put("acceptType", acceptType);
        paramMap.put("invoiceType", invoiceType);
        paramMap.put("bankSite", bankSite);
        paramMap.put("bankOper", bankOper);
        paramMap.put("touchoid", "");
        
        ReturnWrap rw = this.getSelfSvcCallCQ().chargeCommit(paramMap);
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
        
        ReturnWrap rw = this.getSelfSvcCallCQ().sendSMS(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * ��У�����룬ֱ�ӻ�ȡ�û���Ϣ
     * 
     * @param servnumber���ֻ�����
     * @param password����������
     * @param termInfo���ն˻���Ϣ
     * @return �û���Ϣ���������null��˵�������֤ʧ��
     */
    public Map getUserStatus(String servnumber, String password, TerminalInfoPO termInfo)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnum", servnumber);
        paramMap.put("password", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallCQ().getUserStatus(paramMap);
        Map map = new HashMap();
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet cout = (CTagSet) rw.getReturnObject();
            
            map.put("status", cout.GetValue("status") == null ? "" : cout.GetValue("status"));
            map.put("servRegion", cout.GetValue("region") == null ? "" : cout.GetValue("region"));
            return map;
        }
        
        return null;
    }
}
