package com.customize.sd.selfsvc.call.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.sd.selfsvc.call.SelfSvcCallSD;
import com.customize.sd.selfsvc.cardbusi.model.IdCardPO;
import com.customize.sd.selfsvc.cardbusi.model.ProdTempletPO;
import com.customize.sd.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.sd.selfsvc.packageService.model.PrivServPackPO;
import com.customize.sd.selfsvc.prestoredReward.model.PrestoredRewardPO;
import com.customize.sd.selfsvc.realNameReg.model.ChargeRecordPO;
import com.customize.sd.selfsvc.serviceinfo.model.BankCardInfoPO;
import com.customize.sd.selfsvc.serviceinfo.model.BindBankCardPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DateUtil;
import com.gmcc.boss.selfsvc.util.DocumentUtil;

public class SelfSvcCallSDImpl implements SelfSvcCallSD
{
    private static Log logger = LogFactory.getLog(SelfSvcCallSDImpl.class);
    
    private IntMsgUtil intMsgUtil;
    
    /**
     * ɽ���ײ���Ϣ��ѯ
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
            String menuid = (String)map.get("curMenuId");
            
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_TAOCAN, menuid, touchoid, "1", telnumber, operid, atsvNum);
            
            ReturnWrap rw = intMsgUtil.invoke(docXML);
            
            // �ܿ����ص���crset����תΪVector�Ա�ͳһ����
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_TAOCAN) && rw.getReturnObject() instanceof CRSet)
            {
                CRSet crset = (CRSet)rw.getReturnObject();
                Vector vec = new Vector();
                
                // ��һ���ŵ㶫��ռλ
                vec.add(rw.getReturnMsg());
                
                // �ڶ����ŷ�����Ϣ
                vec.add(crset);
                
                rw.setReturnObject(vec);
            }
            
            return rw;
        }
        catch (Exception e)
        {
            logger.error("�ײ���Ϣ��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
            
            // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            String verifyCode = (String) map.get("verifyCode");
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_detailedbill", menuID, touchOID, "1", telnumber, operID, termID, verifyCode);
            // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("���˵���ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }       
    }
    
    /**
     * �˻�����ѯ <������ϸ����>
     * 
     * @param map
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    
    // modify begin cKF48754 2011/10/19 OR_SD_201106_95 ���ݽӿ�Э�� V3.6�޸�ɽ������ѯ
    
    public ReturnWrap queryBalance(Map map)
    {
        try
        {
            String operid = (String)map.get("operid");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("menuid");
            String isoffset = (String)map.get("isoffset");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // �Ƿ�ģ������
            Element eReq_isoffset= eBody.addElement("isoffset");
            eReq_isoffset.addText(isoffset);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_balance", menuid,
                touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        } 
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "����ѯ�쳣");
        }
        
    }
    
    // modify end cKF48754 2011/10/19 OR_SD_201106_95 ���ݽӿ�Э�� V3.6�޸�ɽ������ѯ

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
            // add begin cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
            String chargeType = (String)map.get("chargeType");
            // add end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            //modify begin by cwx456134 2017-05-13 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
            
            // add begin cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
            // �ɷѷ�ʽ
            Element eReq_chargeType = eBody.addElement("charge_type");
            eReq_chargeType.addText(chargeType);
            // add end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
            
            // modify begin g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371��ͳһ�ӿ�ƽ̨����ƴװ�����ַ�����������ǰ̨��װ
            // �����к�
            Element eReq_bankNo = eBody.addElement("bank_no");
            eReq_bankNo.addText(chargeType + bankNo);
            // modify end g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371��ͳһ�ӿ�ƽ̨����ƴװ�����ַ�����������ǰ̨��װ
            
            // �ɷ�ʱ��
            Element eReq_payDate = eBody.addElement("pay_date");
            eReq_payDate.addText(payDate);
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            // ��������
            Element eReq_acceptType = eBody.addElement("accept_type");
            eReq_acceptType.addText(acceptType);
            //modify begin by cwx456134 2017-05-13 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_FEE, menuid, touchoid, "1", servnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("����ǰ��ѯ�˻���Ϣʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ���ѳ�ֵ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chargeCommit(Map<String,String> map)
    {
        try
        {
            String operid = map.get("operid");
            String termid = map.get("termid");
            String menuid = map.get("menuid");
            String servnumber = map.get("servnumber");
            String bankNo = map.get("bankNo");
            String payDate = map.get("payDate");
            String terminalSeq = map.get("terminalSeq");
            String money = map.get("money");
            String acceptType = map.get("acceptType");
            String printFlag = map.get("invoiceType");
            String bankSite = map.get("bankSite");
            String bankOper = map.get("bankOper");
            String touchoid = map.get("touchoid");
            // add begin cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
            String chargeType = map.get("chargeType");
            // add end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
            
            //modify begin sWX219697 2014-7-17 OR_huawei_201406_1125_�������Ѹ��죬������������·��
            String region = map.get("region");
            //modify end sWX219697 2014-7-17 OR_huawei_201406_1125_�������Ѹ��죬������������·��
            
            Document docXML = null;
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // add begin cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371 
            // �ɷѷ�ʽ
            Element eReq_chargeType = eBody.addElement("charge_type");
            eReq_chargeType.addText(chargeType);
            // add end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371
            
            // modify begin g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371��ͳһ�ӿ�ƽ̨����ƴװ�����ַ�����������ǰ̨��װ            
            // �����к�
            Element eReq_bankNo = eBody.addElement("bank_no");
            eReq_bankNo.addText(chargeType + bankNo);
            // modify end g00140516 2011/11/23 R003C11L11n01 OR_SD_201111_371��ͳһ�ӿ�ƽ̨����ƴװ�����ַ�����������ǰ̨��װ
            
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
 
            // modify begin sWX219697 2014-7-17 OR_huawei_201406_1125_�������Ѹ��죬������������·��
            String chargeSwitch = (String)PublicCache.getInstance().getCachedData("LOCAL_CHARGE_SWITCH");
            boolean isSwitchOpen = "1".equals(chargeSwitch);

            docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_BUSI_CHARGEFEE, menuid, touchoid, 
            		        isSwitchOpen ? "0" : "1", 
            		        isSwitchOpen ? region : servnumber, 
            				operid, termid);
            //modify end sWX219697 2014-7-17 OR_huawei_201406_1125_�������Ѹ��죬������������·��

            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("����ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
            
            // modify begin  fwx439896 2017-8-7 OR_huawei_201704_411_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�û���Ϣ��ѯ�ӿڲ��
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_USERINFO))
            {
                Element accessType = eBody.addElement("ACCESSTYPE");
                accessType.addText(Constants.CHANNEL_ID);
               
                //�Ƿ�ģ���� 1ģ����  0��ģ����
                Element isblurry = eBody.addElement("ISBLURRY");
                isblurry.addText("0");
            }
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_USERINFO, "10001001", "", "1", telnumber, operID, termID);
            
            ReturnWrap rw = intMsgUtil.invoke(docXML);
            
            // ���ܿ�,����תΪСд
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_USERINFO) && null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                // ����ͳһתСд
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                String[] openEbusRtn = {"subsname", "prodid", "brandname","substatusname"};
                String[] destRtn = {"subname", "productid", "productname","status"};
                tagSet = CommonUtil.rtnConvert(tagSet, BusinessIdConstants.CLI_QRY_USERINFO, openEbusRtn, destRtn);
                rw.setReturnObject(tagSet);
            }
            
            return rw;
            // modify end  fwx439896 2017-8-7 OR_huawei_201704_411_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�û���Ϣ��ѯ�ӿڲ��
            
        }
        catch (Exception e)
        {
            logger.error("��ȡ�û���Ϣʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }

    //add begin l00190940 2011-9-27 �˵�����
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
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
	}
	//add end l00190940 2011-9-27 �˵�����
	
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
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_MAILBOX, menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
    	}
    	catch (Exception e)
        {
            logger.error("��ѯ�û��Ƿ��ѿ�ͨ�ֻ�����ʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add begin cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    /**
     * �˵���ע��ѯ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap queryBillAddInfo(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnumber");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String beginTime = (String)map.get("beginTime");
            String endTime = (String)map.get("endTime");
            
            // add begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            String verifyCode = (String) map.get("verifyCode");
            // add end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ��װ�������
            // qryflag,�̶���0������
            Element eReq_qryflag = eBody.addElement("qryflag");
            eReq_qryflag.addText("0");
            
            // �ֻ�����
            Element eReq_subsid = eBody.addElement("subsid");
            eReq_subsid.addText(telnumber);
            
            // ��ʼʱ��
            Element eReq_beginTime = eBody.addElement("begin_time");
            eReq_beginTime.addText(beginTime);
            
            // ����ʱ��
            Element eReq_endTime = eBody.addElement("end_time");
            eReq_endTime.addText(endTime);
            
            // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_billaddinfo", menuID,
                touchOID, "1", telnumber, operID, termID, verifyCode);
            // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953            
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ѯ�˵���עʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
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
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_CUSTINFO, menuID, touchOID, "1", telnum, operID, termID);
            
            ReturnWrap rw = intMsgUtil.invoke(docXML);
            
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CUSTINFO))
            {
                // ȡ���ض���
                Vector vector = (Vector)(rw.getReturnObject());
                
                // �ͻ���Ϣ
                CTagSet ctagset = (CTagSet)(vector.get(0));
                
                // ����תСд
                ctagset = CommonUtil.lowerTagKey(ctagset);
                
                // ���ֵ����л�ȡ�Ǽ������⴦��
                ctagset.SetValue("subsCreditName", CommonUtil.getDictNameById("subsCreditId",
                        ctagset.GetValue("creditlevel")));
                
                vector.set(0, ctagset);
            }
            
            return rw;
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
            
            // modify by lKF60882 OR_huawei_201703_629  ��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1 2017-4-13 begin
            // ��ʼ����
            Element eReq_startcycle = null;
            
            // ���ڿ�ʼʱ��
            Element eReq_starttime = null;
            
            // ���ڽ���ʱ��
            Element eReq_endtime = null;
            
            // �Ƿ�ϲ�����
            Element eReq_isunitpayment = null;
            
            // �������ܿ��������keyֵ��Ҫת��
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_BILL2012_SD))
            {
                eReq_startcycle = eBody.addElement("BILLCYCLE");
                eReq_starttime = eBody.addElement("BEGINDATE");
                eReq_endtime = eBody.addElement("ENDDATE");
                eReq_isunitpayment = eBody.addElement("ISUNITEPAYMENT");                
            }
            else
            {
                eReq_startcycle = eBody.addElement("startcycle");
                eReq_starttime = eBody.addElement("starttime");
                eReq_endtime = eBody.addElement("endtime");
                eReq_isunitpayment = eBody.addElement("isunitpayment");
            }
            
            eReq_startcycle.addText(startcycle);
            eReq_starttime.addText(starttime);
            eReq_endtime.addText(endtime);
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
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_BILL2012_SD, menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
            // modify by lKF60882 OR_huawei_201703_629  ��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1 2017-4-13 end
        }
        catch (Exception e)
        {
            logger.error("�°����˵���ѯʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add end cKF48754 2011/11/17 R003C11L11n01 OR_SD_201110_598
    public IntMsgUtil getIntMsgUtil()
    {
        return intMsgUtil;
    }

    public void setIntMsgUtil(IntMsgUtil intMsgUtil)
    {
        this.intMsgUtil = intMsgUtil;
    }
    
    /**
     * У���û��Ƿ�ʵ��ע��
     * 
     * @param bindBankCardPO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap checkFactNameRegist(Map paramMap)
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
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_bindBankCardUserInfo", menuID,
                touchOID, "1", telnum, operID, termID);
         
            // ��¼��ѯ�û���Ϣ����
            logger.info("��¼��ѯ�û���Ϣ���ģ�"+docXML.asXML());
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ѯ�û���Ϣʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �Զ����Ѳ����ӿ�
     * ��Σ�
	 *	TELNUM �û����루��ѡ��
	 *	OPRTYPE �������ͣ���ѡ 0����ѯ��1����ͨ�Զ��ɷ�,2���ر��Զ��ɷ�, 3:���£�
	 *	ISNOTIFY �Ƿ��Ͷ��ţ���ѡ��0�������ͣ�1�����ͣ�Ĭ�Ϸ��Ͷ�Ϣ��
	 *	TRIGAMT������ֵ����ѡ Ԥ����֧����ʽ�������޸�ʱ�ش����󸶷ѿɲ�����
	 *	DRAWAMT���۽���ѡ Ԥ����֧����ʽ�������޸�ʱ�ش����󸶷ѿɲ�����
	 *	BANKID  ���б��
     * @param paramMap
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @modify yWX163692 2013��11��19�� OR_SD_201309_940 �׳�ֵ���׶Σ���Լ�����Զ������ж����� 
     * @remark modify by sWX219697 2014-12-2 15:33:07 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
     */
    public ReturnWrap autoFeeSet(MsgHeaderPO msgHeader, String oprtype, String trigamt, String drawamt)
    {
    	  try
          {
              // ����������
              Document doc = DocumentHelper.createDocument();
              Element eBody = doc.addElement("message_content");
              
              DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
              
              //�������ͣ���ѡ 0����ѯ��1����ͨ�Զ��ɷ�,2���ر��Զ��ɷ�, 3:���£�
              DocumentUtil.addSubElementToEle(eBody, "oprtype", oprtype);
              
              // ���б��
              DocumentUtil.addSubElementToEle(eBody, "bankid", "50");
              
              // ������ֵ����ѡ Ԥ����֧����ʽ�������޸�ʱ�ش����󸶷ѿɲ�����
              DocumentUtil.addSubElementToEle(eBody, "trigamt", trigamt);
              
              // ���۽���ѡ Ԥ����֧����ʽ�������޸�ʱ�ش����󸶷ѿɲ�����
              DocumentUtil.addSubElementToEle(eBody, "drawamt", drawamt);
              
              // �Ƿ��Ͷ��ţ���ѡ��0�������ͣ�1�����ͣ�Ĭ�Ϸ��Ͷ�Ϣ��
              DocumentUtil.addSubElementToEle(eBody, "isnotify", "0".equals(oprtype) ? "0" : "1");
              
              //����CCEIZLWGAutoChargeSettleType
  			  return intMsgUtil.invoke("cli_AutoChargeSettle", msgHeader, doc);
          }
          catch (Exception e)
          {
              logger.error("��ѯ�û���Ϣʧ��!", e);
              
              return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
          }
    }
    
    /**
     * �׳�ֵǩԼ֮ǰ���ýӿڼ���Ƿ������Ʒ��ͨ����
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap checkProCondition(Map map)
    {
        String telnum = (String)map.get("servNumber");
        String ncode = (String)map.get("nCode");
        String type = (String)map.get("operType");
        String effectType = (String)map.get("effectType");
        String param = (String)map.get("param");
        String menuid = (String)map.get("menuID");
        String unicontact = (String)map.get("contactID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        String isSubmit = (String)map.get("isSubmit");
        String executecmd = (String)map.get("executecmd");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // NCODE
            Element eReq_ncode = eBody.addElement("ncode");
            eReq_ncode.addText(ncode);
            
            // ��������
            Element eReq_stype = eBody.addElement("stype");
            eReq_stype.addText(type);
            
            // ��Ч��ʽ
            Element eReq_effect = eBody.addElement("effect_type");
            eReq_effect.addText(effectType);
            
            Element eReq_param = eBody.addElement("param");
            eReq_param.addText(param);
            
            // �ύ��ʽ 0ֻ���鷵�� �ջ�������ֵΪ�ύ
            Element eReq_isSubmit = eBody.addElement("isSubmit");
            eReq_isSubmit.addText(isSubmit);
            
            Element eReq_executecmd = eBody.addElement("executecmd");
            eReq_executecmd.addText(executecmd);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_productreccheck", menuid, unicontact, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��Ʒ��ͨ�������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add begin zKF69263 2014/05/09 R003C14L05n01 OR_huawei_201404_1109
    /**
     * ��ѯδ��ӡ�ķ�Ʊ��¼����
     * 
     * @param map
     * @return ReturnWrap
     * @remark add by zKF69263 OR_huawei_201404_1109 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_����ɷѷ�Ʊ
     */
    @Override
    public ReturnWrap invoiceList(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchoid");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String accessType = "bsacAtsv";
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_NOINVOICEPRINT_SD))
            {
                // �ֻ�����
                Element eReq_telnum = eBody.addElement("SEARCHNUMBER");
                eReq_telnum.addText(telnumber);
                
                // ����
                Element eReq_accessType = eBody.addElement("ACCESSTYPE");
                eReq_accessType.addText(accessType);
                
                // ��ʼʱ��
                Element eReq_startTime = eBody.addElement("STARTDATE");
                eReq_startTime.addText((String)map.get("startTime"));
                
                // ����ʱ��
                Element eReq_endTime = eBody.addElement("ENDDATE");
                eReq_endTime.addText((String)map.get("endTime"));
            }
            else
            {
             // �ֻ�����
                Element eReq_telnum = eBody.addElement("telnum");
                eReq_telnum.addText(telnumber);
                
                // ����
                Element eReq_accessType = eBody.addElement("accesstype");
                eReq_accessType.addText(accessType);
                
                // ��ʼʱ��
                Element eReq_startTime = eBody.addElement("starttime");
                eReq_startTime.addText((String)map.get("startTime"));
                
                // ����ʱ��
                Element eReq_endTime = eBody.addElement("endtime");
                eReq_endTime.addText((String)map.get("endTime"));
            }
                        
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_NOINVOICEPRINT_SD, menuID,
                touchOID, "1", telnumber, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ѯҪ��ӡ�ķ�Ʊ��¼��Ϣʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯҪ��ӡ�ķ�Ʊ��ӡ������
     * 
     * @param map
     * @return ReturnWrap
     * @remark add by zKF69263 OR_huawei_201404_1109 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_����ɷѷ�Ʊ
     */
    @Override
    public ReturnWrap invoiceData(Map map)
    {
        try
        {
            String telnum = (String)map.get("telnumber");
            String recoid = (String)map.get("recoid");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String billCycle = (String)map.get("billCycle");
            String invType = (String)map.get("invType");
            String acctId = (String)map.get("acctId");

            //add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
            String eleinvType = (String)map.get("eleinvType");
            String pushType = (String)map.get("pushType");
            String receiveMode = (String)map.get("receiveMode");
            //add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ҵ��������
            Element eReq_smscont = eBody.addElement("recoid");
            eReq_smscont.addText(recoid);
            
            // ����
            Element eReq_billCycle = eBody.addElement("billCycle");
            eReq_billCycle.addText(billCycle);
            
            // ��ӡ����  0 �վ�  1 ��Ʊ
            Element eReq_invType = eBody.addElement("invType");
            eReq_invType.addText(invType);
            
            // �˺�
            Element eReq_acctId = eBody.addElement("acctId");
            eReq_acctId.addText(acctId);
            
            // ��������
            Element eReq_accessType = eBody.addElement("accessType");
            eReq_accessType.addText("bsacAtsv");

            //add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
            // �Ƿ񿪾ߵ��ӷ�Ʊ 1�� 0��
            Element eReq_eleinvType = eBody.addElement("eleinvType");
            eReq_eleinvType.addText(eleinvType);
            // ���ͷ�ʽ 1����
            Element eReq_pushType = eBody.addElement("pushType");
            eReq_pushType.addText(pushType);
            // ������Ϣ �����ʼ���ַ
            Element eReq_receiveMode = eBody.addElement("receiveMode");
            eReq_receiveMode.addText(receiveMode);
            //add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_INVOICEINFO_SD, menuID, touchOID, "1", telnum, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ѯҪ��ӡ�ķ�Ʊ��ӡ������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    // add end zKF69263 2014/05/09 R003C14L05n01 OR_huawei_201404_1109
    
    /**
     * ���ѳ�ֵ�˻�Ӧ�ɷ��ò�ѯ
     * @param paramMap
     * @remark  add by hWX5316476 2014-03-12 OR_SD_201312_90_ɽ��_�����ն˽���Ӧ��������ʾ���Ż�����
     * @return 
     */
    public ReturnWrap  qryRetcharge(Map paramMap)
    {
    	// ��ǰ�˵�ID
        String menuID = (String)paramMap.get("curMenuid");
        
        // �ͻ��Ӵ�id
        String touchOID = (String)paramMap.get("touchoid");
        
        // ����ԱID
        String operID = (String)paramMap.get("operid");
        
        // �ն˻�ID
        String termID = (String)paramMap.get("termid");
        
        // ���ÿͻ��ֻ���
        String servnumber = (String)paramMap.get("servnumber");
        
        // �ʺ�
        String acctid = (String)paramMap.get("acctid");
        
        // ��ѯ����    Ĭ��1��1�����ˣ�2����ͥ��3����
        String qrytype = (String)paramMap.get("qrytype");
        
        // ��ѯ����
        String cycle = (String)paramMap.get("cycle");
        
        // �ʵ�״̬ Ĭ��1��0��ָ�����ڲ�ѯ��1��û�н��ʵ��·ݵ������ʵ����ɷ�ʱ�ã�Ϊ1ʱ��cycle�������ã�
        String status = (String)paramMap.get("status");
        
        // �˵����� Ĭ��1����1����������2����ţ���3�����������ˣ�
        String isbaddebt = (String)paramMap.get("isbaddebt");
        
        // ��������
        String accesstype = (String)paramMap.get("accesstype");
        
        // �����ֶΣ�Ĭ��Ϊ 0
        String unbilled = (String)paramMap.get("unbilled");
        
        // ҵ����� -1,�������ɽ� 0,�ɷ� 1,��Ԥ���� 2,�������� 4,����Ԥ���� 5,���ʻ��� 6,���Ʊ�ɷ� 7,���˻���
        String processcode = (String)paramMap.get("processcode");
        
        // �û���,��Ϊ�գ��������������ʡ��ʹ�ã�
        String subsid = (String)paramMap.get("subsid");
        
        // �Ƿ���Ҫ�����û���Ϣ""
        String isneedsubsinfo = (String)paramMap.get("isneedsubsinfo");
        
        // �Ƿ�Ҫ���ص����������
        String isneedleftmoney = (String)paramMap.get("isneedleftmoney");
        
    	try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ͻ��ֻ���
            Element eReq_telnum = eBody.addElement("servnumber");
            eReq_telnum.addText(servnumber);
            
            // �ʺ�
            Element eReq_acctid = eBody.addElement("acctid");
            eReq_acctid.addText(acctid);
            
            // ��ѯ����    Ĭ��1��1�����ˣ�2����ͥ��3����
            Element eReq_qrytype = eBody.addElement("qrytype");
            eReq_qrytype.addText(qrytype);
            
            // ��ѯ����
            Element eReq_cycle = eBody.addElement("cycle");
            eReq_cycle.addText(cycle);
            
            // �ʵ�״̬ 
            Element eReq_status = eBody.addElement("status");
            eReq_status.addText(status);
            
            // �˵����� 
            Element eReq_isbaddebt = eBody.addElement("isbaddebt");
            eReq_isbaddebt.addText(isbaddebt);
            
            // ��������
            Element eReq_accesstype = eBody.addElement("accesstype");
            eReq_accesstype.addText(accesstype);
            
            // �����ֶΣ�Ĭ��Ϊ 0
            Element eReq_unbilled = eBody.addElement("unbilled");
            eReq_unbilled.addText(unbilled);
            
            // ҵ����� 
            Element eReq_processcode = eBody.addElement("processcode");
            eReq_processcode.addText(processcode);
            
            // �û���
            Element eReq_subsid = eBody.addElement("subsid");
            eReq_subsid.addText(subsid);
            
            // �Ƿ���Ҫ�����û���Ϣ""
            Element eReq_isneedsubsinfo = eBody.addElement("isneedsubsinfo");
            eReq_isneedsubsinfo.addText(isneedsubsinfo);
            
            // �Ƿ�Ҫ���ص����������
            Element eReq_isneedleftmoney = eBody.addElement("isneedleftmoney");
            eReq_isneedleftmoney.addText(isneedleftmoney);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_RETCHARGE, menuID, touchOID, "1", servnumber, operID,termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("Ӧ�ɷ��ò�ѯʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �˵��ʼ��·��ӿ�
     * @param map 
     * @remark  create sWX219697 2014-04-29 OR_SD_201404_105_ɽ��_��������139�����굥Ͷ����Ŀ�ĺ�
     * @return ReturnWrap
     */
    public ReturnWrap sendBillMail(Map<String,String> map)
    {
    	//��ǰ�˵�id
    	String menuID = map.get("menuID");
    	
    	//�ͻ��Ӵ�id
    	String touchOID = map.get("touchOID");
    	
    	//����Աid
    	String operID = map.get("operID");
    	
    	//�ն˻�id
    	String termID = map.get("termID");
    	
    	//�ͻ��ֻ�����
    	String telnum = map.get("telnum");
    	
    	//��ѯ����
    	String cycle = map.get("cycle");
    	
    	//�Ƿ�ϲ�
    	String isunitepayment = map.get("isunitepayment");
    	
    	try 
    	{
			Document doc = DocumentHelper.createDocument();
			Element eBody = doc.addElement("message_content");
			
			//�ͻ��ֻ�����
			Element eReq_telnum = eBody.addElement("telnum");
			eReq_telnum.addText(telnum);
			
			//��ѯ����
			Element eReq_cycle = eBody.addElement("cycle");
			eReq_cycle.addText(cycle);
			
			//ƫ������Ĭ��Ϊ��
			Element eReq_cycleoffset = eBody.addElement("cycleoffset");
			eReq_cycleoffset.addText("");
			
			//�Ƿ�ϲ����� 1 �ϲ���0 ���ϲ�
			Element eReq_isunitepayment = eBody.addElement("isunitepayment");
			eReq_isunitepayment.addText(isunitepayment);
			
			//�ӻ�����ȡ���ӿ�ҵ��id
			String buisID = (String) PublicCache.getInstance().getCachedData(Constants.SEND_BILL_BUSI_ID);
			Document docXML = intMsgUtil.createMsg(doc, buisID, menuID, touchOID, "1", telnum, operID, termID);
			return intMsgUtil.invoke(docXML);
		} 
    	catch (Exception e) 
    	{
    		
            logger.error("�˵��ʼ��·�ʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
    }
    
    /**
     * �½ᷢƱ�����ڽӿڲ�ѯ
     * @param paramMap
     * @remark add by wWX217192 on 20140504 for OR_huawei_201404_1108 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_��ӡ�½ᷢƱ
     * @return
     */
    public ReturnWrap qryBillCycle(Map map)
    {
    	// �ֻ�����
    	String telnum = (String)map.get("servnum");
    	
    	// ����
    	String billCycle = (String)map.get("cycle");
    	
    	String menuid = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            //modify begin by cwx456134 2017-05-02 OR_huawei_201704_376_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����1
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_BILLCYCLECUSTINFO))
            {
                // �ֻ�����
                Element eReq_telNum = eBody.addElement("TELNUM");
                eReq_telNum.addText(telnum);
            }
            else
            {
                // �ֻ�����
                Element eReq_telNum = eBody.addElement("servnum");
                eReq_telNum.addText(telnum);
            }
            
            // ����
            Element eReq_cycle = eBody.addElement("cycle");
            eReq_cycle.addText(billCycle);
            //modify end by cwx456134 2017-05-02 OR_huawei_201704_376_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����1
    	
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_BILLCYCLECUSTINFO, menuid, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch(Exception e)
        {
        	logger.error("���ڲ�ѯʧ��!", e);
        	
        	return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �½ᷢƱ��ѯ���ݽӿ�
     * @param paramMap
     * @remark add by wWX217192 on 20140508 for OR_huawei_201404_1108 Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_��ӡ�½ᷢƱ
     * @return �½ᷢƱ����
     */
    public ReturnWrap qryMonthInvoice(Map map)
    {
    	// �ֻ�����
    	String servNum = (String)map.get("servnum");
    	
    	// ����
    	String cycle = (String)map.get("billcycle");
    	
    	// ���ڿ�ʼʱ��
    	String startDate = (String)map.get("startdate");
    	
    	// ���ڽ���ʱ��
    	String endDate = (String)map.get("enddate");
    	
    	// ���˺�
    	String acctid = (String)map.get("acctid");
    	
    	String menuId = (String)map.get("menuID");
    	String touchOID = (String)map.get("touchOID");
    	String operId = (String)map.get("operID");
    	String termID = (String)map.get("termID");
    	
        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
        String eleinvType = (String)map.get("eleinvType");
        String pushType = (String)map.get("pushType");
        String receiveMode = (String)map.get("receiveMode");
        //add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
    	
    	try
    	{
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telNum = eBody.addElement("servnum");
            eReq_telNum.addText(servNum);
            
            // �˺�
            Element eReq_Acctid = eBody.addElement("acctId");
            eReq_Acctid.addText(acctid);
            
            // ����
            Element eReq_BillCycle = eBody.addElement("billCycle");
            eReq_BillCycle.addText(cycle);
            
            // ���ڿ�ʼʱ��
            Element eReq_StartDate = eBody.addElement("startDate");
            eReq_StartDate.addText(startDate);
            
            // ���ڽ���ʱ��
            Element eReq_EndDate = eBody.addElement("endDate");
            eReq_EndDate.addText(endDate);

            //add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
            // �Ƿ񿪾ߵ��ӷ�Ʊ 1�� 0��
            Element eReq_eleinvType = eBody.addElement("eleinvType");
            eReq_eleinvType.addText(eleinvType);
            // ���ͷ�ʽ 1����
            Element eReq_pushType = eBody.addElement("pushType");
            eReq_pushType.addText(pushType);
            // ���ͷ�ʽ 1����
            Element eReq_receiveMode = eBody.addElement("receiveMode");
            eReq_receiveMode.addText(receiveMode);
            //add begin by cwx456134 2017-04-19 OR_SD_201703_234_���ӷ�Ʊ�Ż�����
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_MONTHINVOICEINFO, menuId, touchOID, "1", servNum, operId, termID);
            
            return intMsgUtil.invoke(docXML);
    	}
    	catch(Exception e)
    	{
    		logger.error("�½ᷢƱ���ݲ�ѯʧ��!", e);
        	
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
    	}
    }
	
    /**
     * ��ѯ�ɱ���������Ʒ��Ϣ
     * @param paramMap
     * @remark add by jWX216858 2014-5-7 OR_huawei_201404_1116_ɽ��_Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_ת�ײͲ�Ʒ
     * @return ReturnWrap
     */
	public ReturnWrap qryMainProdInfo(Map<String, String> paramMap)
	{
		// ��ǰ�˵�id
		String menuId = paramMap.get("menuId");
		
		// �ն˻�id
		String termId = paramMap.get("termId");
		
		// ����Աid
		String operId = paramMap.get("operId");
		
		// �ͻ��Ӵ�id
		String touchId = paramMap.get("touchId");
		
		// �������
		String region = paramMap.get("region");
		
		// ��Ʒ����
		String prodId = paramMap.get("prodId");
		
		// ��������
		String accessType = paramMap.get("accessType");
		
		try
		{
			Document doc = DocumentHelper.createDocument();
			Element ebody = doc.addElement("message_content");
			
			// �������
			Element eReq_region = ebody.addElement("REGION");
			eReq_region.addText(region);
			
			//modify begin by cwx456134 2017-05-12 OR_huawei_201704_376_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����1
			if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CONVERTPRODINFO))
			{
			    // ��Ʒ����
	            Element eReq_prodId = ebody.addElement("MAINPRODID");
	            eReq_prodId.addText(prodId);
	            
	            // �Ƿ�ȷƥ��channel
                Element eReq_channel = ebody.addElement("ISALLCHANNEL");
                eReq_channel.addText("0");
	            
			}
			else
			{
			    // ��Ʒ����
	            Element eReq_prodId = ebody.addElement("PRODID");
	            eReq_prodId.addText(prodId);
			}
            //modify end by cwx456134 2017-05-12 OR_huawei_201704_376_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����1
			
			// ��������
			Element eReq_accessType = ebody.addElement("ACCESSTYPE");
			eReq_accessType.addText(accessType);
			
			Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_CONVERTPRODINFO, menuId, touchId, "0", region, operId, termId);
			return intMsgUtil.invoke(docXML);
		}
		catch (Exception e)
		{
			logger.error("��ѯ��ת�������Ʒ��Ϣʧ��", e);
			
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
     * ��ѯ�����Ʒģ����Ϣ
     * @param paramMap
     * @return ReturnWrap
     * @remark add by jWX216858 2014-5-8 OR_huawei_201404_1116_ɽ��_Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_ת�ײͲ�Ʒ
     */
	public ReturnWrap qryProdTemplateInfo(Map<String, String> paramMap)
	{
		// ��ǰ�˵�id
		String menuId = paramMap.get("menuId");
		
		// �ն˻�id
		String termId = paramMap.get("termId");
		
		// ����Աid
		String operId = paramMap.get("operId");
		
		// �ͻ��Ӵ�id
		String touchId = paramMap.get("touchId");
		
		// �������
		String region = paramMap.get("region");
		
		// ��Ʒ����
		String prodId = paramMap.get("prodId");
		
		// ��������
		String channel = paramMap.get("channel");
		
		// ��������
		String recType = paramMap.get("recType");
		
		// ��ѯʱ�Ƿ�ȷƥ��
		String ruleType = paramMap.get("ruleType");
		
		try
		{
			// ��װ����
			Document doc = DocumentHelper.createDocument();
			Element ebody = doc.addElement("message_content");

			// ��������
			Element eReq_recType = ebody.addElement("RECTYPE");
			eReq_recType.addText(recType);
			
			//modify begin lWX431760 2017-06-15 OR_huawei_201704_376_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����1
			// ��������
			Element eReq_channel = null;
			// ��Ʒ����
			Element eReq_operId = null;			
			if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_PRODTEMPLATEINFO))
			{
			    eReq_channel= ebody.addElement("ACCESSTYPE");
			    eReq_channel.addText(channel);
			    eReq_operId = ebody.addElement("PRODCODE");
			    eReq_operId.addText(prodId);
			    //�������
	            Element eReq_region = ebody.addElement("REGION");
	            eReq_region.addText(region);
			}
			else
			{
			    eReq_channel = ebody.addElement("CHANNEL");
			    eReq_channel.addText(channel);
			    eReq_operId = ebody.addElement("MAINPRODID");
			    eReq_operId.addText(prodId);
			}			
									 					
			// ��ѯʱ�Ƿ�ȷƥ�� 
			Element eReq_ruleType = ebody.addElement("RULETYPE");
			eReq_ruleType.addText(ruleType);
			
			Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_PRODTEMPLATEINFO, menuId, touchId, "0", region, operId, termId);
			//modify end lWX431760 2017-06-15 OR_huawei_201704_376_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����1
			return intMsgUtil.invoke(docXML);
		}
		catch (Exception e)
		{
			logger.error("��ѯ�����Ʒģ��ʧ��", e);
			
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}

	/**
	 * ִ�������Ʒ���
	 * @param paramMap
     * @return ReturnWrap
	 * @remark add by jWX216858 2014-5-8 OR_huawei_201404_1116_ɽ��_Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_ת�ײͲ�Ʒ
	 */
	public ReturnWrap mainProdChangeRec(Map<String, String> paramMap) 
    {
		// ��ǰ�˵�id
		String menuId = paramMap.get("menuId");
		
		// �ն˻�id
		String termId = paramMap.get("termId");
		
		// ����Աid
		String operId = paramMap.get("operId");
		
		// �ͻ��Ӵ�id
		String touchId = paramMap.get("touchId");
		
		// �ֻ�����
		String telnum = paramMap.get("telnum");
		
		// �Ƿ�ʹ��NOCDE 1����ʹ�� 0:ʹ��
		String notexencode = paramMap.get("NOTEXENCODE");
		
		// �����Ʒ����
		String mainProdId = paramMap.get("MAINPRODID");
		
		// �����Ʒ���Ԥ���� PreBsacNBChgMainProd��Ԥ����,����Ϊִ�������Ʒ���
		String preparebusi = paramMap.get("PREPAREBUSI");
		
		try 
		{
			// ��װ����
			Document doc = DocumentHelper.createDocument();
			Element ebody = doc.addElement("message_content");
			
			// �ֻ���
			Element eReq_telnum = ebody.addElement("TELNUM");
			eReq_telnum.addText(telnum);
			
			// �Ƿ�ʹ��ncode
			Element eReq_ncode = ebody.addElement("NOTEXENCODE");
			eReq_ncode.addText(notexencode);
			
			// �����Ʒ����
			Element eReq_mainProdid = ebody.addElement("MAINPRODID");
			eReq_mainProdid.addText(mainProdId);
		
			// �����Ʒ���Ԥ���� PreBsacNBChgMainProd��Ԥ����,����Ϊִ�������Ʒ���
			Element eReq_prebusi = ebody.addElement("PREPAREBUSI");
			eReq_prebusi.addText(preparebusi);
			if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_PRODCHANGEINFO))
			{
			    //Ӧ�շ���
	            DocumentUtil.addSubElementToEle(ebody, "CONTRASTFEE", "");
			    Element eReq_recProdlist = ebody.addElement("RECPRODLIST");
	            DocumentUtil.addSubElementToEle(eReq_recProdlist, "PRODID",mainProdId);
	            DocumentUtil.addSubElementToEle(eReq_recProdlist, "EFFECTTYPE", paramMap.get("affecttype"));
	            DocumentUtil.addSubElementToEle(eReq_recProdlist, "OPTYPE", "PCOpRec");
	            DocumentUtil.addSubElementToEle(eReq_recProdlist, "OBJTYPE", "PCIntObjMain");
	            DocumentUtil.addSubElementToEle(eReq_recProdlist, "TEMPLATEID", paramMap.get("templateId"));
	            DocumentUtil.addSubElementToEle(eReq_recProdlist, "PRIVID", ""); 
			}
			else
			{
			    Element eReq_prodid = ebody.addElement("prodid");
	            eReq_prodid.addText(paramMap.get("MAINPRODID"));
			    // ��Ч��ʽ
	            Element eReq_affecttype = ebody.addElement("affecttype");
	            eReq_affecttype.addText(paramMap.get("affecttype"));
	            
	            // ��������
	            Element eReq_opertype = ebody.addElement("operType");
	            eReq_opertype.addText(paramMap.get("opertype"));
	            
	            // ��������
	            Element eReq_objtype = ebody.addElement("objtype");
	            eReq_objtype.addText(paramMap.get("objtype"));
	            
	            // �Ż�����
	            Element eReq_privname = ebody.addElement("privname");
	            eReq_privname.addText(paramMap.get("privname"));
	            
	            // ģ�����
	            Element eReq_tempid = ebody.addElement("templateId");
	            eReq_tempid.addText(paramMap.get("templateId"));
			}
			// add begin jWX216858 2015-6-16 OR_SD_201505_294 ���ڶ�MO���¿ͻ����ҵ��ʱ�������ѵ�����
			// MO�ײ����Ѽ�ֵ��Ԥ����ʱ���̶�ֵmessage, ִ�������Ʒ���ʱ����
			Element eReq_MOPrivTips = ebody.addElement("MOPrivTips");
			eReq_MOPrivTips.addText(paramMap.get("MOPrivTips"));
			
			// �������룬�̶�:bsacAtsv
			Element eReq_AccessType = ebody.addElement("ACCESSTYPE");
			eReq_AccessType.addText("bsacAtsv");
			
			Element eReq_issubmit = ebody.addElement("ISSUBMIT");
			eReq_issubmit.addText(paramMap.get("ISSUBMIT"));
			// add end jWX216858 2015-6-16 OR_SD_201505_294 ���ڶ�MO���¿ͻ����ҵ��ʱ�������ѵ�����
			
			String openVersion = CommonUtil.getParamValue("change_confir_aopenversion");		
			
			Document docXML = null;
			
			if("".equals(preparebusi))
			{
				// �����Ʒ���
				docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_BUSI_PRODCHANGEINFO, menuId, touchId, "1", telnum, operId, termId);
			}
			else
			{
				// ��Ʒ���ȷ��
				docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_BUSI_PRODCHANGECONFIR, menuId, touchId, "1", telnum, operId, termId);
			}
			
			ReturnWrap rw = intMsgUtil.invoke(docXML);
			
			// ��Ʒ���ȷ��,�����ܿ��ӿ�,tagset����תСд
			if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_PRODCHANGECONFIR) && StringUtils.isNotBlank(preparebusi))
			{
			    Vector vec = (Vector)rw.getReturnObject();
	            
	            // ����ͳһתСд
	            CTagSet tagSet = CommonUtil.lowerTagKey((CTagSet)vec.get(0));
	            
	            vec.set(0, tagSet);
			}
			
			return rw;
		}
		catch (Exception e)
		{
			logger.error("ִ�������Ʒ���ʧ��", e);
			
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}

	/**
     * <��ѯ�������˻���Ϣ>
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-6-6 09:17:25 OR_huawei_201404_1118 ɽ��_[�����ն�]_֧�Ŵ����̿��г�ֵ����
     */
    public ReturnWrap qryAgentInfo(Map<String,String> map)
    {
    	//����Աid
    	String operid = map.get("operid");
    	
    	//�ն˻�id
    	String atsvNum = map.get("atsvNum");
    	
    	//�������ֻ�����
    	String telnum = map.get("telnum");
    	
    	//�Ӵ�id
    	String touchoid = map.get("touchoid");
    	
    	//��ǰ�˵�id
    	String curMenuId = map.get("curMenuid");
    	
    	try 
    	{
			Document doc = DocumentHelper.createDocument();
			Element eBody = doc.addElement("message_content");
			
			//�������ֻ�����
			eBody.addElement("telnum").addText(telnum);
			
			Document docXML = intMsgUtil.createMsg(doc, "cli_qry_agentinfo", curMenuId, touchoid, "1", telnum, 
					operid, atsvNum);
			
			return intMsgUtil.invoke(docXML);
		} 
    	catch (Exception e) 
    	{
    		logger.error("�������˻���Ϣ��ѯʧ�ܣ�", e);
        	
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
    	
    }
    
    /**
     * �����̽ɷ�ǰ��¼
     * @param map
     * @return
     * @remark create by sWX219697 2014-6-6 OR_huawei_201404_1118 ɽ��_[�����ն�]_֧�Ŵ����̿��г�ֵ����
     */
    public ReturnWrap beforeAgentCharge(Map<String,String> map)
    {
    	//����Աid
    	String operid = map.get("operid");
    	
    	//�ն˻�id
    	String atsvNum = map.get("atsvNum");
    	
    	//�Ӵ�id
    	String touchoid = map.get("touchoid");
    	
    	//��ǰ�˵�id
    	String curMenuId = map.get("curMenuid");
    	
    	//��������֯��������
    	String orgId = map.get("orgId");
    	
    	//�������˻�
    	String agentAccount = map.get("fundacctid");
    	
    	//��ֵ���
    	String tMoney = map.get("amount");
    	
    	//�������ͱ���
    	String accept_type = map.get("accept_type");
    	
    	//���к�
    	String bank_no = map.get("bank_no");
    	
    	//�ͻ��ֻ�����
    	String msisdn = map.get("msisdn");
    	
    	//��Ŀ����
    	String subjectid = map.get("subjectid");
    	
    	//��������
    	String pay_date = map.get("pay_date");
    	
    	//��������
    	String channel = map.get("channel");
    	
    	try 
    	{
			Document doc = DocumentHelper.createDocument();
			Element eBody = doc.addElement("message_content");
			
			//��������֯����id
			eBody.addElement("orgid").addText(orgId);
			
			//�������˻�����
			eBody.addElement("fundacctid").addText(agentAccount);
			
			//��ֵ���
			eBody.addElement("amount").addText(tMoney);
			
			//�������ͱ���
			eBody.addElement("accept_type").addText(accept_type);
			
			//���к�
			eBody.addElement("bank_no").addText(bank_no);
			
			//�������ֻ�����
			eBody.addElement("msisdn").addText(msisdn);
			
			//��Ŀ����
			eBody.addElement("subjectid").addText(subjectid);
			
			//��������
			eBody.addElement("pay_date").addText(pay_date);
			
			//����Ա����
			eBody.addElement("operid").addText(operid);
			
			//��������
			eBody.addElement("channel").addText(channel);
			
			Document docXML = intMsgUtil.createMsg(doc, "cli_busi_beforeagentcharge", curMenuId, touchoid, 
					"1", msisdn, operid, atsvNum);
			
			return intMsgUtil.invoke(docXML);
		} 
    	catch (RuntimeException e) 
    	{
    		logger.error("�����̽���ǰ��¼����ʧ�ܣ�", e);
        	
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
    	
    }
    
    /**
     * �����̳�ֵ
     * @param map
     * @return
     * @remark create by sWX219697 2014-6-6 OR_huawei_201404_1118 ɽ��_[�����ն�]_֧�Ŵ����̿��г�ֵ����
     */
    public ReturnWrap agentCharge(Map<String,String> map)
    {
    	
    	//����Աid
    	String operid = map.get("operid");
    	
    	//�ն˻�id
    	String atsvNum = map.get("atsvNum");
    	
    	//�Ӵ�id
    	String touchoid = map.get("touchoid");
    	
    	//�������ֻ�����
    	String telnum = map.get("telnum");
    	
    	//��ǰ�˵�id
    	String curMenuId = map.get("curMenuid");
    	
    	//��ֵ����λ��Ԫ
    	String amount = map.get("amount");
    	
    	//�����̽ɷ�ǰ��ˮ��
    	String bank_nbr = map.get("bank_nbr");
    	
    	//���к�
    	String bank_no = map.get("bank_no");
    	
    	//��������
    	String pay_date = map.get("pay_date");
    	
    	//������������
    	String channel = map.get("channel");
    	
    	try 
    	{
			Document doc = DocumentHelper.createDocument();
			Element eBody = doc.addElement("message_content");
			
			//��ֵ���
			eBody.addElement("amount").addText(amount);
			
			//�ɷ�ǰ��ˮ��
			eBody.addElement("bank_nbr").addText(bank_nbr);
			
			//���к�
			eBody.addElement("bank_no").addText(bank_no);
			
			//��������
			eBody.addElement("pay_date").addText(pay_date);
			
			//��������
			eBody.addElement("channel").addText(channel);
			
			Document docXML =  intMsgUtil.createMsg(doc, "cli_busi_agentcharge", curMenuId, touchoid, "1", telnum, 
					operid, atsvNum);
			
			return intMsgUtil.invoke(docXML);
		} 
    	catch (Exception e) 
    	{
            logger.error("�����̽���ʧ�ܣ�", e);
        	
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
		
    }

	/**
	 * ��ѯ�û�ʵ���ƵǼǱ�־
	 * @param map
	 * @return �ӿڵ��óɹ����ı�־λ���ӿڷ�����Ϣ
	 * @remark create wWX217192 2014-06-23 OR_huawei_201406_338
	 */
	public ReturnWrap qryRealNameType(Map<String, String> map) 
	{
		try
		{
			// ��װ����
			Document doc = DocumentHelper.createDocument();
			Element ebody = doc.addElement("message_content");
			
			// �ֻ�����
			ebody.addElement("telnum").addText(map.get("telnum"));
			
			Document docXML = intMsgUtil.createMsg(doc, "cli_qry_custrealnametype_sd", (String) map.get("menuID"), 
					(String) map.get("touchOID"), "1", (String) map.get("telnum"), (String) map.get("operID"), (String) map.get("termID"));
			
			return intMsgUtil.invoke(docXML);
			
		}
		catch(Exception e)
		{
			logger.error("��ѯ�û�ʵ���ƵǼǱ�־ʧ��!", e);
			
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
    
    /**
     * ��֤SIM��
     * @param paramMap
     * @return
     * @remark add by hWX5316476 2014-06-23 OR_SD_201405_849_������Ӫҵ������ʵ������֤�Ĺ���
     */
    public ReturnWrap chkSIMCardNo(Map<String,String> paramMap)
    {
        // ��ǰ�˵�id
        String menuId = paramMap.get("menuId");
        
        // �ն˻�id
        String termId = paramMap.get("termId");
        
        // ����Աid
        String operId = paramMap.get("operId");
        
        // �ͻ��Ӵ�id
        String touchId = paramMap.get("touchId");
        
        // �ֻ�����
        String telnum = paramMap.get("telnum");
        
        // SIM������
        String cardno = paramMap.get("cardno");
        
        try 
        {
            // ��װ����
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");
            
            // �ֻ���
            DocumentUtil.addSubElementToEle(ebody, "telnum", telnum);
            
            // SIM������
            DocumentUtil.addSubElementToEle(ebody, "cardno", cardno);
            
            // SIM����֤
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_chksimcardno", menuId, touchId, "1", telnum, operId, termId);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("SIM����֤ʧ��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "SIM����֤�쳣");
        }
    }
    
    /**
     * ��ֵ��¼��֤
     * @param paramMap
     * @return 
     * @remark add by hWX5316476 2014-06-24 V200R003C10LG0601 OR_SD_201405_849_������Ӫҵ������ʵ������֤�Ĺ���
     */
    public ReturnWrap chkChargeRecord(Map<String, Object> paramMap)
    {
        // ��ǰ�˵�id
        String menuId = (String)paramMap.get("menuId");
        
        // �ն˻�id
        String termId = (String)paramMap.get("termId");
        
        // ����Աid
        String operId = (String)paramMap.get("operId");
        
        // �ͻ��Ӵ�id
        String touchId = (String)paramMap.get("touchId");
        
        // �ֻ�����
        String telnum = (String)paramMap.get("telnum");
        
        // �ɷѼ�¼ 
        ChargeRecordPO chargeRecordPO = (ChargeRecordPO)paramMap.get("chargeRecordPO");
        
        try 
        {
            // ��װ����
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");
            
            // �ֻ���
            DocumentUtil.addSubElementToEle(ebody, "telnum", telnum);
            
            // ��ֵ��¼
            Element chargerecord1 = ebody.addElement("chargerecord");
            Element chargerecord2 = ebody.addElement("chargerecord");
            Element chargerecord3 = ebody.addElement("chargerecord");
            
            // �ɷ�����
            DocumentUtil.addSubElementToEle(chargerecord1, "chargedate", chargeRecordPO.getCurrMonChargeDate());
            DocumentUtil.addSubElementToEle(chargerecord2, "chargedate", chargeRecordPO.getLastMonChargeDate());
            DocumentUtil.addSubElementToEle(chargerecord3, "chargedate", chargeRecordPO.getPreLastMonChargeDate());
            
            // �ɷѽ��
            DocumentUtil.addSubElementToEle(chargerecord1, "chargeamount", CommonUtil.yuanToFen(chargeRecordPO.getCurrMonChargeAmount()));
            DocumentUtil.addSubElementToEle(chargerecord2, "chargeamount", CommonUtil.yuanToFen(chargeRecordPO.getLastMonChargeAmount()));
            DocumentUtil.addSubElementToEle(chargerecord3, "chargeamount", CommonUtil.yuanToFen(chargeRecordPO.getPreLastMonChargeAmount()));
            
            // ��¼ʵ������֤������־
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_chkchargerecord", menuId, touchId, "1", telnum, operId, termId);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ֵ��¼��֤�쳣��",  e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��ֵ��¼��֤�쳣�����Ժ����ԣ�");
        }
    }
    
    /**
     * ͨ����¼��֤
     * @param paramMap
     * @return
     * @remark add by hWX5316476 2014-06-24 V200R003C10LG0601 OR_SD_201405_849_������Ӫҵ������ʵ������֤�Ĺ���
     */
    public ReturnWrap chkCallRecord(Map<String, String> paramMap)
    {
        // ��ǰ�˵�id
        String menuId = paramMap.get("menuId");
        
        // �ն˻�id
        String termId = paramMap.get("termId");
        
        // ����Աid
        String operId = paramMap.get("operId");
        
        // �ͻ��Ӵ�id
        String touchId = paramMap.get("touchId");
        
        // �ֻ�����
        String telnum = paramMap.get("telnum");
        
        // ͨ����¼ 
        String[] calledNums = paramMap.get("calledNum").split(",");
        
        try 
        {
            // ��װ����
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");
            
            // �ֻ���
            DocumentUtil.addSubElementToEle(ebody, "telnum", telnum);
            
            for(int i = 0; i < calledNums.length;i++)
            {
                Element calledNum = ebody.addElement("callednum");
                DocumentUtil.addSubElementToEle(calledNum, "calledtelnum", calledNums[i]);
            }
            
            // ��¼ʵ������֤������־
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_chkcallrecord", menuId, touchId, "1", telnum, operId, termId);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("ͨ����¼��֤�쳣��",  e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "ͨ����¼��֤�쳣�����Ժ����ԣ�");
        }
    }
    
    /**
     * ��¼ʵ������֤������־
     * @param paramMap
     * @param map
     * @return ReturnWrap
     * @remark add by hWX5316476 2014-06-24 OR_SD_201405_849_������Ӫҵ������ʵ������֤�Ĺ���
     * @remark modify gWX301560 2015-08-12 OR_SD_201506_971_ɽ��_���ڴ�����ʵ���ƿͻ����Ǽ����ϵͳ֧�ŵ�����
     */
    public ReturnWrap saveRealNameChkRecLog(Map<String,String> paramMap,Map<String,String> map)
    {
        // ��ǰ�˵�id
        String menuId = paramMap.get("menuId");
        
        // �ն˻�id
        String termId = paramMap.get("termId");
        
        // ����Աid
        String operId = paramMap.get("operId");
        
        // �ͻ��Ӵ�id
        String touchId = paramMap.get("touchId");
        
        // �ֻ�����
        String telnum = paramMap.get("telnum");
        
//        // У�鷽ʽ ���� 
//        String attrid = paramMap.get("attrid");
//        
//        // У������
//        String newattrvalue = paramMap.get("newattrvalue");
        
        try 
        {
            // ��װ����
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");
            
            // �ֻ���
            DocumentUtil.addSubElementToEle(ebody, "telnum", telnum);
            
            // ��֤��ϸ
            Element recdetail = ebody.addElement("recdetail");
            
            for(Entry<String, String> obj : map.entrySet())
            {
	            // У�鷽ʽ ����
	            DocumentUtil.addSubElementToEle(recdetail, "attrid", obj.getKey());
	            
	            // У������
	            DocumentUtil.addSubElementToEle(recdetail, "newattrvalue", obj.getValue());
            }
            
            // ��¼ʵ������֤������־
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_saverealnamechkreclog", menuId, touchId, "1", telnum, operId, termId);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��¼ʵ������֤������־�쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��¼ʵ������֤������־�쳣");
        }
    }
    
    /**
	 * ���ɶ�����֤�����
	 * 
	 * @param map
	 * @return �ӿڷ�����Ϣ
	 * @remark create wWX217192 2014-06-24 OR_huawei_201406_338
	 */
	public ReturnWrap getRandomPwd(Map<String, String> map)
	{
		try
		{
			// ��װ����
			Document doc = DocumentHelper.createDocument();
			Element ebody = doc.addElement("message_content");
			
			// �ֻ�����
			ebody.addElement("telnum").addText(map.get("telnum"));
			
			// ҵ������
			ebody.addElement("dorectype").addText(map.get("dorectype"));
			
			// Ĭ��Ϊ4���������������
			ebody.addElement("subcmdid").addText(map.get("subcmdid"));
			
			Document docXML = intMsgUtil.createMsg(doc, "cli_busi_randompwd", (String) map.get("menuID"), 
					(String) map.get("touchOID"), "1", (String) map.get("telnum"), (String) map.get("operID"), (String) map.get("termID"));
			
			return intMsgUtil.invoke(docXML);
		}
		catch(Exception e)
		{
			logger.error("���ɶ�����֤��ʧ��!", e);
			
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * �������������֤
	 * 
	 * @param map
	 * @return �ӿڷ�����Ϣ
	 * @remark create wWX217192 2014-06-25 OR_huawei_201406_338
	 */
	public ReturnWrap checkRandomPwd(Map<String, String> map)
	{
		try
		{
			// ��װ����
			Document doc = DocumentHelper.createDocument();
			Element ebody = doc.addElement("message_content");
			
			// �ֻ�����
			ebody.addElement("telnum").addText(map.get("telnum"));
			
			// �������
			ebody.addElement("randompasswd").addText(map.get("randompwd"));
			
			Document docXML = intMsgUtil.createMsg(doc, "cli_busi_chkrandompwd", map.get("menuID"), 
					map.get("touchOID"), "1", map.get("telnum"), map.get("operID"), map.get("termID"));
			
			return intMsgUtil.invoke(docXML);
		}
		catch(Exception e)
		{
			logger.error("�������������֤ʧ��!", e);
			
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * ����������֤�ӿ�
	 * 
	 * @param map
	 * @return �ӿڷ�����Ϣ
	 * @remark create wWX217192 2014-06-25 OR_huawei_201406_338
	 */
	public ReturnWrap checkUserPwd(Map<String, String> map)
	{
		try
		{
			// ��װ����
			Document doc = DocumentHelper.createDocument();
			Element ebody = doc.addElement("message_content");
			
			// �ֻ�����
			ebody.addElement("telnum").addText(map.get("telnum"));
			
			// ��������
			ebody.addElement("passwd").addText(map.get("passwd"));
			
			Document docXML = intMsgUtil.createMsg(doc, "cli_busi_chkserverpwd", map.get("menuID"), 
					map.get("touchOID"), "1", map.get("telnum"), map.get("operID"), map.get("termID"));
			
			return intMsgUtil.invoke(docXML);
		}
		catch(Exception e)
		{
			logger.error("�������������֤ʧ��!", e);
			
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
    /**
     * �����̳�ֵ���У��
     * @param msgHeader ��Ϣͷ
     * @param orgId ��֯��������
     * @param agentAccount �ʽ��˻�����
     * @param subjectId ��Ŀ����
     * @param tMoney ��ֵ��� ��
     * @return
     * @remark create by sWX219697 2014-8-23 10:43:09 OR_huawei_201408_657_�����ն˴������ʽ��˻���ֵ�����Ż�
     */
	public ReturnWrap checkBeforeAgentCharge(MsgHeaderPO msgHeader, String orgId, 
			String agentAccount, String subjectId, String tMoney)
	{
        try 
        {
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// ��������
			DocumentUtil.addSubElementToEle(eBody, "channelid", orgId);
			
			//�������˻�
			DocumentUtil.addSubElementToEle(eBody, "acctid", agentAccount);
			
			//��Ŀ����
			DocumentUtil.addSubElementToEle(eBody, "subjectid", subjectId);
			
			//��ֵ��� ��
			DocumentUtil.addSubElementToEle(eBody, "adjustamount", tMoney);
			
			// ���ú�̨�ӿ�
			return intMsgUtil.invoke("cli_qry_CheckBeforeAgentCharge", msgHeader, msgBody);
		} 
        catch (Exception e) 
        {
            logger.error("У������̳�ֵ���ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
        
	}

	/**
	 * <��ѯ�û��ĸ�������>
	 * <������ϸ����>
	 * @param msgHeader ��Ϣͷ��
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-10-7 14:28:55 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
	 */
	public ReturnWrap qrySubsPrepayType(MsgHeaderPO msgHeader) 
	{
        try 
        {
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// �ֻ�����
			DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
			
			// ���ú�̨�ӿ�
			return intMsgUtil.invoke("cli_qry_SubsPrepayType", msgHeader, msgBody);
		} 
        catch (Exception e) 
        {
            logger.error("��ѯ�û���������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��ѯ�û���������ʧ��");
		}
	}

	/**
	 * ����ͨ������
	 * @param msgHeader ����ͷ��Ϣ
	 * @param nCode nCode
	 * @return
	 * @remark create by jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_ɽ��_���������ն˲�Ʒ�����������4G��ѡ�ײ��Լ��޸�GPRS��4G����Ĺ��ܣ�ȫҵ�������Ż���
	 */
	@Override
	public ReturnWrap voiceCallRec(MsgHeaderPO msgHeader, String nCode)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element ebody = msgBody.addElement("message_content");
			
			// �ֻ�����
			DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
			
			// nCode
			DocumentUtil.addSubElementToEle(ebody, "ncode", nCode);
			
			// �������� �̶���ADD
			DocumentUtil.addSubElementToEle(ebody, "stype", "ADD");
			
			// Ԥ���� �̶���BsacNBSubmit
			DocumentUtil.addSubElementToEle(ebody, "preparebusi", "BsacNBSubmit");
			
			// ������������Ľ��й���ɾ�� �̶���NEEDPREMUTEX
			DocumentUtil.addSubElementToEle(ebody, "PREMUTEX", "NEEDPREMUTEX");
			
			// ���ýӿ�
			return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_CHANGEPRODUCTSUBMITSD, msgHeader, msgBody); 
		}
		catch (Exception e)
		{
			logger.error("����ͨ������ʧ��:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}

	/**
	 * ������������
	 * @param header ��Ϣͷ
	 * @param productset ��ֵ��Ʒ��(��Ʒ��,��ֵ��Ʒ,�Ż�;��Ʒ��,��ֵ��Ʒ,�Ż�;)
	 * @return
	 * @remark create by jWX216858 2014-10-07 R003C10LG1001 OR_SD_201408_1083_ɽ��_���������ն˲�Ʒ�����������4G��ѡ�ײ��Լ��޸�GPRS��4G����Ĺ��ܣ�ȫҵ�������Ż���
	 */
	@Override
	public ReturnWrap gprsWlanRec(MsgHeaderPO header, String productset) 
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element ebody = msgBody.addElement("message_content");
			
			// ��������
			DocumentUtil.addSubElementToEle(ebody, "rectype", "ChangeProduct");
			
			// �ֻ�����
			DocumentUtil.addSubElementToEle(ebody, "telnum", header.getTelNum());
			
			// ��ֵ��Ʒ�� (��Ʒ��,��ֵ��Ʒ,�Ż�;��Ʒ��,��ֵ��Ʒ,�Ż�;)
			DocumentUtil.addSubElementToEle(ebody, "productset", productset);
			
			// ��������
			DocumentUtil.addSubElementToEle(ebody, "recdate", "");
			
			// ������������Ľ��й���ɾ�� �̶���NEEDPREMUTEX
			DocumentUtil.addSubElementToEle(ebody, "PREMUTEX", "NEEDPREMUTEX");
			
			return intMsgUtil.invoke("cli_busi_GprsWlanRecSD", header, msgBody);
			
		}
		catch (Exception e)
		{
			logger.error("������������ʧ��:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * <��ѯ�׳�ֵ�û��������б�>
	 * <������ϸ����>
	 * @param msgHeader
	 * @param ncode
	 * @param stype
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark sWX219697 2014-12-2 19:36:41 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
	 */
	public ReturnWrap viceNumberQry(MsgHeaderPO msgHeader, String ncode, String stype)
	{
		try 
		{
			Document msgBody = DocumentHelper.createDocument();
			
			Element ebody = msgBody.addElement("message_content");
			
			//�ֻ�����
			DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
			
			//ncode
			DocumentUtil.addSubElementToEle(ebody, "ncode", ncode);
			
			//�������� ��ѯ��QRY
			DocumentUtil.addSubElementToEle(ebody, "stype", stype);
			
			//������ˮ�߲�ѯ
			return intMsgUtil.invoke("cli_qry_vicenum", msgHeader, msgBody);
		} 
		catch (Exception e) 
		{
			logger.error("��ѯ�׳�ֵ�û��������б�ʧ��:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��ѯ�׳�ֵ�û��������б�ʧ��");
		}
	}

	/**
	 * <������ɾ���׳�ֵ������>
	 * <������ϸ����>
	 * @param msgHeader
	 * @param viceArray ����������
	 * @param opertype �������ͣ�1��������2��ɾ��
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark sWX219697 2014-12-4 12:00:03 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
	 */
	public ReturnWrap viceNumberSet(MsgHeaderPO msgHeader, String[] viceArray, String opertype)
	{
		try 
		{
			Document msgBody = DocumentHelper.createDocument();
			
			Element eBody = msgBody.addElement("message_content");
			
			//�ֻ�����
			DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
			
			//�������ͣ�1��������2��ɾ��
			DocumentUtil.addSubElementToEle(eBody, "opertype", opertype);
			
			//CRSet �������б�
			for(String viceNum : viceArray)
			{
				Element crsetNode = eBody.addElement("slavenumlist");
				DocumentUtil.addSubElementToEle(crsetNode, "slavenum", viceNum);
			}
			
			//opcode:CCEIAddEasyVoucherSlaveNum
			return intMsgUtil.invoke("cli_busi_EasyVoucherSlaveNum", msgHeader, msgBody);
		} 
		catch (Exception e) 
		{
			logger.error("������ɾ���׳�ֵ������ʧ��:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "������ɾ���׳�ֵ������ӿڲ���ʧ��");
		}
	}
	
	/**
	 * ��ѯ��ǰ�û��Ƿ�ǩԼ�Ͱ��׳�ֵ
	 * @param headerPo ��Ϣͷ
	 * @return �û�ǩԼ��Ϣ
	 * @remark create by wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
	 */
	public ReturnWrap checkHeBao(MsgHeaderPO headerPo, String bankCardNum)
	{
		/*try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element ebody = msgBody.addElement("message_content");
			
			// ����ѯ���ֻ�����
			DocumentUtil.addSubElementToEle(ebody, "telNum", headerPo.getTelNum());
			
			// ���п�����Ϣ
			DocumentUtil.addSubElementToEle(ebody, "cardNo", bankCardNum);
			
			//���ò�ѯ�Ͱ��׳�ֵǩԼ��ϵ
			return intMsgUtil.invoke("cli_busi_SDHBTelQryRegist", headerPo, msgBody);
		}
		catch(Exception e)
		{
			logger.info("��ѯ��ǰ�û�ǩԼ��Ϣʧ��!", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}*/
		try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
            		"<message_head version=\"1.0\"><menuid>recBindBankCard</menuid>" +
            		"<process_code>cli_busi_SDHBTelQryRegist</process_code><verify_code></verify_code>" +
            		"<resp_time>20150407095636</resp_time>" +
            		"<sequence><req_seq>231</req_seq><operation_seq></operation_seq></sequence>" +
            		"<retinfo><rettype>0</rettype><retcode>100</retcode>" +
            		"<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>" +
            		"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
            		"<message_content><AGRNO></AGRNO><BANKABBR></BANKABBR><cardType></cardType>" +
            		"<cardNo>6225885415787779</cardNo><status>2</status>" +
            		"<signDate></signDate><signTime></signTime></message_content>]]>" +
            		"</message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
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
	 * �Ͱ��׳�ֵƽ̨���Ͷ����������
	 * @param headerPo ������ͷ
	 * @param smsType ��֤������
	 * @param AGRNO Э���
	 * @return �Ͱ��׳�ֵƽ̨�ķ��ر���
	 * @remark create by wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
	 */
	public ReturnWrap sendHeBaoRandom(MsgHeaderPO headerPo, String smsType, BindBankCardPO cardPo)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			
			Element ebody = msgBody.addElement("message_content");
			
			// ���������ֻ�����
			DocumentUtil.addSubElementToEle(ebody, "telNum", headerPo.getTelNum());
			
			// ��֤������
			DocumentUtil.addSubElementToEle(ebody, "smsType", smsType);
			
			// ֤������ 00-����֤
			DocumentUtil.addSubElementToEle(ebody, "IdType", cardPo.getIdCardType());
			
			// ֤������
			DocumentUtil.addSubElementToEle(ebody, "IdNo", cardPo.getIdCardNum());
			
			// Э���
			DocumentUtil.addSubElementToEle(ebody, "AGRNO", cardPo.getAGRNO());
			
			// ������� �Է�Ϊ��λ
			DocumentUtil.addSubElementToEle(ebody, "amount", cardPo.getAmount());
			
			// ���д���
			DocumentUtil.addSubElementToEle(ebody, "BANKABBR", cardPo.getBankAbbr());
			
			// ���п����� 0-��ǿ� 1-���ÿ�
			DocumentUtil.addSubElementToEle(ebody, "cardType", cardPo.getBankCardType());
			
			// ���п����� ��Ҫ����
			DocumentUtil.addSubElementToEle(ebody, "cardNo", cardPo.getBankCardNum());
			
			// ���п����� ��Ҫ���� GBK����
			DocumentUtil.addSubElementToEle(ebody, "cardName", cardPo.getUserFactName());
			
			// ���ÿ�CVV2 ��Ҫ����
			DocumentUtil.addSubElementToEle(ebody, "cardCvv2", cardPo.getCvn2());
			
			// ���ÿ���Ч�� ��Ҫ����
			DocumentUtil.addSubElementToEle(ebody, "cardExpDate", cardPo.getExpire());
			
			return intMsgUtil.invoke("cli_busi_SDHBTelGetSmsChkCode", headerPo, msgBody);
		}
		catch(Exception e)
		{
			logger.info("�Ͱ��׳�ֵƽ̨���Ͷ��������ʧ��!", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * �Ͱ��׳�ֵǩԼ
	 * @param headerPo
	 * @param cardPo
	 * @param smsCode
	 * @return
	 * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
	 */
	public ReturnWrap signHeBao(MsgHeaderPO headerPo, BindBankCardPO cardPo, String smsCode)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			
			Element ebody = msgBody.addElement("message_content");
			
			// ���������ֻ�����
			DocumentUtil.addSubElementToEle(ebody, "telNum", headerPo.getTelNum());
			
			// ���д���
			DocumentUtil.addSubElementToEle(ebody, "BANKABBR", cardPo.getBankAbbr());
			
			// ���п�����
			DocumentUtil.addSubElementToEle(ebody, "cardType", cardPo.getBankCardType());
			
			// ���п�����
			DocumentUtil.addSubElementToEle(ebody, "cardNo", cardPo.getBankCardNum());
			
			// ���п��û���
			DocumentUtil.addSubElementToEle(ebody, "cardName", cardPo.getUserFactName());
			
			// ֤������
			DocumentUtil.addSubElementToEle(ebody, "IdType", cardPo.getIdCardType());
			
			// ֤������
			DocumentUtil.addSubElementToEle(ebody, "IdNo", cardPo.getIdCardNum());
			
			// ������ˮ
			DocumentUtil.addSubElementToEle(ebody, "tradeNo", cardPo.getAppFlowCode());
			
			// ������֤��
			DocumentUtil.addSubElementToEle(ebody, "smsCode", smsCode);
			
			// ����֪ͨ 0-��Ҫ�·��ɹ����� 1-����Ҫ�·��ɹ�����(Ĭ��)
			DocumentUtil.addSubElementToEle(ebody, "smsNotify", "0");
			
			// ���ÿ�CVV2
			DocumentUtil.addSubElementToEle(ebody, "cardCvv2", cardPo.getCvn2());
			
			// ���ÿ���Ч��
			DocumentUtil.addSubElementToEle(ebody, "cardExpDate", cardPo.getExpire());
			
			// ��ʹ������ �Է�Ϊ��λ
			DocumentUtil.addSubElementToEle(ebody, "trigAmt", 
					(String) PublicCache.getInstance().getCachedData(Constants.SH_HEBAO_TRIGAMT));
			
			// �Զ����ѽ�� ��ԪΪ��λ
			DocumentUtil.addSubElementToEle(ebody, "drawAmt",
					(String) PublicCache.getInstance().getCachedData(Constants.SH_HEBAO_DRAWAMT));
			
			return intMsgUtil.invoke("cli_busi_SDHBTelRegist", headerPo, msgBody);
		}
		catch(Exception e)
		{
			logger.info("�Ͱ��׳�ֵƽ̨ǩԼʧ��!", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * �Ͱ��׳�ֵ��Լ�ӿ�
	 * @param headerPo
	 * @param cardPo
	 * @param smsCode
	 * @return
	 * @remark create by wWX217192 2014-11-29 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
	 */
	public ReturnWrap unsignHeBao(MsgHeaderPO headerPo, BindBankCardPO cardPo, String smsCode)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			
			Element ebody = msgBody.addElement("message_content");
			
			// ���������ֻ�����
			DocumentUtil.addSubElementToEle(ebody, "telNum", headerPo.getTelNum());
			
			// ���֧��Э���
			DocumentUtil.addSubElementToEle(ebody, "AGRNO", cardPo.getAGRNO());
			
			// ������ˮ��
			DocumentUtil.addSubElementToEle(ebody, "tradeNo", cardPo.getAppFlowCode());
			
			// ������֤��
			DocumentUtil.addSubElementToEle(ebody, "smsCode", smsCode);
			
			// ����֪ͨ
			DocumentUtil.addSubElementToEle(ebody, "smsNotify", "0");
			
			//���úͰ��׳�ֵ��Լ�ӿ� 
			return intMsgUtil.invoke("cli_busi_SDHBTelUnRegist", headerPo, msgBody);
		}
		catch(Exception e)
		{
			logger.info("�Ͱ��׳�ֵƽ̨��ԼԼʧ��!", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
		
	}
	
	/**
	 * �Ͱ��׳�ֵ�Զ����ѹ�������
	 * @param headerPo
	 * @param oprType
	 * @param trigAmt
	 * @param drawAmt
	 * @param bankId
	 * @return
	 * @remark create by wWX217192 2014-12-10 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
	 */
	public ReturnWrap setHeBaoAutoValue(MsgHeaderPO headerPo, String oprType, BankCardInfoPO bankCardInfoPO)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			
			Element ebody = msgBody.addElement("message_content");
			
			// �ֻ�����
			DocumentUtil.addSubElementToEle(ebody, "TELNUM", headerPo.getTelNum());
			
			// IFPOST
			DocumentUtil.addSubElementToEle(ebody, "IFPOST", "");
			
			// OPRFORMNUM
			DocumentUtil.addSubElementToEle(ebody, "OPRFORMNUM", "");
			
			// ��ѯ���� ��ѯ0,������1,ɾ����2,�޸�:3
			DocumentUtil.addSubElementToEle(ebody, "OPRTYPE", oprType);
			
			// ������ֵ
			DocumentUtil.addSubElementToEle(ebody, "TRIGAMT", bankCardInfoPO.getTrigamt());
			
			// ���۽��
			DocumentUtil.addSubElementToEle(ebody, "DRAWAMT", bankCardInfoPO.getDrawamt());
			
			// ���д���
			DocumentUtil.addSubElementToEle(ebody, "BANKID", bankCardInfoPO.getBankId());
			
			return intMsgUtil.invoke("cli_busi_ChgMobilePaySettleType", headerPo, msgBody);
		}
		catch(Exception e)
		{
			logger.info("�Ͱ��׳�ֵ�����Զ����ѽ��ʧ��!", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
		
	}
	
	/**
     * ����ʱ�����ֻ����������֤��Ϣ�Ƿ�һ��
     * @param msgHeader
     * @param idCardNo
     * @return ReturnWrap
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap checkReissueIdCard(MsgHeaderPO msgHeader, String idCardNo)
    {
        try 
        {
            Document msgBody = DocumentHelper.createDocument();
            
            Element ebody = msgBody.addElement("message_content");
            
            //modify begin lwx439898 2017-05-13 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CHKCERTBYSERVNUM))
            {
                // �ֻ�����
                DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
            }
            else
            {
                // �ֻ�����
                DocumentUtil.addSubElementToEle(ebody, "servnum", msgHeader.getTelNum()); 
            }
            //modify end lwx439898 2017-05-13 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3
            
            // ����֤����
            DocumentUtil.addSubElementToEle(ebody, "certid", idCardNo);
            
            //modify begin lwx439898 2017-05-15 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CHKCERTBYSERVNUM))
            {
                ReturnWrap rw = intMsgUtil.invoke("cli_busi_ChkCertByServnum", msgHeader, msgBody);
                
                if (rw.getStatus() == SSReturnCode.ERROR)
                {
                    throw new ReceptionException("ʵ����֤�쳣����֤ʧ�ܣ�");
                }
                
                CTagSet tagSet = (CTagSet)rw.getReturnObject();
                if ("1".equals(tagSet.GetValue("CHKRESULT")))
                {
                    // ֤������
                    DocumentUtil.addSubElementToEle(ebody, "certtype", "IdCard");
                    
                    return intMsgUtil.invoke("cli_busi_ChkCertByCert", msgHeader, msgBody);
                }
                else
                {
                    throw new ReceptionException("ʵ����֤ʧ�ܣ�");
                }
            }
            else
            {
                return intMsgUtil.invoke("cli_busi_ChkCertByServnum", msgHeader, msgBody);
            }
            //modify begin lwx439898 2017-05-15 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3
        } 
        catch (Exception e) 
        {
            logger.error("����ʱ�����ֻ����������֤��Ϣ�Ƿ�һ��ʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ����ҵ�����У��
     * @param msgHeader
     * @return ReturnWrap
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap checkReissueNum(MsgHeaderPO msgHeader)
    {
        try 
        {
            Document msgBody = DocumentHelper.createDocument();
            
            Element ebody = msgBody.addElement("message_content");
            
            // �绰����
            DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
            
            // ��������
            DocumentUtil.addSubElementToEle(ebody, "accesstype", "bsacAtsv");
            
            // ҵ������
            DocumentUtil.addSubElementToEle(ebody, "rectype", "ChangeEnum");
            
            // �Ƿ����쳣(�0��,�����쳣)
            DocumentUtil.addSubElementToEle(ebody, "isthrowexception", "0");
            
            // ���ú�̨�ӿ�
            return intMsgUtil.invoke("cli_busi_CheckBusiRecValid", msgHeader, msgBody);
        } 
        catch (Exception e) 
        {
            logger.error("����ҵ�����У��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * <�������>
     * <������ϸ����>
     * @param msgHeader
     * @param iccid
     * @param blankCardNum �հ׿�����
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap countReissueFee(MsgHeaderPO msgHeader, String iccid, String blankCardNum)
    {
        try 
        {
            Document msgBody = DocumentHelper.createDocument();
            
            Element ebody = msgBody.addElement("message_content");
            
            // �绰����
            DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
            
            // ��ICCID
            DocumentUtil.addSubElementToEle(ebody, "iccid", iccid);
            
            // �հ׿�����
            DocumentUtil.addSubElementToEle(ebody, "blankcardno", blankCardNum);
            
            // �Ƿ���п���У��(0����Ҫ;1��Ҫ),Ĭ�ϲ���Ҫ
            DocumentUtil.addSubElementToEle(ebody, "ischeck", "1");
            
            // �Ƿ���й���У��
            DocumentUtil.addSubElementToEle(ebody, "ischeckrecvalid", "1");
            
            // ���ú�̨�ӿ�
            return intMsgUtil.invoke("cli_busi_CalChangeEnumFee", msgHeader, msgBody);
        } 
        catch (Exception e) 
        {
            logger.error("������ѣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * <�����ύ>
     * <������ϸ����>
     * @param msgHeader
     * @param recFee Ӧ�ɷ���
     * @param payType ֧����ʽ
     * @param blankno �հ׿�����     
     * @param simInfo sim����Ϣ
     * @param bankNo ���б��
     * @param bankNbr ���нɷ���ˮ��
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap reissueCommit(MsgHeaderPO msgHeader, String recFee, String payType, 
        String blankno, SimInfoPO simInfo, String bankNo, String bankNbr)
    {
        try 
        {
            Document msgBody = DocumentHelper.createDocument();
            
            Element ebody = msgBody.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
            
            // ����
            DocumentUtil.addSubElementToEle(ebody, "accesstype", "bsacAtsv");
            
            // ��ICCID
            DocumentUtil.addSubElementToEle(ebody, "iccid", simInfo.getIccid());
            
            // ��IMSI
            DocumentUtil.addSubElementToEle(ebody, "imsi", simInfo.getImsi());
            
            // ��1
            DocumentUtil.addSubElementToEle(ebody, "isneedfee", "1");
            
            // �ն���ȡ����
            DocumentUtil.addSubElementToEle(ebody, "sumfee", recFee);
            
            // ֧����ʽ
            DocumentUtil.addSubElementToEle(ebody, "paytype", payType);
            
            // �Ƿ����
            DocumentUtil.addSubElementToEle(ebody, "isderatefee", "0");
            
            // �հ׿�
            DocumentUtil.addSubElementToEle(ebody, "blankcardno", blankno);
            
            // �Ƿ����ҵ�����У��
            DocumentUtil.addSubElementToEle(ebody, "ischeckrecvalid", "1");
            
            // add begin zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
            // ���к�
            DocumentUtil.addSubElementToEle(ebody, "agentunit", bankNo);
            
            // Ψһ��ˮ���ն˻����ص�termseq
            DocumentUtil.addSubElementToEle(ebody, "agentformnum", bankNbr);
            
            // ҵ������(������ZZKH ������ZZBK Ԥ������ZZHD)
            DocumentUtil.addSubElementToEle(ebody, "accepttype", Constants.ACCEPTTYPE_REISSUECARD);
            
            // �ɷ����ڣ���ʽ�� YYYYMMDDHH24MISS
            DocumentUtil.addSubElementToEle(ebody, "paydate", DateUtil._getCurrentTime());
            // add end zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
            
            // ���ú�̨�ӿ�
            return intMsgUtil.invoke("cli_busi_ChangeEnumRec", msgHeader, msgBody);
        } 
        catch (Exception e) 
        {
            logger.error("�����ύ��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * <��ѯ�û���Ϣ>
     * <������ϸ����>
     * @param msgHeader
     * @param region
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by zKF69263 2014-12-29 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap getSubscriberByTel(MsgHeaderPO msgHeader,String region)
    {
        try
        {
            // ���
            Map<String, String> map = new HashMap<String, String>();
            
            // �ֻ�����
            map.put("telNum", msgHeader.getTelNum());
            
            //����
            map.put("region", region);
            map.put("warning", "false");
            map.put("qryUnfinish", "false");
            
            return intMsgUtil.invokeDubbo("PTGetSubscriberByTel", msgHeader, map);
        }
        catch (Exception e)
        {
            logger.error("��ѯ�û���Ϣ�ӿڵ����쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"������ѯ�û���Ϣʧ��");
        }

    }
	
	/**
	 * ҵ����Ч��У��
	 * 
	 * @param termInfo �ն˻���Ϣ
	 * @param customer �ͻ���Ϣ
	 * @param menuId �˵�id
	 * @return true�����Լ�������ҵ��false����ֹ����ҵ��
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����
	 */
	public ReturnWrap checkRecValid(MsgHeaderPO msgHeader)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// �ֻ���
			DocumentUtil.addSubElementToEle(eBody, "telNum", msgHeader.getTelNum());
			
			// ��������
			DocumentUtil.addSubElementToEle(eBody, "accessType", "bsacAtsv");
			
			// �������� �̶���RewardActivity
			DocumentUtil.addSubElementToEle(eBody, "recType", "RewardActivity");
			
			return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_CHECKRECVALID, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("ҵ����Ч��У��ʧ��:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * ��ѯ�û��Ѿ����ڵĵ���
	 * @param msgHeader ��Ϣͷ
	 * @return
	 * @remark create by jWX216858 2014-11-29 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����
	 */
	public ReturnWrap qrySubsActLevelList(MsgHeaderPO msgHeader) 
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// �ֻ���
			DocumentUtil.addSubElementToEle(eBody, "telNum", msgHeader.getTelNum());
			
			return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_SUBSPRIVLISTSD, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("��ѯ�û��Ѿ����ڵĵ���:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * ��ѯ��Ʒ�б�
	 * @param msgHeader ��Ϣͷ
	 * @param actLevelId ���α���
	 * @param activityId �����
	 * @return
	 * @remark create by jWX216858 2014-12-01 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����
	 */
	public ReturnWrap qryRewardList(MsgHeaderPO msgHeader, String actLevelId, String activityId)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// �����
			DocumentUtil.addSubElementToEle(eBody, "prodid", activityId);
	        
	        // ���α���
			DocumentUtil.addSubElementToEle(eBody, "privid", actLevelId);
	        
			return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_REWARDLISTSD, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("��ѯ��Ʒ�б�:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * ��ѯ�����
	 * @param msgHeader ��Ϣͷ
	 * @param actid �����
	 * @param levelid ���α���
	 * @param rewardId ��Ʒ����
	 * @return
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����
	 */
	public ReturnWrap qryActivityFee(MsgHeaderPO msgHeader, String actid, String levelid, String rewardId)
	{
		try 
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			//modify begin by cwx456134 2017-05-10 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
			if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CHKPRIVANDCALCFEE))
			{
	            // �ֻ�����
	            DocumentUtil.addSubElementToEle(eBody, "TELNUM", msgHeader.getTelNum());
	            
	            // �����
	            DocumentUtil.addSubElementToEle(eBody, "ACTNO", actid);
	            
	            // ���α���
	            DocumentUtil.addSubElementToEle(eBody, "ACTLEVEL", levelid);
	            	            
	            // ��ֽ�Ʒ  
	            String[] rewardIds = rewardId.split("\\|");
	            StringBuffer actreward = new StringBuffer();
	            for (int i = 0; i < rewardIds.length; i++)
	            {
	                //��ʽ����Ʒ����1$��Ʒ����|��Ʒ����2$��Ʒ����
	                if(StringUtils.isEmpty(actreward.toString()))
	                {
	                    actreward.append(rewardIds[i]).append("$").append("1");
	                }
	                else
	                {
                        actreward.append("|").append(rewardIds[i]).append("$").append("1");
	                }
	            }

                // ��Ʒ���б�
                DocumentUtil.addSubElementToEle(eBody, "ACTREWARD", actreward.toString());
			}
			else
			{
	            // �ֻ�����
	            DocumentUtil.addSubElementToEle(eBody, "telNum", msgHeader.getTelNum());
	            
	            // �����
	            DocumentUtil.addSubElementToEle(eBody, "actid", actid);
	            
	            // ���α���
	            DocumentUtil.addSubElementToEle(eBody, "levelid", levelid);
	            
	            // ��ֽ�Ʒ  
	            String[] rewardIds = rewardId.split("\\|");
	            
	            for (int i = 0; i < rewardIds.length; i++)
	            {
	                // ��Ʒ��Ϣ
	                Element rwdInfo = eBody.addElement("rwdInfo");
	                
	                // ��Ʒ����
	                DocumentUtil.addSubElementToEle(rwdInfo, "rewardId", rewardIds[i]);
	            
	                // ��Ʒ���� imeiid
	                DocumentUtil.addSubElementToEle(rwdInfo, "imeiid", "");
	                
	                // ����
	                DocumentUtil.addSubElementToEle(rwdInfo, "quantity", "1");
	            } 
			}
            //modify e by cwx456134 2017-05-10 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
			
			return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_CHKPRIVANDCALCFEE, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("��������ʧ��:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
	 * Ԥ����������
	 * 
     * @param msgHeader ��Ϣͷ
     * @param prestoredRewardPO ��� 
     * @param bankNo ���б��
     * @param bankNbr ���нɷ���ˮ��
	 * @return
	 * @remark create by jWX216858 2014-12-08 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����
	 */
    public ReturnWrap recRewardCommit(MsgHeaderPO msgHeader, PrestoredRewardPO prestoredRewardPO, String bankNo,
        String bankNbr)
	{
		try 
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// �ֻ�����
			DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
			
			// �����
			DocumentUtil.addSubElementToEle(eBody, "actno", prestoredRewardPO.getActivityId());
			
			// ���α���
			DocumentUtil.addSubElementToEle(eBody, "actlevel", prestoredRewardPO.getActLevelId());
			
			// ��Ʒ��
			DocumentUtil.addSubElementToEle(eBody, "actreward", prestoredRewardPO.getActreward());
			
			// �ֻ�imeiid
			DocumentUtil.addSubElementToEle(eBody, "imeiid", "");
			
			// �Ƿ�Ԥ����
			DocumentUtil.addSubElementToEle(eBody, "onlycheck", prestoredRewardPO.getOnlycheck());
			
			// ��Ʒ����
			DocumentUtil.addSubElementToEle(eBody, "quantity", "1");
			
			// ��������
			DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
			
			// ����
			DocumentUtil.addSubElementToEle(eBody, "password", "");
			
			// �û�Ͷ��ķ��ý�� Ԥ����ʱ���Բ���
			DocumentUtil.addSubElementToEle(eBody, "totalfee", prestoredRewardPO.getTotalFee());
			
			// ֧����ʽcash:�ֽ�  card:������
			DocumentUtil.addSubElementToEle(eBody, "paytype", StringUtils.capitalize(prestoredRewardPO.getPaytype()));
			
			// add begin zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
			// ���к�
            DocumentUtil.addSubElementToEle(eBody, "agentunit", bankNo);
            
            // Ψһ��ˮ���ն˻����ص�termseq
            DocumentUtil.addSubElementToEle(eBody, "agentformnum", bankNbr);
            
            // ҵ������(������ZZKH ������ZZBK Ԥ������ZZHD)
            DocumentUtil.addSubElementToEle(eBody, "accepttype", Constants.ACCEPTTYPE_PRESTORED_REWARD);
            
            // �ɷ����ڣ���ʽ�� YYYYMMDDHH24MISS
            DocumentUtil.addSubElementToEle(eBody, "paydate", DateUtil._getCurrentTime());
			// add end zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������            

			return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_RECREWARDCOMMITSD, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("Ԥ����������ʧ��:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
    /** 
     * ҵ�����ǰ��¼ҵ�������Ϣ
     * 
     * @param msgHeader ��������ͷ
     * @param bankNo ���к�
     * @param payDate �ɷ�����,��ʽ��YYYYMMDDHH24MISS
     * @param acceptType ҵ������(������ZZKH ������ZZBK Ԥ������ZZHD)
     * @param bankNbr Ψһ��ˮ(��agentcharge����AGENTFORMNUM�ֶα���һ��) ���ն˻����ص�termseq
     * @param amount �������
     * @param prestoredRewardPO ���Ϣ
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by zKF69263 2015-05-07 OR_SD_201503_333_SD_�����ն˻����Ԥ������
     */
    public ReturnWrap writeNetFeeInfo(MsgHeaderPO msgHeader, String bankNo, String acceptType,
        String bankNbr, String amount, PrestoredRewardPO prestoredRewardPO)
    {
        try 
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // ���к�
            DocumentUtil.addSubElementToEle(eBody, "bankno", bankNo);
            
            // �ɷ�����,��ʽ��YYYYMMDDHH24MISS
            DocumentUtil.addSubElementToEle(eBody, "paydate", DateUtil._getCurrentTime());
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "msisdn", msgHeader.getTelNum());
            
            // �������
            DocumentUtil.addSubElementToEle(eBody, "amount", CommonUtil.fenToYuan(amount));
            
            // ҵ������(������ZZKH ������ZZBK Ԥ������ZZHD)
            DocumentUtil.addSubElementToEle(eBody, "accepttype", acceptType);
            
            // Ψһ��ˮ(��agentcharge����AGENTFORMNUM�ֶα���һ��) ���ն˻����ص�termseq
            DocumentUtil.addSubElementToEle(eBody, "banknbr", bankNbr);
            
            // Ԥ���������ʱ�������²���
            if (null != prestoredRewardPO)
            {
                // ����룬�����Ͳ�������ֵ
                DocumentUtil.addSubElementToEle(eBody, "actid", prestoredRewardPO.getActivityId());
                
                // ����α��룬�����Ͳ�������ֵ
                DocumentUtil.addSubElementToEle(eBody, "levelid", prestoredRewardPO.getActLevelId());
                
                // ��Ʒ����,���Ʒ��Ŵ��������Ʒ�м���|�ָ�  �磺3333333333|3322322333|4444444|5555555,�����Ͳ�������ֵ
                DocumentUtil.addSubElementToEle(eBody, "rewardid", prestoredRewardPO.getActreward());
                
                // ֧����ʽCash:�ֽ�  Card:�������� �����Ͳ�������ֵ
                DocumentUtil.addSubElementToEle(eBody, "paytype", StringUtils.capitalize(prestoredRewardPO.getPaytype()));
            }
            else
            {
                // ����룬�����Ͳ�������ֵ
                DocumentUtil.addSubElementToEle(eBody, "actid", "");
                
                // ����α��룬�����Ͳ�������ֵ
                DocumentUtil.addSubElementToEle(eBody, "levelid", "");
                
                // ��Ʒ����,���Ʒ��Ŵ��������Ʒ�м���|�ָ�  �磺3333333333|3322322333|4444444|5555555,�����Ͳ�������ֵ
                DocumentUtil.addSubElementToEle(eBody, "rewardid", "");
                
                // ֧����ʽCash:�ֽ�  Card:�������� �����Ͳ�������ֵ
                DocumentUtil.addSubElementToEle(eBody, "paytype", "");
            }
            
            // ��������
            DocumentUtil.addSubElementToEle(eBody, "accesstype", Constants.CHANNEL_ID);

            // ���ýӿ�
            return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_WRITENETFEEINFO, msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("ҵ�����ǰ��¼ʧ��:", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }

	/**
	 * ��ѯӪ���������ú��û�Ԥ�����
	 * @param msgHeader ��Ϣͷ
	 * @param recoid ������ˮ
	 * @param totalFee �û�����ķ���
	 * @return
	 * @remark create by jWX216858 2014-12-05 OR_SD_201410_482_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����
	 */
	public ReturnWrap qryRecFeeAndPreFee(MsgHeaderPO msgHeader, String recoid, String totalFee)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// ������ˮ
			DocumentUtil.addSubElementToEle(eBody, "recoid", recoid);
			
			// �û�����ķ���
			DocumentUtil.addSubElementToEle(eBody, "totalFee", totalFee);
			
			return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_PRIVFEESD, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("��ѯӪ���������ú��û�Ԥ�����ʧ��:", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
	}
	
	/**
     * �����ػ�ҵ���
     * @param msgHeader
     * @param privServPackPO
     * @return
     * @remark create by hWX5316476 2014-12-24 OR_SD_201410_482_SD_���������ն�����Ԥ�����ͻ�������ն������Ƽ����ܵ�����
     */
    public ReturnWrap privServPackRec(MsgHeaderPO msgHeader,PrivServPackPO privServPackPO)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element ebody = msgBody.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
            
            // nCode
            DocumentUtil.addSubElementToEle(ebody, "ncode", privServPackPO.getNcode());
            
            // �������� �̶���ADD
            DocumentUtil.addSubElementToEle(ebody, "stype", "ADD");
            
            // ��Ч���ͣ���0Ĭ��  2����  3������ 4ָ��ʱ����Ч��
            DocumentUtil.addSubElementToEle(ebody, "efftype", privServPackPO.getEffType());
            
            // ��Чʱ�� ��ʽ YYYYMMDD
            DocumentUtil.addSubElementToEle(ebody, "ncode_effdate", privServPackPO.getEffDate().replace("-", ""));
                
            // ʧЧʱ�� ��ʽYYYYMMDD
            DocumentUtil.addSubElementToEle(ebody, "ncode_expdate", privServPackPO.getEndDate().replace("-", ""));
            
            // ���ýӿ�
            return intMsgUtil.invoke("cli_busi_privServPackCommit", msgHeader, msgBody); 
        }
        catch (Exception e)
        {
            logger.error("�ػ�ҵ�������ʧ��:", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ���������ƷId��ȡ�����Ʒ��Ϣ
     * 
     * @param msgHeader
     * @param prodid ��Ʒ����
     * @param type  �������� ���Ϊ��Ʒ��PRODTYPE�����Ϊ�Żݴ�PRIVTYPE
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-1-9 OR_SD_201411_411_SD_���������ն˲�Ʒ���������Ż�������
     */
    public ReturnWrap qryProdInfoById(MsgHeaderPO msgHeader,String prodid,String type)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");
            
            //modify by lWX431760 2017-09-29 OR_huawei_201706_781_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ�����(ͬ��ҵ��)
            // �����Ʒ����
            DocumentUtil.addSubElementToEle(ebody, "objid", prodid);
                
            // ��������
            DocumentUtil.addSubElementToEle(ebody, "objtype", type);
            //modify by lWX431760 2017-09-29 OR_huawei_201706_781_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ�����(ͬ��ҵ��)
                        
            // ���ýӿ�
            return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_GETPRODINFOBYID, msgHeader, doc); 
        }
        catch (Exception e)
        {
            logger.error("���������ƷId��ȡ�����Ʒ��Ϣ�쳣:", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "���������ƷId��ȡ�����Ʒ��Ϣ�쳣");
        }
    }
    
    /**
     * ���ڵ���ת��
     * @param msgHeader
     * @param ncode NCODE
     * @param stype �������� ADD ���� DEL ɾ�� MOD �޸� QRY ��ѯ
     * @param preparebusi Ԥ���� �̶���BsacNBSubmit
     * @param premutex �Ƿ񽫻���������Ľ��й���ɾ������ɾ������
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-1-9 OR_SD_201411_411_SD_���������ն˲�Ʒ���������Ż�������
     * @remark modify by lWX431760 2017-09-29 OR_huawei_201706_781_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ�����(ͬ��ҵ��)
     */
    public ReturnWrap chgLevelInGroup(MsgHeaderPO msgHeader, String ncode, String stype, String preparebusi, String premutex)
    {
        try
        {         
            Document msgBody = DocumentHelper.createDocument();
            Element ebody = msgBody.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(ebody, "telnum", msgHeader.getTelNum());
            
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CHANGEPRODUCTSUBMITSD))
            {
                Element eReq_recProdlist = ebody.addElement("RECPRODLIST");
                DocumentUtil.addSubElementToEle(eReq_recProdlist, "PRODID",ncode);
                DocumentUtil.addSubElementToEle(eReq_recProdlist, "EFFECTTYPE", "");
                DocumentUtil.addSubElementToEle(eReq_recProdlist, "OPTYPE", "PCOpRec");
                DocumentUtil.addSubElementToEle(eReq_recProdlist, "OBJTYPE", "NCODE");
                DocumentUtil.addSubElementToEle(eReq_recProdlist, "TEMPLATEID", "");
                DocumentUtil.addSubElementToEle(eReq_recProdlist, "PRIVID", ""); 
            }
            else
            {
                // nCode
                DocumentUtil.addSubElementToEle(ebody, "ncode", ncode);
                
                // �������� �̶���ADD
                DocumentUtil.addSubElementToEle(ebody, "stype", stype);
                
                // Ԥ���� �̶���BsacNBSubmit
                DocumentUtil.addSubElementToEle(ebody, "preparebusi", preparebusi);
                
                // ������������Ľ��й���ɾ�� �̶���NEEDPREMUTEX
                DocumentUtil.addSubElementToEle(ebody, "PREMUTEX", premutex);
            }
            
            
            // ���ýӿ�             
            return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_CHANGEPRODUCTSUBMITSD, msgHeader, msgBody); 
        }
        catch (Exception e)
        {
            logger.error("���ڵ���ת��ʧ��:", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"���ڵ���ת���쳣");
        }
    }
    
    /** 
     * ����ʱ֤������У��
     * 
     * @param msgHeader ��Ϣͷ
     * @param curMenuid ��ǰ�˵�ID
     * @param certType ֤������
     * @param certId ֤������
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap chkCertSubs(MsgHeaderPO msgHeader, String certType, String certId)
    {
    	try
    	{
    		Document msgBody = DocumentHelper.createDocument();
    		Element eBody = msgBody.addElement("message_content");
    		
    		// ֤������
    		DocumentUtil.addSubElementToEle(eBody, "certID", certId);
    		
    		// ֤������
    		DocumentUtil.addSubElementToEle(eBody, "certType", certType);
    		
    		// ���ýӿ�
    		return intMsgUtil.invoke("cli_qry_chkCertInfoForInstall", msgHeader, msgBody);
    	}
    	catch (Exception e)
    	{
    		logger.error("У��֤���¿�������ʧ�ܣ�",e);
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "У��֤���¿��������쳣");
    	}
    }
    
    /** 
     * ����ѡ�Ź����ѯ�ֻ������б�
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param orgId ��֯����
     * @param fitmod ѡ�Ź���
     * @param mainProdid �����ƷID
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-4 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap qryTelNumberListByRule(MsgHeaderPO msgHeader, String orgId, String fitmod, String mainProdid)
    {
        try
        {
        	Document msgBody = DocumentHelper.createDocument();
        	Element eBody = msgBody.addElement("message_content");
        	
        	// ��֯����id
        	DocumentUtil.addSubElementToEle(eBody, "qryOrgId", orgId);
        	
        	// �����������
        	DocumentUtil.addSubElementToEle(eBody, "fitmod", fitmod);
        	
        	String maxCount = "100";
        	if (CommonUtil.isNotEmpty(Constants.SH_TELNUM_MAXCOUNT))
        	{
        		maxCount = CommonUtil.getParamValue(Constants.SH_TELNUM_MAXCOUNT);
        	}
        	
        	// ��ȡѡ�ŵ��������(1-2000)������2000
        	DocumentUtil.addSubElementToEle(eBody, "maxCount", maxCount);
        	
        	// �Ƿ��ѯ��ʵ�忨���ֻ���
        	DocumentUtil.addSubElementToEle(eBody, "bundleimsi", "0");
        	
        	// �ֻ�����Դ����
        	DocumentUtil.addSubElementToEle(eBody, "telType", "rsclTgsm");
        	
        	return intMsgUtil.invoke("cli_qry_qryAvlTelNum", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("����ѡ�Ź����ѯ�ֻ������б��쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"����ѡ�Ź����ѯ�ֻ������б��쳣");
        }
    }
    
    /**
     * ������Դ��ѡ�ӿ�
     * 
     * @param msgHeader ��Ϣͷ
     * @param telnum �ֻ���
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
	public ReturnWrap telNumTmpPick(MsgHeaderPO msgHeader, String telnum)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			//modify begin by cwx456134 2017-05-08 OR_huawei_201704_404_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����2 
			if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_LOCKORUNLOCKTELNUM))
			{
			    // �ֻ���
                DocumentUtil.addSubElementToEle(eBody, "TELNUM", telnum);
                
                // ������־ 0Ϊ������1Ϊ����
                DocumentUtil.addSubElementToEle(eBody, "OPERFLAG", "0");
			}
			else
			{
	            // �ֻ���
	            DocumentUtil.addSubElementToEle(eBody, "msisdn", telnum);
	            
	            // ��Դ���ͣ�reclTgsm
	            DocumentUtil.addSubElementToEle(eBody, "resType", "rsclTgsm");
	            
	            // ������־ 0Ϊ������1Ϊ����
	            DocumentUtil.addSubElementToEle(eBody, "operFlag", "0");
			}
            //modify end by cwx456134 2017-05-08 OR_huawei_201704_404_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����2
			
			// ���ýӿ�
			return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_LOCKORUNLOCKTELNUM, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("�ֻ�����ѡʧ�ܣ�", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "�ֻ�����ѡ�쳣");
		}
	}
	
	/**
     * У��հ׿���Դ�Ƿ����
     * 
     * @param msgHeader ��Ϣͷ
     * @param curMenuid ��ǰ�˵�ID
     * @param blankNo �հ׿����к�
     * @param prodid �����Ʒ
     * @param recType �������ͣ�����Install������ChangeEnum	
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
	public ReturnWrap chkBlankNo(MsgHeaderPO msgHeader, TerminalInfoPO termInfo, String blankNo, String prodid, String recType)
	{
		try 
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// ����
            DocumentUtil.addSubElementToEle(eBody, "region", termInfo.getRegion());
            
            // ��֯����
            DocumentUtil.addSubElementToEle(eBody, "orgid", termInfo.getOrgid());
            
            // �հ׿���
            DocumentUtil.addSubElementToEle(eBody, "blankCardNo", blankNo);
            
            // �������
            DocumentUtil.addSubElementToEle(eBody, "changeType", recType);
            
            // ��������
            DocumentUtil.addSubElementToEle(eBody, "recType", recType);
            
            // �����Ʒ
            DocumentUtil.addSubElementToEle(eBody, "prodid", prodid);
            
			return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_CHECKBLANKCARD, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("У��հ׿���Դ�Ƿ����ʧ�ܣ�", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "У��հ׿���Դ�Ƿ�����쳣");
		}
	}
	
	/**
     * У��հ׿��Ƿ���Ԥ�ÿտ�
     * 
     * @param msgHeader ��Ϣͷ
     * @param blankNo �հ׿����к�
     * @param telNum �ֻ���
     * @param recType �������ͣ�����Install������ChangeEnum	
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
	public ReturnWrap chkPreSetBlankCard(MsgHeaderPO msgHeader, String blankNo, String telNum, String recType)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// �հ׿����к�
			DocumentUtil.addSubElementToEle(eBody, "blankCardSN", blankNo);
			
			// �տ����к�
			DocumentUtil.addSubElementToEle(eBody, "blankCardSeq", blankNo);
			
			// ��������
			DocumentUtil.addSubElementToEle(eBody, "recType", recType);
			
			// �ֻ�����
			DocumentUtil.addSubElementToEle(eBody, "serverNum", telNum);
			
			// ����
			DocumentUtil.addSubElementToEle(eBody, "region", msgHeader.getRegion());
			
			// �Ƿ��ȡ��Ȩ���� ����
			DocumentUtil.addSubElementToEle(eBody, "retPresetData", "");
			
			return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_CHKISPRESETBLANKCARD, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("У��հ׿��Ƿ���Ԥ�ÿտ�ʧ�ܣ�", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "У��հ׿��Ƿ���Ԥ�ÿտ��쳣");
		}
	}
	
	/**
     * �������
     * 
     * @param msgHeader ��Ϣͷ
     * @param telnum �ֻ���
     * @param tpltTempletPO ģ��po
     * @param simInfoPO sim��po
     * @param blankno �հ׿����к�
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
	public ReturnWrap reckonRecFee(MsgHeaderPO msgHeader, ProdTempletPO tpltTempletPO, SimInfoPO simInfoPO, String blankno)
	{
		try 
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// ��������
			DocumentUtil.addSubElementToEle(eBody, "installTelnum", msgHeader.getTelNum());
			
			// �����Ʒ
			DocumentUtil.addSubElementToEle(eBody, "mainProdid", tpltTempletPO.getMainProdId());
		
			// ģ�����
			DocumentUtil.addSubElementToEle(eBody, "prodTempletid", tpltTempletPO.getTempletId());
			
			// sim����(ICCID)
			DocumentUtil.addSubElementToEle(eBody, "simNum", simInfoPO.getOldiccid());
			
			// �հ׿���
			DocumentUtil.addSubElementToEle(eBody, "blankCardNo", blankno);
			
			// �Ƿ񷵻ؼ������ ��1
			DocumentUtil.addSubElementToEle(eBody, "retDiscountFee", "1");
			//add by lWX298507 OR_huawei_201704_376_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����1 2017-5-8 begin
			if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_QRYRECFEEFORINSTALL))
            {
                DocumentUtil.addSubElementToEle(eBody, "REGION", tpltTempletPO.getRegion());
            }
			
			ReturnWrap rw = intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_QRYRECFEEFORINSTALL, msgHeader, msgBody);
			if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_QRYRECFEEFORINSTALL))
			{
			    Vector vector = (Vector)rw.getReturnObject();
		        CRSet crSet =(CRSet) vector.elementAt(1);
		        rw.setReturnObject(crSet);
		        return rw;
			}
			//add by lWX298507 OR_huawei_201704_376_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����1 2017-5-8 end
			return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_QRYRECFEEFORINSTALL, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("�������ʧ�ܣ�", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��������쳣");
		}
	}
	
	/**
     * ����д���������հ׿���Դ��ѡ�ͻ�ȡ��������
     * 
     * @param msgHeader ��Ϣͷ
     * @param blankno �հ׿����к�
     * @param recType �������ͣ�����Install������ChangeEnum
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
	public ReturnWrap getEncryptedData(MsgHeaderPO msgHeader, String blankNo, String recType, String region)
	{
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// Ԥ�ÿտ����к�
			DocumentUtil.addSubElementToEle(eBody, "blankCardSN", blankNo);
			
			// �ֻ���
			DocumentUtil.addSubElementToEle(eBody, "serverNum", msgHeader.getTelNum());
			
			// ����
			DocumentUtil.addSubElementToEle(eBody, "region", region);
			
			// ��������
			DocumentUtil.addSubElementToEle(eBody, "recType", recType);
			
			// ����Ա 
			DocumentUtil.addSubElementToEle(eBody, "operId", msgHeader.getOperId());
			
			return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_GENWRITECARDDATAENCRYPT, msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("����д��ʧ�ܣ�", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "����д���쳣");
		}
	}
	
	/**
     * д���ɹ�ʧ�ܽӿ�
     * 
     * @param msgHeader ��Ϣͷ
     * @param blankno �հ׿����к�
     * @param simInfoPO sim����Ϣ
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-14 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
	public ReturnWrap updateWriteCardResult(MsgHeaderPO msgHeader, String blankno, SimInfoPO simInfoPO)
	{
		try 
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// �հ׿����к�
			DocumentUtil.addSubElementToEle(eBody, "blankCardNo", blankno);
			
			// iccid
			DocumentUtil.addSubElementToEle(eBody, "iccID", simInfoPO.getIccid());
			
			// imsi
			DocumentUtil.addSubElementToEle(eBody, "imsi", simInfoPO.getImsi());
			
			// ���ջ�imsi����
			DocumentUtil.addSubElementToEle(eBody, "secImsi", simInfoPO.getImsi());
			
			// �ɹ�����д���ɹ�����ʧ��д������Ϣ
			DocumentUtil.addSubElementToEle(eBody, "errMsg", simInfoPO.getErrMsg());

			// �ɹ���0��ʧ��дʧ�ܴ�����
			DocumentUtil.addSubElementToEle(eBody, "errCode", simInfoPO.getErrCode());
		
			// �Ƿ�д���ɹ�1 �ɹ�  -1 ʧ��
			DocumentUtil.addSubElementToEle(eBody, "iswSus", simInfoPO.getWriteResult());
			
			// ����Ա
			DocumentUtil.addSubElementToEle(eBody, "operId", msgHeader.getOperId());
			
			return intMsgUtil.invoke("cli_qry_writeBlankCardFail", msgHeader, msgBody);
		}
		catch (Exception e)
		{
			logger.error("���Ͽ�ʧ�ܣ�", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "���Ͽ��쳣");
		}
	}
	
	/**
     * �����ύ
     * 
     * @param msgHeader ��Ϣͷ
     * @param simInfoPO sim����Ϣ
     * @param tpltTempletPO ģ����Ϣ
     * @param idCardPO �û�������Ϣ
     * @param totalfee �ܷ���
     * @param passwd ��������
     * @param telnum �ֻ�����
     * @param bankNo ���б��
     * @param bankNbr ���нɷ���ˮ��
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-1-15 OR_SD_201407_1069_ɽ��_�����������ն����ӿ������������ܵ�����
     */
    public ReturnWrap terminalInstall(MsgHeaderPO msgHeader, SimInfoPO simInfoPO, ProdTempletPO tpltTempletPO,
        IdCardPO idCardPO, String totalfee, String passwd, String telnum, String bankNo, String bankNbr)
    {
		try
		{
			Document msgBody = DocumentHelper.createDocument();
			Element eBody = msgBody.addElement("message_content");
			
			// ����
			DocumentUtil.addSubElementToEle(eBody, "ACCESSTYPE", Constants.CHANNEL_ID);
			
			// ��Ʒģ��
			DocumentUtil.addSubElementToEle(eBody, "templateId", tpltTempletPO.getTempletId());
			
			// �����Ʒ
			DocumentUtil.addSubElementToEle(eBody, "mainProd", tpltTempletPO.getMainProdId());
			
			// �¿�������
			DocumentUtil.addSubElementToEle(eBody, "telnum", telnum);
			
			// ��ҵ������
			DocumentUtil.addSubElementToEle(eBody, "subRecType", "SELFCVSINSTALL");
			
			// �հ׿���
			DocumentUtil.addSubElementToEle(eBody, "blankCardNo", simInfoPO.getBlankno());
			
			// imsi
			DocumentUtil.addSubElementToEle(eBody, "newImsi", simInfoPO.getImsi());
			
			// iccid
			DocumentUtil.addSubElementToEle(eBody, "newIccid", simInfoPO.getIccid());
			
			// �ܷ���
			DocumentUtil.addSubElementToEle(eBody, "totalPrice", totalfee);
			
			// ����д����֤�� ��־(��1)
			DocumentUtil.addSubElementToEle(eBody, "noCardCheckFlag", "1");
			
			// ֤��ID
			DocumentUtil.addSubElementToEle(eBody, "certid", idCardPO.getIdCardNo());
			
			// ֤������
			DocumentUtil.addSubElementToEle(eBody, "certtype", "IdCard");
			
			// �ͻ�����
			DocumentUtil.addSubElementToEle(eBody, "custname", idCardPO.getIdCardName());
			
			// ��ϵ�绰
			DocumentUtil.addSubElementToEle(eBody, "linkphone", "");
			
			// ��ϵ��ַ
			DocumentUtil.addSubElementToEle(eBody, "linkaddr", idCardPO.getIdCardAddr());
			
			// ֤����ַ
			DocumentUtil.addSubElementToEle(eBody, "certaddr", idCardPO.getIdCardAddr());
			
			// �û�����
			DocumentUtil.addSubElementToEle(eBody, "passwd", passwd);
			
			// add begin zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
			// ���к�
            DocumentUtil.addSubElementToEle(eBody, "agentunit", bankNo);
            
            // Ψһ��ˮ���ն˻����ص�termseq
            DocumentUtil.addSubElementToEle(eBody, "agentformnum", bankNbr);
            
            // ҵ������(������ZZKH ������ZZBK Ԥ������ZZHD)
            DocumentUtil.addSubElementToEle(eBody, "accepttype", Constants.ACCEPTTYPE_CARDINSTALL);
            
            // �ɷ����ڣ���ʽ�� YYYYMMDDHH24MISS
            DocumentUtil.addSubElementToEle(eBody, "paydate", DateUtil._getCurrentTime());
			// add end zKF69263 2015-5-8 OR_SD_201503_333_SD_�����ն˻����Ԥ������
			
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_SELFINSTALL))
            {
                ReturnWrap rw = intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_SELFINSTALL, msgHeader, msgBody);
                if (rw.getStatus() == SSReturnCode.ERROR)
                {
                    throw new ReceptionException(rw.getReturnMsg());
                }
                
                CTagSet v = (CTagSet)rw.getReturnObject();
                
                
                DocumentUtil.addSubElementToEle(eBody, "INSTALL_FORMNUM", v.GetValue("FORMNUM"));
                DocumentUtil.addSubElementToEle(eBody, "SERVNUM", telnum);
                DocumentUtil.addSubElementToEle(eBody, "PAY_DATE", DateUtil._getCurrentTime());
                intMsgUtil.invoke(BusinessIdConstants.WRITEAGENTCHARGEZZZD, msgHeader, msgBody);
                return rw;
                
            }
            else
            {
                // ���ýӿ�
                return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_SELFINSTALL, msgHeader, msgBody);
            }
            
		}
		catch (Exception e)
		{
			logger.error("�����ύʧ��", e);
			return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "�����ύ�쳣");
		}
	}
	

    
    /**
     * <��ѯ�û���ԤԼ�ĺ����б�>
     * <������ϸ����>
     * @param msgHeader
     * @param Document doc
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-6-9 OR_SD_201505_489_����������ԤԼѡ�Ź���
     */
    public ReturnWrap qryBookedTelnum(MsgHeaderPO msgHeader, Document doc)
    {
        try
        {
            return intMsgUtil.invoke("cli_qry_bookedTelnum", msgHeader, doc);
        }
        catch (Exception e)
        {
            logger.error("��ѯ��ԤԼ���ֻ�����ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    

    /**
	 * ɽ���мۿ�����ӿ�
	 * @param header
	 * @param doc
	 * @return
	 * @remark create by wWX217192 2015-06-17 OR_SD_201505_1022_ɽ��_ɽ�����ӳ�ֵ����������_�����ն˸���
	 */
	public ReturnWrap buyValueCard(MsgHeaderPO header, Document doc)
	{
		try
		{
			return getIntMsgUtil().invoke("cli_qry_elecCardSale", header, doc);
		}
		 catch (Exception e)
        {
            logger.error("��ؽɷ��쳣!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"��ؽɷ��쳣��");
        }
	}
}