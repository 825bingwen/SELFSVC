/*
 * 文件名：CallTransferBean.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：呼叫转移开通、取消
 * 修改人：g00140516
 * 修改时间：2010-12-22
 * 修改内容：新增
 * 
 */
package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 呼叫转移开通、取消
 * 
 * 
 * @author g00140516
 * @version 1.0，2010-12-22
 * @see
 * @since
 */
public class CallTransferBean extends BaseBeanImpl
{
    /**
     * 开通、取消呼叫转移
     * 
     * @param customerSimp，用户信息
     * @param termInfo，终端机信息
     * @param transferType，呼转类型
     * @param transferNumber，呼转号码
     * @param menuID
     * @return
     * @see
     */
    public boolean commitCallTransferNo(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, String transferType,
            String transferNumber, String menuID)
    {
        Map map = new HashMap();
        
        map.put("servNumber", customerSimp.getServNumber());
        
        if (Constants.CALL_TRANSFER_CANCEL.equals(transferType))
        {
            map.put("dealType", "0");
        }
        else
        {
            map.put("dealType", "1");
        }
        
        map.put("callType", transferType);
        
        map.put("transferNumber", transferNumber);
        
        map.put("menuID", menuID);
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", termInfo.getOperid());
        map.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.commitCallTransferNo(map);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
}
