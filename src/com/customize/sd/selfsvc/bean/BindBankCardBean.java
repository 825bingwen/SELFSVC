/*
 * �� �� ��:  BindBankCardBean.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <���п����ܰ�>
 * �� �� ��:  zWX176560
 * �޸�ʱ��:  Sep 13, 2013
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <����>
 */
package com.customize.sd.selfsvc.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.sd.selfsvc.bean.impl.BaseBeanSDImpl;
import com.customize.sd.selfsvc.serviceinfo.client.ConnUtil;
import com.customize.sd.selfsvc.serviceinfo.model.BankCardInfoPO;
import com.customize.sd.selfsvc.serviceinfo.model.BindBankCardPO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.ReceptionException;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.inspur.pgtools.SignEncException2;
import com.inspur.pgtools.SignUtil2;

/**
 * <���п����ܰ�>
 * <������ϸ����>
 * 
 * @author  zWX176560
 * @version  2013/09/14 R003C13L08n01 OR_SD_201309_66
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class BindBankCardBean extends BaseBeanSDImpl
{
    /**
     * ���л�
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * ��־
     */
    private static Log logger = LogFactory.getLog(BindBankCardBean.class);
    
    /**
     * <У���û������п���Ϣ>
     * <������ϸ����>
     *  
     * @return
     * @throws Exception 
     * @see [�ࡢ��#��������#��Ա]
     */
    public Map<String, String> queryBindInfo(BindBankCardPO bindBankCardPO) throws Exception
    {
        // ��ˮ��
        String orderID = this.getRandomNumber();
        bindBankCardPO.setContactId(orderID);
        
        // ��������ͷ
        Map<String, String> inParam = new HashMap<String, String>();
        inParam.put("mobile",bindBankCardPO.getTelNum() );
        
        // ��������
        Document docContent = creatQueryBindXml(inParam, "processquerydrpreq");
        Document xmlDoc = this.createMsg(docContent,
                "processquerydrp",
                "recBindBankCard",
                bindBankCardPO.getContactId(),
                "1",
                bindBankCardPO.getTelNum(),
                "");
        
        // ��¼��ѯ�󶨹�ϵ����
        logger.info("��ѯ�󶨹�ϵ���ģ�"+xmlDoc.asXML());
        
        // �����������ز�ѯ����Ϣ
        ConnUtil connUtil = new ConnUtil();
        
        // �����˳���ѯ�󶨹�ϵurl
        String url = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_QRYBIND_URL);
        String xml = xmlDoc.asXML();
        
        // ���뼯
        String charset = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_CHARSET);
        String retMes = connUtil.sendXMLReq(url, xml, charset);
        
        // �������ر��Ļ�ȡ����
        Map<String, String> bindInfo = this.parseResponse(retMes);
         
    	/*Map<String, String> bindInfo = new HashMap<String, String>();
		bindInfo.put("STATUS", "1");
		bindInfo.put("USERNAME", "����ů");
		bindInfo.put("CARDNO", "5222545633255");
		bindInfo.put("BINDDATE", "20120908");*/
        
        return bindInfo;
    
    }
    
    /**
     * ����������
     * 
     * @param inParam
     * @param process_code
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public Document creatQueryBindXml(Map<String, String> inParam, String process_code)
    {
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement(process_code);
        Element tagSet = eBody.addElement("tagset");
        for (Entry<String, String> entry : inParam.entrySet())
        {
            tagSet.addElement(entry.getKey()).addText(entry.getValue());
        }
        return doc;
    }
    
    /**
     * ����XML������
     * 
     * @param ...
     * @return
     */
    public Document createMsg(Document docContent, String process_code, String menuid, String unicontact,
            String route_type, String route_value, String operatorid)
    {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMddHHmmss");
        DocumentFactory df = DocumentFactory.getInstance();
        Document doc = df.createDocument();
        String charset = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_CHARSET);
        doc.setXMLEncoding(charset);
        
        // ���ڵ�
        Element root = doc.addElement("Envelope");
        
        // �˵���
        Element head = root.addElement("Header");
        
        // ��Ϣͷ
        Element eHead = head.addElement("request_head");
        
        // ҵ�����
        Element eProssCode = eHead.addElement("process_code");
        eProssCode.addText(process_code);
        
        // ��֤��
        Element eVerifyCode = eHead.addElement("verify_code");
        eVerifyCode.addText("");
        
        // �˵���
        Element eMenu = eHead.addElement("menuid");
        if (null == menuid)
        {
            menuid = "";
        }
        eMenu.addText(menuid);
        
        // ����ʱ��
        Element eProcessTime = eHead.addElement("req_time");
        eProcessTime.addText(sdf.format(new Date()));
        
        // ������ˮ
        Element eSeq = eHead.addElement("req_seq");
        eSeq.addText("");
        
        // ·�ɣ����ڵ㣩
        Element eRoute = eHead.addElement("route");
        
        // ·������
        Element eRoute_type = eRoute.addElement("route_type");
        eRoute_type.addText(route_type);
        
        // ·���ֶ�ֵ
        Element eRoute_value = eRoute.addElement("route_value");
        eRoute_value.addText(route_value);
        
        // ͳһ�Ӵ�
        Element eUnicontact = eHead.addElement("unicontact");
        eUnicontact.addText(unicontact == null ? "":unicontact);
        
        // ���Ա�ʶ
        Element eTestFlag = eHead.addElement("testflag");
        eTestFlag.addText("0");
        
        // ������Ϣ�����ڵ㣩
        Element eChannelinfo = eHead.addElement("channelinfo");
        
        // ����ID
        Element eChannelid = eChannelinfo.addElement("channelid");
        eChannelid.addText("bsacNB");
        
        // ������id
        Element eUnitid = eChannelinfo.addElement("unitid");
        eUnitid.addText("HUAWEI");
        
        // ����Ա
        Element eOperatorid = eChannelinfo.addElement("operatorid");
        eOperatorid.addText(operatorid);
        
        // ��������
        Element eBody = root.addElement("Body");
        eBody.appendContent(docContent);
        
        return doc;
    }
    
    /**
     * �������ر�����Ϣ
     * 
     * @param response ���صı���
     * @return Map��ŷ�����ϢkeyΪ������ valueΪ����ֵ �������������� status 0��ʧ�� 1���ɹ� RETMSGΪ������Ϣ
     * @throws Exception 
     * @throws Exception 
     * @throws DocumentException
     * @see [�ࡢ��#��������#��Ա]
     */
    public Map<String, String> parseResponse(String response) throws Exception 
    {
        Map<String, String> returnMap = new HashMap<String, String>();
        Document doc = null;
        try
        {
            doc = DocumentHelper.parseText(response);
            
            // ��¼��ѯ�󶨹�ϵ���ر���
            logger.info("���ر��ģ�"+doc.asXML());
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
            throw new Exception("�Բ���ϵͳ���󣬲���ʧ�ܣ����Ժ����ԡ�");
            
        }
        Element root = doc.getRootElement();
        Element eHead = root.element("Header").element("response_head");
        
        String rspCode = null;
        String rspDesc = null;
        String pross_Code = null;
        
        // ���������� �õ�ҵ�����״̬
        if (eHead != null)
        {
            pross_Code = eHead.element("process_code").getText();
            Element eRetinfo = eHead.element("retinfo");
            
            rspCode = eRetinfo.element("retcode").getText();
            rspDesc = eRetinfo.element("retmsg").getText();
            
            returnMap.put("RETCODE", rspCode);
            returnMap.put("RETMSG", rspDesc);
            
            // ״̬ 0��ǩԼ 1�ѽ�Լ 2δǩԼ
            returnMap.put("STATUS", "1");
            
            // ��������벻��0��״̬��Ϊ0
            if (!"0".equals(rspCode))
            {
                returnMap.put("STATUS", "0");
            }
        }
        
        // ��ȡ������������
        Element body = root.element("Body");
        if (null != body)
        {
            Element ebody = body.element(pross_Code + "resp");
            if (null != ebody)
            {
                // ��ȡtagset�ڵ�
                Element tagSetElement = ebody.element("tagset");
                
                // ����tagset�ڵ��ȡ����ֵ
                if (null != tagSetElement)
                {
                    Iterator itChild = tagSetElement.elementIterator();
                    Element eChild = null;
                    while (itChild.hasNext())
                    {
                        eChild = (Element)itChild.next();
                        String key = eChild.getName().toUpperCase(Locale.getDefault());
                        String val = eChild.getText().replace("^_^", "&");
                        returnMap.put(key, val);
                    }
                }
            }
        }
        
        // ����
        return returnMap;
    }

    /**
     * У���û��Ƿ�ʵ��ע������Ƿ���ע����Ϣ
     * 
     * @param bindBankCardPO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public Map<String, String> checkIsFactNameRegist(TerminalInfoPO terminalInfoPO, NserCustomerSimp customer, String curMenuId)
    {      
        // ��װ����
        Map paramMap = new HashMap();
        
        // ���ò���Աid
        paramMap.put("operID", terminalInfoPO.getOperid());
        
        // �����ն˻�id
        paramMap.put("termID", terminalInfoPO.getTermid());
        
        // ���ÿͻ��ֻ���
        paramMap.put("telnum", customer.getServNumber());
        
        // ���ÿͻ��Ӵ�id
        paramMap.put("touchOID", customer.getContactId());
        
        // ���õ�ǰ�˵�
        paramMap.put("curMenuId", curMenuId);
        
        ReturnWrap rw = this.getSelfSvcCallSD().checkFactNameRegist(paramMap);
        if (rw != null && rw.getStatus() == SSReturnCode.SUCCESS)
        {
            // ��ȡ�û���Ϣ�ɹ�
            CTagSet tagset = (CTagSet) rw.getReturnObject();
            String returnMsg = rw.getReturnMsg();
            Map map = new HashMap();
            
            //���÷��ؽ��
            map.put("returnObj", tagset);
            
            //���÷�����Ϣ
            map.put("returnMsg", returnMsg);
            
            return map;
        }
        
        return null;
    }

    /**
     * ���ܰ�ģʽ�ύ
     * 
     * @param bindBankCardPO 
     * @return
     * @throws Exception 
     * @see [�ࡢ��#��������#��Ա]
     * @remark create xKF69944 Aug 20, 2013[��������]
     */
    public Map<String, String> noEncrptyBindComit(BindBankCardPO bindBankCardPO) throws Exception
    {
        logger.info("noEncrptyBindComit...");
        
        // ǩ������
        StringBuffer signBuffer = new StringBuffer();
        
        // ��������
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("pantype", bindBankCardPO.getBankCardType());// ��Ƭ���� 0:��ǿ� 1:���ǿ�
        paramMap.put("customer_mobile", bindBankCardPO.getTelNum());// ��Ч��ɽ���ƶ��ֻ���
        paramMap.put("bank_acc", bindBankCardPO.getBankCardNum());// ���п���
        paramMap.put("user_name", bindBankCardPO.getUserFactName());// ����
        paramMap.put("id_type", bindBankCardPO.getId_type());// ֤������
        paramMap.put("id_num", bindBankCardPO.getIdCardNum());// ���֤��
        paramMap.put("cvn2", bindBankCardPO.getCvn2());// �������ÿ�������д���ֶ�
        paramMap.put("expire", bindBankCardPO.getExpire());// ��Ч��
        
        // ����������
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("root");
        document.setRootElement(rootElement);
        this.createNoEncrptyHead(rootElement.addElement("head"), signBuffer);
        this.createNoEncrptyBody(rootElement.addElement("body"), paramMap, signBuffer);
        
        // ��¼�����п����ͱ���
        logger.info("��¼�����п����ͱ��ģ�"+document.asXML());
        
        // �˳��ṩ�����ܰ��ύURL
        String url = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_NOENCRPTY_URL);
        
        // ���뼯
        String charset = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_CHARSET);
        
        // ���ͱ���
        ConnUtil connUtil = new ConnUtil();
        String xml = document.asXML();
        String retMes = connUtil.sendXMLReq(url, xml, charset);
        
        // �����ؽ��
        Document doc = DocumentHelper.parseText(retMes);
        
        // ��¼�����п����ر���
        logger.info("��¼�����п����ر��ģ�"+doc.asXML());
        Map<String, String> retMap = new HashMap<String, String>();
        this.parseXml(doc.getRootElement(), retMap);
        
        // У��ǩ��
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append("appcode=" + retMap.get("appcode") + "&");
        sBuffer.append("funcode=" + retMap.get("funcode") + "&");
        sBuffer.append("time=" + retMap.get("time") + "&");
        sBuffer.append("rtn_code=" + retMap.get("rtn_code") + "&");
        sBuffer.append("pg_order_code=" + retMap.get("pg_order_code") + "&");
        sBuffer.append("app_flow_code=" + retMap.get("app_flow_code"));
        if (!SignUtil2.verify(sBuffer.toString(), retMap.get("pg_sign")))
        {
            retMap.clear();
        }
        
        // ����
        return retMap;
    }
    
    /**
     * �������ܰ�ģʽ�ύ_ͷ��Ϣ
     * 
     * @param element
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create xKF69944 Aug 20, 2013[��������]
     */
    public Element createNoEncrptyHead(Element element, StringBuffer sign)
    {
        // �׳�ֵϵͳ����
        element.addElement("appcode").setText("07");
        sign.append("appcode=07&");
        
        // ������
        element.addElement("funcode").setText("0006");
        sign.append("funcode=0006&");
        
        // ����ʱ��
        String date = CommonUtil.getCurrentTime();
        element.addElement("time").setText(date);
        sign.append("time="+date+"&");
        
        // ����
        return element;
    }
    
    /**
     * �������ܰ�ģʽ�ύ_����
     * 
     * @param element
     * @param paramMap ��������
     * @param sign ǩ��
     * @return
     * @throws SignEncException2 
     * @see [�ࡢ��#��������#��Ա]
     * @remark create xKF69944 Aug 20, 2013[��������]
     */
    public Element createNoEncrptyBody(Element element, Map<String, String> paramMap, StringBuffer sign) throws SignEncException2
    {   
        // ������ˮ�Ż򶩵���
        String orderID = this.getRandomNumber();
        element.addElement("app_flow_code").setText(orderID);
        sign.append("app_flow_code="+orderID+"&");
        
        // ��Ƭ���� 0����ǿ� 1�����ǿ�
        element.addElement("pantype").setText(paramMap.get("pantype"));
        sign.append("pantype=" + paramMap.get("pantype") + "&");
        
        // �ͻ��ֻ�����
        element.addElement("customer_mobile").setText(paramMap.get("customer_mobile"));
        
        // ���п���
        element.addElement("bank_acc").setText(paramMap.get("bank_acc"));
        sign.append("bank_acc=" + paramMap.get("bank_acc") + "&");
        
        // ����
        element.addElement("user_name").setText(paramMap.get("user_name"));
        sign.append("user_name=" + paramMap.get("user_name") + "&");
        
        // ֤������
        element.addElement("id_type").setText(paramMap.get("id_type"));
        sign.append("id_type=" + paramMap.get("id_type") + "&");
        
        // ���֤��
        element.addElement("id_num").setText(paramMap.get("id_num"));
        sign.append("id_num=" + paramMap.get("id_num"));
        
        // CVN2
        element.addElement("cvn2").setText(paramMap.get("cvn2"));
        element.addElement("expire").setText(paramMap.get("expire"));
        element.addElement("app_sign").setText(SignUtil2.sign(sign.toString()));
        //element.addElement("app_sign").setText("******");
        
        return element;
    }
    
    /**
     * ������Ӧ��xml����
     * 
     * @param element
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public  Map<String, String> parseXml(Element element, Map<String, String> map)
    {
        List<Element> list = element.elements();
        while (list.size() > 0)
        {
            for (Element element2 : list)
            {
                this.parseXml(element2, map);
            }
            break;
        }
        if (list.size() == 0)
        {
            map.put(element.getName(), element.getText().trim());
        }
        return map;
    }
    
    /**
     * �����ֵ
     * 
     * @param a intֵ
     * @return a�ľ���ֵ
     */
    public String abs(int a)
    {
        int b = (a < 0) ? -a : a;
        return Integer.toString(b);
    }
    
    /**
     * <һ�仰���ܼ���>
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String getRandomNumber()
    {
        //return System.currentTimeMillis() + "" + ((int)(Math.random()*9000) + 1000);
        return System.currentTimeMillis() + "" + (new Random().nextInt(8999) + 1000);
    }

    /**
     * ���������
     * 
     * @param bindBankCardPO �û���Ϣ
     * @return
     * @throws Exception
     * @remark 
     * @see [�ࡢ��#��������#��Ա]
     */
    public Map<String, String> unBindBankCard(BindBankCardPO bindBankCardPO) throws Exception
    {
        // ��ˮ��
        String orderID = this.getRandomNumber();
        bindBankCardPO.setContactId(orderID);
        
        // ����������
        Map<String, String> inParam = new HashMap<String, String>();
        inParam.put("mobile", bindBankCardPO.getTelNum());
        Document docContent = creatQueryBindXml(inParam, "processopendrpreq");
        
        // ��������
        Document xmlDoc = this.createMsg(docContent,
                "processopendrp",
                "recBindBankCard",
                bindBankCardPO.getContactId(),
                "1",
                bindBankCardPO.getTelNum(),
                "");
         // ��¼��ѯ�󶨹�ϵ����
         logger.info("��󶨹�ϵ���ģ�"+xmlDoc.asXML());
          
         // �����������ز�ѯ����Ϣ
         ConnUtil connUtil = new ConnUtil();
          
         // �����˳���ѯ�󶨹�ϵurl
         String url = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_UNBIND_URL);
         String xml = xmlDoc.asXML();
          
         // ���뼯
         String charset = (String)PublicCache.getInstance().getCachedData(Constants.SH_EASYPAYCHANGE_CHARSET);
         String retMes = connUtil.sendXMLReq(url, xml, charset);
         
         // �������ر��Ļ�ȡ����
         Map<String, String> bindInfo = this.parseResponse(retMes);
         
         return bindInfo;
    }
    
    /**
     * �����п����ֻ������Զ����Ѳ���
     * 
     * @param customerSimp
     * @param termInfo
     * @param curMenuId
     * @param oprtype �������ͣ���ѡ 0����ѯ��1����ͨ�Զ��ɷѣ�2���ر��Զ��ɷѣ�3:���£�
     * @param trigamt Ԥ�����û�������� ��
     * @param drawamt Ԥ�����û��Զ���ֵ��� ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @modify yWX163692 2013��11��19�� OR_SD_201309_940 �׳�ֵ���׶Σ���Լ�����Զ������ж�����  
     * @remark modify by sWX219697 2014-10-7 14:21:15 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
     */
    public Map<String,String> bindAutoFeeSet(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, 
    		String curMenuId, String oprtype, String trigamt, String drawamt)
    {
        // ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
        		customerSimp.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customerSimp.getServNumber());
        
    	// ���ýӿڽ����Զ��ɷѲ�������ѯ����ͨ���رգ�
        ReturnWrap rw = this.getSelfSvcCallSD().autoFeeSet(msgHeader, oprtype, trigamt, drawamt);

        Map<String, String> retMap = new HashMap<String, String>();
        
        //���óɹ�����ѯ��ͨ�ɹ�
		if(SSReturnCode.SUCCESS == rw.getStatus())
		{
			if(rw.getReturnObject() instanceof CTagSet)
			{
				CTagSet tagSet = (CTagSet)rw.getReturnObject();

				//������� ��
				retMap.put("trigamt", tagSet.GetValue("trigamt"));
				
				//�Զ���ֵ��� ��
				retMap.put("drawamt", tagSet.GetValue("drawamt"));
			}
			
			//�û��ѿ�ͨ�Զ���ֵ
			retMap.put("autoStatus", "0");
		}
		
		//��ѯʱ���û�δ��ͨ�Զ����ѹ���
		else if(1 == rw.getErrcode() && "0".equals(oprtype))
		{
			
			//�û�δ��ͨ�Զ���ֵ
			retMap.put("autoStatus", "1");
		}
		else
		{
			throw new ReceptionException("�û��Զ���������ʧ�ܣ�" + rw.getReturnMsg());
		}
		
		return retMap;
    }

    /**
     *  �׳�ֵǩԼ֮ǰ����Ƿ������Ʒ��ͨ����
     *  
     * @param customerSimp���û���Ϣ
     * @param termInfo���ն���Ϣ
     * @param nCode
     * @param operType���������ͣ���ͨ����ȡ��
     * @param effectType
     * @param param
     * @param menuID
     * @param isSubmit �ύ��ʽ 0ֻ���鷵��  Ϊ�ջ�������ֵʱ�ύ
     * @param executecmd �Ƿ��ύ�Զ��ύ ���Զ��ύ ��Ϊ�շ��Զ��ύ
     * @return ReturnWrap
     * @see
     */
    public ReturnWrap checkProCondition(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, String nCode, String operType,
            String effectType, String param, String isSubmit, String executecmd, String menuID)
    {
        Map map = new HashMap();
        map.put("servNumber", customerSimp.getServNumber());
        map.put("nCode", nCode);
        map.put("operType", operType);
        map.put("effectType", effectType);
        map.put("param", param);
        map.put("menuID", menuID);
        map.put("contactID", customerSimp.getContactId());
        map.put("operID", termInfo.getOperid());
        map.put("termID", termInfo.getTermid());
        map.put("isSubmit", isSubmit);
        map.put("executecmd", executecmd);
        
        ReturnWrap rw = this.getSelfSvcCallSD().checkProCondition(map);
        
        return rw;
    }
    
    /**
     * <��ѯ�û��ĸ�������>
     * <������ϸ����>
     * @param customerSimp
     * @param termInfo
     * @param curMenuId
     * @return PREPAYTYPE 0���󸶷� 1��Ԥ���� 9����ѯ�󶨵��û�������
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-10-7 14:21:15 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
     */
    public Map<String, String> qrySubsPrepayType(NserCustomerSimp customerSimp, TerminalInfoPO termInfo, 
    		String curMenuId)
    {
        // ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
            "", MsgHeaderPO.ROUTETYPE_TELNUM, customerSimp.getServNumber());
        
        //��ѯ�û�����
        ReturnWrap rw = this.getSelfSvcCallSD().qrySubsPrepayType(msgHeader);
		
        Map<String, String> retMap = new HashMap<String, String>();
        
        //���óɹ������ظ�������
		if(SSReturnCode.SUCCESS == rw.getStatus())
		{
			CTagSet tagSet = (CTagSet)rw.getReturnObject();
			
			String payType = tagSet.GetValue("prepaytype");
			
			//��������
			if ("1".equals(payType) || "0".equals(payType))
			{
				retMap.put("payType", payType);
				return retMap;
			}
			else
			{
				throw new ReceptionException("�û��������Ͳ�ѯʧ�ܣ��û�������");
			}
		}
		
		throw new ReceptionException("�û��������Ͳ�ѯʧ�ܣ�"+rw.getReturnMsg());		
    }
    
    /**
     * <��ѯ�û��������б���ˮ��>
     * <������ϸ����>
     * @param customerSimp
     * @param termInfo
     * @param curMenuId
     * @param ncode ZLWGQY
     * @param stype QRY
     * @see [�ࡢ��#��������#��Ա]
     * @remark sWX219697 2014-12-2 18:30:04 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
     */
    public Map<String, Object> viceNumberQry(NserCustomerSimp customer, TerminalInfoPO termInfo, String curMenuId, 
    		String ncode, String stype)
    {
        // ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
        		customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        //������ˮ�߲�ѯ�ӿ�
        ReturnWrap rw = this.getSelfSvcCallSD().viceNumberQry(msgHeader, ncode, stype);
        
        //��ѯ�ɹ�
        if(SSReturnCode.SUCCESS == rw.getStatus())
        {
            //�������б�
            List<BankCardInfoPO> viceNumList = new ArrayList<BankCardInfoPO>();
            Map<String, Object> retMap = new HashMap<String, Object>();
            
            //����CTagset
        	CTagSet tagSet = (CTagSet)rw.getReturnObject();
        	
        	//������ָ��� ~
        	String outparamsplit = tagSet.GetValue("outparamsplit");
        	
        	//13963478598~18660165052~15966640687~FriendNum3~FriendNum4~FriendNum5~FriendNum6~FriendNum7~FriendNum8~FriendNum9
        	String outparam = tagSet.GetValue("outparam");
        	
        	//����������
        	String[] viceArray = outparam.split(outparamsplit);
        	
        	//�û��Ѱ󶨵ĸ����봮�����ڷ�ֹ�û������ظ�������
        	StringBuffer oldViceNumber = new StringBuffer("");
        	
        	//����������������
        	if(StringUtils.isNotBlank(outparam) && (null != viceArray && viceArray.length > 0))
        	{
        		for (String viceNum : viceArray)
        		{
        			if (viceNum.indexOf("FriendNum") == -1)
        			{
        				BankCardInfoPO bankCardInfoPO = new BankCardInfoPO();
        				bankCardInfoPO.setViceNumber(viceNum);
        				viceNumList.add(bankCardInfoPO);
        				
        				//�������ַ��� ��ʽ��13963478598~18660165052~15966640687
        				if("".equals(oldViceNumber.toString()))
        				{
        					oldViceNumber.append(viceNum);
        				}
        				else
        				{
        					oldViceNumber.append("~").append(viceNum);
        				}
        			}
        		}
        	}
        	
        	//��װ����ֵ
        	retMap.put("viceNumList", viceNumList);
        	retMap.put("oldViceNumber", oldViceNumber.toString());
        	
        	return retMap;
        }
		
        throw new ReceptionException("�������ѯʧ�ܣ�" + rw.getReturnMsg());
    }
    
    /**
     * <������ɾ��������>
     * <������ϸ����>
     * @param customer
     * @param termInfo
     * @param curMenuId
     * @param viceArray �������б�
     * @param stype �������ͣ�1��������2��ɾ��
     * @see [�ࡢ��#��������#��Ա]
     * @remark sWX219697 2014-12-4 11:44:40 OR_SD_201408_1190_ɽ��_���������ն����׳�ֵҵ���Ż�������
     */
    public void viceNumberSet(NserCustomerSimp customer, TerminalInfoPO termInfo, String curMenuId, 
    		String[] viceArray, String opertype)
    {
        // ��������ͷ��Ϣ
        MsgHeaderPO msgHeader = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
        		customer.getContactId(), MsgHeaderPO.ROUTETYPE_TELNUM, customer.getServNumber());
        
        //������ˮ�߲�ѯ�ӿ�
        ReturnWrap rw = this.getSelfSvcCallSD().viceNumberSet(msgHeader, viceArray, opertype);
        
        //����ʧ�ܣ����׳��쳣
        if(SSReturnCode.ERROR == rw.getStatus())
        {
        	throw new ReceptionException("1".equals(opertype) ? "��Ӹ�����ʧ�ܣ�" : "ɾ��������ʧ�ܣ�"+rw.getReturnMsg());
        }
    }
    
    /**
     * ��ѯ��ǰ�û��Ƿ�ǩԼ�Ͱ��׳�ֵ
     * @param termInfo
     * @param customer
     * @param curMenuId
     * @return
     * @remark create by wWX217192 2014-11-25 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
     */
    public Map<String, Object> checkHeBao(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String bankCardNum)
    {
    	MsgHeaderPO headerPo = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(), 
    			customer.getContactId(), "1", customer.getServNumber());
    	
    	ReturnWrap rw = getSelfSvcCallSD().checkHeBao(headerPo, bankCardNum);
    	
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	
    	if(SSReturnCode.SUCCESS == rw.getStatus())
    	{
			resultMap.put("status", rw.getStatus());
			
			// ���ӿڷ��ؽ����������
			CTagSet set = (CTagSet) rw.getReturnObject();
			
			if("0".equals(set.GetValue("status")))
			{
				resultMap.put("returnObj", set);
			}
    	}
    	else
    	{
    		// �ӿڵ���ʧ�ܣ�ϵͳҳ��Ǩ��������ҳ��
    		resultMap.put("status", rw.getStatus());
    		resultMap.put("errMsg", rw.getReturnMsg());
    	}
    	
    	return resultMap;
    }
    
    /**
     * �Ͱ��׳�ֵ���Ͷ��������֤��
     * @param termInfo
     * @param customer
     * @param curMenuId
     * @param smsType
     * @param cardPo
     * @return
     * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
     */
    public Map<String, String> sendHeBaoRandom(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String smsType, BindBankCardPO cardPo)
    {
    	MsgHeaderPO headerPo = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
    			customer.getContactId(), "1", customer.getServNumber());
    	
    	// ���úͰ��׳�ֵ���Ͷ��������ӿ�
    	ReturnWrap rw = getSelfSvcCallSD().sendHeBaoRandom(headerPo, smsType, cardPo);
    	
    	// ��װ�ӿڷ�����Ϣ
    	Map<String, String> resultMap = new HashMap<String, String>();
    	
    	// �ӿڵ��óɹ����ҷ��ؽ�����ˮ��
    	if(SSReturnCode.SUCCESS == rw.getStatus())
    	{
    		CTagSet set = (CTagSet)rw.getReturnObject();
    		
    		// ���Ͷ�Ϣ���������ɵĽ�����ˮ��
    		resultMap.put("tradeNo", set.GetValue("tradeNo"));
    	}
    	// �ӿڵ���ʧ��
    	else
    	{
    		resultMap.put("retMsg", rw.getReturnMsg());
    	}
    	
    	return resultMap;
    }
    
    /**
     * �Ͱ��׳�ֵǩԼ�ӿ�
     * @param termInfo
     * @param customer
     * @param curMenuId
     * @param smsCode
     * @param cardPo
     * @return
     * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
     */
    public Map<String, String> signHeBao(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String smsCode, BindBankCardPO cardPo)
    {
    	MsgHeaderPO headerPo = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
    			customer.getContactId(), "1", customer.getServNumber());
    	
    	ReturnWrap rw = getSelfSvcCallSD().signHeBao(headerPo, cardPo, smsCode);
    	
    	// ��װ�ӿڷ�����Ϣ
    	Map<String, String> resultMap = new HashMap<String, String>();
    	
    	// �ӿڵ��óɹ����ҷ��ؽ�����ˮ��
    	if(SSReturnCode.SUCCESS == rw.getStatus())
    	{
    		CTagSet set = (CTagSet) rw.getReturnObject();
    		
    		// ǩԼ�ɹ��󷵻ؿ��֧��Э���
    		resultMap.put("AGRNO", set.GetValue("AGRNO"));
    	}
    	// �ӿڵ���ʧ�ܣ����ؽӿڱ�����Ϣ
    	else
    	{
    		resultMap.put("retMsg", rw.getReturnMsg());
    	}
    	
    	return resultMap;
    }
    
    /**
     * �Ͱ��׳�ֵ��Լ�ӿ�
     * @param termInfo
     * @param customer
     * @param curMenuId
     * @param smsCode
     * @param cardPo
     * @remark create by wWX217192 2014-11-27 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
     */
    public void unsignHeBao(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, String smsCode, BindBankCardPO cardPo)
    {
    	MsgHeaderPO headerPo = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
    			customer.getContactId(), "1", customer.getServNumber());
    	
    	ReturnWrap rw = getSelfSvcCallSD().unsignHeBao(headerPo, cardPo, smsCode);
    	
    	if(SSReturnCode.ERROR == rw.getStatus())
    	{
    		throw new ReceptionException(rw.getReturnMsg()); 
    	}
    }
    
    /**
     * �Ͱ��׳�ֵ�Զ����ý��ѽӿ�
     * @param termInfo
     * @param customer
     * @param curMenuId
     * @param oprType
     * @param trigAmt
     * @param drawAmt
     * @param bankId
     * @remark create by wWX217192 2014-12-10 R003C10LG1101 OR_SD_201409_82_JT_�Ͱ��׳�ֵ֧������
     */
    public void setHeBaoAutoValue(TerminalInfoPO termInfo, NserCustomerSimp customer, String curMenuId, 
    		String oprType, BankCardInfoPO bankCardInfoPO)
    {
    	MsgHeaderPO headerPo = new MsgHeaderPO(curMenuId, termInfo.getOperid(), termInfo.getTermid(),
    			customer.getContactId(), "1", customer.getServNumber());
    	
    	ReturnWrap rw = getSelfSvcCallSD().setHeBaoAutoValue(headerPo, oprType, bankCardInfoPO);
    	
    	if(SSReturnCode.ERROR == rw.getStatus())
    	{
    		throw new ReceptionException(rw.getReturnMsg());
    	}
    }

}
