/*
 * �ļ�����MonthFeeBean.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ���������˵���ѯBean
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-12-8
 * �޸����ݣ�����
 * 
 */
package com.customize.cq.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * ���˵���ѯ
 * 
 * @author g00140516
 * @version 1.0��2010-12-8
 * @see
 * @since
 */
public class MonthFeeBean extends BaseBeanCQImpl
{
    /**
     * �ǵ�ǰ���˵���ѯ
     * 
     * @param customerSimp���û���Ϣ
     * @param terminalInfo���ն˻���Ϣ
     * @param month����ѯ�·�
     * @param curMenuId����ǰ�˵�
     * @return ��ǰ���˵���ѯ
     * @see
     */
    public ReturnWrap qryMonthBill(String telnum, TerminalInfoPO terminalInfo, String month,
            String curMenuId,String billcycle)
    {
        Map map = new HashMap();
        map.put("telnumber", telnum);
        map.put("billCycle", month);
        map.put("menuID", curMenuId);
        map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallCQ().qryMonthBill(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	return rw;
        }
        else 
        {
        	return null;
        }
    }
    
    /**
     * ��ѯ�û��Ƿ��ѿ�ͨ�ֻ�����
     * @return
     */
    public String qrymailBox(String telnum, TerminalInfoPO terminalInfo,String curMenuId)
    {
    	Map map = new HashMap();
        map.put("telnum", telnum);
        map.put("email", telnum+"@139.com");
        map.put("menuID", curMenuId);
        map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallCQ().qrymailBox(map);
        
        String mailFlag = null;
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	CTagSet tagset = (CTagSet) rw.getReturnObject();
        	mailFlag = tagset.GetValue("havemailbox");
        }
        
    	return mailFlag;
    }
    
    /**
     * ��ͨ139�������
     */
    public String add139Mail(String telnum, TerminalInfoPO terminalInfo,String curMenuId)
    {
    	Map map = new HashMap();
        map.put("telnum", telnum);
        map.put("email", telnum+"@139.com");
        map.put("menuID", curMenuId);
        map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallCQ().add139Mail(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	return "1";
        }
        else
        {
        	return "0";
        }
    }
}
