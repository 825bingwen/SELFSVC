package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 当前状态查询 
 * @author xkf29026
 *
 */
public class CurrentStatusBean extends BaseBeanImpl
{
    @SuppressWarnings("unchecked")
    public Map queryCurrentStatus(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId)
    {
        Map paramMap = new HashMap();
     
        //设置操作员id
        paramMap.put("operid", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("termid", terminalInfoPO.getTermid());
        
        //设置客户手机号
        paramMap.put("telnum", customer.getServNumber());
        
        //设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        //设置当前菜单
        paramMap.put("menuid", curMenuId);
        
        ReturnWrap rw = selfSvcCall.queryCurrentStatus(paramMap);
        Map map = new HashMap();
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            
            //设置返回结果
            map.put("returnObj", v);
            
            //设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        else if (rw != null && rw.getStatus() == SSReturnCode.ERROR)
        {
        	CTagSet v = (CTagSet)rw.getReturnObject();
           
        	//设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        	
        return null;
    }
}
