/*
 * 文 件 名:  MainProdChange.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人: yKF70747
 * 修改时间:  Apr 16, 2012
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.call.DubboInvokeUtil;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 *主体产品变更Bean
 * 
 * @author  yKF70747
 * @version  [版本号, Apr 16, 2012]
 * @see  
 * @since  
 */
public class MainProdChangeBean extends BaseBeanHubImpl
{
	private IntMsgUtil intMsgUtil;
	
	private  transient DubboInvokeUtil dubboInvokeUtil;
   /**
    *调用接口查询产品变更确认信息，包括用户需开通的业务、会保留的业务，需取消的业务
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
    	// modify begin wWX217192 2014-09-20 OR_huawei_201409_433 自助终端接入EBUS_资费套餐转换
    	// 组装报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
                "",MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        ReturnWrap rw = getSelfSvcCallHub().mainProductRecInfo(msgHeader, ncode, inttime);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	if(IntMsgUtil.isTransEBUS("BLCSProductRec"))
        	{
        		String response = (String)rw.getReturnObject();
        		// 解析出参报文
        		JSONObject jsontmp = JSONObject.fromObject(response);
        		
        		// modify begin wWX217192 2014-11-04 OR_huawei_201409_433_后台修改接口逻辑
        		// 需开通的业务列表
        		// Object newObjtmp = jsontmp.get("newProdList");
        		
        		// 原始业务列表
        		// Object originalObjtmp = jsontmp.get("originalProdList");
        		
        		// 需取消的业务列表
        		// Object delObjtmp = jsontmp.get("delProdList");
        		
        		// 待展示的产品列表
        		Object showObjtmp = jsontmp.get("showProdList");
        		
        		StringBuffer outJson = new StringBuffer("");
        		
        		// 解析返回报文信息，将CTagSet+3CRSet解析为1个CRSet
        		/*outJson.append("[");
        		if(null != newObjtmp)
        		{
        			outJson.append(newObjtmp.toString().substring(1, newObjtmp.toString().length() - 1)).append(",");
        		}
        		if(null != originalObjtmp)
        		{
        			outJson.append(originalObjtmp.toString().substring(1, originalObjtmp.toString().length() - 1)).append(",");
        		}
        		if(null != delObjtmp)
        		{
        			outJson.append(delObjtmp.toString().substring(1, delObjtmp.toString().length() - 1));
        		}
        		outJson.append("]");*/
        		
        		if(null != showObjtmp)
        		{
        			outJson.append(showObjtmp);
        		}
        		// modify end wWX217192 2014-11-04 OR_huawei_201409_433_后台修改接口逻辑
        		
        		response = outJson.toString();
        		
        		rw = getIntMsgUtil().parseJsonResponse(response, null, 
        				new String[] {"editStatus", "prodID", "prodName", "prodCreateDate", "prodEndDate", "prodPackName",
        				"privID", "privName", "privCreateDate", "privEndDate", "delReason"});
        		
        	}
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
        // modify end wWX217192 2014-09-20 OR_huawei_201409_433 自助终端接入EBUS_资费套餐转换
        
    }
    
	/**
     * 调用接口执行主体产品转换
     * @param terminalInfoPO
     * @param customer
     * @param curMenuId
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify by jWX216858 2014-11-11 OR_huawei_201410_867_HUB_自选套餐流水线部分EBUS改造
     */
    public ReturnWrap mainProductChangeSubmit(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId,String ncode)
    {
    	// 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), terminalInfoPO.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        return getSelfSvcCallHub().mainProductChangeSubmit(msgHeader, ncode);
    }

	public IntMsgUtil getIntMsgUtil() {
		return intMsgUtil;
	}

	public void setIntMsgUtil(IntMsgUtil intMsgUtil) {
		this.intMsgUtil = intMsgUtil;
	}

	public DubboInvokeUtil getDubboInvokeUtil() {
		return dubboInvokeUtil;
	}

	public void setDubboInvokeUtil(DubboInvokeUtil dubboInvokeUtil) {
		this.dubboInvokeUtil = dubboInvokeUtil;
	}
    
}
