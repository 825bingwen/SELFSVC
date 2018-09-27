package com.customize.hub.selfsvc.call.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.dubbo.common.json.ParseException;
import com.customize.hub.selfsvc.call.SelfSvcCallHub;
import com.customize.hub.selfsvc.cardbusi.model.SimInfoPO;
import com.customize.hub.selfsvc.common.DateUtilHub;
import com.customize.hub.selfsvc.invoice.model.CyclePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.call.IntMsgUtilNew;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DocumentUtil;

public class SelfSvcCallHubImpl implements SelfSvcCallHub
{
	// modify begin zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
    //private static final Logger log = Logger.getLogger(SelfSvcCallHubImpl.class);
	private static Log log = LogFactory.getLog(SelfSvcCallHubImpl.class);
    // modify end zKF66389 2015-11-27 V200R003C10LG1101 OR_SD_201511_29_���������ն˵�log4j�汾
    
    private IntMsgUtil intMsgUtil;
    
    private IntMsgUtilNew intMsgUtilNew;
    
    public ReturnWrap qryArrear(Map paramMap)
    {
        String telNum = (String)paramMap.get("telNum");
        String region = (String)paramMap.get("region");
        
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        // ��װ�������
        
        // �ֻ�����
        Element eReq_telnum = eBody.addElement("telnum");
        eReq_telnum.addText(telNum);
        
        Element eReq_password = eBody.addElement("region");
        eReq_password.addText(region);
        
        String operID = (String)paramMap.get("operID");
        String termID = (String)paramMap.get("termID");
        
        Document docXML = intMsgUtil.createMsg(doc, "����", "", "", "1", telNum, operID, termID);
        
        return intMsgUtil.invoke(docXML);
    }
    
    /**
     * �����˻��ɷѷ�ʽ��ѯ��У���Ƿ�Ϊ�����˻���
     * 
     * @param map
     * @return
     * @remark create hWX5316476 2014-05-22 V200R003C10LG0501 OR_huawei_201405_234 �����ն˽���EBUSһ�׶�_�ɷ�
     */
    public ReturnWrap getAccSettleTypeByPayType(Map<String, String> map)
    {
        // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("PTGetAccSettleTypeByPayType"))
        {
            String operid = map.get("curOper");
            String atsvNum = map.get("atsvNum");
            String acctID = map.get("acctID");
            String touchoid = map.get("touchoid");
            String menuid = map.get("menuid");
            String region = map.get("region");
            String telnumber = map.get("telnumber");
            String payType = map.get("payType");
            
            // ���
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // �˻�id
            inParamMap.put("acctID", acctID);
            
            // ����
            inParamMap.put("region", region);
            
            // ����
            inParamMap.put("region", region);
            
            // ֧����ʽ
            inParamMap.put("payType", payType);
            
            return intMsgUtil.invokeDubbo("PTGetAccSettleTypeByPayType",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum,
                    inParamMap);
        }
        
        return null;
    }
    
    /**
     * �����ɷѲ�ѯ�ӿ�
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
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_fee_hub",
                    menuid,
                    touchoid,
                    "1",
                    servnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �����ɷѽӿ�
     * 
     * @param map
     * @return
     * @remark modify hWX5316476 2014-04-17 OR_huawei_201404_375 �����ն�ȫ���̽���EBUS����_��ֵ���� ��ѡ�ײ�
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
            String payType = (String)map.get("payType");
            String touchoid = (String)map.get("touchoid");
            String chargelogoid = (String)map.get("chargelogoid");
            
            // add begin sWX219697 2014-12-23 17:06:19 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
            String presentFee = (String)map.get("presentFee");
            String presentSubject = (String)map.get("presentSubject");
            // add end sWX219697 2014-12-23 17:06:19 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
            
            // add begin lwx439898 2017-12-13  R005C20LG2201 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
            //֧����ˮ
            String taskoid = (String)map.get("taskoid");
            // add end lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
            
            // ͳһ�ӿ�ƽ̨תEBUS����
            if (IntMsgUtil.isTransEBUS("atsvBusiChargeFee"))
            {
                // ���
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // �ֻ���
                inParamMap.put("telnum", servnumber);
                
                //֧����ˮ
                inParamMap.put("TASKOID", taskoid);
                
                // �ɷѽ��
                inParamMap.put("amount", money);
                
                // �ɷ�����
                inParamMap.put("pay_type", payType);
                
                // add begin hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 �����ն�-������������2014(�л�ΪEBUSЭ��)
                // �ɷ���ˮ
                inParamMap.put("chargeLgId", chargelogoid);
                // add end hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 �����ն�-������������2014(�л�ΪEBUSЭ��)
                
                // add begin sWX219697 2014-12-23 16:55:48 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
                // ���ͽ���λ����
                inParamMap.put("presentFee", presentFee);
                
                // ���Ϳ�Ŀ
                inParamMap.put("presentSubject", presentSubject);
                // add end sWX219697 2014-12-23 16:55:48 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
                
                
                // modify begin lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���  ��������RECOID ��PresentRecoid
                // ���μ�ֵ�� ȡֵ����������ļ�����Ӧ��ϵ���飬���presentOid�����ͻ�����ˮ��
                String[][] outParamKeyDefine = { {"dealnum", "dealtime", "presentOid", "RECOID", "PresentRecoid"},
                        {"dealNum", "dealTime", "presentOid", "RECOID", "PresentRecoid"}};
                // modify end lwx439898 2017-12-13 R005C20LG2201 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ��� ��������RECOID ��PresentRecoid
                
                return intMsgUtil.invokeDubbo("atsvBusiChargeFee",
                        menuid,
                        touchoid,
                        "1",
                        servnumber,
                        operid,
                        termid,
                        inParamMap,
                        outParamKeyDefine);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            // ���
            Element eReq_amount = eBody.addElement("amount");
            eReq_amount.addText(money);
            
            // ��������
            Element eReq_payType = eBody.addElement("pay_type");
            eReq_payType.addText(payType);
            
            // add begin jWX216858 2014-07-14 DR_HUB_201405_887_�����ն�-������������2014
            // �ɷ���־��ˮ
            Element eReq_chargelogoid = eBody.addElement("chargelogoid");
            eReq_chargelogoid.addText(chargelogoid);
            // add end jWX216858 2014-07-14 DR_HUB_201405_887_�����ն�-������������2014
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_chargefee_hub",
                    menuid,
                    touchoid,
                    "1",
                    servnumber,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("�ɷѽӿڳ����쳣��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ���±� ar_bank_task �� recoid
     * @param termInfo
     * @param currNumber
     * @param curMenuId
     * @param taskoid
     * @param recoid
     * @param status
     * @return
     * @remark create by lwx439898 2017-10-16 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
     */
    public ReturnWrap updateBankRecoid(TerminalInfoPO termInfo,String currNumber,String curMenuId,String taskoid,String recoid,String status)
    {
        String operid = termInfo.getOperid();
        String termid = termInfo.getTermid();
        
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        try
        {
            Element eReq_taskoid = eBody.addElement("taskoid");
            eReq_taskoid.addText(taskoid);
            Element eReq_recoid = eBody.addElement("recoid");
            eReq_recoid.addText(recoid);
            Element eReq_status = eBody.addElement("status");
            eReq_status.addText(status);
            //����
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(termInfo.getRegion());
            Element eReq_accesstype = eBody.addElement("accesstype");
            eReq_accesstype.addText("bsacAtsv");
            
            //����ͳһƽ̨תEBUS����
            if (IntMsgUtil.isTransEBUS("BLARUpdateRecStatus"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                inParamMap.put("REGION", termInfo.getRegion());
                inParamMap.put("TASKOID", taskoid);
                inParamMap.put("RECOID", recoid);
                inParamMap.put("RECSTATUS", "1");
                inParamMap.put("BANKSTATUS", "1");
                
                return intMsgUtil.invokeDubbo("BLARUpdateRecStatus",
                        curMenuId,
                        "",
                        "1",
                        currNumber,
                        operid,
                        termid,
                        inParamMap);               
            }
                        
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_update_bankrecoid",
                    curMenuId,
                    "",
                    "1",
                    currNumber,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML); 
                    
        }
        catch (Exception e)
        {
            log.error("����֧������ʧ�ܣ�", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ����֧������ӿ�
     * 
     * @param customerSimp
     * @param curMenuId
     * @param termInfo
     * @param isTelNum
     * @param currNumber
     * @param selfPayLog
     * @return
     * @remark create by lwx439898 2017-10-16 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
     */
    
    public ReturnWrap createPayCenterTrans(NserCustomerSimp customerSimp,String curMenuId,TerminalInfoPO termInfo, String isTelNum, String currNumber,
            Map<String, String> selfPayLog)
    {
        try
        {
            //��ȡ����Աid
            String operid = termInfo.getOperid();
            //��ȡ�ն�ID
            String termid = termInfo.getTermid();
                             
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");  
            //����
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(termInfo.getRegion());
            //�ֻ���
            Element eReq_servnumber = eBody.addElement("telnum");
            eReq_servnumber.addText(currNumber);
            //���д���
            Element eReq_bankCode = eBody.addElement("bankcode");
            eReq_bankCode.addText(selfPayLog.get("bankCode"));
            
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            String payDate = sdf1.format(new Date());            
            //����ʱ��
            Element eReq_transtime = eBody.addElement("transtime");
            eReq_transtime.addText(payDate);            
            //��������
            Element eReq_relatetaskoid = eBody.addElement("relatetaskoid");
            eReq_relatetaskoid.addText("");   
            //֧������
            Element eReq_paytype = eBody.addElement("paytype");
            eReq_paytype.addText(selfPayLog.get("Paytype"));
            //��������
            Element eReq_exchangetype = eBody.addElement("exchangetype");
            eReq_exchangetype.addText(selfPayLog.get("exChangeType"));
            //���׽��
            Element eReq_payamount = eBody.addElement("payamount");
            eReq_payamount.addText(selfPayLog.get("Payamount"));
            //���д���״̬ 0��δ����1������ɹ��� 9������ʧ��
            Element eReq_bankstatus = eBody.addElement("bankstatus");
            eReq_bankstatus.addText("0");
            //ҵ����״̬��0δ���� 1����ɹ� 8����ֵ������ 9����ʧ��
            Element eReq_recstatus = eBody.addElement("recstatus");
            eReq_recstatus.addText("0");
            //����״̬�� 0 δ���� 1���˳ɹ� 2���г��� 3 ���ж̿�  4 ��һ��
            Element eReq_checkstatus = eBody.addElement("checkstatus");
            eReq_checkstatus.addText("0");
            //�û�����
            //Element eReq_usercity = eBody.addElement("usercity");
            //eReq_usercity.addText(customerSimp.getRegionName());
            //����Ա����
            Element eReq_operatorid = eBody.addElement("operatorid");
            eReq_operatorid.addText(operid);
            //��ǰҵ������
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText(selfPayLog.get("RecType"));
            //���н�����ˮ��
            Element eReq_bankseqno = eBody.addElement("bankseqno");
            eReq_bankseqno.addText("");           
            //��������
            Element eReq_accesstype = eBody.addElement("accesstype");
            eReq_accesstype.addText(selfPayLog.get("AccessType"));
            
            //����ͳһƽ̨תEBUS����
            if (IntMsgUtil.isTransEBUS("BLARPayOrderBuild"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                inParamMap.put("Telnum", currNumber);
                inParamMap.put("AccessType", selfPayLog.get("AccessType"));
                inParamMap.put("Paytype", selfPayLog.get("Paytype"));
                inParamMap.put("Payamount", selfPayLog.get("Payamount"));
                inParamMap.put("exChangeType", selfPayLog.get("exChangeType"));
                inParamMap.put("relateTaskoid", "");
                inParamMap.put("RecType", selfPayLog.get("RecType"));
                inParamMap.put("bankCode", selfPayLog.get("bankCode"));
                
                String[][] outParamKeyDefine = {{"TASKOID"},{"TASKOID"}};
                
                return intMsgUtil.invokeDubbo("BLARPayOrderBuild",
                        curMenuId,
                        "",
                        "1",
                        currNumber,
                        operid,
                        termid,
                        inParamMap,
                        outParamKeyDefine);               
            }

            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_create_payments",
                    curMenuId,
                    "",
                    "1",
                    currNumber,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML); 
                    
        }
        catch (Exception e)
        {
            log.error("����֧������ʧ�ܣ�", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    } 
    
    /**
     * ��ѯ֧������
     * @param termInfo
     * @param taskoid
     * @param currNumber
     * @param curMenuId
     * @return
     * @remark create by lwx439898 2017-10-16 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
     */
    public ReturnWrap qryPayChargeInfo(TerminalInfoPO termInfo, String taskoid, String currNumber, String curMenuId)
    {
        String operid = termInfo.getOperid();
        String termid = termInfo.getTermid();
        
        try
        {
            Document doc = DocumentHelper.createDocument();           
            Element eBody = doc.addElement("message_content");
            
            //������ˮ
            Element eReq_taskoid = eBody.addElement("taskoid");
            eReq_taskoid.addText(taskoid);
            
            //����
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(termInfo.getRegion());
            
            //����ת��д��ʶ
            Element eReq_useupperkey = eBody.addElement("useupperkey");
            eReq_useupperkey.addText("1");
            
            //����ͳһƽ̨תEBUS����
            if (IntMsgUtil.isTransEBUS("BLARQryArBankTaskByTaskOid"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                inParamMap.put("taskoid", taskoid);
                inParamMap.put("region", termInfo.getRegion());
                
                String[][] outParamKeyDefine = { {"RESULT_INTF","bankstatus","rollstatus","payType","exChangeType","relateTaskOid"}
                ,{"RESULT_INTF","BANKSTATUS","rollstatus","PAYTYPE","EXCHANGETYPE","relateTaskOid"}};
               
                return intMsgUtil.invokeDubbo("BLARQryArBankTaskByTaskOid",
                        curMenuId,
                        "",
                        "1",
                        currNumber,
                        operid,
                        termid,
                        inParamMap,
                        outParamKeyDefine);               
            }
                     
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_paytranstion",
                    curMenuId,
                    "",
                    "1",
                    currNumber,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("��ѯ֧�������쳣��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
 
    /**
     * ��������֧��״̬
     * @param payTransMsg
     * @param payCenterParam
     * @param termInfo
     * @param curMenuId
     * @param currNumber
     * @return
     * @remark create by lwx439898 2017-10-16 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
     */
    public ReturnWrap updateBankPaymentStatus(String payCenterParamInfo,String respMsg,String payTransMsg,TerminalInfoPO termInfo,String curMenuId,String currNumber)
    {
        String operid = termInfo.getOperid();
        String termid = termInfo.getTermid();

        try
        {
            Document doc = DocumentHelper.createDocument();           
            Element eBody = doc.addElement("message_content");
            //������ˮ
            Element eReq_taskoid = eBody.addElement("taskoid");
            eReq_taskoid.addText(payTransMsg);
            //����
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(termInfo.getRegion());
            //����״̬
            Element eReq_bankstatus = eBody.addElement("bankstatus");
            eReq_bankstatus.addText("0");
            //������
            Element eReq_requestinfo = eBody.addElement("requestinfo");
            eReq_requestinfo.addText(payCenterParamInfo);
            //��Ӧ����
            Element eReq_responseinfo = eBody.addElement("responseinfo");
            eReq_responseinfo.addText(respMsg);
            
            Element eReq_isordercommit = eBody.addElement("isordercommit");
            eReq_isordercommit.addText("0");
            
            //����ͳһƽ̨תEBUS����
            if (IntMsgUtil.isTransEBUS("BLARUpdateBankStatus"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                inParamMap.put("RESPONSEINFO", respMsg);
                inParamMap.put("PAYTYPEPARAM", "PayCenter");
                inParamMap.put("REQUESTINFO", payCenterParamInfo);
                inParamMap.put("TASKOID", payTransMsg);
                inParamMap.put("BANKSTATUS", "0");
                inParamMap.put("isOrderCommit", "0");
                inParamMap.put("REGION", termInfo.getRegion());
                
                return intMsgUtil.invokeDubbo("BLARUpdateBankStatus",
                        curMenuId,
                        "",
                        "1",
                        currNumber,
                        operid,
                        termid,
                        inParamMap);               
            }
          
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_update_bankpayments",
                    curMenuId,
                    "",
                    "1",
                    currNumber,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("��������֧��״̬�쳣��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
      
    /**
     * ���������������ն��ֽ�ɷѽӿ� <������ϸ����>
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chargeCommitByAgent(Map map)
    {
        try
        {
            // ��ȡ����ԱID
            String operid = (String)map.get("operid");
            
            // ��ȡ�ն�ID
            String termid = (String)map.get("termid");
            
            // ��ȡ��ǰ�˵�id
            String menuid = (String)map.get("menuid");
            
            // ��ȡ�ֻ���
            String servnumber = (String)map.get("servnumber");
            
            // ��ȡ�ɷѽ��
            String money = (String)map.get("money");
            
            // ��ȡ�ɷ�����
            String payType = (String)map.get("payType");
            
            // ��ȡ�ͻ��Ӵ�id
            String touchoid = (String)map.get("touchoid");
            
            // ��ȡҵ�����
            String businessid = (String)map.get("businessid");
            
            // ��ȡ��������֯�ṹ����
            String orgid = (String)map.get("orgid");
            
            // ��ȡ��Χ��ˮID
            String recoid = (String)map.get("recoid");
            
            // ��ȡ����ҵ�������ID
            String plattype = (String)map.get("plattype");
            
            // add begin hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 �����ն�-������������2014(�л�ΪEBUSЭ��)
            // �ɷ���ˮ��
            String chargelogoid = (String)map.get("chargelogoid");
            // add end hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 �����ն�-������������2014(�л�ΪEBUSЭ��)
            
            // add begin sWX219697 2014-12-23 17:06:19 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
            String presentFee = (String)map.get("presentFee");
            String presentSubject = (String)map.get("presentSubject");
            // add end sWX219697 2014-12-23 17:06:19 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
            
            // ͳһ�ӿ�ƽ̨תEBUS����
            if (IntMsgUtil.isTransEBUS("BLDeductAcctBalance"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // ҵ�����
                inParamMap.put("BUSINESSID", businessid);
                
                // �����̱���ID
                inParamMap.put("CHANNELID", orgid);
                
                // ���׽��
                inParamMap.put("TRADEAMOUNT", money);
                
                // ��Χ��ˮ
                inParamMap.put("RECOID", recoid);
                
                // �û��ֻ���
                inParamMap.put("SERVNUMBER", servnumber);
                
                // ����ҵ�������ID
                inParamMap.put("PLATTYPE", plattype);
                
                // �ֻ�����
                inParamMap.put("telnum", servnumber);
                
                // ���
                inParamMap.put("amount", money);
                
                // ��������
                inParamMap.put("pay_type", payType);
                
                // modify begin hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 �����ն�-������������2014(�л�ΪEBUSЭ��)
                // �ɷ���ˮ
                inParamMap.put("chargeLgId", chargelogoid);
                // modify end hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 �����ն�-������������2014(�л�ΪEBUSЭ��)
                
                // add begin sWX219697 2014-12-23 16:55:48 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
                // ���ͽ���λ����
                inParamMap.put("presentFee", presentFee);
                
                // ���Ϳ�Ŀ
                inParamMap.put("presentSubject", presentSubject);
                // add end sWX219697 2014-12-23 16:55:48 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
                
                // ���μ�ֵ�� ȡֵ����������ļ�����Ӧ��ϵ����
                String[][] outParamKeyDefine = { {"dealnum", "dealtime", "presentOid"},
                        {"dealNum", "dealTime", "presentOid"}};
                
                return intMsgUtil.invokeDubbo("BLDeductAcctBalance",
                        menuid,
                        touchoid,
                        "1",
                        servnumber,
                        operid,
                        termid,
                        inParamMap,
                        outParamKeyDefine);
                
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ҵ�����(��Ӧ��Ŀ)
            Element eReq_businessid = eBody.addElement("businessid");
            eReq_businessid.addText(businessid);
            
            // �����̱���ID
            Element eReq_channelid = eBody.addElement("channelid");
            eReq_channelid.addText(orgid);
            
            // ���׽��
            Element eReq_tradeamount = eBody.addElement("tradeamount");
            eReq_tradeamount.addText(money);
            
            // ��Χ��ˮ
            Element eReq_recoid = eBody.addElement("recoid");
            eReq_recoid.addText(recoid);
            
            // �û��ֻ���
            Element eReq_servnumber = eBody.addElement("servnumber");
            eReq_servnumber.addText(servnumber);
            
            // ����ҵ�������ID
            Element eReq_plattype = eBody.addElement("plattype");
            eReq_plattype.addText(plattype);
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(servnumber);
            
            // ���
            Element eReq_amount = eBody.addElement("amount");
            eReq_amount.addText(money);
            
            // ��������
            Element eReq_payType = eBody.addElement("pay_type");
            eReq_payType.addText(payType);
            
            // add begin hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 �����ն�-������������2014(�л�ΪEBUSЭ��)
            // �ɷ���ˮ��
            eBody.addElement("chargelogoid").addText(chargelogoid);
            // add end hWX5316476 2014-08-26 V200R003C10LG0801 OR_huawei_201408_934 �����ն�-������������2014(�л�ΪEBUSЭ��)
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_agentaccchargeback_hub",
                    menuid,
                    touchoid,
                    "1",
                    servnumber,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("���������������ն��ֽ�ɷѽӿ��쳣��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ����ȡϵͳ�����ӿ�
     * 
     * @param map
     * @return
     */
    public ReturnWrap getParamValue(Map map)
    {
        try
        {
            String operid = (String)map.get("operid");
            String termid = (String)map.get("termid");
            String menuid = (String)map.get("menuid");
            String region = (String)map.get("region");
            String paramid = (String)map.get("paramid");
            String touchoid = (String)map.get("touchoid");
            
            // ͳһ�ӿ�ƽ̨תEBUS����
            if (IntMsgUtil.isTransEBUS("PTGetParameterByID"))
            {
                // ���
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // ����ID
                inParamMap.put("paramID", paramid);
                
                // ���μ�ֵ�� ȡֵ����������ļ�����Ӧ��ϵ����
                String[][] outParamKeyDefine = { {"paramValue"}, {"paramvalue"}};
                
                return intMsgUtil.invokeDubbo("PTGetParameterByID",
                        menuid,
                        touchoid,
                        "0",
                        region,
                        operid,
                        termid,
                        inParamMap,
                        outParamKeyDefine);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ����ID
            Element eReq_paramid = eBody.addElement("paramid");
            eReq_paramid.addText(paramid);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_parametervaluebyid_hub",
                    menuid,
                    touchoid,
                    "0",
                    region,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
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
        String smsContent = "1" + (String)map.get("smsContent");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("PTSendSmsInfo"))
            {
                // ���
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // ����ģ����
                inParamMap.put("TEMPLATENO", "Atsv0001");
                
                // �ֻ�����
                inParamMap.put("TELNUM", telnum);
                
                // �����б�
                inParamMap.put("1", (String)map.get("smsContent"));
                
                return getIntMsgUtil().invokeDubbo("PTSendSmsInfo",
                        menuID,
                        touchOID,
                        "1",
                        telnum,
                        operID,
                        termID,
                        inParamMap);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ����ģ����
            Element eReq_templateno = eBody.addElement("templateno");
            eReq_templateno.addText("Atsv0001");
            
            // �����б�
            Element eReq_smsparam = eBody.addElement("smsparam");
            eReq_smsparam.addText(smsContent);
            
            // �Ƿ��������
            Element eReq_isrvcall = eBody.addElement("isrvcall");
            eReq_isrvcall.addText("1");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_sendsmsinfo",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ȡ��Ʊ��Ϣ
     * 
     * @param map
     * @return
     * @see
     */
    public ReturnWrap getInvoiceData(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String formnum = (String)map.get("formnum");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // ͳһ�ӿ�ƽ̨תEBUS����
            if (IntMsgUtil.isTransEBUS("Atsv_Qry_InvoiceData_Hub"))
            {
                // ��������ͷ��Ϣ
                // �ն˻�
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, operID, termID, "", MsgHeaderPO.ROUTETYPE_TELNUM,
                        telnum);
                
                // ���
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // �绰����
                inParamMap.put("telnum", telnum);
                
                // formnum
                inParamMap.put("formnum", formnum);
                
                return intMsgUtil.invokeDubbo("Atsv_Qry_InvoiceData_Hub", msgHeader, inParamMap);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ������ˮ
            Element eReq_smscont = eBody.addElement("formnum");
            eReq_smscont.addText(formnum);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_invoice_hub",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ���˵���ѯ
     */
    public ReturnWrap qryMonthBill(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String month = (String)map.get("month");
        String type = (String)map.get("type");
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
            Element eReq_month = eBody.addElement("month");
            eReq_month.addText(month);
            
            // ���ȼ�
            Element eReq_type = eBody.addElement("type");
            eReq_type.addText(type);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_detailedbill_hub",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
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
            
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("nkfqoverage139Mail"))
            {
                // ���
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // �ֻ���
                inParamMap.put("telnum", telnumber);
                
                // 0�����Ͷ��ţ�ֱ��ͨ���������ؽ�� 1���Ͷ��� 2���ض�������
                inParamMap.put("outflag", "0");
                
                // 0�������ʼ� 1�����ʼ�
                inParamMap.put("isSendMail", "0");
                
                // ���μ�ֵ�� ȡֵ����������ļ�����Ӧ��ϵ���� receiveMoney Ӧ�ɻ���
                String[][] outParamKeyDefine = {
                        {"balance", "cashBalance", "cardBalance", "monDeduction", "presentBalance",
                                "monpresentBalance", "dkBalance", "predkBalance", "left", "overdraft", "nowfee",
                                "totalowe", "receiveMoney"},
                        {"balance", "cashBalance", "cardBalance", "monDeduction", "presentBalance",
                                "monPresentBalance", "DKBalance", "preDKBalance", "availableBalance", "credit",
                                "realTimeFee", "hisArrears", "receiveMoney"}};
                
                return intMsgUtil.invokeDubbo("nkfqoverage139Mail",
                        menuid,
                        touchoid,
                        "1",
                        telnumber,
                        operid,
                        atsvNum,
                        inParamMap,
                        outParamKeyDefine);
            }
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_balance_hub",
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
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �����ײ���Ϣ��ѯ
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryComboInfo(Map map)
    {
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("telnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        String billcycle = (String)map.get("billcycle");
        
        
            // ���
            Map<String, Object> paramMap = new HashMap<String, Object>();
            
            // �绰����
            paramMap.put("telNum", telnumber);
            
            // ��ѯ����,YYYYMM
            paramMap.put("cycle", billcycle);
            
            // �Ƿ����ʼ���0�������ʼ���1�����ʼ�
            paramMap.put("isEmail", "0");
            paramMap.put("servType", "ALL");
            paramMap.put("reqCode", "1000");
            
            
            // ��������Dubbo����
            MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuid, operid, atsvNum, touchoid, "1", telnumber);    
            ReturnWrap rw = intMsgUtil.invokeDubbo("cli_intf_qusages", msgHeaderPO, paramMap,false);    
            
            //1:���� ���ӣ�2:���� ����3:���� �� 4��WLAN MB  5:EDUWLAN MB��6�������� MB 7.ר�������� MB  8 WLAN ���� 9 EDUWLAN ����
            Map attrTypeMap=new HashMap();
            attrTypeMap.put("1", "����");
            attrTypeMap.put("2", "����");
            attrTypeMap.put("3", "����");
            attrTypeMap.put("4", "WLAN");
            attrTypeMap.put("5", "EDUWLAN");
            attrTypeMap.put("6", "��������");
            attrTypeMap.put("7", "ר��������");
            attrTypeMap.put("8", "WLAN");
            attrTypeMap.put("9", "EDUWLAN");
            if (SSReturnCode.SUCCESS == rw.getStatus())
            {
                String json=(String)rw.getReturnObject();
                try
                {
                    com.alibaba.dubbo.common.json.JSONObject  jsonObj = (com.alibaba.dubbo.common.json.JSONObject)com.alibaba.dubbo.common.json.JSON.parse(json);
                    com.alibaba.dubbo.common.json.JSONArray  mealInfoLst=(com.alibaba.dubbo.common.json.JSONArray)jsonObj.getArray("MealInfoLst");
                    List result = new ArrayList();
                    for(int i=0;i<mealInfoLst.length();i++){
                        com.alibaba.dubbo.common.json.JSONObject _obj=(com.alibaba.dubbo.common.json.JSONObject)mealInfoLst.get(i);
                        
                        List listInner = new ArrayList();
                        String attrType=(String)_obj.get("ATTR_TYPE");
                        String attrTypeName=(String)attrTypeMap.get(_obj.get("ATTR_TYPE"));                        
                        listInner.add(attrTypeName==null?attrType:attrTypeName);
                        listInner.add(_obj.get("PRODNAME"));
                        listInner.add(_obj.get("ATTR_NAME"));
                        listInner.add(_obj.get("FREE_VALUE"));
                        listInner.add(_obj.get("USAGE_VALUE"));
                        listInner.add(_obj.get("ATTR_UNIT"));
                        result.add(listInner);
                    }
                    rw.setReturnObject(result);
                }
                catch (ParseException e)
                {
                    rw.setStatus(SSReturnCode.ERROR);
                    rw.setReturnMsg("��������ֵ�쳣");
                    e.printStackTrace();
                }                
            }
            
            return rw;
    }
    
    /**
     * ����Ʒ���ʷѼ��ѿ�ͨ���ܣ���Ʒչʾ�� <������ϸ����>
     * 
     * @param map
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryFavourable(MsgHeaderPO msgHeaderPO)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("kfQueryProductTree"))
            {
                // ���
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // �绰����
                paramMap.put("telnum", msgHeaderPO.getRouteValue());
                
                // ��������Dubbo����
                return getIntMsgUtil().invokeDubbo("kfQueryProductTree",
                        msgHeaderPO,
                        paramMap,
                        new String[] {"cdeep1", "pkgtype", "packageid", "packagename", "applydate", "enddate"});
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(msgHeaderPO.getRouteValue());
            
            return intMsgUtil.invoke("cli_qry_producttree_hub", msgHeaderPO, doc);
            
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯ��ҪԤԼ����_���� <������ϸ����>
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap phoneNumQuery(Map map)
    {
        ReturnWrap rw = null;
        
        String newVersion = (String)map.get("newVersion");
        
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String operid = (String)map.get("curOper");// ����Ա
        String termid = (String)map.get("termID");// �ն˻�ID
        String menuid = (String)map.get("curMenuId");// �˵�ID
        String region = (String)map.get("region");// ����
        
        String model = (String)map.get("model");// ����ģʽ
        
        // �°�����ѡ������
        if ("1".equals(newVersion))
        {
            String maxPrice = (String)map.get("maxPrice");
            String minPrice = (String)map.get("minPrice");
            String prodType = (String)map.get("prodType");
            String telType = (String)map.get("telType");
            String maxcount = (String)map.get("maxcount");
            String iscountry = (String)map.get("iscountry");
            String telnum = (String)map.get("telnum");
            String orgid = (String)map.get("orgid");
            
            // ��װ�������
            
            // ����ģʽ
            String encodedMode = "";
            
            try
            {
                encodedMode = URLEncoder.encode(model, "UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
                encodedMode = "model";
                
                log.error("��ѡ��ģʽ����ת��ʧ�ܣ�", e);
            }
            
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLCSQueryAvlTelNum"))
            {
                // ���
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // �����������,�Ŷ�
                paramMap.put("telModel", encodedMode);
                
                // �������Ԥ��������
                paramMap.put("maxPreFee", StringUtils.isNotEmpty(maxPrice) ? maxPrice : "1000000");
                
                // �������Ԥ��������
                paramMap.put("minPreFee", minPrice);
                
                // ��ƷƷ��
                paramMap.put("prodType", prodType);
                
                // ��������
                paramMap.put("resType", telType);
                
                // ���ؼ�¼�������������Ϊ2000
                paramMap.put("telNumCount", maxcount);
                
                // ����ʡ��һ����ŵĺ���ȫ��������,������û�к����
                paramMap.put("isCountry", iscountry);
                
                // �绰����
                paramMap.put("servNumber", telnum);
                
                // �û�ѡ��ĵ��е�orgid
                paramMap.put("qryAvlTelNumOrgid", orgid);
                
                // ��������Dubbo����//lowConsumFee modify begin liutao 2016-07-30 OR_HUB_201603_1191  �����ն���ʾ�����Ż������ŵ�ΰ��
                return getIntMsgUtil().invokeDubbo("BLCSQueryAvlTelNum",
                        menuid,
                        "",
                        "0",
                        region,
                        operid,
                        termid,
                        paramMap,
                        new String[][] { {"telInfoList"}, {"telInfoList"}},
                        new String[] {"telNum", "prepay","lowConsumFee"});
            }
            
            Element eReq_model = eBody.addElement("fitmod");
            eReq_model.addText(encodedMode);
            
            // �������Ԥ��������
            Element eReq_maxprice = eBody.addElement("maxprice");
            eReq_maxprice.addText(maxPrice);
            
            // �������Ԥ��������
            Element eReq_minprice = eBody.addElement("minprice");
            eReq_minprice.addText(minPrice);
            
            // ��ƷƷ��
            Element eReq_prdtype = eBody.addElement("prdtype");
            eReq_prdtype.addText(prodType);
            
            // ��������
            Element eReq_teltype = eBody.addElement("teltype");
            eReq_teltype.addText(telType);
            
            // ���ؼ�¼��������
            Element eReq_maxcount = eBody.addElement("maxcount");
            eReq_maxcount.addText(maxcount);
            
            // �������Ԥ��������
            Element eReq_iscountry = eBody.addElement("iscountry");
            eReq_iscountry.addText(iscountry);
            
            // ����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ��������
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            
            // �û�ѡ��ĵ��е�orgid
            Element eReq_qryavltelnumorgid = eBody.addElement("qryavltelnumorgid");
            eReq_qryavltelnumorgid.addText(orgid);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_telnum_hub_new",
                    menuid,
                    "",
                    "0",
                    region,
                    operid,
                    termid);
            rw = intMsgUtil.invoke(docXML);
        }
        else
        {
            String pur = (String)map.get("pur");// ������;(Ĭ�ϴ�"rsupSal")
            String pageIndex = (String)map.get("pageIndex");// ����ҳ��
            
            // ��װ�������
            
            // ����ģʽ=����+�Ŷ�+β��(11λ�»���)
            Element eReq_model = eBody.addElement("model");
            eReq_model.addText(model);
            
            // ������;(Ĭ�ϴ�"rsupSal")
            Element eReq_pur = eBody.addElement("pur");
            eReq_pur.addText(pur);
            
            // ����ҳ��
            Element eReq_pageIndex = eBody.addElement("pageIndex");
            eReq_pageIndex.addText(pageIndex);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_telnum_hub", menuid, "", "0", region, operid, termid);
            rw = intMsgUtil.invoke(docXML);
        }
        
        if (SSReturnCode.SUCCESS == rw.getStatus() || SSReturnCode.SUCCESS_CODE_100 == rw.getStatus())
        {
            rw.setStatus(SSReturnCode.SUCCESS);
        }
        else
        {
            rw.setStatus(SSReturnCode.ERROR);
        }
        
        return rw;
    }
    
    /**
     * ԤԼ����_���� <������ϸ����>
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap bespeakPhone(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");// ����Ա
            String termid = (String)map.get("termid");// �ն˻�ID
            String menuid = (String)map.get("curMenuId");// �˵�ID
            String region = (String)map.get("region");// ����
            
            String telnum = (String)map.get("telnum");// ��Ԥ������
            String seltelprepay = (String)map.get("seltelprepay");// Ԥ���
            String channelid = (String)map.get("channelid");// ��������, Ĭ�ϣ�"bsacAtsv"
            String CredentFlag = (String)map.get("credentFlag");// ƾ֤����(0 ��֤�룻1 ���֤��2 ��ƾ֤)
            String certtype = (String)map.get("certtype");// ֤������,Ĭ�ϣ�IdCard��û��Ϊ��
            String certid = (String)map.get("certid");// ֤������,û��Ϊ��
            
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("nkfSelNBR"))
            {
                // ���
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // �绰����
                paramMap.put("telnum", telnum);
                
                // Ԥ���
                paramMap.put("seltelprepay", seltelprepay);
                
                // ��������
                paramMap.put("channelid", channelid);
                
                // ƾ֤����(0 ��֤�룻1 ���֤��2 ��ƾ֤)
                paramMap.put("credentflag", CredentFlag);
                
                // ֤������,Ĭ�ϣ�IdCard��û��Ϊ��
                paramMap.put("certtype", certtype);
                
                // ֤������,û��Ϊ��
                paramMap.put("certid", certid);
                
                // ��������Dubbo����
                return getIntMsgUtil().invokeDubbo("nkfSelNBR", menuid, "0", "0", region, operid, termid, paramMap);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // Ԥ���
            Element eReq_seltelprepay = eBody.addElement("seltelprepay");
            eReq_seltelprepay.addText(seltelprepay);
            
            // ��������, Ĭ�ϣ�"bsacAtsv"
            Element eReq_channelid = eBody.addElement("channelid");
            eReq_channelid.addText(channelid);
            
            // ƾ֤����(0 ��֤�룻1 ���֤��2 ��ƾ֤)
            Element eReq_CredentFlag = eBody.addElement("CredentFlag");
            eReq_CredentFlag.addText(CredentFlag);
            
            // ֤������,Ĭ�ϣ�IdCard��û��Ϊ��
            Element eReq_certtype = eBody.addElement("certtype");
            eReq_certtype.addText(certtype);
            
            // ֤������,û��Ϊ��
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(certid);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_occupytel_hub",
                    menuid,
                    "0",
                    "0",
                    region,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯ����_���� <������ϸ����>
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap netNbrQuery(Map map)
    {
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String operid = (String)map.get("curOper");// ����Ա
        String termid = (String)map.get("termid");// �ն˻�ID
        String menuid = (String)map.get("curMenuId");// �˵�ID
        String region = (String)map.get("region");// ����
        
        String nettype = (String)map.get("netType");// ��������(Ĭ�ϴ�"GSM")
        String pur = (String)map.get("pur");// ������;(Ĭ�ϴ�"rsupSal")
        
        // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("atsvGetNetNbr"))
        {
            // ���
            Map<String, Object> paramMap = new HashMap<String, Object>();
            
            // ��������
            paramMap.put("nettype", nettype);
            
            // ��Դ����
            paramMap.put("pur", pur);
            
            // ��������Dubbo����
            ReturnWrap rw = getIntMsgUtil().invokeDubbo("atsvGetNetNbr",
                    menuid,
                    "",
                    "0",
                    region,
                    operid,
                    termid,
                    paramMap,
                    new String[][] { {"retDataList"}, {"retDataList"}});
            
            if (SSReturnCode.SUCCESS == rw.getStatus() && rw.getReturnObject() == null)
            {
                
                rw.setReturnObject(new CRSet());
            }
            
            return rw;
        }
        
        // ��װ�������
        
        // ��������(Ĭ�ϴ�"GSM")
        Element eReq_nettype = eBody.addElement("nettype");
        eReq_nettype.addText(nettype);
        
        // ������;(Ĭ�ϴ�"rsupSal")
        Element eReq_pur = eBody.addElement("pur");
        eReq_pur.addText(pur);
        
        Document docXML = intMsgUtil.createMsg(doc, "cli_qry_netnbr_hub", menuid, "", "0", region, operid, termid);
        ReturnWrap rw = intMsgUtil.invoke(docXML);
        
        if (SSReturnCode.SUCCESS == rw.getStatus() || SSReturnCode.SUCCESS_CODE_100 == rw.getStatus())
        {
            rw.setStatus(SSReturnCode.SUCCESS);
        }
        else
        {
            rw.setStatus(SSReturnCode.ERROR);
        }
        
        return rw;
    }
    
    /**
     * ��ѯ����_���� <������ϸ����>
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap netValueQuery(Map map)
    {
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String operid = (String)map.get("curOper");// ����Ա
        String termid = (String)map.get("termid");// �ն˻�ID
        String menuid = (String)map.get("curMenuId");// �˵�ID
        String region = (String)map.get("region");// ����
        
        String netnbr = (String)map.get("netnbr");// ����
        String nettype = (String)map.get("nettype");// ��������(Ĭ�ϴ�"GSM")
        String pur = (String)map.get("pur");// ������;(Ĭ�ϴ�"rsupSal")
        String pageIndex = (String)map.get("pageIndex");// ����ҳ��
        
        // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("atsvGetTelSection"))
        {
            // ���
            Map<String, Object> paramMap = new HashMap<String, Object>();
            
            // ����
            paramMap.put("netnbr", netnbr);
            
            // ��������
            paramMap.put("nettype", nettype);
            
            // ��Դ����
            paramMap.put("pur", pur);
            
            // ��������Dubbo����
            ReturnWrap rw = getIntMsgUtil().invokeDubbo("atsvGetTelSection",
                    menuid,
                    "",
                    "0",
                    region,
                    operid,
                    termid,
                    paramMap,
                    new String[][] { {"retDataList"}, {"retDataList"}});
            
            if (SSReturnCode.SUCCESS == rw.getStatus() && rw.getReturnObject() == null)
            {
                
                rw.setReturnObject(new CRSet());
            }
            
            return rw;
        }
        
        // ��װ�������
        
        // ����
        Element eReq_netnbr = eBody.addElement("netnbr");
        eReq_netnbr.addText(netnbr);
        
        // ��������(Ĭ�ϴ�"GSM")
        Element eReq_nettype = eBody.addElement("nettype");
        eReq_nettype.addText(nettype);
        
        // ������;(Ĭ�ϴ�"rsupSal")
        Element eReq_pur = eBody.addElement("pur");
        eReq_pur.addText(pur);
        
        Document docXML = intMsgUtil.createMsg(doc, "cli_qry_telsection_hub", menuid, "", "0", region, operid, termid);
        ReturnWrap rw = intMsgUtil.invoke(docXML);
        
        if (SSReturnCode.SUCCESS == rw.getStatus() || SSReturnCode.SUCCESS_CODE_100 == rw.getStatus())
        {
            rw.setStatus(SSReturnCode.SUCCESS);
        }
        else
        {
            rw.setStatus(SSReturnCode.ERROR);
        }
        
        return rw;
    }
    
    /**
     * ���֤����ԤԼ_���� <������ϸ����>
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark modify by hWX5316476 2014-9-17 V200R003C10LG0901 OR_huawei_201409_427 �����ն˽���EBUS_���֤����ԤԼ
     */
    public ReturnWrap idCardBook(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");// ����Ա
            String termid = (String)map.get("termid");// �ն˻�ID
            String menuid = (String)map.get("curMenuId");// �˵�ID
            String region = (String)map.get("region");// ����
            
            String name = (String)map.get("name");// ����
            String sex = (String)map.get("sex");// �Ա�
            String nation = (String)map.get("nation");// ����
            String birthday = (String)map.get("birthday");// ����
            String address = (String)map.get("address");// ��ַ
            String idCard = (String)map.get("idCard");// ������ݺ���
            String idiograph = (String)map.get("idiograph");// ǩ������
            String startDate = (String)map.get("startDate");// ��Ч����ʼ����
            String endDate = (String)map.get("endDate");// ��Ч�ڽ�ֹ����
            String newAddress = (String)map.get("newAddress");// ����סַ
            
            if (IntMsgUtil.isTransEBUS("CTCSInstallByIdCard"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // ֤����
                inParamMap.put("certID", idCard);
                
                // ����
                inParamMap.put("certName", name);
                
                // ֤����ַ
                inParamMap.put("certAddr", address);
                
                // �Ա�
                inParamMap.put("certGender", sex);
                
                // ֤����ʼʱ��
                inParamMap.put("certSrtDate", startDate);
                
                // ֤������ʱ��
                inParamMap.put("certEndDate", endDate);
                
                MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuid, operid, termid, "0", "0", region);
                
                return intMsgUtil.invokeDubbo("CTCSInstallByIdCard", msgHeaderPO, inParamMap);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ���֤����
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(idCard);
            
            // ����
            Element eReq_name = eBody.addElement("certname");
            eReq_name.addText(name);
            
            // �Ա�
            Element eReq_sex = eBody.addElement("certgender");
            eReq_sex.addText(sex);
            
            // ����
            Element eReq_nation = eBody.addElement("nation");
            eReq_nation.addText(nation);
            
            // ����
            Element eReq_birthday = eBody.addElement("birthday");
            eReq_birthday.addText(birthday);
            
            // ��ַ
            Element eReq_address = eBody.addElement("certaddr");
            eReq_address.addText(address);
            
            // ǩ������
            Element eReq_idiograph = eBody.addElement("idiograph");
            eReq_idiograph.addText(idiograph);
            
            // ��Ч����ʼ����
            Element eReq_startDate = eBody.addElement("certstartdate");
            eReq_startDate.addText(startDate);
            
            // ��Ч�ڽ�ֹ����
            Element eReq_endDate = eBody.addElement("certenddate");
            eReq_endDate.addText(endDate);
            
            // ����סַ
            Element eReq_newAddress = eBody.addElement("newAddress");
            eReq_newAddress.addText(newAddress);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_install_bookcertinfo_hub",
                    menuid,
                    "0",
                    "0",
                    region,
                    operid,
                    termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
        
        String favorType = (String)map.get("favorType");
        try
        {
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // add by xkf57421 cli_qry_promotions_hub �ӿڳ��μ���ζ�Ҫ����favorabletype
            Element eReq_favorType = eBody.addElement("favorabletype");
            eReq_favorType.addText(favorType);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_promotions_hub",
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
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
            // add by xkf57421 for bsacAtsvCard begin
            String payType = (String)map.get("payType");
            // add by xkf57421 for bsacAtsvCard end
            
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
            
            // add by xkf57421 for bsacAtsvCard begin
            Element eReq_payType = eBody.addElement("paytype");
            eReq_payType.addText(payType);
            // add by xkf57421 for bsacAtsvCard end
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_promotions_hub",
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
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �����˵�������ѯ
     */
    public ReturnWrap qryBillAanlysis(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            
            // �·�
            String month = (String)map.get("month");
            
            // add begin by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus 2015/4/1
            if (IntMsgUtil.isTransEBUS("CTCSQryBillAnalysis"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // �绰����
                inParamMap.put("TELNUM", telnumber);
                
                // �·�
                inParamMap.put("BILLCYCLE", month);
                
                MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuid, operid, atsvNum, touchoid,
                        MsgHeaderPO.ROUTETYPE_TELNUM, telnumber);
                
                return intMsgUtil.invokeDubbo("CTCSQryBillAnalysis", msgHeaderPO, inParamMap);
            }
            // add end by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus 2015/4/1
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �������
            Element eReq_telnumber = eBody.addElement("telnum");
            eReq_telnumber.addText(telnumber);
            
            Element eReq_region = eBody.addElement("month");
            eReq_region.addText(month);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_billanalysis_hub",
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
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �����ʷ��Ƽ�
     */
    public ReturnWrap qryChargeGuide(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String menuid = (String)map.get("curMenuId");
            
            // �û�����
            String region = (String)map.get("region");
            // ��ˮ���
            String streamNo = (String)map.get("streamno");
            // ������
            String questionCode = (String)map.get("questioncode");
            // �𰸱��
            String answerCode = (String)map.get("answercode");
            
            // add begin by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            if (IntMsgUtil.isTransEBUS("CTCSQryChargeGuide"))
            {
                // ��������ͷ��Ϣ
                // �ն˻�
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuid, operid, atsvNum, "", MsgHeaderPO.ROUTETYPE_REGION,
                        region);
                
                // ���
                Map<String, String> inParamMap = new HashMap<String, String>();
                inParamMap.put("region", region);
                inParamMap.put("streamno", streamNo);
                inParamMap.put("questioncode", questionCode);
                inParamMap.put("answercode", answerCode);
                
                return intMsgUtil.invokeDubbo("CTCSQryChargeGuide", msgHeader, inParamMap);
            }
            // add end by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �������
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            
            Element eReq_streamno = eBody.addElement("streamno");
            eReq_streamno.addText(streamNo);
            
            Element eReq_questioncode = eBody.addElement("questioncode");
            eReq_questioncode.addText(questionCode);
            
            Element eReq_answercode = eBody.addElement("answercode");
            eReq_answercode.addText(answerCode);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_chargeguide_hub",
                    menuid,
                    "",
                    "0",
                    "999",
                    operid,
                    atsvNum);
            
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��Ʒ�������ѯ����ת���Ĳ�Ʒ
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
            
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("PTPCEIGetProdListForChgMainProd"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // ����
                inParamMap.put("region", (String)map.get("region"));
                
                // ҵ������
                inParamMap.put("recType", (String)map.get("rectype"));
                
                // �����ƷId
                inParamMap.put("mainProdID", (String)map.get("mainprodid"));
                
                return intMsgUtil.invokeDubbo("PTPCEIGetProdListForChgMainProd",
                        menuid,
                        touchoid,
                        "1",
                        telnumber,
                        operid,
                        atsvNum,
                        inParamMap);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �������
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText((String)map.get("region"));
            
            Element eReq_rectype = eBody.addElement("rectype");
            eReq_rectype.addText((String)map.get("rectype"));
            
            Element eReq_mainprodid = eBody.addElement("mainprodid");
            eReq_mainprodid.addText((String)map.get("mainprodid"));
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_package_changelist_hub",
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
            log.error("��ѯ�û�����ת���Ĳ�Ʒʧ��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��ѯ�û�����ת���Ĳ�Ʒʧ��.");
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
            
            log.info("����ҵ��ID[cli_package_chgcontent_hub]��ʼ.");
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_package_chgcontent_hub",
                    menuid,
                    "",
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            log.info("����ҵ��ID[cli_package_chgcontent_hub]��ʼ.");
            
            // ������Ҫ���õ����Ľ���
            return intMsgUtil.invokeProdChg(docXML);
            
        }
        catch (Exception e)
        {
            log.error("���ݲ�Ʒģ��ID��ѯģ����ϸʧ��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "���ݲ�Ʒģ��ID��ѯģ����ϸʧ��.");
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
            
            log.info("����ҵ��ID[cli_package_chgcommit_hub]��ʼ.");
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_package_chgcommit_hub",
                    menuid,
                    "",
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            log.info("����ҵ��ID[cli_package_chgcommit_hub]����.");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("��Ʒת������ʧ��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��Ʒת������ʧ��.");
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
            
            log.info("����ҵ��ID[cli_package_gettmplist_hub]��ѯ��Ʒ��ģ�忪ʼ.");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_package_gettmplist_hub",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            
            log.info("����ҵ��ID[cli_package_gettmplist_hub]��ѯ��Ʒ��ģ�����.");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("��ѯ��Ʒ��ģ���б�ʧ��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, e.getMessage());
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
            
            log.info("����ҵ��ID[cli_package_chgcheck_hub]��֤�û��Ƿ�߱�ת��������ʼ.");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_package_chgcheck_hub",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            
            log.info("����ҵ��ID[cli_package_chgcheck_hub]��֤�û��Ƿ�߱�ת����������.");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("��֤�û��Ƿ�߱�ת������ʧ��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, e.getMessage());
        }
    }
    
    /**
     * ����GROUPID��ȡ�ֵ������ add by LiFeng
     * 
     * @param map
     * @return
     */
    public ReturnWrap getDictItemByGroup(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String groupid = (String)map.get("groupid");
            
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("PTPUBQryDictItemsByGrpID"))
            {
                // ���
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // �ֵ���ID
                paramMap.put("groupID", groupid);
                
                // modify begin gwx223032 2015-05-12 OR_HUB_201504_412_����_���������ն˳��ؿ��ԤԼ���ܵ�����v1.1
                // ����dubbo����
                ReturnWrap rw = intMsgUtil.invokeDubbo("PTPUBQryDictItemsByGrpID",
                        menuid,
                        touchoid,
                        "1",
                        telnumber,
                        operid,
                        atsvNum,
                        paramMap,
                        new String[] {"dictID", "dictName", "description"});
                // modify end gwx223032 2015-05-12 OR_HUB_201504_412_����_���������ն˳��ؿ��ԤԼ���ܵ�����v1.1
                
                if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
                {
                    if (null == rw.getReturnObject())
                    {
                        rw.setReturnObject(new CRSet());
                    }
                }
                return rw;
                
            }
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // group ID
            Element eReq_groupid = eBody.addElement("groupid");
            eReq_groupid.addText(groupid);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_dictitem",
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
            log.error("��ȡ�ֵ������ʧ��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, e.getMessage());
        }
    }
    
    // ���·������������ѯ Add by LiFeng [XQ[2011]_06_020]�����������·������������ѯ�����ص����� 20110913 Begin
    /**
     * �������·������������ѯ���� <������ϸ����>
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryMonthlyReturnInfo(Map inMap)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)inMap.get("curOper");
            String atsvNum = (String)inMap.get("atsvNum");
            String telnumber = (String)inMap.get("telnumber");
            String touchoid = (String)inMap.get("touchoid");
            String menuid = (String)inMap.get("curMenuId");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText((String)inMap.get("telnum"));
            
            // ������ʼʱ��
            Element eReq_oldmainprodid = eBody.addElement("stardate");
            eReq_oldmainprodid.addText((String)inMap.get("stardate"));
            
            // ��������ʱ��
            Element eReq_newmainprodid = eBody.addElement("enddate");
            eReq_newmainprodid.addText((String)inMap.get("enddate"));
            
            log.info("����ҵ��ID[cli_qry_monthlyreturninfo_hub]��ȡ���·�����Ϣ��ʼ.");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_monthlyreturninfo_hub",
                    menuid,
                    touchoid,
                    "1",
                    telnumber,
                    operid,
                    atsvNum);
            
            log.info("����ҵ��ID[cli_qry_monthlyreturninfo_hub]��ȡ���·�����Ϣ����.");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("��ȡ���·�����Ϣʧ��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, e.getMessage());
        }
    }
    
    /**
     * ҵ��ͳһ��ѯ�ӿ� <������ϸ����>
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark modify by sWX219697 2014-9-11 OR_huawei_201409_430 �����ն˽���EBUS_�˵�����
     */
    public ReturnWrap queryService(MsgHeaderPO msgHead)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLCSIntQuerySubsAllServ"))
            {
                // ���
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // �ֻ���
                inParamMap.put("telNum", msgHead.getTelNum());
                
                // ���
                inParamMap.put("querySN", "0");
                
                String[] outParamListDefine = new String[] {"objType", "spID", "spName", "spBizID", "spBizName",
                        "spBizType", "billingType", "price", "domain", "startDate", "endDate", "seqNum", "charge",
                        "priceDesc"};
                
                // ��������Dubbo����
                return intMsgUtil.invokeDubbo("BLCSIntQuerySubsAllServ", msgHead, inParamMap, outParamListDefine);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHead.getTelNum());
            
            // ���
            DocumentUtil.addSubElementToEle(eBody, "sn", "0");
            
            return intMsgUtil.invoke("cli_qry_spinfo", msgHead, doc);
        }
        catch (Exception e)
        {
            log.error("ҵ��ͳһ��ѯ�ӿ�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �˵����ͷ�ʽ�ύ
     * 
     * @param msgHead ��Ϣͷ
     * @param billSendType ���ͷ�ʽ
     * @param mailAddr �ʼĵ�ַ
     * @return
     * @remark modify by sWX219697 2014-9-9 09:48:21 OR_huawei_201409_430 �����ն˽���EBUS_�˵�����
     */
    public ReturnWrap billSendCommit(MsgHeaderPO msgHead, String billSendType, String mailAddr)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("CTCSDoSubsBillMailNew"))
            {
                // ���
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // �ֻ�����
                inParamMap.put("telNum", msgHead.getTelNum());
                
                // �ʼ�����
                inParamMap.put("mailType", billSendType);
                
                // �ʼĵ�ַ
                inParamMap.put("mailAddr", mailAddr);
                
                // ��������
                inParamMap.put("oprType", "1");
                
                // ��������Dubbo����
                return intMsgUtil.invokeDubbo("CTCSDoSubsBillMailNew", msgHead, inParamMap);
                
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHead.getTelNum());
            
            // �˵���������
            DocumentUtil.addSubElementToEle(eBody, "mailtype", billSendType);
            
            // �������ͣ�Ĭ��Ϊ1����ͨ
            DocumentUtil.addSubElementToEle(eBody, "oprtype", "1");
            
            // ����ʼ�����ΪEmail�ʵ�����ΪEmail��ַ������ʼ������Ƕ����ʵ����߲����ʵ�����Ϊ�ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "mailaddr", mailAddr);
            
            return intMsgUtil.invoke("cli_busi_sendbill", msgHead, doc);
        }
        catch (Exception e)
        {
            log.error("�˵�����ҵ������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // ���·������������ѯ Add by LiFeng [XQ[2011]_06_020]�����������·������������ѯ�����ص����� 20110913 End
    
    // �°��˵���ѯ Add by XKF57421 Begin
    public ReturnWrap qryBillCustInfo(Map map)
    {
        String servnum = (String)map.get("SERVNUM");
        String cyclemonth = (String)map.get("CYCLEMONTH");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // ---����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("CTCSQryBillCycleCustInfo"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // �绰����
                inParamMap.put("telNum", servnum);
                
                // �·�
                inParamMap.put("cycle", cyclemonth);
                
                MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuID, operID, termID, touchOID, "1", servnum);
                
                ReturnWrap rw = intMsgUtil.invokeDubbo("CTCSQryBillCycleCustInfo", msgHeaderPO, inParamMap);
                
                Vector v = new Vector();
                if (rw.getReturnObject() instanceof CTagSet)
                {
                    v.add(rw.getReturnObject());
                    rw.setReturnObject(v);
                }
                return rw;
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("TELNUM");
            eReq_telnum.addText(servnum);
            
            Element eReq_month = eBody.addElement("CYCLE");
            eReq_month.addText(cyclemonth);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_BillCycleCustInfo_hub",
                    menuID,
                    touchOID,
                    "1",
                    servnum,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    public void addJsonArray2Xml(Element bodyElement,com.alibaba.dubbo.common.json.JSONArray  pkgLst,String[] keys,String parentKey){
        if(pkgLst!=null){
            for(int i=0;i<pkgLst.length();i++){
                Element parentElement=bodyElement.addElement(parentKey);
                com.alibaba.dubbo.common.json.JSONObject _pkgLst=(com.alibaba.dubbo.common.json.JSONObject)pkgLst.get(i);                    
                for(int j=0;j<keys.length;j++){
                    Element element =parentElement.addElement(keys[j]);
                    element.setText((String)_pkgLst.get(keys[j])); 
                }
            }
        }
    }
    public String arQryBillCommuHuBExcelEbus(Map map) throws Exception{
        String telnum = (String)map.get("telnum");
        String billcycle = (String)map.get("billcycle");
        //String isunitepayment = (String)map.get("isunitepayment");
        //String arealist = (String)map.get("arealist");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");        
        Map<String, String> inParamMap = new HashMap<String, String>();      
        // �绰����
        inParamMap.put("telNum", telnum);
        // �·�
        inParamMap.put("cycle", billcycle);   
        inParamMap.put("printInfo", "1"); 
        MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuID, operID, termID, touchOID, "1", telnum);        
        ReturnWrap rw = intMsgUtil.invokeDubbo("arQryBillCommuHuBExcelEbus", msgHeaderPO, inParamMap,false);        
        if (SSReturnCode.SUCCESS == rw.getStatus())
        {
            String json=(String)rw.getReturnObject();
            try
            {
                com.alibaba.dubbo.common.json.JSONObject jsonObj=(com.alibaba.dubbo.common.json.JSONObject)com.alibaba.dubbo.common.json.JSON.parse(json);
                com.alibaba.dubbo.common.json.JSONArray  pkgLst=(com.alibaba.dubbo.common.json.JSONArray)jsonObj.getArray("pkgLst");
                com.alibaba.dubbo.common.json.JSONArray  overLst=(com.alibaba.dubbo.common.json.JSONArray)jsonObj.getArray("overLst");
                com.alibaba.dubbo.common.json.JSONArray  bcallLst=(com.alibaba.dubbo.common.json.JSONArray)jsonObj.getArray("bcallLst");
                String printInfo=(String)jsonObj.get("printInfo");
                Document doc = DocumentHelper.createDocument();
                doc.setXMLEncoding("GBK");
                Element bodyElement=doc.addElement("body");
                addJsonArray2Xml( bodyElement,  pkgLst,new String[]{"remainderDesc","includeDesc","usageDesc","className","packageName"},"pkgLst");
                addJsonArray2Xml( bodyElement,  overLst,new String[]{"className","includeDesc"},"overLst");
                addJsonArray2Xml( bodyElement,  bcallLst,new String[]{"usageDesc","className","remainderDesc","includeDesc"},"bcallLst");                
                if(printInfo!=null){
                    Element printInfoElement= bodyElement.addElement("printInfo");
                    printInfoElement.addText(printInfo);
                }                
                //System.out.println(doc.asXML());
                return doc.asXML();
            }
            catch (ParseException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw e;
            }
        }
        return null;
    }
    public ReturnWrap qryCurBillInfo(Map map)
    {
        String telnum = (String)map.get("telnum");
        String billcycle = (String)map.get("billcycle");
        String isunitepayment = (String)map.get("isunitepayment");
        String arealist = (String)map.get("arealist");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // ------�����ն�ebus����------------------------
            if (IntMsgUtil.isTransEBUS("CTCSQryBillArea"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // �绰����
                inParamMap.put("telnum", telnum);
                
                // �·�
                inParamMap.put("billcycle", billcycle);
                
                // 
                inParamMap.put("isunitepayment", isunitepayment);
                
                // 
                inParamMap.put("arealist", arealist);
                
                inParamMap.put("operid", operID);
                
                MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuID, operID, termID, touchOID, "1", telnum);
                
                ReturnWrap rw = intMsgUtil.invokeDubbo("CTCSQryBillArea", msgHeaderPO, inParamMap);
                
                if (SSReturnCode.SUCCESS == rw.getStatus())
                {
                    Vector v = new Vector();
                    if (rw.getReturnObject() instanceof Vector)
                    {
                        v = (Vector)rw.getReturnObject();
                        if (v != null && v.size() > 0)
                        {
                            rw.setReturnObject((CTagSet)v.get(0));
                        }
                    }
                    if (rw.getReturnObject() instanceof CTagSet)
                    {
                        rw.setReturnObject(rw.getReturnObject());
                    }
                }
                return rw;
            }
            // ------------------------------
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            Element eReq_month = eBody.addElement("billcycle");
            eReq_month.addText(billcycle);
            
            Element eReq_custtype = eBody.addElement("isunitepayment");
            eReq_custtype.addText(isunitepayment);
            
            Element eReq_areaFlag = eBody.addElement("arealist");
            eReq_areaFlag.addText(arealist);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_qrybillarea_hub",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // �°��˵���ѯ Add by XKF57421 End
    
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
    
    // add begin l00190940 2011-12-7 OR_HUB_201112_16
    /**
     * ��ѯ�������
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryFamilyNumber(MsgHeaderPO msgHeader)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("CTCSQryDealDealTelNum"))
            {
                CTagSet cTagSet = new CTagSet();
                
                // ѭ����������������CTagSet��
                for (int i = 0; i < 3; i++)
                {
                    cTagSet.SetValue("friendnum" + (i + 1), "");
                }
                
                // ���
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // �绰����
                paramMap.put("telNum", msgHeader.getTelNum());
                
                // ��Ʒ���룬�̶�ֵ"G8887"
                paramMap.put("prodID", "G8887");
                
                // ��������Dubbo����
                ReturnWrap rw = getIntMsgUtil().invokeDubbo("CTCSQryDealDealTelNum",
                        msgHeader,
                        paramMap,
                        new String[] {"attrID", "attrValue"});
                
                // ȡ�������������
                CRSet crSet = (CRSet)rw.getReturnObject();
                
                if (crSet != null)
                {
                    // ȡǰ�����������
                    String[] arrays = new String[] {"FriendNum1", "FriendNum2", "FriendNum3"};
                    
                    // ѭ����ǰ��������������CTagSet��
                    for (int i = 0; i < crSet.GetRowCount(); i++)
                    {
                        if (ArrayUtils.contains(arrays, crSet.GetValue(i, 0)))
                        {
                            cTagSet.SetValue(crSet.GetValue(i, 0).toLowerCase(Locale.ENGLISH), crSet.GetValue(i, 1));
                        }
                    }
                }
                
                rw.setReturnObject(cTagSet);
                
                return rw;
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ���ú�̨�ӿ�
            return intMsgUtil.invoke("cli_qry_szxqskqqh", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            log.error("��ѯ�����ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ���á��޸Ļ�ȡ���������
     */
    public ReturnWrap setFamilyNumber(MsgHeaderPO msgHeader, String sn, String sregion, String stype)
    {
        try
        {
            // add by jWX216858 OR_huawei_201410_867_HUB_��ѡ�ײ���ˮ�߲���EBUS����
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("IncProductSrv2"))
            {
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // �ֻ�����
                paramMap.put("TELNUM", msgHeader.getTelNum());
                
                // ncode �̶�ֵ
                paramMap.put("NCODE", "FriendNew");
                
                // �������λ�á�Ŀǰֻ��1��2��3
                paramMap.put("SN", sn);
                
                // ������룬�����ȡ����������
                paramMap.put("FRIEND", sregion);
                
                // �������ͣ��̶���ADD
                paramMap.put("STYPE", stype);
                
                return getIntMsgUtil().invokeDubbo("IncProductSrv2", msgHeader, paramMap);
            }
            // add end jWX216858 OR_huawei_201410_867_HUB_��ѡ�ײ���ˮ�߲���EBUS����
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(msgHeader.getTelNum());
            
            // ��������
            Element eReq_stype = eBody.addElement("stype");
            eReq_stype.addText(stype);
            
            // ����λ��
            Element eReq_sn = eBody.addElement("sn");
            eReq_sn.addText(sn);
            
            // �������
            Element eReq_sregion = eBody.addElement("sregion");
            eReq_sregion.addText(sregion);
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_szxqskqqh",
                    msgHeader.getMenuId(),
                    msgHeader.getUniContact(),
                    "1",
                    msgHeader.getTelNum(),
                    msgHeader.getOperId(),
                    msgHeader.getTermId());
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("���������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add end l00190940 2011-12-7 OR_HUB_201112_16
    
    /**
     * ��ѯ�û��Ѵ��ڵĵ���_����
     * 
     * @param map
     * @return
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public ReturnWrap qrySubsDangcisList(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLCSQrySubsValidPrivList"))
            {
                // ���
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // �ֻ�����
                paramMap.put("servNumber", telnumber);
                
                // ����dubbo����
                ReturnWrap rw = intMsgUtil.invokeDubbo("BLCSQrySubsValidPrivList",
                        menuID,
                        touchOID,
                        "1",
                        telnumber,
                        operID,
                        termID,
                        paramMap,
                        new String[][] { {"result", "privList"}, {"result", "privList"}},
                        new String[] {"privID", "privName"});
                
                if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
                {
                    Object obj = rw.getReturnObject();
                    
                    if (obj instanceof CTagSet)
                    {
                        rw.setReturnObject(new CRSet());
                    }
                    else if (obj instanceof Vector)
                    {
                        Vector v = (Vector)obj;
                        rw.setReturnObject((CRSet)v.get(1));
                    }
                }
                return rw;
            }
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_subsprivlist",
                    menuID,
                    touchOID,
                    "1",
                    telnumber,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("��ѯ�û��Ѵ��ڵĵ���ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯ��������_����
     * 
     * @param map
     * @return
     */
    public ReturnWrap queryDangciDesc(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            String privid = (String)map.get("privid");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("privid");
            eReq_telnum.addText(privid);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_privdesc",
                    menuID,
                    touchOID,
                    "1",
                    telnumber,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("��ѯ��������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯ��Ʒ�б�_����
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryRewardList(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            // �����
            String activityId = (String)map.get("activityId");
            
            // ���α���
            String dangciId = (String)map.get("dangciId");
            
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("PTPCEIGetBatchRecPresentsByActId"))
            {
                // ���
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // �����
                paramMap.put("actID", activityId);
                
                // ���α���
                paramMap.put("privID", dangciId);
                
                // ����dubbo����
                ReturnWrap rw = intMsgUtil.invokeDubbo("PTPCEIGetBatchRecPresentsByActId",
                        menuID,
                        touchOID,
                        "1",
                        telnumber,
                        operID,
                        termID,
                        paramMap,
                        new String[] {"rewardID", "rewardName", "rewardType", "rewardValue", "scoreDealType",
                                "useScore", "rewardInfo"});
                if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
                {
                    if (null == rw.getReturnObject())
                    {
                        rw.setReturnObject(new CRSet());
                    }
                }
                return rw;
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �����
            Element eReq_prodid = eBody.addElement("prodid");
            eReq_prodid.addText(activityId);
            
            // ���α���
            Element eReq_privid = eBody.addElement("privid");
            eReq_privid.addText(dangciId);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_rewardlist",
                    menuID,
                    touchOID,
                    "1",
                    telnumber,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("��ѯ��Ʒ�б�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯӪ���������ú��û�Ԥ�����
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryPrivFee(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            // Ӫ������������ˮ
            String recoid = (String)map.get("recoid");
            
            // �û��ܷ���
            String totalFee = (String)map.get("totalfee");
            
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLCSGetSubsRecFeeAndPreFee"))
            {
                // ���
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // �������
                paramMap.put("recOid", recoid);
                
                // �û������ܷ���
                paramMap.put("totalFee", totalFee);
                
                // ����dubbo����
                ReturnWrap rw = intMsgUtil.invokeDubbo("BLCSGetSubsRecFeeAndPreFee",
                        menuID,
                        touchOID,
                        "1",
                        telnumber,
                        operID,
                        termID,
                        paramMap);
                if (null != rw && SSReturnCode.SUCCESS == rw.getStatus())
                {
                    if (null == rw.getReturnObject())
                    {
                        rw.setReturnObject(new CRSet());
                    }
                    else
                    {
                        CTagSet ctag = (CTagSet)rw.getReturnObject();
                        
                        CRSet crset = new CRSet(2);
                        crset.AddRow();
                        
                        // �������
                        crset.SetValue(0, 0, ctag.GetValue("recFee"));
                        
                        // Ԥ������
                        crset.SetValue(0, 1, ctag.GetValue("preFee"));
                        
                        rw.setReturnObject(crset);
                    }
                }
                return rw;
            }
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // Ӫ������������ˮ
            Element eReq_recoid = eBody.addElement("recoid");
            eReq_recoid.addText(recoid);
            
            // �û��ܷ���
            Element eReq_totalFee = eBody.addElement("totalfee");
            eReq_totalFee.addText(totalFee);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_privFee",
                    menuID,
                    touchOID,
                    "1",
                    telnumber,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("��ѯӪ���������ú��û�Ԥ�����ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ���������_����
     * 
     * @param map
     * @return
     */
    public ReturnWrap recRewardCommit(Map map)
    {
        try
        {
            String telnumber = (String)map.get("telnum");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("touchOID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            // �����
            String actno = (String)map.get("actno");
            
            // ���α���
            String actlevel = (String)map.get("actlevel");
            
            // ���Ʒ��Ŵ�
            String actrewaed = (String)map.get("actrewaed");
            
            // �ֻ���IMEI��
            String imeiid = (String)map.get("imeiid");
            
            // 1��Ԥ����0������
            String onlycheck = (String)map.get("onlycheck");
            
            // ��Ʒ����
            String quantity = (String)map.get("quantity");
            
            // ��������
            String accesstype = (String)map.get("accesstype");
            
            // ���� ֻ�кӱ������Ĳ���ҪУ������
            String password = (String)map.get("password");
            
            // �û�Ͷ��ķ��ý�� Ԥ����ʱ���Բ���
            String totalfee = (String)map.get("totalfee");
            
            // ֧����ʽcash:�ֽ� card:������
            String paytype = (String)map.get("paytype");
            
            // add begin m00227318 2012/09/14 eCommerce V200R003C12L09 OR_huawei_201209_33
            // �Żݲ�Ʒ�ĸ������Դ�
            String addattrstr = (String)map.get("addattrstr");
            if (addattrstr == null)
            {
                addattrstr = "";
            }
            // add end m00227318 2012/09/14 eCommerce V200R003C12L09 OR_huawei_201209_33
            
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLCSRecRewardactiveLight"))
            {
                // ���
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // �ֻ�����
                paramMap.put("servNumber", telnumber);
                
                // ����������
                paramMap.put("invokerName", "CBLCSRecRewardCommit");
                
                // �����
                Map<String, String> actnoMap = new HashMap<String, String>();
                actnoMap.put("actID", actno);
                paramMap.put("act", actnoMap);
                
                // ���α���
                Map<String, String> levelIDMap = new HashMap<String, String>();
                levelIDMap.put("levelID", actlevel);
                paramMap.put("level", levelIDMap);
                
                // ��Ʒ��Ϣ���������ֻ���IMEI�ţ� ��Ʒ���룬 ��Ʒ����
                List<Map<String, Object>> rewardList = new ArrayList<Map<String, Object>>();
                
                String[] rewardIds = actrewaed.split("\\|");
                String[] imeiids = imeiid.split("\\|", -1);
                
                Map<String, Object> rewardAttrList = new HashMap<String, Object>();
                List<Map<String, String>> attrList = new ArrayList<Map<String, String>>();
                for (int i = 0; i < rewardIds.length; i++)
                {
                    Map<String, Object> rewardMap = new HashMap<String, Object>();
                    
                    // ��Ʒ����
                    rewardMap.put("rewardID", rewardIds[i]);
                    
                    // �ֻ���IMEI��
                    rewardMap.put("startResID", imeiids[i]);
                    rewardMap.put("endResID", imeiids[i]);
                    
                    // modify begin jWX216858 2015-5-28 BUG_HUB_201503_254 �����ն˻��ֶһ����Ӿ�ʧ�� 
                    if ("activitiesRec".equals(menuID))
                    {
                    	// ��Ʒ����
                    	rewardMap.put("quantity", quantity);
                    }
                    else
                    {
                    	// ��Ʒ����
                    	rewardMap.put("quantity", Integer.parseInt(quantity));
                    }
                    // modify end jWX216858 2015-5-28 BUG_HUB_201503_254 �����ն˻��ֶһ����Ӿ�ʧ�� 
                    
                    if (StringUtils.isNotEmpty(addattrstr))
                    {
                        String[] attrArr = addattrstr.split(",");
                        String attrType = attrArr[1];
                        String attrID = attrArr[2].substring(0, attrArr[2].indexOf("="));
                        String attrValue = attrArr[2].substring(attrArr[2].indexOf("=") + 1, attrArr[2].length());
                        Map<String, String> mapAttr = new HashMap<String, String>();
                        mapAttr.put("attrValue", attrValue);
                        mapAttr.put("attrID", attrID);
                        attrList.add(mapAttr);
                        rewardAttrList.put("attrType", attrType);
                        rewardAttrList.put("attrList", attrList);
                        
                    }
                    rewardMap.put("rewardAttrList", rewardAttrList);
                    rewardList.add(rewardMap);
                }
                paramMap.put("rewardList", rewardList);
                
                // �����ֵ
                Map<String, Object> specialMap = new HashMap<String, Object>();
                
                // 1��Ԥ����0������
                specialMap.put("isOnlyChk", onlycheck);
                
                // �û�Ͷ��ķ��ý�� Ԥ����ʱ���Բ���
                specialMap.put("totalFee", totalfee);
                // specialMap.put("presentAttrList", presentList);
                
                paramMap.put("specialList", specialMap);
                
                // ����
                Map<String, String> rewardExtMap = new HashMap<String, String>();
                
                rewardExtMap.put("password", password);
                paramMap.put("rewardExt", rewardExtMap);
                
                // ֧����ʽcash:�ֽ� card:������
                List<Map<String, String>> payTypeList = new ArrayList<Map<String, String>>();
                Map<String, String> payTypeMap = new HashMap<String, String>();
                
                payTypeMap.put("payType", paytype);
                payTypeList.add(payTypeMap);
                
                paramMap.put("payTypeList", payTypeList);
                
                if ("1".equals(onlycheck))
                {
                    // ����dubbo����
                    return intMsgUtil.invokeDubbo("BLCSRecRewardactiveLight",
                            menuID,
                            touchOID,
                            "1",
                            telnumber,
                            operID,
                            termID,
                            paramMap);
                }
                // ����dubbo����
                return intMsgUtil.invokeDubbo("BLCSRecRewardactiveLight",
                        menuID,
                        touchOID,
                        "1",
                        telnumber,
                        operID,
                        termID,
                        paramMap,
                        new String[][] { {"recOid"}, {"recoid"}});
            }
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // �����
            Element eReq_actno = eBody.addElement("actno");
            eReq_actno.addText(actno);
            
            // ���α���
            Element eReq_actlevel = eBody.addElement("actlevel");
            eReq_actlevel.addText(actlevel);
            
            // ���Ʒ��Ŵ�
            Element eReq_actreward = eBody.addElement("actrewaed");
            eReq_actreward.addText(actrewaed);
            
            // �ֻ���IMEI��
            Element eReq_imeiid = eBody.addElement("imeiid");
            eReq_imeiid.addText(imeiid);
            
            // 1��Ԥ����0������
            Element eReq_onlycheck = eBody.addElement("onlycheck");
            eReq_onlycheck.addText(onlycheck);
            
            // ��Ʒ����
            Element eReq_quantity = eBody.addElement("quantity");
            eReq_quantity.addText(quantity);
            
            // ��������
            Element eReq_accesstype = eBody.addElement("accesstype");
            eReq_accesstype.addText(accesstype);
            
            // ���� ֻ�кӱ������Ĳ���ҪУ������
            Element eReq_password = eBody.addElement("password");
            eReq_password.addText(password);
            
            // �û�Ͷ��ķ��ý�� Ԥ����ʱ���Բ���
            Element eReq_totalfee = eBody.addElement("totalfee");
            eReq_totalfee.addText(totalfee);
            
            // ֧����ʽcash:�ֽ� card:������
            Element eReq_paytype = eBody.addElement("paytype");
            eReq_paytype.addText(paytype);
            
            // add begin m00227318 2012/09/14 eCommerce V200R003C12L09 OR_huawei_201209_33
            // �Żݽ�Ʒ�ĸ������Դ�
            Element eReq_addattrstr = eBody.addElement("addattrstr");
            eReq_addattrstr.addText(addattrstr);
            // add end m00227318 2012/09/14 eCommerce V200R003C12L09 OR_huawei_201209_33
            
            Document docXML = null;
            
            // �������ɷѣ�������֯����
            if ("Card".equals(paytype))
            {
                docXML = intMsgUtil.createMsgCard(doc,
                        "cli_busi_recrewardcommit_card",
                        menuID,
                        touchOID,
                        "1",
                        telnumber,
                        operID,
                        termID);
            }
            else
            {
                docXML = intMsgUtil.createMsg(doc,
                        "cli_busi_recrewardcommit",
                        menuID,
                        touchOID,
                        "1",
                        telnumber,
                        operID,
                        termID);
            }
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("���������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �����_��Ʊ��ӡ
     * 
     * @param map
     * @return
     */
    public ReturnWrap invoiceDataByActivity(Map map)
    {
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        String telnum = (String)map.get("telnumber");// �ֻ�����
        String formnums = (String)map.get("formnums");// Ҫ��ӡ��Ʊ����־��ˮ�ţ�����ж�����ԡ�|���ָ�
        String recoid = (String)map.get("recoid");// ������ˮ
        
        try
        {
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("PTSupplyPrintInvoice"))
            {
                // ���
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // �ֻ�����
                paramMap.put("telnum", telnum);
                
                // ҵ����ˮ��
                paramMap.put("formNums", formnums);
                
                // ҵ��Ψһ����
                paramMap.put("recOid", recoid);
                
                // ����dubbo����
                ReturnWrap rw = null;
                rw = intMsgUtil.invokeDubbo("PTSupplyPrintInvoice",
                        menuID,
                        touchOID,
                        "1",
                        telnum,
                        operID,
                        termID,
                        paramMap);
                
                if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
                {
                    CTagSet ctagSet = (CTagSet)rw.getReturnObject();
                    
                    // tag����
                    CTagSet outParam = new CTagSet();
                    
                    // ��װcrset����
                    CRSet crset = new CRSet(2);
                    
                    Vector v = new Vector();
                    
                    // ��Ʊ��Ŀ
                    String invoiceCount = ctagSet.GetValue("invoiceCount");
                    outParam.SetValue("invoice_cnt", invoiceCount);
                    
                    v.add(outParam);
                    
                    // �з�Ʊ���ݣ�ȡ������װ����
                    if (("1").equals(invoiceCount))
                    {
                        // ����һ��
                        crset.AddRow();
                        
                        // ��������
                        crset.SetValue(0, 0, ctagSet.GetValue("payTime"));
                        
                        // ��Ʊ����
                        crset.SetValue(0, 1, ctagSet.GetValue("printItems"));
                    }
                    v.add(crset);
                    rw.setReturnObject(v);
                }
                return rw;
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ��־��ˮ
            Element eReq_formnums = eBody.addElement("formnums");
            eReq_formnums.addText(formnums);
            
            // ������ˮ
            Element eReq_recoid = eBody.addElement("recoid");
            eReq_recoid.addText(recoid);
            
            // ��������
            Element eReq_accessType = eBody.addElement("accessType");
            eReq_accessType.addText("bsacAtsv");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_invoiceinfoByActivity_hub",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
        
    }
    
    // add begin ykf38827 2012/03/20 R003C12L03n01 OR_HUB_201202_800
    /**
     * ��ѯδ��ӡ�ķ�Ʊ��¼����
     * 
     * @param map
     * @return
     */
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
            
            // modify begin wWX217192 2014-07-16 OR_huawei_201406_300 �����ն˽���EBUS���׶�_��Ʊ��ӡ
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLARQueryUnprintInvoceList"))
            {
                // ���
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // �ֻ�����
                paramMap.put("serviceNumber", telnumber);
                
                // ��������
                paramMap.put("accessType", accessType);
                
                // ��������Dubbo����
                return getIntMsgUtil().invokeDubbo("BLARQueryUnprintInvoceList",
                        menuID,
                        touchOID,
                        "1",
                        telnumber,
                        operID,
                        termID,
                        paramMap,
                        new String[] {"billCycle", "formnum", "itemName", "custName", "servNumber", "recFee",
                                "accessName", "recDate", "status", "statusDate", "operName", "orgName", "recType",
                                "oid", "invoiceFee", "upperFee"});
            }
            // modify end wWX217192 2014-07-16 OR_huawei_201406_300 �����ն˽���EBUS���׶�_��Ʊ��ӡ
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            // ����
            Element eReq_accessType = eBody.addElement("accesstype");
            eReq_accessType.addText(accessType);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_noinvoiceprint_hub",
                    menuID,
                    touchOID,
                    "1",
                    telnumber,
                    operID,
                    termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("��ѯҪ��ӡ�ķ�Ʊ��¼��Ϣʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯҪ��ӡ�ķ�Ʊ��ӡ������
     * 
     * @param map
     * @return
     */
    @Override
    public ReturnWrap invoiceData(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String formnum = (String)map.get("formnum");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        String billCycle = (String)map.get("billCycle");
        
        try
        {
            // modify begin wWX217192 2014-07-16 OR_huawei_201406_300 �����ն˽���EBUS���׶�_��Ʊ��ӡ
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLARReprintInvoice"))
            {
                // ���
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // ҵ�������
                paramMap.put("recOid", formnum);
                
                // ��������
                paramMap.put("accessType", "bsacAtsv");
                
                // ����
                paramMap.put("billCycle", billCycle);
                
                // ��������Dubbo����
                return getIntMsgUtil().invokeDubbo("BLARReprintInvoice",
                        menuID,
                        touchOID,
                        "1",
                        telnum,
                        operID,
                        termID,
                        paramMap);
            }
            // modify end wWX217192 2014-07-16 OR_huawei_201406_300 �����ն˽���EBUS���׶�_��Ʊ��ӡ
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ������ˮ
            Element eReq_smscont = eBody.addElement("formnum");
            eReq_smscont.addText(formnum);
            
            // ����
            Element eReq_billCycle = eBody.addElement("billCycle");
            eReq_billCycle.addText(billCycle);
            
            // ��������
            Element eReq_accessType = eBody.addElement("accessType");
            eReq_accessType.addText("bsacAtsv");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_invoiceinfo_hub_new",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
        
    }
    
    // add end ykf38827 2012/03/20 R003C12L03n01 OR_HUB_201202_800
    
    // add begin yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
    /**
     * ��ѯ��Ʒ���ȷ����Ϣ�������û��迪ͨ��ҵ�񡢻ᱣ����ҵ����ȡ����ҵ��
     * 
     * @param map
     * @return
     */
    public ReturnWrap mainProductRecInfo(MsgHeaderPO msgHeader, String ncode, String inttime)
    {
        try
        {
            // modify begin wWX217192 2014-09-20 OR_huawei_201409_433 �����ն˽���EBUS_�ʷ��ײ�ת��
            if (IntMsgUtil.isTransEBUS("BLCSProductRec"))
            {
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // �ֻ�����
                paramMap.put("servNum", msgHeader.getTelNum());
                
                // ncode
                paramMap.put("nCode", ncode);
                
                // ��������
                paramMap.put("operType", "PCOpRec");
                
                // ��Ч��ʽ
                paramMap.put("affectType", "Type_NextCycle");
                
                // ����ʱ��
                paramMap.put("inTime", inttime);
                
                // �Ƿ�Ϊ��Ʒ���Ԥ����
                paramMap.put("prepareBusi", "PreBsacNBChgMainProd");
                
                // չʾ��Ʒ�б�
                paramMap.put("ebusShowProd", "1");
                
                return getIntMsgUtil().invokeDubbo("BLCSProductRec", msgHeader, paramMap, false);
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ncode
            DocumentUtil.addSubElementToEle(eBody, "ncode", ncode);
            
            // ��������
            DocumentUtil.addSubElementToEle(eBody, "oprtype", "PCOpRec");
            
            // ����
            DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
            
            // ��Ч��ʽ
            DocumentUtil.addSubElementToEle(eBody, "affecttype", "Type_NextCycle");
            
            // ����ʱ��
            DocumentUtil.addSubElementToEle(eBody, "intime", inttime);
            
            // �Ƿ�Ϊ��Ʒ���Ԥ����
            DocumentUtil.addSubElementToEle(eBody, "preparebusi", "PreBsacNBChgMainProd");
            
            return intMsgUtil.invoke("cli_busi_MainIntProductRec", msgHeader, msgBody);
            // modify end 2014-09-20 OR_huawei_201409_433 �����ն˽���EBUS_�ʷ��ײ�ת��
        }
        catch (Exception e)
        {
            log.error("��ѯ��Ʒ���ȷ����Ϣʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ���ýӿ�ִ�������Ʒת��
     * 
     * @param map
     * @return
     */
    public ReturnWrap mainProductChangeSubmit(MsgHeaderPO msgHeader, String ncode)
    {
        try
        {
            // add by jWX216858 OR_huawei_201410_867_HUB_��ѡ�ײ���ˮ�߲���EBUS����
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("IncProductSrv2"))
            {
                Map<String, String> paramMap = new HashMap<String, String>();
                
                // �ֻ�����
                paramMap.put("TELNUM", msgHeader.getTelNum());
                
                // ��������ADD ���� DEL ɾ�� MOD �޸� QRY ��ѯ
                paramMap.put("STYPE", "ADD");
                
                // ncode
                paramMap.put("NCODE", ncode);
                
                // �̶�BsacNBSubmit
                paramMap.put("-PREPAREBUSI", "BsacNBSubmit");
                
                return getIntMsgUtil().invokeDubbo("IncProductSrv2", msgHeader, paramMap);
            }
            // add end jWX216858 OR_huawei_201410_867_HUB_��ѡ�ײ���ˮ�߲���EBUS����
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(msgHeader.getTelNum());
            
            Element eReq_ncode = eBody.addElement("ncode");
            eReq_ncode.addText(ncode);
            
            Element eReq_stype = eBody.addElement("stype");
            eReq_stype.addText("ADD");
            
            Element eReq_preparebusi = eBody.addElement("preparebusi");
            eReq_preparebusi.addText("BsacNBSubmit");
            
            return intMsgUtil.invoke("cli_busi_ChangeProductSubmit", msgHeader, doc);
        }
        catch (Exception e)
        {
            log.error("��Ʒת������", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add end yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
    
    // add by yedengchu
    
    /**
     * ����������Ϣ��ѯ�ӿ�
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryBackInfo(Map map)
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
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_fee_hub",
                    menuid,
                    touchoid,
                    "1",
                    servnumber,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add by yedengchu
    public ReturnWrap qryAllBackInfo(Map map)
    {
        String startDate = (String)map.get("startDate");
        String endDate = (String)map.get("endDate");
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("servnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        String qryflag = (String)map.get("qryflag");
        
        try
        {
            // add begin by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus 2015/4/1
            if (IntMsgUtil.isTransEBUS("CTCSQryBalRetInfo"))
            {
                // ��������ͷ��Ϣ
                // �ն˻�
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuid, operid, atsvNum, touchoid,
                        MsgHeaderPO.ROUTETYPE_TELNUM, telnumber);
                
                Map<String, String> inParamMap = new HashMap<String, String>();
                inParamMap.put("telnum", telnumber);
                inParamMap.put("startdate", startDate);
                inParamMap.put("enddate", endDate);
                inParamMap.put("qryflag", qryflag);
                
                String[] outParamKeyDefine = {"subjectname", "begindate", "enddate", "flowtime", "types", "flowamt",
                        "nowbalance", "rewardid", "rewardname", "remarks"};
                return intMsgUtil.invokeDubbo("CTCSQryBalRetInfo", msgHeader, inParamMap, outParamKeyDefine);
            }
            // add end by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus 2015/4/1
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // ��ʼ����
            Element eReq_startdate = eBody.addElement("startdate");
            eReq_startdate.addText(startDate);
            
            // ��������
            Element eReq_endDate = eBody.addElement("enddate");
            eReq_endDate.addText(endDate);
            // ��ѯ��־
            Element eReq_qryflag = eBody.addElement("qryflag");
            eReq_qryflag.addText(qryflag);
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_qry_balancereturninfo_hub",
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
            log.error("������Ϣ��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add by xkf57421 for OR_HUB_201202_1192 begin
    public ReturnWrap validateTelSim(Map<String, String> paramMap)
    {
        
        String telnum = (String)paramMap.get("telnum");
        String iccid = (String)paramMap.get("iccid");
        // String imsi = (String)paramMap.get("imsi");
        String porductid = (String)paramMap.get("porductid");
        
        String region = (String)paramMap.get("rtRegion");
        String operID = (String)paramMap.get("operid");
        String termID = (String)paramMap.get("termid");
        String menuID = (String)paramMap.get("menuid");
        String touchOID = (String)paramMap.get("touchoid");
        try
        {
            // add by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            if (IntMsgUtil.isTransEBUS("CTCSTremCardCkeck"))
            {
                // ��������ͷ��Ϣ
                // �ն˻�
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, operID, touchOID, "", MsgHeaderPO.ROUTETYPE_TELNUM,
                        telnum);
                
                // ���
                Map<String, String> map = new HashMap<String, String>();
                
                // �ֻ���
                map.put("telnum", telnum);
                
                // SIM����
                map.put("simnum", iccid);
                
                // �����ƷID
                map.put("mainprodiid", porductid);
                
                map.put("islocked", "1");
                
                map.put("isblankcard", "0");
                
                return intMsgUtil.invokeDubbo("CTCSTremCardCkeck", msgHeader, map);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            Element eReq_iccid = eBody.addElement("simnum");
            eReq_iccid.addText(iccid);
            
            /*
             * Element eReq_imsi = eBody.addElement("imsi"); eReq_imsi.addText(imsi);
             */

            Element eReq_porductid = eBody.addElement("mainprodiid");
            eReq_porductid.addText(porductid);
            
            Element eReq_lock = eBody.addElement("islocked");
            eReq_lock.addText("1");
            
            Element eReq_blank = eBody.addElement("isblankcard");
            eReq_blank.addText("0");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_ChkTelSimcard_hub",
                    menuID,
                    touchOID,
                    "0",
                    region,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("�ſ�У��ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    public ReturnWrap queryFeeItemList(Map<String, String> paramMap)
    {
        
        String telnum = (String)paramMap.get("telnum");
        String mainprodid = (String)paramMap.get("mainprodid");
        String prodtempletid = (String)paramMap.get("prodtempletid");
        String simnum = (String)paramMap.get("simnum");
        String blankcardno = (String)paramMap.get("blankcardno");
        
        String region = (String)paramMap.get("rtRegion");
        String operID = (String)paramMap.get("operid");
        String termID = (String)paramMap.get("termid");
        String menuID = (String)paramMap.get("menuid");
        String touchOID = (String)paramMap.get("touchoid");
        try
        {
            // add by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            if (IntMsgUtil.isTransEBUS("CTCSQueryCostList"))
            {
                // ��������ͷ��Ϣ
                // �ն˻�
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, operID, touchOID, "", MsgHeaderPO.ROUTETYPE_TELNUM,
                        telnum);
                
                // ���
                Map<String, String> map = new HashMap<String, String>();
                
                // �ֻ���
                map.put("telnum", telnum);
                
                map.put("mainprodiid", mainprodid);
                
                map.put("prodtempletid", prodtempletid);
                
                map.put("simnum", simnum);
                
                map.put("blankcardno", blankcardno);
                
                String[] outParamKeyDefine = {"entityname", "amount", "quantity", "expenessid"};
                return intMsgUtil.invokeDubbo("CTCSQueryCostList", msgHeader, map, outParamKeyDefine);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            Element eReq_mainprodid = eBody.addElement("mainprodiid");
            eReq_mainprodid.addText(mainprodid);
            
            Element eReq_prodtempletid = eBody.addElement("prodtempletid");
            eReq_prodtempletid.addText(prodtempletid);
            
            Element eReq_simnum = eBody.addElement("simnum");
            eReq_simnum.addText(simnum);
            
            Element eReq_blankcardno = eBody.addElement("blankcardno");
            eReq_blankcardno.addText(blankcardno);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_ReckonRecFee_hub",
                    menuID,
                    touchOID,
                    "0",
                    region,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("��ѯ������ϸʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯ��������
     * 
     * @param paramMap
     * @return author wWX191797 time 2014-04-14 for OR_HUB_201311_1069_����_�����ն�-����ѡ�Ź��������ʺ��뿪����Ϣչʾ����ϸ���
     */
    public ReturnWrap querySaleCond(Map<String, String> paramMap)
    {
        
        String telnum = (String)paramMap.get("telnum");
        
        String region = (String)paramMap.get("rtRegion");
        String operID = (String)paramMap.get("operid");
        String termID = (String)paramMap.get("termid");
        String menuID = (String)paramMap.get("menuid");
        String touchOID = (String)paramMap.get("touchoid");
        System.out.println("menuID:[" + menuID + "]");
        if (menuID == null || "".equals(menuID))
        {
            menuID = "choSernumAndOrdUse";
        }
        try
        {
            // add by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            if (IntMsgUtil.isTransEBUS("CTCSGetSaleTermFromNum"))
            {
                // ��������ͷ��Ϣ
                // �ն˻�
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, operID, touchOID, "", MsgHeaderPO.ROUTETYPE_TELNUM,
                        telnum);
                
                // ���
                Map<String, String> map = new HashMap<String, String>();
                
                // �ֻ���
                map.put("telnum", telnum);
                
                return intMsgUtil.invokeDubbo("CTCSGetSaleTermFromNum", msgHeader, map);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            System.out.println("telnum:[" + telnum + "]");
            System.out.println("doc:[" + doc + "]");
            System.out.println("menuID:[" + menuID + "]");
            System.out.println("touchOID:[" + touchOID + "]");
            System.out.println("region:[" + region + "]");
            System.out.println("operID:[" + operID + "]");
            System.out.println("termID:[" + termID + "]");
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            System.out.println("0000000000000000000000000");
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_LocQryTelSaleCond_CRSet",
                    menuID,
                    touchOID,
                    "0",
                    region,
                    operID,
                    termID);
            System.out.println("11111111111111111111111111");
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("��ѯ������ϸʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    public ReturnWrap commitInstallProd(Map<String, String> paramMap)
    {
        
        String telnum = (String)paramMap.get("telnum");
        String simnum = (String)paramMap.get("simnum");
        String mainprodiid = (String)paramMap.get("mainprodiid");
        String prodtempletid = (String)paramMap.get("prodtempletid");
        String sumprice = (String)paramMap.get("sumprice");
        String custname = (String)paramMap.get("custname");
        String certid = (String)paramMap.get("certid");
        String certtype = (String)paramMap.get("certtype");
        String linkaddr = (String)paramMap.get("linkaddr");
        String sex = (String)paramMap.get("sex");
        String passwd = (String)paramMap.get("passwd");
        String blankcard = (String)paramMap.get("blankcard");
        String imsi = (String)paramMap.get("imsi");
        String telprice = (String)paramMap.get("telprice");
        String linkphone = (String)paramMap.get("linkphone");
        String productlist = (String)paramMap.get("productlist");
        String submailaddr = (String)paramMap.get("submailaddr");
        
        String region = (String)paramMap.get("rtRegion");
        String operID = (String)paramMap.get("operid");
        String termID = (String)paramMap.get("termid");
        String menuID = (String)paramMap.get("menuid");
        String touchOID = (String)paramMap.get("touchoid");
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            Element eReq_simnum = eBody.addElement("simnum");
            eReq_simnum.addText(simnum);
            
            Element eReq_mainprodid = eBody.addElement("mainprodiid");
            eReq_mainprodid.addText(mainprodiid);
            
            Element eReq_prodtempletid = eBody.addElement("prodtempletid");
            eReq_prodtempletid.addText(prodtempletid);
            
            Element eReq_blankcardno = eBody.addElement("sumprice");
            eReq_blankcardno.addText(sumprice);
            
            Element eReq_custname = eBody.addElement("custname");
            eReq_custname.addText(custname);
            
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(certid);
            
            Element eReq_certtype = eBody.addElement("certtype");
            eReq_certtype.addText(certtype);
            
            Element eReq_linkaddr = eBody.addElement("linkaddr");
            eReq_linkaddr.addText(linkaddr);
            
            Element eReq_sex = eBody.addElement("sex");
            eReq_sex.addText(sex);
            
            Element eReq_passwd = eBody.addElement("passwd");
            eReq_passwd.addText(passwd);
            
            Element eReq_blankcard = eBody.addElement("blankcard");
            eReq_blankcard.addText(blankcard);
            
            Element eReq_imsi = eBody.addElement("imsi");
            eReq_imsi.addText(imsi);
            
            Element eReq_telprice = eBody.addElement("telprice");
            eReq_telprice.addText(telprice);
            
            Element eReq_linkphone = eBody.addElement("linkphone");
            eReq_linkphone.addText(linkphone);
            
            Element eReq_productlist = eBody.addElement("productlist");
            eReq_productlist.addText(productlist);
            
            Element eReq_submailaddr = eBody.addElement("submailaddr");
            eReq_submailaddr.addText(submailaddr);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_TerminalInstall_hub",
                    menuID,
                    touchOID,
                    "0",
                    region,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("����ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add by xkf57421 for OR_HUB_201202_1192 end
    /**
     * ֤��У�� ֤��У��
     * 
     * @param certType ֤������
     * @param certId ֤������
     * @param pesSubsNum ��غ���
     * @param region ����
     * @param operid ����ԱId
     * @param atsvNum �ն˻�ID
     * @param menuid ��ǰ�˵�Id
     * @return tagset �������
     * @since CommonLog1.0
     * @remark create yKF73963 2012-07-12 OR_HUB_201202_1192
     */
    public ReturnWrap certCardInfo(Map paramMap)
    {
        String certType = (String)paramMap.get("certtype");
        String certId = (String)paramMap.get("certid");
        String pesSubsNum = (String)paramMap.get("pessubsnum");
        String operid = (String)paramMap.get("curOper");
        String atsvNum = (String)paramMap.get("atsvNum");
        String menuid = (String)paramMap.get("curMenuId");
        String region = (String)paramMap.get("region");
        
        try
        {
            // add by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            if (IntMsgUtil.isTransEBUS("CTCSChkCertSubs"))
            {
                // ��������ͷ��Ϣ
                // �ն˻�
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuid, operid, atsvNum, "", MsgHeaderPO.ROUTETYPE_TELNUM,
                        pesSubsNum);
                
                // ���
                Map<String, String> map = new HashMap<String, String>();
                
                map.put("subsnum", pesSubsNum);
                map.put("certtype", certType);
                map.put("cretid", certId);
                
                return intMsgUtil.invokeDubbo("CTCSChkCertSubs", msgHeader, map);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // ֤������
            Element ecertType = eBody.addElement("certtype");
            ecertType.addText(certType);
            // ֤������
            Element ecertId = eBody.addElement("certid");
            ecertId.addText(certId);
            // ��غ���
            Element epesSubsNum = eBody.addElement("pessubsnum");
            epesSubsNum.addText(pesSubsNum);
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_ChkCertSubs_hub",
                    menuid,
                    null,
                    "0",
                    region,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("�����ն�֤��У��ӿڣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ȡSIM����Ϣ
     * 
     * @param
     * @param iccid sim��_iccid
     * @param region ����
     * @param operid ����ԱId
     * @param atsvNum �ն˻�ID
     * @param menuid ��ǰ�˵�Id
     * @return tagset �������
     * @since CommonLog1.0
     * @remark create yKF73963 2012-07-16 OR_HUB_201202_1192
     */
    public ReturnWrap getSimInfo(Map paramMap)
    {
        String iccid = (String)paramMap.get("iccid");
        String operid = (String)paramMap.get("curOper");
        String atsvNum = (String)paramMap.get("atsvNum");
        String menuid = (String)paramMap.get("curMenuId");
        String region = (String)paramMap.get("region");
        
        try
        {
            // add by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            if (IntMsgUtil.isTransEBUS("CTCSTremQuerySIM"))
            {
                // ��������ͷ��Ϣ
                // �ն˻�
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuid, operid, atsvNum, "", MsgHeaderPO.ROUTETYPE_REGION,
                        region);
                
                // ���
                Map<String, String> map = new HashMap<String, String>();
                
                // iccid
                map.put("iccid", iccid);
                
                return intMsgUtil.invokeDubbo("CTCSTremQuerySIM", msgHeader, map);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // sim����iccid
            Element eiccid = eBody.addElement("iccid");
            eiccid.addText(iccid);
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_GetSimInfo_hub",
                    menuid,
                    null,
                    "0",
                    region,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("�����ն˲�ѯSIM���ӿڣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ȡ����
     * 
     * @param
     * @param fitmod �����������
     * @param hlrid ����HLR
     * @param groupid HLR�����
     * @param istoretype ��ѯ���־
     * @param teltype ��ƷƷ��
     * @param prdtype ��������
     * @param maxcount ���ؼ�¼���������
     * @param region ����
     * @param operid ����ԱId
     * @param atsvNum �ն˻�ID
     * @param menuid ��ǰ�˵�Id
     * @return crset �������
     * @since CommonLog1.0
     * @remark create yKF73963 2012-07-16 OR_HUB_201202_1192
     */
    public ReturnWrap getTelnumbs(Map paramMap)
    {
        String operid = (String)paramMap.get("curOper");
        String atsvNum = (String)paramMap.get("atsvNum");
        String menuid = (String)paramMap.get("curMenuId");
        String region = (String)paramMap.get("region");
        
        String fitmod = (String)paramMap.get("fitmod");
        String hlrid = (String)paramMap.get("hlrid");
        String groupid = (String)paramMap.get("groupid");
        String istoretype = (String)paramMap.get("istoretype");
        String prdtype = (String)paramMap.get("prdtype");
        String teltype = (String)paramMap.get("teltype");
        String maxcount = (String)paramMap.get("maxcount");
        String pur = (String)paramMap.get("pur");
        
        try
        {
            // add by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            if (IntMsgUtil.isTransEBUS("CTCSGetNumListFromHLR"))
            {
                // ��������ͷ��Ϣ
                // �ն˻�
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuid, operid, atsvNum, "", MsgHeaderPO.ROUTETYPE_REGION,
                        region);
                
                // ���
                Map<String, String> map = new HashMap<String, String>();
                
                // fitmod
                map.put("fitmod", fitmod);
                // hlrid
                map.put("hlrid", hlrid);
                // groupid
                map.put("groupid", groupid);
                // istoretype
                map.put("istoretype", istoretype);
                // prdtype
                map.put("prdtype", prdtype);
                // teltype
                map.put("teltype", teltype);
                // maxcount
                map.put("maxcount", maxcount);
                // pur
                map.put("pur", pur);
                
                String[] outParamKeyDefine = {"telnum", "price", "region", "orgid"};
                return intMsgUtil.invokeDubbo("CTCSGetNumListFromHLR", msgHeader, map, outParamKeyDefine);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // sim����iccid
            Element efitmod = eBody.addElement("fitmod");
            efitmod.addText(fitmod);
            Element ehlrid = eBody.addElement("hlrid");
            ehlrid.addText(hlrid);
            Element egroupid = eBody.addElement("groupid");
            egroupid.addText(groupid);
            Element eistoretype = eBody.addElement("istoretype");
            eistoretype.addText(istoretype);
            Element eprdtype = eBody.addElement("prdtype");
            eprdtype.addText(prdtype);
            Element eteltype = eBody.addElement("teltype");
            eteltype.addText(teltype);
            Element emaxcount = eBody.addElement("maxcount");
            emaxcount.addText(maxcount);
            Element epur = eBody.addElement("pur");
            epur.addText(pur);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_TheHlrGrpNum_hub",
                    menuid,
                    null,
                    "0",
                    region,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("�����ն˻�ȡ����ӿڣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѡ����
     * 
     * @param telnum �绰����
     * @param region ����
     * @param operid ����ԱId
     * @param atsvNum �ն˻�ID
     * @param menuid ��ǰ�˵�Id
     * @return tagset �������
     * @since CommonLog1.0
     * @remark create yKF73963 2012-07-17 OR_HUB_201202_1192
     */
    public ReturnWrap chooseTheTelnum(Map paramMap)
    {
        String telnum = (String)paramMap.get("telnum");
        
        String operid = (String)paramMap.get("curOper");
        String atsvNum = (String)paramMap.get("atsvNum");
        String menuid = (String)paramMap.get("curMenuId");
        String region = (String)paramMap.get("region");
        try
        {
            // add by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            if (IntMsgUtil.isTransEBUS("CTCSTremNumberChoice"))
            {
                // ��������ͷ��Ϣ
                // �ն˻�
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuid, operid, atsvNum, "", MsgHeaderPO.ROUTETYPE_TELNUM,
                        telnum);
                
                // ���
                Map<String, String> map = new HashMap<String, String>();
                
                map.put("telnum", telnum);
                
                return intMsgUtil.invokeDubbo("CTCSTremNumberChoice", msgHeader, map);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // �绰����
            Element etelnum = eBody.addElement("telnum");
            etelnum.addText(telnum);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_TelNumTmpPick_hub",
                    menuid,
                    null,
                    "0",
                    region,
                    operid,
                    atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("�����ն˺�����ѡ�ӿڣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
        
    }
    
    /**
     * ��ѯ�û����Ƽ���ҵ���б�_����Ӫ���Ƽ��
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryRecommendProductList(MsgHeaderPO msgHeader)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLCSGetRecommendProductList"))
            {
                // ���
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // �Ƿ���Ҫ�ڲ�ѯ�Ƽ��б�Ľӿ��н��г�ͻ����ı�־ ���ձ�ʾ����Ҫ����
                inParamMap.put("isNeedChk", "");
                
                // �ֻ�����
                inParamMap.put("servNumber", msgHeader.getTelNum());
                
                // ��������
                inParamMap.put("accessType", "bsacAtsv");
                
                // ҵ���Ƽ������
                inParamMap.put("actID", "");
                
                // ��Ҫ��ѯ��ҵ���Ƽ���Ŀ
                inParamMap.put("showNum", "5");
                
                // �Ƿ��¼ҵ���Ƽ���Ϣ 0 ��
                inParamMap.put("isRecOrd", "1");
                
                // �Ƿ�չʾӪ��������ҵ���Ƽ� 0��
                inParamMap.put("isQueryReward", "1");
                
                // �Ƿ��¼������Ҫ�ظ��ļ�¼��
                inParamMap.put("isRecordfeedback", "1");
                
                String[] outParamDefine = {"oid", "entityName", "recmdDiction", "nCode", "prodid", "privid", "userSeq",
                        "kmsLink", "templateId", "timeType", "actid", "actName", "contactTimes", "notes", "prodName",
                        "privName", "recmdType", "isFeedBackDef", "type"};
                
                return getIntMsgUtil().invokeDubbo("BLCSGetRecommendProductList",
                        msgHeader,
                        inParamMap,
                        null,
                        outParamDefine);
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // modify begin wWX217192 2014-08-26 R003C14L08n01 OR_huawei_201408_740 ��׼Ӫ������_�����ն���������(�л�ΪEBUSЭ��)
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ��������
            DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
            
            // ҵ���Ƽ������
            DocumentUtil.addSubElementToEle(eBody, "actid", "");
            
            // add begin zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 ��׼Ӫ������_�����ն���������
            // ��Ҫ��ѯ��ҵ���Ƽ���Ŀ������5
            DocumentUtil.addSubElementToEle(eBody, "shownum", "5");
            
            // ��¼�Ƽ���Ϣ(��1)
            DocumentUtil.addSubElementToEle(eBody, "isrecord", "1");
            
            // �Ƿ��ѯӪ������(1 ��ѯ 0 ����ѯ) (��1)
            DocumentUtil.addSubElementToEle(eBody, "isqueryreward", "1");
            
            // �Ƿ��¼���ŷ�����(��1)
            DocumentUtil.addSubElementToEle(eBody, "isrecordfeedback", "1");
            // add end zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 ��׼Ӫ������_�����ն���������
            
            return intMsgUtil.invoke("cli_qry_recommendProductList", msgHeader, msgBody);
            // modify begin wWX217192 2014-08-26 R003C14L08n01 OR_huawei_201408_740 ��׼Ӫ������_�����ն���������(�л�ΪEBUSЭ��)
        }
        catch (Exception e)
        {
            log.error("��ѯ�û����Ƽ���ҵ���б�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��¼ҵ���Ƽ����_����Ӫ���Ƽ��
     * 
     * @param map
     * @return
     */
    public ReturnWrap recommendFeedback(MsgHeaderPO msgHeader, String userSeqs, String status, String actIds,
            String eventTypes)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLCSRecommendFeedback"))
            {
                // ���
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // �ֻ�����
                inParamMap.put("telNum", msgHeader.getTelNum());
                
                // ��������
                inParamMap.put("accessType", "bsacAtsv");
                
                // ����
                inParamMap.put("type", eventTypes);
                
                // ҵ���Ƽ������
                inParamMap.put("actid", actIds);
                
                // �Ƽ���ˮ��
                inParamMap.put("userSeq", userSeqs);
                
                // ״̬:�û��ѽӴ�,δ��Ӧ
                inParamMap.put("status", status);
                
                // ��ע
                inParamMap.put("outNotes", "");
                
                return getIntMsgUtil().invokeDubbo("BLCSRecommendFeedback", msgHeader, inParamMap);
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ��������
            DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
            
            // �Ƽ���ˮ��
            DocumentUtil.addSubElementToEle(eBody, "userseq", userSeqs);
            
            // ״̬:�û��ѽӴ�,δ��Ӧ
            DocumentUtil.addSubElementToEle(eBody, "status", status);
            
            // ��ע
            DocumentUtil.addSubElementToEle(eBody, "outnotes", "");
            
            // ҵ���Ƽ������
            DocumentUtil.addSubElementToEle(eBody, "actid", actIds);
            
            // ���� even��ʵʱӪ��������������ʵʱӪ������ǰ̨�Ƽ�cs_rec_commendsubs������
            DocumentUtil.addSubElementToEle(eBody, "type", eventTypes);
            
            return intMsgUtil.invoke("cli_busi_recommendFeedback", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            log.error("��¼ҵ���Ƽ����ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �Ƽ�ҵ������_����Ӫ���Ƽ��
     * 
     * @param map
     * @return
     */
    public ReturnWrap recommendProduct(MsgHeaderPO msgHeader, String userSeq, String actId, String recmdType)
    {
        try
        {
            // modify begin wWX217192 2014-08-26 OR_huawei_201408_740 ��׼Ӫ������_�����ն���������(�л�ΪEBUSЭ��)
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ��� EBUS�ӿڴ�BLCSRecommendProductת����BLCSRecommendProductByActID�ӿ�
            if (IntMsgUtil.isTransEBUS("BLCSRecommendProductByActID"))
            {
                // ���
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // �ֻ�����
                inParamMap.put("servNumber", msgHeader.getTelNum());
                
                // ��������
                inParamMap.put("accessType", "bsacAtsv");
                
                // �Ƽ���ˮ�� ��2014-08-26���EBUS�ӿ�Э���в����ڴ���� delete by wWX217192
                // inParamMap.put("userSeq", userSeq);
                
                // �����
                inParamMap.put("actID", actId);
                
                // �Ƽ�����
                inParamMap.put("recmdType", recmdType);
                
                return getIntMsgUtil().invokeDubbo("BLCSRecommendProductByActID", msgHeader, inParamMap);
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ��������
            DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
            
            // �û����к�
            DocumentUtil.addSubElementToEle(eBody, "userseq", userSeq);
            
            // ҵ���Ƽ������
            DocumentUtil.addSubElementToEle(eBody, "actid", actId);
            
            // �Ƽ����� -1չʾ�� 3Ӫ������ ��������Ʒ��
            DocumentUtil.addSubElementToEle(eBody, "recmdtype", recmdType);
            
            return intMsgUtil.invoke("cli_busi_recommendProductByActID", msgHeader, msgBody);
            // modify end wWX217192 2014-08-26 OR_huawei_201408_740 ��׼Ӫ������_�����ն���������(�л�ΪEBUSЭ��)
        }
        catch (Exception e)
        {
            log.error("�Ƽ�ҵ������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �Ƽ�ҵ�����ɹ�������ҵ���Ƽ����_����Ӫ���Ƽ��
     * 
     * @param map
     * @return
     */
    public ReturnWrap setRecommendSuccess(MsgHeaderPO msgHeader, String commendOID)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLCSSetRecommendSuccess"))
            {
                // ���
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // ��������
                inParamMap.put("accessType", "bsacAtsv");
                
                // ҵ���Ƽ�Ψһ����
                inParamMap.put("recommendOid", commendOID);
                
                return getIntMsgUtil().invokeDubbo("BLCSSetRecommendSuccess", msgHeader, inParamMap);
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ��������
            DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
            
            // ҵ���Ƽ�Ψһ��ˮ��
            DocumentUtil.addSubElementToEle(eBody, "commendoid", commendOID);
            
            return intMsgUtil.invoke("cli_busi_setRecommendSuccess", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            log.error("����ҵ���Ƽ������", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯ����������Ϣ�б�
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param actId ҵ���Ƽ������
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 ��׼Ӫ������_�����ն���������
     */
    public ReturnWrap qryFeedBackDefList(MsgHeaderPO msgHeader, String actId)
    {
        try
        {
            if (IntMsgUtil.isTransEBUS("BLCSGetFeedBackDef"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                inParamMap.put("actid", actId);
                
                String[] arrAttrsKey = {"recmdName", "nCode", "actinfo", "moContent"};
                
                return getIntMsgUtil().invokeDubbo("BLCSGetFeedBackDef", msgHeader, inParamMap, null, arrAttrsKey);
            }
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // �û��ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ҵ���Ƽ������
            DocumentUtil.addSubElementToEle(eBody, "actid", actId);
            
            // ���ú�̨�ӿ�
            return intMsgUtil.invoke("cli_qry_getFeedBackDefList", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            log.error("��ѯ�û�����������Ϣ�б�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ������������
     * 
     * @param telNum �û��ֻ�����
     * @param operId �ն˻�����Ա
     * @param termId �ն˻����
     * @param touchOID
     * @param menuId �˵�Id
     * @param actId ҵ���Ƽ������
     * @param contents �ظ�����
     * @param recmdType �Ƽ�����
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark add zKF69263 2014/07/17 R003C14L06n01 OR_HUB_201406_225 ��׼Ӫ������_�����ն���������
     */
    public ReturnWrap doFeedBackDef(MsgHeaderPO msgHeader, String actId, String contents, String recmdType)
    {
        try
        {
            if (IntMsgUtil.isTransEBUS("BLCSRecommendProdByResponse"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // �������
                inParamMap.put("servNumber", msgHeader.getTelNum());
                
                // ��������
                inParamMap.put("accessType", "bsacAtsv");
                
                // �����
                inParamMap.put("actID", actId);
                
                // �Ƽ�����
                inParamMap.put("recmdType", recmdType);
                
                // �ظ�����
                inParamMap.put("contents", contents);
                
                return getIntMsgUtil().invokeDubbo("BLCSRecommendProdByResponse", msgHeader, inParamMap);
                
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ��������
            DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
            
            // ҵ���Ƽ������
            DocumentUtil.addSubElementToEle(eBody, "actid", actId);
            
            // �ظ�����
            DocumentUtil.addSubElementToEle(eBody, "contents", contents);
            
            // �Ƽ����� -1չʾ�� 3Ӫ������ ��������Ʒ��
            DocumentUtil.addSubElementToEle(eBody, "recmdtype", recmdType);
            
            return intMsgUtil.invoke("cli_busi_recommendProdByReply", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            log.error("�û�������������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ͨ������� <������ϸ����>
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark modify by sWX219697 2014-9-22 OR_huawei_201409_422_����_�����ն˽���EBUS_�������
     */
    public ReturnWrap wbankpay(Map<String, String> map, MsgHeaderPO msgHead)
    {
        // �ֻ�����
        String telnum = map.get("telnum");
        
        // �����Ʒ��ncode
        String ncode = map.get("ncode");
        
        // ��������
        String oprtype = map.get("oprtype");
        
        // ��������
        String accesstype = map.get("accesstype");
        
        // ����(��)
        String contrastfee = map.get("contrastfee");
        
        try
        {
            
            // EBUS����
            if (IntMsgUtil.isTransEBUS("BLCSProductRec"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                inParamMap.put("nCode", ncode);
                inParamMap.put("servNum", telnum);
                inParamMap.put("operType", oprtype);
                inParamMap.put("contrastFee", contrastfee);
                
                // �ӿڵ���
                return intMsgUtil.invokeDubbo("BLCSProductRec", msgHead, inParamMap, new String[][] { {"formNumber"},
                        {"dealNum"}});
            }
            
            Document docXML = intMsgUtilNew.createMsg(map);
            
            Element root = docXML.getRootElement();
            Element body = root.element("Body");
            
            // cli_rec_wbandpay
            Element eReq_prodlistreq = body.addElement("cli_busi_wbandrec");
            
            // tagset
            Element eReq_tagset = eReq_prodlistreq.addElement("tagset");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eReq_tagset, "telnum", telnum);
            
            // �����Ʒ��ncode
            DocumentUtil.addSubElementToEle(eReq_tagset, "ncode", ncode);
            
            // ��������
            DocumentUtil.addSubElementToEle(eReq_tagset, "oprtype", oprtype);
            
            // ��������
            DocumentUtil.addSubElementToEle(eReq_tagset, "accesstype", accesstype);
            
            // ����(��)
            DocumentUtil.addSubElementToEle(eReq_tagset, "contrastfee", contrastfee);
            
            // ����
            return intMsgUtilNew.invoke(docXML);
        }
        catch (Exception e)
        {
            log.error("��ͨ������ѣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �����ն��˵�Эͬ��ѯ֮139email
     * 
     * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 �����������ն˼�������ʵ�ֶ�����Эͬ��ѯ�˵�����
     */
    public ReturnWrap billColQuery139Email(Map map)
    {
        String region = (String)map.get("region");
        String telnum = (String)map.get("SERVNUM");
        String qrytype = "0";
        String sendtype = "1";
        String triggertype = "1";
        String channel = "102";
        String modelid = "he1002";
        String sendprior = "01";
        String busicode = "ngsendmail";
        String starttime = DateUtilHub._getCurrentTime();
        String statustime = DateUtilHub._getCurrentTime();
        String isqrybill = "0";
        String cycle = (String)map.get("CYCLEMONTH");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // add by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            if (IntMsgUtil.isTransEBUS("CTCSQrySendEmail"))
            {
                // ��������ͷ��Ϣ
                // �ն˻�
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, operID, termID, touchOID, MsgHeaderPO.ROUTETYPE_TELNUM,
                        telnum);
                
                // ���
                Map<String, String> mapInParam = new HashMap<String, String>();
                
                mapInParam.put("telnum", telnum);
                mapInParam.put("region", region);
                mapInParam.put("qrytype", qrytype);
                mapInParam.put("sendtype", sendtype);
                mapInParam.put("triggertype", triggertype);
                mapInParam.put("channel", channel);
                mapInParam.put("modelid", modelid);
                mapInParam.put("sendprior", sendprior);
                mapInParam.put("busicode", busicode);
                mapInParam.put("starttime", starttime);
                mapInParam.put("statustime", statustime);
                mapInParam.put("isqrybill", isqrybill);
                mapInParam.put("cycle", cycle);
                mapInParam.put("billinfo", "0");
                
                return intMsgUtil.invokeDubbo("CTCSQrySendEmail", msgHeader, mapInParam);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            Element eReq_qrytype = eBody.addElement("qrytype");
            eReq_qrytype.addText(qrytype);
            Element eReq_sendtype = eBody.addElement("sendtype");
            eReq_sendtype.addText(sendtype);
            Element eReq_triggertype = eBody.addElement("triggertype");
            eReq_triggertype.addText(triggertype);
            Element eReq_channel = eBody.addElement("channel");
            eReq_channel.addText(channel);
            Element eReq_modelid = eBody.addElement("modelid");
            eReq_modelid.addText(modelid);
            Element eReq_sendprior = eBody.addElement("sendprior");
            eReq_sendprior.addText(sendprior);
            Element eReq_busicode = eBody.addElement("busicode");
            eReq_busicode.addText(busicode);
            // Element eReq_mailaddr = eBody.addElement("mailaddr");
            Element eReq_starttime = eBody.addElement("starttime");
            eReq_starttime.addText(starttime);
            Element eReq_statustime = eBody.addElement("statustime");
            eReq_statustime.addText(statustime);
            Element eReq_isqrybill = eBody.addElement("isqrybill");
            eReq_isqrybill.addText(isqrybill);
            Element eReq_cycle = eBody.addElement("cycle");
            eReq_cycle.addText(cycle);
            // Element eReq_billinfo = eBody.addElement("billinfo");
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_sendbillby139mail_hub",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѳ齱
     * 
     * @remark create yKF73963 ��2012-11-09�� OR_HUB_201209_706 ��������-�齱ƽ̨-�齱�ӿڸ���
     */
    public ReturnWrap mianFeiChouJiang(Map map)
    {
        String actId = (String)map.get("ACTID");
        String telnum = (String)map.get("SERVNUM");
        String channelId = (String)map.get("CHANNELID");
        
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("TELNUM");
            eReq_telnum.addText(telnum);
            
            Element eReq_actId = eBody.addElement("ACTID");
            eReq_actId.addText(actId);
            Element eReq_chanelId = eBody.addElement("CHANNELID");
            eReq_chanelId.addText(channelId);
            // Element eReq_userret = eBody.addElement("USERRET");
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_svc_busi_takeoutpraize",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �����ն��˵�Эͬ��ѯ֮����
     * 
     * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 �����������ն˼�������ʵ�ֶ�����Эͬ��ѯ�˵�����
     */
    public ReturnWrap billColQueryMessage(Map map)
    {
        String telnum = (String)map.get("SERVNUM");
        String billcycle = (String)map.get("CYCLEMONTH");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // add by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            if (IntMsgUtil.isTransEBUS("CTCSSmsNewBill"))
            {
                // ��������ͷ��Ϣ
                // �ն˻�
                MsgHeaderPO msgHeader = new MsgHeaderPO(menuID, operID, termID, touchOID, MsgHeaderPO.ROUTETYPE_TELNUM,
                        telnum);
                
                // ���
                Map<String, String> mapInParam = new HashMap<String, String>();
                
                // �ֻ���
                mapInParam.put("telnum", telnum);
                // ����
                mapInParam.put("billcycle", billcycle);
                
                return intMsgUtil.invokeDubbo("CTCSSmsNewBill", msgHeader, mapInParam);
            }
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            Element eReq_billcycle = eBody.addElement("billcycle");
            eReq_billcycle.addText(billcycle);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_smsnew_qry_bill2012",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �����ն��˵�Эͬ��ѯ֮����
     * 
     * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 �����������ն˼�������ʵ�ֶ�����Эͬ��ѯ�˵�����
     */
    public ReturnWrap billColQueryColorMessage(Map map)
    {
        String telnum = (String)map.get("SERVNUM");
        String billcycle = (String)map.get("CYCLEMONTH");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            // add begin by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            if (IntMsgUtil.isTransEBUS("CTCSQryMmsBillDetail"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // �绰����
                inParamMap.put("telNum", telnum);
                
                // �·�
                inParamMap.put("billCycle", billcycle);
                
                MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuID, operID, termID, touchOID, "1", telnum);
                
                return intMsgUtil.invokeDubbo("CTCSQryMmsBillDetail", msgHeaderPO, inParamMap);
            }
            // add end by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            Element eReq_billcycle = eBody.addElement("billcycle");
            eReq_billcycle.addText(billcycle);
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_selfTerm_qry_mmsbilldetail",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ����ȯ������Ϣ��ѯ
     * 
     * @param [����1] [����1˵��]
     * @param [����2] [����2˵��]
     * @return [��������˵��]
     * @exception/throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     * @depreced
     * @remark create yKF73963��2013-03-18�� OR_HUB_201301_780 ����BOSS�����ֻ�֧������ȯ�ķ������͵�����
     * 
     */
    public ReturnWrap qryEcashReturnInfo(Map map)
    {
        
        String startDate = (String)map.get("startDate");
        String endDate = (String)map.get("endDate");
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("servnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        String region = (String)map.get("region");
        
        try
        {
            // add begin by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            if (IntMsgUtil.isTransEBUS("CTCSQryEcacheReturnInfo"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                inParamMap.put("telNum", telnumber);
                
                // �Ƿ���� 0.�������� 1.������
                inParamMap.put("isSendmsg", "0");
                
                // ��ʼ����
                inParamMap.put("billCycle", startDate);
                
                // ��������
                inParamMap.put("billCycleEnd", endDate);
                
                // ������ʱ����Ϊ��
                inParamMap.put("templateNo", "");
                
                MsgHeaderPO msgHeaderPO = new MsgHeaderPO(menuid, operid, atsvNum, touchoid, "1", telnumber);
                
                String[] outParamKeyDefine = {"validCyle", "expireCyle", "procTime", "ecashValue", "leftValue",
                        "activityName", "retCycle", "totalCycles", "totalValue"};
                return intMsgUtil.invokeDubbo("CTCSQryEcacheReturnInfo", msgHeaderPO, inParamMap, outParamKeyDefine);
            }
            // add end by c00233019 [OR_HUB_201410_634]�����ն˱��ؿ�������ҵ��Ǩ�Ƶ�ebus
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // ��ʼ����
            Element eReq_startdate = eBody.addElement("startDate");
            eReq_startdate.addText(startDate);
            
            // ��������
            Element eReq_endDate = eBody.addElement("endDate");
            eReq_endDate.addText(endDate);
            // ����
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            // �Ƿ����
            Element eReq_sms = eBody.addElement("IS_SENDMSG");
            eReq_sms.addText("0");
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_self_ecashReturnInfoQuery_Hub",
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
            log.error("����ȯ������Ϣ��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��ѯ����ȯ����������Ϣ�����쳣��");
        }
    }
    
    /**
     * ������������
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param productset ��ͨ��ֵ��Ʒ��
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap gprsWlanRec(MsgHeaderPO msgHeader, String productset)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLCSChgOrChkMainProd"))
            {
                Map<String, String> inParamMap = new HashMap<String, String>();
                
                // �ֻ�����
                inParamMap.put("servNum", msgHeader.getTelNum());
                
                // ��������
                inParamMap.put("recType", "ChangeProduct");
                
                // ��ֵ��Ʒ��
                inParamMap.put("productSet", productset.trim());
                
                // ���÷���ӿ�
                return intMsgUtil.invokeDubbo("BLCSChgOrChkMainProd", msgHeader, inParamMap);
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ��������
            DocumentUtil.addSubElementToEle(eBody, "rectype", "ChangeProduct");
            
            // ��ͨ��ֵ��Ʒ��
            DocumentUtil.addSubElementToEle(eBody, "productset", productset);
            
            // ��������
            DocumentUtil.addSubElementToEle(eBody, "recdate", "");
            
            // ���ú�̨�ӿ�
            return intMsgUtil.invoke("cli_busi_GprsWlanRec", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            log.error("������������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ����ҵ������
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param spbizid spҵ�����
     * @param spid ��ҵ����
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create cKF76106 2013-05-14 OR_HUB_201305_29
     */
    public ReturnWrap spRec(MsgHeaderPO msgHeader, String spbizid, String spid)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLCSChangeSubsMonServ"))
            {
                // ���
                Map<String, Object> paramMap = new HashMap<String, Object>();
                
                // ҵ����Ϣ
                List<Map> detailInfo = new ArrayList<Map>();
                
                // modify begin hWX5316476 2014-9-24 OR_HUB_201305_29 �����ն˽���EBUS_��ѡ�ײ�
                Map<String, Object> detailInfomap = new HashMap<String, Object>();
                
                // ����ID��SP��ҵ����
                detailInfomap.put("spID", spid);
                
                // spҵ����룺����ҵ��ΪSPҵ����룬����Ϊ��ƷID
                detailInfomap.put("spBizID", spbizid);
                
                // ��������
                detailInfomap.put("operType", "Order");
                
                detailInfo.add(detailInfomap);
                // modify end hWX5316476 2014-9-24 OR_HUB_201305_29 �����ն˽���EBUS_��ѡ�ײ�
                
                // �ֻ�����
                paramMap.put("servNum", msgHeader.getTelNum());
                
                // ��ȡ��������
                paramMap.put("operType", "Order");
                
                // ҵ����Ϣ
                paramMap.put("detailInfo", detailInfo);
                
                // ��������Dubbo����
                return getIntMsgUtil().invokeDubbo("BLCSChangeSubsMonServ",
                        msgHeader,
                        paramMap,
                        new String[][] { {"orderResult", "formNumber"}, {"orderresult", "formnum"}});
            }
            
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ��ҵ����
            DocumentUtil.addSubElementToEle(eBody, "spid", spid);
            
            // spҵ������
            DocumentUtil.addSubElementToEle(eBody, "spbizid", spbizid);
            
            // ҵ������
            DocumentUtil.addSubElementToEle(eBody, "opertype", "Order");
            
            // ���ú�̨�ӿ�
            return intMsgUtil.invoke("cli_busi_SpRec", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            log.error("������������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ���û��������������� <������ϸ����>
     * 
     * @param map
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
     */
    public ReturnWrap sendSmsHub(Map map)
    {
        String telnum = (String)map.get("telnumber");
        String smsparam = (String)map.get("smsparam");
        String templateno = (String)map.get("templateno");
        String menuID = (String)map.get("menuID");
        String touchOID = (String)map.get("touchOID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("PTSendSmsInfo"))
        {
            // ���
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // ����ģ����
            inParamMap.put("TEMPLATENO", templateno);
            
            // �ֻ�����
            inParamMap.put("TELNUM", telnum);
            
            // �����б�
            String[] str = smsparam.split("#");
            
            for (int i = 0; i < str.length; i++)
            {
                inParamMap.put(str[i].substring(0, 1), str[i].substring(1));
            }
            
            return getIntMsgUtil().invokeDubbo("PTSendSmsInfo",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID,
                    inParamMap);
        }
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ����ģ����
            Element eReq_templateno = eBody.addElement("templateno");
            eReq_templateno.addText(templateno);
            
            // �����б�
            Element eReq_smsparam = eBody.addElement("smsparam");
            eReq_smsparam.addText(smsparam);
            
            // �Ƿ��������
            Element eReq_isrvcall = eBody.addElement("isrvcall");
            eReq_isrvcall.addText("1");
            
            Document docXML = intMsgUtil.createMsg(doc,
                    "cli_busi_sendsmsinfo",
                    menuID,
                    touchOID,
                    "1",
                    telnum,
                    operID,
                    termID);
            return intMsgUtil.invoke(docXML);
            
        }
        catch (Exception e)
        {
            log.error("���Ͷ���ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    // add begin jWX216858 2014/6/17 OR_HUB_201405_829_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ
    /**
     * ��ѯ���ڣ�������
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_HUB_201405_829_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ
     */
    @Override
    public ReturnWrap qryBillCycle(MsgHeaderPO msgHeader, String billCycle)
    {
        try
        {
            // add begin jWX216858 2014/09/16 R003C10LG0901 OR_huawei_201409_195 �����ն˽���EBUS_�½ᷢƱ
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLARBillCycleCustInfo"))
            {
                // ���
                Map<String, String> paraMap = new HashMap<String, String>();
                
                // �ֻ���
                paraMap.put("servNumber", msgHeader.getTelNum());
                
                // ���� YYYYMM��ʽ
                paraMap.put("cycleMonth", billCycle);
                
                ReturnWrap rw = intMsgUtil.invokeDubbo("BLARBillCycleCustInfo", msgHeader, paraMap, new String[] {
                        "cycle", "startDate", "endDate", "acctID", "isUnion"});
                return rw;
            }
            // add end jWX216858 2014/09/16 R003C10LG0901 OR_huawei_201409_195 �����ն˽���EBUS_�½ᷢƱ
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ���
            eBody.addElement("servnum").addText(msgHeader.getTelNum());
            
            // ����
            eBody.addElement("cycle").addText(billCycle);
            
            return intMsgUtil.invoke("cli_qry_billCycleCustInfo", msgHeader, doc);
        }
        catch (Exception e)
        {
            log.error("��ѯ����ʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ����ʱ֤������У��
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param certType ֤������
     * @param certId ֤������
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chkCertSubs(MsgHeaderPO msgHeader, String certType, String certId)
    {
        try
        {
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // ֤������
            inParamMap.put("certType", certType);
            
            // ֤������
            inParamMap.put("certID", certId);
            
            return intMsgUtil.invokeDubbo("PTGetMobileNumByCert", msgHeader, inParamMap);
        }
        catch (Exception e)
        {
            log.error("����ʱ֤������У��ӿڵ����쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "����ʱ֤������У��ӿڵ����쳣");
        }
    }
    
    /**
     * ����ѡ�Ź����ѯ�ֻ������б�
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param orgId ��֯����
     * @param selTelRule ѡ�Ź���
     * @param telPrefix ����ǰ׺
     * @param telSuffix �����׺
     * @param mainProdid �����ƷID
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryTelNumberListByRule(MsgHeaderPO msgHeader, String orgId, String selTelRule, String telPrefix,
            String telSuffix, String mainProdid)
    {
        try
        {
            // ���
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // ��λ
            inParamMap.put("orgid", orgId);
            
            // ��ѯ���� 2����ǰ׺��ѯ 3������׺��ѯ 4�����
            inParamMap.put("seleRule", selTelRule);
            
            // ����ǰ׺ sele_rule = 2ʱ�����û�����ƣ�Ϊ_______�������ƣ�������7λ�����油_ sele_rule = 3ʱ��Ϊ����
            inParamMap.put("telPrefix", telPrefix);
            
            // �����׺
            inParamMap.put("telSuffix", telSuffix);
            
            // ��ƷID
            inParamMap.put("prodid", mainProdid);
            
            // ���غ����б��ֻ����룬Ʒ����Ϣ��ѡ�ŷѣ���λ���֣�����λ��ԤԼʱʹ�ã������׷��ã�������ѣ�����λ���֣���Ԥ����ã���λ���֣�
            String[] outParamKeyDefine = {"telnum", "brand", "price", "orgid", "lowConsumFee", "lowConsumPre"};
            
            return intMsgUtil.invokeDubbo("BLCSResGetTelnumsForATM", msgHeader, inParamMap, outParamKeyDefine);
        }
        catch (Exception e)
        {
            log.error("����ѡ�Ź����ѯ�ֻ������б��쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "����ѡ�Ź����ѯ�ֻ������б��쳣");
        }
    }
    
    /**
     * ������Դ��ѡ�ӿ�
     * 
     * @param msgHeader
     * @param inParamMap ���
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap telNumTmpPick(MsgHeaderPO msgHeader, Map<String, String> inParamMap)
    {
        try
        {
            return intMsgUtil.invokeDubbo("BLCSLockOrUnLockTelNum", msgHeader, inParamMap);
        }
        catch (Exception e)
        {
            log.error("������Դ��ѡ�ӿڵ����쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "������Դ��ѡ�ӿڵ����쳣");
        }
    }
    
    /**
     * У��հ׿���Դ�Ƿ����
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param inParamMap ���
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chkBlankNo(MsgHeaderPO msgHeader, Map<String, String> inParamMap)
    {
        try
        {
            return intMsgUtil.invokeDubbo("BLCSCheckSaleRealSign", msgHeader, inParamMap);
        }
        catch (Exception e)
        {
            log.error("У��հ׿���Դ�Ƿ���ýӿڵ����쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "У��հ׿���Դ�Ƿ���ýӿڵ����쳣");
        }
    }
    
    /**
     * �հ׿���Դ��ѡ
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param blankNo �հ׿�����
     * @param telNum �ֻ�����
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap blankCardTmpPick(MsgHeaderPO msgHeader, String blankNo, String telNum)
    {
        try
        {
            // ���
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // �հ׿���
            inParamMap.put("blankCardNo", blankNo);
            
            // �ֻ�����
            inParamMap.put("telNum", telNum);
            
            return intMsgUtil.invokeDubbo("BLCSGetPersonalData", msgHeader, inParamMap);
        }
        catch (Exception e)
        {
            log.error("�հ׿���Դ��ѡ�쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "�հ׿���Դ��ѡ�����쳣");
        }
        
    }
    
    /**
     * У��հ׿��Ƿ���Ԥ�ÿտ�
     * 
     * @param termInfo �ն���Ϣ
     * @param curMenuId ��ǰ�˵�ID
     * @param blankNo �հ׿����к�
     * @param telNum �ֻ�����
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chkPreSetBlankCard(MsgHeaderPO msgHeader, String blankNo, String telNum)
    {
        try
        {
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // �հ׿���
            inParamMap.put("blankCardNo", blankNo);
            
            // �ֻ���
            inParamMap.put("severNumber", telNum);
            
            // �Ƿ񷵻�Ԥ�����ݣ����Դ�0�������0���������presetData�ǿյģ�
            inParamMap.put("retPreSetData", "0");
            
            // Ԥ�ÿտ���Ȩ���ݴ���ţ����գ�
            inParamMap.put("dataSeq", "");
            
            // �Ƿ���Ԥ�ÿտ����հ׿���Դ
            String[][] outParamKeyDefine = { {"isPresetBlankCard", "resTypeId"}, {"isPresetBlankCard", "resTypeId"}};
            
            return intMsgUtil.invokeDubbo("BLCSChkPreSetBlankCard", msgHeader, inParamMap, outParamKeyDefine);
        }
        catch (Exception e)
        {
            log.error("У��հ׿��Ƿ���Ԥ�ÿտ��ӿڵ����쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "У��հ׿��Ƿ���Ԥ�ÿտ��ӿڵ����쳣");
        }
    }
    
    /**
     * ��ȡ�½ᷢƱ��Ϣ��������
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_HUB_201405_829_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ
     */
    @Override
    public ReturnWrap getMonInvoiceData(MsgHeaderPO msgHeader, CyclePO cycle)
    {
        try
        {
            // add begin jWX216858 2014/09/16 R003C10LG0901 OR_huawei_201409_195 �����ն˽���EBUS_�½ᷢƱ
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLARIntPrintAddValueBillInv"))
            {
                // ���
                Map<String, String> map = new HashMap<String, String>();
                
                // �ֻ�����
                map.put("telNum", msgHeader.getTelNum());
                
                // ����
                map.put("billCycle", cycle.getCycle());
                
                // ��ʼʱ��
                map.put("startDate", cycle.getStartdate());
                
                // ����ʱ��
                map.put("endDate", cycle.getEnddate());
                
                // ���˺�
                map.put("acctID", cycle.getAcctid());
                
                ReturnWrap rw = intMsgUtil.invokeDubbo("BLARIntPrintAddValueBillInv", msgHeader, map, new String[] {
                        "invoiceTypeItem", "invoiceInfo"});
                if (SSReturnCode.SUCCESS == rw.getStatus())
                {
                    if (rw.getReturnObject() instanceof Vector)
                    {
                        Vector v = (Vector)rw.getReturnObject();
                        rw.setReturnObject((CRSet)v.get(1));
                    }
                }
                return rw;
            }
            // add end jWX216858 2014/09/16 R003C10LG0901 OR_huawei_201409_195 �����ն˽���EBUS_�½ᷢƱ
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            eBody.addElement("servnum").addText(msgHeader.getTelNum());
            
            // ����
            eBody.addElement("billCycle").addText(cycle.getCycle());
            
            // ��ʼʱ��
            eBody.addElement("startDate").addText(cycle.getStartdate());
            
            // ����ʱ��
            eBody.addElement("endDate").addText(cycle.getEnddate());
            
            // ���˺�
            eBody.addElement("acctId").addText(cycle.getAcctid());
            return intMsgUtil.invoke("cli_qry_monthinvoiceinfo", msgHeader, doc);
        }
        catch (Exception e)
        {
            log.error("�½ᷢƱ���ݲ�ѯʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ����ʱ�����ֻ���������֤��Ϣ�Ƿ�һ��
     * 
     * @param msgHeader
     * @param idCardNo
     * @return
     * @remark create by sWX219697 2014-10-25 13:59:39 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap checkReissueIdCard(MsgHeaderPO msgHeader, String idCardNo)
    {
        try
        {
            // ���
            Map<String, String> map = new HashMap<String, String>();
            
            // �ֻ�����
            map.put("servnum", msgHeader.getTelNum());
            
            // ���֤����
            map.put("certid", idCardNo);
            
            return intMsgUtil.invokeDubbo("BLCSChkCertByServnum", msgHeader, map);
        }
        catch (Exception e)
        {
            log.error("����ʱ�����ֻ���������֤��Ϣ�Ƿ�һ��ʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ����ҵ�����У��
     * 
     * @param msgHeader
     * @return
     * @remark create by sWX219697 2014-10-25 14:12:49 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap checkReissueNum(MsgHeaderPO msgHeader, String subscriber)
    {
        try
        {
            // ���
            Map<String, Object> map = new HashMap<String, Object>();
            
            // ���û���Ϣ�ַ���ת��map��ֱ�Ӵ�subscriber��������������
            Map<String, Object> subscriberMap = JSONObject.fromObject(subscriber);
            
            // �û���Ϣ
            map.put("subscriber", subscriberMap);
            
            Map<String, String> params = new HashMap<String, String>();
            
            params.put("isThrowException", "0");
            
            //ҵ������
            params.put("recType", "ChangeEnum");
            params.put("channelType", "ALL");
            params.put("subRecType", "");
            params.put("isRollBack", "0");
            
            //��ʾ����
            params.put("promptType", "before");
            
            //����list
            map.put("params", params);
            
            return intMsgUtil.invokeDubbo("PTCheckRecValid", msgHeader, map, new String[][] { {"retInfo"}, {"retInfo"}});
        }
        catch (Exception e)
        {
            log.error("����ҵ�����У��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
        
    }
    
    /**
     * ��ȡд����Ϣ��������
     * @param msgHead ������Ϣͷ
     * @param telnum �ֻ���
     * @param iccid ICCID
     * @param blankno �հ׿����к�
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-28 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����) 
     */
    public ReturnWrap getEncryptedData(MsgHeaderPO msgHead, Map<String, Object> inParamMap)
    {
        try
        {
            return intMsgUtil.invokeDubbo("BLCSWirteDataEncrypt", msgHead, inParamMap);
        }
        catch (Exception e)
        {
            log.error("��ȡд����Ϣ���������쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��ȡд����Ϣ���������쳣");
        }
    }
    
    /**
     * <�������>
     * <������ϸ����>
     * @param msgHeader
     * @param iccid
     * @param blankCardNum �հ׿�����
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-10-28 16:45:41 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap countReissueFee(MsgHeaderPO msgHeader, String iccid, String blankCardNum)
    {
        try
        {
            // ���
            Map<String, String> map = new HashMap<String, String>();
            
            // �ֻ�����
            map.put("servNum", msgHeader.getTelNum());
            
            //iccid
            map.put("enum", iccid);
            
            //�հ׿�����
            map.put("blankCardNo", blankCardNum);
            
            //�Ƿ�У����� 0:��У�飬1��У��
            map.put("isCheckRecValid", "1");
            
            String[] str = {"fee", "feeName", "chargeType", "discount"};
            
            return intMsgUtil.invokeDubbo("BLCSQryChangeEnumFee", msgHeader, map, str);
        }
        catch (Exception e)
        {
            log.error("������ѣ�", e);
            
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
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-10-30 13:57:27 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap reissueCommit(MsgHeaderPO msgHeader, String recFee, String payType, String blankno,
            SimInfoPO simInfo)
    {
        try
        {
            // ���
            Map<String, String> map = new HashMap<String, String>();
            
            // �ֻ�����
            map.put("servNumber", msgHeader.getTelNum());
            
            map.put("accessType", "bsacAtsv");
            
            //iccid
            map.put("enum", simInfo.getIccid());
            
            //imsi
            map.put("imsi", simInfo.getImsi());
            
            //�ܷ��� ��
            map.put("sumFee", recFee);
            
            //�Ƿ���⣬��0
            map.put("isDerateFee", "0");
            
            //�հ׿�����
            map.put("blankCardNo", blankno);
            
            //У�����
            map.put("isCheckRecValid", "1");
            
            //֧����ʽ
            map.put("payType", payType);
            
            //��1
            map.put("isNeedFee", "1");
            
            return intMsgUtil.invokeDubbo("BLCSChangeEnum", msgHeader, map);
        }
        catch (Exception e)
        {
            log.error("�����ύ��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯ�ֻ������Ƿ��б���
     * @param msgHeader
     * @param subsID 
     * @return
     * @remark create by c00233019 2014-10-25 13:59:39 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap qryStoreCard(MsgHeaderPO msgHeader, String subsID)
    {
        try
        {
            // ���
            Map<String, String> map = new HashMap<String, String>();
            
            // subsid
            map.put("subsID", subsID);
            
            return intMsgUtil.invokeDubbo("BLCSQrySubsstandbyCard", msgHeader, map);
        }
        catch (Exception e)
        {
            log.error("��ѯ�ֻ������Ƿ��б�����", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * <�������>
     * <������ϸ����>
     * @param msgHeader
     * @param iccid
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by c00233019 2014-10-29 16:45:41 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap reckonrecfeeByStore(MsgHeaderPO msgHeader, String servnum, String iccid)
    {
        try
        {
            // ���
            Map<String, String> map = new HashMap<String, String>();
            
            // �ֻ�����
            map.put("telNum", servnum);
            
            //iccid
            map.put("simCardNo", iccid);
            
            return intMsgUtil.invokeDubbo("BLCSStandByGetQryRecFee", msgHeader, map);
        }
        catch (Exception e)
        {
            log.error("������ѣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * <�����ύ>
     * <������ϸ����>
     * @param msgHeader
     * @param iccid
     * @param blankCardNum �հ׿�����
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by c00233019 2014-10-29 16:45:41 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap prepareCashCommit(MsgHeaderPO msgHeader, String servnum, String iccid, String tMoney,
            String payType)
    {
        
        try
        {
            // ���
            Map<String, String> map = new HashMap<String, String>();
            
            // �ֻ�����
            map.put("telNum", servnum);
            
            //iccid
            map.put("simCardNo", iccid);
            
            //��ҵ������
            map.put("subsRecType", "StandbyGetAtsv");
            
            //�ܷ��ã��û�Ͷ���
            map.put("sumFee", tMoney);
            
            //֧����ʽ
            map.put("payType", payType);
            
            return intMsgUtil.invokeDubbo("BLCSStandByGet", msgHeader, map);
        }
        catch (Exception e)
        {
            log.error("�����ύ��", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    public IntMsgUtilNew getIntMsgUtilNew()
    {
        return intMsgUtilNew;
    }
    
    public void setIntMsgUtilNew(IntMsgUtilNew intMsgUtilNew)
    {
        this.intMsgUtilNew = intMsgUtilNew;
    }
    
    /**
     * �հ׿�����
     * @param msgHead ������Ϣͷ
     * @param map ���
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap terminalInstall(MsgHeaderPO msgHead, Map<String, String> inParamMap)
    {
        try
        {
            return intMsgUtil.invokeDubbo("BLCSNetAgentInstall", msgHead, inParamMap);
        }
        catch (Exception e)
        {
            log.error("Ԥ�ÿհ׿������쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "Ԥ�ÿհ׿������쳣");
        }
    }
    
    /**
     * д��ʧ�� ���Ͽ��ӿ�
     * @param msgHead ������Ϣͷ
     * @param map ���
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap updateWriteCardResult(MsgHeaderPO msgHead, Map<String, String> inParamMap)
    {
        try
        {
            return intMsgUtil.invokeDubbo("BLCSWriteBlankCardFail", msgHead, inParamMap);
        }
        catch (Exception e)
        {
            log.error("���Ͽ��ӿڵ����쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "���Ͽ��ӿڵ����쳣");
        }
    }
    
    /**
     * У��д������ӿ�
     * @param msgHead ������Ϣͷ
     * @param map ���
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap checkWriteCardInfo(MsgHeaderPO msgHead, Map<String, String> inParamMap)
    {
        try
        {
            return intMsgUtil.invokeDubbo("BLCSWirteCardConfirmResult", msgHead, inParamMap);
        }
        catch (Exception e)
        {
            log.error("У��д������ӿڵ����쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "У��д������ӿڵ����쳣");
        }
    }
    
    /**
     * ���㿪������
     * @param msgHead ������Ϣͷ
     * @param map ���
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-31 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap reckonRecFee(MsgHeaderPO msgHead, Map<String, String> inParamMap)
    {
        try
        {
            // �������ƣ����(��)�������������������ã�Ӧ�շ���
            String[] outParamKeyDefine = {"feeName", "fee", "quantity", "feeID", "discount", "realfee"};
            
            return intMsgUtil.invokeDubbo("BLCSQryRecFeeForInstall", msgHead, inParamMap, outParamKeyDefine);
        }
        catch (Exception e)
        {
            log.error("���㿪�����ýӿڵ����쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "���㿪�����ýӿڵ����쳣");
        }
    }
    
    /**
     * <��ѯ�û���Ϣ>
     * <������ϸ����>
     * @param msgHeader
     * @param region
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-11-6 18:08:12 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap getSubscriberByTel(MsgHeaderPO msgHeader, String region)
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
            log.error("��ѯ�û���Ϣ�ӿڵ����쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "������ѯ�û���Ϣʧ��");
        }
        
    }
    
    /** ���ֻ��Ž���ʵ������֤
     * <������ϸ����>
     * @return String true:��֤֤�ɹ� ��������֤ʧ��
     * @see [�ࡢ��#��������#��Ա]
     * Create Author:<gWX223032> Time:<May 7, 2015> Ver:<OR_HUB_201504_412_����_���������ն˳��ؿ��ԤԼ���ܵ�����v1.1 >
     */
    @Override
    public ReturnWrap isRealName(MsgHeaderPO msgHeader)
    {
        try
        {
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // �ֻ���
            inParamMap.put("telNum", msgHeader.getTelNum());
            
            return intMsgUtil.invokeDubbo("BLCSUnionChkExistedCustInfo", msgHeader, inParamMap);
        }
        catch (Exception e)
        {
            log.error("�����Ƿ���ʵ�����û��ӿڵ����쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "�����Ƿ���ʵ�����û��ӿڵ����쳣");
        }
    }
    
    /** ���ԤԼ�ύ
     * <������ϸ����>
     * @param telNum ԤԼ�ֻ�����
     * @param currArea ԤԼ�ص�
     * @param currProd ԤԼ��Ʒ
     * @param iDcard   ���֤��
     * @param installDate ԤԼ��װʱ��
     * @param band  ����
     * @return String true:�ɹ�, ������ʧ��
     * @see [�ࡢ��#��������#��Ա]
     * Create Author:<gWX223032> Time:<May 7, 2015> Ver:<OR_HUB_201504_412_����_���������ն˳��ؿ��ԤԼ���ܵ�����v1.1 >
     * @modify fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_��BOSS�������󡿿��ҵ��ԤԼӪ����Ŀ������ӥ�ɣ�_�������˵����   
     */
    @Override
    public ReturnWrap broadBandAppoint(String currArea,String installDate, String cardIdNum, String currProd, String band, MsgHeaderPO msgHeader)
    {
        try
        {
            Map<String, String> inParamMap = new HashMap<String, String>();
            
            // �ֻ���
            inParamMap.put("telNum", msgHeader.getTelNum());
            
            // װ����ַ
            inParamMap.put("countryName", currArea);
            
            // add begin fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_��BOSS�������󡿿��ҵ��ԤԼӪ����Ŀ������ӥ�ɣ�_�������˵����
            // ����
            inParamMap.put("band", band);
            // ��Ʒ����
            inParamMap.put("prodName", currProd);
            // ���֤��
            inParamMap.put("certId", cardIdNum);
            
            // ԤԼʱ��  ��ʽyyyy-MM-dd
            String installDates = installDate.substring(0, 4) + "-" + installDate.substring(4, 6) + "-"
                    + installDate.substring(6, 8)+"  00:00:00";
            inParamMap.put("installDate", installDates);
            // add end fwx439896 2017-11-13 V200R005C20LG2301 OR_HUB_201708_259_��BOSS�������󡿿��ҵ��ԤԼӪ����Ŀ������ӥ�ɣ�_�������˵����
            
            return intMsgUtil.invokeDubbo("BLCSMBandPreInstallHUB", msgHeader, inParamMap);
        }
        catch (Exception e)
        {
            log.error("���ԤԼ�ύ�ӿڵ����쳣", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "���ԤԼ�ύ�ӿڵ����쳣");
        }
    }
    
    /**
     * <��ѯ�����ϸ�ӿ�>
     * <������ϸ����>
     * @param msgHeader
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by wWX217192 2016-03-31 OR_HUB_201602_493
     */
    public ReturnWrap showBalanceDetail(MsgHeaderPO msgHeader)
    {
    	try
    	{
    		Map<String, String> inParamMap = new HashMap<String, String>();
    		
    		// �ֻ�����
    		inParamMap.put("telNum", msgHeader.getTelNum());
    		
    		String[] outParam = {"categories", "subject", "range", "consumption", "validity", "destroy"};
    		
    		return intMsgUtil.invokeDubbo("BLARInterQryAllSubjectInfo", msgHeader, inParamMap, outParam);
    	}
    	catch(Exception e)
    	{
    		log.error("��ѯ�����ϸ�ӿڵ���ʧ��", e);
    		
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��ѯ�����ϸ�ӿڵ����쳣");
    	}
    }

}
