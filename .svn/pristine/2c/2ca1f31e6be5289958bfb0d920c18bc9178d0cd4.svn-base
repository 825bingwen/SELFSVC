/*
 * 文件名：RegisterCardBean.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：非实名制认证Bean
 * 修改人：wWX217192
 * 修改时间：2014-06-23
 * 修改内容：新增
 * 
 */
package com.customize.sd.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.customize.sd.selfsvc.realNameReg.model.ChargeRecordPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 非实名制认证补卡
 * 
 * @author wWX217192
 * @version 1.0，2014-06-23
 * @see
 * @since
 */
public class NoRealNameRegBean extends BaseBeanSDImpl 
{
	/**
	 * 查询用户实名制登记标志
	 * @param 手机号码
	 * @param 终端信息
	 * @param 菜单信息
	 * @return 接口调用成功与否的标志位及接口返回信息
	 * @remark create wWX217192 2014-06-23 OR_huawei_201406_338
	 */
	public Map<String, String> qryRealNameType(String telNum, TerminalInfoPO termInfo, String curMenuId)
	{
		// 保存查询用户实名制登记标记的结果
		Map<String, String> resultMap = new HashMap<String, String>();
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("telnum", telNum);
		map.put("menuID", curMenuId);
		map.put("touchOID", "");
		map.put("operID", termInfo.getOperid());
		map.put("termID", termInfo.getTermid());
		
		ReturnWrap rw = this.getSelfSvcCallSD().qryRealNameType(map);
		
		// 接口查询成功，且接口返回成功信息
		if(SSReturnCode.SUCCESS == rw.getStatus())
		{
			CTagSet tagSet = (CTagSet)rw.getReturnObject();
			
			resultMap.put("isRealName", tagSet.GetValue("isrealname"));
			
			resultMap.put("returnMsg", rw.getReturnMsg());
			
			return resultMap;
		}
		else
		{
			resultMap.put("returnMsg", 
					StringUtils.isEmpty(rw.getReturnMsg())? "查询非实名制认证标记失败!" : rw.getReturnMsg());
			
			return resultMap;
		}
	}
	
	/**
	 * 生成短信随机密码
	 * 
	 * @param telNum
	 * @param termInfo
	 * @param curMenuId
	 * @return 接口返回信息：得到随机密码及接口操作的返回信息
	 * @remark create wWX217192 2014-06-26 OR_huawei_201406_338
	 */
	public Map<String, String> getRandomPwd(String telNum, TerminalInfoPO termInfo, String curMenuId)
	{
		// 保存生成随机密码的接口返回结果
		Map<String, String> resultMap = new HashMap<String, String>();
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("telnum", telNum);
		map.put("menuID", curMenuId);
		map.put("touchOID", "");
		map.put("operID", termInfo.getOperid());
		map.put("termID", termInfo.getTermid());
		map.put("dorectype", "实名登记受理");
		map.put("subcmdid", "4");
		
		ReturnWrap rw = this.getSelfSvcCallSD().getRandomPwd(map);
		
		// 接口调用成功，且接口返回成功信息
		if(SSReturnCode.SUCCESS == rw.getStatus())
		{
			CTagSet tagSet = (CTagSet) rw.getReturnObject();
			
			resultMap.put("randomPwd", tagSet.GetValue("new_passwd"));
			
			resultMap.put("returnMsg", rw.getReturnMsg());
			
			return resultMap;
		}
		else
		{
			resultMap.put("returnMsg", 
					StringUtils.isEmpty(rw.getReturnMsg())? "生成短信随机密码失败!" : rw.getReturnMsg());
			
			return resultMap;
		}
	}
	
	/**
	 * 随机短信密码验证
	 * 
	 * @param telNum
	 * @param termInfo
	 * @param curMenuId
	 * @param randomPwd
	 * @return 接口返回信息
	 * @remark create wWX217192 2014-06-27 OR_huawei_201406_338
	 */
	public Map<String, String> checkRandomPwd(String telNum, TerminalInfoPO termInfo, String curMenuId, String randomPwd)
	{
		// 返回给action层的resultMap
		Map<String, String> resultMap = new HashMap<String, String>();
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("telnum", telNum);
		map.put("randompwd", randomPwd);
		map.put("menuID", curMenuId);
		map.put("touchOID", "");
		map.put("operID", termInfo.getOperid());
		map.put("termID", termInfo.getTermid());
		
		ReturnWrap rw = this.getSelfSvcCallSD().checkRandomPwd(map);
		
		// 接口调用成功，且接口返回成功信息
		if(SSReturnCode.SUCCESS == rw.getStatus())
		{
			CTagSet tagSet = (CTagSet) rw.getReturnObject();
			
			// 验证结果
			resultMap.put("authChkResult", tagSet.GetValue("authchkresult"));
			
			// 验证结果信息
			resultMap.put("authChkMsg", tagSet.GetValue("authchkmsg"));
			
			return resultMap;
		}
		// 接口调用成功，但接口返回状态为失败
		else
		{
			resultMap.put("returnMsg", 
					StringUtils.isEmpty(rw.getReturnMsg()) ? "短信随机密码验证失败，请重新输入!" : rw.getReturnMsg());
			
			return resultMap;
		}
	}
	
	/**
	 * 验证个人密码
	 * 
	 * @param telNum
	 * @param termInfo
	 * @param curMenuId
	 * @param passwd
	 * @return 接口返回信息
	 * @remark create wWX217192 2014-06-28 OR_huawei_201406_338
	 */
	public Map<String, String> checkUserPwd(String telNum, TerminalInfoPO termInfo, String curMenuId, String passwd)
	{
		Map<String, String> resultMap = new HashMap<String, String>();
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("telnum", telNum);
		map.put("passwd", passwd);
		map.put("menuID", curMenuId);
		map.put("touchOID", "");
		map.put("operID", termInfo.getOperid());
		map.put("termID", termInfo.getTermid());
		
		ReturnWrap rw = this.getSelfSvcCallSD().checkUserPwd(map);
		
		// 接口调用成功，且接口返回成功信息
		if(SSReturnCode.SUCCESS == rw.getStatus())
		{
			CTagSet tagSet = (CTagSet) rw.getReturnObject();
			
			// 验证结果
			resultMap.put("authChkResult", tagSet.GetValue("authchkresult"));
			
			// 验证结果信息
			resultMap.put("authChkMsg", tagSet.GetValue("authchkmsg"));
			
			return resultMap;
		}
		// 接口调用成功，但接口返回状态为失败
		else
		{
			resultMap.put("returnMsg", 
					StringUtils.isEmpty(rw.getReturnMsg()) ? "个人密码验证失败，请重新输入!" : rw.getReturnMsg());
			
			return resultMap;
		}
	}
	
	/**
	 * SIM卡卡号验证
	 * @param telNum
	 * @param termInfo
	 * @param curMenuId
	 * @param simNo
	 * @return 报文返回信息
	 * @remark create wWX217192 2014-06-30 OR_huawei_201406_338
	 */
	public Map<String, String> checkSIMCardNo(String telNum, TerminalInfoPO termInfo, String curMenuId, String simNo)
	{
		Map<String, String> resultMap = new HashMap<String, String>();
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("telnum", telNum);
		map.put("cardno", simNo);
		map.put("menuId", curMenuId);
		map.put("touchOId", "");
		map.put("operId", termInfo.getOperid());
		map.put("termId", termInfo.getTermid());
		
		ReturnWrap rw = this.getSelfSvcCallSD().chkSIMCardNo(map);
		
		if(SSReturnCode.SUCCESS == rw.getStatus())
		{
			CTagSet tagSet = (CTagSet) rw.getReturnObject();
			
			// 验证结果
			resultMap.put("authChkResult", tagSet.GetValue("authchkresult"));
			
			// 验证结果信息
			resultMap.put("authChkMsg", tagSet.GetValue("authchkmsg"));
			
			return resultMap;
		}
		// 接口调用成功，但接口返回状态为失败
		else
		{
			resultMap.put("returnMsg", 
					StringUtils.isEmpty(rw.getReturnMsg()) ? "SIM卡卡号验证失败，请重新输入!" : rw.getReturnMsg());
			
			return resultMap;
		}
	}
	
	/**
	 * 交易记录验证
	 * 
	 * @param telNum
	 * @param termInfo
	 * @param curMenuId
	 * @param chargeRecordPO
	 * @return 报文返回信息
	 * @remark create wWX217192 2014-07-01 OR_huawei_201406_338
	 */
	public Map<String, String> checkChargeRecord(String telNum, TerminalInfoPO termInfo, String curMenuId, ChargeRecordPO chargeRecordPO)
	{
		Map<String, String> resultMap = new HashMap<String, String>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("telnum", telNum);
		map.put("chargeRecordPO", chargeRecordPO);
		map.put("menuId", curMenuId);
		map.put("touchOId", "");
		map.put("operId", termInfo.getOperid());
		map.put("termId", termInfo.getTermid());
		
		ReturnWrap rw = this.getSelfSvcCallSD().chkChargeRecord(map);
		
		if(null != rw && SSReturnCode.SUCCESS == rw.getStatus())
		{
			CTagSet tagSet = (CTagSet) rw.getReturnObject();
			
			// 验证结果
			resultMap.put("authChkResult", tagSet.GetValue("authchkresult"));
			
			return resultMap;
		}
		else if(null != rw)
		{
			resultMap.put("returnMsg", 
					StringUtils.isEmpty(rw.getReturnMsg())? "交易记录验证失败，请重新输入!" : rw.getReturnMsg());
			
			return resultMap;
		}
		return null;
	}
	
	/**
	 * 验证通话记录
	 * 
	 * @param telNum
	 * @param termInfo
	 * @param curMenuId
	 * @param callNums
	 * @return 接口返回信息
	 * @remark create wWX217192 2014-07-03 OR_huawei_201406_338
	 */
	public Map<String, String> checkCallRecord(String telNum, TerminalInfoPO termInfo, String curMenuId, String callNums)
	{
		Map<String, String> resultMap = new HashMap<String, String>();
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("telnum", telNum);
		map.put("calledNum", callNums);
		map.put("menuId", curMenuId);
		map.put("touchOId", "");
		map.put("operId", termInfo.getOperid());
		map.put("termId", termInfo.getTermid());
		
		ReturnWrap rw = this.getSelfSvcCallSD().chkCallRecord(map);
		
		if(SSReturnCode.SUCCESS == rw.getStatus())
		{
			CTagSet tagSet = (CTagSet) rw.getReturnObject();
			
			// 验证结果
			resultMap.put("authChkResult", tagSet.GetValue("authchkresult"));
			
			return resultMap;
		}
		else
		{
			resultMap.put("returnMsg", 
					StringUtils.isEmpty(rw.getReturnMsg())? "非实名制认证通话记录验证失败" : rw.getReturnMsg());
			
			return resultMap;
		}
	}
} 
