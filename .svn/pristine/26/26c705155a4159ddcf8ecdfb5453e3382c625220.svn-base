package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 密码重置
 * 
 * @author xkf29026
 * 
 */
public class PasswordResetBean extends BaseBeanImpl
{
    /**
     * 校验身份证号
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public ReturnWrap checkIDCard(TerminalInfoPO terminalInfoPO, String IDCard, String servnumbvr, String curMenuId)
    {
        Map map = new HashMap();
        map.put("telnum", servnumbvr);
        map.put("IDCard", IDCard);
        map.put("operid", terminalInfoPO.getOperid());
        map.put("termid", terminalInfoPO.getTermid());
        map.put("touchoid", "");
        map.put("menuid", curMenuId);
        
        ReturnWrap returnWrap = selfSvcCall.checkIDCard(map);
        return returnWrap;
    }
    
    /**
     * 向用户发送随机密码短信
     * 
     * @param termInfo，终端机信息
     * @param shortMessage，短信内容
     * @param CurMenuid，当前菜单
     * @return
     * @see
     */
    @SuppressWarnings("unchecked")
    public boolean sendRandomPwd(TerminalInfoPO termInfo, String shortMessage, String servnumber, String curMenuId)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servnumber);
        paramMap.put("smsContent", shortMessage);
        paramMap.put("priority", "5");
        paramMap.put("menuID", curMenuId);
        paramMap.put("touchOID", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.sendSMS(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * 密码重置
     * 
     * @param termInfo 终端机信息
     * @param servnumber 手机号码
     * @param curMenuId 当前菜单
     * @param newPasswd 新密码
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean resetPassword(TerminalInfoPO termInfo, String servnumber, String curMenuId, String newPasswd)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servnumber);
        paramMap.put("subcmdid", "2");
        paramMap.put("oldpwd", "");
        paramMap.put("menuID", curMenuId);
        paramMap.put("touchOID", "");
        paramMap.put("newpwd", newPasswd);
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.resetPassword(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        // 抛出异常
        String msg = "密码重置失败";
        if(null != rw)
        {
            msg = rw.getReturnMsg();
        }
        
        throw new ReceptionException(msg);
    }

    /**
     * 密码重置新(宁夏)
     * 
     * @param termInfo 终端机信息
     * @param servnumber 手机号码
     * @param curMenuId 当前菜单
     * @param newPasswd 新密码
     * @return boolean 
     * @remark create by hWX5316476 2014-2-20 OR_NX_201402_306 宁夏自助终端优化需求_弱密码改造需求
     */
    @SuppressWarnings("unchecked")
    public Map resetPasswordNew(TerminalInfoPO termInfo, String servnumber, String curMenuId, String newPasswd)
    {
    	Map paramMap = new HashMap();
    	
    	// 手机号
    	paramMap.put("telnum",servnumber);
    	
    	// 拨打号码(可以不传) 手机号
    	paramMap.put("callernum","");
    	
    	// 是否拨打本机  0本机，1非本机
    	paramMap.put("flag", "0");
    	
    	// 接口命令字  2密码重置(按照输入的新密码进行重置)
    	paramMap.put("subcmdid","2");
    	
    	// 老密码
    	paramMap.put("old_passwd","");
    	
    	// 新密码
    	paramMap.put("new_passwd",newPasswd);
    	
    	// 认证方式 AuthCheckA :短信随机密码认证 
    	paramMap.put("chktype", "AuthCheckA");
    	
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
        
        // 调用接口成功，且办理成功
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet cout = (CTagSet)rw.getReturnObject();
            
            // 设置返回结果
            map.put("returnObj", cout);
            
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            // 错误码
            map.put("errcode", rw.getErrcode());
            
            return map;
        }
        // 调用接口成功，但是办理不成功
        else if(rw != null)
        {
        	// 设置返回信息
        	map.put("returnMsg", rw.getReturnMsg());
        	
        	//　错误码
        	map.put("errcode", rw.getErrcode());
        	
        	return map;
        }
        
        return null;
    }
    
    /**
     * 向用户发送随机密码短信(根据SA_DB_SMTEMPLATE中配置的模板编号)
     * 
     * @param termInfo
     * @param smsparam
     * @param servnumber
     * @param templateno
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
     */
    @SuppressWarnings("unchecked")
    public boolean sendRandomPwdHub(TerminalInfoPO termInfo, String smsparam, String servnumber, String templateno, String curMenuId)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servnumber);
        paramMap.put("smsparam", smsparam);
        paramMap.put("templateno", templateno);
        paramMap.put("priority", "5");
        paramMap.put("menuID", curMenuId);
        paramMap.put("touchOID", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.sendSmsHub(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
}
