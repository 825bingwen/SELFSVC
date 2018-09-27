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
 * ʵ����/�굥139���䶨�ƹ���
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  l00190940
 * @version  [�汾��, Spt 22, 2011]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MailBillSendAction extends BaseAction
{
	// ��¼��־
    private static Log logger = LogFactory.getLog(MailBillSendAction.class);
    
    // ���л�
    private static final long serialVersionUID = 1L;
    
    // ��ǰ�˵�
    private String curMenuId;
    
    // �˵����ͼ�¼��ʶ����ȡ������ҵ��ʱ���õ�
    private String oidBill;
    
    // �굥���ͼ�¼��ʶ����ȡ������ҵ��ʱ���õ�
    private String oidDetail;
    
    // �����˵���email
    private String emailBillServ;
    
    // �����굥��email
    private String emailDetailServ;
    
    // �ӿڵ���
    private MailBillSendBean mailBillSendBean;
    
    // �ɹ���Ϣ
    private String successMessage;

    //add begin m00227318 2012/10/11 V200R003C12L10 OR_SD_201209_443
    // ��־����ʶ�˵����ʼ����ƻ��ǲ��Ŷ��ƣ�Ĭ��Ϊ�ʼ�����
    private String mltpFlag = "typeEmail";
    
    //����ҳ����ʾ��Ϣ
    private String errormessage;
    //add end m00227318 2012/10/11 V200R003C12L10 OR_SD_201209_443
    
    /**
     * ��ѯ�˵��굥������Ϣ
     * <������ϸ����>
     * @param 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	public String qryBillMailInfo()
    {
		//add begin m00227318 2012/10/11 V200R003C12L10 OR_SD_201209_443
		//����
		String forward = "error";
		//add end m00227318 2012/10/11 V200R003C12L10 OR_SD_201209_443
		
		//��ȡsession��Ϣ
        HttpSession session = this.getRequest().getSession();
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        //modify begin m00227318 2012/10/17 V200R003C12L10 OR_SD_201209_443
        Map<String,Object> resBill = null;
        
        if("typeEmail".equals(mltpFlag))
        {
        	// �����˵����Ͳ�ѯ�Ľ�������䣬Ĭ��
        	resBill = mailBillSendBean.emailService(termInfo, customer, curMenuId, "Bill", "mltpEml");
        }
        else if("typeMms".equals(mltpFlag))
        {
        	// �����˵����Ͳ�ѯ�Ľ��������
            resBill = mailBillSendBean.emailService(termInfo, customer, curMenuId, "Bill", "mltpMms");
        }
        
        if (resBill != null && resBill.size() > 0)
        {
        	CTagSet tagSet = (CTagSet)resBill.get("returnObj");
        	
    		//���ر�����message_body�ڵ㲻Ϊ�գ�ȡoid��eamil��ַ
    		if (tagSet != null && tagSet.size() > 0)
    		{
    			// ���ݷ���ֵ�����˵����ͼ�¼��ʶ
    			setOidBill(tagSet.GetValue("oid"));
            
            	// email��ַ
    			String email = tagSet.GetValue("email");
            
    			if (oidBill != null && !"".equals(oidBill))
    			{
    				setEmailBillServ(email);
    			}
    		}
        }

		//��¼�ɹ���־����ת��ɹ�ҳ��
		if("typeEmail".equals(mltpFlag))
		{
			// ��¼�ɹ���־
			this.createRecLog(curMenuId, "0", "0", "0", "139�����˵�������Ϣ��ѯ:��ѯ�ɹ�!");
        
			forward = "qryResults";
		}
		else if("typeMms".equals(mltpFlag))
		{
			// ��¼�ɹ���־
			this.createRecLog(curMenuId, "0", "0", "0", "�����˵�������Ϣ��ѯ:��ѯ�ɹ�!");
        
			forward = "qryResultsMMS";
		}
		//modify end m00227318 2012/10/11 V200R003C12L10 OR_SD_201209_443
    	
        return forward;       
    }
	
	/**
     * ���Ϊ139�����˵����굥���͹���
     * <������ϸ����>
     * @param 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	public String changeBillOrDetailMail()
	{
		//��ȡsession��Ϣ
        HttpSession session = this.getRequest().getSession();
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        //modify begin m00227318 2012/10/13 V200R003C12L10 OR_SD_201209_443
        String forward = "failed";
        
        // ȡ��֮ǰ���˵����͹��ܣ�����ͨ139�����˵����͹���
        if (cancelBillMail(termInfo, customer, oidBill) && openBillMail(termInfo, customer, "Bill"))
        {
        	// ��¼�ɹ���־
            this.createRecLog(curMenuId, "0", "0", "0", "139�����˵�����ҵ��:����ɹ�!");
                
        	forward = "success";        		
        	setSuccessMessage("139�����˵�����ҵ�����ɹ���");
        }
        else
        {
        	// ��¼������־
            this.createRecLog(curMenuId, "0", "0", "1", "139�����˵�����ҵ��:���ʧ��!");
            
            this.getRequest().setAttribute("errormessage", "139�����˵�����ҵ����ʧ��!");
        }
		
		return forward;
		//modify end m00227318 2012/10/13 V200R003C12L10 OR_SD_201209_443
	}
	
	/**
     * ��ͨ139�������ŵ��˵����굥���͹���
     * <������ϸ����>
     * @param 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	public String openBillOrDetailMail()
	{
		//��ȡsession��Ϣ
        HttpSession session = this.getRequest().getSession();
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        //modify begin m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
        String forward = "failed";
        
        if(openBillMail(termInfo, customer, "Bill"))
        {
        	forward = "success";
        		
        	if("typeEmail".equals(mltpFlag))
            {
        		// ��¼�ɹ���־
                this.createRecLog(curMenuId, "0", "0", "0", "139�����˵�����ҵ��:���Ƴɹ�!");
                
            	setSuccessMessage("139�����˵�����ҵ���Ƴɹ���");
            }
            else if("typeMms".equals(mltpFlag))
            {
            	// ��¼�ɹ���־
                this.createRecLog(curMenuId, "0", "0", "0", "�����˵�����ҵ��:���Ƴɹ�!");
                
            	setSuccessMessage("�����˵�����ҵ���Ƴɹ���");
            }
        }
        else
        {
        	if("typeEmail".equals(mltpFlag))
            {
        		// ��¼������־
        		this.createRecLog(curMenuId, "0", "0", "1", "139�����˵�����ҵ��:����ʧ��!");
        		
        		this.getRequest().setAttribute("errormessage", "139�����˵�����ҵ����ʧ��!");
            }
        	else if("typeMms".equals(mltpFlag))
        	{
        		// ��¼������־
        		this.createRecLog(curMenuId, "0", "0", "1", "�����˵�����ҵ��:����ʧ��!");
        		
        		this.getRequest().setAttribute("errormessage", "�����˵�����ҵ����ʧ��!");
        	}
        }
		
		return forward;
		//modify end m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
	}
	
	/**
     * ȡ��139�������ŵ��˵����굥���͹���
     * <������ϸ����>
     * @author m00227318
     * @param 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	public String cancleBillOrDetailMail()
	{
		//��ȡsession��Ϣ
        HttpSession session = this.getRequest().getSession();
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        String forward = "failed";
        
        if (cancelBillMail(termInfo, customer, oidBill))
        {
        	forward = "success";
        		
        	if("typeEmail".equals(mltpFlag))
            {
            	// ��¼�ɹ���־
                this.createRecLog(curMenuId, "0", "0", "0", "139�����˵�����ҵ��:ȡ���ɹ�!");
                
            	setSuccessMessage("139�����˵�����ҵ��ȡ���ɹ���");
            }
            else if("typeMms".equals(mltpFlag))
            {
            	// ��¼�ɹ���־
                this.createRecLog(curMenuId, "0", "0", "0", "�����˵�����ҵ��:ȡ���ɹ�!");
                
            	setSuccessMessage("�����˵�����ҵ��ȡ���ɹ���");
            }
        }
        else
        {
        	if("typeEmail".equals(mltpFlag))
            {
        		// ��¼������־
        		this.createRecLog(curMenuId, "0", "0", "1", "139�����˵�����ҵ��:ȡ��ʧ��!");
        		
        		this.getRequest().setAttribute("errormessage", "139�����˵�����ҵ��ȡ��ʧ��!");
            }
        	if("typeMms".equals(mltpFlag))
            {
        		// ��¼������־
        		this.createRecLog(curMenuId, "0", "0", "1", "�����˵�����ҵ��:ȡ��ʧ��!");
        		
        		this.getRequest().setAttribute("errormessage", "�����˵�����ҵ��ȡ��ʧ��!");
            }
        }
		
		return forward;
	}
	
	/**
     * ȡ��֮ǰ���˵����굥���͹���
     * <������ϸ����>
     * @param 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	private boolean cancelBillMail(TerminalInfoPO termInfo, NserCustomerSimp customer, String oid)
	{
		//modify begin m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
        Map<String,Object> cancelResult = mailBillSendBean.cancelBillMail(termInfo, customer, curMenuId, oid);
        //modify end m00227318 2012/10/15 V200R003C12L10 OR_SD_201209_443
        if (cancelResult == null || cancelResult.size() == 0)
        {
        	// add begin g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        	this.getRequest().setAttribute("backStep", "-1");
        	// add end g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        	
        	return false;
        }
        return true;
	}
	
	/**
     * ��ͨ139�������ŵ��˵����굥���͹���
     * <������ϸ����>
     * @param 
     * @return
     * @see [�ࡢ��#��������#��Ա]
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
        	// add begin g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        	this.getRequest().setAttribute("backStep", "-1");
        	// add end g00140516 2012/05/30 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
        	        	      	
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
