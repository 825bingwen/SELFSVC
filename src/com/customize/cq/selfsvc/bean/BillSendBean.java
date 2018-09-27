package com.customize.cq.selfsvc.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 帐单寄送
 * @author z90080209
 * @date   Nov 16, 2011
 */
public class BillSendBean extends BaseBeanCQImpl
{
    /**
     * 判断用户是否开通139手机邮箱功能
     * 
     * @param termInfo 终端机信息
     * @param customer 用户信息
     * @param curMenuId 当前菜单
     * @return
     */
    public String emailService(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId,
            List<DictItemPO> itemList)
    {
        Map paramMap = new HashMap();
        
        // 设置操作员id
        paramMap.put("curOper", termInfo.getOperid());
        
        // 设置终端机id
        paramMap.put("atsvNum", termInfo.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnumber", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        // 序号
        paramMap.put("sn", "0");
        
        ReturnWrap rw = this.getSelfSvcCallCQ().queryService(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 判断用户是否开通139邮箱
            String province = (String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_CQ.equals(province))
            {
                return emailService(rw, itemList);
            }
        }
        else
        {
            return "0";
        }
        return "0";
    }
    
    /**
     * 账单寄送方式提交处理
     * 
     * @param termInfo 终端机信息
     * @param customer 用户信息
     * @param curMenuId 当前菜单
     * @param billSendType 账单寄送方式
     * @param mailAddr 邮箱地址
     * @param oprtype 操作类型
     * @return
     */
    public Map billSendCommit(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId,
            String billSendType, String mailAddr,String oprtype)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnum", customer.getServNumber());
        paramMap.put("menuID", curMenuId);
        paramMap.put("contactID", customer.getContactId());
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        paramMap.put("mailtype", billSendType);
        paramMap.put("mailAddr", mailAddr);
        // 操作类型oprtype，默认为1：开通
        paramMap.put("oprtype", oprtype);
        
        ReturnWrap rw = this.getSelfSvcCallCQ().billSendCommit(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("successFlag", "1");
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
            
        }
        else if(rw != null)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
       return null;
    }
    
    /**
     * 查询用户账单寄送状态
     * @param termInfo
     * @param customer
     * @param curMenuId
     * @return
     */
    public Map billSendState(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId)
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
        
        ReturnWrap rw = getSelfSvcCallCQ().getBillSendInfo(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	//返回用户帐单寄送信息
        	CRSet result = (CRSet)rw.getReturnObject();
    		String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", result);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else
        {
            return null;
        }
	}
}
