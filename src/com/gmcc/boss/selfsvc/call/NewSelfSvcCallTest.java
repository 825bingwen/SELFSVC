/*
 * �ļ�����SelfSvcCallTest.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ��������������ʹ�õĽӿڵ���ʵ����
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ�����
 * 
 */
package com.gmcc.boss.selfsvc.call;

import com.customize.sd.selfsvc.po.NcodePO;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.common.cbo.global.cbo.common.CRSet;
import com.gmcc.boss.common.cbo.global.cbo.common.CTagSet;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.common.MsgHeaderPO;
import com.gmcc.boss.selfsvc.common.SSReturnCode;
import com.gmcc.boss.selfsvc.quickpublish.model.MultiProdCommitPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;
import org.apache.axiom.om.OMElement;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * ��������ʹ��
 * 
 * @author g00140516
 * 
 */
public class NewSelfSvcCallTest implements SelfSvcCall {
    private IntMsgUtil intMsgUtil;

    private SocketUtil socketUtil;
    
    private IntMsgUtilNew intMsgUtilNew;

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
     * ʹ���ֻ����롢�����������������֤
     */
    public ReturnWrap getUserInfoWithPwd(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>cli_qry_userinfo</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><subname>��Ⱥ</subname>"
                + "<region>711</region><regionname></regionname><productid>pp.gt.ygtch.634</productid>"
                + "<productname></productname><productgroup></productgroup><viptype></viptype>"
                + "<productname>ȫ��ͨ</productname><productgroup>BrandMzone</productgroup><viptype>VC0000</viptype>"
                + "<logintype></logintype><feeflag></feeflag><question></question><answer></answer><needcheckstr>"
                + "</needcheckstr><contactid></contactid><status>US22</status><nettype></nettype><subscore>600</subscore><smallregion>7</smallregion>" 
                +"<signtype>sbsnTransTelIn</signtype></message_content>]]></message_body></message_response>";

        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rwp;
    }

    /**
     * �������¼
     */
    public ReturnWrap getUserInfo(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>cli_qry_userinfo</process_code><verify_code>verifyCode</verify_code>"
                + "<unicontact>unicontact</unicontact><resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><subname>��Ⱥ</subname>"
                + "<region>634</region><regionname></regionname><productid>pp.gt.ygtch.634</productid>"
                + "<productname></productname><productgroup></productgroup><viptype></viptype>"
                + "<productname>ȫ��ͨ</productname><productgroup>BrandSzx</productgroup><viptype>VC0000</viptype>"
                + "<logintype></logintype><feeflag></feeflag><question></question><answer></answer><needcheckstr>"
                + "</needcheckstr><status>US22</status><nettype></nettype><subage>2011-05-08</subage><subscore>600</subscore><smallregion></smallregion>" 
                + "<signtype>sbsnTransTelIn</signtype></message_content>]]></message_body></message_response>";

        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rwp;
    }

    /**
     * ���û���������������
     * 
     * @param map
     * @return
     */
    public ReturnWrap sendSMS(Map map) {
        ReturnWrap rwp = new ReturnWrap();
        rwp.setStatus(SSReturnCode.SUCCESS);

        return rwp;
    }

    public ReturnWrap queryMonthDeduct(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>qryBill</menuid><process_code>cli_qry_yckf</process_code><verify_code></verify_code><resp_time>20100921004855</resp_time><sequence><req_seq>97</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><sum_funcfee>300</sum_funcfee><sum_packagefee>2600</sum_packagefee><sum_basefee>0</sum_basefee><feeitem><type>2</type><name>1Ԫ������������л�����0.05Ԫ/����</name><fee>100</fee><time>09.12</time></feeitem><feeitem><type>2</type><name>20Ԫ��200���ӱ��ع��ڳ�;</name><fee>2000</fee><time>09.04</time></feeitem><feeitem><type>2</type><name>GPRS�ײ� -5Ԫ������30M������0.01Ԫ/K</name><fee>500</fee><time>09.04</time></feeitem><feeitem><type>1</type><name>�ƶ�ȫʱͨ��</name><fee>300</fee><time>09.02</time></feeitem></message_content>]]></message_body></message_response>";

        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Vector vector = null;
        if (rwp != null) {
            vector = (Vector) rwp.getReturnObject();
        }

        Vector retVector = new Vector();
        if (vector != null && vector.size() == 2) {
            CTagSet tagset = (CTagSet) vector.get(0);
            CRSet crset = (CRSet) vector.get(1);

            List listBaseFee = null;// //�ײ���Ϣ��
            List listFuncFee = null;// //���ܷ�
            List listSumPackage = null;// ���·�

            if ((crset != null) && (crset.GetRowCount() > 0)) {
                // �ײ���Ϣ��
                if (!"0".equals(tagset.GetValue("sum_basefee"))) {
                    listBaseFee = getMonthFeeList(crset, "3");
                }
                // ���ܷ�
                if (!"0".equals(tagset.GetValue("sum_funcfee"))) {
                    listFuncFee = getMonthFeeList(crset, "1");
                }
                // ���·�
                if (!"0".equals(tagset.GetValue("sum_packagefee"))) {
                    listSumPackage = getMonthFeeList(crset, "2");
                }
            }
            retVector.add(listBaseFee);
            retVector.add(listFuncFee);
            retVector.add(listSumPackage);
            rwp.setReturnObject(retVector);
        }

        return rwp;
    }

    /**
     * ���ܷѡ����·ѡ��ײ���Ϣ�� <������ϸ����>
     * 
     * @param crset
     * @param typeStr
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<List> getMonthFeeList(CRSet crset, String typeStr) {

        // ����list
        List<List> feeList = new ArrayList<List>();

        for (int i = 0; i < crset.GetRowCount(); i++) {
            String type = crset.GetValue(i, 0) == null ? "" : crset.GetValue(i,
                    0);
            if (typeStr.equals(type)) {
                List<String> list = new ArrayList<String>();

                list.add(crset.GetValue(i, 1));
                list.add(CommonUtil.fenToYuan(crset.GetValue(i, 2)));
                list.add(CommonUtil.formatDate(crset.GetValue(i, 3), "MM.dd",
                        "MM��dd��"));

                feeList.add(list);
            }
        }
        return feeList;
    }

    /**
     * ����Ʒ���ʷѼ��ѿ�ͨ����
     */
    public ReturnWrap qryFavourable(Map map) {
        // ��ѯ����
        String queryType = (String) map.get("queryType");

        // ��ȷ
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>qryFavourable</menuid><process_code>cli_qry_zfinfo</process_code>"
                + "<verify_code></verify_code><resp_time>20100921090605</resp_time><sequence><req_seq>23</req_seq>"
                + "<operation_seq></operation_seq></sequence><retinfo>"
                + "<rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
                + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                + "<message_content>"
                + "<info><type>ShowByPrivRate</type><name>������09��˼�翨Ʒ���Ż�[��]</name><desp>0���⣬������ʾ��0.1Ԫ/�졣ͨ����:�л�����0.25Ԫ/���ӣ����ر������,��;��0.25Ԫ/���ӣ����η�����0.6Ԫ/���ӡ�����0.4Ԫ/����</desp><start_time>20090401000000</start_time><end_time></end_time></info>"
                + "<info><type>ShowByPrivRate</type><name>˼������</name><desp>1Ԫ/�£�ָ��һ������������У��ڱ��ز���ó��е绰����;ͨ����ÿ����0.20Ԫ��������������ȡ1Ԫ����������������������Ч���ͣ���Ϊ������Ч</desp><start_time>20090401000000</start_time><end_time></end_time></info>"
                + "<info><type>ShowByProdRate</type><name>������ʾ</name><desp>���к�����ʾ</desp><start_time>20060205000000</start_time><end_time></end_time></info><info><type>ShowByProdRate</type><name>�������ι���</name><desp>��ͨ�������ι��ܿ�����ȫ����Χ��ʹ��</desp><start_time>20060205000000</start_time><end_time></end_time></info>"
                + "<info><type>ShowByProdRate</type><name>09��˼�翨��ѡ��[��]</name><desp>��ѡ��</desp><start_time>20090401000000</start_time><end_time></end_time></info><info><type>ShowByProdRate</type><name>���Ź���</name><desp>����ҵ���⹦�ܷ�</desp><start_time>20071123145754</start_time><end_time></end_time></info><info><type>ShowByProdRate</type><name>5Ԫ��25Mʡ��GPRS����</name><desp>������</desp><start_time>20090501000000</start_time><end_time></end_time></info>"
                + "</message_content>]]></message_body></message_response>";
        // ����
        // String response = "<?xml version=\"1.0\" encoding=\"GBK\"
        // ?><message_response><message_head version=\"1.0\">" +
        // "<menuid>qryFavourable</menuid><process_code>cli_qry_zfinfo</process_code>"
        // +
        // "<verify_code></verify_code>" +
        // "<resp_time>20110620144828</resp_time><" +
        // "sequence><req_seq>14</req_seq><operation_seq></operation_seq>" +
        // "</sequence><retinfo><rettype>0</rettype><retcode>999</retcode>" +
        // "<retmsg><![CDATA[BUSINESSID:QrySubsFeeInfo_Atsv,�м������ʧ�ܣ�EUniCall�׳��쳣,ErrorID
        // -1 ErrorMsg EXCEPTION 'ETuxedo': Destination service not found:
        // IntCmd:CscGetSetInfo, IntSubCmd:, IntID:bsacAtsv,
        // Region:711]]></retmsg></retinfo></message_head>" +
        // "</message_response>";

        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rwp;

    }

    /**
     * PUK���ѯ
     * 
     * @return
     */
    public ReturnWrap queryPUK(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>qryPukQry</menuid>"
                + "<process_code>cli_qry_pukcode</process_code><verify_code></verify_code>"
                + "<resp_time>20100921090638</resp_time><sequence><req_seq>25</req_seq>"
                + "<operation_seq></operation_seq></sequence>"
                + "<retinfo><rettype>0</rettype><retcode>100</retcode>"
                + "<retmsg><![CDATA[Processing the request succeeded!]]><"
                + "/retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                + "<message_content>"
                + "<pin1>1234</pin1>"
                + "<pin2>0167</pin2>"
                + "<puk1>07577572</puk1>"
                + "<puk2>19639147</puk2>"
                + "</message_content>]]></message_body></message_response>";
        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rwp;
    }

    public void setIntMsgUtil(IntMsgUtil intMsgUtil) {
        this.intMsgUtil = intMsgUtil;
    }

    /**
     * ����״̬��ѯ
     */
    public ReturnWrap queryCurrentStatus(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                + "<message_response><message_head version=\"1.0\">"
                + "<menuid>qryService</menuid>"
                + "<process_code>cli_qry_userstate</process_code>"
                + "<verify_code></verify_code>"
                + "<resp_time>20100921002201</resp_time>"
                + "<sequence>"
                + "<req_seq>38</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
                + "<retmsg><![CDATA[Processing the request succeeded!]]>"
                + "</retmsg></retinfo>" + "</message_head>" + "<message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                + "<message_content>" + "<state>��ʹ��</state>"
                + "</message_content>]]>"
                + "</message_body></message_response>";
        
        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rwp;
    }

    /**
     * ��������ز�ѯ
     */
    public ReturnWrap queryUserRegionReq(Map map) {

        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>qryBillItem</menuid><process_code>cli_qry_bill</process_code><verify_code></verify_code>"
                + "<resp_time>20100921004740</resp_time><sequence><req_seq>89</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                + "<message_content>"
                + "<provname>ɽ��</provname>"
                + "<cityname>����</cityname>"
                + "<region>531</region>"
                + "</message_content>]]></message_body></message_response>";

        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rwp;
    }

    /**
     * �굥��ѯ
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public ReturnWrap queryCDRList(Map map) {
        try {
            String cdrType = (String) map.get("CDRType");

            String response = "";

            String province = (String) PublicCache.getInstance().getCachedData(
                    Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_NX.equals(province)) {
                if ("1".equals(cdrType)) {
                    response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
                            + "<message_head version=\"1.0\"><menuid></menuid><process_code>cli_qry_cdr</process_code>"
                            + "<verify_code></verify_code><resp_time>20110619115611</resp_time><sequence>"
                            + "<req_seq>28</req_seq><operation_seq></operation_seq></sequence><retinfo>"
                            + "<rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
                            + "</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                            + "<message_content><cust_name></cust_name><product_name></product_name><product_info></product_info>"
                            + "<createdate></createdate><total_fee></total_fee><telnum>13895600783</telnum><rowcount>63</rowcount>"
                            + "<billitem><bill>gprs~559941~2011-06-18 00:36:28~9425336~��������~0.00</bill></billitem>"
                            + "<billitem><bill>gprs~0~2011-06-18 01:36:29~0~��������~0.00</bill></billitem>"
                            +
                            // "<billitem><bill>����~18795203778~2011-06-18
                            // 10:28:42~111~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~18795203778~2011-06-18
                            // 10:45:31~107~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~18795203778~2011-06-18
                            // 11:08:57~15~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~13895389498~2011-06-18
                            // 11:34:23~42~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~13895389498~2011-06-18
                            // 11:37:18~16~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~18795203778~2011-06-18
                            // 14:23:25~80~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~13909590065~2011-06-18
                            // 17:03:16~45~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~15109577632~2011-06-18
                            // 17:05:17~45~��������~0.22</bill></billitem>" +
                            // "<billitem><bill>����~13629580286~2011-06-18
                            // 17:06:29~212~��������~0.88</bill></billitem>" +
                            // "<billitem><bill>����~13895176008~2011-06-18
                            // 17:10:14~63~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~15109577632~2011-06-18
                            // 17:11:38~65~��������~0.44</bill></billitem>" +
                            // "<billitem><bill>����~13909590065~2011-06-18
                            // 17:13:28~58~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~13895176008~2011-06-18
                            // 17:17:01~69~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~15825316020~2011-06-18
                            // 17:24:17~69~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~13909590065~2011-06-18
                            // 17:30:47~42~��������~0.22</bill></billitem>" +
                            "<billitem><bill>����~13909590065~2011-06-18 17:34:39~59~��������~0.22</bill></billitem>"
                            + "<billitem><bill>��������~13895176008~2011-06-18 17:45:44~0~��������~0.10</bill></billitem>"
                            + "<billitem><bill>����~13895389498~2011-06-18 17:47:38~144~��������~0.00</bill></billitem>"
                            +
                            // "<billitem><bill>��������~13895389498~2011-06-18
                            // 18:03:40~0~��������~0.10</bill></billitem>" +
                            // "<billitem><bill>����~13895675851~2011-06-18
                            // 20:44:49~67~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~3057~2011-06-18
                            // 21:06:52~66582~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~716~2011-06-18
                            // 21:07:15~1543~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~13895389498~2011-06-18
                            // 21:31:17~32~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~18795203778~2011-06-18
                            // 21:33:04~10~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~13895389498~2011-06-18
                            // 21:35:02~28~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~4421~2011-06-18
                            // 22:06:53~49430~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~18795203778~2011-06-18
                            // 22:41:08~149~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~45615~2011-06-19
                            // 00:02:36~87126~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~2802~2011-06-19
                            // 01:06:52~22614~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~779~2011-06-19
                            // 01:07:12~801~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~2463~2011-06-19
                            // 02:06:52~19513~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~716~2011-06-19
                            // 02:07:11~773~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~2853~2011-06-19
                            // 03:06:52~20647~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~716~2011-06-19
                            // 03:07:12~771~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~2690~2011-06-19
                            // 04:06:52~28685~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~716~2011-06-19
                            // 04:07:14~768~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~2873~2011-06-19
                            // 05:06:52~20638~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~716~2011-06-19
                            // 05:07:12~770~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~2088~2011-06-19
                            // 06:06:52~13654~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~768~2011-06-19
                            // 06:07:10~793~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~2912~2011-06-19
                            // 07:06:52~66413~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~716~2011-06-19
                            // 07:07:17~1534~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~24785~2011-06-19
                            // 07:45:20~930554~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~1257~2011-06-19
                            // 07:50:46~2945~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~1950~2011-06-19
                            // 07:52:56~4609~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~1048~2011-06-19
                            // 08:00:19~2550~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~8370~2011-06-19
                            // 08:05:34~107504~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~938~2011-06-19
                            // 08:09:30~6702~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~8953~2011-06-19
                            // 08:10:04~27995~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~938~2011-06-19
                            // 08:14:21~5710~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~3978~2011-06-19
                            // 09:06:52~101745~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~830~2011-06-19
                            // 09:07:17~1856~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>����~053188730192~2011-06-19
                            // 09:27:17~276~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>��������~10658483~2011-06-19
                            // 09:51:04~0~929868~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~3677~2011-06-19
                            // 10:06:52~78051~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~716~2011-06-19
                            // 10:07:15~1553~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>��ҵ��������~13895600783~2011-06-19
                            // 10:08:56~0~600032/95588~0.00</bill></billitem>" +
                            // "<billitem><bill>��ҵ��������~13895600783~2011-06-19
                            // 10:08:56~0~600032/95588~0.00</bill></billitem>" +
                            // "<billitem><bill>����~053188730192~2011-06-19
                            // 10:34:31~150~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~4613~2011-06-19
                            // 11:06:52~125151~��������~0.00</bill></billitem>" +
                            // "<billitem><bill>gprs~716~2011-06-19
                            // 11:07:21~1543~��������~0.00</bill></billitem>" +
                            "</message_content>]]></message_body></message_response>";
                } else if ("3".equals(cdrType)) {
                    response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
                            + "<message_head version=\"1.0\"><menuid></menuid><process_code>cli_qry_cdr</process_code>"
                            + "<verify_code></verify_code><resp_time>20110620103246</resp_time><sequence>"
                            + "<req_seq>28</req_seq><operation_seq></operation_seq></sequence><retinfo>"
                            + "<rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
                            + "</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                            + "<message_content><cust_name></cust_name><product_name></product_name><product_info></product_info>"
                            + "<createdate></createdate><total_fee></total_fee><telnum>13895600783</telnum><rowcount>114</rowcount>"
                            + "<billitem><bill>��������[��������]~929845|10658470~~2011-06-10 09:57:23~0.00~~����~0.00~</bill></billitem>"
                            + "<billitem><bill>��������[��������]~929845|10658470~~2011-06-10 10:03:52~0.00~~����~0.00~</bill></billitem>"
                            + "<billitem><bill>��������[��������]~929868|10658483~~2011-06-10 14:59:58~0.00~~����~0.00~</bill></billitem>"
                            +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-10
                            // 15:57:53~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-10
                            // 16:04:18~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929835|10086~~2011-06-11
                            // 10:06:03~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-11
                            // 16:27:09~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-11
                            // 18:45:12~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-11
                            // 19:00:03~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-11
                            // 19:05:28~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929835|10086~~2011-06-12
                            // 10:41:29~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-13
                            // 08:31:43~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-13
                            // 09:57:41~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-13
                            // 10:04:10~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-13
                            // 11:22:03~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-13
                            // 11:22:05~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-13
                            // 11:34:50~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-13
                            // 11:41:59~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-13
                            // 11:41:59~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-13
                            // 11:43:01~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-13
                            // 11:45:03~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-13
                            // 12:09:25~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-13
                            // 12:09:25~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-13
                            // 15:57:23~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-13
                            // 16:03:44~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-13
                            // 16:44:38~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929839|10658139~~2011-06-14
                            // 09:56:28~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-14
                            // 09:57:31~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-14
                            // 10:04:02~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-14
                            // 10:48:35~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-14
                            // 14:36:14~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-14
                            // 15:50:15~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-14
                            // 15:57:20~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-14
                            // 16:03:39~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-15
                            // 09:57:54~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-15
                            // 10:04:23~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-15
                            // 15:12:58~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-15
                            // 15:57:06~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-15
                            // 16:04:25~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-16
                            // 08:39:30~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 09:57:47~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 10:04:09~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 15:57:10~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 15:57:12~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 15:57:14~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 15:57:15~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 15:57:16~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 15:57:17~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 15:57:17~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 15:57:18~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 15:57:19~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 15:57:20~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 16:03:36~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 16:03:38~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 16:03:40~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 16:03:40~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 16:03:41~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 16:03:42~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 16:03:43~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 16:03:43~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 16:03:46~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-16
                            // 16:03:47~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-16
                            // 19:18:13~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-16
                            // 20:32:13~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-17
                            // 09:36:54~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 09:57:50~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 09:57:55~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 09:58:02~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 09:58:04~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 09:58:07~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 09:58:11~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 09:58:12~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 09:58:14~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 09:58:19~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 09:58:23~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 10:03:48~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 10:03:54~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 10:03:59~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 10:04:01~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 10:04:05~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 10:04:08~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 10:04:09~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 10:04:11~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 10:04:15~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 10:04:20~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-17
                            // 14:43:19~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 15:57:03~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 15:57:10~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 15:57:16~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 15:57:18~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 15:57:21~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 15:57:24~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 15:57:26~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 15:57:28~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 15:57:31~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 15:57:38~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 16:04:09~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 16:04:15~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 16:04:21~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 16:04:24~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 16:04:27~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 16:04:31~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 16:04:32~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 16:04:34~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 16:04:38~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 16:04:43~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-17
                            // 18:01:18~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-17
                            // 18:32:09~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-17
                            // 19:16:45~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 20:36:45~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 20:46:47~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 20:47:48~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929845|10658470~~2011-06-17
                            // 20:47:50~0.00~~����~0.00~</bill></billitem>" +
                            // "<billitem><bill>��������[��������]~929868|10658483~~2011-06-19
                            // 09:51:04~0.00~~����~0.00~</bill></billitem>" +
                            "</message_content>]]></message_body></message_response>";
                } else if ("4".equals(cdrType)) {
                    response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                            + "<menuid></menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code>"
                            + "<resp_time>20110620103433</resp_time><sequence><req_seq>28</req_seq><operation_seq>"
                            + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
                            + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                            + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><cust_name></cust_name>"
                            + "<product_name></product_name><product_info></product_info><createdate></createdate><total_fee>"
                            + "</total_fee><telnum>13895600783</telnum><rowcount>58</rowcount>"
                            + "<billitem><bill>201106CMNET[��������]~2011-06-18 00:36:28~48429~9532.40~327.97~9204.43~0.00~0.19~546.82~8985.58</bill></billitem>"
                            + "<billitem><bill>201106CMNET[��������]~2011-06-18 01:36:29~24057~0.00~0.00~0.00~0.00~0.00~0.00~0.00</bill></billitem>"
                            +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-18
                            // 21:06:52~12~65.02~0.00~65.02~0.00~5.42~2.99~62.04</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-18
                            // 21:07:15~4~1.51~0.00~1.51~0.00~0.38~0.70~0.81</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-18
                            // 22:06:53~24~48.27~0.00~48.27~0.00~2.01~4.32~43.95</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 00:02:36~300~170.17~85.09~85.08~0.00~0.28~44.55~125.62</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 01:06:52~9~44.17~22.09~22.08~0.00~2.45~2.74~41.43</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 01:07:12~5~1.57~0.79~0.78~0.00~0.16~0.76~0.80</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 02:06:52~8~38.11~19.05~19.06~0.00~2.38~2.41~35.71</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 02:07:11~5~1.51~0.76~0.75~0.00~0.15~0.70~0.81</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 03:06:52~11~40.33~20.17~20.16~0.00~1.83~2.79~37.54</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 03:07:12~5~1.51~0.76~0.75~0.00~0.15~0.70~0.81</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 04:06:52~11~56.03~28.02~28.01~0.00~2.55~2.63~53.40</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 04:07:14~5~1.50~0.75~0.75~0.00~0.15~0.70~0.80</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 05:06:52~11~40.31~20.16~20.15~0.00~1.83~2.81~37.50</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 05:07:12~5~1.50~0.75~0.75~0.00~0.15~0.70~0.80</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 06:06:52~8~26.67~13.34~13.33~0.00~1.67~2.04~24.63</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 06:07:10~5~1.55~0.78~0.77~0.00~0.15~0.75~0.80</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 07:06:52~13~64.86~0.00~64.86~0.00~4.99~2.84~62.01</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 07:07:17~11~1.50~0.00~1.50~0.00~0.14~0.70~0.80</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 07:45:20~211~908.74~0.00~908.74~0.00~4.31~24.20~884.54</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 07:50:46~40~2.88~0.00~2.88~0.00~0.07~1.23~1.65</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 07:52:56~352~4.50~0.00~4.50~0.00~0.01~1.90~2.60</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 08:00:19~50~2.49~0.00~2.49~0.00~0.05~1.02~1.47</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 08:05:34~107~104.98~0.00~104.98~0.00~0.98~8.17~96.81</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 08:09:30~28~6.54~0.00~6.54~0.00~0.23~0.92~5.63</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 08:10:04~235~27.34~0.00~27.34~0.00~0.12~8.74~18.60</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 08:14:21~22~5.58~0.00~5.58~0.00~0.25~0.92~4.66</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 09:06:52~12~99.36~0.00~99.36~0.00~8.28~3.88~95.48</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 09:07:17~6~1.81~0.00~1.81~0.00~0.30~0.81~1.00</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 10:06:52~10~76.22~0.00~76.22~0.00~7.62~3.59~72.63</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 10:07:15~5~1.52~0.00~1.52~0.00~0.30~0.70~0.82</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 11:06:52~15~122.22~0.00~122.22~0.00~8.15~4.50~117.71</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 11:07:21~4~1.51~0.00~1.51~0.00~0.38~0.70~0.81</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 12:06:52~11~107.48~0.00~107.48~0.00~9.77~4.26~103.22</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 12:07:17~5~1.49~0.00~1.49~0.00~0.30~0.70~0.79</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 13:06:52~11~101.30~0.00~101.30~0.00~9.21~4.11~97.19</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 13:07:17~5~1.50~0.00~1.50~0.00~0.30~0.70~0.80</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 14:06:52~16~85.88~0.00~85.88~0.00~5.37~4.11~81.77</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 14:07:21~5~1.51~0.00~1.51~0.00~0.30~0.70~0.81</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 15:06:52~7~39.63~0.00~39.63~0.00~5.66~2.57~37.07</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 15:07:09~5~1.55~0.00~1.55~0.00~0.31~0.75~0.80</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 15:20:22~217~20.36~0.00~20.36~0.00~0.09~6.20~14.15</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 15:25:37~59~5.10~0.00~5.10~0.00~0.09~0.98~4.12</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 16:06:52~9~55.04~0.00~55.04~0.00~6.12~2.70~52.34</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 16:07:12~5~1.50~0.00~1.50~0.00~0.30~0.70~0.80</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 17:06:52~7~39.05~0.00~39.05~0.00~5.58~2.50~36.55</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 17:07:09~5~1.50~0.00~1.50~0.00~0.30~0.70~0.80</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 18:06:52~10~75.95~0.00~75.95~0.00~7.60~3.00~72.95</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 18:07:13~5~1.50~0.00~1.50~0.00~0.30~0.70~0.80</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 20:06:52~7~34.92~0.00~34.92~0.00~4.99~2.54~32.38</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 20:07:10~4~1.50~0.00~1.50~0.00~0.38~0.70~0.80</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 21:06:52~10~50.99~0.00~50.99~0.00~5.10~2.55~48.44</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 21:07:13~5~1.50~0.00~1.50~0.00~0.30~0.70~0.80</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 22:06:52~11~37.26~0.00~37.26~0.00~3.39~2.54~34.72</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 22:07:14~7~1.50~0.00~1.50~0.00~0.21~0.70~0.80</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 22:50:56~119~6.89~0.00~6.89~0.00~0.06~0.98~5.91</bill></billitem>"
                            // +
                            // "<billitem><bill>201106CMNET[��������]~2011-06-19
                            // 22:53:10~235~30.08~0.00~30.08~0.00~0.13~8.91~21.17</bill></billitem>"
                            // +
                            "</message_content>]]></message_body></message_response>";
                }
            } else if (Constants.PROOPERORGID_SD.equals(province)) {
                if ("1".equals(cdrType)) {
                    response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                            + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code>"
                            + "<resp_time>20100921001546</resp_time><sequence><req_seq>28</req_seq><operation_seq>"
                            + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                            + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                            + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><cust_name>��Ⱥ</cust_name>"
                            + "<product_name>���еش�</product_name><product_info>ʱ�� ���� ̽��</product_info>"
                            + "<createdate>20080822112345</createdate><total_fee></total_fee>"
                            + "<billitem><bill>15864500526~����~����~2011-06-01 01:42:16~24505~1.0~2.0~3.0~6.0</bill></billitem>"
                            + "<billitem><bill>15864500526~����~����~2011-06-01 01:42:16~24505~1.0~2.0~3.0~6.0</bill></billitem>"
                            + "<billitem><bill>15864500526~����~����~2011-06-01 01:42:16~24505~1.0~2.0~3.0~6.0</bill></billitem>"
                            + "</message_content>]]></message_body></message_response>";
                } else if ("3".equals(cdrType)) {
                    response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                            + "<menuid>qryImsgMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code>"
                            + "<resp_time>20100921001709</resp_time><sequence><req_seq>29</req_seq><operation_seq></operation_seq>"
                            + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                            + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                            + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><cust_name>��Ⱥ</cust_name>"
                            + "<product_name>���еش�</product_name><product_info>ʱ�� ���� ̽��</product_info>"
                            + "<createdate>20080822112345</createdate><total_fee></total_fee>"
                            + "<billitem><bill>�й��ƶ�-����~0.00~0.00~12520~915942~09-19 16:54:56~+MCHAT</bill></billitem>"
                            + "<billitem><bill>�й��ƶ�-����~0.00~0.00~12520~915942~09-19 16:54:56~+MCHAT</bill></billitem>"
                            +
                            // "<billitem><bill>�й��ƶ�-����~0.00~0.00~12520~915942~09-04
                            // 20:32:48~+MCHAT</bill></billitem>" +
                            // "<billitem><bill>ɽ���ƶ��ֻ�����(���˰�)~0.00~0.00~10658139~915910~09-14
                            // 17:46:46~10000</bill></billitem>" +
                            // "<billitem><bill>ɽ���ƶ��ֻ�����(���˰�)~0.00~0.00~10658139~915910~09-14
                            // 17:46:46~10000</bill></billitem>" +
                            // "<billitem><bill>�й��ƶ�-����~0.00~0.00~12520~915942~09-19
                            // 16:54:56~+MCHAT</bill></billitem>" +
                            // "<billitem><bill>�й��ƶ�-����~0.00~0.00~12520~915942~09-19
                            // 16:54:56~+MCHAT</bill></billitem>" +
                            "</message_content>]]></message_body></message_response>";
                } else if ("2".equals(cdrType)) {
                    response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                            + "<menuid>qrySmsMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code>"
                            + "<resp_time>20100921001724</resp_time><sequence><req_seq>30</req_seq><operation_seq>"
                            + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                            + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                            + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><cust_name>��Ⱥ</cust_name>"
                            + "<product_name>���еش�</product_name><product_info>ʱ�� ���� ̽��</product_info>"
                            + "<createdate>20080822112345</createdate><total_fee></total_fee>"
                            + "<billitem><bill>�ƶ����ڵ�Ե����~15864500526~13645319981~09-01 06:59:57~20~0.00</bill></billitem>"
                            + "<billitem><bill>�ƶ����ڵ�Ե����~15864500526~15920677652~09-01 18:27:02~6~0.00</bill></billitem>"
                            +
                            // "<billitem><bill>�ƶ����ڵ�Ե����~15864500526~15920677652~09-01
                            // 20:16:35~76~0.00</bill></billitem>"
                            // +
                            // "<billitem><bill>�ƶ����ڵ�Ե����~15864500526~15920677652~09-01
                            // 20:31:20~60~0.00</bill></billitem>"
                            // +
                            // "<billitem><bill>�ƶ����ڵ�Ե����~15864500526~15920677652~09-01
                            // 20:37:59~30~0.00</bill></billitem>"
                            // +
                            // "<billitem><bill>�ƶ����ڵ�Ե����~15864500526~13791145921~09-02
                            // 14:25:47~60~0.00</bill></billitem>"
                            // +
                            // "<billitem><bill>�ƶ����ڵ�Ե����~15864500526~13791145921~09-02
                            // 14:29:02~36~0.00</bill></billitem>"
                            // +
                            // "<billitem><bill>�ƶ����ڵ�Ե����~15864500526~13791145921~09-03
                            // 11:45:38~16~0.00</bill></billitem>"
                            // +
                            "</message_content>]]></message_body></message_response>";
                } else if ("4".equals(cdrType)) {
                    response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                            + "<menuid>qryAllMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code>"
                            + "<resp_time>20100921010640</resp_time><sequence><req_seq>127</req_seq><operation_seq></operation_seq>"
                            + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                            + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                            + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><cust_name>��Ⱥ</cust_name>"
                            + "<product_name>���еش�</product_name><product_info>ʱ�� ���� ̽��</product_info>"
                            + "<createdate>20080822112345</createdate><total_fee></total_fee>"
                            + "<billitem><bill>201106CMNET[��������]~2011-06-01 01:42:16~10~47~23~23~0~23~23~0</bill></billitem>"
                            + "<billitem><bill>201106CMNET[��������]~2011-06-01 01:42:32~4~1~0~0~0~23~23~0</bill></billitem>"
                            + "</message_content>]]></message_body></message_response>";
                } else if ("5".equals(cdrType)) {
                    response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                            + "<menuid>qryWlanMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code>"
                            + "<resp_time>20100921055850</resp_time><sequence><req_seq>21</req_seq><operation_seq>"
                            + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                            + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                            + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><cust_name>����</cust_name>"
                            + "<product_name>������</product_name><product_info>���� ʵ�� ���</product_info>"
                            + "<createdate>20100809145848</createdate><total_fee></total_fee>"
                            + "<billitem><bill>����~WEB~09-21 05:20:31~243~5185859~640992~0.25</bill></billitem>"
                            + "</message_content>]]></message_body></message_response>";
                }
            } else if (Constants.PROOPERORGID_HUB.equals(province)) {
                if ("1".equals(cdrType))// ͨ���굥
                {
                    response = "<message_response><message_head version=\"1.0\"><menuid>qryAllMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121105626</resp_time><sequence><req_seq>236</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><cust_name>��2700008515650</cust_name><product_name>ȫ��ͨ</product_name><product_info></product_info><createdate></createdate><total_fee></total_fee><billitem><bill>20111001 08:10:29~231~����~13986160101~0.00~0.00~0.00~~~~0.00~~71260A2A</bill></billitem><billitem><bill>20111001 08:18:49~31~����~13986160101~0.00~0.00~0.00~~~~0.00~~71260A2A</bill></billitem><billitem><bill>20111001 08:19:56~39~����~13986160101~0.00~0.00~0.00~~~~0.00~~71260A2A</bill></billitem></message_content>]]></message_body></message_response>";
                } else if ("2".equals(cdrType))// �����嵥
                {
                    response = "<message_response><message_head version=\"1.0\"><menuid>qrySmsMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102321</resp_time><sequence><req_seq>206</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><cust_name>��2700008515650</cust_name><product_name>ȫ��ͨ</product_name><product_info></product_info><createdate></createdate><total_fee></total_fee><billitem><bill>����~13797060656~15927072614~10-01 13:46:12~10~         0.10</bill></billitem><billitem><bill>����~13797060656~15050258478~10-01 13:53:20~76~         0.10</bill></billitem><billitem><bill>����~13797060656~12520569313493~10-05 10:39:36~46~         0.10</bill></billitem></message_content>]]></message_body></message_response>";
                } else if ("3".equals(cdrType))// �����嵥
                {
                    response = "<message_response><message_head version=\"1.0\"><menuid>qryImsgMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121161920</resp_time><sequence><req_seq>60</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><cust_name>��2700008515650</cust_name><product_name>ȫ��ͨ</product_name><product_info></product_info><createdate></createdate><total_fee></total_fee><billitem><bill>SP�����ṩ��~~~13507101860~13507101860~10-01 00:14:59~         0.00~         5.00</bill></billitem><billitem><bill>SP�����ṩ��~~~13507101860~13507101860~10-01 00:14:59~         0.00~        10.00</bill></billitem><billitem><bill>SP�����ṩ��~901449~10666226~-XXVL~13797060656~10-01 14:33:07~         0.00~         0.00</bill></billitem></message_content>]]></message_body></message_response>";
                } else if ("4".equals(cdrType))// GPRS�嵥
                {
                    response = "<message_response><message_head version=\"1.0\"><menuid>qryGprsMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102333</resp_time><sequence><req_seq>207</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><cust_name>��2700008515650</cust_name><product_name>ȫ��ͨ</product_name><product_info></product_info><createdate></createdate><total_fee></total_fee><billitem><bill>CMNET~��ʱ~10-01 00:18:42~554~736~1024~�����ݼƷ�����~0~ʡ��~0~1024</bill></billitem><billitem><bill>CMNET~��ʱ~10-01 01:49:06~40~55~0~�����ݼƷ�����~0~ʡ��~0~0</bill></billitem><billitem><bill>CMNET~��ʱ~10-01 03:19:20~778~864~2048~�����ݼƷ�����~0~ʡ��~0~2048</bill></billitem></message_content>]]></message_body></message_response>";
                } else if ("5".equals(cdrType))// WLAN�嵥
                {
                    response = "<message_response><message_head version=\"1.0\"><menuid>qryWlanMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102341</resp_time><sequence><req_seq>208</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><cust_name>��2700008515650</cust_name><product_name>ȫ��ͨ</product_name><product_info></product_info><createdate></createdate><total_fee></total_fee><billitem><bill>����~WEB~10-09 14:37:21~4325~         3.65</bill></billitem><billitem><bill>����~WEB~10-09 16:13:53~3065~         2.60</bill></billitem><billitem><bill>����~WEB~10-17 09:11:53~3391~         2.85</bill></billitem><billitem><bill>����~WEB~10-17 14:12:53~10775~         9.00</bill></billitem></message_content>]]></message_body></message_response>";
                } else if ("6".equals(cdrType))// �����嵥
                {
                    response = "<message_response><message_head version=\"1.0\"><menuid>qryMmsMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102348</resp_time><sequence><req_seq>209</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><cust_name>��2700008515650</cust_name><product_name>ȫ��ͨ</product_name><product_info></product_info><createdate></createdate><total_fee></total_fee><billitem><bill>���ڲ���~����~801234~10658000~110360~801234~13797060656~10-01 08:02:13~0~         0.00~         0.00</bill></billitem><billitem><bill>���ڲ���~����~801180~106540330000~101101~801180~13797060656~10-01 08:02:21~44284~         0.00~         0.00</bill></billitem><billitem><bill>���ڲ���~����~801234~10658000~110301~801234~13797060656~10-01 08:44:06~0~         0.00~         0.00</bill></billitem></message_content>]]></message_body></message_response>";
                } else if ("7".equals(cdrType))// ������Ϣ���嵥
                {
                    response = "<message_response><message_head version=\"1.0\"><menuid>qryInfoFeeMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102355</resp_time><sequence><req_seq>210</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>-100</retcode><retmsg><![CDATA[*û����Ҫ��ѯ�ļ�¼!]]></retmsg></retinfo></message_head></message_response>";
                } else if ("8".equals(cdrType))// VPMN�嵥
                {
                    response = "<message_response><message_head version=\"1.0\"><menuid>qryVpmnMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102401</resp_time><sequence><req_seq>211</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content><cust_name>��2700008515650</cust_name><product_name>ȫ��ͨ</product_name><product_info></product_info><createdate></createdate><total_fee></total_fee><billitem><bill>����~13797060626~20111001 09:40:03~125~~0.00</bill></billitem><billitem><bill>����~13971275566~20111001 10:24:57~18~~0.00</bill></billitem><billitem><bill>����~13507187666~20111006 08:59:59~80~~0.00</bill></billitem><billitem><bill>����~13797060626~20111006 09:26:14~118~~0.00</bill></billitem><billitem><bill>����~13971275598~20111008 11:00:58~52~~0.00</bill></billitem><billitem><bill>����~13871272007~20111008 16:44:52~37~~0.00</bill></billitem></message_content>]]></message_body></message_response>";
                } else if ("9".equals(cdrType))// PIM�嵥
                {
                    response = "<message_response><message_head version=\"1.0\"><menuid>qryPimMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102407</resp_time><sequence><req_seq>212</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>-100</retcode><retmsg><![CDATA[*û����Ҫ��ѯ�ļ�¼!]]></retmsg></retinfo></message_head></message_response>";
                } else if ("10".equals(cdrType))// �ֻ������嵥
                {
                    response = "<message_response><message_head version=\"1.0\"><menuid>qryFlashMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102414</resp_time><sequence><req_seq>213</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>-100</retcode><retmsg><![CDATA[*û����Ҫ��ѯ�ļ�¼!]]></retmsg></retinfo></message_head></message_response>";
                } else if ("11".equals(cdrType))// G3�嵥
                {
                    response = "<message_response><message_head version=\"1.0\"><menuid>qryG3Muster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102419</resp_time><sequence><req_seq>214</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>-100</retcode><retmsg><![CDATA[*û����Ҫ��ѯ�ļ�¼!]]></retmsg></retinfo></message_head></message_response>";
                } else if ("12".equals(cdrType))// ��Ϸ�㿨�嵥
                {
                    response = "<message_response><message_head version=\"1.0\"><menuid>qryGameMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code></verify_code><resp_time>20111121102424</resp_time><sequence><req_seq>215</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>-100</retcode><retmsg><![CDATA[*û����Ҫ��ѯ�ļ�¼!]]></retmsg></retinfo></message_head></message_response>";
                }
            }

            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }

    /** 
     * �굥��ѯȨ����֤_����
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by hWX5316476 2014/04/22 OR_huawei_201404_375 �����ն�ȫ���̽���EBUS����_��ֵ���� ��ѡ�ײ�
     * @remark modify by zKF69263 2014/09/15 OR_huawei_201409_425 �����ն˽���EBUS_�굥��ѯ
     */
    public ReturnWrap checkQueryAuth(MsgHeaderPO msgHeader)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recStopOpen</menuid><process_code>cli_busi_stopopensubs</process_code><verify_code></verify_code><resp_time>20100921002614</resp_time><sequence><req_seq>55</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
            rwp = new ReturnWrap();
            //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
            
            rwp.setStatus(0);
            rwp.setReturnMsg("");
        }
        return rwp;
    }

    /**
     * ������ʷ��ѯ
     */
    public ReturnWrap qryAllServiceHistory(Map map) {
        String startDate = (String) map.get("startDate");
        String endDate = (String) map.get("endDate");
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>qryServiceHistory</menuid><process_code>cli_qry_rechistory</process_code>"
                + "<verify_code></verify_code><resp_time>20100921002233</resp_time>"
                + "<sequence><req_seq>40</req_seq><operation_seq></operation_seq></sequence><retinfo>"
                + "<rettype>0</rettype><retcode>100</retcode>"
                + "<retmsg><![CDATA[Processing the request succeeded!]]>"
                + "</retmsg></retinfo></message_head>"
                + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                + "<message_content>"
                + "<acptitem><accept_time>20100303210238</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>�շ�</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100318223958</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>�շ�</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100420221556</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>�շ�</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100521172823</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>��Ʒ���</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100521172840</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>��Ʒ���</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100521172918</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>��Ʒ���</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100522214906</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>�շ�</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100527143358</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>��Ʒ���</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100527143407</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>��Ʒ���</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100531203055</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>������</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100608082218</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>��Ʒ���</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100612095443</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>��Ʒ���</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100622210420</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>�շ�</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100726165208</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>�շ�</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100823221444</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>�շ�</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100914174647</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>��Ʒ���</accept_type><contents /></acptitem>"
                + "<acptitem><accept_time>20100915172513</accept_time><accept_site>����</accept_site><accept_staff /><accept_type>������</accept_type><contents /></acptitem>"
                + "</message_content>]]></message_body></message_response>";
        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rwp;
    }

    /**
     * �ײ���Ϣ��ѯ
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryComboInfo(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>qrySerPri</menuid><process_code>cli_qry_taocan</process_code><verify_code></verify_code><resp_time>20100921002206</resp_time><sequence><req_seq>39</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><calctime>09��21��00ʱ</calctime><privitem><privset>10Ԫ��120����Ե���ţ��ƶ�ȫʱͨ��ѡ��</privset><type>SMS</type><sumnum>120</sumnum><leftnum>33</leftnum><unit>��</unit><feename></feename><servtype>0</servtype><starttime>20100901</starttime><endtime>20100930</endtime></privitem></message_content>]]></message_body></message_response>";
        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rwp;
    }

    /**
     * �ɷ���ʷ��ѯ
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryChargeHistory(Map map) {
        String response = "";

        String province = (String) PublicCache.getInstance().getCachedData(
                Constants.PROVINCE_ID);
        if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
                    + "<message_head version=\"1.0\"><menuid>qryBill</menuid>"
                    + "<process_code>cli_qry_chargehistory</process_code><verify_code></verify_code>"
                    + "<resp_time>20110713160218</resp_time><sequence><req_seq>96</req_seq><operation_seq>"
                    + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
                    + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
                    + "<pay_date>2011-01-01 12:25:59</pay_date><billing_cycle_id></billing_cycle_id>"
                    + "<amount>2000</amount><statusname>��</statusname><payment_method></payment_method>"
                    + "<paytype>�����г�ֵ��</paytype><recorgid></recorgid><recorgname></recorgname></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
                    + "<pay_date>2011-01-29 09:31:43</pay_date><billing_cycle_id></billing_cycle_id>"
                    + "<amount>2000</amount><statusname>��</statusname><payment_method></payment_method>"
                    + "<paytype>�����г�ֵ��</paytype><recorgid></recorgid><recorgname></recorgname></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
                    + "<pay_date>2011-02-03 20:03:04</pay_date><billing_cycle_id></billing_cycle_id><amount>1000</amount>"
                    + "<statusname>��</statusname><payment_method></payment_method><paytype>�����г�ֵ��</paytype><recorgid>"
                    + "</recorgid><recorgname></recorgname></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
                    + "<pay_date>2011-02-08 10:51:05</pay_date><billing_cycle_id></billing_cycle_id><amount>3000</amount>"
                    + "<statusname>��</statusname><payment_method></payment_method><paytype>�����г�ֵ��</paytype>"
                    + "<recorgid></recorgid><recorgname></recorgname></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
                    + "<pay_date>2011-02-09 15:05:59</pay_date><billing_cycle_id></billing_cycle_id><amount>3000</amount>"
                    + "<statusname>��</statusname><payment_method></payment_method><paytype>�����г�ֵ��</paytype>"
                    + "<recorgid></recorgid><recorgname></recorgname></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
                    + "<pay_date>2011-02-18 18:24:11</pay_date><billing_cycle_id></billing_cycle_id><amount>2000</amount>"
                    + "<statusname>��</statusname><payment_method></payment_method><paytype>�����г�ֵ��</paytype>"
                    + "<recorgid></recorgid><recorgname></recorgname></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
                    + "<pay_date>2011-04-01 23:25:58</pay_date><billing_cycle_id></billing_cycle_id><amount>2000</amount>"
                    + "<statusname>��</statusname><payment_method></payment_method><paytype>�����г�ֵ��</paytype>"
                    + "<recorgid></recorgid><recorgname></recorgname></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr></pay_serial_nbr><operation_type></operation_type>"
                    + "<pay_date>2011-04-07 17:32:37</pay_date><billing_cycle_id></billing_cycle_id><amount>10000</amount>"
                    + "<statusname>��</statusname><payment_method></payment_method><paytype>�����ն�</paytype>"
                    + "<recorgid></recorgid><recorgname></recorgname></payhistoryitem>"
                    + "</message_content>]]></message_body></message_response>";
        } else {
            response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryBill</menuid><process_code>cli_qry_chargehistory</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921152941</resp_time><sequence><req_seq>75</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<payhistoryitem><pay_serial_nbr>531100504168436860</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100504090015</pay_date><billing_cycle_id>201004</billing_cycle_id><amount>5000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100731726732692</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100731112459</pay_date><billing_cycle_id>201006</billing_cycle_id><amount>500</amount>"
                    + "<statusname>����</statusname><payment_method>�����ն�</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100812442814884</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100812133704</pay_date><billing_cycle_id>201007</billing_cycle_id><amount>0</amount>"
                    + "<statusname>����</statusname><payment_method>CRMӪҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100902623805704</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100902093409</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100504168436860</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100504090015</pay_date><billing_cycle_id>201004</billing_cycle_id><amount>5000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100731726732692</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100731112459</pay_date><billing_cycle_id>201006</billing_cycle_id><amount>500</amount>"
                    + "<statusname>����</statusname><payment_method>�����ն�</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100812442814884</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100812133704</pay_date><billing_cycle_id>201007</billing_cycle_id><amount>0</amount>"
                    + "<statusname>����</statusname><payment_method>CRMӪҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100902623805704</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100902093409</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100504168436860</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100504090015</pay_date><billing_cycle_id>201004</billing_cycle_id><amount>5000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100731726732692</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100731112459</pay_date><billing_cycle_id>201006</billing_cycle_id><amount>500</amount>"
                    + "<statusname>����</statusname><payment_method>�����ն�</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100812442814884</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100812133704</pay_date><billing_cycle_id>201007</billing_cycle_id><amount>0</amount>"
                    + "<statusname>����</statusname><payment_method>CRMӪҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100902623805704</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100902093409</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100504168436860</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100504090015</pay_date><billing_cycle_id>201004</billing_cycle_id><amount>5000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100731726732692</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100731112459</pay_date><billing_cycle_id>201006</billing_cycle_id><amount>500</amount>"
                    + "<statusname>����</statusname><payment_method>�����ն�</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100812442814884</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100812133704</pay_date><billing_cycle_id>201007</billing_cycle_id><amount>0</amount>"
                    + "<statusname>����</statusname><payment_method>CRMӪҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100902623805704</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100902093409</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100504168436860</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100504090015</pay_date><billing_cycle_id>201004</billing_cycle_id><amount>5000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100731726732692</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100731112459</pay_date><billing_cycle_id>201006</billing_cycle_id><amount>500</amount>"
                    + "<statusname>����</statusname><payment_method>�����ն�</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100812442814884</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100812133704</pay_date><billing_cycle_id>201007</billing_cycle_id><amount>0</amount>"
                    + "<statusname>����</statusname><payment_method>CRMӪҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100902623805704</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100902093409</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "<payhistoryitem><pay_serial_nbr>531100917509434324</pay_serial_nbr><operation_type>�շ�</operation_type>"
                    + "<pay_date>20100917214005</pay_date><billing_cycle_id>201008</billing_cycle_id><amount>10000</amount>"
                    + "<statusname>����</statusname><payment_method>����Ӫҵ��</payment_method></payhistoryitem>"
                    + "</message_content>]]></message_body></message_response>";
        }

        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rwp;
    }

    /**
     * ���ֲ�ѯ
     */
    public ReturnWrap queryScoreValue(Map map) {
        String response;
        if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance()
                .getCachedData("ProvinceID"))) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryWlanMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921055850</resp_time><sequence><req_seq>21</req_seq>"
                    + "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
                    + "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
                    + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<scoreinfo>�����У��ҿ��У�~300~50~20~20~20~280~10</scoreinfo>"
                    + "</message_content>]]>"
                    + "</message_body></message_response>";
        } else if (Constants.PROOPERORGID_NX.equals(PublicCache.getInstance()
                .getCachedData("ProvinceID"))) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
                    + "<message_head version=\"1.0\"><menuid>qryScore</menuid>"
                    + "<process_code>cli_qry_scorevalue</process_code><verify_code></verify_code>"
                    + "<resp_time>20110615183754</resp_time><sequence><req_seq>81</req_seq>"
                    + "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype>"
                    + "<retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                    + "</retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<scoreinfo>80364</scoreinfo></message_content>]]></message_body>"
                    + "</message_response>";
        } else {
            response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryWlanMuster</menuid><process_code>cli_qry_cdr</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921055850</resp_time><sequence><req_seq>21</req_seq>"
                    + "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
                    + "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
                    + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<scoreinfo>500~300~200~100</scoreinfo>"
                    + "</message_content>]]>"
                    + "</message_body></message_response>";
        }

        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rwp;
    }

    /**
     * ������Ѳ�ѯ
     */
    public ReturnWrap qryBalanceNotice(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>recChgBalanceUrge</menuid><process_code>cli_qry_alarmbalance</process_code><verify_code>"
                + "</verify_code><resp_time>20100921002343</resp_time><sequence><req_seq>47</req_seq><operation_seq>"
                + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><prepay_type>1</prepay_type>"
                + "<credit>500</credit></message_content>]]>"
                + "</message_body></message_response>";

        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rwp;
    }

    /**
     * ��ȡ��������ֵ������
     */
    public ReturnWrap getDictItem(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>recChgBalanceUrge</menuid><process_code>cli_qry_dictitem</process_code><verify_code>"
                + "</verify_code><resp_time>20100921002343</resp_time><sequence><req_seq>48</req_seq><operation_seq>"
                + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><dictitem><dict_id>5</dict_id>"
                + "<dict_name>5Ԫ</dict_name></dictitem><dictitem><dict_id>10</dict_id><dict_name>10Ԫ</dict_name>"
                + "</dictitem><dictitem><dict_id>15</dict_id><dict_name>15Ԫ</dict_name></dictitem><dictitem>"
                + "<dict_id>20</dict_id><dict_name>20Ԫ</dict_name></dictitem><dictitem><dict_id>30</dict_id>"
                + "<dict_name>30Ԫ</dict_name></dictitem><dictitem><dict_id>50</dict_id><dict_name>50Ԫ</dict_name>"
                + "</dictitem></message_content>]]></message_body></message_response>";

        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rwp;
    }

    /**
     * �������ֵ����
     */
    public ReturnWrap setBalanceNotice(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recChgBalanceUrge</menuid><process_code>cli_busi_alarmbalance</process_code><verify_code></verify_code><resp_time>20100921002348</resp_time><sequence><req_seq>49</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rwp;
    }

    /**
     * ����nocde(��)��ѯ��Ʒ,�Żݵ��ʷ�������Ϣ
     * 
     * @param map
     * @return
     * @see
     */
    public ReturnWrap queryFeeMessage(Map map) {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>recCallDisplay</menuid><process_code>cli_qry_productfee</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921002647</resp_time><sequence><req_seq>57</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><desc>���к�����ʾ</desc>"
                    + "</message_content>]]></message_body></message_response>";

            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }
    
    /**
     * �����ܿ���Ʒ����ͨ�ýӿ�
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public ReturnWrap recCommonServNK(MsgHeaderPO msgHeader, String nCode, String operType, String effectType, String param) {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>recCallDisplay</menuid><process_code>cli_busi_productrec</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921002649</resp_time><sequence><req_seq>58</req_seq><operation_seq>"
                    + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    + "<message_content><addncode></addncode><delncode>H01</delncode><curncode>H01</curncode><add_startdate>"
                    + "</add_startdate><add_enddate></add_enddate><del_enddate>2010-09-21 00:26:30</del_enddate>"
                    + "</message_content>]]></message_body></message_response>";
            
            String response1 = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            "<menuid>privDXBRec</menuid><process_code>cli_busi_productrec</process_code><verify_code></verify_code>" +
            "<resp_time>20150601151027</resp_time><sequence><req_seq>28</req_seq><operation_seq></operation_seq>" +
            "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" +
            "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
            "<addncode>TESTSMS5</addncode><delncode>TESTSMS20</delncode><curncode>TESTSMS20</curncode><nextncode>TESTSMS20</nextncode>" +
            "<add_startdate>2015-07-01 00:00:00</add_startdate><add_enddate></add_enddate><del_enddate>2015-07-01 00:00:00" +
            "</del_enddate></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response1);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }

    /**
     * ��Ʒ����ͨ�ýӿ�
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public ReturnWrap recCommonServ(MsgHeaderPO msgHeader, String nCode, String operType, String effectType, String param) {
        try {
        	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>recCallDisplay</menuid><process_code>cli_busi_productrec</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921002649</resp_time><sequence><req_seq>58</req_seq><operation_seq>"
                    + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    + "<message_content><addncode></addncode><delncode>H01</delncode><curncode>H01</curncode><add_startdate>"
                    + "</add_startdate><add_enddate></add_enddate><del_enddate>2010-09-21 00:26:30</del_enddate>"
                    + "</message_content>]]></message_body></message_response>";
        	
        	String response1 = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            "<menuid>privDXBRec</menuid><process_code>cli_busi_productrec</process_code><verify_code></verify_code>" +
            "<resp_time>20150601151027</resp_time><sequence><req_seq>28</req_seq><operation_seq></operation_seq>" +
            "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" +
            "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
            "<addncode>TESTSMS5</addncode><delncode>TESTSMS20</delncode><curncode>TESTSMS20</curncode><nextncode>TESTSMS20</nextncode>" +
            "<add_startdate>2015-07-01 00:00:00</add_startdate><add_enddate></add_enddate><del_enddate>2015-07-01 00:00:00" +
            "</del_enddate></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response1);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }
    
    public ReturnWrap recCommonProductQry (MsgHeaderPO msgHeader, String nCode)
    {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>recCallDisplay</menuid><process_code>cli_busi_productrec</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921002649</resp_time><sequence><req_seq>58</req_seq><operation_seq>"
                    + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]>"
                    + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    + "<message_content><addncode></addncode><delncode>H01</delncode><curncode>H01</curncode><add_startdate>"
                    + "</add_startdate><add_enddate></add_enddate><del_enddate>2010-09-21 00:26:30</del_enddate>"
                    + "</message_content>]]></message_body></message_response>";
            
            String response1 = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            "<menuid>privDXBRec</menuid><process_code>cli_busi_productrec</process_code><verify_code></verify_code>" +
            "<resp_time>20150601151027</resp_time><sequence><req_seq>28</req_seq><operation_seq></operation_seq>" +
            "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" +
            "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
            "<addncode>TESTSMS5</addncode><delncode>TESTSMS20</delncode><curncode>TESTSMS20</curncode><nextncode>TESTSMS20</nextncode>" +
            "<add_startdate>2015-07-01 00:00:00</add_startdate><add_enddate></add_enddate><del_enddate>2015-07-01 00:00:00" +
            "</del_enddate></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response1);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }

    /**
     * ͣ����ҵ����
     */
    public ReturnWrap stopOpenSubs(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recStopOpen</menuid><process_code>cli_busi_stopopensubs</process_code><verify_code></verify_code><resp_time>20100921002614</resp_time><sequence><req_seq>55</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            //modify begin kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
            rwp = new ReturnWrap();
            //modify end kWX211786 2014-7-16 NGBOSS ChongGouXuNi������:OR_huawei_201407_373_��̬���_�����ն�
           
            rwp.setStatus(0);
            rwp.setReturnMsg("");
        }
        return rwp;
    }

    /**
     * �����޸�
     */
    public ReturnWrap recChangePassword(Map map) {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recChangePwd</menuid><process_code>cli_busi_chgpwd</process_code><verify_code></verify_code><resp_time>20100921002448</resp_time><sequence><req_seq>51</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("ϵͳ���������޸���������");

            return rwp;
        }

    }

    /**
     * ����ת������
     * 
     * @param map
     * @return
     */
    public ReturnWrap commitCallTransferNo(Map map) {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>recCallTransfer</menuid><process_code>cli_busi_calltransfer</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921002750</resp_time><sequence><req_seq>61</req_seq><operation_seq>"
                    + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head></message_response>";

            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }

    /**
     * ҵ���ѯͳһ�ӿ� ����ҵ���ѯ
     */
    public ReturnWrap queryService(Map map) {
        try {
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
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }

    }
    
    /**
     * ��ѯ��ͥ����Ա
    */
    public ReturnWrap queryFamilyMemService(Map<String,String> map)
    {
        try
        {
//            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
//            		        "<message_head version=\"1.0\"><menuid>recAddFamilyMem</menuid><process_code>cli_qry_familymeminfo_sd</process_code>" +
//            				"<verify_code/><resp_time>20140529172040</resp_time><sequence><req_seq>1</req_seq><operation_seq/></sequence>" +
//            				"<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>Processing the request succeeded!</retmsg></retinfo>" +
//            				"</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><result>1</result>" +
//            				"<famCode>6348000328171</famCode><memlist>13863420044���̺�552</memlist><resultList><telnum>13863420044</telnum><name>3213123</name>" +
//            				"<shortNum>552</shortNum><adddate>2012-10-25 14:38:40</adddate><ishost>1</ishost></resultList></message_content>]]></message_body>" +
//            				"</message_response>";
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
            "<message_head version=\"1.0\"><menuid>recAddFamilyMem</menuid><process_code>cli_qry_familymeminfo_sd</process_code>" +
            "<verify_code/><resp_time>20140529172040</resp_time><sequence><req_seq>1</req_seq><operation_seq/></sequence>" +
            "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>Processing the request succeeded!</retmsg></retinfo>" +
            "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><result>1</result>" +
            "<famCode>6348000328171</famCode><memlist>13863420044���̺�552</memlist><resultList><telnum>13863420044</telnum><name>3213123</name>" +
            "<shortNum>552</shortNum><adddate>2012-10-25 14:38:40</adddate><ishost>1</ishost></resultList>" +
            "<resultList><telnum>13863420045</telnum><name>3213124</name>" +
            "<shortNum>555</shortNum><adddate>2012-10-25 14:58:40</adddate><ishost>0</ishost></resultList>"+
            "</message_content>]]></message_body>" +
            "</message_response>";

            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");
            
            return rwp;
        }
//        try
//        {
//            Document doc = DocumentHelper.createDocument();
//            Element eBody = doc.addElement("message_content");
//            
//            String operid = (String)map.get("curOper");
//            String atsvNum = (String)map.get("atsvNum");
//            String menuid = (String)map.get("curMenuId");
//            String servNumber = (String)map.get("servNumber");
//            String memTelnum = (String)map.get("memTelnum");
//            String shortNum = (String)map.get("shortNum");
//            
//            // �ֻ�����
//            eBody.addElement("servNumber").addText(servNumber);
//            eBody.addElement("memTelnum").addText(memTelnum);
//            eBody.addElement("shortNum").addText(shortNum);
//            //�Ƿ񶩹���Ա��ѡ�Ż� 1���� ��������
//            eBody.addElement("addMustPriv").addText("0");
//            //�����Ʒ����
//            eBody.addElement("prodId").addText("");
//            //�Żݱ���
//            eBody.addElement("privId").addText("");
//            
//      
//            
//            Document docXML = intMsgUtil.createMsg(doc, "cli_add_familymem_sd", menuid, "", "1", servNumber, operid, atsvNum);
//            return intMsgUtil.invoke(docXML);
//        }
//        catch (Exception e)
//        {
//            return CommonUtil.getReturnWrap(SSReturnCode.ERROR);
//        }
    }
    
    /**
     * ���Ӽ�ͥ����Ա
    */
    public ReturnWrap addFamilyMem(Map<String, String> map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            		"<menuid>recAddFamilyMem</menuid><process_code>cli_add_familymem_sd</process_code>" +
            		"<verify_code/><resp_time>20140529173651</resp_time><sequence><req_seq>1</req_seq><operation_seq/>" +
            		"</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>Processing the request succeeded!</retmsg>" +
            		"</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><result>1</result></message_content>]]></message_body>" +
            		"</message_response>";
            System.out.println(response);
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");
            
            return rwp;
        }
    }
    /**
     * ҵ��ͳһ�˶��ӿ�
     */
    public ReturnWrap cancelService(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>recQrySPinfo</menuid><process_code>cli_busi_cancelsp</process_code><verify_code></verify_code>"
                + "<resp_time>20100921014415</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq></sequence>"
                + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                + "</retmsg></retinfo></message_head></message_response>";
        ReturnWrap rwp = new ReturnWrap();
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            rwp.setStatus(0);
            rwp.setReturnMsg("");
        }
        return rwp;
    }

    /**
     * �ֻ�֧�����˻���Ϣ��ѯ
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryCmpayAccount(Map map) {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>mainAccoutCharge</menuid><process_code>cli_qry_mpayacc</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921043608</resp_time><sequence><req_seq>8</req_seq><operation_seq>"
                    + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[�����ɹ�]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<boss_seq>531BIP2B09220100921043543521736</boss_seq><telnum>15153161532</telnum>"
                    + "<card_type>00</card_type><card_num>150102197511132076</card_num><user_name>����</user_name>"
                    + "<other_name>lczp</other_name><true_name>01</true_name><sacc_status>0</sacc_status>"
                    + "<acc_mess>|����|�������|�˻����䶯֪ͨ|</acc_mess><sacc_mess>|����|Ԥ��|����|</sacc_mess><cash>0</cash>"
                    + "<card>0</card><wait>0</wait></message_content>]]></message_body></message_response>";

            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }

    /**
     * �ֻ�֧�����˻���ֵ
     * 
     * @param map
     * @return
     */
    public ReturnWrap recMainFee(Map map) {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>cashchargemain</menuid><process_code>cli_busi_mpaycharge</process_code><verify_code>"
                    + "</verify_code><resp_time>20100921044459</resp_time><sequence><req_seq>11</req_seq><operation_seq>"
                    + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[�����ɹ�]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<boss_seq>531BIP2B09520100921044419949872</boss_seq><mpay_seq>10092100127173</mpay_seq>"
                    + "<cash>1000</cash></message_content>]]></message_body></message_response>";

            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }

    /**
     * ��������У������֤��
     * 
     * @param map
     * @return
     */
    public ReturnWrap checkIDCard(Map map) {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_busi_cidcheck</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<maxfailnum>3</maxfailnum><curfailnum>2</curfailnum>" 
//                    		"<subname>�����</subname><region></region>"
//                    + "<regionname></regionname><productid></productid><productname>������</productname>"
//                    + "<productgroup></productgroup><viptype></viptype><logintype></logintype><feeflag></feeflag>"
//                    + "<question></question><answer></answer><needcheckstr></needcheckstr><contactid></contactid>"
//                    + "<nettype></nettype><status></status><subage>10</subage><subscore>1000</subscore>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
//    	try
//        {
//            String telnum = (String)map.get("telnum");
//            String certid = (String)map.get("IDCard");
//            String operid = (String)map.get("operid");
//            String termid = (String)map.get("termid");
//            String menuid = (String)map.get("menuid");
//            String touchoid = (String)map.get("touchoid");
//            
//            Document docXML = null;
//            
//            Document doc = DocumentHelper.createDocument();
//            Element eBody = doc.addElement("message_content");
//            
//            // �ֻ�����
//            Element eReq_telnum = eBody.addElement("telnum");
//            eReq_telnum.addText(telnum);
//            
//            // ����֤��
//            Element eReq_certid = eBody.addElement("certid");
//            eReq_certid.addText(certid);
//            
//            docXML = intMsgUtil.createMsg(doc, "cli_busi_cidcheck", menuid, touchoid, "1", telnum, operid, termid);
//            return intMsgUtil.invoke(docXML);
//        }
//        catch (Exception e)
//        {
////            logger.error("����֤��֤ʧ�ܣ�", e);
//            
//            ReturnWrap rwp = new ReturnWrap();
//            rwp.setStatus(0);
//            rwp.setReturnMsg("");
//            
//            return rwp;
//        }
    }

    /**
     * ��������
     * 
     * @param map
     * @return
     */
    public ReturnWrap resetPassword(Map map) {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_busi_chargefee</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();

            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }

    /**
     * ��ѯԤԼ����
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryChooseTel(Map map) {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
                    + "<message_head version=\"1.0\"><menuid></menuid><process_code>cli_qry_numbynet</process_code>"
                    + "<verify_code></verify_code><resp_time>20110621111358</resp_time><sequence><req_seq>28</req_seq>"
                    + "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
                    + "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
                    + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<tellist><telnum>18709610009</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709611604</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709614964</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709617084</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709614472</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709610013</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709612745</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709610034</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709610001</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709610018</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709614134</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709610022</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709617424</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709610039</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709610006</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709614870</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709612647</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709610754</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709610016</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709617947</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709618430</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709618174</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709612074</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709610020</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709610037</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709610004</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709614740</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709610025</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709617154</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "<tellist><telnum>18709613914</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }

    /**
     * ԤԼ����
     * 
     * @param map
     * @return
     */
    public ReturnWrap chooseTel(Map map) {
        try {
        	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
        			+"<menuid>chooseTel</menuid><process_code>cli_busi_occupytelnew_sd</process_code><verify_code></verify_code>"
        			+"<resp_time>20140303105156</resp_time><sequence><req_seq>2</req_seq><operation_seq></operation_seq>"
        			+"</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
        			+"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
        			+"<message_content><orderid>88009898432252</orderid></message_content>]]></message_body></message_response>";
           
            // String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>
            // <message_response><message_head version=\"1.0\">" +
            // "<menuid>chooseTel</menuid><process_code>cli_busi_occupytel</process_code><verify_code>"
            // +
            // "</verify_code><resp_time>20110622160603</resp_time><sequence><req_seq>17</req_seq>"
            // +
            // "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>109</retcode>"
            // +
            // "<retmsg><![CDATA[��֤��ԤԼ���������Ѿ��ﵽ��󣬲�������Ԥ��!]]></retmsg></retinfo></message_head>"
            // +
            // "</ message_response>";

            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }

    /**
     * ��ֵ����ֵ�ɷ�
     * 
     * @param map
     * @return
     */
    public ReturnWrap cardPayCommit(Map map) {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_busi_chargefee</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content></message_content>]]></message_body></message_response>";

            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }

    /**
     * ��ѯ�û��Ƿ��ѿ�ͨ�ֻ�����
     * 
     * @param map
     * @return
     */
    public ReturnWrap qrymailBox(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>cli_qry_userinfo</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><havemailbox>0</havemailbox>"
                + "</message_content>]]></message_body></message_response>";

        try {
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("��ѯ�û��Ƿ��ѿ�ͨ�ֻ�����ʧ��!");

            return rwp;
        }
    }

    /**
     * ����139�������
     * 
     * @param map
     * @return
     */
    public ReturnWrap add139Mail(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>cli_qry_userinfo</process_code><verify_code></verify_code>"
                + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                + "</message_content>]]></message_body></message_response>";

        try {
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("��ͨ139����ʧ��!");

            return rwp;
        }
    }

    public ReturnWrap orderSPService(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>recQrySPinfo</menuid><process_code>cli_busi_chgsubsmonserv</process_code><verify_code></verify_code>"
                + "<resp_time>20100921014415</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq></sequence>"
                + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
                + "</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                + "<orderresult></orderresult><ordertime></ordertime><orderflag></orderflag></message_content>]]></message_body></message_response>";

        ReturnWrap rwp = new ReturnWrap();
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            rwp.setStatus(0);
            rwp.setReturnMsg("");
        }
        return rwp;
    }

    /**
     * ͨ��socket�ӿڲ�ѯ�굥��¼
     * 
     * @param map
     *            ���
     * @return
     * @see
     * @remark create g00140516 2011/12/09 R003C11L12n01 �굥��ѯʵ��socketЭ��
     */
    public ReturnWrap queryCDRListBySocket(Map map) {
        try {
            String cdrType = (String) map.get("CDRType");

            String response = "";

            String province = (String) PublicCache.getInstance().getCachedData(
                    Constants.PROVINCE_ID);
            if (Constants.PROOPERORGID_NX.equals(province)) {
                if ("1".equals(cdrType)) {
                    response = "gprs~559941~2011-06-18 00:36:28~9425336~��������~0.00;"
                            + "gprs~0~2011-06-18 01:36:29~0~��������~0.00;"
                            + "����~13909590065~2011-06-18 17:34:39~59~��������~0.22;"
                            + "��������~13895176008~2011-06-18 17:45:44~0~��������~0.10;"
                            + "����~13895389498~2011-06-18 17:47:38~144~��������~0.00;";
                } else if ("3".equals(cdrType)) {
                    response = "��������[��������]~929845|10658470~~2011-06-10 09:57:23~0.00~~����~0.00~;"
                            + "��������[��������]~929845|10658470~~2011-06-10 10:03:52~0.00~~����~0.00~;"
                            + "��������[��������]~929868|10658483~~2011-06-10 14:59:58~0.00~~����~0.00~;";
                } else if ("4".equals(cdrType)) {
                    response = "201106CMNET[��������]~2011-06-18 00:36:28~48429~9532.40~327.97~9204.43~0.00~0.19~546.82~8985.58;"
                            + "201106CMNET[��������]~2011-06-18 01:36:29~24057~0.00~0.00~0.00~0.00~0.00~0.00~0.00;";
                }
            }

            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(1);
            rwp.setReturnObject(response);

            return rwp;
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
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
        String response = "";

        // ȫ������
        if ("ALL".equals(feeType)) {
            if (Constants.CDRTYPE_FIXFEE.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryFixfeeMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary></billsummary><billitem>201201@_@01-01 -- 01-31@_@98�������ײ�@_@98|"
                        + "201201@_@01-01 -- 01-31@_@98�������ײ�@_@98|201201@_@01-01 -- 01-31@_@98�������ײ�@_@98</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_GSM.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary></billsummary><billitem>2011-08-01 21:56:04@_@�人@_@����@_@66174709@_@60@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
                        + "2011-08-02 21:56:04@_@�人@_@����@_@05387763965@_@610@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
                        + "2011-08-02 21:56:04@_@�人@_@����@_@66174709@_@132@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
                        + "2011-08-03 21:56:04@_@�人@_@����@_@66174709@_@45@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
                        + "2011-08-03 21:56:04@_@�人@_@����@_@66174709@_@390@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
                        + "2011-08-03 21:56:04@_@�人@_@����@_@66174709@_@88@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
                        + "2011-08-26 21:56:04@_@�人@_@����@_@66174709@_@269@_@���ʳ�;@_@@_@34.40|"
                        + "2011-08-27 21:56:04@_@�人@_@����@_@66174709@_@269@_@���ʳ�;@_@@_@5.07</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_SMS.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qrySmsMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary>3��@_@2��</billsummary>"
                        + "<billitem>2011-08-01 21:56:04@_@�ڵ�@_@10659001@_@����@_@SP����@_@��̳�ܱ�@_@@_@0.30|"
                        + "2011-08-03 21:56:04@_@�ڵ�@_@10659001@_@����@_@SP����@_@**@_@@_@0.10|"
                        + "2011-08-04 21:56:04@_@�ڵ�@_@139********@_@����@_@����@_@@_@�人���Ű�@_@0.00|"
                        + "2011-08-04 21:56:04@_@�۰�̨@_@139********@_@����@_@����@_@@_@@_@0.10|"
                        + "2011-08-04 21:56:04@_@����ATAT@_@139********@_@����@_@����@_@@_@@_@0.70"
                        + "</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_GPRSWLAN.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary></billsummary><billitem>"
                        + "2011-08-01 21:56:04@_@����@_@WLAN@_@12650@_@2018|"
                        + "2011-08-02 21:56:04@_@����@_@BLACKBERRY@_@150@_@20180|"
                        + "2011-08-02 21:56:04@_@����ATAT@_@CMNET@_@800@_@7|"
                        + "2011-08-03 21:56:04@_@�人@_@WLAN@_@800@_@7|"
                        + "2011-08-03 21:56:04@_@�人@_@WLAN@_@800@_@1256|</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_DISCOUNT.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        +
                        // "<billcount>1</billcount><billitem>08-01
                        // 21:56:04@_@�����Ż�@_@-76.00</billitem>" +
                        "<billcount>4</billcount><billitem>"
                        + "2011-08-02 21:56:04@_@�����Ż�@_@-76.00|2011-08-03 21:56:04@_@�����Ż�@_@-76.00|"
                        + "2011-08-02 21:56:04@_@�����Ż�@_@-76.00|2011-08-01 21:56:04@_@�����Ż�@_@-76.00</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_INFOFEE.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary>5</billsummary><billitem>"
                        + "2011-08-01 21:56:04@_@����@_@�����ؿ�@_@10658899@_@����@_@801005@_@�㲥@_@1.00|"
                        + "2011-08-02 21:56:04@_@����@_@�����ؿ�@_@10658899@_@����@_@801005@_@�㲥@_@1.00|"
                        + "2011-08-02 21:56:04@_@����@_@�����ؿ�@_@10658899@_@����@_@801005@_@�㲥@_@1.00|"
                        + "</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_ISMG.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        +
                        // "<billcount>1</billcount><billitem>08-01
                        // 21:56:04@_@WAP@_@����DIY@_@10658899@_@2</billitem>" +
                        "<billcount>5</billcount><billitem>2011-08-01 21:56:04@_@WAP@_@����DIY@_@10658899@_@2|"
                        + "2011-08-02 21:56:04@_@WAP@_@����DIY@_@10658899@_@1|2011-08-02 21:56:04@_@WAP@_@����DIY@_@10658899@_@0|"
                        + "2011-08-02 21:56:04@_@WAP@_@����DIY@_@10658899@_@1|2011-08-03 21:56:04@_@WAP@_@����DIY@_@10658899@_@2</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_OTHERFEE.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary></billsummary><billitem>"
                        + "2011-08-05 21:56:04@_@ΥԼ��@_@20.00|2011-08-05 21:56:04@_@Э�鲹�շ�@_@15.00|"
                        + "2011-08-05 21:56:04@_@�����@_@25.00</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            }
        }
        // �������
        else if ("1".equals(feeType)) {
            if (Constants.CDRTYPE_FIXFEE.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryFixfeeMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary></billsummary><billitem>201201@_@01-01 -- 01-31@_@98�������ײ�@_@98|"
                        + "201201@_@01-01 -- 01-31@_@98�������ײ�@_@98|201201@_@01-01 -- 01-31@_@98�������ײ�@_@98</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_GSM.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary></billsummary><billitem>"
                        + "2011-08-26 21:56:04@_@�人@_@����@_@66174709@_@269@_@���ʳ�;@_@@_@34.40|"
                        + "2011-08-27 21:56:04@_@�人@_@����@_@66174709@_@269@_@���ʳ�;@_@@_@5.07</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_SMS.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qrySmsMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary>3��@_@2��</billsummary>"
                        + "<billitem>2011-08-01 21:56:04@_@�ڵ�@_@10659001@_@����@_@SP����@_@��̳�ܱ�@_@@_@0.30|"
                        + "2011-08-03 21:56:04@_@�ڵ�@_@10659001@_@����@_@SP����@_@**@_@@_@0.10|"
                        + "2011-08-04 21:56:04@_@�۰�̨@_@139********@_@����@_@����@_@@_@@_@0.10|"
                        + "2011-08-04 21:56:04@_@����ATAT@_@139********@_@����@_@����@_@@_@@_@0.70"
                        + "</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_GPRSWLAN.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary></billsummary><billitem>"
                        + "2011-08-01 21:56:04@_@����@_@WLAN@_@12650@_@2018|"
                        + "2011-08-02 21:56:04@_@����@_@BLACKBERRY@_@150@_@20180|"
                        + "2011-08-02 21:56:04@_@����ATAT@_@CMNET@_@800@_@7|"
                        + "2011-08-03 21:56:04@_@�人@_@WLAN@_@800@_@7|"
                        + "2011-08-03 21:56:04@_@�人@_@WLAN@_@800@_@1256|</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_DISCOUNT.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billcount>4</billcount><billitem>"
                        + "2011-08-02 21:56:04@_@�����Ż�@_@-76.00|2011-08-03 21:56:04@_@�����Ż�@_@-76.00|"
                        + "2011-08-02 21:56:04@_@�����Ż�@_@-76.00|2011-08-01 21:56:04@_@�����Ż�@_@-76.00</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_INFOFEE.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary>5</billsummary><billitem>"
                        + "2011-08-01 21:56:04@_@����@_@�����ؿ�@_@10658899@_@����@_@801005@_@�㲥@_@1.00|"
                        + "2011-08-02 21:56:04@_@����@_@�����ؿ�@_@10658899@_@����@_@801005@_@�㲥@_@1.00|"
                        + "2011-08-02 21:56:04@_@����@_@�����ؿ�@_@10658899@_@����@_@801005@_@�㲥@_@1.00|"
                        + "</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_ISMG.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billcount>4</billcount><billitem>2011-08-01 21:56:04@_@WAP@_@����DIY@_@10658899@_@2|"
                        + "2011-08-02 21:56:04@_@WAP@_@����DIY@_@10658899@_@1|"
                        + "2011-08-02 21:56:04@_@WAP@_@����DIY@_@10658899@_@1|2011-08-03 21:56:04@_@WAP@_@����DIY@_@10658899@_@2</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_OTHERFEE.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>192</retcode><retmsg><![CDATA[No information!]]></retmsg></retinfo>"
                        + "</message_head></message_response>";
            }
        }
        // �����
        else {
            if (Constants.CDRTYPE_FIXFEE.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryFixfeeMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>192</retcode><retmsg><![CDATA[No Information!]]></retmsg></retinfo>"
                        + "</message_head></message_response>";
            } else if (Constants.CDRTYPE_GSM.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary></billsummary><billitem>2011-08-01 21:56:04@_@�人@_@����@_@66174709@_@60@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
                        + "2011-08-02 21:56:04@_@�人@_@����@_@66174709@_@89@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
                        + "2011-08-02 21:56:04@_@�人@_@����@_@66174709@_@132@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
                        + "2011-08-03 21:56:04@_@�人@_@����@_@66174709@_@45@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
                        + "2011-08-03 21:56:04@_@�人@_@����@_@66174709@_@390@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
                        + "2011-08-03 21:56:04@_@�人@_@����@_@66174709@_@88@_@�л�@_@ȫ��ͨ88�ײ�֮88Ԫ(G3��)@_@0.00|"
                        + "</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_SMS.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qrySmsMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary>3��@_@2��</billsummary>"
                        + "<billitem>2011-08-04 21:56:04@_@�ڵ�@_@139********@_@����@_@����@_@@_@�人���Ű�@_@0.00|"
                        + "</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_GPRSWLAN.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary></billsummary><billitem>"
                        + "2011-08-01 21:56:04@_@����@_@WLAN@_@12650@_@2018|"
                        + "2011-08-02 21:56:04@_@����@_@BLACKBERRY@_@150@_@20180|"
                        + "2011-08-02 21:56:04@_@����ATAT@_@CMNET@_@800@_@7|"
                        + "2011-08-03 21:56:04@_@�人@_@WLAN@_@800@_@7|"
                        + "2011-08-03 21:56:04@_@�人@_@WLAN@_@800@_@1256|</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_DISCOUNT.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>192</retcode><retmsg><![CDATA[No information!]]></retmsg></retinfo>"
                        + "</message_head></message_response>";
            } else if (Constants.CDRTYPE_INFOFEE.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary>5</billsummary><billitem>"
                        + "2011-08-01 21:56:04@_@����@_@�����ؿ�@_@10658899@_@����@_@801005@_@�㲥@_@0.00|"
                        + "</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_ISMG.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billcount>1</billcount><billitem>2011-08-02 21:56:04@_@WAP@_@����DIY@_@10658899@_@0|"
                        + "</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            } else if (Constants.CDRTYPE_OTHERFEE.equals(cdrType)) {
                response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                        + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                        + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                        + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                        + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                        + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                        + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                        + "<billsummary></billsummary><billitem>"
                        + "2011-08-05 21:56:04@_@ΥԼ��@_@20.00|2011-08-05 21:56:04@_@Э�鲹�շ�@_@15.00|"
                        + "2011-08-05 21:56:04@_@�����@_@25.00</billitem>"
                        + "</message_content>]]></message_body></message_response>";
            }
        }

        ReturnWrap rwp = new ReturnWrap();

        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            rwp.setStatus(0);
            rwp.setReturnMsg("");
        }
        return rwp;
    }

    /**
     * NG3.5���굥����֮��ѯ�ͻ���Ϣ
     * 
     * @param map
     * @return
     * @throws Exception
     * @remark create g00140516 2012/02/13 R003C12L02n01
     */
    public ReturnWrap queryCustomerInfo(Map<String, String> map) {
        String month = (String) map.get("month");
        String response = "";
        if ("201201".equals(month)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryMuster</menuid><process_code>cli_qry_custinfo</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<custname>��Ⱥ</custname><brandnm>���еش�</brandnm><productnm>��ҵ��</productnm><subsid>1234567890123456</subsid>"
                    + "<cyclelist><cycle>20120101</cycle><startdate>20120101</startdate><enddate>20120115</enddate>"
                    + "<acctid>9876543210123456</acctid><unionacct>0</unionacct></cyclelist>"
                    + "<cyclelist><cycle>20120116</cycle><startdate>20120116</startdate><enddate>20120131</enddate>"
                    + "<acctid>6543210123456789</acctid><unionacct>0</unionacct></cyclelist>"
                    + "</message_content>]]></message_body></message_response>";
        }
        if ("201112".equals(month)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryMuster</menuid><process_code>cli_qry_custinfo</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<custname>��Ⱥ</custname><brandnm>���еش�</brandnm><productnm>��ҵ��</productnm><subsid>1234567890123456</subsid>"
                    + "<cyclelist><cycle>20120101</cycle><startdate>20111201</startdate><enddate>20111231</enddate>"
                    + "<acctid>9876543210123456</acctid><unionacct>0</unionacct></cyclelist>"
                    + "</message_content>]]></message_body></message_response>";
        } else {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryMuster</menuid><process_code>cli_qry_custinfo</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<custname>��Ⱥ</custname><brandnm>���еش�</brandnm><productnm>��ҵ��</productnm><subsid>1234567890123456</subsid>"
                    + "<cyclelist><cycle>20120501</cycle><startdate>20120501</startdate><enddate>20120529</enddate>"
                    + "<acctid>9876543210123456</acctid><unionacct>0</unionacct></cyclelist>"
                    + "</message_content>]]></message_body></message_response>";
        }

        ReturnWrap rwp = new ReturnWrap();

        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            rwp.setStatus(0);
            rwp.setReturnMsg("");
        }
        return rwp;
    }

    /**
     * NG3.5���굥����֮�굥��ѯ
     * 
     * @param map
     * @return
     * @throws Exception
     * @remark create g00140516 2012/02/13 R003C12L02n01
     */
    public ReturnWrap queryDetailedRecordsSD2012(Map<String, String> map) {
        String response = "";
        String cdrType = map.get("CDRType");

        if ("1".equals(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>178.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum></gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum></wlantotalnum><wlantotalfee></wlantotalfee>"

                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    + "<billinfo>"
                    + "2012-1-12@_@20120101-20120129@_@168Ԫȫ��ͨ�л��ײ�@_@168.00|"
                    + "2012-1-12@_@20120101-20120129@_@10ԪGPRS��10M@_@10.00|"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("2".equals(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>0.60</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum></gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum></wlantotalnum><wlantotalfee></wlantotalfee>"
                    
                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    + "<billinfo>"
                    + "2011-1-15 9:09:57@_@����@_@����@_@18660187701@_@1Сʱ1��24��@_@�۰�̨@_@188Ԫ�л��ײ�@_@0.00@_@0.00|"
                    + "2011-1-15 9:28:18@_@����@_@����@_@13853183025@_@1��31��@_@@_@188Ԫ�л��ײ�@_@0.00@_@0.00|"
                    + "2011-1-15 9:34:11@_@����@_@����@_@18953190875@_@48��@_@@_@188Ԫ�л��ײ�@_@@_@|"
                    + "2011-1-16 14:28:06@_@�㶫����@_@����@_@15953105927@_@13��@_@����@_@188Ԫ�л��ײ�@_@0.00@_@0.30|"
                    + "2011-1-16 17:09:20@_@�㶫����@_@����@_@18660187701@_@11��@_@����@_@188Ԫ�л��ײ�@_@0.00@_@0.00|"
                    + "2011-1-16 17:21:34@_@�㶫����@_@����@_@13964060128@_@41��@_@����@_@188Ԫ�л��ײ�@_@0.00@_@0.30|"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("3".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    +
                    // "<totalfee>3.90</totalfee><smstotalnum>6</smstotalnum><mmstotalnum>2</mmstotalnum><gprstotalnum></gprstotalnum>"
                    // +
                    // "<gprstotalfee></gprstotalfee><wlantotalnum></wlantotalnum><wlantotalfee></wlantotalfee>"
                    // +
                    // "<billinfo>" +
                    // "2011-1-15
                    // 0:20:31@_@�ڵ�@_@13905320000@_@����@_@����@_@@_@8Ԫ���Ű�@_@0.00|"
                    // +
                    // "2011-1-15
                    // 0:25:36@_@�ڵ�@_@13905320000@_@����@_@����@_@@_@9Ԫ���Ű�@_@0.00|"
                    // +
                    // "2011-1-15
                    // 12:24:33@_@�۰�̨@_@13905311111@_@����@_@����@_@@_@10Ԫ���Ű�@_@0.00|"
                    // +
                    // "2011-1-16
                    // 0:20:31@_@�ڵ�@_@13905320000@_@����@_@����@_@@_@8Ԫ���Ű�@_@0.00|"
                    // +
                    // "2011-1-16
                    // 0:25:36@_@�ڵ�@_@13905320000@_@����@_@����@_@@_@9Ԫ���Ű�@_@0.00|"
                    // +
                    // "2011-1-16
                    // 12:24:33@_@�۰�̨@_@13905311111@_@����@_@����@_@@_@10Ԫ���Ű�@_@0.00|"
                    // +
                    // "2011-1-15
                    // 0:15:11@_@�ڵ�@_@1065812345@_@����@_@����@_@��̳�ܱ�@_@@_@1.30|" +
                    // "2011-1-15
                    // 0:25:31@_@�ڵ�@_@1065812345@_@����@_@����@_@��̳�ܱ�@_@@_@1.30|" +
                    // "2011-1-15
                    // 12:18:21@_@�۰�̨@_@1065812345@_@����@_@����@_@��̳�ܱ�@_@@_@1.30" +
                    // "</billinfo>" +
                    "<totalfee>0.80</totalfee><smstotalnum>4</smstotalnum><mmstotalnum>0</mmstotalnum>"
                    + "<gprstotalnum></gprstotalnum><gprstotalfee></gprstotalfee><wlantotalnum></wlantotalnum>"
                    + "<wlantotalfee></wlantotalfee>"

                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    + "<billinfo>2012-02-20 08:27:09@_@����@_@15165482328@_@����@_@"
                    + "���ڶ���@_@@_@@_@0.10|2012-02-20 19:08:40@_@����@_@13605385534@_@����@_@���ڶ���@_@@_@@_@0.10"
                    + "|2012-02-20 19:42:46@_@����@_@13605385534@_@����@_@���ڶ���@_@@_@@_@0.10|2012-02-20 20:43:14"
                    + "@_@����@_@008613605385534@_@�ն˷���@_@��ֵҵ�����շ���������@_@@_@@_@0.50|</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("4".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee></totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>2</gprstotalnum>"
                    + "<gprstotalfee>0.00</gprstotalfee><wlantotalnum>4</wlantotalnum><wlantotalfee>0.00</wlantotalfee>"

                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    +"<wiredtotalnum>wiredtotalnum</wiredtotalnum><wiredtotalfee>wiredtotalfee</wiredtotalfee><wiredtotaltime>wiredtotaltime</wiredtotaltime><wiredtotalflux>wiredtotalflux</wiredtotalflux>";
                    
                    // GprsWlan��ʾ�������Ż�ʱ���������ز�����1����ʾ  0������ʾ��
                    String showGprsWlanFlux = CommonUtil.getParamValue(Constants.SH_GPRSWLAN_SHOWFLUX);
                    if ("1".equals(showGprsWlanFlux))
                    {
                        response = response + "<billinfo>"
                        +
                        // "2012-04-01 04:22:46@_@ɽ��ʡ@_@CMNET(G��)@_@30��@_@3KB
                        // 7B@_@388Ԫ�ײ�@_@0.00|" +
                        // "2012-04-01
                        // 04:52:46@_@ɽ��ʡ@_@CMNET(G��)@_@30��@_@888B@_@388Ԫ�ײ�@_@0.00|"
                        // +
                        // "2012-04-16 16:05:49@_@ɽ��ʡ@_@CMWAP(G��)@_@30��@_@25KB
                        // 358B@_@388Ԫ�ײ�@_@0.00|" +
                        "2012-04-16 18:18:02@_@ɽ��ʡ@_@CMWAP(G��)@_@26��3��@_@3KB 552B@_@2KB 552B@_@ҵ������1@_@388Ԫ�ײ�@_@0.00|"
                        + "2012-04-16 19:14:10@_@ɽ��ʡ@_@CMWAP(G��)@_@30��@_@84KB 605B@_@83KB 605B@_@ҵ������2@_@388Ԫ�ײ�@_@0.00|"
                        + "2012-04-01 09:05:49@_@ɽ������@_@WLAN@_@2Сʱ6��8��@_@14MB 213KB@_@13MB 213KB@_@ҵ������3@_@10ԪWLANͳһ�ײ�@_@0.00|"
                        + "2012-04-01 13:09:11@_@ɽ������@_@WLAN@_@5Сʱ21��54��@_@10MB 493KB@_@9MB 493KB@_@ҵ������4@_@10ԪWLANͳһ�ײ�@_@0.00|"
                        + "2012-04-01 18:34:08@_@ɽ������@_@WLAN@_@7��7��@_@37KB@_@36KB@_@ҵ������5@_@10ԪWLANͳһ�ײ�@_@0.00|"
                        + "2012-04-05 08:38:45@_@ɽ������@_@WLAN@_@14��35��@_@436KB@_@435KB@_@ҵ������6@_@10ԪWLANͳһ�ײ�@_@0.00|"
                        + "</billinfo>"
                        + "</message_content>]]></message_body></message_response>";
                    }
                    else
                    {
                        response = response + "<billinfo>"
                        +
                        // "2012-04-01 04:22:46@_@ɽ��ʡ@_@CMNET(G��)@_@30��@_@3KB
                        // 7B@_@388Ԫ�ײ�@_@0.00|" +
                        // "2012-04-01
                        // 04:52:46@_@ɽ��ʡ@_@CMNET(G��)@_@30��@_@888B@_@388Ԫ�ײ�@_@0.00|"
                        // +
                        // "2012-04-16 16:05:49@_@ɽ��ʡ@_@CMWAP(G��)@_@30��@_@25KB
                        // 358B@_@388Ԫ�ײ�@_@0.00|" +
                        "2012-04-16 18:18:02@_@ɽ��ʡ@_@CMWAP(G��)@_@26��3��@_@3KB 552B@_@388Ԫ�ײ�@_@0.00|"
                        + "2012-04-16 19:14:10@_@ɽ��ʡ@_@CMWAP(G��)@_@30��@_@84KB 605B@_@388Ԫ�ײ�@_@0.00|"
                        + "2012-04-01 09:05:49@_@ɽ������@_@WLAN@_@2Сʱ6��8��@_@14MB 213KB@_@10ԪWLANͳһ�ײ�@_@0.00|"
                        + "2012-04-01 13:09:11@_@ɽ������@_@WLAN@_@5Сʱ21��54��@_@10MB 493KB@_@10ԪWLANͳһ�ײ�@_@0.00|"
                        + "2012-04-01 18:34:08@_@ɽ������@_@WLAN@_@7��7��@_@37KB@_@10ԪWLANͳһ�ײ�@_@0.00|"
                        + "2012-04-05 08:38:45@_@ɽ������@_@WLAN@_@14��35��@_@436KB@_@10ԪWLANͳһ�ײ�@_@0.00|"
                        + "</billinfo>"
                        + "</message_content>]]></message_body></message_response>";
                    }
        } else if ("5".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>28.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>10</gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum>2</wlantotalnum><wlantotalfee>5.00</wlantotalfee>"

                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    + "<billinfo>"
                    + "2012-1-15 23:50:01@_@����@_@����DIY@_@10658899@_@1Сʱ10��5��@_@2.00|"
                    + "2012-1-15 23:50:01@_@����@_@ũ��ͨ@_@12590@_@1Сʱ10��5��@_@10.00|"
                    + "2012-1-16 23:50:01@_@����@_@����DIY@_@10658899@_@1Сʱ10��5��@_@2.00|"
                    + "2012-1-16 23:50:01@_@����@_@ũ��ͨ@_@12590@_@1Сʱ10��5��@_@10.00"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("6".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>28.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>10</gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum>2</wlantotalnum><wlantotalfee>5.00</wlantotalfee>"

                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    + "<billinfo>"
                    + "2012-1-15 23:50:01@_@WAP@_@����@_@@_@3.00|"
                    + "2012-1-15 22:14:05@_@����@_@�»�ʱѶ@_@10658999@_@5.00|"
                    + "2012-1-15 23:05:18@_@WAP@_@�Ų��ܼ�@_@10652333@_@6.00|"
                    + "2012-1-16 23:50:01@_@WAP@_@����@_@@_@3.00|"
                    + "2012-1-16 22:14:05@_@����@_@�»�ʱѶ@_@10658999@_@5.00|"
                    + "2012-1-16 23:05:18@_@WA@_@�Ų��ܼ�@_@10652333@_@6.00"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("7".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>18.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>10</gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum>2</wlantotalnum><wlantotalfee>5.00</wlantotalfee>"

                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    + "<billinfo>"
                    + "2012-1-15 22:31:15@_@����@_@�����ؿ�@_@1065888@_@����������Ϣ���޹�˾@_@31��@_@2.00|"
                    + "2012-1-15 22:15:32@_@����@_@��������@_@10666666@_@�Ѻ�@_@1��20��@_@7.00|"
                    + "2012-1-17 22:31:15@_@����@_@�����ؿ�@_@1065888@_@����������Ϣ���޹�˾@_@31��@_@2.00|"
                    + "2012-1-17 22:15:32@_@����@_@��������@_@10666666@_@�Ѻ�@_@1��20��@_@7.00"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("8".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>18.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>10</gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum>2</wlantotalnum><wlantotalfee>5.00</wlantotalfee>"

                    + "<txtotaltime>txtotaltime</txtotaltime><thtotalfee>thtotalfee</thtotalfee><cttotalfee>cttotalfee</cttotalfee>"
                    + "<gprstotaltime>gprstotaltime</gprstotaltime><gprstotalflux>gprstotalflux</gprstotalflux><wlantotaltime>wlantotaltime</wlantotaltime>"
                    + "<wlantotalflux>wlantotalflux</wlantotalflux><zzywtotaltime>zzywtotaltime</zzywtotaltime><dsftotaltime>dsftotaltime</dsftotaltime>"
                    
                    + "<billinfo>"
                    + "2012-1-16 22:31:15@_@����@_@�����ؿ�@_@1065888@_@����������Ϣ���޹�˾@_@801005@_@�㲥��Ϣ��@_@2.00|"
                    + "2012-1-16 22:15:32@_@����@_@��������@_@10666666@_@�Ѻ�@_@901002@_@������Ϣ��@_@7.00|"
                    + "2012-1-18 22:31:15@_@����@_@�����ؿ�@_@1065888@_@����������Ϣ���޹�˾@_@801005@_@�㲥��Ϣ��@_@2.00|"
                    + "2012-1-18 22:15:32@_@����@_@��������@_@10666666@_@�Ѻ�@_@901002@_@������Ϣ��@_@7.00"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        }

        ReturnWrap rwp = new ReturnWrap();

        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            rwp.setStatus(0);
            rwp.setReturnMsg("");
        }
        return rwp;
    }

    public ReturnWrap queryDetailedRecordsNX2012(Map<String, String> map) {
        String response = "";
        String cdrType = map.get("CDRType");

        if ("1".equals(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>178.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum></gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum></wlantotalnum><wlantotalfee></wlantotalfee>"
                    + "<billinfo>"
                    + "2012-1-12@_@20120101-20120129@_@168Ԫȫ��ͨ�л��ײ�@_@168.00|"
                    + "2012-01-12@_@20120101-20120129@_@10ԪGPRS��10M@_@10.00|"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("2".equals(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>0.60</totalfee><localcall>localcall</localcall><longcall>longcall</longcall>"
                    + "<roamcall>roamcall</roamcall><citycall>citycall</citycall><othercall>othercall</othercall>"
                    + "<inlancall>inlancall</inlancall><colonycall>colonycall</colonycall><interncall>interncall</interncall>"
                    + "<inlanroam>inlanroam</inlanroam><colonyroam>colonyroam</colonyroam><internroam>internroam</internroam>"
                    + "<smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum></gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum></wlantotalnum><wlantotalfee></wlantotalfee>"
                    + "<billinfo>"
                    + "2011-1-15 9:09:57@_@����@_@����@_@18660187701@_@1Сʱ1��24��@_@�۰�̨@_@188Ԫ�л��ײ�@_@0.00|"
                    + "2011-1-15 9:28:18@_@����@_@����@_@13853183025@_@1��31��@_@@_@188Ԫ�л��ײ�@_@0.00|"
                    + "2011-1-15 9:34:11@_@����@_@����@_@18953190875@_@48��@_@@_@188Ԫ�л��ײ�@_@|"
                    + "2011-1-16 14:28:06@_@�㶫����@_@����@_@15953105927@_@13��@_@����@_@188Ԫ�л��ײ�@_@0.00|"
                    + "2011-1-16 17:09:20@_@�㶫����@_@����@_@18660187701@_@11��@_@����@_@188Ԫ�л��ײ�@_@0.00|"
                    + "2011-1-16 17:21:34@_@�㶫����@_@����@_@13964060128@_@41��@_@����@_@188Ԫ�л��ײ�@_@0.00|"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("3".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>3.90</totalfee><smstotalnum>6</smstotalnum><mmstotalnum>2</mmstotalnum><gprstotalnum></gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum></wlantotalnum><wlantotalfee></wlantotalfee>"
                    + "<billinfo>"
                    + "2011-1-15 0:20:31@_@�ڵ�@_@13905320000@_@����@_@����@_@@_@8Ԫ���Ű�@_@0.00|"
                    + "2011-1-15 0:25:36@_@�ڵ�@_@13905320000@_@����@_@����@_@@_@9Ԫ���Ű�@_@0.00|"
                    + "2011-1-15 12:24:33@_@�۰�̨@_@13905311111@_@����@_@����@_@@_@10Ԫ���Ű�@_@0.00|"
                    + "2011-1-16 0:20:31@_@�ڵ�@_@13905320000@_@����@_@����@_@@_@8Ԫ���Ű�@_@0.00|"
                    + "2011-1-16 0:25:36@_@�ڵ�@_@13905320000@_@����@_@����@_@@_@9Ԫ���Ű�@_@0.00|"
                    + "2011-1-16 12:24:33@_@�۰�̨@_@13905311111@_@����@_@����@_@@_@10Ԫ���Ű�@_@0.00|"
                    + "2011-1-15 0:15:11@_@�ڵ�@_@1065812345@_@����@_@����@_@��̳�ܱ�@_@@_@1.30|"
                    + "2011-1-15 0:25:31@_@�ڵ�@_@1065812345@_@����@_@����@_@��̳�ܱ�@_@@_@1.30|"
                    + "2011-1-15 12:18:21@_@�۰�̨@_@1065812345@_@����@_@����@_@��̳�ܱ�@_@@_@1.30"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("4".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee></totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprssum>gprssum</gprssum>"
                    + "<chargeflux>chargeflux</chargeflux><freechargeflux>freechargeflux</freechargeflux><sumflux>sumflux</sumflux>"
                    + "<wlansumtime>wlansumtime</wlansumtime><wlansumfee>wlansumfee</wlansumfee><wlansum>wlansum</wlansum>"
                    + "<wlansumflux>wlansumflux</wlansumflux><gprssumfee>gprssumfee</gprssumfee><cmwapsum>cmwapsum</cmwapsum>"
                    + "<cmnetsum>cmnetsum</cmnetsum><cmwapflux>cmwapflux</cmwapflux><cmnetflux>cmnetflux</cmnetflux>"
                    + "<cmwapfreeflux>cmwapfreeflux</cmwapfreeflux><cmnetfreeflux>cmnetfreeflux</cmnetfreeflux>"
                    + "<cmwapsumflux>cmwapsumflux</cmwapsumflux><cmnetsumflux>cmnetsumflux</cmnetsumflux><pubwlansum>pubwlansum</pubwlansum>"
                    + "<campuswlansum>campuswlansum</campuswlansum><pubwlanflux>pubwlanflux</pubwlanflux><campuswlanflux>campuswlanflux</campuswlanflux>"
                    + "<pubwlantime>pubwlantime</pubwlantime><campuswlantime>campuswlantime</campuswlantime><pubwlanfee>pubwlanfee</pubwlanfee>"
                    + "<campuswlanfee>campuswlanfee</campuswlanfee><cmwapfee>cmwapfee</cmwapfee><cmnetfee>cmnetfee</cmnetfee>"
                    + "<billinfo>"
                    + "2012-12-1 12:24:33@_@����@_@CMWAP(G��)@_@02:01:02@_@1MB1KB24B@_@5Ԫ��20M����@_@2G@_@0.00|"
                    + "2012-12-1 15:24:32@_@����@_@CMNET(T��)@_@01:03:00@_@1MB1KB25B@_@5Ԫ��21M����@_@2G@_@0.00|"
                    + "2012-12-2 13:24:33@_@����@_@@_@00:05:00@_@1MB1KB25B@_@5Ԫ������@_@2G@_@0.00|"
                    + "2012-12-2 15:24:32@_@����@_@@_@00:00:08@_@1MB1KB25B@_@5Ԫ������@_@2G@_@0.00|"
                    + "2012-12-2 17:24:33@_@����@_@WAP@_@00:05:00@_@@_@10Ԫ��50Сʱ@_@3G@_@0.00|"
                    + "2012-12-2 19:24:32@_@����@_@WAp@_@00:00:08@_@@_@10Ԫ��51Сʱ@_@3G@_@0.00"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("5".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>28.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>10</gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum>2</wlantotalnum><wlantotalfee>5.00</wlantotalfee>"
                    + "<billinfo>"
                    + "2012-1-15 23:50:01@_@����@_@����DIY@_@10658899@_@2.00|"
                    + "2012-1-15 23:50:01@_@����@_@ũ��ͨ@_@12590@_@10.00|"
                    + "2012-1-16 23:50:01@_@����@_@����DIY@_@10658899@_@2.00|"
                    + "2012-1-16 23:50:01@_@����@_@ũ��ͨ@_@12590@_@10.00"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("6".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>18.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>10</gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum>2</wlantotalnum><wlantotalfee>5.00</wlantotalfee>"
                    + "<billinfo>"
                    + "2012-1-15 22:31:15@_@����@_@�����ؿ�@_@1065888@_@����������Ϣ���޹�˾@_@@_@31��@_@2.00|"
                    + "2012-1-15 22:15:32@_@����@_@��������@_@10666666@_@�Ѻ�@_@@_@1��20��@_@7.00|"
                    + "2012-1-17 22:31:15@_@����@_@�����ؿ�@_@1065888@_@����������Ϣ���޹�˾@_@@_@31��@_@2.00|"
                    + "2012-1-17 22:15:32@_@����@_@��������@_@10666666@_@�Ѻ�@_@@_@1��20��@_@7.00"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        } else if ("7".equalsIgnoreCase(cdrType)) {
            response = "<?xml version=\"1.0\" encoding=\"GBK\"?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryGsmMuster</menuid><process_code>cli_qry_cdr2012</process_code><verify_code></verify_code>"
                    + "<req_time>20120201173850</req_time><req_seq>1</req_seq><unicontact></unicontact><testflag>1</testflag>"
                    + "<route><route_type>1</route_type><route_value>11111111111</route_value></route><channelinfo>"
                    + "<operatorid>admin</operatorid><channelid>bsacAtsv</channelid><unitid>HUAWEI</unitid></channelinfo>"
                    + "<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo>"
                    + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\"?><message_content>"
                    + "<totalfee>18.00</totalfee><smstotalnum></smstotalnum><mmstotalnum></mmstotalnum><gprstotalnum>10</gprstotalnum>"
                    + "<gprstotalfee></gprstotalfee><wlantotalnum>2</wlantotalnum><wlantotalfee>5.00</wlantotalfee>"
                    + "<billinfo>"
                    + "2012-1-16 22:31:15@_@����@_@�����ؿ�@_@1065888@_@����������Ϣ���޹�˾@_@801005@_@�㲥��Ϣ��@_@2.00|"
                    + "2012-1-16 22:15:32@_@����@_@��������@_@10666666@_@�Ѻ�@_@901002@_@������Ϣ��@_@7.00|"
                    + "2012-1-18 22:31:15@_@����@_@�����ؿ�@_@1065888@_@����������Ϣ���޹�˾@_@801005@_@�㲥��Ϣ��@_@2.00|"
                    + "2012-1-18 22:15:32@_@����@_@��������@_@10666666@_@�Ѻ�@_@901002@_@������Ϣ��@_@7.00"
                    + "</billinfo>"
                    + "</message_content>]]></message_body></message_response>";
        }

        ReturnWrap rwp = new ReturnWrap();

        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            rwp.setStatus(0);
            rwp.setReturnMsg("");
        }
        return rwp;
    }

    public SocketUtil getSocketUtil() {
        return socketUtil;
    }

    public void setSocketUtil(SocketUtil socketUtil) {
        this.socketUtil = socketUtil;
    }

    @Override
    public ReturnWrap valiIsfirstpwd(Map<String, String> map) {
        try {
            String response1 = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_busi_isfirstpwd</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>101</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response1);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            return rwp;
        }
    }

    @Override
    public ReturnWrap queryScoreChangeHis(Map map) {
        // TODO Auto-generated method stub
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
                    "<menuid>qryScore</menuid><process_code>cli_qry_scorechange</process_code><verify_code></verify_code>" +
                    "<unicontact></unicontact><resp_time>20120518174110</resp_time><sequence><req_seq>18</req_seq><operation_seq>" +
                    "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>" +
                    "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>" +
                    "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
                    "<message_content><responsesubpackge><col_1>����13963456288</col_1><col_2>autotest���ֻ���01</col_2>" +
                    "<col_3>���ù���-���Ͽͷ����⹤��(pinet001)</col_3><col_4>SD.LP</col_4><col_5>���ӽɷѿ�</col_5><col_6>0</col_6>" +
                    "<col_7>0.00</col_7><col_8></col_8><col_9>0.00</col_9><col_10>2009-06-19 14:58:24</col_10></responsesubpackge>" +
                    "<responsesubpackge><col_1>����13963456288</col_1><col_2>autotest���ֻ���01</col_2>" +
                    "<col_3>���ù���-���Ͽͷ����⹤��(pinet001)</col_3><col_4>SD.LP</col_4><col_5>���ӽɷѿ�</col_5>" +
                    "<col_6>0</col_6><col_7>0.00</col_7><col_8></col_8><col_9>0.00</col_9><col_10>2009-06-19 17:11:06</col_10>" +
                    "</responsesubpackge></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            
            return new ReturnWrap();
        }
    }

    @Override
    public ReturnWrap queryScoreDetailHis(Map map) {
        // TODO Auto-generated method stub

        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryScore</menuid><process_code>cli_qry_scroedetail</process_code><verify_code></verify_code>"
                    + "<unicontact></unicontact><resp_time>20120518155720</resp_time><sequence><req_seq>6</req_seq><operation_seq>"
                    + "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><responsesubpackge><col_1>6340963456288</col_1>"
                    + "<col_2>88009891730539</col_2><col_3>���ָ���</col_3><col_4>-20</col_4><col_5>CRMӪҵ��</col_5><col_6></col_6><col_7></col_7>"
                    + "<col_8>2012-04-16 16:54:14</col_8><col_9>��������</col_9><col_10>-20</col_10><col_11></col_11><col_12></col_12><col_13></col_13>"
                    + "<col_14></col_14><col_15></col_15><col_16>201204</col_16><col_17>13963456288</col_17><col_18>����13963456288</col_18><"
                    + "/responsesubpackge><responsesubpackge><col_1>6340963456288</col_1><col_2>88009891729892</col_2><col_3>���ָ���</col_3>"
                    + "<col_4>100</col_4><col_5>CRMӪҵ��</col_5><col_6></col_6><col_7></col_7><col_8>2012-04-16 15:44:10</col_8><col_9>������������</col_9>"
                    + "<col_10>100</col_10><col_11></col_11><col_12></col_12><col_13></col_13><col_14></col_14><col_15></col_15><col_16>201205</col_16>"
                    + "<col_17>13963456288</col_17><col_18>����13963456288</col_18></responsesubpackge><responsesubpackge><col_1>6340963456288</col_1>"
                    + "<col_2>88009891740313</col_2><col_3>���ָ���</col_3><col_4>5000</col_4><col_5>CRMӪҵ��</col_5><col_6></col_6><col_7></col_7>"
                    + "<col_8>2012-05-17 11:31:00</col_8><col_9>������������</col_9><col_10>5000</col_10><col_11></col_11><col_12></col_12><col_13></col_13>"
                    + "<col_14></col_14><col_15></col_15><col_16>201205</col_16><col_17>13963456288</col_17><col_18>����13963456288</col_18>"
                    + "</responsesubpackge></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            return new ReturnWrap();
        }
    }

    @Override
    public ReturnWrap queryScorePrizeHis(Map map) {
        // TODO Auto-generated method stub
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
                    "<menuid>qryScore</menuid><process_code>cli_qry_scoreprize</process_code><verify_code>" +
                    "</verify_code><resp_time>20120518160755</resp_time><sequence><req_seq>3</req_seq>" +
                    "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode>" +
                    "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>" +
                    "<![CDATA[<message_content><responsesubpackge><col_1>2012-03-01 09:50:50</col_1><col_2>��̬��Ӫ���</col_2>" +
                    "<col_3>����</col_3><col_4>ϵͳ����Ա(101)</col_4></responsesubpackge><responsesubpackge><col_1>2012-03-01 09:50:50</col_1>" +
                    "<col_2>��̬��Ӫ���</col_2><col_3>����</col_3><col_4>ϵͳ����Ա(101)</col_4></responsesubpackge><responsesubpackge>" +
                    "<col_1>2012-03-01 09:50:50</col_1><col_2>��̬��Ӫ���</col_2><col_3>����</col_3><col_4>ϵͳ����Ա(101)</col_4>" +
                    "</responsesubpackge></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }
    
    public ReturnWrap qryRecStatusHub(MsgHeaderPO msgHeader, String nCode, String operType)
    {
        try {
            String response = "<message_response><message_head version=\"1.0\"><menuid>recCallDisplay</menuid><process_code>" +
                    "cli_qry_recstatus_hub</process_code><verify_code></verify_code><resp_time>20120619185704</resp_time><sequence>" +
                    "<req_seq>2</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode>" +
                    "<retmsg><![CDATA[GPRS10:1]]></retmsg></retinfo></message_head><message_body><![CDATA[<message_content>" +
                    "<recinfo><col0>GPRS10</col0><col1>1</col1></recinfo></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }
    
    /** 
     * ��Ʒ���ٷ���-�û��Ѷ�����Ʒ��Ϣ��ѯ
     * 
     * @param msgHeader ����ͷ��Ϣ
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryHasProds(MsgHeaderPO msgHeader)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><Envelope><Header><request_head version=\"1.0\">"
                + "<menuid>qryServiceHistory</menuid><process_code>cli_qry_rechistory</process_code>"
                + "<verify_code></verify_code><resp_time>20100921002233</resp_time>"
                + "<sequence><req_seq>40</req_seq><operation_seq></operation_seq></sequence><retinfo>"
                + "<rettype>0</rettype><retcode>100</retcode>"
                + "<retmsg><![CDATA[Processing the request succeeded!]]>"
                + "</retmsg></retinfo></request_head></Header>"
                + "<Body>"
                + "<cli_qry_addedprodlistresp>" + 
                
                // ��Ʒ�� ncode:ncode_001 (�������õ�prodid)
                "<crset>" +
                    "<prodid></prodid>" +//�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ز�Ʒ���룻�������ء���
                    "<prodname></prodname>" +//��Ʒ����
                    "<attrparam></attrparam>" +//�������Դ�
                    "<serviceres></serviceres>" +//������Դ��
                    "<recdate>20100303210238</recdate>" +//����ʱ�䣬��ʽ�� yyyymmddhh24miss
                    "<startdate>20100303210238</startdate>" +//��ʼʱ�䣬��ʽ�� yyyymmddhh24miss
                    "<enddate>20100303210238</enddate>" +//����ʱ�䣬��ʽ�� yyyymmddhh24miss
                    "<state>1</state>" +//״̬ 0��ԤԼ1������2����ͣ3�����˶�
                    "<formnum></formnum>" +//������ˮ
                    "<pkgtype></pkgtype>" +//�ײʹ���
                    "<proddesc></proddesc>" +//��Ʒ����
                    "<doneenum></doneenum>" +//���ͷ�
                    "<doneerelaid></doneerelaid>" +//���͹�ϵ����
                    "<pkgname></pkgname>" +//�ײʹ�������
                    "<canceldate></canceldate>" +//ȡ��ʱ�䣬��ʽ�� yyyymmddhh24miss
                    "<pkgid></pkgid>" +//�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ز�Ʒ���������ģ����룻�������ء���
                    "<prodtype>1</prodtype>" +//���ֶ����ڱ�ʶ�ǲ�Ʒ������NCODE����Ϊ0��ʶΪNCODE��ôCOL_0�����ֵ��ΪNCODE����Ϊ1��COL_1��������ݾ��ǲ�Ʒ��
                    "<privid></privid>" + //�Żݱ���
                    "<ncode>ncode_001</ncode>" + //�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ء�������������ncode
                "</crset>" + 
                
                // �����Ӳ�Ʒ subproductcode001
                "<crset>" +
                        "<prodid>subproductcode001</prodid>" +//�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ز�Ʒ���룻�������ء���
                        "<prodname>��Ʒ����_subproductcode001</prodname>" +//��Ʒ����
                        "<attrparam></attrparam>" +//�������Դ�
                        "<serviceres></serviceres>" +//������Դ��
                        "<recdate>20100303210238</recdate>" +//����ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<startdate>20100303210238</startdate>" +//��ʼʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<enddate>20100303210238</enddate>" +//����ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<state>1</state>" +//״̬ 0��ԤԼ1������2����ͣ3�����˶�
                        "<formnum></formnum>" +//������ˮ
                        "<pkgtype></pkgtype>" +//�ײʹ���
                        "<proddesc></proddesc>" +//��Ʒ����
                        "<doneenum></doneenum>" +//���ͷ�
                        "<doneerelaid></doneerelaid>" +//���͹�ϵ����
                        "<pkgname></pkgname>" +//�ײʹ�������
                        "<canceldate></canceldate>" +//ȡ��ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<pkgid>pkgcode_001</pkgid>" +//�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ز�Ʒ���������ģ����룻�������ء���
                        "<prodtype></prodtype>" +//���ֶ����ڱ�ʶ�ǲ�Ʒ������NCODE����Ϊ0��ʶΪNCODE��ôCOL_0�����ֵ��ΪNCODE����Ϊ1��COL_1��������ݾ��ǲ�Ʒ��
                        "<privid></privid>" + //�Żݱ���
                        "<ncode>subprod_ncode_001</ncode>" + //�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ء�������������ncode
                  "</crset>" +
                  
                  // �����Ӳ�Ʒ subproductcode002
                  "<crset>" +
                        "<prodid>subproductcode002</prodid>" +//�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ز�Ʒ���룻�������ء���
                        "<prodname>��Ʒ����_subproductcode002</prodname>" +//��Ʒ����
                        "<attrparam></attrparam>" +//�������Դ�
                        "<serviceres></serviceres>" +//������Դ��
                        "<recdate>20100303210238</recdate>" +//����ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<startdate>20100303210238</startdate>" +//��ʼʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<enddate>20100303210238</enddate>" +//����ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<state>1</state>" +//״̬ 0��ԤԼ1������2����ͣ3�����˶�
                        "<formnum></formnum>" +//������ˮ
                        "<pkgtype></pkgtype>" +//�ײʹ���
                        "<proddesc></proddesc>" +//��Ʒ����
                        "<doneenum></doneenum>" +//���ͷ�
                        "<doneerelaid></doneerelaid>" +//���͹�ϵ����
                        "<pkgname></pkgname>" +//�ײʹ�������
                        "<canceldate></canceldate>" +//ȡ��ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<pkgid>pkgcode_001</pkgid>" +//�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ز�Ʒ���������ģ����룻�������ء���
                        "<prodtype></prodtype>" +//���ֶ����ڱ�ʶ�ǲ�Ʒ������NCODE����Ϊ0��ʶΪNCODE��ôCOL_0�����ֵ��ΪNCODE����Ϊ1��COL_1��������ݾ��ǲ�Ʒ��
                        "<privid></privid>" + //�Żݱ���
                        "<ncode>subprod_ncode_001</ncode>" + //�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ء�������������ncode
                  "</crset>" +
                        
                   // �����Ӳ�Ʒ�µ��Ż�  subproductcode002 - > privid_001
                   "<crset>" +
                        "<prodid>subproductcode002</prodid>" +//�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ز�Ʒ���룻�������ء���
                        "<prodname>��Ʒ����_subproductcode002</prodname>" +//��Ʒ����
                        "<attrparam></attrparam>" +//�������Դ�
                        "<serviceres></serviceres>" +//������Դ��
                        "<recdate>20100303210238</recdate>" +//����ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<startdate>20100303210238</startdate>" +//��ʼʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<enddate>20100303210238</enddate>" +//����ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<state>1</state>" +//״̬ 0��ԤԼ1������2����ͣ3�����˶�
                        "<formnum></formnum>" +//������ˮ
                        "<pkgtype></pkgtype>" +//�ײʹ���
                        "<proddesc></proddesc>" +//��Ʒ����
                        "<doneenum></doneenum>" +//���ͷ�
                        "<doneerelaid></doneerelaid>" +//���͹�ϵ����
                        "<pkgname></pkgname>" +//�ײʹ�������
                        "<canceldate></canceldate>" +//ȡ��ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<pkgid>pkgcode_001</pkgid>" +//�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ز�Ʒ���������ģ����룻�������ء���
                        "<prodtype></prodtype>" +//���ֶ����ڱ�ʶ�ǲ�Ʒ������NCODE����Ϊ0��ʶΪNCODE��ôCOL_0�����ֵ��ΪNCODE����Ϊ1��COL_1��������ݾ��ǲ�Ʒ��
                        "<privid>privid_001</privid>" + //�Żݱ���
                        "<ncode>subprod_ncode_001</ncode>" + //�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ء�������������ncode
                  "</crset>"
                        
                   + "<crset>" +
                        "<prodid>subproductcode001</prodid>" +//�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ز�Ʒ���룻�������ء���
                        "<prodname>C06</prodname>" +//��Ʒ����
                        "<attrparam></attrparam>" +//�������Դ�
                        "<serviceres></serviceres>" +//������Դ��
                        "<recdate>20100303210238</recdate>" +//����ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<startdate>20100303210238</startdate>" +//��ʼʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<enddate>20100303210238</enddate>" +//����ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<state>1</state>" +//״̬ 0��ԤԼ1������2����ͣ3�����˶�
                        "<formnum></formnum>" +//������ˮ
                        "<pkgtype></pkgtype>" +//�ײʹ���
                        "<proddesc></proddesc>" +//��Ʒ����
                        "<doneenum></doneenum>" +//���ͷ�
                        "<doneerelaid></doneerelaid>" +//���͹�ϵ����
                        "<pkgname></pkgname>" +//�ײʹ�������
                        "<canceldate></canceldate>" +//ȡ��ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<prodpkgid></prodpkgid>" +//�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ز�Ʒ���������ģ����룻�������ء���
                        "<ncode>C0615</ncode>" +//�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ء�������������ncode
                  "</crset>"
                   
                        
                  + "<crset>" +
                        "<prodid>subproductcode001</prodid>" +//�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ز�Ʒ���룻�������ء���
                        "<prodname>C06</prodname>" +//��Ʒ����
                        "<attrparam>addarrtid_003=15965613173=#addarrtid_001=DXTCB5</attrparam>" +//�������Դ�
                        "<serviceres></serviceres>" +//������Դ��
                        "<recdate>20100303210238</recdate>" +//����ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<startdate>20100303210238</startdate>" +//��ʼʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<enddate>20100303210238</enddate>" +//����ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<state>1</state>" +//״̬ 0��ԤԼ1������2����ͣ3�����˶�
                        "<formnum></formnum>" +//������ˮ
                        "<pkgtype></pkgtype>" +//�ײʹ���
                        "<proddesc></proddesc>" +//��Ʒ����
                        "<doneenum></doneenum>" +//���ͷ�
                        "<doneerelaid></doneerelaid>" +//���͹�ϵ����
                        "<pkgname></pkgname>" +//�ײʹ�������
                        "<canceldate></canceldate>" +//ȡ��ʱ�䣬��ʽ�� yyyymmddhh24miss
                        "<prodpkgid></prodpkgid>" +//�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ز�Ʒ���������ģ����룻�������ء���
                        "<ncode>C061</ncode>" +//�Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ء�������������ncode
                  "</crset>"

              
                + "</cli_qry_addedprodlistresp></Body></Envelope>";
        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtilNew.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rwp;
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
     */
    public ReturnWrap qryAddAttr(MsgHeaderPO msgHeader, String qryType, String nCode, String operType)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><Envelope><Header><request_head version=\"1.0\">"
            + "<menuid>qryServiceHistory</menuid><process_code>cli_qry_rechistory</process_code>"
            + "<verify_code></verify_code><resp_time>20100921002233</resp_time>"
            + "<sequence><req_seq>40</req_seq><operation_seq></operation_seq></sequence><retinfo>"
            + "<rettype>0</rettype><retcode>100</retcode>"
            + "<retmsg><![CDATA[Processing the request succeeded!]]>"
            + "</retmsg></retinfo></request_head></Header>"
            + "<Body>"
                + "<cli_qry_prodattrresp>"
                
             // ����ı�
                + "<crset>" +
                    "<objid>objid</objid>" + // �������
                    "<attrid>addarrtid_003</attrid>" + // �������Ա���
                    "<attrname>�������1</attrname>" + // ������������
                    "<attrtype>EDIT</attrtype>" + // ������������ MEMO�����б༭�� SINGLE�����б༭�� DATE��ʱ��༭�� SELECT�������б�
                    "<valuetype>STRING</valuetype>" + // ֵ���� NUMBER������ STRING���ַ���
                    "<minlength>11</minlength>" + // ��������ֵ��С����
                    "<maxlength>11</maxlength>" + // ��������ֵ��󳤶�
                    "<ismandatory>1</ismandatory>" + // �Ƿ����
                    "<isshow>1</isshow>" + // �Ƿ����չ�� 0�����治չʾ 1������չʾ���Ա༭ 2������չʾ���ܱ༭
                    "<attrnum>3</attrnum>" + // ����ֵ����
                    "<attrsplit>@</attrsplit>" + // �������Էָ���
                    "<attrvalue>13645319981@13645319982@13645319984</attrvalue>" + // �û�����ֵ������û�û�ж��������Ĭ��ֵ��
                    "<dictinfo></dictinfo>" + // �ֵ���Ϣ����ʽ�� dictid=dictname|dictid=dictname
                    "<objtype></objtype>" + // ��Ʒ/�Ż�/����
                "</crset>" 
               
               // �����ı�
               + "<crset>" +
                   "<objid>objid</objid>" + // �������
                   "<attrid>addarrtid_004</attrid>" + // �������Ա���
                   "<attrname>�������2</attrname>" + // ������������
                   "<attrtype>EDIT</attrtype>" + // ������������ MEMO�����б༭�� SINGLE�����б༭�� DATE��ʱ��༭�� SELECT�������б�
                   "<valuetype>STRING</valuetype>" + // ֵ���� NUMBER������ STRING���ַ���
                   "<minlength>11</minlength>" + // ��������ֵ��С����
                   "<maxlength>11</maxlength>" + // ��������ֵ��󳤶�
                   "<ismandatory>1</ismandatory>" + // �Ƿ����
                   "<isshow>2</isshow>" + // �Ƿ����չ�� 0�����治չʾ 1������չʾ���Ա༭ 2������չʾ���ܱ༭
                   "<attrnum>1</attrnum>" + // ����ֵ����
                   "<attrsplit>@</attrsplit>" + // �������Էָ���
                   "<attrvalue>13645319982</attrvalue>" + // �û�����ֵ������û�û�ж��������Ĭ��ֵ��
                   "<dictinfo></dictinfo>" + // �ֵ���Ϣ����ʽ�� dictid=dictname|dictid=dictname
                   "<objtype></objtype>" + // ��Ʒ/�Ż�/����
              "</crset>"
               
               // �����
               + "<crset>" +
                   "<objid>objid</objid>" + // �������
                   "<attrid>addarrtidbyPASSWORD_001</attrid>" + // �������Ա���
                   "<attrname>����1</attrname>" + // ������������
                   "<attrtype>PASSWORD</attrtype>" + // ������������ MEMO�����б༭�� SINGLE�����б༭�� DATE��ʱ��༭�� SELECT�������б�
                   "<valuetype>STRING</valuetype>" + // ֵ���� NUMBER������ STRING���ַ���
                   "<minlength>6</minlength>" + // ��������ֵ��С����
                   "<maxlength>6</maxlength>" + // ��������ֵ��󳤶�
                   "<ismandatory>1</ismandatory>" + // �Ƿ����
                   "<isshow>1</isshow>" + // �Ƿ����չ�� 0�����治չʾ 1������չʾ���Ա༭ 2������չʾ���ܱ༭
                   "<attrnum>1</attrnum>" + // ����ֵ����
                   "<attrsplit>@</attrsplit>" + // �������Էָ���
                   "<attrvalue></attrvalue>" + // �û�����ֵ������û�û�ж��������Ĭ��ֵ��
                   "<dictinfo></dictinfo>" + // �ֵ���Ϣ����ʽ�� dictid=dictname|dictid=dictname
                   "<objtype></objtype>" + // ��Ʒ/�Ż�/����
               "</crset>" +
               
               // �����
               "<crset>" +
                   "<objid>objid</objid>" + // �������
                   "<attrid>addarrtidbyPASSWORD_002</attrid>" + // �������Ա���
                   "<attrname>����2</attrname>" + // ������������
                   "<attrtype>PASSWORD</attrtype>" + // ������������ MEMO�����б༭�� SINGLE�����б༭�� DATE��ʱ��༭�� SELECT�������б�
                   "<valuetype>STRING</valuetype>" + // ֵ���� NUMBER������ STRING���ַ���
                   "<minlength>6</minlength>" + // ��������ֵ��С����
                   "<maxlength>6</maxlength>" + // ��������ֵ��󳤶�
                   "<ismandatory>1</ismandatory>" + // �Ƿ����
                   "<isshow>2</isshow>" + // �Ƿ����չ�� 0�����治չʾ 1������չʾ���Ա༭ 2������չʾ���ܱ༭
                   "<attrnum>1</attrnum>" + // ����ֵ����
                   "<attrsplit>@</attrsplit>" + // �������Էָ���
                   "<attrvalue>123456</attrvalue>" + // �û�����ֵ������û�û�ж��������Ĭ��ֵ��
                   "<dictinfo></dictinfo>" + // �ֵ���Ϣ����ʽ�� dictid=dictname|dictid=dictname
                   "<objtype></objtype>" + // ��Ʒ/�Ż�/����
               "</crset>" +
               
               // �����ı�
               "<crset>" +
                   "<objid>objid</objid>" + // �������
                   "<attrid>addarrtidbyTEXTAREA_001</attrid>" + // �������Ա���
                   "<attrname>�����ı�</attrname>" + // ������������
                   "<attrtype>TEXTAREA</attrtype>" + // ������������ MEMO�����б༭�� SINGLE�����б༭�� DATE��ʱ��༭�� SELECT�������б�
                   "<valuetype>STRING</valuetype>" + // ֵ���� NUMBER������ STRING���ַ���
                   "<minlength>6</minlength>" + // ��������ֵ��С����
                   "<maxlength>20</maxlength>" + // ��������ֵ��󳤶�
                   "<ismandatory>1</ismandatory>" + // �Ƿ����
                   "<isshow>1</isshow>" + // �Ƿ����չ�� 0�����治չʾ 1������չʾ���Ա༭ 2������չʾ���ܱ༭
                   "<attrnum>1</attrnum>" + // ����ֵ����
                   "<attrsplit>@</attrsplit>" + // �������Էָ���
                   "<attrvalue></attrvalue>" + // �û�����ֵ������û�û�ж��������Ĭ��ֵ��
                   "<dictinfo></dictinfo>" + // �ֵ���Ϣ����ʽ�� dictid=dictname|dictid=dictname
                   "<objtype></objtype>" + // ��Ʒ/�Ż�/����
               "</crset>" +
               
               // �����ı�
               "<crset>" +
                   "<objid>objid</objid>" + // �������
                   "<attrid>addarrtidbyMONEY_001</attrid>" + // �������Ա���
                   "<attrname>����</attrname>" + // ������������
                   "<attrtype>MONEY</attrtype>" + // ������������ MEMO�����б༭�� SINGLE�����б༭�� DATE��ʱ��༭�� SELECT�������б�
                   "<valuetype>STRING</valuetype>" + // ֵ���� NUMBER������ STRING���ַ���
                   "<minlength>6</minlength>" + // ��������ֵ��С����
                   "<maxlength>20</maxlength>" + // ��������ֵ��󳤶�
                   "<ismandatory>1</ismandatory>" + // �Ƿ����
                   "<isshow>1</isshow>" + // �Ƿ����չ�� 0�����治չʾ 1������չʾ���Ա༭ 2������չʾ���ܱ༭
                   "<attrnum>1</attrnum>" + // ����ֵ����
                   "<attrsplit>@</attrsplit>" + // �������Էָ���
                   "<attrvalue></attrvalue>" + // �û�����ֵ������û�û�ж��������Ĭ��ֵ��
                   "<dictinfo></dictinfo>" + // �ֵ���Ϣ����ʽ�� dictid=dictname|dictid=dictname
                   "<objtype></objtype>" + // ��Ʒ/�Ż�/����
               "</crset>" +
               
               // ��ѡ
               "<crset>" +
                    "<objid>objid</objid>" + // �������
                    "<attrid>addarrtidSELECT_001</attrid>" + // �������Ա���
                    "<attrname>�������</attrname>" + // ������������
                    "<attrtype>SELECT</attrtype>" + // ������������ MEMO�����б༭�� SINGLE�����б༭�� DATE��ʱ��༭�� SELECT�������б�
                    "<valuetype>NUMBER</valuetype>" + // ֵ���� NUMBER������ STRING���ַ���
                    "<minlength></minlength>" + // ��������ֵ��С����
                    "<maxlength></maxlength>" + // ��������ֵ��󳤶�
                    "<ismandatory>1</ismandatory>" + // �Ƿ����
                    "<isshow>1</isshow>" + // �Ƿ����չ�� 0�����治չʾ 1������չʾ���Ա༭ 2������չʾ���ܱ༭
                    "<attrnum>2</attrnum>" + // ����ֵ����
                    "<attrsplit>@</attrsplit>" + // �������Էָ���
                    "<attrvalue>0531@0532</attrvalue>" + // �û�����ֵ������û�û�ж��������Ĭ��ֵ��
                    "<dictinfo>0531=����|0532=�ൺ|0533=�Ͳ�|0534=Ϋ��|0536=�ĳ�|0635=����</dictinfo>" + // �ֵ���Ϣ����ʽ�� dictid=dictname|dictid=dictname
                    "<objtype></objtype>" + // ��Ʒ/�Ż�/����
                "</crset>" +
                    
                // ��ѡ
                "<crset>" +
                     "<objid>objid</objid>" + // �������
                     "<attrid>addarrtidSELECT_002</attrid>" + // �������Ա���
                     "<attrname>��ѡ</attrname>" + // ������������
                     "<attrtype>SELECT</attrtype>" + // ������������ MEMO�����б༭�� SINGLE�����б༭�� DATE��ʱ��༭�� SELECT�������б�
                     "<valuetype>NUMBER</valuetype>" + // ֵ���� NUMBER������ STRING���ַ���
                     "<minlength></minlength>" + // ��������ֵ��С����
                     "<maxlength></maxlength>" + // ��������ֵ��󳤶�
                     "<ismandatory>1</ismandatory>" + // �Ƿ����
                     "<isshow>2</isshow>" + // �Ƿ����չ�� 0�����治չʾ 1������չʾ���Ա༭ 2������չʾ���ܱ༭
                     "<attrnum>1</attrnum>" + // ����ֵ����
                     "<attrsplit>@</attrsplit>" + // �������Էָ���
                     "<attrvalue>0531</attrvalue>" + // �û�����ֵ������û�û�ж��������Ĭ��ֵ��
                     "<dictinfo>0531=����|0532=�ൺ|0533=�Ͳ�|0534=Ϋ��|0536=�ĳ�|0635=����</dictinfo>" + // �ֵ���Ϣ����ʽ�� dictid=dictname|dictid=dictname
                     "<objtype></objtype>" + // ��Ʒ/�Ż�/����
                 "</crset>"
                         
              
                        + "</cli_qry_prodattrresp></Body></Envelope>";
        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtilNew.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rwp;
    }
    
    /** 
     * ��Ʒ���ٷ���-��Ʒ����
     * 
     * @param msgHeader MsgHeaderPO
     * @param multiProdCommitPO MultiProdCommitPO
     * @return ReturnWrap
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap prodRec(MsgHeaderPO msgHeader, MultiProdCommitPO multiProdCommitPO)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><Envelope><Header><request_head version=\"1.0\">"
            + "<menuid>qryServiceHistory</menuid><process_code>cli_qry_rechistory</process_code>"
            + "<verify_code></verify_code><resp_time>20100921002233</resp_time>"
            + "<sequence><req_seq>40</req_seq><operation_seq></operation_seq></sequence><retinfo>"
            + "<rettype>0</rettype><retcode>100</retcode>"
            + "<retmsg><![CDATA[Processing the request succeeded!]]>"
            + "</retmsg></retinfo></request_head></Header>"
            + "<Body>"
                
            + "</Body></Envelope>";

        try {
            return intMsgUtilNew.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);

            return rwp;
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
     */
    public ReturnWrap qrySubProds(MsgHeaderPO msgHeader, String nCode, String type, String optType)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><Envelope><Header><request_head version=\"1.0\">"
            + "<menuid>qryServiceHistory</menuid><process_code>cli_qry_rechistory</process_code>"
            + "<verify_code></verify_code><resp_time>20100921002233</resp_time>"
            + "<sequence><req_seq>40</req_seq><operation_seq></operation_seq></sequence><retinfo>"
            + "<rettype>0</rettype><retcode>100</retcode>"
            + "<retmsg><![CDATA[Processing the request succeeded!]]>"
            + "</retmsg></retinfo></request_head></Header>"
            + "<Body>"
                + "<cli_qry_prodlistresp>"
                + "<tagset>" 
                + "<minprod>2</minprod>" // ��ѡ��Ʒ��С��
                + "</tagset>" 
                + "<tagset>" 
                + "<maxprod>2</maxprod>" // ��ѡ����Ʒ��
                + "</tagset>" 

                + "<crset>" 
                    + "<pkgid>pkg001</pkgid>"
                    + "<prodid>subproductcode001</prodid>" // ��ֵ��Ʒ����
                    + "<privid></privid>" // �Żݱ���
                    + "<prodname>�Ӳ�Ʒ001</prodname>" // ��Ʒ����
                    + "<ismandatory>0</ismandatory>" // ѡ�����ͣ���ѡ�ͱ�ѡ�� 0:��ѡ 1:��ѡ
                    + "<hasattr>1</hasattr>"  // �Ƿ��и�������
                    + "<hasattr>PCIntRelaNormal</hasattr>" // ��ͨҵ��PCIntRelaNormal ҵ���л�:PCIntReladio��
                + "</crset>"
                
                // ��Ʒ���µ��Ӳ�Ʒ��subproductcode002��
                + "<crset>" 
                    + "<pkgid>pkg001</pkgid>"// ��ֵ��Ʒ����
                    + "<prodid>subproductcode002</prodid>" // ��Ʒ����
                    + "<privid></privid>" // �Żݱ���
                    + "<prodname>�Ӳ�Ʒ002</prodname>" // ��Ʒ����
                    + "<ismandatory>0</ismandatory>" // ѡ�����ͣ���ѡ�ͱ�ѡ�� 0:��ѡ 1:��ѡ
                    + "<hasattr>0</hasattr>"  // �Ƿ��и�������
                    + "<hasattr>PCIntRelaNormal</hasattr>" // ��ͨҵ��PCIntRelaNormal ҵ���л�:PCIntReladio��
                + "</crset>"
                // �Ӳ�Ʒ�µ��Żݣ�favourableCode002_001��
                + "<crset>" 
                    + "<pkgid>pkg001</pkgid>"// ��ֵ��Ʒ����
                    + "<prodid>subproductcode002</prodid>" // ��Ʒ����
                    + "<privid>favourableCode002_001</privid>" // �Żݱ���
                    + "<prodname>�Ż�001</prodname>" // ��Ʒ����
                    + "<ismandatory>0</ismandatory>" // ѡ�����ͣ���ѡ�ͱ�ѡ�� 0:��ѡ 1:��ѡ
                    + "<hasattr>1</hasattr>"  // �Ƿ��и�������
                    + "<hasattr>PCIntRelaNormal</hasattr>" // ��ͨҵ��PCIntRelaNormal ҵ���л�:PCIntReladio��
                + "</crset>"
                // �Ӳ�Ʒ�µ��Żݣ�favourableCode002_002��
                + "<crset>" 
                    + "<pkgid>pkg001</pkgid>"// ��ֵ��Ʒ����
                    + "<prodid>subproductcode002</prodid>" // ��Ʒ����
                    + "<privid>favourableCode002_002</privid>" // �Żݱ���
                    + "<prodname>�Ż�002</prodname>" // ��Ʒ����
                    + "<ismandatory>0</ismandatory>" // ѡ�����ͣ���ѡ�ͱ�ѡ�� 0:��ѡ 1:��ѡ
                    + "<hasattr>0</hasattr>"  // �Ƿ��и�������
                    + "<hasattr>PCIntRelaNormal</hasattr>" // ��ͨҵ��PCIntRelaNormal ҵ���л�:PCIntReladio��
                + "</crset>"
                
                + "<crset>" 
                    + "<pkgid>pkg001</pkgid>"// ��ֵ��Ʒ����
                    + "<prodid>subproductcode003</prodid>" // ��Ʒ����
                    + "<privid></privid>" // �Żݱ���
                    + "<prodname>�Ӳ�Ʒ003</prodname>" // ��Ʒ����
                    + "<ismandatory>1</ismandatory>" // ѡ�����ͣ���ѡ�ͱ�ѡ�� 0:��ѡ 1:��ѡ
                    + "<hasattr>1</hasattr>"  // �Ƿ��и�������
                    + "<hasattr>PCIntRelaNormal</hasattr>" // ��ͨҵ��PCIntRelaNormal ҵ���л�:PCIntReladio��
                    
                + "</crset>"
                + "</cli_qry_prodlistresp></Body></Envelope>";
        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtilNew.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rwp;
    }

    /**
     * ����ncode��ѯ�ر���ʾ��Ϣ
     * @param paramMap
     * @return
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public ReturnWrap qryNcodeTips(Map<String, String> paramMap)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><Envelope><Header version=\"1.0\"><response_head>" +
        		"<menuid>recCallDisplay</menuid><process_code>cli_qry_ncodetips</process_code><verify_code></verify_code>" +
        		"<unicontact></unicontact><resp_time>20120921105914</resp_time><sequence><req_seq>3</req_seq>" +
        		"<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode>" +
        		"<retmsg><![CDATA[]]></retmsg></retinfo></response_head></Header><Body><cli_qry_prodlistresp>" +
        		"<crset><ncode>H01</ncode><tiptype>PCTIPNORMAL</tiptype><opertype>PCOpRec</opertype><tip>43434������123</tip>" +
        		"</crset></cli_qry_prodlistresp></Body></Envelope>";
        
        ReturnWrap rwp = null;
        try
        {
            rwp = intMsgUtilNew.parseResponse(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return rwp;
    }
    
    /**
     * ���ݶ�������ѯ�ر���ʾ��Ϣ
     * @param paramMap
     * @param prods
     * @return
     * @remark create g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
     */
    public ReturnWrap qryObjectTips(Map<String, String> paramMap, List<Map<String, String>> prods)
    {
    	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><Envelope><Header version=\"1.0\"><response_head>" +
    			"<menuid>ChangeProduct</menuid><process_code>cli_qry_objecttips</process_code><verify_code></verify_code>" +
    			"<unicontact></unicontact><resp_time>20120924135727</resp_time><sequence><req_seq>1</req_seq>" +
    			"<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode>" +
    			"<retmsg><![CDATA[]]></retmsg></retinfo></response_head></Header><Body><cli_qry_objecttipsresp>" +
    			"<crset><objectid>Christmasmain</objectid><objecttype>Product</objecttype><tiptype>PCTIPNORMAL</tiptype>" +
    			"<opertype>PCOpRec</opertype><tip></tip></crset><crset><objectid>Christ_bag1</objectid>" +
    			"<objecttype>Product</objecttype><tiptype>PCTIPNORMAL</tiptype><opertype>PCOpRec</opertype>" +
    			"<tip></tip></crset></cli_qry_objecttipsresp></Body></Envelope>";
    	
    	ReturnWrap rwp = null;
        try
        {
            rwp = intMsgUtilNew.parseResponse(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return rwp;
    }
    
    /**
     * ��ѯ��ҪԤԼ���루ɽ����
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/01/23 R003C13L01n01 OR_SD_201301_279
     */
    public ReturnWrap qryChooseTelSD(Map map) {
        try {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                +"<menuid>chooseTel</menuid><process_code>cli_qry_numbynet</process_code><verify_code>"
                +"</verify_code><unicontact></unicontact><resp_time>20130122175610</resp_time><sequence>"
                +"<req_seq>14</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                +"<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                +"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                +"<tellist><telnum>15963852756</telnum><productinfo>δ֪</productinfo><fee>10000</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100000</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963857319</telnum><productinfo>δ֪</productinfo><fee>10000</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856731</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856595</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963850601</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856406</telnum><productinfo>δ֪</productinfo><fee>10000</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856407</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856410</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856418</telnum><productinfo>δ֪</productinfo><fee>10000</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856427</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856429</telnum><productinfo>δ֪</productinfo><fee>10000</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856432</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856433</telnum><productinfo>δ֪</productinfo><fee>5000</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856437</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856130</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856133</telnum><productinfo>δ֪</productinfo><fee>5000</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856144</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856145</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856146</telnum><productinfo>δ֪</productinfo><fee>5000</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856148</telnum><productinfo>δ֪</productinfo><fee>5000</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856150</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856565</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856571</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856573</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856575</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856582</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856583</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856586</telnum><productinfo>δ֪</productinfo><fee>15000</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856156</telnum><productinfo>δ֪</productinfo><fee>10000</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856157</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856160</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856170</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856172</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856173</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"<tellist><telnum>15963856175</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
                +"</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rwp = new ReturnWrap();
            rwp.setStatus(0);
            rwp.setReturnMsg("");

            return rwp;
        }
    }

    /**
     * �����ܶ��ѯ��ɽ��������ʹ��ʱ�������ӷ�����
     * @param map
     * @return
     * @remark create g00140516 2013/02/22 R003C13L02n01 OR_NX_201302_600
     */
    public ReturnWrap qryCurrBillFee(Map<String, String> map)
    {
    	return null;
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
        return null;
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
        return null;
    }
    
    /**
     * ���û���������������
     * 
     * @param map
     * @return
     */
    public ReturnWrap sendSmsHub(Map map) {
        ReturnWrap rwp = new ReturnWrap();
        rwp.setStatus(SSReturnCode.SUCCESS);

        return rwp;
    }
	
    
    /**
     * У���ֻ����Ƿ���ʵ���ƵǼ�
     */
    public ReturnWrap realNameCheck(Map map) {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid></menuid><process_code>cli_qry_userinfo</process_code><verify_code>verifyCode</verify_code>"
                + "<unicontact>unicontact</unicontact><resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><ret>0</ret>"
                + "</message_content>]]></message_body></message_response>";

        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return rwp;
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
    	String subcmdid = (String)map.get("subcmdid");
		
		String response = "";
		if("0".equals(subcmdid))
		{
			response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+"<menuid></menuid><process_code>cli_busi_pwdresetnew_nx</process_code><verify_code></verify_code>"
				+"<resp_time>20140222092348</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq>"
				+"</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
				+"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
				+"<message_content><maxfailnum></maxfailnum><curfailnum></curfailnum><leftfailnum></leftfailnum>2<remindflag>"
				+"</remindflag></message_content>]]></message_body></message_response>";
		}
		else if("1".equals(subcmdid))
		{
			response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+"<menuid>recChangePwd</menuid><process_code>cli_busi_pwdresetnew_nx</process_code><verify_code>"
				+"</verify_code><resp_time>20140222093020</resp_time><sequence><req_seq>19</req_seq><operation_seq>"
				+"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
				+"<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
				+"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><maxfailnum></maxfailnum>"
				+"<curfailnum></curfailnum><leftfailnum></leftfailnum><remindflag></remindflag></message_content>]]>"
				+"</message_body></message_response>";
		}
		else if("2".equals(subcmdid))
		{
			response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
				+"<menuid>recPasswordReset</menuid><process_code>cli_busi_pwdresetnew_nx</process_code><verify_code>"
				+"</verify_code><resp_time>20140222092137</resp_time><sequence><req_seq>6</req_seq><operation_seq></operation_seq>"
				+"</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
				+"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
				+"<message_content><maxfailnum></maxfailnum><curfailnum></curfailnum><leftfailnum></leftfailnum><remindflag>"
				+"</remindflag></message_content>]]></message_body></message_response>";
		}

	    ReturnWrap rwp = null;
	    try {
	        rwp = intMsgUtil.parseResponse(response);
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return rwp;
    }
    
    /**
     * �굥����
     * @param map
     * @return
     * @remark create by sWX219697 2014-04-29 OR_SD_201404_105_ɽ��_��������139�����굥Ͷ����Ŀ�ĺ�
     */
    public ReturnWrap sendRecordsMail(Map map)
    {
        ReturnWrap rwp = new ReturnWrap();
        rwp.setStatus(SSReturnCode.SUCCESS);

        return rwp;
    }
    
    /**
     * �ж��û��Ƿ�ͨ���ּƻ� ɽ��
     * 
     * @param map
     * @return ReturnWrap
     * @remark create by sWX219697 2014/5/12 OR_SD_201404_777_ɽ��_�����������նˡ�����__ȫ�������ֲ�ѯ���һ�����
     */
    public ReturnWrap qryIsScoreOpen(Map<String,String> map)
    {
    	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
			+"<menuid>recPasswordReset</menuid><process_code>cli_busi_pwdresetnew_nx</process_code><verify_code>"
			+"</verify_code><resp_time>20140222092137</resp_time><sequence><req_seq>6</req_seq><operation_seq></operation_seq>"
			+"</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
			+"</retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
			+"<message_content><result>0</result>"
			+"</message_content>]]></message_body></message_response>";
	    
    	ReturnWrap rwp = null;
	    try {
	        rwp = intMsgUtil.parseResponse(response);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return rwp;
    }
    
    /**
     * ��ͥ��ȡ��ҵ��ӿ�
     * 
     * @param map
     * @return �ӿڴ������
     * @remark add begin wWX217192 on 20140603 for OR_huawei_201405_875
     */
    @SuppressWarnings("unchecked")
	public ReturnWrap deleteFamilyMem(Map map)
    {
    	// ��ȷ����Ӧ����
    	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
    			"<message_response><message_head version=\"1.0\">" +
    			"<menuid>recAddFamilyMem</menuid><process_code>cli_delete_familymem_sd</process_code>" +
    			"<verify_code></verify_code><resp_time>20140611171505</resp_time>" +
    			"<sequence><req_seq>4</req_seq><operation_seq></operation_seq></sequence>" +
    			"<retinfo><rettype>0</rettype><retcode>0</retcode>" +
    			"<retmsg><![CDATA[]]></retmsg></retinfo>" +
    			"</message_head></message_response>";
    	
    	// ���ſ�ͨ�����ͥ��
    	/*String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>"
    		+ "<message_response><message_head version=\"1.0\">"
    		+ "<menuid>recAddFamilyMem</menuid><process_code>cli_delete_familymem_sd</process_code>"
    		+ "<verify_code></verify_code><resp_time>20140605172333</resp_time><sequence><req_seq>2</req_seq><operation_seq>"
    		+ "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>934382</retcode>"
    		+ "<retmsg><![CDATA[ҵ�����ʧ�ܣ�ʧ��ԭ��:�����ſ�ͨ�˶����ͥ�����޷�������]]></retmsg>"
    		+ "</retinfo></message_head></message_response>";*/
    	
    	// �û�δ��ͨҵ��
    	/*String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
    			"<message_response><message_head version=\"1.0\">" +
    			"<menuid>recAddFamilyMem</menuid>" +
    			"<process_code>cli_delete_familymem_sd</process_code>" +
    			"<verify_code></verify_code><resp_time>20140606150137</resp_time>" +
    			"<sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence>" +
    			"<retinfo><rettype>0</rettype><retcode>112</retcode>" +
    			"<retmsg><![CDATA[�û�δ��ͨҵ��]]></retmsg></retinfo>" +
    			"</message_head></message_response>";*/
    	
    	ReturnWrap rwp = null;
    	try
    	{
    		rwp = intMsgUtil.parseResponse(response);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return rwp;
    	
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
    		String endDate) {
    	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
    		+ "<menuid>qryScore</menuid><process_code>cli_qry_paymentSocre</process_code><verify_code></verify_code>" 
    		+ "<resp_time>20141022091842</resp_time><sequence><req_seq>4</req_seq><operation_seq></operation_seq>"
    		+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo>"
    		+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
    		+ "<resultList><payTime>2014-10-21 11:31:22</payTime><scoretypeid>02</scoretypeid><scoreValue>100</scoreValue>"
    		+ "<invalidTime></invalidTime><reason>������������</reason><reasonType>50</reasonType></resultList>" 
    		+ "<resultList><payTime>2014-10-21 11:32:47</payTime><scoretypeid>02</scoretypeid><scoreValue>200</scoreValue>"
    		+ "<invalidTime></invalidTime><reason>������������</reason><reasonType>50</reasonType></resultList></message_content>]]></message_body></message_response>";
	    
    	ReturnWrap rwp = null;
	    try {
	        rwp = intMsgUtil.parseResponse(response);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return rwp;
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
    	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
    		+ "<menuid>qryScore</menuid><process_code>cli_qry_scorevalueSD</process_code><verify_code></verify_code>" 
    		+ "<resp_time>20141022091147</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq>"
    		+ "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" 
    		+ "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" 
    		+ "<pointBalance>300</pointBalance><userBrand>BrandGotone</userBrand><userLevel>VC1400</userLevel>" 
    		+ "<userStatus>US10</userStatus><resultList><col_0>02</col_0><col_1>300</col_1><col_2></col_2>" 
    		+ "</resultList></message_content>]]></message_body></message_response>";
	    
    	ReturnWrap rwp = null;
	    try {
	        rwp = intMsgUtil.parseResponse(response);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return rwp;
    }
    
    /**
     * ɾ����ͥ����Ա
     * @param header ������ͷ
     * @param memTelnum ��Ա�ֻ���
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap delMemByTelnum(MsgHeaderPO header, String memTelnum)
    {
    	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
    			"<message_response>" +
    			"<message_head version=\"1.0\">" +
    			"<menuid>recAddFamilyMem</menuid>" +
    			"<process_code>cli_delfamilymember</process_code>" +
    			"<verify_code></verify_code>" +
    			"<resp_time>20150206153941</resp_time>" +
    			"<sequence>" +
    			"<req_seq>4</req_seq><operation_seq></operation_seq>" +
    			"</sequence>" +
    			"<retinfo>" +
    			"<rettype>0</rettype>" +
    			"<retcode>100</retcode>" +
    			"<retmsg><![CDATA[Processing the request succeeded!]]></retmsg>" +
    			"</retinfo>" +
    			"</message_head>" +
    			"<message_body>" +
    			"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
    			"<message_content><result>1</result></message_content>]]>" +
    			"</message_body>" +
    			"</message_response>";
    	
    	ReturnWrap rwp = null;
	    try {
	        rwp = intMsgUtil.parseResponse(response);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return rwp;
    }
    

    /**
	 * ��ѯ��ԤԼ�����б�
	 * @param requestMsg
	 * @return
	 * @remark create by wWX217192 2015-03-10 OR_SD_201411_988_SD_���������ն�ѡ�Ź����Ż�������
	 */
	public String queryNumResp(OMElement requestMsg)
	{
		String responseMsg = "<ns:queryNumResponse xmlns:ns=\"http://num.webservice.emall.huawei.com\"><ns:return>" 
				+ "&lt;?xml version='1.0' encoding='UTF-8'?>"
				+ "&lt;message>"
				+ "&lt;returnCode>100&lt;/returnCode>"
				+ "&lt;numList>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863915077&lt;/telnum>"
				+ "&lt;minCost>5000&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863870061&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>200&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863910073&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>200&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15853960062&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>200&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15853922323&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863915277&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863935377&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo&gt;"
				+ "&lt;telnum>15853991753&lt;/telnum>"
				+ "&lt;minCost>30&lt;/minCost>"
				+ "&lt;preFee>100&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15866950277&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863965753&lt;/telnum>"
				+ "&lt;minCost>30&lt;/minCost>"
				+ "&lt;preFee>100&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15853915266&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863860057&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>200&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863953577&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15853951155&lt;/telnum>"
				+ "&lt;minCost>80&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863856622&lt;/telnum>"
				+ "&lt;minCost>80&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15866971566&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15853963975&lt;/telnum>"
				+ "&lt;minCost>30&lt;/minCost>"
				+ "&lt;preFee>100&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15853952077&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15866971551&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15866915050&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863880093&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>200&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863902009&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>200&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15866921766&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15806909911&lt;/telnum>"
				+ "&lt;minCost>80&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863907066&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15806935277&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15853850052&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>200&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15853850135&lt;/telnum>"
				+ "&lt;minCost>30&lt;/minCost>"
				+ "&lt;preFee>100&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15853958135&lt;/telnum>"
				+ "&lt;minCost>30&lt;/minCost>"
				+ "&lt;preFee>100&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15866905366&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15853920550&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15806920975&lt;/telnum>"
				+ "&lt;minCost>30&lt;/minCost>"
				+ "&lt;preFee>100&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15866209969&lt;/telnum>"
				+ "&lt;minCost>80&lt;/minCost>"
				+ "&lt;preFee>800&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15866931066&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863905335&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15806923975&lt;/telnum>"
				+ "&lt;minCost>30&lt;/minCost>"
				+ "&lt;preFee>100&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15865963232&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863928753&lt;/telnum>"
				+ "&lt;minCost>30&lt;/minCost>"
				+ "&lt;preFee>100&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15866902577&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863969900&lt;/telnum>"
				+ "&lt;minCost>80&lt;/minCost>"
				+ "&lt;preFee>300&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15853851003&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>200&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863876002&lt;/telnum>"
				+ "&lt;minCost>50&lt;/minCost>"
				+ "&lt;preFee>200&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo&gt;"
				+ "&lt;numInfo>"
				+ "&lt;telnum>15863926636&lt;/telnum>"
				+ "&lt;minCost>80&lt;/minCost>"
				+ "&lt;preFee>800&lt;/preFee>"
				+ "&lt;signTime>5&lt;/signTime>"
				+ "&lt;/numInfo>"
				+ "&lt;/numList>"
				+ "&lt;/message></ns:return></ns:queryNumResponse>";
		
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
		String responseMsg = "<ns:queryNumResponse xmlns:ns=\"http://num.webservice.emall.huawei.com\">"
			+ "<ns:return><?xml version='1.0' encoding='UTF-8'?>"
			+ "<message><returnCode>100</returnCode>"
			+ "<vprodId>15806619824</vprodId>"
			+ "<errorMsg>0</errorMsg>"
			+ "</message>"
			+ "</ns:return></ns:queryNumResponse>";
		
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
		String responseMsg = "<ns:queryNumResponse xmlns:ns=\"http://num.webservice.emall.huawei.com\">"
			+ "<ns:return><?xml version='1.0' encoding='UTF-8'?>"
			+ "<message><returnCode>100</returnCode>"
			+ "<errorMsg>0</errorMsg>"
			+ "</message>"
			+ "</ns:return></ns:queryNumResponse>";
		
		return responseMsg;
	}
	
	/**
     * ��ѯ�ͻ�Ӧ���ܽ�� ������ֱ�ӵ���һ��boss�ӿ�
     * @param msgHeader
     * @param orgid
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-3-27 18:01:22 
     */
    public ReturnWrap qryPayAmount(MsgHeaderPO msgHeader, String orgid)
    {
        try
        {
    		String resXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<QryPayRsp>" +
			"<HSNDUNS>598</HSNDUNS>" +
			"<IDType>��ʶ����</IDType>" +
			"<IDValue>��ʶ����</IDValue>" +
			"<BizOrderResult>0000</BizOrderResult>" +
			"<ResultDesc>��������</ResultDesc>" +
			"<QryPayInfo>" +
			"<CustName>�ͻ�����</CustName>" +
			"<PayAmount>1000000</PayAmount>" +
			"<Balance>100000</Balance>" +
			"</QryPayInfo>" +
			"</QryPayRsp>";
        	
        	//��װ���ض���
        	ReturnWrap retData = new ReturnWrap();
        	
        	//�������ر���
        	Document resDoc = DocumentHelper.parseText(resXML);
    		Element root = resDoc.getRootElement();
    		
    		//��������Ϊ0000�����ʾ�ӿڵ��óɹ�
    		if(SSReturnCode.INTERBOSS_SUCCESS.equals(root.element("BizOrderResult").getTextTrim()))
    		{
    			//����״̬
    			retData.setStatus(SSReturnCode.SUCCESS);
    			
    			//��װCTagSet
    			CTagSet tagSet = new CTagSet();
    			
    			//ʡ�ݱ���
    			tagSet.SetValue("ProvinceCode", root.element("HSNDUNS").getTextTrim());
    			
        		//������װӦ�ɷ��õ���Ϣ
    			Element qryPayInfo = root.element("QryPayInfo");
    			
    			//�ͻ�����
    			tagSet.SetValue("CustName", qryPayInfo.element("CustName").getTextTrim());
    			
    			//Ӧ�ɷ���
    			tagSet.SetValue("PayAmount", qryPayInfo.element("PayAmount").getTextTrim());
    			
    			//�ͻ�Ԥ����
    			tagSet.SetValue("Balance", qryPayInfo.element("Balance").getTextTrim());

    			retData.setReturnObject(tagSet);
    		}
    		else
    		{
    			retData.setStatus(SSReturnCode.ERROR);
    			
        		//������Ϣ
        		retData.setReturnMsg(root.element("ResultDesc").getTextTrim());
    		}

    		return retData;
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR,"��ѯ�ͻ�Ӧ���ܽ���쳣��");
        }
        
    }
    
    /**
     * ��ʡ�ɷ��ύ
     * <������ϸ����>
     * @param msgHeader
     * @param seq ���������ˮ��
     * @param actualPayAmount ʵ�ʽɷѽ��壩
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-4-2 09:58:49 OR_NX_201501_1030_����_���ڿ�������ҵ��֧��ϵͳ�����֪ͨ
     */
    public ReturnWrap nonlocalCharge(MsgHeaderPO msgHeader, String seq, String actualPayAmount, String orgid)
    {
        try
        {
            // ����һ��boss�ӿڣ����н���
            String resXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            		"<PaymentRsp>" +
            		"<HSNDUNS>5310</HSNDUNS>" +
            		"<IDType>��ʶ����</IDType>" +
            		"<IDValue>��ʶ����</IDValue>" +
            		"<BizOrderResult>0000</BizOrderResult>" +
            		"</PaymentRsp>";
            
        	//��װ���ض���
        	ReturnWrap retData = new ReturnWrap();
        	
        	//�������ر���
        	Document resDoc = DocumentHelper.parseText(resXML);
    		Element resRoot = resDoc.getRootElement();
        	
    		//�ӿڵ��óɹ�
        	if(SSReturnCode.INTERBOSS_SUCCESS.equals(resRoot.element("BizOrderResult").getTextTrim()))
        	{
        		retData.setStatus(SSReturnCode.SUCCESS);
        		retData.setReturnMsg("���ѳɹ�");
        	}
        	else
        	{
        		retData.setStatus(SSReturnCode.ERROR);
        		retData.setReturnMsg(resRoot.element("ResultDesc").getTextTrim());
        	}
        	
        	return retData;
        }
        catch (Exception e)
        {
            
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
        	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
        		+ "<message_head version=\"1.0\">"
        		+ "<menuid>recOrderSP</menuid>"
        		+ "<process_code>cli_sd_ChangeSubsMonServ</process_code>"
        		+ "<verify_code></verify_code>"
        		+ "<resp_time>20150409113534</resp_time>"
        		+ "<sequence>"
        		+ "<req_seq>3</req_seq>"
        		+ "<operation_seq></operation_seq>"
        		+ "</sequence>"
        		+ "<retinfo>"
				+ "<rettype>0</rettype>"
				+ "<retcode>0</retcode>"
				+ "<retmsg>"
				+ "<![CDATA[]]>"
				+ "</retmsg>"
				+ "</retinfo>"
				+ "</message_head>"
				+ "</message_response>";
            
            ReturnWrap rwp = null;
    	    try {
    	        rwp = intMsgUtil.parseResponse(response);
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    return rwp;
        }
        catch (Exception e)
        {
            return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "��ͥ����Աɾ���쳣");
        }
    }
	
    /**
     * <�мۿ�У��>
     * <������ϸ����>
     * @param msgHeader ��Ϣͷ
     * @param paramMap ��Ϣ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-05-11 OR_HUB_201503_1068_HUB_������ϼ��š������·�__���ӻ��мۿ�ҵ��ʡ��ϵͳ���췽����֪ͨ���ĸ������������ն˸���
     */
    public ReturnWrap authValueCard(MsgHeaderPO msgHeader, Map<String, String> paramMap)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
                + "<menuid>qryScore</menuid><process_code>cli_busi_scoreExchangeSD</process_code><verify_code></verify_code>" 
                + "<resp_time>20141022091147</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" 
                + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" 
                + "<isElecCard>1</isElecCard></message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
           return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "�мۿ�У���쳣");
        }
    }
    
    /**
     * <�мۿ���ֵ>
     * <������ϸ����>
     * @param msgHeader ��Ϣͷ
     * @param paramMap ��Ϣ��
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-05-11 OR_HUB_201503_1068_HUB_������ϼ��š������·�__���ӻ��мۿ�ҵ��ʡ��ϵͳ���췽����֪ͨ���ĸ������������ն˸���
     */
    public ReturnWrap valueCardCharge(MsgHeaderPO msgHeader, String valueCardPwd)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
                + "<menuid>qryScore</menuid><process_code>cli_busi_scoreExchangeSD</process_code><verify_code></verify_code>" 
                + "<resp_time>20141022091147</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" 
                + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" 
                + "<chargeResult>1</chargeResult><countTotal>1</countTotal><cardType>1</cardType><cardBusinessPro>1</cardBusinessPro>" +
                "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
           return CommonUtil.getReturnWrap(SSReturnCode.ERROR, "�мۿ�У���쳣");
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
		String response = "{transActionID:'1234567890978',crmFormNum:'54321678907',cardList:[" +
				"{cardNo:'111111111111',cardPwd:'1111111111111',elecCardCntTotal:'30Ԫ',cardDate:'2015-06-01',CardType:'���ѳ�ֵ��',CardBusiPro:'30Ԫ����'}," +
				"{cardNo:'222222222222',cardPwd:'2222222222222',elecCardCntTotal:'30Ԫ',cardDate:'2015-06-01',CardType:'���ѳ�ֵ��',CardBusiPro:'30Ԫ����'}," +
				"{cardNo:'333333333333',cardPwd:'3333333333333',elecCardCntTotal:'30Ԫ',cardDate:'2015-06-01',CardType:'���ѳ�ֵ��',CardBusiPro:'30Ԫ����'}]}";
        
        return getIntMsgUtil().parseJsonResponse(response, null, null);
	}
	
    /**
     * <ɽ�����ֶһ�����ȯ>
     * <������ϸ����>
     * @param header
     * @param piciId ���α���
     * @param dangciId ���α���
     * @param rewardId ��Ʒ����
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-5-29 14:44:35 OR_SD_201505_61�����ն������ӻ��ֶһ�����ȯ
     */
    public ReturnWrap scoreExchange(MsgHeaderPO header,  Document doc)
    {
        String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" 
            + "<menuid>qryScore</menuid><process_code>cli_busi_scoreExchangeSD</process_code><verify_code></verify_code>" 
            + "<resp_time>20141022091147</resp_time><sequence><req_seq>3</req_seq><operation_seq></operation_seq>"
            + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[]]></retmsg></retinfo>" 
            + "</message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" 
            + "<recoid>1113699787498</recoid></message_content>]]></message_body></message_response>";
        
        ReturnWrap rwp = null;
        try {
            rwp = intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rwp;
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
    @Override
	public ReturnWrap sendAddress(MsgHeaderPO header, Document doc) 
    {
    	ReturnWrap rwp = new ReturnWrap();
        rwp.setStatus(SSReturnCode.SUCCESS);

        return rwp;
	}
    
    /**
     * <�û���¼��֤>
     * <������ϸ����>
     * @param header
     * @param doc
     * @return
     * @see [�ࡢ��#��������#��Ա]
     * @remark create by jWX216858 2015-7-10 OR_SD_201506_152_ɽ��_�������ն����ӡ�����APPӦ�á�����
     */
    public ReturnWrap customerLogin(MsgHeaderPO header, Document doc)
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

			ReturnWrap rwp = new ReturnWrap();
			rwp.setStatus(0);
			rwp.setReturnMsg("");

			return rwp;
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
	 * @return the intMsgUtil
	 */
	public IntMsgUtil getIntMsgUtil() {
		
		return intMsgUtil;
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
}