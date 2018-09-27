package com.customize.cq.selfsvc.call.impl;

import java.util.Map;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.cq.selfsvc.call.SelfSvcCallCQ;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.util.CommonUtil;

public class SelfSvcCallCQImpl implements SelfSvcCallCQ
{
    private static Log logger = LogFactory.getLog(SelfSvcCallCQImpl.class);
    
    private IntMsgUtil intMsgUtil;
    
    /**
     * �����ײ���Ϣ��ѯ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryComboInfo(Map map)
    {
        try{
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String cycle = (String)map.get("cycle");
            String menuid = (String)map.get("curMenuId");
            
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            //add begin l00190940 2011-11-16 �ײ���Ϣ��ѯ
            // ����
            Element eReq_cycle = eBody.addElement("cycle");
            eReq_cycle.addText(cycle);
            //add end l00190940 2011-11-16 �ײ���Ϣ��ѯ
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_taocan_cq", menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�ײ���Ϣ��ѯʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * ɽ�����˵���ѯ
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryMonthBill(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnumber");
            String billCycle = (String)map.get("billCycle");
            
            IntMsgUtil imu = new IntMsgUtil();
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // �˵�ģ�����ͣ�ɽ���̶���23
            Element eReq_type = eBody.addElement("type");
            eReq_type.addText("23");
            
            // ��ѯ�·�
            Element eReq_cycleoffset = eBody.addElement("month");
            eReq_cycleoffset.addText(billCycle);
            
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document docXML = imu.createMsg(doc, "cli_qry_detailedbill", menuID, touchOID, "1", telnumber, operID, termID);
            
            return imu.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("���˵���ѯʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }       
    }
    
    /**
     * �˻�����ѯ
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap queryBalance(Map map)
    {
        try
        {                
            String operid = (String)map.get("operid");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("menuid");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_balance_cq",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        } 
        catch (Exception e)
        {
            logger.error("����ѯʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("����ѯ�쳣");
            
            return rw;
        }    
    }
    

    /**
     * ���ѳ�ֵ�˻���Ϣ��ѯ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryfeeChargeAccount(Map map)
    {
        try
        {
            String operid = (String)map.get("operid");
            String atsvNum = (String)map.get("atsvNum");
            String servnumber = (String)map.get("servnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("menuid");
            String bankNo = (String)map.get("bankNo");
            String payDate = (String)map.get("payDate");
            String acceptType = (String)map.get("acceptType");
            
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �����к�
            Element eReq_bankNo = eBody.addElement("bank_no");
            eReq_bankNo.addText(bankNo);
            
            // �ɷ�ʱ��
            Element eReq_payDate = eBody.addElement("pay_date");
            eReq_payDate.addText(payDate);
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            // ��������
            Element eReq_acceptType = eBody.addElement("accept_type");
            eReq_acceptType.addText(acceptType);
            
            docXML = intMsgUtil.createMsg(doc, "cli_qry_fee", menuid, touchoid, "1", servnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�ɷ�ǰ��ѯ�˻���Ϣʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * ���ѳ�ֵ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chargeCommit(Map map)
    {
        try
        {
            String operid = (String)map.get("operid");
            String termid = (String)map.get("termid");
            String menuid = (String)map.get("menuid");
            String servnumber = (String)map.get("servnumber");
            String bankNo = (String)map.get("bankNo");
            String payDate = (String)map.get("payDate");
            String terminalSeq = (String)map.get("terminalSeq");
            String money = (String)map.get("money");
            String acceptType = (String)map.get("acceptType");
            String printFlag = (String)map.get("invoiceType");
            String bankSite = (String)map.get("bankSite");
            String bankOper = (String)map.get("bankOper");
            String touchoid = (String)map.get("touchoid");
            
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �����к�
            Element eReq_bankNo = eBody.addElement("bank_no");
            eReq_bankNo.addText(bankNo);
            
            // �ɷ�ʱ��
            Element eReq_payDate = eBody.addElement("pay_date");
            eReq_payDate.addText(payDate);
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            // ���н�����ˮ��
            Element eReq_bankSeq = eBody.addElement("bank_nbr");
            eReq_bankSeq.addText(terminalSeq);
            
            // ���
            Element eReq_amount = eBody.addElement("amount");
            eReq_amount.addText(money);
            
            // ��������
            Element eReq_acceptType = eBody.addElement("accept_type");
            eReq_acceptType.addText(acceptType);
            
            // ��ӡ��־
            Element eReq_printFlag = eBody.addElement("print_flag");
            eReq_printFlag.addText(printFlag);
            
            // ����Ӫҵ������
            Element eReq_bankSite = eBody.addElement("bsite");
            eReq_bankSite.addText(bankSite);
            
            // ���в���Ա���
            Element eReq_bankOper = eBody.addElement("boperid");
            eReq_bankOper.addText(bankOper);
            
            // ��������
            Element eReq_system = eBody.addElement("systemid");
            eReq_system.addText("1");// �����ն�
            
            docXML = intMsgUtil.createMsg(doc, "cli_busi_chargefee", menuid, touchoid, "1", servnumber, operid, termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�ɷ�ʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            return rw;
        }
    }
    
    /**
     * ���û���������������
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap sendSMS(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String smsContent = "1" + (String)map.get("smsContent");
        String priority = (String)map.get("priority");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            IntMsgUtil imu = new IntMsgUtil();
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            //����ģ����
            Element eReq_templateno = eBody.addElement("templateno");
            eReq_templateno.addText("Atsv0001");
            
            //�����б�
            Element eReq_smsparam = eBody.addElement("smsparam");
            eReq_smsparam.addText(smsContent);
            
            //�Ƿ��������
            Element eReq_isrvcall = eBody.addElement("isrvcall");
            eReq_isrvcall.addText("1");
            
            docXML = imu.createMsg(doc, "cli_busi_sendsmsinfo", menuID, touchOID, "1", telnum, operID, termID);
            return imu.invoke(docXML);

        }
        catch (Exception e)
        {
            logger.error("���Ͷ���ʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * ��У�����룬ֱ�ӻ�ȡ�û���Ϣ
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap getUserStatus(Map map)
    {
        try
        {            
            String telnumber = (String)map.get("telnum");
            String password = (String)map.get("password");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            // �Ƿ�У������,0��У������
            Element eReq_isCheck = eBody.addElement("ischeckpass");
            eReq_isCheck.addText("0");
            
            // ����
            Element eReq_password = eBody.addElement("password");
            eReq_password.addText(password);
            
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_userinfo", "", "", "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ȡ�û���Ϣʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
    }
    
    
    /**
     * ��ȡ�˵����Ͳ�ѯ�ķ�����Ϣ
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap getMailBillSendInfo(Map map)
    {
        try
        {          
            String telnumber = (String)map.get("telnum");
            String billType = (String)map.get("billtype");
            String mailType = (String)map.get("mailtype");
            String menuID = (String)map.get("curMenuId");
            String touchOID = (String)map.get("touchoid");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // �˵�����
            Element eReq_billType = eBody.addElement("billtype");
            eReq_billType.addText(billType);
            
            // �ʼ�����
            Element eReq_mailType = eBody.addElement("mailtype");
            eReq_mailType.addText(mailType);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_billmail", menuID, touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ȡ�˵�������Ϣʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
    }
    
    /**
     * ����ԭ�������
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
	public ReturnWrap getCancelCaseInfo(Map map) 
	{
    	try
        {          
            String telnumber = (String)map.get("telnum");
            String oid = (String)map.get("oid");
            String menuID = (String)map.get("curMenuId");
            String touchOID = (String)map.get("touchoid");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // �˵�����
            Element eReq_billType = eBody.addElement("oid");
            eReq_billType.addText(oid);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_cancelbillmail", menuID, touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("����ԭ�������ʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
	}
	
	/**
     * ��ͨ�������
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
	public ReturnWrap getOpenBillMailInfo(Map map) 
	{
		try
        {          
            String telnumber = (String)map.get("telnum");
            String billType = (String)map.get("billtype");
            String langType = (String)map.get("langtype");
            String mailType = (String)map.get("mailtype");
            String mailAddr = (String)map.get("mailaddr");
            String postCode = (String)map.get("postcode");
            String linkPhone = (String)map.get("linkphone");
            String linkMan = (String)map.get("linkman");
            String email = (String)map.get("email");
            String menuID = (String)map.get("curMenuId");
            String touchOID = (String)map.get("touchoid");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // �˵�����
            Element eReq_billType = eBody.addElement("billtype");
            eReq_billType.addText(billType);
            
            // ��������
            Element eReq_langType = eBody.addElement("langtype");
            eReq_langType.addText(langType);
            
            // �ʼ�����
            Element eReq_mailType = eBody.addElement("mailtype");
            eReq_mailType.addText(mailType);
            
            // �ʼĵ�ַ
            Element eReq_mailAddr = eBody.addElement("mailaddr");
            eReq_mailAddr.addText(mailAddr);
            
            // ��������
            Element eReq_postCode = eBody.addElement("postcode");
            eReq_postCode.addText(postCode);
            
            // ��ϵ�绰
            Element eReq_linkPhone = eBody.addElement("linkphone");
            eReq_linkPhone.addText(linkPhone);
            
            // ��ϵ��
            Element eReq_linkMan = eBody.addElement("linkman");
            eReq_linkMan.addText(linkMan);
            
            // �����ַ
            Element eReq_email = eBody.addElement("email");
            eReq_email.addText(email);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_addbillmail", menuID, touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ͨ139�������ʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
	}
	
    /**
     * ��ѯ�û��Ƿ��ѿ�ͨ�ֻ�����
     * @param map
     * @return
     */
    public ReturnWrap qrymailBox(Map map)
    {
    	try
    	{
    		String telnum = (String)map.get("telnum");
    		String email = (String)map.get("email");
    		String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // �ֻ������ַ
            Element eReq_email = eBody.addElement("email");
            eReq_email.addText(email);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_mailbox", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
    	}
    	catch (Exception e)
        {
            logger.error("��ѯ�û��Ƿ��ѿ�ͨ�ֻ�����ʧ��!", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
    }
    
    /**
     * ��ͨ139�������
     */
    public ReturnWrap add139Mail(Map map)
    {
    	try
    	{
    		String telnum = (String)map.get("telnum");
    		String email = (String)map.get("email");
    		String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �û��ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // �ֻ������ַ
            Element eReq_email = eBody.addElement("email");
            eReq_email.addText(email);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_add139mail", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
    	}
    	catch (Exception e)
        {
            logger.error("��ͨ139�������ʧ��!", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
    }
    
    /**
     * ҵ��ͳһ��ѯ�ӿ�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap queryService(Map map)
    {
        try
        {
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String sn = (String)map.get("sn");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // ���
            Element eReq_sn = eBody.addElement("sn");
            eReq_sn.addText(sn);
            
            docXML = intMsgUtil.createMsg(doc, "cli_qry_spinfo", menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            //log.error("ҵ��ͳһ��ѯ�ӿ�ʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * �˵����ͷ�ʽ�ύ
     */
    public ReturnWrap billSendCommit(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("operID");// ����Ա
            String termid = (String)map.get("termID");//�ն˺�
            String telnum = (String)map.get("telnum");//�ֻ���
            String touchoid = (String)map.get("contactID");//�Ӵ���
            String menuid = (String)map.get("menuID");//�˵�id
            String mailtype = (String)map.get("mailtype");//�˵���������
            String oprtype = (String)map.get("oprtype");// ��������
            String mailaddr = (String)map.get("mailAddr");//�����ַ
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // �˵���������
            Element eReq_mailtype = eBody.addElement("mailtype");
            eReq_mailtype.addText(mailtype);
            
            // �������ͣ�Ĭ��Ϊ1����ͨ
            Element eReq_oprtype = eBody.addElement("oprtype");
            eReq_oprtype.addText(oprtype);
            
            // ����ʼ�����ΪEmail�ʵ�����ΪEmail��ַ������ʼ������Ƕ����ʵ����߲����ʵ�����Ϊ�ֻ�����
            Element eReq_mailaddr = eBody.addElement("mailaddr");
            eReq_mailaddr.addText(mailaddr);
            
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_sendbill", menuid, touchoid, "1", telnum, operid, termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            //log.error("�˵�����ҵ������ʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
    }
    
    public ReturnWrap getBillSendInfo(Map map)
    {
        try
        {          
            String telnumber = (String)map.get("telnum");
            String menuID = (String)map.get("curMenuId");
            String touchOID = (String)map.get("touchoid");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            //�Ƿ��ѯ��ʷ 1Ϊ��ѯ ����Ϊ����ѯ
            Element eReq_qryhis = eBody.addElement("qryhis");
            eReq_qryhis.addText("0");
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_billsend_cq", menuID, touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ȡ�˵�������Ϣʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
    }
    
    /**
     * ����Ʒ���ʷѼ��ѿ�ͨ���ܣ���Ʒչʾ�� <������ϸ����>
     * 
     * @param map
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryFavourable(Map map)
    {
        
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("telnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        
        try
        {
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_producttree_cq",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * �����Ż�ҵ���ѯ
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryPrivInfo(Map map)
    {
        
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("telnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        try
        {
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_promotions_cq",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            ReturnWrap rw = intMsgUtil.invoke(docXML);
            return rw;
        }
        catch (Exception e)
        {
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
        
    }
    
    /**
     * �����Ż�ҵ������
     */
    public ReturnWrap privAcceptCommit(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String isSubmit = (String)map.get("issubmit");
            String nCode = (String)map.get("nCode");
            String sType = (String)map.get("sType");
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // �����Ż�
            Element eReq_nCode = eBody.addElement("ncode");
            eReq_nCode.addText(nCode);
            
            // �������� PCOpRec
            Element eReq_sType = eBody.addElement("stype");
            eReq_sType.addText(sType);
            
            // ����ʽ 0 Ԥ���� 1ֱ������
            Element eReq_issubmit = eBody.addElement("issubmit");
            eReq_issubmit.addText(isSubmit);
            
            docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_promotions_cq",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            // logger.error("�ɷѽӿڳ����쳣��", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("");
            
            return rw;
        }
    }
    
    /**
     * �����Ʒ�������ѯ����ת���Ĳ�Ʒ
     */
    public ReturnWrap qryChangeList(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �������
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText((String)map.get("region"));
            
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText((String)map.get("rectype"));
            
            Element eReq_mainprodid = eBody.addElement("mainprodid");
            eReq_mainprodid.addText((String)map.get("mainprodid"));
            
            docXML = intMsgUtil.createMsg(doc,
                    "cli_package_changelist_cq",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            //log.error("��ѯ�û�����ת���Ĳ�Ʒʧ��", e);
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("��ѯ�û�����ת���Ĳ�Ʒʧ��.");
            return rw;
        }
    }
    
    /**
     * ��֤�û�ѡ����������Ʒ�Ƿ�߱�ת������
     * 
     * @param inMap
     * @return
     */
    public ReturnWrap prodChgCheck(Map inMap)
    {
        try
        {
            String operid = (String)inMap.get("curOper");
            String atsvNum = (String)inMap.get("atsvNum");
            String telnumber = (String)inMap.get("telnumber");
            String touchoid = (String)inMap.get("touchoid");
            String menuid = (String)inMap.get("curMenuId");
            
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText((String)inMap.get("telnum"));
            
            // �ɵ������Ʒ����
            Element eReq_oldmainprodid = eBody.addElement("oldmainprodid");
            eReq_oldmainprodid.addText((String)inMap.get("oldmainprodid"));
            
            // �µ������Ʒ����
            Element eReq_newmainprodid = eBody.addElement("newmainprodid");
            eReq_newmainprodid.addText((String)inMap.get("newmainprodid"));
            
            // ��������(ChangProductMain)
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText((String)inMap.get("rectype"));
            
            // ��Ч����(EffectNextMonth)
            Element eReq_affecttype = eBody.addElement("affecttype");
            eReq_affecttype.addText((String)inMap.get("affecttype"));
            
            //log.info("����ҵ��ID[cli_package_chgcheck_hub]��֤�û��Ƿ�߱�ת��������ʼ.");
            
            docXML = intMsgUtil.createMsg(doc,
                    "cli_package_chgcheck_cq",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            
            //log.info("����ҵ��ID[cli_package_chgcheck_hub]��֤�û��Ƿ�߱�ת����������.");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            //log.error("��֤�û��Ƿ�߱�ת������ʧ��", e);
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg(e.getMessage());
            return rw;
        }
    }
    
    /**
     * �����û�ѡ����²�Ʒ����ѯ���ò�Ʒ��ģ���б�
     */
    public ReturnWrap getProdTmpList(Map inMap)
    {
        try
        {
            String operid = (String)inMap.get("curOper");
            String atsvNum = (String)inMap.get("atsvNum");
            String telnumber = (String)inMap.get("telnumber");
            String touchoid = (String)inMap.get("touchoid");
            String menuid = (String)inMap.get("curMenuId");
            
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText((String)inMap.get("telnum"));
            
            // ����
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText((String)inMap.get("region"));
            
            // ��������(ChangeProduct)
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText((String)inMap.get("rectype"));
            
            // �������Ʒ����
            Element eReq_mainprodid = eBody.addElement("mainprodid");
            eReq_mainprodid.addText((String)inMap.get("mainprodid"));
            
            //log.info("����ҵ��ID[cli_package_gettmplist_hub]��ѯ��Ʒ��ģ�忪ʼ.");
            
            docXML = intMsgUtil.createMsg(doc,
                    "cli_package_gettmplist_cq",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            
            //log.info("����ҵ��ID[cli_package_gettmplist_hub]��ѯ��Ʒ��ģ�����.");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            //log.error("��ѯ��Ʒ��ģ���б�ʧ��", e);
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg(e.getMessage());
            return rw;
        }
    }
    
    /**
     * ��ȡģ����ϸ
     */
    public ReturnWrap qryChgContent(Map inMap)
    {
        try
        {
            String operid = (String)inMap.get("curOper");
            String atsvNum = (String)inMap.get("atsvNum");
            String telnumber = (String)inMap.get("telnumber");
            String menuid = (String)inMap.get("curMenuId");
            String fromProdId = (String)inMap.get("fromProdId");
            String toProdId = (String)inMap.get("toProdId");
            
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText((String)inMap.get("telnum"));
            
            // ����
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText((String)inMap.get("region"));
            
            // ��������(Install)
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText((String)inMap.get("rectype"));
            
            // �������Ʒ����
            Element eReq_mainprodid = eBody.addElement("mainprodid");
            eReq_mainprodid.addText((String)inMap.get("mainprodid"));
            
            // ģ��ID
            Element eReq_templeteid = eBody.addElement("templeteid");
            eReq_templeteid.addText((String)inMap.get("templeteid"));
            
            //log.info("����ҵ��ID[cli_package_chgcontent_hub]��ʼ.");
            docXML = intMsgUtil.createMsg(doc,
                    "cli_package_chgcontent_cq",
                    menuid,
                    "",
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            //log.info("����ҵ��ID[cli_package_chgcontent_hub]��ʼ.");
            
            // ������Ҫ���õ����Ľ���
            return intMsgUtil.invokeProdChg(docXML);
            
        }
        catch (Exception e)
        {
            //log.error("���ݲ�Ʒģ��ID��ѯģ����ϸʧ��", e);
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("���ݲ�Ʒģ��ID��ѯģ����ϸʧ��.");
            return rw;
        }
        
    }
    
    /**
     * �ײ�ת��������
     */
    public ReturnWrap prodChgCommit(Map inMap)
    {
        try
        {
            String operid = (String)inMap.get("curOper");
            String atsvNum = (String)inMap.get("atsvNum");
            String telnumber = (String)inMap.get("telnumber");
            String menuid = (String)inMap.get("curMenuId");
            String sn = (String)inMap.get("sn");
            String fromProdId = (String)inMap.get("fromProdId");
            String toProdId = (String)inMap.get("toProdId");
            
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnumber = eBody.addElement("telnum");
            eReq_telnumber.addText(telnumber);
            
            // �����Ʒ����
            Element eReq_mainprodid = eBody.addElement("mainprodid");
            eReq_mainprodid.addText((String)inMap.get("mainprodid"));
            
            // ģ�����
            Element eReq_templeteid = eBody.addElement("templeteid");
            eReq_templeteid.addText((String)inMap.get("templeteid"));
            
            // ����ʱ��
            Element eReq_recdate = eBody.addElement("recdate");
            eReq_recdate.addText((String)inMap.get("recdate"));
            
            // ��Ч����(1)
            Element eReq_affecttype = eBody.addElement("affecttype");
            eReq_affecttype.addText((String)inMap.get("affecttype"));
            
            // ��ͨ�Ĳ�Ʒ���봮
            Element eReq_productset = eBody.addElement("productset");
            eReq_productset.addText((String)inMap.get("productset"));
            
            // ?
            Element eReq_prodsprelation = eBody.addElement("prodsprelation");
            eReq_prodsprelation.addText(notNeedNull((String)inMap.get("prodsprelation")));
            
            // ɾ���Ĳ�Ʒ���봮
            Element eReq_delprodset = eBody.addElement("delprodset");
            eReq_delprodset.addText(notNeedNull((String)inMap.get("delprodset")));
            
            // sp���봮
            Element eReq_subsprivattrlist = eBody.addElement("subsprivattrlist");
            eReq_subsprivattrlist.addText(notNeedNull((String)inMap.get("subsprivattrlist")));
            
            // ������봮
            Element eReq_subsservattrlist = eBody.addElement("subsservattrlist");
            eReq_subsservattrlist.addText(notNeedNull((String)inMap.get("subsservattrlist")));
            
            Element eReq_delsprelation = eBody.addElement("delsprelation");
            eReq_delsprelation.addText(notNeedNull((String)inMap.get("delsprelation")));
            
            //log.info("����ҵ��ID[cli_package_chgcommit_hub]��ʼ.");
            docXML = intMsgUtil.createMsg(doc, "cli_package_chgcommit_cq", menuid, "", "1", telnumber, operid, atsvNum);
            //log.info("����ҵ��ID[cli_package_chgcommit_hub]����.");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            //log.error("��Ʒת������ʧ��", e);
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("��Ʒת������ʧ��.");
            return rw;
        }
    }
    
    private String notNeedNull(String inStr)
    {
        return inStr == null ? "" : inStr;
    }
    
    public IntMsgUtil getIntMsgUtil()
    {
        return intMsgUtil;
    }

    public void setIntMsgUtil(IntMsgUtil intMsgUtil)
    {
        this.intMsgUtil = intMsgUtil;
    }
    
  //add start l00190940 2011-11-03 ���ֶһ��еĻ��ֲ�ѯ
    /**
     * ���ֲ�ѯ
     * 
     * @param map
     * @return
     */
	public ReturnWrap queryScoreValue(Map map)
	{
		try
        {
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            docXML = intMsgUtil.createMsg(doc, "cli_qry_scorevalue", menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("���ֲ�ѯʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("���ֲ�ѯ�ӿڳ����쳣");
            
            return rw;
        }
	}
	//add end l00190940 2011-11-03 ���ֶһ��еĻ��ֲ�ѯ

	//add start l00190940 2011-11-04 ���ֶһ��еĻ��ֶһ���Ϣ��ѯ
	/**
     * ���ֶһ���Ϣ��ѯ
     * 
     * @param map
     * @return
     */
	public ReturnWrap queryScoreExchangeInfo(Map map) {
		try
        {
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String acttype = (String)map.get("acttype");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // �����
            Element eReq_acttype = eBody.addElement("acttype");
            eReq_acttype.addText(acttype);
            
            docXML = intMsgUtil.createMsg(doc, "cli_qry_scoreexchange_cq", menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("���ֶһ���Ϣ��ѯʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            rw.setReturnMsg("���ֶһ���Ϣ��ѯ�ӿڳ����쳣");
            
            return rw;
        }
	}
	//add end l00190940 2011-11-04 ���ֶһ��еĻ��ֶһ���Ϣ��ѯ

	//add start l00190940 2011-11-04 ���ֶһ��еĻ��ֶһ�����
	/**
     * ���ֶһ�����
     * 
     * @param map
     * @return
     */
	public ReturnWrap exchangeBalance(Map map) 
	{
		try
        {          
            String telnumber = (String)map.get("telnumber");
            String activeno = (String)map.get("activeno");
            String nlevel = (String)map.get("nlevel");
            String serviceid = (String)map.get("serviceid");
            String menuID = (String)map.get("curMenuId");
            String touchOID = (String)map.get("touchoid");
            String operID = (String)map.get("curOper");
            String termID = (String)map.get("atsvNum");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // ����
            Element eReq_activeno = eBody.addElement("activeno");
            eReq_activeno.addText(activeno);
            
            // �����
            Element eReq_nlevel = eBody.addElement("nlevel");
            eReq_nlevel.addText(nlevel);
            
            // ��Ʒ����
            Element eReq_serviceid = eBody.addElement("serviceid");
            eReq_serviceid.addText(serviceid);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_scoreexchange_cq", menuID, touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("���ֶһ�����ʧ�ܣ�", e);
            
            ReturnWrap rw = new ReturnWrap();
            rw.setStatus(0);
            return rw;
        }
	}
	//add end l00190940 2011-11-04 ���ֶһ��еĻ��ֶһ�����
}
