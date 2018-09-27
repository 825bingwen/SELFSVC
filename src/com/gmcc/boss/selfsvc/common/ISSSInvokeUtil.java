package com.gmcc.boss.selfsvc.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.sd.selfsvc.bean.ProdChangeBean;
import com.customize.sd.selfsvc.prodChange.model.ProdChangePO;
import com.gmcc.boss.selfsvc.bean.UserLoginBean;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.model.OfferInfoVO;
import com.gmcc.boss.selfsvc.login.model.NserCustomerSimp;
import com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.opensymphony.xwork2.ActionContext;


/**
 * 
 * <ɽ�������ն˶Խ�����Ӫ��ƽ̨(ISSS)>
 * <������ϸ����>
 * 
 * @author  sWX219697
 * @version  [�汾��, Sep 12, 2014]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 * @remark create by sWX219697 2014-9-12 OR_SD_201409_556_�����ն�Ӫ�������Ż�
 */
public class ISSSInvokeUtil 
{
	private static Log logger = LogFactory.getLog(ISSSInvokeUtil.class);

    /**
     * �����ն˶Խ�ISSS�¼������ɹ�������
     */
    private static final String SUCCESS_CODE = "0";
    
    /**
     * �����Ʒ���Bean
     */
    private static ProdChangeBean prodChangeBean;
    
    /**
     * ��ȡ�û���¼��Ϣ��Bean
     */
    private transient static UserLoginBean userLoginBean = null;
    
    /**
     * <webService�ӿڵ���>
     * <������ϸ����>
     * @param wsUrl 
     * @param operation
     * @param reqMsg ������
     * @return
     * @throws Exception
     * @see [�ࡢ��#��������#��Ա]
     */
    private static String invoke(String wsUrl, String operation ,OMElement reqMsg) throws Exception
    {
		//��ʼ��webService�ͻ��˲�����
		return new Axis2Client(wsUrl, operation).invokeWebService(reqMsg);
    }
    
    /**
     * <Ӫ���¼�����>
     * <������ϸ����>
     * @param telnum �ֻ�����
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public static List<OfferInfoVO> eventInvoke(String telnum)
    {
    	//Ӫ��ҵ��list
		List<OfferInfoVO> offerList = new ArrayList<OfferInfoVO>();
		
		try 
		{
			//���أ�1�����ܿ���������ر�
	        if (!"1".equals(CommonUtil.getParamValue(Constants.SH_CHARGE_WEBSERVICE_SWITCH)))
	        {
	        	return offerList;
	        }
	        
			// �����¼����ͻ�ȡ���õ��¼�����
			String eventCode =  CommonUtil.getParamValue(Constants.SH_CHARGE_WEBSERVICE_EVENTCODE);
			
			// wsUrl
			String wsUrl = CommonUtil.getParamValue(Constants.SH_CHARGE_WEBSERVICE_WSURL);
			
			// ���÷���
			String operation = CommonUtil.getParamValue(Constants.SH_CHARGE_WEBSERVICE_OPERATION);
			
			//�����ռ�
			String serNs = CommonUtil.getParamValue(Constants.SH_CHARGE_WEBSERVICE_SERNS);
			
			//�����ռ�
			String xsdNs = CommonUtil.getParamValue(Constants.SH_CHARGE_WEBSERVICE_XSDNS);
			
			//�û���Ϣsession
			NserCustomerSimp customer = (NserCustomerSimp)getSession(Constants.USER_INFO);
			
			//���û���¼�����û���¼���ֻ��ţ����򴫳�ֵ���ֻ�����
			String msisdn = (null == customer ? telnum : customer.getServNumber());
			
			//��װ������
			OMElement reqMsg = createEventMsg(eventCode,msisdn,serNs,xsdNs);
			
			// true:����, false����
			if (true)
			{
				//����webService�ӿڲ���������ֵ
				offerList = parseEventResponse(invoke(wsUrl, operation , reqMsg), msisdn);
				
				// add begin jWX216858 2015-4-30 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
				Collections.sort(offerList);
				// add end jWX216858 2015-4-30 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
			}
			
			//���Ի���
			else
			{
				//���ر��ģ���ҳչʾ
				String responseMsg = "<RecommednationResult xmlns=\"http://service.prp.campaign.huawei.com\">"
					+"<return>"
					+"<resultCode>0</resultCode>"
					+"<resultMessage>success!</resultMessage>"
					+"<offerList>" 
					//+"<desc>���ʳ�;ҵ�����</desc>"   introduceInfo
					+"<offerName>���ʳ�;</offerName>"
					+"<offerAttrMap><key>offerCode</key><value>pp.gt.tp</value></offerAttrMap>"
					+"<offerAttrMap><key>treatment_id</key><value>test11</value></offerAttrMap>"
					+"<offerAttrMap><key>introduceInfo</key><value>���ʳ�;ҵ�����</value></offerAttrMap>"
					+"<offerAttrMap><key>campaignName</key><value>���ʳ�;����</value></offerAttrMap>"
					+"<offerAttrMap><key>activityPriority</key><value>25</value></offerAttrMap>"
					+"<offerType>4</offerType>"
					+"<offerId> </offerId>"
					+"</offerList>"
					+"<offerList>" 
					//+"<desc>��������ҵ�����</desc>" 
					+"<offerName>��������</offerName>"
					+"<offerAttrMap><key>activity_name</key><value>�и߶˿ͻ��Ƽ�������ͥ��</value></offerAttrMap>"
					+"<offerAttrMap><key>offerCode</key><value>test22</value></offerAttrMap>"
					+"<offerAttrMap><key>treatment_id</key><value>test222</value></offerAttrMap>"
					+"<offerAttrMap><key>introduceInfo</key><value>��������ҵ�����</value></offerAttrMap>"
					+"<offerAttrMap><key>campaignName</key><value>�������ο���</value></offerAttrMap>"
					+"<offerAttrMap><key>activityPriority</key><value>75</value></offerAttrMap>"
					+"<offerType>1</offerType>"
					+"<offerId></offerId>"
					+"</offerList>"
					+"<offerList>" 
					//+"<desc>�����Ʒ���</desc>" 
					+"<offerName>�ʷ��ײ�</offerName>"
					+"<offerAttrMap><key>activity_name</key><value>�ʷ��ײ�</value></offerAttrMap>"
					+"<offerAttrMap><key>offerCode</key><value>pp.gt.tp</value></offerAttrMap>"
					+"<offerAttrMap><key>treatment_id</key><value>test222</value></offerAttrMap>"
					+"<offerAttrMap><key>introduceInfo</key><value>�����Ʒ���</value></offerAttrMap>"
					+"<offerAttrMap><key>activityPriority</key><value>100</value></offerAttrMap>"
					+"<offerType>4</offerType>"
					+"<offerId>1111</offerId>"
					+"</offerList>"
					+"<offerList>" 
					//+"<desc>�����Ʒ���</desc>" 
					+"<offerName>����Ԥ����ȫ��ͨ��88�ײ�</offerName>"
					+"<offerAttrMap><key>activity_name</key><value>����Ԥ����ȫ��ͨ��88�ײ�</value></offerAttrMap>"
					+"<offerAttrMap><key>offerCode</key><value>pp.gt.88tcy.634</value></offerAttrMap>"
					+"<offerAttrMap><key>treatment_id</key><value>test222</value></offerAttrMap>"
					+"<offerAttrMap><key>introduceInfo</key><value>�����Ʒ���</value></offerAttrMap>"
					+"<offerAttrMap><key>activityPriority</key><value>100</value></offerAttrMap>"
					+"<offerType>4</offerType>"
					+"<offerId></offerId>"
					+"</offerList>"
					+"<offerList>" 
					//+"<desc>�����Ʒ���</desc>" 
					+"<offerName>�󸶷����鿨���ɴ�</offerName>"
					+"<offerAttrMap><key>activity_name</key><value>�󸶷����鿨���ɴ�</value></offerAttrMap>"
					+"<offerAttrMap><key>offerCode</key><value>pp.gt.88tcy.634</value></offerAttrMap>"
					+"<offerAttrMap><key>treatment_id</key><value>test222</value></offerAttrMap>"
					+"<offerAttrMap><key>introduceInfo</key><value>�����Ʒ���</value></offerAttrMap>"
					+"<offerAttrMap><key>activityPriority</key><value>25</value></offerAttrMap>"
					+"<offerType>4</offerType>"
					+"<offerId></offerId>"
					+"</offerList>"
					+"</return>"
					+"</RecommednationResult>";
				
				//�������󲢽������ر���
				offerList = parseEventResponse(responseMsg, msisdn);

				// add begin jWX216858 2015-4-30 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
				Collections.sort(offerList);
				// add end jWX216858 2015-4-30 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
			}
		} 
		catch (Exception e)
		{
			logger.error("��ֵ�����Ӫ���¼��ӿ�ʧ�ܣ�", e);
		}
		
    	return offerList;
    }
    
    /**
     * <��װӪ���¼�������>
     * <������ϸ����>
     * @param eventCode
     * @param servNum
     * @param serNs
     * @param xsdNs
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	private static OMElement createEventMsg(String eventCode,String servNum,String serNs,String xsdNs) 
	{
		OMFactory fac = OMAbstractFactory.getOMFactory();  
		
		//���������ռ�
        OMNamespace serNameSpace = fac.createOMNamespace(serNs,"");  
        OMNamespace xsdNameSpace = fac.createOMNamespace(xsdNs, "");
        
        //��װ��������Ϣ
        OMElement reqMsg = fac.createOMElement("getRecommendedOffer", serNameSpace);  
        
        //��װrequestHeader��Ϣ���󣬹̶�������ͷ��
        OMElement reqHeader = fac.createOMElement("requestHeader", serNameSpace);
        
        // ��Χ����ID������������ʱ����һ������ID
        OMElement accessChannel = fac.createOMElement("accessChannel", xsdNameSpace);
        accessChannel.setText("bsacAtsv");  
        reqHeader.addChild(accessChannel);  
        
        // �̶�Ϊ101
        OMElement beId = fac.createOMElement("beId", xsdNameSpace);
        beId.setText("101");  
        reqHeader.addChild(beId);  
        
        // �̶�Ϊ2
        OMElement language = fac.createOMElement("language", xsdNameSpace);
        language.setText("2");  
        reqHeader.addChild(language);  
        
        // �̶�Ϊ��Campaign
        OMElement operator = fac.createOMElement("operator", xsdNameSpace);
        operator.setText("Campaign");  
        reqHeader.addChild(operator);  
        
        // modify by lKF60882 2016-9-27 OR_SD_201609_165_ɽ��_��Ӫ��ƽ̨�����������ն���ϸ�������
        OMElement password = fac.createOMElement("password", xsdNameSpace);
        password.setText(CommonUtil.getParamValue(Constants.SH_ISSS_PASSWORD, "q3geiItxj4ljNLkI6OINDA=="));  
        reqHeader.addChild(password);  
        
        // ���
        OMElement transactionId = fac.createOMElement("transactionId", xsdNameSpace);
        transactionId.setText("xxxxx");  
        reqHeader.addChild(transactionId); 
        
        reqMsg.addChild(reqHeader);
        
        //��װeventBody��Ϣ����
        OMElement eBody = fac.createOMElement("eventBody", serNameSpace);
        
        // �¼�����
        OMElement eventCodeElt = fac.createOMElement("eventCode", xsdNameSpace);
        eventCodeElt.setText(eventCode);  
        eBody.addChild(eventCodeElt);  
        
        // ����
        OMElement msisdn = fac.createOMElement("msisdn", xsdNameSpace);
        msisdn.setText(servNum);  
        eBody.addChild(msisdn);  
        
        reqMsg.addChild(eBody);
        reqMsg.build();  
        
        return reqMsg;

	}
	
	/**
	 * <����Ӫ���¼����ر���>
	 * <���ISSS���ص�Ӫ���Ƽ�ҵ���б�����ĳ���Ƽ���Ϣ��Ӧ��offerid�ڵ㲻���ڣ�
	 * ����У��offertype������offerid�ڵ���ڣ���offertypeҲ����>
	 * @param resMsg
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark modify by sWX219697 2015-2-11 17:47:52 OR_SD_201502_198_ISSS�����նˡ�������֧���޲�ƷӪ����Ƽ�
	 */
	@SuppressWarnings({ "unchecked", "unchecked" })
	private static List<OfferInfoVO> parseEventResponse(String resMsg, String telnum)
	{
		//Ӫ��ҵ��list
		List<OfferInfoVO> resultList = new ArrayList<OfferInfoVO>();
		
		try 
		{
			Document body = DocumentHelper.parseText(resMsg);
			
			//��ȡ���ر��ĵ�return���ڵ�
			Element root = body.getRootElement().element("return");
			
			List<Element> proList = new ArrayList<Element>();
			
			//����return������offerList�ڵ�
			Iterator offerList = root.elementIterator("offerList");
			
			//����offerList�ڵ�������
			while (offerList.hasNext())
			{
				Element eChild = (Element)offerList.next();
				Element elOfferId = eChild.element("offerId");
				
				// add begin jWX216858 2015-5-11 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
				/*if (!isElementNotNull(elOfferId))
				{
					continue;
				}*/
				// add end jWX216858 2015-5-11 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
				
				//�ж�offerType(1���ײ�\ 2��Ӫ������ \3��SP \4�������Ʒ ��5�� ������������Ʒ��6�������������Ż�)��
				Element elOfferType = eChild.element("offerType");
				
				//�����Ʒ
				if (isElementNotNull(elOfferId) 
						&& "4".equals(elOfferType.getText()))
				{
					proList.add(eChild);
					continue;
				}
				
				//�����ն�ֻչʾ 1�ײ� 4 �����Ʒ
				if (isElementNotNull(elOfferId) 
						&& !"1".equals(elOfferType.getText()))
				{
					continue;
				}
				
				OfferInfoVO offerInfo = new OfferInfoVO();
				
				//��������ҵ����չ����
				Iterator attrChild = eChild.elementIterator("offerAttrMap");
				
				//����ҵ����չ����
				while (attrChild.hasNext())
				{
					Element offerAttrMap = (Element)attrChild.next();
					
					String key = offerAttrMap.element("key").getText();
					
					// add begin jWX216858 2015-5-21 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
					Element valueObj = offerAttrMap.element("value");
					
					String value = "";
					if (isElementNotNull(valueObj))
					{
						value = valueObj.getText();
					}
					// add end jWX216858 2015-5-21 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
					
					//��Ʒcode��1���ײ�:  ��Ʒ��;��Ʒ; 2���Ż�Ӫ����: ;����;���� 3��SP:;SP��ҵ����;SPҵ�����
					if ("offerCode".equals(key))
					{
						offerInfo.setOfferCode(value);
					}
					
					//����ʱʹ��
					if ("treatment_id".equals(key))
					{
						offerInfo.setTreatment_id(value);
					}
					
					// add begin hWX5316476 2015-1-15 OR_SD_201412_699_ISSS���¼�OFFER�Ƽ��ӿ�-�Ƽ������Ż�������GA�汾��
					// ҵ���� Ӫ������desc ����introduceInfo��value
					if("introduceInfo".equals(key))
					{
					    offerInfo.setDesc(value);
					}
					// add end hWX5316476 2015-1-15 OR_SD_201412_699_ISSS���¼�OFFER�Ƽ��ӿ�-�Ƽ������Ż�������GA�汾��
					
					//��offerId�ڵ�Ϊ�գ�ֱ�ӽ���offerAttrMap��campaignName��Ӧ��value�ŵ�ҳ���ϲ�Ʒ�����ֶ���
					if("campaignName".equalsIgnoreCase(key) && !isElementNotNull(elOfferId))
					{
						//ҵ������
						offerInfo.setOfferName(value);
					}
					
					// add begin jWX216858 2015-5-7 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
					// ����ȼ�  �� --25,�� --50,�ϸ� --75,�ߡ�100
					if ("activityPriority".equals(key))
					{
						offerInfo.setPriority(value);
						if ("".equals(value))
						{
							offerInfo.setPriority("0");
						}
					}
					// add end jWX216858 2015-5-7 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
				}
				
				if(isElementNotNull(elOfferId))
				{
					//����offercode���Ҷ�Ӧ�Ĳ˵��offercode��sh_menu_item����issscode�ֶζ�Ӧ��
					MenuInfoPO menuItem = parseMenuItem(offerInfo.getOfferCode());
					
					//��δ�ҵ���Ӧ�Ĳ˵������ҵ�񲻽���չʾ
					if (null == menuItem)
					{
						continue;
					}
					
					//��Ӧ��menuId
					offerInfo.setMenuId(menuItem.getMenuid());
					
					//��Ӧ����תurl
					offerInfo.setUrl(menuItem.getGuiobj());
					
					//ҵ������
					offerInfo.setOfferName(eChild.element("offerName").getText());
					
					//ҵ��id������ʱʹ��
					offerInfo.setOfferId(eChild.element("offerId").getText());
				}
				else
				{
					//offerIdΪ��ʱ����ֵΪ1,���ڷ���
					offerInfo.setOfferId("1");
				}
				
				resultList.add(offerInfo);
			}
			
			// ���û��ɰ����������Ʒ�б����뵽resultList��
			resultList.addAll(parseMainProductResponse(proList, telnum));
		} 
		catch (Exception e) 
		{
			logger.error("����Ӫ���¼��ӿڷ��ر���ʧ�ܣ�", e);
		}
		
		return resultList;
	}
	
	/**
	 * <xmlԪ�ؽڵ��Ƿ�Ϊ��>
	 * <������ϸ����>
	 * @param eChild
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private static boolean isElementNotNull(Element eChild)
	{
		boolean isNull = false;
		
		if(null != eChild && StringUtils.isNotBlank(eChild.getText()))
		{
			isNull = true;
		}
		
		return isNull;
	}
	
	/**
	 * * <����Ӫ���¼����ر���>
	 * <���������Ʒ������ص��б���Ϣ>
	 * @param resMsg
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 * @remark create by wWX217192 2014-10-16 OR_SD_201407_1310 ISSS:ISSSƽ̨Ӫ�����Ʒ���ù�����չ
	 */
	@SuppressWarnings("unchecked")
	private static List<OfferInfoVO> parseMainProductResponse(List<Element> proList, String telnum)
	{
		Element eChild = null;
		
		//��������ҵ����չ����
		Iterator attrChild = null;
		
		//�ɱ�������Ʒ�б�list
		List<OfferInfoVO> resultList = new ArrayList<OfferInfoVO>();
		
		OfferInfoVO offerInfo = null;
		
		// ����Ӫ��ƽ̨���صĿɱ�������Ʒ�б�list
		List<OfferInfoVO> offerList = new ArrayList<OfferInfoVO>();
		
		// ��ѯ��ǰ��¼�û��ɱ���������Ʒ�б�list
		List<ProdChangePO> prodChangeList = new ArrayList<ProdChangePO>();
		
		for(int i = 0; i < proList.size(); i++)
		{
			eChild = proList.get(i);
			
			attrChild = eChild.elementIterator("offerAttrMap");
			
			offerInfo = new OfferInfoVO();
			
			while (attrChild.hasNext())
			{
				Element offerAttrMap = (Element)attrChild.next();
				
				String key = offerAttrMap.element("key").getText();
				
				// add begin jWX216858 2015-5-21 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
				Element valueObj = offerAttrMap.element("value");
				
				String value = "";
				if (isElementNotNull(valueObj))
				{
					value = valueObj.getText();
				}
				// add end jWX216858 2015-5-21 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
				
				//��Ʒcode��1���ײ�:  ��Ʒ��;��Ʒ; 2���Ż�Ӫ����: ;����;���� 3��SP:;SP��ҵ����;SPҵ�����
				if ("offerCode".equals(key))
				{
					offerInfo.setOfferCode(value);
				}
				
				//����ʱʹ��
				if ("treatment_id".equals(key))
				{
					offerInfo.setTreatment_id(value);
				}
				
				// add begin hWX5316476 2015-1-15 OR_SD_201412_699_ISSS���¼�OFFER�Ƽ��ӿ�-�Ƽ������Ż�������GA�汾��
                // ҵ���� Ӫ������desc ����introduceInfo��value
                if("introduceInfo".equals(key))
                {
                    offerInfo.setDesc(value);
                }
                // add end hWX5316476 2015-1-15 OR_SD_201412_699_ISSS���¼�OFFER�Ƽ��ӿ�-�Ƽ������Ż�������GA�汾��
                
                // add begin jWX216858 2015-5-7 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
				// ����ȼ�  �� --25,�� --50,�ϸ� --75,�ߡ�100
				if ("activityPriority".equals(key))
				{
					offerInfo.setPriority(value);
					if ("".equals(value))
					{
						offerInfo.setPriority("0");
					}
				}
				// add end jWX216858 2015-5-7 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����

			}
			
			// �����Ʒ�����MenuId
			offerInfo.setMenuId("recProductChange");
			
			// �����Ʒ�����ת��URL
			offerInfo.setUrl("prodChange/qryMainProdChangeInfoList.action");
			
			//ҵ������
			offerInfo.setOfferName(eChild.element("offerName").getText());
			
			//ҵ��id������ʱʹ��
			offerInfo.setOfferId(eChild.element("offerId").getText());

			offerList.add(offerInfo);
		}
		
		// ��ȡ�ն˻���Ϣ
		TerminalInfoPO termInfoPO = (TerminalInfoPO)getSession(Constants.TERMINAL_INFO);
		
		//�û���Ϣsession
		NserCustomerSimp customer = (NserCustomerSimp)getSession(Constants.USER_INFO);
		
		//���û���¼�����û���¼���ֻ��ţ����򴫳�ֵ���ֻ�����
		String servNum = (null == customer ? telnum : customer.getServNumber());
		
		if(null == customer || customer.getRegionID().isEmpty())
		{
			Map customerSimpMap = userLoginBean.getUserInfo(servNum, termInfoPO);
	        
	        if (customerSimpMap.get("customerSimp") != null)
	        {
	        	// ȡ��¼�û���Ϣ
	        	customer = (NserCustomerSimp) customerSimpMap.get("customerSimp");
	        }
		}
		// ���ýӿڲ�ѯ��ת�������Ʒ��Ϣ
		prodChangeList = prodChangeBean.qryMainProdInfo(termInfoPO, customer, "recProductChange");
		
		// ��������Ӫ��ƽ̨���ص������Ʒ��Ϣ
		for(OfferInfoVO offerInfoVo : offerList)
		{
			// �����ͻ���ת���������Ʒ��Ϣ
			for(ProdChangePO prod : prodChangeList)
			{
				// ������Ӫ��ƽ̨���ص������Ʒ������ͻ���ת���������Ʒ������ͬ���򽫴������Ʒ��Ϣչʾ��ǰ̨
				if(offerInfoVo.getOfferCode().split(";", -1)[1].equals(prod.getNewProdId()))
				{
					resultList.add(offerInfoVo);
					break;
				}
			}
		}
		
		return resultList;
	}
	
	/**
	 * <����offercode��������Ӧ�Ĳ˵�>
	 * <������ϸ����>
	 * @return menuItem �˵���
	 * @see [�ࡢ��#��������#��Ա]
	 */
	@SuppressWarnings("unchecked")
	private static MenuInfoPO parseMenuItem(String offerCode)
	{
		MenuInfoPO menuItem = null;
		
        // ��ȡ�ն˻���Ϣ
        TerminalInfoPO terminalInfoPO = (TerminalInfoPO)getSession(Constants.TERMINAL_INFO);
        
        //ȡ�����ն˻���Ӧ�Ĳ˵���
		List<MenuInfoPO> menuItemList = (List<MenuInfoPO>)PublicCache.getInstance()
			.getCachedData(terminalInfoPO.getTermgrpid());
		
	    if (StringUtils.isNotEmpty(offerCode) && null != menuItemList)
	    {
	    	for (int i = 0; i < menuItemList.size(); i++)
	        {
	    		
	    		//����offercode���Ҷ�Ӧ�Ĳ˵���
	            if (offerCode.equals(menuItemList.get(i).getiSSSCode()))
	            {
	                menuItem = menuItemList.get(i);
	                break;
	            }
	        }
	    }
	    
	    return menuItem;
	}
	
    /**
     * <�����������>
     * <������ϸ����>
     * @param servNum �ֻ�����
     * @param offerInfo ҵ����Ϣ��
     * @see [�ࡢ��#��������#��Ա]
     */
    public static void feedbackInvoke(String servNum, String menuId, String status)
    {
		logger.debug("feedbackInvoke begin...");
		
		try 
		{
			//������Ϊ�գ���ֱ�ӷ���
			if(StringUtils.isEmpty(servNum) || StringUtils.isEmpty(menuId) || StringUtils.isEmpty(status))
			{
				logger.debug("feedbackInvoke end: the param is null...");
				return;
			}
			
            String province = CommonUtil.getParamValue(Constants.PROVINCE_ID);
            
            //�ж�ʡ�ݣ�ֻ��ɽ�����з���
            if (!Constants.PROOPERORGID_SD.equals(province))
            {
            	logger.debug("feedbackInvoke end: the province is not SD...");
            	return;
            }
            
            //�����عر���ֱ�ӷ��� 1��������0���ر�
            if (!"1".equals(CommonUtil.getParamValue(Constants.SH_CHARGE_WEBSERVICE_SWITCH)))
            {
            	logger.debug("feedbackInvoke end: the switch is not open...");
            	return;
            }
			
            //��ȡsession�б����ҵ����Ϣ
            String sessionKey = getSessionKey(servNum, menuId);
            
            OfferInfoVO offerInfo = (OfferInfoVO)getSession(sessionKey);
            
            //û����ص�ҵ�񶩹���Ϣ��ֱ�ӷ���
			if (null == offerInfo)
			{
				logger.debug("feedbackInvoke end: there is no session about offerInfo...");
				return;
			}
			
			//����״̬
			offerInfo.setStatus(status);
			
			//���÷����ӿ�
			doFeedbackInvoke(servNum, offerInfo);
		    
		    //���������session
		    removeSession(sessionKey);
		} 
		catch (Exception e)
		{
			logger.error("�����ӿڵ���ʧ�ܣ�", e);
		}
		
		logger.debug("feedbackInvoke end...");
		
    }
    
    /**
     * <�����ӿڵ���>
     * <������ϸ����>
     * @param servNum �ֻ�����
     * @param offerInfo �Ƽ���Ϣ
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-2-11 15:31:00 OR_SD_201502_198_ISSS�����նˡ�������֧���޲�ƷӪ����Ƽ�
     */
    public static void doFeedbackInvoke(String servNum, OfferInfoVO offerInfo) throws Exception
    {
		// wsUrl
		String wsUrl = CommonUtil.getParamValue(Constants.SH_ISSS_FEEDBACK_WSURL);
		
		// ���÷���
		String operation = CommonUtil.getParamValue(Constants.SH_ISSS_FEEDBACK_OPERATION);
		
		//�����ռ�
		String implNs = CommonUtil.getParamValue(Constants.SH_ISSS_FEEDBACK_IMPLNS);
		
		//�����ռ�1
		String xsdNs = CommonUtil.getParamValue(Constants.SH_ISSS_FEEDBACK_XSDNS);
		
		//�����ռ�
		String xsdNs1 = CommonUtil.getParamValue(Constants.SH_ISSS_FEEDBACK_XSDNS1);
		
		//��װ������
		OMElement reqMsg = createFeedbackMsg(servNum, implNs, xsdNs, xsdNs1, offerInfo);
		
		//���÷���webService�ӿ�
	    invoke(wsUrl, operation, reqMsg);
    }
    
    /**
     * <��װ�����ӿ�������>
     * <������ϸ����>
     * @param servNum
     * @param implNs
     * @param xsdNs
     * @param xsdNs1
     * @param offerInfo
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
	private static OMElement createFeedbackMsg(String servNum,String implNs,String xsdNs, 
			String xsdNs1, OfferInfoVO offerInfo) 
	{
		OMFactory fac = OMAbstractFactory.getOMFactory();  
		
		//���������ռ�
        OMNamespace implNameSpace = fac.createOMNamespace(implNs,"");  
        OMNamespace xsdNameSpace = fac.createOMNamespace(xsdNs, "");
        OMNamespace xsd1NameSpace = fac.createOMNamespace(xsdNs1, "");
        
        //��װ��������Ϣ
        OMElement reqMsg = fac.createOMElement("onFeedbackHandle", implNameSpace);  
        
        //��װrequestParam
        OMElement requestParam = fac.createOMElement("requestParam", implNameSpace);
        
        // contactId
        OMElement contactId = fac.createOMElement("contactId", xsdNameSpace);
        contactId.setText("");  
        requestParam.addChild(contactId);
        
        // �����ʼ�
        OMElement email = fac.createOMElement("email", xsdNameSpace);
        email.setText("");  
        requestParam.addChild(email);
        
        //��չ����
        OMElement extAttr = fac.createOMElement("extAttr", xsdNameSpace);
        
        //treatment_id key
        OMElement key = fac.createOMElement("key", xsdNameSpace);
        key.setText("treatment_id");  
        extAttr.addChild(key);
        
        //treatment_id value
        OMElement value = fac.createOMElement("value", xsdNameSpace);
        value.setText(offerInfo.getTreatment_id());  
        extAttr.addChild(value);
        
        requestParam.addChild(extAttr);
        
        // ����
        OMElement msisdn = fac.createOMElement("msisdn", xsdNameSpace);
        msisdn.setText(servNum);  
        requestParam.addChild(msisdn); 
        
        //����ҵ�������
        OMElement orderInfos = fac.createOMElement("orderInfos", xsdNameSpace);
        
        //ҵ��id
        OMElement offerId = fac.createOMElement("offerId", xsdNameSpace);
        offerId.setText(offerInfo.getOfferId());  
        orderInfos.addChild(offerId);
        
        //����ʱ��
        OMElement orderTime = fac.createOMElement("orderTime", xsdNameSpace);
        orderTime.setText(CommonUtil.dateToString(new Date(), "yyyyMMdd HH:mm:ss"));  
        orderInfos.addChild(orderTime);
        
        //����״̬
        OMElement orderStatus = fac.createOMElement("orderStatus", xsdNameSpace);
        orderStatus.setText(offerInfo.getStatus());  
        orderInfos.addChild(orderStatus);
        requestParam.addChild(orderInfos);
        
        //��װrequestHeader��Ϣ���󣬹̶�������ͷ��
        OMElement requestHeader = fac.createOMElement("requestHeader", xsdNameSpace);
        
        // ��Χ����ID������������ʱ����һ������ID
        OMElement accessChannel = fac.createOMElement("accessChannel", xsd1NameSpace);
        accessChannel.setText("bsacAtsv");  
        requestHeader.addChild(accessChannel);  
        
        // �̶�Ϊ101
        OMElement beId = fac.createOMElement("beId", xsd1NameSpace);
        beId.setText("101");  
        requestHeader.addChild(beId);  
        
        // �̶�Ϊ2
        OMElement language = fac.createOMElement("language", xsd1NameSpace);
        language.setText("2");  
        requestHeader.addChild(language);  
        
        // �̶�Ϊ��feedbackuser
        OMElement operator = fac.createOMElement("operator", xsd1NameSpace);
        operator.setText("feedbackuser");  
        requestHeader.addChild(operator);  
        
        // modify by lKF60882 2016-9-27 OR_SD_201609_165_ɽ��_��Ӫ��ƽ̨�����������ն���ϸ�������
        OMElement password = fac.createOMElement("password", xsd1NameSpace);
        password.setText(CommonUtil.getParamValue(Constants.SH_ISSS_PASSWORD, "q3geiItxj4ljNLkI6OINDA=="));  
        requestHeader.addChild(password);  
        
        // ���
        OMElement transactionId = fac.createOMElement("transactionId", xsd1NameSpace);
        transactionId.setText("xxxxx");  
        requestHeader.addChild(transactionId); 
        
        requestParam.addChild(requestHeader);
        
        //��װsubscriberId��Ϣ����
        OMElement subscriberId = fac.createOMElement("subscriberId", xsdNameSpace);
        subscriberId.setText("");
        requestParam.addChild(subscriberId);
        
        reqMsg.addChild(requestParam);
        reqMsg.build();  
        
        return reqMsg;

	}
	
	/**
	 * <����ISSS���ص�ҵ����Ϣ>
	 * <������ϸ����>
	 * @param telnum
	 * @param menuId
	 * @param offerId
	 * @param treatment_id
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public static void setISSSSession(String telnum, String menuId, String offerId, String treatment_id, String offerCode)
	{
    	//����Ӫ��ƽ̨���صĲ�Ʒ��Ϣ
    	OfferInfoVO offerInfo = new OfferInfoVO();
    	
    	//ս��id������ʱʹ��
    	offerInfo.setTreatment_id(treatment_id);
    	
    	//��Ʒid������ʱʹ��
    	offerInfo.setOfferId(offerId);
    	
    	// �����Ʒ���룬������ҵ��Ϊ�����Ʒ���ʱʹ��
    	offerInfo.setOfferCode(offerCode);
    	
		//�û���Ϣsession
		NserCustomerSimp customer = (NserCustomerSimp)getSession(Constants.USER_INFO);
		
		//���û���¼�����û���¼���ֻ��ţ����򴫳�ֵ���ֻ�����
		String msisdn = (null == customer ? telnum : customer.getServNumber());
        
		String sessionKey = getSessionKey(msisdn, menuId);
		
		setSession(sessionKey, offerInfo);
		
	}
    /**
     * <����session>
     * <������ϸ����>
     * @param key
     * @param offerId ��Ʒid������ʱʹ��
     * @param treatment_id ս��id������ʱʹ��
     * @see [�ࡢ��#��������#��Ա]
     */
	public static void setSession(String sessionKey, Object obj)
	{
        //�����session
    	removeSession(sessionKey);
    	
    	getRequest().getSession().setAttribute(sessionKey, obj);
	}
	
	/**
	 * <��ȡ�����session>
	 * <������ϸ����>
	 * @param key
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private static Object getSession(String key)
	{
		//��ȡsession
        return getRequest().getSession().getAttribute(key);
	}
	
	/**
	 * <���session>
	 * <������ϸ����>
	 * @param key
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private static void removeSession(String key)
	{
		//��ȡsession
        HttpSession session = getRequest().getSession();
        
        //���session
        if(null != session.getAttribute(key))
        {
        	session.removeAttribute(key);
        }
        
	}
	
	/**
	 * <�����ֻ������menuId����session key>
	 * <������ϸ����>
	 * @param telnum �ֻ�����
	 * @param menuId �˵�id
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private static String getSessionKey(String telnum , String menuId)
	{
		return "ISSS_"+telnum + "_" + menuId;
	}
	
	/**
	 * <��ȡrequest����>
	 * <������ϸ����>
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private static HttpServletRequest getRequest()
	{
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST); 
        
        return request;
	}

	public ProdChangeBean getProdChangeBean() {
		return prodChangeBean;
	}

	public void setProdChangeBean(ProdChangeBean prodChangeBean) {
		this.prodChangeBean = prodChangeBean;
	}

	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}

	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}
	
}