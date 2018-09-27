package com.customize.hub.selfsvc.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 09版神州行轻松卡亲情号码查询与设置
 * 
 * @author l00190940
 * 
 */
public class FamilyNumberBean extends BaseBeanHubImpl
{

	public Map<String, Object> qryFamilyNumber(TerminalInfoPO termInfo, String curMenuId,
			NserCustomerSimp customer) 
	{
	    // 创建报文头信息
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        // 查询亲情号码
        ReturnWrap rw = getSelfSvcCallHub().qryFamilyNumber(msgHeader);
        
        if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
        {
            String returnMsg = rw.getReturnMsg();
            Map<String, Object> map = new HashMap<String, Object>();
            
            //设置返回结果
            map.put("returnObj", rw.getReturnObject());
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
		return null;
	}

	/**
	 * 设置、修改或取消亲情号码
	 * @param terminalInfoPO 终端机信息
	 * @param customer 客户信息
	 * @param curMenuId 菜单id
	 * @param stype 受理类型
	 * @param sn  亲情号码位置。目前只有1、2、3
	 * @param sregion 亲情号码，如果是取消，传“”
	 * @return
	 * @remark modify by jWX216858 2014-11-12 OR_huawei_201410_867_HUB_自选套餐流水线部分EBUS改造
	 */
	public Map<String, Serializable> setFamilyNumber(TerminalInfoPO terminalInfoPO,
			NserCustomerSimp customer, String curMenuId, String stype,
			String sn, String sregion) 
	{
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, terminalInfoPO.getOperid(), 
        		terminalInfoPO.getTermid(), customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        ReturnWrap rw = this.getSelfSvcCallHub().setFamilyNumber(msgHeader, sn, sregion, stype);
        
        if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
        {
    		CTagSet result = (CTagSet)rw.getReturnObject();
    		String returnMsg = rw.getReturnMsg();
            Map<String, Serializable> map = new HashMap<String, Serializable>();
            
            // 设置返回结果
            map.put("returnObj", result);
            
            // 设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
		return null;
	}

}
