package com.gmcc.boss.selfsvc.charge.action;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.gmcc.boss.selfsvc.bean.CardPayBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 充值卡缴费
 * 
 * @author cKF48754
 * 
 */
public class CardPayAction extends BaseAction
{
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 日志
    // modify begin by xkf29026 2011/10/6 添加final
    public static final Log logger = LogFactory.getLog(CardPayAction.class);
    // modify end by xkf29026 2011/10/6  添加final
    
    // 当前菜单
    private String curMenuId = "";
    
    // 用户手机号
    private String telnum;
    
    // 充值卡密码
    private String cardPwd;
    
    // 错误信息
    private String errormessage;
    
    // 成功信息
    private String successMessage;
    
    // 调用接口Bean
    private CardPayBean cardPayBean;
    
    /**
     * 转向充值卡充值页面
     * 
     * @return
     */
    public String inputNumber()
    {
        return "inputNumber";
    }
    
    /**
     * 充值卡缴费
     * 
     * @return
     */
    public String cardPayCommit()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("cardPayCommit start");
        }
        
        String forward = null;
        
        HttpSession session = getRequest().getSession();
        
        // 终端信息
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // 调用用户接口获取用户状态,如果用户状态为销户，则提示用户不能进行充值卡缴费业务
        String userState = cardPayBean.qryUserState(termInfo, curMenuId, telnum);
        if ("销户".equals(userState))
        {
            setErrormessage(telnum + "已经销户，不能进行充值卡缴费操作！");
            
            // 记录缴费失败日志
            this.createRecLog(telnum, "cardPay", "0", "0", "1", "充值卡缴费:用户" + telnum + "已销户!");
            
            return "error";
        }
        
        // 充值卡缴费
        boolean cardPayStatus = cardPayBean.cardPayCommit(termInfo, curMenuId, telnum, cardPwd);
        
        if (cardPayStatus)
        {
            forward = "success";
            setSuccessMessage("您的充值请求已受理，请等待系统处理。稍后您将收到系统关于此次充值是否成功的提示短信，请确认您的手机处于开机状态。如果您的手机已停机，可能接收不到此提示短信。");
            
            // 记录缴费成功日志
            this.createRecLog(telnum, "cardPay", "0", "0", "0", "充值卡缴费:自助终端缴费成功!");
        }
        
        else
        {
            forward = "error";
            setErrormessage("充值卡缴费失败！");
            
            // 记录缴费失败日志
            this.createRecLog(telnum, "cardPay", "0", "0", "1", "充值卡缴费:自助终端缴费失败!");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("cardPayCommit end");
        }
        
        return forward;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getTelnum()
    {
        return telnum;
    }
    
    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }
    
    public String getCardPwd()
    {
        return cardPwd;
    }
    
    public void setCardPwd(String cardPwd)
    {
        this.cardPwd = cardPwd;
    }
    
    public String getErrormessage()
    {
        return errormessage;
    }
    
    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }
    
    public String getSuccessMessage()
    {
        return successMessage;
    }
    
    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }
    
    public CardPayBean getCardPayBean()
    {
        return cardPayBean;
    }
    
    public void setCardPayBean(CardPayBean cardPayBean)
    {
        this.cardPayBean = cardPayBean;
    }
    
}
