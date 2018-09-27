/*
* @filename: SPRecNXAction.java
* @  All Right Reserved (C), 2012-2112, HUAWEI TECO CO.
* @brif:  梦网业务订购
* @author: g00140516
* @de:  2012/07/06 
* @description: 
* @remark: create g00140516 2012/07/06 R003C12L07n01 OR_NX_201205_649
*/
package com.customize.nx.selfsvc.reception.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.baseService.spService.model.SpAvailPO;
import com.gmcc.boss.selfsvc.baseService.spService.service.SpService;
import com.gmcc.boss.selfsvc.bean.SPBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.CurrencyUtil;

/**
 * 
 * 梦网业务订购
 * 
 * @author  g00140516
 * @version  1.0, 2012/07/06
 * @see  
 * @since  
 */
public class SPRecNXAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    /**
     * 当前菜单
     */
    private String curMenuId;
    
    /**
     * 企业代码
     */
    private String spId;
    
    /**
     * 业务代码
     */
    private String spBizCode;
    
    /**
     * 业务类型
     */
    private String bizType;
    
    /**
     * 业务平台
     */
    private String domain;
    
    /**
     * 业务名称
     */
    private String spBizName;
    
    /**
     * 成功信息
     */
    private String successMessage;
    
    /**
     * 可订购的梦网业务列表
     */
    private List<SpAvailPO> availSPService = null;
    
    private SPBean spBean;
    
    private SpService spService;
    
    // add begin cKF76106 2012/11/05 R003C12L11n01 OR_NX_201210_1335
    // 页数
    private int pageCount;
    
    // 每页显示容量
    private int pageSize;
    
    // 第几页
    private int page = 0;
    // add end cKF76106 2012/11/05 R003C12L11n01 OR_NX_201210_1335
    
    /**
     * 获取可订购的SP业务列表
     * 
     * @return
     * @see 
     */
    public String qryAvailableSP()
    {
        String forward = "error";
        
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        
        // 终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
        
        // 客户信息
        NserCustomerSimp customer = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
        
        // 从数据库中查询可订购的梦网业务
        availSPService = spService.qryAvailSP();
        //if (null == availSPService || availSPService.size() == 0)
        if (isEmpty(availSPService))
        {
            request.setAttribute("errormessage", "抱歉，没有可订购的梦网业务");
            
            this.createRecLog(curMenuId, "0", "0", "1", "没有可订购的梦网业务");
            
            return forward;
        }
        
        // 查询用户已订购的梦网业务
        Map result = spBean.queryService(terminalInfoPO, customer, curMenuId, "0", true);        
        if (null != result && result.size() > 1)
        {
            CRSet crset = (CRSet) result.get("returnObj");
            
            // 从可订购的业务列表里面去掉用户已订购的
            if (null != crset && crset.GetRowCount() > 0)
            {
                String spid = "";
                String spBizid = "";
                
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    spid = crset.GetValue(i, 1);  
                    spBizid = crset.GetValue(i, 3);
                    
                    SpAvailPO spAvailPO = null;
                    
                    for (int j = 0; j < availSPService.size(); j++)
                    {
                        spAvailPO = availSPService.get(j);

                        if (spid.equals(spAvailPO.getSpcode()) && spBizid.equals(spAvailPO.getOperatorCode()))
                        {
                            availSPService.remove(j);
                        }
                        
                        break;
                    }
                }                
            }

            // 没有可订购的梦网业务列表
            //if (null == availSPService || availSPService.size() == 0)
            if (isEmpty(availSPService))
            {
                request.setAttribute("errormessage", "抱歉，没有可订购的梦网业务");
                
                this.createRecLog(curMenuId, "0", "0", "1", "没有可订购的梦网业务");
            }
            else
            {
                SpAvailPO spAvailPO = null;
                
                for (int k = 0; k < availSPService.size(); k++)
                {
                    spAvailPO = availSPService.get(k);
                 // add begin zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
//                    if ("0".equals(spAvailPO.getBillFlag()))
//                    {
//                        spAvailPO.setBillFlag("免费");
//                    }
//                    else if ("1".equals(spAvailPO.getBillFlag()))
//                    {
//                        spAvailPO.setBillFlag("按条");
//                    }
//                    else if("2".equals(spAvailPO.getBillFlag()))
//                    {
//                        spAvailPO.setBillFlag("包月");
//                    }
                    spAvailPO.setBillFlag(getBillFlagName(spAvailPO));
                    // add end zKF66389 2014/07/17 R003C10L07n01 OR_huawei_201407_371 圈复杂度_自助终端 
                    
                    spAvailPO.setFee(CurrencyUtil.divide(spAvailPO.getFee(), "1000"));
                    spAvailPO.setExpireDate(CommonUtil.formatDate(spAvailPO.getExpireDate(), 
                            Constants.DATE_PATTERN_YYYYMMDD, Constants.DATE_PATTERN_YYYYMMDD_1));
                    
                    availSPService.set(k, spAvailPO);
                }
                
                // add begin cKF76106 2012/11/05 R003C12L11n01 OR_NX_201210_1335
                this.getRequest().setAttribute("recordCount", availSPService.size());

                pageSize = 5;
                
                availSPService = getPageList(availSPService);
                // add end cKF76106 2012/11/05 R003C12L11n01 OR_NX_201210_1335

                forward = "availableSP";
            }            
        }
        else
        {
            request.setAttribute("errormessage", "查询已订购梦网业务失败，请稍后再试。给您带来的不便，敬请原谅。");
            
            this.createRecLog(curMenuId, "0", "0", "1", "查询已订购梦网业务失败");
        }
        
        return forward;
    }

    /**
     * 计费类型中文含义
     * <功能详细描述>
     * @param spAvailPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getBillFlagName(SpAvailPO spAvailPO)
    {
        if ("0".equals(spAvailPO.getBillFlag()))
        {
            return "免费";
        }
        else if ("1".equals(spAvailPO.getBillFlag()))
        {
            return "按条";
        }
        else if("2".equals(spAvailPO.getBillFlag()))
        {
            return "包月";
        }
        
        return "";
    }
    
    private boolean isEmpty(List list)
    {
        return null == list || list.size() == 0;
    }
    
    /**
     * 订购业务
     * 
     * @return
     * @see 
     */
    public String orderSP()
    {
        String forward = "error";
        
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        
        request.setAttribute("backStep", "-1");
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
            
        Map result = spBean.orderSPService(terminalInfoPO, customer, curMenuId, "Order", "3", domain, 
                spId, spBizCode, bizType, "0");              
        if (result != null && result.size() > 2)
        {
            forward = "success";
            
            //设置成功信息
            successMessage = "成功订购" + spBizName + "业务";
            
            // modify begin cKF76106 2012/09/11 OR_NX_201209_258
            CTagSet tagSet = (CTagSet) result.get("returnObj");
            // 业务受理流水号
            String recFormnum = "0";
            if(null != tagSet && null != tagSet.get("formnum"))
            {
                recFormnum = (String)tagSet.get("formnum");
            }
            //记录日志
            this.createRecLog(curMenuId, recFormnum, "0", "0", "成功订购" + spBizName + "业务"); 
            // modify end cKF76106 2012/09/11 OR_NX_201209_258
        }
        else if(result != null)
        {
            //设置失败信息
            request.setAttribute("errormessage", (String)result.get("returnMsg"));
            
            //记录日志
            this.createRecLog(curMenuId, "0", "0", "1", (String)result.get("returnMsg"));                     
        }
        else
        {
            //设置失败信息
            request.setAttribute("errormessage", "订购" + spBizName + "业务失败");
            
            //记录日志
            this.createRecLog(curMenuId, "0", "0", "1", "订购" + spBizName + "业务失败");                        
        }
        
        return forward;        
    }
    
    
    /**
     * 分页
     * 
     * @param list 菜单集合
     * @return
     * @see
     */
    public List<SpAvailPO> getPageList(List<SpAvailPO> list)
    {
        int sum=0;
        int start=0;
        int end=0;
        
        // 获取当前页
        if (page == 0)
        {
            page = 1;
        }
        
        // 获取总页数
        if (!list.isEmpty())
        {
            sum=list.size();
            pageCount = list.size() / pageSize;
            if (list.size() % pageSize > 0)
            {
                pageCount = pageCount + 1;
            }
        }
        else
        {
            pageCount = 0;
        }
        
        start = (page - 1) * pageSize;
        if (page >= pageCount)
        {
            end = sum;
        }else{
            end = page * pageSize;
        }
        
        List<SpAvailPO> spList = new ArrayList<SpAvailPO>();
        
        for (int i = start; i < end; i++)
        {
            spList.add(list.get(i));
        }
        return spList;
    }

    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public List<SpAvailPO> getAvailSPService()
    {
        return availSPService;
    }

    public void setAvailSPService(List<SpAvailPO> availSPService)
    {
        this.availSPService = availSPService;
    }

    public SPBean getSpBean()
    {
        return spBean;
    }

    public void setSpBean(SPBean spBean)
    {
        this.spBean = spBean;
    }

    public SpService getSpService()
    {
        return spService;
    }

    public void setSpService(SpService spService)
    {
        this.spService = spService;
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

    public String getSpBizName()
    {
        return spBizName;
    }

    public void setSpBizName(String spBizName)
    {
        this.spBizName = spBizName;
    }

    public String getSuccessMessage()
    {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public int getPage()
    {
        return page;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public void setPage(int page)
    {
        this.page = page;
    }
}
