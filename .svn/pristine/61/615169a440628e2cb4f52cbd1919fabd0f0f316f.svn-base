package com.customize.cq.selfsvc.serviceinfo.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.cq.selfsvc.bean.CallTransferBeanCQ;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

/**
 * 呼叫转移 重庆定制
 * @author z90080209
 * @date   Nov 10, 2011
 */
public class CallTransferActionCQ extends BaseAction
{
//    private static final long serialVersionUID = -3950351941316700610L;
//    
//    private static Log logger = LogFactory.getLog(CallTransferActionCQ.class);
//    
//    private transient CallTransferBeanCQ callTransferBeanCQ = null;
//    
//    // begin zKF66389 findbus清零
//    private String curMenuId = "";
//    // end zKF66389 findbus清零
//    
//    private String recType = "";
//    
//    private String transferType = "";
//    
//    private String transferNo = "";
//    
//    /**
//     * 选择呼转类型
//     * 
//     * @return
//     * @see
//     */
//    public String selectType()
//    {
//        return "transferType";
//    }
//    
//    /**
//     * 输入呼转号码
//     * 
//     * @return
//     * @see
//     */
//    public String inputNumber()
//    {
//        return "inputNumber";
//    }
//    
//    /**
//     * 呼叫转移查询、开通、取消
//     * 
//     * @return
//     * @see
//     */
//    public String commitReception() throws IOException
//    {
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("commitReception Starting ...");
//        }
//        
//        String forward = null;
//        
//        HttpSession session = this.getRequest().getSession();
//        
//        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        if (transferNo == null)
//        {
//            transferNo = "";
//        }
//        if (recType == null)
//        {
//        	recType = "";
//        }
//        
//        //2为查询，0为取消，1为开通
//        if(!"2".equals(recType)&&customerSimp.getServNumber().equals(transferNo))
//        {
//            this.createRecLog(Constants.BUSITYPE_RECCALLTRANSFER, "0", "0", "1", "业务受理失败,呼转号码不能为本机");
//            
//            this.getRequest().setAttribute("errormessage", "业务受理失败,呼转号码不能为本机");
//            
//            logger.error("业务受理失败,呼转号码不能为本机");
//            
//            return "failed";
//        }
//        
//        ReturnWrap rw = callTransferBeanCQ.commitCallTransferNo(customerSimp, termInfo, transferType, transferNo, curMenuId, recType);
//        
//        PrintWriter out = this.getResponse().getWriter();
//        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
//        {
//            if("2".equals(recType)){
//            	if (logger.isInfoEnabled())
//                {
//                    logger.info("用户" + customerSimp.getServNumber() + "已开通该类型呼转");
//                }
//            	
//            	out.write("0");
//                out.flush();
//            }else{
//            	forward = "success";
//            	this.createRecLog(Constants.BUSITYPE_RECCALLTRANSFER, "0", "0", "0", "业务受理成功");
//            	this.getRequest().setAttribute("errormessage", "业务受理成功");
//            	
//            	if (logger.isInfoEnabled())
//                {
//                    logger.info("呼叫转移业务受理成功");
//                }
//            }
//        }
//        else
//        {
//            if("2".equals(recType)){
//            	if (logger.isInfoEnabled())
//                {
//                    logger.info("用户" + customerSimp.getServNumber() + "未开通该类型呼转");
//                }
//            	
//            	out.write("1");
//                out.flush();
//            }else{
//            	forward = "failed";
//                this.createRecLog(Constants.BUSITYPE_RECCALLTRANSFER, "0", "0", "1", "业务受理失败");
//            	this.getRequest().setAttribute("errormessage", "业务受理失败");
//            	
//            	logger.error("呼叫转移业务受理失败");
//            }
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("commitReception End");
//        }
//        
//        return forward;
//    }
//    
//    public String getCurMenuId() {
//		return curMenuId;
//	}
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
//
//	public String getRecType()
//    {
//        return recType;
//    }
//    
//    public void setRecType(String recType)
//    {
//        this.recType = recType;
//    }
//    
//    public String getTransferType()
//    {
//        return transferType;
//    }
//    
//    public void setTransferType(String transferType)
//    {
//        this.transferType = transferType;
//    }
//    
//    public CallTransferBeanCQ getCallTransferBeanCQ()
//    {
//        return callTransferBeanCQ;
//    }
//    
//    public void setCallTransferBeanCQ(CallTransferBeanCQ callTransferBeanCQ)
//    {
//        this.callTransferBeanCQ = callTransferBeanCQ;
//    }
//    
//    public String getTransferNo()
//    {
//        return transferNo;
//    }
//    
//    public void setTransferNo(String transferNo)
//    {
//        this.transferNo = transferNo;
//    }
}
