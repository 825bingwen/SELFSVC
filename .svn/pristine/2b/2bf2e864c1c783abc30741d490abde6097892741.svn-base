package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.quickpublish.model.MultiProdCommitPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 产品快速发布 <功能详细描述>
 * 
 * @author cKF76106
 * @version [版本号, Jul 6, 2012]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class QuickPubBean extends BaseBeanImpl
{
    /**
     * 用户已订购产品信息查询 <功能详细描述>
     * 
     * @param terminalInfoPO 终端机信息
     * @param customer 客户信息
     * @param menuID 菜单
     * @return
     * @see [类、类#方法、类#成员]
     * @remark: modify zKF69263 2014/09/11 R003C14L09n01 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
     */
    public Map qryHasProds(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String menuID)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber(), "", "cli_qry_addedprodlist");
        
        // 调用产品快速发布-用户已订购产品信息查询
        ReturnWrap rw = selfSvcCall.qryHasProds(msgHeader);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            String province = (String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
           
            Vector vector = (Vector)rw.getReturnObject();
            
            if(vector.size() > 1)
            {
                // 设置返回结果
                map.put("returnObj", vector.get(1));
            }
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            // 设置返回码
            map.put("errcode", rw.getErrcode());
            
            map.put("successFlag", "1");
            
            return map;
        }
        
        else if (rw != null)
        {
            String returnMsg = rw.getReturnMsg();
            
            Map map = new HashMap();
            
            // 接口返回错误信息
            map.put("returnMsg", returnMsg);
            
            // 设置返回码
            map.put("errcode", rw.getErrcode());
            
            return map;
        }
        
        return null;
        
    }
    
    /**
     * 查询产品附加属性 <功能详细描述>
     * 
     * @param terminalInfoPO 终端机信息
     * @param customer 客户信息
     * @param menuID 菜单
     * @param qryType 查询类型 0：NCODE 1：产品包下产品
     * @param nCode 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark: modify zKF69263 2014/09/11 R003C14L09n01 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
     */
    public Map qryAddAttr(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String menuID ,String qryType, String nCode ,String operType)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber(), "", "cli_qry_prodattr");
        
        // 产品快速发布-查询产品附加属性
        ReturnWrap rw = selfSvcCall.qryAddAttr(msgHeader, qryType, nCode, operType);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            String province = (String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
           
            // modify begin jWX216858 V200R003C10LG0901 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
            if (rw.getReturnObject() instanceof CRSet)
            {
            	map.put("returnObj", rw.getReturnObject());
            }
            else 
            {
	            Vector vector = (Vector)rw.getReturnObject();
	            
	            if (vector != null && vector.size() > 1)
	            {
	                // 设置返回结果
	                map.put("returnObj", vector.get(1));
	            }
            }
            // modify end jWX216858 V200R003C10LG0901 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            // 设置返回码
            map.put("errcode", rw.getErrcode());
            
            return map;
        }
        
        else if (rw != null)
        {
            String returnMsg = rw.getReturnMsg();
            
            Map map = new HashMap();
            
            // 接口返回错误信息
            map.put("returnMsg", returnMsg);
            
            // 设置返回码
            map.put("errcode", rw.getErrcode());
            
            return map;
            
        }
        
        return null;
        
    }
    
    /**
     * 产品受理 <功能详细描述>
     * 
     * @param terminalInfoPO 终端机信息
     * @param customer 客户信息
     * @param menuID 菜单
     * @return
     * @see [类、类#方法、类#成员]
     * @remark: modify zKF69263 2014/09/11 R003C14L09n01 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
     */
    public ReturnWrap prodRec(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String menuID , MultiProdCommitPO multiProdCommitPO)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber(), "", "cli_busi_multiprodrec");

        // 产品快速发布-产品受理
        return selfSvcCall.prodRec(msgHeader, multiProdCommitPO);
    }
    
    /**
     * 查询产品包下子产品
     * <功能详细描述>
     * @param terminalInfoPO 终端信息
     * @param customer 客户信息
     * @param menuID 菜单ID
     * @param ncode 产品包编码、模板ID
     * @param type 类型：2 产品包 3 模板
     * @param opttype 操作类型
     * @return
     * @see [类、类#方法、类#成员]
     * @remark: modify zKF69263 2014/09/11 R003C14L09n01 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
     */
    public Map qrySubProds(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String menuID,
            String ncode, String type, String opttype)
    {
        // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber(), "", "cli_qry_prodlist");
        
        // 产品快速发布-查询产品包下子产品
        ReturnWrap rw = selfSvcCall.qrySubProds(msgHeader, ncode, type, opttype);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回结果
            map.put("returnObj", rw.getReturnObject());
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            // 设置返回码
            map.put("errcode", rw.getErrcode());
            
            return map;
        }
        else if (rw != null)
        {
            String returnMsg = rw.getReturnMsg();
            
            Map map = new HashMap();
            // 接口返回错误信息
            map.put("returnMsg", returnMsg);
            
            // 设置返回码
            map.put("errcode", rw.getErrcode());
            
            return map;
        }
        
        return null;
        
    }
    
}
