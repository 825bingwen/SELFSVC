package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 账单寄送bean
 * 
 * @author xkf29026
 * 
 */
public class BillSendBean extends BaseBeanHubImpl
{
    /**
     * 判断用户是否开通139手机邮箱功能
     * 
     * @param termInfo 终端机信息
     * @param customer 用户信息
     * @param curMenuId 当前菜单
     * @return
     * @remark create by sWX219697 2014-9-19 09:04:13 OR_huawei_201409_430 自助终端接入EBUS_账单寄送
     */
    public String emailService(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId,
            List<DictItemPO> itemList)
    {
    	//封装消息头
        MsgHeaderPO msgHead = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), 
        		customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        ReturnWrap rw = this.getSelfSvcCallHub().queryService(msgHead);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 判断用户是否开通139邮箱
            String province = (String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_HUB.equals(province))
            {
                return emailService(rw, itemList);
            }
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
     * @return
     * @remark modify by sWX219697 2014-9-9 09:57:15 OR_huawei_201409_430 自助终端接入EBUS_账单寄送
     */
    public Map billSendCommit(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId,
            String billSendType, String mailAddr)
    {
        
    	//封装消息头
        MsgHeaderPO msgHead = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), 
        		customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        ReturnWrap rw = this.getSelfSvcCallHub().billSendCommit(msgHead, billSendType, mailAddr);
        
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
}
