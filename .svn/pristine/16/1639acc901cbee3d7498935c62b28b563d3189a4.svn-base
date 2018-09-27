package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 密码修改
 * @author xkf29026
 *
 */
public class ChangePasswordBean extends BaseBeanImpl
{
    /**
     * 密码修改
     * @param terminalInfoPO 终端信息
     * @param customer 客户信息
     * @param oldPasswd 旧密码
     * @param newPasswd 新密码
     * @param curMenuId 当前菜单
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map recChangePassword(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer,String oldPasswd,String newPasswd, String curMenuId)
    {
        Map paramMap = new HashMap();
        
        // 设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnumber", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        // 设置旧密码
        paramMap.put("oldPasswd", oldPasswd);
        
        // 设置新密码
        paramMap.put("newPasswd", newPasswd);
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = selfSvcCall.recChangePassword(paramMap);
        Map map = new HashMap();
        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            map.put("errcode", rw.getErrcode());
            
            return map;
        }
        else
        {
        	String returnMsg = rw.getReturnMsg();
        	map.put("returnMsg", returnMsg);
        	map.put("errcode", rw.getErrcode());
        	
        	return map;
        }
    }

    /**
     * 密码修改(宁夏)新
     * @param terminalInfoPO 终端信息
     * @param customer 客户信息
     * @param oldPasswd 旧密码
     * @param newPasswd 新密码
     * @param curMenuId 当前菜单
     * @return Map
     * @remark create by hWX5316476 2014-2-20 OR_NX_201402_306 宁夏自助终端优化需求_弱密码改造需求
     */
    @SuppressWarnings("unchecked")
    public Map recChangePasswordNew(TerminalInfoPO termInfo, NserCustomerSimp customer,String oldPasswd,String newPasswd, String curMenuId)
    {
    	Map paramMap = new HashMap();
    	
    	// 手机号
    	paramMap.put("telnum",customer.getServNumber());
    	
    	// 拨打号码(可以不传) 手机号
    	paramMap.put("callernum","");
    	
    	// 是否拨打本机  0本机，1非本机
    	paramMap.put("flag", "0");
    	
    	// 接口命令字  0 服务密码验证  1:密码修改 2：密码重置
    	paramMap.put("subcmdid","1");
    	
    	// 老密码
    	paramMap.put("old_passwd",oldPasswd);
    	
    	// 新密码
    	paramMap.put("new_passwd",newPasswd);
    	
    	// 认证方式 AuthCheckB :服务密码认证 
    	paramMap.put("chktype", "AuthCheckB");
    	
    	// newpwdcount 新密码位数校验，校验是否符合传入位数，传0或不传则不校验。
    	paramMap.put("newpwdcount","0");
    	
    	// 终端ID
    	paramMap.put("termid", termInfo.getTermid());
    	
    	// 当前菜单ID
    	paramMap.put("menuid", curMenuId);
    	
    	// 触摸ID
    	paramMap.put("touchoid", "");
    	
    	// 操作员ID
    	paramMap.put("operid", termInfo.getOperid());
        
        ReturnWrap rw = selfSvcCall.resetPwdNew(paramMap);
        Map map = new HashMap();
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            map.put("errcode", rw.getErrcode());
            
            return map;
        }
        else if( null != rw)
        {
        	String returnMsg = rw.getReturnMsg();
        	map.put("returnMsg", returnMsg);
        	map.put("errcode", rw.getErrcode());
        	
        	return map;
        }
        
        return null;
    }    
}
