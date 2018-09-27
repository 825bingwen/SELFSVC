/*
 * 文件名：ReceptionAction_1.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-12-21
 * 修改内容：新增，公共的业务受理
 */
package com.gmcc.boss.selfsvc.reception.action;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.bean.ReceptionBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 
 * 业务受理，不带任何附加属性
 * 
 * 
 * @author g00140516
 * @version 1.0，2010-12-21
 * @see
 * @since
 */
public class ReceptionAction_1 extends BaseAction
{
    private static final long serialVersionUID = -3950351941316700610L;
    
    private static Log logger = LogFactory.getLog(ReceptionAction_1.class);
    
    private transient ReceptionBean receptionBean = null;
    
    private String curMenuId = "";
    
    private String recType = "";
    
    private String feeMessage = "";
    
    private String operType = "";
    
    private String nCode = "";
    
    public String getOperType()
    {
        return operType;
    }
    
    public void setOperType(String operType)
    {
        this.operType = operType;
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
    
    public ReceptionBean getReceptionBean()
    {
        return receptionBean;
    }
    
    public void setReceptionBean(ReceptionBean receptionBean)
    {
        this.receptionBean = receptionBean;
    }
    
    public String getFeeMessage()
    {
        return feeMessage;
    }
    
    public void setFeeMessage(String feeMessage)
    {
        this.feeMessage = feeMessage;
    }
    
    public void setRecType(String recType)
    {
        this.recType = recType;
    }
    
    public String selectOperationType()
    {
        return "operationType";
    }
    
    /**
     * 调用后台接口，查询该业务对应的资费信息
     * 
     * @return
     * @see
     */
    public String qryFeeMessage()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("qryFeeMessage Starting ...");
        }
        
        String forward = "feemsg";
        
        HttpSession session = this.getRequest().getSession();
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        feeMessage = receptionBean.qryFeeMessage(customerSimp, termInfo, nCode, curMenuId, operType);
        
        if (logger.isInfoEnabled())
        {
            logger.info("办理业务" + recType + "时，自后台获取的提示信息为：" + feeMessage);
        }
        
        if (feeMessage == null || feeMessage.trim().length() == 0)
        {
            feeMessage = "。";
        }
        else
        {
            feeMessage = "，" + feeMessage.trim() + "。";
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("qryFeeMessage End");
        }
        return forward;
    }
    
    /**
     * 业务受理
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
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        String sOperType = "";
        if (Constants.SERVICE_APPLY.equals(operType))
        {
            sOperType = Constants.SERVICE_APPLY_STR;
        }
        else if (Constants.SERVICE_CANCEL.equals(operType))
        {
            sOperType = Constants.SERVICE_CANCEL_STR;
        }
        
        ReturnWrap result = receptionBean.recCommonServ(customerSimp, termInfo, nCode, sOperType, "", "", curMenuId);
        // begin zKF66389 2015-09-15 9月份findbugs修改
        //if (result != null && result.getStatus() == SSReturnCode.SUCCESS)
        if (result.getStatus() == SSReturnCode.SUCCESS)
        {
            forward = "success";
            
            this.createRecLog(curMenuId, "0", "0", "0", "业务受理成功");
            
            this.getRequest().setAttribute("errormessage", "业务受理成功");
            
            if (logger.isInfoEnabled())
            {
                logger.info("业务(" + recType + ")受理成功");
            }
        }
        else
        {
            this.createRecLog(curMenuId, "0", "0", "1", "业务受理失败");
            
            this.getRequest().setAttribute("errormessage", result.getReturnMsg());
            
            logger.error("业务(" + recType + ")受理失败");
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("commitReception End");
        }
        
        return forward;
    }

    public String getNCode()
    {
        return nCode;
    }

    public void setNCode(String code)
    {
        nCode = code;
    }
}
