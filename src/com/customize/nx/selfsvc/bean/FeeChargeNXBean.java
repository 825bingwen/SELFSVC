package com.customize.nx.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.customize.nx.selfsvc.bean.impl.BaseBeanNXImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;

import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 话费充值缴费
 * 
 * @author xkf29026
 * 
 */
public class FeeChargeNXBean extends BaseBeanNXImpl
{
    
    /**
     * 话费充值账户信息查询
     * 
     * @param termInfo 终端机信息
     * @param servnumber 手机号码
     * @param curMenuId 当前菜单
     * @return
     */
    public Map qryfeeChargeAccount(TerminalInfoPO termInfo, String servnumber, String curMenuId)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        // 设置操作员id
        paramMap.put("operid", termInfo.getOperid());
        
        // 设置终端机id
        paramMap.put("atsvNum", termInfo.getTermid());
        
        // 设置客户手机号
        paramMap.put("servnumber", servnumber);
        
        // 设置客户接触id
        paramMap.put("touchoid", "");
        
        // 设置当前菜单id
        paramMap.put("menuid", curMenuId);
        
        // 获取结果
        ReturnWrap rw = this.getSelfSvcCallNX().qryfeeChargeAccount(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null && rw.getStatus() != SSReturnCode.SUCCESS)
        {
           int errcode = rw.getErrcode();
           
           Map map = new HashMap();
           map.put("errcode", errcode);
           return map;
           
        }
        return null;
    }
    
    /**
     * 账户余额查询
     * @param terminalInfoPO  终端机信息
     * @param customer 客户信息
     * @param menuid 当前菜单id
     * @return
     */    
    public Map queryBalance(TerminalInfoPO terminalInfoPO, String servNumber,String menuid)
    {
        Map<String,String> paramMap = new HashMap<String,String>();
        
        // 设置操作员id
        paramMap.put("operid", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnumber", servNumber);
        
        // 设置客户接触id
        paramMap.put("touchoid", "");
        
        // 设置当前菜单id
        paramMap.put("menuid", menuid);
        
        // 获取结果
        ReturnWrap rw = getSelfSvcCallNX().queryBalance(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
    }
    
    /**
     * 话费充值缴费
     * 
     * @param termInfo 终端机信息
     * @param curMenuId 当前菜单
     * @param servnumber 手机号码
     * @param money 缴费金额
     * @param payType 缴费方式
     * @return
     */
    public Map chargeCommit(TerminalInfoPO termInfo, String curMenuId, String servnumber, String money, String terminalSeq)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("operid", termInfo.getOperid());
        paramMap.put("termid", termInfo.getTermid());
        paramMap.put("menuid", curMenuId);
        paramMap.put("servnumber", servnumber);
        paramMap.put("money", money);
        paramMap.put("terminalSeq", terminalSeq);
        paramMap.put("touchoid", "");
        
        ReturnWrap rw = this.getSelfSvcCallNX().chargeCommit(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
    }
    
    /**
     * 向用户发送随机密码短信
     * 
     * @param servNumber，手机号码
     * @param termInfo，终端机信息
     * @param shortMessage，短信内容
     * @param curMenuId，当前菜单
     * @return
     * @see
     */
    public boolean sendRandomPwd(String servNumber, TerminalInfoPO termInfo, String shortMessage, String curMenuId)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnumber", servNumber);
        paramMap.put("smsContent", shortMessage);
        paramMap.put("priority", "5");
        paramMap.put("menuID", curMenuId);
        paramMap.put("touchOID", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallNX().sendSMS(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * 查询银联卡缴费发票信息
     * 
     * @param termInfo 终端机信息
     * @param curMenuId 当前菜单
     * @param servnumber 手机号码
     * @param money 缴费金额（分）
     * @param channel 渠道
     * @remark create cKF76106 2013/02/04 R003C13L01n01 OR_huawei_201302_122 
     * @return
     */
    public Map queryInvoice(TerminalInfoPO termInfo, String curMenuId, String servnumber, String money, String channel)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("operid", termInfo.getOperid());
        paramMap.put("termid", termInfo.getTermid());
        paramMap.put("menuid", curMenuId);
        paramMap.put("servnumber", servnumber);
        paramMap.put("money", money);
        paramMap.put("touchoid", "");
        paramMap.put("channel", channel);
        
        ReturnWrap rw = this.getSelfSvcCallNX().queryInvoice(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet v = (CTagSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
    }
}
