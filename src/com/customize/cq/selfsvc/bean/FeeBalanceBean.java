package com.customize.cq.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class FeeBalanceBean extends BaseBeanCQImpl
{
    
    /**
     * 账户余额查询
     * @param terminalInfoPO  终端机信息
     * @param customer 客户信息
     * @param menuid 当前菜单id
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map queryBalance(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String menuid)
    {
        Map<String,String> paramMap = new HashMap<String,String>();
        
        //设置操作员id
        paramMap.put("operid", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //设置客户手机号
        paramMap.put("telnumber", customer.getServNumber());
        
        //设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        //设置当前菜单id
        paramMap.put("menuid", menuid);        

        // 获取结果
        ReturnWrap rw = this.getSelfSvcCallCQ().queryBalance(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet) rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        return null;
    }
}
