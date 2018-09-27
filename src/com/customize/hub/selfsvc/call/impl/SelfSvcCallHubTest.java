package com.customize.hub.selfsvc.call.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

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



public class SelfSvcCallHubTest implements SelfSvcCallHub
{
    private IntMsgUtil intMsgUtil;
    
    private IntMsgUtilNew intMsgUtilNew;
    
    public ReturnWrap qryArrear(Map paramMap)
    {
        try
        {
            String resp = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recQryArrear</menuid> "
                    + "<process_code>cli_qry_arrear</process_code><verify_code></verify_code><resp_time>20110513002353</resp_time> "
                    + "<sequence><req_seq>50</req_seq><operation_seq></operation_seq></sequence><retinfo> "
                    + "<rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg> "
                    + "</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?> "
                    + "<message_content><arrear><time>201101</time><fee>10.00</fee></arrear><arrear><time>201103</time> "
                    + "<fee>19.73</fee></arrear><arrear><time>201104</time><fee>121.25</fee></arrear><arrear><time>201105</time> "
                    + "<fee>99.12</fee></arrear></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(resp);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            return rtwp;
        }
        
    }
    
    /**
     * �����˻��ɷѷ�ʽ��ѯ��У���Ƿ�Ϊ�����˻���
     * @param map
     * @return
     * @remark create hWX5316476 2014-05-22 V200R003C10LG0501 OR_huawei_201405_234  �����ն˽���EBUSһ�׶�_�ɷ�
     */
    public ReturnWrap getAccSettleTypeByPayType(Map<String, String> map)
    {
        // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if(IntMsgUtil.isTransEBUS("PTGetAccSettleTypeByPayType"))
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(1);
            rtwp.setReturnMsg("");
            return rtwp;
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
            // ͳһ�ӿ�ƽ̨תEBUS����
            if(IntMsgUtil.isTransEBUS("Atsv_Qry_Fee_Hub"))
            {
                // ����Ϊ��ֵ�ԣ�����JSONObj
                Map<String,Object> outParamMap = new HashMap<String,Object>();

                // ƴװ����
                outParamMap.put("region", "531");
                outParamMap.put("regionname", "����");
                outParamMap.put("productid", "");
                outParamMap.put("productname", "pp.gt.ygtch.634");
                outParamMap.put("balance", "1000");
                JSONObject jsonObj = JSONObject.fromObject(outParamMap);
                System.out.println(jsonObj.toString());
                
                return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
            
            }
            String resp = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
                    + "<message_head version=\"1.0\"><menuid></menuid><process_code>cli_qry_fee_hub</process_code>"
                    + "<verify_code></verify_code><resp_time>20100921001956</resp_time>"
                    + "<sequence><req_seq>33</req_seq><operation_seq></operation_seq></sequence>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
                    + "</retinfo></message_head><message_body>" + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    + "<message_content>" + "<region>531</region>" + "<regionname>����</regionname>"
                    + "<productid></productid>" + "<productname></productname>" + "<balance>1000</balance>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(resp);
        }
        catch (Exception e)
        {
            return new ReturnWrap();
        }
    }
    
    /**
     * �����ɷѽӿ�
     * 
     * @param map
     * @return
     */
    public ReturnWrap chargeCommit(Map map)
    {
        try
        {
            // ͳһ�ӿ�ƽ̨תEBUS����
            if(IntMsgUtil.isTransEBUS("atsvBusiChargeFee"))
            {
                // ����Ϊ��ֵ�ԣ�����JSONObj
                Map<String,Object> outParamMap = new HashMap<String,Object>();

                // ƴװ����
                outParamMap.put("dealNum", "123456");
                outParamMap.put("dealTime", "2011-05-28 10:07:55");
                outParamMap.put("presentOid", "6546498781111447");
                JSONObject jsonObj = JSONObject.fromObject(outParamMap);
                System.out.println(jsonObj.toString());
                String[][] outParamKeyDefine = {{"dealnum","dealtime","presentOid"},{"dealNum","dealTime","presentOid"}};

                
                return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),outParamKeyDefine,null);
            }
            String resp = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_busi_chargefee_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<dealNum>123456789</dealNum><dealTime>2011-05-28 10:07:55</dealTime>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(resp);
        }
        catch (Exception e)
        {           
            return new ReturnWrap();
        }
    }
    /**
     * ���������������ն��ֽ�ɷѽӿ� <������ϸ����>
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chargeCommitByAgent(Map map){
        try
        {
            // ͳһ�ӿ�ƽ̨תEBUS����
            if(IntMsgUtil.isTransEBUS("BLDeductAcctBalance"))
            {
                // ����Ϊ��ֵ�ԣ�����JSONObj
                Map<String,Object> outParamMap = new HashMap<String,Object>();

                // ƴװ����
                outParamMap.put("dealNum", "123456");
                outParamMap.put("dealTime", "2011-05-28 10:07:55");
                JSONObject jsonObj = JSONObject.fromObject(outParamMap);
                System.out.println(jsonObj.toString());
                return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    +"<menuid>feeCharge</menuid><process_code>cli_busi_agentaccchargeback_hub</process_code><verify_code></verify_code>"
                    +"<resp_time>20131114170411</resp_time><sequence><req_seq>2</req_seq><operation_seq></operation_seq></sequence>"
                    +"<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
                    +"</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    +"<message_content><dealNum>711131114405769745</dealNum><dealTime>2013-11-14 17:06:07</dealTime></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
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
            // ͳһ�ӿ�ƽ̨תEBUS����
            if(IntMsgUtil.isTransEBUS("PTGetParameterByID"))
            {
                // ����Ϊ��ֵ�ԣ�����JSONObj
                Map<String,Object> outParamMap = new HashMap<String,Object>();

                // ƴװ����
                outParamMap.put("paramValue", "123456");
                
                // ���μ�ֵ�� ȡֵ����������ļ�����Ӧ��ϵ����
                String[][] outParamKeyDefine = {{"paramValue"},{"paramvalue"}};
                JSONObject jsonObj = JSONObject.fromObject(outParamMap);
                System.out.println(jsonObj.toString());
                return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),outParamKeyDefine,null);
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
                    "<menuid></menuid><process_code>cli_qry_parametervaluebyid_hub</process_code><verify_code>" +
                    "</verify_code><resp_time>20110715144704</resp_time><sequence><req_seq>52</req_seq><operation_seq>" +
                    "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" +
                    "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>" +
                    "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><paramvalue>1</paramvalue>" +
                    "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
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
        ReturnWrap rtwp = new ReturnWrap();
        rtwp.setStatus(SSReturnCode.SUCCESS);
        
        return rtwp;
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
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_busi_chargefee</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<bcycle_count>2</bcycle_count>" 
                    // add begin zKF66389 2015-11-13 R003C10L11n01 OR_huawei_201510_582
                    + "<invoice_cnt>1</invoice_cnt>"
                    // add end zKF66389 2015-11-13 R003C10L11n01 OR_huawei_201510_582
                    + "<bill><bcycleid>201009</bcycleid><ss_fee>100</ss_fee><ys_fee>99.99</ys_fee>"
                    + "<capital_fee>��ʰ��Ԫ���Ǿ���</capital_fee><last_balance>0</last_balance><this_balance>100</this_balance>"
                    + "<item_count>3</item_count><item_set>�ϴ����|0;���ν���|100;�������|100</item_set></bill>"
                    + "<bill><bcycleid>201009</bcycleid><ss_fee>100</ss_fee><ys_fee>99.99</ys_fee>"
                    + "<capital_fee>��ʰ��Ԫ���Ǿ���</capital_fee><last_balance>0</last_balance><this_balance>100</this_balance>"
                    + "<item_count>3</item_count><item_set>�ϴ����|0;���ν���|100;�������|100</item_set></bill>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * ���˵���ѯ
     */
    public ReturnWrap qryMonthBill(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recQryArrear</menuid> "
                    + "<process_code>cli_qry_arrear</process_code><verify_code></verify_code><resp_time>20110513002353</resp_time> "
                    + "<sequence><req_seq>50</req_seq><operation_seq></operation_seq></sequence><retinfo> "
                    + "<rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg> "
                    + "</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?> "
                    + "<message_content>"
                    + "<billItem><type>1</type><recName>�����ײ�</recName><recFee>6000</recFee></billItem>"
                    + "<billItem><type>2</type><recName>����</recName><recFee>500</recFee></billItem>"
                    + "<billItem><type>2</type><recName>��������</recName><recFee>600</recFee></billItem>"
                    + "<billItem><type>2</type><recName>�ֻ�����Ϣ��</recName><recFee>300</recFee></billItem>"
                    + "<billItem><type>2</type><recName>������</recName><recFee>400</recFee></billItem>"
                    + "<billItem><type>2</type><recName>������ʾ</recName><recFee>500</recFee></billItem>"
                    + "<billItem><type>3</type><recName>����ͨ����</recName><recFee>100</recFee></billItem>"
                    + "<billItem><type>3</type><recName>����ͨ�ŷ�</recName><recFee>450</recFee></billItem>"
                    + "<billItem><type>3</type><recName>ʡ�����η�</recName><recFee>20</recFee></billItem>"
                    + "<billItem><type>4</type><recName>����������Ϣ��</recName><recFee>800</recFee></billItem>"
                    + "<billItem><type>5</type><recName>���˱������Ѷ�</recName><recFee>2650</recFee></billItem>"
                    + "<billItem><type>TotalFee</type><recName>���·��úϼ�</recName><recFee>8500</recFee></billItem>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
        
    }
    
    /**
     * �˻�����ѯ
     */
    public ReturnWrap queryBalance(Map<String, String> paramMap)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if(IntMsgUtil.isTransEBUS("nkfqoverage139Mail"))
            {
                // ����Ϊ��ֵ�ԣ�����JSONObj
                Map<String,Object> outParamMap = new HashMap<String,Object>();
                
                // ƴװ����
                outParamMap.put("balance", "127");
                outParamMap.put("cashBalance", "1");
                outParamMap.put("cardBalance", "2");
                outParamMap.put("monDeduction", "3");
                outParamMap.put("presentBalance", "4");
                outParamMap.put("monpresentBalance", "5");
                outParamMap.put("dkBalance", "6");
                outParamMap.put("predkBalance", "7");
                outParamMap.put("left", "8");
                outParamMap.put("overdraft", "9");
                outParamMap.put("nowfee", "10");
                outParamMap.put("totalowe", "11");
                outParamMap.put("receiveMoney", "999");

                // ���μ�ֵ�� ȡֵ����������ļ�����Ӧ��ϵ����
                String[][] outParamKeyDefine = {{"balance","cashBalance","cardBalance","monDeduction","presentBalance","monpresentBalance","dkBalance","predkBalance","left","overdraft","nowfee","totalowe","receiveMoney"},
                                                {"balance","cashBalance","cardBalance","monDeduction","presentBalance","monPresentBalance","DKBalance","preDKBalance","availableBalance","credit","realTimeFee","hisArrears","receiveMoney"}};
                
                JSONObject jsonObj = JSONObject.fromObject(outParamMap);
                System.out.println(jsonObj.toString());
                return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),outParamKeyDefine,null);
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryBill</menuid><process_code>cli_qry_balance</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<balance>1299</balance>"
                    + "<cashBalance>1</cashBalance>"
                    + "<cardBalance>2</cardBalance>"
                    + "<monDeduction>3</monDeduction>"
                    + "<presentBalance>4</presentBalance>"
                    + "<monPresentBalance>5</monPresentBalance>"
                    + "<DKBalance>6</DKBalance>"
                    + "<preDKBalance>7</preDKBalance>"
                    + "<availableBalance>8</availableBalance>"
                    + "<credit>9</credit>"
                    + "<realTimeFee>10</realTimeFee>"
                    + "<hisArrears>11</hisArrears>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
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
            ReturnWrap rtwp = intMsgUtil.invokeDubbo("cli_intf_qusages", msgHeaderPO, paramMap,false);    
            
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
            if (SSReturnCode.SUCCESS == rtwp.getStatus())
            {
                String json=(String)rtwp.getReturnObject();
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
                    rtwp.setReturnObject(result);
                }
                catch (ParseException e)
                {
                    rtwp.setStatus(SSReturnCode.ERROR);
                    rtwp.setReturnMsg("��������ֵ�쳣");
                    e.printStackTrace();
                }                
            }
            
            return rtwp;
        
        
        
//        try
//        {
//            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
//            if (IntMsgUtil.isTransEBUS("nkfQueryPrivUESD")) 
//            {
//                String response = "{errno:'0',retDataList:[{packagetype:'1type',packageid:'1privid',packagename:'1privname',presenttype:'1attname',presentnum:'1attcost',totalused:'1cyclecount',unit:'1unit'}"
//                    + ",{packagetype:'2type',packageid:'2privid',packagename:'2privname',presenttype:'2attname',presentnum:'2attcost',totalused:'2cyclecount',unit:'2unit'}"
//                    + ",{packagetype:'3type',packageid:'3privid',packagename:'3privname',presenttype:'3attname',presentnum:'3attcost',totalused:'3cyclecount',unit:'3unit'}"
//                    + ",{packagetype:'4type',packageid:'4privid',packagename:'4privname',presenttype:'4attname',presentnum:'4attcost',totalused:'4cyclecount',unit:'4unit'}"
//                    + ",{packagetype:'1type',packageid:'1privid',packagename:'1privname',presenttype:'1attname',presentnum:'1attcost',totalused:'1cyclecount',unit:'1unit'}"
//                    + ",{packagetype:'2type',packageid:'2privid',packagename:'2privname',presenttype:'2attname',presentnum:'2attcost',totalused:'2cyclecount',unit:'2unit'}"
//                    + ",{packagetype:'3type',packageid:'3privid',packagename:'3privname',presenttype:'3attname',presentnum:'3attcost',totalused:'3cyclecount',unit:'3unit'}"
//                    + ",{packagetype:'4type',packageid:'4privid',packagename:'4privname',presenttype:'4attname',presentnum:'4attcost',totalused:'4cyclecount',unit:'4unit'}"
//                    + ",{packagetype:'1type',packageid:'1privid',packagename:'1privname',presenttype:'1attname',presentnum:'1attcost',totalused:'1cyclecount',unit:'1unit'}"
//                    + ",{packagetype:'2type',packageid:'2privid',packagename:'2privname',presenttype:'2attname',presentnum:'2attcost',totalused:'2cyclecount',unit:'2unit'}"
//                    + ",{packagetype:'3type',packageid:'3privid',packagename:'3privname',presenttype:'3attname',presentnum:'3attcost',totalused:'3cyclecount',unit:'3unit'}"
//                    + ",{packagetype:'4type',packageid:'4privid',packagename:'4privname',presenttype:'4attname',presentnum:'4attcost',totalused:'4cyclecount',unit:'4unit'}]}";
//                
//                ReturnWrap rtwp = getIntMsgUtil().parseJsonResponse(response, null, 
//                        new String[]{"packagetype", "packageid", "packagename", "presenttype", "presentnum", "totalused", "unit"});
//                if (SSReturnCode.SUCCESS == rtwp.getStatus() && rtwp.getReturnObject() instanceof CTagSet) {
//                    
//                    Vector vector = new Vector();
//                    vector.add(rtwp.getReturnObject());
//                    vector.add(new CRSet());
//                    rtwp.setReturnObject(vector);
//                }
//                return rtwp;
//            }
//            
//            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
//                    + "<menuid></menuid><process_code>cli_busi_chargefee_hub</process_code><verify_code></verify_code>"
//                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
//                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
//                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
//                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
//                    + "<calctime>20110530120000</calctime><privitem><type>1type</type><privid>1privid</privid><privname>1privname</privname><attname>1attname</attname><attcost>1attcost</attcost><cyclecount>1cyclecount</cyclecount><unit>1unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>2type</type><privid>2privid</privid><privname>2privname</privname><attname>2attname</attname><attcost>2attcost</attcost><cyclecount>2cyclecount</cyclecount><unit>2unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>3type</type><privid>3privid</privid><privname>3privname</privname><attname>3attname</attname><attcost>3attcost</attcost><cyclecount>3cyclecount</cyclecount><unit>3unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>4type</type><privid>4privid</privid><privname>4privname</privname><attname>4attname</attname><attcost>4attcost</attcost><cyclecount>4cyclecount</cyclecount><unit>4unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>1type</type><privid>1privid</privid><privname>1privname</privname><attname>1attname</attname><attcost>1attcost</attcost><cyclecount>1cyclecount</cyclecount><unit>1unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>2type</type><privid>2privid</privid><privname>2privname</privname><attname>2attname</attname><attcost>2attcost</attcost><cyclecount>2cyclecount</cyclecount><unit>2unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>3type</type><privid>3privid</privid><privname>3privname</privname><attname>3attname</attname><attcost>3attcost</attcost><cyclecount>3cyclecount</cyclecount><unit>3unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>4type</type><privid>4privid</privid><privname>4privname</privname><attname>4attname</attname><attcost>4attcost</attcost><cyclecount>4cyclecount</cyclecount><unit>4unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>1type</type><privid>1privid</privid><privname>1privname</privname><attname>1attname</attname><attcost>1attcost</attcost><cyclecount>1cyclecount</cyclecount><unit>1unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>2type</type><privid>2privid</privid><privname>2privname</privname><attname>2attname</attname><attcost>2attcost</attcost><cyclecount>2cyclecount</cyclecount><unit>2unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>3type</type><privid>3privid</privid><privname>3privname</privname><attname>3attname</attname><attcost>3attcost</attcost><cyclecount>3cyclecount</cyclecount><unit>3unit</unit></privitem>"
//                    + "<calctime>20110530120000</calctime><privitem><type>4type</type><privid>4privid</privid><privname>4privname</privname><attname>4attname</attname><attcost>4attcost</attcost><cyclecount>4cyclecount</cyclecount><unit>4unit</unit></privitem>"
//                    + "</message_content>]]></message_body></message_response>";
//            return intMsgUtil.parseResponse(response);
//        }
//        catch (Exception e)
//        {
//            ReturnWrap rtwp = new ReturnWrap();
//            rtwp.setStatus(0);
//            rtwp.setReturnMsg("");
//            e.printStackTrace();
//            return rtwp;
//        }
    }
    
    /**
     * ����Ʒ���ʷѼ��ѿ�ͨ����
     */
    public ReturnWrap qryFavourable(MsgHeaderPO msgHeaderPO)
    {
        // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("kfQueryProductTree")) 
        {
            String response = "[{\"applydate\":\"2013-11-11 00:00:00\",\"cdeep1\":\"1\",\"enddate\":\"\",\"packageid\":\"150\"," +
            "\"packagename\":\"�ƶ���������ҵ����\",\"pkgtype\":\"��Ʒ\"},{\"applydate\":\"2013-11-11 00:00:00\"," +
            "\"cdeep1\":\"1.1\",\"enddate\":\"\",\"packageid\":\"150\",\"packagename\":\"�ƶ�������������\",\"pkgtype\":" +
            "\"����\"},{\"applydate\":\"2011-08-26 19:13:59\",\"cdeep1\":\"2\",\"enddate\":\"\",\"packageid\":" +
            "\"pkg.serv.fetion\",\"packagename\":\"����ҵ���\",\"pkgtype\":\"��Ʒ��\"},{\"applydate\":\"2011-08-26 19:13:59\"," +
            "\"cdeep1\":\"3\",\"enddate\":\"\",\"packageid\":\"pkg.serv.my\",\"packagename\":\"�������ΰ�\",\"pkgtype\":\"��Ʒ��\"}]";
          
            return getIntMsgUtil().parseJsonResponse(response, null, new String[]{"cdeep1","pkgtype", "packageid", "packagename", "applydate", "enddate"});
        }
        
        // ��ȷ
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>showUserservice</menuid><process_code>cli_qry_producttree_hub" +
                "</process_code><verify_code></verify_code><resp_time>20110802163908</resp_time><sequence><req_seq>34" +
                "</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode>" +
                "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>" +
                "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
                "<detail><level>1</level><type>��Ʒ</type><typeid>pkg.prod.zshf</typeid>" +
                "<typename>[��ѡ�Żݰ�27]�������ۿ۷�������</typename><startdate>2009-07-01 00:00:00</startdate>" +
                "<enddate></enddate></detail><detail><level>2</level><type>��Ʒ</type><typeid>pkg.prod.least_qs</typeid>" +
                "<typename>[��ѡ�Żݰ�26]-��������������</typename><startdate>2009-08-01 00:00:00</startdate><enddate>" +
                "</enddate></detail><detail><level>3</level><type>��Ʒ��</type><typeid>pkg.serv.fetion</typeid><typename>" +
                "����ҵ���</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>3.1</level>" +
                "<type>��Ʒ</type><typeid>FetionBase</typeid><typename>���Ż���ҵ��</typename><startdate>2010-01-31 13:28:09</startdate>" +
                "<enddate></enddate></detail><detail><level>3.1.1</level><type>����</type><typeid>FetionBase</typeid><typename>���Ż���ҵ��" +
                "</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>4</level>" +
                "<type>��Ʒ��</type><typeid>pkg.prod.139mail</typeid><typename>139�ֻ������Ʒ��</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>4.1</level><type>��Ʒ</type><typeid>139mail_mf</typeid><typename>139�ֻ�������Ѱ�</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>4.1.1</level><type>����</type><typeid>139mail_mf</typeid><typename>139�ֻ�������Ѱ�</typename><startdate>2010-01-31 13:28:09</startdate><enddate></enddate></detail><detail><level>5</level><type>��Ʒ</type><typeid>SELFRING</typeid><typename>����</typename><startdate>2010-06-11 09:47:40</startdate><enddate></enddate></detail><detail><level>5.1</level><type>����</type><typeid>SELFRING</typeid><typename>����</typename><startdate>2010-06-11 09:47:40</startdate><enddate></enddate></detail><detail><level>6</level><type>��Ʒ</type><typeid>pkg.prod.newbusi</typeid><typename>��ҵ�����Żݰ�</typename><startdate>2010-07-01 00:00:00</startdate><enddate></enddate></detail><detail><level>7</level><type>��Ʒ</type><typeid>LDTX</typeid><typename>��������</typename><startdate>2010-06-22 11:52:22</startdate><enddate></enddate></detail><detail><level>7.1</level><type>����</type><typeid>LDTX</typeid><typename>��������</typename><startdate>2010-06-22 11:52:22</startdate><enddate></enddate></detail><detail><level>8</level><type>��Ʒ</type><typeid>KJTX</typeid><typename>������֪��</typename><startdate>2010-06-22 11:52:22</startdate><enddate></enddate></detail><detail><level>8.1</level><type>����</type><typeid>KJTX</typeid><typename>������֪��</typename><startdate>2010-06-22 11:52:22</startdate><enddate></enddate></detail><detail><level>9</level><type>��Ʒ</type><typeid>inc.grp.vpmn.mem.next</typeid><typename>��Ա��ѡ�ײ�-������Ч(�������ڵ���ת���û�)</typename><startdate>2011-04-01 00:00:00</startdate><enddate></enddate></detail><detail><level>9.1</level><type>�Ż�</type><typeid>SVN250035</typeid><typename>��Ա�ײ�37-����5Ԫ�ײ�5</typename><startdate>2011-04-01 00:00:00</startdate><enddate></enddate></detail><detail><level>10</level><type>��Ʒ</type><typeid>pkg.gt.st</typeid><typename>[�����Żݰ�]:��׼ȫ��ͨ</typename><startdate>2007-09-01 00:00:00</startdate><enddate></enddate></detail><detail><level>10.1</level><type>�Ż�</type><typeid>B210221</typeid><typename>����ȫ��ͨ88�ײ�88ԪG3������</typename><startdate>2010-02-01 00:00:00</startdate><enddate></enddate></detail><detail><level>11</level><type>��Ʒ��</type><typeid>pkg.prod.gprs</typeid><typename>GPRSҵ����</typename><startdate>2005-07-01 00:00:00</startdate><enddate></enddate></detail><detail><level>11.1</level><type>����</type><typeid>150</typeid><typename>GPRS����</typename><startdate>2005-04-26 17:58:42</startdate><enddate></enddate></detail><detail><level>12</level><type>��Ʒ��</type><typeid>pkg.serv.ct</typeid><typename>����;��</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>12.1</level><type>��Ʒ</type><typeid>109</typeid><typename>���ʳ�;</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>12.1.1</level><type>����</type><typeid>109</typeid><typename>���ʳ�;</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>12.2</level><type>��Ʒ</type><typeid>901</typeid><typename>���ڳ�;</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>12.2.1</level><type>����</type><typeid>901</typeid><typename>���ڳ�;</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>13</level><type>��Ʒ��</type><typeid>pkg.serv.my</typeid><typename>�������ΰ�</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>13.1</level><type>��Ʒ</type><typeid>904</typeid><typename>��������</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>13.1.1</level><type>����</type><typeid>904</typeid><typename>��������</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>14</level><type>��Ʒ</type><typeid>120</typeid><typename>����ת��</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>14.1</level><type>����</type><typeid>120</typeid><typename>����ת��</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>15</level><type>��Ʒ</type><typeid>107</typeid><typename>����ͨ��</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>15.1</level><type>����</type><typeid>107</typeid><typename>����ͨ��</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>16</level><type>��Ʒ</type><typeid>100</typeid><typename>������ʾ</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>16.1</level><type>����</type><typeid>100</typeid><typename>������ʾ</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>17</level><type>��Ʒ</type><typeid>114</typeid><typename>����Ϣ</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>17.1</level><type>����</type><typeid>114</typeid><typename>����Ϣ</typename><startdate>2000-09-11 11:38:08</startdate><enddate></enddate></detail><detail><level>18</level><type>��Ʒ</type><typeid>inc.grp.vpmn.mem.base</typeid><typename>��ԱVPMN����</typename><startdate>2009-08-11 00:00:00</startdate><enddate></enddate></detail><detail><level>18.1</level><type>����</type><typeid>841</typeid><typename>VPMN����</typename><startdate>2009-08-11 00:00:00</startdate><enddate></enddate></detail></message_content>]]></message_body></message_response>";
        // ����
        // String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
        // +
        // "<menuid>qryFavourable</menuid><process_code>cli_qry_zfinfo</process_code>" +
        // "<verify_code></verify_code>" +
        // "<resp_time>20110620144828</resp_time><" +
        // "sequence><req_seq>14</req_seq><operation_seq></operation_seq>" +
        // "</sequence><retinfo><rettype>0</rettype><retcode>999</retcode>" +
        // "<retmsg><![CDATA[BUSINESSID:QrySubsFeeInfo_Atsv,�м������ʧ�ܣ�EUniCall�׳��쳣,ErrorID -1 ErrorMsg EXCEPTION
        // 'ETuxedo': Destination service not found: IntCmd:CscGetSetInfo, IntSubCmd:, IntID:bsacAtsv,
        // Region:711]]></retmsg></retinfo></message_head>" +
        // "</message_response>";
        
        ReturnWrap rtwp = null;
        try
        {
            rtwp = intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rtwp;
        
    }
    
    /**
     * ��ѯԤԼ����_����
     * 
     * @param map
     * @return
     */
    public ReturnWrap phoneNumQuery(Map map)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLCSQueryAvlTelNum")) 
            {
                String response = "{telInfoList:[{telNum:'13645319981',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319982',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319983',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319984',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319985',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319986',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319987',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319988',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319989',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319980',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319901',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319902',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319903',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319904',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319905',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319906',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319907',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319908',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319909',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319900',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319911',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319912',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319913',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319914',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319915',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319916',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319917',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319918',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319919',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319910',prepay:'1000',minimumCharge:'1223'}"
                    + ",{telNum:'13645319999',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319999',prepay:'1000',minimumCharge:'1223'},{telNum:'13645319999',prepay:'1000',minimumCharge:'1223'}]}";
                
                return getIntMsgUtil().parseJsonResponse(response, null,null);
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<tellist><telnum>13645319981</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319982</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319983</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319984</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319985</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319986</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319987</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319988</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319989</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319980</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319901</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319902</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319903</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                    + "<tellist><telnum>13645319904</telnum><seltel_prepayfee>1000</seltel_prepayfee><minimumCharge>2000</minimumCharge></tellist>"
                 /*   + "<tellist><telnum>13645319905</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319906</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319907</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319908</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319909</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319900</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319911</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319912</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319913</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319914</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319915</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319916</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319917</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319918</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319919</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319910</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319999</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319999</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"
                    + "<tellist><telnum>13645319999</telnum><seltel_prepayfee>1000</seltel_prepayfee></tellist>"*/
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * ԤԼ����_����
     * 
     * @param map
     * @return
     */
    public ReturnWrap bespeakPhone(Map map)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("nkfSelNBR")) 
            {
                String response = "{oid:'10000000001',vidatetime:'19010101000000',remind:'�ֻ������ѳɹ�ԤԼ������48Сʱ�ڵ�ָ��Ӫҵ��������������֤����Ч֤������ӭ�´�ʹ�ã������ۺ�ҵ�������ն�Ϊ���ṩ����ϵķ���'}";
                
                return getIntMsgUtil().parseJsonResponse(response, null, null);
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryWlanMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921055850</resp_time><sequence><req_seq>21</req_seq>"
                    + "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
                    + "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
                    + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<oid>10000000001</oid>"
                    + "<vidatetime>1901-01-01 00:00:00</vidatetime>"
                    + "<remind>�ֻ������ѳɹ�ԤԼ������48Сʱ�ڵ�ָ��Ӫҵ��������������֤����Ч֤������ӭ�´�ʹ�ã������ۺ�ҵ�������ն�Ϊ���ṩ����ϵķ���</remind>"
                    + "</message_content>]]>" + "</message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * ������ѯ����
     * 
     * @param map
     * @return
     */
    public ReturnWrap netNbrQuery(Map map)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("atsvGetNetNbr")) 
            {
                String response = "{retDataList:[{resSegment:'135'},{resSegment:'136'},{resSegment:'137'},{resSegment:'138'},{resSegment:'139'},{resSegment:'135'},{resSegment:'136'}"
                    + ",{resSegment:'137'},{resSegment:'138'},{resSegment:'139'},{resSegment:'135'},{resSegment:'136'},{resSegment:'137'},{resSegment:'138'},{resSegment:'139'}"
                    + ",{resSegment:'135'},{resSegment:'136'},{resSegment:'137'},{resSegment:'138'},{resSegment:'139'},{resSegment:'135'},{resSegment:'136'},{resSegment:'137'}"
                    + ",{resSegment:'138'},{resSegment:'139'},{resSegment:'135'},{resSegment:'136'},{resSegment:'137'},{resSegment:'138'},{resSegment:'139'},{resSegment:'135'}"
                    + ",{resSegment:'136'},{resSegment:'137'},{resSegment:'138'},{resSegment:'139'}]}";
                
                return getIntMsgUtil().parseJsonResponse(response, null, null);
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<tellist><netnum>135</netnum></tellist>"
                    + "<tellist><netnum>136</netnum></tellist>"
                    + "<tellist><netnum>137</netnum></tellist>"
                    + "<tellist><netnum>138</netnum></tellist>"
                    + "<tellist><netnum>139</netnum></tellist>"
                    + "<tellist><netnum>135</netnum></tellist>"
                    + "<tellist><netnum>136</netnum></tellist>"
                    + "<tellist><netnum>137</netnum></tellist>"
                    + "<tellist><netnum>138</netnum></tellist>"
                    + "<tellist><netnum>139</netnum></tellist>"
                    + "<tellist><netnum>135</netnum></tellist>"
                    + "<tellist><netnum>136</netnum></tellist>"
                    + "<tellist><netnum>137</netnum></tellist>"
                    + "<tellist><netnum>138</netnum></tellist>"
                    + "<tellist><netnum>139</netnum></tellist>"
                    + "<tellist><netnum>135</netnum></tellist>"
                    + "<tellist><netnum>136</netnum></tellist>"
                    + "<tellist><netnum>137</netnum></tellist>"
                    + "<tellist><netnum>138</netnum></tellist>"
                    + "<tellist><netnum>139</netnum></tellist>"
                    + "<tellist><netnum>135</netnum></tellist>"
                    + "<tellist><netnum>136</netnum></tellist>"
                    + "<tellist><netnum>137</netnum></tellist>"
                    + "<tellist><netnum>138</netnum></tellist>"
                    + "<tellist><netnum>139</netnum></tellist>"
                    + "<tellist><netnum>135</netnum></tellist>"
                    + "<tellist><netnum>136</netnum></tellist>"
                    + "<tellist><netnum>137</netnum></tellist>"
                    + "<tellist><netnum>138</netnum></tellist>"
                    + "<tellist><netnum>139</netnum></tellist>"
                    + "<tellist><netnum>135</netnum></tellist>"
                    + "<tellist><netnum>136</netnum></tellist>"
                    + "<tellist><netnum>137</netnum></tellist>"
                    + "<tellist><netnum>138</netnum></tellist>"
                    + "<tellist><netnum>139</netnum></tellist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * ������ѯ����
     * 
     * @param map
     * @return
     */
    public ReturnWrap netValueQuery(Map map)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("atsvGetTelSection")) 
            {
                String response = "{retDataList:[{resSegment:'0531'},{resSegment:'0532'},{resSegment:'0533'},{resSegment:'0534'},{resSegment:'0535'},{resSegment:'0536'},{resSegment:'0537'}"
                    + ",{resSegment:'0538'},{resSegment:'0539'},{resSegment:'0530'},{resSegment:'1531'},{resSegment:'2532'},{resSegment:'3533'},{resSegment:'4534'},{resSegment:'5535'}"
                    + ",{resSegment:'6536'},{resSegment:'7537'},{resSegment:'8538'},{resSegment:'9539'},{resSegment:'0531'},{resSegment:'0532'},{resSegment:'0533'},{resSegment:'0534'}"
                    + ",{resSegment:'0535'},{resSegment:'0536'},{resSegment:'0537'},{resSegment:'0538'},{resSegment:'0539'},{resSegment:'0530'},{resSegment:'1531'},{resSegment:'2532'}"
                    + ",{resSegment:'3533'},{resSegment:'4534'},{resSegment:'5535'},{resSegment:'6536'},{resSegment:'7537'},{resSegment:'8538'},{resSegment:'9539'},{resSegment:'0531'}"
                    + ",{resSegment:'0532'},{resSegment:'0533'},{resSegment:'0534'},{resSegment:'0535'},{resSegment:'0536'},{resSegment:'0537'},{resSegment:'0538'},{resSegment:'0539'}"
                    + ",{resSegment:'0530'},{resSegment:'1531'},{resSegment:'2532'},{resSegment:'3533'},{resSegment:'4534'},{resSegment:'5535'},{resSegment:'6536'},{resSegment:'7537'}"
                    + ",{resSegment:'8538'},{resSegment:'9539'}]}";
                
                return getIntMsgUtil().parseJsonResponse(response, null, null);
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<tellist><telsection>0531</telsection></tellist>"
                    + "<tellist><telsection>0532</telsection></tellist>"
                    + "<tellist><telsection>0533</telsection></tellist>"
                    + "<tellist><telsection>0534</telsection></tellist>"
                    + "<tellist><telsection>0535</telsection></tellist>"
                    + "<tellist><telsection>0536</telsection></tellist>"
                    + "<tellist><telsection>0537</telsection></tellist>"
                    + "<tellist><telsection>0538</telsection></tellist>"
                    + "<tellist><telsection>0539</telsection></tellist>"
                    + "<tellist><telsection>0530</telsection></tellist>"
                    + "<tellist><telsection>1531</telsection></tellist>"
                    + "<tellist><telsection>2532</telsection></tellist>"
                    + "<tellist><telsection>3533</telsection></tellist>"
                    + "<tellist><telsection>4534</telsection></tellist>"
                    + "<tellist><telsection>5535</telsection></tellist>"
                    + "<tellist><telsection>6536</telsection></tellist>"
                    + "<tellist><telsection>7537</telsection></tellist>"
                    + "<tellist><telsection>8538</telsection></tellist>"
                    + "<tellist><telsection>9539</telsection></tellist>"
                    + "<tellist><telsection>0531</telsection></tellist>"
                    + "<tellist><telsection>0532</telsection></tellist>"
                    + "<tellist><telsection>0533</telsection></tellist>"
                    + "<tellist><telsection>0534</telsection></tellist>"
                    + "<tellist><telsection>0535</telsection></tellist>"
                    + "<tellist><telsection>0536</telsection></tellist>"
                    + "<tellist><telsection>0537</telsection></tellist>"
                    + "<tellist><telsection>0538</telsection></tellist>"
                    + "<tellist><telsection>0539</telsection></tellist>"
                    + "<tellist><telsection>0530</telsection></tellist>"
                    + "<tellist><telsection>1531</telsection></tellist>"
                    + "<tellist><telsection>2532</telsection></tellist>"
                    + "<tellist><telsection>3533</telsection></tellist>"
                    + "<tellist><telsection>4534</telsection></tellist>"
                    + "<tellist><telsection>5535</telsection></tellist>"
                    + "<tellist><telsection>6536</telsection></tellist>"
                    + "<tellist><telsection>7537</telsection></tellist>"
                    + "<tellist><telsection>8538</telsection></tellist>"
                    + "<tellist><telsection>9539</telsection></tellist>"
                    + "<tellist><telsection>0531</telsection></tellist>"
                    + "<tellist><telsection>0532</telsection></tellist>"
                    + "<tellist><telsection>0533</telsection></tellist>"
                    + "<tellist><telsection>0534</telsection></tellist>"
                    + "<tellist><telsection>0535</telsection></tellist>"
                    + "<tellist><telsection>0536</telsection></tellist>"
                    + "<tellist><telsection>0537</telsection></tellist>"
                    + "<tellist><telsection>0538</telsection></tellist>"
                    + "<tellist><telsection>0539</telsection></tellist>"
                    + "<tellist><telsection>0530</telsection></tellist>"
                    + "<tellist><telsection>1531</telsection></tellist>"
                    + "<tellist><telsection>2532</telsection></tellist>"
                    + "<tellist><telsection>3533</telsection></tellist>"
                    + "<tellist><telsection>4534</telsection></tellist>"
                    + "<tellist><telsection>5535</telsection></tellist>"
                    + "<tellist><telsection>6536</telsection></tellist>"
                    + "<tellist><telsection>7537</telsection></tellist>"
                    + "<tellist><telsection>8538</telsection></tellist>"
                    + "<tellist><telsection>9539</telsection></tellist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * ����֤����ԤԼ_����
     * 
     * @param map
     * @return
     */
    public ReturnWrap idCardBook(Map map)
    {
        try
        {
            if(IntMsgUtil.isTransEBUS("CTCSInstallByIdCard"))
            {
                ReturnWrap rtwp = new ReturnWrap();
                rtwp.setStatus(1);
                rtwp.setReturnMsg("ԤԼ�ɹ�");
                return rtwp;
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryWlanMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921055850</resp_time><sequence><req_seq>21</req_seq>"
                    + "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
                    + "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
                    + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<oid>10000000001</oid>"
                    + "<vidatetime>1901-01-01 00:00:00</vidatetime>"
                    + "<remind></remind>"
                    + "</message_content>]]>" + "</message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
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
        
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_promotions_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20110601001932</resp_time><sequence><req_seq>35</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>G1119</privid><privname>���ñ�������B��388Ԫ</privname><ncode>G1111</ncode><prepayfee>1000</prepayfee><groupid>123</groupid><groupname>1234</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>G1116</privid><privname>���ñ�������B��88Ԫ</privname><ncode>G1111</ncode><prepayfee>1000</prepayfee><groupid>123</groupid><groupname>1234</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>G1115</privid><privname>���ñ�������A��788Ԫ</privname><ncode>G1111</ncode><prepayfee>1000</prepayfee><groupid>123</groupid><groupname>1234</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>G1114</privid><privname>���ñ�������A��588Ԫ</privname><ncode>G1111</ncode><prepayfee>1000</prepayfee><groupid>123</groupid><groupname>1234</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>G1113</privid><privname>���ñ�������A��388Ԫ</privname><ncode>G1111</ncode><prepayfee>1000</prepayfee><groupid>123</groupid><groupname>1234</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>G220012H</privid><privname>��������300Ԫ18����</privname><ncode>G220012H</ncode><prepayfee>1000</prepayfee><groupid>123</groupid><groupname>1234</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>220000H</privid><privname>��������10Ԫ2����</privname><ncode>220000H</ncode><prepayfee>1000</prepayfee><groupid>223</groupid><groupname>��������10Ԫ2����</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>CMMB_1</privid><privname>�ֻ����Ӱ����ײ�</privname><ncode>CMMB_MON</ncode><prepayfee>600</prepayfee><groupid>223</groupid><groupname>123411</groupname></reclist>"
                    + "<reclist><region>711</region><orgid>HB.EZ</orgid><privid>G220220</privid><privname>GPRS5Ԫ��</privname><ncode>G220220</ncode><prepayfee>0</prepayfee><groupid>123</groupid><groupname>1234</groupname></reclist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    public ReturnWrap privAcceptCommit(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>recPrivAccept</menuid><process_code>cli_qry_promotions_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921014415</resp_time><sequence><req_seq>35</req_seq><operation_seq></operation_seq></sequence>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                    + "</retmsg></retinfo></message_head></message_response>";
            
            return intMsgUtil.parseResponse(response);
            
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * �����˵�������ѯ
     */
    public ReturnWrap qryBillAanlysis(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_billanalysis_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20110603191908</resp_time><sequence><req_seq>52</req_seq><operation_seq></operation_seq></sequence>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<restatus>0</restatus>"
                    + "<billAnalysisInfo>#*#�»�����#*#:10%;#*#ͨ�ŷ�#*#:20%;#*#����:#*#:;����ͨ����:30%;����ͨ����:20%;���ڳ�;��:10%;����/�۰�̨��;��:5%;����/�۰�̨���η�:5%;IPͨ�ŷ�:6%;����ͨ�ŷ�:30%;����ͨ�ŷ�:1%;����������:30%;#*#��ֵҵ���#*#:10%;#*#���շ�#*#:10%;#*#������#*#:20%;</billAnalysisInfo>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
            
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * �����ʷ��Ƽ�
     */
    public ReturnWrap qryChargeGuide(Map map)
    {
        try
        {
            // �û�����
            String region = (String)map.get("region");
            // ��ˮ���
            String streamNo = (String)map.get("streamno");
            // ������
            String questionCode = (String)map.get("questioncode");
            // �𰸱��
            String answerCode = (String)map.get("answercode");
            
            String question = "";
            String answer = "";
            String recinfo = "";
            if (region.length() > 0)
            {
                if (streamNo.length() == 0 && questionCode.length() == 0 && answerCode.length() == 0)
                {
                    streamNo = "20011957982339";
                    question = "1";
                    answer = "�Ƿ���ܲ��ܴ���ת�İ�������������";
                    recinfo = "1-1-1~����~1-1-2~��";
                }
                
                if (streamNo.equals("20011957982339"))
                {
                    if (questionCode.equals("1") && answerCode.equals("1-1-2"))
                    {
                        streamNo = "20011957982339";
                        question = "3";
                        answer = "����Ը��ѡ����������ʷѣ�������������ʷѣ�";
                        recinfo = "2-2-1~������~2-2-2~������";
                    }
                    if (questionCode.equals("3") && answerCode.equals("2-2-1"))
                    {
                        streamNo = "20011957982339";
                        question = "11";
                        answer = "��ÿ������ͨ�ŵ�����Ԥ���Ƕ��٣�";
                        recinfo = "4-6-1~50Ԫ����~4-6-2~50Ԫ-100Ԫ~4-6-3~100Ԫ-150Ԫ~4-6-4~150Ԫ-250Ԫ~4-6-5~250Ԫ-400Ԫ~4-6-6~400Ԫ-600Ԫ~4-6-7~600Ԫ-800Ԫ~4-6-8~800Ԫ����";
                    }
                    if (questionCode.equals("11") && answerCode.equals("4-6-1"))
                    {
                        streamNo = "20011957982339";
                        question = "14";
                        answer = "���Ƿ��л�ʯ�ƸԶ���������������";
                        recinfo = "5-29-1~û��~5-29-2~��";
                    }
                    if (questionCode.equals("14") && answerCode.equals("5-29-1"))
                    {
                        question = "";
                        answer = "";
                        streamNo = "20011957982339";
                        recinfo = "EZPP24~���������д��ڿ������10Ԫ~    ����10Ԫ/�£�����ѡ��������ʾ5Ԫ/��,������ѣ�������40���ӣ�����æʱ(8-21)����0.25Ԫ/��,��ʱ(21-8)����0.2Ԫ/�֡�~~~";
                    }
                }
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_chargeguide_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20110603042314</resp_time><sequence><req_seq>52</req_seq><operation_seq></operation_seq></sequence>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<streamno>"
                    + streamNo
                    + "</streamno><questioncode>"
                    + question
                    + "</questioncode>"
                    + "<question>"
                    + answer
                    + "</question><recinfo>"
                    + recinfo
                    + "</recinfo>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
            
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * ��Ʒ�������ѯ�û���ת���ײ��嵥
     */
    public ReturnWrap qryChangeList(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_package_changelist_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20110623115115</resp_time><sequence><req_seq>52</req_seq><operation_seq></operation_seq></sequence>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<sprid>B2135</sprid><sprname>����ȫ��ͨ88�ײ�88Ԫ</sprname>"
                    + "<changelist><tprid>B210221</tprid><tprname>����ȫ��ͨ88�ײ�88ԪG3������</tprname></changelist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
            
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * ��Ʒ�������ȡ�Ż�/����/��Ʒ����嵥
     */
    public ReturnWrap qryChgContent(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_package_chgcontent_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20110603191908</resp_time><sequence><req_seq>52</req_seq><operation_seq></operation_seq></sequence>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<sn>2011062100040023</sn>"
                    + "<sbrandname>1234</sbrandname>"
                    + "<tbrandname>4567</tbrandname>"
                    + "<chgcontent><optype>1</optype><prtype>PRIVILEGE</prtype><prid>B2135</prid><prname>����ȫ��ͨ88�ײ�88Ԫ</prname></chgcontent>"
                    + "<chgcontent><optype>0</optype><prtype>PRIVILEGE</prtype><prid>LDTX</prid><prname>��������</prname></chgcontent>"
                    + "<chgcontent><optype>0</optype><prtype>PRODUCT</prtype><prid>656</prid><prname>656</prname></chgcontent>"
                    + "<chgcontent><optype>0</optype><prtype>PRIVILEGE</prtype><prid>B210221</prid><prname>����ȫ��ͨ88�ײ�88ԪG3������</prname></chgcontent>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
            
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * ��Ʒ�������ȡ�Ż�/����/��Ʒ����嵥
     */
    public ReturnWrap prodChgCommit(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_package_chgcommit_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20110603191908</resp_time><sequence><req_seq>52</req_seq><operation_seq></operation_seq></sequence>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<commitlist><optype>1</optype><prtype>PRIVILEGE</prtype><prid>B2135</prid><prname>����ȫ��ͨ88�ײ�88Ԫ</prname></commitlist>"
                    + "<commitlist><optype>0</optype><prtype>PRIVILEGE</prtype><prid>LDTX</prid><prname>��������</prname></commitlist>"
                    + "<commitlist><optype>0</optype><prtype>PRODUCT</prtype><prid>656</prid><prname>656</prname></commitlist>"
                    + "<commitlist><optype>0</optype><prtype>PRIVILEGE</prtype><prid>B210221</prid><prname>����ȫ��ͨ88�ײ�88ԪG3������</prname></commitlist>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
            
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * ҵ���ѯͳһ�ӿ� ����ҵ���ѯ
     */
    public ReturnWrap queryService(MsgHeaderPO msgHead)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>recQrySPinfo</menuid><process_code>cli_qry_spinfo</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921002353</resp_time><sequence><req_seq>50</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request "
                    + "succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    + "<message_content><cusermoninfodt><usermoninfo_deal_type>22</usermoninfo_deal_type>"
                    + "<usermoninfo_spid>000001</usermoninfo_spid><usermoninfo_spname>�й��ƶ�</usermoninfo_spname>"
                    + "<usermoninfo_spbizid>FetionBase</usermoninfo_spbizid><usermoninfo_spbizname>���Ź���</usermoninfo_spbizname>"
                    + "<usermoninfo_spbiztype></usermoninfo_spbiztype><usermoninfo_pricetype></usermoninfo_pricetype>"
                    + "<usermoninfo_price></usermoninfo_price><usermoninfo_domain></usermoninfo_domain><usermoninfo_start_time>"
                    + "2010-06-08 08:22:18</usermoninfo_start_time><usermoninfo_end_time /><usermoninfo_seq />"
                    + "<usermoninfo_replace_charge></usermoninfo_replace_charge><usermoninfo_pricedesc>���</usermoninfo_pricedesc>"
                    + "</cusermoninfodt><cusermoninfodt><usermoninfo_deal_type>21</usermoninfo_deal_type>"
                    + "<usermoninfo_spid>801174</usermoninfo_spid><usermoninfo_spname>�й��ƶ�</usermoninfo_spname><usermoninfo_spbizid>"
                    + "125820</usermoninfo_spbizid><usermoninfo_spbizname>�����</usermoninfo_spbizname><usermoninfo_spbiztype>52"
                    + "</usermoninfo_spbiztype><usermoninfo_pricetype>���</usermoninfo_pricetype><usermoninfo_price>0"
                    + "</usermoninfo_price><usermoninfo_domain>12580</usermoninfo_domain><usermoninfo_start_time>2010-09-20 13:52:13"
                    + "</usermoninfo_start_time><usermoninfo_end_time /><usermoninfo_seq /><usermoninfo_replace_charge>"
                    + "</usermoninfo_replace_charge><usermoninfo_pricedesc>0.00���</usermoninfo_pricedesc></cusermoninfodt>"
                    + "<cusermoninfodt><usermoninfo_deal_type>22</usermoninfo_deal_type><usermoninfo_spid>000001</usermoninfo_spid>"
                    + "<usermoninfo_spname>�й��ƶ�</usermoninfo_spname><usermoninfo_spbizid>MMAIL_F</usermoninfo_spbizid>"
                    + "<usermoninfo_spbizname>139������Ѱ�</usermoninfo_spbizname><usermoninfo_spbiztype></usermoninfo_spbiztype>"
                    + "<usermoninfo_pricetype></usermoninfo_pricetype><usermoninfo_price></usermoninfo_price><usermoninfo_domain>"
                    + "</usermoninfo_domain><usermoninfo_start_time>2010-09-14 17:46:47</usermoninfo_start_time>"
                    + "<usermoninfo_end_time /><usermoninfo_seq /><usermoninfo_replace_charge></usermoninfo_replace_charge>"
                    + "<usermoninfo_pricedesc>���</usermoninfo_pricedesc></cusermoninfodt></message_content>]]></message_body>"
                    + "</message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
        
    }
    
    /**
     * �˵����ͷ�ʽ�ύ
     * @param msgHead ��Ϣͷ
     * @param billSendType ���ͷ�ʽ
     * @param mailAddr �ʼĵ�ַ
     * @return
     * @remark modify by sWX219697 2014-9-9 09:36:24 OR_huawei_201409_430 �����ն˽���EBUS_�˵�����
     */
    public ReturnWrap billSendCommit(MsgHeaderPO msgHead, String billSendType, String mailAddr)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_busi_chargefee</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            return rtwp;
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
    
    public ReturnWrap getProdTmpList(Map inMap)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ReturnWrap prodChgCheck(Map inMap)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ReturnWrap getDictItemByGroup(Map map)
    {
        try
        {
              // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("PTPUBQryDictItemsByGrpID"))
            {
                if("City420000".equals(map.get("groupid"))){
                    String response = "[{\"description\":\"270\",\"dictID\":\"420100\",\"dictName\":\"�人��\",\"groupID\":\"City420000\",\"sortOrder\":1},{\"description\":\"714\",\"dictID\":\"420200\",\"dictName\":\"��ʯ��\",\"groupID\":\"City420000\",\"sortOrder\":2},{\"description\":\"719\",\"dictID\":\"420300\",\"dictName\":\"ʮ����\",\"groupID\":\"City420000\",\"sortOrder\":3},{\"description\":\"717\",\"dictID\":\"420500\",\"dictName\":\"�˲���\",\"groupID\":\"City420000\",\"sortOrder\":4},{\"description\":\"710\",\"dictID\":\"420600\",\"dictName\":\"������\",\"groupID\":\"City420000\",\"sortOrder\":5},{\"description\":\"711\",\"dictID\":\"420700\",\"dictName\":\"������\",\"groupID\":\"City420000\",\"sortOrder\":6},{\"description\":\"724\",\"dictID\":\"420800\",\"dictName\":\"������\",\"groupID\":\"City420000\",\"sortOrder\":7},{\"description\":\"712\",\"dictID\":\"420900\",\"dictName\":\"Т����\",\"groupID\":\"City420000\",\"sortOrder\":8},{\"description\":\"716\",\"dictID\":\"421000\",\"dictName\":\"������\",\"groupID\":\"City420000\",\"sortOrder\":9},{\"description\":\"713\",\"dictID\":\"421100\",\"dictName\":\"�Ƹ���\",\"groupID\":\"City420000\",\"sortOrder\":10},{\"description\":\"715\",\"dictID\":\"421200\",\"dictName\":\"������\",\"groupID\":\"City420000\",\"sortOrder\":11},{\"description\":\"722\",\"dictID\":\"421300\",\"dictName\":\"������\",\"groupID\":\"City420000\",\"sortOrder\":12},{\"description\":\"718\",\"dictID\":\"422800\",\"dictName\":\"��ʩ����������������\",\"groupID\":\"City420000\",\"sortOrder\":13},{\"description\":\"HB.JH\",\"dictID\":\"429004\",\"dictName\":\"������\",\"groupID\":\"City420000\",\"sortOrder\":14},{\"description\":\"HB.QJ\",\"dictID\":\"429005\",\"dictName\":\"Ǳ����\",\"groupID\":\"City420000\",\"sortOrder\":15},{\"description\":\"HB.TM\",\"dictID\":\"429006\",\"dictName\":\"������\",\"groupID\":\"City420000\",\"sortOrder\":16},{\"description\":\"shen nong jia lin qu\",\"dictID\":\"429021\",\"dictName\":\"��ũ������\",\"groupID\":\"City420000\",\"sortOrder\":17}]";
                    return intMsgUtil.parseJsonResponse(response, null, new String[]{"dictID","dictName","description"});
                }
                
                if("District420700".equals(map.get("groupid"))){
                    String response = "[{\"description\":\"liang zi hu qu\",\"dictID\":\"420702\",\"dictName\":\"���Ӻ���\",\"groupID\":\"District420700\",\"sortOrder\":0},{\"description\":\"hua rong qu\",\"dictID\":\"420703\",\"dictName\":\"������\",\"groupID\":\"District420700\",\"sortOrder\":0},{\"description\":\"e cheng qu\",\"dictID\":\"420704\",\"dictName\":\"������\",\"groupID\":\"District420700\",\"sortOrder\":0},{\"description\":\"qi ta qu\",\"dictID\":\"420705\",\"dictName\":\"������\",\"groupID\":\"District420700\",\"sortOrder\":0}]";
                    return intMsgUtil.parseJsonResponse(response, null, new String[]{"dictID","dictName","description"});
                }
                
                String response = "[{'dictID':'30','dictName':'30Ԫ'},{'dictID':'50','dictName':'50Ԫ'}]";
                
                return intMsgUtil.parseJsonResponse(response, null, new String[]{"dictID","dictName"});
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<list><dictId>420700</dictId><dictName>������</dictName></list>"
                    + "<list><dictId>RwdGift_Fee</dictId><dictName>����</dictName></list>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    public ReturnWrap qryMonthlyReturnInfo(Map map)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    // add begin l00190940 2011-12-7 OR_HUB_201112_16
    /** 
     * ��ѯ�������
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public ReturnWrap qryFamilyNumber(MsgHeaderPO msgHeader)
    {
        try
        {
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("CTCSQryDealDealTelNum"))
            {
                CTagSet cTagSet = new CTagSet();
                
                // ѭ����������������CTagSet��
                for (int i = 0; i < 3; i++)
                {
                    cTagSet.SetValue("friendnum" + (i + 1), "");
                }
                
                String response = "[{'attrID':'FriendNum1','attrValue':'15168866090'},{'attrID':'FriendNum2','attrValue':''},{'attrID':'FriendNum3','attrValue':'15168866091'}]";
                
                ReturnWrap rtwp = intMsgUtil.parseJsonResponse(response, null, new String[]{"attrID", "attrValue"});
                
                // ȡ�������������
                CRSet crSet = (CRSet) rtwp.getReturnObject();
                
                if (crSet != null)
                {
                    // ȡǰ�����������
                    String[] arrays = new String[]{"FriendNum1", "FriendNum2", "FriendNum3"};
                    
                    // ѭ����ǰ��������������CTagSet��
                    for (int i = 0; i < crSet.GetRowCount(); i++)
                    {
                        if (ArrayUtils.contains(arrays, crSet.GetValue(i, 0)))
                        {
                            cTagSet.SetValue(crSet.GetValue(i, 0).toLowerCase(Locale.ENGLISH), crSet.GetValue(i, 1));
                        }
                    }
                }
                
                rtwp.setReturnObject(cTagSet);
                
                return rtwp;
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryWlanMuster</menuid><process_code>cli_qry_szxqskqqh</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921055850</resp_time><sequence><req_seq>21</req_seq>"
                    + "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
                    + "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
                    + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<friendnum1>15168866090</friendnum1>"
                    + "<friendnum2></friendnum2>"
                    + "<friendnum3>15168866091</friendnum3>"
                    + "</message_content>]]>"
                    + "</message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * ���á��޸Ļ�ȡ���������
     */
    public ReturnWrap setFamilyNumber(MsgHeaderPO msgHeader, String sn, String sregion, String style)
    {
    	// ����תEBUS���ؿ���
        if (IntMsgUtil.isTransEBUS("IncProductSrv2"))
        {
            String response = "{'ACCESSTYPE':'INNER','ADDNCODE':'FriendNew','ADD_ENDDATE':'','ADD_STARTDATE':'2014-12-15 18:57:00'," 
            	+ "'BOSSCODE':'FriendNew','BRAND':'BrandSzx','BUSITYPE':'0','CALL_NUMBER':'13971984332','CURID':'','CURNCODE':''," 
            	+ "'DELNCODE':'','DEL_ENDDATE':'','EFFTYPE':'Type_Default','EFF_DATE':'2014-12-15 18:57:00','END_DATE':'ZERO'," 
            	+ "'ERRNO':'0','EXECUTECMD':'TRUE','FORMNUMBER':'711141215229853868','FRIEND':'13908689393','FriendNum2':'13908689393'," 
            	+ "'HASPARAM':'2','IMSI':'460001984733294','INPARAMFORMAT':'','INPARAMSPLIT':'~','IPADDRESS':'','ISNEEDTHIRDCONF':'0'," 
            	+ "'ISWRITEFILELOG':'0','ISWRITETABLOG':'1','JOBDESC':'�������2009','JOBNAME':'�������2009','LINKNODE':'','LINKTYPE':''," 
            	+ "'MAINPRODID':'MP9990103000138','MSISDN':'13971984332','NCODE':'FriendNew','NEXTOUTPARAM':'FriendNum1~FriendNum2~FriendNum3'," 
            	+ "'NEXTOUTPARAMALL':'','NEXTOUTPARAMNOREP':'','NOPENEDPMP':'ZERO','OLD_PASSWD':'','OPENEDPMP':'ZERO','ORDERRESULT':'9900'," 
            	+ "'ORI_NEXTATTRS':'','OUTOPERID':'','OUTPARAMFORMAT':'#FriendNum1~#FriendNum2~#FriendNum3','OUTPARAMSPLIT':''," 
            	+ "'PARM':'','REALOPERID':'','REALRETCODE':'100','REGION':'711','RETCODE':'0','RETCONVERT':'1','RETMSG':'�����������2009�ɹ���'," 
            	+ "'RETURN':'0','SENDORNOT':'0','SN':'2','STEP':'10','STYPE':'ADD','SUBORDERRESULT':'','SUBSCREATEDATE':'2001-01-18 10:01:10'," 
            	+ "'SUBSID':'7110000002387','TELNUM':'13971984332','TEMPLATENO':'P00119','TEMPLATEPARA':'1$�������2009&5$13908689393&3$2&2$2014-12-15��Ч,&4$����'," 
            	+ "'UNITID':'INNER','VNCODE':'vFrenw','YYWWFORMNUM':'20089229853866','accessType':'bsacAtsv','formnum':'','interfaceID':'IncProductSrv2'," 
            	+ "'menuID':'recFamilyNumber','operatorID':'101','region':'','reqSeq':'1','reqTime':'20141215190602','routeType':'1','telNum':'13971984332'," 
            	+ "'uniContact':'','uniContactFlag':'','unitID':'HUAWEI','virtualActorFlag':'1'}";
            
            return getIntMsgUtil().parseJsonResponse(response, null, null);
        }
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid></menuid><process_code>cli_busi_szxqskqqh</process_code><verify_code></verify_code><resp_time>20100921002348</resp_time><sequence><req_seq>49</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
        ReturnWrap rtwp = null;
        try
        {
            rtwp = intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rtwp;
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
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLCSQrySubsValidPrivList"))
            {
                String response = "[{'privID':'dangci001','privName':'��������001'}]";
                
                return getIntMsgUtil().parseJsonResponse(response, null, new String[]{"privID","privName"});
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<list><privid>dangci001</privid><privname>��������001</privname></list>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * ��ѯ�����µ�����_���� <������ϸ����>
     * 
     * @param paramMap
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap queryDangciDesc(Map paramMap)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<list><dangciName>dangciName</dangciName><dangciDesc>2011��1��7����" +
                            "�����ƶ�ȫ��ͨ�ͻ���ͨ�������̳ǻ�ָ��Ӫҵ���μ�ȫ��ͨԤ�滰�ѻ��ֻ����" +
                            "ֻ��Ԥ����Ӧ���ѣ���Ѻ�𣩣����ҳ�ŵ����һ���������Գ�ֵ�۸񻻹�G3������" +
                            "��Ϳ�0Ԫ�����������ƶ��������ͬʱ�����ܻ��ѻ��»��ķǷ����顣����������ѡ��" +
                            "�����μӰɣ� <br/> <br/> ���ÿͻ��������ƶ�ȫ��ͨ�ͻ� <br/><br/>" +
                            " ��������ر���ʾ <br/><br/> ע�������������ͻ����迪ͨ�ֻ��Ķ������ٵݰ�ҵ��" +
                            "�ֻ��Ķ������ٵݰ�ҵ����Ϣ��5Ԫ/�¡�ҵ��ͨ�ڼ䣬ϵͳÿ�²���ֱ�Ӽ���ķ�ʽΪ�䷵��5Ԫ��" +
                            "�����ٵݰ���ҵ����¹��ܷѣ����ǿͻ�ʹ�ø�ҵ��������������ý�����֧����" +
                            " <br/><br/> ������ݮ��BlackBerry���ֻ�8310��8910ȫ��ͨԤ�滻������ʱ��" +
                            "Ҫ��ͻ��ȿ�ͨ���й��ƶ���ݮ��������ҵ���BlackBerryҵ��BES������" +
                            "���ܰ����������������򽫲��ܰ�����������͵Ļ���ҵ��<br/><br/>" +
                            "��ݮ��BlackBerry������������ֻ����ָ����̨��������վ���ܰ����˷�����" +
                            "<br/><br/>��ŵ��12���·���</dangciDesc></list>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
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
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("PTPCEIGetBatchRecPresentsByActId")) 
            {
                String response = "[{'rewardID':'rewardid001','rewardName':'rewardname001','rewardType':'RwdGift_Prod','rewardValue':'570','scoreDealType':'0','useScore':'50','rewardInfo':'500Ԫ���ѣ�5����������'},"
                    + "{'rewardID':'rewardid001','rewardName':'rewardname001','rewardType':'RwdGift_Prod','rewardValue':'570','scoreDealType':'0','useScore':'50','rewardInfo':'500Ԫ���ѣ�5����������'},"
                    + "{'rewardID':'rewardid001','rewardName':'rewardname001','rewardType':'RwdGift_Prod','rewardValue':'570','scoreDealType':'0','useScore':'50','rewardInfo':'500Ԫ���ѣ�5����������'},"
                    + "{'rewardID':'rewardid001','rewardName':'rewardname001','rewardType':'RwdGift_Prod','rewardValue':'570','scoreDealType':'0','useScore':'50','rewardInfo':'500Ԫ���ѣ�5����������'},"
                    + "{'rewardID':'rewardid001','rewardName':'rewardname001','rewardType':'RwdGift_Prod','rewardValue':'570','scoreDealType':'0','useScore':'50','rewardInfo':'500Ԫ���ѣ�5����������'},"
                    + "{'rewardID':'rewardid001','rewardName':'rewardname001','rewardType':'RwdGift_Prod','rewardValue':'570','scoreDealType':'0','useScore':'50','rewardInfo':'500Ԫ���ѣ�5����������'},"
                    + "{'rewardID':'rewardid001','rewardName':'rewardname001','rewardType':'RwdGift_Prod','rewardValue':'570','scoreDealType':'0','useScore':'50','rewardInfo':'500Ԫ���ѣ�5����������'}]";
                
                return getIntMsgUtil().parseJsonResponse(response, null, null);
            }
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_rewardlist</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Prod</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>0</scoredealtype><userscore>50</userscore><rewardnote>500Ԫ���ѣ�5����������</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Fee</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>0</scoredealtype><userscore>50</userscore><rewardnote>500Ԫ���ѣ�5����������</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Prod</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>0</scoredealtype><userscore>50</userscore><rewardnote>500Ԫ���ѣ�5����������</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Fee</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>0</scoredealtype><userscore>50</userscore><rewardnote>500Ԫ���ѣ�5����������</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Prod</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>0</scoredealtype><userscore>50</userscore><rewardnote>500Ԫ���ѣ�5����������</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Fee</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>0</scoredealtype><userscore>50</userscore><rewardnote>500Ԫ���ѣ�5����������</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Prod</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>0</scoredealtype><userscore>50</userscore><rewardnote>500Ԫ���ѣ�5����������</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Fee</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>1</scoredealtype><userscore>50</userscore><rewardnote>500Ԫ���ѣ�5����������</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Prod</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>1</scoredealtype><userscore>50</userscore><rewardnote>500Ԫ���ѣ�5����������</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Fee</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>1</scoredealtype><userscore>50</userscore><rewardnote>500Ԫ���ѣ�5����������</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Prod</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>1</scoredealtype><userscore>50</userscore><rewardnote>500Ԫ���ѣ�5����������</rewardnote></rewardlist>"
                    + "<rewardlist><rewardid>rewardid001</rewardid><rewardname>rewardname001</rewardname><rewardtype>RwdGift_Fee</rewardtype><rewardvalue>570</rewardvalue><scoredealtype>1</scoredealtype><userscore>50</userscore><rewardnote>500Ԫ���ѣ�5����������</rewardnote></rewardlist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * ��ѯ��Ʒ�б�_����
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryPrivFee(Map map)
    {
        try
        {
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLCSGetSubsRecFeeAndPreFee"))
            {
                String response = "[{'recFee':'100','preFee':'20'}]";
                
                return intMsgUtil.parseJsonResponse(response, null, new String[]{"recFee","preFee"});
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<privfeelist><yxfaFee>100</yxfaFee><ycFee>20</ycFee></privfeelist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * ���������_���� <������ϸ����>
     * 
     * @param paramMap
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap recRewardCommit(Map paramMap)
    {
        try
        {
            // ����EBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLCSRecRewardactiveLight"))
            {
                String response = "{'recOid':'100000001'}";
                
                return getIntMsgUtil().parseJsonResponse(response, new String[][]{{"recOid"},{"recoid"}}, null);
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryBill</menuid><process_code>cli_qry_balance</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<recoid>100000001</recoid>" + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return new ReturnWrap();
        }
    }
    
    @Override
    public ReturnWrap qryBillCustInfo(Map map)
    {
        // TODO Auto-generated method stub
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
                
                ReturnWrap rtwp = intMsgUtil.invokeDubbo("CTCSQryBillCycleCustInfo", msgHeaderPO, inParamMap);
                
                Vector v = new Vector();
                if (rtwp.getReturnObject() instanceof CTagSet)
                {
                    v.add(rtwp.getReturnObject());
                    rtwp.setReturnObject(v);
                }
                return rtwp;
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
    
    @Override
    public ReturnWrap qryCurBillInfo(Map map)
    {
        // TODO Auto-generated method stub
        return null;
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
        ReturnWrap rtwp = intMsgUtil.invokeDubbo("arQryBillCommuHuBExcelEbus", msgHeaderPO, inParamMap,false);        
        if (SSReturnCode.SUCCESS == rtwp.getStatus())
        {
            String json=(String)rtwp.getReturnObject();
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
        /*
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>"+
"<body><pkgLst><remainderDesc>0����</remainderDesc><includeDesc>60����</includeDesc><usageDesc>70����</usageDesc><className>��������</className><packageName>ȫ��ͨͳһ�ײͣ�2012�棩_�����ײ�158��</packageName></pkgLst><pkgLst><remainderDesc>10����</remainderDesc><includeDesc>60����</includeDesc><usageDesc>50����</usageDesc><className>����IP��;</className><packageName>�人���еش������ײ�(19Ԫ)</packageName></pkgLst><pkgLst><remainderDesc>0��</remainderDesc><includeDesc>500��</includeDesc><usageDesc>520��</usageDesc><className>����</className><packageName>�人���еش������ײ�(19Ԫ)</packageName></pkgLst><pkgLst><remainderDesc>50��</remainderDesc><includeDesc>200��</includeDesc><usageDesc>150��</usageDesc><className>���ڶ���</className><packageName>�人����5Ԫ�����ײ�3����</packageName></pkgLst><pkgLst><remainderDesc>50��</remainderDesc><includeDesc>100��</includeDesc><usageDesc>50��</usageDesc><className>���ڶ���</className><packageName>�人���Ű�</packageName></pkgLst><pkgLst><remainderDesc>0��</remainderDesc><includeDesc>100��</includeDesc><usageDesc>150��</usageDesc><className>����</className><packageName>����5Ԫ���Ű�</packageName></pkgLst><pkgLst><remainderDesc>823.951M</remainderDesc><includeDesc>1024M</includeDesc><usageDesc>200.049M</usageDesc><className>WLAN����</className><packageName>����WLAN�ײ�</packageName></pkgLst><pkgLst><remainderDesc>2.5����</remainderDesc><includeDesc>5����</includeDesc><usageDesc>2.5����</usageDesc><className>У԰WLANʱ��</className><packageName>У԰WLAN�ײ�</packageName></pkgLst><pkgLst><remainderDesc>0M</remainderDesc><includeDesc>80M</includeDesc><usageDesc>90.002M</usageDesc><className>��������</className><packageName>ȫ��ͨͳһ�ײͣ�2012�棩_�����ײ�158��</packageName></pkgLst><pkgLst><remainderDesc>92159.961M</remainderDesc><includeDesc>102400M</includeDesc><usageDesc>10240.039M</usageDesc><className>��������</className><packageName>�人���еش������ײ�(19Ԫ)*0</packageName></pkgLst><pkgLst><remainderDesc>90M</remainderDesc><includeDesc>100M</includeDesc><usageDesc>10M</usageDesc><className>��������</className><packageName>CPE��������40Ԫ�ײ�</packageName></pkgLst><pkgLst><className>������������</className><includeDesc>0M</includeDesc></pkgLst><pkgLst><className>��УWLAN����</className><includeDesc>0����</includeDesc></pkgLst><pkgLst><className>WLAN����</className><includeDesc>0M</includeDesc></pkgLst><pkgLst><usageDesc>70����</usageDesc><className>��������</className><remainderDesc>0����</remainderDesc><includeDesc>60����</includeDesc></pkgLst><pkgLst><usageDesc>50����</usageDesc><className>����IP��;</className><remainderDesc>10����</remainderDesc><includeDesc>60����</includeDesc></pkgLst><pkgLst><usageDesc>520��</usageDesc><className>����</className><remainderDesc>0��</remainderDesc><includeDesc>500��</includeDesc></pkgLst><pkgLst><usageDesc>200��</usageDesc><className>���ڶ���</className><remainderDesc>100��</remainderDesc><includeDesc>300��</includeDesc></pkgLst><pkgLst><usageDesc>150��</usageDesc><className>����</className><remainderDesc>0��</remainderDesc><includeDesc>100��</includeDesc></pkgLst><pkgLst><usageDesc>200.049M</usageDesc><className>WLAN����</className><remainderDesc>823.951M</remainderDesc><includeDesc>1024M</includeDesc></pkgLst><pkgLst><usageDesc>2.5����</usageDesc><className>У԰WLANʱ��</className><remainderDesc>2.5����</remainderDesc><includeDesc>5����</includeDesc></pkgLst><pkgLst><usageDesc>10330.041M</usageDesc><className>��������</className><remainderDesc>92159.961M</remainderDesc><includeDesc>102480M</includeDesc></pkgLst><pkgLst><usageDesc>10M</usageDesc><className>��������</className><remainderDesc>90M</remainderDesc><includeDesc>100M</includeDesc></pkgLst></body>";
        
        return "<?xml version=\"1.0\" encoding=\"GBK\" ?>"+
        "<body>"+
        "   <pkgLst>"+ 
        "       <remark/>"+ 
        "       <remainderDesc>362����</remainderDesc>"+ 
        "       <includeDesc>380����</includeDesc>"+ 
        "       <usageDesc>18����</usageDesc>"+ 
        "       <classID>1</classID>"+ 
        "       <className>�������б���</className>"+ 
        "       <packageOid>B210703</packageOid>"+ 
        "       <packageName>���������б����ײ�38ԪA</packageName>"+ 
        "   </pkgLst>"+ 
        "   <pkgLst>"+ 
        "       <remark/>"+ 
        "       <remainderDesc>98��</remainderDesc>"+ 
        "       <includeDesc>100��</includeDesc>"+ 
        "       <usageDesc>2��</usageDesc>"+ 
        "       <classID>2</classID>"+ 
        "       <className>���ڶ���</className>"+ 
        "       <packageOid>G271427AP</packageOid>"+ 
        "       <packageName>�人���Ű�</packageName>"+ 
        "   </pkgLst>"+ 
        "   <pkgLst>"+ 
        "       <remark/>"+ 
        "       <remainderDesc>70M</remainderDesc>"+ 
        "       <includeDesc>70M</includeDesc>"+ 
        "       <usageDesc>0M</usageDesc>"+ 
        "       <classID>6</classID>"+ 
        "       <className>��������</className>"+ 
        "       <packageOid>G220672</packageOid>"+ 
        "       <packageName>�ֻ�����10Ԫ�����ײ�</packageName>"+ 
        "   </pkgLst>"+ 
        "   <overLst>"+ 
        "       <className>��������</className>"+ 
        "       <includeDesc>��������includedesc</includeDesc>"+ 
        "   </overLst>"+ 
        "   <overLst>"+ 
        "       <className>��������2</className>"+ 
        "       <includeDesc>��������2includedesc</includeDesc>"+ 
        "   </overLst>"+ 
        "   <bcallLst>"+ 
        "       <usageDesc>18����</usageDesc>"+ 
        "       <remark/>"+ 
        "       <className>�������б���</className>"+ 
        "       <classID>1</classID>"+ 
        "       <remainderDesc>362����</remainderDesc>"+ 
        "       <includeDesc>380����</includeDesc>"+ 
        "   </bcallLst>"+ 
        "   <bcallLst>"+ 
        "       <usageDesc>2��</usageDesc>"+ 
        "       <remark/>"+ 
        "       <className>���ڶ���</className>"+ 
        "       <classID>2</classID>"+ 
        "       <remainderDesc>98��</remainderDesc>"+ 
        "       <includeDesc>100��</includeDesc>"+ 
        "   </bcallLst>"+ 
        "   <bcallLst>"+ 
        "       <usageDesc>0M</usageDesc>"+ 
        "       <remark/>"+ 
        "       <className>��������</className>"+ 
        "       <classID>6</classID>"+ 
        "       <remainderDesc>70M</remainderDesc>"+ 
        "       <includeDesc>70M</includeDesc>"+ 
        "   </bcallLst><printInfo>&lt;&lt;&gt;&gt;&gt;����������������\n����������</printInfo>"+ 
        "</body>"
        ;*/
    }
    // add begin ykf38827 2012/03/20 R003C12L03n01 OR_HUB_201202_800
    /**
     * ��ѯδ��ӡ�ķ�Ʊ��¼����
     * 
     * @param map
     * @return
     */
    @Override
    public ReturnWrap invoiceList(Map map)
    {
        try
        {
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLARQueryUnprintInvoceList")) 
            {
                String response = "[{\"accessName\":\"CRMӪҵ��\",\"billCycle\":0,\"custName\":\"����13607233161\",\"formnum\":\"711131111405756007\",\"invoiceFee\":0,\"itemName\":\"�շ�\",\"oid\":\"10005405756005\",\"operName\":\"ϵͳ����Ա(101)\",\"orgName\":\"����\",\"recDate\":\"2013-11-11 20:07:40\",\"recFee\":11200,\"recType\":\"Charge\",\"rowNum\":1,\"servNumber\":\"13607233161\",\"status\":\"����\",\"statusDate\":\"2013-11-11 20:07:40\",\"upTotalFee\":11200,\"upperFee\":\"Ҽ��Ҽʰ��Բ��\"}," +
                        "{\"accessName\":\"CRMӪҵ��\",\"billCycle\":0,\"custName\":\"����13607233161\",\"formnum\":\"711131111405756059\",\"invoiceFee\":0,\"itemName\":\"�շ�\",\"oid\":\"10005405756057\",\"operName\":\"ϵͳ����Ա(101)\",\"orgName\":\"����\",\"recDate\":\"2013-11-11 20:38:14\",\"recFee\":11300,\"recType\":\"Charge\",\"rowNum\":1,\"servNumber\":\"13607233161\",\"status\":\"����\",\"statusDate\":\"2013-11-11 20:38:14\",\"upTotalFee\":11300,\"upperFee\":\"Ҽ��Ҽʰ��Բ��\"}," +
                        "{\"accessName\":\"CRMӪҵ��\",\"billCycle\":0,\"custName\":\"����13607233161\",\"formnum\":\"711131130405795045\",\"invoiceFee\":0,\"itemName\":\"�շ�\",\"oid\":\"10005405795043\",\"operName\":\"ϵͳ����Ա(101)\",\"orgName\":\"����\",\"recDate\":\"2013-11-30 15:38:27\",\"recFee\":10000,\"recType\":\"Charge\",\"rowNum\":1,\"servNumber\":\"13607233161\",\"status\":\"����\",\"statusDate\":\"2013-11-30 15:38:27\",\"upTotalFee\":10000,\"upperFee\":\"Ҽ��Բ��\"}," +
                        "{\"accessName\":\"CRMӪҵ��\",\"billCycle\":0,\"custName\":\"����13607233161\",\"formnum\":\"711131202405796667\",\"invoiceFee\":0,\"itemName\":\"�շ�\",\"oid\":\"10005405796665\",\"operName\":\"ϵͳ����Ա(101)\",\"orgName\":\"����\",\"recDate\":\"2013-12-02 16:45:18\",\"recFee\":100000,\"recType\":\"Charge\",\"rowNum\":1,\"servNumber\":\"13607233161\",\"status\":\"����\",\"statusDate\":\"2013-12-02 16:45:18\",\"upTotalFee\":100000,\"upperFee\":\"ҼǪԲ��\"}," +
                        "{\"accessName\":\"CRMӪҵ��\",\"billCycle\":0,\"custName\":\"����13607233161\",\"formnum\":\"711131202405796851\",\"invoiceFee\":0,\"itemName\":\"�շ�\",\"oid\":\"10005405796849\",\"operName\":\"ϵͳ����Ա(101)\",\"orgName\":\"����\",\"recDate\":\"2013-12-02 17:02:26\",\"recFee\":54500,\"recType\":\"Charge\",\"rowNum\":1,\"servNumber\":\"13607233161\",\"status\":\"����\",\"statusDate\":\"2013-12-02 17:02:26\",\"upTotalFee\":54500,\"upperFee\":\"�����ʰ��Բ��\"}," +
                        "{\"accessName\":\"CRMӪҵ��\",\"billCycle\":0,\"custName\":\"����13607233161\",\"formnum\":\"711131202405796981\",\"invoiceFee\":0,\"itemName\":\"�շ�\",\"oid\":\"10005405796979\",\"operName\":\"ϵͳ����Ա(101)\",\"orgName\":\"����\",\"recDate\":\"2013-12-02 17:07:45\",\"recFee\":78900,\"recType\":\"Charge\",\"rowNum\":1,\"servNumber\":\"13607233161\",\"status\":\"����\",\"statusDate\":\"2013-12-02 17:07:45\",\"upTotalFee\":78900,\"upperFee\":\"��۰�ʰ��Բ��\"}]";
                
                return getIntMsgUtil().parseJsonResponse(response, null, new String[] {"billCycle", "formnum", "itemName", "custName", "servNumber", "recFee", "accessName", "recDate",
                        "status", "statusDate", "operName", "orgName", "recType", "oid", "invoiceFee", "upperFee"});
            }
            
            String resp = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
                    "<menuid>invoicePrint</menuid><process_code>cli_qry_noinvoiceprint_hub</process_code><verify_code>100101</verify_code>" +
                    "<unicontact></unicontact><resp_time>20120522094655</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq>" +
                    "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>" +
                    "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>" +
                    "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
                    "<invoicelist><cycle>0</cycle><formnum>634120509891654602</formnum><busname>�շ�</busname>" +
                    "<custname>����13963456977</custname><svrnumber>13963456977</svrnumber><optsum>10000</optsum>" +
                    "<channel>����Ӫҵ��</channel><accepttime>2012-05-07 19:18:57</accepttime><status>����</status>" +
                    "<statustime>2012-05-07 19:18:57</statustime><operator>���ù���-���Ͽͷ����⹤��(pinet004)</operator>" +
                    "<acceptunit>����</acceptunit><businessId>Charge</businessId><busOptCode>88009891654601</busOptCode>" +
                    "<invoiceSum>0</invoiceSum><capitalSum>Ҽ��Բ��</capitalSum><cardtype>88009891654601</cardtype><linkphone>0</linkphone>" +
                    "<linkaddress>Ҽ��Բ��</linkaddress></invoicelist><invoicelist><cycle>0</cycle><formnum>634120509891654609</formnum>" +
                    "<busname>�շ�</busname><custname>����13963456977</custname><svrnumber>13963456977</svrnumber><optsum>1000</optsum>" +
                    "<channel>����Ӫҵ��</channel><accepttime>2012-05-07 19:18:57</accepttime><status>����</status>" +
                    "<statustime>2012-05-07 19:18:57</statustime><operator>���ù���-���Ͽͷ����⹤��(pinet004)</operator>" +
                    "<acceptunit>����</acceptunit><businessId>Charge</businessId><busOptCode>88009891654608</busOptCode>" +
                    "<invoiceSum>0</invoiceSum><capitalSum>ҼʰԲ��</capitalSum><cardtype>88009891654608</cardtype>" +
                    "<linkphone>0</linkphone><linkaddress>ҼʰԲ��</linkaddress></invoicelist><invoicelist><cycle>0</cycle>" +
                    "<formnum>634120509891656444</formnum><busname>�շ�</busname><custname>����13963456977</custname>" +
                    "<svrnumber>13963456977</svrnumber><optsum>13000</optsum><channel>����Ӫҵ��</channel>" +
                    "<accepttime>2012-05-09 13:50:57</accepttime><status>����</status><statustime>2012-05-09 13:50:57</statustime>" +
                    "<operator>���ù���-���Ͽͷ����⹤��(pinet007)</operator><acceptunit>����</acceptunit><businessId>Charge</businessId>" +
                    "<busOptCode>88009891656443</busOptCode><invoiceSum>0</invoiceSum><capitalSum>Ҽ����ʰԲ��</capitalSum>" +
                    "<cardtype>88009891656443</cardtype><linkphone>0</linkphone><linkaddress>Ҽ����ʰԲ��</linkaddress></invoicelist>" +
                    "<invoicelist><cycle>0</cycle><formnum>634120509891656451</formnum><busname>�շ�</busname><custname>����13963456977</custname>" +
                    "<svrnumber>13963456977</svrnumber><optsum>2000</optsum><channel>����Ӫҵ��</channel><accepttime>2012-05-09 13:50:57</accepttime>" +
                    "<status>����</status><statustime>2012-05-09 13:50:57</statustime><operator>���ù���-���Ͽͷ����⹤��(pinet007)</operator>" +
                    "<acceptunit>����</acceptunit><businessId>Charge</businessId><busOptCode>88009891656450</busOptCode>" +
                    "<invoiceSum>0</invoiceSum><capitalSum>��ʰԲ��</capitalSum><cardtype>88009891656450</cardtype>" +
                    "<linkphone>0</linkphone><linkaddress>��ʰԲ��</linkaddress></invoicelist><invoicelist><cycle>0</cycle>" +
                    "<formnum>634120509891658704</formnum><busname>�շ�</busname><custname>����13963456977</custname>" +
                    "<svrnumber>13963456977</svrnumber><optsum>14000</optsum><channel>����Ӫҵ��</channel>" +
                    "<accepttime>2012-05-09 13:50:57</accepttime><status>����</status><statustime>2012-05-09 13:50:57</statustime>" +
                    "<operator>���ù���-���Ͽͷ����⹤��(pinet004)</operator><acceptunit>����</acceptunit><businessId>Charge</businessId>" +
                    "<busOptCode>88009891658703</busOptCode><invoiceSum>0</invoiceSum><capitalSum>Ҽ����ʰԲ��</capitalSum>" +
                    "<cardtype>88009891658703</cardtype><linkphone>0</linkphone><linkaddress>Ҽ����ʰԲ��</linkaddress></invoicelist>" +
                    "<invoicelist><cycle>0</cycle><formnum>634120509891658711</formnum><busname>�շ�</busname>" +
                    "<custname>����13963456977</custname><svrnumber>13963456977</svrnumber><optsum>2000</optsum><channel>����Ӫҵ��</channel>" +
                    "<accepttime>2012-05-09 13:50:57</accepttime><status>����</status><statustime>2012-05-09 13:50:57</statustime>" +
                    "<operator>���ù���-���Ͽͷ����⹤��(pinet004)</operator><acceptunit>����</acceptunit><businessId>Charge</businessId>" +
                    "<busOptCode>88009891658710</busOptCode><invoiceSum>0</invoiceSum><capitalSum>��ʰԲ��</capitalSum>" +
                    "<cardtype>88009891658710</cardtype><linkphone>0</linkphone><linkaddress>��ʰԲ��</linkaddress></invoicelist>" +
                    "<invoicelist><cycle>0</cycle><formnum>634120509891659798</formnum><busname>�շ�</busname><custname>����13963456977</custname>" +
                    "<svrnumber>13963456977</svrnumber><optsum>2967</optsum><channel>����Ӫҵ��</channel>" +
                    "<accepttime>2012-05-09 13:50:57</accepttime><status>����</status><statustime>2012-05-09 13:50:57</statustime>" +
                    "<operator>���ù���-���д������⹤��(p162ds02)</operator><acceptunit>����</acceptunit><businessId>Charge</businessId>" +
                    "<busOptCode>88009891659797</busOptCode><invoiceSum>0</invoiceSum><capitalSum>��ʰ��Բ½�����</capitalSum>" +
                    "<cardtype>88009891659797</cardtype><linkphone>0</linkphone><linkaddress>��ʰ��Բ½�����</linkaddress></invoicelist>" +
                    "<invoicelist><cycle>0</cycle><formnum>634120509891659805</formnum><busname>�շ�</busname><custname>����13963456977</custname>" +
                    "<svrnumber>13963456977</svrnumber><optsum>33</optsum><channel>����Ӫҵ��</channel><accepttime>2012-05-09 13:50:57</accepttime>" +
                    "<status>����</status><statustime>2012-05-09 13:50:57</statustime><operator>���ù���-���д������⹤��(p162ds02)</operator>" +
                    "<acceptunit>����</acceptunit><businessId>Charge</businessId><busOptCode>88009891659804</busOptCode><invoiceSum>0</invoiceSum>" +
                    "<capitalSum>��������</capitalSum><cardtype>88009891659804</cardtype><linkphone>0</linkphone><linkaddress>��������</linkaddress>" +
                    "</invoicelist></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(resp);
        }
        catch (Exception e)
        {
            ReturnWrap rtwpp = new ReturnWrap();            
            return rtwpp;
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
    	ReturnWrap rtwp = new ReturnWrap();
        try
        {
            /*
             * String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head
             * version=\"1.0\">" + "<menuid></menuid><process_code>cli_qry_supplyprtinvoice</process_code><verify_code></verify_code>" + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>" + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" + "<![CDATA[Processing
             * the request succeeded!]]></retmsg></retinfo></message_head><message_body>" + "<![CDATA[<?xml
             * version=\"1.0\" encoding=\"GBK\" ?><message_content>" + "<invoice_cnt>1</invoice_cnt>" + "<invoicelist><chargedate>20120101</chargedate><invoiceinfo>printDate@~@20110609||" +
             * "username@~@��7115176988576||callfeeCreateTime@~@||telnumber@~@15864500526||" +
             * "formnum@~@711110609957987385||invoicefeedx@~@��ӭѡ���й��ƶ�ͨ�ţ�||invoicefee@~@99.00Ԫ||" +
             * "paynumno@~@135176988576|| ||invoicefeehj@~@Ԥ�滰��@99.00||Score@~@||agreementleftbal@~@@||" +
             * "agreementleftbal_Z@~@@||||||InvoiceNote@~@�������飬���¼www.hb.10086.cn��ѯ||" +
             * "printtime@~@��ӡ��Ʊʱ��@@16:47:40||totalmoney@~@ʵ���ֽ�@@99.00||leftmoney@~@�������@@30799.29||" +
             * "overdraft@~@�������ö��@@0.00</invoiceinfo></invoicelist>" + "</message_content>]]></message_body></message_response>";
             */

            /*
             * String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head
             * version=\"1.0\">"+ "<menuid>invoicePrint</menuid><process_code>cli_qry_invoiceinfo_hub</process_code><verify_code></verify_code><unicontact></unicontact>"+ "<resp_time>20120326142254</resp_time><sequence><req_seq>9</req_seq><operation_seq>"+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"+ "<![CDATA[Processing
             * the request succeeded!]]></retmsg></retinfo></message_head><message_body>"+ "<![CDATA[<?xml
             * version=\"1.0\" encoding=\"GBK\" ?><message_content>"+ "<invoice_cnt>1</invoice_cnt>"+ "<invoicelist>"+ "<chargedate>20120323</chargedate>"+ "<invoiceinfo>"+
             * "paynumno@~@||"+ "username@~@����13605444000||"+ "telnumber@~@13605444000||"+
             * "formnum@~@634120323891281550||"+ "note@~@||"+ "WorkStation@~@����||"+ "CollectOper@~@101||"+
             * "time@~@2012.03.23||"+ "rectype@~@�ش�Ʊ||"+ "PrdGrpHead@~@������||"+ "PrdGrpInfo@~@���� ʵ�� ���||"+
             * "BrandLogo@~@BrandXNWLogo.bmp||"+ "ContentItem1Name@~@���ӷ�||"+ "ContentItem1@~@20.00||"+
             * "ContentItem4Name@~@�ϼ�:||"+ "ContentItem4@~@20.00||"+ "totalmoneydx@~@��ʰԲ��||"+ "totalmoney@~@20.00Ԫ"+ "</invoiceinfo>"+ "</invoicelist>"+ "</message_content>]]></message_body></message_response>";
             */
            
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLARReprintInvoice")) 
            {
//              String response = "[{invoiceTypeItem:'invoice_cnt',invoiceItemData:'1'},{invoiceTypeItem:'chargedate',invoiceItemData:'20111010'},{invoiceTypeItem:'hz_username',invoiceItemData:'�ͻ�ȫ��'}"
//                  + ",{invoiceTypeItem:'hz_formnum',invoiceItemData:'634120509891654602'},{invoiceTypeItem:'hz_telnumber',invoiceItemData:'�绰����'},{invoiceTypeItem:'hz_totalmoneydx',invoiceItemData:'�շѽ��(��д)'}"
//                  + ",{invoiceTypeItem:'hfjedx',invoiceItemData:'���ѽ��(��д)'},{invoiceTypeItem:'hz_totalmoney',invoiceItemData:'С  д'},{invoiceTypeItem:'hz_rectype',invoiceItemData:'�������'}"
//                  + ",{invoiceTypeItem:'kxxz',invoiceItemData:'��������'},{invoiceTypeItem:'hz_time',invoiceItemData:'��ӡʱ��'},{invoiceTypeItem:'paynumno',invoiceItemData:''}"
//                  + ",{invoiceTypeItem:'username',invoiceItemData:'��20029663004184'},{invoiceTypeItem:'telnumber',invoiceItemData:'13677113451'},{invoiceTypeItem:'formnum',invoiceItemData:'711111010663004170'}"
//                  + ",{invoiceTypeItem:'note',invoiceItemData:''},{invoiceTypeItem:'WorkStation',invoiceItemData:'��������Ӫҵ��'},{invoiceTypeItem:'CollectOper',invoiceItemData:'1101838'}"
//                  + ",{invoiceTypeItem:'time',invoiceItemData:'10:07:30'},{invoiceTypeItem:'year',invoiceItemData:'2011'},{invoiceTypeItem:'month',invoiceItemData:'10'}"
//                  + ",{invoiceTypeItem:'day',invoiceItemData:'10'},{invoiceTypeItem:'rectype',invoiceItemData:'�ش�Ʊ'},{invoiceTypeItem:'PrdGrpHead',invoiceItemData:'ȫ��ͨ'}"
//                  + ",{invoiceTypeItem:'PrdGrpInfo',invoiceItemData:'�������ƿء�Ʒλ'},{invoiceTypeItem:'ggcont',invoiceItemData:''},{invoiceTypeItem:'BrandLogo',invoiceItemData:'C://BrandGotoneLogo.bmp'}"
//                  + ",{invoiceTypeItem:'ContentItem1Name',invoiceItemData:'�ֻ�֧����ֵ'},{invoiceTypeItem:'ContentItem1',invoiceItemData:'300.00'},{invoiceTypeItem:'ContentItem3Name',invoiceItemData:'�ϼ�:'}"
//                  + ",{invoiceTypeItem:'ContentItem3',invoiceItemData:'300.00'},{invoiceTypeItem:'totalmoneydx',invoiceItemData:'����Բ��'},{invoiceTypeItem:'totalmoney',invoiceItemData:'300.00Ԫ'}]";
                
                String response = "[{\"invoiceItemData\":\"����ҵ��ʹ�÷�\",\"invoiceTypeItem\":\"feetype\"},{\"invoiceItemData\":\"ȫ��ͨ:�������ƿء�Ʒλ\",\"invoiceTypeItem\":\"productgrp\"},{\"invoiceItemData\":\"����13607233161\",\"invoiceTypeItem\":\"username\"},{\"invoiceItemData\":\"13607233161\",\"invoiceTypeItem\":\"telnumber\"},{\"invoiceItemData\":\"7110000000161\",\"invoiceTypeItem\":\"paynumno\"},{\"invoiceItemData\":\"711131111405755683\",\"invoiceTypeItem\":\"formnum\"},{\"invoiceItemData\":\"����\",\"invoiceTypeItem\":\"WorkStation\"},{\"invoiceItemData\":\"101\",\"invoiceTypeItem\":\"CollectOper\"},{\"invoiceItemData\":\"2013\",\"invoiceTypeItem\":\"printyear\"},{\"invoiceItemData\":\"11\",\"invoiceTypeItem\":\"printmonth\"},{\"invoiceItemData\":\"11\",\"invoiceTypeItem\":\"printday\"},{\"invoiceItemData\":\"14��5��\",\"invoiceTypeItem\":\"custnetage\"},{\"invoiceItemData\":\"Ҽ��ҼʰҼԲ��\",\"invoiceTypeItem\":\"invoicefeedx\"},{\"invoiceItemData\":\"111.00Ԫ\",\"invoiceTypeItem\":\"invoicefee\"},{\"invoiceItemData\":\"Ԥ�滰�ѣ�111.00\",\"invoiceTypeItem\":\"invoicefeehj\"},{\"invoiceItemData\":\"��ӡ��Ʊʱ�䣺\",\"invoiceTypeItem\":\"hz_printtime\"},{\"invoiceItemData\":\"16:41:17\",\"invoiceTypeItem\":\"printtime\"},{\"invoiceItemData\":\"ʵ���ֽ�\",\"invoiceTypeItem\":\"hz_totalmoney\"},{\"invoiceItemData\":\"111.00\",\"invoiceTypeItem\":\"totalmoney\"},{\"invoiceItemData\":\"������\",\"invoiceTypeItem\":\"hz_leftmoney\"},{\"invoiceItemData\":\"0.00\",\"invoiceTypeItem\":\"leftmoney\"},{\"invoiceItemData\":\"�������ö�ȣ�\",\"invoiceTypeItem\":\"hz_overdraft\"},{\"invoiceItemData\":\"0.00\",\"invoiceTypeItem\":\"overdraft\"},{\"invoiceItemData\":\"�������飬���¼www.hb.10086.cn��ѯ\",\"invoiceTypeItem\":\"note\"}]";
                
                return getIntMsgUtil().parseJsonResponse(response, null, new String[]{"invoiceTypeItem", "invoiceItemData"});
            }

            String response = "<message_response><message_head version=\"1.0\"><menuid>invoicePrint</menuid><process_code>cli_qry_invoiceinfo_hub</process_code>"
                    + "<verify_code></verify_code><resp_time>20120423162734</resp_time><sequence><req_seq>346</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA["
                    + "<message_content><invoice_cnt>1</invoice_cnt><invoicelist><chargedate>20111010</chargedate>"
                    + "<invoiceinfo>hz_username@~@�ͻ�ȫ��||hz_formnum@~@634120509891654602||hz_telnumber@~@�绰����||hz_totalmoneydx@~@�շѽ��(��д)||"
                    + "hfjedx@~@���ѽ��(��д)||hz_totalmoney@~@С  д||hz_rectype@~@�������||kxxz@~@��������||hz_time@~@��ӡʱ��||paynumno@~@||"
                    + "username@~@��20029663004184||telnumber@~@13677113451||formnum@~@711111010663004170||note@~@||WorkStation@~@��������Ӫҵ��||"
                    + "CollectOper@~@1101838||time@~@10:07:30||year@~@2011||month@~@10||day@~@10||rectype@~@�ش�Ʊ||PrdGrpHead@~@ȫ��ͨ||"
                    + "PrdGrpInfo@~@�������ƿء�Ʒλ||ggcont@~@||BrandLogo@~@C://BrandGotoneLogo.bmp||ContentItem1Name@~@�ֻ�֧����ֵ||"
                    + "ContentItem1@~@300.00||ContentItem3Name@~@�ϼ�:||ContentItem3@~@300.00||totalmoneydx@~@����Բ��||totalmoney@~@300.00Ԫ"
                    + "</invoiceinfo></invoicelist></message_content>]]></message_body></message_response>";
            
            response = "<message_response><message_head version=\"1.0\"><menuid>invoicePrint</menuid><process_code>cli_qry_invoiceinfo_hub</process_code>"
                + "<verify_code></verify_code><resp_time>20120423162734</resp_time><sequence><req_seq>346</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                + "</message_head><message_body><![CDATA["
                + "<message_content>" 
                + "<invoicelist><name>invoice_cnt</name><value>1</value></invoicelist>" 
                + "<invoicelist><name>chargedate</name><value>20111010</value></invoicelist>" 
                + "<invoicelist><name>hz_username</name><value>�ͻ�ȫ��</value></invoicelist>" 
                + "<invoicelist><name>hz_formnum</name><value>634120509891654602</value></invoicelist>" 
                + "<invoicelist><name>hz_telnumber</name><value>�绰����</value></invoicelist>" 
                + "<invoicelist><name>hz_totalmoneydx</name><value>�շѽ��(��д)</value></invoicelist>" 
                + "<invoicelist><name>hfjedx</name><value>���ѽ��(��д)</value></invoicelist>" 
                + "<invoicelist><name>hz_totalmoney</name><value>С  д</value></invoicelist>" 
                + "<invoicelist><name>hz_rectype</name><value>�������</value></invoicelist>" 
                + "<invoicelist><name>kxxz</name><value>��������</value></invoicelist>" 
                + "<invoicelist><name>hz_time</name><value>��ӡʱ��</value></invoicelist>" 
                + "<invoicelist><name>paynumno</name><value></value></invoicelist>" 
                + "<invoicelist><name>username</name><value>��20029663004184</value></invoicelist>" 
                + "<invoicelist><name>telnumber</name><value>13677113451</value></invoicelist>" 
                + "<invoicelist><name>formnum</name><value>711111010663004170</value></invoicelist>" 
                + "<invoicelist><name>note</name><value></value></invoicelist>" 
                + "<invoicelist><name>WorkStation</name><value>��������Ӫҵ��</value></invoicelist>" 
                + "<invoicelist><name>CollectOper</name><value>1101838</value></invoicelist>" 
                + "<invoicelist><name>time</name><value>10:07:30</value></invoicelist>" 
                + "<invoicelist><name>year</name><value>2011</value></invoicelist>" 
                + "<invoicelist><name>month</name><value>10</value></invoicelist>" 
                + "<invoicelist><name>day</name><value>10</value></invoicelist>" 
                + "<invoicelist><name>rectype</name><value>�ش�Ʊ</value></invoicelist>" 
                + "<invoicelist><name>PrdGrpHead</name><value>ȫ��ͨ</value></invoicelist>" 
                + "<invoicelist><name>PrdGrpInfo</name><value>�������ƿء�Ʒλ</value></invoicelist>" 
                + "<invoicelist><name>ggcont</name><value></value></invoicelist>" 
                + "<invoicelist><name>BrandLogo</name><value>C://BrandGotoneLogo.bmp</value></invoicelist>" 
                + "<invoicelist><name>ContentItem1Name</name><value>�ֻ�֧����ֵ</value></invoicelist>" 
                + "<invoicelist><name>ContentItem1</name><value>300.00</value></invoicelist>" 
                + "<invoicelist><name>ContentItem3Name</name><value>�ϼ�:</value></invoicelist>" 
                + "<invoicelist><name>ContentItem3</name><value>300.00</value></invoicelist>" 
                + "<invoicelist><name>totalmoneydx</name><value>����Բ��</value></invoicelist>" 
                + "<invoicelist><name>totalmoney</name><value>300.00Ԫ</value></invoicelist>" 
                + "</message_content>]]></message_body></message_response>";
            
            response = "<message_response><message_head version=\"1.0\"><menuid>invoicePrint</menuid><process_code>cli_qry_invoiceinfo_hub</process_code>"
                + "<verify_code></verify_code><resp_time>20120423162734</resp_time><sequence><req_seq>346</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                + "</message_head><message_body><![CDATA["
                + "<message_content>" 
                + "<invoice_cnt>1</invoice_cnt>" 
                + "<chargedate>20111010</chargedate>"
                + "<hz_username>�ͻ�ȫ��</hz_username>"
                + "<hz_formnum>634120509891654602</hz_formnum>"
                + "<hz_telnumber>�绰����</hz_telnumber>"
                + "<hz_totalmoneydx>�շѽ��(��д)</hz_totalmoneydx>"
                + "<hfjedx>���ѽ��(��д)</hfjedx>" 
                + "<hz_totalmoney>С  д</hz_totalmoney>"
                + "<hz_rectype>�������</hz_rectype>" 
                + "<kxxz>��������</kxxz>" 
                + "<hz_time>��ӡʱ��</hz_time>" 
                + "<paynumno></paynumno>" 
                + "<username>��20029663004184</username>" 
                + "<telnumber>13677113451</telnumber>" 
                + "<formnum>711111010663004170</formnum>" 
                + "<note></note>" 
                + "<WorkStation>��������Ӫҵ��</WorkStation>" 
                + "<CollectOper>1101838</CollectOper>" 
                + "<time>10:07:30</time>" 
                + "<year>2011</year>" 
                + "<month>10</month>" 
                + "<day>10</day>" 
                + "<rectype>�ش�Ʊ</rectype>" 
                + "<PrdGrpHead>ȫ��ͨ</PrdGrpHead>" 
                + "<PrdGrpInfo>�������ƿء�Ʒλ</PrdGrpInfo>" 
                + "<ggcont></ggcont>" 
                + "<BrandLogo>C://BrandGotoneLogo.bmp</BrandLogo>" 
                + "<ContentItem1Name>�ֻ�֧����ֵ</ContentItem1Name>" 
                + "<ContentItem1>300.00</ContentItem1>" 
                + "<ContentItem3Name>�ϼ�:</ContentItem3Name>" 
                + "<ContentItem3>300.00</ContentItem3>" 
                + "<totalmoneydx>����Բ��</totalmoneydx>" 
                + "<totalmoney>300.00Ԫ</totalmoney>"
                + "</message_content>]]></message_body></message_response>";
            
            rtwp = intMsgUtil.parseResponse(response);
            return rtwp;
        }
        catch (Exception e)
        {
            return rtwp;
        }
    }
    
    // add end ykf38827 2012/03/20 R003C12L03n01 OR_HUB_201202_800
    
    // add begin yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
    /**
     * ���ýӿڻ�ȡ��Ʒ���ȷ����Ϣ
     * 
     * @param map
     * @return
     */
    @Override
    public ReturnWrap mainProductRecInfo(MsgHeaderPO msgHeader, String ncode, String inttime)
    {
        try
        {
            // modify begin wWX217192 2014-09-20 OR_huawei_201409_433 �����ն˽���EBUS_�ʷ��ײ�ת�� 
            if(IntMsgUtil.isTransEBUS("BLCSProductRec"))
            {
                /*String response = "{\"formNumber\":\"711140923210886396\",\"orderID\":\"711140923210886388\",\"subsID\":\"7115183830258\",\"voipImsi\":\"\",\"yywwFormNum\":\"20089210886394\"," +
                        "\"newProdList\":[{\"editStatus\":\"A\",\"prodID\":\"Christ_bag1\",\"prodName\":\"Christ��Ʒ��1\",\"prodCreateDate\":\"2012-04-25 10:06:53\",\"prodEndDate\":\"\",\"prodPackName\":\"\",\"privID\":\"\",\"privName\":\"\",\"privCreateDate\":\"\",\"privEndDate\":\"\",\"delReason\":\"\"}]," +
                        "\"originalProdList\":[{\"editStatus\":\"O\",\"prodID\":\"C06\",\"prodName\":\"��������\",\"prodCreateDate\":\"2007-09-01 00:00:00\",\"prodEndDate\":\"\",\"prodPackName\":\"\",\"privID\":\"\",\"privName\":\"\",\"privCreateDate\":\"\",\"privEndDate\":\"\",\"delReason\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodID\":\"H01\",\"prodName\":\"������ʾ\",\"prodCreateDate\":\"2002-07-02 17:33:40\",\"prodEndDate\":\"\",\"prodPackName\":\"\",\"privID\":\"\",\"privName\":\"\",\"privCreateDate\":\"\",\"privEndDate\":\"\",\"delReason\":\"\"}]," +
                        "\"delProdList\":[{\"editStatus\":\"D\",\"prodID\":\"Christmasappend\",\"prodName\":\"Christmas��ֵ1\",\"prodCreateDate\":\"2012-04-12 15:46:20\",\"prodEndDate\":\"\",\"prodPackName\":\"\",\"privID\":\"\",\"privName\":\"\",\"privCreateDate\":\"\",\"privEndDate\":\"\",\"delReason\":\"\"}]}";*/
                
                /*String response = "{\"delProdList\":[{\"delReason\":\"\",\"editStatus\":\"D\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"2014-10-01 00:00:00\",\"prodID\":\"MP9990402000353\",\"prodName\":\"�������еش������ײ�(У԰��)18Ԫ\",\"prodPackName\":\"\"}]," +
                        "\"newProdList\":[{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-10-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"MP9990402000355\",\"prodName\":\"�������еش������ײ�(У԰��)38Ԫ\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-10-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"pkg.mz.st\",\"prodName\":\"[�����Żݰ�]:��׼���еش�\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-10-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"B211055\",\"prodName\":\"�������еش������ײͣ�У԰�棩38Ԫ��ѡ��Ʒ\",\"prodPackName\":\"[�����Żݰ�]:��׼���еش�\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-10-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"pkg.eduwlan\",\"prodName\":\"��УWLANҵ��\",\"prodPackName\":\"\"},{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-10-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"atom.EduWlan\",\"prodName\":\"��УWLANҵ��(����)\",\"prodPackName\":\"��УWLANҵ��\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-10-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"G211058\",\"prodName\":\"�������еش������ײͣ�У԰�棩38ԪWLAN�Ż�\",\"prodPackName\":\"��УWLANҵ��\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-10-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"G_RP210211\",\"prodName\":\"������;һ���Ʊ�ѡ�Żݰ�(���ʷ�)\",\"prodPackName\":\"\"}]," +
                        "\"originalProdList\":[{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"LTE\",\"prodName\":\"4G����\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"114\",\"prodName\":\"����Ϣ\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"pkg.serv.ct\",\"prodName\":\"����;��\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"901\",\"prodName\":\"���ڳ�;\",\"prodPackName\":\"����;��\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"CTYFZ\",\"prodName\":\"���ʳ�;һ����\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"G210212\",\"prodName\":\"���ʳ�;һ���ư����ӼƷ�\",\"prodPackName\":\"���ʳ�;һ����\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"S1406684101\",\"prodName\":\"�����������\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"120\",\"prodName\":\"����ת��\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"100\",\"prodName\":\"������ʾ\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"4G1stMatch\",\"prodName\":\"4G�ͻ�ר���������¼����Ż�\",\"prodPackName\":\"\"}]}";*/
                String response = "{\"delProdList\":[{\"delReason\":\"\",\"editStatus\":\"D\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"2014-12-01 00:00:00\",\"prodID\":\"MP9990402000353\",\"prodName\":\"�������еش������ײ�(У԰��)18Ԫ\",\"prodPackName\":\"\"}]," +
                        "\"newProdList\":[{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"MP9990402000355\",\"prodName\":\"�������еش������ײ�(У԰��)38Ԫ\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"pkg.mz.st\",\"prodName\":\"[�����Żݰ�]:��׼���еش�\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"B211055\",\"prodName\":\"�������еش������ײͣ�У԰�棩38Ԫ��ѡ��Ʒ\",\"prodPackName\":\"[�����Żݰ�]:��׼���еش�\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"pkg.eduwlan\",\"prodName\":\"��УWLANҵ��\",\"prodPackName\":\"\"},{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"atom.EduWlan\",\"prodName\":\"��УWLANҵ��(����)\",\"prodPackName\":\"��УWLANҵ��\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"G211058\",\"prodName\":\"�������еش������ײͣ�У԰�棩38ԪWLAN�Ż�\",\"prodPackName\":\"��УWLANҵ��\"},{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"G_RP210211\",\"prodName\":\"������;һ���Ʊ�ѡ�Żݰ�(���ʷ�)\",\"prodPackName\":\"\"}]," +
                        "\"originalProdList\":[{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"LTE\",\"prodName\":\"4G����\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"114\",\"prodName\":\"����Ϣ\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"pkg.serv.ct\",\"prodName\":\"����;��\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"901\",\"prodName\":\"���ڳ�;\",\"prodPackName\":\"����;��\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"CTYFZ\",\"prodName\":\"���ʳ�;һ����\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"G210212\",\"prodName\":\"���ʳ�;һ���ư����ӼƷ�\",\"prodPackName\":\"���ʳ�;һ����\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"S1406684101\",\"prodName\":\"�����������\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"120\",\"prodName\":\"����ת��\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"100\",\"prodName\":\"������ʾ\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"4G1stMatch\",\"prodName\":\"4G�ͻ�ר���������¼����Ż�\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"privCreateDate\":\"2014-11-01 00:00:00\",\"privEndDate\":\"\",\"privID\":\"2014101301\",\"privName\":\"����1\",\"prodCreateDate\":\"2014-11-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"20141013\",\"prodName\":\"Ԥ������-spj����\",\"prodPackName\":\"\"}]," +
                        "\"showProdList\":[{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"MP9990402000355\",\"prodName\":\"�������еش������ײ�(У԰��)38Ԫ\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"B211055\",\"prodName\":\"�������еش������ײͣ�У԰�棩38Ԫ��ѡ��Ʒ\",\"prodPackName\":\"[�����Żݰ�]:��׼���еش�\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"atom.EduWlan\",\"prodName\":\"��УWLANҵ��(����)\",\"prodPackName\":\"��УWLANҵ��\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"G211058\",\"prodName\":\"�������еش������ײͣ�У԰�棩38ԪWLAN�Ż�\",\"prodPackName\":\"��УWLANҵ��\"}," +
                        "{\"editStatus\":\"A\",\"prodCreateDate\":\"2014-12-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"G_RP210211\",\"prodName\":\"������;һ���Ʊ�ѡ�Żݰ�(���ʷ�)\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"LTE\",\"prodName\":\"4G����\",\"prodPackName\":\"\"},{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"114\",\"prodName\":\"����Ϣ\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"pkg.serv.ct\",\"prodName\":\"����;��\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"901\",\"prodName\":\"���ڳ�;\",\"prodPackName\":\"����;��\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"CTYFZ\",\"prodName\":\"���ʳ�;һ����\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"G210212\",\"prodName\":\"���ʳ�;һ���ư����ӼƷ�\",\"prodPackName\":\"���ʳ�;һ����\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"S1406684101\",\"prodName\":\"�����������\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"120\",\"prodName\":\"����ת��\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"100\",\"prodName\":\"������ʾ\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"\",\"prodID\":\"4G1stMatch\",\"prodName\":\"4G�ͻ�ר���������¼����Ż�\",\"prodPackName\":\"\"}," +
                        "{\"editStatus\":\"O\",\"privCreateDate\":\"2014-11-01 00:00:00\",\"privEndDate\":\"\",\"privID\":\"2014101301\",\"privName\":\"����1\",\"prodCreateDate\":\"2014-11-01 00:00:00\",\"prodEndDate\":\"\",\"prodID\":\"20141013\",\"prodName\":\"Ԥ������-spj����\",\"prodPackName\":\"\"}," +
                        "{\"delReason\":\"\",\"editStatus\":\"D\",\"prodCreateDate\":\"2014-09-18 18:02:30\",\"prodEndDate\":\"2014-12-01 00:00:00\",\"prodID\":\"MP9990402000353\",\"prodName\":\"�������еش������ײ�(У԰��)18Ԫ\",\"prodPackName\":\"\"}]}";
                
                ReturnWrap rtwp = new ReturnWrap();
                
                rtwp.setReturnObject(response);
                rtwp.setStatus(SSReturnCode.SUCCESS);
                rtwp.setReturnMsg("");
                
                return rtwp;
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recMainProdChange</menuid>"
                    + "<process_code>cli_busi_MainIntProductRec</process_code><verify_code></verify_code><unicontact></unicontact>"
                    + "<resp_time>20120425100133</resp_time><sequence><req_seq>4</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype>"
                    + "<retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    + "<message_content>"
                    + "<prodlist><type>A</type><prodid>Christ_bag1</prodid><prodname>Christ��Ʒ��1</prodname><begindate>2012-04-25 10:06:53</begindate>"
                    + "<enddate>2012-04-25 10:06:53</enddate><pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate>"
                    + "</privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>C06</prodid><prodname>��������</prodname><begindate>2007-09-01 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>H01</prodid><prodname>������ʾ</prodname><begindate>2002-07-02 17:33:40</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>SELFRING</prodid><prodname>����</prodname><begindate>2006-02-23 11:17:50</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>Z07</prodid><prodname>�ƶ�ȫʱͨ</prodname><begindate>2006-08-01 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>M03</prodid><prodname>�������ο�ͨ</prodname><begindate>2001-03-23 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname>���μ����</pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>pkg_roamid</prodid><prodname>���μ����</prodname><begindate>2001-03-23 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>pp.gt.csl</prodid><prodname>�ں�V��</prodname><begindate>2007-09-01 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>pkg_cslbx.634</prodid><prodname>���������ײͱ�ѡ��</prodname><begindate>2007-09-01 00:00:00</begindate><enddate>"
                    + "</enddate><pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>gl.base.999251.634</prodid><prodname>ȫʱͨЭ������3Ԫ</prodname><begindate>2007-09-01 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid>gl.base.999251.634</privid><privname>ȫʱͨЭ������3Ԫ</privname><privbegindate>2007-09-01 00:00:00</privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>D</type><prodid>Christmasappend</prodid><prodname>Christmas��ֵ1</prodname><begindate>2012-04-12 15:46:20</begindate><enddate>2012-04-26 00:00:00</enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
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
        	// ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("IncProductSrv2"))
            {
                String response = "{'-preparebusi':'BsacNBSubmit','ACCESSTYPE':'INNER','ADDNCODE':'MP9990402000353'," 
                	+ "'ADD_ENDDATE':'','ADD_STARTDATE':'2014-12-01 00:00:00','BOSSCODE':'INT_B211053_MAIN','BRAND':'BrandSzx',"
                	+ "'BUSITYPE':'0','CALL_NUMBER':'13545408602','CURID':'','CURNCODE':'','DELNCODE':'MP7110503000163','DEL_ENDDATE':'',"
                	+ "'EFFTYPE':'Type_Default','EFF_DATE':'2014-12-01','EFF_DATETIME':'2014-12-01 00:00:00','END_DATE':'ZERO'," 
                	+ "'ERRNO':'0','EXECUTECMD':'TRUE','FORMNUMBER':'711141117221133696','HASPARAM':'0','IMSI':'460001823766622'," 
                	+ "'INPARAMFORMAT':'','INPARAMSPLIT':'','IPADDRESS':'','ISNEEDTHIRDCONF':'0','ISWRITEFILELOG':'0'," 
                	+ "'ISWRITETABLOG':'1','JOBDESC':'���еش������ײ�2012�棨У԰�棩18Ԫ','JOBNAME':'�����Ʒת����','LINKNODE':''," 
                	+ "'LINKTYPE':'','MAINPRODID':'MP7110503000163','MSISDN':'13545408602','NCODE':'INT_B211053_MAIN'," 
                	+ "'NEXTOUTPARAM':'','NEXTOUTPARAMALL':'','NEXTOUTPARAMNOREP':'','NOPENEDPMP':'ZERO','OLD_PASSWD':''," 
                	+ "'OPENEDPMP':'ZERO','ORDERRESULT':'9900','ORI_NEXTATTRS':'','OUTOPERID':'','OUTPARAMFORMAT':'','OUTPARAMSPLIT':''," 
                	+ "'PARM':'','REALOPERID':'','REALRETCODE':'100','REGION':'711','RETCODE':'0','RETCONVERT':'1'," 
                	+ "'RETMSG':'','RETURN':'','SENDORNOT':'0','STEP':'76','STYPE':'ADD','SUBORDERRESULT':'','SUBSCREATEDATE':''," 
                	+ "'SUBSID':'7115181030118','TELNUM':'13545408602','TEMPLATENO':'P00100','TEMPLATEPARA':'1$�����Ʒת����&2$2014-12-01&3$ZERO&4$ZERO'," 
                	+ "'UNITID':'INNER','VNCODE':'vsmasingle','YYWWFORMNUM':'20089221133694','accessType':'bsacAtsv','formnum':'','interfaceID':'IncProductSrv2'," 
                	+ "'menuID':'telProdDiy','operatorID':'101','region':'','reqSeq':'1','reqTime':'20141117152831','routeType':'1'," 
                	+ "'telNum':'13545408602','uniContact':'','uniContactFlag':'','unitID':'HUAWEI','virtualActorFlag':'1'}";
                return intMsgUtil.parseJsonResponse(response, null, null);
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
                    "<message_response><message_head version=\"1.0\">" +
                    "<menuid>recMainProdChange</menuid><process_code>" +
                    "cli_busi_ChangeProductSubmit</process_code><verify_code></verify_code>" +
                    "<unicontact></unicontact><resp_time>20120425100313</resp_time><sequence>" +
                    "<req_seq>5</req_seq><operation_seq></operation_seq></sequence><retinfo>" +
                    "<rettype>0</rettype><retcode>979077</retcode><retmsg>" +
                    "<![CDATA[������ҵ�������������ԭ��:��ʾ����Ʒ[����]����������[��Ʒ���]ҵ��!......]]>" +
                    "</retmsg></retinfo></message_head></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    // add begin yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
    
    /**
     * ��ѯҪ��ӡ�ķ�Ʊ��ӡ������
     * @param map
     * @return
     */
    public ReturnWrap invoiceDataByActivity(Map map)
    {
        try
        { 
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("PTSupplyPrintInvoice"))
            {
                String response = "{'payTime':'20120323','invoiceCount':'1','printItems':" +
                        "'paynumno@~@||username@~@����13605444000||telnumber@~@13605444000||"+
                        "formnum@~@634120323891281550||note@~@||WorkStation@~@����||"+
                        "CollectOper@~@101||time@~@2012.03.23||rectype@~@�ش�Ʊ||PrdGrpHead@~@������||"+
                        "PrdGrpInfo@~@���� ʵ�� ���||BrandLogo@~@BrandXNWLogo.bmp||ContentItem1Name@~@���ӷ�||"+
                        "ContentItem1@~@20.00||ContentItem4Name@~@�ϼ�:||ContentItem4@~@20.00||"+
                        "totalmoneydx@~@��ʰԲ��||totalmoney@~@20.00Ԫ'}";
                ReturnWrap rtwp = null;
                rtwp = intMsgUtil.parseJsonResponse(response, null, null);
                
                if (rtwp != null && rtwp.getStatus() == SSReturnCode.SUCCESS)
                {
                    CTagSet ctagSet = (CTagSet) rtwp.getReturnObject();
                    
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
                    if ("1".equals(invoiceCount))
                    {
                        // ����һ��
                        crset.AddRow();
                        
                        // ��������
                        crset.SetValue(0, 0, ctagSet.GetValue("payTime"));
                        
                        // ��Ʊ����
                        crset.SetValue(0, 1, ctagSet.GetValue("printItems"));
                    }
                    v.add(crset);
                    rtwp.setReturnObject(v);
                }
                return rtwp;
                
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"+
                       "<menuid>invoicePrint</menuid><process_code>cli_qry_invoiceinfo_hub</process_code><verify_code></verify_code><unicontact></unicontact>"+
                       "<resp_time>20120326142254</resp_time><sequence><req_seq>9</req_seq><operation_seq>"+
                       "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"+
                       "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"+
                       "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"+
                       "<invoice_cnt>1</invoice_cnt>"+
                       "<invoicelist>"+
                       "<chargedate>20120323</chargedate>"+
                       "<invoiceinfo>"+
                       "paynumno@~@||"+
                       "username@~@����13605444000||"+
                       "telnumber@~@13605444000||"+
                       "formnum@~@634120323891281550||"+
                        "note@~@||"+
                       "WorkStation@~@����||"+
                       "CollectOper@~@101||"+
                       "time@~@2012.03.23||"+
                       "rectype@~@�ش�Ʊ||"+
                       "PrdGrpHead@~@������||"+
                       "PrdGrpInfo@~@���� ʵ�� ���||"+
                       "BrandLogo@~@BrandXNWLogo.bmp||"+
                       "ContentItem1Name@~@���ӷ�||"+
                        "ContentItem1@~@20.00||"+
                        "ContentItem4Name@~@�ϼ�:||"+
                        "ContentItem4@~@20.00||"+
                        "totalmoneydx@~@��ʰԲ��||"+
                        "totalmoney@~@20.00Ԫ"+
                        "</invoiceinfo>"+
                        "</invoicelist>"+
                        "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    public ReturnWrap validateTelSim(Map<String, String> paramMap)
    {
        return null;
    }
    
    public ReturnWrap queryFeeItemList(Map<String, String> paramMap)
    {
        return null;
    }
   
    public ReturnWrap querySaleCond(Map<String, String> paramMap){
        return null;
    }
@Override
    
    public ReturnWrap qryAllBackInfo(Map map)
    {
        // TODO Auto-generated method stub
        return null;
    }
    public ReturnWrap commitInstallProd(Map<String, String> paramMap)
    {
        return null;
    }
/**
 * ֤��У��
 * ֤��У��
 * @param ֤������
 * @param startTime ֤������
 * @param endTime ��غ���
 * @param logLevel ����ԱId
 * @param userName �ն˻�ID
 * @param bufferNum ��ǰ�˵�Id
 * @return tagset �������
 * @since CommonLog1.0
 * @remark create yKF73963 2012-07-12 OR_HUB_201202_1192
*/
 public ReturnWrap certCardInfo(Map paramMap){
     String certType = (String)paramMap.get("certType");
     String certId = (String)paramMap.get("certId");
     String pesSubsNum = (String)paramMap.get("pesSubsNum");
     String operid = (String)paramMap.get("curOper");
     String atsvNum = (String)paramMap.get("atsvNum");
     String menuid = (String)paramMap.get("curMenuId");
     String region = (String)paramMap.get("region");
    
     
     Document doc = DocumentHelper.createDocument();
     Element eBody = doc.addElement("message_content");
    // ֤������
     Element eReq_telnum = eBody.addElement("certType");
     eReq_telnum.addText(certType);
     // ֤������
     Element eReq_startdate = eBody.addElement("certId");
     eReq_startdate.addText(certId);
     // ��غ���
     Element eReq_endDate = eBody.addElement("pesSubsNum");
     eReq_endDate.addText(pesSubsNum);
     Document docXML = intMsgUtil.createMsg(doc,"cli_ChkCertSubs_hub",menuid,null,"0",region,operid,atsvNum);
     return intMsgUtil.invoke(docXML);
   
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
 public ReturnWrap getSimInfo(Map paramMap){
     String iccid = (String)paramMap.get("iccid");
     String operid = (String)paramMap.get("curOper");
     String atsvNum = (String)paramMap.get("atsvNum");
     String menuid = (String)paramMap.get("curMenuId");
     String region = (String)paramMap.get("region");
    
     
     Document doc = DocumentHelper.createDocument();
     Element eBody = doc.addElement("message_content");
    //sim����iccid
     Element eiccid = eBody.addElement("iccid");
     eiccid.addText(iccid);
     Document docXML = intMsgUtil.createMsg(doc,"cli_GetSimInfo_hub",menuid,null,"0",region,operid,atsvNum);
     return intMsgUtil.invoke(docXML);
   
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
     
     Document docXML = intMsgUtil.createMsg(doc, "cli_TheHlrGrpNum_hub", menuid, null, "0", region, operid, atsvNum);
     return intMsgUtil.invoke(docXML);
     
 }
 /**
  * ��ѡ����
  * * @param telnum �绰����
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
     
     Document doc = DocumentHelper.createDocument();
     Element eBody = doc.addElement("message_content");
     // �绰����
     Element etelnum = eBody.addElement("telnum");
     etelnum.addText(telnum);
    
     Document docXML = intMsgUtil.createMsg(doc, "cli_TelNumTmpPick_hub", menuid, null, "0", region, operid, atsvNum);
     return intMsgUtil.invoke(docXML);
     
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
         if(IntMsgUtil.isTransEBUS("BLCSGetRecommendProductList"))
         {
             // ����Ϊһ��List
            List<Map> list = new ArrayList<Map>();
            Map<String,String> param1 = new HashMap<String,String>();
            param1.put("prodName", "prodName001");
            param1.put("privName", "commendoid001");
            param1.put("recmdDiction", "privName001");
            param1.put("userSeq", "userSeq001");
            param1.put("actid", "actid001");
            param1.put("oid", "oid001");
            param1.put("entityName", "entityName001");
            param1.put("prodid", "prodid001");
            param1.put("privid", "privid001");
            param1.put("recmdType", "recmdType001");
            param1.put("contactTimes", "contactTimes001");
            param1.put("notes", "notes001");
            param1.put("actName", "actName001");
            param1.put("recmdTimeValue", "recmdTimeValue001");
            param1.put("isFeedBackDef", "1");
            param1.put("type", "typee001");
            
            Map<String,String> param2 = new HashMap<String,String>();
            param2.put("prodName", "prodName002");
            param2.put("privName", "commendoid002");
            param2.put("recmdDiction", "privName002");
            param2.put("userSeq", "userSeq002");
            param2.put("actid", "actid002");
            param2.put("oid", "oid002");
            param2.put("entityName", "entityName002");
            param2.put("prodid", "prodid002");
            param2.put("privid", "privid002");
            param2.put("recmdType", "recmdType002");
            param2.put("contactTimes", "contactTimes002");
            param2.put("notes", "notes002");
            param2.put("actName", "actName002");
            param2.put("recmdTimeValue", "recmdTimeValue002");
            param2.put("isFeedBackDef", "0");
            param2.put("type", "type002");
            
            list.add(param1);
            list.add(param2);
            
            JSONArray jsonArray = JSONArray.fromObject(list);

            // ����List  ȡֵ�������List����λ�ö�Ӧ��ϵ����
            String[] outParamListIndexDefine = {"oid","entityName","recmdDiction","ncode","ncode","privid","userSeq","smsport",
                    "smstempletid","recmdTimeValue","actid","actName","contactTimes","notes","prodName","privName","recmdType",
                    "isFeedBackDef","type"};
            
            return getIntMsgUtil().parseJsonResponse(jsonArray.toString(),null,outParamListIndexDefine);
         }
         
         String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                 + "<menuid></menuid><process_code>cli_qry_recommendProductList</process_code><verify_code></verify_code>"
                 + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                 + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                 + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                 + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                 + "<rewardlist>" +
                        "<commendoid>commendoid001</commendoid>" +
                        "<businame>businame001</businame>" +
                        "<actdict>actdict001</actdict>" +
                        "<ncode>ncode001</ncode>" +
                        "<prodid>prodid001</prodid>" +
                        "<privid>privid001</privid>" +
                        "<userseq>userseq001</userseq>" +
                        "<smsport>smsport001</smsport>" +
                        "<smstempletid>smstempletid001</smstempletid>" +
                        "<commendtimetable>commendtimetable001</commendtimetable>" +
                        "<actid>actid001</actid>" +
                        "<actname>actname001</actname>" +
                        "<commendtime>commendtime001</commendtime>" +
                        "<actcontent>actcontent001</actcontent>" +
                        "<prodname>prodname001</prodname>" +
                        "<privname>privname001</privname>" +
                        "<commendtype>commendtype001</commendtype>" +
                        "<isfeedbackdef>0</isfeedbackdef>" +
                        "<eventtype>even</eventtype>" +
                 "</rewardlist>"+
                 "<rewardlist>" +
                         "<commendoid>commendoid002</commendoid>" +
                         "<businame>businame002</businame>" +
                         "<actdict>actdict002</actdict>" +
                         "<ncode>ncode002</ncode>" +
                         "<prodid>prodid002</prodid>" +
                         "<privid>privid002</privid>" +
                         "<userseq>userseq002</userseq>" +
                         "<smsport>smsport002</smsport>" +
                         "<smstempletid>smstempletid002</smstempletid>" +
                         "<commendtimetable>commendtimetable002</commendtimetable>" +
                         "<actid>actid002</actid>" +
                         "<actname>actname002</actname>" +
                         "<commendtime>commendtime002</commendtime>" +
                         "<actcontent>actcontent002</actcontent>" +
                         "<prodname>prodname002</prodname>" +
                         "<privname>privname002</privname>" +
                         "<commendtype>commendtype002</commendtype>" +
                         "<isfeedbackdef>1</isfeedbackdef>" +
                         "<eventtype></eventtype>" +
                  "</rewardlist>"

                 + "</message_content>]]></message_body></message_response>";
         return intMsgUtil.parseResponse(response);
     }
     catch (Exception e)
     {
         ReturnWrap rtwp = new ReturnWrap();
         rtwp.setStatus(0);
         rtwp.setReturnMsg("");
         
         return rtwp;
     }
 }
 
 /**
  * ��¼ҵ���Ƽ�����ӿ�_����Ӫ���Ƽ��
  * 
  * @param paramMap
  * @return
  * @see [�ࡢ��#��������#��Ա]
  */
 public ReturnWrap recommendFeedback(MsgHeaderPO msgHeader, String userSeqs, String status, String actIds, String eventTypes)
 {
     try
     {
         // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
         if(IntMsgUtil.isTransEBUS("BLCSRecommendFeedback"))
         {
            // ����Ϊ��ֵ�ԣ�����JSONObj
            Map<String,Object> outParamMap = new HashMap<String,Object>();
            JSONObject jsonObj = JSONObject.fromObject(outParamMap);
            
            return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
         }
         
         String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                 + "<menuid>qryBill</menuid><process_code>cli_busi_recommendFeedback</process_code><verify_code></verify_code>"
                 + "<resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq>"
                 + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                 + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                 + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                 +"</message_content>]]></message_body></message_response>";
         
         return intMsgUtil.parseResponse(response);
     }
     catch (Exception e)
     {
         ReturnWrap rtwp = new ReturnWrap();
         rtwp.setStatus(0);
         rtwp.setReturnMsg("");
         
         return rtwp;
     }
 }
 
 
 /**
  * �Ƽ�ҵ������_����Ӫ���Ƽ��
  * 
  * @param paramMap
  * @return
  * @see [�ࡢ��#��������#��Ա]
  */
 public ReturnWrap recommendProduct(MsgHeaderPO msgHeader, String userSeq, String actId, String recmdType)
 {
     try
     {
         // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
         if(IntMsgUtil.isTransEBUS("BLCSRecommendProductByActID"))
         {
            // ����Ϊ��ֵ�ԣ�����JSONObj
            Map<String,Object> outParamMap = new HashMap<String,Object>();
            // outParamMap.put("orderID", "34343434");
            JSONObject jsonObj = JSONObject.fromObject(outParamMap);
            
            return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
         }
         
         String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                 + "<menuid>qryBill</menuid><process_code>cli_busi_recommendProductByActID</process_code><verify_code></verify_code>"
                 + "<resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq>"
                 + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                 + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                 + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                 + "</message_content>]]></message_body></message_response>";
         
         return intMsgUtil.parseResponse(response);
     }
     catch (Exception e)
     {
         ReturnWrap rtwp = new ReturnWrap();
         rtwp.setStatus(0);
         rtwp.setReturnMsg("");
         
         return rtwp;
     }
 }
 
 
 
 /**
  * �Ƽ�ҵ������ɹ�������ҵ���Ƽ����_����Ӫ���Ƽ��
  * 
  * @param paramMap
  * @return
  * @see [�ࡢ��#��������#��Ա]
  */
 public ReturnWrap setRecommendSuccess(MsgHeaderPO msgHeader, String commendOID)
 {
     try
     {
         // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
         if(IntMsgUtil.isTransEBUS("BLCSSetRecommendSuccess"))
         {
            // ����Ϊ��ֵ�ԣ�����JSONObj
            Map<String,Object> outParamMap = new HashMap<String,Object>();
            JSONObject jsonObj = JSONObject.fromObject(outParamMap);
            
            return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
         }
         
         String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                 + "<menuid>qryBill</menuid><process_code>cli_busi_setRecommendSuccess</process_code><verify_code></verify_code>"
                 + "<resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq>"
                 + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                 + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                 + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                 + "</message_content>]]></message_body></message_response>";
         
         return intMsgUtil.parseResponse(response);
     }
     catch (Exception e)
     {
         ReturnWrap rtwp = new ReturnWrap();
         rtwp.setStatus(0);
         rtwp.setReturnMsg("");
         
         return rtwp;
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
            // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
            if(IntMsgUtil.isTransEBUS("BLCSGetFeedBackDef"))
            {
                Map<String,Object> outParamMap = new HashMap<String,Object>();
                
                outParamMap.put("actID", "bb");
                
                outParamMap.put("actinfo", "20121229;20121229;10005402818165|20000306880396");
                
                outParamMap.put("feedbackStatus", "1");
                
                outParamMap.put("isNeedFeedback", "0");
                
                outParamMap.put("isNotify", "1");
                
                outParamMap.put("moContent", "2");
                
                outParamMap.put("msgText", "Ӫ��");
                
                outParamMap.put("nCode", "");
                
                outParamMap.put("recmdName", "�����1");
                
                String[] arrAttrsKey = {"recmdName", "nCode", "actinfo", "moContent"};
                
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                
                list.add(outParamMap);
                JSONArray jsonObj = JSONArray.fromObject(outParamMap);
                
                
                return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,arrAttrsKey);
            }

            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>cli_qry_getFeedBackDefList</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                + "<feedbacklist><recmdname>�Ƽ�����</recmdname><ncode>�Ƽ�ncode</ncode><actinfo>�Ƽ����Ϣ</actinfo><mocontent>�ظ�����</mocontent></feedbacklist>"
                + "<feedbacklist><recmdname>recmdname001</recmdname><ncode>ncode001</ncode><actinfo>actinfo001</actinfo><mocontent>1</mocontent></feedbacklist>"
                + "<feedbacklist><recmdname>recmdname002</recmdname><ncode>ncode002</ncode><actinfo>actinfo002</actinfo><mocontent>1</mocontent></feedbacklist>"
                + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
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
            if(IntMsgUtil.isTransEBUS("BLCSRecommendProdByResponse"))
            {
                Map<String,Object> outParamMap = new HashMap<String,Object>();
                JSONObject jsonObj = JSONObject.fromObject(outParamMap);
                
                return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>qryBill</menuid><process_code>cli_busi_recommendProdByReply</process_code><verify_code></verify_code>"
                + "<resp_time>20100921004808</resp_time><sequence><req_seq>95</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }

    /**
     * ��ͨ�ɷ�
     * @param map
     * @return
     */
    public ReturnWrap wbankpay(Map<String, String> map, MsgHeaderPO msgHead)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><Envelope><Header><request_head version=\"1.0\">"
            + "<menuid>qryServiceHistory</menuid><process_code>cli_qry_rechistory</process_code>"
            + "<verify_code></verify_code><resp_time>20100921002233</resp_time>"
            + "<sequence><req_seq>40</req_seq><operation_seq></operation_seq></sequence><retinfo>"
            + "<rettype>0</rettype><retcode>100</retcode>"
            + "<retmsg><![CDATA[Processing the request succeeded!]]>"
            + "</retmsg></retinfo></request_head></Header>"
            + "<Body>"
            + "<cli_busi_wbandrecresp>"
            + "<tagset>"
            + "<dealNum>dealNum001</dealNum>"
            + "</tagset>"
            + "</cli_busi_wbandrecresp>"
            + "</Body></Envelope>";
        
        try {
            return intMsgUtilNew.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);

            return rtwp;
        }
    }
    /**
     * �����ն��˵�Эͬ��ѯ֮139email
     * @remark create yKF73963 2012-10-09 OR_HUB_201204_533 �����������ն˼�������ʵ�ֶ�����Эͬ��ѯ�˵�����
     */
     public ReturnWrap billColQuery139Email(Map map)
     {   String region=(String)map.get("region");
         String telnum = (String)map.get("SERVNUM");
         String qrytype="0";
         String sendtype="1";
         String triggertype="1";
         String channel="102";
         String modelid="he1002";
         String sendprior="01";
         String busicode="ngsendmail";
         String starttime=DateUtilHub._getCurrentTime();
         String statustime=DateUtilHub._getCurrentTime();
         String isqrybill="0";
         String cycle = (String)map.get("CYCLEMONTH");
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
             Element eReq_mailaddr = eBody.addElement("mailaddr");
             Element eReq_starttime = eBody.addElement("starttime");
             eReq_starttime.addText(starttime);
             Element eReq_statustime = eBody.addElement("statustime");
             eReq_statustime.addText(statustime);
             Element eReq_isqrybill = eBody.addElement("isqrybill");
             eReq_isqrybill.addText(isqrybill);
             Element eReq_cycle = eBody.addElement("cycle");
             eReq_cycle.addText(cycle);
             Element eReq_billinfo = eBody.addElement("billinfo");
             docXML = imu.createMsg(doc, "cli_sendbillby139mail_hub", menuID, touchOID, "1", telnum, operID, termID);
             
             return imu.invoke(docXML);
         }
         catch (Exception e)
         {
             ReturnWrap rtwp = new ReturnWrap();
             rtwp.setStatus(0);
             rtwp.setReturnMsg("");
             
             return rtwp;
         }
     }
     /**
      * �����ն��˵�Эͬ��ѯ֮����
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
              IntMsgUtil imu = new IntMsgUtil();
              Document docXML = null;
              
              Document doc = DocumentHelper.createDocument();
              Element eBody = doc.addElement("message_content");
              
              Element eReq_telnum = eBody.addElement("telnum");
              eReq_telnum.addText(telnum);
              
              Element eReq_billcycle = eBody.addElement("billcycle");
              eReq_billcycle.addText(billcycle);
              
              docXML = imu.createMsg(doc, "bsacSms_cli_sms_qry_bill2012", menuID, touchOID, "1", telnum, operID, termID);
              
              return imu.invoke(docXML);
          }
          catch (Exception e)
          {
              ReturnWrap rtwp = new ReturnWrap();
              rtwp.setStatus(0);
              rtwp.setReturnMsg("");
              
              return rtwp;
          }
      }
      /**
       * �����ն��˵�Эͬ��ѯ֮����
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
               IntMsgUtil imu = new IntMsgUtil();
               Document docXML = null;
               
               Document doc = DocumentHelper.createDocument();
               Element eBody = doc.addElement("message_content");
               
               Element eReq_telnum = eBody.addElement("telnum");
               eReq_telnum.addText(telnum);
               
               Element eReq_billcycle = eBody.addElement("billcycle");
               eReq_billcycle.addText(billcycle);
               
               docXML = imu.createMsg(doc, "cli_sms_qry_mmsbilldetail", menuID, touchOID, "1", telnum, operID, termID);
               
               return imu.invoke(docXML);
           }
           catch (Exception e)
           {
               ReturnWrap rtwp = new ReturnWrap();
               rtwp.setStatus(0);
               rtwp.setReturnMsg("");
               
               return rtwp;
           }
       }
       /**
        *��ѳ齱
        * @remark create yKF73963 ��2012-11-09�� OR_HUB_201209_706  ��������-�齱ƽ̨-�齱�ӿڸ��� 
        */
        public ReturnWrap mianFeiChouJiang(Map map)
        {   String actId=(String)map.get("ACTID");
            String telnum = (String)map.get("SERVNUM");
        
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
               
                Element eReq_telnum = eBody.addElement("TELNUM");
                eReq_telnum.addText(telnum);
              
                Element eReq_actId = eBody.addElement("ACTID");
                eReq_actId.addText(actId);
                docXML = imu.createMsg(doc, "cli_sendbillby139mail_hub", menuID, touchOID, "1", telnum, operID, termID);
                
                return imu.invoke(docXML);
            }
            catch (Exception e)
            {
                ReturnWrap rtwp = new ReturnWrap();
                rtwp.setStatus(0);
                rtwp.setReturnMsg("");
                
                return rtwp;
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
         * @remark create yKF73963��2013-03-18�� OR_HUB_201301_780  ����BOSS�����ֻ�֧������ȯ�ķ������͵����� 
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
                 //����
                 Element eReq_region = eBody.addElement("region");
                 eReq_region.addText(region);
                 Document docXML = intMsgUtil.createMsg(doc,
                         "cli_qry_ecashReturnInfo_hub",
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
                
                 
                 ReturnWrap rtwp = new ReturnWrap();
                 rtwp.setStatus(0);
                 rtwp.setReturnMsg("��ѯ����ȯ����������Ϣ�����쳣��");
                 
                 return rtwp;
             }
         }
         
         /** 
          * ������������
          * 
          * @param msgHeader ����ͷ��Ϣ
          * @param productset ��ͨ��ֵ��Ʒ��
          * @return ReturnWrap
          * @see [�ࡢ��#��������#��Ա]
          * @remark create cKF76106 2013-05-14 OR_HUB_201305_29
          */
         public ReturnWrap gprsWlanRec(MsgHeaderPO msgHeader, String productset)
         {
             try
             {
                 // ����ͳһ�ӿ�ƽ̨תEBUS���ؿ���
                 if (IntMsgUtil.isTransEBUS("BLCSChgOrChkMainProd"))
                 {
                     // ���÷���ӿ�
                     return getIntMsgUtil().parseJsonResponse("[{\"editStatus\":\"4\",\"effectTime\":\"2014-09-25 09:46:48\",\"entityID\":\"G211039\",\"objType\":\"PRODUCT\"},{\"editStatus\":\"0\",\"effectTime\":\"2014-09-25 09:46:48\",\"entityID\":\"G221772\",\"objType\":\"PRODUCT\"},{\"editStatus\":\"0\",\"effectTime\":\"2014-09-25 09:46:48\",\"entityID\":\"00.2010043001\",\"objType\":\"PRIVILEGE\"}]", 
                             null, null);
                 }
                 
                 String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
                        "<menuid>recMainProdChange</menuid><process_code>cli_busi_GprsWlanRec</process_code><verify_code>" +
                        "</verify_code><resp_time>20130514140538</resp_time><sequence><req_seq>1</req_seq><operation_seq>" +
                        "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" +
                        "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response> ";
                 return intMsgUtil.parseResponse(response);
             }
             catch (Exception e)
             {
                 ReturnWrap rtwp = new ReturnWrap();
                 rtwp.setStatus(0);
                 rtwp.setReturnMsg("");
                 
                 return rtwp;
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
                     return getIntMsgUtil().parseJsonResponse("{orderResult:'0',effetiTime:'2014-04-15',orderFlag:'1',formNumber:'20140115000001'}", 
                         new String[][]{{"orderResult", "formNumber"},{"orderresult", "formnum"}}, null);
                 }
                 
                 String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
                        "<menuid>recMainProdChange</menuid><process_code>cli_busi_GprsWlanRec</process_code><verify_code>" +
                        "</verify_code><resp_time>20130514140538</resp_time><sequence><req_seq>1</req_seq><operation_seq>" +
                        "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" +
                        "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response> ";
                 return intMsgUtil.parseResponse(response);
             }
             catch (Exception e)
             {
                 ReturnWrap rtwp = new ReturnWrap();
                 rtwp.setStatus(0);
                 rtwp.setReturnMsg("");
                 
                 return rtwp;
             }
         }
    
     /**
      * ���û���������������
      * 
      * @param map
      * @return
      */
     public ReturnWrap sendSmsHub(Map map) {
         ReturnWrap rtwp = new ReturnWrap();
         rtwp.setStatus(SSReturnCode.SUCCESS);

         return rtwp;
     }
     
    public IntMsgUtilNew getIntMsgUtilNew()
    {
        return intMsgUtilNew;
    }

    public void setIntMsgUtilNew(IntMsgUtilNew intMsgUtilNew)
    {
        this.intMsgUtilNew = intMsgUtilNew;
    }

    @Override
    public ReturnWrap getMonInvoiceData(MsgHeaderPO msgHeader, CyclePO cycle) {
        try
        {
            // ����תEBUS���ؿ���
            if (IntMsgUtil.isTransEBUS("BLARIntPrintAddValueBillInv"))
            {
                String response = "{'billInvoice':[{ 'invoiceInfo':'ȫ��ͨ:�������ƿء�Ʒλ'," 
                    + "'invoiceTypeItem':'productgrp'},{'invoiceInfo':'0','invoiceTypeItem': 'Score'},    "
                    + "{'invoiceInfo':'6��4��','invoiceTypeItem':'custnetage'}," 
                    + "{'invoiceInfo':'����13908686600','invoiceTypeItem':'username'},"
                    + "{'invoiceInfo':'13908686600(����)','invoiceTypeItem':'telnumber'},"
                    + "{'invoiceInfo':'7119000825245','invoiceTypeItem':'paynumno'}," 
                    + "{'invoiceInfo':'711140919406408357','invoiceTypeItem':'formnum'}," 
                    + "{'invoiceInfo':'����','invoiceTypeItem':'WorkStation'}," 
                    + "{'invoiceInfo':'101','invoiceTypeItem':'CollectOper'},"
                    + "{'invoiceInfo':'2014','invoiceTypeItem' : 'printyear'},"
                    + "{'invoiceInfo':'09','invoiceTypeItem':'printmonth'}," 
                    + "{'invoiceInfo':'19','invoiceTypeItem':'printday'},"
                    + "{'invoiceInfo':'2014��04�»���','invoiceTypeItem':'feetype'},"
                    + "{'invoiceInfo':'Ҽʰ��ԲҼ������','invoiceTypeItem':'invoicefeedx'},"
                    + "{'invoiceInfo':'13.14Ԫ','invoiceTypeItem':'invoicefee'},"
                    + "{'invoiceInfo':'ͨ�ŷ���ѣ�13.14Ԫ','invoiceTypeItem':'invoicefeehj'},"
                    + "{'invoiceInfo':'���η�Ʊ���: 13.14Ԫ','invoiceTypeItem':'thisinvamt'},"
                    + "{'invoiceInfo':'��ӡ��Ʊʱ�䣺','invoiceTypeItem':'hz_printtime'}," 
                    + "{'invoiceInfo':'09:50:39','invoiceTypeItem':'printtime'},"
                    + "{'invoiceInfo':'������Ʊ','invoiceTypeItem':'note'}]}";
                ReturnWrap rtwp = intMsgUtil.parseJsonResponse(response, null, new String[]{"invoiceTypeItem", "invoiceInfo"});
                if (SSReturnCode.SUCCESS == rtwp.getStatus())
                {
                    if (rtwp.getReturnObject() instanceof Vector)
                    {
                        Vector v = (Vector) rtwp.getReturnObject();
                        rtwp.setReturnObject((CRSet)v.get(1));
                    }
                }
                return rtwp;
            }
            // ��ȷ�ķ��ر���
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
                    "<message_response>" +
                    "<message_head version=\"1.0\">" +
                    "<menuid>recMonthInvoice</menuid>" +
                    "<process_code>cli_qry_monthinvoiceinfo_sd</process_code>" +
                    "<verify_code></verify_code>" +
                    "<resp_time>20140520191951</resp_time>" +
                    "<sequence>" +
                    "<req_seq>2</req_seq>" +
                    "<operation_seq></operation_seq>" +
                    "</sequence>" +
                    "<retinfo>" +
                    "<rettype>0</rettype>" +
                    "<retcode>0</retcode>" +
                    "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg>" +
                    "</retinfo>" +
                    "</message_head>" +
                    "<message_body>" +
                    "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
                    "<message_content>" +
                    "<invoicelist><name>productgrp</name><value>ȫ��ͨ:�������ƿء�Ʒλ</value></invoicelist>" +
                    "<invoicelist><name>Score</name><value>0</value></invoicelist>" +
                    "<invoicelist><name>username</name><value>�콣ƽ0612</value></invoicelist>" +
                    "<invoicelist><name>custnetage</name><value>11��9��</value></invoicelist>" +
                    "<invoicelist><name>telnumber</name><value>13972963980</value></invoicelist>" +
                    "<invoicelist><name>paynumno</name><value>7110005037647</value></invoicelist>" +
                    "<invoicelist><name>formnum</name><value>20091128448092</value></invoicelist>" +
                    "<invoicelist><name>WorkStation</name><value>����</value></invoicelist>" +
                    "<invoicelist><name>CollectOper</name><value>1301818</value></invoicelist>" +
                    "<invoicelist><name>printyear</name><value>2014</value></invoicelist>" +
                    "<invoicelist><name>printmonth</name><value>06</value></invoicelist>" +
                    "<invoicelist><name>printday</name><value>19</value></invoicelist>" +
                    "<invoicelist><name>feetype</name><value>2014��03�»���</value></invoicelist>" +
                    "<invoicelist><name>invoicefeedx</name><value>��ʰ��ԲҼ�Ƿ���</value></invoicelist>" +
                    "<invoicelist><name>invoicefee</name><value>88.12Ԫ</value></invoicelist>" +
                    "<invoicelist><name>invoicefeehj</name><value>ͨ�ŷ���ѣ�103.12Ԫ</value></invoicelist>" +
                    "<invoicelist><name>presentused</name><value>��������: 15.00Ԫ</value></invoicelist>" +
                    "<invoicelist><name>thisinvamt</name><value>���η�Ʊ���: 88.12Ԫ</value></invoicelist>" +
                    "<invoicelist><name>hz_printtime</name><value>��ӡ��Ʊʱ�䣺</value></invoicelist>" +
                    "<invoicelist><name>printtime</name><value>09:32:23</value></invoicelist>" +
                    "<invoicelist><name>note</name><value></value></invoicelist>" +
                    "</message_content>]]>" +
                    "</message_body></message_response>";
            
            // δ��ȫ���˵��쳣���ر���
            /*String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
                    "<message_response>" +
                    "<message_head version=\"1.0\">" +
                    "<menuid>recMonthInvoice</menuid>" +
                    "<process_code>cli_qry_monthinvoiceinfo_sd</process_code>" +
                    "<verify_code></verify_code>" +
                    "<resp_time>20140520152439</resp_time>" +
                    "<sequence><req_seq>4</req_seq><operation_seq></operation_seq></sequence>" +
                    "<retinfo>" +
                    "<rettype>0</rettype>" +
                    "<retcode>966391</retcode>" +
                    "<retmsg><![CDATA[�ʺţ�[6348888934481]�����ڣ�[20140101]���������˵�û����ȫ���ˣ����ܴ�ӡ�����ڷ�Ʊ��]]></retmsg>" +
                    "</retinfo>" +
                    "</message_head></message_response>";*/
            
            // �Ѵ�ӡ���ķ�Ʊ�쳣���ر���
            /*String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
                    "<message_response>" +
                    "<message_head version=\"1.0\">" +
                    "<menuid>recMonthInvoice</menuid>" +
                    "<process_code>cli_qry_monthinvoiceinfo_sd</process_code>" +
                    "<verify_code></verify_code>" +
                    "<resp_time>20140520154904</resp_time>" +
                    "<sequence>" +
                    "<req_seq>4</req_seq>" +
                    "<operation_seq></operation_seq>" +
                    "</sequence>" +
                    "<retinfo>" +
                    "<rettype>0</rettype>" +
                    "<retcode>966515</retcode>" +
                    "<retmsg><![CDATA[��ӡ�����Ѿ��ﵽ����ӡ�������븴λ���ٴδ�ӡ��]]></retmsg>" +
                    "</retinfo>" +
                    "</message_head></message_response>";*/
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
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
            String response = "{\"count\":0,\"ifValid\":\"1\"}";
            return intMsgUtil.parseJsonResponse(response, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"����ʱ֤������У��ӿڵ����쳣");
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
    public ReturnWrap qryTelNumberListByRule(MsgHeaderPO msgHeader, String orgId, String selTelRule,
        String telPrefix, String telSuffix, String mainProdid)
    {
        try
        {
            String response = "{\"retCode\":1,\"retMsg\":\"\",\"telNumlist\":[" +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655557169\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655557176\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655557075\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655557068\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556993\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556885\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556777\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556791\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556683\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556265\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556373\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556366\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556474\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556467\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556481\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556575\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556568\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556669\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555981\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555974\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555967\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556070\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556063\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556056\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556171\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556164\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556272\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555462\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555455\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555448\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555549\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555570\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555563\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555556\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555671\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555664\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555657\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555772\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555765\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555758\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555873\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555866\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655555859\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556676\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556784\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556892\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556878\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556986\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655556979\",\"telnumGrade\":\"2\"}," +
                    "{\"brand\":\"\",\"lowConsumFee\":\"0\",\"lowConsumPre\":\"0\",\"lowInserviceTime\":\"0\",\"orgid\":\"HB.EZ\",\"price\":\"0\",\"product\":\"\",\"telnum\":\"13655557082\",\"telnumGrade\":\"2\"}]}";
            
            String[] outParamKeyDefine = {"telnum","brand","price","orgid","lowConsumFee","lowConsumPre"};
            return intMsgUtil.parseJsonResponse(response, null, outParamKeyDefine);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"���Ͽ��ӿڵ����쳣");
        }
    }
    
    /** 
     * ������Դ��ѡ�ӿ�
     * 
     * @param msgHeader 
     * @param telNum �ֻ�����
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap telNumTmpPick(MsgHeaderPO msgHeader, Map<String,String> inParamMap)
    {
        try
        {
            return CommonUtil.getReturnWrap(SSReturnCode.SUCCESS,"������Դ��ѡ�ɹ�");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"������Դ��ѡ�ӿڵ����쳣");
        }
    }
    
    /** 
     * У��հ׿���Դ�Ƿ����
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @param blankNo �հ׿�����
     * @param orgId ��֯����
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chkBlankNo(MsgHeaderPO msgHeader, Map<String,String> inParamMap)
    {
        try
        {
            return CommonUtil.getReturnWrap(SSReturnCode.SUCCESS,"У��հ׿���Դ�Ƿ���óɹ�");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"У��հ׿���Դ�Ƿ���ýӿڵ����쳣");
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
            
            String response = "{\"busiStatus\":\"INVALID\",\"isPresetBlankCard\":\"TRUE\"," +
            		"\"orgID\":\"HB.EZ\",\"presetBlankCardType\":\"\",\"resTypeId\":\"rsclW.PSAM001\",\"retMsg\":\"\"}";
            String[][] outParamKeyDefine = {{"isPresetBlankCard","resTypeId"},{"isPresetBlankCard","resTypeId"}};
            return intMsgUtil.parseJsonResponse(response, outParamKeyDefine, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"У��հ׿��Ƿ���Ԥ�ÿտ��ӿڵ����쳣");
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
            String response = "{\"atr\":\"\",\"cardType\":\"\"," +
            		"\"eki\":\"FBA690E6944D405579134357B2C26C76\"," +
            		"\"iccID\":\"898600A1178407806261\",\"imsi\":\"460077178083738\"," +
            		"\"pin1\":\"1234\",\"pin2\":\"0731\",\"puk1\":\"28405510\",\"puk2\":\"41134844\"," +
            		"\"smsp\":\"+8613800711500\"}";
            return intMsgUtil.parseJsonResponse(response, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"�հ׿���Դ��ѡ�ӿڵ����쳣");
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
//    public ReturnWrap chkBlankNo(MsgHeaderPO msgHeader, Map<String,String> inParamMap)
//    {
//        try
//        {
//            return intMsgUtil.invokeDubbo("BLCSCheckSaleRealSign", msgHeader, inParamMap);
//        }
//        catch (Exception e)
//        {
//            
//            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"У��հ׿���Դ�Ƿ���ýӿڵ����쳣");
//        }
//    }
//
//    /** 
//     * �հ׿���Դ��ѡ
//     * 
//     * @param msgHeader ����ͷ��Ϣ
//     * @param blankNo �հ׿�����
//     * @param telNum �ֻ�����
//     * @return ReturnWrap
//     * @see [�ࡢ��#��������#��Ա]
//     */
//    public ReturnWrap blankCardTmpPick(MsgHeaderPO msgHeader, String blankNo, String telNum)
//    {
//        try
//        {
//            // ���
//            Map<String,String> inParamMap = new HashMap<String,String>();
//            
//            // �հ׿���
//            inParamMap.put("blankCardNo", blankNo);
//            
//            // �ֻ�����
//            inParamMap.put("telNum", telNum);
//            
//            return intMsgUtil.invokeDubbo("BLCSGetPersonalData", msgHeader, inParamMap);
//        }
//        catch (Exception e)
//        {
//            
//            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"�հ׿���Դ��ѡ�����쳣");
//        }
//
//    }
//    
//    /** 
//     * У��հ׿��Ƿ���Ԥ�ÿտ�
//     * 
//     * @param termInfo �ն���Ϣ
//     * @param curMenuId ��ǰ�˵�ID
//     * @param blankNo �հ׿����к�
//     * @param telNum �ֻ�����
//     * @see [�ࡢ��#��������#��Ա]
//     */
//    public ReturnWrap chkPreSetBlankCard(MsgHeaderPO msgHeader, String blankNo, String telNum)
//    {
//        try
//        {
//            Map<String,String> inParamMap = new HashMap<String,String>();
//            
//            // �հ׿���
//            inParamMap.put("blankCardNo", blankNo);
//            
//            // �ֻ���
//            inParamMap.put("severNumber", telNum);
//            
//            // �Ƿ񷵻�Ԥ�����ݣ����Դ�0�������0���������presetData�ǿյģ�
//            inParamMap.put("retPreSetData", "0");
//            
//            // Ԥ�ÿտ���Ȩ���ݴ���ţ����գ�
//            inParamMap.put("dataSeq", "");
//
//            // �Ƿ���Ԥ�ÿտ����հ׿���Դ
//            String[][] outParamKeyDefine = {{"isPresetBlankCard","resTypeId"},{"isPresetBlankCard","resTypeId"}};
//            
//            return intMsgUtil.invokeDubbo("BLCSChkPreSetBlankCard", msgHeader, inParamMap,outParamKeyDefine);
//        }
//        catch (Exception e)
//        {
//            
//            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"У��հ׿��Ƿ���Ԥ�ÿտ��ӿڵ����쳣");
//        }
//    }
    
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
            if (IntMsgUtil.isTransEBUS("BLARBillCycleCustInfo"))
            {
                String response = "{'cycList':[{'acctID':'','brandName':'ȫ��ͨ',"
                    + "'custName':'����test','cycle':'201407','endDate':'2014-07-31',"
                    + "'isUnion':'0','productName':'4G�����ײ�338Ԫ��','region':'711'," 
                    + "'regionName':'����','startDate':'2014-07-01','subsCredit':'-1'," 
                    + "'subsID':'7115183834310','unSubsNum':0}],'errCode':'0'}";
                return intMsgUtil.parseJsonResponse(response, null, 
                        new String[]{"cycle","startDate","endDate","acctID","isUnion"});
            }
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>recMonthInvoice</menuid><process_code>cli_qry_billCycleCustInfo</process_code><verify_code></verify_code>"
                + "<resp_time>20100921004740</resp_time><sequence><req_seq>89</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                
                // �ͻ�����
                + "<custname>��ǿ</custname>"
                
                // Ʒ��
                + "<brandnm>������</brandnm>"
                
                // �����Ʒ
                + "<productnm>�����Ʒ</productnm>"
                
                // ��������
                + "<regionname>ɽ��</regionname>"
                
                // �û�ID
                + "<userid>10000000000001</userid>"
                
                // �û��ȼ�
                + "<creditlevel>3</creditlevel>"
                
                // ������Ϣ 
                
                // ����+��ʼʱ��+����ʱ��+���˺�+�Ƿ�ϻ��û�
                + "<cyclelist><cycle>20120601</cycle><startdate>2012-06-01</startdate><enddate>2012-06-30</enddate><acctid>10000001</acctid><unionacct>1</unionacct><unionacct>2</unionacct></cyclelist>"
                
                + "</message_content>]]></message_body></message_response>";
                
            ReturnWrap rtwp = null;   
        
            rtwp = intMsgUtil.parseResponse(response);
            return rtwp;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ����ʱ�����ֻ����������֤��Ϣ�Ƿ�һ��
     * @param msgHeader
     * @param idCardNo
     * @return
     * @remark create by sWX219697 2014-10-25 13:59:39 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap checkReissueIdCard(MsgHeaderPO msgHeader, String idCardNo) 
    {
        // ����Ϊ��ֵ�ԣ�����JSONObj
        Map<String,Object> outParamMap = new HashMap<String,Object>();

        // ƴװ����
        outParamMap.put("ifValid", "1");
        
        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        
        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(), null, null);
    }

    /**
     * У���û��Ĳ�������
     * @param msgHeader
     * @return
     * @remark create by sWX219697 2014-10-25 14:12:49 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap checkReissueNum(MsgHeaderPO msgHeader, String subscriber) 
    {
        // ����Ϊ��ֵ�ԣ�����JSONObj
        Map<String,Object> outParamMap = new HashMap<String,Object>();

        // ƴװ����
        outParamMap.put("retInfo", "true");
        
        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        
        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(), null, null);
    }
    
    /**
     * ��ȡд����Ϣ��������
     * @param msgHead ������Ϣͷ
     * @param inParamMap ���
     * @return ReturnWrap
     */
    public ReturnWrap getEncryptedData(MsgHeaderPO msgHead,Map<String,Object> inParamMap)
    {
        try
        {
            Map<String,Object> outParamMap = new HashMap<String,Object>();
            
            outParamMap.put("issueData", "ABCABCABCCCCCC");
            
            JSONObject jsonObj = JSONObject.fromObject(outParamMap);
            
            return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"��ȡд����Ϣ���������쳣");
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
    	String response = "[{\"chargeItemID\":\"Prepay635620417\",\"chargePrivID\":\"\"," +
    			"\"chargeProdID\":\"\",\"chargeRequest\":\"\",\"chargeType\":0,\"discPrivID\":\"\"," +
    			"\"discount\":0,\"endDate\":\"\",\"entityID\":\"7110000000555\",\"entityType\":\"CSubscriber\"," +
    			"\"extTag\":{},\"fee\":50,\"feeID\":\"Prepay\",\"feeName\":\"����Ԥ��\",\"fromSubjectID\":\"\"," +
    			"\"oid\":\"20089219053152\",\"prePayTagParam\":{},\"remitType\":\"\",\"startDate\":\"\"," +
    			"\"subjectID\":\"acsbAdv\",\"subjectOid\":\"\",\"subsPrivOid\":\"\"," +
    			"\"toBillCode\":\"\"},{\"chargeItemID\":\"InsSimReUse\",\"chargePrivID\":\"\"," +
    			"\"chargeProdID\":\"\",\"chargeRequest\":\"\",\"chargeType\":0,\"discPrivID\":\"\"," +
    			"\"discount\":0,\"endDate\":\"2014-11-10 11:45:02\",\"entityID\":\"7110000000555\"," +
    			"\"entityType\":\"CSubscriber\",\"extTag\":{\"cqRwdActChargeProdID\":\"\"," +
    			"\"endResID\":\"17711000000000000946\",\"firstDisc\":\"0\",\"quantity\":\"1\"," +
    			"\"resTypeID\":\"rsclW.PSAM001\",\"startResID\":\"17711000000000000946\"," +
    			"\"tgchgOffset\":\"0\"},\"fee\":100,\"feeID\":\"SIM\",\"feeName\":\"�Ϻ�����ר�ÿ�(װ��)\"," +
    			"\"fromSubjectID\":\"\",\"oid\":\"20089219053162\",\"prePayTagParam\":{}," +
    			"\"remitType\":\"\",\"startDate\":\"2014-11-10 11:45:02\",\"subjectID\":\"\"," +
    			"\"subjectOid\":\"\",\"subsPrivOid\":\"\",\"toBillCode\":\"\"}]";
    	
    	String[] str = {"fee","feeName","chargeType"};
    	
    	return getIntMsgUtil().parseJsonResponse(response,null,str);
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
    public ReturnWrap reissueCommit(MsgHeaderPO msgHeader, String recFee, String payType, 
            String blankno, SimInfoPO simInfo)
    {
        Map<String,Object> outParamMap = new HashMap<String,Object>();
        
        //��ˮ��
        outParamMap.put("recOid", "123455666646");
        
        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        
        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
    }
    
    /**
     * �հ׿�����
     * @param msgHead ������Ϣͷ
     * @param map ���
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap terminalInstall(MsgHeaderPO msgHead,Map<String, String> inParamMap)
    {
        try
        {
            Map<String,Object> outParamMap = new HashMap<String,Object>();
            
            // ������ˮ
            outParamMap.put("installFormNum", "20141031102035");
            
            // ҵ��OID
            outParamMap.put("oid", "20141031102035");
            
            JSONObject jsonObj = JSONObject.fromObject(outParamMap);
            
            return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"Ԥ�ÿհ׿������쳣");
        }
    }
    
    /**
     * ���Ͽ��ӿ�
     * @param msgHead ������Ϣͷ
     * @param map ���
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap updateWriteCardResult(MsgHeaderPO msgHead,Map<String, String> inParamMap)
    {
        try
        {
            return CommonUtil.getReturnWrap(SSReturnCode.SUCCESS,"���Ͽ��ӿڵ��óɹ�");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"���Ͽ��ӿڵ����쳣");
        }
    }
    
    /**
     * У��д������ӿ�
     * @param msgHead ������Ϣͷ
     * @param map ���
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-30 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap checkWriteCardInfo(MsgHeaderPO msgHead,Map<String, String> inParamMap)
    {
        try
        {
            Map<String,Object> outParamMap = new HashMap<String,Object>();
            
            // У���� 0���ɹ� 1��ʧ��
            outParamMap.put("result", "0");
            
            JSONObject jsonObj = JSONObject.fromObject(outParamMap);
            System.out.println(jsonObj.toString());
            return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);  
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"У��д������ӿڵ����쳣");
        }
    }
    
    /**
     * ���㿪������
     * @param msgHead ������Ϣͷ
     * @param map ���
     * @return ReturnWrap
     * @remark create by hWX5316476 2014-10-31 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap reckonRecFee(MsgHeaderPO msgHead,Map<String, String> inParamMap)
    {
        try
        {
            Map<String, Object> outParamMap = new HashMap<String, Object>();
            Map<String, String> inParam = new HashMap<String, String>();
            List feelist = new ArrayList();
            inParam.put("feeName","�������û�����Ԥ����");
            inParam.put("fee", "1000");
            inParam.put("quantity", "1");
            inParam.put("feeID", "������");
            inParam.put("discount", "200");
            inParam.put("realfee", "800");
            
            feelist.add(inParam);
            outParamMap.put("feelist", feelist);
            JSONObject jsonObj = JSONObject.fromObject(outParamMap);
            String[] str = {"feeName","fee","quantity","feeID","discount","realfee"};
            return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,str);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"���㿪�����ýӿڵ����쳣");
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
	    	//CTagSet
//            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
//                +"<menuid>openAccount</menuid><process_code>cli_qryStoreCard_nx</process_code><verify_code></verify_code>"
//                +"<resp_time>20140305180720</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence>"
//                +"<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
//                +"</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
//                +"<subsID>1</subsID><region>1</region><iccID>1</iccID><startDate>1</startDate><endDate>1</endDate><activeDate>1</activeDate>"
//                +"<applyOID>1</applyOID><cancleOID>1</cancleOID></message_content>]]></message_body></message_response>";
//            
//            return intMsgUtil.parseResponse(response);
            
	    	//CRSet
//			String response = "[{startDate:'',endDate:'',subsID:'',iccID:''}]";
//			return getIntMsgUtil().parseJsonResponse(response, null, null);
			
			ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(SSReturnCode.SUCCESS);

            return rtwp;
	    	
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * <�������>
     * <������ϸ����>
     * @param msgHeader
     * @param iccid
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by c00233019 2014-10-28 16:45:41 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap reckonrecfeeByStore(MsgHeaderPO msgHeader, String servnum, String iccid)
    {
    	try 
	    {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                +"<menuid>openAccount</menuid><process_code>cli_qryStoreCard_nx</process_code><verify_code></verify_code>"
                +"<resp_time>20140305180720</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence>"
                +"<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
                +"</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                +"<totalFee>10</totalFee></message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtwp = new ReturnWrap();
            rtwp.setStatus(0);
            rtwp.setReturnMsg("");
            
            return rtwp;
        }
    }
    
    /**
     * <�����ύ>
     * <������ϸ����>
     * @param msgHeader
     * @param servnum �ֻ�����
     * @param iccid iccid 
     * @param tMoney �û�Ͷ����     
     * @param payType ֧����ʽ
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by c00233019 2014-10-28 16:45:41 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap prepareCashCommit(MsgHeaderPO msgHeader, String servnum, String iccid, String tMoney, String payType)
    {
        Map<String,Object> outParamMap = new HashMap<String,Object>();
        
        //��ˮ��
        outParamMap.put("formNum", "12345677777777777777");
        
        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        
        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
    }
    
    /**
     * <��ѯ�û���Ϣ>
     * <������ϸ����>
     * @param msgHeader
     * @param region
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2014-11-6 18:13:32 OR_HUB_201405_478_����_���������ն�֧���ֳ�д_��������(�հ׿�����)
     */
    public ReturnWrap getSubscriberByTel(MsgHeaderPO msgHeader,String region)
    {
        String response = "{\"qryResult\":1,\"subscriber\":{\"BOType\":\"CSubscriber\",\"acctID\":\"7115179983706\"," +
                "\"active\":1,\"areaID\":\"HB.EZ.02.08.02\",\"authType\":\"AuthCheckB\",\"blankCardNo\":\"\"," +
                "\"brand\":\"BrandSzx\",\"controlModal\":\"4\",\"countyID\":\"HB.EZ.02\"," +
                "\"createDate\":\"2011-08-07 15:22:01\",\"credit\":0,\"creditStatus\":\"0\"," +
                "\"currentCustMgr\":\"\",\"custID\":\"7116104535428\",\"delayTime\":0,\"dummySubs\":1," +
                "\"editStatus\":1,\"enum\":\"89860097174121982474\",\"iccID\":\"89860097174121982474\"," +
                "\"imsi\":\"460001984747525\",\"invalidDate\":\"\",\"isSingle\":0,\"langType\":\"Chinese\"," +
                "\"mobileType\":\"mbtpNml\",\"netType\":\"GSM\",\"notes\":\"\",\"ownerOrgID\":\"HB.EZ.02.08.02\"," +
                "\"password\":\"123456\",\"payMode\":\"\",\"platType\":0,\"proStatus\":\"\",\"proStopKey\":\"\"," +
                "\"prodID\":\"MP7110503000163\",\"pzFlag\":0,\"region\":\"711\",\"registerOrgID\":\"HB.EZ.02.08.02\"," +
                "\"relateSubsID\":\"0\",\"responseCustMgr\":\"\",\"score\":0,\"servNumber\":\"15871503875\"," +
                "\"settleDay\":1,\"signType\":\"sbsnInst\",\"srvNum\":\"15871503875\"," +
                "\"startDate\":\"2011-08-07 15:22:01\",\"status\":\"US10\"," +
                "\"statusDate\":\"2014-04-25 09:55:21\",\"stopKey\":\"00000000\"," +
                "\"stopType\":2,\"subsID\":\"7115179964250\",\"subsName\":\"��7115179964250\"," +
                "\"subsType\":\"Person\",\"unFinish\":\"\",\"urgeType\":\"ugtpUS\"," +
                "\"userID\":\"7116104535428\",\"vipCardID\":\"\",\"vipCardType\":\"\"," +
                "\"vipType\":\"VC0000\"}}";
        
        return getIntMsgUtil().parseJsonResponse(response,new String[][]{{"qryResult", "subscriber"},{"qryResult", "subscriber"}},null);
    }

    @Override
    public ReturnWrap isRealName(MsgHeaderPO msgHeader)
    {
        Map<String,Object> outParamMap = new HashMap<String,Object>();
        //��ˮ��
        outParamMap.put("isrealName", "1");
        
        JSONObject jsonObj = JSONObject.fromObject(outParamMap);
        
        return getIntMsgUtil().parseJsonResponse(jsonObj.toString(),null,null);
    }

    @Override
    public ReturnWrap broadBandAppoint(String currArea,String installDate, String iDcard, String currProd, String band, MsgHeaderPO msgHeader)
    {
        ReturnWrap rtwp = new ReturnWrap();
        rtwp.setStatus(SSReturnCode.SUCCESS);
        return rtwp;
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
    		String response = "{\"credit\":\"3.00Ԫ\",\"curFee\":\"0.00\"," +
    				"\"leftBalance\":\"259.00\",\"subjectListInfo\":[{\"categories\":\"��ͨ���\"," +
    				"\"consumption\":\"25900\",\"destroy\":\"\",\"range\":\"��\",\"subject\":\"�ֽ��Ŀ\"," +
    				"\"validity\":\"������Ч\"}]}";
    		String[] outParam = {"categories", "subject", "range", "consumption", "validity", "destroy"};
    		return getIntMsgUtil().parseJsonResponse(response, null, outParam);
    	}
    	catch(Exception e)
    	{
    		return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��ѯ�����ϸ�ӿڵ����쳣");
    	}
    }
    
    /**
     * ����֧������ӿ�
     * @param customerSimp
     * @param curMenuId
     * @param termInfo
     * @param isTelNum
     * @param currNumber
     * @param selfPayLog
     * @return
     * @remark create by lwx439898 2017-10-16 OR_HUB_201709_456_����_֧��������Ҫ �����ն���Ͻӿڸ���
     */
    public ReturnWrap createPayCenterTrans(NserCustomerSimp customerSimp, String curMenuId, TerminalInfoPO termInfo,
            String isTelNum, String currNumber, Map<String, String> selfPayLog)
    {
        try
        {       
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>arcreateunionpaytask</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                + "<taskoid>711123456789</taskoid>"
                + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "����֧������ӿ��쳣");
        }
    }    
    
    /**
     * ��������֧��״̬
     * @param payTransMsg
     * @param payCenterParam
     * @return
     */
    public ReturnWrap updateBankPaymentStatus(String payCenterParamInfo,String respMsg,String payTransMsg,TerminalInfoPO termInfo,String curMenuId,String currNumber)
    {
        try
        {         
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>arcreateunionpaytask</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��������֧��״̬�쳣");
        }       
    }
    
    public ReturnWrap qryPayChargeInfo(TerminalInfoPO termInfo, String taskoid, String currNumber, String curMenuId)
    {
        try
        {       
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>arcreateunionpaytask</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                + "<taskoid>711123456789</taskoid><region>711</region><servnumber>13688883412</servnumber><bankcode></bankcode><bankseqno></bankseqno><bankstatus>1</bankstatus><paytype>PayCenter</paytype>"
                + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "����֧������ӿ��쳣");
        }
    }
    
    public ReturnWrap updateBankRecoid(TerminalInfoPO termInfo,String currNumber,String curMenuId,String taskoid,String recoid,String status)
    {
        try
        {       
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>arcreateunionpaytask</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "���±��쳣"); 
        }
    }
}
