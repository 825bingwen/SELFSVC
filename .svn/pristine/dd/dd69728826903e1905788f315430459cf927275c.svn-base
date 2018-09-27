/*
 * 文件名：MonthFeeBean.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：月账单查询Bean
 * 修改人：g00140516
 * 修改时间：2010-12-8
 * 修改内容：新增
 * 
 */
package com.customize.cq.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 月账单查询
 * 
 * @author g00140516
 * @version 1.0，2010-12-8
 * @see
 * @since
 */
public class MonthFeeBean extends BaseBeanCQImpl
{
    /**
     * 非当前月账单查询
     * 
     * @param customerSimp，用户信息
     * @param terminalInfo，终端机信息
     * @param month，查询月份
     * @param curMenuId，当前菜单
     * @return 当前月账单查询
     * @see
     */
    public ReturnWrap qryMonthBill(String telnum, TerminalInfoPO terminalInfo, String month,
            String curMenuId,String billcycle)
    {
        Map map = new HashMap();
        map.put("telnumber", telnum);
        map.put("billCycle", month);
        map.put("menuID", curMenuId);
        map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallCQ().qryMonthBill(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	return rw;
        }
        else 
        {
        	return null;
        }
    }
    
    /**
     * 查询用户是否已开通手机邮箱
     * @return
     */
    public String qrymailBox(String telnum, TerminalInfoPO terminalInfo,String curMenuId)
    {
    	Map map = new HashMap();
        map.put("telnum", telnum);
        map.put("email", telnum+"@139.com");
        map.put("menuID", curMenuId);
        map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallCQ().qrymailBox(map);
        
        String mailFlag = null;
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	CTagSet tagset = (CTagSet) rw.getReturnObject();
        	mailFlag = tagset.GetValue("havemailbox");
        }
        
    	return mailFlag;
    }
    
    /**
     * 开通139免费邮箱
     */
    public String add139Mail(String telnum, TerminalInfoPO terminalInfo,String curMenuId)
    {
    	Map map = new HashMap();
        map.put("telnum", telnum);
        map.put("email", telnum+"@139.com");
        map.put("menuID", curMenuId);
        map.put("touchOID", "");
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallCQ().add139Mail(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	return "1";
        }
        else
        {
        	return "0";
        }
    }
}
