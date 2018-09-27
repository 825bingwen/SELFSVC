package com.customize.cq.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 账/详单139邮箱定制bean
 * 
 * @author l00190940
 * 
 */

public class MailBillSendBean extends BaseBeanCQImpl
{
	/**
	 * 判断用户是否开通email发送账/详单功能
	 * 
	 * @param termInfo 终端机信息
	 * @param customer 用户信息
	 * @param curMenuId 当前菜单
	 * @param billType 账单类型
	 * @param mailType 邮寄类型
	 * @return
	 */
	public Map emailService(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String billType, String mailType)
	{
		Map paramMap = new HashMap();
		
		 // 设置操作员id
        paramMap.put("operID", termInfo.getOperid());
        
        // 设置终端机id
        paramMap.put("termID", termInfo.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnum", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        // 设置账单类型
        paramMap.put("billtype", billType);
        
        // 设置邮寄类型
        paramMap.put("mailtype", mailType);
        
        ReturnWrap rw = getSelfSvcCallCQ().getMailBillSendInfo(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	//判断用户是否开通了email发送账/详单功能
        	String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        	if(Constants.PROOPERORGID_CQ.equals(province))
        	{
        		CTagSet result = (CTagSet)rw.getReturnObject();
        		String returnMsg = rw.getReturnMsg();
                Map map = new HashMap();
                
                // 设置返回结果
                map.put("returnObj", result);
                
                // 设置返回信息
                map.put("returnMsg", returnMsg);
                
                return map;
        	}
        }
        else
        {
            return null;
        }
		return null;
	}
	
	/**
	 * 取消原来的账单或详单寄送功能
	 * 
	 * @param termInfo 终端机信息
	 * @param customer 用户信息
	 * @param curMenuId 当前菜单
	 * @param oid 寄送的记录标识
	 * @return
	 */
	public Map cancelBillMail(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String oid)
	{
		Map paramMap = new HashMap();
		
		 // 设置操作员id
        paramMap.put("operID", termInfo.getOperid());
        
        // 设置终端机id
        paramMap.put("termID", termInfo.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnum", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        // 设置账单类型
        paramMap.put("oid", oid);
        
        ReturnWrap rw = getSelfSvcCallCQ().getCancelCaseInfo(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        	if(Constants.PROOPERORGID_CQ.equals(province))
        	{
        		CTagSet result = (CTagSet)rw.getReturnObject();
        		String returnMsg = rw.getReturnMsg();
                Map map = new HashMap();
                
                // 设置返回结果
                map.put("returnObj", result);
                
                // 设置返回信息
                map.put("returnMsg", returnMsg);
                
                return map;
        	}
        }
        else
        {
            return null;
        }
		return null;
	}

	/**
	 * 开通账单或详单寄送功能
	 * 
	 * @param termInfo 终端机信息
	 * @param customer 用户信息
	 * @param curMenuId 当前菜单
	 * @param billType 账单类型
	 * @param langType 语言
	 * @param mailType 邮寄类型
	 * @param mailAddr 邮寄地址
	 * @param postCode 邮编
	 * @param linkPhone 联系电话
	 * @param linkMan 联系人
	 * @param email 邮箱地址
	 * 
	 * @return
	 */
	public Map openBillMail(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String billType, String langType, String mailType, String mailAddr, String postCode, String linkPhone, String linkMan, String email) 
	{
		Map paramMap = new HashMap();
		
		// 设置操作员id
        paramMap.put("operID", termInfo.getOperid());
       
        // 设置终端机id
        paramMap.put("termID", termInfo.getTermid());
       
        // 设置客户手机号
        paramMap.put("telnum", customer.getServNumber());
       
        // 设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
       
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        // 设置账单类型
        paramMap.put("billtype", billType);
        
        // 设置语言
        paramMap.put("langtype", langType);
        
        // 设置邮寄类型
        paramMap.put("mailtype", mailType);
        
        // 设置邮寄地址
        paramMap.put("mailaddr", mailAddr);
        
        // 设置邮政编码
        paramMap.put("postcode", postCode);
        
        // 设置联系电话
        paramMap.put("linkphone", linkPhone);
        
        // 设置联系人
        paramMap.put("linkman", linkMan);
        
        // 设置邮箱地址
        paramMap.put("email", email);
       
        ReturnWrap rw = getSelfSvcCallCQ().getOpenBillMailInfo(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        	if(Constants.PROOPERORGID_CQ.equals(province))
        	{
        		CTagSet result = (CTagSet)rw.getReturnObject();
        		String returnMsg = rw.getReturnMsg();
                Map map = new HashMap();
                
                // 设置返回结果
                map.put("returnObj", result);
                
                // 设置返回信息
                map.put("returnMsg", returnMsg);
                
                return map;
        	}
        }
        else
        {
            return null;
        }
		return null;
	}
}
