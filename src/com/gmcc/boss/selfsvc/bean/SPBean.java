package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;

//add begin g00140516 2011/11/05 R003C11L11n01 BUG_HUB_201111_24
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
//add end g00140516 2011/11/05 R003C11L11n01 BUG_HUB_201111_24

import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 梦网业务查询退订
 * 
 * @author xkf29026
 * 
 */
public class SPBean extends BaseBeanImpl
{
    /**
     * 梦网业务查询
     * 
     * @param terminalInfoPO 终端机信息
     * @param customer 客户信息
     * @param curMenuId 当前菜单
     * @param sn 序号
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map queryService(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String sn)
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
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        // 序号
        paramMap.put("sn", sn);
        
        ReturnWrap rw = selfSvcCall.queryService(paramMap);
        Map map = new HashMap();
        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet v = (CRSet)rw.getReturnObject();
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
        	
        	// 设置返回信息
            map.put("returnMsg", returnMsg);
            
            map.put("errcode", rw.getErrcode());
            return map;
        }
    }
    
    /**
     * 业务统一退订接口
     * 
     * @param terminalInfoPO 终端机信息
     * @param customer 客户信息
     * @param curMenuId 当前菜单
     * @param operType 操作类型
     * @param cancelType 退订类型
     * @param dealType
     * @param domain
     * @param spId
     * @param spBizCode
     * @param bizType
     * @param effectType
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map cancelService(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId,
            String operType, String cancelType, String dealType, String domain, String spId, String spBizCode,
            String bizType, String effectType)
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
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        paramMap.put("operType", operType);
        
        paramMap.put("cancelType", cancelType);
        
        paramMap.put("dealType", dealType);
        
        paramMap.put("domain", domain);
        
        paramMap.put("spId", spId);
        
        paramMap.put("spBizCode", spBizCode);
        
        paramMap.put("bizType", bizType);
        
        paramMap.put("effectType", effectType);
        
        ReturnWrap rw = selfSvcCall.cancelService(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // modify begin cKF76106 2012/09/11 OR_NX_201209_258
            CTagSet tagSet = (CTagSet) rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", tagSet);
            // modify end cKF76106 2012/09/11 OR_NX_201209_258
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            map.put("errcode", rw.getErrcode());
            
            return map;
        } 
        else if(rw != null)
        {
        	Map map = new HashMap();
        	map.put("returnMsg", rw.getReturnMsg());
        	map.put("errcode", rw.getErrcode());
        	return map;
        }
		return null;
    }
    
    /**
     * 梦网业务订购接口
     * 
     * @param terminalInfoPO 终端机信息
     * @param customer 用户信息
     * @param curMenuId 菜单
     * @param operType 操作类型
     * @param cancelFlag 退订标志
     * @param domain 平台
     * @param spId 对象编码
     * @param spBizCode 业务编码
     * @param bizType 业务类型
     * @param effectType 生效方式
     * @return 订购结果
     * @see 
     * @remark create g00140516 2011/11/05 R003C11L11n01 BUG_HUB_201111_24
     */
    public Map orderSPService(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId,
            String operType, String cancelFlag, String domain, String spId, String spBizCode, String bizType, String effectType)
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
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        paramMap.put("operType", operType);
        
        paramMap.put("cancelFlag", cancelFlag);
        
        paramMap.put("domain", domain);
        
        paramMap.put("spId", spId);
        
        paramMap.put("spBizCode", spBizCode);
        
        paramMap.put("bizType", bizType);
        
        paramMap.put("effectType", effectType);
        
        ReturnWrap rw = selfSvcCall.orderSPService(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet tagSet = (CTagSet) rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", tagSet);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            map.put("errcode", rw.getErrcode());
            
            return map;
        } 
        else if(rw != null)
        {
            Map map = new HashMap();
            map.put("returnMsg", rw.getReturnMsg());
            map.put("errcode", rw.getErrcode());
            return map;
        }
        return null;
    }
    
    /**
     * 查询用户已订购梦网业务列表
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @param sn
     * @param ignoreFlag true时，后台返回192也认为是成功的；否则，192认为失败
     * @return
     */
    public Map queryService(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId, String sn, boolean ignoreFlag)
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
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        // 序号
        paramMap.put("sn", sn);
        
        ReturnWrap rw = selfSvcCall.queryService(paramMap);
        Map map = new HashMap();
        
        // 后台返回192也认为是成功的
        if (ignoreFlag)
        {
        	// begin zKF66389 2015-09-15 9月份findbugs修改
        	//if (rw != null && (rw.getStatus() == SSReturnCode.SUCCESS || rw.getErrcode() == 192))
        	if (rw.getStatus() == SSReturnCode.SUCCESS || rw.getErrcode() == 192)
            {
                CRSet v = (CRSet)rw.getReturnObject();
                String returnMsg = rw.getReturnMsg();
                
                // 设置返回结果
                map.put("returnObj", v);
                
                // 设置返回信息
                map.put("returnMsg", returnMsg);
                
                return map;
            }
            else
            {
            	String returnMsg = rw.getReturnMsg();
            	
            	// 设置返回信息
                map.put("returnMsg", returnMsg);
                return map;
            }
        }
        
        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        if (rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet v = (CRSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回结果
            map.put("returnObj", v);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else
        {
        	String returnMsg = rw.getReturnMsg();
        	
        	// 设置返回信息
            map.put("returnMsg", returnMsg);
            return map;
        }
    }
}
