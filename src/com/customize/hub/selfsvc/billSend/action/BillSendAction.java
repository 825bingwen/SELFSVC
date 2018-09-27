package com.customize.hub.selfsvc.billSend.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.BillSendBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 实现账单寄送功能
 * 
 * @author xkf29026
 * 
 */
public class BillSendAction extends BaseAction
{
    // 记录日志
    private static Log logger = LogFactory.getLog(BillSendAction.class);
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单
    private String curMenuId;
    
    // 账单寄送类型,电子邮件--mltpEml；彩信--mltpMms；短信--mltpSms
    private String billSendType;
    
    // 是否开通139手机邮箱
    private String emailService;
    
    // 接口调用
    private BillSendBean billSendBean;
    
    // 如果邮寄类型为Email帐单，则为Email地址；如果邮寄类型是短信帐单或者彩信帐单，则为手机号码
    private String mailAddr;
    
    // 转到选择账单寄送方式页面
    public String billSendPage()
    {
        HttpSession session = this.getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 从字典表中获取判断用户是否开通139邮箱的条件信息
        List<DictItemPO> itemList = (List<DictItemPO>)PublicCache.getInstance().getCachedData("139emailService");
        
        // 调用接口判断用户是否开通139手机邮箱
        emailService = billSendBean.emailService(termInfo, customer, curMenuId, itemList);
        if ("1".equals(emailService))
        {
            setEmailService("1");
        }
        else
        {
            setEmailService("0");
        }
        return "selectBillSendType";
    }
    
    /**
     * 选择账单寄送类型后提交
     * 
     * @return
     */
    public String billSendCommit()
    {
        // 记录日志开始
        logger.debug("billSendCommit starting...");
        
        HttpSession session = this.getRequest().getSession();
        
        // 获取客户信息
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // 获取终端机信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 将账单类型转换为接口协议中的值
        if (billSendType.equals("mltpEml"))// 账单类型：电子邮件
        {
            billSendType = "mltp0200";
            
            mailAddr = customer.getServNumber() + "@139.com";
        }
        else if (billSendType.equals("mltpMms"))// 账单类型：彩信
        {
            billSendType = "mltp0050";
            
            mailAddr = customer.getServNumber();
        }
        else if (billSendType.equals("mltpSms"))// 账单类型：短信
        {
            billSendType = "mltp0030";
            
            mailAddr = customer.getServNumber();
        }
        else if(billSendType.equals("mltpNo")) //add by xkf57421 for OR_HUB_201112_1044 begin
        {
        	billSendType = "mltp0000";
            
            mailAddr = customer.getServNumber();
        }
        //add by xkf57421 for OR_HUB_201112_1044 end
        
        // 调用接口处理账单寄送方式
        Map billSendMap = billSendBean.billSendCommit(termInfo, customer, curMenuId, billSendType, mailAddr);
        if (billSendMap != null && billSendMap.get("successFlag")!= null)
        {
            // 日志记录
            this.getRequest().setAttribute("successMessage", "办理账单寄送方式成功！");
            createRecLog(Constants.BUSITYPE_BILLSEND, "0", "0", "0", "办理账单寄送方式成功:" + billSendMap.get("returnMsg"));
            logger.debug("billSendCommit ended");
            
            return "success";
        }
        else if(billSendMap != null)
        {
            // add begin g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            this.getRequest().setAttribute("backStep", "-1");           
            // add end g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            
            // 日志记录
            this.getRequest().setAttribute("errormessage", "办理账单寄送方式失败!");
            createRecLog(Constants.BUSITYPE_BILLSEND, "0", "0", "1", "办理账单寄送方式失败:" + billSendMap.get("returnMsg"));
            logger.debug("billSendCommit ended");
            
            return "error";
        }
        else
        {
            // add begin g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            this.getRequest().setAttribute("backStep", "-1");           
            // add end g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
            
            // 日志记录
            this.getRequest().setAttribute("errormessage", "办理账单寄送方式失败!");
            createRecLog(Constants.BUSITYPE_BILLSEND, "0", "0", "1", "办理账单寄送方式失败!");
            logger.debug("billSendCommit ended");
            
            return "error";
        }
    }
    
    public BillSendBean getBillSendBean()
    {
        return billSendBean;
    }
    
    public void setBillSendBean(BillSendBean billSendBean)
    {
        this.billSendBean = billSendBean;
    }
    
    public String getBillSendType()
    {
        return billSendType;
    }
    
    public void setBillSendType(String billSendType)
    {
        this.billSendType = billSendType;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getEmailService()
    {
        return emailService;
    }
    
    public void setEmailService(String emailService)
    {
        this.emailService = emailService;
    }
    
    public String getMailAddr()
    {
        return mailAddr;
    }
    
    public void setMailAddr(String mailAddr)
    {
        this.mailAddr = mailAddr;
    }
}
