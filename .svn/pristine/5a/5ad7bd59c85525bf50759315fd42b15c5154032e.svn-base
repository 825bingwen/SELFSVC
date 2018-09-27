package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 湖北营销推荐活动
 * 
 * @author  cKF76106
 * @version  [版本号, Aug 21, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RecommendProductBean extends BaseBeanHubImpl
{
    /**
     * 查询用户可推荐的业务列表_湖北营销推荐活动
     * @param terminalInfoPO
     * @param customerSimp
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> qryRecommendProductList(TerminalInfoPO terminalInfoPO,String touchOID,String telnum,String curMenuId )
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
                "",MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        ReturnWrap rw = super.getSelfSvcCallHub().qryRecommendProductList(msgHeader);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String returnMsg = rw.getReturnMsg();
            Map<String, Object> map = new HashMap<String, Object>();
            
            //设置返回结果
            map.put("returnObj", rw.getReturnObject());
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            // 设置返回码
            map.put("errcode", rw.getErrcode());
            
            // 设置成功标志
            map.put("successFlag", "1");

            return map;
        }
        else if (rw != null)
        {
            String returnMsg = rw.getReturnMsg();
            Map<String, Object> map = new HashMap<String, Object>();
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            // 设置返回码
            map.put("errcode", rw.getErrcode());

            return map;
        }
        
        return null;
    }
    
    
    /**
     * 记录业务推荐结果_湖北营销推荐活动
     * @param terminalInfoPO
     * @param customerSimp
     * @param curMenuId
     * @param userSeq
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public ReturnWrap recommendFeedback(TerminalInfoPO terminalInfoPO, String touchOID, String telnum,
        String curMenuId, String userSeqs, String status, String actIds, String eventTypes)
    {
        /*Map paramMap = new HashMap();
        
        //设置操作员id
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // 设置客户接触id
        paramMap.put("touchOID", touchOID); 
        
        //设置当前菜单
        paramMap.put("menuID", curMenuId);
        
        //手机号码
        paramMap.put("telnum", telnum);
        
        //业务推荐唯一流水号,多个以逗号分隔
        paramMap.put("userSeq", userSeqs);
        
        //状态
        paramMap.put("status", status);
        
        //推荐活动编码,多个以逗号分隔
        paramMap.put("actid", actIds);
        
        //事件类型,多个以逗号分隔
        paramMap.put("eventtype", eventTypes);*/
    	
    	// 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
                "",MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        ReturnWrap rw = super.getSelfSvcCallHub().recommendFeedback(msgHeader, userSeqs, status, actIds, eventTypes);
                
        return rw;
    }
    
    /**
     * 推荐业务受理
     * @param terminalInfoPO
     * @param customerSimp
     * @param curMenuId
     * @param commendOID
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public ReturnWrap recommendProduct(TerminalInfoPO terminalInfoPO, String touchOID, String telnum, String curMenuId,
        String userSeq, String actId, String recmdType)
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
                "",MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        ReturnWrap rw = super.getSelfSvcCallHub().recommendProduct(msgHeader, userSeq, actId, recmdType);
        
        return rw;
    }
    
    /**
     * 更新业务推荐结果为“办理成功”
     * @param terminalInfoPO
     * @param customerSimp
     * @param curMenuId
     * @param commendOID
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap setRecommendSuccess(TerminalInfoPO terminalInfoPO,String touchOID,String telnum,String curMenuId,String commendOID )
    {
        // 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
                "",MsgHeaderPO.ROUTETYPE_TELNUM, telnum);
        
        ReturnWrap rw = super.getSelfSvcCallHub().setRecommendSuccess(msgHeader, commendOID);
                
        return rw;
    }
    
    /**
     * 查询回馈定义信息列表
     * 
     * @param terminalInfoPO 终端机信息
     * @param customerSimp 用户信息
     * @param curMenuId 菜单Id
     * @param actId 业务推荐活动编码
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public ReturnWrap qryFeedBackDefList(TerminalInfoPO terminalInfoPO, NserCustomerSimp customerSimp, 
        String curMenuId, String telNum, String actId)
    {
        String contactID = null;
        
        if (null != customerSimp)
        {
            contactID = customerSimp.getContactId();
        }
        
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
            contactID, MsgHeaderPO.ROUTETYPE_TELNUM, telNum);
        
        return super.getSelfSvcCallHub().qryFeedBackDefList(msgHeader, actId);
    }
    
    /** 
     * 回馈定义受理
     * 
     * @param terminalInfoPO 终端机信息
     * @param customerSimp 用户信息
     * @param curMenuId 菜单Id
     * @param actId 业务推荐活动编码
     * @param contents 回复内容
     * @param recmdType 推荐类型
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 精准营销二期_自助终端渠道改造
     */
    public ReturnWrap doFeedBackDef(TerminalInfoPO terminalInfoPO, NserCustomerSimp customerSimp,
        String curMenuId, String actId, String contents, String recmdType)
    {
    	// begin zKF66389 2015-09-15 9月份findbugs修改
//    	String contactID = null;
//        
//        if (null != customerSimp)
//        {
//            contactID = customerSimp.getContactID();
//        }
        
    	// 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
        		customerSimp.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customerSimp.getServNumber());
        // end zKF66389 2015-09-15 9月份findbugs修改
    	
        return super.getSelfSvcCallHub().doFeedBackDef(msgHeader, actId, contents, recmdType);
    }
}
