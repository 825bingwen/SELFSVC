package com.customize.cq.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;

public class PrivAcceptCQBean extends BaseBeanCQImpl{
	
//	public Map qryPrivInfo(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String sn) {
//		
//		Map<String,String> paramMap = new HashMap<String,String>();
//        
//        // 设置操作员id
//        paramMap.put("curOper", terminalInfoPO.getOperid());
//        
//        // 设置终端机id
//        paramMap.put("atsvNum", terminalInfoPO.getTermid());
//        
//        // 设置客户手机号
//        paramMap.put("telnumber", customer.getServNumber());
//        
//        // 设置客户接触id
//        paramMap.put("touchoid", customer.getContactID());
//        
//        // 设置当前菜单
//        paramMap.put("curMenuId", curMenuId);
//        
//        // 序号
//        paramMap.put("sn", sn);
//        
//        ReturnWrap rw = this.getSelfSvcCallCQ().qryPrivInfo(paramMap);
//        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
//        {
//        	CRSet v = (CRSet) rw.getReturnObject();
//            String returnMsg = rw.getReturnMsg();
//            Map map = new HashMap();
//            // 设置返回结果
//            map.put("returnObj", v);
//            
//            // 设置返回信息
//            map.put("returnMsg", returnMsg);
//            
//            return map;
//        }
//        return null;
//	}
//	
//	public Map privAcceptCommit(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId,String isSubmit,String nCode){
//		Map<String,String> paramMap = new HashMap<String,String>();
//        
//        // 设置操作员id
//        paramMap.put("curOper", terminalInfoPO.getOperid());
//        
//        // 设置终端机id
//        paramMap.put("atsvNum", terminalInfoPO.getTermid());
//        
//        // 设置客户手机号
//        paramMap.put("telnumber", customer.getServNumber());
//        
//        // 设置客户接触id
//        paramMap.put("touchoid", customer.getContactID());
//        
//        // 设置当前菜单
//        paramMap.put("curMenuId", curMenuId);
//        
//        //设置当前受理优惠
//        paramMap.put("nCode", nCode);
//        
//        //设置当前受理地区
//        paramMap.put("issubmit", isSubmit);
//        
//        //设置当前受理的类型
//        paramMap.put("sType", "PCOpRec");
//        
//        ReturnWrap rw = this.getSelfSvcCallCQ().privAcceptCommit(paramMap);
//        
//        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
//        {
//            CRSet v = (CRSet)rw.getReturnObject();
//            String returnMsg = rw.getReturnMsg();
//            Map map = new HashMap();
//            
//            // 设置返回结果
//            map.put("returnObj", v);
//
//            // 设置返回信息
//            map.put("returnMsg", returnMsg);
//            
//            //设置返回状态
//            map.put("status", "1");
//            
//            return map;
//        }else{
//        	String returnMsg = rw.getReturnMsg();
//        	Map map = new HashMap();
//        	map.put("status", "0");
//        	map.put("returnMsg", returnMsg);
//        	return map;
//        	
//        } 
//	}
}
