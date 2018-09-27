package com.customize.nx.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.nx.selfsvc.bean.impl.BaseBeanNXImpl;
import com.customize.nx.selfsvc.invoice.model.CyclePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 月结发票bean
 * 
 * @author  jWX216858
 * @version  [版本号, 2014-6-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MonInvoicePrintBean extends BaseBeanNXImpl
{
	/**
	 * 获取打印发票数据
	 * 
	 * @param termInfo 终端机信息
	 * @param customer 客户信息
	 * @param curMenuId 当前菜单
	 * @param cycle 账期
	 * @param cyclepo 账期信息
	 * @return
	 * @remark create jWX216858 2014/06/17 OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印
	 */
	public ReturnWrap invoiceData(TerminalInfoPO termInfo,NserCustomerSimp customer, String curMenuId, CyclePO cyclepo)
	{
		Map<String, String> paramMap = new HashMap<String, String>();
		
		// 操作员id
		paramMap.put("operId", termInfo.getOperid());
		
		// 手机号
		paramMap.put("telnum", customer.getServNumber());
		
		// 终端id
		paramMap.put("termId", termInfo.getTermid());
		
		// 客户接触id
		paramMap.put("touchId", customer.getContactId());
		
		// 当前菜单
		paramMap.put("menuId", curMenuId);
		
		// 账期
		paramMap.put("cycle", cyclepo.getCycle());
		
		// 开始时间
		paramMap.put("startdate", cyclepo.getStartdate());
		
		// 结束时间
		paramMap.put("enddate", cyclepo.getEnddate());
		
		// 主账号
		paramMap.put("acctid", cyclepo.getAcctid());
		
		return this.getSelfSvcCallNX().getMonInvoiceData(paramMap);
	}
	
	/**
     * 月结发票的账期接口查询
     * 
     * @param customerSimp，用户信息
     * @param terminalInfo，终端机信息
     * @param month，查询月份
     * @param curMenuId，当前菜单
     * @param billcycle 账期
     * 
     * @return 账期信息
     * @see
     * @remark create jWX216858 2014/06/17 OR_NX_201406_553_宁夏_[营改增]自助终端提供增值税月结发票打印
     */
	public ReturnWrap qryBillCycle(String telNum, TerminalInfoPO terminalInfo, String curMenuId, String billCycle)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		// 手机号
		map.put("servnum", telNum);
		
		// 账期
		map.put("cycle", billCycle);
		
		// 当前菜单
		map.put("menuId", curMenuId);
		
		// 客户接触id
		map.put("touchId", "");
		
		// 操作员
        map.put("operId", terminalInfo.getOperid());
        
        // 终端机id
        map.put("termId", terminalInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallNX().qryBillCycle(map);
		
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return rw;
        }
        
        return null;
	}
}
