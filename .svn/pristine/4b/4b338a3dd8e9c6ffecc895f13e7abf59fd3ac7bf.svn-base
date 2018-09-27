/**
 * 
 */
package com.gmcc.boss.selfsvc.call;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import net.sf.json.JSONObject;

import com.gmcc.boss.common.base.CPEntity;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.quickpublish.model.MultiProdCommitPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.valueCard.model.ValueCardVO;

/**
 * @author zKF69263
 *
 */
public class NewSelfSvcDubboCallTest extends NewSelfSvcCallTest {

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
	        ReturnWrap rw = new ReturnWrap();
	        rw.setStatus(1);
	        rw.setReturnMsg("服务密码验证成功!");
	        return rw;
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
    public ReturnWrap getUserInfoHub(Map<String, String> map)
    {
    	if(IntMsgUtil.isTransEBUS("sMQryUserInfoHub"))
    	{
	    	// 出参为键值对，放在JSONObj
	    	Map<String,Object> outParamMap = new HashMap<String,Object>();
	
	    	// 拼装出参
	    	// 品牌
	    	outParamMap.put("region", "711");
	    	outParamMap.put("brand", "BrandSzx");
	    	outParamMap.put("subsid", "7110000000814");
	    	outParamMap.put("prodid", "pp.gt.st");
	    	outParamMap.put("status", "US10");
	    	outParamMap.put("flag", "2");
	    	outParamMap.put("starlevel", "1");
	    	outParamMap.put("regionname", "鄂州");
	    	outParamMap.put("nettype", "GSM");
	    	outParamMap.put("stopname", "");
	    	outParamMap.put("vipType", "VC160B");
	    	outParamMap.put("sexname", "男");
	    	outParamMap.put("createdate", "2013-05-20");
	    	outParamMap.put("regname", "济南历下区花园路营业厅");
	    	outParamMap.put("subname", "大大");
	    	outParamMap.put("prodname", "pp.gt包");
	    	outParamMap.put("subage", "1997-09-20");
	    	outParamMap.put("smallregion", "711");
//	    	outParamMap.put("acctID", "7110000000197");//是托收用户的13607232776
	    	outParamMap.put("acctID", "7115057586341");// 不是托收用户的额13451008857
	    	
	          // add begin hWX5316476 2014-8-18 OR_huawei_201408_680 [携号转网]-自助终端优化需求(切换为EBUS协议)
            // 湖北入网类型sbsnTransTelOut：跨运营商携出；sbsnTransTelIn：跨运营商携入
            outParamMap.put("signType", "sbsnTransTelIn");
            // add end hWX5316476 2014-8-18 OR_huawei_201408_680 [携号转网]-自助终端优化需求(切换为EBUS协议)
	    	
	    	JSONObject jsonObj = JSONObject.fromObject(outParamMap);
	    	System.out.println(jsonObj.toString());
	    	ReturnWrap rw = null;
	    	rw =  getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
	    	rw.setReturnMsg("信息查询成功");
	    	return rw;
    	}
    	return super.getUserInfoHub(map);
    }
    
	/**
     * 停开机业务处理
     */
    public ReturnWrap stopOpenSubs(Map map) 
    {
    	// 湖北统一接口平台转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("BLCSStopOpenSubs")) 
		{
			return getIntMsgUtil().parseJsonResponse("{recFormNum:'20140115000001'}", null, null);
		}
		
		return super.stopOpenSubs(map);
    }
    
    /**
     * 业务查询统一接口 梦网业务查询
     */
    public ReturnWrap queryService(Map map) 
    {
    	// 湖北统一接口平台转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("BLCSIntQuerySubsAllServ")) 
		{
			String response = "[{objType:'22',spID:'000001',spName:'中国移动',spBizID:'FetionBase',spBizName:'飞信功能',spBizType:'',billingType:'',price:'',domain:'',startDate:'2010-06-08 08:22:18',endDate:'',seqNum:'',charge:'',priceDesc:'免费'}"
				+ ",{objType:'21',spID:'801174',spName:'中国移动',spBizID:'125820',spBizName:'生活播报',spBizType:'52',billingType:'免费',price:'0',domain:'12580',startDate:'2010-09-20 13:52:13',endDate:'',seqNum:'',charge:'',priceDesc:'0.00免费'}"
				+ ",{objType:'22',spID:'000001',spName:'中国移动',spBizID:'MMAIL_F',spBizName:'139邮箱免费版',spBizType:'',billingType:'',price:'',domain:'',startDate:'2010-09-14 17:46:47',endDate:'',seqNum:'',charge:'',priceDesc:'免费'}]";
			
			return getIntMsgUtil().parseJsonResponse(response, null, null);
		}
		
		return super.queryService(map);
    }
    
    /**
     * 业务统一退订接口
     */
    public ReturnWrap cancelService(Map map) 
    {
    	// 湖北统一接口平台转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("BLCSIntServUniteCancel")) 
		{
			return getIntMsgUtil().parseJsonResponse("{recOid:'20140115000002',formNum:'20140115000003'}", null, null);
		}
		
		return super.cancelService(map);
    }
    
    /**
     * SP业务订购
     */
    public ReturnWrap orderSPService(Map map) {
        
    	// 湖北统一接口平台转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("BLCSChangeSubsMonServ")) 
		{
			return getIntMsgUtil().parseJsonResponse("{orderResult:'0',effetiTime:'2014-04-15',orderFlag:'1',formNumber:'20140115000001'}", 
				new String[][]{{"orderResult", "formNumber"},{"orderresult", "formnum"}}, null);
		}
		
		return super.cancelService(map);
    }
    
    /**
     * 密码修改
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2014/04/18 OR_huawei_201404_374 自助终端全流程接入EBUS改造_重置密码、修改密码
     */
	@Override
	public ReturnWrap recChangePassword(Map map)
    {
		// 湖北统一接口平台转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("BLCSChangeSubsPassWord")) 
		{
			ReturnWrap rw = new ReturnWrap();
	        rw.setStatus(1);
	        return rw;
		}
		return super.recChangePassword(map);
    }
	
	/**
     * 密码重置校验身份证号
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
	@Override
    public ReturnWrap checkIDCard(Map map)
    {
		// 湖北统一接口平台转EBUS开关开启
        if(IntMsgUtil.isTransEBUS("PTIDCardCheck"))
        {
    		return getIntMsgUtil().parseJsonResponse(null, null, null);
        }
        
        return super.checkIDCard(map);
    }
	

	
	/**
     * 向用户发送随机密码短信
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     * @remark 
     */
	@Override
    public ReturnWrap sendSmsHub(Map map)
    {
        // 湖北统一接口平台转EBUS开关开启
        if(IntMsgUtil.isTransEBUS("PTSendSmsInfo"))
        {
        	 ReturnWrap rw = new ReturnWrap();
             rw.setStatus(SSReturnCode.SUCCESS);

             return rw;
        }
        
        return super.sendSmsHub(map);
    }
    
    /**
     * 向用户发送随机密码短信
     * <功能详细描述>
     * @param map
     * @return ReturnWrap 
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ReturnWrap sendSMS(Map map)
    {
    	// 湖北统一接口平台转EBUS开关开启
        if(IntMsgUtil.isTransEBUS("PTSendSmsInfo"))
        {
        	ReturnWrap rw = new ReturnWrap();
            rw.setStatus(SSReturnCode.SUCCESS);

            return rw;
        }
        
        return super.sendSMS(map);
    }
    
	/**
     * 密码重置
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
	@Override
    public ReturnWrap resetPassword(Map map)
    {
		// 湖北统一接口平台转EBUS开关开启
        if(IntMsgUtil.isTransEBUS("BLCSSetInitPwd"))
        {
        	ReturnWrap rw = new ReturnWrap();
            rw.setStatus(SSReturnCode.SUCCESS);

            return rw;
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
            try{ 
            	// 出参为键值对，放在JSONObj
            	Map<String,Object> outParamMap = new HashMap<String,Object>();

            	// 拼装出参
            	outParamMap.put("statusName", "正在使用");
            	
            	JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        		
            	// 出参键值对 取值键名与输出的键名对应关系数组
            	String[][] outParamKeyDefine = {{"statusName"},{"state"}};
            	
            	return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),outParamKeyDefine,null);
            }
            catch (Exception e)
            {
                ReturnWrap rw = new ReturnWrap();
                rw.setStatus(0);
                rw.setReturnMsg("当前状态查询失败!");
                
                return rw;
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
        	ReturnWrap rw = new ReturnWrap();
            rw.setStatus(SSReturnCode.SUCCESS);

            return rw;
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
    	if(IntMsgUtil.isTransEBUS("Atsv_Qry_UserInfo_Hub"))
    	{
    		try 
    		{
    			// 出参为键值对，放在JSONObj
            	Map<String,Object> outParamMap = new HashMap<String,Object>();

            	// 拼装出参
            	outParamMap.put("subName", "高群");
            	outParamMap.put("region", "711");
            	outParamMap.put("regionName", "");
            	outParamMap.put("productID", "pp.gt.ygtch.634");
            	outParamMap.put("productName", "全球通");
            	outParamMap.put("productGroup", "BrandMzone");
            	outParamMap.put("vipType", "VC0000");
            	outParamMap.put("netType", "");
            	outParamMap.put("contactID", "1");
            	outParamMap.put("status", "US22");
            	outParamMap.put("subAge", "2");
            	outParamMap.put("subScore", "600");
            	outParamMap.put("smallRegion", "7");
            	
            	JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        		System.out.println(jsonObj.toString());
        		
        		// 出参键值对 取值键名与输出的键名对应关系数组
        		String[][] outParamKeyDefine = {{"subName", "region", "regionName", "productID", "productName", "productGroup", "vipType", "netType", "contactID", "status", "subAge", "subScore", "smallRegion"},
						{"subname", "region", "regionname", "productid", "productname", "productgroup", "viptype","nettype", "contactid", "status", "subage", "subscore", "smallregion"}};

        		return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),outParamKeyDefine,null);
			} 
    		catch (Exception e) 
    		{
                ReturnWrap rw = new ReturnWrap();
                rw.setStatus(0);
                rw.setReturnMsg("使用手机号码、服务密码进行身份认证异常！");
                return rw;
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
     */
	/*@Override
    public ReturnWrap getUserInfo(Map map)
    {
		if(IntMsgUtil.isTransEBUS("Atsv_Qry_UserInfo_Hub"))
		{
			try 
    		{
    			// 出参为键值对，放在JSONObj
            	Map<String,Object> outParamMap = new HashMap<String,Object>();

            	// 拼装出参
            	outParamMap.put("subName", "高群");
            	outParamMap.put("region", "711");
            	outParamMap.put("regionName", "");
            	outParamMap.put("productID", "pp.gt.ygtch.634");
            	outParamMap.put("productName", "全球通");
            	outParamMap.put("productGroup", "BrandMzone");
            	outParamMap.put("vipType", "VC0000");
            	outParamMap.put("netType", "");
            	outParamMap.put("contactID", "1");
            	outParamMap.put("status", "US22");
            	outParamMap.put("subAge", "2011-05-08");
            	outParamMap.put("subScore", "600");
            	outParamMap.put("smallRegion", "7");
            	
            	JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        		System.out.println(jsonObj.toString());
        		
        		// 出参键值对 取值键名与输出的键名对应关系数组
        		String[][] outParamKeyDefine = {{"subName", "region", "regionName", "productID", "productName", "productGroup", "vipType", "netType", "contactID", "status", "subAge", "subScore", "smallRegion"},
						{"subname", "region", "regionname", "productid", "productname", "productgroup", "viptype","nettype", "contactid", "status", "subage", "subscore", "smallregion"}};

        		return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),outParamKeyDefine,null);
			} 
    		catch (Exception e) 
    		{
                ReturnWrap rw = new ReturnWrap();
                rw.setStatus(0);
                rw.setReturnMsg("使用手机号码、服务密码进行身份认证异常！");
                return rw;
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
        	ReturnWrap rw = new ReturnWrap();
            rw.setStatus(SSReturnCode.SUCCESS);

            return rw;
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
     * @remark create hWX5316476 2014/04/21 OR_huawei_201404_375 自助终端全流程接入EBUS改造_充值交费 自选套餐
     */
    @Override
    public ReturnWrap queryDetailedRecords2012(MsgHeaderPO msgHeader, String month,
        String cdrType, String feeType)
    {
    	// 湖北统一接口平台转EBUS开关开启
    	if (IntMsgUtil.isTransEBUS("qryClearList"))
    	{
	        // 出参为键值对，放在JSONObj
        	Map<String,Object> outParamMap = new HashMap<String,Object>();
        	String billaccount = "";
        	String billitem = "";
	        // 全部费用
	        if ("ALL".equals(feeType)) 
	        {
	            if (Constants.CDRTYPE_FIXFEE.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "201201@_@01-01 -- 01-31@_@98新商旅套餐@_@98|"
                    + "201201@_@01-01 -- 01-31@_@98新商旅套餐@_@98|201401@_@01-01 -- 01-31@_@98新商旅套餐@_@98";
	            	
	            } 
	            else if (Constants.CDRTYPE_GSM.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2016-08-01 21:56:04@_@武汉@_@主叫@_@66174709@_@60@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
	                        + "2011-08-02 21:56:04@_@武汉@_@主叫@_@05387763965@_@610@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
	                        + "2011-08-02 21:56:04@_@武汉@_@主叫@_@66174709@_@132@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
	                        + "2011-08-03 21:56:04@_@武汉@_@主叫@_@66174709@_@45@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
	                        + "2011-08-03 21:56:04@_@武汉@_@主叫@_@66174709@_@390@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
	                        + "2011-08-03 21:56:04@_@武汉@_@主叫@_@66174709@_@88@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
	                        + "2011-08-26 21:56:04@_@武汉@_@主叫@_@66174709@_@269@_@国际长途@_@@_@34.40|"
	                        + "2011-08-27 21:56:04@_@武汉@_@主叫@_@66174709@_@269@_@国际长途@_@@_@5.07";
	            }
	            else if (Constants.CDRTYPE_SMS.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@内地@_@10659001@_@发送@_@SP彩信@_@体坛周报@_@@_@0.30|"
                        + "2011-08-03 21:56:04@_@内地@_@10659001@_@发送@_@SP彩信@_@**@_@@_@0.10|"
                        + "2011-08-04 21:56:04@_@内地@_@139********@_@接收@_@短信@_@@_@武汉短信包@_@0.00|"
                        + "2011-08-04 21:56:04@_@港澳台@_@139********@_@发送@_@彩信@_@@_@@_@0.10|"
                        + "2011-08-04 21:56:04@_@美国ATAT@_@139********@_@发送@_@彩信@_@@_@@_@0.70";
	            }
	            else if (Constants.CDRTYPE_GPRSWLAN.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@北京@_@WLAN@_@12650@_@2018|"
	                        + "2011-08-02 21:56:04@_@北京@_@BLACKBERRY@_@150@_@20180|"
	                        + "2011-08-02 21:56:04@_@美国ATAT@_@CMNET@_@800@_@7|"
	                        + "2011-08-03 21:56:04@_@武汉@_@WLAN@_@800@_@7|"
	                        + "2011-08-03 21:56:04@_@武汉@_@WLAN@_@800@_@1256|";
	            } 
	            else if (Constants.CDRTYPE_DISCOUNT.equals(cdrType)) 
	            {
	                billaccount = "4";
	                billitem = "2011-08-02 21:56:04@_@总账优惠@_@-76.00|2011-08-03 21:56:04@_@总账优惠@_@-76.00|"
	                        + "2011-08-02 21:56:04@_@总账优惠@_@-76.00|2011-08-01 21:56:04@_@总账优惠@_@-76.00";
	            } 
	            else if (Constants.CDRTYPE_INFOFEE.equals(cdrType)) 
	            {
	                billaccount = "5";
	                billitem = "2011-08-01 21:56:04@_@彩信@_@超级贺卡@_@10658899@_@新浪@_@801005@_@点播@_@1.00|"
	                        + "2011-08-02 21:56:04@_@彩信@_@超级贺卡@_@10658899@_@新浪@_@801005@_@点播@_@1.00|"
	                        + "2011-08-02 21:56:04@_@彩信@_@超级贺卡@_@10658899@_@新浪@_@801005@_@点播@_@1.00|";
	            } 
	            else if (Constants.CDRTYPE_ISMG.equals(cdrType))
	            {
	                billaccount = "5";
	                billitem = "2011-08-01 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@2|"
	                        + "2011-08-02 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@1|2011-08-02 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@0|"
	                        + "2011-08-02 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@1|2011-08-03 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@2";
	            } 
	            else if (Constants.CDRTYPE_OTHERFEE.equals(cdrType))
	            {
	                billaccount = "";
	                billitem = "2011-08-05 21:56:04@_@违约金@_@20.00|2011-08-05 21:56:04@_@协议补收费@_@15.00|"
	                        + "2011-08-05 21:56:04@_@租机费@_@25.00";
	            }
	        }
	        // 非零费用
	        else if ("1".equals(feeType)) 
	        {
	            if (Constants.CDRTYPE_FIXFEE.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "201201@_@01-01 -- 01-31@_@98新商旅套餐@_@98|"
	                        + "201201@_@01-01 -- 01-31@_@98新商旅套餐@_@98|201205@_@01-01 -- 01-31@_@98新商旅套餐@_@98";
	            } 
	            else if (Constants.CDRTYPE_GSM.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-26 21:56:04@_@武汉@_@主叫@_@66174709@_@269@_@国际长途@_@@_@34.40|"
	                        + "2011-08-27 21:56:04@_@武汉@_@主叫@_@66174709@_@269@_@国际长途@_@@_@5.07";
	            } 
	            else if (Constants.CDRTYPE_SMS.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@内地@_@10659001@_@发送@_@SP彩信@_@体坛周报@_@@_@0.30|"
	                        + "2011-08-03 21:56:04@_@内地@_@10659001@_@发送@_@SP彩信@_@**@_@@_@0.10|"
	                        + "2011-08-04 21:56:04@_@港澳台@_@139********@_@发送@_@彩信@_@@_@@_@0.10|"
	                        + "2011-08-04 21:56:04@_@美国ATAT@_@139********@_@发送@_@彩信@_@@_@@_@0.70";
	            } 
	            else if (Constants.CDRTYPE_GPRSWLAN.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@北京@_@WLAN@_@12650@_@2018|"
	                        + "2011-08-02 21:56:04@_@北京@_@BLACKBERRY@_@150@_@20180|"
	                        + "2011-08-02 21:56:04@_@美国ATAT@_@CMNET@_@800@_@7|"
	                        + "2011-08-03 21:56:04@_@武汉@_@WLAN@_@800@_@7|"
	                        + "2011-08-03 21:56:04@_@武汉@_@WLAN@_@800@_@1256|";
	            } 
	            else if (Constants.CDRTYPE_DISCOUNT.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-02 21:56:04@_@总账优惠@_@-76.00|2011-08-03 21:56:04@_@总账优惠@_@-76.00|"
	                        + "2011-08-02 21:56:04@_@总账优惠@_@-76.00|2011-08-01 21:56:04@_@总账优惠@_@-76.00";
	            } 
	            else if (Constants.CDRTYPE_INFOFEE.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@彩信@_@超级贺卡@_@10658899@_@新浪@_@801005@_@点播@_@1.00|"
	                        + "2011-08-02 21:56:04@_@彩信@_@超级贺卡@_@10658899@_@新浪@_@801005@_@点播@_@1.00|"
	                        + "2011-08-02 21:56:04@_@彩信@_@超级贺卡@_@10658899@_@新浪@_@801005@_@点播@_@1.00|";
	            } 
	            else if (Constants.CDRTYPE_ISMG.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@2|"
	                        + "2011-08-02 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@1|"
	                        + "2011-08-02 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@1|2011-08-03 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@2";
	                
	            } 
	            else if (Constants.CDRTYPE_OTHERFEE.equals(cdrType)) 
	            {
	                ReturnWrap rw = new ReturnWrap();
	                rw.setReturnObject(new CTagSet());
	                rw.setStatus(SSReturnCode.SUCCESS);
	                rw.setReturnMsg("No information!");
	                return rw;
	            }
	        }
	        // 零费用
	        else 
	        {
	            if (Constants.CDRTYPE_FIXFEE.equals(cdrType)) 
	            {
	            	ReturnWrap rw = new ReturnWrap();
	            	rw.setReturnObject(new CTagSet());
	                rw.setStatus(SSReturnCode.SUCCESS);
	                rw.setReturnMsg("No information!");
	                return rw;
	            } 
	            else if (Constants.CDRTYPE_GSM.equals(cdrType))
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@武汉@_@主叫@_@66174709@_@60@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
	                        + "2011-08-02 21:56:04@_@武汉@_@主叫@_@66174709@_@89@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
	                        + "2011-08-02 21:56:04@_@武汉@_@主叫@_@66174709@_@132@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
	                        + "2011-08-03 21:56:04@_@武汉@_@主叫@_@66174709@_@45@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
	                        + "2011-08-03 21:56:04@_@武汉@_@主叫@_@66174709@_@390@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|"
	                        + "2011-08-03 21:56:04@_@武汉@_@主叫@_@66174709@_@88@_@市话@_@全球通88套餐之88元(G3版)@_@0.00|";
	            } 
	            else if (Constants.CDRTYPE_SMS.equals(cdrType)) 
	            {
	                billaccount = "3条@_@2条<";
	                billitem = "2011-08-04 21:56:04@_@内地@_@139********@_@接收@_@短信@_@@_@武汉短信包@_@0.00|";
	            } 
	            else if (Constants.CDRTYPE_GPRSWLAN.equals(cdrType)) 
	            {
	                billaccount = "";
	                billitem = "2011-08-01 21:56:04@_@北京@_@WLAN@_@12650@_@2018|"
	                        + "2011-08-02 21:56:04@_@北京@_@BLACKBERRY@_@150@_@20180|"
	                        + "2011-08-02 21:56:04@_@美国ATAT@_@CMNET@_@800@_@7|"
	                        + "2011-08-03 21:56:04@_@武汉@_@WLAN@_@800@_@7|"
	                        + "2011-08-03 21:56:04@_@武汉@_@WLAN@_@800@_@1256|";
	            } 
	            else if (Constants.CDRTYPE_DISCOUNT.equals(cdrType))
	            {
	            	ReturnWrap rw = new ReturnWrap();
	            	rw.setReturnObject(new CTagSet());
	                rw.setStatus(SSReturnCode.SUCCESS);
	                rw.setReturnMsg("No information!");
	                return rw;
	            } 
	            else if (Constants.CDRTYPE_INFOFEE.equals(cdrType)) 
	            {
	                billaccount = "5";
	                billitem =  "2011-08-01 21:56:04@_@彩信@_@超级贺卡@_@10658899@_@新浪@_@801005@_@点播@_@0.00|";
	            } 
	            else if (Constants.CDRTYPE_ISMG.equals(cdrType))
	            {
	                billaccount = "5";
	                billitem = "2011-08-02 21:56:04@_@WAP@_@彩铃DIY@_@10658899@_@0|";
	            }
	            else if (Constants.CDRTYPE_OTHERFEE.equals(cdrType)) 
	            {
	                billaccount = "5";
	                billitem = "2011-08-05 21:56:04@_@违约金@_@20.00|2011-08-05 21:56:04@_@协议补收费@_@15.00|"
	                        + "2011-08-05 21:56:04@_@租机费@_@25.00";
	            }
	        }
	        outParamMap.put("billcount", billaccount);
	        outParamMap.put("billitem", billitem);
	        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
    		System.out.println(jsonObj.toString());
    		
	        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
    	}
        
    	return super.queryDetailedRecords2012(msgHeader, month, cdrType, feeType);
    }
	
	 /**
     * 缴费历史查询
     * 
     * @param map
     * @return
     */
    @Override
	public ReturnWrap qryChargeHistory(Map map)
    {
		// 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("BLCSQueryReceptionHis"))
        {
        	String response = "[{'formNum':'531100504168436860','itemName':'收费','recDate':'20100504090015',"
        		+ "'cycle':'201004','fee':'5000','status':'在网','contactType':'网上营业厅'},"
	        	+ "{'formNum':'531100731726732692','itemName':'收费','recDate':'20100731112459',"
	    		+ "'cycle':'201006','fee':'5000','status':'在网','contactType':'自助终端'}]";
	    	
	    	// 数组类型的参数
	    	String[] arrAttrsKey = {"formNum","itemName","recDate",
	    			"cycle","fee","status","contactType"};
	    	
	    	return getIntMsgUtil().parseJsonResponse(response, null, arrAttrsKey);
        }
		return super.qryChargeHistory(map);
	}

    /**
     * 积分查询
     */
    @Override
	public ReturnWrap queryScoreValue(Map map)
    {
    	// 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("qrySubsScoreSimple139Mail"))
        {
        	return getIntMsgUtil().parseJsonResponse("{'scoreinfo':'神州行，我看行！~300~50~20~20~20~280~10'}", null, null);
        }
		return super.queryScoreValue(map);
	}
    
    /**
     * 积分明细查询
     */
	@Override
	public ReturnWrap queryScoreDetailHis(Map map)
	{
		// 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("BLCSQryScoreDetailHis"))
        {
        	String response = "[{'subsID':'6340963456288','recOid':'88009891730539','scoreType':'积分浮动'," 
        		+ "'chgScore':'-20','accessType':'CRM营业厅','updateTime':'2012-04-16 16:54:14'," 
        		+ "'reason':'积分消费','cycle':'201204','servNumber':'13963456288','subsName':'姓名13963456288'}]";

        	// 数组类型的参数,生成CRSet
        	String[] arrAttrsKey = {"subsID","recOid","scoreType","chgScore","accessType",
        			"null1","null2","updateTime","reason","chgScore","null3","null4",
        			"null5","null6","null7","cycle","servNumber","subsName",};
        	
        	return getIntMsgUtil().parseJsonResponse(response, null, arrAttrsKey);
        }
		return super.queryScoreDetailHis(map);
	}

	/**
     * 湖北版的积分兑换历史查询
     */
	@Override
	public ReturnWrap queryScorePrizeHis(Map map)
	{
		// 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("fmGetScorePrize"))
        {
        	String response = "{'retDataList':[{'statusdate':'2012-03-01 09:50:50'," 
        		+ "'prodname':'常态化营销活动','orgname':'鄂州','opername':'系统管理员'}]}";
        	
        	return getIntMsgUtil().parseJsonResponse(response, null, 
        			new String[]{"statusdate","prodname","startdate","opername"});
        }
		return super.queryScorePrizeHis(map);
	}

	/**
	 * 归属地查询（湖北）
	 * @param map
	 * @return
	 * @remark create by sWX219697 2014-7-8 16:35:45 OR_huawei_201407_35_自助终端接入EBUS二阶段_归属地查询 
	 */
    public ReturnWrap queryUserRegionReq(Map map)
	{
		// 湖北统一接口平台转EBUS开关开启
		if (IntMsgUtil.isTransEBUS("PTQueryLocation"))
		{
        	// 出参为键值对，放在JSONObj
        	Map<String,Object> outParamMap = new HashMap<String,Object>();

        	// 拼装出参
        	outParamMap.put("regionName", "济南");
        	outParamMap.put("provName", "山东");
        	
        	JSONObject jsonObj = JSONObject.fromObject(outParamMap);
    		
    		// 出参键值对 取值键名与输出的键名对应关系数组
        	String[][] outParamKeyDefine = {{"regionName","provName"},{"cityname","provname"}};
        	
        	return getIntMsgUtil().parseJsonResponse(jsonObj.toString(), outParamKeyDefine, null);
		}
		return super.queryUserRegionReq(map);
	}

    /**
     * PUK码查询
     * @param map
	 * @return
     * @remark create by wWX217192 2014-07-09 OR_huawei_201406_303 自助终端接入EBUS二阶段_PUK码查询
     */
    @SuppressWarnings("unchecked")
	public ReturnWrap queryPUK(Map map)
    {
    	// 湖北统一接口平台转EBUS开关开启
    	if(IntMsgUtil.isTransEBUS("BLCSQrySimPuk"))
    	{
    		try
    		{
    			String[] arrAttrsKey = new String[]{"iCol0", "iCol1", "iCol2", "iCol3"};
    			
    			return getIntMsgUtil().parseJsonResponse("[{\"iCol0\":\"PIN1\",\"iCol1\":\"PIN2\",\"iCol2\":\"PUK1\",\"iCol3\":\"PUK2\",\"iCol4\":\"ICCID\"},{\"iCol0\":\"1234\",\"iCol1\":\"\",\"iCol2\":\"01631028\",\"iCol3\":\"\",\"iCol4\":\"89860090179806837096\",\"iCol5\":\"460008689006290\",\"iCol6\":\"89860090179806837096\",\"iCol7\":\"13908686291\",\"iCol8\":\"\"}]", null, arrAttrsKey);
    		}
    		catch(Exception e)
    		{
    			ReturnWrap rw = new ReturnWrap();
    			rw.setStatus(0);
                rw.setReturnMsg("PUK码查询异常！");
                
                return rw;
    		}
    	}
    	
    	return super.queryPUK(map);
    }
    
    /**
     * 根据nocde(新)查询产品,优惠的资费描述信息
     * 
     * @param map 接口入参
     * @return ReturnWrap 返回值
     * @remark create zKF69263 2014/08/04 R003C14L08n01 OR_huawei_201407_40 自助终端接入EBUS二阶段_飞信
     */
    public ReturnWrap queryFeeMessage(Map map) 
    {
        // 湖北转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("BLCSQryRatePlane"))
        {
            String response = "{'desc':'主叫号码显示ebus'}";
            
            return getIntMsgUtil().parseJsonResponse(response, null, null);
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
    @Override
    public ReturnWrap qryNcodeTips(Map<String, String> paramMap)
    {
        // 湖北转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("BLCSGetObjectTipsByNCode"))
        {
            String response = "[{'NCODE':'H01','TIPTYPE':'PCTIPNORMAL','OPERATORTYPE':'PCOpRec','TIPTEXT':'ebus让若人123'}]";
            
            ReturnWrap rw = getIntMsgUtil().parseJsonResponse(response, null, 
                new String[]{"NCODE","TIPTYPE","OPERATORTYPE","TIPTEXT"});
            
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
			String response = "{'abc':[{'OBJID':'Christmasmain','OBJTYPE':'Product','TIPTYPE':'PCTIPNORMAL','OPERTYPE':'PCOpRec','TIPTEXT':''}," +
					"{'OBJID':'Christ_bag1','OBJTYPE':'Product','TIPTYPE':'PCTIPNORMAL','OPERTYPE':'PCOpRec','TIPTEXT':''}]}";
			
			ReturnWrap rw = getIntMsgUtil().parseJsonResponse(response, null, new String[]{"OBJID","OBJTYPE","TIPTYPE","OPERTYPE","TIPTEXT"});
			if (SSReturnCode.SUCCESS == rw.getStatus())
			{
				Vector v = new Vector();
				v.add(new CTagSet());
				v.add(rw.getReturnObject());
				rw.setReturnObject(v);
			}
			return rw;
		}
		return super.qryObjectTips(paramMap, prods);
	}
	
	/**
     * 受理历史查询
     * @param map
	 * @return
	 * @remark create by wWX217192 OR_huawei_201407_39 自助终端接入EBUS二阶段_受理历史查询 
     */
    @SuppressWarnings("unchecked")
	public ReturnWrap qryAllServiceHistory(Map map)
    {
    	// 湖北统一接口平台转EBUS开关开启
    	if(IntMsgUtil.isTransEBUS("BLCSQryReceptionSimple"))
    	{
    		try
    		{
    			String response = "[{\"accessType\":\"bsacHal\",\"accessTypeName\":\"CRM营业厅\",\"formNum\":\"999140108405842653\",\"messageInfo\":\"\",\"recDate\":\"2014-01-08 17:56:48\",\"recFee\":\"0\",\"recName\":\"改资料\",\"recNote\":\"\",\"recOpID\":\"10000000\",\"recOpName\":\"mxq\",\"recOrgID\":\"HB\",\"recOrgName\":\"\"},"
    				+ "{\"accessType\":\"bsacHal\",\"accessTypeName\":\"CRM营业厅\",\"formNum\":\"711140121405850883\",\"messageInfo\":\"\",\"recDate\":\"2014-01-21 10:09:36\",\"recFee\":\"0\",\"recName\":\"改资料\",\"recNote\":\"\",\"recOpID\":\"101\",\"recOpName\":\"系统管理员\",\"recOrgID\":\"HB.EZ\",\"recOrgName\":\"鄂州\"}]";
    		
    			return getIntMsgUtil().parseJsonResponse(response, null, 
    					new String[]{"recDate", "recOrgName", "recOpID", "recName"});
    		}
    		catch(Exception e)
    		{
    			ReturnWrap rw = new ReturnWrap();
    			rw.setStatus(0);
    			rw.setReturnMsg("受理历史查询失败！");
    			
    			return rw;
    		}
    	}
    	return super.qryAllServiceHistory(map);
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
            String res = "{'DATALIST':[{'C0':'pkg.gt.lte.choose','C1':'B221546','C2':'',"
            	+ "'C3':'4G语音88元套餐','C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'},"
            	+ "{'C0':'pkg.gt.lte.choose','C1':'B221563','C2':'','C3':'4G语音58元套餐',"
            	+ "'C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'},"
            	+ "{'C0':'pkg.gt.lte.choose','C1':'B221562','C2':'','C3':'4G语音48元套餐'," 
            	+ "'C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'},"
            	+ "{'C0':'pkg.gt.lte.choose','C1':'B221565','C2':'','C3':'4G语音408元套餐',"
            	+ "'C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'}," 
            	+ "{'C0':'pkg.gt.lte.choose','C1':'B221564','C2':'','C3':'4G语音328元套餐',"
            	+ "'C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'}," 
            	+ "{'C0':'pkg.gt.lte.choose','C1':'B221566','C2':'','C3':'4G语音28元套餐',"
            	+ "'C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'}," 
            	+ "{'C0':'pkg.gt.lte.choose','C1':'B221548','C2':'','C3':'4G语音238元套餐',"
            	+ "'C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'}," 
            	+ "{'C0':'pkg.gt.lte.choose','C1':'B221547','C2':'','C3':'4G语音168元套餐',"
            	+ "'C4':'SeleType_Choice','C5':'0','C6':'PCIntRelaNormal'}]," 
            	+ "'MAX':'1','MIN':'1'}";
            return getIntMsgUtil().parseJsonResponse(res, new String[][]{{"MIN","MAX","DATALIST"},{"minprod","maxprod","DATALIST"}}, 
            		new String[]{"C0","C1","C2","C3","C4","C5","C6"});
        }
        return super.qrySubProds(msgHeader, nCode, type, optType); 
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
            String res = "[{'attrID':'Prod:MINCONSUMEFEE','attrType':'EDIT','defValue':'3',"
            	+ "'dicts':'','entityName':'最低消费','ifNeeds':'1','isShowInRec':'1',"
            	+ "'maxLen':'10','minLen':'0','objType':'Product','sepSign':'',"
            	+ "'serviceID':'pkg.prod.newbusi','valCount':'1','valueType':'INT32'}]";
            return getIntMsgUtil().parseJsonResponse(res, null, new String[]{"objected","attrID","entityName","attrType","valueType","minLen",
                		"maxLen","isNeed","isShowInRec","valCount","sepSign","defaultValue","dictVal",
                		"objType"});
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
     */
    public ReturnWrap prodRec(MsgHeaderPO msgHeader, MultiProdCommitPO multiProdCommitPO)
    {
        // 湖北统一接口平台转EBUS开关开启
        if (IntMsgUtil.isTransEBUS("BLCSMultiProductRec"))
        {
            String response = "{'recOid':'20140624162701','orderID':'20140624162701'}";
            
            return getIntMsgUtil().parseJsonResponse(response, null, null);
        }
        
        return super.prodRec(msgHeader, multiProdCommitPO);
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
            String res = "{'subsID':'7110000001263','subsProdList':[{'NcodeID':'','applyDate':'2008-07-01 00:00:00',"
            	+ "'attr':'','cancelDate':'','createDate':'2008-07-01 00:00:00','description':'','donorSubsID':''," 
            	+ "'endDate':'','formNum':'','ifLinkSubsActive':'0','outNcodeType':'1','pkgProdID':'','privID':'',"
            	+ "'prodID':'MP7110101095776','prodName':'鄂州全球通畅享套餐68元','productClass':'class.pp.gt.st',"
            	+ "'productClassName':'标准全球通大类','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'2008-07-01 00:00:00','attr':'','cancelDate':'','createDate':'2008-07-01 00:00:00',"
            	+ "'description':'','donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0','outNcodeType':'1'," 
            	+ "'pkgProdID':'pkg.gt.st','privID':'','prodID':'B711110','prodName':'鄂州全球通畅享套餐68元必选产品'," 
            	+ "'productClass':'BASE','productClassName':'主资费产品','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'2001-07-01 00:00:00','attr':'','cancelDate':'','createDate':'2001-07-01 00:00:00',"
            	+ "'description':'','donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0','outNcodeType':'1'," 
            	+ "'pkgProdID':'prk.prod.cxyh','privID':'','prodID':'G17','prodName':'WAP上网业务'," 
            	+ "'productClass':'ProdClass_Bill_Append_Pack','productClassName':'优惠包','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'2005-11-30 15:12:27','attr':'','cancelDate':'','createDate':'2005-11-30 15:12:27',"
            	+ "'description':'','donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0','outNcodeType':'1',"
            	+ "'pkgProdID':'pkg.prod.serv','privID':'','prodID':'G28','prodName':'移动数据流量功能(无资费,已停用) '," 
            	+ "'productClass':'ProdClass_Bill_Append_Pack','productClassName':'优惠包','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'','attr':'','cancelDate':'','createDate':'2013-11-11 00:00:00'," 
            	+ "'description':'','donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0',"
            	+ "'outNcodeType':'1','pkgProdID':'','privID':'','prodID':'150','prodName':'移动数据流量业务',"
            	+ "'productClass':'ProdClass_NewServ_Base','productClassName':'基本功能','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'','attr':'','cancelDate':'','createDate':'2009-03-01 00:00:00'," 
            	+ "'description':'','donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0'," 
            	+ "'outNcodeType':'1','pkgProdID':'','privID':'','prodID':'pkg.prod.newbusi','prodName':'新业务类优惠包',"
            	+ "'productClass':'ProdClass_Bill_Append_Pack','productClassName':'优惠包','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'','attr':'','cancelDate':'','createDate':'2009-03-01 00:00:00','description':'',"
            	+ "'donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0','outNcodeType':'1','pkgProdID':'',"
            	+ "'privID':'','prodID':'pkg.prod.zshf','prodName':'[可选优惠包27]－湖北折扣分月送类','productClass':'ProdClass_Act_Zs',"
            	+ "'productClassName':'赠送业务','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'','attr':'','cancelDate':'','createDate':'2009-09-15 19:28:45',"
            	+ "'description':'','donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0','outNcodeType':'1'," 
            	+ "'pkgProdID':'','privID':'','prodID':'EZ_JFQL_001','prodName':'鄂州积分回馈活动','productClass':'rtScoreReward',"
            	+ "'productClassName':'','rltRefID':'','serviceRes':'','status':'1'}," 
            	+ "{'NcodeID':'','applyDate':'','attr':'','cancelDate':'','createDate':'2009-09-15 19:29:05','description':'',"
            	+ "'donorSubsID':'','endDate':'','formNum':'','ifLinkSubsActive':'0','outNcodeType':'1','pkgProdID':'','privID':''," 
            	+ "'prodID':'EZ_JFQL_001','prodName':'鄂州积分回馈活动','productClass':'rtScoreReward','productClassName':''," 
            	+ "'rltRefID':'','serviceRes':'','status':'1'},{'NcodeID':'','applyDate':'','attr':'','cancelDate':'',"
            	+ "'createDate':'2009-09-15 19:29:21','description':'','donorSubsID':'','endDate':'','formNum':''," 
            	+ "'ifLinkSubsActive':'0','outNcodeType':'1','pkgProdID':'','privID':'','prodID':'EZ_JFQL_001',"
            	+ "'prodName':'鄂州积分回馈活动','productClass':'rtScoreReward','productClassName':'','rltRefID':'','serviceRes':'','status':'1'}]}";
            return getIntMsgUtil().parseJsonResponse(res, null, new String[]{"prodID", "prodName", "attr", "serviceRes", "applyDate", "createDate", "endDate", 
                        "status", "formNum", "productClass", "description", "donorSubsID", "rltRefID", 
                        "productClassName", "cancelDate", "pkgProdID", "outNcodeType", "privID", "NcodeID"});
        }
        return super.qryHasProds(msgHeader);
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
				String response = "{'-ncode':'SUBSNCODEEXIST','ACCESSTYPE':'INNER','BOSSCODE':'KJTX','BRAND':'BrandSzx'," 
        			+ "'BUSITYPE':'0','CALL_NUMBER':'15271716697','CURID':'KJTX','CURNAME':'开机提醒','CUROUTPARAM':''," 
        			+ "'CUROUTPARAMALL':'','CUROUTPARAMNOREP':'','CURSTATUS':'1','EFFTYPE':'Type_Default','ERRNO':'0'," 
        			+ "'EXECUTECMD':'TRUE','HASPARAM':'0','INPARAMFORMAT':'','INPARAMSPLIT':'','IPADDRESS':'','ISWRITEFILELOG':'0'," 
        			+ "'ISWRITETABLOG':'1','JOBDESC':'开机提醒','JOBNAME':'开机提醒','LINKNODE':'','LINKTYPE':'','MAINPRODID':'pp.eo.st'," 
        			+ "'MSISDN':'15271716697','NCODE':'KJTX','NEXTID':'KJTX','NEXTNAME':'开机提醒','NEXTOUTPARAM':'','NEXTOUTPARAMALL':''," 
        			+ "'NEXTOUTPARAMNOREP':'','NOPENEDPMP':'ZERO','OLD_PASSWD':'','OPENEDPMP':'ZERO','OUTOPERID':'','OUTPARAMFORMAT':''," 
        			+ "'OUTPARAMSPLIT':'','PARM':'','REALOPERID':'','REALRETCODE':'100','REGION':'711','RETCODE':'0','RETCONVERT':'1'," 
        			+ "'RETMSG':'已开通','RETURN':'0','SENDORNOT':'0','STEP':'50','STYPE':'QRY','SUBSCREATEDATE':'2014-11-08 16:19:12'," 
        			+ "'SUBSID':'7119001087427','TELNUM':'15271716697','UNITID':'INNER','VNCODE':'vsmasingle','accessType':'bsacAtsv'," 
        			+ "'formnum':'','interfaceID':'IncProductSrv2','menuID':'recTestFunc2','operatorID':'101','region':'','reqSeq':'1'," 
        			+ "'reqTime':'20141118192306','routeType':'1','telNum':'15271716697','uniContact':'','uniContactFlag':''," 
        			+ "'unitID':'HUAWEI','virtualActorFlag':'1'}";
        		
        		// 调用dubbo服务
        		return getIntMsgUtil().parseJsonResponse(response, null, null);
        	}
			
		}
        catch (Exception e)
        {
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
        		String response = "{'-PARM':'','-effect_type':'','ACCESSTYPE':'INNER','ADDNCODE':'','ADD_ENDDATE':''," 
        			+ "'ADD_STARTDATE':'','BOSSCODE':'KJTX','BRAND':'BrandSzx','BUSITYPE':'0','CALL_NUMBER':'15271716697'," 
        			+ "'CURID':'KJTX','CURNAME':'开机提醒','CURNCODE':'KJTX','DELNCODE':'KJTX','DEL_ENDDATE':'2014-12-01 00:00:00'," 
        			+ "'EFFTYPE':'Type_Default','EFF_DATE':'2014-12-01','EFF_DATETIME':'2014-12-01 00:00:00','END_DATE':'ZERO'," 
        			+ "'ERRNO':'0','EXECUTECMD':'TRUE','FORMNUMBER':'711141118406525069','HASPARAM':'0','IMSI':'460022722015257'," 
        			+ "'INPARAMFORMAT':'','INPARAMSPLIT':'','IPADDRESS':'','ISNEEDTHIRDCONF':'0','ISWRITEFILELOG':'0','ISWRITETABLOG':'1'," 
        			+ "'JOBDESC':'开机提醒','JOBNAME':'开机提醒','LINKNODE':'','LINKTYPE':'','MAINPRODID':'pp.eo.st','MSISDN':'15271716697'," 
        			+ "'NCODE':'KJTX','NOPENEDPMP':'ZERO','OLD_PASSWD':'','OPENEDPMP':'ZERO','ORDERRESULT':'9900','ORI_NEXTATTRS':''," 
        			+ "'OUTOPERID':'','OUTPARAMFORMAT':'','OUTPARAMSPLIT':'','PARM':'','REALOPERID':'','REALRETCODE':'100','REGION':'711'," 
        			+ "'RETCODE':'0','RETCONVERT':'1','RETMSG':'取消开机提醒成功','RETURN':'','SENDORNOT':'0','STEP':'70','STYPE':'DEL'," 
        			+ "'SUBORDERRESULT':'','SUBSCREATEDATE':'2014-11-08 16:19:12','SUBSID':'7119001087427','TELNUM':'15271716697'," 
        			+ "'TEMPLATENO':'P00200','TEMPLATEPARA':'1$开机提醒&2$2014-12-01&3$ZERO','UNITID':'INNER','VNCODE':'vsmasingle'," 
        			+ "'YYWWFORMNUM':'10005406525067','accessType':'bsacAtsv','formnum':'','interfaceID':'IncProductSrv2'," 
        			+ "'menuID':'recTestFunc2','operatorID':'101','region':'','reqSeq':'1','reqTime':'20141118192345','routeType':'1'," 
        			+ "'telNum':'15271716697','uniContact':'','uniContactFlag':'','unitID':'HUAWEI','virtualActorFlag':'1'}";
        		
        		// 调用dubbo服务
        		ReturnWrap rw = getIntMsgUtil().parseJsonResponse(response, null, null);

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
     * @remark create by sWX219697 2015-3-27 18:01:22 
     */
    public ReturnWrap qryPayAmount(MsgHeaderPO msgHeader, String orgid)
    {
        try
        {
    		String resXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<QryPayRsp>" +
			"<HSNDUNS>754</HSNDUNS>" +
			"<BizOrderResult>0000</BizOrderResult>" +
			"<ResultDesc>错误描述</ResultDesc>" +
			"<QryPayInfo>" +
			"<CustName>姓名</CustName>" +
			"<PayAmount>1000000</PayAmount>" +
			"<Balance>100000</Balance>" +
			"</QryPayInfo>" +
			"</QryPayRsp>";
        	
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
     * @remark create by sWX219697 2015-4-2 09:58:49 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
     */
    public ReturnWrap nonlocalCharge(MsgHeaderPO msgHeader, String seq, String actualPayAmount, String orgid)
    {
        try
        {
            // 调用一级boss接口，进行交费
            String resXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            		"<PaymentRsp>" +
            		"<HSNDUNS>5310</HSNDUNS>" +
            		"<BizOrderResult>0000</BizOrderResult>" +
            		"</PaymentRsp>";
            
        	//封装返回对象
        	ReturnWrap rw = new ReturnWrap();
        	
        	//解析返回报文
        	Document resDoc = DocumentHelper.parseText(resXML);
    		Element resRoot = resDoc.getRootElement();
        	
    		//接口调用成功
        	if(SSReturnCode.INTERBOSS_SUCCESS.equals(resRoot.element("BizOrderResult").getTextTrim()))
        	{
        		rw.setStatus(SSReturnCode.SUCCESS);
        		rw.setReturnMsg("交费成功");
        	}
        	else
        	{
        		rw.setStatus(SSReturnCode.ERROR);
        		rw.setReturnMsg(resRoot.element("ResultDesc").getTextTrim());
        	}
        	
        	return rw;
        }
        catch (Exception e)
        {
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"异地缴费异常！");
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
        Map<String,Object> outParamMap = new HashMap<String,Object>();
        
        //校验结果
        outParamMap.put("isElecCard", "1");
        
        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        
        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
    }
    
    /**
     * <有价卡充值>
     * <功能详细描述>
     * @param msgHeader 消息头
     * @param paramMap 消息体
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2015-05-11 OR_HUB_201503_1068_HUB_关于配合集团《关于下发__电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     */
    public ReturnWrap valueCardCharge(MsgHeaderPO msgHeader, Map<String, String> paramMap)
    {
        Map<String,Object> outParamMap = new HashMap<String,Object>();
        
        //校验结果
        outParamMap.put("chargeResult", "1");
        
        //充值金额
        outParamMap.put("countTotal", "100");
        
        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        
        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
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
		String response = "{transActionID:'1234567890978',crmFormNum:'54321678907',cardList:[" +
				"{cardNo:'111111111111',cardPwd:'1111111111111',elecCardCntTotal:'30元',cardDate:'2015-06-01',CardType:'话费充值卡',CardBusiPro:'30元话费'}," +
				"{cardNo:'222222222222',cardPwd:'2222222222222',elecCardCntTotal:'30元',cardDate:'2015-06-01',CardType:'话费充值卡',CardBusiPro:'30元话费'}," +
				"{cardNo:'333333333333',cardPwd:'3333333333333',elecCardCntTotal:'30元',cardDate:'2015-06-01',CardType:'话费充值卡',CardBusiPro:'30元话费'}]}";
        
        return getIntMsgUtil().parseJsonResponse(response, null, null);
	}
}
