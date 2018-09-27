/*
* @filename: NewSelfSvcDubboCallImpl.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  Dubbo服务协议调用接口
* @author: zKF69263
* @de:  2014/04/14 
* @description: 
* @remark: create g00140516 2012/07/03 R003C12L07n01 OR_huawei_201205_740
*/
package com.gmcc.boss.selfsvc.call;

import com.gmcc.boss.common.base.CPEntity;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.quickpublish.model.MultiProdCommitPO;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdCommitPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DocumentUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;


/**
 * Dubbo服务协议调用接口
 * 
 * @author  zKF69263
 * @version  1.0, 2014/04/14
 * @since  
 */
public class NewSelfSvcDubboCallImpl extends NewSelfSvcCallImpl 
{
	private static Log logger = LogFactory.getLog(NewSelfSvcDubboCallImpl.class);
	
	/**
     * 服务密码验证
     * 
     * @param map
     * @return  ReturnWrap
     * @remark create by hWX5316476 2014-05-13 OR_huawei_201405_235  自助终端接入EBUS一阶段_获取用户信息
     */
    public ReturnWrap checkUserPassword(Map<String, String> map)
    {
    	if(IntMsgUtil.isTransEBUS("BLCSCheckPassWd"))
    	{
			try 
	    	{ 
	    		// 入参　
	        	Map<String,String> inParamMap = new HashMap<String,String>();
	
	        	// 用户号码
	        	inParamMap.put("telNum", map.get("telnumber"));
	        	
	        	// 密码
	        	inParamMap.put("inputPwd", map.get("password"));
	        	                    
	        	return getIntMsgUtil().invokeDubbo("BLCSCheckPassWd", "10001001", "",  "1", 
        			map.get("telnumber"), map.get("curOper"), map.get("atsvNum"), inParamMap);
			} 
	    	catch (Exception e) 
	    	{
	    		logger.error("服务密码验证：", e);
	    		
	    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "服务密码验证失败!");
			}
    	}
    	return super.checkUserPassword(map);
    }
    
    /**
     * 获取用户信息
     * 
     * @param map
     * @return
     * @remark create by hWX5316476 2014-05-13 OR_huawei_201405_235  自助终端接入EBUS一阶段_获取用户信息
     */
    public ReturnWrap getUserInfoHub(Map map)
    {
    	if(IntMsgUtil.isTransEBUS("sMQryUserInfoHub"))
    	{
	    	try 
	    	{
	    		// 入参　
	        	Map<String,String> inParamMap = new HashMap<String,String>();
	
	        	// 用户号码
	        	inParamMap.put("telnum", (String)map.get("telnumber"));

	        	return getIntMsgUtil().invokeDubbo("sMQryUserInfoHub", "10001001", "",  "1", 
        			(String)map.get("telnumber"), (String)map.get("curOper"), (String)map.get("atsvNum"), inParamMap);
			} 
	    	catch (Exception e) 
	    	{
	    		logger.error("获取用户信息：", e);
	    		
	    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "获取用户信息失败!");
			}
    	}
    	return super.getUserInfoHub(map);
    }
    
    
	/**
     * 密码修改
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
     @Override
	public ReturnWrap recChangePassword(Map map)
    {
		// 湖北统一接口平台转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("BLCSChgSubsPassWord")) 
		{
			try 
			{
				// 入参
				Map<String, String> paramMap = new HashMap<String, String>();

				// 手机号
				paramMap.put("telNum", (String)map.get("telnumber"));

				// 旧密码
				paramMap.put("subsOldPassword", (String)map.get("oldPasswd"));

				// 新密码
				paramMap.put("subsNewPassword", (String)map.get("newPasswd"));

                // 功能对应的命令字值 0:密码校验 1:改密码 2: 密码重置(新密码) 3.密码重置(随机)4:密码初始化
				paramMap.put("subCmd", "1");

				return getIntMsgUtil().invokeDubbo("BLCSChgSubsPassWord", (String) map.get("curMenuId"),
					(String) map.get("touchoid"), "1", (String) map.get("telnumber"), (String) map.get("curOper"),
					(String) map.get("atsvNum"), paramMap);
			} 
			catch (Exception e) 
			{
				logger.error("密码修改失败：", e);
				
				return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "密码修改失败!");
			}
		}
		
		return super.recChangePassword(map);
    }
	
	/**
     * 停开机业务处理
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap stopOpenSubs(Map map)
    {
    	// 湖北统一接口平台转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("BLCSStopOpenSubs"))
		{
			try
	        {
				// 入参
				Map<String, Object> paramMap = new HashMap<String, Object>();

				// 手机号
				paramMap.put("servNumber", map.get("telnumber"));

				// 业务类型
				paramMap.put("recType", map.get("stoptype"));

				// 原因
				paramMap.put("changeReason", map.get("reason"));

				// 发送请求到Dubbo服务
				return getIntMsgUtil().invokeDubbo("BLCSStopOpenSubs", (String) map.get("curMenuId"),
					(String) map.get("touchoid"), "1", (String) map.get("telnumber"), (String) map.get("curOper"),
					(String) map.get("atsvNum"), paramMap);
	        }
	        catch (Exception e)
	        {
	            logger.error("停开机失败：", e);
	            
	            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "办理停开机调用接口失败");
	        }
		}
		
		return super.stopOpenSubs(map);
    }
    
    /**
     * 业务统一查询接口 <功能详细描述>
     * 
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap queryService(Map map)
    {
    	// 湖北统一接口平台转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("BLCSIntQuerySubsAllServ")) 
		{
			try
	        {
				// 入参
				Map<String, Object> paramMap = new HashMap<String, Object>();

				// 手机号
				paramMap.put("telNum", map.get("telnumber"));

				// 是否上网卡查询
				paramMap.put("querySN", map.get("sn"));

				// 发送请求到Dubbo服务
				return getIntMsgUtil().invokeDubbo("BLCSIntQuerySubsAllServ", (String) map.get("curMenuId"), 
				    (String) map.get("touchoid"), "1", (String) map.get("telnumber"), (String) map.get("curOper"),
					(String) map.get("atsvNum"), paramMap,
					new String[] { "objType", "spID", "spName", "spBizID",
						"spBizName", "spBizType", "billingType", "price",
						"domain", "startDate", "endDate", "seqNum",
						"charge", "priceDesc"});
	        }
	        catch (Exception e)
	        {
	            logger.error("业务统一查询接口失败：", e);
	            
	            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "业务统一查询接口调用失败");
	        }
		}
		
		return super.queryService(map);
    }
    
    /**
     * 业务统一退订接口
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ReturnWrap cancelService(Map map)
    {
    	// 湖北统一接口平台转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("BLCSIntServUniteCancel")) 
		{
			try
	        {
				// 入参
				Map<String, Object> paramMap = new HashMap<String, Object>();
				
				// 业务信息列表
				List<Map<String, Object>> lstDetailInfo = new ArrayList<Map<String,Object>>();
				
				// 业务信息
				Map<String, Object> detailInfo = new HashMap<String, Object>();
				
				// 对象类型：梦网业务、自有业务、集团业务
				detailInfo.put("objType", map.get("dealType"));
				
				// 对象ID：SP企业编码
				detailInfo.put("spID", map.get("spId"));
				
				// sp业务编码：梦网业务为SP业务编码，其他为产品ID
				detailInfo.put("spBizID", map.get("spBizCode"));
				
				// 业务类型：梦网业务时传入cancel_type为4的时候必须传
				detailInfo.put("spBizType", map.get("bizType"));
				
				// sp所属domian：梦网业务有效
				detailInfo.put("domain", map.get("domain"));
				
				lstDetailInfo.add(detailInfo);
				
				// 手机号
				paramMap.put("servNum", map.get("telnumber"));
				
				// 业务信息
				paramMap.put("detailInfo", lstDetailInfo);

				// 发送请求到Dubbo服务
				return getIntMsgUtil().invokeDubbo("BLCSIntServUniteCancel", (String) map.get("curMenuId"),
					(String) map.get("touchoid"), "1", (String) map.get("telnumber"), (String) map.get("curOper"),
					(String) map.get("atsvNum"), paramMap);
	        }
	        catch (Exception e)
	        {
	            logger.error("业务统一退订接口失败：", e);
	            
	            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "业务统一退订接口调用失败");
	        }
		}
		
		return super.cancelService(map);
    }
    
    /**
     * 订购SP业务
     * 
     * @param map 入参
     * @return 订购结果
     * @see 
     * @remark create g00140516 2011/11/05 R003C11L11n01 BUG_HUB_201111_24
     */
    public ReturnWrap orderSPService(Map map)
    {
    	// 湖北统一接口平台转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("BLCSChangeSubsMonServ")) 
		{
			try
	        {
				// 入参
				Map<String, Object> paramMap = new HashMap<String, Object>();
				
				// 业务信息
				// 业务信息
                List<Map> detailInfo = new ArrayList<Map>();
                
                Map<String, Object> detailInfomap = new HashMap<String, Object>();
                
				// 对象ID：SP企业编码
                detailInfomap.put("spID", map.get("spId"));
				
				// sp业务编码：梦网业务为SP业务编码，其他为产品ID
                detailInfomap.put("spBizID", map.get("spBizCode"));
				
				// sp业务编码类型
                detailInfomap.put("spBizType", map.get("bizType"));
				
				// sp所属domian：如果退订标志为"5"(5 == CANCEL_FLAG，根据DOMAIN进行退订
                detailInfomap.put("spDomain", map.get("domain"));
                
                // 操作类型
                detailInfomap.put("operType", map.get("operType"));
				
                detailInfo.add(detailInfomap);
                
				// 退订标识：只有操作类型为退订时，才取退订标识
				paramMap.put("cancelFlag", map.get("cancelFlag"));
				
				// 是否发送srp控制参数
				paramMap.put("flag", "1");
				
				// 获取操作类型
				paramMap.put("operType", map.get("operType"));
				
				// 业务信息
				paramMap.put("detailInfo", detailInfo);

				// 发送请求到Dubbo服务
				return getIntMsgUtil().invokeDubbo("BLCSChangeSubsMonServ", (String) map.get("curMenuId"),
					(String) map.get("touchoid"), "1", (String) map.get("telnumber"), (String) map.get("curOper"),
					(String) map.get("atsvNum"), paramMap, 
					new String[][]{{"orderResult", "formNumber"},{"orderresult", "formnum"}});
	        }
	        catch (Exception e)
	        {
	            logger.error("SP业务订购接口失败：", e);
	            
	            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "SP业务订购接口调用失败");
	        }
		}
		
		return super.orderSPService(map);
    }
    
    
    /**
     * 获取4G卡类型
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark 刘涛 create by liutao00194861 OR_HUB_201603_1191	【BOSS常规需求】自助终端显示内容优化需求（张德伟）
	 */
	@Override
    public ReturnWrap getIs4GCard(MsgHeaderPO msgHeader)
    {
		try
		{
			Map<String, String> inParamMap = new HashMap<String, String>();

			// 手机号码
			inParamMap.put("telnum", msgHeader.getTelNum());

			String[] outParam = {"result"};

			return getIntMsgUtil().invokeDubbo("BLCSCheckIs4GCardByTelnum", msgHeader, inParamMap, outParam);
		}
		catch(Exception e)
		{
			logger.error("查询是否4G卡失败", e);

			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "查询是否4G卡失败");
		}
	}

	/**
	 * 获取积分
	 * scoreinfo.SetRegion(jvInParams["region"].GetStr());
	 * scoreinfo.SetservNumber(jvInParams["servnumber"].GetStr());
	 * scoreinfo.SetEntityID(jvInParams["entityid"].GetStr());
	 * scoreinfo.Setprodtype(jvInParams["prodtype"].GetStr());
	 * scoreinfo.SetCycle(jvInParams["scorecycle"].GetStr());
	 * scoreinfo.SetEntityType(jvInParams["entitytype"].GetStr());
	 * scoreinfo.SetScoreTypeID(jvInParams["scoretypeid"].GetStr());
	 * scoreinfo.SetqryBalanceDetail(_atoi64(jvInParams["qrybalancedetail"].GetStr()));
	 *
	 * @param map
	 * @return
	 * @remark 刘涛 create by liutao00194861 OR_HUB_201603_1191	【BOSS常规需求】自助终端显示内容优化需求（张德伟）
	 * @see [类、类#方法、类#成员]
	 */
	@Override
	public ReturnWrap getAvailIntegralEbus(MsgHeaderPO msgHeader)
	{
		try
		{
			Map<String, String> inParamMap = new HashMap<String, String>();

			// 手机号码
			inParamMap.put("servnumber", msgHeader.getTelNum());
			inParamMap.put("region", msgHeader.getRegion());
			//inParamMap.put("entityid", msgHeader.ge);

			String[] outParam = {"result"};

			return getIntMsgUtil().invokeDubbo("GetAvailIntegralEbus", msgHeader, inParamMap, outParam);
		}
		catch(Exception e)
		{
			logger.error("查询是否4G卡失败", e);

			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "查询是否4G卡失败");
		}
	}
	
	/**
     * 向用户发送随机密码短信
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2014/04/21 OR_huawei_201404_375 自助终端全流程接入EBUS改造_充值交费 自选套餐
     */
	@Override
    public ReturnWrap sendSmsHub(Map map)
    {
        // 湖北统一接口平台转EBUS开关开启
        if(IntMsgUtil.isTransEBUS("PTSendSmsInfo"))
        {
        	try 
        	{
        		// 入参　
            	Map<String,String> inParamMap = new HashMap<String,String>();
            	
            	// 短信模板编号
            	inParamMap.put("TEMPLATENO",(String)map.get("templateno"));
            	
            	// 手机号码
            	inParamMap.put("TELNUM", (String)map.get("telnumber"));
            	
            	// 参数列表
            	String smsparam =(String)map.get("smsparam");
            	String[] str = smsparam.split("#");
            	
            	for(int i = 0;i < str.length;i++)
            	{
            		inParamMap.put(str[i].substring(0,1),str[i].substring(1));
            	}
            	
            	return getIntMsgUtil().invokeDubbo("PTSendSmsInfo", (String)map.get("menuID"), 
        			(String)map.get("touchOID"), "1", (String)map.get("telnumber"), (String)map.get("operID"), 
        			(String)map.get("termID"), inParamMap);
			}
        	catch (Exception e) 
        	{
        		logger.error("向用户发送随机密码短信失败：", e);
        		
        		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "向用户发送随机密码短信失败!");
        	}
        }
        return super.sendSmsHub(map);
    }
    
    /**
     * 向用户发送随机密码短信
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2014/04/21 OR_huawei_201404_375 自助终端全流程接入EBUS改造_充值交费 自选套餐
     */
    @Override
    public ReturnWrap sendSMS(Map map)
    {
    	// 湖北统一接口平台转EBUS开关开启
        if(IntMsgUtil.isTransEBUS("PTSendSmsInfo"))
        {
        	try
        	{
	        	// 入参　
	        	Map<String,String> inParamMap = new HashMap<String,String>();
	        	
	        	// 短信模板编号
	        	inParamMap.put("TEMPLATENO","Atsv0001");
	        	
	        	// 手机号码
	        	inParamMap.put("TELNUM", (String)map.get("telnumber"));
	        	
	        	// 参数列表
	        	inParamMap.put("1",(String)map.get("smsContent"));
	        	
	        	return getIntMsgUtil().invokeDubbo("PTSendSmsInfo", (String)map.get("menuID"), 
        			(String)map.get("touchOID"), "1", (String)map.get("telnumber"), (String)map.get("operID"), 
        			(String)map.get("termID"), inParamMap);
        	}
        	catch(Exception e)
        	{
        		logger.error("向用户发送随机密码短信失败：", e);
        		
        		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "向用户发送随机密码短信失败!");
        	}
        }
        return super.sendSMS(map);
    }
    
	/**
     * 密码重置
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2014/04/21 OR_huawei_201404_374 自助终端全流程接入EBUS改造_重置密码、修改密码
     */
	@Override
    public ReturnWrap resetPassword(Map map)
    {
		// 湖北统一接口平台转EBUS开关开启
        if(IntMsgUtil.isTransEBUS("BLCSChgSubsPassWord"))
        {
        	try
        	{
	        	// 入参　
	        	Map<String,String> inParamMap = new HashMap<String,String> ();
	        	
	        	// 手机号
	        	inParamMap.put("telNum", (String)map.get("telnumber"));
	        	
	        	// 旧密码
	        	inParamMap.put("subsOldPassword", "");
	        	
	        	// 新密码
	        	inParamMap.put("subsNewPassword", "");
	        	
	        	// 功能对应的命令字值 0:密码校验 1:改密码 2: 密码重置(新密码) 3.密码重置(随机)4:密码初始化
	        	inParamMap.put("subCmd", "4");
	        	
	        	return getIntMsgUtil().invokeDubbo("BLCSChgSubsPassWord", (String)map.get("menuID"), 
        			(String)map.get("touchOID"), "1", (String)map.get("telnumber"), (String)map.get("operID"), 
        			(String)map.get("termID"), inParamMap);
        	}
        	catch(Exception e)
        	{
        		logger.error("密码重置失败：", e);
        		
        		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "密码重置失败!");
        	}
        }
        return super.resetPassword(map);
    }
	
    /**
     * 本机状态查询
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2014/04/19
     */
	@Override
    public ReturnWrap queryCurrentStatus(Map map)
    {
		// 湖北统一接口平台转EBUS开关开启
        if(IntMsgUtil.isTransEBUS("sMQryUserInfoHub"))
        {
            try
            { 
            	// 入参　
            	Map<String,String> inParamMap = new HashMap<String,String>();
            	
            	// 手机号
            	inParamMap.put("telnum", (String)map.get("telnum"));
            	
            	// 出参键值对 取值键名与输出的键名对应关系数组
            	String[][] outParamKeyDefine = {{"statusName"},{"state"}};
            	
            	return getIntMsgUtil().invokeDubbo("sMQryUserInfoHub", (String)map.get("menuid"), 
            		(String)map.get("touchoid"), "1", (String)map.get("telnum"), (String)map.get("operid"), 
            		(String)map.get("operid"), inParamMap, outParamKeyDefine);
            }
            catch (Exception e)
            {
                logger.error("当前状态查询失败：", e);
                
	              return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "当前状态查询失败!");
            }
        }
        return super.queryCurrentStatus(map);
    }
	
    /**
     * 呼叫转移受理
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2014/04/19 
     */
	@Override
    public ReturnWrap commitCallTransferNo(Map map)
    {
		// 湖北统一接口平台转EBUS开关开启
		if(IntMsgUtil.isTransEBUS("BLCSEIDealCallTransfer"))
		{
	        try
	        {
	        	// 入参　
            	Map<String,String> inParamMap = new HashMap<String,String>();
            	
            	// 手机号
            	inParamMap.put("telNum", (String)map.get("servNumber"));
            	
	            // 办理类型
            	inParamMap.put("dealType", (String)map.get("dealType"));

            	// 呼转类型
            	inParamMap.put("callType", (String)map.get("callType"));
	            
	            // 被呼转号码
            	inParamMap.put("calledNum", (String)map.get("transferNumber"));
            	
            	return getIntMsgUtil().invokeDubbo("BLCSEIDealCallTransfer", (String)map.get("menuID"), 
            		(String)map.get("contactID"), "1", (String)map.get("servNumber"), 
            		(String)map.get("operID"), (String)map.get("termID"), inParamMap);
	        }
	        catch (Exception e)
	        {
	            logger.error("呼叫转移受理失败：", e);
	
	            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "呼叫转移受理发生异常！");
	        }
		}
        return super.commitCallTransferNo(map);
    }
	
    /**
     * 使用手机号码、服务密码进行身份认证
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2014/04/21 OR_huawei_201404_373 自助终端全流程接入EBUS改造_服务密码认证、身份证认证
     */
	/*@Override
    public ReturnWrap getUserInfoWithPwd(Map map)
    {
		// 湖北统一接口平台转EBUS开关开启
    	if(IntMsgUtil.isTransEBUS("Atsv_Qry_UserInfo_Hub"))
    	{
    		try 
    		{
    			// 入参
    			Map<String,String> inParamMap = new HashMap<String,String>();
    			
    			// 手机号码
    			inParamMap.put("telNum", (String)map.get("telnum"));
    			
    			// 是否校验密码
    			inParamMap.put("isCheckPass", "1");
    			
    			// 密码
    			inParamMap.put("passWord", (String)map.get("password"));

    			// 出参键值对 取值键名与输出的键名对应关系数组
    			String[][] outParamKeyDefine = {{"subname", "region", "regionname", "productid", "productname", "productgroup", "viptype", "nettype", "contacted", "status", "subage", "subscore", "smallregion"},
    											{"subname", "region", "regionname", "productid", "productname", "productgroup", "viptype","nettype", "contactid", "status", "subage", "subscore", "smallregion"}};

    			return getIntMsgUtil().invokeDubbo("Atsv_Qry_UserInfo_Hub", "10001001", "", "1", 
    				(String)map.get("telnum"), (String)map.get("operID"), (String)map.get("termID"), 
    				inParamMap, outParamKeyDefine);
			} 
    		catch (Exception e) 
    		{
    			logger.error("服务密码认证失败：", e);
                
                return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
			}
    	}
        return super.getUserInfoWithPwd(map);
    }*/
	
    /**
     * 无密码登录
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2014/04/21 OR_huawei_201404_373 自助终端全流程接入EBUS改造_服务密码认证、身份证认证
     */
/*	@Override
    public ReturnWrap getUserInfo(Map map)
    {
		// 湖北统一接口平台转EBUS开关开启
		if(IntMsgUtil.isTransEBUS("Atsv_Qry_UserInfo_Hub"))
		{
			try 
    		{
				// 入参
    			Map<String,String> inParamMap = new HashMap<String,String>();

    			// 手机号码
    			inParamMap.put("telnum", (String)map.get("telnum"));
    			
    			// 是否校验密码
    			inParamMap.put("ischeckpass", "0");
    			
    			// 密码
    			inParamMap.put("password", (String)map.get("password"));

    			// 出参键值对 取值键名与输出的键名对应关系数组
    			String[][] outParamKeyDefine = {{"subname", "region", "regionname", "productid", "productname", "productgroup", "viptype", "nettype", "contacted", "status", "subage", "subscore", "smallregion"},
    											{"subname", "region", "regionname", "productid", "productname", "productgroup", "viptype","nettype", "contactid", "status", "subage", "subscore", "smallregion"}};

    			return getIntMsgUtil().invokeDubbo("Atsv_Qry_UserInfo_Hub", "10001001", "", "1", 
    				(String)map.get("telnum"), (String)map.get("operID"), (String)map.get("termID"), 
    				inParamMap, outParamKeyDefine);
			} 
    		catch (Exception e) 
    		{
    			logger.error("无密码登录获取用户信息失败：", e);
                
                return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
			}
		}
        return super.getUserInfo(map);
    }*/
	
	/** 
     * 详单查询权限验证_湖北
     * 
     * @param msgHeader 报文头信息
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2014/04/22 OR_huawei_201404_375 自助终端全流程接入EBUS改造_充值交费 自选套餐
     * @remark modify by zKF69263 2014/09/15 OR_huawei_201409_425 自助终端接入EBUS_详单查询
     */
    @Override
    public ReturnWrap checkQueryAuth(MsgHeaderPO msgHeader)
    {
		// 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("CTCSQueryBillDetailChk"))
		{
			try
	        {
				// 入参
				Map<String,String> inParamMap = new HashMap<String,String>();
				
				// 手机号码
				inParamMap.put("telNum", msgHeader.getTelNum());
				
				// 调用ebus服务
	            return getIntMsgUtil().invokeDubbo("CTCSQueryBillDetailChk", msgHeader, inParamMap);
	        }
	        catch (Exception e)
	        {
	            logger.error("湖北验证详单查询权限失败：", e);
	            
	            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
	        }
		}
		
		return super.checkQueryAuth(msgHeader);
    }
	
    /** 
     * NG3.5帐详单改造之详单查询
     * 
     * @param msgHeader 报文头信息
     * @param month 查询月份
     * @param cdrType 详单类型
     * @param feeType 费用类型
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark create g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
     * * @remark create hWX5316476 2014/04/21 OR_huawei_201404_375 自助终端全流程接入EBUS改造_充值交费 自选套餐
     */
    @Override
    public ReturnWrap queryDetailedRecords2012(MsgHeaderPO msgHeader, String month,
        String cdrType, String feeType)
    {
    	// 湖北统一接口平台转EBUS开关开启
    	if (IntMsgUtil.isTransEBUS("qryClearList"))
    	{
    		try 
    		{
				Map<String,String> inParamMap = new HashMap<String,String>();

	            // 手机号码
				inParamMap.put("telnum", msgHeader.getTelNum());
	            
	            // 用户密码
				inParamMap.put("userpwd", "");
	            
	            // 账期
				inParamMap.put("billcycle", month);
				
	            // 起始日期
				inParamMap.put("startdate", "");
	            
	            // 结束日期
				inParamMap.put("enddate", "");
	            
	            // 详单类型
				inParamMap.put("flag", cdrType);
	            
	            // 校验密码
				inParamMap.put("ckkey", "");
	            
	            // 选择类型
				inParamMap.put("selecttype", feeType);
	            
	            // 访问类型
				inParamMap.put("accesstype", "bsacAtsv");
				
				// 出参键值对 取值键名与输出的键名对应关系数组
    			String[][] outParamKeyDefine = {{"billcount","billitem"},{"billsummary","billitem"}};
    			
    			// 调用ebus服务
                return getIntMsgUtil().invokeDubbo("qryClearList", msgHeader, inParamMap, outParamKeyDefine);
			}
    		catch (Exception e) 
    		{
    			logger.error("新详单查询失败：", e);
                
    			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
			}
    	}
    	
    	return super.queryDetailedRecords2012(msgHeader, month, cdrType, feeType);
    }

	/**
     * 缴费历史查询
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
	@Override
	public ReturnWrap qryChargeHistory(Map map)
	{
		// 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("BLCSQueryReceptionHis"))
        {
        	try
        	{
	        	// 入参
	        	Map<String, Object> paramMap = new HashMap<String, Object>();
	
	        	// 手机号码
	        	paramMap.put("telNum", (String)map.get("servNumber"));
	        	
	        	// 开始时间
	        	paramMap.put("startDate", (String)map.get("startDate"));
	        	
	        	// 结束时间
	        	paramMap.put("endDate", (String)map.get("endDate"));
	        	
	        	// 业务类型，接口那边说先传死的
	        	paramMap.put("receptionType", "Charge");
	        	
	        	// 本次使用中传“0”,网厅用到
	        	paramMap.put("qryRecOrdbyAcctID", "0");
	        	
	        	// 发送请求到Dubbo服务
	        	return getIntMsgUtil().invokeDubbo("BLCSQueryReceptionHis", (String)map.get("curMenuId"), 
        			(String)map.get("contactID"), "1", (String)map.get("servNumber"),
        			(String)map.get("operID"), (String)map.get("termID"), paramMap, 
        			new String[]{"formNum","itemName","recDate","cycle","fee","status","contactType"});
        	}
        	catch (Exception e)
        	{
        		logger.error("缴费历史查询失败：", e);
        		
        		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "缴费历史查询失败!");
        	}
        }
		return super.qryChargeHistory(map);
	}
	
	 /**
     * 积分查询
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify by hWX5316476 2014-05-13 OR_huawei_201405_233  自助终端接入EBUS一阶段_查询积分
     */
	@Override
	public ReturnWrap queryScoreValue(Map map) 
	{
		// 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("qrySubsScoreSimple139Mail"))
        {
        	try 
        	{
	        	// 入参
				Map<String, Object> paramMap = new HashMap<String, Object>();
				
				// 手机号码
				paramMap.put("telnum", (String)map.get("telnumber"));
				
				// 0不发送邮件 1发送邮件
				paramMap.put("issendmail", "0");
				
				// 发送请求到Dubbo服务
				ReturnWrap rw =  getIntMsgUtil().invokeDubbo("qrySubsScoreSimple139Mail", (String)map.get("curMenuId"), 
					(String)map.get("touchoid"), "1", (String)map.get("telnumber"),
					(String)map.get("curOper"), (String)map.get("atsvNum"), paramMap);
				
				// 成功返回积分信息 
				if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
				{
					CTagSet cs = (CTagSet)rw.getReturnObject();
					String sbrand = cs.GetValue("sbrand");
					
					String brandName = "";
					if("M".equals(sbrand))
					{
						brandName = "动感地带";
					}
					else if("G".equals(sbrand))
					{
						brandName = "全球通";
					}
					else
					{
						brandName = "神州行";
					}
					// 拼装成串格式：品牌描述~可用积分~本月新增积分~本月消费积分~本月奖励积分~本月已兑换积分~本月末剩余积分~年底清零积分
					StringBuffer sb = new StringBuffer();
					sb.append(brandName).append("~");
					sb.append(cs.GetValue("lleftscore")).append("~");
	                sb.append(cs.GetValue("curincscorestr")).append("~");
	                sb.append(cs.GetValue("curconsumescorestr")).append("~");
	                sb.append(cs.GetValue("curawardscorestr")).append("~");
	                sb.append(cs.GetValue("curconvertscorestr")).append("~");
	                sb.append(cs.GetValue("curlastscorestr")).append("~");
	                sb.append(cs.GetValue("yearzeroscorestr"));
					CTagSet cts = new CTagSet();
					cts.SetValue("scoreinfo", sb.toString());
					rw.setReturnObject(cts);
				}
				return rw;
        	}
        	catch (Exception e)
        	{
        		logger.error("积分查询失败：", e);
        		
        		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "积分查询失败!");
        	}
        }
		return super.queryScoreValue(map);
	}
	
	/**
     * 积分明细查询
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
	@Override
	public ReturnWrap queryScoreDetailHis(Map map) 
	{ 
		// 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("BLCSQryScoreDetailHis"))
        {							
        	try
        	{
	        	// 入参
	        	Map<String, Object> paramMap = new HashMap<String, Object>();
	        	
	        	// 手机号码
	        	paramMap.put("servNumber", (String)map.get("telnumber"));
	        	
	        	// 地区
	        	paramMap.put("region", (String)map.get("region"));
	        	
	        	// 查询类型 :0 积分;1 M值;2 分贝 不为空
	        	paramMap.put("qryType", "0");
	        	
	        	// 开始时间
	        	paramMap.put("startDate", (String)map.get("startDate") + "000000");
	        	
	        	// 结束时间
	        	paramMap.put("endDate", (String)map.get("endDate") + "235959");
	        	
	        	// 发送请求到Dubbo服务
	        	ReturnWrap rw =  getIntMsgUtil().invokeDubbo("BLCSQryScoreDetailHis", (String)map.get("curMenuId"), 
        			(String)map.get("touchoid"),"1", (String)map.get("telnumber"), 
        			(String)map.get("curOper"), (String)map.get("atsvNum"), paramMap, 
        			new String[]{"subsID","recOid","scoreType",
        				"chgScore","accessType","null1","null2",
        				"updateTime","reason","chgScore","null3",
        				"null4","null5","null6","null7","cycle",
        				"servNumber","subsName"});
	        	
	        	// 如果接口调用成功，将返回出参的数组部分添加到returnObject
	        	if(rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
	        	{
	        		Vector vector = (Vector)rw.getReturnObject();
	        		
	        		if (vector != null && vector.size() > 1)
	                {
	                    CRSet crset = (CRSet) vector.get(1);
	                    rw.setReturnObject(crset);
	                }
	        		else
	        		{
	        			CRSet crset = new CRSet();
	        			rw.setReturnObject(crset);
	        		}
	        	}
	        	return rw;
        	}
        	catch (Exception e)
        	{
        		logger.error("积分明细查询失败：", e);
        		
        		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "积分明细查询失败!");
        	}
        }
		return super.queryScoreDetailHis(map);
	}

	/**
	 * 湖北版的积分兑换历史查询
	 * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
	 */
	@Override
	public ReturnWrap queryScorePrizeHis(Map map)
	{
		// 湖北统一接口平台转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("fmGetScorePrize"))
		{
			try
			{
				// 入参
				Map<String, Object> paramMap = new HashMap<String, Object>();
				
				// 手机号码
				paramMap.put("telnum", (String)map.get("telnumber"));
				
				// 开始时间 
				paramMap.put("startDate", (String)map.get("startDate"));
				
				//结束时间
				paramMap.put("endDate", (String)map.get("endDate"));
				
				//发送请求到Dubbo服务
				return getIntMsgUtil().invokeDubbo("fmGetScorePrize", (String)map.get("curMenuId"),
					(String)map.get("touchoid"), "1", (String)map.get("telnumber"), 
					(String)map.get("curOper"), (String)map.get("atsvNum"), paramMap,
					new String[]{"statusdate","prodname","orgname","opername"});
			}
			catch (Exception e)
			{
				logger.error("湖北版的积分兑换历史查询失败：", e);
				
				return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "湖北版的积分兑换历史查询失败!");
			}
		}
		return super.queryScorePrizeHis(map);
	}

    /**
     * PUK码查询
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 2014-07-09 OR_huawei_201406_303 自助终端接入EBUS二阶段_PUK码查询 
     */
    public ReturnWrap queryPUK(Map map)
    {
    	// 湖北统一接口平台转EBUS开关开启
    	if(IntMsgUtil.isTransEBUS("BLCSQrySimPuk"))
    	{
    		try
    		{
    			// 封装入参
    			Map<String, Object> paramMap = new HashMap<String, Object>();
    			
    			// 手机号码
    			paramMap.put("telNum", (String) map.get("telnumber"));
    			
    			// 此处必须传servNumber
    			paramMap.put("servNumber", (String) map.get("telnumber"));
    			
    			// 查询方式
    			paramMap.put("qryType", "");
    			
    			//是否上网卡查询
    			paramMap.put("isNetCard", "");
    			
    			//SIM卡号
    			paramMap.put("enum", "");
    			
    			// 空白卡号
    			paramMap.put("blankNum", "");
    			
    			// 是否需要MSI信息
    			paramMap.put("isLinkMsiInfo", "");
    			
    			String[] arrAttrsKey = {"iCol0", "iCol1", "iCol2", "iCol3"};
    			
    			return getIntMsgUtil().invokeDubbo("BLCSQrySimPuk", (String) map.get("curMenuId"), 
					(String) map.get("touchoid"), "1", (String) map.get("telnumber"), 
					(String) map.get("curOper"), (String) map.get("atsvNum"), paramMap, arrAttrsKey);
    		}
    		catch(Exception e)
    		{
    		    return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
    		}
    		
    	}
    	return super.queryPUK(map);
    }

	/**
	 * 归属地查询（湖北）
	 * @param map
	 * @return
	 * @remark create by sWX219697 2014-7-8 OR_huawei_201407_35_自助终端接入EBUS二阶段_归属地查询 
	 */
    public ReturnWrap queryUserRegionReq(Map map)
	{
		// 湖北统一接口平台转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("PTQueryLocation"))
		{
			try
			{
				// 入参
				Map<String, Object> inMap = new HashMap<String, Object>();
	            
				// 手机号码
				inMap.put("servNum", (String)map.get("qryServnuber"));
				
				//是否输出省份信息 1：输出，非1：不输出
				inMap.put("linkProvInfo", "1");
				
				//发送请求到Dubbo服务
				return getIntMsgUtil().invokeDubbo("PTQueryLocation", (String)map.get("curMenuId"),
					(String)map.get("touchoid"), "0", "999", (String)map.get("curOper"), 
					(String)map.get("atsvNum"), inMap,
					new String[][]{{"regionName","provName"},{"cityname","provname"}});
			}
			catch (Exception e)
			{
				logger.error("湖北版归属地查询失败：", e);
				
				return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "湖北版归属地查询失败!");
			}
		}
			
		return super.queryUserRegionReq(map);
	}

	/**
	 * 根据nocde(新)查询产品,优惠的资费描述信息
	 * 
	 * @param map 接口入参
	 * @return ReturnWrap 返回值
	 * @remark create zKF69263 2014/08/04 R003C14L08n01 OR_huawei_201407_40 自助终端接入EBUS二阶段_飞信
	 */
	@Override
    public ReturnWrap queryFeeMessage(Map map)
    {
		// 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("BLCSQryRatePlane"))
        {
	        try
	        {
                Map<String, Object> inParamMap = new HashMap<String, Object>();
	            
                // NCODE
                inParamMap.put("nCode", map.get("nCode"));
                
                // 操作类型
                inParamMap.put("opType", "PCOpRec");
                
	            // 手机号码
	            inParamMap.put("telNum", map.get("servNumber"));
	            
	            // 发送dubbo请求
	            return getIntMsgUtil().invokeDubbo("BLCSQryRatePlane", (String)map.get("menuID"), 
	            	(String)map.get("contactID"), "1", (String)map.get("servNumber"),
            		(String)map.get("operID"), (String)map.get("termID"), inParamMap);
	        }
	        catch (Exception e)
	        {
	            logger.error("业务受理时，产品资费信息查询失败：", e);
	            
	            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
	        }
        }
        
        return super.queryFeeMessage(map);
    }
    
    /**
     * 根据ncode查询特别提示信息
     * 
     * @param paramMap 接口入参
     * @return ReturnWrap 返回值
     * @remark create zKF69263 2014/08/04 R003C14L08n01 OR_huawei_201407_40 自助终端接入EBUS二阶段_飞信
     */
    public ReturnWrap qryNcodeTips(Map<String, String> paramMap)
    {
        // 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("BLCSGetObjectTipsByNCode"))
        {
            try
            {
                // 入参
                Map<String, Object> param = new HashMap<String, Object>();
                
                // 查询数据
                List<Map<String, String>> dataList = new  ArrayList<Map<String,String>>();
                
                // crset入参
                Map<String, String> prodParam = new HashMap<String, String>();
                
                // NCODE编码
                prodParam.put("NCODE", paramMap.get("ncode"));
                
                // 提示类型
                prodParam.put("TIPTYPE", paramMap.get("tipType"));
                
                // 操作类型
                prodParam.put("OPERATORTYPE", paramMap.get("operType"));
                
                // 添加查询数据
                dataList.add(prodParam);
                
                // 查询数据
                param.put("dataList", dataList);
                
                // 手机号码
                param.put("servNumber", paramMap.get("telnum"));
                
                // 业务类型
                param.put("recType", paramMap.get("rectype"));
                
                // 发送dubbo请求
                ReturnWrap rw = getIntMsgUtil().invokeDubbo("BLCSGetObjectTipsByNCode", paramMap.get("menuid"), 
                    paramMap.get("unicontact"), "1", paramMap.get("telnum"), 
                    paramMap.get("operatorid"), paramMap.get("termid"),
                    param, new String[]{"NCODE","TIPTYPE","OPERATORTYPE","TIPTEXT"});
                
                // 组装vector存入rw
                if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
                {
                    Vector<CPEntity> v = new Vector<CPEntity>();
                    v.add(0, null);
                    v.add(1, (CRSet)rw.getReturnObject());
                    rw.setReturnObject(v);
                }
                
                return rw;
            }
            catch (Exception e)
            {
                logger.error("根据NCode查询产品提示信息失败：", e);
                
                return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "根据NCode查询产品提示信息失败!");
            }
        }
        
        return super.qryNcodeTips(paramMap);
    }
	
	/**
     * 查询产品提示信息
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark add by jWX216858 2014-07-03 OR_huawei_201407_33 自助终端接入EBUS二阶段_促销活动
     */
	@Override
	public ReturnWrap qryObjectTips(Map<String, String> paramMap, List<Map<String, String>> prods) 
	{
		// 湖北转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("BLCSGetObjectTips"))
		{
			// 入参
			Map<String, Object> param = new HashMap<String, Object>();
			
			// 手机号码
			param.put("servNumber", paramMap.get("telnum"));
			
			// 业务类型
			param.put("recType", paramMap.get("recType"));
			
			Map<String, String> prodMap = null;
			
			// crset入参
			Map<String, String> prodParam = new HashMap<String, String>();
			List<Map<String, String>> prodList = new  ArrayList<Map<String,String>>();
			
			// 遍历集合，入参转换
			for (int i = 0; i < prods.size(); i++)
			{
				prodMap = prods.get(i);
				
				// 对象类型
				prodParam.put("OBJTYPE", prodMap.get("objectType"));
				
				// 提示类型
				prodParam.put("TIPTYPE", prodMap.get("tipType"));
				
				// 操作类型
				prodParam.put("OPERTYPE", prodMap.get("operType"));
				
				// 对象编码
				prodParam.put("OBJID", prodMap.get("objectID"));
				
				prodList.add(prodParam);
			}
			
			param.put("dataList", prodList);
			
			// 发送dubbo请求
			ReturnWrap rw = getIntMsgUtil().invokeDubbo("BLCSGetObjectTips", paramMap.get("menuid"), 
				paramMap.get("unicontact"), "1", paramMap.get("telnum"), 
				paramMap.get("operatorid"), paramMap.get("termid"),
				param, new String[]{"OBJID","OBJTYPE","TIPTYPE","OPERTYPE","TIPTEXT"});
		
			// 组装vector存入rw
			if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
			{
				CRSet crset = (CRSet) rw.getReturnObject();
				Vector<CPEntity> v = new Vector<CPEntity>();
				if (null != crset)
				{
					v.add(0, new CTagSet());
					v.add(1, crset);
				}
				rw.setReturnObject(v);
			}
			return rw;
		}
		return super.qryObjectTips(paramMap, prods);
	}
	
	/** 
     * 产品快速发布-查询产品包下子产品
     * 
     * @param msgHeader 报文头信息
     * @param nCode 产品包编码、模板ID
     * @param type 类型：2 产品包 3 模板
     * @param optType 操作类型
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: add zKF69263 2014/09/15 R003C14L09n01 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
     */
    public ReturnWrap qrySubProds(MsgHeaderPO msgHeader, String nCode, String type, String optType)
    {
        // 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("PTGetPackageOrTempletItems"))
        {
            try
            {
            	// 封装入参
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // 对象编码
                paramMap.put("nodeID", nCode);
                
                // 对type进行转换，2 产品包PCIntObjPack 3 模板PCIntObjTplt
                if ("2".equals(type))
                {
                	type = "PCIntObjPack";
                }
                else if ("3".equals(type))
                {
                	type = "PCIntObjTplt";
                }
                
                // 对象类型
                paramMap.put("objType", type);
                
                // 受理类型
                paramMap.put("recType", "ChangeProduct");
                
                // 渠道编码
                paramMap.put("channel", msgHeader.getChannelId());
                
                // 受理类型
                paramMap.put("opType", optType);
                
                // 出参转换 :可选产品最小数,可选产品最大数,
                String[][] objStr = {{"MIN","MAX","DATALIST"},{"minprod","maxprod","DATALIST"}};
                
                /*
                 * 设置CRSet出参顺序
                 * 产品包编码,产品编码,优惠编码,产品名称,选择类型 SeleType_Choice：可选 SeleType_Must：必选
                 * 是否有附加属性 0：无 1：有,接口业务类型 PCIntRelaNormal：普通业务 PCIntRelaRadio：业务切换
                 */
                String[] attrKey = {"C0","C1","C2","C3","C4","C5","C6"};
                
                // 调用ebus服务接口
                ReturnWrap rw = getIntMsgUtil().invokeDubbo("PTGetPackageOrTempletItems", msgHeader, paramMap,
                		objStr, attrKey);
                if (SSReturnCode.SUCCESS == rw.getStatus())
                {
                	Vector v = new Vector();
                	if (rw.getReturnObject() instanceof CTagSet)
                	{
                		v.add(rw.getReturnObject());
                		v.add(new CRSet());
                		rw.setReturnObject(v);
                	}
                }
                return rw;
            }
            catch (Exception e)
            {
                logger.error("查询产品包下子产品：", e);
                
                return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
            }
        }
        
        return super.qrySubProds(msgHeader, nCode, type, optType);
    }
    
    /** 
     * 产品快速发布-用户已订购产品信息查询
     * 
     * @param msgHeader 报文头信息
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: modify zKF69263 2014/09/16 R003C14L09n01 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
     */
    public ReturnWrap qryHasProds(MsgHeaderPO msgHeader)
    {
        // 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("PTQrySubsOrderAndIntMultiProduct"))
        {
            try
            {
                // 封装入参
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // 是否走原BLCSEIIntMultiProductQry逻辑标识
                paramMap.put("isIntMultiProdQry", "1");
                
                // 增值产品大类编码
                paramMap.put("prodClass", "");
                
                // 查询方式,固定传5
                paramMap.put("qryType", "5");
                
                // 套餐大类（套餐查询时用）
                paramMap.put("pkgType", "");
                
                // 是否输出产品包
                paramMap.put("isSolution", "");
                
                // 集团用户编号
                paramMap.put("grpSubsOid", "0");
                
                // 赠送方用户标识
                paramMap.put("donorSubsId", "");
                
                // 是否查询宽带用户个人增值产品信息。传""
                paramMap.put("isHaveBandProdID", "");
                
                // 客服IVR套餐使用查询用。传""
                paramMap.put("queryFlag", "");
                
                // 客服IVR套餐使用查询用。传""
                paramMap.put("pkgInfo", "");
                
                // 客服IVR套餐使用查询用。传""
                paramMap.put("exceptClass", "");
                
                // 客服IVR套餐使用查询用。传""
                paramMap.put("prodList", "");
                
                // 调用ebus服务接口
                return getIntMsgUtil().invokeDubbo("PTQrySubsOrderAndIntMultiProduct", msgHeader, paramMap, 
                    new String[]{"prodID", "prodName", "attr", "serviceRes", "applyDate", "createDate", "endDate", 
                        "status", "formNum", "productClass", "description", "donorSubsID", "rltRefID", 
                        "productClassName", "cancelDate", "pkgProdID", "outNcodeType", "privID", "NcodeID"});
            }
            catch (Exception e)
            {
                logger.error("查询用户已订购产品失败：", e);
                
                return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
            }
        }
        
        return super.qryHasProds(msgHeader);
    }
    
    /** 
     * 产品快速发布-查询产品附加属性
     * 
     * @param msgHeader 报文头信息
     * @param qryType 查询类型 0：NCODE 1：产品包下产品
     * @param nCode nCode
     * @param operType PCOpRec:开通  PCOpMod:修改 PCOpDel:关闭 
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: add zKF69263 2014/09/15 R003C14L09n01 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
     */
    public ReturnWrap qryAddAttr(MsgHeaderPO msgHeader, String qryType, String nCode, String operType)
    {
        // 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("PTGetAppendAttrByID"))
        {
            try
            {   
                // 封装入参
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // 用户号码
                paramMap.put("telnum", msgHeader.getTelNum());
                
                // 查询类型
                paramMap.put("qryType", qryType);
                
                // 用户编号
                paramMap.put("entityID", nCode);
                
                // 操作类型
                paramMap.put("opType", operType);
                
                /**
                 * 设置CRSet出参顺序
                 * 对象编码，附加属性编码，附加属性名称，附加属性类型，值类型 NUMBER:数字 STRING:字符串，
                 * 附加属性值最小长度，附加属性值最大长度，是否必须 0：不是 1：是，是否界面展现 0：不是 1：是，
                 * 属性值数量，附加属性分隔符，用户订购值，字典信息，对象类型:产品/优惠/服务
                 */ 
                String[] attrParam = {"objected","attrID","entityName","attrType","valueType","minLen",
                		"maxLen","isNeed","isShowInRec","valCount","sepSign","defaultValue","dictVal","objType"};
                
                // 调用ebus服务接口
                return getIntMsgUtil().invokeDubbo("PTGetAppendAttrByID", msgHeader, paramMap, attrParam);
                
            }
            catch (Exception e)
            {
                logger.error("查询产品附加属性失败：", e);
                
                return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
            }
        }
        
        return super.qryAddAttr(msgHeader, qryType, nCode, operType);
    }
    
    /** 
     * 产品快速发布-产品受理
     * 
     * @param msgHeader MsgHeaderPO
     * @param multiProdCommitPO MultiProdCommitPO
     * @return ReturnWrap
     * @see [类、类#方法、类#成员]
     * @remark: modify zKF69263 2014/09/24 R003C14L09n01 OR_huawei_201407_41 自助终端接入EBUS二阶段_产品快速发布
     */
    public ReturnWrap prodRec(MsgHeaderPO msgHeader, MultiProdCommitPO multiProdCommitPO)
    {
        // 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("BLCSMultiProductRec"))
        {
            try
            {   
                // 封装入参
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // 服务号码
                paramMap.put("servNum", multiProdCommitPO.getTelnum());
                
                // 是否校验资源
                paramMap.put("isChkRes", multiProdCommitPO.getIscheck());
                
                // 赠送号码
                paramMap.put("doneeNum", multiProdCommitPO.getDoneenum());
                
                // 算费标识，预留字段，暂不使用。传“”
                paramMap.put("isCalcFee", multiProdCommitPO.getIscalcfee());
                
                // 是否发送短信，0:不发送;1发送
                paramMap.put("isSendSms", multiProdCommitPO.getIssendsms());
                
                // 传“”
                paramMap.put("operSource", multiProdCommitPO.getOpersource());
                
                // 产品列表
                List<ProdCommitPO> prodCommitPOList = multiProdCommitPO.getProdCommitList();
                
                // 组装产品列表
                List<Map<String, Object>> recProdList = new ArrayList<Map<String, Object>>();
                
                // 循环产品列表
                for(ProdCommitPO prodCommitPO : prodCommitPOList)
                {
                    Map<String, Object> recProdMap = new HashMap<String, Object>();
                    
                    // 增值产品编码，对产品包下的子产品或者模板下的子产品，传产品编码；其它传NCODE
                    recProdMap.put("prodID", prodCommitPO.getNcode());
                    
                    // 生效方式，2:立即，4:次日，3:次月
                    recProdMap.put("effectType", prodCommitPO.getEffecttype());
                    
                    // 操作类型，PCOpRec:开通 PCOpMod:修改 PCOpDel:关闭 PCOpPau:暂停 PCOpRes:恢复
                    recProdMap.put("opType", prodCommitPO.getOpertype());
                    
                    // 附加属性(attrid1=attrvalue1=optype1#attrid2=attrvalue2=optype2……)
                    recProdMap.put("attrPara", prodCommitPO.getAttrparam());
                    
                    // 原增值产品编码(只有操作类型为修改时才有效)
                    recProdMap.put("oldProdID", prodCommitPO.getOldprodid());
                    
                    // 服务资源(resid1=restype1=optype1#resid2=restype2=optype2……)
                    recProdMap.put("resPara", prodCommitPO.getServiceres());

                    // 接口对应类型，对产品包下子产品或者模板下子产品受理的时候使用。 PCIntRelaNormal：普通业务 PCIntRelaRadio：业务切换
                    recProdMap.put("relaType", prodCommitPO.getInftype());
                    
                    // 是否按模板处理, 默认为0，0：不， 1：是
                    recProdMap.put("isTemplate", prodCommitPO.getTempletflag());
                    
                    // 指定生效时间
                    recProdMap.put("startDate", prodCommitPO.getStartdate());
                    
                    // 指定失效时间
                    recProdMap.put("endDate", prodCommitPO.getEnddate());
                    
                    // 产品包编码
                    recProdMap.put("packageID", prodCommitPO.getPkgid());
                    
                    // 对象类型
                    recProdMap.put("objType", prodCommitPO.getObjtype());
                    
                    // 优惠编码
                    recProdMap.put("privID", prodCommitPO.getPrivid());
                    
                    // 模板编码
                    recProdMap.put("templateID", prodCommitPO.getTempletid());
                    
                    // 加入组装产品列表中
                    recProdList.add(recProdMap);
                }
                
                // 产品列表
                paramMap.put("recProdList", recProdList);
                
                // 调用ebus服务接口
                return getIntMsgUtil().invokeDubbo("BLCSMultiProductRec", msgHeader, paramMap,
                    new String[][]{{"recOid", "orderID"}, {"recOid", "orderID"}});
                
            }
            catch (Exception e)
            {
                logger.error("产品受理失败：", e);
                
                return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
            }
        }
        
        return super.prodRec(msgHeader, multiProdCommitPO);
    }
    
    /**
     * 受理历史查询
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by wWX217192 OR_huawei_201407_39 自助终端接入EBUS二阶段_受理历史查询 
     */
    public ReturnWrap qryAllServiceHistory(Map map)
    {
    	// 湖北统一接口平台转EBUS开关开启
    	if(IntMsgUtil.isTransEBUS("BLCSQryReceptionSimple"))
    	{
    		try
    		{
    			// 封装入参
	    		Map<String, Object> paramMap = new HashMap<String, Object>();
	    		
	    		// 手机号码
	    		paramMap.put("telNum", map.get("servnumber"));
	    		
	    		paramMap.put("outOperid", "1");
	    		
	    		// 查询开始时间
	    		paramMap.put("startDate", map.get("startDate"));
	    		
	    		// 查询结束时间
	    		paramMap.put("endDate", map.get("endDate"));
	    		
	    		paramMap.put("operType", "1");
	    		 
	    		// 封装多个参数值，生成CRSet返回到前台
	    		String[] arrAttrsKey = {"recdate", "contacttype", "recopid", "recdefname"};
	    		
	    		// 其中关于接口入参qryAllFlag，在自助终端侧不传值的情况下，后台默认为0，遵循统一接口平台的处理，此处省去qryAllFlag的值
	    		return getIntMsgUtil().invokeDubbo("BLCSQryReceptionSimple", 
	    				(String) map.get("curMenuId"), 
	    				(String) map.get("touchoid"), "1",
	    				(String) map.get("servnumber"), 
	    				(String) map.get("curOper"), 
	    				(String) map.get("atsvNum"), paramMap, null, arrAttrsKey);
    		}
    		catch(Exception e)
    		{
    			ReturnWrap rw = new ReturnWrap();
    			rw.setStatus(0);
                rw.setReturnMsg("");
                
                return rw;
    		}
    	}
    	
    	return super.qryAllServiceHistory(map);
    }

    /**
     * 湖北专用，查询业务受理状态
     * 
     * @param msgHeader 消息头
     * @param nCode
     * @param operType，操作类型，开通或者取消
     * @return
     * @se
     * @remark create by jWX216858 2014-11-12 OR_huawei_201410_872_HUB_开机早知道流水线部分EBUS改造
     */
	@Override
	public ReturnWrap qryRecStatusHub(MsgHeaderPO msgHeader, String nCode, String operType) 
	{
		try
		{
			// 湖北转EBUS开关开启
			if (IntMsgUtil.isTransEBUS("IncProductSrv2"))
        	{
        		Map<String, String> paramMap = new HashMap<String, String>();
        		
        		// 手机号码
        		paramMap.put("TELNUM", msgHeader.getTelNum());
        		
        		// 操作类型ADD 增加 DEL 删除 MOD 修改 QRY 查询
        		paramMap.put("STYPE", "QRY");
        		
        		// 固定传SUBSNCODEEXIST
        		paramMap.put("NCODE", "SUBSNCODEEXIST");
        		
        		// 产品对应的ncode
        		paramMap.put("NCODELIST", nCode);
        		
        		// 调用dubbo服务
        		return getIntMsgUtil().invokeDubbo("IncProductSrv2", msgHeader, paramMap);
        	}
		}
        catch (Exception e)
        {
            logger.error("查询业务受理状态失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
		return super.qryRecStatusHub(msgHeader, nCode, operType);
	}

	/**
     * 产品受理通用接口
     * <功能详细描述>
     * @param msgHeader 消息头信息
     * @param nCode ncode
     * @param operType 操作类型ADD 增加 DEL 删除 MOD 修改 QRY 查询
     * @param effectType 生效方式
     * @param param 附加属性传 （action类传过的都是空）
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by jWX216858 2014-11-12 OR_huawei_201410_872_HUB_开机早知道流水线部分EBUS改造
     */
	@Override
	public ReturnWrap recCommonServ(MsgHeaderPO msgHeader, String nCode, String operType, String effectType, String param)
	{
		try
		{
			// 湖北转EBUS开关开启
			if (IntMsgUtil.isTransEBUS("IncProductSrv2"))
        	{
        		Map<String, String> paramMap = new HashMap<String, String>();
        		
        		// 手机号码
        		paramMap.put("TELNUM", msgHeader.getTelNum());
        		
        		// 操作类型ADD 增加 DEL 删除 MOD 修改 QRY 查询
        		paramMap.put("STYPE", operType);
        		
        		// 固定传SUBSNCODEEXIST
        		paramMap.put("NCODE", nCode);
        		
        		// 生效方式
        		paramMap.put("EFFECT_TYPE", effectType);
        		
        		// 附加属性串
        		paramMap.put("PARM", param);
        		
        		// 调用dubbo服务
        		ReturnWrap rw = getIntMsgUtil().invokeDubbo("IncProductSrv2", msgHeader, paramMap);

        		// 解析返回信息，进行再次封装
        		if (SSReturnCode.SUCCESS == rw.getStatus())
        		{
        			CTagSet ctagSet = new CTagSet();
        			if (rw.getReturnObject() instanceof Vector)
        			{
        				Vector v = (Vector) rw.getReturnObject();
        				CTagSet ctag = (CTagSet) v.get(0);
        				ctagSet.SetValue("formnum", ctag.GetValue("FORMNUMBER")); // 业务受理流水
        			}
        			if (rw.getReturnObject() instanceof CTagSet)
        			{
        				CTagSet ctag = (CTagSet)rw.getReturnObject();
        				ctagSet.SetValue("formnum", ctag.GetValue("FORMNUMBER")); // 业务受理流水
        			}
        			rw.setReturnObject(ctagSet);
        		}
        		return rw;
        	}
		}
        catch (Exception e)
        {
            logger.error("查询业务受理状态失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
		return super.recCommonServ(msgHeader, nCode, operType, effectType, param);
	}
	
	/**
     * 查询客户应缴总金额 湖北，直接调用一级boss接口
     * @param msgHeader
     * @param orgid
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-3-27 18:01:22 OR_NX_201501_1030_关于跨区服务业务支撑系统改造的通知
     */
    public ReturnWrap qryPayAmount(MsgHeaderPO msgHeader, String orgid)
    {
        try
        {
        	//创建请求报文
        	Document doc = DocumentHelper.createDocument();
        	
        	//请求报文根节点
        	Element eleRoot = doc.addElement("QryPayReq");
        	
        	//接口业务id
        	DocumentUtil.addSubElementToEle(eleRoot, "BIZID", "KQFWPayFeeQry");
        	
        	//操作员编号
        	DocumentUtil.addSubElementToEle(eleRoot, "OperatorId", msgHeader.getOperId());
        	
        	//组织结构编号
        	DocumentUtil.addSubElementToEle(eleRoot, "OrgID", orgid);
        	
        	//表示类型，01手机号码
        	DocumentUtil.addSubElementToEle(eleRoot, "IDType", "01");
        	
        	//手机号码
        	DocumentUtil.addSubElementToEle(eleRoot, "IDValue", msgHeader.getTelNum());
        	
        	//一级boss接口调用url
        	String interBOSSURL = CommonUtil.getParamValue(Constants.NONLOCALCHARGE_BOSSURL);
        	
            //直接调用一级boss接口 查询客户应缴金额
        	String resXML = getIntMsgUtil().httpInvoke(interBOSSURL, doc, "UTF-8");
        	
        	//封装返回对象
        	ReturnWrap retData = new ReturnWrap();
        	
        	//解析返回报文
        	Document resDoc = DocumentHelper.parseText(resXML);
    		Element root = resDoc.getRootElement();
    		
    		//若返回码为0000，则表示接口调用成功
    		if(SSReturnCode.INTERBOSS_SUCCESS.equals(root.element("BizOrderResult").getTextTrim()))
    		{
    			//调用状态
    			retData.setStatus(SSReturnCode.SUCCESS);
    			
    			//封装CTagSet
    			CTagSet tagSet = new CTagSet();
    			
    			//省份编码
    			tagSet.SetValue("ProvinceCode", root.element("HSNDUNS").getTextTrim());
    			
        		//解析封装应缴费用等信息
    			Element qryPayInfo = root.element("QryPayInfo");
    			
    			//客户名称
    			tagSet.SetValue("CustName", qryPayInfo.element("CustName").getTextTrim());
    			
    			//应缴费用
    			tagSet.SetValue("PayAmount", qryPayInfo.element("PayAmount").getTextTrim());
    			
    			//客户预存金额
    			tagSet.SetValue("Balance", qryPayInfo.element("Balance").getTextTrim());

    			retData.setReturnObject(tagSet);
    		}
    		else
    		{
    			retData.setStatus(SSReturnCode.ERROR);
    			
        		//错误信息
        		retData.setReturnMsg(root.element("ResultDesc").getTextTrim());
    		}

    		return retData;
        }
        catch (Exception e)
        {
            logger.error("查询客户应缴总金额异常!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"查询客户应缴总金额异常！");
        }
        
    }
    
    /**
     * 跨省缴费提交
     * <功能详细描述>
     * @param msgHeader
     * @param seq 规则操作流水号
     * @param actualPayAmount 实际缴费金额（厘）
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-4-2 09:58:49 OR_NX_201501_1030_关于跨区服务业务支撑系统改造的通知
     */
    public ReturnWrap nonlocalCharge(MsgHeaderPO msgHeader, String seq, String actualPayAmount, String orgid)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eleRoot = msgBody.addElement("PaymentReq");

            // 标识类型 01:手机号
            DocumentUtil.addSubElementToEle(eleRoot, "IDType", "01");
            
            // 手机号
            DocumentUtil.addSubElementToEle(eleRoot, "IDValue", msgHeader.getTelNum());
            
            // 实际缴费金额（厘
            DocumentUtil.addSubElementToEle(eleRoot, "PayAmount", actualPayAmount);
            
            // 手续费
            DocumentUtil.addSubElementToEle(eleRoot, "HandCharge", "0");
            
            // 规则操作流水号
            DocumentUtil.addSubElementToEle(eleRoot, "Seq", seq);
            
        	//组织结构编号
        	DocumentUtil.addSubElementToEle(eleRoot, "OrgID", orgid);
        	
        	//操作员编号
        	DocumentUtil.addSubElementToEle(eleRoot, "OperatorId", msgHeader.getOperId());
            
            // BIZID 接口ID(类似opcode)
            DocumentUtil.addSubElementToEle(eleRoot, "BIZID", "KQFWPayFee");
            
        	//一级boss接口调用url
        	String interBOSSURL = CommonUtil.getParamValue(Constants.NONLOCALCHARGE_BOSSURL);
            
            // 调用一级boss接口，进行交费
            String resXML = getIntMsgUtil().httpInvoke(interBOSSURL, msgBody, "UTF-8");
            
        	//封装返回对象
        	ReturnWrap retData = new ReturnWrap();
        	
        	//解析返回报文
        	Document resDoc = DocumentHelper.parseText(resXML);
    		Element resRoot = resDoc.getRootElement();
        	
    		//接口调用成功
        	if(SSReturnCode.INTERBOSS_SUCCESS.equals(resRoot.element("BizOrderResult").getTextTrim()))
        	{
        		retData.setStatus(SSReturnCode.SUCCESS);
        		retData.setReturnMsg("交费成功");
        	}
        	
        	//接口调用失败
        	else
        	{
        		retData.setStatus(SSReturnCode.ERROR);
        		retData.setReturnMsg(resRoot.element("ResultDesc").getTextTrim());
        	}
        	
        	return retData;
        }
        catch (Exception e)
        {
            logger.error("异地缴费异常!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"异地缴费异常！");
        }
    }
    
    /**
	 * 湖北有价卡购买
	 * @param valueCardVO
	 * @param paramMap
	 * @return
	 * @remark create by wWX217192 2015-05-13 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
	 */
	public ReturnWrap getValueCards(MsgHeaderPO header, Map<String, String> inParam)
	{
		try
		{
			return getIntMsgUtil().invokeDubbo("BLCSElecCardSale", header, inParam, null, 
            		new String[]{"cardNo", "cardPwd", "elecCardCntTotal", "cardDate", "CardType", "CardBusiPro"});
		}
		catch(Exception e)
		{
			logger.error("有价卡购买失败！", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "有价卡购买失败！");
		}
	}
    
    /**
     * <有价卡校验>
     * <功能详细描述>
     * @param msgHeader 消息头
     * @param paramMap 消息体
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-05-11 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     */
    public ReturnWrap authValueCard(MsgHeaderPO msgHeader, Map<String, String> paramMap)
    {
        try
        {
            return getIntMsgUtil().invokeDubbo("BLCSChkIfEvcCard", msgHeader, paramMap);
        }
        catch (Exception e)
        {
            logger.error("有价卡类型校验失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"有价卡类型校验失败");
        }
    }
    
    /**
     * <有价卡充值>
     * <功能详细描述>
     * @param msgHeader 消息头
     * @param valueCardPwd 充值卡密码
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-05-11 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     * @remark modify by hWX5316476 2015-6-10 OR_SD_201505_1022_山东电子充值卡改造需求_自助终端改造
     */
    public ReturnWrap valueCardCharge(MsgHeaderPO msgHeader, String valueCardPwd)
    {
        try
        {
            // 入参
            Map<String, String> map = new HashMap<String, String>();
            
            //电子有价卡充值渠道
            map.put("channelType", msgHeader.getChannelId());
            
            //被充值号码
            map.put("calledIdValue", msgHeader.getRouteValue());
            
            //电子充值卡密码
            map.put("cardPassWord", valueCardPwd);
            
            //充值接入省代码，湖北：270
            map.put("calllingProv", Constants.VALUECARD_HUB_PROVCODE);
            
            //主叫手机号码 可不传
            map.put("callingIdValue", "");
            
            //营业厅代码，可不传
            map.put("actionID", "");
            
            //操作员编码
            map.put("actionUserID", msgHeader.getOperId());
            
            return getIntMsgUtil().invokeDubbo("BLCSElecCardCharge", msgHeader, map);
        }
        catch (Exception e)
        {
            logger.error("有价卡充值失败：", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"有价卡充值失败");
        }
    }
	/**
	 * 密码重置前校验身份证号
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 * @remark create by hWX5316476 2014/04/20 OR_huawei_201404_374 自助终端全流程接入EBUS改造_重置密码、修改密码
	 */
	@Override
	public ReturnWrap checkIDCard(Map map)
	{
		// 湖北统一接口平台转EBUS开关开启
		if(IntMsgUtil.isTransEBUS("PTIDCardCheck"))
		{
			try
			{
				// 入参　
				Map<String,String> inParamMap = new HashMap<String,String>();

				// 证件id
				inParamMap.put("certID", (String)map.get("IDCard"));

				// 证件类型
				inParamMap.put("certType","IdCard");

				// 用户号码(手机号码)
				inParamMap.put("callNum", (String)map.get("telnum"));

				return getIntMsgUtil().invokeDubbo("PTIDCardCheck", (String)map.get("menuid"),
						(String)map.get("touchoid"), "1", (String)map.get("telnum"), (String)map.get("operid"),
						(String)map.get("termid"), inParamMap);
			}
			catch (Exception e)
			{
				logger.error("密码重置前校验身份证号失败：", e);

				return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "密码修改失败!");
			}
		}

		return super.checkIDCard(map);
	}

}
