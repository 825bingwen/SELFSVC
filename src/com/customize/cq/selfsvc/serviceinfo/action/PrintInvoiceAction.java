package com.customize.cq.selfsvc.serviceinfo.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.cq.selfsvc.bean.FeeChargeBean;
import com.gmcc.boss.common.base.CEntityString;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 发票重打
 * @author z90080209
 * @date   Nov 16, 2011
 */
public class PrintInvoiceAction extends BaseAction
{
    
//    private static Log logger = LogFactory.getLog(PrintInvoiceAction.class);
//    
//    // 序列化
//    private static final long serialVersionUID = 1L;
//    
//    // 当前菜单
//    private String curMenuId;
//    
//    // 页号
//    private String pagecount;
//    
//    // 错误信息
//    private String errorMessage;
//    
//    //成功信息
//    private String successMessage;
//    
//    // 可打印发票结果集
//    private Vector invoiceList;
//    
//    // 调用接口Bean
//    private FeeChargeBean feeChargeBean;
//    
//    /**
//     * 查询可打印发票
//     * @return
//     */
//    public String queryInvoice()
//    {
//        logger.debug("queryInvoice start!");
//        
//        String forward = null;
//        
//        // 获取session
//        HttpSession session = getRequest().getSession();
//        
//        // 获取终端机信息
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 获取客户信息
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        if(pagecount != null)
//        {
//            this.getRequest().setAttribute("pagecount", pagecount.split(",")[0]);
//        }
//        
//        if (curMenuId == null)
//        {
//        	curMenuId = "";
//        }
//        
//        // 提供发票打印功能
//        String canPrintInvoice = terminalInfoPO.getTermspecial().substring(1, 2);
//        if (!"1".equals(canPrintInvoice))
//        {
//        	// 设置错误信息
//        	setErrorMessage("该终端不提供发票打印功能！");
//            return "error";
//        }
//        
//        Date date = new Date();
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//        String payDate = sdf1.format(date);
//        Map resultMap = feeChargeBean.chargeCommit(terminalInfoPO,
//        		curMenuId,
//                customer.getServNumber(),
//                "20",
//                payDate,
//                "",
//                "",
//                "",
//                "1",
//                "",
//                "");
//        if (resultMap != null && resultMap.size() > 1)
//        {
//        	Object obj = resultMap.get("returnObj");
//        	if (obj instanceof Vector)
//            {
//        		// 对发票查询接口的返回值进行解析
//                Vector chargeInfo = (Vector)obj;
//                CRSet crset = (CRSet)chargeInfo.get(1);
//                if (crset != null && crset.GetRowCount() > 0)
//                {
//                    forward = "invoiceList";
//                    // 将得到的结果数据放到页面显示的前提
//                    Vector v = new Vector();
//                    v.add(new CEntityString("业务名称,发票金额,业务时间,操作"));
//                    v.add(crset);
//                    
//                    // 设置结果集
//                    setInvoiceList(v);
//                }
//                else
//                {
//                    // 设置错误信息
//                	setErrorMessage("您没有可打印的发票。");
//                    forward = "error";
//                    
//                    // 创建错误日志
//                    this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "0", "查询成功，但是用户没有可打印的发票。");
//                }
//            }
//        }
//        else
//        {
//        	// 设置错误信息
//        	setErrorMessage("查询可打印发票失败！");
//            forward = "error";
//            
//            // 创建错误日志
//            this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "0", "查询可打印发票失败，请稍后再试。带来不便，敬请原谅。");
//        }
//        
//        logger.debug("queryInvoice end!");
//        return forward;
//    }
//    
//    private String spName;
//    
//    private String spBizName;
//    
//    private String price;
//    
//    private String billFlagName;
//    
//    private String status;
//    
//    private String spId;
//    
//    private String spBizCode;
//    
//    private String bizType;
//    
//    private String domain;
//    
//    private String dealType;
//    
//    private String startTime;
//
//    /**
//     * 发票打印提交
//     * @return
//     */
//    public String invoicePrint()
//    {
//        logger.debug("cancelSP start"); 
//       
//        String forward = "error";
//        
//        // 获取session
//        HttpSession session = getRequest().getSession();
//        
//        // 获取终端机信息
//        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 获取客户信息
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        //判断当前菜单是否为空
//        if (curMenuId == null)
//        {
//        	curMenuId = "";
//        }
//        
//        //定义操作类型
//        String operType = "Cancel";
//        
//        /*
//         * 1-根据SPID进行退订;
//         * 2-退订用户订购的所有SP业务; 
//         * 3-根据SPID、SPBIZID退订; 
//         * 4-根据spbiztype进行退订; 
//         * 5-根据DOMAIN进行退订
//         */
//        String cancelType = "3";
//        
//        String effectType = "";
//        
//        Map result = new HashMap();//spBean.cancelService(terminalInfoPO, customer, CurMenuid,operType, cancelType, dealType, domain, spId, spBizCode, bizType, effectType);
//        result.put("1111", "2222");
//        if (result != null && result.size() > 1)
//        {
//            forward = "success";
//            
//            //设置成功信息
//            setSuccessMessage("成功退订" + spBizName + "业务");
//            
//            //记录成功信息
//            this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "0","业务受理成功!");
//        }
////        else if(result != null)
////        {
////            //设置失败信息
////            setErrormessage((String)result.get("returnMsg"));
////            
////            //记录失败信息
////            this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "1",(String)result.get("returnMsg"));
////        }
//        else
//        {
//        	//设置失败信息
//            setErrorMessage("退订" + spBizName + "业务失败");           
//            
//            //记录失败信息
//            this.createRecLog(Constants.BUSITYPE_SUBSCANCELSP, "0", "0", "1", "退订" + spBizName + "业务失败");
//        }
//        
//        logger.debug("cancelSP end!");
//        return forward;
//        
//    }
//    
//
//    
//    public String getCurMenuId() {
//		return curMenuId;
//	}
//
//
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
//
//
//
//	public String getPagecount()
//    {
//        return pagecount;
//    }
//    
//    public void setPagecount(String pagecount)
//    {
//        this.pagecount = pagecount;
//    }
//    
//    public String getErrorMessage() {
//		return errorMessage;
//	}
//
//	public void setErrorMessage(String errorMessage) {
//		this.errorMessage = errorMessage;
//	}
//
//	public Vector getInvoiceList()
//    {
//        return invoiceList;
//    }
//    
//    public void setInvoiceList(Vector invoiceList)
//    {
//        this.invoiceList = invoiceList;
//    }
//    
//    public FeeChargeBean getFeeChargeBean()
//    {
//        return feeChargeBean;
//    }
//    
//    public void setFeeChargeBean(FeeChargeBean feeChargeBean)
//    {
//        this.feeChargeBean = feeChargeBean;
//    }
//
//    public String getSpName()
//    {
//        return spName;
//    }
//
//    public void setSpName(String spName)
//    {
//        this.spName = spName;
//    }
//
//    public String getSpBizName()
//    {
//        return spBizName;
//    }
//
//    public void setSpBizName(String spBizName)
//    {
//        this.spBizName = spBizName;
//    }
//
//    public String getPrice()
//    {
//        return price;
//    }
//
//    public void setPrice(String price)
//    {
//        this.price = price;
//    }
//
//    public String getBillFlagName()
//    {
//        return billFlagName;
//    }
//
//    public void setBillFlagName(String billFlagName)
//    {
//        this.billFlagName = billFlagName;
//    }
//
//    public String getStatus()
//    {
//        return status;
//    }
//
//    public void setStatus(String status)
//    {
//        this.status = status;
//    }
//
//    public String getSpId()
//    {
//        return spId;
//    }
//
//    public void setSpId(String spId)
//    {
//        this.spId = spId;
//    }
//
//    public String getSpBizCode()
//    {
//        return spBizCode;
//    }
//
//    public void setSpBizCode(String spBizCode)
//    {
//        this.spBizCode = spBizCode;
//    }
//
//    public String getBizType()
//    {
//        return bizType;
//    }
//
//    public void setBizType(String bizType)
//    {
//        this.bizType = bizType;
//    }
//
//    public String getDomain()
//    {
//        return domain;
//    }
//
//    public void setDomain(String domain)
//    {
//        this.domain = domain;
//    }
//
//    public String getDealType()
//    {
//        return dealType;
//    }
//
//    public void setDealType(String dealType)
//    {
//        this.dealType = dealType;
//    }
//
//    public String getStartTime()
//    {
//        return startTime;
//    }
//
//    public void setStartTime(String startTime)
//    {
//        this.startTime = startTime;
//    }
//
//    public String getSuccessMessage()
//    {
//        return successMessage;
//    }
//
//    public void setSuccessMessage(String successMessage)
//    {
//        this.successMessage = successMessage;
//    }
}
