package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class QueryBean extends BaseBeanHubImpl
{
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    //private static final Logger log = Logger.getLogger(QueryBean.class);
	private static Log log = LogFactory.getLog(QueryBean.class);
    // modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
    
    public Map qryArrear(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId)
    {
        Map paramMap = new HashMap();
        
        // 设置操作员id
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        // 设置终端机id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // 设置客户手机号
        paramMap.put("telNum", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        // 设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        paramMap.put("region", customer.getRegionID());
        
        ReturnWrap rw = this.getSelfSvcCallHub().qryArrear(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet v = (CRSet)rw.getReturnObject();
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
    
    // 分月返还到账情况查询 Add by LiFeng [XQ[2011]_06_020]电子渠道分月返还到账情况查询需求【重点需求】 20110913 Begin
    /**
     * 新增分月返还到账情况查询功能 <功能详细描述>
     * 
     * @param inMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map qryMonthlyReturnInfo(Map<String, String> inMap)
    {
        Map outMap = new HashMap();
        ReturnWrap rw = this.getSelfSvcCallHub().qryMonthlyReturnInfo(inMap);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            Vector v = null;
            
            if(rw.getReturnObject() instanceof Vector)
            {
                v = (Vector)rw.getReturnObject();
            }
            else if(rw.getReturnObject() instanceof CTagSet)
            {
                v = new Vector();
                v.add((CTagSet)rw.getReturnObject());
            }
            
            String returnMsg = rw.getReturnMsg();
            
            // 设置返回结果
            outMap.put("returnObj", v);
            
            // 设置返回信息
            outMap.put("returnMsg", returnMsg);
            
            outMap.put("status", "1");
            
        }
        else
        {
            String returnMsg = rw.getReturnMsg();
            outMap.put("status", "0");
            outMap.put("returnMsg", returnMsg);
            log.error(returnMsg);
        }
        return outMap;
    }
    // 分月返还到账情况查询 Add by LiFeng [XQ[2011]_06_020]电子渠道分月返还到账情况查询需求【重点需求】 20110913 End
}
