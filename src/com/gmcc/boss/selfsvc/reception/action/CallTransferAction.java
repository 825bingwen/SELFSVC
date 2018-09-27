/*
 * �ļ�����CallTransferAction.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ����������ת�ƿ�ͨ��ȡ��
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-12-22
 * �޸����ݣ�����
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
 * ����ת�ƿ�ͨ��ȡ��
 * 
 * 
 * @author g00140516
 * @version 1.0��2010-12-22
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
     * �ɹ���ʾ��Ϣ
     */
    private String successMessage = "";

	/**
     * ѡ���ת����
     * 
     * @return
     * @see
     */
    public String selectType()
    {
        return "transferType";
    }
    
    /**
     * �����ת����
     * 
     * @return
     * @see
     */
    public String inputNumber()
    {
        return "inputNumber";
    }
    
    /**
     * ����ת�ƿ�ͨ��ȡ��
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
        
        // add begin g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        this.getRequest().setAttribute("backStep", "-1");
        // add end g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        
        NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        if (transferNo == null)
        {
            transferNo = "";
        }
        
        // add begin xkf29026 2011/10/19 ��ת���벻��Ϊ����
        if(customerSimp.getServNumber().equals(transferNo))
        {
            this.createRecLog(Constants.BUSITYPE_RECCALLTRANSFER, "0", "0", "1", "ҵ������ʧ��,��ת���벻��Ϊ����");
            
            this.getRequest().setAttribute("errormessage", "ҵ������ʧ��,��ת���벻��Ϊ����");
            
            logger.error("ҵ������ʧ��,��ת���벻��Ϊ����");
            
            return forward;
        }
        // add end xkf29026 2011/10/19 ��ת���벻��Ϊ����
        
        String transferTypeName = "ҵ������";
        if ("24".equals(transferType))
        {
        	transferTypeName = "��������ת����";
        }
        else if ("25".equals(transferType))
        {
        	transferTypeName = "��æ��ת����";
        }
        else if ("26".equals(transferType))
        {
        	transferTypeName = "��Ӧ���ת����";
        }
        else if ("27".equals(transferType))
        {
        	transferTypeName = "���ɼ���ת����";
        }
        else if ("28".equals(transferType))
        {
        	transferTypeName = "����ת��ȡ��";
        } 
        
        if (callTransferBean.commitCallTransferNo(customerSimp, termInfo, transferType, transferNo, curMenuId))
        {
            forward = "success";
            
            this.createRecLog(Constants.BUSITYPE_RECCALLTRANSFER, "0", "0", "0", "ҵ������ɹ�");
            
            successMessage = transferTypeName + "�ɹ�";
            
            if (logger.isInfoEnabled())
            {
                logger.info("����ת��ҵ������ɹ�");
            }
        }
        else
        {
            this.createRecLog(Constants.BUSITYPE_RECCALLTRANSFER, "0", "0", "1", "ҵ������ʧ��");
            
            this.getRequest().setAttribute("errormessage", transferTypeName + "ʧ��");
            
            logger.error("����ת��ҵ������ʧ��");
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
