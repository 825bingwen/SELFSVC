package com.customize.cq.selfsvc.billSend.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.cq.selfsvc.bean.BillSendBean;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.bean.ReceptionBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 实现账单寄送功能
 * @author z90080209
 * @date   Nov 16, 2011
 */
public class BillSendAction extends BaseAction
{  
    // 记录日志
    private static Log logger = LogFactory.getLog(BillSendAction.class);
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 当前菜单
    // begin zKF66389 findbus清零
    //private String curMenuId;
    // begin zKF66389 findbus清零
    
    // 账单寄送类型,电子邮件--mltpEml；彩信--mltpMms；短信--mltpSms
    private String billSendType;
    
    // 是否开通139手机邮箱
    private String emailService;
    
    // 是否开通短信寄送
    private String smsState;
    
    // 是否开通彩信寄送
    private String mmsState;
    
    // 是否开通邮箱寄送
    private String emlState;
    
    // begin zKF66389 findbus清零
    // 操作类型（0 取消，1 开通）
    private String oprType;
    // end zKF66389 findbus清零
    
    // 接口调用
    private BillSendBean billSendBean;
    
    //接口调用
    private ReceptionBean receptionBean;
    
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
        String dictItem_spbizid = "";
        for(int i = 0;i < itemList.size();i++)
        {
            if(itemList.get(i).getDictid().equals("139spbizid"))
            {
                dictItem_spbizid = itemList.get(i).getDictname();
            }
        }
        
        setEmailService("0");
        // 调用接口判断用户是否开通139手机邮箱
        ReturnWrap result = receptionBean.recCommonServ(customer, termInfo, dictItem_spbizid, "QRY", "", "", "curMenuId");
        if (result != null && result.getStatus() == SSReturnCode.SUCCESS)
        {
        	setEmailService("1");
        }
        else
        {
        	setEmailService("0");
        }
        
        setSmsState("0");
        setMmsState("0");
        setEmlState("0");
        // 判断用户账单寄送开通状态
        Map resBill = billSendBean.billSendState(termInfo, customer, "curMenuId");
        if (resBill != null && resBill.size() > 0)
        {
        	CRSet crset = (CRSet)resBill.get("returnObj");
        	
        	for (int i = 0; i < crset.GetRowCount(); i++)
            {
                if("Bill".equals(crset.GetValue(i, 3))){
                	String type = (String)crset.GetValue(i, 5);
                	if("mltpSMS".equals(type)){
                		setSmsState("1");
                	}else if("mltpMms".equals(type)){
                		setMmsState("1");
                	}else if("mltpEml".equals(type)){
                		setEmlState("1");
                	}
                }
            }
        }
        else
        {
            this.getRequest().setAttribute("errormessageBill", "账单寄送信息查询失败!");
            
            // 记录错误日志
            this.createRecLog("curMenuId", "0", "0", "1", "账单寄送信息查询:信息查询失败!");
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
        
        // 调用接口处理账单寄送方式
        // begin zKF66389 findbus清零
        Map billSendMap = billSendBean.billSendCommit(termInfo, customer, "curMenuId", billSendType, mailAddr, oprType);
        // end zKF66389 findbus清零
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
            // 日志记录
            this.getRequest().setAttribute("errormessage", "办理账单寄送方式失败:"+billSendMap.get("returnMsg"));
            createRecLog(Constants.BUSITYPE_BILLSEND, "0", "0", "1", "办理账单寄送方式失败:" + billSendMap.get("returnMsg"));
            logger.debug("billSendCommit ended");
            
            return "error";
        }
        else
        {
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
    
    public ReceptionBean getReceptionBean()
    {
        return receptionBean;
    }
    
    public void setReceptionBean(ReceptionBean receptionBean)
    {
        this.receptionBean = receptionBean;
    }
    
    public String getBillSendType()
    {
        return billSendType;
    }
    
    public void setBillSendType(String billSendType)
    {
        this.billSendType = billSendType;
    }
    
    // begin zKF66389 findbus清零
//    public String getCurMenuId() {
//		return curMenuId;
//	}
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
	// end zKF66389 findbus清零

	public String getEmailService()
    {
        return emailService;
    }
    
    public void setEmailService(String emailService)
    {
        this.emailService = emailService;
    }
    
    public String getSmsState()
    {
        return smsState;
    }
    
    public void setSmsState(String smsState)
    {
        this.smsState = smsState;
    }
    
    public String getMmsState()
    {
        return mmsState;
    }
    
    public void setMmsState(String mmsState)
    {
        this.mmsState = mmsState;
    }
    
    public String getEmlState()
    {
        return emlState;
    }
    
    public void setEmlState(String emlState)
    {
        this.emlState = emlState;
    }
    
    // begin zKF66389 findbus清零
    public String getOprType() {
		return oprType;
	}

	public void setOprType(String oprType) {
		this.oprType = oprType;
	}
	// end zKF66389 findbus清零

	public String getMailAddr()
    {
        return mailAddr;
    }
    
    public void setMailAddr(String mailAddr)
    {
        this.mailAddr = mailAddr;
    }
}
