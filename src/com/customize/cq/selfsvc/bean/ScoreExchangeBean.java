package com.customize.cq.selfsvc.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.customize.cq.selfsvc.bean.impl.BaseBeanCQImpl;
import com.customize.cq.selfsvc.reception.model.ScoreExchangeInfoVO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 积分兑换
 * @author l00190940
 * @date   2011/11/03
 */

public class ScoreExchangeBean extends BaseBeanCQImpl
{

	/**
     * 积分查询
     * @param terminalInfoPO 终端信息
     * @param customer 客户信息
     * @param curMenuId 当前菜单
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map queryScoreValue(TerminalInfoPO terminalInfoPO,NserCustomerSimp customer,String curMenuId)
    {
        Map paramMap = new HashMap();
        
        //设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //设置客户手机号
        paramMap.put("telnumber", customer.getServNumber());
        
        //设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        //设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = getSelfSvcCallCQ().queryScoreValue(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            String v = ((CTagSet)rw.getReturnObject()).GetValue("scoreinfo");
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj", v);
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
        	Map map = new HashMap();
            
            //设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        	
        return null;
    }

	public Map queryScoreExchangeInfo(TerminalInfoPO terminalInfoPO,
			NserCustomerSimp customer, String curMenuId, String acttype) 
	{
        Map paramMap = new HashMap();
        
        //设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //设置客户手机号
        paramMap.put("telnumber", customer.getServNumber());
        
        //设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        //设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        //设置活动类型
        paramMap.put("acttype", acttype);
        
        ReturnWrap rw = getSelfSvcCallCQ().queryScoreExchangeInfo(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            CRSet v = ((CRSet)rw.getReturnObject());
            String returnMsg = rw.getReturnMsg();
            List resultList = new ArrayList();
            
            for(int i = 0 ; i <= v.GetRowCount() - 1 ; i++)
            {
            	ScoreExchangeInfoVO scoreExchangeInfoVO = new ScoreExchangeInfoVO();
            	
            	//modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
//            	scoreExchangeInfoVO.setScore(v.GetValue(i, 0));
//            	scoreExchangeInfoVO.setNlevel(v.GetValue(i, 1));
//            	scoreExchangeInfoVO.setNote(v.GetValue(i, 2));
//            	scoreExchangeInfoVO.setItemid(v.GetValue(i, 3));
//            	scoreExchangeInfoVO.setActiveno(v.GetValue(i, 4));
            	//modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi需求编号:OR_huawei_201407_373_静态检查_自助终端
            	
            	resultList.add(scoreExchangeInfoVO);
            }

            Map map = new HashMap();
            
            //设置返回结果
            map.put("returnObj",resultList);
            
            //设置返回信息
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        else if (rw != null)
        {
        	Map map = new HashMap();
            
            //设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            return map;
        }
        
		return null;
	}

	public Map exchangeBalance(TerminalInfoPO terminalInfoPO,
			NserCustomerSimp customer, String curMenuId, String activeno,
			String nlevel, String serviceid) 
	{
        Map paramMap = new HashMap();
        
        //设置操作员id
        paramMap.put("curOper", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("atsvNum", terminalInfoPO.getTermid());
        
        //设置客户手机号
        paramMap.put("telnumber", customer.getServNumber());
        
        //设置客户接触id
        paramMap.put("touchoid", customer.getContactId());
        
        //设置当前菜单
        paramMap.put("curMenuId", curMenuId);
        
        //设置活动编号
        paramMap.put("activeno", activeno);
        
        //设置活动级别
        paramMap.put("nlevel", nlevel);
        
        //设置奖品编码
        paramMap.put("serviceid", serviceid);
        
        ReturnWrap rw = getSelfSvcCallCQ().exchangeBalance(paramMap);
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
        	String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        	if(Constants.PROOPERORGID_CQ.equals(province))
        	{
        		CTagSet result = (CTagSet)rw.getReturnObject();
        		String returnMsg = rw.getReturnMsg();
                Map map = new HashMap();
                
                // 设置成功标志
                map.put("successFlag", "1");
                
                // 设置返回结果
                map.put("returnObj", result);
                
                // 设置返回信息
                map.put("returnMsg", returnMsg);
                
                return map;
        	}
        }
        else if(rw != null)
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
