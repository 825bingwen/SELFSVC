package com.gmcc.boss.selfsvc.serviceinfo.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.bean.CurrentStatusBean;
import com.gmcc.boss.selfsvc.common.BaseAction;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public class CurrentStatusAction extends BaseAction
{
    /**
     * ���л�
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * ��ǰ�˵�id
     */
    private String curMenuId = "";
    
    private static Log logger = LogFactory.getLog(CurrentStatusAction.class);
    
    // ���ýӿ�bean
    private CurrentStatusBean currentStatusBean;
    
    // ��ǰ״̬
    private String currentStatus;
    
    // ������Ϣ
    private String errormessage;
    
    //�ͻ���Ϣ
    private NserCustomerSimp customer;
    
    /**
     * ����״̬��ѯ
     * 
     * @return
     */
    public String qryCurrentStatus()
    {
        // ��¼��־
        logger.debug("queryCurrentStatus starting");
        
        // ��ȡsession
        HttpSession session = getRequest().getSession();
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ��ȡ�ͻ���Ϣ
        setCustomer((NserCustomerSimp)session.getAttribute(Constants.USER_INFO));
        
        // ��ѯ�ͻ�״̬��Ϣ
        Map result = currentStatusBean.queryCurrentStatus(terminalInfoPO, customer, curMenuId);
        
        // ����ҳ��
        String forward = "";
        
        if (result != null && result.size() > 1)
        {
            CTagSet cset = (CTagSet)result.get("returnObj");
            
            // ���õ�ǰ״̬
            setCurrentStatus(cset.GetValue("state"));
            
            // ��¼�ɹ���־
            this.createRecLog(Constants.BUSITYPE_CURRENTSTATUS, "0", "0", "0", "�ֻ���ǰ״̬:��ǰ״̬��ѯ�ɹ�!");
        
            forward = "qryCurrentStatus";
        }
        else if (result != null)
        {
        	setErrormessage("��ѯ�ֻ���ǰ����״̬ʧ�ܣ�" + result.get("returnMsg"));
            
            // ��¼������־
            this.createRecLog(Constants.BUSITYPE_CURRENTSTATUS, "0", "0", "1", "�ֻ���ǰ״̬:��ǰ״̬��ѯʧ��!");
            forward = "error";
        }
        else
        {
        	setErrormessage("��ѯ�ֻ���ǰ����״̬�쳣��" );
        	forward = "error";
        }
        
        logger.debug("queryCurrentStatus end!");
        
        return forward;
    }
    
    public String getCurMenuId() {
		return curMenuId;
	}

	public void setCurMenuId(String curMenuId) {
		this.curMenuId = curMenuId;
	}

	public CurrentStatusBean getCurrentStatusBean()
    {
        return currentStatusBean;
    }
    
    public void setCurrentStatusBean(CurrentStatusBean currentStatusBean)
    {
        this.currentStatusBean = currentStatusBean;
    }
    
    public String getCurrentStatus()
    {
        return currentStatus;
    }
    
    public void setCurrentStatus(String currentStatus)
    {
        this.currentStatus = currentStatus;
    }
    
    public NserCustomerSimp getCustomer()
    {
        return customer;
    }

    public void setCustomer(NserCustomerSimp customer)
    {
        this.customer = customer;
    }

	public String getErrormessage() 
	{
		return errormessage;
	}

	public void setErrormessage(String errormessage) 
	{
		this.errormessage = errormessage;
	}
}
