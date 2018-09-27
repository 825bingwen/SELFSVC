package com.customize.nx.selfsvc.call.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.nx.selfsvc.call.SelfSvcCallNX;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.util.CommonUtil;

public class SelfSvcCallNXImpl implements SelfSvcCallNX
{
    private static Log logger = LogFactory.getLog(SelfSvcCallNXImpl.class);
    
    private IntMsgUtil intMsgUtil;
    
    /**
     * �˻�����ѯ
     */
    public ReturnWrap queryBalance(Map map)
    {
        try
        {
            String operid = (String)map.get("operid");// ����Աid
            String atsvNum = (String)map.get("atsvNum");// �ն˻�id
            String telnumber = (String)map.get("telnumber");// �ͻ��ֻ���
            String touchoid = (String)map.get("touchoid");// �ͻ��Ӵ�id
            String menuid = (String)map.get("menuid");// ��ǰ�˵�ID
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_balance_nx", menuid,
                touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("����ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ���˵���ѯ
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryMonthBill(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String month = (String)map.get("month");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            Element eReq_month = eBody.addElement("month");
            eReq_month.addText(month);
            
            Element eReq_contactid = eBody.addElement("contactid");
            eReq_contactid.addText(touchOID);
                        
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_detailedbill_nx", menuID, touchOID, "1", telnum, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("���˵���ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ���û���������������
     * 
     * @param map
     * @return
     */
    public ReturnWrap sendSMS(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String smsContent = (String)map.get("smsContent");
        String priority = (String)map.get("priority");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ��������
            Element eReq_smscont = eBody.addElement("smscont");
            eReq_smscont.addText(smsContent);
            
            // ���ȼ�
            Element eReq_priority = eBody.addElement("priority");
            eReq_priority.addText(priority);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_sendsms", menuID, touchOID, "1", telnum, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("���ŷ���ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �ɷѲ�ѯ�ӿ�
     * 
     * @param map
     * @return
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
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_fee_nx", menuid, touchoid, "1", servnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�ɷѲ�ѯ�ӿ�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �ɷѽӿ�
     * 
     * @param map
     * @return
     */
    public ReturnWrap chargeCommit(Map map)
    {
        try
        {
            String operid = (String)map.get("operid");
            String termid = (String)map.get("termid");
            String menuid = (String)map.get("menuid");
            String servnumber = (String)map.get("servnumber");
            String money = (String)map.get("money");
            String touchoid = (String)map.get("touchoid");
            String terminalSeq = (String) map.get("terminalSeq");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);

            Element eReq_ncode = eBody.addElement("ncode");
            eReq_ncode.addText("1");
            
            Element eReq_contactid = eBody.addElement("formnum");
            eReq_contactid.addText(terminalSeq);
            
            // ���
            Element eReq_amount = eBody.addElement("amount");
            eReq_amount.addText(money);
            
            Element eReq_invoicetype = eBody.addElement("invoicetype");
            eReq_invoicetype.addText("0");
            
            Element eReq_chargetype = eBody.addElement("chargetype");
            eReq_chargetype.addText("0");
            
            Element eReq_bankaccount = eBody.addElement("bankaccount");
            eReq_bankaccount.addText("");
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_chargefee_nx", menuid,
                touchoid, "1", servnumber, operid, termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�ɷ�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯ��Ʊ
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/02/04 R003C13L01n01 OR_huawei_201302_122 
     */
    public ReturnWrap queryInvoice(Map<String, String> map)
    {
        try
        {
            String operid = map.get("operid");
            String termid = map.get("termid");
            String menuid = map.get("menuid");
            String servnumber = map.get("servnumber");
            String money = map.get("money");
            String touchoid = map.get("touchoid");
            String channel = map.get("channel");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            // ����
            Element eReq_invoicechannel = eBody.addElement("invoicechannel");
            eReq_invoicechannel.addText(channel);
            
            // �ɷѽ��֣�
            Element eReq_amount = eBody.addElement("amount");
            eReq_amount.addText(money);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_invoice_nx", menuid,
                touchoid, "1", servnumber, operid, termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ѯ��Ʊʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }

    public IntMsgUtil getIntMsgUtil()
    {
        return intMsgUtil;
    }

    public void setIntMsgUtil(IntMsgUtil intMsgUtil)
    {
        this.intMsgUtil = intMsgUtil;
    }

    /**
     * �����ײ���Ϣ��ѯ
     * @param map
     * @return
     */
    public ReturnWrap qryPackageInfo(Map map)
    {
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("telnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        String billcycle = (String)map.get("billcycle");
        
        // ��װ�������
        // �ֻ�����
        Element eReq_telnum = eBody.addElement("telnum");
        eReq_telnum.addText(telnumber);
        
        // ��ǰ����
        Element eReq_billcycle = eBody.addElement("billcycle");
        eReq_billcycle.addText(billcycle);
        
        Document docXML = intMsgUtil.createMsg(doc, "cli_qry_taocan_nx", menuid,
            touchoid, "1", telnumber, operid, atsvNum);
        return intMsgUtil.invoke(docXML);
    }
    
    /**
     * �����ֻ������ѯ�ͻ���Ϣ
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap getCustinfo(Map map)
    {
        try
        {
            String telnum = (String)map.get("telnum");
            String cycle = (String)map.get("cycle");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ����
            Element eReq_cycle = eBody.addElement("cycle");
            eReq_cycle.addText(cycle);
            
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_custinfo", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ѯ�ͻ���Ϣʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }       
    }
    
    /**
     * ���˵���ѯ�°�
     */
    public ReturnWrap qryMonthBill_new(Map map)
    {
        try
        {
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            String telnum = (String)map.get("telnum");// �ֻ�����
            String acctid = (String)map.get("acctid");// �ʻ�ID��ͬ�ͻ���Ϣ��ѯ�ӿڷ��ص����˺�
            String subsid = (String)map.get("subsid");// �û�ID
            String startcycle = (String)map.get("startcycle");// ��ʼ����
            String starttime = (String)map.get("starttime")+"000000";// ���ڿ�ʼʱ��
            String endtime = (String)map.get("endtime")+"235959";// ���ڽ���ʱ��
            String isunitpayment = (String)map.get("isunitpayment");// �Ƿ�ϲ�����
            String region = (String)map.get("region");// ����
            String arealist = (String)map.get("arealist");// �����б�
            String factory = (String)map.get("factory");// ���ұ�������ı�ʶ������Ϊ��
            
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �û��ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // �ʻ�ID��ͬ�ͻ���Ϣ��ѯ�ӿڷ��ص����˺�
            Element eReq_acctid = eBody.addElement("acctid");
            eReq_acctid.addText(acctid);
            
            // �û�ID
            Element eReq_subsid = eBody.addElement("subsid");
            eReq_subsid.addText(subsid);
            
            // ��ʼ����
            Element eReq_startcycle = eBody.addElement("startcycle");
            eReq_startcycle.addText(startcycle);
            
            // ���ڿ�ʼʱ��
            Element eReq_starttime = eBody.addElement("starttime");
            eReq_starttime.addText(starttime);
            
            // ���ڽ���ʱ��
            Element eReq_endtime = eBody.addElement("endtime");
            eReq_endtime.addText(endtime);
            
            // �Ƿ�ϲ�����
            Element eReq_isunitpayment = eBody.addElement("isunitpayment");
            eReq_isunitpayment.addText(isunitpayment);
            
            // ����
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            
            // �����б�
            Element eReq_arealist = eBody.addElement("arealist");
            eReq_arealist.addText(arealist);
            
            // ���ұ�������ı�ʶ������Ϊ��
            Element eReq_factory = eBody.addElement("factory");
            eReq_factory.addText(factory);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_bill2012_nx", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke_MonthFee_NX(docXML);
        }
        catch (Exception e)
        {
            logger.error("�°����˵���ѯʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯ���л���֧����ʽ
     * 
     * @param paramMap
     * @return
     * @see 
     */
    public ReturnWrap qryChargeType(Map<String, String> paramMap)
    {
        try
        {
            String menuID = (String)paramMap.get("menuID");
            String touchOID = (String)paramMap.get("touchOID");
            String operID = (String)paramMap.get("operID");
            String termID = (String)paramMap.get("termID");
            
            String telnum = (String)paramMap.get("telnum");
            String payType = (String) paramMap.get("paytype");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ֧����ʽ
            Element eReq_paytype = eBody.addElement("paytype");
            eReq_paytype.addText(payType);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_chargetype_nx", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�û�����֧����ʽ��ѯʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ȡ�����л���֧����ʽ
     * 
     * @param paramMap
     * @return
     * @see 
     */
    public ReturnWrap cancelChargeType(Map<String, String> paramMap)
    {
        try
        {
            String menuID = (String)paramMap.get("menuID");
            String touchOID = (String)paramMap.get("touchOID");
            String operID = (String)paramMap.get("operID");
            String termID = (String)paramMap.get("termID");
            
            String telnum = (String)paramMap.get("telnum");
            String payType = (String) paramMap.get("paytype");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ֧����ʽ
            Element eReq_paytype = eBody.addElement("paytype");
            eReq_paytype.addText(payType);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_delchargetype_nx", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("ɾ�����л���֧����ʽʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �������л���֧����ʽ
     * 
     * @param paramMap
     * @return
     * @see 
     */
    public ReturnWrap addChargeType(Map<String, String> paramMap)
    {
        try
        {
            String menuID = (String)paramMap.get("menuID");
            String touchOID = (String)paramMap.get("touchOID");
            String operID = (String)paramMap.get("operID");
            String termID = (String)paramMap.get("termID");
            
            String telnum = (String)paramMap.get("telnum");
            String payType = (String) paramMap.get("paytype");
            String bankid = (String) paramMap.get("bankid");
            String bankacct = (String) paramMap.get("bankacct");
            String drawtype = (String) paramMap.get("drawtype");
            String drawamt = (String) paramMap.get("drawamt");
            String trigamt = (String) paramMap.get("trigamt");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ֧����ʽ
            Element eReq_paytype = eBody.addElement("paytype");
            eReq_paytype.addText(payType);
            
            // ���б���
            Element eReq_bankid = eBody.addElement("bankid");
            eReq_bankid.addText(bankid);
            
            // �����˺�
            Element eReq_bankacct = eBody.addElement("bankacct");
            eReq_bankacct.addText(bankacct);
            
            // ��������
            Element eReq_drawtype = eBody.addElement("drawtype");
            eReq_drawtype.addText(drawtype);
            
            // ���۽��
            Element eReq_drawamt = eBody.addElement("drawamt");
            eReq_drawamt.addText(drawamt);
            
            // �������
            Element eReq_trigamt = eBody.addElement("trigamt");
            eReq_trigamt.addText(trigamt);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_addchargetype_nx", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�������л���֧����ʽʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯ��Ʒ���ȷ����Ϣ�������û��迪ͨ��ҵ�񡢻ᱣ����ҵ����ȡ����ҵ��
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281
     */
    public ReturnWrap mainProductRecInfo(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            
            String menuID = (String)map.get("menuID");
            
            String touchOID = (String)map.get("touchoid");
            
            String operID = (String)map.get("operID");
            
            String termID = (String)map.get("termID");
            
            // ncode
            String ncode = (String)map.get("ncode");
            
            // ��������
            String oprtype = (String)map.get("oprtype");
            
            // ����
            String accesstype = (String)map.get("accesstype");
            
            // ��Ч��ʽ
            String affecttype = (String)map.get("affecttype");
            
            // ����ʱ��
            String intime = (String)map.get("intime");
            
            String preparebusi = (String)map.get("preparebusi");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Element eReq_ncode = eBody.addElement("ncode");
            eReq_ncode.addText(ncode);
            
            Element eReq_oprtype = eBody.addElement("oprtype");
            eReq_oprtype.addText(oprtype);
            
            Element eReq_accesstype = eBody.addElement("accesstype");
            eReq_accesstype.addText(accesstype);
            
            Element eReq_affecttype = eBody.addElement("affecttype");
            eReq_affecttype.addText(affecttype);
            
            Element eReq_intime = eBody.addElement("intime");
            eReq_intime.addText(intime);
            
            Element eReq_preparebusi = eBody.addElement("preparebusi");
            eReq_preparebusi.addText(preparebusi);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_MainIntProductRec", menuID,
                touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ѯ��Ʒ���ȷ����Ϣʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ���ýӿ�ִ�������Ʒת��
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281
     */
    public ReturnWrap mainProductChangeSubmit(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            
            String menuID = (String)map.get("menuID");
            
            String touchOID = (String)map.get("touchoid");
            
            String operID = (String)map.get("operID");
            
            String termID = (String)map.get("termID");
            
            // ncode
            String ncode = (String)map.get("ncode");
            
            // ��������
            String stype = (String)map.get("stype");
            
            String preparebusi = (String)map.get("preparebusi");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Element eReq_ncode = eBody.addElement("ncode");
            eReq_ncode.addText(ncode);
            
            Element eReq_stype = eBody.addElement("stype");
            eReq_stype.addText(stype);
            
            Element eReq_preparebusi = eBody.addElement("preparebusi");
            eReq_preparebusi.addText(preparebusi);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_ChangeProductSubmit", menuID,
                touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��Ʒת������", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     *  ��ѯ�ɱ�������Ʒ�б�
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281 
     */
    public ReturnWrap qryChangeMainProduct(Map<String,String> map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            
            String menuID = (String)map.get("menuID");
            
            String touchOID = (String)map.get("touchoid");
            
            String operID = (String)map.get("operID");
            
            String termID = (String)map.get("termID");
            
            // ����ʽ
            String rectype = (String)map.get("rectype");

            //����
            String channel = (String)map.get("channel");

            // �������
            String solutionid = (String)map.get("solutionid");

            // ��Ʒ����
            String prodtype = (String)map.get("prodtype");

            // Ŀ¼����
            String catalogid = (String)map.get("catalogid");

            // �û��������ƷID
            String prodid = (String)map.get("prodid");

            // ��������
            String region = (String)map.get("region");

            // ��λ����
            String org = (String)map.get("org");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // ����ʽ
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText(rectype);

            //����
            Element eReq_channel = eBody.addElement("channel");
            eReq_channel.addText(channel);
            
            // �������
            Element eReq_solutionid = eBody.addElement("solutionid");
            eReq_solutionid.addText(solutionid);
            
            // ��Ʒ����
            Element eReq_prodtype = eBody.addElement("prodtype");
            eReq_prodtype.addText(prodtype);
            
            // Ŀ¼����
            Element eReq_catalogid = eBody.addElement("catalogid");
            eReq_catalogid.addText(catalogid);
            
            // �û��������ƷID
            Element eReq_prodid = eBody.addElement("prodid");
            eReq_prodid.addText(prodid);
            
            // ��������
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            
            // ��λ����
            Element eReq_org = eBody.addElement("org");
            eReq_org.addText(org);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_changeMainProduct", menuID,
                touchOID, "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ѯ�ɱ�������Ʒʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �������Ͳ�ѯ����ֵ
     * 
     * @param map
     * @return returnWrap
     * @remark create zWX176560 2013/08/22 R003C13L09n01 OR_NX_201308_595
     */
    public ReturnWrap qryUserScoreInfoByType(Map paramMap)
    {
        try
        {
            // ��ǰ�˵�ID
            String menuID = (String)paramMap.get("curMenuId");
            
            // �ͻ��Ӵ�id
            String touchOID = (String)paramMap.get("touchOID");
            
            // ����ԱID
            String operID = (String)paramMap.get("operID");
            
            // �ն˻�ID
            String termID = (String)paramMap.get("termID");
            
            // �û��ֻ�����
            String telnum = (String)paramMap.get("telnum");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ��ѯ��ʽ Ĭ�ϴ���0�� ���˻���
            Element eReq_paytype = eBody.addElement("qrytype");
            eReq_paytype.addText("0");
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_userscoreInfo", menuID,
                touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�������Ͳ�ѯ����ʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ����ʱ֤������У��
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark OR_NX_201303_280  ���������ն��Ż�����֮���߿���
     */
    public ReturnWrap chkCertSubs(Map map)
    {
        // �˵�
        String menuID = (String)map.get("menuID");
        
        // ����ԱID
        String operID = (String)map.get("operID");
        
        // �ն�ID
        String termID = (String)map.get("termID");
        
        // ����
        String region = (String)map.get("region");
        
        // ͳһ�Ӵ���ˮ
        String touchOID = (String)map.get("touchoid");
        
        // ֤������
        String certtype = (String)map.get("certtype");
        
        // ֤������
        String certid = (String)map.get("certid");
        
        // Ĭ�ϴ�1
        String choicetelnum = (String)map.get("choicetelnum");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ֤������
            Element eReq_certtype = eBody.addElement("certtype");
            eReq_certtype.addText(certtype);
            
            // ֤������
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(certid);
            
            // ��ǰ������������Ĭ�ϴ�1
            Element eReq_choicetelnum = eBody.addElement("choicetelnum");
            eReq_choicetelnum.addText(choicetelnum);
                        
            Document docXML = intMsgUtil.createMsg(doc, "cli_chkcertsubs_nx", menuID, touchOID, "0", region, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("֤������У��ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯ�ֻ������б�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark OR_NX_201303_280  ���������ն��Ż�����֮���߿���
     */
    public ReturnWrap qryNumberByProdid(Map map)
    {
        // �˵�
        String menuID = (String)map.get("menuID");
        
        // ����ԱID
        String operID = (String)map.get("operID");
        
        // �ն�ID
        String termID = (String)map.get("termID");
        
        // ����
        String region = (String)map.get("region");
        
        // ͳһ�Ӵ���ˮ
        String touchOID = (String)map.get("touchOID");

        // ����
        String orgid = (String)map.get("orgid");
        
        // ��ѯ���� 2����ǰ׺��ѯ 3������׺��ѯ
        String sele_rule = (String)map.get("sele_rule");
        
        // ����ǰ׺ sele_rule = 2ʱ�����û�����ƣ�Ϊ_______�������ƣ�������7λ�����油_ sele_rule = 3ʱ��Ϊ����
        String tel_prefix = (String)map.get("tel_prefix");
        
        // �����׺ sele_rule = 2ʱ��Ϊ���� sele_rule = 3ʱ������4λ�����油_
        String tel_suffix = (String)map.get("tel_suffix");
                
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ����
            Element eReq_orgid = eBody.addElement("county_id");
            eReq_orgid.addText(orgid);
              
            // ��ѯ����
            Element eReq_sele_rule = eBody.addElement("sele_rule");
            eReq_sele_rule.addText(sele_rule);
             
            // �����׺
            Element eReq_tel_suffix = eBody.addElement("tel_suffix");
            eReq_tel_suffix.addText(tel_suffix);
            
            // ����ǰ׺
            Element eReq_tel_prefix = eBody.addElement("tel_prefix");
            eReq_tel_prefix.addText(tel_prefix);
                        
          
            Document docXML = intMsgUtil.createMsg(doc, "cli_qrynumberlist", menuID, touchOID, "0", region, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ѯ�ֻ������б�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ������Դ��ѡ�ӿ�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap telNumTmpPick(Map map)
    {
        // �˵�
        String menuID = (String)map.get("menuID");
        
        // ����ԱID
        String operID = (String)map.get("operID");
        
        // �ն�ID
        String termID = (String)map.get("termID");
        
        // ����
        String region = (String)map.get("region");
        
        // ͳһ�Ӵ���ˮ
        String touchOID = (String)map.get("touchoid");
        
        // �ֻ����� 
        String msisdn = (String)map.get("telnum");
        
        // ��Դ���� rsclTgsm
        String restype = (String)map.get("restype");
        
        // ������־ 0Ϊ������1Ϊ����
        String operflag = (String)map.get("operflag");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_msisdn = eBody.addElement("msisdn");
            eReq_msisdn.addText(msisdn);
            
            // ��Դ����
            Element eReq_restype = eBody.addElement("restype");
            eReq_restype.addText(restype);
            
            // ������־
            Element eReq_operflag = eBody.addElement("operflag");
            eReq_operflag.addText(operflag);
                        
            Document docXML = intMsgUtil.createMsg(doc, "cli_telnumtmppick_nx", menuID, touchOID, "0", region, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("������Դ��ѡʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * У��հ׿���Դ�Ƿ����
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chkBlankNo(Map map)
    {
        // �˵�
        String menuID = (String)map.get("menuID");
        
        // ����ԱID
        String operID = (String)map.get("operID");
        
        // �ն�ID
        String termID = (String)map.get("termID");
        
        // ����
        String region = (String)map.get("region");
        
        // ͳһ�Ӵ���ˮ
        String touchOID = (String)map.get("touchoid");
        
        // �հ׿����к�
        String blankno = (String)map.get("blankno");
        
        // ��֯��λ
        String orgid = (String)map.get("orgid");
        
        // ��Դ����
        String res_kind_id = (String)map.get("res_kind_id");
        
        // ��Դ���� ����""
        String res_type_id = (String)map.get("res_type_id");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ����
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            
            // ��λ
            Element eReq_org_id = eBody.addElement("org_id");
            eReq_org_id.addText(orgid);
            
            // ����Ա
            Element eReq_operid = eBody.addElement("oper_id");
            eReq_operid.addText(operID);
            
            // ��Դ����
            Element eReq_reskindid = eBody.addElement("res_kind_id");
            eReq_reskindid.addText(res_kind_id);
            
            // ��Դ����
            Element eReq_restypeid = eBody.addElement("res_type_id");
            eReq_restypeid.addText(res_type_id);
            
            // ��Դ����
            Element eReq_startsn = eBody.addElement("startsn");
            eReq_startsn.addText(blankno);
            
            // ��Դ����
            Element eReq_endsn = eBody.addElement("endsn");
            eReq_endsn.addText(blankno);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_chkblankno_nx", menuID, touchOID, "0", region, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("У��հ׿���Դ�Ƿ����ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �հ׿���Դ��ѡ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap blankCardTmpPick(Map map)
    {
        // �˵�
        String menuID = (String)map.get("menuID");
        
        // ����ԱID
        String operID = (String)map.get("operID");
        
        // �ն�ID
        String termID = (String)map.get("termID");
        
        // ����
        String region = (String)map.get("region");
        
        // ͳһ�Ӵ���ˮ
        String touchOID = (String)map.get("touchOID");
        
        // �հ׿����к�
        String blankno = (String)map.get("blankno");
        
        // �������
        String telnum = (String)map.get("telnum");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �������
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // �հ׿����к�
            Element eReq_blankno = eBody.addElement("blankcardnum");
            eReq_blankno.addText(blankno);            
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_blankcardtmppick_nx", menuID, touchOID, "0", region, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�հ׿���Դ��ѡʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �ſ�У��
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chkTelSimcard(Map map)
    {
    	// �˵�
        String menuID = (String)map.get("menuID");
        
        // ����ԱID
        String operID = (String)map.get("operID");
        
        // �ն�ID
        String termID = (String)map.get("termID");
        
        // ����
        String region = (String)map.get("region");
        
        // ͳһ�Ӵ���ˮ
        String touchOID = (String)map.get("touchOID");
        
        // �ֻ�����
        String telnum = (String)map.get("telnum");
        
        // SIM��ICCID
        String iccid = (String)map.get("iccid");
        
        // ��Ʒ����
        String mainprodid = (String)map.get("porductid");
        
        // ҵ�����ͣ���Install��
        String rectype = (String)map.get("rectype");
        
        // �Ƿ��Ѿ�����  ��1
        String islocked = (String)map.get("islocked");
        
        // �Ƿ��ǿհ׿� ��0
        String isblankcard = (String)map.get("isblankcard");
        
        // ������ID orgid
        String agentid = (String)map.get("agentid");

        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");

            // �ֻ�����
            Element eReq_telnum = eBody.addElement("servnum");
            eReq_telnum.addText(telnum);
            
            // SIM��ICCID
            Element eReq_iccid = eBody.addElement("iccid");
            eReq_iccid.addText(iccid);
            
            // ��Ʒ����
            Element eReq_porductid = eBody.addElement("mainprodid");
            eReq_porductid.addText(mainprodid);
            
            // ҵ�����ͣ���Install��
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText(rectype);
            
            // �Ƿ��Ѿ�����  ��1
            Element eReq_islocked = eBody.addElement("islocked");
            eReq_islocked.addText(islocked);
            
            // �Ƿ��ǿհ׿� ��0
            Element eReq_isblankcard = eBody.addElement("isblankcard");
            eReq_isblankcard.addText(isblankcard);

            //  ������ID orgid
            Element eReq_agentid = eBody.addElement("agentid");
            eReq_agentid.addText(agentid);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_chktelsimcard_nx", menuID, touchOID, "0", region, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�ſ�У��ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �������
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap reckonRecFee(Map map)
    {
        // �˵�
        String menuID = (String)map.get("menuID");
        
        // ����ԱID
        String operID = (String)map.get("operID");
        
        // �ն�ID
        String termID = (String)map.get("termID");
        
        // ����
        String region = (String)map.get("region");
        
        // ͳһ�Ӵ���ˮ
        String touchOID = (String)map.get("touchOID");
        
        // �ֻ�����
        String telnum = (String)map.get("telnum");
        
        // �����Ʒ����
        String mainprodid = (String)map.get("mainprodid");
        
        // ��Ʒģ�����
        String prodtempletid = (String)map.get("prodtempletid");
        
        // iccid SIM����
        String simnum = (String)map.get("simnum");
        
        // �հ׿�����
        String blankcardno= (String)map.get("blankcardno");
        
        // �Ƿ񷵻� ������� ��1
        String retdiscountfee= (String)map.get("retdiscountfee");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("installtelnum");
            eReq_telnum.addText(telnum);
            
            // �����Ʒ����
            Element eReq_mainprodid = eBody.addElement("mainprodid");
            eReq_mainprodid.addText(mainprodid);
            
            // ��Ʒģ�����
            Element eReq_prodtempletid = eBody.addElement("prodtempletid");
            eReq_prodtempletid.addText(prodtempletid);
            
            // iccid SIM����
            Element eReq_simnum = eBody.addElement("simnum");
            eReq_simnum.addText(simnum);
            
            // �հ׿�����
            Element eReq_blankcardno = eBody.addElement("blankcardno");
            eReq_blankcardno.addText(blankcardno);
            
            // �հ׿�����
            Element eReq_retdiscountfee = eBody.addElement("retdiscountfee");
            eReq_retdiscountfee.addText(retdiscountfee);

            Document docXML = intMsgUtil.createMsg(doc, "cli_reckonrecfee_nx", menuID, touchOID, "0", region, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ����
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap terminalInstall(Map map)
    {
        // �˵�
        String menuID = (String)map.get("menuID");
        
        // ����ԱID
        String operID = (String)map.get("operID");
        
        // �ն�ID
        String termID = (String)map.get("termID");
        
        // ����
        String region = (String)map.get("region");
        
        // ͳһ�Ӵ���ˮ
        String touchOID = (String)map.get("touchOID");
        
        // ����bsacAtsv
        String accesstype = (String)map.get("accesstype");
        
        // �ֻ�����
        String telnum = (String)map.get("installtelnum");
        
        // ���� SIM
        String simnum = (String)map.get("simnum");

        // imsi
        String imsi= (String)map.get("imsi");
        
        // �����Ʒ����
        String mainprodid = (String)map.get("mainprodid");
        
        // ��Ʒģ�����
        String prodtempletid = (String)map.get("prodtempletid");
        
        // ѡ�ŷ�(��ѡ)������0����
        String telprice= (String)map.get("telprice");
        
        // ҵ�����ͣ���Install��
        String rectype= (String)map.get("rectype");
        
        // ֤������
        String certtype= (String)map.get("certtype");
        
        // ֤����
        String certid= (String)map.get("certid");
 
        // �ͻ�����
        String custname= (String)map.get("custname");
        
        // �û����ɷ���
        String totalfee= (String)map.get("totalfee");
        
        // ����
        String password= (String)map.get("password");
        
        // �ͻ���ϵ�绰
        String linkphone= (String)map.get("linkphone");
        
        // ��ϵ��ַ
        String linkaddr= (String)map.get("linkaddr");
        
        // Ĭ�ϴ�0
        String existdetail= (String)map.get("existdetail");
        
        // �ͻ���ַ
        String custaddr= (String)map.get("custaddr");
        
        // ��ϵ������
        String linkname= (String)map.get("linkname");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ����
            Element eReq_accesstype = eBody.addElement("accesstype");
            eReq_accesstype.addText(accesstype);
            
            // ��������
            Element eReq_telnum = eBody.addElement("installtelnum");
            eReq_telnum.addText(telnum);

            // ����
            Element eReq_simnum = eBody.addElement("simnum");
            eReq_simnum.addText(simnum);
            
            // imsi
            Element eReq_imsi = eBody.addElement("imsi");
            eReq_imsi.addText(imsi);
            
            // �����Ʒ����
            Element eReq_mainprodid = eBody.addElement("mainprodid");
            eReq_mainprodid.addText(mainprodid);
            
            // ��Ʒģ�����
            Element eReq_prodtempletid = eBody.addElement("prodtempletid");
            eReq_prodtempletid.addText(prodtempletid);

            // ѡ�ŷ�(��ѡ)������ֱ�����
            Element eReq_telprice = eBody.addElement("telprice");
            eReq_telprice.addText(telprice);
            
            // ҵ������
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText(rectype);
            
            // ֤������
            Element eReq_certtype = eBody.addElement("certtype");
            eReq_certtype.addText(certtype);

            // ֤����
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(certid);

            // �ͻ�����
            Element eReq_custname = eBody.addElement("custname");
            eReq_custname.addText(custname);
            
            // �û������ܷ���
            Element eReq_totalfee = eBody.addElement("totalfee");
            eReq_totalfee.addText(totalfee);
            
            // ����
            Element eReq_password = eBody.addElement("password");
            eReq_password.addText(password);
            
            // �ͻ���ϵ�绰
            Element eReq_linkphone = eBody.addElement("linkphone");
            eReq_linkphone.addText(linkphone);
            
            // ��ַ
            Element eReq_linkaddr = eBody.addElement("linkaddr");
            eReq_linkaddr.addText(linkaddr);
            
            // existdetail
            Element eReq_existdetail = eBody.addElement("existdetail");
            eReq_existdetail.addText(existdetail);
            
            // �ͻ���ַ
            Element eReq_custaddr = eBody.addElement("custaddr");
            eReq_custaddr.addText(custaddr);
            
            // ��ϵ������
            Element eReq_linkname = eBody.addElement("linkname");
            eReq_linkname.addText(linkname);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_terminalinstall_nx", menuID, touchOID, "0", region, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("����ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    //add begin jWX216858 2014/6/17 OR_NX_201406_553_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ
    /**
     * ��ѯ���ڣ����ģ�
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_NX_201406_553_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ
     */
     @Override
 	public ReturnWrap qryBillCycle(Map<String, String> paramMap) 
     {
     	String telnum = paramMap.get("servnum");
    		String operId = paramMap.get("operId");
    		String menuId = paramMap.get("menuId");
    		String touchId = paramMap.get("touchId");
    		String termId = paramMap.get("termId");
    		String cycle = paramMap.get("cycle");
    		try
    		{
    			Document doc = DocumentHelper.createDocument();
    			Element eBody = doc.addElement("message_content");
    			
    			// �ֻ���
    			eBody.addElement("servnum").addText(telnum);
    			
    			// ����
    			eBody.addElement("cycle").addText(cycle);
    			
    			Document docXML = intMsgUtil.createMsg(doc, "cli_qry_billCycleCustInfo", menuId, touchId, "1", telnum, operId, termId);
    			
    			return intMsgUtil.invoke(docXML);
    		}
    		catch (Exception e)
    		{
    			logger.error("��ѯ����ʧ��!", e);
         	
    			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
    		}
 	}
        
    /**
     * ��ȡ�½ᷢƱ��Ϣ�����ģ�
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_NX_201406_553_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ
     */
	@Override
	public ReturnWrap getMonInvoiceData(Map<String, String> paramMap)
	{
		String telnum = paramMap.get("telnum");
		String operId = paramMap.get("operId");
		String menuId = paramMap.get("menuId");
		String touchId = paramMap.get("touchId");
		String termId = paramMap.get("termId");
		String cycle = paramMap.get("cycle");
		String startdate = paramMap.get("startdate");
		String enddate = paramMap.get("enddate");
		String acctId = paramMap.get("acctid");
		try
		{
			Document doc = DocumentHelper.createDocument();
			Element eBody = doc.addElement("message_content");
			
			//�ֻ�����
			eBody.addElement("servnum").addText(telnum);
			
			//����
			eBody.addElement("billCycle").addText(cycle);
			
			// ��ʼʱ��
			eBody.addElement("startDate").addText(startdate);
			
			// ����ʱ��
			eBody.addElement("endDate").addText(enddate);
			
			// ���˺�
			eBody.addElement("acctId").addText(acctId);
			Document docXML = intMsgUtil.createMsg(doc, "cli_qry_monthinvoiceinfo", menuId, touchId, "1", telnum, operId, termId);
			
			return intMsgUtil.invoke(docXML);
		}
		catch (Exception e)
		{
			logger.error("�½ᷢƱ���ݲ�ѯʧ��!", e);
     	
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	//add end jWX216858 2014/6/17 OR_NX_201406_553_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ

}
