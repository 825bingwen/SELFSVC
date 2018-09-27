package com.customize.cq.selfsvc.reception.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.cq.selfsvc.bean.MailBillSendBean;
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
//	// ��¼��־
//    private static Log logger = LogFactory.getLog(MailBillSendAction.class);
//    
//    // ���л�
//    private static final long serialVersionUID = 1L;
//    
//    // begin zKF66389 findbus����
//    // ��ǰ�˵�
//    private String curMenuId;
//    // begin zKF66389 findbus����
//    
//    // �˵����ͼ�¼��ʶ����ȡ������ҵ��ʱ���õ�
//    private String oidBill;
//    
//    // �굥���ͼ�¼��ʶ����ȡ������ҵ��ʱ���õ�
//    private String oidDetail;
//    
//    // �����˵���email
//    private String emailBillServ;
//    
//    // �����굥��email
//    private String emailDetailServ;
//    
//    // �ӿڵ���
//    private MailBillSendBean mailBillSendBean;
//    
//    // ȡ���˵����ͻ����굥���͵ı�־,1���˵����ͣ�2���굥����
//    private String flag;
//    
//    // �ɹ���Ϣ
//    private String successMessage;
//
//    /**
//     * ��ѯ�˵��굥������Ϣ
//     * <������ϸ����>
//     * @param 
//     * @return
//     * @see [�ࡢ��#��������#��Ա]
//     */
//	public String qryBillMailInfo()
//    {
//		if (logger.isDebugEnabled())
//        {
//            logger.debug("query BillMailInformation Starting...");
//        }
//		
//		//��ȡsession��Ϣ
//        HttpSession session = this.getRequest().getSession();
//        
//        // ��ȡ�ͻ���Ϣ
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        // ��ȡ�ն˻���Ϣ
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        // �����˵����Ͳ�ѯ�Ľ��
//        Map resBill = mailBillSendBean.emailService(termInfo, customer, curMenuId, "Bill", "mltpEml");
//       
//        if (resBill != null && resBill.size() > 0)
//        {
//            CTagSet tagSet = (CTagSet)resBill.get("returnObj");
//            
//            // ���ݷ���ֵ�����˵����ͼ�¼��ʶ
//            setOidBill(tagSet.GetValue("oid"));
//            
//            // email��ַ
//            String email = tagSet.GetValue("email");
//            
//            if (oidBill != null && !"".equals(oidBill))
//            {
//            	setEmailBillServ(email);
//            }
//        }
//        else
//        {
//            this.getRequest().setAttribute("errormessageBill", "�˵�������Ϣ��ѯʧ��!");
//            
//            // ��¼������־
//            this.createRecLog(curMenuId, "0", "0", "1", "�˵�������Ϣ��ѯ:��Ϣ��ѯʧ��!");
//        }
//        
//        // �����굥���Ͳ�ѯ���
//        Map resDetail = mailBillSendBean.emailService(termInfo, customer, curMenuId, "BillDetail", "mltpEml");
//        
//        if (resDetail != null && resDetail.size() > 0)
//        {
//            CTagSet tagSet = (CTagSet)resDetail.get("returnObj");
//            
//            // ���ݷ���ֵ�����굥���ͼ�¼��ʶ
//            setOidDetail(tagSet.GetValue("oid"));
//            
//            // email��ַ
//            String email = tagSet.GetValue("email");
//            
//            if (oidDetail != null && !"".equals(oidDetail))
//            {
//            	setEmailDetailServ(email);
//            }
//            
//        }
//        else
//        {
//            this.getRequest().setAttribute("errormessageDetail", "�굥������Ϣ��ѯʧ��!");
//            
//            // ��¼������־
//            this.createRecLog(curMenuId, "0", "0", "1", "�굥������Ϣ��ѯ:��Ϣ��ѯʧ��!");
//        }
//  
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("query BillMailInformation End!");
//        }
//    	
//    	return "qryResults";
//    }
//	
//	/**
//     * ���Ϊ139�����˵����굥���͹���
//     * <������ϸ����>
//     * @param 
//     * @return
//     * @see [�ࡢ��#��������#��Ա]
//     */
//	public String changeBillOrDetailMail()
//	{
//		if (logger.isDebugEnabled())
//        {
//            logger.debug("change bill mail to 139 Starting...");
//        }
//		
//		//��ȡsession��Ϣ
//        HttpSession session = this.getRequest().getSession();
//        
//        // ��ȡ�ͻ���Ϣ
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        // ��ȡ�ն˻���Ϣ
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        if("1".equals(flag))
//        {
//        	if(!cancelBillMail(termInfo, customer, oidBill))
//        	{
//        		
//        		return "failed";
//        	}
//            
//        	if(!openBillMail(termInfo, customer, "Bill"))
//        	{
//        		return "failed";
//        	}
//            
//            setSuccessMessage("139�����˵�����ҵ���Ƴɹ���");
//        }
//        else if("2".equals(flag))
//        {
//        	// ȡ��֮ǰ���굥���͹���
// 
//        	if(!cancelBillMail(termInfo, customer, oidDetail))
//        	{
//        		return "failed";
//        	}
//        	
//        	if(!openBillMail(termInfo, customer, "BillDetail"))
//        	{
//        		return "failed";
//        	}
//            
//            setSuccessMessage("139�����굥����ҵ���Ƴɹ���");
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("change bill mail to 139 End!");
//        }
//		
//		return "success";
//	}
//	
//	/**
//     * ��ͨ139�����˵����굥���͹���
//     * <������ϸ����>
//     * @param 
//     * @return
//     * @see [�ࡢ��#��������#��Ա]
//     */
//	public String openBillOrDetailMail()
//	{
//		if (logger.isDebugEnabled())
//        {
//            logger.debug("open 139 bill mail Starting...");
//        }
//		//��ȡsession��Ϣ
//        HttpSession session = this.getRequest().getSession();
//        
//        // ��ȡ�ͻ���Ϣ
//        NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
//        
//        // ��ȡ�ն˻���Ϣ
//        TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
//        
//        if("1".equals(flag))
//        {
//        	if(!openBillMail(termInfo, customer, "Bill"))
//        	{
//        		return "failed";
//        	}
//            
//            setSuccessMessage("139�����˵�����ҵ���Ƴɹ���");
//        }
//        else if("2".equals(flag))
//        {   
//        	if(!openBillMail(termInfo, customer, "BillDetail"))
//        	{
//        		return "failed";
//        	}
//            
//            setSuccessMessage("139�����굥����ҵ���Ƴɹ���");
//        }
//        
//        if (logger.isDebugEnabled())
//        {
//            logger.debug("open 139 bill mail End!");
//        }
//		
//		return "success";
//	}
//	
//	/**
//     * ȡ��֮ǰ���˵����굥���͹���
//     * <������ϸ����>
//     * @param 
//     * @return
//     * @see [�ࡢ��#��������#��Ա]
//     */
//	private boolean cancelBillMail(TerminalInfoPO termInfo, NserCustomerSimp customer, String oid)
//	{
//        Map cancelResult = mailBillSendBean.cancelBillMail(termInfo, customer, curMenuId, oid);
//        
//        if (cancelResult == null || cancelResult.size() == 0)
//        {
//        	this.getRequest().setAttribute("errormessage", "����ԭ�˵����͵�ַʧ�ܣ�");
//        	
//        	// ��¼������־
//            this.createRecLog(curMenuId, "0", "0", "1", "����ԭ�˵����͵�ַʧ�ܣ�");
//        	
//        	return false;
//        }
//        return true;
//	}
//	
//	/**
//     * ��ͨ139�����˵����굥���͹���
//     * <������ϸ����>
//     * @param 
//     * @return
//     * @see [�ࡢ��#��������#��Ա]
//     */
//	private boolean openBillMail(TerminalInfoPO termInfo, NserCustomerSimp customer, String billType)
//	{
//        String email = customer.getServNumber() + "@139.com";
//        
//        Map openResult = mailBillSendBean.openBillMail(termInfo, customer, curMenuId, billType, "Che", "mltpEml", "", "", "", "", email);
//        
//        if (openResult == null || openResult.size() == 0)
//        {
//        	this.getRequest().setAttribute("errormessage", "��ͨ139�����˵�����ҵ��ʧ�ܣ�");
//        	
//        	// ��¼������־
//            this.createRecLog(curMenuId, "0", "0", "1", "��ͨ139�����˵�����ҵ��ʧ�ܣ�");
//        	
//        	return false;
//        }
//        return true;
//	}
//
//	public MailBillSendBean getMailBillSendBean() {
//		return mailBillSendBean;
//	}
//
//	public void setMailBillSendBean(MailBillSendBean mailBillSendBean) {
//		this.mailBillSendBean = mailBillSendBean;
//	}
//
//	public String getCurMenuId() {
//		return curMenuId;
//	}
//
//	public void setCurMenuId(String curMenuId) {
//		this.curMenuId = curMenuId;
//	}
//
//	public String getEmailDetailServ() {
//		return emailDetailServ;
//	}
//
//	public void setEmailDetailServ(String emailDetailServ) {
//		this.emailDetailServ = emailDetailServ;
//	}
//
//	public String getEmailBillServ() {
//		return emailBillServ;
//	}
//
//	public void setEmailBillServ(String emailBillServ) {
//		this.emailBillServ = emailBillServ;
//	}
//
//	public String getOidBill() {
//		return oidBill;
//	}
//
//	public void setOidBill(String oidBill) {
//		this.oidBill = oidBill;
//	}
//
//	public String getOidDetail() {
//		return oidDetail;
//	}
//
//	public void setOidDetail(String oidDetail) {
//		this.oidDetail = oidDetail;
//	}
//
//    public String getFlag() {
//		return flag;
//	}
//
//	public void setFlag(String flag) {
//		this.flag = flag;
//	}
//
//	public String getSuccessMessage() {
//		return successMessage;
//	}
//
//	public void setSuccessMessage(String successMessage) {
//		this.successMessage = successMessage;
//	}
}
