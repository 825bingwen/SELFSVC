/*
 * �ļ�����SelfSvcCallImpl.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * �����������ն�ϵͳͨ��ӿڵ���ʵ���࣬����ͳһ�ӿ�ƽ̨
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ�����
 * 
 */
package com.gmcc.boss.selfsvc.call;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.sd.selfsvc.po.NcodePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Axis2Client;
import com.gmcc.boss.selfsvc.common.BusiCodeConstants;
import com.gmcc.boss.selfsvc.common.BusinessIdConstants;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.quickpublish.model.MultiProdCommitPO;
import com.gmcc.boss.selfsvc.quickpublish.model.ProdCommitPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import com.gmcc.boss.selfsvc.util.DESedeEncrypt;
import com.gmcc.boss.selfsvc.util.DocumentUtil;


/**
 * ɽ�������ն�ϵͳ�ӿڵ���ʵ���࣬����ͳһ�ӿ�ƽ̨ <һ�仰���ܼ���> <������ϸ����>
 * 
 * @author g00140516
 * @version 1.0, 2011/05/31
 * @see 
 * @since 
 */
public class SelfSvcCallImpl implements SelfSvcCall
{
    private static Log logger = LogFactory.getLog(SelfSvcCallImpl.class);
    
    private IntMsgUtil intMsgUtil;
    
    private SocketUtil socketUtil;
    
    public SocketUtil getSocketUtil()
    {
        return socketUtil;
    }
    
    public void setSocketUtil(SocketUtil socketUtil)
    {
        this.socketUtil = socketUtil;
    }
    
    /**
     * ��ѯ��ͥ����Ա
     * 
     * @param map ����
     * @return [��������˵��]
     * @exception/throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     * @depreced
     * @remark create kWX211786 May 23, 2014 �汾�� ����/BUG��ţ�OR_huawei_201404_1115_ɽ��_��ͥ����Ա��ӹ���
    */
    public ReturnWrap queryFamilyMemService(Map<String,String> map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String menuid = (String)map.get("curMenuId");
            
            // �ֻ�����
            eBody.addElement("telnum").addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_familymeminfo_sd", menuid, "", "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ѯ��ͥ����Ա�ӿ�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��Ӽ�ͥ����Ա
     * 
     * @param map ����
     * @return [��������˵��]
     * @exception/throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     * @depreced
     * @remark create kWX211786 May 23, 2014 �汾�� ����/BUG��ţ�OR_huawei_201404_1115_ɽ��_��ͥ����Ա��ӹ���
    */
    public ReturnWrap addFamilyMem(Map<String, String> map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String menuid = (String)map.get("curMenuId");
            String servNumber = (String)map.get("servNumber");
            String memTelnum = (String)map.get("memTelnum");
            String shortNum = (String)map.get("shortNum");
            
            // �ֻ�����
            eBody.addElement("servNumber").addText(servNumber);
            eBody.addElement("memTelnum").addText(memTelnum);
            eBody.addElement("shortNum").addText(shortNum);
            //�Ƿ񶩹���Ա��ѡ�Ż� 1���� ��������
            eBody.addElement("addMustPriv").addText("0");
            //�����Ʒ����
            eBody.addElement("prodId").addText("");
            //�Żݱ���
            eBody.addElement("privId").addText("");
            
      
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_add_familymem_sd", menuid, "", "1", servNumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��Ӽ�ͥ����Ա�ӿ�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
	/**
     * ����������֤
     * 
     * @param map
     * @return  ReturnWrap
     * @remark create by hWX5316476 2014-05-13 OR_huawei_201405_235  �����ն˽���EBUSһ�׶�_��ȡ�û���Ϣ
     */
    public ReturnWrap checkUserPassword(Map<String, String> map)
    {
    	return null;
    }
    
    /**
     * ��ȡ�û���Ϣ
     * 
     * @param map
     * @return
     * @remark create by hWX5316476 2014-05-13 OR_huawei_201405_235  �����ն˽���EBUSһ�׶�_��ȡ�û���Ϣ
     */
    public ReturnWrap getUserInfoHub(Map<String, String> map)
    {
    	return null;
    }
    
    /**
     * ʹ���ֻ����롢����������������֤
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap getUserInfoWithPwd(Map map)
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
            // �Ƿ�У������
            Element eReq_isCheck = eBody.addElement("ischeckpass");
            eReq_isCheck.addText("1");
            
            // ����
            Element eReq_password = eBody.addElement("password");
            eReq_password.addText(password);
            
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_userinfo", "10001001", "", "1", telnumber, operID, termID);
            // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("����������֤ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �������¼
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap getUserInfo(Map map)
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
            // �Ƿ�У������
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
            
            // modify begin g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_USERINFO, "10001001", "", "1", telnumber, operID, termID);
            // modify end g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
            
            ReturnWrap rw = intMsgUtil.invoke(docXML);
            
            // ���ܿ�,����תΪСд
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_USERINFO) && null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                // ����ͳһתСд
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                String[] openEbusRtn = {"subsname", "prodid", "viptypeid", "brandid", "brandname"};
                String[] destRtn = {"subname", "productid", "viptype", "productgroup", "productname"};
                tagSet = CommonUtil.rtnConvert(tagSet, BusinessIdConstants.CLI_QRY_USERINFO, openEbusRtn, destRtn);
                rw.setReturnObject(tagSet);
            }
            
            return rw;
            // modify end  fwx439896 2017-8-7 OR_huawei_201704_411_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�û���Ϣ��ѯ�ӿڲ��
        }
        catch (Exception e)
        {
            logger.error("��֤ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �³��۷Ѳ�ѯ
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap queryMonthDeduct(Map map)
    {
        Document doc = DocumentHelper.createDocument();
        Element eBody = doc.addElement("message_content");
        
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("menuid");
        String telnumber = (String)map.get("telnumber");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        // ��װ�������
        // �ֻ�����
        Element eReq_telnum = eBody.addElement("telnum");
        eReq_telnum.addText(telnumber);
        
        Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_YCKF, menuid, touchoid, "1", telnumber, operID, termID);
        ReturnWrap rw = intMsgUtil.invoke(docXML);
        Vector vector = (Vector)rw.getReturnObject();
        
        Vector retVector = new Vector();
        if (vector != null && vector.size() == 2)
        {
            CTagSet tagset = (CTagSet)vector.get(0);
            
            // �ܿ�����keyתΪСд
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_YCKF))
            {
                tagset = CommonUtil.lowerTagKey(tagset);
                vector.set(0, tagset);
            }
            
            CRSet crset = (CRSet)vector.get(1);
            
            List listBaseFee = null;// //�ײ���Ϣ��
            List listFuncFee = null;// //���ܷ�
            List listSumPackage = null;// ���·�
            
            if ((crset != null) && (crset.GetRowCount() > 0))
            {
                // �ײ���Ϣ��
                if (!"0".equals(tagset.GetValue("sum_basefee")))
                {
                    listBaseFee = getMonthFeeList(crset, "3");
                }
                // ���ܷ�
                if (!"0".equals(tagset.GetValue("sum_funcfee")))
                {
                    listFuncFee = getMonthFeeList(crset, "1");
                }
                // ���·�
                if (!"0".equals(tagset.GetValue("sum_packagefee")))
                {
                    listSumPackage = getMonthFeeList(crset, "2");
                }
            }
            retVector.add(listBaseFee);
            retVector.add(listFuncFee);
            retVector.add(listSumPackage);
            rw.setReturnObject(retVector);
        }
        
        return rw;
    }
    
    /**
     * ���ܷѡ����·ѡ��ײ���Ϣ��
     * <������ϸ����>
     * @param crset
     * @param typeStr
     * @return  List<List>
     * @see [�ࡢ��#��������#��Ա]
     */
    private List<List> getMonthFeeList(CRSet crset, String typeStr)
    {
        
        // ����list
        List<List> feeList = new ArrayList<List>();
        
        for (int i = 0; i < crset.GetRowCount(); i++)
        {
            String type = crset.GetValue(i, 0) == null ? "" : crset.GetValue(i, 0);
            if (typeStr.equals(type))
            {
                List<String> list = new ArrayList<String>();
                
                list.add(crset.GetValue(i, 1));
                list.add(CommonUtil.fenToYuan(crset.GetValue(i, 2)));
                
                // �ܿ����صĸ�ʽת��
                if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_YCKF))
                {
                    list.add(CommonUtil.formatDate(crset.GetValue(i, 3).substring(0, 5), "MM-dd", "MM��dd��"));
                }
                else
                {
                    list.add(CommonUtil.formatDate(crset.GetValue(i, 3), "MM.dd", "MM��dd��"));
                }
                
                feeList.add(list);
            }
        }
        return feeList;
    }
    
   
    public void setIntMsgUtil(IntMsgUtil intMsgUtil)
    {
        this.intMsgUtil = intMsgUtil;
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
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            //����ģ����
            Element eReq_templateno = eBody.addElement("templateno");
            eReq_templateno.addText("Atsv0001");
            
            //�����б�
            // modify by lKF60882 OR_huawei_201703_629  ��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1 2017-4-13 begin
            Element eReq_smsparam = null;
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_SENDSMSINFO))
            {
                eReq_smsparam = eBody.addElement("SMPARAM");
            }
            else
            {
                eReq_smsparam = eBody.addElement("smsparam");
            }
            // modify by lKF60882 OR_huawei_201703_629  ��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1 2017-4-13 end
            
            eReq_smsparam.addText(smsContent);
            
            //�Ƿ��������
            Element eReq_isrvcall = eBody.addElement("isrvcall");
            eReq_isrvcall.addText("1");
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_BUSI_SENDSMSINFO, menuID, touchOID, "1", telnum, operID, termID);
            return intMsgUtil.invoke(docXML);

        }
        catch (Exception e)
        {
            logger.error("���Ͷ���ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ����Ʒ���ʷѼ��ѿ�ͨ����
     * <������ϸ����>
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
        String queryType = (String)map.get("queryType");
        
        try
        {
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // ��ѯ���� 1��Ʒ���ʷ� 2���ѿ�ͨ�����Ż�
            Element eReq_qrytype = eBody.addElement("qrytype");
            eReq_qrytype.addText(queryType);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_ZFINFO, menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        
        }
        catch (Exception e)
        {
            logger.error("��ѯ����Ʒ���ʷѼ��ѿ�ͨ����ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * PUK���ѯ
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap queryPUK(Map map)
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
            
            Document docXML = intMsgUtil.createMsg(doc,BusinessIdConstants.CLI_QRY_PUKCODE,menuid,touchoid,"1",telnumber,operid,atsvNum);
            ReturnWrap rw=intMsgUtil.invoke(docXML);
            
            //modify begin fwx439896 2017-04-19 OR_huawei_201703_629_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1
            // ���ܿ� ����ͳһתСд
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_PUKCODE) && null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                tagSet = CommonUtil.lowerTagKey(tagSet);
                rw.setReturnObject(tagSet);
            }
            //modify end fwx439896 2017-04-19 OR_huawei_201703_629_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1
          
            return rw ;
        }
        catch (Exception e)
        {
            logger.error("PUK���ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ����״̬��ѯ
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap queryCurrentStatus(Map map)
    {
       try{
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
    
            String operid = (String)map.get("operid");
            String atsvNum = (String)map.get("termid");
            String telnumber = (String)map.get("telnum");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("menuid");
                       
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc,BusinessIdConstants.CLI_QRY_USERSTATE,menuid,touchoid,"1",telnumber,operid,atsvNum);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ǰ״̬��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��������ز�ѯ
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap queryUserRegionReq(Map map)
    {
        try{
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("qryServnuber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_NUMREGION, menuid, touchoid, "0", "999", operid, atsvNum);
            ReturnWrap rw = intMsgUtil.invoke(docXML);
           
            //modify begin fwx439896 2017-04-19 OR_huawei_201703_629_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�
            //���ܿ�  ����ͳһתСд
            if(CommonUtil.isInvokeOpenEbus("cli_qry_numregion") && null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                tagSet = CommonUtil.lowerTagKey(tagSet);
                rw.setReturnObject(tagSet);
            }
            //modify end fwx439896 2017-04-19 OR_huawei_201703_629_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�
         
            return rw;
        }
        catch (Exception e)
        {
            logger.error("��������ز�ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �굥��ѯ
     * 
     * @param map
     * @return ReturnWrap 
     * @see 
     * @remark modify g00140516 2012/02/26 R003C12L02n01 OR_huawei_201112_953
     */
    public ReturnWrap queryCDRList(Map map)
    {
        try
        {
            String telnum = (String)map.get("servNumber");
            String month = (String)map.get("month");
            String cdrType = (String)map.get("CDRType");
            String fee_type = (String)map.get("fee_type");
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String startTime = (String) map.get("startTime");
            String endTime = (String) map.get("endTime");
            String verifyCode = (String) map.get("verifyCode");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // �·�
            Element eReq_month = eBody.addElement("month");
            eReq_month.addText(month);
            
            // �Ƿ��ͺϼ�ֵ
            Element eReq_count = eBody.addElement("count_flag");
            eReq_count.addText("1");
            
            // ͨ���嵥��ѯ��־
            Element eReq_query = eBody.addElement("query_flag");
            eReq_query.addText("");
            
            // �굥����
            Element eReq_cdrtype = eBody.addElement("cdrtype");
            eReq_cdrtype.addText(cdrType);
            
            // ��ѯ����
            Element eReq_fee_type = eBody.addElement("fee_type");
            eReq_fee_type.addText(fee_type);
            
            // factory
            Element eReq_factory = eBody.addElement("factory");
            eReq_factory.addText("9A3A9B26E157B508228301EF1F7BF352");
            
            Element eReq_starttime = eBody.addElement("starttime");
            eReq_starttime.addText(startTime);
            
            Element eReq_endtime = eBody.addElement("endtime");
            eReq_endtime.addText(endTime);            
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_cdr", menuid, unicontact, "1", telnum, operID, termID, verifyCode);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�굥��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /** 
     * �굥��ѯȨ����֤_����
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap checkQueryAuth(MsgHeaderPO msgHeader)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ���ú�̨�ӿ�
            return intMsgUtil.invoke("cli_check_cdrauth_hub", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("������֤�굥��ѯȨ��ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ������ʷ��ѯ
     * <������ϸ����>
     * @param map
     * @return
     * @see  [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryAllServiceHistory(Map map)
    {
        String startDate = (String)map.get("startDate");
        String endDate = (String)map.get("endDate");
        String operid = (String)map.get("curOper");
        String atsvNum = (String)map.get("atsvNum");
        String telnumber = (String)map.get("servnumber");
        String touchoid = (String)map.get("touchoid");
        String menuid = (String)map.get("curMenuId");
        
        try
        {
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
            
            Document docXML = intMsgUtil.createMsg(doc,BusinessIdConstants.CLI_QRY_RECHISTORY,menuid,touchoid,"1",telnumber,operid,atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("������ʷ��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �ɷ���ʷ��ѯ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryChargeHistory(Map map)
    {
        try
        {
            String telnumber = (String)map.get("servNumber");
            String startDate = (String)map.get("startDate");
            String endDate = (String)map.get("endDate");
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // ��ʼʱ��
            Element eReq_startdate = eBody.addElement("startdate");
            eReq_startdate.addText(startDate);
            
            // ����ʱ��
            Element eReq_enddate = eBody.addElement("enddate");
            eReq_enddate.addText(endDate);
            
            Document docXML = intMsgUtil.createMsg(doc,BusinessIdConstants.CLI_QRY_CHARGEHISTORY,menuid,unicontact,"1",telnumber,operID,termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�ɷ���ʷ��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ���ֲ�ѯ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap queryScoreValue(Map map)
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
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_scorevalue", menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("���ֲ�ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "���ֲ�ѯ�ӿڳ����쳣");
        }
    }
    
    /**
     * �������
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryBalanceNotice(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            //modify begin fwx439896 2017-5-15 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����
            
            Document docXML = intMsgUtil.createMsg(doc,BusinessIdConstants.CLI_QRY_ALARMBALANCE,menuid,touchoid,"1",telnumber,operid,atsvNum);
            ReturnWrap  rw=intMsgUtil.invoke(docXML);
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_ALARMBALANCE) && null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            { 
                // ����ͳһתСд  prepay_type  credit
                CTagSet tagSet = (CTagSet) rw.getReturnObject();
                String[] openEbusRtn = {"prepaytype", "notifyvalue"};
                String[] destRtn = {"prepay_type", "credit", };
                tagSet = CommonUtil.rtnConvert(tagSet, BusinessIdConstants.CLI_QRY_USERINFO, openEbusRtn, destRtn);
                rw.setReturnObject(tagSet);
            } 
            //modify end begin fwx439896 2017-5-15 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����
            return rw;
        }
        catch (Exception e)
        {
            logger.error("��ѯ�û�������ѷ�ֵʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ȡ��������ֵ������
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap getDictItem(Map map)
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
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // group ID
            Element eReq_groupid = eBody.addElement("groupid");
            eReq_groupid.addText(groupid);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_dictitem", menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ѯ������ѷ�ֵ�б�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �������ֵ����
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap setBalanceNotice(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String credit = (String)map.get("balanceAwake");
            String operType = (String)map.get("operType");/////////
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);

           //modify begin fwx439896 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3
           if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_ALARMBALANCE))
           {
        	   // �������ֵ
               Element eReq_credit = eBody.addElement("NOTIFYVALUE");   
               eReq_credit.addText(credit);
           }   
           else
           {
               // �������ֵ
               Element eReq_credit = eBody.addElement("credit");
               eReq_credit.addText(credit);
               
               Element eReq_systemid = eBody.addElement("systemid");
               eReq_systemid.addText("bsacAtsv");
               
               Element eReq_operType = eBody.addElement("operType");
               eReq_operType.addText(operType);
           
           } 
           //modify end fwx439896 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3
         
            Document docXML = intMsgUtil.createMsg(doc,BusinessIdConstants.CLI_BUSI_ALARMBALANCE,menuid,touchoid,"1",telnumber,operid,atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�����������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }

    /**
     * ����nocde(��)��ѯ��Ʒ,�Żݵ��ʷ�������Ϣ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap queryFeeMessage(Map map)
    {
        String telnum = (String)map.get("servNumber");
        String nCode = (String)map.get("nCode");
        String menuid = (String)map.get("menuID");
        String unicontact = (String)map.get("contactID");
        String operID = (String)map.get("operID");
        String termID = (String)map.get("termID");
        
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // NCODE
            Element eReq_ncode = eBody.addElement("ncode");
            eReq_ncode.addText(nCode);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_PRODUCTFEE, menuid, unicontact, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("ҵ������ʱ����Ʒ�ʷ���Ϣ��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �����ܿ�ʱ��Ʒ����ͨ�ýӿ�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @Remark create by lWX431760 2017-07-19 OR_huawei_201706_780_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ���������
     * @Remark modify by lWX431760 2017-09-29 OR_huawei_201706_781_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ�����(ͬ��ҵ��)
     */
    public ReturnWrap recCommonServNK(MsgHeaderPO msgHeader, String nCode, String operType, String effectType, String param)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            //�������
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            //ƽ̨��ˮ
            DocumentUtil.addSubElementToEle(eBody, "chnlrecoid", "");

            //�Ƽ��˺���
            DocumentUtil.addSubElementToEle(eBody, "recommender", "");

            //�����Ʒ����
            DocumentUtil.addSubElementToEle(eBody, "MAINPRODID","");

            //����Ա
            DocumentUtil.addSubElementToEle(eBody, "OPERID",msgHeader.getOperId());

            //Ӧ�շ���
            DocumentUtil.addSubElementToEle(eBody, "CONTRASTFEE","");
            
            //�Ƿ���ѣ�1������ѣ��������ԭ���߼����
            DocumentUtil.addSubElementToEle(eBody, "NONEEDCALCFEE","1");

            //�����Ʒ��������м����������Ʒ��ѡ�ײͣ�������������ѡ���ײͳ���ʱ���ش�
            DocumentUtil.addSubElementToEle(eBody, "PRODLIST", "");
            
            Element recprodlist = eBody.addElement("RECPRODLIST");
            
            // ��ֵ��Ʒ����
            DocumentUtil.addSubElementToEle(recprodlist, "PRODID", nCode);
            
            // ��Ч��ʽ Type_Immediate:���� Type_NextCycle:���� Type_Default:Ĭ��
            DocumentUtil.addSubElementToEle(recprodlist, "EFFECTTYPE", "Type_Default");
            
            DocumentUtil.addSubElementToEle(recprodlist, "OPTYPE", operType);
            
            // ��������
            DocumentUtil.addSubElementToEle(recprodlist, "ATTRPARA", "");
            
            // ԭ��ֵ��Ʒ����
            DocumentUtil.addSubElementToEle(recprodlist, "OLDPRODID", "");
            
            // ������Դ
            DocumentUtil.addSubElementToEle(recprodlist, "RESPARA", "");
            
            // �ӿڶ�Ӧ����
            DocumentUtil.addSubElementToEle(recprodlist, "RELATYPE", "");
            
            // �Ƿ�ģ�崦�� Ĭ��Ϊ:0
            DocumentUtil.addSubElementToEle(recprodlist, "ISTEMPLATE", "0");
            
            // ��Чʱ�� ָ��ʱ����Чʱ�� �� ��ʽΪ��YYYYMMDDHH24MISS
            DocumentUtil.addSubElementToEle(recprodlist, "STARTDATE", "");
            
            // ʧЧʱ�� ָ��ʱ��ʧЧʱ�� �� ��ʽΪ��YYYYMMDDHH24MISS
            DocumentUtil.addSubElementToEle(recprodlist, "ENDDATE", "");
            
            // ��Ʒ������
            DocumentUtil.addSubElementToEle(recprodlist, "PACKAGEID", "");
            
            // ��������
            DocumentUtil.addSubElementToEle(recprodlist, "OBJTYPE", "NCODE");
            
            // �Żݱ���
            DocumentUtil.addSubElementToEle(recprodlist, "PRIVID", "");
            
            // ģ�����
            DocumentUtil.addSubElementToEle(recprodlist, "TEMPLATEID", "");
          
            return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_PRODUCTREC, msgHeader, doc);
            
        }
        catch (Exception e)
        {
            logger.error("��Ʒ����ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��Ʒ����ͨ�ýӿ�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap recCommonServ(MsgHeaderPO msgHeader, String nCode, String operType, String effectType, String param)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // NCODE
            DocumentUtil.addSubElementToEle(eBody, "ncode", nCode);
            
            // ��������
            DocumentUtil.addSubElementToEle(eBody, "stype", operType);
            
            // ��Ч��ʽ
            DocumentUtil.addSubElementToEle(eBody, "effect_type", effectType);
            
            DocumentUtil.addSubElementToEle(eBody, "param", param);
            
            return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_PRODUCTREC, msgHeader, doc);
            
        }
        catch (Exception e)
        {
            logger.error("��Ʒ����ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }    
    
    /**
     * ��ѯͬ��ҵ���û�������Ϣ
     * @param msgHeader
     * @param nCode
     * @return
     * @remark create by lWX431760 2017-09-28 OR_huawei_201706_781_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն���ҵ�����(ͬ��ҵ��)
     */
    public ReturnWrap recCommonProductQry (MsgHeaderPO msgHeader, String nCode)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // NCODE
            DocumentUtil.addSubElementToEle(eBody, "ncode", nCode);                        
            
            return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_PRODUCTQRY, msgHeader, doc);
            
        }
        catch (Exception e)
        {
            logger.error("��ѯͬ��ҵ���û�������Ϣʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ͣ����ҵ����
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap stopOpenSubs(Map map)
    {
        try
        {
            String operid = (String)map.get("curOper");
            String atsvNum = (String)map.get("atsvNum");
            String telnumber = (String)map.get("telnumber");
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("curMenuId");
            String stoptype = (String)map.get("stoptype");
            String reason = (String)map.get("reason");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // ��������
            Element eReq_dealtype = eBody.addElement("stoptype");
            eReq_dealtype.addText(stoptype);
            
            String reasonElement = "reason";
            if(CommonUtil.isInvokeOpenEbus("cli_busi_stopopensubs"))
            {
                reasonElement="ChangeReason";
                // �Ƿ���������֤
                Element eReq_Pwdtype = eBody.addElement("needPwd");
                eReq_Pwdtype.addText("0");
            }   
            // ԭ��
            Element eReq_calltype = eBody.addElement(reasonElement);
            eReq_calltype.addText(reason);
            
            Document docXML = intMsgUtil.createMsg(doc,"cli_busi_stopopensubs",menuid,touchoid,"1",telnumber,operid,atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("ͣ����ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �����޸�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap recChangePassword(Map map)
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
            String oldPasswd = (String)map.get("oldPasswd");
            String newPasswd = (String)map.get("newPasswd");
            
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            // ԭ����
            Element eReq_oldPasswd = eBody.addElement("old_password");
            eReq_oldPasswd.addText(oldPasswd);
            
            // ������
            Element eReq_new_password = eBody.addElement("new_password");
            eReq_new_password.addText(newPasswd);
            
            // ��������
            Element eReq_subcmdid = eBody.addElement("subcmdid");
            eReq_subcmdid.addText("1");
            
            // modify by lKF60882 OR_huawei_201703_629  ��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1 2017-4-13 begin
            String businessId = BusinessIdConstants.CLI_BUSI_CHGPWD;
            
            // ���ܿ��Ļ�,�����е������޸Ľӿ�
            if(CommonUtil.isInvokeOpenEbus(businessId))
            {
                businessId = BusinessIdConstants.CLI_BUSI_PWDRESET_MOD;
                
                // ���ܿ�ʹ������
                DESedeEncrypt des = new DESedeEncrypt();
                eReq_oldPasswd.setName("OLDPWD");
                eReq_oldPasswd.setText(des.decrypt(oldPasswd));
                
                eReq_new_password.setName("NEWPWD");
                eReq_new_password.setText(des.decrypt(newPasswd));
            }
            
            Document docXML = intMsgUtil.createMsg(doc,businessId,menuid,touchoid,"1",telnumber,operid,atsvNum);
            // modify by lKF60882 OR_huawei_201703_629  ��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1 2017-4-13 end
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("���������޸�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ����ת������
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap commitCallTransferNo(Map map)
    {
        String telnum = (String)map.get("servNumber");
        String dealtype = (String)map.get("dealType");
        String calltype = (String)map.get("callType");
        String callednum = (String)map.get("transferNumber");
        String menuid = (String)map.get("menuID");
        String unicontact = (String)map.get("contactID");
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
            Element eReq_dealtype = eBody.addElement("dealtype");
            eReq_dealtype.addText(dealtype);
            
            // ��ת����
            Element eReq_calltype = eBody.addElement("calltype");
            eReq_calltype.addText(calltype);
            
            //modify begin lWX431760 2017-05-11 OR_huawei_201704_404_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����2
            // ����ת����
            Element eReq_callednum = null;
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CALLTRANSFER))
            {             
                eReq_callednum = eBody.addElement("callednumm");                
            }
            else
            {
                eReq_callednum = eBody.addElement("callednum");                
            }
            
            if (callednum == null)
            {
                callednum = "";
            }
            eReq_callednum.addText(callednum);
            //modify end lWX431760 2017-05-11 OR_huawei_201704_404_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����2
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_BUSI_CALLTRANSFER, menuid, unicontact, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("����ת������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_spinfo", menuid, touchoid, "1", telnumber, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("ҵ��ͳһ��ѯ�ӿ�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ҵ��ͳһ�˶��ӿ�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap cancelService(Map map)
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
            String operType = (String)map.get("operType");
            String cancelType = (String)map.get("cancelType");
            String dealType = (String)map.get("dealType");
            String domain = (String)map.get("domain");
            String spid = (String)map.get("spId");
            String bizid = (String)map.get("spBizCode");
            String biztype = (String)map.get("bizType");
            String effectType = (String)map.get("effectType");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // ��������
            Element eReq_operType = eBody.addElement("oper_type");
            eReq_operType.addText(operType);
            
            // �˶�����
            Element eReq_cancelType = eBody.addElement("cancel_type");
            eReq_cancelType.addText(cancelType);
            
            // ��������
            Element eReq_dealType = eBody.addElement("deal_type");
            eReq_dealType.addText(dealType);
            
            // ƽ̨
            Element eReq_domain = eBody.addElement("domain");
            eReq_domain.addText(domain);
            
            // �������
            Element eReq_spid = eBody.addElement("spid");
            eReq_spid.addText(spid);
            
            // ҵ�����
            Element eReq_bizid = eBody.addElement("bizid");
            eReq_bizid.addText(bizid);
            
            // ҵ������
            Element eReq_biztype = eBody.addElement("biztype");
            eReq_biztype.addText(biztype);
            
            // ��Ч��ʽ
            Element eReq_effecttype = eBody.addElement("effect_type");
            eReq_effecttype.addText(effectType);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_cancelsp", menuid, touchoid, "1", telnumber, operid, atsvNum);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("ҵ��ͳһ�˶��ӿ�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     *  �ֻ�֧�����˻���Ϣ��ѯ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryCmpayAccount(Map map)
    {
        try{
            String secure_pwd = (String)map.get("securePwd");
            String telnumber = (String)map.get("servNumber");
            String orgID = (String)map.get("orgID");
            String action_time = (String)map.get("actionTime");
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ��װ�������
            // ҵ�������ʶ���̶�Ϊ MPAY_ACC_QRY
            Element eOper_flag = eBody.addElement("oper_flag");
            eOper_flag.addText("MPAY_ACC_QRY");
            
            // ��ȫ����
            Element eSecure_pwd = eBody.addElement("secure_pwd");
            eSecure_pwd.addText(secure_pwd);
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // Ӫҵ�����
            Element eOrgid = eBody.addElement("orgid");
            eOrgid.addText(orgID);
            
            // ����ԱID
            Element eOperid = eBody.addElement("operid");
            eOperid.addText(operID);
            
            // ��������ʱ��
            Element eAction_time = eBody.addElement("action_time");
            eAction_time.addText(action_time);
            
            Document docXML = intMsgUtil.createMsg(doc,"cli_qry_mpayacc",menuid,unicontact,"1",telnumber,operID,termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�ֻ�֧�����˻���Ϣ��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     *  �ֻ�֧�����˻���ֵ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap recMainFee(Map map)
    {
        try{
            String secure_pwd = (String)map.get("securePwd");
            String telnumber = (String)map.get("servNumber");
            String orgID = (String)map.get("orgID");
            String action_time = (String)map.get("actionTime");
            String payed = (String)map.get("money");
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ��װ�������
            // ҵ�������ʶ���̶�Ϊ MPAY_CHARGE
            Element eOper_flag = eBody.addElement("oper_flag");
            eOper_flag.addText("MPAY_CHARGE");
            
            // ��ȫ����
            Element eSecure_pwd = eBody.addElement("secure_pwd");
            eSecure_pwd.addText(secure_pwd);
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // Ӫҵ�����
            Element eOrgid = eBody.addElement("orgid");
            eOrgid.addText(orgID);
            
            // ����ԱID
            Element eOperid = eBody.addElement("operid");
            eOperid.addText(operID);
            
            // ��������ʱ��
            Element eAction_time = eBody.addElement("action_time");
            eAction_time.addText(action_time);
            
            // ��ֵ���
            Element ePayed = eBody.addElement("payed");
            ePayed.addText(payed);
            
            Document docXML = intMsgUtil.createMsg(doc,"cli_busi_mpaycharge",menuid,unicontact,"1",telnumber,operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("���˻���ֵʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }

    /**
     * ��������У�����֤��
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap checkIDCard(Map map)
    {
        try
        {
            String telnum = (String)map.get("telnum");
            String certid = (String)map.get("IDCard");
            String operid = (String)map.get("operid");
            String termid = (String)map.get("termid");
            String menuid = (String)map.get("menuid");
            String touchoid = (String)map.get("touchoid");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ���֤��
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(certid);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_BUSI_CIDCHECK, menuid, touchoid, "1", telnum, operid, termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("���֤��֤ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��������
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap resetPassword(Map map)
    {
        try
        {
            String telnum = (String)map.get("telnumber");
            String subcmdid = (String)map.get("subcmdid");
            String oldpwd = (String)map.get("oldpwd");
            String menuid = (String)map.get("menuID");
            String touchoid = (String)map.get("touchOID");
            String newpwd = (String)map.get("newpwd");
            String operid = (String)map.get("operID");
            String termid = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ������
            Element eReq_oldpwd = eBody.addElement("oldpwd");
            eReq_oldpwd.addText(oldpwd);
            
            // ������
            Element eReq_newpwd = eBody.addElement("newpwd");
            eReq_newpwd.addText(newpwd);
            
            // 0������У�� 1�������޸� 2���������ã���У��oldpwd
            Element eReq_subcmdid = eBody.addElement("subcmdid");
            eReq_subcmdid.addText(subcmdid);
            
            // modify by lKF60882 OR_huawei_201703_629  ��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1 2017-4-13 begin
            String businessId = BusinessIdConstants.CLI_BUSI_PWDRESET;
            
            // ���ܿ��Ļ������ֹ���
            if(CommonUtil.isInvokeOpenEbus(businessId))
            {
                // ����У��
                if("0".equals(subcmdid))
                {
                    businessId = BusinessIdConstants.CLI_BUSI_PWDRESET_CHK;
                    eReq_oldpwd.setName("PASSWORD");
                }
                // �����޸�
                else if("1".equals(subcmdid))
                {
                    businessId = BusinessIdConstants.CLI_BUSI_PWDRESET_MOD;
                }
                
                // ���ܿ�ʹ������
                DESedeEncrypt des = new DESedeEncrypt();
                eReq_oldpwd.setText(des.decrypt(oldpwd));
                eReq_newpwd.setText(des.decrypt(newpwd));
            }
            
            Document docXML = intMsgUtil.createMsg(doc, businessId, menuid, touchoid, "1", telnum, operid, termid);
            // modify by lKF60882 OR_huawei_201703_629  ��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն����нӿ�1 2017-4-13 end
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ѯ��ҪԤԼ����
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryChooseTel(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String)map.get("curOper");// ����Ա
            String atsvNum = (String)map.get("atsvNum");// �ն˻�ID
            String menuid = (String)map.get("curMenuId");// �˵�ID
            
            String county_id = (String)map.get("county_id");// ����
            String sele_rule = (String)map.get("sele_rule");// sele_rule ��ѯ���� 2����ǰ׺��ѯ 3������׺��ѯ
            String tel_prefix = (String)map.get("tel_prefix");// tel_prefix ����ǰ׺
            String tel_suffix = (String)map.get("tel_suffix");// tel_suffix �����׺
            String region = (String) map.get("region");//����            
            
            //modify begin fwx439896 2017-5-16 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����
            String businessId=BusinessIdConstants.CLI_QRY_NUMBYNET;
			//���ܿ� ��װ����
            if (CommonUtil.isInvokeOpenEbus(businessId)) 
			{
        		// ��������SD.LA
				DocumentUtil.addSubElementToEle(eBody, "COUNTYID", county_id);

				// ��ѯ���� 2����ǰ׺��ѯ 3������׺��ѯ
				DocumentUtil.addSubElementToEle(eBody, "SELERULE", sele_rule);

				// ����ǰ׺ sele_rule = 2ʱ�����û�����ƣ�Ϊ_______�������ƣ�������7λ�����油_ sele_rule
				// = 3ʱ��Ϊ����
				DocumentUtil.addSubElementToEle(eBody, "TELPREFIX", tel_prefix==null?" " :tel_prefix);

				// �����׺ sele_rule = 2ʱ��Ϊ���� sele_rule = 3ʱ������4λ�����油_
				DocumentUtil.addSubElementToEle(eBody, "TELSUFFIX", tel_suffix);

				// ��ֵΪ1��ʱ�򣬱�ʾͨ��selcountryid���뵥λID(ɽ����)
				DocumentUtil
						.addSubElementToEle(eBody, "ISSELORGID", "");

				// �̳ǵ�λ��SD.LA.ES(ɽ����) 
				DocumentUtil.addSubElementToEle(eBody, "SELCOUNTRYID",
						"");
				
				
				//ԭ���г�ֵ������ѡ�ţ��̶�ֵCOMMAGT_SELTEL 
				DocumentUtil.addSubElementToEle(eBody, "OPTYPE",
						"");
				// �����ն�һ��ѡ�ŵ��������̶�ֵ500
				DocumentUtil.addSubElementToEle(eBody, "TELCOUNT",
						"500");
				// �����ն�ѡ�ŵ���;���̶�ֵSimAny
				DocumentUtil.addSubElementToEle(eBody, "TELBRANDID",
						"SimAny");

			} else
			{
            // ��װ�������
            // ����
            Element eReq_county_id = eBody.addElement("county_id");
            eReq_county_id.addText(county_id);
            
            // ����ǰ׺
            Element eReq_sele_rule = eBody.addElement("sele_rule");
            eReq_sele_rule.addText(sele_rule);
    
            // �����׺
            Element eReq_tel_suffix = eBody.addElement("tel_suffix");
            eReq_tel_suffix.addText(tel_suffix);
            
            // ��ѯ����
            Element eReq_tel_prefix = eBody.addElement("tel_prefix");
            eReq_tel_prefix.addText(tel_prefix);
		 	}
            //modify end fwx439896 2017-5-16 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����

            Document docXML = intMsgUtil.createMsg(doc, businessId, menuid, "", "0", region, operid, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("ѡ��ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ԤԼ����
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chooseTel(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String atsvNum = (String)map.get("atsvNum");// �ն˻�ID
            String menuid = (String)map.get("curMenuId");// �˵�ID
            
            String telnum = (String)map.get("telnum");// ��Ԥ������
            String region = (String)map.get("region");// ���У���ѯʱ���ص���Ϣ��
            String org_id = (String)map.get("org_id");// ��λ����ѯʱ���ص���Ϣ��
            String certtype = (String)map.get("certtype");// Ĭ�ϣ�IdCard
            String name = (String)map.get("name");// �û�����
            String certid = (String)map.get("certid");// ���֤��
            String contacttel = (String)map.get("contacttel");// ��ϵ����
            String validday = (String)map.get("validday");// ԤԼʱ��
            String operID = (String) map.get("curOper");
            
            // ��װ�������
            // ��Ԥ������
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            //���У���ѯʱ���ص���Ϣ��
            Element eReq_region = eBody.addElement("region");
            eReq_region.addText(region);
            
            //��λ����ѯʱ���ص���Ϣ��
            Element eReq_org_id = eBody.addElement("org_id");
            eReq_org_id.addText(org_id);
            
            //Ĭ�ϣ�IdCard
            Element eReq_certtype = eBody.addElement("certtype");
            eReq_certtype.addText(certtype);
            
            //���֤��
            Element eReq_certid = eBody.addElement("certid");
            eReq_certid.addText(certid);
            
            //�û�����
            Element eReq_name = eBody.addElement("name");
            eReq_name.addText(name);
            
            //��ϵ���룬��Ϊ����
            Element eReq_contacttel = eBody.addElement("contacttel");
            eReq_contacttel.addText(contacttel);
            
            //ԤԼʱ��
            Element eReq_validday = eBody.addElement("validday");
            eReq_validday.addText(validday);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_occupytel", menuid, "", "1", telnum, operID, atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("ռ��ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ��ֵ����ֵ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap cardPayCommit(Map map)
    {
        try
        {
            
            String menuid = (String)map.get("menuid");// ��ǰ�˵�ID
            String touchoid = (String)map.get("touchoid");// �ͻ��Ӵ�id
            String telnum = (String)map.get("telnum");// �ͻ��ֻ���
            String operid = (String)map.get("operid");// ����Աid          
            String termid = (String)map.get("termid");// �ն˻�id
            String cardpwd = (String)map.get("cardpwd"); // �ͻ���ֵ������

            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // ��װ�������
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            //modify begin by cwx456134 2017-05-10 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_CARDPAY))
            {
                // ��ֵ������
                Element eReq_cardPwd = eBody.addElement("CARDPWD");
                eReq_cardPwd.addText(cardpwd);
            }
            else
            {
                // ��ֵ������
                Element eReq_cardPwd = eBody.addElement("card_pwd");
                eReq_cardPwd.addText(cardpwd); 
            }      
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_BUSI_CARDPAY, menuid, touchoid, "1", telnum, operid, termid);
            //modify end by cwx456134 2017-05-10 OR_huawei_201704_415_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����4
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {    
            logger.error("��ֵ����ֵʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
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
            String telnum = (String)map.get("servNumber");
            String email = (String)map.get("email");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("contactID");
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
     * ����139�������
     * @param map
     * @return
     */
    public ReturnWrap add139Mail(Map map)
    {
        try
        {
            String telnum = (String)map.get("servNumber");
            String menuID = (String)map.get("menuID");
            String touchOID = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String email=(String)map.get("email");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // 139����
            Element eReq_email = eBody.addElement("email");
            eReq_email.addText(email);

            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_add139mail", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ͨ139����ʧ��!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }

    /**
     * ����SPҵ��
     * 
     * @param map ���
     * @return �������
     * @see 
     * @remark create g00140516 2011/11/05 R003C11L11n01 BUG_HUB_201111_24
     */
    public ReturnWrap orderSPService(Map map)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String operid = (String) map.get("curOper");
            String atsvNum = (String) map.get("atsvNum");
            String telnumber = (String) map.get("telnumber");
            String touchoid = (String) map.get("touchoid");
            String menuid = (String) map.get("curMenuId");
            String operType = (String) map.get("operType");
            String cancelFlag = (String) map.get("cancelFlag");
            String domain = (String) map.get("domain");
            String spid = (String) map.get("spId");
            String bizid = (String) map.get("spBizCode");
            String biztype = (String)map.get("bizType");
            String effectType = (String)map.get("effectType");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // ��������
            Element eReq_operType = eBody.addElement("oper_type");
            eReq_operType.addText(operType);
            
            // ƽ̨
            Element eReq_domain = eBody.addElement("domain");
            eReq_domain.addText(domain);
            
            // �������
            Element eReq_spid = eBody.addElement("spid");
            eReq_spid.addText(spid);
            
            // ҵ�����
            Element eReq_bizid = eBody.addElement("bizid");
            eReq_bizid.addText(bizid);
            
            // ҵ������
            Element eReq_biztype = eBody.addElement("biztype");
            eReq_biztype.addText(biztype);
            
            // ��Ч��ʽ
            Element eReq_effecttype = eBody.addElement("effect_type");
            eReq_effecttype.addText(effectType);
            
            // �˶�����
            Element eReq_cancelFlag = eBody.addElement("cancel_flag");
            eReq_cancelFlag.addText(cancelFlag);
            
            // �����־
            Element eReq_Flag = eBody.addElement("flag");
            eReq_Flag.addText("1");
            
            // �������
            Element eReq_Chktype = eBody.addElement("chktype");
            eReq_Chktype.addText("");
            
            // ������Դ
            Element eReq_Source = eBody.addElement("source");
            eReq_Source.addText("");
                        
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_chgsubsmonserv", menuid, touchoid, "1", telnumber, operid, atsvNum);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("SPҵ�񶩹��ӿ�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ͨ��socket�ӿڲ�ѯ�굥��¼
     * 
     * @param map ���
     * @return
     * @see
     * @remark create g00140516 2011/12/09 R003C11L12n01 �굥��ѯʵ��socketЭ��
     */
    public ReturnWrap queryCDRListBySocket(Map map)
    {
        try
        {
            String telnum = (String)map.get("servNumber");
            String month = (String)map.get("month");
            String cdrType = (String)map.get("CDRType");
            String fee_type = (String)map.get("fee_type");
            String menuid = (String)map.get("menuID");
            String operID = (String)map.get("operID");
            String startTime = (String)map.get("startTime");
            String endTime = (String)map.get("endTime");
            
            // ����
            StringBuffer buffer = new StringBuffer(1024);
            buffer.append(telnum)
                    .append("~")
                    .append(month)
                    .append("~1~")
                    .append(cdrType)
                    .append("~")
                    .append(fee_type)
                    .append("~")
                    .append(Constants.CDR_FACTORY_INFO)
                    .append("~")
                    .append(startTime)
                    .append("~")
                    .append(endTime);
            
            return socketUtil.invoke(menuid, BusiCodeConstants.BUSICODE_CDRQRY, operID, buffer.toString());
        }
        catch (Exception e)
        {
            logger.error("�굥��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }

    /** 
     * NG3.5���굥����֮�굥��ѯ
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param month ��ѯ�·�
     * @param cdrType �굥����
     * @param feeType ��������
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
     */
    public ReturnWrap queryDetailedRecords2012(MsgHeaderPO msgHeader, String month,
        String cdrType, String feeType)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // �û�����
            DocumentUtil.addSubElementToEle(eBody, "password", "");
            
            // ����
            DocumentUtil.addSubElementToEle(eBody, "billcycle", month);
            
            // ��ʼʱ��
            DocumentUtil.addSubElementToEle(eBody, "startdate", "");
            
            // ����ʱ��
            DocumentUtil.addSubElementToEle(eBody, "enddate", "");
            
            // �굥����
            DocumentUtil.addSubElementToEle(eBody, "flag", cdrType);
            
            // ��ѯ����
            DocumentUtil.addSubElementToEle(eBody, "chkey", "");
            
            // factory
            DocumentUtil.addSubElementToEle(eBody, "selecttype", feeType);
            
            // add by xkf57421 for adding ���뷽ʽ begin
            DocumentUtil.addSubElementToEle(eBody, "accesstype", "bsacAtsv");
            // add by xkf57421 for adding ���뷽ʽ end
            
            // ���ú�̨�ӿ�
            return intMsgUtil.invoke("cli_qry_cdr2012", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("���굥��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * NG3.5���굥����֮��ѯ�ͻ���Ϣ
     * 
     * @param map
     * @return
     * @throws Exception
     * @remark create g00140516 2012/02/13 R003C12L02n01 OR_huawei_201112_953
     */
    public ReturnWrap queryCustomerInfo(Map<String, String>  map)
    {
        try
        {
            String telnum = (String)map.get("servNumber");
            String month = (String)map.get("month");
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String verifyCode = (String) map.get("verifyCode");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // �·�
            Element eReq_cycle = eBody.addElement("cycle");
            eReq_cycle.addText(month);
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_CUSTINFO, menuid, unicontact, "1", telnum, operID, termID, verifyCode);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�ͻ���Ϣ��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * NG3.5���굥����֮�굥��ѯ
     * 
     * @param map
     * @return
     * @throws Exception
     * @remark create g00140516 2012/02/13 R003C12L02n01 OR_huawei_201112_953
     */
    public ReturnWrap queryDetailedRecordsSD2012(Map<String, String>  map)
    {
        try
        {
            String telnum = (String)map.get("servNumber");
            String cdrType = (String)map.get("CDRType");           
            String startDate = (String)map.get("startDate");
            String endDate = (String) map.get("endDate");
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String verifyCode = (String) map.get("verifyCode");
            String iscycle = (String) map.get("iscycle");
            String cycle = (String) map.get("cycle");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ��ѯ��ʶ
            Element eReq_iscycle = eBody.addElement("iscycle");
            eReq_iscycle.addText(iscycle);
            
            // �ֻ�����
            Element eReq_balcycle = eBody.addElement("balcycle");
            eReq_balcycle.addText(cycle);
            
            // ���ڿ�ʼʱ��
            Element eReq_startcycle = eBody.addElement("startcycle");
            eReq_startcycle.addText(startDate);            
            
            // ���ڽ���ʱ��
            Element eReq_endcycle = eBody.addElement("endcycle");
            eReq_endcycle.addText(endDate);
            
            // �굥����
            Element eReq_cdr_type = eBody.addElement("cdr_type");
            eReq_cdr_type.addText(cdrType);
                        
            // ���ұ�������ı�ʶ
            Element eReq_factory = eBody.addElement("factory");
            eReq_factory.addText("9A3A9B26E157B508228301EF1F7BF352");
            
            // modify by lKF60882 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹�������� 2017-4-1 begin
            // �������ܿ��������keyֵ��Ҫת��
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CDR2012_SD))
            {
                eReq_startcycle.setName("STARTTIME");
                eReq_endcycle.setName("ENDTIME");
                eReq_cdr_type.setName("CDRTYPE");
            }
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_CDR2012_SD, menuid, unicontact, "1", telnum, operID, termID, verifyCode);
            ReturnWrap rw = intMsgUtil.invoke(docXML);
            
            // �������ܿ�������������ת��
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_CDR2012_SD) && null != rw && SSReturnCode.SUCCESS == rw.getStatus())
            {
                rw= genCdrResponse(rw, cdrType);
            }
            // modify by lKF60882 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹�������� 2017-4-1 end
            
            return rw;
        }
        catch (Exception e)
        {
            logger.error("���굥��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ���ݲ�ͬ���굥���ͣ����з�����Ϣ��ת��
     * 
     * @param rw
     * @param cdrType �굥����
     * @return
     * @remark lKF60882 2017-4-15 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹��������
     */
    private ReturnWrap genCdrResponse(ReturnWrap rw, String cdrType)
    {
        Object obj = rw.getReturnObject();
        
        CTagSet tagSet = null;
        CRSet set = null;
        if("1".equals(cdrType))
        {
            tagSet = new CTagSet();
            set = (CRSet)obj;
        }
        else
        {
            Vector vec = (Vector)obj;
            
            // ����ͳһתСд
            tagSet = CommonUtil.lowerTagKey((CTagSet)vec.get(0));
            set = (CRSet)vec.get(1);
        }
        
        // ��ȡbillinfo�б�תΪ�ַ���ƴ�ӵ���ʽ������ͳһ����
        rw = genBillInfo(rw, set, tagSet);
        
        return rw;
    }

    /** 
     * ���굥��ϢcrsetתΪtagset�е�billinfo
     * @param rw
     * @param obj
     * @remark lKF60882 2017-4-15 OR_huawei_201704_4_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն˽ӿڵ������׹��������
     */
    private ReturnWrap genBillInfo(ReturnWrap rw, CRSet set, CTagSet tagSet)
    {
        StringBuffer billinfo = new StringBuffer();
        
        // ����
        for(int i = 0, row = set.GetRowCount(); i < row; i++)
        {
            // ��˳��ƴ�ӣ����ֶ���@_@����
            for(int j = 0, col = set.GetColumnCount(); j < col; j++)
            {
                billinfo.append(set.GetValue(i, j)).append("@_@");
            }
            
            // ������¼��|����
            billinfo.append("|");
        }
        
        // ����װ���tagset����rw
        tagSet.put("billinfo", billinfo.toString());
        
        rw.setReturnObject(tagSet);
        
        return rw;
    }
    
    
    /**
     * ��֤�Ƿ�Ϊ��ʼ����
     * @param map
     * @return
     * @throws Exception
     * @remark create YKF38827 2012/02/24 R003C12L02n01  OR_NX_201112_87
     */
    public ReturnWrap valiIsfirstpwd(Map<String, String>  map){
        
        try
        {
            String telnum = (String)map.get("telnum");
            String menuid = (String)map.get("menuID");
            String touchoid = (String)map.get("touchOID");
            String operid = (String)map.get("operID");
            String termid = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
           
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_isfirstpwd", menuid, touchoid, "1", telnum, operid, termid);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("��ѯ�Ƿ�Ϊ��ʼ����ʧ�ܣ�", e);            
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �����°��굥��ѯ
     * 
     * @param map
     * @return
     * @throws Exception
     * @remark create g00140516 2012/03/14 R003C12L03n01 OR_NX_201203_128
     */
    public ReturnWrap queryDetailedRecordsNX2012(Map<String, String>  map)
    {
        try
        {
        	// modify begin g00140516 2013/02/28 R003C13L02n01 BUG_NX_201302_123 �������ڲ�ѯ�굥ʱ����������Σ�ISCYCLE��BILLCYCLE
        	String telnum = (String)map.get("servNumber");
            String cdrType = (String)map.get("CDRType");           
            String startDate = (String)map.get("startDate");
            String endDate = (String) map.get("endDate");
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String verifyCode = (String) map.get("verifyCode");
            String cycle = (String) map.get("cycle");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ���ڿ�ʼʱ�䡣�����ն�ֻ�а����ڲ�ѯ�����Դ�""
            Element eReq_startcycle = eBody.addElement("startcycle");
            eReq_startcycle.addText("");            
            
            // ���ڽ���ʱ�䡣�����ն�ֻ�а����ڲ�ѯ�����Դ�""
            Element eReq_endcycle = eBody.addElement("endcycle");
            eReq_endcycle.addText("");
            
            // �굥����
            Element eReq_cdr_type = eBody.addElement("cdr_type");
            eReq_cdr_type.addText(cdrType);
                        
            // ���ұ�������ı�ʶ
            Element eReq_factory = eBody.addElement("factory");
            eReq_factory.addText("9A3A9B26E157B508228301EF1F7BF352");
            
            // �����ն�ֻ�а����ڲ�ѯ�����Դ�1
            Element eIsCycle = eBody.addElement("iscycle");
            eIsCycle.addText("1");
            
            Element eBillCycle = eBody.addElement("billcycle");
            eBillCycle.addText(cycle);
            // modify end g00140516 2013/02/28 R003C13L02n01 BUG_NX_201302_123 �������ڲ�ѯ�굥ʱ����������Σ�ISCYCLE��BILLCYCLE
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_cdr2012_nx", menuid, unicontact, "1", telnum, operID, termID, verifyCode);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("���굥��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ɽ��������ϸ��ѯ
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap queryScoreDetailHis(Map map)
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
            String startDate = (String)map.get("startDate");
            String endDate = (String)map.get("endDate");
            String regions = (String)map.get("region");
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // ��ʼʱ��
            Element startD = eBody.addElement("startdate");
            startD.addText(startDate + "000000");
            // ����ʱ��
            Element endD = eBody.addElement("enddate");
            endD.addText(endDate + "235959");
            
            // �̶�0���û�����
            Element qrytype = eBody.addElement("qrytype");
            qrytype.addText("0");
              
            //modify begin lwx439898 2017-05-22 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3
            if(CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_QRY_SCROEDETAIL))
            {
                Element regionsd = eBody.addElement("region");
                regionsd.addText(regions);
            }
            else
            {
                // ���б��
                Element regionsd = eBody.addElement("subregion");
                regionsd.addText(regions);               
            }
            //modify end lwx439898 2017-05-22 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_scroedetail", menuid, touchoid, "1", telnumber, operid, atsvNum);
            // docXML = new IntMsgUtil().createMsg(doc, "cli_qry_scroedetail", menuid, touchoid, "1", telnumber, operid,
            // atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("������ϸ��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "������ϸ��ѯ�ӿڳ����쳣");
        }
    }
    
    
    /**
     * ɽ�����ֶһ���ʷ��ѯ
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap queryScoreChangeHis(Map map)
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
            String startDate = (String)map.get("startDate");
            String endDate = (String)map.get("endDate");
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // ��ʼʱ��
            Element startD = eBody.addElement("startdate");
            startD.addText(startDate + " 00:00:00");
            // ����ʱ��
            Element endD = eBody.addElement("enddate");
            endD.addText(endDate + " 23:59:59");
            
            
            Document docXML = intMsgUtil.createMsg(doc, BusinessIdConstants.CLI_QRY_SCORECHANGE, menuid, touchoid, "1", telnumber, operid, atsvNum);
//             docXML = new IntMsgUtil().createMsg(doc, "cli_qry_scorechange", menuid, touchoid, "1", telnumber, operid,
//             atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("ɽ�����ֶһ���ʷ��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "ɽ�����ֶһ���ʷ��ѯ�ӿڳ����쳣");
        }
    }
    
    
    @Override
    public ReturnWrap queryScorePrizeHis(Map map)
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
            String startDate = (String)map.get("startDate");
            String endDate = (String)map.get("endDate");
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            // ��ʼʱ��
            Element startD = eBody.addElement("startdate");
            startD.addText(startDate);
            // ����ʱ��
            Element endD = eBody.addElement("enddate");
            endD.addText(endDate);
            
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_scoreprize", menuid, touchoid, "1", telnumber, operid, atsvNum);
//             docXML = new IntMsgUtil().createMsg(doc, "cli_qry_scorechange", menuid, touchoid, "1", telnumber, operid,
//             atsvNum);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("�������ֶһ���ʷ��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "�������ֶһ���ʷ��ѯ�ӿڳ����쳣");
        }
    }
    
    public ReturnWrap qryRecStatusHub(MsgHeaderPO msgHeader, String nCode, String operType)
    {
        
        try
        {
        	Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ncode�б�
            DocumentUtil.addSubElementToEle(eBody, "ncodelist", nCode);
            
            // NCODE
            DocumentUtil.addSubElementToEle(eBody, "ncode", "SUBSNCODEEXSIT");
            
            // ��������
            DocumentUtil.addSubElementToEle(eBody, "stype", operType);
            
            return intMsgUtil.invoke("cli_qry_recstatus_hub", msgHeader, doc);
        }
        catch (Exception e)
        {
            logger.error("������Ʒ����״̬��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /** 
     * ��Ʒ���ٷ���-�û��Ѷ�����Ʒ��Ϣ��ѯ
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark: modify zKF69263 2014/09/11 R003C14L09n01 OR_huawei_201407_41 �����ն˽���EBUS���׶�_��Ʒ���ٷ���
     */
    public ReturnWrap qryHasProds(MsgHeaderPO msgHeader)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // ��װ�������
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ֵ��Ʒ������� 
            DocumentUtil.addSubElementToEle(eBody, "prodclass", "");

            // ��ѯ��ʽ  ��5�����ú�ԤԼ�� 
            DocumentUtil.addSubElementToEle(eBody, "qrytype", "5");

            // ��ֵ��Ʒ���룬���ж�����м���#�ָ����̶�������
            DocumentUtil.addSubElementToEle(eBody, "prodid", "");

            // �ײʹ��࣬�ײͲ�ѯʱ�á�������
            DocumentUtil.addSubElementToEle(eBody, "pkgtype", "");

            // �Ƿ������Ʒ����Ĭ�������������
            DocumentUtil.addSubElementToEle(eBody, "issolution", "");

            // �Ƿ�������Ų�Ʒ 0�������;1�����;Ĭ��Ϊ1���̶���0
            DocumentUtil.addSubElementToEle(eBody, "grpsubsoid", "0");

            // ���ͷ��û���ʶ��������
            DocumentUtil.addSubElementToEle(eBody, "donorsubsid", "");

            // ���ѯ����û�������ֵ��Ʒ��Ϣ��������
            DocumentUtil.addSubElementToEle(eBody, "ishavebandprod", "");

            // �ͷ�IVR�ײ�ʹ�ò�ѯ�á�������
            DocumentUtil.addSubElementToEle(eBody, "queryflag", "");

            // �ͷ�IVR�ײ�ʹ�ò�ѯ�á�������
            DocumentUtil.addSubElementToEle(eBody, "pkginfo", "");

            // �ͷ�IVR�ײ�ʹ�ò�ѯ�á�������
            DocumentUtil.addSubElementToEle(eBody, "exceptclass", "");
            
            // �ͷ�IVR�ײ�ʹ�ò�ѯ�á�������
            DocumentUtil.addSubElementToEle(eBody, "prodidlist", "");
            
            // ���ú�̨�ӿ�
            return intMsgUtil.invoke(msgHeader.getProcessCode(), msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("��ѯ�û��Ѷ�����Ʒʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /** 
     * ��Ʒ���ٷ���-��ѯ��Ʒ��������
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param qryType ��ѯ���� 0��NCODE 1����Ʒ���²�Ʒ
     * @param nCode nCode
     * @param operType PCOpRec:��ͨ  PCOpMod:�޸� PCOpDel:�ر� 
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark: modify zKF69263 2014/09/11 R003C14L09n01 OR_huawei_201407_41 �����ն˽���EBUS���׶�_��Ʒ���ٷ���
     */
    public ReturnWrap qryAddAttr(MsgHeaderPO msgHeader, String qryType, String nCode, String operType)
    { 
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // ��װ�������
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ��ѯ���� 0��NCODE 1����Ʒ���²�Ʒ
            DocumentUtil.addSubElementToEle(eBody, "qrytype", qryType);
            
            // NOCODE���߲�Ʒ����
            DocumentUtil.addSubElementToEle(eBody, "ncode", nCode);
            
            // �Ƿ������������ͱ�־��������
            DocumentUtil.addSubElementToEle(eBody, "isallrectype", "");
            
            // ��������,��ChangeProduct
            DocumentUtil.addSubElementToEle(eBody, "rectype", "ChangeProduct");
            
            // PCOpRec:��ͨ
            DocumentUtil.addSubElementToEle(eBody, "opttype", operType);
            
            // ���ú�̨�ӿ�
            return intMsgUtil.invoke(msgHeader.getProcessCode(), msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("��ѯ��Ʒ��������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /** 
     * ��Ʒ���ٷ���-��Ʒ����
     * 
     * @param msgHeader MsgHeaderPO
     * @param multiProdCommitPO MultiProdCommitPO
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark: modify zKF69263 2014/09/11 R003C14L09n01 OR_huawei_201407_41 �����ն˽���EBUS���׶�_��Ʒ���ٷ���
     */
    public ReturnWrap prodRec(MsgHeaderPO msgHeader, MultiProdCommitPO multiProdCommitPO)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // ��װ�������
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", multiProdCommitPO.getTelnum());
            
            // ��ʶ�ýӿ��Ƿ�ֻ����У�飬�����а��� 0��������У�飬���ǽ��а��� 1��ֻ����У�飬�����а��� ���Դ�������Ĭ��Ϊ������У�飬���ǰ���
            DocumentUtil.addSubElementToEle(eBody, "ischeck", multiProdCommitPO.getIscheck());
            
            // ���ͺ��룬��������ҵ�񡣴�����
            DocumentUtil.addSubElementToEle(eBody, "doneenum", multiProdCommitPO.getDoneenum());

            // ��ѱ�ʶ��Ԥ���ֶΣ��ݲ�ʹ�á�������
            DocumentUtil.addSubElementToEle(eBody, "iscalcfee", multiProdCommitPO.getIscalcfee());

            // ���ŷ��ͱ�ʶ 0�������� 1������ Ĭ��Ϊ1����Χ�̶���1
            DocumentUtil.addSubElementToEle(eBody, "issendsms", multiProdCommitPO.getIssendsms());
            
            // ������
            DocumentUtil.addSubElementToEle(eBody, "opersource", multiProdCommitPO.getOpersource());
            
            // �����ƷCRSET�б�
            Element eReq_multiprod = eBody.addElement("cli_busi_multiprodrecreq");
            List<ProdCommitPO> prodCommitPOList = multiProdCommitPO.getProdCommitList();
            for(ProdCommitPO prodCommitPO : prodCommitPOList)
            {
                // �Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ������Ʒ���룻������NCODE
                DocumentUtil.addSubElementToEle(eReq_multiprod, "ncode", prodCommitPO.getNcode());
                
                // ��Ч��ʽ 2������ 3������ 4������ 5��ָ����Чʱ�� Ĭ����������Ч������Χ���ݲ�Ʒ��ʵ���������
                DocumentUtil.addSubElementToEle(eReq_multiprod, "effecttype", prodCommitPO.getEffecttype());
                
                // �������� PCOpRec:��ͨ PCOpMod:�޸� PCOpDel:�ر� PCOpPau:��ͣ PCOpRes:�ָ�
                DocumentUtil.addSubElementToEle(eReq_multiprod, "opertype", prodCommitPO.getOpertype());

                // �������ԣ���ʽ�� attrid1=attrvalue1=optype1#attrid2=attrvalue2=optype2�� ���������Ա���=��������ֵ=�������ԵĲ������ͣ����в�������Ŀǰû�ã�ֱ��ʹ��attrid1=attrvalue1= #attrid2=attrvalue2=#������
                DocumentUtil.addSubElementToEle(eReq_multiprod, "attrparam", prodCommitPO.getAttrparam());
                
                // ԭ��ֵ��Ʒ���룬Ŀǰû�á�ֱ�Ӵ�����
                DocumentUtil.addSubElementToEle(eReq_multiprod, "oldprodid", prodCommitPO.getOldprodid());
                
                // ������Դ����ʽ��resid1=restype1=optype1#resid2=restype2=optype2��Ŀǰû�á�ֱ�Ӵ�����
                DocumentUtil.addSubElementToEle(eReq_multiprod, "serviceres", prodCommitPO.getServiceres());
                
                // �ӿڶ�Ӧ���ͣ��Բ�Ʒ�����Ӳ�Ʒ����ģ�����Ӳ�Ʒ�����ʱ��ʹ�á� PCIntRelaNormal����ͨҵ�� PCIntRelaRadio��ҵ���л�
                DocumentUtil.addSubElementToEle(eReq_multiprod, "inftype", prodCommitPO.getInftype());
                
                // �Ƿ�ģ�崦�� 1���� ���������� Ŀǰû�ã�����������
                DocumentUtil.addSubElementToEle(eReq_multiprod, "templetflag", prodCommitPO.getTempletflag());
                
                // ������Чʱ�䣬��ʽ��yyyymmddhh24miss ��effecttype=5ʱ���ش�������Ϊ�ա� �������������������
                DocumentUtil.addSubElementToEle(eReq_multiprod, "startdate", prodCommitPO.getStartdate());
                
                // ����ʧЧʱ�䣬��ʽ��yyyymmddhh24miss ��effecttype=5ʱ�����û�ָ��ʧЧʱ�䣬���û�δָ�������������������������������
                DocumentUtil.addSubElementToEle(eReq_multiprod, "enddate", prodCommitPO.getEnddate());
                
                // ��Ʒ�����롣�Բ�Ʒ�����Ӳ�Ʒ����ģ�����Ӳ�Ʒ�����ʱ��ʹ��
                DocumentUtil.addSubElementToEle(eReq_multiprod, "pkgid", prodCommitPO.getPkgid());
                
                // �������ͣ�������Ʒ���Żݡ�����SP�������Ʒ��ģ�塢ncode
                DocumentUtil.addSubElementToEle(eReq_multiprod, "objtype", prodCommitPO.getObjtype());
                
                // �Żݱ��롣�Բ�Ʒ�����Ӳ�Ʒ����ģ�����Ӳ�Ʒ�����ʱ��ʹ��
                DocumentUtil.addSubElementToEle(eReq_multiprod, "privid", prodCommitPO.getPrivid());
                
                // ģ����롣�Բ�Ʒ�����Ӳ�Ʒ����ģ�����Ӳ�Ʒ�����ʱ��ʹ��
                DocumentUtil.addSubElementToEle(eReq_multiprod, "templetid", prodCommitPO.getTempletid());
            }
            
            // ���ú�̨�ӿ�
            return intMsgUtil.invoke(msgHeader.getProcessCode(), msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("��ѯ��Ʒ��������ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /** 
     * ��Ʒ���ٷ���-��ѯ��Ʒ�����Ӳ�Ʒ
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param nCode ��Ʒ�����롢ģ��ID
     * @param type ���ͣ�2 ��Ʒ�� 3 ģ��
     * @param optType ��������
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark: modify zKF69263 2014/09/11 R003C14L09n01 OR_huawei_201407_41 �����ն˽���EBUS���׶�_��Ʒ���ٷ���
     */
    public ReturnWrap qrySubProds(MsgHeaderPO msgHeader, String nCode, String type, String optType)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element eBody = msgBody.addElement("message_content");
            
            // ��װ�������
            // �ֻ�����
            DocumentUtil.addSubElementToEle(eBody, "telnum", msgHeader.getTelNum());
            
            // ��Ʒ���������ģ�����
            DocumentUtil.addSubElementToEle(eBody, "pkgid", "");
            
            // ���� 2����Ʒ�� 3��ģ��
            DocumentUtil.addSubElementToEle(eBody, "type", type);

            // ��������
            DocumentUtil.addSubElementToEle(eBody, "rectype", "ChangeProduct");
            
            // �Ƿ������������ͱ�־
            DocumentUtil.addSubElementToEle(eBody, "isallrectype", "");
            
            // ���ú�̨�ӿ�
            return intMsgUtil.invoke(msgHeader.getProcessCode(), msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("��ѯ�û��Ѷ�����Ʒʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ����ncode��ѯ�ر���ʾ��Ϣ
     * @param paramMap
     * @return
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public ReturnWrap qryNcodeTips(Map<String, String> paramMap)
    {
        return null;
    }
    
    /**
     * ���ݶ�������ѯ�ر���ʾ��Ϣ
     * @param paramMap
     * @return
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public ReturnWrap qryObjectTips(Map<String, String> paramMap, List<Map<String, String>> prods)
    {
    	return null;
    }
    
    /**
     * ��ѯ��ҪԤԼ���루ɽ����
     * @param paramMap
     * @return
     * @remark create cKF76106 2013/01/23 R003C13L01n01 OR_SD_201301_279
     */
    public ReturnWrap qryChooseTelSD(Map map)
    {
        return null;
    }
    
    /**
     * �����ܶ��ѯ
     * @param map
     * @return
     * @remark create g00140516 2013/02/22 R003C13L02n01 OR_NX_201302_600
     */
    public ReturnWrap qryCurrBillFee(Map<String, String> map)
    {
    	try
    	{
    		Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            String touchoid = (String)map.get("touchoid");
            String menuid = (String)map.get("menuid");
            String telnumber = (String)map.get("telnumber");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            // ��װ�������
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnumber);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_currbillfee", menuid, touchoid, "1", telnumber, operID, termID);
            return intMsgUtil.invoke(docXML);
    	}
    	catch (Exception e)
        {
            logger.error("�����ܶ��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    	
    }
    
    /**
     * ����굥��ӡ�Ƿ񳬳���������
     * 
     * @param map
     * @return
     * @throws Exception
     * @remark create cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
     */
    public ReturnWrap queryPrintCdr(Map<String, String>  map)
    {
        try
        {
            String telnum = (String)map.get("telnum");
            String cdrType = (String)map.get("CDRType");           
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // �굥����
            Element eReq_cdrtype = eBody.addElement("cdrtype");
            eReq_cdrtype.addText(cdrType);
                        
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_printcdr", menuid, unicontact, "1", telnum, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("���굥��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    
    /**
     * �����굥��ӡ����
     * 
     * @param map
     * @return
     * @throws Exception
     * @remark create cKF76106 2013/03/26 R003C13L03n01 OR_NX_201301_222
     */
    public ReturnWrap writePrintCdrLog(Map<String, String>  map)
    {
        try
        {
            String telnum = (String)map.get("telnum");
            String cdrType = (String)map.get("CDRType");           
            String menuid = (String)map.get("menuID");
            String unicontact = (String)map.get("contactID");
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            String actiontype = "PRINT";
            String objecttype = "PRINTCDR";

            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);

            // �굥����
            Element eReq_cdrtype = eBody.addElement("cdrtype");
            eReq_cdrtype.addText(cdrType);
            
            // ��¼��ӡ��־ʱ����PRINT���������óɹ̶�ֵ
            Element eReq_actiontype = eBody.addElement("actiontype");
            eReq_actiontype.addText(actiontype);
            
            // ��¼��ӡ��־ʱ����PRINTCDR,�������óɹ̶�ֵ
            Element eReq_objecttype = eBody.addElement("objecttype");
            eReq_objecttype.addText(objecttype);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_writeprintcdrlog", menuid, unicontact, "1", telnum, operID, termID);
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("���굥��ѯʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * ���û���������������
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     * @remark create cKF76106 2013/07/24 R003C13L07n24 OR_HUB_201307_20
     */
    public ReturnWrap sendSmsHub(Map map)
    {
        return null;
    }
	
    /**
     * У���ֻ����Ƿ���ʵ���ƵǼ�
     * <������ϸ����>
     * @param map
     * @return ReturnWrap 
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap realNameCheck(Map map)
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
            
            String operID = (String)map.get("operID");
            String termID = (String)map.get("termID");
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_realNameCheck", "10001001", "", "1", telnumber, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            logger.error("У���ֻ����Ƿ���ʵ���ƵǼ�ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
        }
    }
    
    /**
     * �������루�£������ģ�
     * 
     * @param map
     * @return ReturnWrap
     * @remark create by hWX5316476 2014/2/18 OR_NX_201402_306 ���������ն��Ż�����_�������������
     */
    public ReturnWrap  resetPwdNew(Map map)
    {
    	try 
    	{
    		String telnum = (String)map.get("telnum");
			String callernum = (String)map.get("callernum");
			String flag = (String)map.get("flag");
			String subcmdid = (String)map.get("subcmdid");
			String old_passwd = (String)map.get("old_passwd");
			String new_passwd = (String)map.get("new_passwd");
			String chktype = (String)map.get("chktype");
			String newpwdcount = (String)map.get("newpwdcount");
			String menuid = (String)map.get("menuid");
			String termid = (String)map.get("termid");
			String touchoid = (String)map.get("touchoid");
			String operid = (String)map.get("operid");
			
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");

            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);

            // �Ƿ񲦴򱾻�  0������1�Ǳ���
            Element eReq_flag = eBody.addElement("flag");
            eReq_flag.addText(flag);

			/*�ӿ������� 
			 * 0 ����������֤ 1 �޸ķ������� 2 ��������(����������������������) 
			 * 3 ��������(ϵͳ����������벢��������) 
			 * 4 ������ʱ�������(һ������Ч)����ͨ�����ŷ�ʽ���͸��û���������벻���ظ��ӿ� 
			 * 5 ��֤����������ȷ��(��֤����ͨ���������������) 
			 * 7 �����״���������У�飬�û��״�ͨ�������������룬Ҫ��ԭ�������Ϊ��ʼ���룬������Ϊ�Ѿ����ù�����
			*/
            Element eReq_subcmdid = eBody.addElement("subcmdid");
            eReq_subcmdid.addText(subcmdid);
            
            // ������
            Element eReq_old_passwd = eBody.addElement("old_passwd");
            eReq_old_passwd.addText(old_passwd);
            
            // ������
            Element eReq_new_passwd = eBody.addElement("new_passwd");
            eReq_new_passwd.addText(new_passwd);
            
            // ��֤��ʽ
            Element eReq_chktype = eBody.addElement("chktype");
            eReq_chktype.addText(chktype);
            
            // �������(���Բ���) 
            Element eReq_callernum = eBody.addElement("callernum");
            eReq_callernum.addText(callernum);
            
            // ������λ��У�飬У���Ƿ���ϴ���λ������0�򲻴���У�顣
            Element eReq_newpwdcount = eBody.addElement("newpwdcount");
            eReq_newpwdcount.addText(newpwdcount);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_busi_pwdresetnew_nx", menuid, touchoid, "1", telnum, operid, termid);
            
            return intMsgUtil.invoke(docXML);
		} 
    	catch (Exception e) 
    	{
    		logger.error("��������(��)ʧ�ܣ�", e);
            
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
    }
    
    /**
     * <�굥�ʼ��·�>
     * <������ϸ����>
     * @param map
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark  create by sWX219697 2014-04-29 OR_SD_201404_105_ɽ��_��������139�����굥Ͷ����Ŀ�ĺ�
     */
    public ReturnWrap sendRecordsMail(Map<String,String> map)
    {
    	String telnum = map.get("telnum");
    	String contactID = map.get("contactID");
    	String operID = map.get("operID");
    	String termID = map.get("termID");
    	String menuID = map.get("menuID");
    	String cdr_type = map.get("cdr_type");
    	String iscycle = map.get("iscycle");
    	String balcycle = map.get("balcycle");
    	String startcycle = map.get("startcycle");
    	String endcycle = map.get("endcycle");
    	String accessnum = map.get("accessnum");
    	String staffid = map.get("staffid");
    	String nosms = map.get("nosms");
    	String qryregion = map.get("qryregion");
    	
    	try 
    	{
			Document doc = DocumentHelper.createDocument();
			Element eBody = doc.addElement("message_content");
			
			//�ͻ��ֻ�����
			Element eReq_telnum = eBody.addElement("telnum");
			eReq_telnum.addText(telnum);
			
			//��ѯ����
			Element eReq_qryregion = eBody.addElement("qryregion");
			eReq_qryregion.addText(qryregion);
			
			//�Ƿ����ڲ�ѯ 0 ����ֹʱ���ѯ��1 �����ڲ�ѯ��Ĭ��Ϊ0
			Element eReq_iscycle = eBody.addElement("iscycle");
			eReq_iscycle.addText(iscycle);
			
			//��ѯ��ʼʱ��
			Element eReq_startcycle = eBody.addElement("startcycle");
			eReq_startcycle.addText(startcycle);
			
			//��ѯ����ʱ��
			Element eReq_endcycle = eBody.addElement("endcycle");
			eReq_endcycle.addText(endcycle);
			
			//�����ڲ�ѯ
			Element eReq_balcycle = eBody.addElement("balcycle");
			eReq_balcycle.addText(balcycle);

			//ģ���ʶ
			Element eReq_cdr_type = eBody.addElement("cdr_type");
			eReq_cdr_type.addText(cdr_type);
			
			//�ͷ��������
			Element eReq_accessnum = eBody.addElement("accessnum");
			eReq_accessnum.addText(accessnum);
			
			//����
			Element eReq_staffid = eBody.addElement("staffid");
			eReq_staffid.addText(staffid);
			
			//�Ƿ��Ͷ������ѣ�0 ���ͣ�1 �����ͣ�Ĭ��Ϊ0��
			Element eReq_nosms = eBody.addElement("nosms");
			eReq_nosms.addText(nosms);
			
			//�ӻ�����ȡ���ӿ�ҵ��id
			String buisID = (String) PublicCache.getInstance().getCachedData(Constants.SEND_RECORDS_BUIS_ID);
			
			Document docXML=intMsgUtil.createMsg(doc, buisID, menuID, contactID, "1", telnum, operID, termID);
			
			return intMsgUtil.invoke(docXML);
		} 
    	catch (Exception e) 
    	{
    		logger.error("�굥�ʼ��·��ӿڵ���ʧ�ܣ�", e);
            
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
    }
    
    /**
     * ɽ�� �ж��û��Ƿ�ͨ���ּƻ�
     * @param map
     * @return ReturnWrap
     * @remark create by sWX219697 2014-05-12 OR_SD_201404_777_ɽ��_�����������նˡ�����__ȫ�������ֲ�ѯ���һ�����
     */
    public ReturnWrap qryIsScoreOpen(Map<String,String> map)
    {
    	
    	//�û��ֻ�����
    	String telnum = map.get("telnum");
    	
    	//����Ա���
    	String operID = map.get("operID");
    	
    	//�ն˻����
    	String termID = map.get("termID");
    	
    	//�ͻ��Ӵ���
    	String contactID = map.get("contactID");
    	
    	//��ǰ�˵����
    	String menuID = map.get("curMenuId");
    	
    	//��Ʒid
    	String prodID = map.get("prodID");
    	
    	//��ѯ����
    	String region = map.get("region");
    	
    	try 
    	{
			Document doc = DocumentHelper.createDocument();
			Element eBody = doc.addElement("message_content");
			
			//�ֻ�����
			Element eReq_telnum = eBody.addElement("telnum");
			eReq_telnum.addText(telnum);
			
			//��Ʒid
			Element eReq_prodid = eBody.addElement("prodid");
			eReq_prodid.addText(prodID);
			
			//��ѯ����
			Element eReq_region = eBody.addElement("region");
			eReq_region.addText(region);
			
			//�Ƿ��ѯ��ʷ
			Element eReq_isqryhis = eBody.addElement("isqryhis");
			eReq_isqryhis.addText("");
			
			//�ӿ�id����
			Document docXML = intMsgUtil.createMsg(doc, "cli_qry_isopenscore", menuID, contactID, "1", telnum, operID, termID);
			
			return intMsgUtil.invoke(docXML);
		} 
    	catch (Exception e) 
    	{
    		logger.error("ɽ���ж��û��Ƿ�ͨ���ּƻ�ʧ�ܣ�",e);
    		
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
		}
    	
    }
    
    /**
     * ��ͥ��ȡ��ҵ��ӿ�(ɽ��)
     * 
     * @param map
     * @return �ӿڴ�����
     * @remark add begin wWX217192 on 20140603 for OR_huawei_201405_875
     */
	public ReturnWrap deleteFamilyMem(Map<String, String> map)
    {
    	try
    	{
    		Document doc = DocumentHelper.createDocument();
    		Element eBody = doc.addElement("message_content");
    		
    		// �ֻ�����
    		eBody.addElement("servnumber").addText(map.get("servnum"));
    		
    		eBody.addElement("opertype").addText("0");
    		
    		eBody.addElement("issendsms").addText("1");
    		
    		Document docXML = intMsgUtil.createMsg(doc, "cli_delete_familymem_sd", map.get("menuID"),
    				map.get("touchOID"), "1", map.get("servnum"), map.get("operID"), map.get("termID"));
    		
    		return intMsgUtil.invoke(docXML);
    	}
    	catch(Exception e)
    	{
    		logger.error("��ͥ��ȡ��ʧ��!", e);
    		
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
    	}
    }

	/**
     * ���ַ��Ų�ѯ��ɽ����
     * @param header ����ͷ��Ϣ
     * @param startDate ��ʼʱ��
     * @param endDate ����ʱ��
     * @return
     * @remark create by jWX216858 2014-10-20 R003C10LG1001 OR_SD_201407_498_ɽ���ƶ������������ֲ�ѯ����֧������
     */
	@Override
	public ReturnWrap qryPayMentScoreSD(MsgHeaderPO header, String startDate,
			String endDate) 
	{
		Document msgBody = DocumentHelper.createDocument();
		Element ebody = msgBody.addElement("message_content");
		
		// �ֻ���
		DocumentUtil.addSubElementToEle(ebody, "telNum", header.getTelNum());
		
		// ��ʼʱ��
		DocumentUtil.addSubElementToEle(ebody, "startdate", startDate + "000000");
		
		// ����ʱ��
		DocumentUtil.addSubElementToEle(ebody, "enddate", endDate + "235959");
		
		// �̶�����1��û�л��ַ�����Ϣ�ӿڷ��ص�crsetΪ�յı�־
		DocumentUtil.addSubElementToEle(ebody, "NODATANOROW", "1");
		
		// ���ýӿڲ�ѯ���ַ�����Ϣ
		return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_PAYMENTSOCRE, header, msgBody);
	}
	
	/**
     * ���ֲ�ѯ��ɽ����
     * @param header ����ͷ��Ϣ
     * @return
     * @remark create by jWX216858 2014-10-20 R003C10LG1001 OR_SD_201407_498_ɽ���ƶ������������ֲ�ѯ����֧������
     */
	@Override
	public ReturnWrap qryScoreValueSD(MsgHeaderPO header)
	{
		Document msgBody = DocumentHelper.createDocument();
		Element ebody = msgBody.addElement("message_content");
		
		// �ֻ���
		DocumentUtil.addSubElementToEle(ebody, "telnum", header.getTelNum());
		
		// ���ýӿڲ�ѯ������Ϣ
		return intMsgUtil.invoke(BusinessIdConstants.CLI_QRY_SCOREVALUESD, header, msgBody);
	}
	
	/**
     * ɾ����ͥ����Ա
     * @param header ������ͷ
     * @param memTelnum ��Ա�ֻ���
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark modify by sWX219697 2015-2-4 10:45:04 OR_SD_201412_777 �����ն˷ſ���ͥ����Աɾ���Ĺ���
     */
    public ReturnWrap delMemByTelnum(MsgHeaderPO header, String memTelnum)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");

            // �ֻ���
            DocumentUtil.addSubElementToEle(ebody, "servNumber", header.getTelNum());
            
            // ɾ���ĳ�Ա�ֻ���
            DocumentUtil.addSubElementToEle(ebody, "memtelnum", memTelnum);
            
            // �����Ű���ҵ��1����,��1��ղ�����
            DocumentUtil.addSubElementToEle(ebody, "allowmemdel", "");
            
            // �����Ʒ����
            DocumentUtil.addSubElementToEle(ebody, "prodid", "");
            
            // ֻ��ɾ���Լ�����1��ղ�������
            DocumentUtil.addSubElementToEle(ebody, "onlydelitself", "");
            
            // ����ɾ����ͥ����Ա�ӿ�
            return intMsgUtil.invoke("cli_delfamilymember", header, doc);
        }
        catch (Exception e)
        {
            logger.error("��ͥ����Աɾ���쳣");
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��ͥ����Աɾ���쳣");
        }
    }
    
    /**
	 * ��ѯ��ԤԼ�����б�
	 * @param requestMsg
	 * @return
	 * @remark create by wWX217192 2015-03-10 OR_SD_201411_988_SD_���������ն�ѡ�Ź����Ż�������
	 */
	public String queryNumResp(OMElement requestMsg)
	{
		// ��ѯ���������URL
    	String wsUrl = CommonUtil.getParamValue(Constants.SH_QUERYNUM_WEBSERVICE_WSURL);
		
    	// ���÷���
    	String operation = CommonUtil.getParamValue(Constants.SH_QUERYNUM_OPERATION);
    	
        String responseMsg = "";
        
        // ��webservice����˷�������
		try 
		{
			responseMsg = new Axis2Client(wsUrl, operation).invokeWebService(requestMsg);
		}
		catch (AxisFault e)
		{
			logger.error("webservice�ӿڲ�ѯ��ԤԼ����ʧ��!", e);
		}
		
		return responseMsg;
	}
	
	/**
	 * ��ѡ����
	 * @param requestMsg
	 * @return
	 * @remark create by wWX217192 2015-03-10 OR_SD_201411_988_SD_���������ն�ѡ�Ź����Ż�������
	 */
	public String pickNumResp(OMElement requestMsg)
	{
		// �����URL��ַ
    	String wsUrl = CommonUtil.getParamValue(Constants.SH_PICKNUM_WEBSERVICE_WSURL);
    	
    	String operation = CommonUtil.getParamValue(Constants.SH_PICKNUM_OPERATION);
    	
    	String responseMsg = "";
    	
    	// ��webservice����˷�������
    	try
    	{
			responseMsg = new Axis2Client(wsUrl, operation).invokeWebService(requestMsg);
		} 
    	catch (AxisFault e) 
    	{
			logger.error("webservice�ӿ���ѡ����ʧ��!", e);
		}
    	
    	return responseMsg;
	}
	
	/**
	 * ԤԼ����
	 * @param requestMsg
	 * @return
	 * @remark create by wWX217192 2015-03-10 OR_SD_201411_988_SD_���������ն�ѡ�Ź����Ż�������
	 */
	public String bookNumResp(OMElement requestMsg)
	{
		// �����URL��ַ
		String wsUrl = CommonUtil.getParamValue(Constants.SH_BOOKNUM_WEBSERVICE_WSURL);
		
		// ���÷���
		String operation = CommonUtil.getParamValue(Constants.SH_BOOKNUM_OPERATION);
		
		String responseMsg = "";
		
		// ��webservice����˷�������
		try 
		{
			responseMsg = new Axis2Client(wsUrl, operation).invokeWebService(requestMsg);
		} 
		catch (AxisFault e) 
		{
			logger.error("webservice�ӿ�ԤԼ����ʧ��!", e);
		}
		
		return responseMsg;
	}
	
	/**
     * ��ѯ�ͻ�Ӧ���ܽ��
     * @param msgHeader
     * @param orgid
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-3-24 OR_NX_201501_1030_����_���ڿ�������ҵ��֧��ϵͳ�����֪ͨ
     */
    public ReturnWrap qryPayAmount(MsgHeaderPO msgHeader, String orgid)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element ebody = msgBody.addElement("message_content");

            // 01:�ֻ���
            DocumentUtil.addSubElementToEle(ebody, "IDType", "01");
            
            // �ֻ���
            DocumentUtil.addSubElementToEle(ebody, "IDValue", msgHeader.getTelNum());
            
            // ��֯��������
            DocumentUtil.addSubElementToEle(ebody, "OrgID", orgid);
            
            // BIZID �ӿ�ID(����opcode)
            DocumentUtil.addSubElementToEle(ebody, "BIZID", "KQFWPayFeeQry");
            
            // ���ýӿڲ�ѯ������Ϣ
            return intMsgUtil.invoke("cli_qry_userPayAmount", msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("��ѯ�ͻ�Ӧ���ܽ���쳣!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"��ѯ�ͻ�Ӧ���ܽ���쳣��");
        }
        
    }
	
    /**
     * ��ؽɷ�
     * <������ϸ����>
     * @param msgHeader
     * @param seq ���������ˮ��
     * @param actualPayAmount ʵ�ʽɷѽ��壩
     * @param orgid ��֯��������
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-3-24 OR_NX_201501_1030_����_���ڿ�������ҵ��֧��ϵͳ�����֪ͨ
     */
    public ReturnWrap nonlocalCharge(MsgHeaderPO msgHeader, String seq, String actualPayAmount, String orgid)
    {
        try
        {
            Document msgBody = DocumentHelper.createDocument();
            Element ebody = msgBody.addElement("message_content");

            // ��ʶ���� 01:�ֻ���
            DocumentUtil.addSubElementToEle(ebody, "IDType", "01");
            
            // �ֻ���
            DocumentUtil.addSubElementToEle(ebody, "IDValue", msgHeader.getTelNum());
            
            // ʵ�ʽɷѽ��壩
            DocumentUtil.addSubElementToEle(ebody, "PayAmount", actualPayAmount);
            
            // ������
            DocumentUtil.addSubElementToEle(ebody, "HandCharge", "0");
            
            // ���������ˮ��
            DocumentUtil.addSubElementToEle(ebody, "Seq", seq);
            
            // ��֯��������
            DocumentUtil.addSubElementToEle(ebody, "OrgID", orgid);
            
            // BIZID �ӿ�ID(����opcode)
            DocumentUtil.addSubElementToEle(ebody, "BIZID", "KQFWPayFee");
            
            // ���ýӿڲ�ѯ������Ϣ
            return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_NONLOCALCHARGE, msgHeader, msgBody);
        }
        catch (Exception e)
        {
            logger.error("��ؽɷ��쳣!", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"��ؽɷ��쳣��");
        }
    }
    
    /**
	 * ɽ����������ҵ��
	 * @param header
	 * @param spid
	 * @param bizid
	 * @return
	 * @remark create by wWX217192 2015-04-02 OR_SD_201502_373_ɽ��_���������ն˳��غ����ְ���ҵ���֧������
	 */
	public ReturnWrap orderSP(MsgHeaderPO header, String spid, String bizid)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");
            //modify begin fwx439896 2017-05-22 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3_����������˵����
            //���ܿ�  ��װ����
            String  businessId=BusinessIdConstants.CLI_SD_CHANGESUBSMONSERV;
        	if(CommonUtil.isInvokeOpenEbus(businessId))
        	{
        		// �ֻ�����
                DocumentUtil.addSubElementToEle(ebody, "telnum",header.getTelNum());
                
                //������Դ    ѡ�����Ϊ��
                DocumentUtil.addSubElementToEle(ebody, "source", "");
                
                 // ��ҵ����  ҵ�����
                DocumentUtil.addSubElementToEle(ebody, "DETAILINFO",spid +","+bizid);
               
                // �������� Order ���� Cancel �˶� Pause ��ͣ Resume �ָ� ���� ���������
                DocumentUtil.addSubElementToEle(ebody, "opertype", "Order"  );
                
                //�˶���־ 1 �˶�ĳ��������ҵ������ҵ��  2 �˶�����ҵ��  3 ����ҵ�������˶�  4	����DOMAIN�����˶�     Ĭ�ϴ���3
                DocumentUtil.addSubElementToEle(ebody, "cancel_flag", "3");
                
                //ƽ̨����
                DocumentUtil.addSubElementToEle(ebody, "SPDOMAIN", "bsacAtsv");
              
                //	����	String	��ʶ
                DocumentUtil.addSubElementToEle(ebody, "FLAG", " ");

                //	�Ƿ���srp���Ʋ���0������ 1 ����
                DocumentUtil.addSubElementToEle(ebody, "SRPFLAG", "1");
                
         
        	}
        	else
        	{
        		 // �ֻ���
                DocumentUtil.addSubElementToEle(ebody, "telnum", header.getTelNum());
                
                // ��ȡ��������
                DocumentUtil.addSubElementToEle(ebody, "operType", "Order");
                
                // ������Դ
                DocumentUtil.addSubElementToEle(ebody, "source", "");
                
                // �˶���ʶ��ֻ�в�������Ϊ�˶�ʱ����ȡ�˶���ʶ
                DocumentUtil.addSubElementToEle(ebody, "cancel_flag", "3");
                
                // �Ƿ���srp���Ʋ���
                DocumentUtil.addSubElementToEle(ebody, "srpFlag", "1");
                
                // �Ƿ��ύ 1Ϊ�ύ 0Ϊ���ύ(У��) Ĭ���ύ
                DocumentUtil.addSubElementToEle(ebody, "isSubmit", "1");
                
                // ������ϸ��Ϣ
                Element detailInfo = ebody.addElement("detailInfo");
                
                // ����ID 
                DocumentUtil.addSubElementToEle(detailInfo, "spid", spid);
                
                // spҵ�����  
                DocumentUtil.addSubElementToEle(detailInfo, "spBizId", bizid);
                
                // spҵ���������
                DocumentUtil.addSubElementToEle(detailInfo, "spBizType", "");
                
                // sp����domain ����˶���־Ϊ"5"(5 == CANCEL_FLAG������DOMAIN�����˶�
                DocumentUtil.addSubElementToEle(detailInfo, "spDomain", "");
                
                // ��Ч����
                DocumentUtil.addSubElementToEle(detailInfo, "effectType", "");
                
                DocumentUtil.addSubElementToEle(detailInfo, "SPAttr", ""); 
                
                DocumentUtil.addSubElementToEle(detailInfo, "OuterOperSeq", "");
        	}	
            //modify begin fwx439896 2017-05-22 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3_����������˵����

            // ������������ҵ��ӿ�
            return intMsgUtil.invoke(businessId, header, doc);
            
        }
        catch (Exception e)
        {
            logger.error("����ҵ�񶩹�ʧ�ܣ�", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "����ҵ�񶩹�ʧ�ܣ�");
        }
    }
	
	/**
	 * �����мۿ�����
	 * @param valueCardVO
	 * @param paramMap
	 * @return
	 * @remark create by wWX217192 2015-05-13 OR_HUB_201503_1068_HUB_������ϼ��š������·�__���ӻ��мۿ�ҵ��ʡ��ϵͳ���췽����֪ͨ���ĸ������������ն˸���
	 */
	public ReturnWrap getValueCards(MsgHeaderPO header, Map<String, String> inParam)
	{
		try
		{
			return getIntMsgUtil().invokeDubbo("BLCSElecCardSale", header, inParam);
		}
		catch(Exception e)
		{
			logger.error("�мۿ�����ʧ�ܣ�", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "�мۿ�����ʧ�ܣ�");
		}
	}
	
    /**
     * <�мۿ�У��>
     * <������ϸ����>
     * @param msgHeader ��Ϣͷ
     * @param paramMap ��Ϣ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-6-10 OR_SD_201505_1022_ɽ�����ӳ�ֵ����������_�����ն˸���
     */
    public ReturnWrap authValueCard(MsgHeaderPO msgHeader, Map<String, String> paramMap)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");

            // �����мۿ�����
            DocumentUtil.addSubElementToEle(ebody, "elecCardNum", paramMap.get("elecCardNum"));
            
            // �����мۿ�У��ӿ�
            return intMsgUtil.invoke("cli_qry_chkIfEvcCard", msgHeader, doc);
        }
        catch (Exception e)
        {
            logger.error("�мۿ�����У��ʧ�ܣ�", e);
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"�мۿ�����У��ʧ��");
        }
    }
    
    /**
     * <�мۿ���ֵ>
     * @param msgHeader ��Ϣͷ
     * @param valueCardPwd �мۿ�����
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2015-6-10 OR_SD_201505_1022_ɽ�����ӳ�ֵ����������_�����ն˸���
     */
    public ReturnWrap valueCardCharge(MsgHeaderPO msgHeader,String valueCardPwd)
    {
        try
        {
            Document doc = DocumentHelper.createDocument();
            Element ebody = doc.addElement("message_content");
            
            // �����мۿ���ֵ���� 01��ʵ��Ӫҵ��;03���ƶ�����;04������Ӫҵ��;05�������ն�;08������Ӫҵ��;09����������
            DocumentUtil.addSubElementToEle(ebody, "channelType", "05");
            
            // ����ֵ����
            DocumentUtil.addSubElementToEle(ebody, "calledIdValue", msgHeader.getRouteValue());
            
            // ���ӳ�ֵ������
            DocumentUtil.addSubElementToEle(ebody, "cardPassWord", valueCardPwd);
            
            //modify begin lwx439898 2017-05-15 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3
            if (CommonUtil.isInvokeOpenEbus(BusinessIdConstants.CLI_BUSI_ELECCARDCHARGE))
            {               
                //�ܿ� ��ֵ����ʡ�ݱ��룬ɽ����531
                DocumentUtil.addSubElementToEle(ebody, "callingProv", Constants.VALUECARD_SD_PROVCODE);
            }
            else
            {
                // ��ֵ����ʡ�ݱ��룬ɽ����531
                DocumentUtil.addSubElementToEle(ebody, "calllingProv", Constants.VALUECARD_SD_PROVCODE);
                
            }
            //modify end lwx439898 2017-05-15 OR_huawei_201704_412_��ɽ���ƶ��ӿ�Ǩ��ר�⡿-�����ն�ҵ�����3
                      
            // �����ֻ����� �ɲ���
            DocumentUtil.addSubElementToEle(ebody, "callingIdValue", "");
            
            // Ӫҵ�����룬�ɲ���
            DocumentUtil.addSubElementToEle(ebody, "actionID", "");
            
            // ����Ա����
            DocumentUtil.addSubElementToEle(ebody, "actionUserID", msgHeader.getOperId());

            // ������������ҵ��ӿ�
            return intMsgUtil.invoke("cli_busi_elecCardCharge", msgHeader, doc);
        }
        catch (Exception e)
        {
            logger.error("�мۿ���ֵʧ�ܣ�", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "�мۿ���ֵʧ�ܣ�");
        }
    }
    
    /**
     * <ɽ�����ֶһ�����ȯ>
     * <������ϸ����>
     * @param header
     * @param doc
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-5-29 14:44:35 OR_SD_201505_61�����ն������ӻ��ֶһ�����ȯ
     */
    public ReturnWrap scoreExchange(MsgHeaderPO header,  Document doc)
    {
        try
        {
            return intMsgUtil.invoke("cli_busi_scoreExchangeSD", header, doc);
        }
        catch (Exception e)
        {
            logger.error("���ֶһ�����ȯʧ�ܣ�", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "���ֶһ�����ȯʧ��");
        }

    }
    
    /**
     * <���ŷ���app���ص�ַ>
     * <������ϸ����>
     * @param header
     * @param doc
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-7-7 OR_SD_201506_152_ɽ��_�������ն����ӡ�����APPӦ�á�����
     */
    public ReturnWrap sendAddress(MsgHeaderPO header, Document doc)
    {
        try
        {
            return intMsgUtil.invoke(BusinessIdConstants.CLI_BUSI_SENDSMSINFO, header, doc);
        }
        catch (Exception e)
        {
            logger.error("���ŷ���app���ص�ַʧ�ܣ�", e);
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "���ŷ���app���ص�ַʧ�ܣ�");
        }
    }
    
    @Override
    public ReturnWrap getIs4GCard(MsgHeaderPO msgHeader)
    {
        return null;
    }

    @Override
    public ReturnWrap getAvailIntegralEbus(MsgHeaderPO msgHeader)
    {
        return null;
    }

    /**
     * @return ���� intMsgUtil
     */
    public IntMsgUtil getIntMsgUtil()
    {
        return intMsgUtil;
    }
    
}



