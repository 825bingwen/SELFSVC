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
     * 非当前月账单查询
     * 
     * @param customerSimp，用户信息
     * @param terminalInfo，终端机信息
     * @param month，查询月份
     * @param curMenuId，当前菜单
     * @param qryType，查询类型
     * @return 当前月账单查询
     * @see
     */
    public Map qryMonthBillAy(NserCustomerSimp customerSimp, TerminalInfoPO terminalInfoPO, String month,
            String curMenuId){
    	 Map paramMap = new HashMap();
    	  // 设置操作员id
         paramMap.put("curOper", terminalInfoPO.getOperid()); 
         // 设置终端机id
         paramMap.put("atsvNum", terminalInfoPO.getTermid());
         // 设置客户接触id
         paramMap.put("touchoid", customerSimp.getContactId());  
         // 设置当前菜单
         paramMap.put("curMenuId", curMenuId);   
         // 设置客户手机号
         paramMap.put("telnumber", customerSimp.getServNumber());
         // 设置参数月份
         paramMap.put("month",month);
         
         ReturnWrap rw = this.getSelfSvcCallHub().qryBillAanlysis(paramMap);
         if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
         {
        	 //---------ebus接口改造----------
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
             // 设置返回结果
             map.put("returnObj", v);    
             // 设置返回信息
             map.put("returnMsg", returnMsg);
             
             return map;
         }
         return null;
	}
}
