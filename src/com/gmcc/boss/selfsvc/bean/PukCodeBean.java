package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * PUK码查询
 * @author xkf29026
 *
 */
public class PukCodeBean extends BaseBeanImpl
{
    
    /**
     * PUK码查询
     * @param terminalInfoPO 
     * @param customer
     * @param curMenuId
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map queryPUK(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId)
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
        ReturnWrap rw = selfSvcCall.queryPUK(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	StringBuffer buf = new StringBuffer();
        	if(IntMsgUtil.isTransEBUS("BLCSQrySimPuk"))
        	{
        		CRSet rSet = (CRSet) rw.getReturnObject();
        		
        		buf.append("PIN1:" + rSet.GetValue(1, 0) + "\t" + "PUK1:" + rSet.GetValue(1, 2));
        		buf.append("\r\n");
        		buf.append("PIN2:" + rSet.GetValue(1, 1) + "\t" + "PUK2:" + rSet.GetValue(1, 3));
        	}
        	else
        	{
        		CTagSet cout = (CTagSet)rw.getReturnObject();
        		
                buf.append("PIN1:" + cout.GetValue("pin1") + "\t" + "PUK1:" + cout.GetValue("puk1"));
                buf.append("\r\n");
                buf.append("PIN2:" + cout.GetValue("pin2") + "\t" + "PUK2:" + cout.GetValue("puk2"));
        	}
        	
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", buf.toString());
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
        
    }
}
