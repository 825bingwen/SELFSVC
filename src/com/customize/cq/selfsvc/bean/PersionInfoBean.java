package com.customize.cq.selfsvc.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

//add begin l00190940 2011-11-17 我的信息查询（重庆）
/**
 * 重庆个人信息查询
 * 
 * @author yKF28472
 * 
 */
public class PersionInfoBean extends BaseBeanCQImpl
{
    @SuppressWarnings("unchecked")
    public Map qryComboInfo(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String month)
    {
        Map paramMap = new HashMap();
        
        // 设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnumber", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        // 设置账期为当前月份
        paramMap.put("cycle", month);
           
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = getSelfSvcCallCQ().qryComboInfo(paramMap);
        if (rw != null)
        {
        	CRSet v = (CRSet)rw.getReturnObject();
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
//add end l00190940 2011-11-17 我的个人信息查询（重庆）
    
    /**
     * 查询本机品牌资费及已开通功能（产品展示）
     * @param terminalInfoPO 终端机信息
     * @param customer 客户信息
     * @param curMenuId 当前菜单
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map qryFavourable(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId)
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
        
        //设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = getSelfSvcCallCQ().qryFavourable(paramMap);
        if (rw != null)
        {
        	CRSet crset = (CRSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", crset);
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        return null;
    }
}
