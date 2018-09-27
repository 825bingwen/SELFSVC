/*
 * @filename: CurrBillFeeBean.java
 * @  All Right Reserved (C), 2013-2113, HUAWEI TECO CO.
 * @brif:  话费总额查询，同CRM的余额查询中的未出帐话费
 * @author: g00140516
 * @de:  2013/02/22
 * @description: 
 * @remark: create g00140516 2013/02/22 R003C13L02n01 OR_NX_201302_600
 */
package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 话费总额查询
 * @author g00140516
 *
 */
public class CurrBillFeeBean extends BaseBeanImpl
{
	/**
	 * 话费总额查询
	 * @param customerInfo 用户信息
	 * @param termInfo 终端机信息
	 * @param menuid 当前菜单
	 * @return
	 */
    public ReturnWrap qryCurrBillFee(NserCustomerSimp customerInfo,TerminalInfoPO termInfo,String menuid)
    {
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("touchoid", customerInfo.getContactId());// 统一接触流水
        paramMap.put("telnumber", customerInfo.getServNumber());// 手机号码
        paramMap.put("menuid", menuid);// 菜单ID
        paramMap.put("operID", termInfo.getOperid());// 操作员
        paramMap.put("termID", termInfo.getTermid());//终端编号
        
        return selfSvcCall.qryCurrBillFee(paramMap);
    }
}
