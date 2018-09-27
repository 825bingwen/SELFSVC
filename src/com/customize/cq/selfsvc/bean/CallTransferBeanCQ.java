package com.customize.cq.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 呼叫转移 重庆定制
 * @author z90080209
 * @date   Nov 10, 2011
 */
public class CallTransferBeanCQ extends BaseBeanImpl
{
    /**
     * 开通、取消呼叫转移
     * 
     * @param customerSimp，用户信息
     * @param termInfo，终端机信息
     * @param transferType，呼转类型
     * @param transferNumber，呼转号码
     * @param recType，操作类型
     * @param menuID
     * @return
     * @see
     */
    public ReturnWrap commitCallTransferNo(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, String transferType,
            String transferNumber, String menuID, String recType)
    {
        Map map = new HashMap();
        
        map.put("servNumber", customerSimp.getServNumber());
    	map.put("dealType", recType);
        map.put("callType", transferType);
        map.put("transferNumber", transferNumber);
        map.put("menuID", menuID);
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", termInfo.getOperid());
        map.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.commitCallTransferNo(map);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return rw;
        }else{
        	return null;
        }
    }
}
