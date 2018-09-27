package com.customize.nx.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.nx.selfsvc.bean.impl.BaseBeanNXImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
/**
 * 主体产品变更Bean
 * 
 * @author  cKF76106
 * @version  [版本号, Jun 20, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MainProdChangeBean extends BaseBeanNXImpl
{
   /**
    * 调用接口查询产品变更确认信息，包括用户需开通的业务、会保留的业务，需取消的业务
    * @param terminalInfoPO
    * @param customer
    * @param curMenuId
    * @param ncode
    * @param inttime
    * @return
    * @see [类、类#方法、类#成员]
    */
    public Map mainProductRecInfo(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId,String ncode,String inttime)
    {
        Map<String,String> paramMap = new HashMap<String,String>();
        
        // 设置操作员id
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnum", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        // 设置当前菜单
        paramMap.put("menuID", curMenuId);
        
        //ncode
        paramMap.put("ncode", ncode);
        
        //操作类型
        paramMap.put("oprtype", "PCOpRec");
        
        //渠道
        paramMap.put("accesstype", "bsacAtsv");
        
        //生效方式
        paramMap.put("affecttype", "Type_NextCycle");
        
        //受理时间
        paramMap.put("intime", inttime);
        
        paramMap.put("preparebusi", "PreBsacNBChgMainProd");
        
        ReturnWrap rw = getSelfSvcCallNX().mainProductRecInfo(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet crset = (CRSet)rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", crset);
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        return null;
        
    }
    
    /**
     * 调用接口执行主体产品转换
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap mainProductChangeSubmit(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId,String ncode)
    {
        Map<String,String> paramMap = new HashMap<String,String>();
        
        // 设置操作员id
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnum", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        // 设置当前菜单
        paramMap.put("menuID", curMenuId);
        
        //ncode
        paramMap.put("ncode", ncode);
        
        //操作类型
        paramMap.put("stype", "ADD");        
       
        paramMap.put("preparebusi", "BsacNBSubmit");
        
        ReturnWrap rw = getSelfSvcCallNX().mainProductChangeSubmit(paramMap);
        
        return rw;
    }
    
    /**
     * 查询可变更主体产品列表
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @param ncode
     * @param inttime
     * @return
     * @see [类、类#方法、类#成员]
     */
     public Map qryChangeMainProduct(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId,String prodid,String org)
     {
         Map<String,String> paramMap = new HashMap<String,String>();
         
         // 设置操作员id
         paramMap.put("operID", terminalInfoPO.getOperid());
         
         // 设置终端机id
         paramMap.put("termID", terminalInfoPO.getTermid());
         
         // 设置客户手机号
         paramMap.put("telnum", customer.getServNumber());
         
         // 设置客户接触id
         paramMap.put("touchoid", customer.getContactId());
         
         // 设置当前菜单
         paramMap.put("menuID", curMenuId);
         
         // 受理方式
         paramMap.put("rectype", "ChangeProduct");
         
         //渠道
         paramMap.put("channel", "bsacAtsv");
         
         // 解决方案
         paramMap.put("solutionid", "");
         
         // 产品类型
         paramMap.put("prodtype", "ProdType_Person");
         
         // 目录编码
         paramMap.put("catalogid", "");
         
         // 用户的主体产品ID
         paramMap.put("prodid", prodid);
         
         // 地区编码
         paramMap.put("region", customer.getRegionID());
         
         // 单位编码
         paramMap.put("org", org);
  
         ReturnWrap rw = getSelfSvcCallNX().qryChangeMainProduct(paramMap);
         if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
         {
             String returnMsg = rw.getReturnMsg();
             Map map = new HashMap();
             
             //设置返回结果
             map.put("returnObj", rw.getReturnObject());
             
             //设置返回信息
             map.put("returnMsg", returnMsg);
             
             return map;
         }
         else if (rw != null)
         {
             String returnMsg = rw.getReturnMsg();
             Map map = new HashMap();
             
             // 设置返回信息
             map.put("returnMsg", returnMsg);
             
             return map;
         }
         return null;
         
     }
    
}
