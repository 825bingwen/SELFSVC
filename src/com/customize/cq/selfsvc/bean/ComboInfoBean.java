package com.customize.cq.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

//add begin l00190940 2011-11-17 套餐信息查询（重庆）
/**
 * 套餐信息查询
 * @author xkf29026
 *
 */
public class ComboInfoBean extends BaseBeanCQImpl
{
    /**
     * 套餐信息查询 
     * @param terminalInfoPO 终端信息
     * @param customer 客户信息
     * @param curMenuId 当前菜单
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map qryComboInfo(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId, String month)
    {
        Map paramMap = new HashMap();
        
        //设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //设置客户手机号
        paramMap.put("telnumber", customer.getServNumber());
        
        //设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        //设置账期
        paramMap.put("cycle", month);
        
        //设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = this.getSelfSvcCallCQ().qryComboInfo(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet v = (CRSet)rw.getReturnObject();
            
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", v);
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
        	String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
    }
}
//add end l00190940 2011-11-17 套餐信息查询（重庆）