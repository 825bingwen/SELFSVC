package com.gmcc.boss.selfsvc.call;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.gmcc.boss.common.base.CPEntity;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.busiAcceptTime.model.BusiAcceptTimePO;
import com.gmcc.boss.selfsvc.busiAcceptTime.service.BusiAcceptTimeService;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.ApplicationContextUtil;
import com.huawei.ebus.connector.dubbo.IDubboletInterface;
import com.huawei.ebus.connector.dubbo.bean.DubboInPamamBean;
import com.huawei.ebus.connector.dubbo.bean.DubboOutParamBean;
import com.opensymphony.xwork2.ActionContext;

/**
 * 发送请求到Dubbo服务器工具类
 * 
 * @author zKF69263
 * @version [版本号, 2014-4-9]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DubboInvokeUtil
{
    private static Log logger = LogFactory.getLog(DubboInvokeUtil.class);
    
    // 缴费服务大类
    public static final String SERVICETYPE_PAYMENT = "PaymentService";
    
    // 账单服务大类
    public static final String SERVICETYPE_BILL = "BillService";
    
    // 信息查询大类
    public static final String SERVICETYPE_INFORMATION_INQUIRY = "InformationInquiryService";
    
    // 业务受理
    public static final String SERVICETYPE_BUSINESS_ACCEPTANCE = "BusinessAcceptanceService";
    
    // 资源管理服务大类
    public static final String SERVICETYPE_RESOURCE_MANAGEMENT = "ResourceManagementService";
    
    // 认证鉴权大类
    public static final String SERVICETYPE_AUTHENTICATE = "AuthenticateService";
    
    // 支付管理大类
    public static final String SERVICETYPE_PAYMENT_MANAGEMENT = "PaymentManagementService";
    
    // 积分服务大类
    public static final String SERVICETYPE_CREDIT_MANAGEMENT = "CreditManagementService";
    
    // 市场营销大类
    public static final String SERVICETYPE_MARKETING = "MarketingService";
    
    // 客户资料管理大类
    public static final String SERVICETYPE_CUSTOMER_DATA_MANAGEMENT = "CustomerDataManagementSercice";
    
    // 服务变更大类
    public static final String SERVICETYPE_SERVICE_CHANGE = "ServiceChange";
    
    // 渠道管理大类
    public static final String SERVICETYPE_CHANNEL_MANAGEMENT = "ChannelManagement";
    
    /**
     * 受理时长表操作service
     */
    private BusiAcceptTimeService busiAcceptTimeServiceImpl;
    
    /**
     * dubbo服务接口
     */
    private transient IDubboletInterface dubbolet;
    
    /**
     * 创建DubboInPamamBean请求对象
     * 
     * @param map 入参map
     * @param interfaceId 接口标识  interfaceId
     * @param menuid  菜单ID
     * @param unicontact 统一接触流水
     * @param route_type  路由类型
     * @param route_value  路由值
     * @param operatorid 操作员id
     * @param atsvNum  终端机
     * @remark: create by hWX5316476 2014/04/09 OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
     * @return DubboInPamamBean
     */
	public DubboInPamamBean createDubboInParamBean(Map map, String interfaceId,
		String menuid, String unicontact, String route_type,
		String route_value, String operatorid, String atsvNum,
		SimpleDateFormat sdf, int transID)
	{
		DubboInPamamBean reqBean = new DubboInPamamBean();
		
		// 渠道
        reqBean.setAccessType("bsacAtsv");
 
    	// 接口标识  interfaceId
        reqBean.setInterfaceID(interfaceId);
        
        // 请求时间
        synchronized (sdf)
        {
            reqBean.setReqTime(sdf.format(new Date()));
        }
        
        // 请求流水
        reqBean.setReqSeq(String.valueOf(transID++));  
        
        // 路由 0：地区  1：手机号
        reqBean.setRouteType(route_type);
        
        // 路由值 0：地区  1：手机号
        if("0".equals(route_type))
        {
        	// 地区
        	reqBean.setRegion(route_value);
        	
        	// 手机号
        	reqBean.setTelnum("");
        }
        else if("1".equals(route_type))
        {
        	// 地区
        	reqBean.setRegion("");
        	
        	// 手机号
        	reqBean.setTelnum(route_value);
        }
        else
        {
        	// 地区
        	reqBean.setRegion("");
        	
        	// 手机号
        	reqBean.setTelnum("");
        }
        
        // 操作员
        reqBean.setOperatorID(operatorid);
        
        // 统一接触流水
        reqBean.setUniContact(unicontact);
        
        // 菜单项
        reqBean.setMenuID(menuid);
        
        // 子渠道
        reqBean.setUnitid("HUAWEI");
        
        // 流水号
        reqBean.setFormnum("");
        
        // 统一接触流水标识
        reqBean.setUniContactFlag("");
        
        // 入参
        JSONObject jsonObj = JSONObject.fromObject(map);
        
        // map的大小为1，并且map的值为一个list
		if(map.size() == 1 )
		{
			Iterator<String> keys = jsonObj.keys();
			if(null != keys && keys.hasNext())
			{
				String key = keys.next();
				
				Object obj = jsonObj.get(key);
				
				if (obj instanceof JSONArray)
				{
					JSONArray jsonArray = (JSONArray)obj;
					reqBean.setParam(jsonArray.toString());
				}
				else
				{
					reqBean.setParam(jsonObj.toString());
				}
			}
		}
		else if(map.size() > 1 )
		{
			reqBean.setParam(jsonObj.toString());
		}
		
		if (logger.isInfoEnabled())
        {
            logger.info("request message: " + JSONObject.fromObject(reqBean).toString());
        }

        return reqBean;
	}
    
    /**
     * 发送请求到Dubbo服务
     * 
     * @param reqBean DubboInPamamBean Dubbo服务入参Bean
     * @return ReturnWrap 返回值
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 2014-4-9 版本号 需求/BUG编号: OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
     */
	public ReturnWrap invokeDubbo(DubboInPamamBean reqBean) 
	{
		return this.invokeDubbo(reqBean, null, null, true);
	}
    
    /**
     * 发送请求到Dubbo服务
     * 
     * @param reqBean DubboInPamamBean Dubbo服务入参Bean
     * @param objAttrsKey 对象属性Key列表
     * 		取得属性的键值，例如new String[][] {{"retMsg", "retCode"}, {"retMsg2", "retCode2"}}，依据数组0中的键值取值，然后依据数组1中的键值放入TagSet中
     * @param arrAttrsKey 数组中对象属性Key列表
     * 		取得列表中的键值，例如：new String[] {"description", "dictID", "dictName"}，依据此顺序放入crset中
     * @return ReturnWrap 返回值
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 2014-4-9 版本号 需求/BUG编号: OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
     */
	public ReturnWrap invokeDubbo(DubboInPamamBean reqBean,
		String[][] objAttrsKey, String[] arrAttrsKey, boolean ifParseResponseMsg)
	{
        // 解析发送报文获取操作员id，businessId，路由方式，号码，region
        BusiAcceptTimePO busiAcceptTimePO = this.parseRequest(reqBean);
               
        // 报文发送时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
        String startTime = sdf.format(new Date());

        // 获取终端机信息
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);  
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
        busiAcceptTimePO.setTermId(terminalInfoPO.getTermid());
        busiAcceptTimePO.setRegion(terminalInfoPO.getRegion());
        
        try
        {
            if (logger.isWarnEnabled())
            {
                logger.warn("request param message: " + reqBean.getParam());
            }
            
            String serviceId = "";
            
            // 支持统一接口平台转接口列表
	    	List<DictItemPO> actInterfaceList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.EBUS_INTERFACE_LIST);
	    	
	    	if (actInterfaceList != null)
	    	{
	    		for (int i = 0; i < actInterfaceList.size(); i++)
	    	    {
	    	        DictItemPO dictItemPO = actInterfaceList.get(i);
	    	        if (reqBean.getInterfaceID().equals(dictItemPO.getDictid()))
	    	        {
	    	        	serviceId = dictItemPO.getDictname();
	    	        	
	    	            break;
	    	        }
	    	    }
	    	}
            
            if (logger.isInfoEnabled())
            {
            	logger.info("服务大类: " + serviceId + "，接口名：" + reqBean.getInterfaceID());
            }
            
            // 调用dubbo服务
            DubboOutParamBean resBean = null;
            
            if (StringUtils.isNotEmpty(serviceId))
            {
            	resBean = ((IDubboletInterface) ApplicationContextUtil.getBean(StringUtils.uncapitalize(serviceId))).doPost(reqBean);
            }
            else
            {
            	resBean = dubbolet.doPost(reqBean);
            }
            
            String rspMsg = resBean.getParam();
            
            if (logger.isInfoEnabled())
            {
            	logger.info("response message: " + JSONObject.fromObject(resBean).toString());
                logger.info("response param message: " + rspMsg);
            }
            else if (logger.isWarnEnabled())
            {
                logger.warn("response param message: " + (rspMsg.length() > 1024 ? rspMsg.substring(0, 1024) : rspMsg));
            }
            
            // 报文返回时间
            String endTime = sdf.format(new Date());
            
            // 受理时长
            int acceptTime = (int)(sdf.parse(endTime).getTime()-sdf.parse(startTime).getTime());
            
            busiAcceptTimePO.setStartTime(startTime.substring(0, 19));
            busiAcceptTimePO.setEndTime(endTime.substring(0,19));
            busiAcceptTimePO.setAcceptTime(acceptTime);
            
            
            // 解析应答报文，返回ReturnWrap
            ReturnWrap rw = new ReturnWrap();
            
            // 判断业务是否受理成功
            if ("0".equals(resBean.getRetcode()))
            {
                // 增加判断ifParseResponseMsg为true时，解析返回响应报文，否则不解析，默认是true。
                if (ifParseResponseMsg)
                {
                    rw = parseJsonResponse(rspMsg, objAttrsKey, arrAttrsKey);
                }
                else 
                {
                    rw.setReturnObject(rspMsg);
                }
            	
        		rw.setStatus(SSReturnCode.SUCCESS);
                busiAcceptTimePO.setStatus(SSReturnCode.SUCCESS);
                rw.setReturnMsg(resBean.getRetMsg());
            }
            else
            {
            	rw.setStatus(SSReturnCode.ERROR);
                busiAcceptTimePO.setStatus(SSReturnCode.ERROR);
                rw.setReturnMsg(resBean.getRetMsg());
            }
            
            rw.setErrcode(Integer.parseInt(resBean.getRetcode()));
            
            // 将受理时长插入数据库
            this.busiAcceptTimeServiceImpl.insertBusiAcceptTime(busiAcceptTimePO);
              
            // 解析返回报文
            return rw;
        }
        catch (Exception e)
        {
            logger.error("调用Dubbo服务接口失败：", e);
        }
        
        ReturnWrap rw = new ReturnWrap();
        rw.setStatus(SSReturnCode.ERROR);
        rw.setReturnMsg("系统发生异常，请稍后再试。给您带来的不便，敬请原谅。");
        
        return rw;
    }
    
    /**
     * 解析应答报文
     * 
     * @param outJsonParam Json格式的应答报文
     * @param objAttrsKey 对象属性Key列表
     * @param arrAttrsKey 数组中对象属性Key列表
     * @return ReturnWrap 返回值
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 2014-4-9 版本号 需求/BUG编号: OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
     */
	public ReturnWrap parseJsonResponse(String outJsonParam,
		String[][] objAttrsKey, String[] arrAttrsKey)
    {
    	// 返回值ReturnWrap
    	ReturnWrap rtData = new ReturnWrap();
    	
    	//modify begin sWX219697 2014-12-9 17:43:59 Bug_82790
    	//param为空时，不需要解析。解决param为空字符串时报错的问题
    	if(StringUtils.isBlank(outJsonParam))
    	{
    		return rtData;
    	}
    	//modify end sWX219697 2014-12-9 17:43:59 Bug_82790
        
        // 解析出参报文
		JSONObject jsonObj = JSONObject.fromObject("{\"param\":" + outJsonParam + "}");
		
        Object paramObj = jsonObj.get("param");
		
        // 判断param节点返回的是JSONArray还是JSONObject
		if (paramObj instanceof JSONArray)
		{
			// 将Json数组转换为CRSet，放入ReturnWrap中返回
			rtData.setReturnObject(parseJSONArrayToCRSet((JSONArray) paramObj, arrAttrsKey));
		}
		else 
		{
			// 格式：CTagSet, CRSet, CRSet, ...
			Vector<CPEntity> retVector = parseJSONObjectToTagSet(
				(JSONObject) paramObj, objAttrsKey, arrAttrsKey);
	        
			// 判断如果存在两个对象，则返回Vector
	        if (retVector.size() == 2)
	        {
	        	rtData.setReturnObject(retVector);
	        }
	        // 判断如果存在一个对象，则直接返回
	        else if (retVector.size() == 1)
	        {
	        	rtData.setReturnObject(retVector.get(0));
	        }
	        else {
	        	
	        	rtData.setReturnObject(null);
	        }
		}
		
		return rtData;
    }
    
    /**
     * 将Json对象转换为TagSet
     * 
     * @param jsonObj Json对象
     * @return Vector<CPEntity>
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 2014-4-9 版本号 需求/BUG编号: OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
     */
	private Vector<CPEntity> parseJSONObjectToTagSet(JSONObject jsonObj,
		String[][] objAttrsKey, String[] arrAttrsKey) 
	{
		// 格式：CTagSet, CRSet, CRSet, ...
        Vector<CPEntity> retVector = new Vector<CPEntity>();
		
		if (null == jsonObj)
		{
			return retVector;
		}
		
		CTagSet tagSet = null;
		CRSet rSet = null;
		
		// 如果json对象有值时，初始化tagSet
		if (jsonObj.size() > 0)
		{
			tagSet = new CTagSet();
		}
		
		if (null != objAttrsKey)
		{
			for (int i = 0; i < objAttrsKey[0].length; i++)
			{
				if (jsonObj.get(objAttrsKey[0][i]) instanceof JSONArray)
				{
					rSet = parseJSONArrayToCRSet((JSONArray) jsonObj.get(objAttrsKey[0][i]), arrAttrsKey);
				}
				else
				{
					if (objAttrsKey.length > 1)
					{
						tagSet.SetValue(objAttrsKey[1][i], String.valueOf(jsonObj.get(objAttrsKey[0][i])));
					}
					else
					{
						tagSet.SetValue(objAttrsKey[0][i], String.valueOf(jsonObj.get(objAttrsKey[0][i])));
					}
				}
			}
		}
		else 
		{
			// 获取转换后的json所有的keys
			Iterator<String> keys = jsonObj.keys();
			
			// 用迭代器遍历key取得value值放入tagSet中
			while (keys.hasNext())
			{
				// 取得每一项的Key值
				String key = keys.next();
				
				if (jsonObj.get(key) instanceof JSONArray)
				{
					rSet = parseJSONArrayToCRSet((JSONArray) jsonObj.get(key), arrAttrsKey);
				}
				else
				{
					tagSet.SetValue(key, String.valueOf(jsonObj.get(key)));
				}
			}
		}
		
		// 判断是否存在TagSet
		if (null != tagSet && !tagSet.isEmpty())
		{
			retVector.add(tagSet);
		}
		
		// 判断是否存在rSet
		if (null != rSet && rSet.GetRowCount() > 0)
		{
			retVector.add(rSet);
		}
		
		return retVector;
	}
	
	/**
     * 将Json数组转换为CRSet
     * 
     * @param jsonArray Json数组
     * @param arrAttrsKey 数组中数据属性Key
     * @return CRSet
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 2014-4-9 版本号 需求/BUG编号: OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
     */
	private CRSet parseJSONArrayToCRSet(JSONArray jsonArray, String[] arrAttrsKey) 
	{
		if (null == jsonArray)
		{
			return null;
		}
		
		CRSet rSet = null;
		
		// 遍历Json数组放入CRSet中
		for (int i = 0; i < jsonArray.size(); i++)
		{
			// 判断是否是JSONObject对象
			if (jsonArray.get(i) instanceof JSONObject)
			{
				// 取出jsonObject
				JSONObject jsonObject = (JSONObject)jsonArray.get(i);
				
				if (i == 0)
				{
					rSet = new CRSet((null != arrAttrsKey) ? arrAttrsKey.length : jsonObject.entrySet().size());
				}

				// 新增CRSet行
				rSet.AddRow();
				
				if (null != arrAttrsKey)
				{
					// 遍历取得的Key值
					for (int j = 0; j < arrAttrsKey.length; j++) 
					{
						rSet.SetValue(i, j, 
						    (jsonObject.get(arrAttrsKey[j]) != null) ? String.valueOf(jsonObject.get(arrAttrsKey[j])) : "");
					}
				}
				else
				{
					// 取得jsonObject中的Key值
					Iterator<String> keys = jsonObject.keys();
					
					int j = 0;
					
					// 用迭代器遍历key取得value值并设置CRSet
					while (keys.hasNext())
					{
					    String next= keys.next(); 
						rSet.SetValue(i, j, 
						    (jsonObject.get(next) != null) ? String.valueOf(jsonObject.get(next)) : "");
						
						j++;
					}
				}
			}
		}
		
		return rSet;
	}
    
	/**
     * 解析请求报文
     * 
     * @param reqBean DubboInPamamBean Dubbo传入参数
     * @return BusiAcceptTimePO
     * @see [类、类#方法、类#成员]
     * @remark add by zKF69263 2014-4-9 版本号 需求/BUG编号: OR_huawei_201404_389 自助终端全流程接入EBUS改造_公用方法改造
     */
    private BusiAcceptTimePO parseRequest(DubboInPamamBean reqBean)
    {
        BusiAcceptTimePO busiAcceptTimePO = new BusiAcceptTimePO();
        
        // 组装受理时间
        busiAcceptTimePO.setBusinessId(reqBean.getInterfaceID());
        busiAcceptTimePO.setMenuId(reqBean.getMenuID());
        busiAcceptTimePO.setRegion(reqBean.getRegion());
        busiAcceptTimePO.setRouteType(reqBean.getRouteType());
        busiAcceptTimePO.setServnum(reqBean.getTelnum());
        busiAcceptTimePO.setOperId(reqBean.getOperatorID());
        
        // 返回
        return busiAcceptTimePO;
    }
    
	/**
	 * @return the busiAcceptTimeServiceImpl
	 */
	public BusiAcceptTimeService getBusiAcceptTimeServiceImpl() 
	{
		return busiAcceptTimeServiceImpl;
	}

	/**
	 * @param busiAcceptTimeServiceImpl the busiAcceptTimeServiceImpl to set
	 */
	public void setBusiAcceptTimeServiceImpl(BusiAcceptTimeService busiAcceptTimeServiceImpl) 
	{
		this.busiAcceptTimeServiceImpl = busiAcceptTimeServiceImpl;
	}

	/**
	 * @return the dubbolet
	 */
	public IDubboletInterface getDubbolet() 
	{
		return dubbolet;
	}

	/**
	 * @param dubbolet the dubbolet to set
	 */
	public void setDubbolet(IDubboletInterface dubbolet) 
	{
		this.dubbolet = dubbolet;
	}
}