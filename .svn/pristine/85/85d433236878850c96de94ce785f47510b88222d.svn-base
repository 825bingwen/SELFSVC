package com.customize.cq.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 话费充值缴费
 * @author xkf29026
 *
 */
public class FeeChargeBean  extends BaseBeanCQImpl
{
    
    /**
     * 话费充值账户信息查询
     * @param termInfo 终端机信息
     * @param servnumber 手机号码
     * @param curMenuId 当前菜单
     * @param bankNo 缴费方式
     * @param payDate 缴费时间
     * @param acceptType 受理类型
     * @return
     */
    public Map qryfeeChargeAccount(TerminalInfoPO termInfo, String servnumber, String curMenuId, String bankNo,
            String payDate, String acceptType)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        //设置操作员id
        paramMap.put("operid", termInfo.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", termInfo.getTermid());
        
        //设置客户手机号
        paramMap.put("servnumber", servnumber);
        
        //设置客户接触id
        paramMap.put("touchoid", "");
        
        //设置当前菜单id
        paramMap.put("menuid", curMenuId);
        
        //缴费方式
        paramMap.put("bankNo", bankNo);
        
        //缴费日期
        paramMap.put("payDate", payDate);
        
        //受理类型
        paramMap.put("acceptType", acceptType);
        
        //获取结果
        ReturnWrap rw = this.getSelfSvcCallCQ().qryfeeChargeAccount(paramMap);
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
     * @param termInfo 终端机信息
     * @param curMenuId 当前菜单
     * @param servnumber 手机号码
     * @param bankNo 缴费方式
     * @param payDate 缴费时间
     * @param terminalSeq 终端序列号
     * @param money 缴费金额
     * @param acceptType 受理类型
     * @param invoiceType 发票类型
     * @param bankSite
     * @param bankOper
     * @return
     */
    public Map chargeCommit(TerminalInfoPO termInfo, String curMenuId, String servnumber, String bankNo,
            String payDate, String terminalSeq, String money, String acceptType, String invoiceType, String bankSite,
            String bankOper)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("operid", termInfo.getOperid());
        paramMap.put("termid", termInfo.getTermid());
        paramMap.put("menuid", curMenuId);
        paramMap.put("servnumber", servnumber);
        paramMap.put("bankNo", bankNo);
        paramMap.put("payDate", payDate);
        paramMap.put("terminalSeq", terminalSeq);
        paramMap.put("money", money);
        paramMap.put("acceptType", acceptType);
        paramMap.put("invoiceType", invoiceType);
        paramMap.put("bankSite", bankSite);
        paramMap.put("bankOper", bankOper);
        paramMap.put("touchoid", "");
        
        ReturnWrap rw = this.getSelfSvcCallCQ().chargeCommit(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Vector v = (Vector)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", v);
            
            //设置返回信息
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
        
        ReturnWrap rw = this.getSelfSvcCallCQ().sendSMS(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * 不校验密码，直接获取用户信息
     * 
     * @param servnumber，手机号码
     * @param password，服务密码
     * @param termInfo，终端机信息
     * @return 用户信息，如果返回null，说明身份验证失败
     */
    public Map getUserStatus(String servnumber, String password, TerminalInfoPO termInfo)
    {
        Map paramMap = new HashMap();
        paramMap.put("telnum", servnumber);
        paramMap.put("password", "");
        paramMap.put("operID", termInfo.getOperid());
        paramMap.put("termID", termInfo.getTermid());
        
        ReturnWrap rw = this.getSelfSvcCallCQ().getUserStatus(paramMap);
        Map map = new HashMap();
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet cout = (CTagSet) rw.getReturnObject();
            
            map.put("status", cout.GetValue("status") == null ? "" : cout.GetValue("status"));
            map.put("servRegion", cout.GetValue("region") == null ? "" : cout.GetValue("region"));
            return map;
        }
        
        return null;
    }
}
