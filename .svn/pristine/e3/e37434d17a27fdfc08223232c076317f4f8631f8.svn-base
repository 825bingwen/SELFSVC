package com.customize.sd.selfsvc.reception.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.sd.selfsvc.bean.MailBillSendBean;
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
	// 记录日志
    private static Log logger = LogFactory.getLog(MailBillSendAction.class);
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单
    private String curMenuId;
    
    // 账单寄送记录标识，在取消寄送业务时会用到
    private String oidBill;
    
    // 详单寄送记录标识，在取消寄送业务时会用到
    private String oidDetail;
    
    // 发送账单的email
    private String emailBillServ;
    
    // 发送详单的email
    private String emailDetailServ;
    
    // 接口调用
    private MailBillSendBean mailBillSendBean;
    
    // 成功信息
    private String successMessage;

    //add begin m00227318 2012/10/11 V200R003C12L10 OR_SD_201209_443
    // 标志，标识账单是邮件定制还是彩信定制，默认为邮件定制
    private String mltpFlag = "typeEmail";
    
    //错误页面显示信息
    private String errormessage;
    //add end m00227318 2012/10/11 V200R003C12L10 OR_SD_201209_443
    
    /**
     * 查询账单详单定制信息
     * <功能详细描述>
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
     */
	public String qryBillMailInfo()
    {
		//add begin m00227318 2012/10/11 V200R003C12L10 OR_SD_201209_443
		//返回
		String forward = "error";
		//add end m00227318 2012/10/11 V200R003C12L10 OR_SD_201209_443
		
		//获取session信息
        HttpSession session = this.getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        //modify begin m00227318 2012/10/17 V200R003C12L10 OR_SD_201209_443
        Map<String,Object> resBill = null;
        
        if("typeEmail".equals(mltpFlag))
        {
        	// 返回账单寄送查询的结果，邮箱，默认
        	resBill = mailBillSendBean.emailService(termInfo, customer, curMenuId, "Bill", "mltpEml");
        }
        else if("typeMms".equals(mltpFlag))
        {
        	// 返回账单寄送查询的结果，彩信
            resBill = mailBillSendBean.emailService(termInfo, customer, curMenuId, "Bill", "mltpMms");
        }
        
        if (resBill != null && resBill.size() > 0)
        {
        	CTagSet tagSet = (CTagSet)resBill.get("returnObj");
        	
    		//返回报文里message_body节点不为空，取oid和eamil地址
    		if (tagSet != null && tagSet.size() > 0)
    		{
    			// 根据返回值设置账单寄送记录标识
    			setOidBill(tagSet.GetValue("oid"));
            
            	// email地址
    			String email = tagSet.GetValue("email");
            
    			if (oidBill != null && !"".equals(oidBill))
    			{
    				setEmailBillServ(email);
    			}
    		}
        }

		//记录成功日志，并转向成功页面
		if("typeEmail".equals(mltpFlag))
		{
			// 记录成功日志
			this.createRecLog(curMenuId, "0", "0", "0", "139邮箱账单寄送信息查询:查询成功!");
        
			forward = "qryResults";
		}
		else if("typeMms".equals(mltpFlag))
		{
			// 记录成功日志
			this.createRecLog(curMenuId, "0", "0", "0", "彩信账单寄送信息查询:查询成功!");
        
			forward = "qryResultsMMS";
		}
		//modify end m00227318 2012/10/11 V200R003C12L10 OR_SD_201209_443
    	
        return forward;       
    }
	
	/**
     * 变更为139邮箱账单或详单寄送功能
     * <功能详细描述>
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
     */
	public String changeBillOrDetailMail()
	{
		//获取session信息
        HttpSession session = this.getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        //modify begin m00227318 2012/10/13 V200R003C12L10 OR_SD_201209_443
        String forward = "failed";
        
        // 取消之前的账单寄送功能，并开通139邮箱账单寄送功能
        if (cancelBillMail(termInfo, customer, oidBill) && openBillMail(termInfo, customer, "Bill"))
        {
        	// 记录成功日志
            this.createRecLog(curMenuId, "0", "0", "0", "139邮箱账单寄送业务:变更成功!");
                
        	forward = "success";        		
        	setSuccessMessage("139邮箱账单寄送业务变更成功！");
        }
        else
        {
        	// 记录错误日志
            this.createRecLog(curMenuId, "0", "0", "1", "139邮箱账单寄送业务:变更失败!");
            
            this.getRequest().setAttribute("errormessage", "139邮箱账单寄送业务变更失败!");
        }
		
		return forward;
		//modify end m00227318 2012/10/13 V200R003C12L10 OR_SD_201209_443
	}
	
	/**
     * 开通139邮箱或彩信的账单或详单寄送功能
     * <功能详细描述>
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
     */
	public String openBillOrDetailMail()
	{
		//获取session信息
        HttpSession session = this.getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        //modify begin m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
        String forward = "failed";
        
        if(openBillMail(termInfo, customer, "Bill"))
        {
        	forward = "success";
        		
        	if("typeEmail".equals(mltpFlag))
            {
        		// 记录成功日志
                this.createRecLog(curMenuId, "0", "0", "0", "139邮箱账单寄送业务:定制成功!");
                
            	setSuccessMessage("139邮箱账单寄送业务定制成功！");
            }
            else if("typeMms".equals(mltpFlag))
            {
            	// 记录成功日志
                this.createRecLog(curMenuId, "0", "0", "0", "彩信账单寄送业务:定制成功!");
                
            	setSuccessMessage("彩信账单寄送业务定制成功！");
            }
        }
        else
        {
        	if("typeEmail".equals(mltpFlag))
            {
        		// 记录错误日志
        		this.createRecLog(curMenuId, "0", "0", "1", "139邮箱账单寄送业务:定制失败!");
        		
        		this.getRequest().setAttribute("errormessage", "139邮箱账单寄送业务定制失败!");
            }
        	else if("typeMms".equals(mltpFlag))
        	{
        		// 记录错误日志
        		this.createRecLog(curMenuId, "0", "0", "1", "彩信账单寄送业务:定制失败!");
        		
        		this.getRequest().setAttribute("errormessage", "彩信账单寄送业务定制失败!");
        	}
        }
		
		return forward;
		//modify end m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
	}
	
	/**
     * 取消139邮箱或彩信的账单或详单寄送功能
     * <功能详细描述>
     * @author m00227318
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
     */
	public String cancleBillOrDetailMail()
	{
		//获取session信息
        HttpSession session = this.getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        String forward = "failed";
        
        if (cancelBillMail(termInfo, customer, oidBill))
        {
        	forward = "success";
        		
        	if("typeEmail".equals(mltpFlag))
            {
            	// 记录成功日志
                this.createRecLog(curMenuId, "0", "0", "0", "139邮箱账单寄送业务:取消成功!");
                
            	setSuccessMessage("139邮箱账单寄送业务取消成功！");
            }
            else if("typeMms".equals(mltpFlag))
            {
            	// 记录成功日志
                this.createRecLog(curMenuId, "0", "0", "0", "彩信账单寄送业务:取消成功!");
                
            	setSuccessMessage("彩信账单寄送业务取消成功！");
            }
        }
        else
        {
        	if("typeEmail".equals(mltpFlag))
            {
        		// 记录错误日志
        		this.createRecLog(curMenuId, "0", "0", "1", "139邮箱账单寄送业务:取消失败!");
        		
        		this.getRequest().setAttribute("errormessage", "139邮箱账单寄送业务取消失败!");
            }
        	if("typeMms".equals(mltpFlag))
            {
        		// 记录错误日志
        		this.createRecLog(curMenuId, "0", "0", "1", "彩信账单寄送业务:取消失败!");
        		
        		this.getRequest().setAttribute("errormessage", "彩信账单寄送业务取消失败!");
            }
        }
		
		return forward;
	}
	
	/**
     * 取消之前的账单或详单寄送功能
     * <功能详细描述>
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
     */
	private boolean cancelBillMail(TerminalInfoPO termInfo, NserCustomerSimp customer, String oid)
	{
		//modify begin m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
        Map<String,Object> cancelResult = mailBillSendBean.cancelBillMail(termInfo, customer, curMenuId, oid);
        //modify end m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
        if (cancelResult == null || cancelResult.size() == 0)
        {
        	// add begin g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        	this.getRequest().setAttribute("backStep", "-1");
        	// add end g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        	
        	return false;
        }
        return true;
	}
	
	/**
     * 开通139邮箱或彩信的账单或详单寄送功能
     * <功能详细描述>
     * @param 
     * @return
     * @see [类、类#方法、类#成员]
     */
	private boolean openBillMail(TerminalInfoPO termInfo, NserCustomerSimp customer, String billType)
	{
        String email = customer.getServNumber() + "@139.com";
        
        //modify begin m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
        Map<String,Object> openResult = null;
        
        if("typeEmail".equals(mltpFlag))
        {
        	openResult = mailBillSendBean.openBillMail(termInfo, customer, curMenuId, billType, "Che", "mltpEml", "", "", "", "", email);
        }
        else if("typeMms".equals(mltpFlag))
        {
        	openResult = mailBillSendBean.openBillMail(termInfo, customer, curMenuId, billType, "Che", "mltpMms", "", "", "", "", "");
        }
        
        if (openResult == null || openResult.size() == 0)
        {          	
        	// add begin g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        	this.getRequest().setAttribute("backStep", "-1");
        	// add end g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        	        	      	
        	return false;
        }
        //modify end m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
        return true;
	}

	public MailBillSendBean getMailBillSendBean() {
		return mailBillSendBean;
	}

	public void setMailBillSendBean(MailBillSendBean mailBillSendBean) {
		this.mailBillSendBean = mailBillSendBean;
	}

	public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getEmailDetailServ() {
		return emailDetailServ;
	}

	public void setEmailDetailServ(String emailDetailServ) {
		this.emailDetailServ = emailDetailServ;
	}

	public String getEmailBillServ() {
		return emailBillServ;
	}

	public void setEmailBillServ(String emailBillServ) {
		this.emailBillServ = emailBillServ;
	}

	public String getOidBill() {
		return oidBill;
	}

	public void setOidBill(String oidBill) {
		this.oidBill = oidBill;
	}

	public String getOidDetail() {
		return oidDetail;
	}

	public void setOidDetail(String oidDetail) {
		this.oidDetail = oidDetail;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getMltpFlag() {
		return mltpFlag;
	}

	public void setMltpFlag(String mltpFlag) {
		this.mltpFlag = mltpFlag;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
}
