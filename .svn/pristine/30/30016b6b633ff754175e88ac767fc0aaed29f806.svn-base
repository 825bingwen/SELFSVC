package com.customize.hub.selfsvc.bean;


import java.util.HashMap;
import java.util.Map;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 新版账单查询
 * @author xkf57421
 * @version 1.0
 * @since  201202
 */
@SuppressWarnings("unchecked")
public class BillQueryHubBean extends BaseBeanHubImpl
{

	/**
	 * 查询当前月账单信息
	 * @param customerSimp，用户信息
	 * @param month，月份信息
	 * @return CRSet，用户信息集合
	 * @see [类、类#方法、类#成员]
	 */
	public CTagSet qryCurMonBillInfo(NserCustomerSimp customerSimp,TerminalInfoPO terminalInfo,
			String month,String curMenuId,String custType,String areaFlag)
	{
		Map map = new HashMap();
        map.put("telnum", customerSimp.getServNumber());
        map.put("billcycle", month);
        map.put("isunitepayment", custType);
        map.put("arealist", areaFlag);
        
        map.put("menuID", curMenuId);
        map.put("touchOID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = getSelfSvcCallHub().qryCurBillInfo(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return (CTagSet)rw.getReturnObject();
        }
        
        return null;
	}
	
	/**
	 * 查询当前月账单趋势图
	 * @param customerSimp
	 * @param terminalInfo
	 * @param month
	 * @param curMenuId
	 * @param custType
	 * @param areaFlag
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public CTagSet qryCurMonBillTrend(NserCustomerSimp customerSimp,TerminalInfoPO terminalInfo,
			String month,String curMenuId,String custType,String areaFlag)
	{
		Map map = new HashMap();
        map.put("telnum", customerSimp.getServNumber());
        map.put("billcycle", month);
        map.put("isunitepayment", custType);
        map.put("arealist", areaFlag);
        
        map.put("menuID", curMenuId);
        map.put("touchOID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = getSelfSvcCallHub().qryCurBillInfo(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return (CTagSet)rw.getReturnObject();
        }
        
        return null;
	}
	
	public CTagSet qryCurMonMvalue(NserCustomerSimp customerSimp,TerminalInfoPO terminalInfo,
			String month,String curMenuId,String custType,String areaFlag)
	{
		Map map = new HashMap();
        map.put("telnum", customerSimp.getServNumber());
        map.put("billcycle", month);
        map.put("isunitepayment", custType);
        map.put("arealist", areaFlag);
        
        map.put("menuID", curMenuId);
        map.put("touchOID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = getSelfSvcCallHub().qryCurBillInfo(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return (CTagSet)rw.getReturnObject();
        }
        
        return null;
	}
	   public String arQryBillCommuHuBExcelEbus(NserCustomerSimp customerSimp,TerminalInfoPO terminalInfo,
	            String month,String curMenuId,String custType,String areaFlag) throws Exception
	    {
	        Map map = new HashMap();
	        map.put("telnum", customerSimp.getServNumber());
	        map.put("billcycle", month);
	        map.put("isunitepayment", custType);
	        map.put("arealist", areaFlag);
	        
	        map.put("menuID", curMenuId);
	        map.put("touchOID", customerSimp.getContactId());
	        map.put("operID", terminalInfo.getOperid());
	        map.put("termID", terminalInfo.getTermid());
	        
	        return  getSelfSvcCallHub().arQryBillCommuHuBExcelEbus(map);
	    }
	/**
	 * 查询资费推荐信息
	 * @param customerSimp
	 * @param terminalInfo
	 * @param month
	 * @param curMenuId
	 * @param custType
	 * @param areaFlag
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public CTagSet qryBillValuate(NserCustomerSimp customerSimp,TerminalInfoPO terminalInfo,
			String month,String curMenuId,String custType,String areaFlag)
	{
		Map map = new HashMap();
        map.put("telnum", customerSimp.getServNumber());
        map.put("billcycle", month);
        map.put("isunitepayment", custType);
        map.put("arealist", areaFlag);
        
        map.put("menuID", curMenuId);
        map.put("touchOID", customerSimp.getContactId());
        map.put("operID", terminalInfo.getOperid());
        map.put("termID", terminalInfo.getTermid());
        
        ReturnWrap rw = getSelfSvcCallHub().qryCurBillInfo(map);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return (CTagSet)rw.getReturnObject();
        }
        
        return null;
	}
}
