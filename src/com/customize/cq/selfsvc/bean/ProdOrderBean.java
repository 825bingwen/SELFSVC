package com.customize.cq.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 产品查询受理
 * @author z90080209
 * @date   Oct 28, 2011
 */
public class ProdOrderBean extends BaseBeanCQImpl
{
//    /**
//     * 产品查询
//     * 
//     * @param terminalInfoPO 终端机信息
//     * @param customer 客户信息
//     * @param curMenuId 当前菜单
//     * @param sn 序号
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public Map queryService(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String sn)
//    {
//        Map paramMap = new HashMap();
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
//        ReturnWrap rw = selfSvcCall.queryService(paramMap);
//        Map map = new HashMap();
//        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
//        {
//            CRSet v = (CRSet)rw.getReturnObject();
//            String returnMsg = rw.getReturnMsg();
//            
//            // 设置返回结果
//            map.put("returnObj", v);
//            
//            // 设置返回信息
//            map.put("returnMsg", returnMsg);
//            
//            return map;
//        }
//        else
//        {
//        	String returnMsg = rw.getReturnMsg();
//        	
//        	// 设置返回信息
//            map.put("returnMsg", returnMsg);
//            return map;
//        }
//    }
}
