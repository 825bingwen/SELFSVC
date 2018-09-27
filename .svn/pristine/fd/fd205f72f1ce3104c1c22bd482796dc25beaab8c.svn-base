/*
* @filename: RealNameRegAction.java
* @  All Right Reserved (C), 2014-2018, HUAWEI TECO CO.
* @brif:  实名认证办理
* @author: hWX5316476
* @de:  2014-06-10 
* @description: 新增实名认证bean
* @remark: create hWX5316476 2014-06-11 V200R003C10LG0601 OR_SD_201405_849
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

public class RealNameRegBean extends BaseBeanSDImpl
{
    /**
     * 获取短信随机码，并发送短信随机码到手机
     * @param termInfoPO  终端机信息
     * @param curMenuId  当前菜单
     * @param telnum 手机号
     * @return
     */
	public Map<String, String> sendRandomPwd(TerminalInfoPO termInfoPO,String curMenuId, String telnum)
	{
	    // 参数
        Map<String,String> paramMap = new HashMap<String, String>();
        
        // 操作员id
        paramMap.put("operID", termInfoPO.getOperid());
        
        // 终端机id
        paramMap.put("termID", termInfoPO.getTermid());
        
        // 当前菜单
        paramMap.put("menuID", curMenuId);
        
        // 触摸id
        paramMap.put("touchOID", "");
        
        // 手机号
        paramMap.put("telnum", telnum);
        
        // 业务类型
        paramMap.put("dorectype", "实名登记受理");
        
        // 功能 4：生成并发送短信随机码
        paramMap.put("subcmdid", "4");
        
        // 调用生成并发送短信验证码接口
        ReturnWrap rw = getSelfSvcCallSD().getRandomPwd(paramMap);
        Map<String, String> map = new HashMap<String, String>();
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            
            // 新的随机密码
            map.put("new_passwd", v.GetValue("new_passwd"));
        }
        else
        {
            String retMsg = rw.getReturnMsg();
            
            // 设置返回信息
            map.put("returnMsg", (StringUtils.isEmpty(retMsg))?"对不起，短信随机码发送失败，请稍后再试!":retMsg);
        }
        return map;
	}
    
	/**
	 * 验证短信验证码 
	 * @param termInfoPO  终端机信息
	 * @param curMenuId  当前菜单
	 * @param telnum  手机号
	 * @param randomPwd   短信验证码
	 * @return
	 */
    public Map<String, String> verifyRandomPwd(TerminalInfoPO termInfoPO,String curMenuId, String telnum, String randomPwd)
    {
        // 参数
        Map<String,String> paramMap = new HashMap<String, String>();
        
        // 操作员id
        paramMap.put("operID", termInfoPO.getOperid());
        
        // 终端机id
        paramMap.put("termID", termInfoPO.getTermid());
        
        // 当前菜单
        paramMap.put("menuID", curMenuId);
        
        // 触摸id
        paramMap.put("touchOID", "");
        
        // 手机号
        paramMap.put("telnum", telnum);
        
        // 短信验证码
        paramMap.put("randompwd", randomPwd);
        
        // 调用短信验证码验证接口
        ReturnWrap rw = getSelfSvcCallSD().checkRandomPwd(paramMap);
        
        Map<String, String> map = new HashMap<String, String>();
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            
            // 验证结果0 失败  1 成功
            map.put("authchkresult", v.GetValue("authchkresult"));
            
            // 验证结果信息
            map.put("authchkmsg", v.GetValue("authchkmsg"));
        }
        else
        {
            String retMsg = rw.getReturnMsg();
            
            // 设置返回信息
            map.put("returnMsg", (StringUtils.isEmpty(retMsg))?"对不起，短信验证码验证失败!":retMsg);
        }
        return map;
    }
    
    /**
     * 个人服务密码验证
     * @param termInfoPO
     * @param curMenuId
     * @param telnum
     * @param password
     * @return
     */
    public Map<String, String> verifyServerPwd(TerminalInfoPO termInfoPO,String curMenuId,String telnum,String password)
    {
        // 参数
        Map<String,String> paramMap = new HashMap<String, String>();
        
        // 操作员id
        paramMap.put("operID", termInfoPO.getOperid());
        
        // 终端机id
        paramMap.put("termID", termInfoPO.getTermid());
        
        // 当前菜单
        paramMap.put("menuID", curMenuId);
        
        // 触摸id
        paramMap.put("touchOID", "");
        
        // 手机号
        paramMap.put("telnum", telnum);
        
        // 密码
        paramMap.put("passwd", password);
        
        // 调用个人服务密码验证接口
        ReturnWrap rw = getSelfSvcCallSD().checkUserPwd(paramMap);
        
        Map<String, String> map = new HashMap<String, String>();
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            
            // 验证结果0 失败  1 成功
            map.put("authchkresult", v.GetValue("authchkresult"));
            
            // 验证结果信息
            map.put("authchkmsg", v.GetValue("authchkmsg"));
        }
        else
        {
            String retMsg = rw.getReturnMsg();
            
            // 设置返回信息
            map.put("returnMsg", (StringUtils.isEmpty(retMsg))?"对不起，个人密码验证失败!":retMsg);
        }
        return map;
    }
    /**
	 * SIM卡认证
	 * @param termInfoPO 终端机信息
	 * @param curMenuId 当前菜单
	 * @param telnum 手机号
	 * @param cardNum 卡号
	 * @return map
	 */
	public Map<String,String> chkSIMCardNo(TerminalInfoPO termInfoPO,String curMenuId,String telnum,String cardNum)
	{
		// 参数
		Map<String,String> paramMap = new HashMap<String, String>();
		
		// 操作员id
		paramMap.put("operId", termInfoPO.getOperid());
		
		// 终端机id
		paramMap.put("termId", termInfoPO.getTermid());
		
		// 当前菜单
		paramMap.put("menuId", curMenuId);
		
		// 触摸id
		paramMap.put("touchId", "");
		
		// 手机号
		paramMap.put("telnum", telnum);
		
		// 卡号
		paramMap.put("cardno", cardNum);
		
		// SIM卡认证
		ReturnWrap rw = getSelfSvcCallSD().chkSIMCardNo(paramMap);
		Map<String,String> map = new HashMap<String,String>();
		if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            
            // 验证结果0 失败  1 成功
            map.put("authchkresult", v.GetValue("authchkresult"));
            
            // 验证结果信息
            map.put("authchkmsg", v.GetValue("authchkmsg"));
		}
		else
		{
		    String retMsg = rw.getReturnMsg();
		    
		    // 设置返回信息
		    map.put("returnMsg", (StringUtils.isEmpty(retMsg))?"对不起，SIM卡认证异常，请稍后再试!":retMsg);
		}
		
		return map;
	}
	
	/**
	 * 充值记录验证
	 * @param termInfoPO 终端机信息
	 * @param curMenuId 当前菜单
	 * @param telnum  手机号
	 * @param chargeRecordPO 缴费日志
	 * @return
	 */
	public Map<String,String> chkChargeRecord(TerminalInfoPO termInfoPO, String curMenuId, String telnum, ChargeRecordPO chargeRecordPO)
	{
	    // 参数
        Map<String,Object> paramMap = new HashMap<String, Object>();
        
        // 操作员id
        paramMap.put("operId", termInfoPO.getOperid());
        
        // 终端机id
        paramMap.put("termId", termInfoPO.getTermid());
        
        // 当前菜单
        paramMap.put("menuId", curMenuId);
        
        // 触摸id
        paramMap.put("touchId", "");
        
        // 手机号
        paramMap.put("telnum", telnum);
        
        // 缴费日志
        paramMap.put("chargeRecordPO", chargeRecordPO);
        
        // 充值记录验证
        ReturnWrap rw = getSelfSvcCallSD().chkChargeRecord(paramMap);
        
        Map<String,String> map = new HashMap<String,String>();
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            
            // 验证结果0 失败  1 成功
            map.put("authchkresult", v.GetValue("authchkresult"));
            
            // 验证结果信息
            map.put("authchkmsg", v.GetValue("authchkmsg"));
        }
        else
        {
            String retMsg = rw.getReturnMsg();
            
            // 设置返回信息
            map.put("returnMsg", (StringUtils.isEmpty(retMsg))?"对不起，充值记录验证异常，请稍后再试!":retMsg);
        }
        
        return map;
	}
	
	/**
	 * 通话记录验证
	 * @param termInfoPO 终端机信息
	 * @param curMenuId  当前菜单
	 * @param telnum   手机号码
	 * @param calledNum  被叫号码
	 * @return
	 */
	public Map<String, String> chkCallRecord(TerminalInfoPO termInfoPO, String curMenuId, String telnum, String calledNum)
	{
	    // 参数
        Map<String,String> paramMap = new HashMap<String, String>();
        
        // 操作员id
        paramMap.put("operId", termInfoPO.getOperid());
        
        // 终端机id
        paramMap.put("termId", termInfoPO.getTermid());
        
        // 当前菜单
        paramMap.put("menuId", curMenuId);
        
        // 触摸id
        paramMap.put("touchId", "");
        
        // 手机号
        paramMap.put("telnum", telnum);
        
        // 被叫号码
        paramMap.put("calledNum", calledNum);
        
        // 通话记录验证
        ReturnWrap rw = getSelfSvcCallSD().chkCallRecord(paramMap);
        
        Map<String,String> map = new HashMap<String,String>();
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            
            // 验证结果0 失败  1 成功
            map.put("authchkresult", v.GetValue("authchkresult"));
            
            // 验证结果信息
            map.put("authchkmsg", v.GetValue("authchkmsg"));
        }
        else
        {
            String retMsg = rw.getReturnMsg();
            
            // 设置返回信息
            map.put("returnMsg", (StringUtils.isEmpty(retMsg))?"对不起，通话记录验证异常，请稍后再试!":retMsg);
        }
        
        return map;
	}
	
	/**
     * 记录实名制认证受理日志
     * @param termInfoPO 终端机信息
     * @param curMenuId 菜单id
     * @param telnum 手机号
     * @param chkMethod  验证方式
     * @param chkValue  验证内容
     * @return
     * @remark modify gWX301560 2015-08-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求
     */
    public ReturnWrap saveRealNameChkRecLog(TerminalInfoPO termInfoPO, String curMenuId, String telnum, Map<String,String> map)
    {
        // 参数
        Map<String,String> paramMap = new HashMap<String, String>();
        
        // 操作员id
        paramMap.put("operId", termInfoPO.getOperid());
        
        // 终端机id
        paramMap.put("termId", termInfoPO.getTermid());
        
        // 当前菜单
        paramMap.put("menuId", curMenuId);
        
        // 触摸id
        paramMap.put("touchId", "");
        
        // 手机号
        paramMap.put("telnum", telnum);
        
//        // 校验方式 编码 
//        paramMap.put("attrid", chkMethod);
//        
//        // 校验内容
//        paramMap.put("newattrvalue", chkValue);
        
        // SIM卡认证
        ReturnWrap rw = getSelfSvcCallSD().saveRealNameChkRecLog(paramMap,map);
        
        return rw;
    }
}
