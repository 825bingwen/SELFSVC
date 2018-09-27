package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class BillAnalysisBean extends BaseBeanHubImpl {
	
	/**
     * �ǵ�ǰ���˵���ѯ
     * 
     * @param customerSimp���û���Ϣ
     * @param terminalInfo���ն˻���Ϣ
     * @param month����ѯ�·�
     * @param curMenuId����ǰ�˵�
     * @param qryType����ѯ����
     * @return ��ǰ���˵���ѯ
     * @see
     */
    public Map qryMonthBillAy(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfoPO, String month,
            String curMenuId){
    	 Map paramMap = new HashMap();
    	  // ���ò���Աid
         paramMap.put("curOper", terminalInfoPO.getOperid()); 
         // �����ն˻�id
         paramMap.put("atsvNum", terminalInfoPO.getTermid());
         // ���ÿͻ��Ӵ�id
         paramMap.put("touchoid", customerSimp.getContactId());  
         // ���õ�ǰ�˵�
         paramMap.put("curMenuId", curMenuId);   
         // ���ÿͻ��ֻ���
         paramMap.put("telnumber", customerSimp.getServNumber());
         // ���ò����·�
         paramMap.put("month",month);
         
         ReturnWrap rw = this.getSelfSvcCallHub().qryBillAanlysis(paramMap);
         if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
         {
        	 //---------ebus�ӿڸ���----------
        	 CTagSet v= new CTagSet();
        	 if(rw.getReturnObject() instanceof Vector)
        	 {
        		 Vector vc=(Vector)rw.getReturnObject();
        		 if(vc!=null && vc.size()>0)
        		 {
        			 if((vc.get(0)) instanceof CTagSet)
                	 {
        				 v= (CTagSet)vc.get(0);
                	 }
        		 }
        	 }
        	 if(rw.getReturnObject() instanceof CTagSet)
        	 {
        		 v = (CTagSet)rw.getReturnObject();
        	 }
        	 //----------------------------
//        	 CTagSet v = (CTagSet)rw.getReturnObject();
        	 
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
}
