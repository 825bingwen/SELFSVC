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
 * 停开机业务处理
 * @author xkf29026
 *
 */
public class RecOpenAndStopSubsBean extends BaseBeanImpl
{
    @SuppressWarnings("unchecked")
    public Map stopOpenSubs(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId,String stoptype,String reason)
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
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        //停开机类型
        paramMap.put("stoptype", stoptype);
        
        //停开机说明
        paramMap.put("reason", reason);
        
        ReturnWrap rw = selfSvcCall.stopOpenSubs(paramMap);
        Map map = new HashMap();
        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
        }
        else
        {
        	String returnMsg = rw.getReturnMsg();
        	map.put("returnMsg", returnMsg);
        }
        return map;
    }
}
