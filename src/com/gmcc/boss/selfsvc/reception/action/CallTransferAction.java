/*
 * 文件名：CallTransferAction.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：呼叫转移开通、取消
 * 修改人：g00140516
 * 修改时间：2010-12-22
 * 修改内容：新增
 */
package com.gmcc.boss.selfsvc.reception.action;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.bean.CallTransferBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 呼叫转移开通、取消
 * 
 * 
 * @author g00140516
 * @version 1.0，2010-12-22
 * @see
 * @since
 */
public class CallTransferAction extends BaseAction
{
    private static final long serialVersionUID = -3950351941316700610L;
    
    private static Log logger = LogFactory.getLog(CallTransferAction.class);
    
    private transient CallTransferBean callTransferBean = null;
    
    private String curMenuId = "";
    
    private String recType = "";
    
    private String transferType = "";
    
    private String transferNo = "";
    
    /**
     * 成功提示信息
     */
    private String successMessage = "";

	/**
     * 选择呼转类型
     * 
     * @return
     * @see
     */
    public String selectType()
    {
        return "transferType";
    }
    
    /**
     * 输入呼转号码
     * 
     * @return
     * @see
     */
    public String inputNumber()
    {
        return "inputNumber";
    }
    
    /**
     * 呼叫转移开通、取消
     * 
     * @return
     * @see
     */
    public String commitReception()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("commitReception Starting ...");
        }
        
        String forward = "failed";
        
        HttpSession session = this.getRequest().getSession();
        
        // add begin g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        this.getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        if (transferNo == null)
        {
            transferNo = "";
        }
        
        // add begin xkf29026 2011/10/19 呼转号码不能为本机
        if(customerSimp.getServNumber().equals(transferNo))
        {
            this.createRecLog(Constants.BUSITYPE_RECCALLTRANSFER, "0", "0", "1", "业务受理失败,呼转号码不能为本机");
            
            this.getRequest().setAttribute("errormessage", "业务受理失败,呼转号码不能为本机");
            
            logger.error("业务受理失败,呼转号码不能为本机");
            
            return forward;
        }
        // add end xkf29026 2011/10/19 呼转号码不能为本机
        
        String transferTypeName = "业务受理";
        if ("24".equals(transferType))
        {
        	transferTypeName = "无条件呼转受理";
        }
        else if ("25".equals(transferType))
        {
        	transferTypeName = "遇忙呼转受理";
        }
        else if ("26".equals(transferType))
        {
        	transferTypeName = "无应答呼转受理";
        }
        else if ("27".equals(transferType))
        {
        	transferTypeName = "不可及呼转受理";
        }
        else if ("28".equals(transferType))
        {
        	transferTypeName = "呼叫转移取消";
        } 
        
        if (callTransferBean.commitCallTransferNo(customerSimp, termInfo, transferType, transferNo, curMenuId))
        {
            forward = "success";
            
            this.createRecLog(Constants.BUSITYPE_RECCALLTRANSFER, "0", "0", "0", "业务受理成功");
            
            successMessage = transferTypeName + "成功";
            
            if (logger.isInfoEnabled())
            {
                logger.info("呼叫转移业务受理成功");
            }
        }
        else
        {
            this.createRecLog(Constants.BUSITYPE_RECCALLTRANSFER, "0", "0", "1", "业务受理失败");
            
            this.getRequest().setAttribute("errormessage", transferTypeName + "失败");
            
            logger.error("呼叫转移业务受理失败");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("commitReception End");
        }
        
        return forward;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public String getRecType()
    {
        return recType;
    }
    
    public void setRecType(String recType)
    {
        this.recType = recType;
    }
    
    public String getTransferType()
    {
        return transferType;
    }
    
    public void setTransferType(String transferType)
    {
        this.transferType = transferType;
    }
    
    public CallTransferBean getCallTransferBean()
    {
        return callTransferBean;
    }
    
    public void setCallTransferBean(CallTransferBean callTransferBean)
    {
        this.callTransferBean = callTransferBean;
    }
    
    public String getTransferNo()
    {
        return transferNo;
    }
    
    public void setTransferNo(String transferNo)
    {
        this.transferNo = transferNo;
    }
        
    public String getSuccessMessage()
    {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage)
	{
		this.successMessage = successMessage;
	}
}
