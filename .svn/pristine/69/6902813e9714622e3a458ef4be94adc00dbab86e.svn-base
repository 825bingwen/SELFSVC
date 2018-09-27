/*
 * 文 件 名:  InvoicePrint.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人: YKF38827
 * 修改时间:  Mar 13, 2012
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.invoice.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.InvoicePrintBean;
import com.customize.hub.selfsvc.feeservice.action.FeeBalanceAction;
import com.customize.hub.selfsvc.invoice.model.InvoicePrintPO;
import com.customize.hub.selfsvc.invoice.service.InvoicePrintHubService;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 发票打印
 * 
 * @author YKF38827
 * @version [Mar 13, 2012]
 * @see
 * @since
 */
public class InvoicePrintHubAction extends BaseAction
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 日志
     */
    private static Log logger = LogFactory.getLog(FeeBalanceAction.class);
    
    /**
     * 业务BEAN
     */
    private transient InvoicePrintHubService invoicePrintService;
    
    /**
     * 设置结果
     */
    private List<List<String>> result;
    
    /**
     * 调用接口Bean
     */
    private transient InvoicePrintBean invoicePrintBean;
    
    /**
     * 结果标题
     */
    private String[] servicetitle;
    
    /**
     * 当前菜单
     */
    private String curMenuId = "";
    
    /**
     * 用户手机号
     */
    private String servnumber;
    
    /**
     * 发票打印类型
     */
    private String invoiceType;
    
    /**
     * 是否已发送了短信密码
     */
    private String ifSendRrandPwd;
    
    /**
     * 合打流水号
     */
    private String totFormNum = "";
    
    /**
     * 流水号
     */
    private String formnum;
    
    /**
     * 账期
     */
    private String billCycle;
    
    private String valiMess;

    /**
     * 检测发票打印机状态
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String validatePrinTerm()
    {
        String gotoJsp = "";
        if (null == valiMess || 0 == valiMess.length())
        {
            // 清除是否随机码验证
            HttpSession session = this.getRequest().getSession();
            session.removeAttribute("ifSendRrandPwd");
            gotoJsp = "success";
        }
        else
        {
            this.getRequest().setAttribute("errorMessage", valiMess);
            gotoJsp = "error";
        }
        return gotoJsp;
    }
    
    /**
     * 读取要打印的发票记录信息
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public String invoiceList()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("invoiceList Starting...");
        }
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        // TerminalInfoPO terminalInfoPO = null;
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        servnumber = customer.getServNumber();
        Map mapResult = invoicePrintBean.invoiceList(terminalInfoPO, customer, curMenuId);
        
        if (mapResult != null && mapResult.size() > 1)
        {
            CRSet crset = (CRSet)mapResult.get("returnObj");
            
            // 判断返回报文数据是否为空
            if (null == crset)
            {
                getRequest().setAttribute("errormessage", "没有可打印的发票信息！");
                return "error";
            }
            else
            {
                // 定义结果标题名称
                String[] titles = {"账单月份", "业务类型", "实收金额", "受理时间", "受理地点"};
                
                // 设置标题名称
                setServicetitle(titles);
                
                result = new ArrayList();
                List listInner = null;
                for (int i = 0; i < crset.GetRowCount(); i++)
                {
                    listInner = new ArrayList();
                    listInner.add(crset.GetValue(i, 0));
                    listInner.add(crset.GetValue(i, 2));
                    listInner.add(CommonUtil.fenToYuan(crset.GetValue(i, 5)));
                    listInner.add(crset.GetValue(i, 7));
                    listInner.add(crset.GetValue(i, 11));
                    listInner.add(crset.GetValue(i, 13));
                    
                    if (i == crset.GetRowCount() - 1)
                    {
                        totFormNum = totFormNum + crset.GetValue(i, 13);
                    }
                    else
                    {
                        totFormNum = totFormNum + crset.GetValue(i, 13) + "|";
                        
                    }
                    
                    result.add(listInner);
                }
            }
            
        }
        else
        {
            
            if (mapResult != null)
            {
                getRequest().setAttribute("errormessage", "没有可打印的发票信息！");
            }
            else
            {
                getRequest().setAttribute("errormessage", "查询异常");
                
            }
            
            return "error";
        }
        
        return "success";
    }
    
    /**
     * 湖北打印发票需要短信随机码认证
     * 
     * @return
     * @see
     */
    @SuppressWarnings("unchecked")
    public String validateByRandomPwd()
    {
        
        if (logger.isDebugEnabled())
        {
            logger.debug("validateByRandomPwd Starting ...");
        }
        String forward = "error";
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        ifSendRrandPwd = (session.getAttribute("ifSendRrandPwd") == null ? "0" : session.getAttribute("ifSendRrandPwd")
                .toString());
        
        // modify begin cKF76106 2013/04/12 R003C13L04n01 OR_HUB_201303_548
        // 发票打印是否进行随机码认证(1：需要  0：不需要)
        String needRandPwd = (String) PublicCache.getInstance().getCachedData("SH_RANDPWD_INVOICE");

        if ("0".equals(ifSendRrandPwd) && "1".equals(needRandPwd))// 初次发送随机码认证
        {
        // modify end cKF76106 2013/04/12 R003C13L04n01 OR_HUB_201303_548
            String invoiceType = (String)request.getParameter("invoiceType");
            String dealNum = (String)request.getParameter("dealNum");
            String formnum = (String)request.getParameter("formnum");
            String billCycle = (String)request.getParameter("billCycle");
            request.setAttribute("invoiceType", invoiceType);
            request.setAttribute("dealNum", dealNum);
            request.setAttribute("formnum", formnum);
            request.setAttribute("billCycle", billCycle);
            
            // 终端机信息
            TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
            
            // 生成随机密码
            String randomPwd = createRandomPassword(servnumber, CommonUtil.getCurrentTime());
            
            // modify begin cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20

            // 发送随机密码短信
            // String shortMessage = (String)PublicCache.getInstance().getCachedData("SH_PRTINVOICE_RANDOMPWD_CONTENT");
            // shortMessage = shortMessage.replace("[%1]", randomPwd);
            
            // 参数列表:12013-07-03 12:01:01#2123456
            String smsparam = "1" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "#2" + randomPwd;
            
            if (!invoicePrintBean.sendRandomPwdHub(termInfo,
                    smsparam,
                    servnumber,
                    "Atsv_invoicePrt",
                    curMenuId))
            {
                logger.error("向用户" + servnumber + "发送随机密码短信失败");
                
                this.createRecLog(Constants.BUSITYPE_PRINTINVOICE, "0", "0", "1", "发票打印功能，随机密码短信发送失败。");
                
                this.getRequest().setAttribute("errormessage", "随机密码短信发送失败，不能打印发票。");
            }
            else
            {
                
                if (logger.isInfoEnabled())
                {
                    logger.info("向用户" + servnumber + "发送随机密码短信成功，随机码" + randomPwd);
                }
                
                forward = "success";
            }
            // modify end cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
            
            if (logger.isDebugEnabled())
            {
                logger.debug("validateByRandomPwd End");
            }
        }
        else
        {
            String invoiceType = (String)request.getParameter("invoiceType");
            String dealNum = (String)request.getParameter("dealNum");
            String formnum = (String)request.getParameter("formnum");
            String billCycle = (String)request.getParameter("billCycle");
            
            
            List list = printInvoice(invoiceType, formnum, billCycle);
            if (null == list)
            {
                getRequest().setAttribute("errormessage", "获取发票内容为空");
                return "error";
            }
            // 调用接口打印发票
            request.setAttribute("invoice", getXmlStr(list));
            forward = "successPrin";
            
        }
        
        return forward;
    }
    
    /**
     * 随机密码验证，验证通过后，打印发票
     * 
     * @return
     * @see
     */
    @SuppressWarnings("unchecked")
    public String printInvoiceWithPwd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("printInvoice Starting ...");
        }
        
        String forward = "";
        
        HttpServletRequest request = this.getRequest();
        
        String randomPwd = (String)request.getParameter("randomPwd");
        String invoiceType = (String)request.getParameter("invoiceType");
        String dealNum = (String)request.getParameter("dealNum");
        String formnum = (String)request.getParameter("formnum");
        String billCycle = (String)request.getParameter("billCycle");
        
        String result = this.loginWithRandomPwd(servnumber, randomPwd);
        if ("1".equals(result))
        {
            forward = "success";
            HttpSession session = request.getSession();
            session.setAttribute("ifSendRrandPwd", "1");
            // 调用接口打印发票
            List list = printInvoice(invoiceType, formnum, billCycle);
            if (null == list)
            {
                getRequest().setAttribute("errormessage", "获取发票内容为空");
                forward = "error";
            }
            else
            {
                // 调用接口打印发票
                request.setAttribute("invoice", getXmlStr(list));
            }
            
            if (logger.isInfoEnabled())
            {
                logger.info("发票打印功能，用户" + servnumber + "使用随机密码进行认证成功");
            }
        }
        else
        {
            forward = "errors";
            
            String errorMsg = "";
            
            if ("-1".equals(result))
            {
                errorMsg = "您输入的随机密码已经超时，请返回重试或者进行其它操作。";
            }
            else
            {
                // modify begin g00140516 2012/09/18 R003C12L09n01 湖北电子渠道二期提示信息改造
                errorMsg = getConvertMsg("随机密码输入错误，请重新输入。", "zz_02_01_000003", null, null);
                // modify end g00140516 2012/09/18 R003C12L09n01 湖北电子渠道二期提示信息改造
            }
            
            logger.error("发票打印功能，用户" + servnumber + "输入的随机密码错误或超时，认证失败");
            
            this.createRecLog(Constants.BUSITYPE_PRINTINVOICE, "0", "0", "1", errorMsg);
            
            this.getRequest().setAttribute("errormessage", errorMsg);
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("loginWithRandomPwd End");
        }
        
        return forward;
    }
    
    /**
     * 调用接口打印发票
     * 
     * @param invoiceType
     * @param dealNum
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, String>> printInvoice(String invoiceType, String formnum, String billCycle)
    {
        HttpServletRequest request = this.getRequest();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        // 提供发票打印功能时，获取发票信息
        String canPrintInvoice = termInfo.getTermspecial().substring(1, 2);
        if ("1".equals(canPrintInvoice))
        {
            Map invoiceData = invoicePrintBean.invoiceData(termInfo, curMenuId, servnumber, formnum, billCycle);
            
            if (invoiceData != null && invoiceData.size() > 1)
            {
                Object invoiceObj = invoiceData.get("returnObj");
                if (invoiceObj instanceof CRSet)
                {
                    List<Map<String, String>> invoicesList = new ArrayList<Map<String, String>>();
                    
                    // 取接口返回对象
                    CRSet crset  = (CRSet)invoiceObj;

                    // crset转成MAP
                    Map<String, String> invoiceMaps = getInvoiceInfo(crset);
                    
                    // 加入list
                    invoicesList.add(invoiceMaps);
                    
                    // 返回
                    return invoicesList;
                }
            }
        }
        return null;
    }
    
    /**
     * 对客服返回的打印发票内容进行处理
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    
    public Map<String, String> getInvoiceInfo(CRSet crset)
    {
        Map<String, String> invoiceMaps = new HashMap<String, String>();

        // 缴费时间
        invoiceMaps.put("chargeDate", getInvoiceItem(crset, "chargeDate"));
        
        // 客户名称
        invoiceMaps.put("username", getInvoiceItem(crset, "username"));
        
        // 款项性质
        invoiceMaps.put("callfeeCreateTime", getInvoiceItem(crset, "feetype"));
        
        // 电话号码
        invoiceMaps.put("telnumber", getInvoiceItem(crset, "telnumber"));
        
        // 电话号码
        invoiceMaps.put("formnum", getInvoiceItem(crset, "formnum"));
        
        // 大写金额
        invoiceMaps.put("invoicefeedx", getInvoiceItem(crset, "invoicefeedx"));
        
        // 小写金额
        invoiceMaps.put("invoicefee", getInvoiceItem(crset, "invoicefee"));
        
        // 合同号
        invoiceMaps.put("paynumno", getInvoiceItem(crset, "paynumno"));
        
        // 合同号
        invoiceMaps.put("paynumno", getInvoiceItem(crset, "paynumno"));
        
        // 费用合计
        invoiceMaps.put("invoicefeehj", getInvoiceItem(crset, "invoicefeehj"));
        
        // 积分
        invoiceMaps.put("Score", getInvoiceItem(crset, "Score"));
        
        invoiceMaps.put("agreementleftbal", getInvoiceItem(crset, "hz_agreementleftbal") + "@" + getInvoiceItem(crset, "agreementleftbal"));
        
        invoiceMaps.put("agreementleftbal_Z", getInvoiceItem(crset, "hz_agreementleftbal_Z") + "@" + getInvoiceItem(crset, "agreementleftbal_Z"));
        
        invoiceMaps.put("InvoiceNote", getInvoiceItem(crset, "note"));
        
        // 打印时间
        invoiceMaps.put("printtime", getInvoiceItem(crset, "hz_printtime") + "@" + getInvoiceItem(crset, "printtime"));
        
        // 打印时间
        invoiceMaps.put("totalmoney", getInvoiceItem(crset, "hz_totalmoney") + "@" + getInvoiceItem(crset, "totalmoney"));
        
        // 累计结余
        invoiceMaps.put("leftmoney", getInvoiceItem(crset, "hz_leftmoney") + "@" + getInvoiceItem(crset, "leftmoney"));
        
        //
        invoiceMaps.put("overdraft", getInvoiceItem(crset, "hz_overdraft") + "@" + getInvoiceItem(crset, "overdraft"));
        
        return invoiceMaps;
        
    }
    
    /**
     * 获取发票键值
     * 
     * @param invoiceArray
     * @param itemName
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getInvoiceItem(CRSet crset, String itemName)
    {
        for (int i = 0; i < crset.GetRowCount(); i++)
        {
            if (itemName.equals(crset.GetValue(i, 0)))
            {
                return crset.GetValue(i, 1);
            }
        }
        return "";
    }
    
    /**
     * 将发票数据组织成xml
     * 
     * @param list 发票数据
     * @return
     * @see
     */
    @SuppressWarnings("unchecked")
    private String getXmlStr(List list)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("getXmlStr Starting...");
        }
        
        StringBuffer xmlStr = new StringBuffer(1024);
        
        xmlStr.append("<xml id=\"invoiceXml\" version=\"1.0\" encoding=\"GBK\"><root>");
        
        Iterator it = null;
        for (int i = 0; i < list.size(); i++)
        {
            xmlStr.append("<entry index=\"INVOICE_").append(i).append("\" itemname=\"invoice").append(i).append("\">");
            
            it = ((HashMap)list.get(i)).entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry entry = (Map.Entry)it.next();
                String subItemKey = (String)entry.getKey();
                String subItemValue = (String)entry.getValue();
                
                xmlStr.append("<")
                        .append(subItemKey)
                        .append("><![CDATA[")
                        .append(subItemValue)
                        .append("]]></")
                        .append(subItemKey)
                        .append(">");
            }
            
            xmlStr.append("</entry>");
        }
        
        xmlStr.append("</root></xml>");
        
        if (logger.isDebugEnabled())
        {
            logger.debug("getXmlStr End!");
        }
        return xmlStr.toString();
    }
    
    /**
     * 记录发票打印日志
     * 
     * @see
     */
    public void insertInvoiceLog()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("insertInvoiceLog Starting ...");
        }
        
        HttpServletRequest request = this.getRequest();
        
        String servNumber = request.getParameter("servnumber");
        String formnum = request.getParameter("formnum");
        
        TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
        
        this.createRecLog(servNumber, Constants.BUSITYPE_PRINTINVOICE, "0", "0", "0", "");
        
        InvoicePrintPO record = new InvoicePrintPO();
        record.setServNumber(servNumber);
        record.setFormnum(formnum);
        record.setTermID(termInfo.getTermid());
        
        invoicePrintService.insertInvoiceLog(record);
        
        if (logger.isDebugEnabled())
        {
            logger.debug("insertInvoiceLog End");
        }
    }
    
    public String getIfSendRrandPwd()
    {
        return ifSendRrandPwd;
    }
    
    public void setIfSendRrandPwd(String ifSendRrandPwd)
    {
        this.ifSendRrandPwd = ifSendRrandPwd;
    }
    
    public InvoicePrintHubService getInvoicePrintService()
    {
        return invoicePrintService;
    }
    
    public void setInvoicePrintService(InvoicePrintHubService invoicePrintService)
    {
        this.invoicePrintService = invoicePrintService;
    }
    
    public InvoicePrintBean getInvoicePrintBean()
    {
        return invoicePrintBean;
    }
    
    public void setInvoicePrintBean(InvoicePrintBean invoicePrintBean)
    {
        this.invoicePrintBean = invoicePrintBean;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
	public String[] getServicetitle()
    {
        return servicetitle;
    }
	@edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setServicetitle(String[] servicetitle)
    {
        this.servicetitle = servicetitle;
    }
    
    public String getServnumber()
    {
        return servnumber;
    }
    
    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }
    
    public String getInvoiceType()
    {
        return invoiceType;
    }
    
    public void setInvoiceType(String invoiceType)
    {
        this.invoiceType = invoiceType;
    }
    
    public String getTotFormNum()
    {
        return totFormNum;
    }
    
    public void setTotFormNum(String totFormNum)
    {
        this.totFormNum = totFormNum;
    }
    
    public String getFormnum()
    {
        return formnum;
    }
    
    public void setFormnum(String formnum)
    {
        this.formnum = formnum;
    }
    
    public String getValiMess()
    {
        return valiMess;
    }
    
    public void setValiMess(String valiMess)
    {
        this.valiMess = valiMess;
    }
    
    public List<List<String>> getResult()
    {
        return result;
    }
    
    public void setResult(List<List<String>> result)
    {
        this.result = result;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getBillCycle()
    {
        return billCycle;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setBillCycle(String billCycle)
    {
        this.billCycle = billCycle;
    }
}
