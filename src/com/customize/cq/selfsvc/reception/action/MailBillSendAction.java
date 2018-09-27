package com.customize.cq.selfsvc.reception.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.cq.selfsvc.bean.MailBillSendBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 实现账/详单139邮箱定制功能
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  l00190940
 * @version  [版本号, Spt 22, 2011]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MailBillSendAction extends BaseAction
{
//	// 记录日志
//    private static Log logger = LogFactory.getLog(MailBillSendAction.class);
//    
//    // 序列化
//    private static final long serialVersionUID = 1L;
//    
//    // begin zKF66389 findbus清零
//    // 当前菜单
//    private String curMenuId;
//    // begin zKF66389 findbus清零
//    
//    // 账单寄送记录标识，在取消寄送业务时会用到
//    private String oidBill;
//    
//    // 详单寄送记录标识，在取消寄送业务时会用到
//    private String oidDetail;
//    
//    // 发送账单的email
//    private String emailBillServ;
//    
//    // 发送详单的email
//    private String emailDetailServ;
//    
//    // 接口调用
//    private MailBillSendBean mailBillSendBean;
//    
//    // 取消账单寄送还是详单寄送的标志,1：账单寄送；2：详单寄送
//    private String flag;
//    
//    // 成功信息
//    private String successMessage;
//
//    /**
//     * 查询账单详单定制信息
//     * <功能详细描述>
//     * @param 
//     * @return
//     * @see [类、类#方法、类#成员]
//     */
//	public String qryBillMailInfo()
//    {
//		if (logger.isDebugEnabled())
//        {
//            logger.debug("query BillMailInformation Starting...");
//        }
//		
//		//获取session信息
//        HttpSession session = this.getRequest().getSession();
//        
//        // 获取客户信息
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        // 获取终端机信息
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // 返回账单寄送查询的结果
//        Map resBill = mailBillSendBean.emailService(termInfo, customer, curMenuId, "Bill", "mltpEml");
//       
//        if (resBill != null && resBill.size() > 0)
//        {
//            CTagSet tagSet = (CTagSet)resBill.get("returnObj");
//            
//            // 根据返回值设置账单寄送记录标识
//            setOidBill(tagSet.GetValue("oid"));
//            
//            // email地址
//            String email = tagSet.GetValue("email");
//            
//            if (oidBill != null && !"".equals(oidBill))
//            {
//            	setEmailBillServ(email);
//            }
//        }
//        else
//        {
//            this.getRequest().setAttribute("errormessageBill", "账单寄送信息查询失败!");
//            
//            // 记录错误日志
//            this.createRecLog(curMenuId, "0", "0", "1", "账单寄送信息查询:信息查询失败!");
//        }
//        
//        // 返回详单寄送查询结果
//        Map resDetail = mailBillSendBean.emailService(termInfo, customer, curMenuId, "BillDetail", "mltpEml");
//        
//        if (resDetail != null && resDetail.size() > 0)
//        {
//            CTagSet tagSet = (CTagSet)resDetail.get("returnObj");
//            
//            // 根据返回值设置详单寄送记录标识
//            setOidDetail(tagSet.GetValue("oid"));
//            
//            // email地址
//            String email = tagSet.GetValue("email");
//            
//            if (oidDetail != null && !"".equals(oidDetail))
//            {
//            	setEmailDetailServ(email);
//            }
//            
//        }
//        else
//        {
//            this.getRequest().setAttribute("errormessageDetail", "详单寄送信息查询失败!");
//            
//            // 记录错误日志
//            this.createRecLog(curMenuId, "0", "0", "1", "详单寄送信息查询:信息查询失败!");
//        }
//  
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("query BillMailInformation End!");
//        }
//    	
//    	return "qryResults";
//    }
//	
//	/**
//     * 变更为139邮箱账单或详单寄送功能
//     * <功能详细描述>
//     * @param 
//     * @return
//     * @see [类、类#方法、类#成员]
//     */
//	public String changeBillOrDetailMail()
//	{
//		if (logger.isDebugEnabled())
//        {
//            logger.debug("change bill mail to 139 Starting...");
//        }
//		
//		//获取session信息
//        HttpSession session = this.getRequest().getSession();
//        
//        // 获取客户信息
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        // 获取终端机信息
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        if("1".equals(flag))
//        {
//        	if(!cancelBillMail(termInfo, customer, oidBill))
//        	{
//        		
//        		return "failed";
//        	}
//            
//        	if(!openBillMail(termInfo, customer, "Bill"))
//        	{
//        		return "failed";
//        	}
//            
//            setSuccessMessage("139邮箱账单寄送业务定制成功！");
//        }
//        else if("2".equals(flag))
//        {
//        	// 取消之前的详单寄送功能
// 
//        	if(!cancelBillMail(termInfo, customer, oidDetail))
//        	{
//        		return "failed";
//        	}
//        	
//        	if(!openBillMail(termInfo, customer, "BillDetail"))
//        	{
//        		return "failed";
//        	}
//            
//            setSuccessMessage("139邮箱详单寄送业务定制成功！");
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("change bill mail to 139 End!");
//        }
//		
//		return "success";
//	}
//	
//	/**
//     * 开通139邮箱账单或详单寄送功能
//     * <功能详细描述>
//     * @param 
//     * @return
//     * @see [类、类#方法、类#成员]
//     */
//	public String openBillOrDetailMail()
//	{
//		if (logger.isDebugEnabled())
//        {
//            logger.debug("open 139 bill mail Starting...");
//        }
//		//获取session信息
//        HttpSession session = this.getRequest().getSession();
//        
//        // 获取客户信息
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        // 获取终端机信息
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        if("1".equals(flag))
//        {
//        	if(!openBillMail(termInfo, customer, "Bill"))
//        	{
//        		return "failed";
//        	}
//            
//            setSuccessMessage("139邮箱账单寄送业务定制成功！");
//        }
//        else if("2".equals(flag))
//        {   
//        	if(!openBillMail(termInfo, customer, "BillDetail"))
//        	{
//        		return "failed";
//        	}
//            
//            setSuccessMessage("139邮箱详单寄送业务定制成功！");
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("open 139 bill mail End!");
//        }
//		
//		return "success";
//	}
//	
//	/**
//     * 取消之前的账单或详单寄送功能
//     * <功能详细描述>
//     * @param 
//     * @return
//     * @see [类、类#方法、类#成员]
//     */
//	private boolean cancelBillMail(TerminalInfoPO termInfo, NserCustomerSimp customer, String oid)
//	{
//        Map cancelResult = mailBillSendBean.cancelBillMail(termInfo, customer, curMenuId, oid);
//        
//        if (cancelResult == null || cancelResult.size() == 0)
//        {
//        	this.getRequest().setAttribute("errormessage", "撤销原账单寄送地址失败！");
//        	
//        	// 记录错误日志
//            this.createRecLog(curMenuId, "0", "0", "1", "撤销原账单寄送地址失败！");
//        	
//        	return false;
//        }
//        return true;
//	}
//	
//	/**
//     * 开通139邮箱账单或详单寄送功能
//     * <功能详细描述>
//     * @param 
//     * @return
//     * @see [类、类#方法、类#成员]
//     */
//	private boolean openBillMail(TerminalInfoPO termInfo, NserCustomerSimp customer, String billType)
//	{
//        String email = customer.getServNumber() + "@139.com";
//        
//        Map openResult = mailBillSendBean.openBillMail(termInfo, customer, curMenuId, billType, "Che", "mltpEml", "", "", "", "", email);
//        
//        if (openResult == null || openResult.size() == 0)
//        {
//        	this.getRequest().setAttribute("errormessage", "开通139邮箱账单寄送业务失败！");
//        	
//        	// 记录错误日志
//            this.createRecLog(curMenuId, "0", "0", "1", "开通139邮箱账单寄送业务失败！");
//        	
//        	return false;
//        }
//        return true;
//	}
//
//	public MailBillSendBean getMailBillSendBean() {
//		return mailBillSendBean;
//	}
//
//	public void setMailBillSendBean(MailBillSendBean mailBillSendBean) {
//		this.mailBillSendBean = mailBillSendBean;
//	}
//
//	public String getCurMenuId() {
//		return curMenuId;
//	}
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
//
//	public String getEmailDetailServ() {
//		return emailDetailServ;
//	}
//
//	public void setEmailDetailServ(String emailDetailServ) {
//		this.emailDetailServ = emailDetailServ;
//	}
//
//	public String getEmailBillServ() {
//		return emailBillServ;
//	}
//
//	public void setEmailBillServ(String emailBillServ) {
//		this.emailBillServ = emailBillServ;
//	}
//
//	public String getOidBill() {
//		return oidBill;
//	}
//
//	public void setOidBill(String oidBill) {
//		this.oidBill = oidBill;
//	}
//
//	public String getOidDetail() {
//		return oidDetail;
//	}
//
//	public void setOidDetail(String oidDetail) {
//		this.oidDetail = oidDetail;
//	}
//
//    public String getFlag() {
//		return flag;
//	}
//
//	public void setFlag(String flag) {
//		this.flag = flag;
//	}
//
//	public String getSuccessMessage() {
//		return successMessage;
//	}
//
//	public void setSuccessMessage(String successMessage) {
//		this.successMessage = successMessage;
//	}
}
