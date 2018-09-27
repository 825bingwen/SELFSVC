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
 * ʵ���˵����͹���
 * @author z90080209
 * @date   Nov 16, 2011
 */
public class BillSendAction extends BaseAction
{  
    // ��¼��־
    private static Log logger = LogFactory.getLog(BillSendAction.class);
    
    // ���л�
    private static final long serialVersionUID = 1L;
    
    // ��ǰ�˵�
    // begin zKF66389 findbus����
    //private String curMenuId;
    // begin zKF66389 findbus����
    
    // �˵���������,�����ʼ�--mltpEml������--mltpMms������--mltpSms
    private String billSendType;
    
    // �Ƿ�ͨ139�ֻ�����
    private String emailService;
    
    // �Ƿ�ͨ���ż���
    private String smsState;
    
    // �Ƿ�ͨ���ż���
    private String mmsState;
    
    // �Ƿ�ͨ�������
    private String emlState;
    
    // begin zKF66389 findbus����
    // �������ͣ�0 ȡ����1 ��ͨ��
    private String oprType;
    // end zKF66389 findbus����
    
    // �ӿڵ���
    private BillSendBean billSendBean;
    
    //�ӿڵ���
    private ReceptionBean receptionBean;
    
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
        String dictItem_spbizid = "";
        for(int i = 0;i < itemList.size();i++)
        {
            if(itemList.get(i).getDictid().equals("139spbizid"))
            {
                dictItem_spbizid = itemList.get(i).getDictname();
            }
        }
        
        setEmailService("0");
        // ���ýӿ��ж��û��Ƿ�ͨ139�ֻ�����
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
        // �ж��û��˵����Ϳ�ͨ״̬
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
            this.getRequest().setAttribute("errormessageBill", "�˵�������Ϣ��ѯʧ��!");
            
            // ��¼������־
            this.createRecLog("curMenuId", "0", "0", "1", "�˵�������Ϣ��ѯ:��Ϣ��ѯʧ��!");
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
        
        // ���ýӿڴ����˵����ͷ�ʽ
        // begin zKF66389 findbus����
        Map billSendMap = billSendBean.billSendCommit(termInfo, customer, "curMenuId", billSendType, mailAddr, oprType);
        // end zKF66389 findbus����
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
            // ��־��¼
            this.getRequest().setAttribute("errormessage", "�����˵����ͷ�ʽʧ��:"+billSendMap.get("returnMsg"));
            createRecLog(Constants.BUSITYPE_BILLSEND, "0", "0", "1", "�����˵����ͷ�ʽʧ��:" + billSendMap.get("returnMsg"));
            logger.debug("billSendCommit ended");
            
            return "error";
        }
        else
        {
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
    
    // begin zKF66389 findbus����
//    public String getCurMenuId() {
//		return curMenuId;
//	}
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
	// end zKF66389 findbus����

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
    
    // begin zKF66389 findbus����
    public String getOprType() {
		return oprType;
	}

	public void setOprType(String oprType) {
		this.oprType = oprType;
	}
	// end zKF66389 findbus����

	public String getMailAddr()
    {
        return mailAddr;
    }
    
    public void setMailAddr(String mailAddr)
    {
        this.mailAddr = mailAddr;
    }
}
