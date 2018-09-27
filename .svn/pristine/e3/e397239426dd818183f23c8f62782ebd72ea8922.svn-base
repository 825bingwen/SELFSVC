package com.gmcc.boss.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseBeanImpl;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 号码归属地查询
 * @author x
 *
 */
public class UserRegionBean extends BaseBeanImpl
{
    /**
     * 号码归属地查询
     * @param terminalInfoPO 终端信息
     * @param customer 客户信息
     * @param qryServnuber 要查询的手机号
     * @param curMenuId 当前菜单
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map queryUserRegionReq(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String qryServnuber, String curMenuId)
    {
        Map paramMap = new HashMap();
        
        //设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //设置客户接触id
        paramMap.put("touchoid", (customer == null ? "" : customer.getContactId()));
        
        //设置要查询的手机号
        paramMap.put("qryServnuber", qryServnuber);
        
        //设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = selfSvcCall.queryUserRegionReq(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CTagSet cout = (CTagSet) rw.getReturnObject();
            
            String returnObj = "";
            
            String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
            {
            	//山东的省、市是分开传的            	
            	returnObj = cout.GetValue("provname") + " " + cout.GetValue("cityname");
            }
            else
            {
            	returnObj = cout.GetValue("cityname");
            }
            
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", returnObj);
            
            //设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        return null;
    }
}
