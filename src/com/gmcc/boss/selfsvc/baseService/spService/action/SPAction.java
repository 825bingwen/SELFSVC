package com.gmcc.boss.selfsvc.baseService.spService.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.base.CEntityString;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.baseService.spService.model.SpAvailPO;
import com.gmcc.boss.selfsvc.baseService.spService.service.SpService;
import com.gmcc.boss.selfsvc.bean.SPBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.CurrencyUtil;


/**
 * 全网梦网业务查询退订或订购
 * @author xkf29026
 *
 */
public class SPAction extends BaseAction
{
    
    private static Log logger = LogFactory.getLog(SPAction.class);
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单
    private String curMenuId;
    
    // 页号
    private String pagecount;
    
    // 错误信息
    private String errormessage;
    
    //成功信息
    private String successMessage;
    
    // 用户已开通梦网业务结果集
    private Vector spservice;
    
    // 用户可订购的全网梦网业务集合
    private List<SpAvailPO> availSPService;
    
    // 操作类型
    private String operType;
    
    // 是否已定购 0:未订购 1:已订购
    private String dinggouStatus;
    
    // 接口调用
    private SPBean spBean;
    
    // 数据库调用service
    SpService spService;
    
    /**
     * 根据spcode+operatorcode查询梦网业务
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String querySpByCode()
    {
        logger.debug("querySpByCode start!");
        
        String forward = null;
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        if (curMenuId == null)
        {
        	curMenuId = "";
        }
        
        // modify begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
        Map result = spBean.queryService(terminalInfoPO, customer, curMenuId, "0");        
        
        forward = this.parseResult(result);
        // modify end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
        
        // 增加湖北全网梦网业务订购功能
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if(Constants.PROOPERORGID_HUB.equals(province))
        {
            // 从自助终端数据库中查询用户可订购的全网梦网业务
            availSPService = spService.qryAvailSP();
            
            List<SpAvailPO> availSPServiceBak = new ArrayList<SpAvailPO>();
            for (SpAvailPO spAvailPO : availSPService)
            {
                if (curMenuId.split("-")[0].equals(spAvailPO.getSpcode()) && curMenuId.split("-")[1].equals(spAvailPO.getOperatorCode()))
                {
                    availSPServiceBak.add(spAvailPO);
                }
            }
            availSPService = availSPServiceBak;
            dinggouStatus = "0";
            
            // 过滤掉用户已经订购的sp业务,过滤条件为spid和spbizid同时相同。
            if (spservice != null)
            {
                CRSet spserviceData = (CRSet)spservice.get(1);
                if(availSPService != null && spserviceData != null && availSPService.size() >0 && spserviceData.GetRowCount() >0)
                {
                    for(int i=0;i<spserviceData.GetRowCount();i++)
                    {
                        String spid = spserviceData.GetValue(i, 1);  
                        String spBizid = spserviceData.GetValue(i, 3);
//                        for(int j=0;j<availSPService.size();j++)
//                        {
//                            SpAvailPO spAvailPO = availSPService.get(j);
//                            String spcode = spAvailPO.getSpcode();
//                            String spbizcode = spAvailPO.getOperatorCode();
//                            if(spid.equals(spcode) && spBizid.equals(spbizcode))
//                            {
//                                dinggouStatus = "1";
//                            }
//                        }
                        for(SpAvailPO sp : availSPService)
                        {
                          String spcode = sp.getSpcode();
                          String spbizcode = sp.getOperatorCode();
                          if(spid.equals(spcode) && spBizid.equals(spbizcode))
                          {
                              dinggouStatus = "1";
                              sp.setOfferMan(spserviceData.GetValue(i, 2));// 提供商
                              sp.setOperatorName(spserviceData.GetValue(i, 4));// 业务名称
                              sp.setFee(CurrencyUtil.divide(spserviceData.GetValue(i, 7),"1000"));// 费用(元)
                              sp.setValiddate(spserviceData.GetValue(i, 9));// 开通日期
                          }
                        }
                    }
                }
            }
            
            // 调整计费方式，费用和时间的显示格式
            this.setBillFlag();
            return "querySpByCode";
        }
        
        
        logger.debug("querySpByCode end!");
        return forward;
    }

    /**
     * 调整计费方式，费用和时间的显示格式
     * @param availSPService
     * @remark create by jWX216858 2014/08/07 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
     */
	public void setBillFlag()
	{
		if(availSPService != null)
		{
		    for(int k=0;k<availSPService.size();k++)
		    {
		        SpAvailPO spAvailPO = availSPService.get(k);
		        if("0".equals(spAvailPO.getBillFlag()))
		        {
		            spAvailPO.setBillFlag("免费");
		        }
		        if("1".equals(spAvailPO.getBillFlag()))
		        {
		            spAvailPO.setBillFlag("按条");
		        }
		        if("2".equals(spAvailPO.getBillFlag()))
		        {
		            spAvailPO.setBillFlag("包月");
		        }
		        spAvailPO.setFee(CurrencyUtil.divide(spAvailPO.getFee(),"1000"));
		        spAvailPO.setExpireDate(CommonUtil.formatDate(spAvailPO.getExpireDate(),"yyyyMMdd","yyyy-MM-dd"));
		        availSPService.set(k, spAvailPO);
		    }
		}
	}

    /**
     * 解析bean返回的map
     * @param forward
     * @param result
     * @remark create by jWX216858 2014/08/07 V200R003C10LG0801 OR_huawei_201408_93 圈复杂度_自助终端（二阶段）
     */
	public String parseResult(Map result)
	{
		String forward = null;
		if (result.size() > 2)
        {
            CRSet crset = (CRSet)result.get("returnObj");
            
            if (crset != null && crset.GetRowCount() > 0)
            {
                forward = "qrySPinfo";
                // 将得到的结果数据放到页面显示的前提
                Vector v = new Vector();
                v.add(new CEntityString("业务名称,提供商,资费,开通时间"));
                v.add(crset);
                
                // 设置结果集
                setSpservice(v);
            }
            else
            {
                //add begin by cwx456134 2017-4-23 DTS2017041809030 历史问题
                forward = "error";
                //add end by cwx456134 2017-4-23 DTS2017041809030 历史问题
                
            	setErrormessage("您尚未开通任何梦网业务");
                if ("Order".equals(operType))
                {
                    // 创建错误日志
                    this.createRecLog(curMenuId, "0", "0", "0", "查询成功，但是用户没有开通任何梦网业务。");
                }
                else
                {
                    // 创建错误日志
                    this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "0", "查询成功，但是用户没有开通任何梦网业务。");
                }
            }
        }
        else
        {
            String resultMsg = getConvertMsg((String) result.get("returnMsg"), "zz_04_20_000001", 
                    String.valueOf(result.get("errcode")), new String[]{"查询", "已开通SP业务"});
            
            // 设置错误信息
            setErrormessage(resultMsg);
            forward = "error";
            
            if ("Order".equals(operType))
            {
                // 创建错误日志
                this.createRecLog(curMenuId, "0", "0", "1", resultMsg);
            }
            else
            {
                // 创建错误日志
                this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "1", resultMsg);
            }
        }
		return forward;
	}
    
	/**
     * 梦网业务查询
     * 
     * @return
     */
    public String querySP()
    {
        logger.debug("querySP start!");
        
        String forward = "error";
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        if(pagecount != null)
        {
            this.getRequest().setAttribute("pagecount", pagecount.split(",")[0]);
        }
        
        // modify begin jWX216858 2014/08/07 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        /*if (CurMenuid == null)
        {
            CurMenuid = "";
        }*/
        curMenuId = curMenuId == null ? "" : curMenuId;
        // modify begin jWX216858 2014/08/07 V200R003C10LG0801 OR_huawei_201408_93   圈复杂度_自助终端（二阶段）
        
        // modify begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
        Map result = spBean.queryService(terminalInfoPO, customer, curMenuId, "0");
        forward = this.parseResult(result);
        // modify begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
        
        // 增加湖北全网梦网业务订购功能
        String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
        if(Constants.PROOPERORGID_HUB.equals(province) && "Order".equals(operType))
        {
        	// 从自助终端数据库中查询用户可订购的全网梦网业务
        	availSPService = spService.qryAvailSP();
        	
        	// 过滤掉用户已经订购的sp业务,过滤条件为spid和spbizid同时相同。
        	if (spservice != null)
        	{
            	CRSet spserviceData = (CRSet)spservice.get(1);
            	if(availSPService != null && spserviceData != null && availSPService.size() >0 && spserviceData.GetRowCount() >0)
            	{
            		for(int i=0;i<spserviceData.GetRowCount();i++)
            		{
            			String spid = spserviceData.GetValue(i, 1);  
            			String spBizid = spserviceData.GetValue(i, 3);
            			for(int j=0;j<availSPService.size();j++)
            			{
            				SpAvailPO spAvailPO = availSPService.get(j);
            				String spcode = spAvailPO.getSpcode();
            				String spbizcode = spAvailPO.getOperatorCode();
            				if(spid.equals(spcode) && spBizid.equals(spbizcode))
            				{
            					availSPService.remove(j);
            				}
            			}
            		}
            	}
        	}
        	
            // 调整计费方式，费用和时间的显示格式
        	this.setBillFlag();
        	return "spAvailList";
        }

        logger.debug("querySP end!");
        return forward;
        
    }
    
    private String spName;
    
    private String spBizName;
    
    private String price;
    
    private String billFlagName;
    
    private String status;
    
    private String spId;
    
    private String spBizCode;
    
    private String bizType;
    
    private String domain;
    
    private String dealType;
    
    private String startTime;

    /**
     * 退订梦网业务
     * 
     * @return
     */
    public String cancelSP()
    {
        logger.debug("cancelSP start"); 
       
        String forward = "error";
        
        // add begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        this.getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        //判断当前菜单是否为空
        curMenuId = (curMenuId == null) ? "" : curMenuId;
        
        //modify begin g00140516 2011/11/05 R003C11L11n01 BUG_HUB_201111_24
        /**
         * 业务类型根据operType值来判断，如果值为Order则为订购，如果值为Cancel则为退订。
         */
        Map result = null;
        String cancelType = "";
        String effectType = "";
        String operInfo = "";
        if("Cancel".equals(operType))
        {
        	cancelType = "3";
        	operInfo = "退订";
        	
        	result = spBean.cancelService(terminalInfoPO, customer, 
        			curMenuId,operType, cancelType, dealType, domain, spId, spBizCode, bizType, effectType);
        }
        else if("Order".equals(operType))
        {
        	// modify begin sWX219697 2014-6-30 16:36:11 OR_HUB_201406_1115_湖北跨运营商携号转网
            String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

        	//湖北若为携号转网携入用户，需判断要订购的业务是否在白名单中
        	if(Constants.PROOPERORGID_HUB.equals(province) 
        			&& "sbsnTransTelIn".equalsIgnoreCase(customer.getSignType()))
        	{
        		//查询sp业务白名单
        		SpAvailPO spPO = new SpAvailPO();
        		
        		//sp企业编码
        		spPO.setSpcode(spId);
        		
        		//sp业务编码
        		spPO.setOperatorCode(spBizCode);
        		
        		//sp业务类型
        		spPO.setServType(bizType);
        		
        		
        		//该业务不在白名单，暂时无法订购
        		if (spService.authWhiteList(spPO) <= 0)
        		{
        			//获取提示信息，若缓存中没有，则采用默认
        			String msg = (String) PublicCache.getInstance().getCachedData(Constants.TRANSIN_MSG);
        			String showMsg = (null == msg || "".equals(msg)) ? 
        					"非常抱歉，您目前不能办理此业务，详情咨询10086。" : msg;
        			setSuccessMessage(showMsg);
        			this.createRecLog(curMenuId, "0", "0", "1", "携入用户订购的该业务不在白名单中");
        			return "success";
        		}
        		
        		//要订购的业务在白名单中，可以订购
        		cancelType = "3";
                effectType = "0";
                operInfo = "订购";
                
                result = spBean.orderSPService(terminalInfoPO, customer, 
                		curMenuId, operType, cancelType, domain, spId, spBizCode, bizType, effectType);
        		
        	}
        	
        	//非湖北用户或非携号转网用户则可以直接订购
        	else
        	{
                cancelType = "3";
                effectType = "0";
                operInfo = "订购";
                
                result = spBean.orderSPService(terminalInfoPO, customer, 
                		curMenuId, operType, cancelType, domain, spId, spBizCode, bizType, effectType);
            
        	}
        	// modify end sWX219697 2014-6-30 16:36:11 OR_HUB_201406_1115_湖北跨运营商携号转网
            
        }
        
        // modify begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
        if (result != null && result.size() > 2)
        {
            forward = "success";
            
            //设置成功信息
            setSuccessMessage("成功"+ operInfo + spBizName + "业务");
            
            // modify begin cKF76106 2012/09/11 OR_NX_201209_258
            CTagSet tagSet = (CTagSet) result.get("returnObj");
            
            // modify begin sWX219697 2014-6-30 OR_HUB_201406_1115_湖北跨运营商携号转网(减少圈复杂度)
            // 业务受理流水号
            boolean b = (null != tagSet && null != tagSet.get("formnum"));
            String recFormnum = b ? (String)tagSet.get("formnum") : "0";
            
            //记录日志业务类型
            String busiType = "Order".equals(operType) ? curMenuId : Constants.BUSITYPE_SUBSCANCELSP;
            
            //业务流水号
            String formNum = "Order".equals(operType) ? "0" : recFormnum;
            
            //保存订购日志
            this.createRecLog(busiType, formNum, "0", "0","业务受理成功!");
            
            // modify end cKF76106 2012/09/11 OR_NX_201209_258
            // modify begin sWX219697 2014-6-30 OR_HUB_201406_1115_湖北跨运营商携号转网(减少圈复杂度)

        }
        else
        {
            String[] params = new String[]{operInfo, spBizName + "业务"};
                        
            String resultMsg = "";
            String errCode = "";
            
            if (result != null)
            {
                resultMsg = (String) result.get("returnMsg");
                errCode = String.valueOf(result.get("errcode"));
            }
            else
            {
                resultMsg = operInfo + spBizName + "业务失败";
            }
            
            resultMsg = getConvertMsg(resultMsg, "zz_04_20_000001", errCode, params);         
            
            //设置失败信息
            setErrormessage(resultMsg);
            
            // modify begin sWX219697 2014-6-30 OR_HUB_201406_1115_湖北跨运营商携号转网(减少圈复杂度)
            //记录日志业务类型
            String busiType = "Order".equals(operType) ? curMenuId : Constants.BUSITYPE_SUBSCANCELSP;
            
            //记录失败日志
            this.createRecLog(busiType, "0", "0", "1", resultMsg);
            // modify end sWX219697 2014-6-30 OR_HUB_201406_1115_湖北跨运营商携号转网(减少圈复杂度)
          
        }
        // modify begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
        //modify end g00140516 2011/11/05 R003C11L11n01 BUG_HUB_201111_24
        
        logger.debug("cancelSP end!");
        return forward;
        
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getPagecount()
    {
        return pagecount;
    }
    
    public void setPagecount(String pagecount)
    {
        this.pagecount = pagecount;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String essageerrormessage)
    {
        this.errormessage = essageerrormessage;
    }
    
    public Vector getSpservice()
    {
        return spservice;
    }
    
    public void setSpservice(Vector spservice)
    {
        this.spservice = spservice;
    }
    
    public SPBean getSpBean()
    {
        return spBean;
    }
    
    public void setSpBean(SPBean spBean)
    {
        this.spBean = spBean;
    }

    public String getSpName()
    {
        return spName;
    }

    public void setSpName(String spName)
    {
        this.spName = spName;
    }

    public String getSpBizName()
    {
        return spBizName;
    }

    public void setSpBizName(String spBizName)
    {
        this.spBizName = spBizName;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getBillFlagName()
    {
        return billFlagName;
    }

    public void setBillFlagName(String billFlagName)
    {
        this.billFlagName = billFlagName;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getSpId()
    {
        return spId;
    }

    public void setSpId(String spId)
    {
        this.spId = spId;
    }

    public String getSpBizCode()
    {
        return spBizCode;
    }

    public void setSpBizCode(String spBizCode)
    {
        this.spBizCode = spBizCode;
    }

    public String getBizType()
    {
        return bizType;
    }

    public void setBizType(String bizType)
    {
        this.bizType = bizType;
    }

    public String getDomain()
    {
        return domain;
    }

    public void setDomain(String domain)
    {
        this.domain = domain;
    }

    public String getDealType()
    {
        return dealType;
    }

    public void setDealType(String dealType)
    {
        this.dealType = dealType;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getSuccessMessage()
    {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }

	public String getOperType() 
	{
		return operType;
	}

	public void setOperType(String operType) 
	{
		this.operType = operType;
	}
	
    public SpService getSpService() 
    {
		return spService;
	}

	public void setSpService(SpService spService) 
	{
		this.spService = spService;
	}

	public List getAvailSPService() 
	{
		return availSPService;
	}

	public void setAvailSPService(List availSPService) 
	{
		this.availSPService = availSPService;
	}

    public String getDinggouStatus()
    {
        return dinggouStatus;
    }

    public void setDinggouStatus(String dinggouStatus)
    {
        this.dinggouStatus = dinggouStatus;
    }
}
