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
 * ʵ���˵����͹���
 * 
 * @author xkf29026
 * 
 */
public class BillSendAction extends BaseAction
{
    // ��¼��־
    private static Log logger = LogFactory.getLog(BillSendAction.class);
    
    // ���л�
    private static final long serialVersionUID = 1L;
    
    // ��ǰ�˵�
    private String curMenuId;
    
    // �˵���������,�����ʼ�--mltpEml������--mltpMms������--mltpSms
    private String billSendType;
    
    // �Ƿ�ͨ139�ֻ�����
    private String emailService;
    
    // �ӿڵ���
    private BillSendBean billSendBean;
    
    // ����ʼ�����ΪEmail�ʵ�����ΪEmail��ַ������ʼ������Ƕ����ʵ����߲����ʵ�����Ϊ�ֻ�����
    private String mailAddr;
    
    // ת��ѡ���˵����ͷ�ʽҳ��
    public String billSendPage()
    {
        HttpSession session = this.getRequest().getSession();
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ���ֵ���л�ȡ�ж��û��Ƿ�ͨ139�����������Ϣ
        List<DictItemPO> itemList = (List<DictItemPO>)PublicCache.getInstance().getCachedData("139emailService");
        
        // ���ýӿ��ж��û��Ƿ�ͨ139�ֻ�����
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
     * ѡ���˵��������ͺ��ύ
     * 
     * @return
     */
    public String billSendCommit()
    {
        // ��¼��־��ʼ
        logger.debug("billSendCommit starting...");
        
        HttpSession session = this.getRequest().getSession();
        
        // ��ȡ�ͻ���Ϣ
        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
        
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
        
        // ���˵�����ת��Ϊ�ӿ�Э���е�ֵ
        if (billSendType.equals("mltpEml"))// �˵����ͣ������ʼ�
        {
            billSendType = "mltp0200";
            
            mailAddr = customer.getServNumber() + "@139.com";
        }
        else if (billSendType.equals("mltpMms"))// �˵����ͣ�����
        {
            billSendType = "mltp0050";
            
            mailAddr = customer.getServNumber();
        }
        else if (billSendType.equals("mltpSms"))// �˵����ͣ�����
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
        
        // ���ýӿڴ����˵����ͷ�ʽ
        Map billSendMap = billSendBean.billSendCommit(termInfo, customer, curMenuId, billSendType, mailAddr);
        if (billSendMap != null && billSendMap.get("successFlag")!= null)
        {
            // ��־��¼
            this.getRequest().setAttribute("successMessage", "�����˵����ͷ�ʽ�ɹ���");
            createRecLog(Constants.BUSITYPE_BILLSEND, "0", "0", "0", "�����˵����ͷ�ʽ�ɹ�:" + billSendMap.get("returnMsg"));
            logger.debug("billSendCommit ended");
            
            return "success";
        }
        else if(billSendMap != null)
        {
            // add begin g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
            this.getRequest().setAttribute("backStep", "-1");           
            // add end g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
            
            // ��־��¼
            this.getRequest().setAttribute("errormessage", "�����˵����ͷ�ʽʧ��!");
            createRecLog(Constants.BUSITYPE_BILLSEND, "0", "0", "1", "�����˵����ͷ�ʽʧ��:" + billSendMap.get("returnMsg"));
            logger.debug("billSendCommit ended");
            
            return "error";
        }
        else
        {
            // add begin g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
            this.getRequest().setAttribute("backStep", "-1");           
            // add end g00140516 2012/06/01 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
            
            // ��־��¼
            this.getRequest().setAttribute("errormessage", "�����˵����ͷ�ʽʧ��!");
            createRecLog(Constants.BUSITYPE_BILLSEND, "0", "0", "1", "�����˵����ͷ�ʽʧ��!");
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
