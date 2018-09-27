package com.gmcc.boss.selfsvc.baseService.spService.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.baseService.spService.model.SpInforPO;
import com.gmcc.boss.selfsvc.bean.SPBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class RecSPAction extends BaseAction
{
    
    private static Log logger = LogFactory.getLog(SPAction.class);
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单
    private String curMenuId;
    
    // 页数
    private int pageCount;
    
    // 每页显示容量
    private int pageSize;
    
    // 第几页
    private int page = 0;
    
    // 错误信息
    private String errormessage;
    
    //成功信息
    private String successMessage;
    
    // 接口调用
    private SPBean spBean;
    
    // 查询结果
    List<SpInforPO> spList = new ArrayList<SpInforPO>();

    /**
     * 梦网业务查询
     * 
     * @return
     */
    public String querySP()
    {
        logger.debug("querySP start!");
        
        String forward = null;
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // modify begin cKF76106 2012/11/05 R003C12L11n01 OR_NX_201210_1335
        
        if (curMenuId == null)
        {
        	curMenuId = "";
        }
        
        // modify begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
        Map result = spBean.queryService(terminalInfoPO, customer, curMenuId, "0");
        if (result != null && result.size() > 2)
        {
            CRSet crset = (CRSet)result.get("returnObj");
            if (crset != null && crset.GetRowCount() > 0)
            {
                // 所有业务
                for (int i = crset.GetRowCount() - 1; i >= 0; i--)
                {
                    crset.SetValue(i, 13, getPrice(i, crset));
                    // //过滤，只留下梦网业务
                    // if ("1".equals(crset.GetValue(i, 0))
                    // || "21".equals(crset.GetValue(i, 0)))
                    // {
                    // String price = "";
                    // String feeType = crset.GetValue(i, 6);
                    // if ("包月计费".equals(feeType))
                    // {
                    // String fee = (String)crset.GetValue(i, 7);
                    // price = Double.parseDouble(fee)/1000 + "元/月";
                    // }
                    // else if ("免费".equals(feeType))
                    // {
                    // price = "免费";
                    // }
                    // else
                    // {
                    // price = crset.GetValue(0, 13);
                    //                            
                    // }
                    // if (crset.GetValue(i, 12) != null && crset.GetValue(i, 12).trim().length() > 0)
                    // {
                    // price += "(" + crset.GetValue(i, 12) +")";
                    // }
                    // crset.SetValue(i, 13, price);
                    //                        
                    // if (resultSet.GetRowCount() == 0)
                    // {
                    // resultSet = new CRSet(crset.GetColumnCount());
                    // }
                    //                        
                    // resultSet.AddRow();
                    // for (int j = 0; j < crset.GetColumnCount(); j++)
                    // {
                    // resultSet.SetValue(resultSet.GetRowCount() - 1, j, crset.GetValue(i, j));
                    // }
                    // }
                }
            }
            
            if (crset != null && crset.GetRowCount() > 0)
            {
                forward = "qrySPinfo";
                spList.clear();
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    SpInforPO spPo = new SpInforPO();

                    // 对象类型
                    spPo.setDealType(crset.GetValue(i, 0));
                    // 对象编码
                    spPo.setSpID(crset.GetValue(i, 1));
                    // 供应商
                    spPo.setSpName(crset.GetValue(i, 2));
                    // 业务代码
                    spPo.setSpBizID(crset.GetValue(i, 3));
                    // 业务名称
                    spPo.setSpBizName(crset.GetValue(i, 4));
                    // 业务类型
                    spPo.setSpBizType(crset.GetValue(i, 5));
                    // 平台类型
                    spPo.setDomain(crset.GetValue(i, 8));
                    // 开通时间
                    spPo.setStartTime(crset.GetValue(i, 9));
                    // 资费
                    spPo.setPrice(crset.GetValue(i, 13));
                    
                    spList.add(spPo);
                }
                
                this.getRequest().setAttribute("recordCount", spList.size());

                pageSize = 5;
                
                spList = getPageList(spList);
            }
            // modify end cKF76106 2012/11/05 R003C12L11n01 OR_NX_201210_1335
            
            else
            {
                // 设置错误信息
                setErrormessage("您尚未开通任何梦网业务。");
                forward = "error";
                
                // 创建错误日志
                this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "0", "查询成功，但是用户没有开通任何梦网业务。");
            }
        }
        else
        {
            String resultMsg = getConvertMsg((String) result.get("returnMsg"), "zz_04_20_000001", 
                    String.valueOf(result.get("errcode")), new String[]{"查询", "已开通SP业务"});
            
            // 设置错误信息
            setErrormessage(resultMsg);
            forward = "error";
            
            // 创建错误日志
            this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "1", resultMsg);
        }
        // modify end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
        
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
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        //判断当前菜单是否为空
        if (curMenuId == null)
        {
        	curMenuId = "";
        }
        
        //定义操作类型
        String operType = "Cancel";
        
        /*
         * 1-根据SPID进行退订;
         * 2-退订用户订购的所有SP业务; 
         * 3-根据SPID、SPBIZID退订; 
         * 4-根据spbiztype进行退订; 
         * 5-根据DOMAIN进行退订
         */
        String cancelType = "3";
        
        String effectType = "";
        
        // modify begin g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
        Map result = spBean.cancelService(terminalInfoPO, customer, curMenuId,operType, cancelType, dealType, domain, spId, spBizCode, bizType, effectType);
        if (result != null && result.size() > 2)
        {
            forward = "success";
            
            //设置成功信息
            setSuccessMessage("成功退订" + spBizName + "业务");
            
            //记录成功信息
            this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "0","业务受理成功!");
        }
        else
        {
            String[] params = new String[]{"退订", spBizName + "业务"};
            
            String resultMsg = "";
            String errCode = "";
            
            if (result != null)
            {
                resultMsg = (String) result.get("returnMsg");
                errCode = String.valueOf(result.get("errcode"));
            }
            else
            {
                resultMsg = "退订" + spBizName + "业务失败";
            }
            
            resultMsg = getConvertMsg(resultMsg, "zz_04_20_000001", 
                    errCode, params);
            
            //设置失败信息
            setErrormessage(resultMsg);
            
            // add begin g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            getRequest().setAttribute("backStep", "-1");
            // add end g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            
            //记录失败信息
            this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "1", resultMsg);
        }
        // modify end g00140516 2012/08/07 R003C12L08n01 湖北电子渠道二期提示信息改造
        
        logger.debug("cancelSP end!");
        return forward;
    }
    
    /**
     * 获取梦网业务费用信息
     * 
     * @param i
     * @param crset
     * @return
     */
    private String getPrice(int i, CRSet crset)
    {
        String price = "";
        String feeType = crset.GetValue(i, 6);
        if ("1".equals(crset.GetValue(i, 0)))
        {
            if ("包月计费".equals(feeType))
            {
                String fee = (String)crset.GetValue(i, 7);
                price = Double.parseDouble(fee) / 1000 + "元/月";
                
            }
            else if ("免费".equals(feeType))
            {
                price = "免费";
            }
            else
            {
                price = (String)crset.GetValue(i, 13);
            }
        }
        
        if ("21".equals(crset.GetValue(i, 0)))
        {
            if ("包月计费".equals(feeType))
            {
                String fee = (String)crset.GetValue(i, 7);
                price = Double.parseDouble(fee) / 1000 + "元/月";
                
            }
            else if ("免费".equals(feeType))
            {
                price = "免费";
            }
            else
            {
                price = (String)crset.GetValue(i, 13);
            }
        }
        
        if ("22".equals(crset.GetValue(i, 0)) || "31".equals(crset.GetValue(i, 0)) || "32".equals(crset.GetValue(i, 0)))
        {
            price = (String)crset.GetValue(i, 13);
        }
        
        if (crset.GetValue(i, 12) != null && crset.GetValue(i, 12).trim().length() > 0)
        {
            price += "(" + crset.GetValue(i, 12) + ")";
        }
        
        return price;
    }
    
    
    /**
     * 分页
     * 
     * @param list 菜单集合
     * @return
     * @see
     */
    public List<SpInforPO> getPageList(List<SpInforPO> list)
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
        
        List<SpInforPO> spList = new ArrayList<SpInforPO>();
        
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

	public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String essageerrormessage)
    {
        this.errormessage = essageerrormessage;
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

    public List<SpInforPO> getSpList()
    {
        return spList;
    }

    public void setSpList(List<SpInforPO> spList)
    {
        this.spList = spList;
    }
}
