package com.customize.nx.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.customize.nx.selfsvc.bean.impl.BaseBeanNXImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class PackageInfoBean extends BaseBeanNXImpl
{
    public Map qryPackageInfo(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String month)
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
        paramMap.put("billcycle", month);
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = this.getSelfSvcCallNX().qryPackageInfo(paramMap);
        if (rw != null)
        {
            Vector v = (Vector)rw.getReturnObject();
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