package com.customize.sd.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 账/详单139邮箱定制bean
 * 
 * @author l00190940
 * 
 */

public class MailBillSendBean extends BaseBeanSDImpl
{
	/**
	 * 判断用户是否开通email发送账/详单功能
	 * 
	 * @param termInfo 终端机信息
	 * @param customer 用户信息
	 * @param CurMenuid 当前菜单
	 * @param billType 账单类型
	 * @param mailType 邮寄类型
	 * @return
	 */
	public Map<String,Object> emailService(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String billType, String mailType)
	{
		//modify begin m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
		Map<String,String> paramMap = new HashMap<String,String>();
		//modify end m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
		
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
        
        ReturnWrap rw = getSelfSvcCallSD().getMailBillSendInfo(paramMap);
        
        //modify end m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
        Map<String,Object> map = new HashMap<String,Object>();
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {	
            // 设置返回结果
            map.put("returnObj", rw.getReturnObject());
            
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            //设置返回码
            map.put("errcode", String.valueOf(rw.getErrcode()));
            
            return map;
        }
       
		return null;
        //modify end m00227318 2012/10/17 V200R003C12L10 OR_SD_201209_443
	}
	
	/**
	 * 取消原来的账单或详单寄送功能
	 * 
	 * @param termInfo 终端机信息
	 * @param customer 用户信息
	 * @param CurMenuid 当前菜单
	 * @param oid 寄送的记录标识
	 * @return
	 */
	public Map<String,Object> cancelBillMail(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String oid)
	{
		//modify begin m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
		Map<String,Object> paramMap = new HashMap<String,Object>();
		//modify end m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
		
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
        
        ReturnWrap rw = getSelfSvcCallSD().getCancelCaseInfo(paramMap);
        
        //modify begin m00227318 2012/10/18 V200R003C12L10 OR_SD_201209_443
        Map<String,Object> map = new HashMap<String,Object>();
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {	
            // 设置返回结果
            map.put("returnObj", rw.getReturnObject());
                
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
                
            return map;
        }
        //modify end m00227318 2012/10/18 V200R003C12L10 OR_SD_201209_443
        
		return null;
	}

	/**
	 * 开通账单或详单寄送功能
	 * 
	 * @param termInfo 终端机信息
	 * @param customer 用户信息
	 * @param CurMenuid 当前菜单
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
	public Map<String,Object> openBillMail(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String billType, String langType, String mailType, String mailAddr, String postCode, String linkPhone, String linkMan, String email) 
	{
		//modify begin m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
		Map<String,Object> paramMap = new HashMap<String,Object>();
		//modify end m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
		
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
       
        ReturnWrap rw = getSelfSvcCallSD().getOpenBillMailInfo(paramMap);
        
        //modify begin m00227318 2012/10/18 V200R003C12L10 OR_SD_201209_443
        Map<String,Object> map = new HashMap<String,Object>();
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 设置返回结果
            map.put("returnObj", rw.getReturnObject());
                
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
                
            return map;
        }
        //modify end m00227318 2012/10/18 V200R003C12L10 OR_SD_201209_443
		return null;
	}
}
