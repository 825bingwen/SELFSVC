/*
 * 文件名： ProdChangeAction.java
 * 描述： 资费套餐变更action类
 * 创建人：jWX216858
 */
package com.customize.sd.selfsvc.prodChange.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.ProdChangeBean;
import com.customize.sd.selfsvc.prodChange.model.ProdChangePO;
import com.customize.sd.selfsvc.prodChange.model.ProdInfoPO;
import com.customize.sd.selfsvc.prodChange.model.ProdTemplatePO;
import com.customize.sd.selfsvc.prodChange.service.ProdChangeService;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.model.OfferInfoVO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;


public class ProdChangeSDAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
	//private static final Logger log = Logger.getLogger(ProdChangeSDAction.class);
	private static Log log = LogFactory.getLog(ProdChangeSDAction.class);
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_升级自助终端的log4j版本
	
	/**
	 * 地区
	 */
	private String region = "";
	
	/**
	 * 可转换主体产品列表
	 */
	private List<ProdChangePO> prodChangeList = new ArrayList<ProdChangePO>();
	
	/**
	 * 产品模板列表
	 */
	private List<ProdTemplatePO> prodTemplateList = new ArrayList<ProdTemplatePO>();

	/**
	 * 需开通的业务列表
	 */
	private List<ProdInfoPO> openProdList = new ArrayList<ProdInfoPO>();
	
	/**
	 * 需取消的业务列表
	 */
	private List<ProdInfoPO> cancelProdList = new ArrayList<ProdInfoPO>();
	
	/**
	 * 需保留的业务列表
	 */
	private List<ProdInfoPO> reserveProdList = new ArrayList<ProdInfoPO>();
	
	/**
	 * 当前菜单Id
	 */
	private String curMenuId = "";
	
	/**
	 * 新的产品编码
	 */
	private String newProdId = "";
	
	/**
	 * 接口bean
	 */
	private ProdChangeBean prodChangeBean;
	
	/**
	 * 新套餐名称
	 */
	private String newProdName = "";
	
	/**
	 * 模板编码
	 */
	private String templateId;
	
	/**
	 * 模板名称
	 */
	private String templateName;
	
	/**
	 * 改变自有套餐的档次 
	 * 1：是（成功页面展示新套餐为档次名称） 其它：否 
	 */
	private String chgSelfLevel;
	
	/**
	 * 客户信息
	 */
	private NserCustomerSimp customer;
	
	/**
	 * 转出的主体产品ID
	 */
	private String oldProdId;
	
	/**
	 * 套餐资费变更service
	 */
	private ProdChangeService prodChangeService;
	
	// add begin jWX216858 2015-6-16 OR_SD_201505_294 关于对MO包月客户变更业务时增加提醒的需求
	/**
	 * MO套餐退订提示
	 */
	private String retMOPrivTips;
	// add end jWX216858 2015-6-16 OR_SD_201505_294 关于对MO包月客户变更业务时增加提醒的需求
	
	/**
     * 查询可转换的主体产品信息列表
     * 
     * @return
     * @see [类、类#方法、类#成员]
     * @remark modify by hWX5316476 2015-1-5 OR_SD_201411_411_SD_关于自助终端产品受理功能优化的需求
     */
	public String qryMainProdChangeInfoList()
	{
		log.debug("qryMainProdChangeInfoList start!");
		
		String forward = "error";
		
		HttpServletRequest request = getRequest();
		
		// 获取终端机信息
		TerminalInfoPO termInfoPO = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
		
		// 获取客户信息
		customer = (NserCustomerSimp) request.getSession().getAttribute(Constants.USER_INFO);
		
		// modify begin wWX217192 2014-10-16 OR_SD_201407_1310 ISSS:ISSS平台营销活动产品配置功能扩展
		
		// 智能营销平台返回的产品信息
    	OfferInfoVO offerInfo = (OfferInfoVO) request.getSession().getAttribute("ISSS_" + customer.getServNumber() + "_" + curMenuId);
    	
    	// 若智能营销平台中的session中包含产品返回信息，则用户直接进入选择模板页面
		if(null != offerInfo)
		{
			// 设置产品编码的参数
			newProdId = offerInfo.getOfferCode();
			return "mainProdTemplate";
		}
    	
		// modify end wWX217192 2014-10-16 OR_SD_201407_1310 ISSS:ISSS平台营销活动产品配置功能扩展
		
		try
        {
		    // 调用接口查询可转换主体产品信息列表
	        prodChangeList = prodChangeBean.qryMainProdInfo(termInfoPO, customer, curMenuId);
	        
	        // 查询用户已办理的主体产品信息
            ProdChangePO prodChangeSelfPO = prodChangeBean.qryProdInfoById(termInfoPO, customer, curMenuId);
            
            if(null != prodChangeSelfPO)
            {
                prodChangeList.add(prodChangeSelfPO);
            }
            
            if(prodChangeList.size() == 0)
            {
                // 记录失败日志
                this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "0", "没有可转换的主体产品");
                request.setAttribute("errormessage", "对不起，没有可转换的主体产品");
            }
            else
            {
                // 记录成功日志
                this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "0", "查询可转换的主体产品信息列表成功");
                forward = "qryProdChangeInfoList"; 
            }
        }
        catch (Exception e)
        {
            // 记录失败日志
            this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "1", e.getMessage());
            request.setAttribute("errormessage", e.getMessage());
        }
		
		log.debug("qryMainProdChangeInfoList End!");
		
		return forward;
	}
	
	/**
	 * 主体产品模板信息列表
	 * @return
	 * @remark modify by hWX5316476 2015-1-5 OR_SD_201411_411_SD_关于自助终端产品受理功能优化的需求
	 */
	public String mainProdTemplateList()
	{
		log.debug("mainProdTemplateList start!");
		
		String forward = "error";
		
		HttpServletRequest request = getRequest();
		
		// 获取终端机信息
		TerminalInfoPO termInfoPO = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
		
		// 获取客户信息
		customer = (NserCustomerSimp) request.getSession().getAttribute(Constants.USER_INFO);
		
		try
        {
		    oldProdId = customer.getProductID();
		    
		    // 如果选择的主体产品为已办理的主体产品，查询组内可转换的档次列表
		    if(oldProdId.equals(newProdId))
	        {
	            // 查询组内可转换的档次列表
		        prodTemplateList = prodChangeService.qryLevelByProdId(customer,newProdName);
	        }
	        else
	        {
	            // 调用接口查询主体产品模板信息列表
	            prodTemplateList = prodChangeBean.mainProdTemplateInfo(termInfoPO, customer, curMenuId, newProdId);
	        }
		    
		    // 记录成功日志
            this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "0", "查询主体产品模板信息列表成功");
		    
		    if(null == prodTemplateList || 0 == prodTemplateList.size())
		    {
	            request.setAttribute("errormessage", "对不起，没有对应的主体产品模板信息"); 
		    }
		    else
		    {
		        forward = "mainProdTemplate";
		    }
        }
        catch (Exception e)
        {
            // 记录失败日志
            this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "1", e.getMessage());
            request.setAttribute("errormessage", e.getMessage());
            forward = ERROR;
        }
		
		log.debug("mainProdTemplateList end!");
		
		return forward;
	}
	
	/**
	 * 查询产品变更确认信息，列出开通的业务、取消的业务、保留的业务
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String prodChangeValidateInfo()
	{
		log.debug("prodChangeValidateInfo start!");
		
		HttpServletRequest request = getRequest();
		
		// 获取终端机信息
		TerminalInfoPO termInfoPO = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
		
		// 获取客户信息
		customer = (NserCustomerSimp) request.getSession().getAttribute(Constants.USER_INFO);
		
		// 判断是够为主体产品预处理
		boolean pretreatment = true;
		
		String forward = "error";
		
		// 调用接口查询产品变更确认信息
		Map<String, Object> mapResult = prodChangeBean.mainProdChangeRec(termInfoPO, customer, curMenuId, pretreatment, templateId, newProdId);
		
		// 取出调用接口返回的信息
		if (null != mapResult && 1 < mapResult.size())
		{
			Vector<Object> retData = (Vector<Object>) mapResult.get("returnObj"); 
			
			// add begin jWX216858 2015-6-16 OR_SD_201505_294 关于对MO包月客户变更业务时增加提醒的需求
			CTagSet ctagSet = (CTagSet) retData.get(0);
			
			// 获取MO套餐退订提示信息
			retMOPrivTips = ctagSet.GetValue("RetMOPrivTips");
			// add end jWX216858 2015-6-16 OR_SD_201505_294 关于对MO包月客户变更业务时增加提醒的需求
			
			CRSet crset = (CRSet) retData.get(1);
			
			// 遍历CRSet
			for (int i = 0; i < crset.GetRowCount(); i++)
			{
				ProdInfoPO prodInfoPO = new ProdInfoPO();
				
				// 套餐名称
				prodInfoPO.setProdname(crset.GetValue(i, 2));
				
				// 优惠名称
				prodInfoPO.setPrivname(crset.GetValue(i, 7));
				
				// 编辑状态  A 新增的 O 保留的 D 删除的
				String status = crset.GetValue(i, 0);
				
				// 新增的业务
				if ("A".equals(status))
				{
					openProdList.add(prodInfoPO);
				}
				else if ("O".equals(status))// 保留的业务
				{
					reserveProdList.add(prodInfoPO);
				}
				else if ("D".equals(status))// 取消的业务
				{
					cancelProdList.add(prodInfoPO);
				}
			}
			// 记录成功日志
			this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "0", "查询产品变更确认信息成功");
			forward = "prodChangeValidateInfo";
		}
		else if (null != mapResult)
		{
			// 记录失败日志
			this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "1", (String)mapResult.get("returnMsg"));
			request.setAttribute("errormessage", (String)mapResult.get("returnMsg"));
		}
		else
		{
			// 记录失败日志
			this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "1", "查询产品变更确认信息失败");
			request.setAttribute("errormessage", "查询产品变更确认信息失败");
		}
		
		log.debug("prodChangeValidateInfo end!");
		
		return forward;
	}
	
	 /**
     * 调用接口执行主体产品转换
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String mainProdChangeSubmit()
    {
		log.debug("mainProdChangeSubmit start!");
		
    	HttpServletRequest request = getRequest();
		
		// 获取终端机信息
		TerminalInfoPO termInfoPO = (TerminalInfoPO) request.getSession().getAttribute(Constants.TERMINAL_INFO);
		
		// 获取客户信息
		customer = (NserCustomerSimp) request.getSession().getAttribute(Constants.USER_INFO);
		
		// 表示执行主体产品变更
		boolean pretreatment = false;
		
		String forward = "error";
		
		// 调用接口执行主体产品转换
		Map<String, Object> mapResult = prodChangeBean.mainProdChangeRec(termInfoPO, customer, curMenuId, pretreatment, templateId, newProdId);
		
		// 取出调用接口返回的信息
		if (null != mapResult &&  mapResult.size() > 1)
		{
			// 记录成功日志
			this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "0", "主体产品转换成功");
			forward = "mainProdChangeSubmit";
		}
		else if (null != mapResult)
		{
			// 记录失败日志
			this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "1", (String)mapResult.get("returnMsg"));
			request.setAttribute("errormessage", (String)mapResult.get("returnMsg"));
		}
		else
		{
			// 记录失败日志
			this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "1", "主体产品转换失败");
			request.setAttribute("errormessage", "执行主体产品转换失败");
		}
		
		log.debug("mainProdChangeSubmit end!");
		return forward;
    }

    /**
     * 组内档次转换
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-1-9 OR_SD_201411_411_SD_关于自助终端产品受理功能优化的需求
     */
    public String chgLevelInGroup()
    {
        try
        {
            customer = this.getCustomerSimp();
            
            // 调用接口进行组内档次转换
            prodChangeBean.chgLevelInGroup(this.getTerminalInfoPO(),  customer, curMenuId, templateId);
            
            // 设置成功页面展示档次名称
            chgSelfLevel = "1";
        }
        catch (Exception e)
        {
            // 记录失败日志
            this.createRecLog(customer.getServNumber(),Constants.BUSITYPE_PRODUCTCHANGE, "0", "0", "1", e.getMessage());
            this.getRequest().setAttribute("errormessage", e.getMessage());
            return ERROR;
        }
        
        return "chgLevelInGroup";
    }

	public String getRegion()
	{
		return region;
	}

	public void setRegion(String region)
	{
		this.region = region;
	}

	public List<ProdChangePO> getProdChangeList()
	{
		return prodChangeList;
	}

	public void setProdChangeList(List<ProdChangePO> prodChangeList)
	{
		this.prodChangeList = prodChangeList;
	}

	public List<ProdTemplatePO> getProdTemplateList()
	{
		return prodTemplateList;
	}

	public void setProdTemplateList(List<ProdTemplatePO> prodTemplateList) 
	{
		this.prodTemplateList = prodTemplateList;
	}

	public List<ProdInfoPO> getOpenProdList() 
	{
		return openProdList;
	}

	public void setOpenProdList(List<ProdInfoPO> openProdList) 
	{
		this.openProdList = openProdList;
	}

	public List<ProdInfoPO> getCancelProdList()
	{
		return cancelProdList;
	}

	public void setCancelProdList(List<ProdInfoPO> cancelProdList)
	{
		this.cancelProdList = cancelProdList;
	}

	public List<ProdInfoPO> getReserveProdList() 
	{
		return reserveProdList;
	}

	public void setReserveProdList(List<ProdInfoPO> reserveProdList)
	{
		this.reserveProdList = reserveProdList;
	}

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getNewProdId()
	{
		return newProdId;
	}

	public void setNewProdId(String newProdId)
	{
		this.newProdId = newProdId;
	}

	public ProdChangeBean getProdChangeBean()
	{
		return prodChangeBean;
	}

	public void setProdChangeBean(ProdChangeBean prodChangeBean) 
	{
		this.prodChangeBean = prodChangeBean;
	}

	public String getNewProdName() 
	{
		return newProdName;
	}

	public void setNewProdName(String newProdName)
	{
		this.newProdName = newProdName;
	}

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public NserCustomerSimp getCustomer()
	{
		return customer;
	}

	public void setCustomer(NserCustomerSimp customer)
	{
		this.customer = customer;
	}

	public String getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}

    public ProdChangeService getProdChangeService()
    {
        return prodChangeService;
    }

    public void setProdChangeService(ProdChangeService prodChangeService)
    {
        this.prodChangeService = prodChangeService;
    }

    public String getOldProdId()
    {
        return oldProdId;
    }

    public void setOldProdId(String oldProdId)
    {
        this.oldProdId = oldProdId;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    public String getChgSelfLevel()
    {
        return chgSelfLevel;
    }

    public void setChgSelfLevel(String chgSelfLevel)
    {
        this.chgSelfLevel = chgSelfLevel;
    }

	public String getRetMOPrivTips() {
		return retMOPrivTips;
	}

	public void setRetMOPrivTips(String retMOPrivTips) {
		this.retMOPrivTips = retMOPrivTips;
	}

}
