package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 充值卡充值
 * 
 * @author cKF48754
 * 
 */
public class CardPayBean extends BaseBeanImpl
{
    
    /**
     * 判断用户当前状态
     * 
     * @return
     */
    public String qryUserState(TerminalInfoPO termInfo, String curMenuId, String telnum)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
     
        // 设置当前菜单
        paramMap.put("menuid", curMenuId);
        
        // 设置客户接触id
        paramMap.put("touchoid", "");
        
        // 设置客户手机号
        paramMap.put("telnum", telnum);
        
        // 设置操作员id
        paramMap.put("operid", termInfo.getOperid());
        
        // 设置终端机id
        paramMap.put("termid", termInfo.getTermid());
        
        ReturnWrap rw = selfSvcCall.queryCurrentStatus(paramMap);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
           
            //modify begin fwx439896 2017-6-20  OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
            String userState=null;

        	//能开返回结果中 20  99代表销户
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_USERSTATE))
            {
            	if("20".equals(v.GetValue("STATUS"))||"99".equals(v.GetValue("STATUS")))
            	{
            		userState="销户";
            	}
            		
            }
            else
            {
            	userState=v.GetValue("state");
            }	
            return userState;
            //modify end fwx439896 2017-6-20  OR_huawei_201704_415_【山东移动接口迁移专题】-自助终端业务办理4
        }
        return null;
    }
    
    /**
     * 充值卡充值
     * 
     * @param termInfo 终端机信息
     * @param curMenuId 当前菜单
     * @param telnum 手机号码
     * @param cardpwd 充值卡密码
     * @return
     */
    public boolean cardPayCommit(TerminalInfoPO termInfo, String curMenuId, String telnum, String cardpwd)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 设置当前菜单
        paramMap.put("menuid", curMenuId);
        
        // 设置客户接触id
        paramMap.put("touchoid", "");
        
        // 设置客户手机号
        paramMap.put("telnum", telnum);
        
        // 设置操作员id
        paramMap.put("operid", termInfo.getOperid());
        
        // 设置终端机id
        paramMap.put("termid", termInfo.getTermid());
        
        // 设置客户充值卡密码
        paramMap.put("cardpwd", cardpwd);
        
        ReturnWrap rw = selfSvcCall.cardPayCommit(paramMap);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        return false;
        
    }
    
}
