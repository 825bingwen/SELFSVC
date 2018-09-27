package com.customize.hub.selfsvc.idCardBook.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.hub.selfsvc.bean.IdCardBookBean;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 身份证入网预约
 * <功能详细描述>
 * 
 * @author  cKF48754
 * @version  [版本号, Jun 24, 2011]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class IdCardBookAction extends BaseAction
{
    
    // 序列化
    private static final long serialVersionUID = 1L;
    
    // 日志
    private static Log logger = LogFactory.getLog(IdCardBookAction.class);
    
    // 当前菜单id
    private String curMenuId = "";
    
    // 错误消息
    private String errormessage = "";
    
    // 成功消息消息
    private String successMessage = "";
    
    // 姓名(显示用)
    private String idCardName = "";
    
    // 性别（显示用）
    private String idCardSex = "";
    
    // 身份证号(显示用)
    private String idCardNo = "";
    
    //证件地址
    private String idCardAddr;
    
    //证件开始时间
    private String idCardStartTime;
    
    //证件结束时间
    private String idCardEndTime;
    
    // 0姓名~男~民族~出生~住址~37061218840530402X~签发机关~有效期起始日期~有效期截止日期~最新住址
    private String idCardContent = "";
    
    // 调用接口Bean
    private IdCardBookBean idCardBookBean;
    
    /**
     * 转向身份证读卡页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toReadCard()
    {      
        return "toReadCard";
    }
    
   /**
    * 转向身份证读卡错误页面
    * <功能详细描述>
    * @return
    * @see [类、类#方法、类#成员]
    */
    public String goCardError()
    {     
        //错误信息
        setErrormessage(errormessage);
        
        return "error";
    }
    
   /**
    * 身份证信息展示
    * <功能详细描述>
    * @return
    * @see [类、类#方法、类#成员]
    */
    public String getCardInfor()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("getCardInfor Starting...");
        }

        String forward = "cardInfor";
        
        if (logger.isDebugEnabled())
        {
            logger.debug("getCardInfor End");
        }
        
        return forward;
    }
    
    /**
     * 身份证入网预约
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String idCardBookCommit()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("idCardBookCommit Starting...");
        }
        
        // add begin g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        this.getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        // 获取session
        HttpSession session = getRequest().getSession();
        
        // 获取终端机信息
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);      
        
        // 定义错误页面转向
        String forward = "error";
        
        // 身份证入网预约
        Map result = idCardBookBean.idCardBook(terminalInfoPO,curMenuId,idCardContent);
        
        int rtStatus = (Integer)result.get("rtStatus");
        
        if (SSReturnCode.SUCCESS == rtStatus)
        {
            setSuccessMessage("入网预约受理成功!");
             
            forward = "success";
             
            // 记录成功日志
            this.createRecLog(Constants.BUSITYPE_IdCardBook, "0", "0", "0", "身份证入网预约成功!");
        }
        else
        {
            setErrormessage("业务办理失败！"+result.get("returnMsg").toString());
             
            // 记录错误日志
            this.createRecLog(Constants.BUSITYPE_IdCardBook, "0", "0", "1", "身份证入网预约失败!");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("idCardBookCommit End!");
        }
        
        return forward;
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

    public void setErrormessage(String errormessage)
    {
        this.errormessage = errormessage;
    }

    public String getIdCardName()
    {
        return idCardName;
    }

    public void setIdCardName(String idCardName)
    {
        this.idCardName = idCardName;
    }

    public String getIdCardSex()
    {
        return idCardSex;
    }

    public void setIdCardSex(String idCardSex)
    {
        this.idCardSex = idCardSex;
    }

    public String getIdCardNo()
    {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo)
    {
        this.idCardNo = idCardNo;
    }

    public IdCardBookBean getIdCardBookBean()
    {
        return idCardBookBean;
    }

    public String getSuccessMessage()
    {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage)
    {
        this.successMessage = successMessage;
    }

    public void setIdCardBookBean(IdCardBookBean idCardBookBean)
    {
        this.idCardBookBean = idCardBookBean;
    }

	public String getIdCardContent() 
	{
		return idCardContent;
	}

	public void setIdCardContent(String idCardContent) 
	{
		this.idCardContent = idCardContent;
	}

	public String getIdCardAddr() 
	{
		return idCardAddr;
	}

	public void setIdCardAddr(String idCardAddr) 
	{
		this.idCardAddr = idCardAddr;
	}

	public String getIdCardStartTime() 
	{
		return idCardStartTime;
	}

	public void setIdCardStartTime(String idCardStartTime) 
	{
		this.idCardStartTime = idCardStartTime;
	}

	public String getIdCardEndTime() 
	{
		return idCardEndTime;
	}

	public void setIdCardEndTime(String idCardEndTime) 
	{
		this.idCardEndTime = idCardEndTime;
	}

    
}
