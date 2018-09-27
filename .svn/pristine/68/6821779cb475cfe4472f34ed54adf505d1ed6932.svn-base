/*
* @filename: ScoreExECoupBean.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brief:  湖北，积分兑换电子券功能，查询和提交的Bean
* @author: m00227318
* @date:  2012/09/20
* @remark: create m00227318 2012/09/20 eCommerce V200R003C12L09 OR_huawei_201209_33
*/
package com.customize.hub.selfsvc.bean;

import java.util.HashMap;
import java.util.Map;

import com.customize.hub.selfsvc.bean.impl.BaseBeanHubImpl;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
* 湖北积分兑换电子券
* @author m00227318
* 
*/
public class ScoreExECoupBean extends BaseBeanHubImpl
{
	/**
	 * 查询当前用户的优惠类赠品
	 * @param touchid 客户接触id
	 * @param terminalInfoPO 终端机信息
	 * @param telnum 当前用户手机号
	 * @param curMenuId 当前菜单id
	 * @param prodid 活动编码
	 * @param privid 档次编码
	 * @return
	 */
	public Map<String, Object> qryPrefRewardList(TerminalInfoPO terminalInfoPO, NserCustomerSimp customerSimp, String curMenuId, String prodid, String privid)
	{
		Map<String, String> paramMap = new HashMap<String, String>();
		
		//设置当前菜单
        paramMap.put("menuID", curMenuId);
        
        //手机号码
        paramMap.put("telnum", customerSimp.getServNumber());
        
        //设置客户接触ID
        paramMap.put("touchOID", customerSimp.getContactId());
        
        //设置操作员id
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        //设置终端机id
        paramMap.put("termID", terminalInfoPO.getTermid());        
        
        //设置活动编码
        paramMap.put("activityId", prodid);
        
        //设置档次编码
        paramMap.put("dangciId", privid);
        
        //调用接口查询当前用户的赠品
        ReturnWrap rw = super.getSelfSvcCallHub().qryRewardList(paramMap);
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {           
            
            //设置返回结果
            map.put("returnObj", rw.getReturnObject());
            
            //设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            // 设置返回码
            map.put("errcode", rw.getErrcode());
            
            // 设置成功标志
            map.put("successFlag", "1");

            return map;
        }
        
        if (rw != null)
        {
            //设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            // 设置返回码
            map.put("errcode", rw.getErrcode());           
        }
        else
        {
        	//设置返回信息
            map.put("returnMsg", "积分兑换电子券活动，赠品列表查询失败");
            
            // 设置返回码
            map.put("errcode", "");
        }
        
        return map;
	}
	
	/**
	 * 提交当前用户积分兑换电子券的业务
	 * @author m00227318
	 * @param termInfo:终端机信息
	 * @param customerSimp:客户信息
	 * @param curMenuId:当前菜单
	 * @param activityId：活动编码
	 * @param dangciId：档次编码
	 * @param actreward：附加属性字符串
	 * @param imeiid：手机imeiid
	 * @param onlycheck:1为预受理，0为受理
	 * @param quantity：赠品数量
	 * @param accesstype：受理渠道
	 * @param password：密码
	 * @param totalfee：用户投入的费用金额
	 * @param paytype：支付方式
	 * @param addattrstr：优惠产品的附加属性串
	 * @return
	 * @remark: create m00227318 2012/09/20 eCommerce V200R003C12L09 OR_huawei_201209_33
	 */
	public Map<String,Object> prefRewardCommit(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String activityId, String dangciId, 
           String actreward, String imeiid, String onlycheck, String quantity, String accesstype, String password, String totalfee, String paytype, String addattrstr)
	{
        Map<String,String> paramMap = new HashMap<String,String>();
        
        // 设置操作员id
        paramMap.put("operID", termInfo.getOperid());
        
        // 设置终端机id
        paramMap.put("termID", termInfo.getTermid());
        
        // 设置客户手机号
        paramMap.put("telnum", customer.getServNumber());
        
        // 设置客户接触id
        paramMap.put("touchOID", customer.getContactId());
        
        // 设置当前菜单
        paramMap.put("menuID", curMenuId);
        
        // 活动编码
        paramMap.put("actno", activityId);
        
        // 档次编码
        paramMap.put("actlevel", dangciId);
        
        // 活动赠品编号串
        paramMap.put("actrewaed", actreward);
        
        // 手机imeiid号
        paramMap.put("imeiid", imeiid);
        
        // 1是预受理；0是受理
        paramMap.put("onlycheck", onlycheck);
        
        // 赠品数量
        paramMap.put("quantity", quantity);
        
        // 受理渠道
        paramMap.put("accesstype", accesstype);
        
        // 密码 只有河北和宁夏不需要校验密码
        paramMap.put("password", password);
        
        // 用户投入的费用金额 预受理时可以不传
        paramMap.put("totalfee", totalfee);
        
        // 支付类型
        paramMap.put("paytype", paytype);
        
        //新增产品的附加属性
        paramMap.put("addattrstr", addattrstr);
        
        ReturnWrap rw = getSelfSvcCallHub().recRewardCommit(paramMap);
        
        Map<String,Object> map = new HashMap<String,Object>();
        
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // 设置返回结果
            map.put("returnObj", rw.getReturnObject());
            
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            // 设置返回码
            map.put("errcode", rw.getErrcode());
            
            // 设置成功标志
            map.put("successFlag", "1");
        }
        else if (rw != null)
        {
            // 设置返回信息
            map.put("returnMsg", rw.getReturnMsg());
            
            // 设置返回码
            map.put("errcode", rw.getErrcode());
        }
        else
        {
        	//设置返回信息
            map.put("returnMsg", "积分兑换电子券活动办理失败");
            
            // 设置返回码
            map.put("errcode", "");
        }
        
		return map;
	}
}