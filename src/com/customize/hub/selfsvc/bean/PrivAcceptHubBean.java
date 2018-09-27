package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;

public class PrivAcceptHubBean extends BaseBeanHubImpl{
	
	public Map qryPrivInfo(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String sn,String favorType) 
	{
		
		Map<String,String> paramMap = new HashMap<String,String>();
        
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
        
        // ���
        paramMap.put("sn", sn);
        
        paramMap.put("favorType", favorType);
        
        ReturnWrap rw = this.getSelfSvcCallHub().qryPrivInfo(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	CRSet v = (CRSet) rw.getReturnObject();
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
	
	//modified by xkf57421 for add parameter payType:Cash,Card(isSubmit=0||1)
	public Map privAcceptCommit(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId,String isSubmit,String nCode,String payType){
		Map<String,String> paramMap = new HashMap<String,String>();
        
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
        
        //���õ�ǰ�����Ż�
        paramMap.put("nCode", nCode);
        
        //���õ�ǰ�������
        paramMap.put("issubmit", isSubmit);
        
        //���õ�ǰ���������
        paramMap.put("sType", "PCOpRec");
        
        //add by xkf57421 for bsacAtsvCard begin
        paramMap.put("payType", payType);
        //add by xkf57421 for bsacAtsvCard end
        
        ReturnWrap rw = this.getSelfSvcCallHub().privAcceptCommit(paramMap);
        // begin zKF66389 9�·�findbugs�޸�
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        // end zKF66389 9�·�findbugs�޸�
        {
            CRSet v = (CRSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // ���÷��ؽ��
            map.put("returnObj", v);

            // ���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            //���÷���״̬
            map.put("status", "1");
            
            return map;
        }else{
        	String returnMsg = rw.getReturnMsg();
        	Map map = new HashMap();
        	map.put("status", "0");
        	map.put("returnMsg", returnMsg);
        	return map;
        	
        } 
	}
}
